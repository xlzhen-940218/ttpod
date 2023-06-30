package com.sds.android.ttpod.component.p087d.p088a;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.GlobalMenuItem;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeFramework;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.MenuUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.GlobalMenuThumbImageView;
import com.sds.android.ttpod.widget.SimpleGridView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a.d */
/* loaded from: classes.dex */
public class GlobalMenuDialog extends Dialog {

    /* renamed from: l */
    private static boolean f3908l = false;

    /* renamed from: m */
    private static boolean f3909m = false;

    /* renamed from: a */
    private ViewPager f3910a;

    /* renamed from: b */
    private C1150a f3911b;

    /* renamed from: c */
    private InterfaceC1152b f3912c;

    /* renamed from: d */
    private View f3913d;

    /* renamed from: e */
    private View f3914e;

    /* renamed from: f */
    private Context f3915f;

    /* renamed from: g */
    private GlobalMenuThumbImageView f3916g;

    /* renamed from: h */
    private boolean f3917h;

    /* renamed from: i */
    private C1153c f3918i;

    /* renamed from: j */
    private C1153c f3919j;

    /* renamed from: k */
    private C1153c f3920k;

    /* renamed from: n */
    private List<GlobalMenuItem> f3921n;

    /* renamed from: o */
    private boolean f3922o;

    /* renamed from: p */
    private GlobalMenuItem f3923p;

    /* renamed from: q */
    private Handler f3924q;

    /* renamed from: r */
    private View.OnClickListener f3925r;

    /* renamed from: s */
    private final ViewPager.OnPageChangeListener f3926s;

    /* compiled from: GlobalMenuDialog.java */
    /* renamed from: com.sds.android.ttpod.component.d.a.d$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1152b {
        void onMenuItemClicked(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m6864f() {
        if (this.f3918i != null) {
            long longValue = ((Long) CommandCenter.getInstance().m4602a(new Command(CommandID.SLEEP_MODE_REMAIN_TIME, new Object[0]), Long.class)).longValue();
            long j = longValue / 60;
            this.f3918i.m6847a(getContext().getString(R.string.menu_remain_time, Integer.valueOf((int) j), Integer.valueOf((int) (longValue - (j * 60)))));
        }
    }

    /* renamed from: a */
    public void m6883a() {
        if (this.f3919j == null) {
            return;
        }
        this.f3919j.m6854a(Preferences.m2862l().ordinal());
    }

    /* renamed from: a */
    public void m6879a(Boolean bool) {
        if (!this.f3921n.contains(this.f3923p) && bool.booleanValue()) {
            this.f3921n.add(8, this.f3923p);
        } else if (!bool.booleanValue()) {
            this.f3921n.remove(this.f3923p);
        }
        this.f3911b.m6855a(this.f3921n.subList(0, 8), this.f3921n.subList(8, this.f3921n.size()));
        this.f3911b.notifyDataSetChanged();
    }

    /* renamed from: b */
    public void m6876b() {
        if (this.f3918i != null) {
            if (((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
                if (!this.f3924q.hasMessages(1)) {
                    this.f3924q.sendEmptyMessageDelayed(1, 1000L);
                }
                m6864f();
                return;
            }
            this.f3924q.removeCallbacksAndMessages(null);
            this.f3918i.m6847a(getContext().getString(R.string.menu_sleep));
        }
    }

    /* renamed from: a */
    public void m6877a(boolean z) {
        this.f3917h = z;
    }

    public GlobalMenuDialog(Context context, InterfaceC1152b interfaceC1152b) {
        super(context, R.style.Dialog_Transparent);
        this.f3922o = true;
        this.f3924q = new Handler() { // from class: com.sds.android.ttpod.component.d.a.d.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        GlobalMenuDialog.this.m6864f();
                        GlobalMenuDialog.this.f3924q.sendEmptyMessageDelayed(1, 1000L);
                        return;
                    default:
                        return;
                }
            }
        };
        this.f3925r = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        };
        this.f3926s = new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.component.d.a.d.3
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                if (i2 > 0) {
                    GlobalMenuDialog.this.f3916g.setThumbOffset(f);
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i == 0) {
                    return;
                }
                GlobalMenuDialog.this.f3911b.m6859a();
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        };
        setCanceledOnTouchOutside(true);
        this.f3915f = context;
        this.f3912c = interfaceC1152b;
        m6882a(context);
    }

    /* renamed from: a */
    private void m6882a(Context context) {
        setContentView(R.layout.popups_global_menu_panel);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = DisplayUtils.getWidth();
        window.setWindowAnimations(R.style.Dialog_Window_Anim);
        window.setAttributes(attributes);
        this.f3914e = findViewById(R.id.layout_root_view);
        this.f3913d = findViewById(R.id.content_view);
        this.f3916g = (GlobalMenuThumbImageView) findViewById(R.id.iv_indicator);
        this.f3910a = (ViewPager) findViewById(R.id.vp_menu);
        this.f3911b = new C1150a(context);
        this.f3921n = MenuUtils.m8257a();
        m6878a(this.f3921n);
        m6862g();
        this.f3911b.m6855a(this.f3921n.subList(0, 8), this.f3921n.subList(8, this.f3921n.size()));
        this.f3910a.setAdapter(this.f3911b);
        this.f3910a.setOnPageChangeListener(this.f3926s);
        this.f3914e.setOnClickListener(this.f3925r);
    }

    /* renamed from: a */
    private void m6878a(List<GlobalMenuItem> list) {
        this.f3923p = new GlobalMenuItem(12, 0, R.string.unicom_flow_menu_name, ThemeElement.SETTING_TRAFFIC_IMAGE, R.string.icon_unicom_flow);
        if (EnvironmentUtils.DeviceConfig.getSubscriberId().startsWith("46001") && Cache.getInstance().m3231H() && Cache.getInstance().m3230I()) {
            list.add(8, this.f3923p);
        }
    }

    /* renamed from: g */
    private void m6862g() {
        if ((Preferences.m2913bp() && Preferences.m2912bq()) && this.f3922o) {
            int size = this.f3921n.size() - 2;
            this.f3921n.remove(0);
            this.f3921n.add(3, this.f3921n.get(size));
            this.f3921n.remove(size);
            this.f3921n.add(8, this.f3921n.get(0));
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        if (((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
            this.f3924q.sendEmptyMessage(1);
        }
        this.f3910a.setCurrentItem(0, false);
        this.f3916g.setThumbOffset(0.0f);
        //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU, SPage.PAGE_NONE, SPage.PAGE_GLOBAL_MENU);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
            case 82:
                dismiss();
                break;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.f3924q.removeCallbacksAndMessages(null);
        super.dismiss();
    }

    /* renamed from: h */
    private void m6861h() {
        int i;
        int dimensionPixelSize = this.f3915f.getResources().getDimensionPixelSize(R.dimen.global_menu_dialog_height);
        ThemeFramework.AbstractC2016e m3258b = ThemeManager.m3258b(ThemeElement.SETTING_BACKGROUND);
        if (m3258b != null) {
            i = m3258b.getHeight();
            if (i <= dimensionPixelSize) {
                i = dimensionPixelSize;
            }
        } else {
            i = dimensionPixelSize;
        }
        this.f3913d.getLayoutParams().height = i;
        if (isShowing()) {
            hide();
            show();
        }
    }

    /* renamed from: i */
    private void m6860i() {
        Drawable m3265a = ThemeManager.m3265a(ThemeElement.SETTING_HORIZONTAL_BACKGROUND_IMAGE);
        if (m3265a == null) {
            ThemeFramework.AbstractC2016e m3258b = ThemeManager.m3258b(ThemeElement.SETTING_BACKGROUND);
            m3265a = m3258b != null ? m3258b.getWildCardDrawable() : null;
            if (m3265a == null) {
                m3265a = new ColorDrawable(this.f3915f.getResources().getColor(R.color.slide_menu_background));
            }
        }
        this.f3913d.setBackground(m3265a);
    }

    /* renamed from: c */
    public void m6872c() {
        m6861h();
        m6860i();
        this.f3916g.m1742a();
        this.f3911b.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GlobalMenuDialog.java */
    /* renamed from: com.sds.android.ttpod.component.d.a.d$a */
    /* loaded from: classes.dex */
    public final class C1150a extends PagerAdapter {

        /* renamed from: b */
        private Context f3931b;

        /* renamed from: c */
        private ArrayList<List<GlobalMenuItem>> f3932c;

        /* renamed from: d */
        private View.OnClickListener f3933d;

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6855a(List<GlobalMenuItem> list, List<GlobalMenuItem> list2) {
            this.f3932c = new ArrayList<>(2);
            this.f3932c.add(new ArrayList(list));
            this.f3932c.add(new ArrayList(list2));
        }

        private C1150a(Context context) {
            this.f3933d = new View.OnClickListener() { // from class: com.sds.android.ttpod.component.d.a.d.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!ViewUtils.m4641a()) {
                        C1153c c1153c = (C1153c) view.getTag();
                        int m6986b = c1153c.f3940f.m6986b();
                        if (m6986b == 3) {
                            //new SUserEvent("PAGE_CLICK", SAction.ACTION_GLOBAL_MENU_PLAY_MODE.getValue(), SPage.PAGE_GLOBAL_MENU.getValue(), SPage.PAGE_NONE.getValue()).append("play_mode", Integer.valueOf(Preferences.m2862l().ordinal())).post();
                            EntryUtils.m8303a();
                            return;
                        }
                        switch (m6986b) {
                            case 0:
                            case 6:
                                c1153c.m6846a(false);
                                break;
                            case 4:
                                c1153c.m6846a(false);
                                GlobalMenuDialog.m6873b(false);
                                break;
                            case 5:
                                c1153c.m6846a(false);
                                GlobalMenuDialog.m6869c(false);
                                break;
                            case 12:
                                c1153c.m6846a(false);
                                break;
                        }
                        if (GlobalMenuDialog.this.f3912c != null) {
                            GlobalMenuDialog.this.f3912c.onMenuItemClicked(m6986b);
                        }
                    }
                }
            };
            this.f3931b = context;
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return this.f3932c.size();
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            SimpleGridView simpleGridView = new SimpleGridView(viewGroup.getContext());
            viewGroup.addView(simpleGridView);
            simpleGridView.setNumColumns(4);
            List<GlobalMenuItem> list = this.f3932c.get(i);
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                GlobalMenuItem globalMenuItem = list.get(i2);
                View inflate = View.inflate(this.f3931b, R.layout.menu_global_item, null);
                C1153c c1153c = new C1153c(inflate);
                inflate.setTag(c1153c);
                inflate.setOnClickListener(this.f3933d);
                m6856a(c1153c, globalMenuItem);
                c1153c.m6853a(globalMenuItem);
                simpleGridView.addView(inflate);
            }
            GlobalMenuDialog.this.m6876b();
            GlobalMenuDialog.this.m6883a();
            return simpleGridView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6859a() {
            if (GlobalMenuDialog.this.f3920k != null) {
                GlobalMenuDialog.this.f3920k.f3939e.setVisibility(Preferences.m2913bp() && Preferences.m2912bq() ? View.VISIBLE : View.GONE);
            }
        }

        /* renamed from: a */
        private void m6856a(C1153c c1153c, GlobalMenuItem globalMenuItem) {
            switch (globalMenuItem.m6986b()) {
                case 0:
                    globalMenuItem.m6987a(VersionUpdateModule.hasNewVersion() || Preferences.m2907bv());
                    return;
                case 1:
                    GlobalMenuDialog.this.f3918i = c1153c;
                    return;
                case 2:
                case 7:
                case 8:
                case 10:
                case 11:
                default:
                    return;
                case 3:
                    GlobalMenuDialog.this.f3919j = c1153c;
                    return;
                case 4:
                    globalMenuItem.m6987a(GlobalMenuDialog.f3908l);
                    return;
                case 5:
                    globalMenuItem.m6987a(GlobalMenuDialog.f3909m);
                    return;
                case 6:
                    globalMenuItem.m6987a(Preferences.m2992aO());
                    return;
                case 9:
                    GlobalMenuDialog.this.f3920k = c1153c;
                    m6859a();
                    return;
                case 12:
                    globalMenuItem.m6987a(Preferences.m2921bh());
                    return;
            }
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GlobalMenuDialog.java */
    /* renamed from: com.sds.android.ttpod.component.d.a.d$c */
    /* loaded from: classes.dex */
    public final class C1153c {

        /* renamed from: b */
        private final IconTextView f3936b;

        /* renamed from: c */
        private final TextView f3937c;

        /* renamed from: d */
        private final IconTextView f3938d;

        /* renamed from: e */
        private final View f3939e;

        /* renamed from: f */
        private GlobalMenuItem f3940f;

        private C1153c(View view) {
            this.f3936b = (IconTextView) view.findViewById(R.id.itv_icon);
            this.f3937c = (TextView) view.findViewById(R.id.tv_title);
            this.f3938d = (IconTextView) view.findViewById(R.id.itv_indicator);
            this.f3939e = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6853a(GlobalMenuItem globalMenuItem) {
            this.f3940f = globalMenuItem;
            this.f3937c.setText(globalMenuItem.m6984c());
            this.f3938d.setVisibility(globalMenuItem.m6983d() ? View.VISIBLE : View.GONE);
            ThemeUtils.m8172a(this.f3936b, globalMenuItem.m6982e(), globalMenuItem.m6990a(), ThemeElement.SETTING_TEXT);
            ThemeManager.m3269a(this.f3937c, ThemeElement.SETTING_TEXT);
            ThemeManager.m3269a(this.f3939e, ThemeElement.SETTING_ITEM_BACKGROUND);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6846a(boolean z) {
            this.f3940f.m6987a(z);
            this.f3938d.setVisibility(z ? View.VISIBLE : View.GONE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6847a(CharSequence charSequence) {
            this.f3937c.setText(charSequence);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m6854a(int i) {
            int[] iArr = {R.string.repeat_play, R.string.repeat_one_play, R.string.sequence_play, R.string.shuffle_play};
            String[] strArr = {ThemeElement.SETTING_PLAY_LOOP_IMAGE, ThemeElement.SETTING_PLAY_SINGLE_IMAGE, ThemeElement.SETTING_PLAY_SEQUENCE_IMAGE, ThemeElement.SETTING_PLAY_SHUFFLE_IMAGE};
            int[] iArr2 = {R.string.icon_repeat_play, R.string.icon_repeat_one_play, R.string.icon_sequence_play, R.string.icon_shuffle_play};
            this.f3940f.m6989a(iArr[i]);
            this.f3940f.m6988a(strArr[i]);
            this.f3940f.m6985b(iArr2[i]);
            m6853a(this.f3940f);
        }
    }

    /* renamed from: b */
    public static void m6873b(boolean z) {
        f3908l = z;
    }

    /* renamed from: c */
    public static void m6869c(boolean z) {
        f3909m = z;
    }
}
