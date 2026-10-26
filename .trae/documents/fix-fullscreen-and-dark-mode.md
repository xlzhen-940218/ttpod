# 修复全屏模式与黑夜模式适配

## Context（背景）

工程已升级到 `compileSdk/targetSdk 37`。从 Android 15（API 35）起，targetSdk ≥ 35 的应用被强制 **edge-to-edge**：

- 旧的 `getWindow().setFlags(1024, 1024)`（`FLAG_FULLSCREEN`）、`android:windowFullscreen` 不再能可靠隐藏系统栏；
- 页面内容会直接绘制到状态栏/导航栏下方，ActionBar 顶进状态栏、底部播放条被导航栏遮挡；
- 工程目前没有任何 `WindowCompat`/`WindowInsetsController`/insets 处理代码。

同时工程**没有任何 `values-night` 资源**，设置页、对话框、二级页面等资源化界面在系统深色模式下仍然是白底深字；主界面颜色来自自研皮肤（`assets/theme/theme.xml` 默认皮肤本身就是深色半透明 + 白字），无需改动皮肤系统。

本方案分两部分：先修全屏/系统栏，再做黑夜模式资源化适配。

---

## 一、全屏与系统栏（核心）

### 1. 新增工具类 `WindowCompatUtils`

路径：`app/src/main/java/com/sds/android/ttpod/framework/p106a/WindowCompatUtils.java`

提供：

- `applyEdgeToEdge(Activity)`：
  - `WindowCompat.setDecorFitsSystemWindows(window, false)`；
  - 给 `android.R.id.content` 注册 `ViewCompat.setOnApplyWindowInsetsListener`，把 `WindowInsetsCompat.Type.systemBars()` 的 inset 设置为 padding（系统栏隐藏时 inset 自动为 0，内容即自动全屏，无需额外标志位）；
  - `WindowInsetsControllerCompat` 设置 `BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE`；
  - `setAppearanceLightStatusBars(false)` / `setAppearanceLightNavigationBars(false)`（各 Activity 主题的窗口背景均为深色/黑色，使用浅色系统栏图标）。
- `setFullScreen(Activity, boolean)` / `setFullScreen(Dialog, boolean)`：
  - 通过 `WindowCompat.getInsetsController(window, decorView)` 执行 `hide/show(WindowInsetsCompat.Type.systemBars())`；
  - 替换所有 `setFlags(1024,1024)` / `clearFlags(1024)` 调用。
- `isNightMode(Context)`：读取 `Configuration.UI_MODE_NIGHT_MASK`（供后续使用）。

只使用 androidx 已有依赖（appcompat 1.7.1 自带 core），无需新增依赖。

### 2. `BaseActivity` 统一接入

文件：`app/src/main/java/com/sds/android/ttpod/framework/base/BaseActivity.java`

在 `onCreate()` 中 `SmartBarUtils.m4642a(getWindow().getDecorView())` 之后调用 `WindowCompatUtils.applyEdgeToEdge(this)`。

作用：所有继承 `BaseActivity` 的页面（ThemeActivity / ActionBarActivity / SlidingClosableActivity / MainActivity / EntryActivity / LockScreenActivity 等）统一修复内容被状态栏、导航栏遮挡的问题；`activity_actionbar.xml` 的 ActionBar 会自动落于状态栏下方，无需改布局尺寸。

### 3. 替换废弃的全屏调用

| 文件 | 修改 |
| --- | --- |
| `activities/MainActivity.java` `startLandscapeFragment()` / `closeLandscapeFragment()` | `setFlags(1024,1024)` → `WindowCompatUtils.setFullScreen(this, true)`；`clearFlags(1024)` → `setFullScreen(this, false)` |
| `EntryActivity.updateSplash()`（末尾） | 删除 `setFlags(1024,1024)`，改为在 `onCreate()` 里调用 `WindowCompatUtils.setFullScreen(this, true)`（启动页始终全屏） |
| `component/lockscreen/LockScreenActivity.onCreate()` | `setFlags/clearFlags(1024)` → `WindowCompatUtils.setFullScreen(this, Preferences.m3070F())`；在 `onResume()` 再次应用，防止系统栏被系统恢复（保留原有 `4194304`/`TYPE_GESTURE_DETECTION_END` 两个 flag，不动） |
| `component/landscape/LandscapeFragment.java` | `onStart()` 中 `mDialog.show()` 之后调用 `WindowCompatUtils.setFullScreen(mDialog, true)`；在 `dismiss()`/`onHiddenChanged(true)` 时调用 `setFullScreen(mDialog, false)`。保留 `R.style.Dialog_FullScreen` 兼容旧版本 |

### 4. 样式补充（可选兼容）

文件：`app/src/main/res/values/styles.xml`

`Dialog_FullScreen` / `Share_Dialog_FullScreen` 保留 `android:windowFullscreen=true` 以兼容旧系统，新增：

```xml
<item name="android:windowLayoutInDisplayCutoutMode">shortEdges</item>
```

避免刘海屏下横屏播放器被裁切。

---

## 二、黑夜模式适配

### 1. 新增 `values-night/colors.xml`

路径：`app/src/main/res/values-night/colors.xml`（工程当前不存在该目录）

覆盖已有语义颜色（布局/样式已在引用，改一处即可全局生效）：

- 背景：`activity_background_default #121212`、`background_actionbar #1E1E1E`、`default_background_and_selector #121212`、`dialog_background #2A2A2A`、`title_background #1E1E1E`、`layout_background #0F0F0F`、`section_background #1A1A1A`、`menu_backgroundcolor #1E1E1E`、`tab_background_feedback #1E1E1E`、`backgroundcolor_white #1E1E1E`、`unicom_flow_background_color #1E1E1E`、`dialog_footer_button_background #2A2A2A`
- 文字：`common_title_color #E8E8E8`、`listview_item_title #E6E6E6`、`listview_item_subtitle #9E9E9E`、`dialog_title #EDEDED`、`dialog_content_text_color #E6E6E6`、`dialog_message_text_color #BDBDBD`、`dialog_standard_title #7FC4E8`、`dialog_editor_hint #7A7A7A`
- 分隔线：`divider_line`、`listview_divider_line`、`listview_divider_color`、`setting_listview_divider_color`、`setting_listview_header_or_footer_divider_color` → `#2C2C2C`

注意：**不覆盖** `white`、`player_bar_song_name`、`transparent_*` 等用于深色皮肤/白色图标的颜色，避免主界面与图标反色。

### 2. 新增语义颜色 + 改造硬编码样式

在 `values/colors.xml` 增加日间值，在 `values-night/colors.xml` 覆盖：

- `setting_card_background`（日 `#ffffff` / 夜 `#1E1E1E`）
- `setting_card_title_color`（日 `#959fa5` / 夜 `#9E9E9E`）
- `setting_text_primary`（日 `#2f2f2f` / 夜 `#E6E6E6`）
- `setting_text_secondary`（日 `#959fa5` / 夜 `#9E9E9E`）
- `setting_item_icon_color`（日 `#91b1bb` / 夜 `#8FA9B1`）
- `actionbar_title_text_color`（日 `#1b3b43` / 夜 `#E6E6E6`）
- `dialog_edit_label_color`（日 `#000000` / 夜 `#E6E6E6`）

`values/styles.xml` 改为引用上述颜色：

- `Setting_Card_Content`：`android:background` → `@color/setting_card_background`
- `Setting_Card_Title`：`android:background` → `@color/setting_card_background`，`textColor` → `@color/setting_card_title_color`
- `ActionBar_Title`：`textColor` → `@color/actionbar_title_text_color`
- `Dialog_Edit_Label`：`textColor` → `@color/dialog_edit_label_color`
- `ActionBar_ActionView_Text`：`#ff2f2f2f` → `@color/setting_text_primary`

### 3. 设置页布局替换硬编码颜色

把以下文件中的 `@android:color/white` / `#ffffff` / `#ff2f2f2f` / `#959fa5` / `#91b1bb` / `#777777` 替换为对应语义颜色：

- `activity_setting_main.xml`、`activity_setting_about.xml`、`activity_setting_audition_download_quality.xml`、`activity_setting_desktop_lyric.xml`、`activity_setting_lyric_pic.xml`、`activity_setting_notification.xml`、`activity_setting_other.xml`：根背景 → `@color/activity_background_default`
- `fragment_settings.xml`：ScrollView 背景 → `@color/activity_background_default`
- `activity_setting_card_item.xml`：`tv_content` → `@color/setting_text_primary`；`label_subtitle` → `@color/setting_text_secondary`；`itv_label_icon` → `@color/setting_item_icon_color`；`itv_action_view` → `@color/setting_text_secondary`
- `setting_container_desc.xml`：背景 `#ffffff` → `@color/activity_background_default`
- `activity_setting_about.xml` 中的 `#959fa5` → `@color/setting_text_secondary`

### 4. 其他高频白底页面（同批替换）

- 反馈页：`activity_feedback_chat.xml`、`fragment_feedback_compose.xml`、`fragment_feedback_list.xml` 根背景改用 `@color/activity_background_default`
- 网页/地址选择：`layout_web_page.xml`、`layout_address_select.xml`、`mediascan_filepicker_header.xml` 白底改用 `@color/activity_background_default`
- 联通流量页：`activity_unicom_flow_unsubscribe_detail.xml` 的 `#ffffff` 背景改为 `@color/dialog_background`（或 `@color/activity_background_default`）
- 自动补全弹窗：`dialog_auto_complete.xml` 白底改为 `@color/dialog_background`
- `cmmusic_*` 系列使用 `@color/backgroundcolor_white` 的布局无需改文件，已由 values-night 覆盖。

> 说明：皮肤驱动的主界面、播放页、锁屏歌词页本身是深色皮肤 + 白字，黑夜模式下无需改动；本方案只处理资源化界面（设置、对话框、通用二级页）。

### 5. 状态栏/导航栏图标明暗

在 `WindowCompatUtils.applyEdgeToEdge()` 中统一调用：

```java
controller.setAppearanceLightStatusBars(false);
controller.setAppearanceLightNavigationBars(false);
```

因为各 Activity 主题窗口背景为深色/黑色，浅色图标在日间/夜间均可见，避免深色模式下状态栏图标不可读。

### 6. 不需要改动

- `assets/theme/theme.xml` 皮肤文件：默认皮肤已是深色半透明白字，不改动，避免影响用户已下载的 `.tsk` 皮肤。
- `attrs.xml` 中未被读取的 AppTheme 属性：本次不做清理，避免超范围改动。

---

## 三、验证

1. 构建：`.\gradlew assembleDebug`（Windows PowerShell）。
2. 全屏验证（Android 15 模拟器/真机）：
   - 主界面竖屏：状态栏下方无遮挡，ActionBar 完整可见，底部播放条不被导航栏遮挡。
   - 播放页进入横屏：状态栏、导航栏隐藏；退出横屏后恢复显示且布局正常。
   - 从状态栏下滑可临时唤出系统栏（`BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE`）。
   - 设置 → 锁屏歌词 → 全屏/非全屏切换后重新进入锁屏界面，系统栏隐藏/显示正确。
   - 冷启动启动页图片全屏显示。
3. 黑夜模式验证：
   - 系统切换深色模式后重启应用：设置页、设置卡片、对话框、反馈页、网页页均为深色背景 + 浅色文字，无白底残留。
   - 切换回浅色模式：上述页面恢复白底深字。
   - 主界面（皮肤驱动）在深色模式下文字与顶栏保持可读。
4. 回归：检查滑动返回动画、ActionBar 标题、弹窗按钮文字颜色均正常。
