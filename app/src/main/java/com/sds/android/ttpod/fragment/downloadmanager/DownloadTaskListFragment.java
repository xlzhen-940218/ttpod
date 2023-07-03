package com.sds.android.ttpod.fragment.downloadmanager;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class DownloadTaskListFragment extends BaseFragment {
    protected int mDownloadType;
    private View mFailedView;
    private View mLeftActionView;
    private View mRightActionView;
    private View mRootView;
    private StateView mStateView;
    protected C1432a mTaskAdapter;
    private InterfaceC1436b mTaskCountChangeListener;
    protected ListView mTaskListView;
    protected View mTopActionPanel;
    protected List<DownloadTaskInfo> mTasks = new ArrayList();
    private int mIconResId = R.string.icon_male;
    private int mNoDataMessage = R.string.no_song_go_recommend;

    /* renamed from: com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1436b {
        /* renamed from: a */
        void mo5633a(int i);
    }

    public abstract void onDropDownMenuClicked(int i);

    protected abstract List<DownloadTaskInfo> readTaskList();

    public abstract void updateTaskState(DownloadTaskInfo downloadTaskInfo);

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDownloadType = getArguments().getInt(DownloadManagerFragment.DOWNLOAD_TYPE);
        if (this.mDownloadType == DownloadTaskInfo.TYPE_APP.intValue()) {
            setNoDataMessage(R.string.icon_no_playing, R.string.no_content);
        } else if (this.mDownloadType == DownloadTaskInfo.TYPE_VIDEO.intValue()) {
            setNoDataMessage(R.string.icon_no_playing, R.string.no_mv_download);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mRootView, "BackgroundMaskColor");
        ThemeManager.m3269a(this.mTaskListView, ThemeElement.COMMON_SEPARATOR);
        this.mStateView.onThemeLoaded();
        this.mTaskAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.loadMethod(getClass(), "updateTaskState", DownloadTaskInfo.class));
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.download_task_list_view, viewGroup, false);
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateView = (StateView) view.findViewById(R.id.media_task_loading_view);
        this.mFailedView = onCreateFailedView(getLayoutInflater(bundle));
        this.mStateView.setFailedView(this.mFailedView);
        this.mTaskListView = (ListView) view.findViewById(R.id.task_list);
        this.mTopActionPanel = getLayoutInflater(bundle).inflate(R.layout.download_list_header, (ViewGroup) this.mTaskListView, false);
        this.mTaskListView.addHeaderView(this.mTopActionPanel);
        this.mTaskAdapter = new C1432a(getLayoutInflater(bundle));
        updateStateViews();
        this.mTasks = readTaskList();
        updateListVisibility();
        notifyTaskListChanged();
        this.mTaskListView.setAdapter((ListAdapter) this.mTaskAdapter);
        this.mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                DownloadTaskInfo downloadTaskInfo = DownloadTaskListFragment.this.mTasks.get(ListViewUtils.m8266a(DownloadTaskListFragment.this.mTaskListView.getHeaderViewsCount(), i, DownloadTaskListFragment.this.mTasks.size()));
                switch (downloadTaskInfo.getState().intValue()) {
                    case 0:
                    case 1:
                    case 2:
                        //SUserUtils.m4956a(SAction.ACTION_DOWNING_SINGLE_PAUSE, SPage.PAGE_NONE);
                        CommandCenter.getInstance().execute(new Command(CommandID.CANCEL_DOWNLOAD_TASK, downloadTaskInfo));
                        return;
                    case 3:
                    case 5:
                        CommandCenter.getInstance().execute(new Command(CommandID.ADD_DOWNLOAD_TASK, downloadTaskInfo));
                        return;
                    case 4:
                    default:
                        return;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTopActionPanelVisible(int i) {
        this.mTopActionPanel.setVisibility(i);
    }

    protected void setLeftTopAction(int i, int i2, View.OnClickListener onClickListener) {
        this.mLeftActionView = this.mTopActionPanel.findViewById(R.id.left_action);
        this.mLeftActionView.setOnClickListener(onClickListener);
        ((TextView) this.mTopActionPanel.findViewById(R.id.left_action_name)).setText(i);
        ((ImageView) this.mTopActionPanel.findViewById(R.id.left_action_icon)).setImageResource(i2);
    }

    protected void setRightTopAction(int i, int i2, View.OnClickListener onClickListener) {
        this.mRightActionView = this.mTopActionPanel.findViewById(R.id.right_action);
        this.mRightActionView.setOnClickListener(onClickListener);
        ((TextView) this.mTopActionPanel.findViewById(R.id.right_action_name)).setText(i);
        ((ImageView) this.mTopActionPanel.findViewById(R.id.right_action_icon)).setImageResource(i2);
    }

    public void setOnTaskCountChangeListener(InterfaceC1436b interfaceC1436b) {
        this.mTaskCountChangeListener = interfaceC1436b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View getTopActionPanel() {
        return this.mTopActionPanel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addTask(DownloadTaskInfo downloadTaskInfo) {
        addTask(this.mTasks.size(), downloadTaskInfo);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addTask(int i, DownloadTaskInfo downloadTaskInfo) {
        this.mTasks.add(i, downloadTaskInfo);
        updateListVisibility();
        notifyTaskListChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeTask(DownloadTaskInfo downloadTaskInfo) {
        this.mTasks.remove(downloadTaskInfo);
        updateListVisibility();
        notifyTaskListChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void clear() {
        this.mTasks.clear();
        updateListVisibility();
        notifyTaskListChanged();
    }

    protected void updateListVisibility() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyTaskListChanged() {
        if (this.mTaskCountChangeListener != null) {
            this.mTaskCountChangeListener.mo5633a(this.mTasks.size());
        }
        this.mTaskAdapter.m5708a(this.mTasks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStateViews() {
        if (needFailedState()) {
            if (this.mTasks == null) {
                this.mStateView.setState(StateView.EnumC2248b.LOADING);
                return;
            } else if (this.mTasks.size() == 0) {
                this.mStateView.setState(StateView.EnumC2248b.FAILED);
                configFailedView(this.mFailedView);
                return;
            } else if (this.mTasks.size() > 0) {
                this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                return;
            } else {
                return;
            }
        }
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
    }

    protected boolean needFailedState() {
        return true;
    }

    protected void configFailedView(View view) {
        configNoDataView((IconTextView) view.findViewById(R.id.no_media_icon), (TextView) view.findViewById(R.id.textview_load_failed), (TextView) view.findViewById(R.id.no_data_action_view));
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, (ViewGroup) null);
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setText(this.mIconResId);
        textView.setText(this.mNoDataMessage);
        textView2.setVisibility(View.INVISIBLE);
    }

    private void setNoDataMessage(int i, int i2) {
        this.mIconResId = i;
        this.mNoDataMessage = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment$a */
    /* loaded from: classes.dex */
    public class C1432a extends BaseAdapter {

        /* renamed from: c */
        private final LayoutInflater f5023c;

        /* renamed from: b */
        private SparseArray f5022b = new SparseArray();

        /* renamed from: d */
        private List<DownloadTaskInfo> f5024d = new ArrayList();

        public C1432a(LayoutInflater layoutInflater) {
            this.f5023c = layoutInflater;
            this.f5022b.append(3, DownloadTaskListFragment.this.getString(R.string.download_paused));
            this.f5022b.append(5, DownloadTaskListFragment.this.getString(R.string.download_failed));
            this.f5022b.append(0, DownloadTaskListFragment.this.getString(R.string.download_waiting));
        }

        /* renamed from: a */
        public void m5708a(List<DownloadTaskInfo> list) {
            DownloadTaskListFragment.this.updateStateViews();
            this.f5024d = list;
            DownloadTaskListFragment.this.mTaskAdapter.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f5024d.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.f5024d.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            C1435a c1435a;
            View view2;
            if (view == null) {
                view2 = this.f5023c.inflate(R.layout.download_list_item, viewGroup, false);
                C1435a c1435a2 = new C1435a((ViewGroup) view2);
                view2.setTag(c1435a2);
                c1435a = c1435a2;
            } else {
                c1435a = (C1435a) view.getTag();
                view2 = view;
            }
            m5714a(i, c1435a);
            return view2;
        }

        /* renamed from: a */
        private void m5714a(int i, C1435a c1435a) {
            final DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) getItem(i);
            m5712a(c1435a, downloadTaskInfo);
            if (!StringUtils.isEmpty(downloadTaskInfo.getAudioQuality())) {
                m5713a(c1435a.f5034g, downloadTaskInfo);
            } else {
                c1435a.f5034g.setVisibility(View.GONE);
            }
            c1435a.f5035h.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    //SUserUtils.m4956a(SAction.ACTION_DOWNING_SINGLE_CLICK_DELETE, SPage.PAGE_NONE);
                    PopupsUtils.m6733a(DownloadTaskListFragment.this.getActivity(), C1432a.this.m5710a(downloadTaskInfo), new BaseDialog.OnClickListener<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.a.1.1
                        @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void onClick(MessageDialog messageDialog) {
                            //SUserUtils.m4956a(SAction.ACTION_DOWNING_SINGLE_CLICK_DELETE_SURE, SPage.PAGE_NONE);
                            CommandCenter.getInstance().execute(new Command(CommandID.DELETE_DOWNLOAD_TASK, downloadTaskInfo, true));
                            DownloadTaskListFragment.this.removeTask(downloadTaskInfo);
                        }
                    });
                }
            });
            m5709a(downloadTaskInfo, c1435a);
            c1435a.m3250a(ThemeUtils.m8163b());
        }

        /* renamed from: a */
        private void m5712a(C1435a c1435a, DownloadTaskInfo downloadTaskInfo) {
            c1435a.f5030c.setText(m5710a(downloadTaskInfo));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public String m5710a(DownloadTaskInfo downloadTaskInfo) {
            String m8401k = FileUtils.m8401k(downloadTaskInfo.getSavePath());
            if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
                List<String> m8335c = StringUtils.stringToArray(m8401k, "-");
                return m8335c.size() > 1 ? m8335c.get(1).trim() : m8335c.get(0).trim();
            }
            return m8401k;
        }

        /* renamed from: a */
        private void m5709a(DownloadTaskInfo downloadTaskInfo, C1435a c1435a) {
            int i;
            int i2;
            Integer fileLength = downloadTaskInfo.getFileLength();
            int downloadLength = downloadTaskInfo.getDownloadLength();
            switch (downloadTaskInfo.getState().intValue()) {
                case 1:
                case 2:
                    int intValue = ((Integer) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_TASK_DOWNLOADED_LENGTH, downloadTaskInfo), Integer.class)).intValue();
                    downloadTaskInfo.setDownloadLength(intValue);
                    i = 0;
                    i2 = intValue;
                    break;
                default:
                    i = 8;
                    i2 = downloadLength;
                    break;
            }
            if (i == 0) {
                c1435a.f5031d.setProgress(downloadTaskInfo.getDownloadProgress().intValue());
            }
            c1435a.f5031d.setVisibility(i);
            c1435a.f5033f.setText((String) this.f5022b.get(downloadTaskInfo.getState().intValue()));
            c1435a.f5033f.setVisibility(8 == i ? View.VISIBLE : View.GONE);
            if (fileLength == null || fileLength.intValue() <= 0) {
                c1435a.f5032e.setVisibility(View.GONE);
                return;
            }
            c1435a.f5032e.setText(DownloadTaskListFragment.this.getString(R.string.download_size_ratio, String.format("%.1f", Float.valueOf((i2 >> 16) / 16.0f)), String.format("%.1f", Float.valueOf((fileLength.intValue() >> 16) / 16.0f))));
            c1435a.f5032e.setVisibility(View.VISIBLE);
        }

        /* renamed from: a */
        private void m5713a(IconTextView iconTextView, DownloadTaskInfo downloadTaskInfo) {
            AudioQuality valueOf = AudioQuality.valueOf(downloadTaskInfo.getAudioQuality());
            if (valueOf.ordinal() >= AudioQuality.SUPER.ordinal()) {
                iconTextView.setVisibility(View.VISIBLE);
                iconTextView.setText(valueOf == AudioQuality.LOSSLESS ? R.string.icon_text_wusun : R.string.icon_text_gaozhi);
                iconTextView.setTextColor(valueOf == AudioQuality.LOSSLESS ? -2185667 : -8665764);
                return;
            }
            iconTextView.setVisibility(View.GONE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment$a$a */
        /* loaded from: classes.dex */
        public final class C1435a extends ThemeManager.AbstractC2018a {

            /* renamed from: b */
            private View f5029b;

            /* renamed from: c */
            private TextView f5030c;

            /* renamed from: d */
            private ProgressBar f5031d;

            /* renamed from: e */
            private TextView f5032e;

            /* renamed from: f */
            private TextView f5033f;

            /* renamed from: g */
            private IconTextView f5034g;

            /* renamed from: h */
            private IconTextView f5035h;

            private C1435a(ViewGroup viewGroup) {
                this.f5029b = viewGroup;
                this.f5030c = (TextView) viewGroup.findViewById(R.id.textview_download_item_filename);
                this.f5031d = (ProgressBar) viewGroup.findViewById(R.id.progressbar_download_item);
                this.f5032e = (TextView) viewGroup.findViewById(R.id.textivew_download_item_progress);
                this.f5033f = (TextView) viewGroup.findViewById(R.id.textview_download_item_hint_info);
                this.f5034g = (IconTextView) viewGroup.findViewById(R.id.flag_quality_view);
                this.f5035h = (IconTextView) viewGroup.findViewById(R.id.download_delete);
            }

            @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.AbstractC2018a
            /* renamed from: a */
            protected void mo3251a() {
                ThemeManager.m3269a(this.f5029b, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
                ThemeManager.m3269a(this.f5031d, ThemeElement.COMMON_PROGRESS_BAR);
                ThemeManager.m3269a(this.f5030c, ThemeElement.SONG_LIST_ITEM_TEXT);
                ThemeManager.m3269a(this.f5032e, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                ThemeManager.m3269a(this.f5033f, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                ThemeUtils.m8173a(this.f5035h, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            }
        }
    }
}
