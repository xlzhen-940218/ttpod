package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;

/* loaded from: classes.dex */
public class StateView extends FrameLayout implements ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private View f8044a;

    /* renamed from: b */
    private ViewGroup f8045b;

    /* renamed from: c */
    private View f8046c;

    /* renamed from: d */
    private View f8047d;

    /* renamed from: e */
    private View f8048e;

    /* renamed from: f */
    private TextView f8049f;

    /* renamed from: g */
    private Animation f8050g;

    /* renamed from: h */
    private InterfaceC2247a f8051h;

    /* renamed from: i */
    private View.OnClickListener f8052i;

    /* renamed from: com.sds.android.ttpod.widget.StateView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2247a {
        /* renamed from: a */
        void mo1450a();
    }

    /* renamed from: com.sds.android.ttpod.widget.StateView$b */
    /* loaded from: classes.dex */
    public enum EnumC2248b {
        LOADING,
        SUCCESS,
        FAILED,
        NO_DATA
    }

    public StateView(Context context) {
        this(context, null);
    }

    public StateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8052i = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.StateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (StateView.this.f8051h != null) {
                    StateView.this.f8051h.mo1450a();
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StateView);
        int failed_view_id = obtainStyledAttributes.getResourceId(R.styleable.StateView_failed_view, -1);
        int success_view_id = obtainStyledAttributes.getResourceId(R.styleable.StateView_success_view, -1);
        int loading_view_id = obtainStyledAttributes.getResourceId(R.styleable.StateView_loading_view, R.layout.loadingview_loading);
        int nodata_view_id = obtainStyledAttributes.getResourceId(R.styleable.StateView_nodata_view, -1);
        LayoutInflater from = LayoutInflater.from(context);
        if (failed_view_id != -1) {
            this.f8046c = from.inflate(failed_view_id, (ViewGroup) null);
            addView(this.f8046c, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            this.f8046c.setOnClickListener(this.f8052i);
        }
        if (success_view_id != -1) {
            this.f8045b = (ViewGroup) from.inflate(success_view_id, (ViewGroup) null);
            addView(this.f8045b, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if (loading_view_id != -1) {
            this.f8044a = from.inflate(loading_view_id, (ViewGroup) null);
            addView(this.f8044a, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if (nodata_view_id != -1) {
            this.f8047d = from.inflate(nodata_view_id, (ViewGroup) null);
            addView(this.f8047d, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        this.f8048e = this.f8044a.findViewById(R.id.online_refresh_animation);
        this.f8049f = (TextView) this.f8044a.findViewById(R.id.online_refresh_text);
        this.f8050g = AnimationUtils.loadAnimation(context, R.anim.rotate);
        setState(EnumC2248b.SUCCESS);
    }

    public void setFailedView(View view) {
        if (this.f8046c != null) {
            removeView(view);
        }
        this.f8046c = view;
        if (this.f8046c != null) {
            this.f8046c.setOnClickListener(this.f8052i);
        }
        addView(view);
    }

    public void setSuccessView(View view) {
        if (this.f8045b != null) {
            removeView(view);
        }
        this.f8045b = (ViewGroup) view;
        addView(view);
    }

    public void setLoadingView(View view) {
        if (this.f8044a != null) {
            removeView(this.f8044a);
        }
        this.f8044a = view;
        addView(view);
    }

    public void setNodataView(View view) {
        if (this.f8047d != null) {
            removeView(this.f8047d);
        }
        this.f8047d = view;
        addView(view);
    }

    public void setState(EnumC2248b enumC2248b) {
        if (this.f8044a != null) {
            this.f8044a.setVisibility(enumC2248b == EnumC2248b.LOADING ? View.VISIBLE : View.GONE);
        }
        if (this.f8045b != null) {
            this.f8045b.setVisibility(enumC2248b == EnumC2248b.SUCCESS ? View.VISIBLE : View.GONE);
        }
        if (this.f8046c != null) {
            this.f8046c.setVisibility(enumC2248b == EnumC2248b.FAILED ? View.VISIBLE : View.GONE);
        }
        if (this.f8047d != null) {
            this.f8047d.setVisibility(enumC2248b != EnumC2248b.NO_DATA ? View.GONE : View.VISIBLE);
        }
        if (enumC2248b == EnumC2248b.LOADING) {
            this.f8048e.startAnimation(this.f8050g);
        } else {
            this.f8048e.clearAnimation();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeManager.m3269a(this, "BackgroundMaskColor");
        m1451b();
        ThemeManager.m3269a(this.f8044a, "BackgroundMaskColor");
        m1453a();
        ColorStateList m3254c = ThemeManager.m3254c(ThemeElement.COMMON_CONTENT_TEXT);
        if (m3254c != null) {
            this.f8049f.setTextColor(m3254c);
            if (this.f8046c != null && (this.f8046c instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) this.f8046c;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if ((childAt instanceof TextView) && childAt.getId() != R.id.no_data_action_view) {
                        ((TextView) childAt).setTextColor(m3254c);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m1453a() {
        IconTextView iconTextView;
        ThemeManager.m3269a(this.f8047d, "BackgroundMaskColor");
        if (this.f8047d != null && (iconTextView = (IconTextView) this.f8047d.findViewById(R.id.icon_no_data)) != null) {
            ThemeUtils.m8173a(iconTextView, ThemeElement.TILE_SUB_TEXT);
        }
    }

    /* renamed from: b */
    private void m1451b() {
        ThemeManager.m3269a(this.f8046c, "BackgroundMaskColor");
        if (this.f8046c != null) {
            IconTextView iconTextView = (IconTextView) this.f8046c.findViewById(R.id.no_media_icon);
            if (iconTextView != null) {
                ThemeUtils.m8173a(iconTextView, ThemeElement.SONG_LIST_ITEM_TEXT);
            }
            TextView textView = (TextView) this.f8046c.findViewById(R.id.textview_load_failed);
            if (textView != null) {
                ThemeManager.m3269a(textView, ThemeElement.SONG_LIST_ITEM_TEXT);
            }
        }
    }

    public void setOnRetryRequestListener(InterfaceC2247a interfaceC2247a) {
        this.f8051h = interfaceC2247a;
    }
}
