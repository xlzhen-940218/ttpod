package com.sds.android.ttpod.component.p090f;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.p106a.CodeIdentifyInputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: com.sds.android.ttpod.component.f.a */
/* loaded from: classes.dex */
public class SceneFactory {
    /* renamed from: a */
    public static GLSurfaceView.Renderer m6632a(Context context, SkinCache skinCache) {
        SnowSceneRenderer snowSceneRenderer = null;
        if (skinCache != null) {
            skinCache.m3581g();
            try {
                CodeIdentifyInputStreamReader codeIdentifyInputStreamReader = new CodeIdentifyInputStreamReader(new ByteArrayInputStream(skinCache.m3584d("/scene.xml")));
                KXmlParser kXmlParser = new KXmlParser();
                kXmlParser.setInput(codeIdentifyInputStreamReader);
                kXmlParser.nextTag();
                kXmlParser.require(2, null, "Scene");
                if ("Snow".equals(kXmlParser.getAttributeValue(null, "Name"))) {
                    snowSceneRenderer = m6631a(context, skinCache, kXmlParser);
                } else {
                    skinCache.handleClose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                skinCache.handleClose();
            }
        }
        return snowSceneRenderer;
    }

    /* renamed from: a */
    private static SnowSceneRenderer m6631a(Context context, SkinCache skinCache, KXmlParser kXmlParser) throws IOException, XmlPullParserException {
        Bitmap m3586c;
        SnowSceneRenderer snowSceneRenderer = new SnowSceneRenderer(context);
        String attributeValue = kXmlParser.getAttributeValue(null, "SnowTexture");
        if (attributeValue != null && (m3586c = skinCache.loadTskBitmap(attributeValue)) != null) {
            snowSceneRenderer.m6625a(m3586c);
        }
        snowSceneRenderer.m6629a(ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "WindSpeed"), snowSceneRenderer.m6613h()));
        snowSceneRenderer.m6622b(ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "Gravity"), snowSceneRenderer.m6612i()));
        snowSceneRenderer.m6619c(ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "MinStartXSpeed"), snowSceneRenderer.m6623b()), ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "MaxStartXSpeed"), snowSceneRenderer.m6620c()));
        snowSceneRenderer.m6621b(ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "MinStartYSpeed"), snowSceneRenderer.m6618d()), ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "MaxStartYSpeed"), snowSceneRenderer.m6616e()));
        snowSceneRenderer.m6628a(ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "MinStartZ"), snowSceneRenderer.m6615f()), ValueParser.parseFloat(kXmlParser.getAttributeValue(null, "MaxStartZ"), snowSceneRenderer.m6614g()));
        snowSceneRenderer.m6626a(ValueParser.parseInt(kXmlParser.getAttributeValue(null, "SnowNumber"), snowSceneRenderer.m6630a()));
        return snowSceneRenderer;
    }
}
