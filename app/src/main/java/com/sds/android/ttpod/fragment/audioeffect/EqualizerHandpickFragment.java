package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p070b.AudioEffectGridAdapter;
import com.sds.android.ttpod.component.p085b.AudioEffectItem;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EqualizerHandpickFragment extends BaseEqualizerFragment {
    private static final int[] HANDPICK_RES = {R.drawable.img_background_imageview_effect_equalizer_handpick_common, R.drawable.img_background_imageview_effect_equalizer_handpick_rock, R.drawable.img_background_imageview_effect_equalizer_handpick_pop, R.drawable.img_background_imageview_effect_equalizer_handpick_dance, R.drawable.img_background_imageview_effect_equalizer_handpick_hip_hop, R.drawable.img_background_imageview_effect_equalizer_handpick_classical, R.drawable.img_background_imageview_effect_equalizer_handpick_bass, R.drawable.img_background_imageview_effect_equalizer_handpick_voice};
    private static final String[] HANDPICK_TITLES_VALUES = {"普通/Normal", "摇滚/Rock", "流行/Pop", "舞曲/Dance", "嘻哈/Hip-Hop", "古典/Classic", "超重低音/Bass", "人声/Vocal"};
    public static final String KEY_CUSTOM = "自定义/custom";
    private static final String TAG = "EqualizerHandpickFragment";
    private AudioEffectGridAdapter mHandpickAdapter;
    private List<AudioEffectItem> mHandpickList = new ArrayList(HANDPICK_RES.length);
    private boolean mIsSelected = false;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.EqualizerHandpickFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            EqualizerHandpickFragment.this.performItemClick((String) view.getTag(R.id.view_bind_data));
        }
    };
    private View mRootView;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_audio_effect_reverb, viewGroup, false);
            AudioEffectParam m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s();
            initHandpickData(m2457s != null ? m2457s.m4421g() : "");
            initGridView();
        }
        return this.mRootView;
    }

    private void initGridView() {
        GridView gridView = (GridView) this.mRootView.findViewById(R.id.gridview_reverb);
        if (this.mEqualizerSettings != null) {
            this.mHandpickAdapter.m7611a(this.mEqualizerSettings.getName());
        }
        gridView.setAdapter((ListAdapter) this.mHandpickAdapter);
        gridView.setOnItemClickListener(this.mOnItemClickListener);
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mHandpickAdapter = null;
            this.mHandpickList.clear();
            this.mRootView = null;
        }
    }

    @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEqualizerFragment
    protected void updateListView(String str) {
        this.mHandpickAdapter.m7611a(str);
    }

    private void initHandpickData(String str) {
        int length = HANDPICK_RES.length;
        for (int i = 0; i < length; i++) {
            this.mHandpickList.add(new AudioEffectItem(HANDPICK_RES[i], HANDPICK_TITLES_VALUES[i], HANDPICK_TITLES_VALUES[i]));
        }
        this.mHandpickAdapter = new AudioEffectGridAdapter(getActivity(), this.mHandpickList, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performItemClick(String str) {
        if (str.equals(KEY_CUSTOM)) {
            String m2966ah = Preferences.m2966ah();
            TTEqualizer.Settings equalizerSettingsByName = StringUtils.isEmpty(m2966ah) ? getEqualizerSettingsByName(str) : new TTEqualizer.Settings(m2966ah);
            setEqualizer(equalizerSettingsByName);
            startCustomEqualizerActivity(equalizerSettingsByName);
        } else {
            setEqualizer(getEqualizerSettingsByName(str));
            Preferences.m3075C(true);
        }
        this.mHandpickAdapter.m7611a(str);
        if (!this.mIsSelected) {
            this.mIsSelected = true;
            //AudioEffectStatistic.m5265g();
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_DEFAULT_HANDPICK_SELECTED, SPage.PAGE_NONE, SPage.PAGE_NONE);
        }
    }
}
