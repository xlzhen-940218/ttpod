package com.sds.android.ttpod.framework.p106a;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.sds.android.sdk.core.p057a.ImageCache;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.BitmapUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.io.File;
import java.lang.ref.WeakReference;

/* renamed from: com.sds.android.ttpod.framework.a.f */
/* loaded from: classes.dex */
public final class ImageCacheUtils {

    /* renamed from: a */
    private static ImageCache f5631a;

    /* renamed from: b */
    private static C1780b f5632b;

    /* compiled from: ImageCacheUtils.java */
    /* renamed from: com.sds.android.ttpod.framework.a.f$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1786a {
        /* renamed from: a */
        Bitmap mo4732a(Bitmap bitmap);
    }

    /* renamed from: a */
    public static void m4757a() {
        f5631a = ImageCache.m8814a(0.05f, TTPodConfig.m5300h());
        f5632b = new C1780b();
    }

    /* renamed from: b */
    public static ImageCache m4743b() {
        return f5631a;
    }

    /* renamed from: a */
    public static final void m4752a(ImageView imageView, String str, int i, int i2, int i3) {
        m4751a(imageView, str, i, i2, i3, 0);
    }

    /* renamed from: a */
    public static final void m4751a(ImageView imageView, String str, int i, int i2, int i3, final int i4) {
        final WeakReference weakReference = new WeakReference(imageView);
        String m8811a = ImageCache.m8811a(str, i, i2, imageView.getScaleType());
        if (!StringUtils.isEmpty(m8811a)) {
            Bitmap m4746a = m4746a(m8811a, i, i2, (String) null);
            ImageView imageView2 = (ImageView) weakReference.get();
            if (imageView2 != null) {
                imageView2.setTag(m8811a);
            }
            if (m4746a == null) {
                ImageView imageView3 = (ImageView) weakReference.get();
                if (imageView3 != null) {
                    m4755a(imageView3, i3);
                }
                ImageView imageView4 = (ImageView) weakReference.get();
                if (imageView4 != null) {
                    f5631a.m8810a(m8811a, i, i2, imageView4.getScaleType(), new ImageCache.InterfaceC0565a() { // from class: com.sds.android.ttpod.framework.a.f.1
                        @Override // com.sds.android.sdk.core.p057a.ImageCache.InterfaceC0565a
                        /* renamed from: a */
                        public void mo4733a(String str2, int i5, int i6, Bitmap bitmap) {
                            ImageView imageView5 = (ImageView) weakReference.get();
                            if (imageView5 != null && imageView5.getTag().equals(str2)) {
                                ImageCacheUtils.m4737c(imageView5, bitmap, i4);
                            }
                        }
                    });
                    return;
                }
                return;
            }
            ImageView imageView5 = (ImageView) weakReference.get();
            if (imageView5 != null) {
                m4737c(imageView5, m4746a, i4);
                return;
            }
            return;
        }
        ImageView imageView6 = (ImageView) weakReference.get();
        if (imageView6 != null) {
            m4755a(imageView6, i3);
        }
    }

    /* renamed from: a */
    public static void m4744a(String str, String str2, int i, int i2) {
        f5631a.m8812a(str, i, i2);
        FileUtils.m8413b(f5631a.m8815a() + File.separator + SecurityUtils.C0610b.m8359b(str), str2);
    }

    /* renamed from: a */
    public static final Bitmap m4745a(String str, int i, int i2, boolean z) {
        Bitmap m4746a = m4746a(str, i, i2, (String) null);
        if (m4746a == null && !z && (m4746a = f5632b.m4779a(str, i, i2)) != null && f5631a != null) {
            f5631a.m8806a(str, (String) null, i, i2, m4746a);
        }
        return m4746a;
    }

    /* renamed from: a */
    public static final void m4750a(ImageView imageView, String str, int i, int i2, String str2) {
        if (!StringUtils.isEmpty(str)) {
            final WeakReference weakReference = new WeakReference(imageView);
            Bitmap m4746a = m4746a(str, i, i2, str2);
            if (m4746a != null) {
                ImageView imageView2 = (ImageView) weakReference.get();
                if (imageView2 != null) {
                    imageView2.setImageBitmap(m4746a);
                    return;
                }
                return;
            }
            Bitmap m4779a = f5632b.m4779a(str2, i, i2);
            if (m4779a != null) {
                ImageView imageView3 = (ImageView) weakReference.get();
                if (imageView3 != null) {
                    imageView3.setImageBitmap(m4779a);
                    return;
                }
                return;
            }
            ImageView imageView4 = (ImageView) weakReference.get();
            if (imageView4 != null) {
                imageView4.setTag(str);
            }
            f5631a.m8809a(str, i, i2, new ImageCache.InterfaceC0565a() { // from class: com.sds.android.ttpod.framework.a.f.2
                @Override // com.sds.android.sdk.core.p057a.ImageCache.InterfaceC0565a
                /* renamed from: a */
                public void mo4733a(String str3, int i3, int i4, Bitmap bitmap) {
                    ImageView imageView5 = (ImageView) weakReference.get();
                    if (imageView5 != null && imageView5.getTag() != null && imageView5.getTag().equals(str3)) {
                        imageView5.setImageBitmap(bitmap);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public static final void m4747a(String str, int i, int i2, Bitmap bitmap) {
        f5631a.m8812a(str, i, i2);
        f5631a.m8806a(str, (String) null, i, i2, bitmap);
        C1780b c1780b = f5632b;
        C1780b.m4777a(f5631a.m8815a() + File.separator + SecurityUtils.C0610b.m8359b(str), bitmap);
    }

    /* renamed from: a */
    public static final Bitmap m4748a(String str, int i, int i2) {
        Bitmap m4746a = m4746a(str, i, i2, (String) null);
        if (m4746a == null) {
            m4746a = f5632b.m4779a(f5631a.m8815a() + File.separator + SecurityUtils.C0610b.m8359b(str), i, i2);
            if (m4746a != null && f5631a != null) {
                f5631a.m8806a(str, (String) null, i, i2, m4746a);
            }
        }
        return m4746a;
    }

    /* renamed from: b */
    public static final void m4739b(String str, int i, int i2) {
        f5631a.m8812a(str, i, i2);
    }

    /* renamed from: a */
    private static void m4755a(ImageView imageView, int i) {
        try {
            imageView.setImageResource(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m4737c(ImageView imageView, Bitmap bitmap, int i) {
        if (i != 0) {
            imageView.setAnimation(AnimationUtils.loadAnimation(imageView.getContext(), i));
        }
        imageView.setImageBitmap(bitmap);
    }

    /* renamed from: b */
    public static final void m4740b(ImageView imageView, String str, int i, int i2, int i3, final int i4) {
        if (!StringUtils.isEmpty(str)) {
            Bitmap m4746a = m4746a(str, i, i2, (String) null);
            final WeakReference weakReference = new WeakReference(imageView);
            ImageView imageView2 = (ImageView) weakReference.get();
            if (imageView2 != null) {
                imageView2.setTag(str);
            }
            if (m4746a == null) {
                f5631a.m8809a(str, i, i2, new ImageCache.InterfaceC0565a() { // from class: com.sds.android.ttpod.framework.a.f.3
                    @Override // com.sds.android.sdk.core.p057a.ImageCache.InterfaceC0565a
                    /* renamed from: a */
                    public void mo4733a(String str2, int i5, int i6, Bitmap bitmap) {
                        ImageView imageView3 = (ImageView) weakReference.get();
                        if (imageView3 != null && imageView3.getTag() != null && imageView3.getTag().equals(str2)) {
                            ImageCacheUtils.m4736d(imageView3, bitmap, i4);
                            ImageCacheUtils.f5631a.m8806a(str2, (String) null, i5, i6, bitmap);
                        }
                    }
                });
                return;
            }
            ImageView imageView3 = (ImageView) weakReference.get();
            if (imageView3 != null) {
                m4736d(imageView3, m4746a, i4);
                return;
            }
            return;
        }
        ImageView imageView4 = (ImageView) new WeakReference(imageView).get();
        if (imageView4 != null) {
            m4754a(imageView4, i, i2, i3, i4);
        }
    }

    /* renamed from: a */
    private static void m4754a(ImageView imageView, int i, int i2, int i3, int i4) {
        try {
            m4736d(imageView, BitmapUtils.m8440b(BaseApplication.getApplication().getResources(), i3, i, i2), i4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m4736d(final ImageView imageView, Bitmap bitmap, final int i) {
        if (imageView != null && bitmap != null) {
            TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<Bitmap, Bitmap>(bitmap) { // from class: com.sds.android.ttpod.framework.a.f.4
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public Bitmap mo1981a(Bitmap bitmap2) {
                    try {
                        return C1780b.m4791a(BaseApplication.getApplication(), bitmap2, i);
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: b  reason: avoid collision after fix types in other method */
                public void postExecute(Bitmap bitmap2) {
                    imageView.setImageBitmap(bitmap2);
                }
            });
        }
    }

    /* renamed from: a */
    public static final void m4749a(IconTextView iconTextView, String str, int i, int i2, String str2, final InterfaceC1786a interfaceC1786a) {
        if (!StringUtils.isEmpty(str)) {
            final WeakReference weakReference = new WeakReference(iconTextView);
            Bitmap m4746a = m4746a(str, i, i2, str2);
            if (m4746a != null) {
                IconTextView iconTextView2 = (IconTextView) weakReference.get();
                if (iconTextView2 != null) {
                    iconTextView2.setImageDrawable(m4742b(m4746a, interfaceC1786a));
                    return;
                }
                return;
            }
            Bitmap m4779a = f5632b.m4779a(str2, i, i2);
            if (m4779a != null) {
                IconTextView iconTextView3 = (IconTextView) weakReference.get();
                if (iconTextView3 != null) {
                    iconTextView3.setImageDrawable(m4742b(m4779a, interfaceC1786a));
                    return;
                }
                return;
            }
            IconTextView iconTextView4 = (IconTextView) weakReference.get();
            if (iconTextView4 != null) {
                iconTextView4.setTag(str);
            }
            f5631a.m8809a(str, i, i2, new ImageCache.InterfaceC0565a() { // from class: com.sds.android.ttpod.framework.a.f.5
                @Override // com.sds.android.sdk.core.p057a.ImageCache.InterfaceC0565a
                /* renamed from: a */
                public void mo4733a(String str3, int i3, int i4, Bitmap bitmap) {
                    IconTextView iconTextView5 = (IconTextView) weakReference.get();
                    if (iconTextView5 != null && iconTextView5.getTag().equals(str3)) {
                        iconTextView5.setImageDrawable(ImageCacheUtils.m4742b(bitmap, interfaceC1786a));
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private static Bitmap m4746a(String str, int i, int i2, String str2) {
        if (f5631a == null) {
            m4757a();
        }
        return f5631a.m8807a(str, str2, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static BitmapDrawable m4742b(Bitmap bitmap, InterfaceC1786a interfaceC1786a) {
        if (interfaceC1786a != null) {
            bitmap = interfaceC1786a.mo4732a(bitmap);
        }
        return new BitmapDrawable(BaseApplication.getApplication().getResources(), bitmap);
    }
}
