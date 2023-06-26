package com.sds.android.ttpod.component.p086c;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.TTPodApplication;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.component.OnlineMediaUrlWrapper;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaItemUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/* renamed from: com.sds.android.ttpod.component.c.b */
/* loaded from: classes.dex */
public final class DownloadMenuHandler {

    /* renamed from: a */
    private static final String f3858a = DownloadMenuHandler.class.getSimpleName();

    /* renamed from: b */
    private static SparseArray<AudioQuality> f3859b = new SparseArray<>();

    /* renamed from: c */
    private static final String[] f3860c = TTPodApplication.getApplication().getResources().getStringArray(R.array.song_quality_list);

    /* renamed from: d */
    private Activity activity;

    /* renamed from: e */
    private List<MediaItem> f3862e;

    /* renamed from: f */
    private String f3863f;

    /* renamed from: g */
    private SAction f3864g = SAction.ACTION_NONE;

    static {
        f3859b.put(0, AudioQuality.LOSSLESS);
        f3859b.put(1, AudioQuality.SUPER);
        f3859b.put(2, AudioQuality.STANDARD);
        f3859b.put(3, AudioQuality.COMPRESSED);
    }

    public DownloadMenuHandler(Activity activity) {
        this.activity = activity;
    }

    /* renamed from: a */
    public void m6930a(SAction sAction) {
        this.f3864g = sAction;
    }

    /* renamed from: a */
    public void m6926a(List<MediaItem> list) {
        if (ListUtils.m4718a(list)) {
            if (EnvironmentUtils.AppConfig.getTestMode()) {
                throw new IllegalArgumentException("mediaItem should not be null");
            }
            LogUtils.error(f3858a, "handleSongListDownload mediaItem should not be null");
            return;
        }
        this.f3862e = list;
        if (m6924b() <= 0) {
            PopupsUtils.m6721a("已经下载了");
        } else if (!EnvironmentUtils.DeviceConfig.m8474e()) {
            PopupsUtils.m6760a((int) R.string.network_unavailable);
        } else {
            PopupsUtils.m6728a(this.activity, m6921c(), this.activity.getString(R.string.title_choose_download_playlist, new Object[]{Integer.valueOf(m6924b())}), m6941a());
        }
    }

    /* renamed from: a */
    public void m6927a(MediaItem mediaItem, String str) {
        if (mediaItem == null) {
            if (EnvironmentUtils.AppConfig.getTestMode()) {
                throw new IllegalArgumentException("mediaItem should not be null");
            }
            LogUtils.error(f3858a, "handleSingleSongDownload mediaItem should not be null");
            return;
        }
        this.f3862e = new ArrayList(1);
        this.f3862e.add(mediaItem);
        this.f3863f = str;
        String extra = mediaItem.getExtra();
        if (extra != null && !this.activity.isFinishing()) {
            OnlineMediaItem onlineMediaItem = JSONUtils.fromJson(extra,  OnlineMediaItem.class);
            if (onlineMediaItem == null) {
                LogUtils.error(f3858a, "cast to onlineMediaItem is null");
                return;
            }
            AudioQuality m3058L = Preferences.m3058L();
            if (m3058L != AudioQuality.UNDEFINED) {
                m6928a(mediaItem, SPage.PAGE_NONE);
                m6936a(onlineMediaItem, mediaItem, m6937a(onlineMediaItem, m3058L));
                return;
            }
            m6928a(mediaItem, SPage.PAGE_DIALOG_DOWNLOAD);
            m6929a(mediaItem, onlineMediaItem);
        }
    }

    /* renamed from: a */
    private void m6928a(MediaItem mediaItem, SPage sPage) {

    }

    /* renamed from: a */
    private ActionItem.InterfaceC1135b m6941a() {
        return new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.c.b.1
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                AudioQuality m6940a = DownloadMenuHandler.this.m6940a(actionItem.m7005e());
                if (DownloadMenuHandler.this.f3864g != SAction.ACTION_NONE) {

                }
                List m6925a = DownloadMenuHandler.this.m6925a(DownloadMenuHandler.this.f3862e, m6940a);
                CommandCenter.getInstance().execute(new Command(CommandID.ASYN_ADD_DOWNLOAD_TASK_LIST, m6925a, false));
                PopupsUtils.m6721a(DownloadMenuHandler.this.activity.getString(R.string.toast_download_songs, new Object[]{Integer.valueOf(m6925a.size())}));
            }
        };
    }

    /* renamed from: b */
    private int m6924b() {
        int i = 0;
        Iterator<MediaItem> it = this.f3862e.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                i = StringUtils.isEmpty(it.next().getLocalDataSource()) ? i2 + 1 : i2;
            } else {
                return i2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public AudioQuality m6940a(int i) {
        return f3859b.get(i);
    }

    /* renamed from: c */
    private List<ActionItem> m6921c() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < f3860c.length; i++) {
            if (!m6920c(i)) {
                ActionItem actionItem = new ActionItem(i, 0, m6923b(i));
                m6935a(actionItem);
                arrayList.add(actionItem);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m6935a(ActionItem actionItem) {
        switch (actionItem.m7005e()) {
            case 0:
                actionItem.m7012a(R.string.icon_text_wusun);
                actionItem.m7011a(ActionItem.EnumC1134a.TITLE_ICON);
                return;
            case 1:
                actionItem.m7012a(R.string.icon_text_gaozhi);
                actionItem.m7011a(ActionItem.EnumC1134a.TITLE_ICON);
                return;
            default:
                actionItem.m7011a(ActionItem.EnumC1134a.NO_ICON);
                return;
        }
    }

    /* renamed from: b */
    private String m6923b(int i) {
        return String.format(f3860c[i], Float.valueOf(((float) ((Long) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_TOTAL_DOWNLOAD_FILE_SIZE, this.f3862e, m6940a(i)), Long.class)).longValue()) / 1048576.0f));
    }

    /* renamed from: c */
    private boolean m6920c(int i) {
        if (this.f3862e.size() > 1) {
            return false;
        }
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) JSONUtils.fromJson(this.f3862e.get(0).getExtra(), OnlineMediaItem.class);
        if (onlineMediaItem == null) {
            return true;
        }
        if (i == 0) {
            return onlineMediaItem.getLLUrls() == null || onlineMediaItem.getLLUrls().isEmpty();
        } else if (i == 1) {
            OnlineMediaItem.Url m4682b = OnlineMediaItemUtils.m4682b(onlineMediaItem, AudioQuality.SUPER);
            return m4682b == null || m4682b.getBitrate() < AudioQuality.bitrateRange(AudioQuality.SUPER)[0];
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public List<DownloadTaskInfo> m6925a(List<MediaItem> list, AudioQuality audioQuality) {
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (!StringUtils.isEmpty(((MediaItem) arrayList.get(size)).getLocalDataSource())) {
                arrayList.remove(size);
            } else {
                DownloadTaskInfo m4761a = DownloadUtils.m4761a((MediaItem) arrayList.get(size), audioQuality);
                if (m4761a != null) {
                    if (FileUtils.m8419a(m4761a.getSavePath())) {
                        MediaItemUtils.m4714a((MediaItem) arrayList.get(size), m4761a.getSavePath());
                        MediaStorage.updateMediaItem(this.activity, (MediaItem) arrayList.get(size));
                        arrayList.remove(size);
                    } else {
                        arrayList2.add(m4761a);
                    }
                }
            }
        }
        Collections.reverse(arrayList2);
        return arrayList2;
    }

    /* renamed from: a */
    public void m6929a(final MediaItem mediaItem, final OnlineMediaItem onlineMediaItem) {
        List<ActionItem> m6938a = m6938a(onlineMediaItem);
        final View inflate = this.activity.getLayoutInflater().inflate(R.layout.dialog_list_footer_remember, (ViewGroup) null, false);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.c.b.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.dialog_check_box);
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        PopupsUtils.m6727a(this.activity, m6938a, this.activity.getString(R.string.choose_music_download_quality), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.c.b.3
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                if (inflate != null && ((CheckBox) inflate.findViewById(R.id.dialog_check_box)).isChecked()) {
                    Preferences.m2895d(AudioQuality.quality(((OnlineMediaUrlWrapper) actionItem.m7004f()).m7013b().getBitrate()));
                }
                OnlineMediaUrlWrapper onlineMediaUrlWrapper = (OnlineMediaUrlWrapper) actionItem.m7004f();
                OnlineMediaItem.Url m7013b = onlineMediaUrlWrapper.m7013b();

                if (OnlineMediaUrlWrapper.EnumC1133a.MEDIA == onlineMediaUrlWrapper.m7014a()) {
                    DownloadMenuHandler.this.m6936a(onlineMediaItem, mediaItem, m7013b);
                }
            }
        }, inflate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m6936a(OnlineMediaItem onlineMediaItem, MediaItem mediaItem, OnlineMediaItem.Url url) {
        if (url == null) {
            LogUtils.error(f3858a, "downloadSingleSong url is null");
            return;
        }
        DownloadTaskInfo m4759a = DownloadUtils.m4759a(mediaItem.getGroupID(), url.getUrl(), OnlineMediaItemUtils.m4688a(onlineMediaItem, url), Long.valueOf(onlineMediaItem.getSongId()), onlineMediaItem.getTitle(), DownloadTaskInfo.TYPE_AUDIO, true, this.f3863f);
        m4759a.setAudioQuality(AudioQuality.quality(url.getBitrate()).toString());
        m4759a.setTag(mediaItem);

        m4759a.setSongType(m6939a(url));
        CommandCenter.getInstance().execute(new Command(CommandID.ADD_DOWNLOAD_TASK, m4759a));
    }

    /* renamed from: a */
    private OnlineMediaItem.Url m6937a(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        List<OnlineMediaItem.Url> downloadUrls = onlineMediaItem.getDownloadUrls();
        List<OnlineMediaItem.Url> lLUrls = onlineMediaItem.getLLUrls();
        ArrayList<OnlineMediaItem.Url> arrayList = new ArrayList();
        if (downloadUrls != null) {
            arrayList.addAll(downloadUrls);
        }
        if (lLUrls != null) {
            arrayList.addAll(lLUrls);
        }
        if (arrayList.isEmpty()) {
            LogUtils.error(f3858a, "mediaDownloadUrls is empty, the song may offline");
            return null;
        }
        int[] bitrateRange = AudioQuality.bitrateRange(audioQuality);
        for (OnlineMediaItem.Url url : arrayList) {
            if (url.getBitrate() > bitrateRange[0] && url.getBitrate() <= bitrateRange[1]) {
                return url;
            }
        }
        return (OnlineMediaItem.Url) arrayList.get(arrayList.size() - 1);
    }

    /* renamed from: a */
    private List<ActionItem> m6938a(OnlineMediaItem onlineMediaItem) {
        List<OnlineMediaItem.Url> downloadUrls = onlineMediaItem.getDownloadUrls();
        List<OnlineMediaItem.Url> lLUrls = onlineMediaItem.getLLUrls();
        ArrayList<OnlineMediaItem.Url> arrayList = new ArrayList();
        if (downloadUrls != null) {
            arrayList.addAll(downloadUrls);
        }
        if (lLUrls != null) {
            arrayList.addAll(lLUrls);
        }
        ArrayList arrayList2 = new ArrayList();
        for (OnlineMediaItem.Url url : arrayList) {
            ActionItem actionItem = new ActionItem(url.getUrl().hashCode(), 0, url.getTypeDescription() + " " + url.getSize().replaceAll(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY, ""));
            if (AudioQuality.quality(url.getBitrate()) == AudioQuality.LOSSLESS) {
                actionItem.m7012a(R.string.icon_text_wusun);
                actionItem.m7011a(ActionItem.EnumC1134a.TITLE_ICON);
            } else if (AudioQuality.quality(url.getBitrate()) == AudioQuality.SUPER) {
                actionItem.m7011a(ActionItem.EnumC1134a.TITLE_ICON);
                actionItem.m7012a(R.string.icon_text_gaozhi);
            } else {
                actionItem.m7011a(ActionItem.EnumC1134a.NO_ICON);
            }
            actionItem.m7009a(new OnlineMediaUrlWrapper(OnlineMediaUrlWrapper.EnumC1133a.MEDIA, url));
            arrayList2.add(actionItem);
        }
        Collections.reverse(arrayList2);
        return arrayList2;
    }

    /* renamed from: a */
    private int m6939a(OnlineMediaItem.Url url) {
        String[] strArr = {"压缩", "标准", "高品", "超高", "无损"};
        for (int length = strArr.length - 1; length >= 0; length--) {
            if (url.getTypeDescription().contains(strArr[length])) {
                if (length >= 2) {
                    return length + 1;
                } else {
                    if (length == 1) {
                        return !url.getFormat().equals("mp3") ? 1 : 2;
                    }
                    return 0;
                }
            }
        }
        return 0;
    }
}
