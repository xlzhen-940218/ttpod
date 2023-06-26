package com.sds.android.ttpod.fragment.main.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.AZSideBar;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class BaseGroupListFragment extends BaseFragment {
    private AZSideBar mAZSideBar;
    private View mFailedView;
    private View mFooterView;
    protected ActionExpandableListView mGroupHeaderListView;
    private List<GroupItem> mGroupItemList;
    private C1630a mGroupListAdapter;
    private String mPlayingGroupID;
    private StateView mStateView;

    protected abstract void configFailedView(View view);

    protected abstract void configListFooterView(View view);

    protected abstract boolean isLocalGroup();

    protected abstract View onCreateFailedView(LayoutInflater layoutInflater);

    protected abstract View onCreateListFooterView(LayoutInflater layoutInflater);

    protected abstract void onGroupItemClicked(GroupItem groupItem);

    protected abstract void onReloadData();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mStateView = (StateView) layoutInflater.inflate(R.layout.fragment_draggable_group_list, viewGroup, false);
        this.mGroupHeaderListView = (ActionExpandableListView) this.mStateView.findViewById(R.id.list_group);
        this.mFooterView = onCreateListFooterView(layoutInflater);
        if (this.mFooterView != null) {
            this.mGroupHeaderListView.addFooterView(this.mFooterView);
        }
        this.mAZSideBar = (AZSideBar) this.mStateView.findViewById(R.id.azsidebar);
        this.mFailedView = onCreateFailedView(layoutInflater);
        this.mStateView.setFailedView(this.mFailedView);
        return this.mStateView;
    }

    protected boolean isAZSideBarEnable() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.PLAY_GROUP_CHANGED, ReflectUtils.m8375a(getClass(), "playGroupChanged", new Class[0]));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mGroupListAdapter = new C1630a();
        this.mPlayingGroupID = Preferences.getLocalGroupId();
        this.mGroupHeaderListView.mo1261a(this.mGroupListAdapter, 0, 0);
        this.mGroupHeaderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                int m8266a = ListViewUtils.m8266a(BaseGroupListFragment.this.mGroupHeaderListView.getHeaderViewsCount(), i, BaseGroupListFragment.this.mGroupListAdapter.getCount());
                if (m8266a > -1) {
                    BaseGroupListFragment.this.onGroupItemClicked((GroupItem) BaseGroupListFragment.this.mGroupItemList.get(m8266a));
                }
            }
        });
        this.mGroupHeaderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment.2
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view2, int i, long j) {
                int m8266a = ListViewUtils.m8266a(BaseGroupListFragment.this.mGroupHeaderListView.getHeaderViewsCount(), i, BaseGroupListFragment.this.mGroupListAdapter.getCount());
                if (m8266a > -1) {
                    BaseGroupListFragment.this.onGroupItemLongClicked((GroupItem) BaseGroupListFragment.this.mGroupItemList.get(m8266a));
                    return true;
                }
                return true;
            }
        });
        this.mAZSideBar.setOnMatchedPositionChangeListener(new AZSideBar.InterfaceC2161a() { // from class: com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment.3
            @Override // com.sds.android.ttpod.widget.AZSideBar.InterfaceC2161a
            /* renamed from: a */
            public void mo1905a(int i, String str) {
                BaseGroupListFragment.this.selectRow(i);
            }
        });
        this.mGroupHeaderListView.setOnScrollListener(this.mAZSideBar);
        updateStateViews();
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        updateAZSideBar();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        this.mStateView.onThemeLoaded();
        this.mAZSideBar.onThemeLoaded();
        ThemeManager.m3269a(this.mGroupHeaderListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.mFooterView, ThemeElement.SUB_BAR_TEXT);
        ThemeManager.m3269a(this.mFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        notifyDataSetChanged();
    }

    protected void onGroupItemLongClicked(GroupItem groupItem) {
    }

    public void updateGroupList(List<GroupItem> list) {
        this.mGroupItemList = list;
        statisticGroupCount(list);
        notifyDataSetChanged();
        if (isViewAccessAble()) {
            updateStateViews();
            updateAZSideBar();
        }
    }

    private void statisticGroupCount(List<GroupItem> list) {
        int i;
        if (String.valueOf(SPage.PAGE_MY_SONGLIST.getValue()).equals(getPage())) {
            int size = list != null ? list.size() : 0;
            if (size > 0) {
                Iterator<GroupItem> it = list.iterator();
                i = 0;
                while (it.hasNext() && !it.next().getGroupID().startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX)) {
                    i++;
                }
            } else {
                i = 0;
            }

        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<GroupItem> getGroupItemList() {
        return this.mGroupItemList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeGroupItem(GroupItem groupItem) {
        if (this.mGroupItemList != null) {
            this.mGroupItemList.remove(groupItem);
            notifyDataSetChanged();
            configListFooterView(this.mFooterView);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ActionExpandableListView getListView() {
        return this.mGroupHeaderListView;
    }

    private void updateAZSideBar() {
        if (isViewAccessAble()) {
            boolean isAZSideBarEnable = isAZSideBarEnable();
            this.mAZSideBar.setVisibility(isAZSideBarEnable ? View.VISIBLE : View.GONE);
            this.mGroupHeaderListView.setVerticalScrollBarEnabled(isAZSideBarEnable ? false : true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateAZKeys(List<String> list) {
        DebugUtils.m8426a((Object) list, "rawAZKeys");
        if (isViewAccessAble()) {
            this.mAZSideBar.m1909a(list);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mGroupItemList == null) {
            onReloadData();
        }
    }

    public void selectRow(int i) {
        this.mGroupHeaderListView.requestFocus();
        this.mGroupHeaderListView.setSelection(i);
    }

    public boolean isEmpty() {
        return this.mGroupItemList == null || this.mGroupItemList.isEmpty();
    }

    private void updateStateViews() {
        if (isLocalGroup()) {
            if (isEmpty()) {
                this.mStateView.setState(StateView.EnumC2248b.FAILED);
                configFailedView(this.mFailedView);
                return;
            }
            this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
            configListFooterView(this.mFooterView);
        } else if (this.mGroupItemList == null) {
            this.mStateView.setState(StateView.EnumC2248b.LOADING);
        } else if (this.mGroupItemList.size() > 0) {
            this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
            configListFooterView(this.mFooterView);
        } else {
            this.mStateView.setState(StateView.EnumC2248b.FAILED);
            configFailedView(this.mFailedView);
        }
    }

    public void playGroupChanged() {
        this.mPlayingGroupID = Preferences.getLocalGroupId();
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyDataSetChanged() {
        if (this.mGroupListAdapter != null) {
            this.mGroupListAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View getGroupItemView(GroupItem groupItem, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.media_group_item, (ViewGroup) null);
            view.setTag(new C1631b(view));
        }
        C1631b c1631b = (C1631b) view.getTag();
        Context context = view.getContext();
        boolean startsWith = groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX);
        c1631b.m5463d().setVisibility(View.GONE);
        c1631b.m5465b().setText(startsWith ? FileUtils.getFilename(groupItem.getName()) : TTTextUtils.validateString(view.getContext(), groupItem.getName()));
        c1631b.m5464c().setText(context.getString(R.string.count_of_media, groupItem.getCount()) + (startsWith ? "  " + groupItem.getName() : ""));
        c1631b.m5466a().setVisibility(StringUtils.equals(groupItem.getGroupID(), this.mPlayingGroupID) ? View.VISIBLE : View.GONE);
        view.setEnabled(StringUtils.equals(groupItem.getGroupID(), this.mPlayingGroupID) ? false : true);
        ThemeManager.m3269a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(c1631b.m5465b(), ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(c1631b.m5464c(), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeManager.m3269a(c1631b.m5466a(), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        ThemeUtils.m8175a(c1631b.m5463d(), (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        ThemeUtils.m8175a(c1631b.m5462e(), (int) R.string.icon_drag_sort, ThemeElement.SONG_LIST_ITEM_TEXT);
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment$a */
    /* loaded from: classes.dex */
    public class C1630a extends BaseAdapter {
        private C1630a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (BaseGroupListFragment.this.mGroupItemList == null) {
                return 0;
            }
            return BaseGroupListFragment.this.mGroupItemList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return BaseGroupListFragment.this.getGroupItemView((GroupItem) BaseGroupListFragment.this.mGroupItemList.get(i), view, viewGroup);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment$b */
    /* loaded from: classes.dex */
    public static class C1631b {

        /* renamed from: a */
        private TextView f5354a;

        /* renamed from: b */
        private TextView f5355b;

        /* renamed from: c */
        private IconTextView f5356c;

        /* renamed from: d */
        private View f5357d;

        /* renamed from: e */
        private IconTextView f5358e;

        public C1631b(View view) {
            this.f5354a = (TextView) view.findViewById(R.id.title_view);
            this.f5355b = (TextView) view.findViewById(R.id.subtitle_view);
            this.f5356c = (IconTextView) view.findViewById(R.id.menu_view);
            this.f5357d = view.findViewById(R.id.view_playstate_group);
            this.f5358e = (IconTextView) view.findViewById(R.id.drag_handle);
        }

        /* renamed from: a */
        public View m5466a() {
            return this.f5357d;
        }

        /* renamed from: b */
        public TextView m5465b() {
            return this.f5354a;
        }

        /* renamed from: c */
        public TextView m5464c() {
            return this.f5355b;
        }

        /* renamed from: d */
        public IconTextView m5463d() {
            return this.f5356c;
        }

        /* renamed from: e */
        public IconTextView m5462e() {
            return this.f5358e;
        }
    }
}
