package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.d */
/* loaded from: classes.dex */
public class IntEvaluator implements TypeEvaluator<Integer> {
    @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.TypeEvaluator
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public Integer mo6005a(float f, Integer num, Integer num2) {
        int intValue = num.intValue();
        return Integer.valueOf((int) (((num2.intValue() - intValue) * f) + intValue));
    }
}
