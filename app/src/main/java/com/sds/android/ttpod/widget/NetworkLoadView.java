package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;

/* loaded from: classes.dex */
public class NetworkLoadView extends FrameLayout implements ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private View f7744a;

    /* renamed from: b */
    private View f7745b;

    /* renamed from: c */
    private ImageView f7746c;

    /* renamed from: d */
    private TextView f7747d;

    /* renamed from: e */
    private TextView f7748e;

    /* renamed from: f */
    private Animation f7749f;

    /* renamed from: g */
    private InterfaceC2206b f7750g;

    /* renamed from: h */
    private EnumC2205a f7751h;

    /* renamed from: i */
    private int f7752i;

    /* renamed from: j */
    private boolean f7753j;

    /* renamed from: k */
    private boolean f7754k;

    /* renamed from: l */
    private View.OnClickListener f7755l;

    /* renamed from: com.sds.android.ttpod.widget.NetworkLoadView$a */
    /* loaded from: classes.dex */
    public enum EnumC2205a {
        LOADING,
        IDLE,
        FAILED
    }

    /* renamed from: com.sds.android.ttpod.widget.NetworkLoadView$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2206b {
        /* renamed from: a */
        void mo1678a();
    }

    public NetworkLoadView(Context context) {
        super(context);
        this.f7750g = null;
        this.f7751h = EnumC2205a.IDLE;
        this.f7752i = 4;
        this.f7753j = false;
        this.f7754k = false;
        this.f7755l = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.NetworkLoadView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetworkLoadView.this.setLoadState(EnumC2205a.LOADING);
            }
        };
        m1682a(context);
    }

    public NetworkLoadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7750g = null;
        this.f7751h = EnumC2205a.IDLE;
        this.f7752i = 4;
        this.f7753j = false;
        this.f7754k = false;
        this.f7755l = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.NetworkLoadView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetworkLoadView.this.setLoadState(EnumC2205a.LOADING);
            }
        };
        m1682a(context);
    }

    public NetworkLoadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7750g = null;
        this.f7751h = EnumC2205a.IDLE;
        this.f7752i = 4;
        this.f7753j = false;
        this.f7754k = false;
        this.f7755l = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.NetworkLoadView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetworkLoadView.this.setLoadState(EnumC2205a.LOADING);
            }
        };
        m1682a(context);
    }

    /* renamed from: a */
    protected void m1682a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.loading_view_layout, (ViewGroup) this, true);
        this.f7744a = findViewById(R.id.network_loading_frame);
        this.f7745b = findViewById(R.id.network_error_frame);
        this.f7746c = (ImageView) findViewById(R.id.online_refresh_animation);
        this.f7747d = (TextView) this.f7744a.findViewById(R.id.online_refresh_text);
        this.f7748e = (TextView) this.f7745b.findViewById(R.id.textview_load_failed);
        this.f7749f = AnimationUtils.loadAnimation(context, R.anim.rotate);
        this.f7745b.setOnClickListener(this.f7755l);
    }

    public void setOnStartLoadingListener(InterfaceC2206b interfaceC2206b) {
        this.f7750g = interfaceC2206b;
    }

    public void setLoadState(EnumC2205a enumC2205a) {
        this.f7751h = enumC2205a;
        LogUtils.m8388a("NetworkLoadView", "NetworkLoadView.setLoadState-----> " + this.f7751h + " mOnStartLoadingListener: " + this.f7750g);
        switch (this.f7751h) {
            case IDLE:
                m1679d();
                return;
            case LOADING:
                m1683a();
                if (this.f7750g != null) {
                    this.f7750g.mo1678a();
                    return;
                }
                return;
            case FAILED:
                m1680c();
                return;
            default:
                return;
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ColorStateList m3254c = ThemeManager.m3254c(ThemeElement.COMMON_CONTENT_TEXT);
        if (m3254c != null && this.f7748e != null && this.f7747d != null) {
            this.f7748e.setTextColor(m3254c);
            this.f7747d.setTextColor(m3254c);
        }
        Drawable m3265a = ThemeManager.m3265a(ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3270a(this, m3265a);
        ThemeManager.m3270a(this.f7744a, m3265a);
        ThemeManager.m3270a(this.f7745b, m3265a);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        LogUtils.m8388a("NetworkLoadView", "NetworkLoadView.onWindowVisibilityChanged----->visibility " + i);
        if (getVisibility() == View.VISIBLE && this.f7751h == EnumC2205a.LOADING) {
            m1683a();
        } else {
            setLoadState(this.f7751h);
        }
    }

    /* renamed from: a */
    public void m1683a() {
        LogUtils.m8388a("NetworkLoadView", "NetworkLoadView.showLoadingAnimView-----> ");
        setVisibility(View.VISIBLE);
        this.f7744a.setVisibility(View.VISIBLE);
        this.f7745b.setVisibility(View.INVISIBLE);
        m1681b();
    }

    /* renamed from: b */
    private void m1681b() {
        if ((!this.f7753j || this.f7754k) && this.f7744a.getVisibility() == View.VISIBLE && this.f7751h == EnumC2205a.LOADING) {
            this.f7746c.clearAnimation();
            this.f7746c.startAnimation(this.f7749f);
        }
    }

    /* renamed from: c */
    private void m1680c() {
        LogUtils.m8388a("NetworkLoadView", "NetworkLoadView.showLoadingFailedView-----> ");
        setVisibility(View.VISIBLE);
        this.f7744a.setVisibility(View.INVISIBLE);
        this.f7745b.setVisibility(View.VISIBLE);
        this.f7746c.clearAnimation();
    }

    public void setStartAnimationUntilVisibleToUser(boolean z) {
        this.f7753j = z;
    }

    public void setIsVisibleToUser(boolean z) {
        this.f7754k = z;
        if (z) {
            m1681b();
        }
    }

    public void setHideStyle(int i) {
        this.f7752i = i;
    }

    /* renamed from: d */
    private void m1679d() {
        LogUtils.m8388a("NetworkLoadView", "NetworkLoadView.hideAllView-----> ");
        setVisibility(this.f7752i);
        this.f7746c.clearAnimation();
    }
}
