package com.sds.android.ttpod.activities.musiccircle.shake;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.sds.android.cloudapi.ttpod.data.AroundTTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.util.List;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.b */
/* loaded from: classes.dex */
public class ShakeController implements Animation.AnimationListener {

    /* renamed from: a */
    private ImageView f2917a;

    /* renamed from: b */
    private ImageView f2918b;

    /* renamed from: c */
    private Animation f2919c;

    /* renamed from: d */
    private Animation f2920d;

    /* renamed from: e */
    private Context f2921e;

    /* renamed from: f */
    private InterfaceC0848a f2922f;

    /* renamed from: g */
    private List<AroundTTPodUser> f2923g;

    /* renamed from: h */
    private boolean f2924h = false;

    /* renamed from: i */
    private BDLocation f2925i;

    /* compiled from: ShakeController.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0848a {
        void onEndSearchAnimationFinished(List<AroundTTPodUser> list);

        void onEndSearchAnimationStart(List<AroundTTPodUser> list);

        void onSearchFail();

        void onSearchSuccess(List<AroundTTPodUser> list);

        void onStartSearchAnimationFinished(List<AroundTTPodUser> list);

        void onStartSearchAnimationStart(List<AroundTTPodUser> list);
    }

    public ShakeController(Context context, View view, InterfaceC0848a interfaceC0848a) {
        this.f2921e = context;
        this.f2922f = interfaceC0848a;
        this.f2917a = (ImageView) view.findViewById(R.id.shake_icon);
        this.f2918b = (ImageView) view.findViewById(R.id.shake_wave);
        this.f2919c = AnimationUtils.loadAnimation(this.f2921e, R.anim.musiccircle_shake_clockwise_60);
        this.f2920d = AnimationUtils.loadAnimation(this.f2921e, R.anim.musiccircle_shake_clockwise_360);
        this.f2919c.setAnimationListener(this);
        this.f2920d.setAnimationListener(this);
    }

    /* renamed from: a */
    public void m7835a(BDLocation bDLocation) {
        this.f2918b.setVisibility(View.VISIBLE);
        this.f2917a.setVisibility(View.VISIBLE);
        this.f2918b.setImageResource(R.drawable.img_musiccircle_shake_wave);
        this.f2917a.startAnimation(this.f2919c);
        this.f2917a.setVisibility(View.VISIBLE);
        this.f2923g = null;
        this.f2924h = true;
        this.f2925i = bDLocation;
    }

    /* renamed from: a */
    public void m7836a() {
        this.f2917a.setVisibility(View.VISIBLE);
        this.f2918b.setVisibility(View.VISIBLE);
        this.f2917a.setImageResource(R.drawable.img_musiccircle_shake_animation_phone);
        this.f2918b.setImageResource(R.drawable.img_musiccircle_shake_wave);
    }

    /* renamed from: d */
    private void m7831d() {
        float f;
        float f2;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f2921e);
        if (this.f2925i != null) {
            f2 = (float) this.f2925i.getLongitude();
            f = (float) this.f2925i.getLatitude();
            if (f2 != 0.0f) {
                defaultSharedPreferences.edit().putFloat("location_longitude", (float) this.f2925i.getLongitude()).commit();
            }
            if (f != 0.0f) {
                defaultSharedPreferences.edit().putFloat("location_latitude", (float) this.f2925i.getLatitude()).commit();
            }
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        if (f2 == 0.0f) {
            f2 = defaultSharedPreferences.getFloat("location_longitude", 0.0f);
        }
        if (f == 0.0f) {
            f = defaultSharedPreferences.getFloat("location_latitude", 0.0f);
        }
        CommandCenter.getInstance().m4606a(new Command(CommandID.REQUEST_SHACK_USERS, Float.valueOf(f2), Float.valueOf(f), "shake"));
    }

    /* renamed from: a */
    public void m7834a(List<AroundTTPodUser> list) {
        this.f2918b.setVisibility(View.INVISIBLE);
        this.f2918b.clearAnimation();
        this.f2918b.setImageResource(R.drawable.img_musiccircle_shake_wave);
        this.f2924h = false;
        if (list != null) {
            if (this.f2922f != null) {
                this.f2922f.onSearchSuccess(list);
                this.f2923g = list;
                return;
            }
            return;
        }
        m7832c();
        if (this.f2922f != null) {
            this.f2923g = null;
            this.f2922f.onSearchFail();
        }
    }

    /* renamed from: b */
    public boolean m7833b() {
        return this.f2924h;
    }

    /* renamed from: c */
    public void m7832c() {
        this.f2917a.setVisibility(View.INVISIBLE);
        this.f2918b.setVisibility(View.VISIBLE);
        this.f2918b.setImageResource(R.drawable.img_musiccircle_shake_fail);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationStart(Animation animation) {
        if (animation == this.f2919c) {
            if (this.f2922f != null) {
                this.f2922f.onStartSearchAnimationStart(this.f2923g);
            }
        } else if (animation == this.f2920d && this.f2922f != null) {
            this.f2922f.onEndSearchAnimationStart(this.f2923g);
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        if (animation == this.f2919c) {
            m7831d();
            this.f2918b.startAnimation(this.f2920d);
            if (this.f2922f != null) {
                this.f2922f.onStartSearchAnimationFinished(this.f2923g);
            }
        } else if (animation == this.f2920d && this.f2922f != null) {
            this.f2922f.onEndSearchAnimationFinished(this.f2923g);
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public void onAnimationRepeat(Animation animation) {
    }
}
