package com.sds.android.ttpod.fragment.skinmanager.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.skinmanager.CategoryViewHolder;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p128a.CategoryItem;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.widget.StateView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class BaseCategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    protected static final long TIME_DELTA = 43200000;
    private C1753a mCategoryAdapter;
    protected GridView mGridView;
    private boolean mIsUpdated = false;
    private StateView mStateView;

    protected abstract int getItemLayoutId();

    protected abstract Command getLoadDataCommand();

    protected abstract CommandID getRequestDataCommandID();

    protected abstract void startActivity(CategoryItem categoryItem);

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCategoryAdapter = new C1753a();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_base_category_layout, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateView = (StateView) view.findViewById(R.id.theme_loadingview);
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        this.mGridView = (GridView) view.findViewById(R.id.gridview_category);
        this.mGridView.setAdapter((ListAdapter) this.mCategoryAdapter);
        this.mGridView.setOnItemClickListener(this);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        loadData();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        this.mCategoryAdapter.notifyDataSetChanged();
    }

    private void loadData() {
        CommandCenter.getInstance().m4606a(getLoadDataCommand());
    }

    public void onDataListDownloaded() {
        loadData();
    }

    public void onDataListParsed(ArrayList<CategoryItem> arrayList, Long l) {
        this.mCategoryAdapter.m5356a();
        setAdapterDataSource(arrayList);
        if (!this.mIsUpdated) {
            checkUpdateDataList(l);
            this.mIsUpdated = true;
        }
    }

    protected void setAdapterDataSource(ArrayList<CategoryItem> arrayList) {
        this.mCategoryAdapter.m5351a(arrayList);
        this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
    }

    protected C1753a getAdapter() {
        return this.mCategoryAdapter;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!EnvironmentUtils.C0604c.m8474e()) {
            PopupsUtils.m6760a((int) R.string.shake_error_hint);
            return;
        }
        CategoryItem item = this.mCategoryAdapter.getItem(i);
        if (item != null) {
            startActivity(item);
        }
    }

    private void checkUpdateDataList(Long l) {
        if (l.longValue() + TIME_DELTA < System.currentTimeMillis()) {
            CommandCenter.getInstance().m4606a(new Command(getRequestDataCommandID(), new Object[0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment$a */
    /* loaded from: classes.dex */
    public class C1753a extends BaseAdapter {

        /* renamed from: b */
        private LayoutInflater f5539b;

        /* renamed from: c */
        private ArrayList<CategoryItem> f5540c;

        /* renamed from: d */
        private final float f5541d = 0.6f;

        /* renamed from: e */
        private final float f5542e = 0.5f;

        /* renamed from: f */
        private final int f5543f = DisplayUtils.m7225c() / 2;

        /* renamed from: g */
        private final int f5544g = (int) ((this.f5543f * 0.6f) + 0.5f);

        public C1753a() {
            this.f5539b = LayoutInflater.from(BaseCategoryFragment.this.getActivity());
        }

        /* renamed from: a */
        public void m5351a(ArrayList<CategoryItem> arrayList) {
            if (arrayList != null) {
                this.f5540c = arrayList;
                notifyDataSetChanged();
            }
        }

        /* renamed from: a */
        public void m5356a() {
            if (this.f5540c != null) {
                this.f5540c.clear();
            }
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public CategoryItem getItem(int i) {
            if (this.f5540c == null || i >= this.f5540c.size()) {
                return null;
            }
            return this.f5540c.get(i);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.f5540c != null) {
                return this.f5540c.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = m5353a(viewGroup);
            }
            return m5354a(view, i);
        }

        /* renamed from: a */
        private View m5353a(ViewGroup viewGroup) {
            View inflate = this.f5539b.inflate(BaseCategoryFragment.this.getItemLayoutId(), viewGroup, false);
            inflate.setTag(new CategoryViewHolder(inflate));
            return inflate;
        }

        /* renamed from: a */
        private View m5354a(View view, int i) {
            CategoryViewHolder categoryViewHolder = (CategoryViewHolder) view.getTag();
            CategoryItem item = getItem(i);
            categoryViewHolder.m5391b().setText(item.m3863b());
            m5352a(item, categoryViewHolder.m5392a());
            return view;
        }

        /* renamed from: a */
        private void m5352a(CategoryItem categoryItem, ImageView imageView) {
            String m3860e = categoryItem.m3860e();
            Bitmap m4748a = ImageCacheUtils.m4748a(m3860e, this.f5543f, this.f5544g);
            if (m4748a != null) {
                imageView.setImageBitmap(m4748a);
            } else {
                ImageCacheUtils.m4752a(imageView, m3860e, this.f5543f, this.f5544g, (int) R.drawable.img_skin_default_thumbnail);
            }
        }
    }
}
