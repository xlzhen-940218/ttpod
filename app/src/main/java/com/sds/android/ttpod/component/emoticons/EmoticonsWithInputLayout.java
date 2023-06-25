package com.sds.android.ttpod.component.emoticons;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.SensitiveWordUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;

/* loaded from: classes.dex */
public class EmoticonsWithInputLayout extends LinearLayout {

    /* renamed from: a */
    private InputMethodManager f4094a;

    /* renamed from: b */
    private EmoticonsLayout f4095b;

    /* renamed from: c */
    private IconTextView f4096c;

    /* renamed from: d */
    private View f4097d;

    /* renamed from: e */
    private EditText f4098e;

    /* renamed from: f */
    private View f4099f;

    /* renamed from: g */
    private IconTextView f4100g;

    /* renamed from: h */
    private View f4101h;

    /* renamed from: i */
    private View f4102i;

    /* renamed from: j */
    private SlidingClosableRelativeLayout f4103j;

    /* renamed from: k */
    private boolean f4104k;

    /* renamed from: l */
    private boolean f4105l;

    /* renamed from: m */
    private String f4106m;

    /* renamed from: n */
    private TextWatcher f4107n;

    /* renamed from: com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1207a {
        void onSend(String str);
    }

    public EmoticonsWithInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4107n = new TextWatcher() { // from class: com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.5

            /* renamed from: b */
            private int f4114b;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!StringUtils.isEmpty(EmoticonsWithInputLayout.this.f4106m) && charSequence.toString().contains(EmoticonsWithInputLayout.this.f4106m) && i < EmoticonsWithInputLayout.this.f4106m.length()) {
                    if (i + i2 < EmoticonsWithInputLayout.this.f4106m.length()) {
                        this.f4114b = (EmoticonsWithInputLayout.this.f4106m.length() - i2) + i3;
                    } else {
                        this.f4114b = i;
                    }
                    EmoticonsWithInputLayout.this.f4106m = null;
                    return;
                }
                this.f4114b = 0;
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                try {
                    if (this.f4114b > 0) {
                        editable.delete(0, this.f4114b);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void setLayoutBottomBackgroundColor(int i) {
        this.f4102i.setBackgroundColor(i);
    }

    /* renamed from: a */
    public void m6664a(SlidingClosableRelativeLayout slidingClosableRelativeLayout, View view, final InterfaceC1207a interfaceC1207a) {
        this.f4104k = true;
        this.f4094a = (InputMethodManager) getContext().getSystemService("input_method");
        this.f4099f = findViewById(R.id.layout_send);
        this.f4100g = (IconTextView) findViewById(R.id.tv_send);
        this.f4101h = findViewById(R.id.iv_circle);
        this.f4102i = findViewById(R.id.layout_bottom);
        setSendEnable(true);
        this.f4103j = slidingClosableRelativeLayout;
        this.f4098e = (EditText) findViewById(R.id.et_comment);
        this.f4096c = (IconTextView) findViewById(R.id.btn_emoctions);
        this.f4095b = (EmoticonsLayout) findViewById(R.id.layout_emoticons);
        this.f4095b.setInputEditText(this.f4098e);
        this.f4095b.m6676a(this.f4103j);
        this.f4099f.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                String trim = EmoticonsWithInputLayout.this.f4098e.getText().toString().trim();
                if (Preferences.m2954aq() == null) {
                    EmoticonsWithInputLayout.this.m6663b();
                }
                String substring = (StringUtils.isEmpty(EmoticonsWithInputLayout.this.getReplyTo()) || StringUtils.isEmpty(trim)) ? trim : trim.substring(EmoticonsWithInputLayout.this.getReplyTo().length());
                int length = substring != null ? substring.length() : 0;
                if (length == 0) {
                    PopupsUtils.m6721a("请输入有效评论");
                } else if (length > 200) {
                    PopupsUtils.m6721a(String.format("评论最多%d字", 200));
                } else if (SensitiveWordUtils.m8243a(EmoticonsWithInputLayout.this.getContext()).m8242a(substring)) {
                    PopupsUtils.m6721a("内容含有敏感词，提交失败");
                } else {
                    EmoticonsWithInputLayout.this.setSendEnable(false);
                    interfaceC1207a.onSend(substring);
                }
            }
        });
        this.f4096c.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EmoticonsWithInputLayout.this.f4104k = false;
                if (EmoticonsWithInputLayout.this.f4095b.getVisibility() == 8) {
                    EmoticonsWithInputLayout.this.f4096c.setText(R.string.icon_keyboard);
                    EmoticonsWithInputLayout.this.f4095b.setVisibility(View.VISIBLE);
                    EmoticonsWithInputLayout.this.f4094a.hideSoftInputFromWindow(EmoticonsWithInputLayout.this.f4098e.getWindowToken(), 0);
                    if (!EmoticonsWithInputLayout.this.f4105l) {
                        EmoticonsWithInputLayout.this.f4105l = true;
                        EmoticonsWithInputLayout.this.f4103j.m1528a(EmoticonsWithInputLayout.this.f4095b.getRectEmoticons());
                    }
                } else {
                    EmoticonsWithInputLayout.this.f4096c.setText(R.string.icon_emoji);
                    EmoticonsWithInputLayout.this.f4095b.setVisibility(View.GONE);
                    EmoticonsWithInputLayout.this.f4105l = false;
                    EmoticonsWithInputLayout.this.f4103j.m1516b(EmoticonsWithInputLayout.this.f4095b.getRectEmoticons());
                    EmoticonsWithInputLayout.this.f4098e.requestFocus();
                    EmoticonsWithInputLayout.this.f4094a.showSoftInput(EmoticonsWithInputLayout.this.f4098e, 2);
                }
                if (EmoticonsWithInputLayout.this.f4097d != null) {
                    EmoticonsWithInputLayout.this.f4097d.setVisibility(View.VISIBLE);
                }
            }
        });
        this.f4098e.addTextChangedListener(this.f4107n);
        this.f4098e.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                EmoticonsWithInputLayout.this.f4104k = false;
                EmoticonsWithInputLayout.this.f4095b.setVisibility(View.GONE);
                if (EmoticonsWithInputLayout.this.f4097d != null) {
                    EmoticonsWithInputLayout.this.f4097d.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });
        if (view != null) {
            this.f4097d = view;
            this.f4097d.setVisibility(View.GONE);
            this.f4097d.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    EmoticonsWithInputLayout.this.f4097d.setVisibility(View.GONE);
                    EmoticonsWithInputLayout.this.m6663b();
                }
            });
        }
        ThemeManager.m3269a(this.f4102i, ThemeElement.COMMON_BOTTOM_BAR);
        this.f4096c.setText(R.string.icon_emoji);
    }

    public void setSoftInputReplyTo(SpannableString spannableString) {
        this.f4098e.setText(spannableString);
        Selection.setSelection(this.f4098e.getText(), this.f4098e.getText().length());
        this.f4098e.requestFocus();
        this.f4094a.toggleSoftInput(2, 0);
    }

    public void setIsShowCommentAvatarAnimation(boolean z) {
        this.f4104k = z;
    }

    /* renamed from: a */
    public void m6668a() {
        this.f4098e.setText("");
        setSendEnable(true);
        m6663b();
        this.f4106m = null;
    }

    /* renamed from: b */
    public void m6663b() {
        this.f4094a.hideSoftInputFromWindow(this.f4098e.getWindowToken(), 0);
        this.f4095b.setVisibility(View.GONE);
        this.f4096c.setText(R.string.icon_emoji);
    }

    public void setSendEnable(boolean z) {
        this.f4099f.setEnabled(z);
        this.f4101h.setVisibility(z ? View.GONE : View.VISIBLE);
        this.f4100g.setVisibility(z ? View.VISIBLE : View.INVISIBLE);
        if (z) {
            this.f4101h.clearAnimation();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f4101h.getContext(), R.anim.unlimited_rotate);
        this.f4101h.setAnimation(loadAnimation);
        loadAnimation.startNow();
    }

    /* renamed from: c */
    public void m6660c() {
        if (this.f4095b.getVisibility() == View.VISIBLE) {
            this.f4098e.clearFocus();
        }
        if (((Activity) this.f4098e.getContext()).getWindow().getAttributes().softInputMode == 0 && this.f4097d != null) {
            this.f4097d.setVisibility(View.VISIBLE);
        }
    }

    public void setmEmoticonsLayoutVisibility(int i) {
        this.f4095b.setVisibility(i);
        if (this.f4097d != null) {
            this.f4097d.setVisibility(i);
        }
    }

    /* renamed from: d */
    public boolean m6658d() {
        this.f4104k = false;
        this.f4094a.hideSoftInputFromWindow(this.f4098e.getWindowToken(), 0);
        if (this.f4095b.getVisibility() == View.VISIBLE) {
            this.f4105l = false;
            this.f4103j.m1516b(this.f4095b.getRectEmoticons());
            this.f4095b.setVisibility(View.GONE);
            return true;
        }
        return false;
    }

    public String getReplyTo() {
        return this.f4106m;
    }

    public void setReplyTo(String str) {
        this.f4106m = str;
    }

    /* renamed from: e */
    public void m6656e() {
        ThemeManager.m3269a(this, ThemeElement.COMMON_BOTTOM_BAR);
        ThemeUtils.m8173a(this.f4096c, ThemeElement.PLAY_BAR_TEXT);
        ThemeUtils.m8173a(this.f4100g, ThemeElement.PLAY_BAR_TEXT);
    }
}
