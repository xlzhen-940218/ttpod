package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.b */
/* loaded from: classes.dex */
public class FloatEvaluator implements TypeEvaluator<Number> {
    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.TypeEvaluator
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public Float mo6005a(float f, Number number, Number number2) {
        float floatValue = number.floatValue();
        return Float.valueOf(floatValue + ((number2.floatValue() - floatValue) * f));
    }
}
