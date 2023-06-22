package com.sds.android.ttpod.component.p091g.p092a;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import com.sds.android.ttpod.framework.modules.skin.view.Rotate3dAnimation;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.component.g.a.a */
/* loaded from: classes.dex */
public class AnimationCommand {

    /* renamed from: a */
    private final ArrayList<float[]> f4153a = new ArrayList<>(10);

    /* renamed from: a */
    public void m6589a(int i, int i2, int i3) {
        this.f4153a.add(new float[]{i, i2, i3});
    }

    /* renamed from: a */
    public void m6590a(float f, float f2, int i, int i2) {
        this.f4153a.add(new float[]{-1.0f, i, i2, f, f2});
    }

    /* renamed from: a */
    public void m6593a(float f, float f2, float f3, float f4, float f5, float f6, int i, int i2) {
        this.f4153a.add(new float[]{-2.0f, i, i2, f, f2, f3, f4, f5, f6});
    }

    /* renamed from: a */
    public void m6591a(float f, float f2, float f3, float f4, int i, int i2) {
        this.f4153a.add(new float[]{-3.0f, i, i2, f, f2, f3, f4});
    }

    /* renamed from: a */
    public void m6592a(float f, float f2, float f3, float f4, float f5, int i, int i2) {
        this.f4153a.add(new float[]{-4.0f, i, i2, f, f2, f3, f4, f5});
    }

    /* renamed from: a */
    public AnimationSet m6594a() {
        int i;
        int i2 = 0;
        if (m6587b() > 0) {
            AnimationSet animationSet = new AnimationSet(false);
            Iterator<float[]> it = this.f4153a.iterator();
            while (true) {
                i = i2;
                if (!it.hasNext()) {
                    break;
                }
                Animation m6588a = m6588a(it.next());
                if (m6588a != null) {
                    animationSet.addAnimation(m6588a);
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
            }
            if (i > 0) {
                return animationSet;
            }
            return null;
        }
        return null;
    }

    /* renamed from: b */
    public int m6587b() {
        int i = 0;
        if (this.f4153a.size() <= 0) {
            return 0;
        }
        Iterator<float[]> it = this.f4153a.iterator();
        while (true) {
            int i2 = i;
            if (it.hasNext()) {
                float[] next = it.next();
                i = (int) Math.max(next[2] + next[1], i2);
            } else {
                return i2;
            }
        }
    }

    /* renamed from: c */
    public int m6586c() {
        return this.f4153a.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public void m6585d() {
        this.f4153a.clear();
    }

    /* renamed from: a */
    private static Animation m6588a(float[] fArr) {
        Animation rotate3dAnimation;
        if (fArr[1] <= 0.0f) {
            return null;
        }
        switch ((int) fArr[0]) {
            case -4:
                rotate3dAnimation = new Rotate3dAnimation(fArr[3], fArr[4], fArr[5], fArr[6], fArr[7], false);
                break;
            case -3:
                rotate3dAnimation = new RotateAnimation(fArr[3], fArr[4], 1, fArr[5], 1, fArr[6]);
                break;
            case -2:
                rotate3dAnimation = new ScaleAnimation(fArr[3], fArr[4], fArr[5], fArr[6], 1, fArr[7], 1, fArr[8]);
                break;
            case -1:
                rotate3dAnimation = new AlphaAnimation(fArr[3], fArr[4]);
                break;
            case 0:
                rotate3dAnimation = new AlphaAnimation(0.0f, 1.0f);
                break;
            case 1:
                rotate3dAnimation = new AlphaAnimation(1.0f, 0.0f);
                break;
            case 2:
                rotate3dAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
                break;
            case 3:
                rotate3dAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                break;
            case 4:
                rotate3dAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                break;
            case 5:
                rotate3dAnimation = new RotateAnimation(360.0f, 0.0f, 1, 0.5f, 1, 0.5f);
                break;
            case 6:
                rotate3dAnimation = new Rotate3dAnimation(0.0f, 90.0f, 0.0f, false);
                break;
            case 7:
                rotate3dAnimation = new Rotate3dAnimation(90.0f, 0.0f, 0.0f, false);
                break;
            default:
                return null;
        }
        rotate3dAnimation.setDuration((int) fArr[1]);
        rotate3dAnimation.setStartOffset((int) fArr[2]);
        return rotate3dAnimation;
    }
}
