package com.sds.android.ttpod.fragment.main.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.local.MediaGroupSearchActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GroupListFragment extends BaseGroupListFragment implements IOrderAble, ISearchAble {
    private static final SparseArray<C1641a> CLICK_ACTION_MAP = new SparseArray<>();
    private static final long DELAY_LAUNCH = 200;
    public static final String KEY_GROUP_TYPE = "group_type";
    private static final SparseArray<SAction> ORDERID_ACTION_MAP;
    private static final String TAG = "GroupListFragment";
    protected GroupType mGroupType;

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        DebugUtils.m8426a(arguments, "bundle");
        String string = arguments.getString(KEY_GROUP_TYPE);
        if (StringUtils.isEmpty(string)) {
            throw new IllegalArgumentException("groupType must not be empty");
        }
        this.mGroupType = GroupType.valueOf(string);
        LogUtils.debug(TAG, "onCreate lookStatisticId grouptype=%s", this.mGroupType.name());
        if (this.mGroupType != GroupType.CUSTOM_LOCAL && this.mGroupType != GroupType.CUSTOM_ALL && this.mGroupType != GroupType.DEFAULT_ALBUM && this.mGroupType != GroupType.DEFAULT_ARTIST && this.mGroupType != GroupType.DEFAULT_FOLDER && this.mGroupType != GroupType.DEFAULT_GENRE) {
            throw new IllegalArgumentException("GroupType must be GroupType.CUSTOM_LOCAL, GroupType.DEFAULT_ARTIST, GroupType.DEFAULT_ALBUM, GroupType.DEFAULT_FOLDER or GroupType.DEFAULT_GENRE");
        }
        C1641a c1641a = CLICK_ACTION_MAP.get(this.mGroupType.ordinal());
        if (c1641a == null) {
            return;
        }
        setPage(c1641a.f5371b);
    }

    static {
        CLICK_ACTION_MAP.append(GroupType.DEFAULT_ARTIST.ordinal(), new C1641a(SAction.ACTION_LOCAL_ARTIST_CLICK, SPage.PAGE_LOCAL_ARTIST, SPage.PAGE_LOCAL_ARTIST_DETAIL));
        CLICK_ACTION_MAP.append(GroupType.DEFAULT_ALBUM.ordinal(), new C1641a(SAction.ACTION_LOCAL_ALBUM_CLICK, SPage.PAGE_LOCAL_ALBUM, SPage.PAGE_LOCAL_ALBUM_DETAIL));
        CLICK_ACTION_MAP.append(GroupType.DEFAULT_FOLDER.ordinal(), new C1641a(SAction.ACTION_LOCAL_FOLDER_CLICK, SPage.PAGE_LOCAL_FOLDER, SPage.PAGE_LOCAL_FOLDER_DETAIL));
        CLICK_ACTION_MAP.append(GroupType.CUSTOM_LOCAL.ordinal(), new C1641a(SAction.ACTION_NONE, SPage.PAGE_MY_SONGLIST, SPage.PAGE_NONE));
        CLICK_ACTION_MAP.append(GroupType.CUSTOM_ALL.ordinal(), new C1641a(SAction.ACTION_NONE, SPage.PAGE_MY_SONGLIST, SPage.PAGE_NONE));
        ORDERID_ACTION_MAP = new SparseArray<>();
        ORDERID_ACTION_MAP.append(7, SAction.ACTION_MENU_ORDER_BY_TITLE);
        ORDERID_ACTION_MAP.append(10, SAction.ACTION_MENU_ORDER_BY_ADDED_TIME);
        ORDERID_ACTION_MAP.append(12, SAction.ACTION_MENU_ORDER_BY_AMOUNT);
        ORDERID_ACTION_MAP.append(9, SAction.ACTION_MENU_ORDER_BY_ALBUM);
        ORDERID_ACTION_MAP.append(11, SAction.ACTION_MENU_ORDER_BY_FILENAME);
        ORDERID_ACTION_MAP.append(8, SAction.ACTION_MENU_ORDER_BY_ARTIST);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.list.GroupListFragment$a */
    /* loaded from: classes.dex */
    public static final class C1641a {

        /* renamed from: a */
        private SAction f5370a;

        /* renamed from: b */
        private SPage f5371b;

        /* renamed from: c */
        private SPage f5372c;

        private C1641a(SAction sAction, SPage sPage, SPage sPage2) {
            this.f5370a = sAction;
            this.f5371b = sPage;
            this.f5372c = sPage2;
        }
    }

    public static SAction orderAction(int i) {
        return ORDERID_ACTION_MAP.get(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_GROUP_LIST, ReflectUtils.loadMethod(cls, "updateGroupList", GroupType.class, List.class));
        map.put(CommandID.SCAN_FINISHED, ReflectUtils.loadMethod(cls, "onScanFinished", Integer.class));
        map.put(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, ReflectUtils.loadMethod(cls, "updateMediaLibraryChanged", String.class));
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, (ViewGroup) null);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    protected void configListFooterView(View view) {
        TextView textView = (TextView) view;
        List<GroupItem> groupItemList = getGroupItemList();
        textView.setText((groupItemList != null ? groupItemList.size() : 0) + " " + groupTypeToName());
    }

    private String groupTypeToName() {
        switch (this.mGroupType) {
            case DEFAULT_ARTIST:
                return getString(R.string.local_music_artist);
            case DEFAULT_GENRE:
                return getString(R.string.local_music_genre);
            case DEFAULT_FOLDER:
                return getString(R.string.folder);
            case DEFAULT_ALBUM:
                return getString(R.string.local_music_album);
            case CUSTOM_LOCAL:
            case CUSTOM_ALL:
                return getString(R.string.playlist);
            default:
                throw new IllegalArgumentException("invalid GroupType");
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.listview_footer_local_media, (ViewGroup) null);
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.GroupListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GroupListFragment.this.startActivity(new Intent(GroupListFragment.this.getActivity(), MediaScanAnimationActivity.class));
            }
        });
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    protected void configFailedView(View view) {
        configNoDataView((IconTextView) view.findViewById(R.id.no_media_icon), (TextView) view.findViewById(R.id.textview_load_failed), (TextView) view.findViewById(R.id.no_data_action_view));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    protected boolean canLoadDataWhenResume() {
        return !isLocalGroup();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    protected void onReloadData() {
        if (MediaStorage.MEDIA_ORDER_BY_AMOUNT.equals(Preferences.m3016a(this.mGroupType))) {
            CommandCenter.getInstance().execute(new Command(CommandID.QUERY_GROUP_ITEM_LIST_BY_AMOUNT_ORDER, this.mGroupType));
        } else {
            CommandCenter.getInstance().execute(new Command(CommandID.QUERY_GROUP_ITEM_LIST, this.mGroupType));
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getGroupItemList() != null) {
            updateAZKeys(buildRawAZKeys(getGroupItemList()));
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    protected boolean isLocalGroup() {
        return this.mGroupType.equals(GroupType.DEFAULT_ARTIST) || this.mGroupType.equals(GroupType.DEFAULT_ALBUM) || this.mGroupType.equals(GroupType.DEFAULT_GENRE) || this.mGroupType.equals(GroupType.DEFAULT_FOLDER);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    protected boolean isAZSideBarEnable() {
        return getGroupItemList() != null && getGroupItemList().size() > 20 && isLocalGroup() && !MediaStorage.MEDIA_ORDER_BY_AMOUNT.equals(Preferences.m3016a(this.mGroupType));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.BaseGroupListFragment
    public void onGroupItemClicked(GroupItem groupItem) {
        C1641a c1641a;
        if (isLocalGroup() && (c1641a = CLICK_ACTION_MAP.get(this.mGroupType.ordinal())) != null) {
            //SUserUtils.m4956a(c1641a.f5370a, c1641a.f5372c);
        }
        launchSubMediaListFragment((String) TTTextUtils.validateString(getActivity(), groupItem.getName()), groupItem.getGroupID());
    }

    public void onScanFinished(Integer num) {
        onReloadData();
    }

    public void updateMediaLibraryChanged(String str) {
        if (getGroupItemList() != null) {
            if (MediaStorage.GROUP_ID_ALL_LOCAL.equals(str)) {
                onReloadData();
                return;
            }
            for (GroupItem groupItem : getGroupItemList()) {
                if (str.equals(groupItem.getGroupID())) {
                    onReloadData();
                    return;
                }
            }
        }
    }

    public void updateGroupList(GroupType groupType, List<GroupItem> list) {
        if (groupType == this.mGroupType) {
            updateGroupList(list);
            updateAZKeys(buildRawAZKeys(list));
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.ISearchAble
    public void search() {
        if (CLICK_ACTION_MAP.get(this.mGroupType.ordinal()) != null) {
            //SUserUtils.m4956a(SAction.ACTION_LOCAL_SEARCH, SPage.PAGE_NONE);
        }
        startActivityForResult(new Intent(getActivity(), MediaGroupSearchActivity.class).putExtra(KEY_GROUP_TYPE, this.mGroupType.name()).putExtra("origin", groupTypeToName()), 1);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, final Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1 && intent != null) {
            new Handler().postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.list.GroupListFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, intent.getStringExtra("title"));
                    bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, intent.getStringExtra(AbsMediaListFragment.KEY_GROUP_ID));
                    GroupListFragment.this.launchFragment((BaseFragment) Fragment.instantiate(GroupListFragment.this.getActivity(), SubMediaListFragment.class.getName(), bundle));
                }
            }, DELAY_LAUNCH);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GroupType getGroupType() {
        return this.mGroupType;
    }

    private List<String> buildRawAZKeys(List<GroupItem> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (GroupItem groupItem : list) {
            if (this.mGroupType == GroupType.DEFAULT_FOLDER) {
                arrayList.add(FileUtils.getFilename(groupItem.getName()));
            } else {
                arrayList.add(groupItem.getName());
            }
        }
        return arrayList;
    }

    private void launchSubMediaListFragment(String str, String str2) {
        Bundle bundle = new Bundle();
        if (this.mGroupType == GroupType.DEFAULT_FOLDER) {
            str = FileUtils.getFilename(str);
        }
        bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, str);
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, str2);
        launchFragment((BaseFragment) instantiate(getActivity(), SubMediaListFragment.selectSubMediaListFragmentClassName(str2), bundle));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onKeyPressed(int i, KeyEvent keyEvent) {
        super.onKeyPressed(i, keyEvent);
        if (getUserVisibleHint() && i == 84) {
            search();
        }
    }

    public void order() {
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IOrderAble
    public void order(int i) {
        String str = "";
        if (i == 12) {
            str = MediaStorage.MEDIA_ORDER_BY_AMOUNT;
        }
        Preferences.m3015a(this.mGroupType, str);
        SAction orderAction = orderAction(i);
        if (orderAction != null) {
            //SUserUtils.m4956a(orderAction, SPage.PAGE_NONE);
        }
        onReloadData();
    }
}
