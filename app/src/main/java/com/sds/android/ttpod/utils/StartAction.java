package com.sds.android.ttpod.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;


import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.data.User;


import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
import com.sds.android.ttpod.activities.version.VersionUpgradeProgressActivity;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.WebSlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.MainFragment;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubRankDetailFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubSongCategoryDetailFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.share.ShareInfo;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.a.v */
/* loaded from: classes.dex */
public class StartAction {

    /* renamed from: a */
    private static final String f2518a = StartAction.class.getName();

    /* renamed from: d */
    private static boolean f2519d;

    /* renamed from: b */
    private BaseFragment f2520b;

    /* renamed from: c */
    private BaseActivity f2521c;

    public StartAction(BaseActivity baseActivity, BaseFragment baseFragment) {
        this.f2521c = baseActivity;
        this.f2520b = baseFragment;
    }

    public StartAction(BaseActivity baseActivity) {
        this.f2521c = baseActivity;
    }

    /* renamed from: a */
    public static boolean m8233a() {
        return f2519d;
    }

    /* renamed from: a */
    public static void m8219a(boolean z) {
        f2519d = z;
    }

    /* renamed from: a */
    public static boolean m8229a(Uri uri) {
        return uri == null || !"ttpod".equals(uri.getScheme());
    }

    /* renamed from: b */
    public boolean m8216b(Uri uri) {
        if (uri == null) {
            return false;
        }
        if ("ttpod".equals(uri.getScheme()) && "open".equals(uri.getHost())) {
            return m8213c(uri);
        }
        if ("ttpod".equals(uri.getScheme())) {
            return m8228a(m8210d(uri));
        }
        if (FeedbackMessage.TYPE_FILE.equals(uri.getScheme())) {
            Bundle bundle = new Bundle();
            bundle.putString("uri", uri.getPath());
            return m8195l(bundle);
        }
        return m8213c(uri);
    }

    /* renamed from: a */
    public boolean m8228a(Bundle bundle) {
        boolean m8215b;
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("action");
        int m8227a = (int) m8227a(bundle, "sid", 0L);
        m8231a(m8227a, string, true);
        if ("play".equals(string)) {
            m8219a(true);
            m8215b = m8212c(bundle);
        } else if ("play_next".equals(string)) {
            m8215b = m8209d(bundle);
        } else if ("play_previous".equals(string)) {
            m8215b = m8207e(bundle);
        } else if ("play_pause".equals(string)) {
            m8215b = m8203g(bundle);
        } else if ("play_stop".equals(string)) {
            m8215b = m8205f(bundle);
        } else if ("play_resume".equals(string)) {
            m8215b = m8201h(bundle);
        } else if ("download".equals(string)) {
            m8215b = m8193n(bundle);
        } else if ("play_list".equals(string)) {
            m8215b = m8191p(bundle);
        } else if ("user_post_list".equals(string)) {
            m8215b = m8190q(bundle);
        } else if ("post_detail".equals(string)) {
            m8215b = m8189r(bundle);
        } else if ("open_web".equals(string)) {
            m8215b = m8188s(bundle);
        } else if ("search".equals(string)) {
            m8215b = m8187t(bundle);
        } else if ("share".equals(string)) {
            m8215b = m8197j(bundle);
        } else if ("dialog".equals(string)) {
            m8215b = m8199i(bundle);
        } else {
            m8215b = m8215b(bundle);
        }
        if (m8215b) {
            m8231a(m8227a, string, false);
        }
        return m8215b;
    }

    /* renamed from: c */
    private boolean m8213c(Uri uri) {
        String queryParameter = uri.getQueryParameter("id");
        String queryParameter2 = uri.getQueryParameter("target");
        if (!StringUtils.isEmpty(queryParameter) && !StringUtils.isEmpty(queryParameter2)) {
            long m8204f = m8204f(queryParameter);
            Bundle bundle = new Bundle();
            if ("post".equals(queryParameter2)) {
                bundle.putLong("post_id", m8204f);
                return m8189r(bundle);
            } else if ("user".equals(queryParameter2)) {
                bundle.putLong("user_id", m8204f);
                return m8190q(bundle);
            }
        }
        return false;
    }

    /* renamed from: b */
    private boolean m8215b(Bundle bundle) {
        long m8227a = m8227a(bundle, "postId", 0L);
        if (0 != m8227a) {
            bundle.putLong("post_id", m8227a);
            bundle.putInt("category_type", 1);
            //StatisticUtils.m4909a("push", "show", "gexin", 1L);
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_PUSH_OPEN.getValue(), SPage.PAGE_PUSH.getValue(), SPage.PAGE_ONLINE_POST_DETAIL.getValue()).append("cid", Preferences.m2945az()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(m8227a)).post();
            return m8189r(bundle);
        }
        long m8227a2 = m8227a(bundle, "rankId", 0L);
        if (m8227a2 > 0) {
            this.f2521c.launchFragment(new SubRankDetailFragment((int) m8227a2));
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_PUSH_OPEN.getValue(), SPage.PAGE_PUSH.getValue(), SPage.PAGE_ONLINE_RANK_DETAIL.getValue()).append("cid", Preferences.m2945az()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(m8227a2)).post();
            return true;
        }
        long m8227a3 = m8227a(bundle, "categoryId", 0L);
        if (m8227a3 > 0) {
            this.f2521c.launchFragment(new SubSongCategoryDetailFragment(String.valueOf(m8227a3)));
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_PUSH_OPEN.getValue(), SPage.PAGE_PUSH.getValue(), SPage.PAGE_ONLINE_SONG_CATEGORY.getValue()).append("cid", Preferences.m2945az()).append("channel_id", Long.valueOf(m8227a3)).post();
            return true;
        }
        String string = bundle.getString("link");
        if (!StringUtils.isEmpty(string)) {
            bundle.putInt("category_type", 1);
            bundle.putString(WebFragment.EXTRA_TITLE, this.f2521c.getString(R.string.detail_page));
            bundle.putString(WebFragment.EXTRA_URL, string);
            bundle.putBoolean(WebFragment.EXTRA_IS_SHOW_PLAY_CONTROL_BAR, true);
            //StatisticUtils.m4909a("push", "show", "gexin", 2L);
            WebSlidingClosableFragment webSlidingClosableFragment = new WebSlidingClosableFragment();
            webSlidingClosableFragment.setArguments(bundle);
            this.f2521c.launchFragment(webSlidingClosableFragment);
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_PUSH_OPEN.getValue(), SPage.PAGE_PUSH.getValue(), SPage.PAGE_ONLINE_POST_DETAIL_WEB.getValue()).append("cid", Preferences.m2945az()).append("url", string).post();
            return true;
        }
        String string2 = bundle.getString("search");
        if (!StringUtils.isEmpty(string2)) {
            bundle.putString("search", string2);
            return m8187t(bundle);
        }
        String string3 = bundle.getString("uri");
        int m8227a4 = (int) m8227a(bundle, "app", 0L);
        if (!StringUtils.isEmpty(string3) && m8227a4 > 0) {
            bundle.putString("uri", string3);
            return m8212c(bundle);
        } else if (bundle.getBoolean("push_redirect_id")) {
            m8218b();
            //new SUserEvent("PAGE_CLICK", SAction.ACTION_PUSH_OPEN.getValue(), SPage.PAGE_PUSH.getValue(), SPage.PAGE_ONLINE_FIND_SONG.getValue()).append("cid", Preferences.m2945az()).post();
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: c */
    private boolean m8212c(Bundle bundle) {
        String string = bundle.getString("uri");
        String string2 = bundle.getString("song_id");
        if (m8200h(string)) {
            return m8194m(bundle);
        }
        if (m8198i(string)) {
            return m8195l(bundle);
        }
        if (!StringUtils.isEmpty(string2)) {
            return m8196k(bundle);
        }
        return false;
    }

    /* renamed from: d */
    private boolean m8209d(Bundle bundle) {
        m8217b((int) m8227a(bundle, "play_mode", -1L));
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.NEXT, new Object[0]));
        return true;
    }

    /* renamed from: e */
    private boolean m8207e(Bundle bundle) {
        m8217b((int) m8227a(bundle, "play_mode", -1L));
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.PREVIOUS, new Object[0]));
        return true;
    }

    /* renamed from: f */
    private boolean m8205f(Bundle bundle) {
        m8217b((int) m8227a(bundle, "play_mode", -1L));
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.STOP, new Object[0]));
        return true;
    }

    /* renamed from: g */
    private boolean m8203g(Bundle bundle) {
        m8217b((int) m8227a(bundle, "play_mode", -1L));
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.PAUSE, new Object[0]));
        return true;
    }

    /* renamed from: h */
    private boolean m8201h(Bundle bundle) {
        m8217b((int) m8227a(bundle, "play_mode", -1L));
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.RESUME, new Object[0]));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m8230a(long j, boolean z, Bundle bundle) {
        if (!z && 1 == j && this.f2521c != null) {
            String m8226a = m8226a(bundle, "uri", "");
            if (!StringUtils.isEmpty(m8226a)) {
                CommandCenter.getInstance().execute(new Command(CommandID.START_COMMON_UPGRADE, m8226a));
                this.f2521c.startActivity(new Intent(this.f2521c, VersionUpgradeProgressActivity.class));
            }
        }
    }

    /* renamed from: i */
    private boolean m8199i(final Bundle bundle) {
        if (this.f2521c != null) {
            String m8226a = m8226a(bundle, "title", "提示");
            String m8226a2 = m8226a(bundle, "message", "内容");
            String m8226a3 = m8226a(bundle, "button_ok", "");
            String m8226a4 = m8226a(bundle, "button_cancel", "");
            final long m8227a = m8227a(bundle, "type", 0L);
            MessageDialog messageDialog = new MessageDialog(this.f2521c, m8226a2, (int) R.string.ok, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.a.v.1
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    StartAction.this.m8230a(m8227a, false, bundle);
                }
            }, (int) R.string.cancel, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.a.v.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    StartAction.this.m8230a(m8227a, true, bundle);
                }
            });
            if (!StringUtils.isEmpty(m8226a3)) {
                messageDialog.m7258a(m8226a3);
            }
            if (!StringUtils.isEmpty(m8226a4)) {
                messageDialog.m7252b(m8226a4);
            }
            messageDialog.setTitle(m8226a);
            messageDialog.show();
            return false;
        }
        return false;
    }

    /* renamed from: j */
    private boolean m8197j(Bundle bundle) {
        String m8226a = m8226a(bundle, "url", "");
        if (StringUtils.isEmpty(m8226a)) {
            return false;
        }
        String m8226a2 = m8226a(bundle, "title", "");
        String m8226a3 = m8226a(bundle, User.KEY_AVATAR, "");
        String m8226a4 = m8226a(bundle, "desc", "");
        String m8226a5 = m8226a(bundle, "type", "");
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.m1961c(true);
        shareInfo.m1957e(m8226a2);
        shareInfo.m1962c(m8226a3);
        shareInfo.m1951h(m8226a);
        shareInfo.m1949i(m8226a5);
        shareInfo.m1959d(m8226a4 + " " + m8226a);
        PopupsUtils.m6753a((Activity) this.f2521c, shareInfo);
        return true;
    }

    /* renamed from: k */
    private boolean m8196k(Bundle bundle) {
        String string = bundle.getString("song_id");
        final int m8227a = (int) m8227a(bundle, "play_mode", -1L);
        final int m8227a2 = (int) m8227a(bundle, "position", 0L);
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        List<Long> m8338b = StringUtils.stringToLongArray(string, ",");
        if (m8338b.size() > 0) {
            OnlineMediaUtils.m4675a(m8338b, new OnlineMediaUtils.InterfaceC1790a<List<MediaItem>>() { // from class: com.sds.android.ttpod.a.v.3
                @Override // com.sds.android.ttpod.framework.p106a.OnlineMediaUtils.InterfaceC1790a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo4039a(List<MediaItem> list) {
                    if (list != null && list.size() > 0) {
                        StartAction.this.m8221a(list, m8227a, m8227a2);
                    }
                }
            });
        }
        return true;
    }

    /* renamed from: l */
    private boolean m8195l(Bundle bundle) {
        String string = bundle.getString("uri");
        int m8227a = (int) m8227a(bundle, "play_mode", -1L);
        if (StringUtils.isEmpty(string) || !FileUtils.m8419a(string)) {
            return false;
        }
        CommandCenter.getInstance().execute(new Command(CommandID.PLAY, string));
        m8217b(m8227a);
        return true;
    }

    /* renamed from: m */
    private boolean m8194m(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        String string = bundle.getString("uri");
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("artist");
        int m8227a = (int) m8227a(bundle, "position", 0L);
        int m8227a2 = (int) m8227a(bundle, "duration", 0L);
        int m8227a3 = (int) m8227a(bundle, "play_mode", -1L);
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        arrayList.add(MediaItemUtils.m4711a(string, string2, string3, m8227a2));
        return m8221a(arrayList, m8227a3, m8227a);
    }

    /* renamed from: a */
    private void m8231a(int i, String str, boolean z) {
        if (i <= 0) {
            return;
        }
        //new //SSystemEvent("SYS_THIRDPARTY", str).append("sid", Integer.valueOf(i)).append("action", z ? "0" : "1").post();
    }

    /* renamed from: n */
    private boolean m8193n(Bundle bundle) {
        String string = bundle.getString("song_id");
        if (!StringUtils.isEmpty(string)) {
            return m8214b(string);
        }
        DownloadTaskInfo m8192o = m8192o(bundle);
        if (m8192o != null) {
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_DOWNLOAD_TASK, m8192o));
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private boolean m8214b(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        List<Long> m8338b = StringUtils.stringToLongArray(str, ",");
        if (m8338b.size() > 0) {
            OnlineMediaUtils.m4675a(m8338b, new OnlineMediaUtils.InterfaceC1790a<List<MediaItem>>() { // from class: com.sds.android.ttpod.a.v.4
                @Override // com.sds.android.ttpod.framework.p106a.OnlineMediaUtils.InterfaceC1790a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo4039a(List<MediaItem> list) {
                    if (list != null && list.size() > 0) {
                        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ASYN_ADD_DOWNLOAD_TASK_LIST, DownloadUtils.m4758a(list, AudioQuality.HIGH), Boolean.FALSE), 10);
                    }
                }
            });
            return true;
        }
        return false;
    }

    /* renamed from: o */
    private DownloadTaskInfo m8192o(Bundle bundle) {
        String string = bundle.getString("uri");
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        int m8227a = (int) m8227a(bundle, "type", m8211c(string));
        int intValue = (m8227a > DownloadTaskInfo.TYPE_APP.intValue() || m8227a < DownloadTaskInfo.TYPE_AUDIO.intValue()) ? DownloadTaskInfo.TYPE_AUDIO.intValue() : m8227a;
        String string2 = bundle.getString("path");
        if (StringUtils.isEmpty(string2) || !FileUtils.m8419a(string2)) {
            string2 = m8232a(intValue);
        }
        String string3 = bundle.getString("name");
        StringBuilder append = new StringBuilder(string2).append(File.separator);
        if (!StringUtils.isEmpty(string3)) {
            String m8399m = FileUtils.getSuffix(string);
            String m8399m2 = FileUtils.getSuffix(string3);
            append.append(string3);
            if (StringUtils.isEmpty(m8399m2) && !StringUtils.isEmpty(m8399m)) {
                append.append(".").append(m8399m);
            }
        } else {
            string3 = FileUtils.m8401k(string);
            append.append(FileUtils.getFilename(string));
        }
        return DownloadUtils.m4760a(string, append.toString(), 0L, string3, Integer.valueOf(intValue), true, "thirdparty_download");
    }

    /* renamed from: c */
    private int m8211c(String str) {
        String m8399m = FileUtils.getSuffix(str);
        if (!StringUtils.isEmpty(m8399m)) {
            if (m8399m.equalsIgnoreCase("apk")) {
                return DownloadTaskInfo.TYPE_APP.intValue();
            }
            if (m8399m.equalsIgnoreCase("mp4")) {
                return DownloadTaskInfo.TYPE_VIDEO.intValue();
            }
        }
        return DownloadTaskInfo.TYPE_AUDIO.intValue();
    }

    /* renamed from: a */
    private String m8232a(int i) {
        if (DownloadTaskInfo.TYPE_AUDIO.intValue() == i) {
            return TTPodConfig.getSongPath();
        }
        if (DownloadTaskInfo.TYPE_APP.intValue() == i) {
            return TTPodConfig.getAppPath();
        }
        if (DownloadTaskInfo.TYPE_VIDEO.intValue() == i) {
            return TTPodConfig.getMvPath();
        }
        if (DownloadTaskInfo.TYPE_SKIN.intValue() == i) {
            return TTPodConfig.getSkinPath();
        }
        return TTPodConfig.getTTPodPath();
    }

    /* renamed from: b */
    private void m8217b(int i) {
        if (i >= 0 && i < PlayMode.values().length) {
            Preferences.m3018a(PlayMode.values()[i]);
            CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_PLAY_MODE, new Object[0]), ModuleID.SUPPORT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m8221a(List<MediaItem> list, int i, int i2) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        if (i2 >= list.size()) {
            i2 = 0;
        }
        CommandCenter.getInstance().execute(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, list));
        CommandCenter.getInstance().execute(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list.get(i2)));
        m8217b(i);
        return true;
    }

    /* renamed from: p */
    private boolean m8191p(Bundle bundle) {
        String string = bundle.getString("media_json");
        int m8227a = (int) m8227a(bundle, "play_mode", -1L);
        int m8227a2 = (int) m8227a(bundle, "position", 0L);
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        return m8221a(m8220a(m8202g(m8206e(string))), m8227a, m8227a2);
    }

    /* renamed from: q */
    private boolean m8190q(Bundle bundle) {
        long m8227a = m8227a(bundle, "user_id", 0L);
        if (m8227a > 0) {
            TTPodUser tTPodUser = new TTPodUser();
            tTPodUser.setUserId(m8227a);
            this.f2521c.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, "start-action"));
            return true;
        }
        return false;
    }

    /* renamed from: r */
    private boolean m8189r(Bundle bundle) {
        long m8227a = m8227a(bundle, "post_id", 0L);
        long m8227a2 = m8227a(bundle, "display_type", 0L);
        if (m8227a > 0) {
            this.f2521c.launchFragment(SubPostDetailFragment.createById(m8227a, "start-action"));
            if (1 == m8227a2) {
                m8218b();
            }
            return true;
        }
        return false;
    }

    /* renamed from: s */
    private boolean m8188s(Bundle bundle) {
        String string = bundle.getString("uri");
        String string2 = bundle.getString("title");
        long m8227a = m8227a(bundle, "display_type", 0L);
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        Intent intent = new Intent(this.f2521c, WebActivity.class);
        intent.setData(Uri.parse(string));
        intent.putExtra(WebFragment.EXTRA_TITLE, string2);
        intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
        this.f2521c.startActivity(intent);
        if (1 == m8227a) {
            m8218b();
        }
        return true;
    }

    /* renamed from: t */
    private boolean m8187t(Bundle bundle) {
        String string = bundle.getString("search");
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        m8222a(string, (int) m8227a(bundle, "id", 0L));
        return true;
    }

    /* renamed from: b */
    private void m8218b() {
        if (this.f2520b instanceof MainFragment) {
            ((MainFragment) this.f2520b).toggleFindSongFragment();
        }
    }

    /* renamed from: a */
    private void m8222a(String str, int i) {
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SearchResultFragment.KEY_SEARCH_WORD, str);
        bundle.putBoolean(OnlineSearchEntryActivity.KEY_FROM_THIRD, true);
        bundle.putInt("app", i);
        searchResultFragment.setArguments(bundle);
        this.f2521c.launchFragment(searchResultFragment);
    }

    /* renamed from: d */
    private String m8208d(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: e */
    private String m8206e(String str) {
        try {
            return new String(Base64.decode(str.getBytes(), 0));
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: a */
    private long m8227a(Bundle bundle, String str, long j) {
        Object obj = bundle.get(str);
        if (obj != null && !StringUtils.isEmpty(obj.toString())) {
            return m8204f(obj.toString());
        }
        return j;
    }

    /* renamed from: a */
    private String m8226a(Bundle bundle, String str, String str2) {
        String string = bundle.getString(str);
        return StringUtils.isEmpty(string) ? str2 : string;
    }

    /* renamed from: d */
    private Bundle m8210d(Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putString("scheme", uri.getScheme());
        bundle.putString("action", uri.getHost());
        for (String str : m8223a(uri.getEncodedQuery())) {
            String queryParameter = uri.getQueryParameter(str);
            if (!StringUtils.isEmpty(queryParameter)) {
                bundle.putString(str, queryParameter);
            }
        }
        return bundle;
    }

    /* renamed from: a */
    public Set<String> m8223a(String str) {
        if (str == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        do {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            linkedHashSet.add(m8208d(str.substring(i, indexOf2)));
            i = indexOf + 1;
        } while (i < str.length());
        return Collections.unmodifiableSet(linkedHashSet);
    }

    /* renamed from: f */
    private long m8204f(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0L;
        }
    }

    /* renamed from: g */
    private JSONArray m8202g(String str) {
        if (!StringUtils.isEmpty(str)) {
            try {
                return new JSONArray(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new JSONArray();
    }

    /* renamed from: a */
    private List<MediaItem> m8220a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("uri");
                if (!StringUtils.isEmpty(string)) {
                    arrayList.add(MediaItemUtils.m4711a(string, jSONObject.optString("title", ""), jSONObject.optString("artist", ""), (int) m8204f(jSONObject.optString("duration", "0"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /* renamed from: h */
    private boolean m8200h(String str) {
        return !StringUtils.isEmpty(str) && (str.startsWith("http://") || str.startsWith("https://"));
    }

    /* renamed from: i */
    private boolean m8198i(String str) {
        return !StringUtils.isEmpty(str) && FileUtils.m8419a(str);
    }
}
