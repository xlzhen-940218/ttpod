package com.sds.android.ttpod.activities.mediascan.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class MediaScanExcludeActivity extends SlidingClosableActivity {
    private C0764a mAdapter;
    private List<String> mAllFolderList = new ArrayList();
    private Set<String> mExcludeFolderSet = new HashSet();

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mediascan_exclude);
        setTitle(R.string.mediascan_setting_choose_exclude_folders);
        this.mExcludeFolderSet = Preferences.m2882g() != null ? Preferences.m2882g() : this.mExcludeFolderSet;
        for (String str : this.mExcludeFolderSet) {
            if (FileUtils.m8419a(str)) {
                this.mAllFolderList.add(str);
            }
        }
        this.mAdapter = new C0764a();
        ListView listView = (ListView) findViewById(R.id.listview_mediascan_exclude);
        listView.setAdapter((ListAdapter) this.mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.activities.mediascan.setting.MediaScanExcludeActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((C0766b) view.getTag()).f2729c.toggle();
            }
        });
        CommandCenter.getInstance().m4606a(new Command(CommandID.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_FOLDER));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_GROUP_LIST, ReflectUtils.m8375a(getClass(), "updateGroupList", GroupType.class, List.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Preferences.m3008a(new HashSet(this.mExcludeFolderSet));
    }

    public void updateGroupList(GroupType groupType, List<GroupItem> list) {
        if (groupType.equals(GroupType.DEFAULT_FOLDER)) {
            for (GroupItem groupItem : list) {
                String name = groupItem.getName();
                if (!this.mExcludeFolderSet.contains(name)) {
                    this.mAllFolderList.add(name);
                }
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.mediascan.setting.MediaScanExcludeActivity$a */
    /* loaded from: classes.dex */
    private final class C0764a extends BaseAdapter {
        private C0764a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return MediaScanExcludeActivity.this.mAllFolderList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return MediaScanExcludeActivity.this.mAllFolderList.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            C0766b c0766b;
            if (view == null) {
                view = LayoutInflater.from(MediaScanExcludeActivity.this).inflate(R.layout.mediascan_exclude_item, viewGroup, false);
                C0766b c0766b2 = new C0766b((TextView) view.findViewById(R.id.textview_mediascan_exclude_item_title), (TextView) view.findViewById(R.id.textview_mediascan_exclude_item_sub_title), (CheckBox) view.findViewById(R.id.checkbox_mediascan_exclude_item));
                view.setTag(c0766b2);
                c0766b = c0766b2;
            } else {
                c0766b = (C0766b) view.getTag();
            }
            String str = (String) MediaScanExcludeActivity.this.mAllFolderList.get(i);
            c0766b.f2727a.setText(FileUtils.getFilename(str));
            c0766b.f2728b.setText(FileUtils.m8400l(str));
            c0766b.f2729c.setOnCheckedChangeListener(null);
            c0766b.f2729c.setChecked(MediaScanExcludeActivity.this.mExcludeFolderSet.contains(str));
            c0766b.f2729c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sds.android.ttpod.activities.mediascan.setting.MediaScanExcludeActivity.a.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        MediaScanExcludeActivity.this.mExcludeFolderSet.add(MediaScanExcludeActivity.this.mAllFolderList.get(i));
                    } else {
                        MediaScanExcludeActivity.this.mExcludeFolderSet.remove(MediaScanExcludeActivity.this.mAllFolderList.get(i));
                    }
                }
            });
            return view;
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.mediascan.setting.MediaScanExcludeActivity$b */
    /* loaded from: classes.dex */
    private static final class C0766b {

        /* renamed from: a */
        private TextView f2727a;

        /* renamed from: b */
        private TextView f2728b;

        /* renamed from: c */
        private CheckBox f2729c;

        private C0766b(TextView textView, TextView textView2, CheckBox checkBox) {
            this.f2727a = textView;
            this.f2728b = textView2;
            this.f2729c = checkBox;
        }
    }
}
