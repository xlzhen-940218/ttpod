package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.ttpod.R;


import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.File;
import java.util.List;

/* renamed from: com.sds.android.ttpod.fragment.main.findsong.a */
/* loaded from: classes.dex */
public class MvManager {

    /* renamed from: a */
    private static boolean f5246a;

    /* renamed from: a */
    public static void m5561a(final Context context, final MvPopupDialogCallBack mvPopupDialogCallBack, final int i) {
        if (f5246a) {
            m5554d(context, mvPopupDialogCallBack, i);
            return;
        }
        int i2 = i == 0 ? R.string.play_mv_warning : R.string.download_mv_warning;
        int i3 = i == 0 ? R.string.continue_play : R.string.continue_download;
        MessageDialog messageDialog = new MessageDialog(context, i2, new BaseDialog.OnClickListener<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void onClick(MessageDialog messageDialog2) {
                boolean unused = MvManager.f5246a = true;
                messageDialog2.dismiss();
                MvManager.m5554d(context, mvPopupDialogCallBack, i);
            }
        }, (BaseDialog.OnClickListener<MessageDialog>) null);
        messageDialog.m7255b(i3);
        messageDialog.setTitle(R.string.prompt_title);
        messageDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m5554d(Context context, MvPopupDialogCallBack mvPopupDialogCallBack, int i) {
        if (1 == i && UnicomFlowUtil.m3946f()) {
            m5562a(context, mvPopupDialogCallBack);
        } else {
            mvPopupDialogCallBack.onSuccess();
        }
    }

    /* renamed from: a */
    private static void m5562a(final Context context, final MvPopupDialogCallBack mvPopupDialogCallBack) {
        if (!Preferences.m2916bm()) {
            mvPopupDialogCallBack.onSuccess();
        }
    }

    /* renamed from: e */
    private static void m5553e(Context context, final MvPopupDialogCallBack mvPopupDialogCallBack, final int i) {
        if (i == 2 ? !Preferences.m2918bk() : !Preferences.m2919bj()) {
            mvPopupDialogCallBack.onSuccess();
            return;
        }
    }

    /* renamed from: b */
    public static void showMv(Context context, MvPopupDialogCallBack mvPopupDialogCallBack, int i) {
        m5560a(context, mvPopupDialogCallBack, i, 2);
    }

    /* renamed from: a */
    public static void m5560a(Context context, MvPopupDialogCallBack mvPopupDialogCallBack, int i, int i2) {
        if (!EnvironmentUtils.DeviceConfig.isConnected()) {
            PopupsUtils.m6721a(context.getString(R.string.cannot_play_for_network_error));
        } else if (2 == EnvironmentUtils.DeviceConfig.hasNetwork()) {
            mvPopupDialogCallBack.onSuccess();
        } else if (UnicomFlowUtil.m3946f() && HttpRequest.isProxy()) {
            if (i == 0) {
                m5553e(context, mvPopupDialogCallBack, i2);
            } else {
                mvPopupDialogCallBack.onSuccess();
            }
        } else {
            m5561a(context, mvPopupDialogCallBack, i);
        }
    }

    /* renamed from: a */
    public static void m5559a(MediaItem mediaItem) {
        String m5556b = m5556b(mediaItem);
        DownloadTaskInfo m4760a = DownloadUtils.m4760a(m5556b, TTPodConfig.getMvPath() + File.separator + (mediaItem.getArtist() + " - " + mediaItem.getTitle()) + ("." + FileUtils.getSuffix(FileUtils.getFilename(m5556b))), 0L, mediaItem.getTitle(), DownloadTaskInfo.TYPE_VIDEO, true, "mv_channel");
        m4760a.setTag(m5556b);
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.ADD_DOWNLOAD_TASK, m4760a));
    }

    /* renamed from: b */
    public static String m5556b(MediaItem mediaItem) {
        OnlineMediaItem onlineMediaItem;
        List<OnlineMediaItem.Url> mVUrls;
        if (mediaItem == null || (onlineMediaItem = (OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class)) == null || (mVUrls = onlineMediaItem.getMVUrls()) == null || mVUrls.isEmpty()) {
            return "";
        }
        return mVUrls.get(0).getUrl();
    }
}
