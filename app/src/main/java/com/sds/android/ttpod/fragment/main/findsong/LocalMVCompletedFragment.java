package com.sds.android.ttpod.fragment.main.findsong;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.collection.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MVOnlineData;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment;
import com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.MVStatistic;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.StateView;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class LocalMVCompletedFragment extends MVListFragment {
    private static DownloadTaskListFragment.InterfaceC1436b mListener;
    private static SparseArrayCompat<String> mMvFilePath = new SparseArrayCompat<>();
    private static StateView mStateView;
    private View mFailedView;

    public LocalMVCompletedFragment(DownloadTaskListFragment.InterfaceC1436b interfaceC1436b) {
        super(null, null, new C1524a());
        mListener = interfaceC1436b;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected void setupListFooter() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, 0, 0);
        viewGroup.setLayoutParams(layoutParams);
        return layoutInflater.inflate(R.layout.fragment_local_mv_list, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected void initViews(View view) {
        mStateView = (StateView) view.findViewById(R.id.local_mv_state_view);
        this.mListView = (ListView) view.findViewById(R.id.list);
        this.mFailedView = onCreateFailedView(getLayoutInflater(null));
        mStateView.setFailedView(this.mFailedView);
        setSlidingCloseMode(0);
        mStateView.setState(StateView.EnumC2248b.LOADING);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7180c(false);
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_MV_THUMBNAIL, ReflectUtils.m8375a(getClass(), "updateMVThumbnail", String.class, Bitmap.class));
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.m8375a(getClass(), "updateTaskState", DownloadTaskInfo.class));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(getRootView(), ThemeElement.BACKGROUND_MASK);
        mStateView.onThemeLoaded();
        this.mListAdapter.notifyDataSetChanged();
    }

    public void updateMVThumbnail(String str, Bitmap bitmap) {
        ((C1524a) this.mListAdapter).m5637a(str, bitmap);
    }

    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_VIDEO && downloadTaskInfo.getState().intValue() == 4) {
            updateDownloadMvList();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (mMvFilePath != null) {
            mMvFilePath.clear();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected String onLoadTitleText() {
        return getString(R.string.my_mv);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updateDownloadMvList();
    }

    public void updateDownloadMvList() {
        ArrayList<MVOnlineData> readDownloadMvList = readDownloadMvList();
        this.mListAdapter.m7663a((List) readDownloadMvList);
        this.mListView.setAdapter(this.mListAdapter);
        ((C1524a) this.mListAdapter).m5546a((Activity) getActivity());
        if (mListener != null) {
            mListener.mo5633a(readDownloadMvList.size());
        }
        configNoDataView((IconTextView) this.mFailedView.findViewById(R.id.no_media_icon), (TextView) this.mFailedView.findViewById(R.id.textview_load_failed), (TextView) this.mFailedView.findViewById(R.id.no_data_action_view));
        if (this.mListAdapter.isEmpty()) {
            mStateView.setState(StateView.EnumC2248b.FAILED);
        } else {
            mStateView.setState(StateView.EnumC2248b.SUCCESS);
        }
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setText(R.string.icon_no_playing);
        textView.setText(R.string.no_mv_download);
        textView2.setVisibility(View.INVISIBLE);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MVStatistic.m5076a("mv_channel");
        MVStatistic.m5072c();
        playMv((MVOnlineData) view.getTag(R.id.view_bind_data));
    }

    public void deleteAllCompleted() {
        PopupsUtils.m6741a(getActivity(), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.LocalMVCompletedFragment.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MessageDialog messageDialog) {
                LocalMVCompletedFragment.this.clearList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearList() {
        if (this.mListAdapter != null) {
            List<MVOnlineData> m7662b = this.mListAdapter.m7662b();
            if (m7662b != null) {
                for (MVOnlineData mVOnlineData : m7662b) {
                    File downloadFile = getDownloadFile(mVOnlineData);
                    if (downloadFile != null) {
                        downloadFile.delete();
                    }
                }
                m7662b.clear();
                mStateView.setState(StateView.EnumC2248b.FAILED);
            }
            if (this.mListAdapter != null) {
                this.mListAdapter.notifyDataSetChanged();
            }
        }
    }

    private ArrayList<MVOnlineData> readDownloadMvList() {
        ArrayList<MVOnlineData> arrayList = new ArrayList<>();
        File file = new File(TTPodConfig.m5283y());
        final String[] stringArray = getResources().getStringArray(R.array.mv_suffix);
        File[] listFiles = file.listFiles(new FileFilter() { // from class: com.sds.android.ttpod.fragment.main.findsong.LocalMVCompletedFragment.2
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                String lowerCase = FileUtils.m8399m(file2.getPath()).toLowerCase();
                for (String str : stringArray) {
                    if (lowerCase.endsWith(str)) {
                        return true;
                    }
                }
                return false;
            }
        });
        if (listFiles != null) {
            for (File file2 : listFiles) {
                MVOnlineData parseMvDataFromFile = parseMvDataFromFile(FileUtils.m8401k(file2.getName()));
                if (parseMvDataFromFile != null) {
                    arrayList.add(parseMvDataFromFile);
                    mMvFilePath.put(parseMvDataFromFile.hashCode(), file2.getPath());
                }
            }
        }
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment
    protected void playMv(MVOnlineData mVOnlineData) {
        playLocalMv(mVOnlineData, getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void playLocalMv(MVOnlineData mVOnlineData, Activity activity) {
        File file;
        try {
            file = new File(mMvFilePath.get(mVOnlineData.hashCode()));
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        if (file != null && file.exists()) {
            VideoPlayManager.m5814a(activity, Uri.fromFile(file).toString(), mVOnlineData.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File getDownloadFile(MVOnlineData mVOnlineData) {
        String str = mMvFilePath.get(mVOnlineData.hashCode());
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.LocalMVCompletedFragment$a */
    /* loaded from: classes.dex */
    public static class C1524a extends MVListFragment.AbstractView$OnClickListenerC1592a {

        /* renamed from: a */
        private HashMap<String, Bitmap> f5163a;

        private C1524a() {
            this.f5163a = new HashMap<>();
        }

        /* renamed from: a */
        public void m5637a(String str, Bitmap bitmap) {
            this.f5163a.put(str, bitmap);
            notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.AbstractView$OnClickListenerC1592a, com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        public void mo5400a(View view, MVOnlineData mVOnlineData, int i) {
            if (this.f3158d != null) {
                MVOnlineData mVOnlineData2 = (MVOnlineData) this.f3158d.get(i);
                view.setTag(R.id.view_bind_data, mVOnlineData2);
                MVListFragment.AbstractView$OnClickListenerC1592a.C1594a c1594a = (MVListFragment.AbstractView$OnClickListenerC1592a.C1594a) view.getTag();
                c1594a.m5537c().setText(mVOnlineData2.getName());
                c1594a.m5535d().setText(mVOnlineData2.getSinger());
                c1594a.m5541a().setTag(R.id.view_bind_data, mVOnlineData2);
                c1594a.m5541a().setOnClickListener(this);
                ImageView m5533e = c1594a.m5533e();
                m5533e.setImageResource(R.drawable.img_mv_default_image);
                String str = (String) LocalMVCompletedFragment.mMvFilePath.get(mVOnlineData2.hashCode());
                if (str != null) {
                    if (this.f5163a.containsKey(str)) {
                        Bitmap bitmap = this.f5163a.get(str);
                        if (bitmap != null) {
                            m5533e.setImageDrawable(new BitmapDrawable(bitmap));
                        }
                    } else {
                        CommandCenter.m4607a().m4596b(new Command(CommandID.GET_MV_THUMBNAIL, str));
                    }
                }
                ThemeManager.m3269a(c1594a.m5537c(), ThemeElement.SONG_LIST_ITEM_TEXT);
                ThemeManager.m3269a(c1594a.m5535d(), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                ThemeManager.m3269a(c1594a.m5539b(), ThemeElement.SONG_LIST_ITEM_MENU_IMAGE);
                ThemeUtils.m8175a(c1594a.m5539b(), (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            }
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.AbstractView$OnClickListenerC1592a
        /* renamed from: a */
        protected ActionItem[] mo5544a(MVOnlineData mVOnlineData) {
            return new ActionItem[]{new ActionItem(100, 0, (int) R.string.watch_mv), new ActionItem((int) ApShareReceiveFragment.WHAT_GET_SHARED_LIST_COMPLETE, 0, (int) R.string.delete_mv)};
        }

        @Override // com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.AbstractView$OnClickListenerC1592a
        /* renamed from: a */
        protected void mo5542a(ActionItem actionItem, int i, MVOnlineData mVOnlineData) {
            switch (actionItem.m7005e()) {
                case 100:
                    m5636b(mVOnlineData);
                    return;
                case ApShareReceiveFragment.WHAT_GET_SHARED_LIST_COMPLETE /* 101 */:
                    m5635c(mVOnlineData);
                    return;
                default:
                    throw new UnsupportedOperationException("this id not defined in the dialog");
            }
        }

        /* renamed from: b */
        protected void m5636b(MVOnlineData mVOnlineData) {
            LocalMVCompletedFragment.playLocalMv(mVOnlineData, this.f5263e);
        }

        /* renamed from: c */
        protected void m5635c(final MVOnlineData mVOnlineData) {
            final File downloadFile = LocalMVCompletedFragment.getDownloadFile(mVOnlineData);
            if (downloadFile != null) {
                new MessageDialog(this.f5263e, this.f5263e.getString(R.string.remove_confirm, new Object[]{FileUtils.m8402j(downloadFile.getPath())}), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.findsong.LocalMVCompletedFragment.a.1
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(MessageDialog messageDialog) {
                        downloadFile.delete();
                        C1524a.this.f3158d.remove(mVOnlineData);
                        C1524a.this.notifyDataSetChanged();
                        int count = C1524a.this.getCount();
                        if (count == 0) {
                            LocalMVCompletedFragment.mStateView.setState(StateView.EnumC2248b.FAILED);
                        }
                        if (LocalMVCompletedFragment.mListener != null) {
                            LocalMVCompletedFragment.mListener.mo5633a(count);
                        }
                    }
                }, (BaseDialog.InterfaceC1064a<MessageDialog>) null).show();
            }
        }
    }
}
