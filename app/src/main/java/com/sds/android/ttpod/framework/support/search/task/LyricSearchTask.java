package com.sds.android.ttpod.framework.support.search.task;

import android.text.TextUtils;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.support.search.p135a.LyricSearchTaskInfo;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.support.search.task.b */
/* loaded from: classes.dex */
public class LyricSearchTask extends LyrPicBaseSearchTask {

    /* renamed from: a */
    private LyricSearchTaskInfo lyricSearchTaskInfo;

    public LyricSearchTask(LyricSearchTaskInfo lyricSearchTaskInfo) {
        this.lyricSearchTaskInfo = lyricSearchTaskInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: f */
    public LyricSearchTaskInfo getLyricSearchTaskInfo() {
        return this.lyricSearchTaskInfo;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected boolean mo2133a(ArrayList<String> arrayList) {
        return true;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: b */
    protected String mo2129b(MediaItem mediaItem) {
        return null;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected ArrayList<ResultData> mo2140a(KXmlParser kXmlParser) throws Exception {
        int next;
        ArrayList<ResultData> arrayList = new ArrayList<>();
        if (getLyricSearchTaskInfo().getMediaItem() == null) {
            return arrayList;
        }
        kXmlParser.nextTag();
        kXmlParser.require(2, null, "lrc_list");
        do {
            next = kXmlParser.next();
            switch (next) {
                case 2:
                    if ("lrc".equals(kXmlParser.getName())) {
                        ResultData resultData = new ResultData();
                        resultData.title = kXmlParser.getAttributeValue(null, "title");
                        resultData.artist = kXmlParser.getAttributeValue(null, "artist");
                        String lyricPath = !getLyricSearchTaskInfo().getMediaItem().isOnline() ? m2143g() : null;
                        if (lyricPath == null) {
                            lyricPath = TTPodConfig.getLyricPath() + File.separator + m2145b(resultData.artist, resultData.title);
                        }
                        String attributeValue = kXmlParser.getAttributeValue(null, "lrcID");
                        int parseInt = Integer.parseInt(kXmlParser.getAttributeValue(null, "trc"));
                        ResultData.Item[] itemArr = new ResultData.Item[1];
                        itemArr[0] = new ResultData.Item(parseInt == 0 ? "lrc" : "trc"
                                , m2147a(resultData.title, resultData.artist, attributeValue)
                                , lyricPath + (parseInt == 0 ? ".lrc" : ".trc")
                                , Integer.parseInt(attributeValue));
                        resultData.lyricArray = itemArr;
                        arrayList.add(resultData);
                        if (this.lyricSearchTaskInfo.isAuto()) {
                            break;
                        } else {
                            return arrayList;
                        }
                    } else {
                        kXmlParser.m3910a();
                        continue;
                    }
            }
        } while (next != 1);
        return arrayList;
    }

    /* renamed from: g */
    private String m2143g() {
        String m2145b = m2145b((String) null, (String) null);
        String[] h = getLyricSearchTaskInfo().getSongInfo();
        if (m2145b == null) {
            m2145b = FileUtils.removeWrongCharacter(h[1]);
            if (h[3] != null) {
                m2145b = m2145b + "_" + h[3];
            }
        }
        return TTPodConfig.getLyricPath() + File.separator + m2145b;
    }

    /* renamed from: a */
    private String m2147a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("http://lrc.ttpod.com/down?");
            sb.append("lrcid=");
            sb.append(URLEncoder.encode(str3, "UTF-8"));
            sb.append("&code=");
            sb.append(TTTextUtils.decryptLyricKey(str, str2, Integer.parseInt(str3)));
            sb.append("&s=");
            sb.append(EnvironmentUtils.UUIDConfig.getS());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public void downloadLyric(final ResultData.Item item, final boolean z) {
        if (item.getUrl() != null && item.getUrl().startsWith("netease:")) {
            com.sds.android.sdk.lib.p065e.TaskScheduler.start(new Runnable() {
                @Override
                public void run() {
                    try {
                        m2155b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_STARTED);
                        String id = item.getUrl().substring("netease:".length());
                        String json = requestData("https://music.163.com/api/song/lyric?id=" + id + "&lv=1&kv=1&tv=-1");
                        String lrc = parseNeteaseLyric(json);
                        if (!TextUtils.isEmpty(lrc)) {
                            File lrcFile = new File(item.getLocalLyricPath());
                            File parent = lrcFile.getParentFile();
                            if (parent != null) {
                                FileUtils.createFolder(parent.getAbsolutePath());
                            }
                            FileUtils.writeStringToFile(lrc, item.getLocalLyricPath());
                            ArrayList<String> list = new ArrayList<>();
                            list.add(item.getLocalLyricPath());
                            m2154b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FINISHED, null, list, item.getId());
                            if (z) {
                                m2164a(SearchStatus.SEARCH_ONLINE_FINISHED);
                            }
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    m2154b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FAILURE, null, null, item.getId());
                    if (z) {
                        m2164a(SearchStatus.SEARCH_ONLINE_FAILURE);
                    }
                }
            });
        } else if (item.getUrl() != null && item.getUrl().startsWith("lrclib:")) {
            com.sds.android.sdk.lib.p065e.TaskScheduler.start(new Runnable() {
                @Override
                public void run() {
                    try {
                        m2155b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_STARTED);
                        String id = item.getUrl().substring("lrclib:".length());
                        String json = requestData("https://lrclib.net/api/get/" + id);
                        if (!TextUtils.isEmpty(json)) {
                            com.google.gson.JsonObject obj = com.google.gson.JsonParser.parseString(json).getAsJsonObject();
                            String lrc = "";
                            if (obj != null) {
                                if (obj.has("syncedLyrics") && !obj.get("syncedLyrics").isJsonNull()) {
                                    lrc = obj.get("syncedLyrics").getAsString();
                                } else if (obj.has("plainLyrics") && !obj.get("plainLyrics").isJsonNull()) {
                                    lrc = obj.get("plainLyrics").getAsString();
                                }
                            }

                            if (!TextUtils.isEmpty(lrc)) {
                                FileUtils.writeStringToFile(lrc, item.getLocalLyricPath());
                                ArrayList<String> list = new ArrayList<>();
                                list.add(item.getLocalLyricPath());
                                m2154b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FINISHED, null, list, item.getId());
                                if (z) {
                                    m2164a(SearchStatus.SEARCH_ONLINE_FINISHED);
                                }
                                return;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    m2154b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FAILURE, null, null, item.getId());
                    if (z) {
                        m2164a(SearchStatus.SEARCH_ONLINE_FAILURE);
                    }
                }
            });
        } else {
            super.downloadLyric(item, z);
        }
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected void startDownloadLyric(List<ResultData> list) {
        downloadLyric(list.get(0).getLyricArray()[0], true);
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String getMediaItem(MediaItem mediaItem) {
        return null;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String buildUrl() {
        StringBuilder sb = new StringBuilder();
        MediaItem mediaItem = this.lyricSearchTaskInfo.getMediaItem();
        try {
            sb.append("http://lrc.ttpod.com/search?");
            String title = mediaItem.getTitle();
            if (this.lyricSearchTaskInfo.isAuto() && !TextUtils.isEmpty(this.lyricSearchTaskInfo.m2197f())) {
                title = this.lyricSearchTaskInfo.m2197f();
            }
            if (!TTTextUtils.isValidateMediaString(title)) {
                title = "";
            }
            sb.append("title=");
            sb.append(URLEncoder.encode(title, "UTF-8"));
            getLyricSearchTaskInfo().setTitle(title);
            String artist = mediaItem.getArtist();
            if (this.lyricSearchTaskInfo.isAuto() && !TextUtils.isEmpty(this.lyricSearchTaskInfo.m2196g())) {
                artist = this.lyricSearchTaskInfo.m2196g();
            }
            if (!TTTextUtils.isValidateMediaString(artist)) {
                artist = "";
            }
            sb.append("&artist=");
            sb.append(URLEncoder.encode(artist, "UTF-8"));
            getLyricSearchTaskInfo().setSinger(artist);
            if (!mediaItem.isOnline()) {
                sb.append("&filename=");
                sb.append(URLEncoder.encode(this.lyricSearchTaskInfo.getSongInfo()[1], "UTF-8"));
            }
            sb.append("&duration=");
            sb.append(mediaItem.getDuration());
            sb.append("&bitrate=");
            sb.append(mediaItem.getBitRate());
            sb.append("&srate=");
            sb.append(mediaItem.getSampleRate());
            if (!this.lyricSearchTaskInfo.isAuto() && mediaItem.isOnline()) {
                sb.append("&song_id=");
                sb.append(mediaItem.getSongID());
                long artistID = mediaItem.getArtistID();
                if (artistID != 0) {
                    sb.append("&singer_id=");
                    sb.append(artistID);
                }
            }
            sb.append("&raw=2");
            sb.append("&trc=1");
            sb.append("&auto=");
            sb.append(this.lyricSearchTaskInfo.isAuto() ? 0 : 1);
            sb.append("&s=");
            sb.append(EnvironmentUtils.UUIDConfig.getS());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected ArrayList<ResultData> requestResultDataArrayList(String initialUrl) {
        ArrayList<ResultData> resultDataArrayList = new ArrayList<>();
        MediaItem mediaItem = lyricSearchTaskInfo.getMediaItem();
        String title = lyricSearchTaskInfo.getTitle();
        if (TextUtils.isEmpty(title) && mediaItem != null) {
            title = mediaItem.getTitle();
        }
        String artist = lyricSearchTaskInfo.getSinger();
        if (TextUtils.isEmpty(artist) && mediaItem != null) {
            artist = mediaItem.getArtist();
        }
        if (TextUtils.isEmpty(title)) {
            return resultDataArrayList;
        }

        // 网易云曲库对华语歌曲覆盖更好，优先使用；无结果时再用 lrclib 兜底
        searchNeteaseLyric(title, artist, resultDataArrayList);
        if (resultDataArrayList.isEmpty()) {
            searchLrclibLyric(title, artist, resultDataArrayList);
        }
        return resultDataArrayList;
    }

    /* loaded from: classes.dex */
    private static class NeteaseSong {
        long id;
        String name;
        String artist;
        String album;
        int score;

        NeteaseSong(long id, String name, String artist, String album, int score) {
            this.id = id;
            this.name = name;
            this.artist = artist;
            this.album = album;
            this.score = score;
        }
    }

    private void searchNeteaseLyric(String title, String artist, ArrayList<ResultData> out) {
        try {
            ArrayList<NeteaseSong> candidates = new ArrayList<>();
            String primaryQuery = TextUtils.isEmpty(artist) ? title : title + " " + artist;
            appendNeteaseCandidates(primaryQuery, title, artist, candidates);
            boolean artistFound = false;
            for (NeteaseSong candidate : candidates) {
                if (isArtistMatched(artist, candidate.artist) && !TextUtils.isEmpty(artist)) {
                    artistFound = true;
                    break;
                }
            }
            // 首次查询若没有命中目标歌手（常见于同名翻唱排在前面），再用纯歌名查一次
            if (!artistFound && !TextUtils.isEmpty(artist)) {
                appendNeteaseCandidates(title, title, artist, candidates);
            }

            java.util.HashSet<Long> addedIds = new java.util.HashSet<>();
            ArrayList<NeteaseSong> distinct = new ArrayList<>();
            for (NeteaseSong candidate : candidates) {
                if (addedIds.add(candidate.id)) {
                    distinct.add(candidate);
                }
            }
            java.util.Collections.sort(distinct, new java.util.Comparator<NeteaseSong>() {
                @Override
                public int compare(NeteaseSong a, NeteaseSong b) {
                    return b.score - a.score;
                }
            });
            for (NeteaseSong candidate : distinct) {
                if (out.size() >= 5) {
                    break;
                }
                ResultData resultData = new ResultData();
                resultData.setTitle(candidate.name);
                resultData.setArtist(candidate.artist);
                resultData.setAlbum(candidate.album);
                String localLyricPath = TTPodConfig.getLyricPath() + File.separator
                        + m2145b(candidate.artist, candidate.name);
                ResultData.Item[] items = new ResultData.Item[1];
                items[0] = new ResultData.Item("lrc", "netease:" + candidate.id,
                        localLyricPath + ".lrc", (int) (candidate.id & 0x7fffffff));
                resultData.setLyricArray(items);
                out.add(resultData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void appendNeteaseCandidates(String query, String title, String artist,
                                         ArrayList<NeteaseSong> candidates) {
        try {
            String searchUrl = "https://music.163.com/api/search/get/web?s="
                    + URLEncoder.encode(query, "UTF-8")
                    + "&type=1&offset=0&total=true&limit=10";
            String json = requestData(searchUrl);
            if (TextUtils.isEmpty(json)) {
                return;
            }
            com.google.gson.JsonObject root = com.google.gson.JsonParser.parseString(json).getAsJsonObject();
            if (root == null || !root.has("result") || root.get("result").isJsonNull()) {
                return;
            }
            com.google.gson.JsonObject resultObj = root.getAsJsonObject("result");
            if (resultObj == null || !resultObj.has("songs") || resultObj.get("songs").isJsonNull()) {
                return;
            }
            com.google.gson.JsonArray songs = resultObj.getAsJsonArray("songs");
            String normalizedTitle = normalizeSearchText(title);
            for (int i = 0; i < songs.size(); i++) {
                com.google.gson.JsonElement element = songs.get(i);
                if (element == null || !element.isJsonObject()) {
                    continue;
                }
                com.google.gson.JsonObject song = element.getAsJsonObject();
                if (!song.has("id") || song.get("id").isJsonNull()) {
                    continue;
                }
                String name = stringValue(song, "name");
                String songArtist = joinArtists(song);
                if (!isTitleMatched(title, name)) {
                    continue;
                }
                int score = normalizedTitle.equals(normalizeSearchText(name)) ? 20 : 10;
                if (!TextUtils.isEmpty(artist) && isArtistMatched(artist, songArtist)) {
                    score += 10;
                }
                candidates.add(new NeteaseSong(song.get("id").getAsLong(), name, songArtist,
                        albumName(song), score));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchLrclibLyric(String title, String artist, ArrayList<ResultData> out) {
        String query = TextUtils.isEmpty(artist) ? title : title + " " + artist;
        appendLrclibResults(query, out);
        if (out.isEmpty() && !TextUtils.isEmpty(artist)) {
            appendLrclibResults(title, out);
        }
    }

    private void appendLrclibResults(String query, ArrayList<ResultData> out) {
        try {
            String searchUrl = "https://lrclib.net/api/search?q=" + URLEncoder.encode(query, "UTF-8");
            String json = requestData(searchUrl);
            if (TextUtils.isEmpty(json)) {
                return;
            }
            com.google.gson.JsonArray array = com.google.gson.JsonParser.parseString(json).getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                if (out.size() >= 5) {
                    break;
                }
                com.google.gson.JsonElement element = array.get(i);
                if (element == null || !element.isJsonObject()) {
                    continue;
                }
                com.google.gson.JsonObject obj = element.getAsJsonObject();
                ResultData resultData = new ResultData();
                resultData.setTitle(stringValue(obj, "trackName"));
                resultData.setArtist(stringValue(obj, "artistName"));
                resultData.setAlbum(stringValue(obj, "albumName"));
                if (!isTitleMatched(query, resultData.getTitle())) {
                    continue;
                }

                String lrc = "";
                if (obj.has("syncedLyrics") && !obj.get("syncedLyrics").isJsonNull()) {
                    lrc = obj.get("syncedLyrics").getAsString();
                } else if (obj.has("plainLyrics") && !obj.get("plainLyrics").isJsonNull()) {
                    lrc = obj.get("plainLyrics").getAsString();
                }

                if (!TextUtils.isEmpty(lrc) && obj.has("id") && !obj.get("id").isJsonNull()) {
                    int id = obj.get("id").getAsInt();
                    String localLyricPath = TTPodConfig.getLyricPath() + File.separator
                            + m2145b(resultData.getArtist(), resultData.getTitle());
                    ResultData.Item[] items = new ResultData.Item[1];
                    items[0] = new ResultData.Item("lrc", "lrclib:" + id, localLyricPath + ".lrc", id);
                    resultData.setLyricArray(items);
                    out.add(resultData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String stringValue(com.google.gson.JsonObject obj, String key) {
        return obj != null && obj.has(key) && !obj.get(key).isJsonNull() ? obj.get(key).getAsString() : "";
    }

    private static String joinArtists(com.google.gson.JsonObject song) {
        if (song == null || !song.has("artists") || song.get("artists").isJsonNull() || !song.get("artists").isJsonArray()) {
            return "";
        }
        com.google.gson.JsonArray artists = song.getAsJsonArray("artists");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < artists.size(); i++) {
            com.google.gson.JsonElement element = artists.get(i);
            if (element == null || !element.isJsonObject()) {
                continue;
            }
            String name = stringValue(element.getAsJsonObject(), "name");
            if (TextUtils.isEmpty(name)) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append('/');
            }
            sb.append(name);
        }
        return sb.toString();
    }

    private static String albumName(com.google.gson.JsonObject song) {
        if (song == null || !song.has("album") || song.get("album").isJsonNull() || !song.get("album").isJsonObject()) {
            return "";
        }
        return stringValue(song.getAsJsonObject("album"), "name");
    }

    private static String parseNeteaseLyric(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            com.google.gson.JsonObject root = com.google.gson.JsonParser.parseString(json).getAsJsonObject();
            if (root == null) {
                return null;
            }
            if (root.has("nolyric") && !root.get("nolyric").isJsonNull() && root.get("nolyric").getAsBoolean()) {
                return null;
            }
            if (!root.has("lrc") || root.get("lrc").isJsonNull() || !root.get("lrc").isJsonObject()) {
                return null;
            }
            com.google.gson.JsonObject lrcObj = root.getAsJsonObject("lrc");
            if (lrcObj.has("lyric") && !lrcObj.get("lyric").isJsonNull()) {
                String lrc = lrcObj.get("lyric").getAsString();
                return TextUtils.isEmpty(lrc.trim()) ? null : lrc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: b */
    private String m2145b(String str, String str2) {
        String str3;
        MediaItem i = this.lyricSearchTaskInfo.getMediaItem();
        String artist = i.getArtist();
        String title = i.getTitle();
        boolean isValidateMediaString = TTTextUtils.isValidateMediaString(title);
        boolean isValidateMediaString2 = TTTextUtils.isValidateMediaString(artist);
        boolean isValidateMediaString3 = TTTextUtils.isValidateMediaString(str2);
        boolean isValidateMediaString4 = TTTextUtils.isValidateMediaString(str);
        if (isValidateMediaString) {
            if (isValidateMediaString2 || isValidateMediaString4) {
                StringBuilder sb = new StringBuilder();
                if (isValidateMediaString2) {
                    str = artist;
                }
                str3 = sb.append(str).append(" - ").append(title).toString();
            } else {
                str3 = title;
            }
        } else if (!isValidateMediaString3) {
            str3 = null;
        } else {
            str3 = str + " - " + str2;
        }
        return FileUtils.removeWrongCharacter(str3);
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected ArrayList<String> mo2135a(String songName, String singer) {
        String songSingerName;
        String m2146a;
        boolean z = true;
        String[] songInfo = this.lyricSearchTaskInfo.getSongInfo();
        MediaItem mediaItem = getLyricSearchTaskInfo().getMediaItem();
        if (mediaItem.isOnline()) {
            songSingerName = null;
        } else {
            songSingerName = songInfo[1];
            if (songInfo[3] != null) {
                songSingerName = songSingerName + '_' + songInfo[3];
            }
        }
        String m8397o = FileUtils.removeWrongCharacter(songName);
        String m8397o2 = TTTextUtils.isValidateMediaString(singer) ? FileUtils.removeWrongCharacter(singer) : null;
        if (mediaItem.getStartTime() == null || mediaItem.getStartTime().intValue() <= 0) {
            z = false;
        }
        if (mediaItem.isOnline()) {
            m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".trc", z);
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".lrc", z);
            }
        } else {
            m2146a = m2146a(songInfo[0], songSingerName, m8397o, m8397o2, ".trc", z);
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".trc", z);
            }
            if (m2146a == null) {
                m2146a = m2146a(songInfo[0], songSingerName, m8397o, m8397o2, ".lrc", z);
            }
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".lrc", z);
            }
        }
        if (m2146a != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(m2146a);
            return arrayList;
        }
        return null;
    }

    /* renamed from: a */
    private String m2146a(String str, String str2, String str3, String str4, String str5, boolean z) {
        if (str2 != null && !z) {
            String str6 = str + str2 + str5;
            if (new File(str6).exists()) {
                return str6;
            }
        }
        String str7 = str + (TextUtils.isEmpty(str4) ? "" : str4 + " - ") + (TextUtils.isEmpty(str3) ? "" : str3) + str5;
        if (!new File(str7).exists()) {
            String str8 = str + (TextUtils.isEmpty(str4) ? "" : str4 + "-") + (TextUtils.isEmpty(str3) ? "" : str3) + str5;
            if (!new File(str8).exists()) {
                String str9 = str + (TextUtils.isEmpty(str3) ? "" : str3 + " - ") + (TextUtils.isEmpty(str4) ? "" : str4) + str5;
                if (!new File(str9).exists()) {
                    StringBuilder append = new StringBuilder().append(str).append(TextUtils.isEmpty(str3) ? "" : str3 + "-");
                    if (TextUtils.isEmpty(str4)) {
                        str4 = "";
                    }
                    String sb = append.append(str4).append(str5).toString();
                    if (!new File(sb).exists()) {
                        return null;
                    }
                    return sb;
                }
                return str9;
            }
            return str8;
        }
        return str7;
    }
}
