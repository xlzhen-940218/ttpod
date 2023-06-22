package com.sds.android.ttpod.component.landscape.p098b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.Xml;
import com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem;
import com.sds.android.ttpod.component.landscape.p100d.Color4F;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: com.sds.android.ttpod.component.landscape.b.g */
/* loaded from: classes.dex */
public class ParticleSystemWithConfig extends BaseParticleSystem implements NextEffect {

    /* renamed from: h */
    protected int f4487h;

    /* renamed from: i */
    protected ArrayList<BaseParticleSystem.C1255b> f4488i;

    /* renamed from: j */
    protected ArrayList<BaseParticleSystem> f4489j;

    /* renamed from: k */
    protected Context f4490k;

    /* renamed from: p */
    private ArrayList<ArrayList<BaseParticleSystem.C1255b>> f4491p;

    /* renamed from: n */
    private static final String f4485n = TTPodConfig.m5284x() + File.separator + "particle_effect_configuration.xml";

    /* renamed from: g */
    protected static final String f4484g = "landscape" + File.separator;

    /* renamed from: o */
    private static final String f4486o = f4484g + "particle_effect_configuration.xml";

    @Override // com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem, com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.p098b.Node
    /* renamed from: a */
    public void mo6188a() {
        super.mo6188a();
        Iterator<ArrayList<BaseParticleSystem.C1255b>> it = this.f4491p.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.f4491p.clear();
        this.f4488i.clear();
        if (this.f4489j != null) {
            this.f4489j.clear();
        }
    }

    public ParticleSystemWithConfig(Context context) {
        this.f4490k = context;
        m6269k();
        this.f4487h = Preferences.m2950au();
        if (this.f4487h >= this.f4491p.size()) {
            this.f4487h = this.f4491p.size() - 1;
        }
        this.f4488i = this.f4491p.get(this.f4487h);
        m6268l();
    }

    /* renamed from: k */
    protected void m6269k() {
        try {
            InputStream open = this.f4490k.getAssets().open(f4486o);
            this.f4491p = m6275a(open);
            open.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public float m6274a(XmlPullParser xmlPullParser) throws Throwable {
        String nextText = xmlPullParser.nextText();
        if (nextText != null) {
            return Float.parseFloat(nextText);
        }
        return 0.0f;
    }

    /* renamed from: a */
    protected void m6273a(XmlPullParser xmlPullParser, PointF pointF) throws Throwable {
        String attributeValue = xmlPullParser.getAttributeValue(null, "x");
        if (attributeValue != null) {
            pointF.x = Float.parseFloat(attributeValue);
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "y");
        if (attributeValue2 != null) {
            pointF.y = Float.parseFloat(attributeValue2);
        }
    }

    /* renamed from: a */
    protected void m6271a(XmlPullParser xmlPullParser, Color4F color4F) throws Throwable {
        String attributeValue = xmlPullParser.getAttributeValue(null, "r");
        if (attributeValue != null) {
            color4F.m6155a(Float.parseFloat(attributeValue));
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "g");
        if (attributeValue2 != null) {
            color4F.m6152b(Float.parseFloat(attributeValue2));
        }
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "b");
        if (attributeValue3 != null) {
            color4F.m6150c(Float.parseFloat(attributeValue3));
        }
        String attributeValue4 = xmlPullParser.getAttributeValue(null, "a");
        if (attributeValue4 != null) {
            color4F.m6147d(Float.parseFloat(attributeValue4));
        }
    }

    /* renamed from: b */
    protected int m6270b(XmlPullParser xmlPullParser) throws Throwable {
        String nextText = xmlPullParser.nextText();
        if (nextText != null) {
            return Integer.parseInt(nextText);
        }
        return 0;
    }

    /* renamed from: a */
    private void m6272a(XmlPullParser xmlPullParser, BaseParticleSystem.C1255b c1255b, String str) throws Throwable {
        if ("life".equals(str)) {
            c1255b.f4448y = m6274a(xmlPullParser);
        } else if ("life_var".equals(str)) {
            c1255b.f4449z = m6274a(xmlPullParser);
        } else if ("emit_angle".equals(str)) {
            c1255b.f4425b = m6274a(xmlPullParser);
        } else if ("emit_angle_var".equals(str)) {
            c1255b.f4426c = m6274a(xmlPullParser);
        } else if ("emit_angle_delta".equals(str)) {
            c1255b.f4427d = m6274a(xmlPullParser);
        } else if ("speed".equals(str)) {
            c1255b.f4428e = m6274a(xmlPullParser);
        } else if ("speed_var".equals(str)) {
            c1255b.f4429f = m6274a(xmlPullParser);
        } else if ("position".equals(str)) {
            m6273a(xmlPullParser, c1255b.f4430g);
        } else if ("position_var".equals(str)) {
            m6273a(xmlPullParser, c1255b.f4431h);
        } else if ("revolution_angle".equals(str)) {
            c1255b.f4432i = m6274a(xmlPullParser);
        } else if ("revolution_angle_var".equals(str)) {
            c1255b.f4433j = m6274a(xmlPullParser);
        } else if ("revolution_angle_delta".equals(str)) {
            c1255b.f4434k = m6274a(xmlPullParser);
        } else if ("revolution_angle_delta_var".equals(str)) {
            c1255b.f4435l = m6274a(xmlPullParser);
        } else if ("start_revolution_radius".equals(str)) {
            c1255b.f4436m = m6274a(xmlPullParser);
        } else if ("start_revolution_radius_var".equals(str)) {
            c1255b.f4437n = m6274a(xmlPullParser);
        } else if ("end_revolution_radius".equals(str)) {
            c1255b.f4438o = m6274a(xmlPullParser);
        } else if ("end_revolution_radius_var".equals(str)) {
            c1255b.f4439p = m6274a(xmlPullParser);
        } else if ("rotate_angle".equals(str)) {
            c1255b.f4440q = m6274a(xmlPullParser);
        } else if ("rotate_angle_var".equals(str)) {
            c1255b.f4441r = m6274a(xmlPullParser);
        } else if ("rotate_angle_delta".equals(str)) {
            c1255b.f4442s = m6274a(xmlPullParser);
        } else if ("rotate_angle_delta_var".equals(str)) {
            c1255b.f4443t = m6274a(xmlPullParser);
        } else if ("start_width_size".equals(str)) {
            c1255b.f4444u = m6274a(xmlPullParser);
        } else if ("start_width_size_var".equals(str)) {
            c1255b.f4445v = m6274a(xmlPullParser);
        } else if ("end_width_size".equals(str)) {
            c1255b.f4446w = m6274a(xmlPullParser);
        } else if ("end_width_size_var".equals(str)) {
            c1255b.f4447x = m6274a(xmlPullParser);
        } else if ("start_color".equals(str)) {
            m6271a(xmlPullParser, c1255b.f4417A);
        } else if ("start_color_var".equals(str)) {
            m6271a(xmlPullParser, c1255b.f4418B);
        } else if ("end_color".equals(str)) {
            m6271a(xmlPullParser, c1255b.f4419C);
        } else if ("end_color_var".equals(str)) {
            m6271a(xmlPullParser, c1255b.f4420D);
        } else if ("total_count".equals(str)) {
            c1255b.f4421E = m6270b(xmlPullParser);
        } else if ("emit_count".equals(str)) {
            c1255b.f4422F = m6274a(xmlPullParser);
        } else if ("particle_image".equals(str)) {
            c1255b.f4423G = xmlPullParser.nextText();
        }
    }

    /* renamed from: a */
    private ArrayList<ArrayList<BaseParticleSystem.C1255b>> m6275a(InputStream inputStream) throws Throwable {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "UTF-8");
        ArrayList<BaseParticleSystem.C1255b> arrayList = null;
        ArrayList<ArrayList<BaseParticleSystem.C1255b>> arrayList2 = null;
        BaseParticleSystem.C1255b c1255b = null;
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case 0:
                    arrayList2 = new ArrayList<>();
                    break;
                case 2:
                    String name = newPullParser.getName();
                    if ("particle_effect_list".equals(name)) {
                        arrayList = new ArrayList<>();
                        break;
                    } else if (arrayList == null) {
                        break;
                    } else if ("particle_effect".equals(name)) {
                        c1255b = new BaseParticleSystem.C1255b();
                        break;
                    } else if (c1255b != null) {
                        m6272a(newPullParser, c1255b, name);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if ("particle_effect".equals(newPullParser.getName())) {
                        arrayList.add(c1255b);
                        c1255b = null;
                        break;
                    } else if ("particle_effect_list".equals(newPullParser.getName())) {
                        arrayList2.add(arrayList);
                        arrayList = null;
                        break;
                    } else {
                        break;
                    }
            }
        }
        return arrayList2;
    }

    /* renamed from: l */
    protected void m6268l() {
        Bitmap bitmap;
        if (this.f4489j != null) {
            Iterator<BaseParticleSystem> it = this.f4489j.iterator();
            while (it.hasNext()) {
                it.next().mo6188a();
            }
            this.f4489j.clear();
        }
        this.f4489j = new ArrayList<>();
        Bitmap bitmap2 = null;
        Iterator<BaseParticleSystem.C1255b> it2 = this.f4488i.iterator();
        int i = 0;
        while (it2.hasNext()) {
            BaseParticleSystem.C1255b next = it2.next();
            next.f4424a = i;
            BaseParticleSystem mo6221a = mo6221a(next);
            try {
                bitmap = BitmapFactory.decodeStream(this.f4490k.getAssets().open(f4484g + next.f4423G));
            } catch (IOException e) {
                e.printStackTrace();
                bitmap = bitmap2;
            }
            mo6221a.m6322a(bitmap, true);
            this.f4489j.add(mo6221a);
            i++;
            bitmap2 = bitmap;
        }
    }

    /* renamed from: a */
    protected BaseParticleSystem mo6221a(BaseParticleSystem.C1255b c1255b) {
        return new BaseParticleSystem(c1255b);
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem, com.sds.android.ttpod.component.landscape.p098b.Scene, com.sds.android.ttpod.component.landscape.Scheduler.InterfaceC1275a
    /* renamed from: a */
    public void mo6105a(float f) {
        if (this.f4489j != null) {
            Iterator<BaseParticleSystem> it = this.f4489j.iterator();
            while (it.hasNext()) {
                it.next().mo6105a(f);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.BaseParticleSystem, com.sds.android.ttpod.component.landscape.p098b.Scene
    /* renamed from: b */
    public void mo6185b() {
        if (this.f4489j != null) {
            Iterator<BaseParticleSystem> it = this.f4489j.iterator();
            while (it.hasNext()) {
                it.next().mo6185b();
            }
        }
    }

    @Override // com.sds.android.ttpod.component.landscape.p098b.NextEffect
    /* renamed from: f_ */
    public synchronized void mo6214f_() {
        int size = this.f4491p.size();
        this.f4487h++;
        if (this.f4487h >= size) {
            this.f4487h = 0;
        }
        Preferences.m2857m(this.f4487h);
        this.f4488i = this.f4491p.get(this.f4487h);
        m6268l();
    }
}
