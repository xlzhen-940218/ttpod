# 修复「歌词找不到」与「歌曲封面不显示」

## Context

- 歌词：`LyricSearchTask` 现在只接 `lrclib.net`，华语曲库覆盖差，很多中文歌搜不到；旧的 `lrc.ttpod.com` 已失效。
- 封面：`PictureSearchTask` 的在线源是 `api.deezer.com`，本机/设备网络不可达，导致在线封面全部失败；同时存在三个叠加问题：
  1. `mo2129b()` 内嵌封面判断逻辑写反（有效图片返回 null，无效文件返回路径）；
  2. `Preferences.m3072E()`（显示内嵌图片）默认 false，内嵌封面默认完全不展示；
  3. 本地兜底只扫描 `artist/<歌手>/` 全部图片，不识别歌曲所在文件夹的 `cover.jpg/folder.jpg`。
- 已验证可用接口（curl 实测 200）：
  - 搜索：`https://music.163.com/api/search/get/web?s=<q>&type=1&offset=0&total=true&limit=10`
  - 歌曲详情（取封面）：`https://music.163.com/api/song/detail?ids=[<id>]` → `songs[0].album.picUrl`
  - 歌词：`https://music.163.com/api/song/lyric?id=<id>&lv=1&kv=1&tv=-1` → `lrc.lyric`
  - 备选歌词源：`https://lrclib.net/api/search?q=`、`https://lrclib.net/api/get/<id>`
- 现有链路（不改动）：任务广播 `Action.LYRIC_PIC_OPERATE_RESULT` → `SearchModule` → `Cache.m3205a` / `Preferences.m2932b` → `MiniLyricManager` 刷新歌词、`BasePlayerFragment` 刷新封面。因此本轮只需要保证任务能搜到、能落盘、能广播。

## 实施步骤

### 1. `framework/support/search/task/LyrPicBaseSearchTask.java`
- `requestData(String url)` 增加请求头：`User-Agent: Mozilla/5.0 (Android ...)`、`Referer: https://music.163.com/`，沿用现有 10s 超时。
- 新增 `protected byte[] requestBytes(String url)`：同样的请求头与超时，返回原始字节（复用 `getBytesByInputStream`），供封面二进制下载使用。
- 新增静态工具方法：
  - `normalizeSearchText(String)`：小写化、去掉括号内容（中英文括号）、去除空白与常见标点。
  - `isTitleMatched(String a, String b)`：归一化后相等或互相包含。
  - `isArtistMatched(String queryArtist, String resultArtist)`：任一为空视为通过；否则归一化后互相包含。

### 2. `framework/support/search/task/LyricSearchTask.java`
- `requestResultDataArrayList(...)` 改为「网易云优先 + lrclib 兜底」：
  1. 用 `lyricSearchTaskInfo.getTitle()` + `getSinger()` 调网易云搜索；标题/歌手为空时退回 `mediaItem` 字段。
  2. 解析 `result.songs[]`，按 `isTitleMatched`/`isArtistMatched` 过滤，按「标题完全匹配 > 标题包含 > 歌手匹配」打分排序，最多取 5 条。
  3. 每条生成 `ResultData`（title/artist/album）与 `Item("lrc", "netease:<id>", <lyricPath>/<artist> - <title>.lrc, id & 0x7fffffff)`；本地文件名继续复用现有 `m2145b()`。
  4. 网易云无结果时再走现有 lrclib 逻辑；先查 `title artist`，为空再用纯 `title` 重试，最多 5 条（`lrclib:` 前缀不变）。
- `downloadLyric(Item, boolean)` 增加 `netease:` 分支（与现有 `lrclib:` 分支结构一致，在 `TaskScheduler` 后台线程执行）：
  - GET 歌词接口，取 `lrc.lyric`；为空或 `nolyric=true` 视为失败。
  - `FileUtils.createFolder(new File(item.getLocalLyricPath()).getParent())` 后 `FileUtils.writeStringToFile(lrc, path)`。
  - 成功后广播 `SEARCH_DOWNLOAD_FINISHED`（含 `download_result_list`），`z` 为真时再 `m2164a(SearchManager.SEARCH_ONLINE_FINISHED)`；失败按原逻辑广播失败。
- 保留 `lrclib:` 分支与 `super.downloadLyric(...)` 兜底。

### 3. `framework/support/search/task/PictureSearchTask.java`
- 重写 `mo2129b(MediaItem)` 修复反转逻辑：
  1. 先判断 `getCacheEmbedPath()/<hash>.jpg` 是否可解码（`m2125c`），是则直接返回。
  2. 用 `MediaTag.createMediaTag(localDataSource, true)` 取 `cover()`，写完缓存文件后再校验，有效则返回。
  3. 无效时调用新增私有方法 `findFolderArtwork(localDataSource)`：扫描歌曲所在目录的 `cover/folder/album/albumart/albumart1/front/artwork` 及与音频同名的图片（jpg/jpeg/png/webp，大小写不敏感），用 `m2125c` 校验。
  4. 都找不到返回 `null`（不再返回无效路径）。
- `requestResultDataArrayList(...)` 用网易云替换 Deezer：
  1. 查询串优先 `title + " " + artist`，无结果再用 `artist`。
  2. 选最佳匹配（复用第 1 步的匹配方法），调 `/api/song/detail?ids=[<id>]` 取 `album.picUrl`，`http://` 统一替换为 `https://`。
  3. 本地路径：`getArtistPath()/<去非法字符的歌手>/<neteaseId>.jpg`，`Item("album", picUrl, localPath, neteaseId & 0x7fffffff)`；返回单条即可（`startDownloadLyric` 只取第一条）。
- 重写 `downloadLyric(Item, boolean)`（覆盖基类）：后台线程用 `requestBytes()` 下载，`BitmapFactory` 校验有效后写入本地路径（先 `FileUtils.createFolder` 建目录），成功广播 `SEARCH_DOWNLOAD_FINISHED`（`z` 时再 `SEARCH_ONLINE_FINISHED`），失败广播 `SEARCH_DOWNLOAD_FAILURE`。避免 Download Manager 对 https/重定向/Hotlink 的兼容问题。
- `mo2135a(String, String)` 收紧本地兜底：只保留 `jpg/jpeg/png/webp` 且 `m2125c` 可解码的文件，按 `lastModified` 倒序返回，避免把 `result.xml` 之类文件当封面、并优先使用最近下载的图。

### 4. `framework/storage/environment/Preferences.java`
- `m3072E()` 的默认值由 `false` 改为 `true`，让内嵌/文件夹封面开箱即可显示（设置页开关保持可关闭）。

### 5. `adapter/PictureManagerAdapter.java`（手动「更换歌手图片」页，旧 `picdown.ttpod.cn` 已失效）
- 在 `PictureSearchTask` 增加静态 `searchArtistPictures(MediaItem, String artist)`：用网易云按歌手/歌名搜索，返回多条 `ResultData`（每条含一个封面 `Item`）。
- `m7693a(String artist)` 的异步体改为调用该静态方法，删除 `m7688b` 拼出的旧 XML 请求与 `KXmlParser` 解析。
- `m7694a(...)` 不再依赖已不存在的 `artist/<歌>/result.xml` 判断，改为直接触发一次在线搜索（`f3128f` 防重入）。
- 删除 `m2138a()` 中对 `picdown.ttpod.cn` 的拼接（若仍被其他地方引用则保留方法但内部改为网易云搜索 URL，仅作日志用途）。

### 6. 构建验证
- `.\gradlew.bat assembleDebug --console=plain -q`，确认编译通过。

## 验证方式

1. 装机后播放含内嵌封面的 MP3（关闭 WiFi 也可），播放页应立即出现封面。
2. 播放无内嵌封面的歌曲、且同目录有 `cover.jpg`，播放页应显示该图。
3. 播放一首中文歌（本地无 `.lrc/.trc`），联网后应在 `/sdcard/ttpod/lyric/` 下生成 `歌手 - 歌名.lrc`，并出现滚动歌词。
4. 播放无内嵌封面且目录无图的歌曲，联网后应在 `/sdcard/ttpod/artist/<歌手>/<id>.jpg` 生成网易云专辑封面并显示。
5. 打开「更换歌手图片」页，搜索歌手应返回网易云封面列表，点击可切换封面。
6. 断网播放已有内嵌封面歌曲，封面仍可显示（不依赖网络）。
