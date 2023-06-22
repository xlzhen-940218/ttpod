package com.voicedragon.musicclient.util;

import android.os.Environment;

/* loaded from: classes.dex */
public class MRadar {
    public static final String PACKNAME = "com.voicedragon.musicclient";
    public static final String PACKNAME_GOOGLE = "com.voicedragon.musicclient.googleplay";
    public static final boolean isDownloadTtpod = true;

    /* loaded from: classes.dex */
    public static class EXTRA {
        public static final String TOACTIVITYENLOCALPLAY = "toactivityenlocalplay";
        public static final String TOACTIVITYMORE = "toactivitymore";
        public static final String TOARTISTINFO = "toartistinfo";
        public static final String TOARTISTRECOMMAND = "toartistrecommand";
        public static final String TOARTISTRECOMMAND_EN = "toartistrecommand_en";
        public static final String TOCOMMENTMD5 = "tocommentmd5";
        public static final String TOCOMMENTTYPE = "tocommenttype";
        public static final String TOFUZZY = "tofuzzy";
        public static final String TOLOCALSONGLIST = "tolocalsonglist";
        public static final String TOMV = "tomv";
        public static final String TOSHARE = "toshare";
        public static final String TOSHAREFFID = "toshareffid";
        public static final String TOSHAREPFID = "tosharepfid";
        public static final String TOSINGLE = "tosingle";
        public static final String TOSONGMENUSONGS_KEY = "tosongmenusongs_key";
        public static final String TOSONGMENUSONGS_NAME = "tosongmenusongs_name";
    }

    /* loaded from: classes.dex */
    public static class KEY {
        public static final int FAVSHARE = 9;
        public static final int FAVSONG = 5;
        public static final int FEEDINFO = 12;
        public static final int FLOAT = 2;
        public static final int LOCALPLAYEN = 7;
        public static final int LocalList = 3;
        public static final int LocalSONGList = 4;
        public static final int OTHERS = 10;
        public static final int PERSONAL = 11;
        public static final int PLAZA = 8;
        public static final int SINGLE = 1;
        public static final int SINGLE_EN = 0;
        public static final int SONGMENUSONGS = 6;
        public static final int WOMUSIC = 13;
    }

    /* loaded from: classes.dex */
    public static class Login {

        /* renamed from: CN */
        public static final String f1072CN = "zh";
        public static final int Facebook = 8;
        public static int GENDER = 0;

        /* renamed from: QQ */
        public static final int f1073QQ = 7;
        public static final int QZone = 3;
        public static final int Renren = 10;
        public static final int SinaWeibo = 1;
        public static final int TencentWeibo = 2;
        public static final int Twitter = 9;

        /* renamed from: VN */
        public static final String f1074VN = "VN";
        public static final int Wechat = 4;
        public static final int WechatMoments = 5;
        public static String NAME = "";
        public static String IDESC = "";
        public static String TOKEN = "";
        public static String SORT = "";
        public static String UID = "";
        public static String PHOTOTIME = "";
        public static String TAG = "";
    }

    /* loaded from: classes.dex */
    public static class LoginInfo {
        public static final String LOGININFO = "logininfo";
        public static final String LOGININFOGENDER = "logininfogender";
        public static final String LOGININFOIDESC = "logininfoidesc";
        public static final String LOGININFONAME = "logininfoname";
        public static final String LOGININFOPHOTOTIME = "logininfophototime";
        public static final String LOGININFOSORT = "logininfosort";
        public static final String LOGININFOTAG = "logininfotag";
        public static final String LOGININFOTOKEN = "logininfotoken";
        public static final String LOGININFOUID = "logininfouid";
    }

    /* loaded from: classes.dex */
    public static class MOREKEY {
        public static boolean IS_SERACH_3G = false;
        public static boolean IS_SLEEP = false;
        public static boolean IS_VIBRATOR = false;
        public static final String MORE = "more";
        public static final String SEARCH_WIFI = "search_wifi";
        public static final String VIBRATOR = "audio_search";
    }

    /* loaded from: classes.dex */
    public static class Net {
        public static final int NET_2G = 3001;
        public static final int NET_3G = 3002;
        public static final int NET_WIFI = 3000;
    }

    /* loaded from: classes.dex */
    public static class REFRESHTIME {
        public static final String FAVSHARE_LASTTIME = "favshare_lasttime";
        public static final String PERSONAL_LASTTIME = "personal_lasttime";
        public static final String PLAZA_FOLLOW_LASTTIME = "plaza_follow_lasttime";
        public static final String PLAZA_SQUARE_LASTTIME = "plaza_square_lasttime";
        public static final String REFRESH_KEY = "refresh_key";
        public static final long TIME_10 = 60000;
        public static final long TIME_30 = 900000;
    }

    /* loaded from: classes.dex */
    public static class RESULT {
        public static final int PLAZA = 1000;
        public static final int PLAZA_COMMENT_FOLLOW = 1004;
        public static final int PLAZA_COMMENT_SQUARE = 1003;
        public static final int PLAZA_SHARE_FOLLOW = 1002;
        public static final int PLAZA_SHARE_SQUARE = 1001;
        public static final int SINGLE_EN_COMMENT = 1007;
        public static final int SINGLE_EN_FAV = 1006;
        public static final int SINGLE_EN_SHARE = 1005;
    }

    /* loaded from: classes.dex */
    public static class Record {
        public static final long BGDURATION = 1800000;
        public static final long DURATION = 12000;
        public static final String KEY = "311069bfebaf67c4567a8e622f330561";
        public static final String KEY_BGRECORD = "eccd657f5e52756e38efa0f8d2897ca5";
        public static final String NORESULT = "noresult";
        public static final int NORESULT_INITFAIL = 3;
        public static final int NORESULT_NONET = 1;
        public static final int NORESULT_NOSERVER = 2;
        public static final int NORESULT_NOSONG = 0;
        public static String OPTIONS = null;
        public static final long PLAYDURATION = 29000;
        public static final int SEARCH_TYPE_HUM = 0;
        public static final int SEARCH_TYPE_NEW = 2;
        public static final int SEARCH_TYPE_NEW_NEW = 3;
        public static final int SEARCH_TYPE_SONG = 1;
        public static boolean isStart = false;
        public static long START = 0;
        public static boolean isNew = false;
        public static int WIDTH = 0;
    }

    /* loaded from: classes.dex */
    public static class SDPath {
        public static final String SDPATH_ROOT_PATH_DORESO = String.valueOf(Environment.getExternalStorageDirectory().toString()) + "/Doreso/";
        public static final String SDPATH_DORESO_IMAGE = String.valueOf(SDPATH_ROOT_PATH_DORESO) + "Image/";
        public static final String SDPATH_DORESO_MUSIC = String.valueOf(SDPATH_ROOT_PATH_DORESO) + "music/";
        public static final String SDPATH_DORESO_CACHE = String.valueOf(SDPATH_ROOT_PATH_DORESO) + "cache/";
        public static final String SDPATH_DORESO_LYRIC = String.valueOf(SDPATH_ROOT_PATH_DORESO) + "lyric/";
        public static final String SDPATH_DORESO_RECORD = String.valueOf(SDPATH_ROOT_PATH_DORESO) + "record/";
    }

    /* loaded from: classes.dex */
    public static class SINGLE {
        public static final int ITEM_COVER = 1;
        public static final int ITEM_RECOMMEND = 2;
        public static final int ITEM_SINGER = 0;
        public static final int LOADLRC_FROMFILE = 6;
        public static final int LOADLRC_FROMNONE = 7;
        public static final int UPDATE_LRC = 3;
        public static final int UPDATE_LRC_AUTO = 4;
        public static final int UPDATE_PLAYLIST = 5;
        public static final int UPDATE_PLIST = 9;
        public static final int UPDATE_UI = 8;
    }
}
