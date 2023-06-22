package com.sds.android.ttpod.fragment.skinmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.BackgroundActivity;
import com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p128a.CategoryItem;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.ThemeStatistic;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class BackgroundCategoryFragment extends BaseCategoryFragment {
    private TextView mTextView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.ON_BACKGROUND_CATEGORY_LIST_PARSED, ReflectUtils.m8375a(cls, "onDataListParsed", ArrayList.class, Long.class));
        map.put(CommandID.ON_BKG_CATEGORY_LIST_DOWNLOADED, ReflectUtils.m8375a(cls, "onDataListDownloaded", new Class[0]));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTextView = (TextView) view.findViewById(R.id.picture_copyright);
        this.mTextView.setVisibility(View.VISIBLE);
        ((ViewGroup.MarginLayoutParams) this.mGridView.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(R.dimen.tab_label_height);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mTextView, ThemeElement.SUB_BAR_TEXT);
        ThemeManager.m3269a(this.mTextView, ThemeElement.SUB_BAR_BACKGROUND);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected Command getLoadDataCommand() {
        return new Command(CommandID.PARSE_CATEGORY_LIST, 1);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected CommandID getRequestDataCommandID() {
        return CommandID.REQUEST_BKG_CATEGORY_LIST;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected void startActivity(CategoryItem categoryItem) {
        Intent intent = new Intent(getActivity(), BackgroundActivity.class);
        intent.putExtra("id", categoryItem.m3867a());
        intent.putExtra("name", categoryItem.m3863b());
        getActivity().startActivity(intent);
        ThemeStatistic.m4890e(categoryItem.m3863b());
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment
    protected int getItemLayoutId() {
        return R.layout.layout_background_category_item;
    }
}
