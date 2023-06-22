package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.content.Intent;
import android.widget.CompoundButton;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.unicomflow.OrderFlowDetailActivity;
import com.sds.android.ttpod.activities.unicomflow.UnicomDialog;
import com.sds.android.ttpod.activities.unicomflow.UnicomFlowManager;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
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
        MessageDialog messageDialog = new MessageDialog(context, i2, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog2) {
                boolean unused = MvManager.f5246a = true;
                messageDialog2.dismiss();
                MvManager.m5554d(context, mvPopupDialogCallBack, i);
            }
        }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
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
            mvPopupDialogCallBack.mo1219a();
        }
    }

    /* renamed from: a */
    private static void m5562a(final Context context, final MvPopupDialogCallBack mvPopupDialogCallBack) {
        if (!Preferences.m2916bm()) {
            mvPopupDialogCallBack.mo1219a();
            return;
        }
        String string = context.getString(R.string.mv_download_unicom_net_prompt);
        String string2 = context.getString(R.string.prompt_title);
        UnicomFlowStatistic.m4861C();
        SUserUtils.m4957a(1126, SPage.PAGE_NONE);
        UnicomFlowManager.m7757a(context, string, string2, (int) R.string.unicom_open_service, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog) {
                boolean m7768b = unicomDialog.m7768b();
                if (m7768b) {
                    UnicomFlowStatistic.m4858F();
                    SUserUtils.m4957a(1129, SPage.PAGE_NONE);
                }
                Preferences.m2971ae(!m7768b);
                UnicomFlowStatistic.m4859E();
                context.startActivity(new Intent(context, OrderFlowDetailActivity.class));
                SUserUtils.m4957a(1127, SPage.PAGE_NONE);
            }
        }, (int) R.string.mv_download, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.3
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog) {
                boolean m7768b = unicomDialog.m7768b();
                if (m7768b) {
                    UnicomFlowStatistic.m4858F();
                    SUserUtils.m4957a(1129, SPage.PAGE_NONE);
                }
                Preferences.m2971ae(!m7768b);
                UnicomFlowStatistic.m4860D();
                SUserUtils.m4957a(1128, SPage.PAGE_NONE);
                mvPopupDialogCallBack.mo1219a();
            }
        }, new CompoundButton.OnCheckedChangeListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
    }

    /* renamed from: e */
    private static void m5553e(Context context, final MvPopupDialogCallBack mvPopupDialogCallBack, final int i) {
        if (i == 2 ? !Preferences.m2918bk() : !Preferences.m2919bj()) {
            mvPopupDialogCallBack.mo1219a();
            return;
        }
        UnicomFlowStatistic.m4802y();
        SUserUtils.m4957a(1138, SPage.PAGE_NONE);
        String string = context.getString(R.string.unicom_mv_message);
        String string2 = context.getString(R.string.unicom_dialog_month_end_title);
        UnicomDialog unicomDialog = new UnicomDialog(context, string, (int) R.string.unicom_mv_dialog, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.5
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                UnicomFlowStatistic.m4863A();
                if (unicomDialog2.m7768b()) {
                    UnicomFlowStatistic.m4801z();
                    SUserUtils.m4957a(1142, SPage.PAGE_NONE);
                }
                SUserUtils.m4957a(1139, SPage.PAGE_NONE);
                mvPopupDialogCallBack.mo1219a();
                unicomDialog2.dismiss();
            }
        }, (int) R.string.mv_download, new BaseDialog.InterfaceC1064a<UnicomDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.6
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(UnicomDialog unicomDialog2) {
                mvPopupDialogCallBack.mo1218b();
                if (unicomDialog2.m7768b()) {
                    UnicomFlowStatistic.m4801z();
                    SUserUtils.m4957a(1142, SPage.PAGE_NONE);
                }
                SUserUtils.m4957a(1140, SPage.PAGE_NONE);
                UnicomFlowStatistic.m4862B();
                unicomDialog2.dismiss();
            }
        }, true, new CompoundButton.OnCheckedChangeListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.a.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (i == 2) {
                    Preferences.m2973ad(z ? false : true);
                } else {
                    Preferences.m2975ac(z ? false : true);
                }
            }
        });
        unicomDialog.setTitle(string2);
        unicomDialog.m7246d(true);
        unicomDialog.setCanceledOnTouchOutside(false);
        unicomDialog.show();
    }

    /* renamed from: b */
    public static void m5557b(Context context, MvPopupDialogCallBack mvPopupDialogCallBack, int i) {
        m5560a(context, mvPopupDialogCallBack, i, 2);
    }

    /* renamed from: a */
    public static void m5560a(Context context, MvPopupDialogCallBack mvPopupDialogCallBack, int i, int i2) {
        if (!EnvironmentUtils.C0604c.m8474e()) {
            PopupsUtils.m6721a(context.getString(R.string.cannot_play_for_network_error));
        } else if (2 == EnvironmentUtils.C0604c.m8476d()) {
            mvPopupDialogCallBack.mo1219a();
        } else if (UnicomFlowUtil.m3946f() && HttpRequest.m8704b()) {
            if (i == 0) {
                m5553e(context, mvPopupDialogCallBack, i2);
            } else {
                mvPopupDialogCallBack.mo1219a();
            }
        } else {
            m5561a(context, mvPopupDialogCallBack, i);
        }
    }

    /* renamed from: a */
    public static void m5559a(MediaItem mediaItem) {
        String m5556b = m5556b(mediaItem);
        DownloadTaskInfo m4760a = DownloadUtils.m4760a(m5556b, TTPodConfig.m5283y() + File.separator + (mediaItem.getArtist() + " - " + mediaItem.getTitle()) + ("." + FileUtils.m8399m(FileUtils.m8402j(m5556b))), 0L, mediaItem.getTitle(), DownloadTaskInfo.TYPE_VIDEO, true, "mv_channel");
        m4760a.setTag(m5556b);
        CommandCenter.m4607a().m4596b(new Command(CommandID.ADD_DOWNLOAD_TASK, m4760a));
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
