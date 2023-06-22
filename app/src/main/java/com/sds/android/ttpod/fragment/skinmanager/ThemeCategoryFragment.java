package com.sds.android.ttpod.fragment.skinmanager;

import android.content.Intent;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SkinCategoryDetailActivity;
import com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p128a.CategoryItem;
import com.sds.android.ttpod.framework.p106a.p107a.ThemeStatistic;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class ThemeCategoryFragment extends BaseCategoryFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.ON_SKIN_CATEGORY_LIST_PARSED, ReflectUtils.m8375a(cls, "onDataListParsed", ArrayList.class, Long.class));
        map.put(CommandID.ON_SKIN_CATEGORY_LIST_DOWNLOADED, ReflectUtils.m8375a(cls, "onDataListDownloaded", new Class[0]));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected Command getLoadDataCommand() {
        return new Command(CommandID.PARSE_CATEGORY_LIST, 0);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected CommandID getRequestDataCommandID() {
        return CommandID.REQUEST_SKIN_CATEGORY_LIST;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected void startActivity(CategoryItem categoryItem) {
        Intent intent = new Intent(getActivity(), SkinCategoryDetailActivity.class);
        intent.putExtra("id", categoryItem.m3867a());
        intent.putExtra("name", categoryItem.m3863b());
        getActivity().startActivity(intent);
        ThemeStatistic.m4892d(categoryItem.m3863b());
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected int getItemLayoutId() {
        return R.layout.layout_category_item;
    }
}
