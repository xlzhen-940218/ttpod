package com.sds.android.ttpod.activities.audioeffect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
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
public class CustomEqualizerActivity extends ActionBarActivity implements ThemeManager.InterfaceC2019b {
    private static final int COUNT_EQU_BAND = 10;
    private static final int FACTOR = 100;
    private static final String GENRE_CUSTOM = "自定义/custom";
    public static final String KEY_CUSTOM_EQUALIZER = "KEY_CUSTOM_EQUALIZER";
    private static final int KNOB_HEIGHT = 42;
    private static final int KNOB_WIDTH = 26;
    private static final int SEEKBAR_MAX_VALUE = 30;
    private short[] mCustomData;
    private View mDefaultView;
    private TextView mEqualizerStyeName;
    private View mResetView;
    private View mSaveView;
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
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.audioeffect.CustomEqualizerActivity.1

        /* renamed from: b */
        private EditTextDialog f2578b = null;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_effect_custom_default /* 2131231137 */:
                    CustomEqualizerActivity.this.startActivity(new Intent(CustomEqualizerActivity.this, EqualizerFragmentActivity.class));
                    //AudioEffectStatistic.m5267e();
                    //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_DEFAULT, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                    return;
                case R.id.textview_effect_custom_save /* 2131231138 */:
                    m8149a();
                    //AudioEffectStatistic.m5262j();
                    //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_NEW, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                    return;
                case R.id.textview_effect_custom_reset /* 2131231139 */:
                    Arrays.fill(CustomEqualizerActivity.this.mCustomData, (short) 0);
                    CustomEqualizerActivity.this.setEqualizer();
                    CustomEqualizerActivity.this.updateView();
                    CustomEqualizerActivity.this.mScrollView.scrollTo(0, 0);
                    //AudioEffectStatistic.m5260l();
                    //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_RESET, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        private void m8149a() {
            this.f2578b = new EditTextDialog(CustomEqualizerActivity.this, new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, "", CustomEqualizerActivity.this.getNewCustomEqualizerName(), CustomEqualizerActivity.this.getString(R.string.effect_custom_equalizer_input_name))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.activities.audioeffect.CustomEqualizerActivity.1.1
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog) {
                    EditTextDialog.C1144a m6902c;
                    String str = "";
                    if (editTextDialog != null && (m6902c = editTextDialog.m6902c(1)) != null) {
                        str = m6902c.m6896d().toString();
                    }
                    if (!StringUtils.isEmpty(str)) {
                        String validateFileName = CustomEqualizerActivity.validateFileName(str);
                        if (!validateFileName.equals(str)) {
                            f2578b.m7242f(false);
                            PopupsUtils.m6760a((int) R.string.effect_custom_equalizer_input_name_invalid);
                            return;
                        }
                        f2578b.m7242f(true);
                        TTEqualizer.Settings settings = new TTEqualizer.Settings(validateFileName, (short) CustomEqualizerActivity.this.mCustomData.length, CustomEqualizerActivity.this.mCustomData);
                        CustomEqualizerActivity.this.mCustomEqualizerMap.put(str, settings);
                        CommandCenter.getInstance().execute(new Command(CommandID.SAVE_CUSTOM_EQUALIZER, settings));
                        PopupsUtils.m6760a((int) R.string.save_successfully);
                        //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_NEW_OK, SPage.PAGE_AUDIO_EQUALIZER, SPage.PAGE_NONE);
                        //AudioEffectStatistic.m5261k();
                        return;
                    }
                    f2578b.m7242f(false);
                    PopupsUtils.m6760a((int) R.string.effect_custom_equalizer_input_name);
                }
            }, null);
            this.f2578b.setTitle(R.string.save);
            this.f2578b.show();
        }
    };
    private TTSeekBar.InterfaceC2262a mProgressEvent = new TTSeekBar.InterfaceC2262a() { // from class: com.sds.android.ttpod.activities.audioeffect.CustomEqualizerActivity.2

        /* renamed from: b */
        private int f2581b;

        @Override // com.sds.android.ttpod.widget.audioeffect.TTSeekBar.InterfaceC2262a
        /* renamed from: a */
        public void mo1368a(TTSeekBar tTSeekBar, int i) {
            int i2 = 0;
            while (true) {
                if (i2 >= 10) {
                    break;
                } else if (CustomEqualizerActivity.this.mSeekBars[i2] != tTSeekBar) {
                    i2++;
                } else {
                    this.f2581b = i2;
                    CustomEqualizerActivity.this.mSeekBarIndicators[i2].setPressed(true);
                    break;
                }
            }
            CustomEqualizerActivity.this.mWaveView.mo1411a(this.f2581b, CustomEqualizerActivity.this.mCustomData[this.f2581b]);
        }

        @Override // com.sds.android.ttpod.widget.audioeffect.TTSeekBar.InterfaceC2262a
        /* renamed from: b */
        public void mo1367b(TTSeekBar tTSeekBar, int i) {
            CustomEqualizerActivity.this.mCustomData[this.f2581b] = (short) ((15 - i) * 100);
            CustomEqualizerActivity.this.mWaveView.mo1411a(this.f2581b, CustomEqualizerActivity.this.mCustomData[this.f2581b]);
            Preferences.m3075C(true);
            CustomEqualizerActivity.this.setEqualizer();
            ((Vibrator) CustomEqualizerActivity.this.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(20L);
        }

        @Override // com.sds.android.ttpod.widget.audioeffect.TTSeekBar.InterfaceC2262a
        /* renamed from: c */
        public void mo1366c(TTSeekBar tTSeekBar, int i) {
            CustomEqualizerActivity.this.mWaveView.invalidate();
            CustomEqualizerActivity.this.mSeekBarIndicators[this.f2581b].setPressed(false);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static String validateFileName(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", "");
    }

    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.effect_equalizer);
        setContentView(R.layout.activity_custom_equalizer);
        TTEqualizer.Settings settings = new TTEqualizer.Settings(getIntent().getStringExtra(KEY_CUSTOM_EQUALIZER));
        this.mCustomData = settings.getBandLevels();
        CommandCenter.getInstance().execute(new Command(CommandID.QUERY_CUSTOM_EQUALIZER_LIST, new Object[0]));
        initContentViews();
        updateView();
        int color = getResources().getColor(R.color.effect_dialog_background);
        View findViewById = findViewById(R.id.layout_root);
        View findViewById2 = findViewById(R.id.layout_equ_edit);
        findViewById.setBackgroundColor(color);
        getRootView().setBackgroundColor(color);
        ActionBarUtils.m8130a(getActionBarController());
        findViewById2.setBackgroundColor(color);
        this.mEqualizerStyeName = (TextView) findViewById(R.id.textview_effect_equalizer);
        this.mEqualizerStyeName.setText(settings.getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_CUSTOM_EQUALIZER_LIST, ReflectUtils.loadMethod(cls, "updateCustomEqualizerList", List.class));
        map.put(CommandID.UPDATE_AUDIO_EFFECT_INFO, ReflectUtils.loadMethod(cls, "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        AudioEffectParam m2457s;
        if (!isFinishing() && (m2457s = SupportFactory.getInstance(BaseApplication.getApplication()).m2457s()) != null) {
            TTEqualizer.Settings settings = new TTEqualizer.Settings(m2457s.m4421g());
            this.mCustomData = settings.getBandLevels();
            updateView();
            this.mEqualizerStyeName.setText(settings.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        updateAudioEffectInfo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Preferences.m2864k(new TTEqualizer.Settings("自定义/custom", (short) this.mCustomData.length, this.mCustomData).toString());
    }

    public void updateCustomEqualizerList(List<TTEqualizer.Settings> list) {
        if (!isFinishing() && list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                this.mCustomEqualizerMap.put(list.get(i).getName(), list.get(i));
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            computeViewWidths();
        }
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

    private void computeViewWidths() {
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
        this.mWaveView = (EqualizerAnimationWaveView) findViewById(R.id.equ_wave_custom);
        this.mWaveView.setWaveShadowColor(-66);
        this.mWaveView.setWaveShadowRadius(10.0f);
        this.mWaveView.setWaveColor(-9766146);
        this.mWaveView.setWaveStrokeWidth(2);
        this.mWaveView.setCoordinateVisible(true);
    }

    private void initEditView() {
        View findViewById = findViewById(R.id.layout_equ_edit);
        this.mSaveView = findViewById.findViewById(R.id.textview_effect_custom_save);
        this.mSaveView.setOnClickListener(this.mOnClickListener);
        this.mResetView = findViewById.findViewById(R.id.textview_effect_custom_reset);
        this.mResetView.setOnClickListener(this.mOnClickListener);
        this.mDefaultView = findViewById.findViewById(R.id.textview_effect_custom_default);
        this.mDefaultView.setOnClickListener(this.mOnClickListener);
        initSeekScrollView();
        ((LinearLayout) findViewById.findViewById(R.id.layout_scroll_frame)).addView(this.mScrollView);
    }

    private void initScrollBlock() {
        this.mViewScrollBlock = findViewById(R.id.view_scroll_block);
        this.mViewScrollBlock.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.activities.audioeffect.CustomEqualizerActivity.3

            /* renamed from: b */
            private float f2583b = 0.0f;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float x = motionEvent.getX();
                switch (action) {
                    case 0:
                        this.f2583b = x;
                        return true;
                    case 1:
                    case 3:
                        this.f2583b = 0.0f;
                        return true;
                    case 2:
                        m8146a(x - this.f2583b);
                        return true;
                    default:
                        return false;
                }
            }

            /* renamed from: a */
            private void m8146a(float f) {
                CustomEqualizerActivity.this.mScrollView.scrollBy((int) (CustomEqualizerActivity.this.mWidthScrollContent * (f / CustomEqualizerActivity.this.mWidthWaveView)), 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEqualizer() {
        CommandCenter.getInstance().postInvokeResult(new Command(CommandID.SET_EQUALIZER, new TTEqualizer.Settings("自定义/custom", (short) this.mCustomData.length, this.mCustomData)));
        this.mEqualizerStyeName.setText("自定义/custom");
    }

    private void initSeekScrollView() {
        initScrollContentView();
        this.mScrollView = new HorizontalScrollView(this) { // from class: com.sds.android.ttpod.activities.audioeffect.CustomEqualizerActivity.4

            /* renamed from: b */
            private int[] f2585b = {0, 0};

            @Override // android.view.View
            protected void onScrollChanged(int i, int i2, int i3, int i4) {
                if (this.f2585b[0] != i || this.f2585b[1] != i3) {
                    this.f2585b[0] = i;
                    this.f2585b[1] = i3;
                    if (i != 0) {
                        if (i == CustomEqualizerActivity.this.mWidthScrollContent - CustomEqualizerActivity.this.mWidthScrollView) {
                            m8145a(100);
                            return;
                        } else if (i != i3) {
                            m8144b((int) (((i - i3) / CustomEqualizerActivity.this.mWidthScrollContent) * CustomEqualizerActivity.this.mWidthWaveView));
                            return;
                        } else {
                            return;
                        }
                    }
                    m8145a(0);
                }
            }

            /* renamed from: a */
            private void m8145a(int i) {
                int i2;
                int i3 = 0;
                if (i == 0) {
                    i2 = CustomEqualizerActivity.this.mWaveView.getPaddingLeft();
                    i3 = CustomEqualizerActivity.this.mWidthScrollBlock + i2;
                } else if (i == 100) {
                    i3 = CustomEqualizerActivity.this.mWaveView.getWidth() - CustomEqualizerActivity.this.mWaveView.getPaddingRight();
                    i2 = i3 - CustomEqualizerActivity.this.mWidthScrollBlock;
                } else {
                    i2 = 0;
                }
                CustomEqualizerActivity.this.mViewScrollBlock.layout(i2, CustomEqualizerActivity.this.mViewScrollBlock.getTop(), i3, CustomEqualizerActivity.this.mViewScrollBlock.getBottom());
            }

            /* renamed from: b */
            private void m8144b(int i) {
                int left = CustomEqualizerActivity.this.mViewScrollBlock.getLeft() + i;
                int right = CustomEqualizerActivity.this.mViewScrollBlock.getRight() + i;
                int paddingLeft = CustomEqualizerActivity.this.mWaveView.getPaddingLeft();
                if (left < paddingLeft) {
                    right = CustomEqualizerActivity.this.mWidthScrollBlock + paddingLeft;
                } else {
                    paddingLeft = left;
                }
                int paddingRight = CustomEqualizerActivity.this.mWaveView.getPaddingRight();
                if (right > CustomEqualizerActivity.this.mWidthWaveView - paddingRight) {
                    right = CustomEqualizerActivity.this.mWidthWaveView - paddingRight;
                    paddingLeft = right - CustomEqualizerActivity.this.mWidthScrollBlock;
                }
                CustomEqualizerActivity.this.mViewScrollBlock.layout(paddingLeft, CustomEqualizerActivity.this.mViewScrollBlock.getTop(), right, CustomEqualizerActivity.this.mViewScrollBlock.getBottom());
            }
        };
        this.mScrollView.addView(this.mViewScrollContent);
    }

    private void initScrollContentView() {
        this.mViewScrollContent = new LinearLayout(this);
        this.mViewScrollContent.setOrientation(LinearLayout.HORIZONTAL);
        this.mViewScrollContent.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        this.mSeekBars = new TTSeekBar[10];
        this.mSeekBarIndicators = new ImageView[10];
        int i = (int) (getResources().getDisplayMetrics().density * 53.0f);
        int i2 = 9;
        int i3 = 16000;
        while (i2 >= 0) {
            View inflate = View.inflate(this, R.layout.audio_effect_equalizer_seekbar, null);
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

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
    }
}
