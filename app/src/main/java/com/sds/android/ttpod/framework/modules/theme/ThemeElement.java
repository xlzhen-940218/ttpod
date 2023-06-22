package com.sds.android.ttpod.framework.modules.theme;

import java.lang.reflect.Field;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ThemeElement {
    static final String BACKGROUND_IMAGE = "BackgroundImage";
    public static final String BACKGROUND_MASK = "BackgroundMaskColor";
    static final String HOME_BACKGROUND_BLUR = "HomeBackgroundBlur";
    private static final String IMAGE_FLAG = "_IMG";
    static final String IMAGE_HIGHLIGHT_SUFFIX = "_h";
    static final String IMAGE_NORMAL_SUFFIX = "_n";
    public static final String PANEL_SUB_BAR = "SubBar";
    public static final String PANEL_TOP_BAR = "TopBar";
    static final String PLAY_BAR_IMG_BACKGROUND = "play_bar_bkg";
    static final String PLAY_BAR_IMG_BACKGROUND2 = "play_bar_bkg2";
    private static final String SEPARATOR_TOKEN = ":";
    static final String SETTING_IMG_AVATAR = "avatar";
    static final String SETTING_IMG_BACKGROUND = "setting_background";
    static final String SONG_LIST_ITEM_IMG_INDICATOR = "song_list_item_indicator";
    public static final String STATUS_BAR_MODE = "StatusBar";
    static final String SUB_BAR = "SubBar";
    static final String SUB_BAR_IMG_BACKGROUND = "sub_bar_bkg";
    static final String SUB_BAR_IMG_INDICATOR = "sub_bar_indicator";
    static final String TEXT = "Text";
    private static final String TEXT_FLAG = "Text";
    static final String TOP_BAR = "TopBar";
    static final String TOP_BAR_IMG_BACKGROUND = "top_bar_bkg";
    static final String TOP_BAR_IMG_INDICATOR = "top_bar_indicator";
    private static ArrayList<String> sImageElement = new ArrayList<>();
    public static final String PANEL_COMMON = "Common";
    static final String TITLE_TEXT = "TitleText";
    public static final String COMMON_TITLE_TEXT = buildPublicId(PANEL_COMMON, TITLE_TEXT);
    static final String SUB_TITLE_TEXT = "SubTitleText";
    public static final String COMMON_SUB_TITLE_TEXT = buildPublicId(PANEL_COMMON, SUB_TITLE_TEXT);
    static final String CONTENT_TEXT = "ContentText";
    public static final String COMMON_CONTENT_TEXT = buildPublicId(PANEL_COMMON, CONTENT_TEXT);
    static final String SUB_CONTENT_TEXT = "SubContentText";
    public static final String COMMON_SUB_CONTENT_TEXT = buildPublicId(PANEL_COMMON, SUB_CONTENT_TEXT);
    static final String INDICATOR = "Indicator";
    public static final String COMMON_INDICATOR = buildPublicId(PANEL_COMMON, INDICATOR);
    static final String SEPARATOR = "Separator";
    public static final String COMMON_SEPARATOR = buildPublicId(PANEL_COMMON, SEPARATOR);
    static final String CONTENT = "Content";
    public static final String COMMON_CONTENT = buildPublicId(PANEL_COMMON, CONTENT);
    public static final String COMMON_TOP_BAR = buildPublicId(PANEL_COMMON, "TopBar");
    public static final String COMMON_SUB_BAR = buildPublicId(PANEL_COMMON, "SubBar");
    static final String BOTTOM_BAR = "BottomBar";
    public static final String COMMON_BOTTOM_BAR = buildPublicId(PANEL_COMMON, BOTTOM_BAR);
    static final String INPUT_SEND_IMG = "input_box_send";
    public static final String COMMON_SEND_IMAGE = buildPublicId(PANEL_COMMON, INPUT_SEND_IMG);
    static final String INPUT_EMOTION_IMG = "input_box_emotion";
    public static final String COMMON_EMOTION_IMAGE = buildPublicId(PANEL_COMMON, INPUT_EMOTION_IMG);
    static final String INPUT_KEYBOARD_IMG = "input_box_keyboard";
    public static final String COMMON_KEYBOARD_IMAGE = buildPublicId(PANEL_COMMON, INPUT_KEYBOARD_IMG);
    static final String PROGRESS_BAR = "ProgressBar";
    public static final String COMMON_PROGRESS_BAR = buildPublicId(PANEL_COMMON, PROGRESS_BAR);
    static final String PROGRESS_DRAWABLE = "Progress";
    public static final String COMMON_PROGRESS_DRAWABLE = buildPublicId(PANEL_COMMON, PROGRESS_DRAWABLE);
    static final String SECOND_PROGRESS_DRAWABLE = "SecondProgress";
    public static final String COMMON_SECOND_PROGRESS_DRAWABLE = buildPublicId(PANEL_COMMON, SECOND_PROGRESS_DRAWABLE);
    static final String CLEAR_IMG = "common_clear";
    public static final String COMMON_CLEAR_IMAGE = buildPublicId(PANEL_COMMON, CLEAR_IMG);
    public static final String PANEL_HOME = "Home";
    public static final String HOME_TEXT = buildPublicId(PANEL_HOME, "Text");
    static final String SUB_TEXT = "SubText";
    public static final String HOME_SUB_TEXT = buildPublicId(PANEL_HOME, SUB_TEXT);
    static final String AVATAR_FRAME = "AvatarFrame";
    public static final String HOME_AVATAR_FRAME = buildPublicId(PANEL_HOME, AVATAR_FRAME);
    static final String BACKGROUND = "Background";
    public static final String HOME_BACKGROUND = buildPublicId(PANEL_HOME, BACKGROUND);
    static final String HOME_IMG_WIFI_ONLY_ON = "home_wifi_only_on";
    public static final String HOME_WIFI_ONLY_ON_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_WIFI_ONLY_ON);
    static final String HOME_IMG_WIFI_ONLY_OFF = "home_wifi_only_off";
    public static final String HOME_WIFI_ONLY_OFF_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_WIFI_ONLY_OFF);
    static final String HOME_IMG_CHANGE_BACKGROUND = "home_change_background";
    public static final String HOME_CHANGE_BACKGROUND_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_CHANGE_BACKGROUND);
    static final String HOME_IMG_ARROW = "home_arrow";
    public static final String HOME_ARROW_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_ARROW);
    static final String HOME_IMG_MUSIC = "home_music";
    public static final String HOME_MUSIC_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_MUSIC);
    public static final String HOME_IMG_FAVORITE = "home_favorite";
    public static final String HOME_FAVORITE_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_FAVORITE);
    public static final String HOME_IMG_SONG_LIST = "home_song_list";
    public static final String HOME_SONG_LIST_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_SONG_LIST);
    public static final String HOME_IMG_FOLDER = "home_folder";
    public static final String HOME_FOLDER_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_FOLDER);
    public static final String HOME_IMG_DOWNLOAD = "home_download";
    public static final String HOME_DOWNLOAD_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_DOWNLOAD);
    static final String HOME_IMG_APP = "home_app";
    public static final String HOME_APP_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_APP);
    static final String HOME_IMG_RANDOM_PLAY = "home_random_play";
    public static final String HOME_RANDOM_PLAY_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_RANDOM_PLAY);
    public static final String HOME_IMG_RECENT_PLAY = "home_recent_play";
    public static final String HOME_RECENT_PLAY_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_RECENT_PLAY);
    static final String HOME_IMG_NEW_SONG_LIST = "home_new_song_list";
    public static final String HOME_NEW_SONG_LIST_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_NEW_SONG_LIST);
    static final String HOME_IMG_SCAN = "home_scan";
    public static final String HOME_SCAN_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_SCAN);
    static final String HOME_IMG_NEW = "home_new";
    public static final String HOME_NEW_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_NEW);
    public static final String HOME_IMG_RECENT_ADD = "home_recent_add";
    public static final String HOME_RECENT_ADD_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_RECENT_ADD);
    static final String HOME_IMG_MENU = "home_menu";
    public static final String HOME_MENU_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_MENU);
    public static final String HOME_IMG_ARTIST = "home_artist";
    public static final String HOME_ARTIST_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_ARTIST);
    public static final String HOME_IMG_ALBUM = "home_album";
    public static final String HOME_ALBUM_IMAGE = buildPublicId(PANEL_HOME, HOME_IMG_ALBUM);
    public static final String TOP_BAR_TEXT = buildPublicId("TopBar", "Text");
    public static final String TOP_BAR_INDICATOR = buildPublicId("TopBar", INDICATOR);
    public static final String TOP_BAR_BACKGROUND = buildPublicId("TopBar", BACKGROUND);
    public static final String TOP_BAR_SEPARATOR = buildPublicId("TopBar", SEPARATOR);
    static final String TOP_BAR_IMG_SEARCH = "top_bar_search";
    public static final String TOP_BAR_SEARCH_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_SEARCH);
    static final String TOP_BAR_IMG_BACK = "top_bar_back";
    public static final String TOP_BAR_BACK_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_BACK);
    static final String TOP_BAR_IMG_EDIT = "top_bar_edit";
    public static final String TOP_BAR_EDIT_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_EDIT);
    static final String TOP_BAR_IMG_ADD_FRIENDS = "top_bar_add_friends";
    public static final String TOP_BAR_ADD_FRIENDS_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_ADD_FRIENDS);
    static final String TOP_BAR_IMG_ADD = "top_bar_add";
    public static final String TOP_BAR_ADD_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_ADD);
    static final String TOP_BAR_IMG_ORDER = "top_bar_order";
    public static final String TOP_BAR_ORDER_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_ORDER);
    static final String TOP_BAR_IMG_SHARE = "top_bar_share";
    public static final String TOP_BAR_SHARE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_SHARE);
    static final String TOP_BAR_IMG_MESSAGE = "top_bar_message";
    public static final String TOP_BAR_MESSAGE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_MESSAGE);
    static final String TOP_BAR_IMG_LIST_INLOVE = "top_bar_list_inlove";
    public static final String TOP_BAR_INLOVE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_LIST_INLOVE);
    static final String TOP_BAR_IMG_LIST_UNLOVE = "top_bar_list_unlove";
    public static final String TOP_BAR_UNLOVE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_LIST_UNLOVE);
    static final String TOP_BAR_IMG_RECOGNIZE_HISTORY = "top_bar_recognize_history";
    public static final String TOP_BAR_RECOGNIZE_HISTORY_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_RECOGNIZE_HISTORY);
    static final String TOP_BAR_IMG_REFRESH = "top_bar_refresh";
    public static final String TOP_BAR_REFRESH_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_REFRESH);
    static final String TOP_BAR_IMG_EDIT_DONE = "top_bar_edit_done";
    public static final String TOP_BAR_EDIT_DONE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_EDIT_DONE);
    static final String TOP_BAR_IMG_DOWNLOAD = "top_bar_download";
    public static final String TOP_BAR_DOWNLOAD_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_DOWNLOAD);
    static final String TOP_BAR_IMG_MENU = "top_bar_menu";
    public static final String TOP_BAR_MENU_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_MENU);
    static final String TOP_BAR_IMG_MORE = "top_bar_more";
    public static final String TOP_BAR_MORE_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_MORE);
    static final String TOP_BAR_IMG_INFOMATION = "top_bar_information";
    public static final String TOP_BAR_INFORMATION_IMAGE = buildPublicId("TopBar", TOP_BAR_IMG_INFOMATION);
    public static final String SUB_BAR_TEXT = buildPublicId("SubBar", "Text");
    public static final String SUB_BAR_INDICATOR = buildPublicId("SubBar", INDICATOR);
    public static final String SUB_BAR_BACKGROUND = buildPublicId("SubBar", BACKGROUND);
    public static final String PANEL_PLAY_BAR = "PlayBar";
    public static final String PLAY_BAR_TEXT = buildPublicId(PANEL_PLAY_BAR, "Text");
    public static final String PLAY_BAR_SUB_TEXT = buildPublicId(PANEL_PLAY_BAR, SUB_TEXT);
    static final String TIME_TEXT = "TimeText";
    public static final String PLAY_BAR_TIME_TEXT = buildPublicId(PANEL_PLAY_BAR, TIME_TEXT);
    public static final String PLAY_BAR_BACKGROUND = buildPublicId(PANEL_PLAY_BAR, BACKGROUND);
    static final String PLAY_BAR_IMG_DEF_ARTIST = "play_bar_def_artist";
    public static final String PLAY_BAR_DEF_ARTIST_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_DEF_ARTIST);
    static final String PLAY_BAR_IMG_PLAY = "play_bar_play";
    public static final String PLAY_BAR_PLAY_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_PLAY);
    static final String PLAY_BAR_IMG_PAUSE = "play_bar_pause";
    public static final String PLAY_BAR_PAUSE_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_PAUSE);
    static final String PLAY_BAR_IMG_PLAY_NEXT = "play_bar_play_next";
    public static final String PLAY_BAR_NEXT_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_PLAY_NEXT);
    static final String PLAY_BAR_IMG_SIDEBAR = "play_bar_sidebar";
    public static final String PLAY_BAR_SIDEBAR_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_SIDEBAR);
    static final String ARTIST = "Artist";
    public static final String PLAY_BAR_ARTIST = buildPublicId(PANEL_PLAY_BAR, ARTIST);
    static final String ARTISTMASK = "ArtistMask";
    public static final String PLAY_BAR_ARTISTMASK = buildPublicId(PANEL_PLAY_BAR, ARTISTMASK);
    static final String PLAY_BAR_IMG_ARTIST_MASK = "play_bar_artist_mask";
    public static final String PLAY_BAR_ARTIS_MASK_IMAGE = buildPublicId(PANEL_PLAY_BAR, PLAY_BAR_IMG_ARTIST_MASK);
    public static final String PANEL_SONG_LIST_ITEM = "SongListItem";
    public static final String SONG_LIST_ITEM_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, "Text");
    public static final String SONG_LIST_ITEM_SUB_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, SUB_TEXT);
    public static final String SONG_LIST_ITEM_INDICATOR = buildPublicId(PANEL_SONG_LIST_ITEM, INDICATOR);
    public static final String SONG_LIST_ITEM_BACKGROUND = buildPublicId(PANEL_SONG_LIST_ITEM, BACKGROUND);
    static final String SONG_LIST_ITEM_IMG_INLOVE = "song_list_item_inlove";
    public static final String SONG_LIST_ITEM_INLOVE_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_INLOVE);
    static final String SONG_LIST_ITEM_IMG_UNLOVE = "song_list_item_unlove";
    public static final String SONG_LIST_ITEM_UNLOVE_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_UNLOVE);
    static final String SONG_LIST_ITEM_IMG_MENU = "song_list_item_menu";
    public static final String SONG_LIST_ITEM_MENU_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_MENU);
    static final String SONG_LIST_ITEM_IMG_PLAY = "song_list_item_play";
    public static final String SONG_LIST_ITEM_PLAY_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_PLAY);
    static final String SONG_LIST_ITEM_IMG_PAUSE = "song_list_item_pause";
    public static final String SONG_LIST_ITEM_PAUSE_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_PAUSE);
    static final String SONG_LIST_POP_BACKGROUND_IMG = "song_list_pop_bkg";
    public static final String SONG_LIST_POP_BACKGROUND = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_POP_BACKGROUND_IMG);
    static final String SONG_LIST_ITEM_IMG_DRAG = "song_list_item_drag";
    public static final String SONG_LIST_ITEM_DRAG_IMAGE = buildPublicId(PANEL_SONG_LIST_ITEM, SONG_LIST_ITEM_IMG_DRAG);
    static final String AZBAR_AREA = "AZBarArea";
    public static final String SONG_LIST_ITEM_AZBAR_AREA = buildPublicId(PANEL_SONG_LIST_ITEM, AZBAR_AREA);
    static final String AZBAR = "AZBar";
    public static final String SONG_LIST_ITEM_AZBAR = buildPublicId(PANEL_SONG_LIST_ITEM, AZBAR);
    static final String AZBAR_TEXT = "AZBarText";
    public static final String SONG_LIST_ITEM_AZBAR_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, AZBAR_TEXT);
    static final String POP_TEXT = "PopText";
    public static final String SONG_LIST_POP_TEXT = buildPublicId(PANEL_SONG_LIST_ITEM, POP_TEXT);
    public static final String PANEL_CARD = "Card";
    public static final String CARD_TEXT = buildPublicId(PANEL_CARD, "Text");
    public static final String CARD_SUB_TEXT = buildPublicId(PANEL_CARD, SUB_TEXT);
    static final String CONTROL_TEXT = "ControlText";
    public static final String CARD_CONTROL_TEXT = buildPublicId(PANEL_CARD, CONTROL_TEXT);
    static final String CONTROL_BACKGROUND = "ControlBackground";
    public static final String CARD_CONTROL_BACKGROUND = buildPublicId(PANEL_CARD, CONTROL_BACKGROUND);
    public static final String CARD_BACKGROUND = buildPublicId(PANEL_CARD, BACKGROUND);
    public static final String PANEL_PLAYER_MUSIC_LIST = "PlayerMusicList";
    public static final String PLAYER_MUSIC_LIST_TEXT = buildPublicId(PANEL_PLAYER_MUSIC_LIST, "Text");
    public static final String PLAYER_MUSIC_LIST_SUB_TEXT = buildPublicId(PANEL_PLAYER_MUSIC_LIST, SUB_TEXT);
    public static final String PLAYER_MUSIC_LIST_INDICATOR = buildPublicId(PANEL_PLAYER_MUSIC_LIST, INDICATOR);
    public static final String PLAYER_MUSIC_LIST_SEPARATOR = buildPublicId(PANEL_PLAYER_MUSIC_LIST, SEPARATOR);
    public static final String PLAYER_MUSIC_LIST_BACKGROUND = buildPublicId(PANEL_PLAYER_MUSIC_LIST, BACKGROUND);
    public static final String PLAYER_MUSIC_LIST_AZBAR = buildPublicId(PANEL_PLAYER_MUSIC_LIST, AZBAR_AREA);
    public static final String PLAYER_MUSIC_LIST_AZBAR_BACKGROUND = buildPublicId(PANEL_PLAYER_MUSIC_LIST, AZBAR);
    public static final String PLAYER_MUSIC_LIST_AZBAR_TEXT = buildPublicId(PANEL_PLAYER_MUSIC_LIST, AZBAR_TEXT);
    static final String PLAYER_MUSIC_LIST_IMG_INLOVE = "player_music_list_item_inlove";
    public static final String PLAYER_MUSIC_LIST_INLOVE_IMAGE = buildPublicId(PANEL_PLAYER_MUSIC_LIST, PLAYER_MUSIC_LIST_IMG_INLOVE);
    static final String PLAYER_MUSIC_LIST_IMG_UNLOVE = "player_music_list_item_unlove";
    public static final String PLAYER_MUSIC_LIST_UNLOVE_IMAGE = buildPublicId(PANEL_PLAYER_MUSIC_LIST, PLAYER_MUSIC_LIST_IMG_UNLOVE);
    static final String PLAYER_MUSIC_LIST_IMG_MENU = "player_music_list_item_menu";
    public static final String PLAYER_MUSIC_LIST_MENU_IMAGE = buildPublicId(PANEL_PLAYER_MUSIC_LIST, PLAYER_MUSIC_LIST_IMG_MENU);
    public static final String PANEL_TILE = "Tile";
    public static final String TILE_TEXT = buildPublicId(PANEL_TILE, "Text");
    public static final String TILE_SUB_TEXT = buildPublicId(PANEL_TILE, SUB_TEXT);
    public static final String TILE_BACKGROUND = buildPublicId(PANEL_TILE, BACKGROUND);
    static final String MASK = "Mask";
    public static final String TILE_MASK = buildPublicId(PANEL_TILE, MASK);
    static final String TILE_IMG_EDITOR = "tile_editor";
    public static final String TILE_EDITOR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_EDITOR);
    static final String TILE_IMG_DISCOVERY = "tile_discovery";
    public static final String TILE_DISCOVERY_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_DISCOVERY);
    static final String TILE_IMG_MUSIC_CIRCLE = "tile_music_circle";
    public static final String TILE_MUSIC_CIRCLE_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_MUSIC_CIRCLE);
    static final String TILE_IMG_EMOTION = "tile_emotion";
    public static final String TILE_EMOTION_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_EMOTION);
    static final String TILE_IMG_POPULAR = "tile_popular";
    public static final String TILE_POPULAR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_POPULAR);
    static final String TILE_IMG_DAILY_FIVE = "tile_daily_five";
    public static final String TILE_DAILY_FIVE_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_DAILY_FIVE);
    static final String TILE_IMG_NET_RADIO = "tile_net_radio";
    public static final String TILE_NET_RADIO_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_NET_RADIO);
    static final String TILE_IMG_EXPERT = "tile_expert";
    public static final String TILE_EXPERT_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_EXPERT);
    static final String TILE_IMG_HUMOR = "tile_humor";
    public static final String TILE_HUMOR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_HUMOR);
    static final String TILE_IMG_ORIGINAL = "tile_original";
    public static final String TILE_ORIGINAL_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_ORIGINAL);
    static final String TILE_IMG_ACTIVITY = "tile_activity";
    public static final String TILE_ACTIVITY_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_ACTIVITY);
    static final String TILE_IMG_STAR = "tile_star";
    public static final String TILE_STAR_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_STAR);
    static final String TILE_IMG_TV = "tile_tv";
    public static final String TILE_TV_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_TV);
    static final String TILE_IMG_RANK = "tile_rank";
    public static final String TILE_RANK_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_RANK);
    static final String TILE_IMG_CATEGORY = "tile_category";
    public static final String TILE_CATEGORY_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_CATEGORY);
    static final String TILE_IMG_SINGER = "tile_singer";
    public static final String TILE_SINGER_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_SINGER);
    static final String TILE_IMG_RADIO = "tile_radio";
    public static final String TILE_RADIO_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_RADIO);
    static final String TILE_IMG_MV = "tile_mv";
    public static final String TILE_MV_IMAGE = buildPublicId(PANEL_TILE, TILE_IMG_MV);
    public static final String PANEL_SETTING = "Setting";
    public static final String SETTING_TEXT = buildPublicId(PANEL_SETTING, "Text");
    static final String AVATAR_TEXT = "AvatarText";
    public static final String SETTING_AVATAR_TEXT = buildPublicId(PANEL_SETTING, AVATAR_TEXT);
    public static final String SETTING_BACKGROUND = buildPublicId(PANEL_SETTING, BACKGROUND);
    static final String ITEM_BACKGROUND = "ItemBackground";
    public static final String SETTING_ITEM_BACKGROUND = buildPublicId(PANEL_SETTING, ITEM_BACKGROUND);
    public static final String SETTING_SEPARATOR = buildPublicId(PANEL_SETTING, SEPARATOR);
    static final String TIME_BUTTON_TEXT = "TimeButtonText";
    public static final String SETTING_TIME_BUTTON_TEXT = buildPublicId(PANEL_SETTING, TIME_BUTTON_TEXT);
    static final String QUIT_BUTTON_TEXT = "QuitButtonText";
    public static final String SETTING_QUIT_BUTTON_TEXT = buildPublicId(PANEL_SETTING, QUIT_BUTTON_TEXT);
    static final String TIME_BUTTON_BACKGROUND = "TimeButtonBackground";
    public static final String SETTING_TIME_BUTTON_BACKGROUND = buildPublicId(PANEL_SETTING, TIME_BUTTON_BACKGROUND);
    static final String QUIT_BUTTON_BACKGROUND = "QuitButtonBackground";
    public static final String SETTING_QUIT_BUTTON_BACKGROUND = buildPublicId(PANEL_SETTING, QUIT_BUTTON_BACKGROUND);
    static final String SETTING_IMG_SHARE_SONG = "setting_pc_share_song";
    public static final String SETTING_SHARE_SONG_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SHARE_SONG);
    static final String SETTING_IMG_PLAY_SINGLE = "setting_play_single";
    public static final String SETTING_PLAY_SINGLE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_SINGLE);
    static final String SETTING_IMG_PLAY_LOOP = "setting_play_loop";
    public static final String SETTING_PLAY_LOOP_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_LOOP);
    static final String SETTING_IMG_PLAY_SEQUENCE = "setting_play_sequence";
    public static final String SETTING_PLAY_SEQUENCE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_SEQUENCE);
    static final String SETTING_IMG_PLAY_SHUFFLE = "setting_play_shuffle";
    public static final String SETTING_PLAY_SHUFFLE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_PLAY_SHUFFLE);
    static final String SETTING_IMG_RECOGNIZE = "setting_recognize";
    public static final String SETTING_RECOGNIZE_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_RECOGNIZE);
    static final String SETTING_IMG_THEME = "setting_theme";
    public static final String SETTING_THEME_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_THEME);
    static final String SETTING_IMG_AUDIO_EFFECT = "setting_audio_effect";
    public static final String SETTING_AUDIO_EFFECT_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_AUDIO_EFFECT);
    static final String SETTING_IMG_ICON = "setting_icon";
    public static final String SETTING_ICON_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_ICON);
    static final String SETTING_IMG_EXIT = "setting_exit";
    public static final String SETTING_EXIT_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_EXIT);
    static final String SETTING_IMG_RECOMMEND = "setting_recommend";
    public static final String SETTING_RECOMMEND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_RECOMMEND);
    static final String SETTING_IMG_TTPOD_FM = "setting_ttpod_fm";
    public static final String SETTING_TTPOD_FM_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_TTPOD_FM);
    static final String SETTING_IMG_KTV = "setting_ktv";
    public static final String SETTING_KTV_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_KTV);
    static final String SETTING_IMG_NEW = "setting_new";
    public static final String SETTING_NEW_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_NEW);
    static final String SETTING_IMG_CHANGE_BACKGROUND = "setting_change_background";
    public static final String SETTING_CHANGE_BACKGROUND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_CHANGE_BACKGROUND);
    static final String SETTING_IMG_SHOW = "setting_show";
    public static final String SETTING_SHOW_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SHOW);
    static final String SETTING_IMG_TRAFFIC = "setting_traffic";
    public static final String SETTING_TRAFFIC_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_TRAFFIC);
    static final String SETTING_IMG_SLEEP = "setting_sleep";
    public static final String SETTING_SLEEP_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SLEEP);
    static final String SETTING_IMG_AVATAR_MASK = "avatar_mask";
    public static final String SETTING_AVATAR_MASK_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_AVATAR_MASK);
    static final String SETTING_IMG_AVATAR_FRAME = "avatar_frame";
    public static final String SETTING_AVATAR_FRAME_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_AVATAR_FRAME);
    public static final String SETTING_AVATAR_IMAGE = buildPublicId(PANEL_SETTING, "avatar");
    static final String AVATAR = "Avatar";
    public static final String SETTING_AVATAR = buildPublicId(PANEL_SETTING, AVATAR);
    static final String SETTING_IMG_ARROW_UP = "setting_arrow_up";
    public static final String SETTING_ARROW_UP_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_ARROW_UP);
    static final String SETTING_IMG_ARROW_DOWN = "setting_arrow_down";
    public static final String SETTING_ARROW_DOWN_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_ARROW_DOWN);
    public static final String SETTING_AVATAR_FRAME = buildPublicId(PANEL_SETTING, AVATAR_FRAME);
    static final String AVATAR_MASK = "AvatarMask";
    public static final String SETTING_AVATAR_MASK = buildPublicId(PANEL_SETTING, AVATAR_MASK);
    static final String SETTING_IMG_SCANNING = "setting_scanning";
    public static final String SETTING_SCANNING_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_SCANNING);
    static final String SETTING_IMG_HORIZONTAL_BACKGROUND = "setting_horizontal_background";
    public static final String SETTING_HORIZONTAL_BACKGROUND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_HORIZONTAL_BACKGROUND);
    static final String SETTING_IMG_MENU_INDICATOR_BACKGROUND = "setting_indicator_background";
    public static final String SETTING_MENU_INDICATOR_BACKGROUND_IMAGE = buildPublicId(PANEL_SETTING, SETTING_IMG_MENU_INDICATOR_BACKGROUND);

    static {
        collectMembers();
    }

    private static String buildPublicId(String str, String str2) {
        return str + SEPARATOR_TOKEN + str2;
    }

    private static void collectMembers() {
        Field[] declaredFields;
        for (Field field : ThemeElement.class.getDeclaredFields()) {
            try {
                if (field.getName().contains(IMAGE_FLAG)) {
                    field.setAccessible(true);
                    sImageElement.add(field.get(null).toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTextElementId(String str) {
        return str != null && str.endsWith("Text");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isImageElementId(String str) {
        return sImageElement.contains(str);
    }

    public static String parsePanelId(String str) {
        int indexOf = str.indexOf(SEPARATOR_TOKEN);
        if (indexOf >= 0) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    public static String parseElementId(String str) {
        int indexOf = str.indexOf(SEPARATOR_TOKEN);
        if (indexOf >= 0) {
            return str.substring(indexOf + 1);
        }
        return str;
    }
}
