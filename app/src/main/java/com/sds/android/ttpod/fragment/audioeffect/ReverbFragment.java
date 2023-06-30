package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p070b.AudioEffectGridAdapter;
import com.sds.android.ttpod.component.p085b.AudioEffectItem;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ReverbFragment extends BaseFragment {
    private static final int[] REVERB_RES = {R.drawable.img_background_imageview_effect_reverb_none, R.drawable.img_background_imageview_effect_reverb_concert, R.drawable.img_background_imageview_effect_reverb_bedroom, R.drawable.img_background_imageview_effect_reverb_stone_house, R.drawable.img_background_imageview_effect_reverb_theater, R.drawable.img_background_imageview_effect_reverb_cave, R.drawable.img_background_imageview_effect_reverb_roadway, R.drawable.img_background_imageview_effect_reverb_city, R.drawable.img_background_imageview_effect_reverb_parking_lot, R.drawable.img_background_imageview_effect_reverb_parlor, R.drawable.img_background_imageview_effect_reverb_auditorium, R.drawable.img_background_imageview_effect_reverb_hall};
    private AudioEffectGridAdapter mAdapter;
    private List<AudioEffectItem> mAudioEffectItems = new ArrayList(REVERB_RES.length);
    private boolean mIsSelectd = false;
    private View mRootView;

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int length = REVERB_RES.length;
        String[] stringArray = getActivity().getResources().getStringArray(R.array.environment_title);
        if (stringArray.length != length) {
            throw new RuntimeException("音效资源不正确");
        }
        for (int i = 0; i < length; i++) {
            this.mAudioEffectItems.add(new AudioEffectItem(REVERB_RES[i], stringArray[i], Integer.toString(i)));
        }
        this.mAdapter = new AudioEffectGridAdapter(getActivity(), this.mAudioEffectItems, "");
        updateAudioEffectInfo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.loadMethod(getClass(), "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        AudioEffectParam m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s();
        if (this.mAdapter != null && m2457s != null) {
            this.mAdapter.m7611a(String.valueOf(m2457s.m4429d()));
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_audio_effect_reverb, viewGroup, false);
            GridView gridView = (GridView) this.mRootView.findViewById(R.id.gridview_reverb);
            gridView.setAdapter((ListAdapter) this.mAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.ReverbFragment.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    ReverbFragment.this.processItemClick(i);
                }
            });
        }
        return this.mRootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processItemClick(int i) {
        String m6997c = this.mAudioEffectItems.get(i).m6997c();
        this.mAdapter.m7611a(m6997c);
        int parseInt = Integer.parseInt(m6997c);
        Preferences.m3075C(true);
        CommandCenter.getInstance().execute(new Command(CommandID.SET_REVERB, Integer.valueOf(parseInt)));
        if (!this.mIsSelectd) {
            this.mIsSelectd = true;
            //AudioEffectStatistic.m5258n();
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_REVERB_SELECTED, SPage.PAGE_AUDIO_REVERB, SPage.PAGE_NONE);
        }
    }
}
