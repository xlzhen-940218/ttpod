package com.sds.android.ttpod.component.p087d;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.sds.android.cloudapi.ttpod.data.Post;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.share.ShareAction;
import com.sds.android.ttpod.activities.share.ShareSelectActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.ListDialog;
import com.sds.android.ttpod.component.p087d.p088a.MediaInfoDialog;
import com.sds.android.ttpod.component.p087d.p088a.MediaInfoEditDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.component.p087d.p088a.MoreOptionalDialog;
import com.sds.android.ttpod.component.p087d.p088a.MultiChoiceListDialog;
import com.sds.android.ttpod.component.p087d.p088a.OptionalDialog;
import com.sds.android.ttpod.component.p087d.p088a.SingleChoiceListDialog;
import com.sds.android.ttpod.component.p087d.p088a.WaitingDialog;
import com.sds.android.ttpod.component.soundsearch.IThemeEditable;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.main.findsong.MvManager;
import com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack;
import com.sds.android.ttpod.fragment.main.list.FavoriteSubMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.IEditAble;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.BlueToothUtils;
import com.sds.android.ttpod.utils.FavoriteUtils;
import com.sds.android.ttpod.utils.GroupItemUtils;
import com.sds.android.ttpod.utils.RingtoneUtils;
import com.sds.android.ttpod.utils.ShareInfoConvertUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.share.ShareInfo;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.d */
/* loaded from: classes.dex */
public class PopupsUtils {

    /* renamed from: a */
    private static Toast f4017a;

    /* renamed from: b */
    private static WaitingDialog f4018b;

    /* renamed from: c */
    private static PopupWindow popupWindow;

    /* renamed from: d */
    private static Context f4020d;

    /* renamed from: e */
    private static final SparseIntArray f4021e = new SparseIntArray();

    /* renamed from: f */
    private static SparseArray<AudioQuality> f4022f = new SparseArray<>();

    /* renamed from: g */
    private static final int[] f4023g;

    static {
        f4021e.put("title_key".hashCode(), 7);
        f4021e.put("artist_key".hashCode(), 8);
        f4021e.put("album_key".hashCode(), 9);
        f4021e.put("genre_key".hashCode(), 13);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_ADD_TIME.hashCode(), 10);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_FILE_NAME.hashCode(), 11);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME.hashCode(), 14);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_TITLE_DESC.hashCode(), 7);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_ARTIST_DESC.hashCode(), 8);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_ALBUM_DESC.hashCode(), 9);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_GENRE_DESC.hashCode(), 13);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC.hashCode(), 10);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_FILE_NAME_DESC.hashCode(), 11);
        f4021e.put(MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC.hashCode(), 14);
        f4022f.put(0, AudioQuality.LOSSLESS);
        f4022f.put(1, AudioQuality.SUPER);
        f4022f.put(2, AudioQuality.STANDARD);
        f4022f.put(3, AudioQuality.COMPRESSED);
        f4023g = new int[]{10, 20, 30, 60, 90, 0};
    }

    /* renamed from: a */
    public static void m6749a(Context context) {
        f4017a = Toast.makeText(context, "", Toast.LENGTH_LONG);
        if (SDKVersionUtils.sdkThan11()) {
            f4020d = context;
        }
    }

    /* renamed from: a */
    public static void m6721a(String str) {
        if (SDKVersionUtils.sdkThan11()) {
            f4017a.cancel();
            f4017a = Toast.makeText(f4020d, "", Toast.LENGTH_LONG);
        }
        f4017a.setText(str);
        f4017a.show();
    }

    /* renamed from: a */
    public static void m6760a(int i) {
        m6721a(BaseApplication.getApplication().getString(i));
    }

    /* renamed from: a */
    public static synchronized void m6748a(Context context, int i) {
        synchronized (PopupsUtils.class) {
            if (context != null) {
                m6732a(context, context.getResources().getString(i), true);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m6734a(Context context, String str) {
        synchronized (PopupsUtils.class) {
            if (context != null) {
                m6731a(context, str, true, true);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m6745a(Context context, int i, boolean z) {
        synchronized (PopupsUtils.class) {
            if (context != null) {
                m6731a(context, context.getResources().getString(i), z, true);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m6732a(Context context, String str, boolean z) {
        synchronized (PopupsUtils.class) {
            if (context != null) {
                m6731a(context, str, z, true);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m6744a(Context context, int i, boolean z, boolean z2) {
        synchronized (PopupsUtils.class) {
            if (context != null) {
                m6731a(context, context.getString(i), z, z2);
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m6731a(Context context, String str, boolean z, boolean z2) {
        synchronized (PopupsUtils.class) {
            if (context != null) {
                if (f4018b != null && f4018b.isShowing()) {
                    f4018b.dismiss();
                    f4018b = null;
                }
                f4018b = new WaitingDialog(context);
                f4018b.setCanceledOnTouchOutside(z);
                f4018b.setCancelable(z2);
                f4018b.m6775a((CharSequence) str);
                f4018b.show();
            }
        }
    }

    /* renamed from: a */
    public static void m6761a() {
        if (f4018b != null && f4018b.isShowing()) {
            f4018b.dismiss();
            f4018b = null;
        }
    }

    /* renamed from: a */
    public static SingleChoiceListDialog m6735a(Context context, CharSequence charSequence, CheckableActionItem[] checkableActionItemArr, int i, final ActionItem.InterfaceC1135b interfaceC1135b, BaseDialog.InterfaceC1064a<SingleChoiceListDialog> interfaceC1064a) {
        if (context != null) {
            final SingleChoiceListDialog singleChoiceListDialog = new SingleChoiceListDialog(context, checkableActionItemArr, interfaceC1064a);
            singleChoiceListDialog.setTitle(charSequence);
            singleChoiceListDialog.m6778c(i);
            singleChoiceListDialog.show();
            singleChoiceListDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.1
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i2) {
                    if (interfaceC1135b != null) {
                        interfaceC1135b.mo5409a(actionItem, i2);
                    }
                    singleChoiceListDialog.dismiss();
                }
            });
            return singleChoiceListDialog;
        }
        return null;
    }

    /* renamed from: a */
    public static ListDialog m6728a(Context context, List<ActionItem> list, String str, ActionItem.InterfaceC1135b interfaceC1135b) {
        return m6727a(context, list, str, interfaceC1135b, (View) null);
    }

    /* renamed from: a */
    public static ListDialog m6725a(Context context, ActionItem[] actionItemArr, String str, ActionItem.InterfaceC1135b interfaceC1135b) {
        return m6728a(context, new ArrayList(Arrays.asList(actionItemArr)), str, interfaceC1135b);
    }

    /* renamed from: a */
    public static ListDialog m6727a(Context context, List<ActionItem> list, String str, final ActionItem.InterfaceC1135b interfaceC1135b, final View view) {
        if (context != null) {
            final ListDialog listDialog = new ListDialog(context, (ActionItem[]) list.toArray(new ActionItem[0]), R.string.cancel, null) { // from class: com.sds.android.ttpod.component.d.d.12
                @Override // com.sds.android.ttpod.component.p087d.p088a.ListDialog
                /* renamed from: b */
                protected View mo6702b() {
                    return view;
                }
            };
            listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.15
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    listDialog.dismiss();
                    if (interfaceC1135b != null) {
                        interfaceC1135b.mo5409a(actionItem, i);
                    }
                }
            });
            listDialog.setTitle(str);
            listDialog.show();
            return listDialog;
        }
        return null;
    }

    /* renamed from: a */
    public static ListDialog m6740a(final Context context, final MediaItem mediaItem) {
        if (context == null || mediaItem == null) {
            return null;
        }
        if (mediaItem.isOnline() && mediaItem.getLocalDataSource() == null) {
            m6760a((int) R.string.set_ringtone_online_toast);
            mediaItem.getLocalDataSource();
        }
        final ListDialog listDialog = new ListDialog(context, new ActionItem[]{new ActionItem(0, 0, (int) R.string.ringtone_phone), new ActionItem(1, 0, (int) R.string.ringtone_notice), new ActionItem(2, 0, (int) R.string.ringtone_all)}, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
        listDialog.setTitle(context.getString(R.string.set_ringtone_title));
        listDialog.m7254b(R.string.cancel, null);
        listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.16
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(final ActionItem actionItem, int i) {
                TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<Object, Integer>(null) { // from class: com.sds.android.ttpod.component.d.d.16.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                    /* renamed from: c */
                    public Integer inBackground(Object obj) {
                        int m8247a;
                        String localDataSource = mediaItem.getLocalDataSource();
                        if (localDataSource == null) {
                            return 2;
                        }
                        switch (actionItem.m7005e()) {
                            case 0:
                                //LocalStatistic.m5140aN();
                                m8247a = RingtoneUtils.m8247a(context, localDataSource, 1);
                                break;
                            case 1:
                                //LocalStatistic.m5139aO();
                                m8247a = RingtoneUtils.m8247a(context, localDataSource, 2);
                                break;
                            default:
                                //LocalStatistic.m5138aP();
                                m8247a = RingtoneUtils.m8247a(context, localDataSource, 1);
                                RingtoneUtils.m8247a(context, localDataSource, 2);
                                RingtoneUtils.m8247a(context, localDataSource, 4);
                                break;
                        }
                        return Integer.valueOf(m8247a);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                    /* renamed from: a */
                    public void postExecute(Integer num) {
                        switch (num.intValue()) {
                            case 1:
                                PopupsUtils.m6760a((int) R.string.set_ringtone_successful);
                                return;
                            case 2:
                                PopupsUtils.m6760a((int) R.string.set_ringtone_fail);
                                return;
                            case 3:
                                PopupsUtils.m6760a((int) R.string.set_ringtone_unsupport);
                                return;
                            default:
                                return;
                        }
                    }
                });
                listDialog.dismiss();
            }
        });
        listDialog.show();
        return listDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */


    /* renamed from: a */
    public static void m6739a(Context context, MediaItem mediaItem, DialogInterface.OnDismissListener onDismissListener) {
        if (context != null) {
            new MediaInfoDialog(context, mediaItem, onDismissListener).show();
        }
    }

    /* renamed from: b */
    public static void m6712b(Context context, MediaItem mediaItem, DialogInterface.OnDismissListener onDismissListener) {
        if (context != null) {
            new MediaInfoEditDialog(context, mediaItem, onDismissListener).show();
        }
    }

    /* renamed from: a */
    public static void m6733a(Context context, String str, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a) {
        MessageDialog messageDialog = new MessageDialog(context, context.getString(R.string.media_delete_single, str), (int) R.string.delete, interfaceC1064a, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.delete);
        messageDialog.show();
    }

    /* renamed from: a */
    public static void m6741a(Context context, BaseDialog.InterfaceC1064a<MessageDialog> interfaceC1064a) {
        MessageDialog messageDialog = new MessageDialog(context, context.getString(R.string.download_remove_all_confirm_hint), (int) R.string.delete, interfaceC1064a, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.delete_all_download);
        messageDialog.show();
    }

    /* renamed from: a */
    public static OptionalDialog m6747a(Context context, int i, CharSequence charSequence, CharSequence charSequence2, BaseDialog.InterfaceC1064a<OptionalDialog> interfaceC1064a) {
        return m6746a(context, i, charSequence, charSequence2, interfaceC1064a, (BaseDialog.InterfaceC1064a<OptionalDialog>) null);
    }

    /* renamed from: a */
    public static OptionalDialog m6746a(Context context, int i, CharSequence charSequence, final CharSequence charSequence2, BaseDialog.InterfaceC1064a<OptionalDialog> interfaceC1064a, BaseDialog.InterfaceC1064a<OptionalDialog> interfaceC1064a2) {
        if (charSequence2 == null) {
            return null;
        }
        OptionalDialog optionalDialog = new OptionalDialog(context, i, interfaceC1064a, interfaceC1064a2) { // from class: com.sds.android.ttpod.component.d.d.17
            @Override // com.sds.android.ttpod.component.p087d.p088a.OptionalDialog
            /* renamed from: b */
            protected View mo6699b(Context context2) {
                return PopupsUtils.m6711b(context2, charSequence2);
            }
        };
        optionalDialog.setTitle(charSequence);
        optionalDialog.show();
        return optionalDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static View m6711b(Context context, CharSequence charSequence) {
        TextView textView = (TextView) View.inflate(context, R.layout.dialog_body_message, null);
        textView.setText(charSequence);
        return textView;
    }

    /* renamed from: a */
    public static MoreOptionalDialog m6736a(Context context, CharSequence charSequence, final CharSequence charSequence2, int i, BaseDialog.InterfaceC1064a<MoreOptionalDialog> interfaceC1064a) {
        if (charSequence2 == null) {
            return null;
        }
        MoreOptionalDialog moreOptionalDialog = new MoreOptionalDialog(context, interfaceC1064a, null) { // from class: com.sds.android.ttpod.component.d.d.18
            @Override // com.sds.android.ttpod.component.p087d.p088a.MoreOptionalDialog
            /* renamed from: b */
            protected View mo6698b(Context context2) {
                return PopupsUtils.m6711b(context2, charSequence2);
            }
        };
        moreOptionalDialog.setTitle(charSequence);
        moreOptionalDialog.m7255b(i);
        moreOptionalDialog.show();
        return moreOptionalDialog;
    }

    /* renamed from: b */
    public static void m6710b(Context context, String str, final BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a) {
        if (context != null) {
            EditTextDialog.C1144a[] c1144aArr = new EditTextDialog.C1144a[1];
            if (StringUtils.isEmpty(str)) {
                str = GroupItemUtils.m8269a();
            }
            c1144aArr[0] = new EditTextDialog.C1144a(0, "", str, context.getString(R.string.input_playlist_name_hint));
            EditTextDialog editTextDialog = new EditTextDialog(context, c1144aArr, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.component.d.d.19
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog2) {
                    try {
                        if (GroupItemUtils.m8268a(editTextDialog2.m6902c(0).m6896d().toString())) {
                            editTextDialog2.m7242f(false);
                            PopupsUtils.m6760a((int) R.string.playlist_name_existed);
                        } else {
                            editTextDialog2.m7242f(true);
                           interfaceC1064a.mo2038a(editTextDialog2);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
            editTextDialog.setTitle(R.string.create_playlist);
            editTextDialog.show();
        }
    }

    /* renamed from: a */
    public static void m6724a(final Context context, String[] strArr, final boolean z) {
        MultiChoiceListDialog multiChoiceListDialog = new MultiChoiceListDialog(context, new CheckableActionItem[]{(CheckableActionItem) new CheckableActionItem(0, R.string.clean_cache_online, false).m7010a((CharSequence) strArr[0]), (CheckableActionItem) new CheckableActionItem(1, R.string.clean_cache_music, false).m7010a((CharSequence) strArr[1]), (CheckableActionItem) new CheckableActionItem(2, R.string.clean_cache_pic, false).m7010a((CharSequence) strArr[2]), (CheckableActionItem) new CheckableActionItem(3, R.string.clean_cache_lyric, false).m7010a((CharSequence) strArr[3])}, new BaseDialog.InterfaceC1064a<MultiChoiceListDialog>() { // from class: com.sds.android.ttpod.component.d.d.20
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MultiChoiceListDialog multiChoiceListDialog2) {
                List<CheckableActionItem> m6813e = multiChoiceListDialog2.m6813e();
                if (m6813e != null && !m6813e.isEmpty()) {
                    //SUserUtils.m4956a(SAction.ACTION_SETTING_CLEAR_CACHE_CLEAR, SPage.PAGE_NONE);
                    //LocalStatistic.m5089q();
                    PopupsUtils.m6744a(context, (int) R.string.cleaning_cache, false, false);
                    TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<List<CheckableActionItem>, Boolean>(m6813e) { // from class: com.sds.android.ttpod.component.d.d.20.1
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public Boolean inBackground(List<CheckableActionItem> list) {
                            boolean z2;
                            int size = list.size();
                            int i = 0;
                            boolean z3 = false;
                            while (i < size) {
                                CheckableActionItem checkableActionItem = list.get(i);
                                if (checkableActionItem.m7005e() == 0) {
                                    FileUtils.m8415b(new File(TTPodConfig.getCacheObjectPath()));
                                    z2 = true;
                                } else if (1 == checkableActionItem.m7005e()) {
                                    MediaItem mediaItem = Cache.getInstance().getCurrentPlayMediaItem();
                                    FileUtils.m8417a(TTPodConfig.getCacheMediaPath(), 0L, mediaItem != null ? new String[]{TTPodConfig.getAudioTmp(), TTPodConfig.getSongIdPath(mediaItem.getSongID())} : null);
                                    z2 = true;
                                } else if (2 == checkableActionItem.m7005e()) {
                                    FileUtils.m8415b(new File(TTPodConfig.getArtPath()));
                                    FileUtils.m8415b(new File(TTPodConfig.getArtistPath()));
                                    z2 = true;
                                } else if (3 == checkableActionItem.m7005e()) {
                                    FileUtils.m8415b(new File(TTPodConfig.getLyricPath()));
                                    z2 = true;
                                } else {
                                    z2 = z3;
                                }
                                i++;
                                z3 = z2;
                            }
                            return Boolean.valueOf(z3);
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                        /* renamed from: a */
                        public void postExecute(Boolean bool) {
                            if (context != null && z) {
                                PopupsUtils.m6761a();
                            }
                            if (bool.booleanValue()) {
                                PopupsUtils.m6760a((int) R.string.cache_has_been_cleaned);
                            }
                        }
                    });
                }
            }
        }, (BaseDialog.InterfaceC1064a<MultiChoiceListDialog>) null);
        multiChoiceListDialog.m7255b(R.string.setting_dialog_button_clean);
        multiChoiceListDialog.setTitle(context.getString(R.string.clean_cache_title));
        multiChoiceListDialog.show();
    }

    /* renamed from: a */
    public static void shareMediaItem(Activity activity, MediaItem mediaItem) {
        if (activity != null) {
            m6752a(activity, (Serializable) ShareInfoConvertUtils.m8236a(mediaItem, ""));
        }
    }

    /* renamed from: a */
    public static void m6753a(Activity activity, ShareInfo shareInfo) {
        if (activity != null && shareInfo != null) {
            m6752a(activity, (Serializable) shareInfo);
        }
    }

    /* renamed from: a */
    private static void m6752a(Activity activity, Serializable serializable) {
        m6751a(activity, serializable, "");
    }

    /* renamed from: a */
    private static void m6751a(Activity activity, Serializable serializable, String str) {
        if (activity != null && !activity.isFinishing()) {
            Intent intent = new Intent(activity, ShareSelectActivity.class);
            intent.putExtra(ShareSelectActivity.KEY_EXTRA_DATA, serializable);
            ShareSelectActivity.setShareAction(new ShareAction(activity, str));
            activity.startActivity(intent);
        }
    }

    /* renamed from: a */
    public static void m6754a(Activity activity, MediaItem mediaItem, Bitmap bitmap) {
        if (activity != null) {
            m6752a(activity, (Serializable) ShareInfoConvertUtils.m8237a(mediaItem, bitmap));
        }
    }

    /* renamed from: a */
    public static void m6759a(Activity activity, Post post) {
        m6752a(activity, (Serializable) post);
    }

    /* renamed from: a */
    public static void m6758a(Activity activity, Post post, String str) {
        m6751a(activity, (Serializable) post, str);
    }

    /* renamed from: b */
    public static void m6713b(final Context context, final BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a) {
        if (context != null) {
            EditTextDialog editTextDialog = new EditTextDialog(context, new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, "", "" + Preferences.m2846p(), context.getString(R.string.input_wait_sleep_time), 2, 17, 4)}, R.string.start, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.component.d.d.21
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog2) {
                    try {
                        String obj = editTextDialog2.m6902c(1).m6896d().toString();
                        Integer valueOf = Integer.valueOf(StringUtils.isEmpty(obj) ? 0 : Integer.parseInt(obj));
                        editTextDialog2.m7242f(true);
                        if (valueOf.intValue() <= 0) {
                            PopupsUtils.m6721a(context.getString(R.string.input_invalid_args));
                            editTextDialog2.m7242f(false);
                        } else if (ErrCode.ErrNone != ((ErrCode) CommandCenter.getInstance().m4602a(new Command(CommandID.START_SLEEP_MODE, valueOf), ErrCode.class))) {
                            PopupsUtils.m6721a(context.getString(R.string.input_invalid_args));
                        } else {
                            //LocalStatistic.m5155a(valueOf.intValue());
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_SLEEP.getValue(), SPage.PAGE_GLOBAL_MENU.getValue(), SPage.PAGE_GLOBAL_MENU_DIALOG.getValue()).append("sleep_time", valueOf).post();
                            PopupsUtils.m6721a(context.getString(R.string.sleep_after_num_minute, valueOf));
                            if (interfaceC1064a != null) {
                                interfaceC1064a.mo2038a(editTextDialog2);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
            editTextDialog.setTitle(context.getString(R.string.input_wait_sleep_time));
            editTextDialog.show();
        }
    }

    /* renamed from: a */
    public static void m6755a(final Activity activity, final MediaItem mediaItem, final DialogInterface.OnDismissListener onDismissListener, final ActionItem.InterfaceC1135b interfaceC1135b, final ActionItem.InterfaceC1135b interfaceC1135b2) {
        if (activity != null) {
            if (mediaItem == null) {
                throw new IllegalArgumentException("mediaItem should not be null");
            }
            final ListDialog listDialog = new ListDialog(activity, new ActionItem[]{new ActionItem(0, (int) R.drawable.img_contextmenu_remove, (int) R.string.delete), new ActionItem(1, (int) R.drawable.img_contextmenu_add, (int) R.string.add), new ActionItem(2, (int) R.drawable.img_contextmenu_share, (int) R.string.share), new ActionItem(3, (int) R.drawable.img_contextmenu_send, (int) R.string.send), new ActionItem(4, (int) R.drawable.img_contextmenu_mediainfo, (int) R.string.media_info)}, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
            ActionItem.InterfaceC1135b interfaceC1135b3 = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.2
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    listDialog.dismiss();
                    switch (actionItem.m7005e()) {
                        case 0:
                            if (interfaceC1135b != null) {
                                //LocalStatistic.m5145aI();
                                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_DELETE.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_DIALOG_DELETE.getValue()).post();
                                interfaceC1135b.mo5409a(actionItem, i);
                                return;
                            }
                            return;
                        case 1:
                            if (interfaceC1135b2 != null) {
                                //LocalStatistic.m5144aJ();
                                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_ADD.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_DIALOG_ADD_SONG.getValue()).post();
                                interfaceC1135b2.mo5409a(actionItem, i);
                                return;
                            }
                            return;
                        case 2:
                            //LocalStatistic.m5146aH();
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_SHARE.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_DIALOG_SHARE.getValue()).post();
                            PopupsUtils.shareMediaItem(activity, mediaItem);
                            return;
                        case 3:
                            //LocalStatistic.m5142aL();
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_SEND.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_NONE.getValue()).post();
                            BlueToothUtils.m8308a(activity, new File[]{new File(mediaItem.getLocalDataSource())});
                            return;
                        case 4:
                            //LocalStatistic.m5137aQ();
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_SONG_INFO.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_DIALOG_MORE_MUSIC_INFO.getValue()).post();
                            PopupsUtils.m6739a(activity, mediaItem, onDismissListener);
                            return;
                        default:
                            return;
                    }
                }
            };
            listDialog.setTitle(R.string.more);
            listDialog.m6844a(interfaceC1135b3);
            listDialog.m7254b(R.string.cancel, new BaseDialog.InterfaceC1064a<ListDialog>() { // from class: com.sds.android.ttpod.component.d.d.3
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(ListDialog listDialog2) {
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_CANCEL.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_PORTRAIT_PLAYER.getValue()).post();
                    listDialog2.dismiss();
                }
            });
            //LocalStatistic.m5147aG();
            listDialog.show();
        }
    }

    /* renamed from: b */
    public static void m6715b(final Activity activity, final MediaItem mediaItem) {
        if (activity != null) {
            if (mediaItem == null) {
                throw new IllegalArgumentException("mediaItem should not be null");
            }
            final ListDialog listDialog = new ListDialog(activity, new ActionItem[]{new ActionItem(0, (int) R.drawable.img_contextmenu_share, (int) R.string.share), new ActionItem(1, (int) R.drawable.img_contextmenu_mv, (int) R.string.media_item_menu_mv)}, (int) R.string.cancel, new BaseDialog.InterfaceC1064a<ListDialog>() { // from class: com.sds.android.ttpod.component.d.d.4
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(ListDialog listDialog2) {
                    listDialog2.dismiss();
                }
            });
            ActionItem.InterfaceC1135b interfaceC1135b = new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.5
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    listDialog.dismiss();
                    switch (actionItem.m7005e()) {
                        case 0:
                            //LocalStatistic.m5146aH();
                            PopupsUtils.shareMediaItem(activity, mediaItem);
                            return;
                        case 1:
                            MvManager.showMv(activity, new MvPopupDialogCallBack() { // from class: com.sds.android.ttpod.component.d.d.5.1
                                @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                                /* renamed from: b */
                                public void mo1218b() {
                                    MvManager.m5559a(mediaItem);
                                }

                                @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                                /* renamed from: a */
                                public void onSuccess() {
                                    VideoPlayManager.playVideo(activity, mediaItem);
                                }
                            }, 0);
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PORTRAIT_MENU_MORE_MV.getValue(), SPage.PAGE_PLAYER_MENU_MORE.getValue(), SPage.PAGE_PLAYER_PLAY_MV.getValue()).post();
                            return;
                        default:
                            return;
                    }
                }
            };
            listDialog.m7251b(true);
            listDialog.setTitle(R.string.more);
            //LocalStatistic.m5147aG();
            listDialog.m6844a(interfaceC1135b);
            listDialog.show();
        }
    }

    /* renamed from: b */
    public static void m6714b(final Context context) {
        int[] iArr;
        String string;
        ArrayList arrayList = new ArrayList(f4023g.length);
        for (int i : f4023g) {
            if (i > 0) {
                string = context.getString(R.string.timing_some_minute, Integer.valueOf(i));
            } else {
                string = context.getString(R.string.timing_custom);
            }
            arrayList.add(new ActionItem(0, 0, string).m7009a(Integer.valueOf(i)));
        }
        final ListDialog listDialog = new ListDialog(context, arrayList, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
        listDialog.setTitle(R.string.set_auto_sleep_time);
        listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.6
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i2) {
                listDialog.dismiss();
                int intValue = ((Number) actionItem.m7004f()).intValue();
                if (intValue <= 0) {
                    PopupsUtils.m6713b(context, (BaseDialog.InterfaceC1064a<EditTextDialog>) null);
                } else if (ErrCode.ErrNone != ((ErrCode) CommandCenter.getInstance().m4602a(new Command(CommandID.START_SLEEP_MODE, Integer.valueOf(intValue)), ErrCode.class))) {
                    PopupsUtils.m6721a(context.getString(R.string.input_invalid_args));
                } else {
                    //LocalStatistic.m5155a(intValue);
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_SLEEP.getValue(), SPage.PAGE_GLOBAL_MENU.getValue(), SPage.PAGE_GLOBAL_MENU_DIALOG.getValue()).append("sleep_time", Integer.valueOf(intValue)).post();
                    PopupsUtils.m6721a(context.getString(R.string.sleep_after_num_minute, Integer.valueOf(intValue)));
                }
            }
        });
        listDialog.m7254b(R.string.cancel, null);
        listDialog.show();
    }

    /* renamed from: c */
    public static void m6705c(Activity activity, MediaItem mediaItem) {
        if (activity != null && mediaItem != null) {
            ListDialog listDialog = new ListDialog(activity, new ActionItem[]{new ActionItem(0, (int) R.drawable.img_contextmenu_share, (int) R.string.share), new ActionItem(1, (int) R.drawable.img_contextmenu_send, (int) R.string.send), new ActionItem(2, (int) R.drawable.img_contextmenu_mediainfo, (int) R.string.media_info)}, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
            listDialog.setTitle(R.string.more);
            listDialog.m6844a(new C1197a(activity, mediaItem, listDialog, null));
            //LocalStatistic.m5147aG();
            listDialog.show();
        }
    }

    /* renamed from: d */
    private static ActionItem[] m6704d() {
        return new ActionItem[]{new ActionItem(0, (int) R.drawable.img_contextmenu_share, (int) R.string.share), new ActionItem(1, (int) R.drawable.img_contextmenu_send, (int) R.string.send), new ActionItem(2, (int) R.drawable.img_contextmenu_mediainfo, (int) R.string.media_info), new ActionItem(3, (int) R.drawable.img_favourite_selected, (int) R.string.favorite), new ActionItem(4, (int) R.drawable.img_contextmenu_remove, (int) R.string.delete), new ActionItem(5, (int) R.drawable.img_contextmenu_add, (int) R.string.add), new ActionItem(6, (int) R.drawable.img_set_ring, (int) R.string.ringtone)};
    }

    /* renamed from: a */
    private static ActionItem[] m6717a(boolean z) {
        ActionItem[] actionItemArr = new ActionItem[z ? 4 : 3];
        actionItemArr[0] = new ActionItem(3, (int) R.drawable.img_favourite_selected, (int) R.string.favorite);
        actionItemArr[1] = new ActionItem(7, (int) R.drawable.img_musiccircle_post_detail_header_download_normal, (int) R.string.download_song);
        actionItemArr[2] = new ActionItem(0, (int) R.drawable.img_contextmenu_share, (int) R.string.share);
        if (z) {
            actionItemArr[3] = new ActionItem(8, (int) R.drawable.img_contextmenu_mv, (int) R.string.media_item_menu_mv);
        }
        return actionItemArr;
    }

    /* compiled from: PopupsUtils.java */
    /* renamed from: com.sds.android.ttpod.component.d.d$a */
    /* loaded from: classes.dex */
    private static class C1197a implements ActionItem.InterfaceC1135b {

        /* renamed from: a */
        private Activity activity;

        /* renamed from: b */
        private MediaItem mediaItem;

        /* renamed from: c */
        private ListDialog listDialog;

        /* renamed from: d */
        private Fragment fragment;

        C1197a(Activity activity, MediaItem mediaItem, ListDialog listDialog, Fragment fragment) {
            this.activity = activity;
            this.mediaItem = mediaItem;
            this.listDialog = listDialog;
            this.fragment = fragment;
        }

        @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
        /* renamed from: a */
        public void mo5409a(ActionItem actionItem, int i) {
            switch (actionItem.m7005e()) {
                case 0:
                    //LocalStatistic.m5146aH();
                    PopupsUtils.shareMediaItem(this.activity, this.mediaItem);

                    break;
                case 1:
                    //LocalStatistic.m5142aL();
                    BlueToothUtils.m8308a(this.activity, new File[]{new File(this.mediaItem.getLocalDataSource())});

                    break;
                case 2:
                    //LocalStatistic.m5137aQ();
                    PopupsUtils.m6739a(this.activity, this.mediaItem, (DialogInterface.OnDismissListener) null);

                    break;
                case 3:
                    this.mediaItem.setFav(false);
                    FavoriteUtils.m8282b(this.mediaItem, false);
                    break;
                case 4:
                    if (this.fragment != null && (this.fragment instanceof FavoriteSubMediaListFragment.FavoriteSongFragment)) {
                        ((FavoriteSubMediaListFragment.FavoriteSongFragment) this.fragment).onDeleteMediaItem(this.mediaItem);
                        break;
                    }
                    break;
                case 5:
                    PopupsUtils.m6729a(this.activity, Cache.getInstance().m3155k(), this.mediaItem, (ActionItem.InterfaceC1135b) null, (BaseDialog.InterfaceC1064a<EditTextDialog>) null);
                    break;
                case 6:
                    PopupsUtils.m6740a((Context) this.activity, this.mediaItem);
                    break;
                case 7:
                    new DownloadMenuHandler(this.activity).m6927a(this.mediaItem, "favorite-song");
                    break;
                case 8:
                    MvManager.showMv(this.activity, new MvPopupDialogCallBack() { // from class: com.sds.android.ttpod.component.d.d.a.1
                        @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                        /* renamed from: a */
                        public void onSuccess() {
                            VideoPlayManager.playVideo(C1197a.this.activity, C1197a.this.mediaItem);
                        }

                        @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                        /* renamed from: b */
                        public void mo1218b() {
                            MvManager.m5559a(C1197a.this.mediaItem);
                        }
                    }, 0);
                    break;
            }
            this.listDialog.dismiss();
        }
    }



    /* renamed from: a */
    public static void m6757a(Activity activity, FavoriteSubMediaListFragment.FavoriteSongFragment favoriteSongFragment, MediaItem mediaItem) {
        ActionItem[] m6704d;
        if (activity != null && mediaItem != null) {
            if (mediaItem.isOnline() && !FileUtils.m8419a(mediaItem.getLocalDataSource())) {
                m6704d = m6717a(mediaItem.containMV());
            } else {
                m6704d = m6704d();
            }
            ListDialog listDialog = new ListDialog(activity, m6704d, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
            listDialog.setTitle(R.string.more);
            listDialog.m6844a(new C1197a(activity, mediaItem, listDialog, favoriteSongFragment));
            //LocalStatistic.m5147aG();
            listDialog.show();
        }
    }

    /* renamed from: a */
    public static void m6729a(Context context, List<GroupItem> list, MediaItem mediaItem, ActionItem.InterfaceC1135b interfaceC1135b, BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a) {
        MediaItem queryMediaItem;
        ArrayList arrayList = new ArrayList(1);
        if (!mediaItem.isOnline()) {
            arrayList.add(mediaItem);
        } else if (mediaItem.getLocalDataSource() != null) {
            MediaItem m4712a = MediaItemUtils.m4712a(mediaItem.getLocalDataSource());
            if (m4712a != null && (queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, m4712a.getID())) != null) {
                arrayList.add(queryMediaItem);
            } else {
                return;
            }
        }
        m6726a(context, list, arrayList, interfaceC1135b, interfaceC1064a);
    }

    /* renamed from: a */
    public static void m6726a(final Context context, List<GroupItem> list, final Collection<MediaItem> collection, final ActionItem.InterfaceC1135b interfaceC1135b, final BaseDialog.InterfaceC1064a<EditTextDialog> interfaceC1064a) {
        if (context != null) {
            if (collection == null) {
                throw new IllegalArgumentException("mediaItems should not be null");
            }
            int size = (list == null || list.isEmpty()) ? 0 : list.size();
            ActionItem[] actionItemArr = new ActionItem[size];
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    GroupItem groupItem = list.get(i);
                    actionItemArr[i] = new ActionItem(groupItem.hashCode(), 0, groupItem.getName()).m7009a((Object) groupItem.getGroupID());
                }
            }
            final ListDialog listDialog = new ListDialog(context, actionItemArr, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
            listDialog.setTitle(R.string.dialog_add_to_group_title);
            listDialog.m7262a(R.string.create_playlist_add, new BaseDialog.InterfaceC1064a<ListDialog>() { // from class: com.sds.android.ttpod.component.d.d.7
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(ListDialog listDialog2) {
                    listDialog2.dismiss();
                    PopupsUtils.m6710b(context, (String) null, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.component.d.d.7.1
                        @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo2038a(EditTextDialog editTextDialog) {
                            String obj = editTextDialog.m6902c(0).m6896d().toString();
                            CommandCenter.getInstance().execute(new Command(CommandID.ADD_MEDIA_ITEM_LIST, (String) CommandCenter.getInstance().m4602a(new Command(CommandID.ADD_GROUP, obj), String.class), collection));
                            PopupsUtils.m6721a(context.getString(R.string.add_to_group_success, obj));
                            if (interfaceC1064a != null) {
                                interfaceC1064a.mo2038a(editTextDialog);
                            }
                        }
                    });
                }
            });
            listDialog.show();
            listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.8
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i2) {
                    String str = (String) actionItem.m7004f();
                    //LocalStatistic.m5143aK();
                    CommandCenter.getInstance().execute(new Command(CommandID.ADD_MEDIA_ITEM_LIST, str, collection));
                    PopupsUtils.m6721a(context.getString(R.string.add_to_group_success, actionItem.m7006d()));
                    if (interfaceC1135b != null) {
                        interfaceC1135b.mo5409a(actionItem, i2);
                    }
                    listDialog.dismiss();
                }
            });
        }
    }

    /* renamed from: a */
    public static void m6738a(Context context, final MediaItem mediaItem, final String str, final BaseDialog.InterfaceC1064a<MoreOptionalDialog> interfaceC1064a) {
        String string;
        if (context != null) {
            if (mediaItem == null || str == null) {
                throw new IllegalArgumentException("mediaItems or groupID should not be null");
            }
            if (TTTextUtils.isValidateMediaString(mediaItem.getArtist())) {
                string = context.getString(R.string.media_delete_single_with_artist, mediaItem.getArtist(), mediaItem.getTitle());
            } else {
                string = context.getString(R.string.media_delete_single, mediaItem.getTitle());
            }
            m6736a(context, context.getString(R.string.media_delete_title), string, (int) R.string.delete, new BaseDialog.InterfaceC1064a<MoreOptionalDialog>() { // from class: com.sds.android.ttpod.component.d.d.9
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MoreOptionalDialog moreOptionalDialog) {
                    if (str.equals(MediaStorage.GROUP_ID_FAV)) {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_FAVORITE_MEDIA_ITEM, mediaItem, Boolean.valueOf(moreOptionalDialog.m6821b())));
                    } else {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_MEDIA_ITEM, str, mediaItem, Boolean.valueOf(moreOptionalDialog.m6821b())));
                    }
                    if (interfaceC1064a != null) {
                        interfaceC1064a.mo2038a(moreOptionalDialog);
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(mediaItem);
                    if (moreOptionalDialog.m6817e()) {
                        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.DELETE_PRIVATE_EFFECT_LIST, arrayList));
                    }
                    if (moreOptionalDialog.m6819c()) {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_PICTURE, arrayList));
                    }
                    if (moreOptionalDialog.m6816f()) {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_LYRIC, arrayList));
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public static void m6730a(Context context, final Collection<MediaItem> collection, final String str, final BaseDialog.InterfaceC1064a<MoreOptionalDialog> interfaceC1064a) {
        String string;
        if (context != null) {
            DebugUtils.m8424a((Collection) collection, "mediaItems");
            DebugUtils.m8426a(str, "groupID");
            MediaItem next = collection.iterator().next();
            if (TTTextUtils.isValidateMediaString(next.getArtist())) {
                string = context.getString(R.string.media_delete_multi_with_artist, next.getArtist(), next.getTitle(), Integer.valueOf(collection.size()));
            } else {
                string = context.getString(R.string.media_delete_multi, next.getTitle(), Integer.valueOf(collection.size()));
            }
            m6736a(context, context.getString(R.string.media_delete_title), string, (int) R.string.delete, new BaseDialog.InterfaceC1064a<MoreOptionalDialog>() { // from class: com.sds.android.ttpod.component.d.d.10
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MoreOptionalDialog moreOptionalDialog) {
                    if (str.equals(MediaStorage.GROUP_ID_FAV)) {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_FAVORITE_MEDIA_ITEM_LIST, collection, Boolean.valueOf(moreOptionalDialog.m6821b())));
                    } else {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_MEDIA_ITEM_LIST, str, collection, Boolean.valueOf(moreOptionalDialog.m6821b())));
                    }
                    if (moreOptionalDialog.m6817e()) {
                        PopupsUtils.m6707b(collection);
                    }
                    if (interfaceC1064a != null) {
                        interfaceC1064a.mo2038a(moreOptionalDialog);
                    }
                    if (moreOptionalDialog.m6819c()) {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_PICTURE, collection));
                    }
                    if (moreOptionalDialog.m6816f()) {
                        CommandCenter.getInstance().execute(new Command(CommandID.DELETE_LYRIC, collection));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m6707b(Collection<MediaItem> collection) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.DELETE_PRIVATE_EFFECT_LIST, arrayList));
    }

    /* renamed from: a */
    public static void m6750a(Activity activity, final String str, final ActionItem.InterfaceC1135b interfaceC1135b) {
        if (activity != null) {
            m6735a(activity, activity.getString(R.string.media_order_title), new CheckableActionItem[]{new CheckableActionItem(7, R.string.order_as_title), new CheckableActionItem(8, R.string.order_as_artist), new CheckableActionItem(9, R.string.order_as_album), new CheckableActionItem(13, R.string.order_as_genre), new CheckableActionItem(11, R.string.order_as_file_name), new CheckableActionItem(MediaStorage.GROUP_ID_EFFECT_LOCAL.equals(str) || str.startsWith(MediaStorage.GROUP_ID_EFFECT_ONLINE) || MediaStorage.GROUP_ID_FAV.equals(str) ? 14 : 10, R.string.order_as_add_time)}, f4021e.get(Preferences.m2860l(str).hashCode()), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.component.d.d.11
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    PopupsUtils.m6720a(str, actionItem.m7005e());
                    if (interfaceC1135b != null) {
                        interfaceC1135b.mo5409a(actionItem, i);
                    }
                }
            }, (BaseDialog.InterfaceC1064a<SingleChoiceListDialog>) null);
        }
    }

    /* renamed from: a */
    public static void m6720a(String str, int i) {
        Preferences.m2860l(str);
        String str2 = null;
        switch (i) {
            case 7:
                str2 = "title_key";
                break;
            case 8:
                str2 = "artist_key";
                break;
            case 9:
                str2 = "album_key";
                break;
            case 10:
                str2 = MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC;
                break;
            case 11:
                str2 = MediaStorage.MEDIA_ORDER_BY_FILE_NAME;
                break;
            case 13:
                str2 = "genre_key";
                break;
            case 14:
                str2 = MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC;
                break;
        }
        Preferences.m3010a(str, str2);
    }

    /* renamed from: a */
    public static void m6742a(Context context, View view, final IEditAble.EditRequestListener editRequestListener) {
        if (context != null && view != null) {
            DebugUtils.m8422b(popupWindow, "mEditPanel");
            DebugUtils.m8426a(editRequestListener, "editRequestListener");
            View listMediaEditFooterView = View.inflate(context, R.layout.list_media_edit_footer, null);
            listMediaEditFooterView.setClickable(true);
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.d.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    switch (view2.getId()) {
                        case R.id.btn_send /* 2131230855 */:
                            //SUserUtils.m4956a(SAction.ACTION_BATCH_OPERATE_SEND, SPage.PAGE_NONE);
                            editRequestListener.onSendToRequested();
                            return;
                        case R.id.btn_remove /* 2131231668 */:
                            //SUserUtils.m4956a(SAction.ACTION_BATCH_OPERATE_REMOVE, SPage.PAGE_NONE);
                            editRequestListener.onRemoveRequested();
                            return;
                        case R.id.btn_add /* 2131231669 */:
                            //SUserUtils.m4956a(SAction.ACTION_BATCH_OPERATE_ADD, SPage.PAGE_NONE);
                            editRequestListener.onAddToRequested();
                            return;
                        default:
                            return;
                    }
                }
            };
            listMediaEditFooterView.findViewById(R.id.btn_remove).setOnClickListener(onClickListener);
            listMediaEditFooterView.findViewById(R.id.btn_add).setOnClickListener(onClickListener);
            listMediaEditFooterView.findViewById(R.id.btn_send).setOnClickListener(onClickListener);
            popupWindow = new PopupWindow(listMediaEditFooterView, view.getWidth(), context.getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height), false);
            popupWindow.setAnimationStyle(R.style.DialogWindowAnim);
            popupWindow.update();
            popupWindow.showAtLocation(view, 80, 0, 0);
        }
    }

    /* renamed from: a */
    public static void m6743a(Context context, View view, final IThemeEditable.InterfaceC1333a interfaceC1333a) {
        if (context != null && view != null) {
            View listThemeEditFooterView = View.inflate(context, R.layout.list_theme_edit_footer, null);
            listThemeEditFooterView.setClickable(true);
            listThemeEditFooterView.findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.d.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    interfaceC1333a.onRemoveRequested();
                }
            });
            ThemeManager.m3269a(listThemeEditFooterView, ThemeElement.SUB_BAR_BACKGROUND);
            ThemeManager.m3269a(listThemeEditFooterView.findViewById(R.id.delete_textView), ThemeElement.SUB_BAR_TEXT);
            ThemeUtils.m8173a((IconTextView) listThemeEditFooterView.findViewById(R.id.delete_iconTextView), ThemeElement.SUB_BAR_TEXT);
            popupWindow = new PopupWindow(listThemeEditFooterView, view.getWidth(), context.getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height), false);
            popupWindow.update();
            popupWindow.showAtLocation(view, 80, 0, 0);
        }
    }

    /* renamed from: b */
    public static boolean m6716b() {
        return popupWindow != null && popupWindow.isShowing();
    }

    /* renamed from: c */
    public static void m6706c() {
        if (m6716b()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /* renamed from: a */
    public static void m6723a(PopupWindow popupWindow) {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
