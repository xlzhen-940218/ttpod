package com.sds.android.ttpod.fragment.audioeffect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.audioeffect.EqualizerFragmentActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.EqualizerPreset;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.sds.android.ttpod.widget.audioeffect.TTSeekBar;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CustomEqualizerFragment extends BaseFragment implements ThemeManager.InterfaceC2019b {
    private static final int COUNT_EQU_BAND = 10;
    private static final TTEqualizer.Settings DEFAULT_SETTINGS = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
    private static final int FACTOR = 100;
    private static final String GENRE_CUSTOM = "自定义/custom";
    private static final String GENRE_NORMAL = "普通/Normal";
    private static final int KNOB_HEIGHT = 42;
    private static final int KNOB_WIDTH = 26;
    private static final int SEEKBAR_MAX_VALUE = 30;
    private short[] mCustomData;
    private View mDefaultView;
    private String mEqualizerStyeName;
    private TextView mEqualizerStyeTextView;
    private View mFragmentView;
    private View mResetView;
    private View mSaveView;
    private LinearLayout mScrollFrame;
    private HorizontalScrollView mScrollView;
    private ImageView[] mSeekBarIndicators;
    private TTSeekBar[] mSeekBars;
    private View mViewScrollBlock;
    private LinearLayout mViewScrollContent;
    private EqualizerAnimationWaveView mWaveView;
    private int mWidthScrollBlock;
    private int mWidthScrollContent;
    private int mWidthScrollView;
    private int mWidthWaveView;
    private Map<String, TTEqualizer.Settings> mCustomEqualizerMap = new HashMap();
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.CustomEqualizerFragment.1

        /* renamed from: b */
        private EditTextDialog f4953b = null;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_effect_custom_default /* 2131231137 */:
                    CustomEqualizerFragment.this.startActivity(new Intent(CustomEqualizerFragment.this.getActivity().getApplicationContext(), EqualizerFragmentActivity.class));
                    //AudioEffectStatistic.m5267e();
                    //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_DEFAULT, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                    return;
                case R.id.textview_effect_custom_save /* 2131231138 */:
                    m5756a();
                    //AudioEffectStatistic.m5262j();
                    //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_NEW, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                    return;
                case R.id.textview_effect_custom_reset /* 2131231139 */:
                    CustomEqualizerFragment.this.resetEqualizer();
                    CustomEqualizerFragment.this.updateView();
                    CustomEqualizerFragment.this.mScrollView.scrollTo(0, 0);
                    //AudioEffectStatistic.m5260l();
                    //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_RESET, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        private void m5756a() {
            this.f4953b = new EditTextDialog(CustomEqualizerFragment.this.getActivity(), new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, "", CustomEqualizerFragment.this.getNewCustomEqualizerName(), CustomEqualizerFragment.this.getString(R.string.effect_custom_equalizer_input_name))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.audioeffect.CustomEqualizerFragment.1.1
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog) {
                    EditTextDialog.C1144a m6902c;
                    String str = "";
                    if (editTextDialog != null && (m6902c = editTextDialog.m6902c(1)) != null) {
                        str = m6902c.m6896d().toString();
                    }
                    if (!StringUtils.isEmpty(str)) {
                        String validateFileName = CustomEqualizerFragment.validateFileName(str);
                        if (!validateFileName.equals(str)) {
                            f4953b.m7242f(false);
                            PopupsUtils.m6760a((int) R.string.effect_custom_equalizer_input_name_invalid);
                            return;
                        }
                        f4953b.m7242f(true);
                        TTEqualizer.Settings settings = new TTEqualizer.Settings(validateFileName, (short) CustomEqualizerFragment.this.mCustomData.length, CustomEqualizerFragment.this.mCustomData);
                        CustomEqualizerFragment.this.mCustomEqualizerMap.put(str, settings);
                        CommandCenter.getInstance().execute(new Command(CommandID.SAVE_CUSTOM_EQUALIZER, settings));
                        PopupsUtils.m6760a((int) R.string.save_successfully);
                        //AudioEffectStatistic.m5261k();
                        //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_NEW_OK, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                        return;
                    }
                   f4953b.m7242f(false);
                    PopupsUtils.m6760a((int) R.string.effect_custom_equalizer_input_name);
                }
            }, null);
            this.f4953b.setTitle(R.string.save);
            this.f4953b.show();
        }
    };
    private TTSeekBar.InterfaceC2262a mProgressEvent = new TTSeekBar.InterfaceC2262a() { // from class: com.sds.android.ttpod.fragment.audioeffect.CustomEqualizerFragment.2

        /* renamed from: b */
        private int f4956b;

        @Override // com.sds.android.ttpod.widget.audioeffect.TTSeekBar.InterfaceC2262a
        /* renamed from: a */
        public void mo1368a(TTSeekBar tTSeekBar, int i) {
            int i2 = 0;
            while (true) {
                if (i2 >= 10) {
                    break;
                } else if (CustomEqualizerFragment.this.mSeekBars[i2] != tTSeekBar) {
                    i2++;
                } else {
                    this.f4956b = i2;
                    CustomEqualizerFragment.this.mSeekBarIndicators[i2].setPressed(true);
                    break;
                }
            }
            CustomEqualizerFragment.this.mWaveView.mo1411a(this.f4956b, CustomEqualizerFragment.this.mCustomData[this.f4956b]);
        }

        @Override // com.sds.android.ttpod.widget.audioeffect.TTSeekBar.InterfaceC2262a
        /* renamed from: b */
        public void mo1367b(TTSeekBar tTSeekBar, int i) {
            CustomEqualizerFragment.this.mCustomData[this.f4956b] = (short) ((15 - i) * 100);
            CustomEqualizerFragment.this.mWaveView.mo1411a(this.f4956b, CustomEqualizerFragment.this.mCustomData[this.f4956b]);
            Preferences.m3075C(true);
            CustomEqualizerFragment.this.setEqualizer("自定义/custom");
            ((Vibrator) CustomEqualizerFragment.this.getActivity().getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20L);
        }

        @Override // com.sds.android.ttpod.widget.audioeffect.TTSeekBar.InterfaceC2262a
        /* renamed from: c */
        public void mo1366c(TTSeekBar tTSeekBar, int i) {
            CustomEqualizerFragment.this.mWaveView.invalidate();
            CustomEqualizerFragment.this.mSeekBarIndicators[this.f4956b].setPressed(false);
        }
    };

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        super.onThemeChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String validateFileName(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", "");
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TTEqualizer.Settings settings;
        if (this.mFragmentView == null) {
            this.mFragmentView = layoutInflater.inflate(R.layout.activity_custom_equalizer, (ViewGroup) null);
            this.mEqualizerStyeTextView = (TextView) this.mFragmentView.findViewById(R.id.textview_effect_equalizer);
            AudioEffectParam m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s();
            if (m2457s != null) {
                settings = new TTEqualizer.Settings(m2457s.m4421g());
            } else {
                settings = new TTEqualizer.Settings(EqualizerPreset.m4334b(), (short) 10, EqualizerPreset.m4333b(EqualizerPreset.m4334b()));
            }
            this.mCustomData = settings.getBandLevels();
            this.mEqualizerStyeName = settings.getName();
            this.mEqualizerStyeTextView.setText(this.mEqualizerStyeName);
            CommandCenter.getInstance().execute(new Command(CommandID.QUERY_CUSTOM_EQUALIZER_LIST, new Object[0]));
            initContentViews();
            updateView();
        }
        return this.mFragmentView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_CUSTOM_EQUALIZER_LIST, ReflectUtils.m8375a(cls, "updateCustomEqualizerList", List.class));
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.m8375a(cls, "updateAudioEffectInfo", new Class[0]));
        map.put(CommandID.UPDATE_MANUAL_SETTING_EFFECT, ReflectUtils.m8375a(cls, "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        AudioEffectParam m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s();
        if (m2457s != null) {
            TTEqualizer.Settings settings = new TTEqualizer.Settings(m2457s.m4421g());
            short[] bandLevels = settings.getBandLevels();
            if (!Arrays.equals(bandLevels, this.mCustomData)) {
                this.mCustomData = bandLevels;
                updateView();
            }
            String name = settings.getName();
            if (name != null && !name.equals(this.mEqualizerStyeName)) {
                this.mEqualizerStyeName = name;
                this.mEqualizerStyeTextView.setText(settings.getName());
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        Preferences.m2864k(new TTEqualizer.Settings("自定义/custom", (short) this.mCustomData.length, this.mCustomData).toString());
    }

    public void updateCustomEqualizerList(List<TTEqualizer.Settings> list) {
        if (!getActivity().isFinishing() && list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this.mCustomEqualizerMap.put(list.get(i).getName(), list.get(i));
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getNewCustomEqualizerName() {
        String string = getString(R.string.my_preset);
        int i = 1;
        String str = string;
        while (this.mCustomEqualizerMap.containsKey(str)) {
            str = string + i;
            i++;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void computeViewWidths() {
        this.mWidthScrollContent = this.mViewScrollContent.getWidth();
        this.mWidthScrollView = this.mScrollView.getWidth();
        float f = this.mWidthScrollView / this.mWidthScrollContent;
        this.mWidthWaveView = this.mWaveView.getWidth();
        this.mWidthScrollBlock = (int) (f * ((this.mWidthWaveView - this.mWaveView.getPaddingLeft()) - this.mWaveView.getPaddingRight()));
        ViewGroup.LayoutParams layoutParams = this.mViewScrollBlock.getLayoutParams();
        if (layoutParams.width != this.mWidthScrollBlock) {
            layoutParams.width = this.mWidthScrollBlock;
            this.mViewScrollBlock.setLayoutParams(layoutParams);
        }
    }

    private void initContentViews() {
        initWaveView();
        initEditView();
        initScrollBlock();
    }

    private void initWaveView() {
        this.mWaveView = (EqualizerAnimationWaveView) this.mFragmentView.findViewById(R.id.equ_wave_custom);
        this.mWaveView.setWaveShadowColor(-66);
        this.mWaveView.setWaveShadowRadius(10.0f);
        this.mWaveView.setWaveColor(-9766146);
        this.mWaveView.setWaveStrokeWidth(2);
        this.mWaveView.setCoordinateVisible(true);
    }

    private void initEditView() {
        View findViewById = this.mFragmentView.findViewById(R.id.layout_equ_edit);
        this.mSaveView = findViewById.findViewById(R.id.textview_effect_custom_save);
        this.mSaveView.setOnClickListener(this.mOnClickListener);
        this.mResetView = findViewById.findViewById(R.id.textview_effect_custom_reset);
        this.mResetView.setOnClickListener(this.mOnClickListener);
        this.mDefaultView = findViewById.findViewById(R.id.textview_effect_custom_default);
        this.mDefaultView.setOnClickListener(this.mOnClickListener);
        this.mScrollFrame = (LinearLayout) findViewById.findViewById(R.id.layout_scroll_frame);
        initSeekScrollView();
        this.mScrollFrame.addView(this.mScrollView);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        this.mScrollFrame.removeView(this.mScrollView);
        super.onDestroyView();
    }

    private void initScrollBlock() {
        this.mViewScrollBlock = this.mFragmentView.findViewById(R.id.view_scroll_block);
        this.mViewScrollBlock.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.CustomEqualizerFragment.3

            /* renamed from: b */
            private float f4958b = 0.0f;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float x = motionEvent.getX();
                switch (action) {
                    case 0:
                        ViewParent parent = CustomEqualizerFragment.this.mViewScrollBlock.getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f4958b = x;
                        return true;
                    case 1:
                    case 3:
                        this.f4958b = 0.0f;
                        return true;
                    case 2:
                        m5753a(x - this.f4958b);
                        return true;
                    default:
                        return false;
                }
            }

            /* renamed from: a */
            private void m5753a(float f) {
                CustomEqualizerFragment.this.mScrollView.scrollBy((int) (CustomEqualizerFragment.this.mWidthScrollContent * (f / CustomEqualizerFragment.this.mWidthWaveView)), 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetEqualizer() {
        Arrays.fill(this.mCustomData, (short) 0);
        setEqualizer(GENRE_NORMAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEqualizer(String str) {
        CommandCenter.getInstance().m4596b(new Command(CommandID.SET_EQUALIZER, new TTEqualizer.Settings(str, (short) this.mCustomData.length, this.mCustomData)));
        this.mEqualizerStyeTextView.setText(str);
    }

    private void initSeekScrollView() {
        initScrollContentView();
        this.mScrollView = new HorizontalScrollView(getActivity().getApplicationContext()) { // from class: com.sds.android.ttpod.fragment.audioeffect.CustomEqualizerFragment.4

            /* renamed from: b */
            private int[] f4960b = {0, 0};

            @Override // android.view.View
            protected void onScrollChanged(int i, int i2, int i3, int i4) {
                if (this.f4960b[0] != i || this.f4960b[1] != i3) {
                    this.f4960b[0] = i;
                    this.f4960b[1] = i3;
                    if (i != 0) {
                        if (i == CustomEqualizerFragment.this.mWidthScrollContent - CustomEqualizerFragment.this.mWidthScrollView) {
                            m5752a(100);
                            return;
                        } else if (i != i3) {
                            m5751b((int) (((i - i3) / CustomEqualizerFragment.this.mWidthScrollContent) * CustomEqualizerFragment.this.mWidthWaveView));
                            return;
                        } else {
                            return;
                        }
                    }
                    m5752a(0);
                }
            }

            /* renamed from: a */
            private void m5752a(int i) {
                int i2;
                int i3 = 0;
                if (i == 0) {
                    i2 = CustomEqualizerFragment.this.mWaveView.getPaddingLeft();
                    i3 = CustomEqualizerFragment.this.mWidthScrollBlock + i2;
                } else if (i == 100) {
                    i3 = CustomEqualizerFragment.this.mWaveView.getWidth() - CustomEqualizerFragment.this.mWaveView.getPaddingRight();
                    i2 = i3 - CustomEqualizerFragment.this.mWidthScrollBlock;
                } else {
                    i2 = 0;
                }
                CustomEqualizerFragment.this.mViewScrollBlock.layout(i2, CustomEqualizerFragment.this.mViewScrollBlock.getTop(), i3, CustomEqualizerFragment.this.mViewScrollBlock.getBottom());
            }

            /* renamed from: b */
            private void m5751b(int i) {
                int left = CustomEqualizerFragment.this.mViewScrollBlock.getLeft() + i;
                int right = CustomEqualizerFragment.this.mViewScrollBlock.getRight() + i;
                int paddingLeft = CustomEqualizerFragment.this.mWaveView.getPaddingLeft();
                if (left < paddingLeft) {
                    right = CustomEqualizerFragment.this.mWidthScrollBlock + paddingLeft;
                } else {
                    paddingLeft = left;
                }
                int paddingRight = CustomEqualizerFragment.this.mWaveView.getPaddingRight();
                if (right > CustomEqualizerFragment.this.mWidthWaveView - paddingRight) {
                    right = CustomEqualizerFragment.this.mWidthWaveView - paddingRight;
                    paddingLeft = right - CustomEqualizerFragment.this.mWidthScrollBlock;
                }
                CustomEqualizerFragment.this.mViewScrollBlock.layout(paddingLeft, CustomEqualizerFragment.this.mViewScrollBlock.getTop(), right, CustomEqualizerFragment.this.mViewScrollBlock.getBottom());
            }

            @Override // android.view.View
            public void onWindowFocusChanged(boolean z) {
                super.onWindowFocusChanged(z);
                if (z) {
                    CustomEqualizerFragment.this.computeViewWidths();
                }
            }
        };
        this.mScrollView.addView(this.mViewScrollContent);
    }

    private void initScrollContentView() {
        this.mViewScrollContent = new LinearLayout(getActivity().getApplicationContext());
        this.mViewScrollContent.setOrientation(LinearLayout.HORIZONTAL);
        this.mViewScrollContent.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.mSeekBars = new TTSeekBar[10];
        this.mSeekBarIndicators = new ImageView[10];
        int i = (int) (getResources().getDisplayMetrics().density * 53.0f);
        int i2 = 9;
        int i3 = 16000;
        while (i2 >= 0) {
            View inflate = View.inflate(getActivity().getApplicationContext(), R.layout.audio_effect_equalizer_seekbar, null);
            this.mViewScrollContent.addView(inflate, 0, new ViewGroup.LayoutParams(i, -1));
            this.mSeekBars[i2] = (TTSeekBar) inflate.findViewById(R.id.ttseekbar_effect_custom_equalizer);
            this.mSeekBars[i2].setKnob(R.drawable.xml_background_ttseekbar_knob);
            this.mSeekBars[i2].m1371a(26, KNOB_HEIGHT);
            this.mSeekBars[i2].setOffset(25);
            this.mSeekBars[i2].setProgressMax(30);
            this.mSeekBars[i2].setProgressEvent(this.mProgressEvent);
            this.mSeekBarIndicators[i2] = (ImageView) inflate.findViewById(R.id.ttseekbar_effect_indicator);
            ((TextView) inflate.findViewById(R.id.textview_effect_equalizer_seekbar_title)).setText(i3 >= 1000 ? String.valueOf(i3 / 1000) + 'k' : String.valueOf(i3));
            i2--;
            i3 /= 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateView() {
        this.mWaveView.setWaveValue(this.mCustomData);
        for (int i = 0; i < 10; i++) {
            this.mSeekBars[i].setProgress(15 - (this.mCustomData[i] / 100));
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
    }
}
