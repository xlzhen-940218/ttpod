package com.sds.android.ttpod.fragment.audioeffect;

import android.content.Intent;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.activities.audioeffect.CustomEqualizerActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.EqualizerPreset;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class BaseEqualizerFragment extends BaseFragment {
    public static final TTEqualizer.Settings DEFAULT_SETTINGS = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
    private static final String TAG = "BaseEqualizerFragment";
    protected TTEqualizer.Settings mEqualizerSettings = null;

    protected abstract void updateListView(String str);

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadEqData();
    }

    private void loadEqData() {
        AudioEffectParam m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s();
        if (m2457s != null) {
            this.mEqualizerSettings = new TTEqualizer.Settings(m2457s.m4421g());
        } else if (this.mEqualizerSettings == null) {
            this.mEqualizerSettings = DEFAULT_SETTINGS;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.loadMethod(getClass(), "updateAudioEffectInfo", new Class[0]));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            updateAudioEffectInfo();
        }
    }

    public void updateAudioEffectInfo() {
        if (isAdded()) {
            TTEqualizer.Settings settings = DEFAULT_SETTINGS;
            AudioEffectParam m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s();
            if (m2457s != null) {
                settings = new TTEqualizer.Settings(m2457s.m4421g());
            }
            updateListView(settings.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setEqualizer(TTEqualizer.Settings settings) {
        LogUtils.debug(TAG, "setEqualizer " + settings);
        CommandCenter.getInstance().execute(new Command(CommandID.SET_EQUALIZER, settings));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TTEqualizer.Settings getEqualizerSettingsByName(String str) {
        short[] m4333b = EqualizerPreset.m4333b(str);
        if (m4333b == null) {
            m4333b = DEFAULT_SETTINGS.getBandLevels();
        }
        return new TTEqualizer.Settings(str, (short) 10, m4333b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void startCustomEqualizerActivity(TTEqualizer.Settings settings) {
        Preferences.m2864k(settings.toString());
        Intent intent = new Intent(getActivity(), CustomEqualizerActivity.class);
        intent.putExtra(CustomEqualizerActivity.KEY_CUSTOM_EQUALIZER, settings.toString());
        startActivity(intent);
    }
}
