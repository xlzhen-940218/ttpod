package com.sds.android.ttpod.activities.local;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p072d.BaseSearchAdapter;
import com.sds.android.ttpod.adapter.p072d.MediaGroupListAdapter;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.GroupListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MediaGroupSearchActivity extends BaseSearchActivity {
    private static final String TAG = "MediaGroupSearchActivity";
    private C0734a mAdapter;
    private GroupType mGroupType;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onNewIntent(getIntent());
        CommandCenter.m4607a().m4606a(new Command(CommandID.QUERY_GROUP_ITEM_LIST, this.mGroupType));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            String stringExtra = intent.getStringExtra(GroupListFragment.KEY_GROUP_TYPE);
            if (TextUtils.isEmpty(stringExtra)) {
                throw new IllegalArgumentException("group type is invalid");
            }
            this.mGroupType = GroupType.valueOf(stringExtra);
        }
    }

    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity
    protected BaseSearchAdapter onCreateAdapter() {
        this.mAdapter = new C0734a(this);
        return this.mAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_GROUP_LIST, ReflectUtils.m8375a(getClass(), "updateGroupList", GroupType.class, List.class));
    }

    public void updateGroupList(GroupType groupType, List<GroupItem> list) {
        Object[] objArr = new Object[2];
        objArr[0] = groupType.name();
        objArr[1] = Integer.valueOf(list != null ? list.size() : -1);
        LogUtils.m8386a(TAG, "updateGroupList groupType=%s, size=%d", objArr);
        if (this.mGroupType.equals(groupType)) {
            this.mAdapter.m8078a(list);
            onLoadDataFinished();
        }
    }

    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity, android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity
    public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
        GroupItem m8072a = this.mAdapter.getItem(i).m8072a();
        setResult(-1, new Intent().putExtra(AbsMediaListFragment.KEY_GROUP_ID, m8072a.getGroupID()).putExtra("title", m8072a.getName()));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.local.MediaGroupSearchActivity$a */
    /* loaded from: classes.dex */
    public class C0734a extends BaseSearchAdapter {
        public C0734a(Activity activity) {
            super(activity);
        }

        /* renamed from: a */
        public void m8078a(List<GroupItem> list) {
            if (list == null) {
                throw new IllegalArgumentException("datas must not be null");
            }
            for (GroupItem groupItem : list) {
                this.f3229f.add(new C0735a(groupItem));
            }
            f3225c = "";
            f3226d = "";
            this.f3231h = this.f3229f;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f3231h.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public C0735a getItem(int i) {
            return (C0735a) this.f3231h.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.f3228b, R.layout.media_group_item, null);
                view.setTag(new MediaGroupListAdapter.C0969a(view));
            }
            m8079a((MediaGroupListAdapter.C0969a) view.getTag(), getItem(i));
            return view;
        }

        /* renamed from: a */
        private void m8079a(MediaGroupListAdapter.C0969a c0969a, C0735a c0735a) {
            c0969a.m7574e().setVisibility(View.GONE);
            c0969a.m7576c().setText(c0735a.m8071b());
            c0969a.m7575d().setText(this.f3228b.getString(R.string.count_of_media, new Object[]{c0735a.m8072a().getCount()}));
            c0969a.m3250a(ThemeUtils.m8163b());
        }

        /* renamed from: com.sds.android.ttpod.activities.local.MediaGroupSearchActivity$a$a */
        /* loaded from: classes.dex */
        public class C0735a implements BaseSearchAdapter.InterfaceC0967a {

            /* renamed from: b */
            private GroupItem f2665b;

            /* renamed from: c */
            private PinyinUtils.Pinyin f2666c;

            /* renamed from: d */
            private PinyinUtils.Pinyin f2667d;

            /* renamed from: a */
            public GroupItem m8072a() {
                return this.f2665b;
            }

            /* renamed from: b */
            public CharSequence m8071b() {
                if (TextUtils.isEmpty(C0734a.f3226d) || this.f2667d == null) {
                    return this.f2665b.getName();
                }
                int[] iArr = {0, 0, 0};
                if (!C0734a.this.m7589a(this.f2666c, iArr) && !C0734a.this.m7589a(this.f2667d, iArr)) {
                    C0734a.this.m7587a(this.f2665b.getName(), iArr);
                }
                if (iArr[2] > 0) {
                    SpannableString spannableString = new SpannableString(this.f2665b.getName());
                    spannableString.setSpan(C0734a.f3227e, iArr[1], iArr[2] + iArr[1], 33);
                    return spannableString;
                }
                return this.f2665b.getName();
            }

            public C0735a(GroupItem groupItem) {
                this.f2665b = groupItem;
                PinyinUtils.Pinyin[] twoKindOfPinyin = PinyinUtils.getTwoKindOfPinyin(this.f2665b.getName());
                if (twoKindOfPinyin != null) {
                    this.f2666c = twoKindOfPinyin[0];
                    this.f2667d = twoKindOfPinyin[1];
                }
            }

            @Override // com.sds.android.ttpod.adapter.p072d.BaseSearchAdapter.InterfaceC0967a
            /* renamed from: c */
            public int[] mo7583c() {
                int[] iArr = {0, 0, 0};
                boolean a = C0734a.this.m7589a(this.f2666c, iArr);
                if (!a && !(a = C0734a.this.m7589a(this.f2667d, iArr))) {
                    a = C0734a.this.m7587a(this.f2665b.getName(), iArr);
                }
                if (a) {
                    return iArr;
                }
                return null;
            }
        }
    }
}
