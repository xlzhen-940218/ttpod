package com.sds.android.ttpod.component.lockscreen.p101a.p102a;

import android.view.View;
import com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty;
import com.sds.android.ttpod.component.lockscreen.p101a.p103b.IntProperty;
import com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property;
import com.sds.android.ttpod.component.lockscreen.p101a.p104c.p105a.AnimatorProxy;

/* renamed from: com.sds.android.ttpod.component.lockscreen.a.a.i */
/* loaded from: classes.dex */
final class PreHoneycombCompat {

    /* renamed from: a */
    static Property<View, Float> f4650a = new FloatProperty<View>("alpha") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.1
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5960a(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5961a());
        }
    };

    /* renamed from: b */
    static Property<View, Float> f4651b = new FloatProperty<View>("pivotX") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.7
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5954b(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5955b());
        }
    };

    /* renamed from: c */
    static Property<View, Float> f4652c = new FloatProperty<View>("pivotY") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.8
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5951c(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5952c());
        }
    };

    /* renamed from: d */
    static Property<View, Float> f4653d = new FloatProperty<View>("translationX") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.9
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5939i(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5936k());
        }
    };

    /* renamed from: e */
    static Property<View, Float> f4654e = new FloatProperty<View>("translationY") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.10
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5937j(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5934l());
        }
    };

    /* renamed from: f */
    static Property<View, Float> f4655f = new FloatProperty<View>("rotation") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.11
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5949d(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5950d());
        }
    };

    /* renamed from: g */
    static Property<View, Float> f4656g = new FloatProperty<View>("rotationX") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.12
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5947e(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5948e());
        }
    };

    /* renamed from: h */
    static Property<View, Float> f4657h = new FloatProperty<View>("rotationY") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.13
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5945f(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5946f());
        }
    };

    /* renamed from: i */
    static Property<View, Float> f4658i = new FloatProperty<View>("scaleX") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.14
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5943g(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5944g());
        }
    };

    /* renamed from: j */
    static Property<View, Float> f4659j = new FloatProperty<View>("scaleY") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.2
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5941h(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5942h());
        }
    };

    /* renamed from: k */
    static Property<View, Integer> f4660k = new IntProperty<View>("scrollX") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.3
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.IntProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5966a(View view, int i) {
            AnimatorProxy.m5956a(view).m5959a(i);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Integer mo5963a(View view) {
            return Integer.valueOf(AnimatorProxy.m5956a(view).m5940i());
        }
    };

    /* renamed from: l */
    static Property<View, Integer> f4661l = new IntProperty<View>("scrollY") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.4
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.IntProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5966a(View view, int i) {
            AnimatorProxy.m5956a(view).m5953b(i);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Integer mo5963a(View view) {
            return Integer.valueOf(AnimatorProxy.m5956a(view).m5938j());
        }
    };

    /* renamed from: m */
    static Property<View, Float> f4662m = new FloatProperty<View>("x") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.5
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5935k(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5932m());
        }
    };

    /* renamed from: n */
    static Property<View, Float> f4663n = new FloatProperty<View>("y") { // from class: com.sds.android.ttpod.component.lockscreen.a.a.i.6
        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.FloatProperty
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5968a(View view, float f) {
            AnimatorProxy.m5956a(view).m5933l(f);
        }

        @Override // com.sds.android.ttpod.component.lockscreen.p101a.p103b.Property
        /* renamed from: a */
        public Float mo5963a(View view) {
            return Float.valueOf(AnimatorProxy.m5956a(view).m5931n());
        }
    };
}
