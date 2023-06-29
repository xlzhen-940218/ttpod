package com.sds.android.ttpod.framework.modules.theme;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import androidx.core.view.ViewCompat;
import android.util.StateSet;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.p106a.C1780b;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.sds.android.ttpod.framework.modules.theme.b */
/* loaded from: classes.dex */
public class ThemeFramework {

    /* compiled from: ThemeFramework.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.b$e */
    /* loaded from: classes.dex */
    public static abstract class AbstractC2016e {

        /* renamed from: a */
        public static final String[] f6949a = {"LEFT_RIGHT", "TOP_BOTTOM", "RIGHT_LEFT", "BOTTOM_TOP", "TL_BR", "BL_TR", "TR_BL", "BR_TL"};

        /* renamed from: b */
        protected String f6950b;

        /* renamed from: c */
        protected Drawable f6951c;

        /* renamed from: d */
        protected Drawable f6952d;

        /* renamed from: e */
        protected Drawable f6953e;

        /* renamed from: f */
        protected int f6954f;

        /* renamed from: g */
        protected int f6955g;

        /* renamed from: h */
        protected int f6956h;

        /* renamed from: i */
        protected int f6957i;

        public AbstractC2016e(String str) {
            this.f6950b = str;
        }

        /* renamed from: a */
        public void mo3290a() {
            this.f6951c = null;
            this.f6952d = null;
            this.f6953e = null;
        }

        /* renamed from: c */
        public Drawable m3289c() {
            return this.f6951c;
        }

        /* renamed from: d */
        public Drawable m3288d() {
            return this.f6952d;
        }

        /* renamed from: e */
        public int m3287e() {
            return this.f6954f;
        }

        /* renamed from: f */
        public int m3286f() {
            return this.f6955g;
        }

        /* renamed from: g */
        public int m3285g() {
            return this.f6956h;
        }

        /* renamed from: h */
        public int m3284h() {
            return this.f6957i;
        }

        /* renamed from: b */
        public Object mo3277b() {
            if (this.f6953e == null) {
                if (this.f6952d != null && this.f6951c != null) {
                    this.f6953e = m3283i();
                } else {
                    this.f6953e = m3282j();
                }
            }
            return this.f6953e.getConstantState().newDrawable();
        }

        /* renamed from: i */
        private Drawable m3283i() {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, this.f6952d);
            stateListDrawable.addState(new int[]{16842913}, this.f6952d);
            stateListDrawable.addState(StateSet.WILD_CARD, this.f6951c);
            return stateListDrawable;
        }

        /* renamed from: j */
        private Drawable m3282j() {
            if (this.f6951c != null) {
                return this.f6951c;
            }
            if (this.f6952d != null) {
                return this.f6952d;
            }
            return null;
        }
    }

    /* compiled from: ThemeFramework.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.b$c */
    /* loaded from: classes.dex */
    public static class C2014c extends AbstractC2016e {
        public C2014c(String str) {
            super(str);
        }

        /* renamed from: c */
        private int m3300c(String str) {
            return m3299d(str);
        }

        /* renamed from: a */
        public static int m3304a(String str) {
            try {
                return m3301b(str);
            } catch (Exception e) {
                e.printStackTrace();
                return 0xff000000;
            }
        }

        /* renamed from: b */
        protected static int m3301b(String str) {
            String substring;
            if (str != null) {
                if (str.startsWith("#")) {
                    String trim = str.trim();
                    int indexOf = trim.indexOf(" ");
                    int i = -1;
                    if (indexOf > 0) {
                        i = Integer.parseInt(trim.substring(indexOf).trim());
                        substring = trim.substring(1, indexOf);
                    } else {
                        substring = trim.substring(1);
                    }
                    if (substring.length() != 6) {
                        throw new NumberFormatException(String.format("Color value '%s' is incorrect. Format is either#RRGGBB Alpha", substring));
                    }
                    String str2 = "FF" + substring;
                    if (i >= 0 && i <= 100) {
                        str2 = String.format("%02X", Integer.valueOf((int) (((i * 255.0f) / 100.0f) + 0.5f))) + str2.substring(2);
                    }
                    return (int) Long.parseLong(str2, 16);
                }
                throw new NumberFormatException(String.format("Color value '%s' must start with #", str));
            }
            throw new NumberFormatException();
        }


        /* renamed from: a */
        public void m3302a(XmlPullParser xmlPullParser) {
            int attributeCount = xmlPullParser.getAttributeCount();
            GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;
            List<Integer> list = null;
            for (int i = 0; i < attributeCount; i++) {
                String attributeName = xmlPullParser.getAttributeName(i);
                String attributeValue = xmlPullParser.getAttributeValue(i);
                if ("ID".equals(attributeName)) {
                    this.f6950b = attributeValue;
                } else if ("Normal".equals(attributeName)) {
                    list = m3298e(attributeValue);
                } else if ("Selected".equals(attributeName)) {
                    this.f6952d = new ColorDrawable(m3304a(attributeValue));
                } else if ("Width".equals(attributeName)) {
                    this.f6954f = m3299d(attributeValue);
                } else if ("Height".equals(attributeName)) {
                    this.f6955g = m3299d(attributeValue);
                } else if ("Orientation".equals(attributeName)) {
                    orientation = GradientDrawable.Orientation.valueOf(f6949a[Integer.parseInt(attributeValue)]);
                } else if ("CornerRadius".equals(attributeName)) {
                    this.f6956h = m3299d(attributeValue);
                } else if ("Padding".equals(attributeName)) {
                    this.f6957i = m3300c(attributeValue);
                }
            }
            this.f6951c = m3303a(list, orientation);
        }

        @Override // com.sds.android.ttpod.framework.modules.theme.ThemeFramework.AbstractC2016e
        /* renamed from: b */
        public Object mo3277b() {
            if (ThemeElement.isTextElementId(this.f6950b)) {
                return m3297i();
            }
            return super.mo3277b();
        }

        /* renamed from: i */
        private ColorStateList m3297i() {
            if (this.f6952d != null && this.f6951c != null) {
                return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, StateSet.WILD_CARD}, new int[]{ThemeManager.m3272a((ColorDrawable) this.f6952d), ThemeManager.m3272a((ColorDrawable) this.f6952d), ThemeManager.m3272a((ColorDrawable) this.f6951c)});
            }
            if (this.f6951c == null) {
                return null;
            }
            return new ColorStateList(new int[][]{StateSet.WILD_CARD}, new int[]{ThemeManager.m3272a((ColorDrawable) this.f6951c)});
        }

        /* renamed from: d */
        private int m3299d(String str) {
            try {
                return DisplayUtils.m7229a(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return -2;
            }
        }

        /* renamed from: a */
        protected Drawable m3303a(List<Integer> list, GradientDrawable.Orientation orientation) {
            int i = 0;
            if (list == null) {
                return null;
            }
            if (1 == list.size()) {
                return new ColorDrawable(list.get(0).intValue());
            }
            int[] iArr = new int[list.size()];
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    iArr[i2] = list.get(i2).intValue();
                    i = i2 + 1;
                } else {
                    return new GradientDrawable(orientation, iArr);
                }
            }
        }

        /* renamed from: e */
        private List<Integer> m3298e(String str) {
            String[] split = str.split(",");
            ArrayList arrayList = new ArrayList();
            for (String str2 : split) {
                arrayList.add(Integer.valueOf(m3304a(str2)));
            }
            return arrayList;
        }
    }

    /* compiled from: ThemeFramework.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.b$f */
    /* loaded from: classes.dex */
    public static class C2017f extends AbstractC2016e {

        /* renamed from: j */
        private static String[] f6958j = {m3274j() + ".png", "_n.png", ".png", ".9.png"};

        /* renamed from: k */
        private static String[] f6959k = {"_h.png", "_h.9.png"};

        public C2017f(String str, Bitmap bitmap) {
            this(str, bitmap, null);
        }

        public C2017f(String str, Bitmap bitmap, Bitmap bitmap2) {
            super(str);
            this.f6951c = m3279a(str, bitmap);
            this.f6952d = m3279a(str, bitmap2);
        }

        /* renamed from: a */
        public static C2017f m3280a(C2013b c2013b, String str) {
            Bitmap m3278a = m3278a(str, c2013b, f6958j);
            Bitmap m3278a2 = m3278a(str, c2013b, f6959k);
            if (m3278a != null && m3278a2 != null) {
                return new C2017f(str, m3278a, m3278a2);
            }
            if (m3278a != null) {
                return new C2017f(str, m3278a);
            }
            return null;
        }

        /* renamed from: a */
        private static Bitmap m3278a(String str, C2013b c2013b, String[] strArr) {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                Bitmap m3276b = m3276b(c2013b, str + strArr[i]);
                if (m3276b != null) {
                    return m3276b;
                }
            }
            return null;
        }

        /* renamed from: b */
        private static Bitmap m3276b(C2013b c2013b, String str) {
            if (!c2013b.m3315b(str)) {
                return null;
            }
            return c2013b.m3318a(str);
        }

        /* renamed from: j */
        private static String m3274j() {
            String m7221g = DisplayUtils.m7221g();
            if ("_xhdpi".equals(m7221g)) {
                return m7221g + (((double) DisplayUtils.m7223e()) > 2.0d ? FeedbackItem.STATUS_SOLVED : "1");
            }
            return m7221g;
        }

        /* renamed from: a */
        protected Drawable m3279a(String str, Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            Resources resources = BaseApplication.getApplication().getResources();
            if (m3281a(bitmap)) {
                return new NinePatchDrawable(resources, new NinePatch(bitmap, bitmap.getNinePatchChunk(), str));
            }
            return new BitmapDrawable(resources, bitmap);
        }

        /* renamed from: a */
        protected boolean m3281a(Bitmap bitmap) {
            byte[] ninePatchChunk = bitmap.getNinePatchChunk();
            return ninePatchChunk != null && NinePatch.isNinePatchChunk(ninePatchChunk);
        }

        @Override // com.sds.android.ttpod.framework.modules.theme.ThemeFramework.AbstractC2016e
        /* renamed from: i */
        public Drawable mo3277b() {
            return (Drawable) super.mo3277b();
        }
    }

    /* compiled from: ThemeFramework.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.b$a */
    /* loaded from: classes.dex */
    public static class C2012a extends AbstractC2016e {

        /* renamed from: j */
        private HashMap<String, AbstractC2016e> f6937j;

        public C2012a(String str) {
            super(str);
            this.f6937j = new HashMap<>();
        }

        @Override // com.sds.android.ttpod.framework.modules.theme.ThemeFramework.AbstractC2016e
        /* renamed from: a */
        public void mo3290a() {
            super.mo3290a();
            for (AbstractC2016e abstractC2016e : this.f6937j.values()) {
                if (abstractC2016e != null) {
                    abstractC2016e.mo3290a();
                }
            }
            this.f6937j.clear();
            this.f6937j = null;
        }

        /* renamed from: a */
        public void m3322a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
            int eventType = xmlPullParser.getEventType();
            C2014c c2014c = null;
            do {
                String name = xmlPullParser.getName();
                String attributeValue = xmlPullParser.getAttributeValue(null, "ID");
                if (2 == eventType) {
                    if ("Color".equals(name)) {
                        c2014c = new C2014c(attributeValue);
                        c2014c.m3302a(xmlPullParser);
                    }
                } else if (3 == eventType) {
                    if ("Color".equals(xmlPullParser.getName())) {
                        if (c2014c != null) {
                            this.f6937j.put(c2014c.f6950b, c2014c);
                        }
                    } else {
                        return;
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
        }

        /* renamed from: a */
        public Object m3323a(String str) {
            AbstractC2016e abstractC2016e = this.f6937j.get(str);
            if (abstractC2016e != null) {
                return abstractC2016e.mo3277b();
            }
            return null;
        }

        /* renamed from: b */
        public AbstractC2016e m3321b(String str) {
            return this.f6937j.get(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ThemeFramework.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.b$d */
    /* loaded from: classes.dex */
    public static class C2015d {

        /* renamed from: a */
        protected String f6944a;

        /* renamed from: c */
        protected String f6946c;

        /* renamed from: d */
        protected String f6947d;

        /* renamed from: b */
        protected ColorDrawable f6945b = null;

        /* renamed from: e */
        private HashMap<String, C2012a> f6948e = new HashMap<>();

        C2015d() {
        }

        /* renamed from: a */
        public HashMap<String, C2012a> m3296a() {
            return this.f6948e;
        }

        /* renamed from: b */
        public void m3292b() {
            this.f6948e.clear();
            this.f6948e = null;
        }

        /* renamed from: a */
        public void m3295a(InputStream inputStream) {
            C2012a m3293a;
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                XmlPullParser newPullParser = newInstance.newPullParser();
                try {
                    newPullParser.setInput(inputStream, null);
                    int eventType = newPullParser.getEventType();
                    do {
                        if (2 == eventType) {
                            String name = newPullParser.getName();
                            String attributeValue = newPullParser.getAttributeValue(null, "ID");
                            if (name.equals("View")) {
                                m3294a(newPullParser);
                            } else if (name.equals("Panel") && (m3293a = m3293a(newPullParser, attributeValue)) != null) {
                                this.f6948e.put(attributeValue, m3293a);
                            }
                        }
                        try {
                            eventType = newPullParser.next();
                            continue;
                        } catch (IOException e) {
                            e.printStackTrace();
                            continue;
                        }
                    } while (1 != eventType);
                } catch (XmlPullParserException e2) {
                    e2.printStackTrace();
                }
            } catch (XmlPullParserException e3) {
                e3.printStackTrace();
            }
        }

        /* renamed from: c */
        ColorDrawable m3291c() {
            if (this.f6945b != null) {
                return (ColorDrawable) this.f6945b.getConstantState().newDrawable();
            }
            return null;
        }

        /* renamed from: a */
        private void m3294a(XmlPullParser xmlPullParser) {
            int attributeCount = xmlPullParser.getAttributeCount();
            for (int i = 0; i < attributeCount; i++) {
                String attributeName = xmlPullParser.getAttributeName(i);
                String attributeValue = xmlPullParser.getAttributeValue(i);
                if (ThemeElement.STATUS_BAR_MODE.equals(attributeName)) {
                    this.f6944a = attributeValue;
                } else if (ThemeElement.BACKGROUND_MASK.equals(attributeName)) {
                    this.f6945b = new ColorDrawable(C2014c.m3304a(attributeValue));
                } else if ("BackgroundImage".equals(attributeName)) {
                    this.f6946c = attributeValue;
                } else if ("HomeBackgroundBlur".equals(attributeName)) {
                    this.f6947d = attributeName;
                }
            }
        }

        /* renamed from: a */
        private C2012a m3293a(XmlPullParser xmlPullParser, String str) {
            C2012a c2012a;
            XmlPullParserException e;
            IOException e2;
            c2012a = new C2012a(str);
            try {
                c2012a.m3322a(xmlPullParser);
            } catch (IOException e3) {
                e2 = e3;
                e2.printStackTrace();
                return c2012a;
            } catch (XmlPullParserException e4) {
                e = e4;
                e.printStackTrace();
                return c2012a;
            }
            return c2012a;
        }
    }

    /* compiled from: ThemeFramework.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.b$b */
    /* loaded from: classes.dex */
    public static class C2013b {

        /* renamed from: b */
        private C2012a f6939b;

        /* renamed from: d */
        private SkinCache f6941d;

        /* renamed from: a */
        private List<String> f6938a = new ArrayList();

        /* renamed from: e */
        private HashMap<String, C2017f> f6942e = new HashMap<>();

        /* renamed from: f */
        private C1780b f6943f = new C1780b();

        /* renamed from: c */
        private C2015d f6940c = new C2015d();

        C2013b(SkinCache skinCache) {
            this.f6941d = skinCache;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public static C2013b m3319a(SkinCache skinCache) {
            InputStream inputStream;
            C2013b c2013b = new C2013b(skinCache);
            if (skinCache == null || !skinCache.m3581g()) {
                inputStream = null;
            } else {
                try {
                    inputStream = new ByteArrayInputStream(skinCache.m3584d("/theme.xml"));
                } catch (Exception e) {
                    inputStream = null;
                }
                c2013b.m3310d();
            }
            if (inputStream == null) {
                c2013b.m3307e("theme");
                inputStream = m3309d("theme/theme.xml");
            }
            if (inputStream != null) {
                c2013b.f6940c.m3295a(inputStream);
                try {
                    inputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return c2013b;
        }

        /* renamed from: d */
        private static InputStream m3309d(String str) {
            try {
                return BaseApplication.getApplication().getResources().getAssets().open(str);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        public void m3320a() {
            if (this.f6939b != null) {
                this.f6939b.mo3290a();
                this.f6939b = null;
            }
            if (this.f6940c != null) {
                this.f6940c.m3292b();
                this.f6940c = null;
            }
            this.f6938a.clear();
            this.f6938a = null;
            this.f6942e.clear();
            this.f6942e = null;
            this.f6941d.clear();
            this.f6941d = null;
            System.gc();
        }

        /* renamed from: a */
        Bitmap m3318a(String str) {
            Bitmap m3586c = this.f6941d != null ? this.f6941d.m3586c('/' + str) : null;
            if (m3586c == null) {
                InputStream m3309d = m3309d("theme/" + str);
                try {
                    Bitmap m4780a = this.f6943f.m4780a(m3309d);
                    if (m3309d != null) {
                        try {
                            m3309d.close();
                            return m4780a;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return m4780a;
                        }
                    }
                    return m4780a;
                } catch (Throwable th) {
                    if (m3309d != null) {
                        try {
                            m3309d.close();
                            return null;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                }
            }
            return m3586c;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public Object m3317a(String str, String str2) {
            C2012a m3313c = m3313c();
            if (m3313c == null) {
                return null;
            }
            return m3313c.m3323a(m3306e(str, str2));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: b */
        public Drawable m3314b(String str, String str2) {
            String m3305f = m3305f(str, str2);
            C2017f c2017f = this.f6942e.get(m3305f);
            if (c2017f == null && (c2017f = C2017f.m3280a(this, m3305f)) != null) {
                this.f6942e.put(m3305f, c2017f);
            }
            if (c2017f != null) {
                return c2017f.mo3277b().getConstantState().newDrawable();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: b */
        public ColorDrawable m3316b() {
            return this.f6940c.m3291c();
        }

        /* renamed from: b */
        boolean m3315b(String str) {
            return this.f6938a != null && this.f6938a.contains(str);
        }

        /* renamed from: e */
        private String m3306e(String str, String str2) {
            if (str.equals(ThemeElement.PANEL_HOME)) {
                if (str2.equals("Text")) {
                    return "ContentText";
                }
                if (str2.equals("SubText")) {
                    return "SubContentText";
                }
                if (str2.equals("Background")) {
                    return "Content";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_TOP_BAR)) {
                if (str2.equals("Text")) {
                    return "TitleText";
                }
                if (str2.equals("SubText")) {
                    return "SubTitleText";
                }
                if (str2.equals("Background")) {
                    return ThemeElement.PANEL_TOP_BAR;
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SUB_BAR)) {
                if (str2.equals("Text")) {
                    return "TitleText";
                }
                if (str2.equals("SubText")) {
                    return "SubTitleText";
                }
                if (str2.equals("Background")) {
                    return ThemeElement.PANEL_SUB_BAR;
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SONG_LIST_ITEM)) {
                if (str2.equals("Text")) {
                    return "ContentText";
                }
                if (str2.equals("SubText")) {
                    return "SubContentText";
                }
                if (str2.equals("Background")) {
                    return "Content";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_CARD)) {
                if (str2.equals("Text")) {
                    return "ContentText";
                }
                if (str2.equals("SubText") || str2.equals("ControlText")) {
                    return "SubContentText";
                }
                if (str2.equals("Background")) {
                    return "Content";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_PLAY_BAR)) {
                if (str2.equals("Text")) {
                    return "TitleText";
                }
                if (str2.equals("SubText") || str2.equals("TimeText")) {
                    return "SubTitleText";
                }
                if (str2.equals("Background")) {
                    return "BottomBar";
                }
                return str2;
            } else {
                return str2;
            }
        }

        /* renamed from: f */
        private String m3305f(String str, String str2) {
            if (str.equals(ThemeElement.PANEL_TOP_BAR)) {
                if (str2.equals("Background")) {
                    return "top_bar_bkg";
                }
                if (str2.equals("Indicator")) {
                    return "top_bar_indicator";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SUB_BAR)) {
                if (str2.equals("Background")) {
                    return "sub_bar_bkg";
                }
                if (str2.equals("Indicator")) {
                    return "sub_bar_indicator";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SONG_LIST_ITEM)) {
                if (str2.equals("Indicator")) {
                    return "song_list_item_indicator";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_PLAY_BAR)) {
                if (str2.equals("Background")) {
                    return "play_bar_bkg2";
                }
                return str2;
            } else if (str.equals(ThemeElement.PANEL_SETTING) && str2.equals("Background")) {
                return "setting_background";
            } else {
                return str2;
            }
        }

        /* renamed from: c */
        C2012a m3312c(String str) {
            return this.f6940c.m3296a().get(str);
        }

        /* renamed from: c */
        C2012a m3313c() {
            if (this.f6939b == null) {
                this.f6939b = m3312c(ThemeElement.PANEL_COMMON);
            }
            return this.f6939b;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: c */
        public Object m3311c(String str, String str2) {
            C2012a m3312c = m3312c(str);
            if (m3312c != null) {
                return m3312c.m3323a(str2);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: d */
        public AbstractC2016e m3308d(String str, String str2) {
            C2012a m3312c = m3312c(str);
            if (m3312c != null) {
                return m3312c.m3321b(str2);
            }
            return null;
        }

        /* renamed from: d */
        private void m3310d() {
            Iterable<String> m3583e = this.f6941d.m3583e();
            if (m3583e != null) {
                for (String str : m3583e) {
                    if (str.endsWith(".png")) {
                        if (str.startsWith("/")) {
                            str = str.substring(1);
                        }
                        this.f6938a.add(str);
                    }
                }
            }
        }

        /* renamed from: e */
        private void m3307e(String str) {
            String[] list;
            try {
                for (String str2 : BaseApplication.getApplication().getAssets().list(str)) {
                    if (str2.endsWith(".png")) {
                        this.f6938a.add(str2);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
