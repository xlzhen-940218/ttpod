package com.sds.android.ttpod.activities.musiccircle.radar;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.sds.android.cloudapi.ttpod.data.AlikeTTPodUser;
import com.sds.android.cloudapi.ttpod.result.AlikeUserListResult;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.c */
/* loaded from: classes.dex */
public class RadarAnimationManager implements View.OnClickListener {

    /* renamed from: C */
    private InterfaceC0838a f2872C;

    /* renamed from: D */
    private WrapUserPostListFragment.InterfaceC1704a f2873D;

    /* renamed from: a */
    private Activity f2874a;

    /* renamed from: b */
    private View f2875b;

    /* renamed from: e */
    private FrameLayout f2878e;

    /* renamed from: f */
    private ImageView f2879f;

    /* renamed from: g */
    private RadarAnimation f2880g;

    /* renamed from: h */
    private Button f2881h;

    /* renamed from: o */
    private int f2888o;

    /* renamed from: v */
    private List<AlikeTTPodUser> f2895v;

    /* renamed from: w */
    private List<DrawView> f2896w;

    /* renamed from: x */
    private TextSwitcher f2897x;

    /* renamed from: z */
    private String[] f2899z;

    /* renamed from: c */
    private int f2876c = 0;

    /* renamed from: d */
    private EnumC0840c f2877d = EnumC0840c.NONE;

    /* renamed from: i */
    private ImageView[] f2882i = new ImageView[4];

    /* renamed from: j */
    private RelativeLayout[] f2883j = new RelativeLayout[4];

    /* renamed from: k */
    private Point[] f2884k = new Point[4];

    /* renamed from: l */
    private double[] f2885l = new double[4];

    /* renamed from: m */
    private boolean[] f2886m = new boolean[4];

    /* renamed from: n */
    private Map<Object, Object> f2887n = new HashMap();

    /* renamed from: p */
    private int f2889p = 4;

    /* renamed from: q */
    private int f2890q = 0;

    /* renamed from: r */
    private List<Number> f2891r = new ArrayList();

    /* renamed from: s */
    private List<Number> f2892s = new ArrayList();

    /* renamed from: t */
    private int f2893t = 0;

    /* renamed from: u */
    private int f2894u = 0;

    /* renamed from: y */
    private int f2898y = 0;

    /* renamed from: A */
    private int f2870A = 0;

    /* renamed from: B */
    private int f2871B = 0;

    /* compiled from: RadarAnimationManager.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0838a {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RadarAnimationManager.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.c$c */
    /* loaded from: classes.dex */
    public enum EnumC0840c {
        ERROR,
        NONE,
        HAS_RESULT,
        FINISH
    }

    /* renamed from: a */
    public void m7886a(WrapUserPostListFragment.InterfaceC1704a interfaceC1704a) {
        this.f2873D = interfaceC1704a;
    }

    /* renamed from: a */
    public void m7902a() {
        this.f2879f.clearAnimation();
        this.f2887n.clear();
        this.f2891r.clear();
        this.f2892s.clear();
        this.f2895v.clear();
        m7858t();
    }

    /* renamed from: b */
    public void m7884b() {
        this.f2880g = new RadarAnimation();
        this.f2880g.setRepeatMode(1);
        this.f2880g.setRepeatCount(-1);
        this.f2880g.setInterpolator(new LinearInterpolator());
        this.f2880g.m7903a(new RadarAnimation.InterfaceC0831a() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.c.1
            @Override // com.sds.android.ttpod.activities.musiccircle.radar.RadarAnimation.InterfaceC0831a
            /* renamed from: a */
            public void mo7851a(float f) {
                RadarAnimationManager.this.m7901a(f);
            }
        });
        this.f2880g.setAnimationListener(new Animation.AnimationListener() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.c.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                if (RadarAnimationManager.this.f2877d != EnumC0840c.HAS_RESULT) {
                    RadarAnimationManager.this.m7855w();
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                RadarAnimationManager.this.m7871g();
            }
        });
        this.f2879f.startAnimation(this.f2880g);
        m7867k();
    }

    /* renamed from: f */
    private void m7873f() {
        Collections.shuffle(this.f2891r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7901a(float f) {
        if (this.f2890q >= this.f2889p && this.f2889p != 0) {
            this.f2877d = EnumC0840c.FINISH;
        }
        ImageView m7883b = m7883b(f);
        if (m7883b != null) {
            TextView textView = (TextView) this.f2883j[this.f2898y].findViewById(R.id.info1);
            LogUtils.debug("TEST", "get point view to display.... : " + m7883b + " status: " + this.f2877d);
            m7883b.setVisibility(View.VISIBLE);
            if (this.f2877d == EnumC0840c.HAS_RESULT && m7900a(this.f2898y)) {
                if (!StringUtils.isEmpty(textView.getText().toString())) {
                    LogUtils.debug("TEST", "is allow to display... : " + this.f2898y);
                    Animation m7864n = m7864n();
                    m7883b.startAnimation(m7864n);
                    this.f2887n.put(m7864n, new C0839b(m7883b, this.f2898y));
                    this.f2890q++;
                    this.f2886m[this.f2898y] = true;
                } else if (this.f2898y < this.f2895v.size()) {
                    LogUtils.error("TEST", "error in radar animation, wrong result:" + this.f2898y + " name: " + this.f2895v.get(this.f2898y).getNickName() + " fans: " + this.f2895v.get(this.f2898y).getFollowersCount() + " same song: " + this.f2895v.get(this.f2898y).getSameSongCount());
                } else {
                    LogUtils.error("TEST", "error in radar animation, wrong scope: " + this.f2898y + " data size: " + this.f2895v.size());
                }
            } else {
                LogUtils.debug("TEST", "hideFlashAnimation start...");
                m7883b.startAnimation(m7863o());
                this.f2886m[this.f2898y] = true;
            }
            m7870h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m7871g() {
        LogUtils.debug("TEST", "onAnimationRepeat, count: " + this.f2876c + " status: " + this.f2877d + " display count: " + this.f2890q);
        this.f2876c++;
        if (this.f2876c >= 4) {
            if (this.f2890q > 0) {
                this.f2877d = EnumC0840c.FINISH;
                LogUtils.debug("TEST", "set Status to FINISH, repeat count: " + this.f2876c + " display count: " + this.f2890q);
            } else {
                this.f2877d = EnumC0840c.ERROR;
            }
        }
        if (this.f2877d == EnumC0840c.FINISH) {
            if (this.f2889p == 0) {
                LogUtils.debug("TEST", "handleErrorStatus: " + this.f2877d + " resultCount: " + this.f2889p);
                m7868j();
                return;
            }
            m7869i();
        } else if (this.f2877d == EnumC0840c.ERROR) {
            LogUtils.debug("TEST", "handleErrorStatus: " + this.f2877d);
            m7868j();
        } else if (this.f2890q == 0) {
            m7854x();
            if (this.f2877d != EnumC0840c.HAS_RESULT) {
                m7855w();
            }
        }
    }

    /* renamed from: h */
    private void m7870h() {
        int i = this.f2871B;
        this.f2871B = i + 1;
        if (i % 2 == 0) {
            this.f2870A %= this.f2899z.length;
            TextSwitcher textSwitcher = this.f2897x;
            String[] strArr = this.f2899z;
            int i2 = this.f2870A;
            this.f2870A = i2 + 1;
            textSwitcher.setText(strArr[i2]);
        }
    }

    /* renamed from: i */
    private void m7869i() {
        this.f2879f.clearAnimation();
        this.f2897x.setVisibility(View.GONE);
        this.f2881h.setVisibility(View.VISIBLE);
    }

    /* renamed from: j */
    private void m7868j() {
        this.f2879f.clearAnimation();
        this.f2879f.setVisibility(View.INVISIBLE);
        PopupsUtils.m6721a("知音少，弦断有谁听？");
        this.f2878e.setBackgroundResource(R.drawable.img_musiccircle_radar_error);
        this.f2897x.setVisibility(View.GONE);
        this.f2881h.setVisibility(View.VISIBLE);
    }

    /* renamed from: c */
    public void m7880c() {
        m7860r();
        this.f2879f.setVisibility(View.VISIBLE);
        this.f2881h.setVisibility(View.GONE);
        this.f2897x.setText("");
        this.f2897x.setVisibility(View.VISIBLE);
        this.f2878e.setBackgroundResource(R.drawable.img_musiccircle_radar);
        m7859s();
        m7858t();
        m7884b();
        MusicCircleStatistic.m7965j();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f2881h == view) {
            m7880c();
        }
    }

    /* renamed from: k */
    private void m7867k() {
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_ALIKE_USERS, "alike"));
    }

    /* renamed from: a */
    public void m7892a(AlikeUserListResult alikeUserListResult) {
        if (m7882b(alikeUserListResult)) {
            m7885a(alikeUserListResult.getDataList());
        }
    }

    /* renamed from: b */
    private boolean m7882b(AlikeUserListResult alikeUserListResult) {
        return alikeUserListResult.getCode() == 1 && alikeUserListResult.getDataList() != null && alikeUserListResult.getDataList().size() >= 1 && alikeUserListResult.getDataList().size() <= 4;
    }

    public RadarAnimationManager(Activity activity, View view) {
        this.f2874a = activity;
        this.f2875b = view;
    }

    /* renamed from: d */
    public void m7877d() {
        this.f2881h = (Button) this.f2875b.findViewById(R.id.rescan);
        this.f2899z = this.f2874a.getResources().getStringArray(R.array.poetry);
        this.f2881h.setOnClickListener(this);
        this.f2881h.setVisibility(View.GONE);
        this.f2878e = (FrameLayout) this.f2875b.findViewById(R.id.layout_radar);
        this.f2879f = (ImageView) this.f2875b.findViewById(R.id.radar_line_view);
        this.f2882i[0] = (ImageView) this.f2875b.findViewById(R.id.point1);
        this.f2882i[1] = (ImageView) this.f2875b.findViewById(R.id.point2);
        this.f2882i[2] = (ImageView) this.f2875b.findViewById(R.id.point3);
        this.f2882i[3] = (ImageView) this.f2875b.findViewById(R.id.point4);
        this.f2883j[0] = (RelativeLayout) this.f2875b.findViewById(R.id.layout_user_info1);
        this.f2883j[1] = (RelativeLayout) this.f2875b.findViewById(R.id.layout_user_info2);
        this.f2883j[2] = (RelativeLayout) this.f2875b.findViewById(R.id.layout_user_info3);
        this.f2883j[3] = (RelativeLayout) this.f2875b.findViewById(R.id.layout_user_info4);
        this.f2897x = (TextSwitcher) this.f2875b.findViewById(R.id.info);
        this.f2897x.setFactory(new ViewSwitcher.ViewFactory() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.c.3
            @Override // android.widget.ViewSwitcher.ViewFactory
            public View makeView() {
                TextView textView = new TextView(RadarAnimationManager.this.f2874a);
                textView.setGravity(49);
                textView.setTextSize(20.0f);
                return textView;
            }
        });
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f2874a, android.R.anim.fade_in);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.f2874a, android.R.anim.fade_out);
        this.f2897x.setInAnimation(loadAnimation);
        this.f2897x.setOutAnimation(loadAnimation2);
        m7866l();
        this.f2895v = new ArrayList();
        this.f2896w = new ArrayList();
        m7859s();
        m7858t();
        m7854x();
        m7855w();
        m7865m();
    }

    /* renamed from: l */
    private void m7866l() {
        this.f2891r.add(0);
        this.f2891r.add(1);
        this.f2891r.add(2);
        this.f2891r.add(3);
    }

    /* renamed from: m */
    private void m7865m() {
        this.f2870A = m7898a(this.f2899z.length - 1, 0);
        if (this.f2870A % 2 == 1) {
            this.f2870A++;
        }
    }

    /* renamed from: n */
    private Animation m7864n() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f2874a, R.anim.flash_show_animation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.c.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                LogUtils.debug("TEST", " showFlashAnimation start...");
                C0839b c0839b = (C0839b) RadarAnimationManager.this.f2887n.get(animation);
                if (c0839b != null) {
                    RadarAnimationManager.this.m7894a((View) c0839b.m7849a(), c0839b.m7847b());
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }
        });
        return loadAnimation;
    }

    /* renamed from: o */
    private Animation m7863o() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f2874a, R.anim.flash_hide_animation);
        loadAnimation.setFillAfter(true);
        return loadAnimation;
    }

    /* renamed from: a */
    public void m7885a(List<AlikeTTPodUser> list) {
        if (!this.f2891r.isEmpty()) {
            this.f2895v.addAll(list);
            this.f2889p = this.f2895v.size();
            this.f2890q = 0;
            if (this.f2889p > 4) {
                this.f2889p = 4;
            }
            this.f2892s.clear();
            for (int i = 0; i < this.f2889p; i++) {
                this.f2892s.add(this.f2891r.get(i));
            }
            LogUtils.debug("TEST", "return " + this.f2889p + " result!list size:" + list.size() + " index: " + this.f2892s.toString());
            this.f2877d = EnumC0840c.HAS_RESULT;
            m7854x();
            m7862p();
            MusicCircleStatistic.m7986a(this.f2889p);
        }
    }

    /* renamed from: e */
    public void m7875e() {
        this.f2877d = EnumC0840c.ERROR;
    }

    /* renamed from: a */
    public void m7891a(InterfaceC0838a interfaceC0838a) {
        this.f2872C = interfaceC0838a;
    }

    /* renamed from: p */
    private void m7862p() {
        int i = 0;
        int i2 = 0;
        while (i < this.f2889p) {
            Number number = this.f2891r.get(i);
            LogUtils.debug("TEST", "show number: " + number.intValue());
            RelativeLayout relativeLayout = this.f2883j[number.intValue()];
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.c.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RadarAnimationManager.this.f2873D.mo5428a((AlikeTTPodUser) view.getTag());
                }
            });
            int i3 = i2 + 1;
            AlikeTTPodUser alikeTTPodUser = this.f2895v.get(i2);
            relativeLayout.setTag(alikeTTPodUser);
            ((TextView) relativeLayout.findViewById(R.id.user_name)).setText(alikeTTPodUser.getNickName());
            ((TextView) relativeLayout.findViewById(R.id.info1)).setText("粉丝：" + alikeTTPodUser.getFollowersCount());
            ((TextView) relativeLayout.findViewById(R.id.info2)).setText(String.format("共同歌曲: %d", Integer.valueOf(alikeTTPodUser.getSameSongCount())));
            LogUtils.debug("TEST", "name: " + alikeTTPodUser.getNickName() + "fans: " + alikeTTPodUser.getFollowersCount() + " avatar: " + alikeTTPodUser.getAvatarUrl());
            String avatarUrl = alikeTTPodUser.getAvatarUrl();
            int m7229a = DisplayUtils.dp2px(70);
            ImageCacheUtils.displayImage((ImageView) relativeLayout.findViewById(R.id.avatar), avatarUrl, m7229a, m7229a, (int) R.drawable.img_avatar_default);
            i++;
            i2 = i3;
        }
    }

    /* renamed from: b */
    private ImageView m7883b(float f) {
        for (int i = 0; i < 4; i++) {
            double d = this.f2885l[i];
            if (f >= d && !this.f2886m[i]) {
                int m7879c = m7879c((float) d);
                this.f2898y = m7879c;
                return this.f2882i[m7879c];
            }
        }
        return null;
    }

    /* renamed from: a */
    private boolean m7900a(int i) {
        for (Number number : this.f2892s) {
            if (i == number.intValue()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    private int m7879c(float f) {
        return (int) (f / 90.0f);
    }

    /* renamed from: q */
    private void m7861q() {
        for (int i = 0; i < 4; i++) {
            m7893a(this.f2882i[i], i);
        }
    }

    /* renamed from: r */
    private void m7860r() {
        this.f2877d = EnumC0840c.NONE;
        this.f2889p = 0;
        this.f2890q = 0;
        this.f2876c = 0;
        this.f2871B = 0;
        this.f2887n.clear();
        this.f2895v.clear();
        m7854x();
        m7865m();
    }

    /* renamed from: s */
    private void m7859s() {
        for (RelativeLayout relativeLayout : this.f2883j) {
            relativeLayout.setVisibility(View.INVISIBLE);
        }
        for (ImageView imageView : this.f2882i) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    /* renamed from: t */
    private void m7858t() {
        RelativeLayout relativeLayout = (RelativeLayout) this.f2875b.findViewById(R.id.root);
        for (DrawView drawView : this.f2896w) {
            drawView.setVisibility(View.GONE);
            drawView.m7916a();
            relativeLayout.removeView(drawView);
        }
        this.f2896w.clear();
    }

    /* renamed from: u */
    private void m7857u() {
        if (this.f2893t == 0 || this.f2893t == 128) {
            Rect rect = new Rect();
            this.f2879f.getGlobalVisibleRect(rect);
            this.f2893t = (rect.width() / 2) - 10;
        }
        if (this.f2893t <= 0) {
            this.f2893t = 128;
        }
        LogUtils.debug("TEST", "radar radius: " + this.f2893t);
    }

    /* renamed from: v */
    private void m7856v() {
        if (this.f2894u == 0) {
            Rect rect = new Rect();
            this.f2882i[0].getGlobalVisibleRect(rect);
            this.f2894u = rect.width() / 2;
        }
        if (this.f2894u < 0) {
            this.f2894u = 10;
        }
        LogUtils.debug("TEST", "point radius: " + this.f2894u);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public void m7855w() {
        m7873f();
        m7857u();
        m7856v();
        m7853y();
        m7861q();
    }

    /* renamed from: x */
    private void m7854x() {
        LogUtils.debug("TEST", "resetPointAngleFlag...");
        for (int i = 0; i < 4; i++) {
            this.f2886m[i] = false;
        }
    }

    /* renamed from: y */
    private void m7853y() {
        int i = this.f2893t - (this.f2894u * 2);
        int i2 = (this.f2893t / 3) + 1;
        m7854x();
        for (int i3 = 0; i3 < 4; i3++) {
            int m7898a = m7898a(i, i2);
            double m7898a2 = m7898a(70, 20);
            int sin = (int) (m7898a * Math.sin(Math.toRadians(m7898a2)));
            int cos = (int) (m7898a * Math.cos(Math.toRadians(m7898a2)));
            this.f2884k[i3] = new Point(sin, cos);
            this.f2885l[i3] = m7896a(this.f2884k[i3]) + (i3 * 90);
            LogUtils.debug("TEST", "r: " + m7898a + " x: " + sin + " y:" + cos + " a: " + m7898a2 + " an: " + this.f2885l[i3]);
        }
    }

    /* renamed from: a */
    private double m7896a(Point point) {
        return Math.atan2(point.x, point.y) * 57.29577951308232d;
    }

    /* renamed from: a */
    private void m7893a(ImageView imageView, int i) {
        imageView.setVisibility(View.INVISIBLE);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        switch (i) {
            case 0:
                layoutParams.setMargins(this.f2884k[0].x, 0, 0, this.f2884k[0].y);
                break;
            case 1:
                layoutParams.setMargins(this.f2884k[1].x, this.f2884k[1].y, 0, 0);
                break;
            case 2:
                layoutParams.setMargins(0, this.f2884k[2].y, this.f2884k[2].x, 0);
                break;
            default:
                layoutParams.setMargins(0, 0, this.f2884k[3].x, this.f2884k[3].y);
                break;
        }
        imageView.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    private int m7898a(int i, int i2) {
        return new Random().nextInt(i - i2) + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7894a(View view, int i) {
        float width;
        float f;
        int dimension = ((int) this.f2874a.getResources().getDimension(R.dimen.dialog_header_height)) + m7852z();
        int[] m7895a = m7895a(view);
        ImageView imageView = (ImageView) this.f2883j[i].findViewById(R.id.avatar);
        int[] m7895a2 = m7895a(imageView);
        m7895a2[1] = m7895a2[1] - dimension;
        float width2 = m7895a[0] + (view.getWidth() / 2);
        float height = (m7895a[1] + (view.getHeight() / 2)) - dimension;
        switch (i) {
            case 0:
                width = m7895a2[0] + imageView.getWidth();
                f = imageView.getHeight() + m7895a2[1];
                break;
            case 1:
            case 2:
                width = imageView.getWidth() + m7895a2[0];
                f = m7895a2[1];
                break;
            default:
                width = m7895a2[0] + imageView.getWidth();
                f = imageView.getHeight() + m7895a2[1];
                break;
        }
        m7899a(i, width2, height, width, f);
    }

    /* renamed from: a */
    private int[] m7895a(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr;
    }

    /* renamed from: z */
    private int m7852z() {
        if (this.f2888o == 0) {
            Rect rect = new Rect();
            this.f2874a.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            this.f2888o = rect.top;
        }
        return this.f2888o;
    }

    /* renamed from: a */
    private void m7899a(int i, float f, float f2, float f3, float f4) {
        DrawView drawView = new DrawView(this.f2874a, i, new DrawView.InterfaceC0830a() { // from class: com.sds.android.ttpod.activities.musiccircle.radar.c.6
            @Override // com.sds.android.ttpod.activities.musiccircle.radar.DrawView.InterfaceC0830a
            /* renamed from: a */
            public void mo7850a(int i2, int i3, int i4) {
                RadarAnimationManager.this.m7897a(i2, i3, i4);
            }
        });
        ((RelativeLayout) this.f2875b.findViewById(R.id.root)).addView(drawView);
        this.f2896w.add(drawView);
        drawView.m7915a(f, f3, f2, f4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7897a(int i, int i2, int i3) {
        this.f2883j[i].setVisibility(View.VISIBLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: RadarAnimationManager.java */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.radar.c$b */
    /* loaded from: classes.dex */
    public class C0839b {

        /* renamed from: b */
        private ImageView f2907b;

        /* renamed from: c */
        private int f2908c;

        public C0839b(ImageView imageView, int i) {
            this.f2907b = imageView;
            this.f2908c = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public ImageView m7849a() {
            return this.f2907b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public int m7847b() {
            return this.f2908c;
        }
    }
}
