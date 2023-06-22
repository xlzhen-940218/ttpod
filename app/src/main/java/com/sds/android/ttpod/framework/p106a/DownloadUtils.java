package com.sds.android.ttpod.framework.p106a;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.a.e */
/* loaded from: classes.dex */
public class DownloadUtils {

    /* renamed from: a */
    static final /* synthetic */ boolean f5630a;

    static {
        f5630a = !DownloadUtils.class.desiredAssertionStatus();
    }

    /* renamed from: a */
    public static DownloadTaskInfo m4760a(String str, String str2, Long l, String str3, Integer num, Boolean bool, String str4) {
        return m4759a(MediaStorage.GROUP_ID_DOWNLOAD, str, str2, l, str3, num, bool, str4);
    }

    /* renamed from: a */
    public static DownloadTaskInfo m4759a(String str, String str2, String str3, Long l, String str4, Integer num, Boolean bool, String str5) {
        DownloadTaskInfo downloadTaskInfo = new DownloadTaskInfo(StringUtils.m8346a(str) ? MediaStorage.GROUP_ID_DOWNLOAD : str, str2, str3, num, bool);
        downloadTaskInfo.setFileId(l);
        downloadTaskInfo.setFileName(str4);
        downloadTaskInfo.setOrigin(str5);
        downloadTaskInfo.setCutOffTimes(0);
        downloadTaskInfo.setDownloadTime(0L);
        downloadTaskInfo.setConnectTimeStamp(0L);
        downloadTaskInfo.setRespondTime(0L);
        return downloadTaskInfo;
    }

    /* renamed from: a */
    public static DownloadTaskInfo m4761a(MediaItem mediaItem, AudioQuality audioQuality) {
        OnlineMediaItem.Url m4682b;
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class);
        if (onlineMediaItem == null || (m4682b = OnlineMediaItemUtils.m4682b(onlineMediaItem, audioQuality)) == null) {
            return null;
        }
        DownloadTaskInfo m4760a = m4760a(m4682b.getUrl(), OnlineMediaItemUtils.m4688a(onlineMediaItem, m4682b), Long.valueOf(onlineMediaItem.getSongId()), onlineMediaItem.getTitle(), DownloadTaskInfo.TYPE_AUDIO, true, "专辑下载");
        m4760a.setAudioQuality(AudioQuality.quality(m4682b.getBitrate()).toString());
        m4760a.setTag(mediaItem);
        if (!StringUtils.m8346a(mediaItem.getGroupID())) {
            m4760a.setGroupId(mediaItem.getGroupID());
            return m4760a;
        }
        return m4760a;
    }

    /* renamed from: a */
    public static List<DownloadTaskInfo> m4758a(List<MediaItem> list, AudioQuality audioQuality) {
        ArrayList arrayList = new ArrayList(list.size());
        for (MediaItem mediaItem : list) {
            DownloadTaskInfo m4761a = m4761a(mediaItem, audioQuality);
            if (m4761a != null) {
                m4761a.setGroupId(mediaItem.getGroupID());
                arrayList.add(m4761a);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m4762a(DownloadTaskInfo downloadTaskInfo) {
        if (f5630a || downloadTaskInfo == null) {
            String groupId = downloadTaskInfo.getGroupId();
            if (StringUtils.m8346a(groupId)) {
                return groupId;
            }
            if (StringUtils.m8344a(MediaStorage.GROUP_ID_FAV_LOCAL, groupId) || groupId.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX)) {
                return MediaStorage.GROUP_ID_FAV;
            }
            return groupId;
        }
        throw new AssertionError("taskInfo is null!");
    }
}
