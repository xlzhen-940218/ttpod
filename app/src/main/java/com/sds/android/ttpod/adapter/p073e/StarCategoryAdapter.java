package com.sds.android.ttpod.adapter.p073e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.g */
/* loaded from: classes.dex */
public class StarCategoryAdapter extends BaseListAdapter<StarCategory> {
    public StarCategoryAdapter(Context context, List<StarCategory> list) {
        super(context, list);
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_star_category_item, (ViewGroup) null, false);
        inflate.setTag(inflate.findViewById(R.id.category_title));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void buildDataUI(View view, StarCategory starCategory, int i) {
        ((TextView) view.getTag()).setText(starCategory.getName());
    }
}
