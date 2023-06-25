package com.sds.android.ttpod.fragment.skinmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.ThemeRankActivity;
import com.sds.android.ttpod.fragment.skinmanager.base.OnlineThemeFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.widget.StateView;
import com.voicedragon.musicclient.util.DensityUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class ThemeRecommendFragment extends OnlineThemeFragment {
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void initListViewHeader() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_theme_header_layout, null);
        inflate.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getActivity(),120)));
        this.mThemeListView.addHeaderView(inflate);
        setHeadToggle(inflate.findViewById(R.id.button_toggle_background_manager), R.drawable.img_beautiful_background, new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.ThemeRecommendFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EntryUtils.m8285j(ThemeRecommendFragment.this.getActivity());
            }
        });
        setHeadToggle(inflate.findViewById(R.id.button_toggle_theme_rank), R.drawable.img_theme_rank, new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.ThemeRecommendFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                //ThemeStatistic.m4895c();
                ThemeRecommendFragment.this.getActivity().startActivity(new Intent(ThemeRecommendFragment.this.getActivity(), ThemeRankActivity.class));
            }
        });
    }

    private void setHeadToggle(View view, int i, View.OnClickListener onClickListener) {
        try {
            view.setBackgroundResource(i);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        view.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_RECOMMEND_SKIN_LIST, ReflectUtils.m8375a(cls, "updateDataListForAdapter", ArrayList.class));
        map.put(CommandID.UPDATE_DOWNLOADED_SKIN_LIST, ReflectUtils.m8375a(cls, "updateDownloadedSkinList", ArrayList.class));
    }

    public void updateDataListForAdapter(ArrayList<SkinItem> arrayList) {
        this.mCacheMode = false;
        if (arrayList != null && arrayList.size() != 0) {
            if (checkIfReloadData(arrayList)) {
                setAdapterDataSource(arrayList);
            }
            CommandCenter.getInstance().m4596b(new Command(CommandID.REQUEST_DOWNLOADED_SKIN_LIST, new Object[0]));
            return;
        }
        this.mStateView.setState(StateView.EnumC2248b.FAILED);
    }

    public void updateDownloadedSkinList(ArrayList<SkinItem> arrayList) {
        setLocalSkinInfoMap(arrayList);
        performSkinInfoLoaded();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.OnlineThemeFragment, com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected String getStatisticOrigin() {
        return "recommend";
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSubClassTag = ThemeRecommendFragment.class.getSimpleName();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        Cache.getInstance().m3203a(getThemeDataList());
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected ArrayList<SkinItem> loadDataFromCache() {
        return Cache.getInstance().m3145s();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected CommandID getLoadDataCommandID() {
        return CommandID.REQUEST_RECOMMEND_SKIN_LIST;
    }
}
