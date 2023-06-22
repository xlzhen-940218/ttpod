package com.sds.android.ttpod.activities.audioeffect;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.PrivateEffectItem;
import com.sds.android.ttpod.framework.p106a.p107a.AudioEffectStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SelectDeleteEffectActivity extends SlidingClosableActivity {
    public static final String KEY_EFFECT_LIST = "key_effect_list";
    private static List<PrivateEffectItem> sEffectList;
    private View mDeleteView;
    private SelectDeleteEffectListFragment mFragment;
    private ActionBarController.C1070a mSelectionAction;
    private List<PrivateEffectItem> mList = new ArrayList();
    private boolean mIsSelected = false;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.audioeffect.SelectDeleteEffectActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.button_delete) {
                SelectDeleteEffectActivity.this.performDeleteClick();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getString(R.string.select_delete_effects));
        setContentView(R.layout.activity_select_delete_effect);
        initViews();
        initMyEffectFragment();
        initActionBar();
        initData();
    }

    public static void setEffectList(List<PrivateEffectItem> list) {
        sEffectList = list;
    }

    public void updateQueryPrivateEffect(List<PrivateEffectItem> list, List<MediaItem> list2) {
        if (!isFinishing()) {
            this.mList = list;
            this.mFragment.updateData(list);
            this.mSelectionAction.m7155c(this.mList.size() > 0);
        }
    }

    private void initActionBar() {
        this.mSelectionAction = getActionBarController().m7178d(R.drawable.img_checkbox_multiselect_unchecked);
        this.mSelectionAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.audioeffect.SelectDeleteEffectActivity.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                if (c1070a.m7150f() == null) {
                    c1070a.m7166a((Object) c1070a);
                    c1070a.m7153d(R.drawable.img_checkbox_multiselect_checked);
                    SelectDeleteEffectActivity.this.mFragment.selectAll();
                    return;
                }
                c1070a.m7166a((Object) null);
                c1070a.m7153d(R.drawable.img_checkbox_multiselect_unchecked);
                SelectDeleteEffectActivity.this.mFragment.selectNone();
            }
        });
        this.mSelectionAction.m7155c(false);
        ActionBarUtils.m8130a(getActionBarController());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAction() {
        if (this.mFragment.getSelectedCount() == this.mList.size()) {
            this.mSelectionAction.m7166a((Object) this.mSelectionAction);
            this.mSelectionAction.m7153d(R.drawable.img_checkbox_multiselect_checked);
            return;
        }
        this.mSelectionAction.m7166a((Object) null);
        this.mSelectionAction.m7153d(R.drawable.img_checkbox_multiselect_unchecked);
    }

    private void initMyEffectFragment() {
        this.mFragment = new SelectDeleteEffectListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_myeffect, this.mFragment).commit();
    }

    private void initViews() {
        this.mDeleteView = findViewById(R.id.button_delete);
        this.mDeleteView.setOnClickListener(this.mOnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performDeleteClick() {
        List<PrivateEffectItem> selectItems = this.mFragment.getSelectItems();
        this.mList.removeAll(selectItems);
        CommandCenter.m4607a().m4596b(new Command(CommandID.DELETE_PRIVATE_EFFECT_LIST, selectItems));
        this.mFragment.updateData(this.mList);
        this.mSelectionAction.m7155c(this.mList.size() > 0);
        this.mIsSelected = false;
        AudioEffectStatistic.m5246z();
        SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_DEL_SELECTED_OK, SPage.PAGE_NONE, SPage.PAGE_AUDIO_MY_CLOUD_EFFECT);
        if (selectItems != null && !selectItems.isEmpty()) {
            PopupsUtils.m6760a((int) R.string.string_del_audio);
        }
        finish();
    }

    private void initData() {
        updateQueryPrivateEffect(sEffectList, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        sEffectList = null;
        super.onDestroy();
    }

    /* loaded from: classes.dex */
    public class SelectDeleteEffectListFragment extends BaseEffectListFragment {
        private SparseBooleanArray mSelectedArray = new SparseBooleanArray();

        public SelectDeleteEffectListFragment() {
        }

        @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment
        protected View getEffectItem(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater(null).inflate(R.layout.effect_list_item, (ViewGroup) null);
                view.setTag(new C0697a(view));
            }
            C0697a c0697a = (C0697a) view.getTag();
            PrivateEffectItem privateEffectItem = this.mMyEffectItemList.get(i);
            c0697a.m8134a().setText(privateEffectItem.m4332a());
            c0697a.m8133b().setText(getString(R.string.effect_provide_by, privateEffectItem.m4329b()));
            c0697a.m8131d().setVisibility(View.GONE);
            c0697a.m8132c().setVisibility(View.VISIBLE);
            c0697a.m8132c().setChecked(this.mSelectedArray.get(i));
            c0697a.m8132c().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.audioeffect.SelectDeleteEffectActivity.SelectDeleteEffectListFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SelectDeleteEffectListFragment.this.mSelectedArray.put(i, !SelectDeleteEffectListFragment.this.mSelectedArray.get(i));
                    SelectDeleteEffectActivity.this.updateAction();
                }
            });
            return view;
        }

        @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment
        public void updateData(List<PrivateEffectItem> list) {
            super.updateData(list);
            this.mSelectedArray = new SparseBooleanArray(list.size());
        }

        @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEffectListFragment, android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            super.onItemClick(adapterView, view, i, j);
            this.mSelectedArray.put(i, !this.mSelectedArray.get(i));
            notifyDataSetChanged();
            SelectDeleteEffectActivity.this.updateAction();
            if (!SelectDeleteEffectActivity.this.mIsSelected) {
                AudioEffectStatistic.m5247y();
                SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_DEL_SELECTED, SPage.PAGE_NONE, SPage.PAGE_AUDIO_MY_CLOUD_EFFECT);
                SelectDeleteEffectActivity.this.mIsSelected = true;
            }
        }

        public void selectAll() {
            int count = this.mAdapter.getCount();
            for (int i = 0; i < count; i++) {
                this.mSelectedArray.put(i, true);
            }
            notifyDataSetChanged();
        }

        public void selectNone() {
            this.mSelectedArray.clear();
            notifyDataSetChanged();
        }

        public List<PrivateEffectItem> getSelectItems() {
            int size = this.mSelectedArray.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i = 0; i < size; i++) {
                if (this.mSelectedArray.valueAt(i)) {
                    arrayList.add(this.mMyEffectItemList.get(this.mSelectedArray.keyAt(i)));
                }
            }
            return arrayList;
        }

        public int getSelectedCount() {
            int i = 0;
            int size = this.mSelectedArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.mSelectedArray.valueAt(i2)) {
                    i++;
                }
            }
            return i;
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.audioeffect.SelectDeleteEffectActivity$a */
    /* loaded from: classes.dex */
    public class C0697a {

        /* renamed from: b */
        private TextView f2600b;

        /* renamed from: c */
        private TextView f2601c;

        /* renamed from: d */
        private TextView f2602d;

        /* renamed from: e */
        private CheckBox f2603e;

        public C0697a(View view) {
            this.f2600b = (TextView) view.findViewById(R.id.textview_title);
            this.f2601c = (TextView) view.findViewById(R.id.textview_author);
            this.f2602d = (TextView) view.findViewById(R.id.textview_style);
            this.f2603e = (CheckBox) view.findViewById(R.id.check_view);
        }

        /* renamed from: a */
        public TextView m8134a() {
            return this.f2600b;
        }

        /* renamed from: b */
        public TextView m8133b() {
            return this.f2601c;
        }

        /* renamed from: c */
        public CheckBox m8132c() {
            return this.f2603e;
        }

        /* renamed from: d */
        public TextView m8131d() {
            return this.f2602d;
        }
    }
}
