package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes.dex */
public class BoostFragment extends BaseFragment {
    private static final int PADDING = 8;
    private static final float RATION_LEFT = 0.5f;
    private static final float RATION_LIMIT = 0.73f;
    private static final float RATION_RIGHT = 0.5f;
    private static final int SEEKBAR_MAX_VALUE = 10;
    private static int mBass;
    private static int mTreble;
    private static int mVirtualizer;
    private TextView mBassText;
    private RunnableC1387a mBoostCommandRunable;
    private RadialProgressWidget mButtonBass;
    private RadialProgressWidget mButtonTreble;
    private RadialProgressWidget mButtonVirtualizer;
    private CheckedTextView mImageLimit;
    private boolean mIsEdit;
    private boolean mLimit;
    private View mRootView;
    private SeekBar mSeekBarTreble;
    private TextView mTrebleText;
    private TextView mVirtualizerText;
    private static Handler mHandler = new Handler();
    private static LinkedList<RunnableC1387a> mBassQueue = new LinkedList<>();
    private static LinkedList<RunnableC1387a> mVirtualizerQueue = new LinkedList<>();
    private static LinkedList<RunnableC1387a> mTrebleQueue = new LinkedList<>();
    private boolean mBoostAjust = false;
    private float mBalance = -1.0f;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.BoostFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BoostFragment.this.mImageLimit.toggle();
            BoostFragment.this.judgeEffectIsEdit();
            BoostFragment.this.mLimit = BoostFragment.this.mImageLimit.isChecked();
            CommandCenter.getInstance().m4596b(new Command(CommandID.SET_BOOSTLIMIT_ENABLED, Boolean.valueOf(BoostFragment.this.mLimit)));
            if (!BoostFragment.this.mBoostAjust) {
                BoostFragment.this.mBoostAjust = true;
                //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_BOOST_CHANNEL_LIMIT, SPage.PAGE_AUDIO_BOOST, SPage.PAGE_NONE);
                //AudioEffectStatistic.m5254r();
            }
        }
    };
    private RadialProgressWidget.InterfaceC2256b mOnRotateChangeListener = new RadialProgressWidget.InterfaceC2256b() { // from class: com.sds.android.ttpod.fragment.audioeffect.BoostFragment.2
        @Override // com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget.InterfaceC2256b
        /* renamed from: a */
        public void mo1397a(RadialProgressWidget radialProgressWidget, int i) {
            if (radialProgressWidget == BoostFragment.this.mButtonBass) {
                BoostFragment.this.setBassValue(i);
            } else if (radialProgressWidget == BoostFragment.this.mButtonTreble) {
                BoostFragment.this.setTrebleValue(i);
            } else if (radialProgressWidget == BoostFragment.this.mButtonVirtualizer) {
                BoostFragment.this.setVirtualizerValue(i);
            }
            BoostFragment.mHandler.postDelayed(BoostFragment.this.mBoostCommandRunable, 100L);
        }
    };
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.BoostFragment.3
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            float max = BoostFragment.this.mSeekBarTreble.getMax() / 2.0f;
            float f = (i - max) / max;
            BoostFragment.this.judgeEffectIsEdit();
            if (f != BoostFragment.this.mBalance) {
                BoostFragment.this.mBalance = f;
                CommandCenter.getInstance().m4605a(new Command(CommandID.SET_CHANNELBALANCE, Float.valueOf(f)), 10);
                BoostFragment.this.judgeClickEffectBoostChanBalance();
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.audioeffect.BoostFragment$a */
    /* loaded from: classes.dex */
    public static class RunnableC1387a implements Runnable {

        /* renamed from: a */
        private CommandID f4944a;

        /* renamed from: b */
        private int f4945b;

        public RunnableC1387a(CommandID commandID, int i) {
            this.f4944a = commandID;
            this.f4945b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            CommandCenter.getInstance().m4596b(new Command(this.f4944a, Integer.valueOf(RadialProgressWidget.m1402c(this.f4945b))));
            if (this.f4944a == CommandID.SET_BASSBOOST) {
                int unused = BoostFragment.mBass = this.f4945b;
                BoostFragment.mBassQueue.remove(this);
            }
            if (this.f4944a == CommandID.SET_TREBLEBOOST) {
                int unused2 = BoostFragment.mTreble = this.f4945b;
                BoostFragment.mTrebleQueue.remove(this);
            }
            if (this.f4944a == CommandID.SET_VIRTUALIZER) {
                int unused3 = BoostFragment.mVirtualizer = this.f4945b;
                BoostFragment.mVirtualizerQueue.remove(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.m8375a(getClass(), "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        updateView(SupportFactory.m2397a(BaseApplication.getApplication()).m2457s());
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsEdit = Preferences.m2960ak();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_audio_effect_boost_new, viewGroup, false);
            initViews();
            updateView(SupportFactory.m2397a(BaseApplication.getApplication()).m2457s(), true);
        }
        return this.mRootView;
    }

    private void initViews() {
        int m7225c = DisplayUtils.m7225c();
        int m7229a = (int) ((m7225c - DisplayUtils.m7229a(8)) * 0.5f);
        int m7229a2 = (int) ((m7225c - DisplayUtils.m7229a(8)) * 0.5f);
        this.mButtonBass = (RadialProgressWidget) this.mRootView.findViewById(R.id.rotatebutton_bass_boost);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(m7229a, m7229a);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(m7229a2, m7229a2);
        this.mButtonBass.setLayoutParams(layoutParams);
        this.mButtonTreble = (RadialProgressWidget) this.mRootView.findViewById(R.id.rotatebutton_treble_boost);
        this.mButtonTreble.setLayoutParams(layoutParams);
        this.mButtonVirtualizer = (RadialProgressWidget) this.mRootView.findViewById(R.id.rotatebutton_surround);
        this.mButtonVirtualizer.setLayoutParams(layoutParams2);
        this.mImageLimit = (CheckedTextView) this.mRootView.findViewById(R.id.button_channel_limit);
        this.mImageLimit.setLayoutParams(new LinearLayout.LayoutParams((int) (m7229a2 * RATION_LIMIT), (int) (m7229a2 * RATION_LIMIT)));
        this.mSeekBarTreble = (SeekBar) this.mRootView.findViewById(R.id.seekbar_channel_balance);
        this.mBassText = (TextView) this.mRootView.findViewById(R.id.boost_bass_value);
        this.mTrebleText = (TextView) this.mRootView.findViewById(R.id.boost_treble_value);
        this.mVirtualizerText = (TextView) this.mRootView.findViewById(R.id.boost_virtualizer_value);
        this.mButtonBass.setOnRadialViewValueChanged(this.mOnRotateChangeListener);
        this.mButtonTreble.setOnRadialViewValueChanged(this.mOnRotateChangeListener);
        this.mButtonVirtualizer.setOnRadialViewValueChanged(this.mOnRotateChangeListener);
        this.mImageLimit.setOnClickListener(this.mOnClickListener);
        this.mSeekBarTreble.setOnSeekBarChangeListener(this.mOnSeekBarChangeListener);
        this.mSeekBarTreble.setMax(10);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void updateView(AudioEffectParam audioEffectParam, boolean z) {
        if (z) {
            mBass = 0;
            mTreble = 0;
            mVirtualizer = 0;
        }
        updateView(audioEffectParam);
    }

    public void updateView(AudioEffectParam audioEffectParam) {
        if (audioEffectParam != null) {
            int m1404b = RadialProgressWidget.m1404b(audioEffectParam.m4448a());
            if (mBass != m1404b) {
                mBass = m1404b;
                this.mButtonBass.setCurrentValue(m1404b);
                this.mBassText.setText(String.valueOf(m1404b));
            }
            int m1404b2 = RadialProgressWidget.m1404b(audioEffectParam.m4439b());
            if (mTreble != m1404b2) {
                mTreble = m1404b2;
                this.mButtonTreble.setCurrentValue(m1404b2);
                this.mTrebleText.setText(String.valueOf(m1404b2));
            }
            int m1404b3 = RadialProgressWidget.m1404b(audioEffectParam.m4433c());
            if (mVirtualizer != m1404b3) {
                mVirtualizer = m1404b3;
                this.mButtonVirtualizer.setCurrentValue(m1404b3);
                this.mVirtualizerText.setText(String.valueOf(m1404b3));
            }
            boolean m4423f = audioEffectParam.m4423f();
            boolean isChecked = this.mImageLimit.isChecked();
            if (this.mLimit != m4423f || m4423f != isChecked) {
                this.mLimit = m4423f;
                this.mImageLimit.setChecked(m4423f);
                Preferences.m3073D(m4423f);
            }
            float m4426e = audioEffectParam.m4426e();
            if (this.mBalance != m4426e) {
                this.mBalance = m4426e;
                this.mSeekBarTreble.setProgress(Math.round((m4426e + 1.0f) * 5.0f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVirtualizerValue(int i) {
        if (!this.mBoostAjust) {
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_BOOST_VIRTUALIZER, SPage.PAGE_AUDIO_BOOST, SPage.PAGE_NONE);
            //AudioEffectStatistic.m5255q();
            this.mBoostAjust = true;
        }
        if (mVirtualizerQueue.size() > 0) {
            mHandler.removeCallbacks(mVirtualizerQueue.remove(0));
        }
        this.mBoostCommandRunable = new RunnableC1387a(CommandID.SET_VIRTUALIZER, i);
        mVirtualizerQueue.add(this.mBoostCommandRunable);
        this.mVirtualizerText.setText(String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrebleValue(int i) {
        if (!this.mBoostAjust) {
            //AudioEffectStatistic.m5256p();
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_BOOST_TREBLE, SPage.PAGE_AUDIO_BOOST, SPage.PAGE_NONE);
            this.mBoostAjust = true;
        }
        if (mTrebleQueue.size() > 0) {
            mHandler.removeCallbacks(mTrebleQueue.remove(0));
        }
        this.mBoostCommandRunable = new RunnableC1387a(CommandID.SET_TREBLEBOOST, i);
        mTrebleQueue.add(this.mBoostCommandRunable);
        this.mTrebleText.setText(String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBassValue(int i) {
        if (!this.mBoostAjust) {
            //AudioEffectStatistic.m5253s();
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_BOOST_BASS, SPage.PAGE_AUDIO_BOOST, SPage.PAGE_NONE);
            this.mBoostAjust = true;
        }
        if (mBassQueue.size() > 0) {
            mHandler.removeCallbacks(mBassQueue.remove(0));
        }
        this.mBoostCommandRunable = new RunnableC1387a(CommandID.SET_BASSBOOST, i);
        mBassQueue.add(this.mBoostCommandRunable);
        this.mBassText.setText(String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeEffectIsEdit() {
        if (!this.mIsEdit) {
            this.mIsEdit = true;
            Preferences.m3075C(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void judgeClickEffectBoostChanBalance() {
        if (!this.mBoostAjust) {
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_BOOST_CHANNEL_BALANCE, SPage.PAGE_AUDIO_BOOST, SPage.PAGE_NONE);
            this.mBoostAjust = true;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (!z) {
            this.mBoostAjust = z;
        }
    }
}
