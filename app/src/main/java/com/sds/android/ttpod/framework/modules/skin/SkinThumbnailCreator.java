package com.sds.android.ttpod.framework.modules.skin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.skin.p129b.SSkinInfo;
import com.sds.android.ttpod.framework.modules.skin.p129b.SerializableSkin;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.methods.HttpGet;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.q */
/* loaded from: classes.dex */
public class SkinThumbnailCreator extends SkinReader implements Runnable {

    /* renamed from: a */
    public static final int f6693a = DisplayUtils.m7225c() >> 2;

    /* renamed from: c */
    public static final int f6694c = (int) ((f6693a * 1.5555556f) + 0.5f);

    /* renamed from: d */
    private static final String[] f6695d = {"/Preview", "/Preview.jpg", "/Preview.png", "/Background", "/Background.jpg", "/Background.png"};

    /* renamed from: e */
    private SkinItem f6696e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SkinThumbnailCreator(SkinItem skinItem) {
        this.f6696e = null;
        this.f6696e = skinItem;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0089 A[Catch: Exception -> 0x0097, TryCatch #7 {Exception -> 0x0097, blocks: (B:43:0x0080, B:45:0x0089, B:47:0x008e, B:49:0x0093), top: B:71:0x0080 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008e A[Catch: Exception -> 0x0097, TryCatch #7 {Exception -> 0x0097, blocks: (B:43:0x0080, B:45:0x0089, B:47:0x008e, B:49:0x0093), top: B:71:0x0080 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0093 A[Catch: Exception -> 0x0097, TRY_LEAVE, TryCatch #7 {Exception -> 0x0097, blocks: (B:43:0x0080, B:45:0x0089, B:47:0x008e, B:49:0x0093), top: B:71:0x0080 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean m3519a(String str, String str2) {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        boolean z = false;
        Throwable th;
        Exception e;
        BufferedInputStream bufferedInputStream = null;
        HttpRequest.Response m8708a = HttpRequest.m8708a(new HttpGet(str), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
        if (m8708a != null && m8708a.getStatusCode() == 200) {
            try {
                fileOutputStream = new FileOutputStream(str2);
                try {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(m8708a.getInputStream(), 8192);
                    try {
                        bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 8192);
                        int i = 0;
                        while (true) {
                            try {
                                int read = bufferedInputStream2.read();
                                if (read < 0) {
                                    if (i >= 10) {
                                        break;
                                    }
                                    i++;
                                } else {
                                    bufferedOutputStream.write(read);
                                }
                            } catch (Exception e1) {
                                e = e1;
                                bufferedInputStream = bufferedInputStream2;
                                bufferedOutputStream2 = bufferedOutputStream;
                                try {
                                    e.printStackTrace();
                                    try {
                                        m8708a.getInputStream().close();
                                        if (bufferedInputStream != null) {
                                            bufferedInputStream.close();
                                        }
                                        if (bufferedOutputStream2 != null) {
                                            bufferedOutputStream2.close();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    return z;
                                } catch (Throwable th1) {
                                    th = th1;
                                    bufferedOutputStream = bufferedOutputStream2;
                                    try {
                                        m8708a.getInputStream().close();
                                        if (bufferedInputStream != null) {
                                            bufferedInputStream.close();
                                        }
                                        if (bufferedOutputStream != null) {
                                            bufferedOutputStream.close();
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedInputStream = bufferedInputStream2;
                                m8708a.getInputStream().close();
                                if (bufferedInputStream != null) {
                                }
                                if (bufferedOutputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                throw th;
                            }
                        }
                        z = true;
                        try {
                            m8708a.getInputStream().close();
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    } catch (Exception e5) {
                        e = e5;
                        bufferedOutputStream2 = null;
                        bufferedInputStream = bufferedInputStream2;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedOutputStream = null;
                        bufferedInputStream = bufferedInputStream2;
                    }
                } catch (Exception e6) {
                    e = e6;
                    bufferedOutputStream2 = null;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedOutputStream = null;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
                bufferedOutputStream2 = null;
            } catch (Throwable th5) {
                th = th5;
                fileOutputStream = null;
                bufferedOutputStream = null;
            }
        }
        return z;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean a;
        Bitmap m4748a = ImageCacheUtils.m4748a(this.f6696e.getPath(), f6693a, f6694c);
        if ((m4748a == null && 4 == this.f6696e.m3575a()) || 3 == this.f6696e.m3575a()) {
            OnlineSkinItem m3566f = this.f6696e.m3566f();
            if (m3566f != null) {
                m3521a(m3566f);
                return;
            }
            return;
        }
        if (this.f6696e.f6658a == 0) {
            a = m3526e(this.f6696e.path);
        } else {
            a = m3530a(m3533a(this.f6696e.f6658a, this.f6696e.path));
        }
        if (a && m3522a()) {
            if (m4748a == null) {
                m3517b();
            }
            m3520a(this.f6696e);
        }
        handleClose();
    }

    /* renamed from: a */
    private boolean m3522a() {
        SSkinInfo m3516c = m3516c();
        if (m3516c == null) {
            return false;
        }
        this.f6696e.title = m3516c.m3778d();
        return true;
    }

    /* renamed from: b */
    private boolean m3517b() {
        Bitmap bitmap;
        byte[] bArr;
        String[] strArr = f6695d;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                bitmap = null;
                break;
            }
            try {
                bArr = this.packHandle.mo3753b(strArr[i]);
            } catch (IOException e) {
                e.printStackTrace();
                bArr = null;
            }
            if (bArr == null) {
                i++;
            } else {
                bitmap = m3518a(bArr);
                break;
            }
        }
        if (bitmap != null) {
            ImageCacheUtils.m4747a(this.f6696e.getPath(), f6693a, f6694c, bitmap);
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private void m3521a(OnlineSkinItem onlineSkinItem) {
        Bitmap decodeFile;
        String str = TTPodConfig.m5298j() + File.separator + FileUtils.getFilename(onlineSkinItem.getPictureUrl());
        if (m3519a(onlineSkinItem.getPictureUrl(), str) && (decodeFile = BitmapFactory.decodeFile(str)) != null) {
            ImageCacheUtils.m4747a(this.f6696e.getPath(), f6693a, f6694c, decodeFile);
        }
        m3520a(this.f6696e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: c */
    private SSkinInfo m3516c() {
        SSkinInfo sSkinInfo = null;
        BufferedReader k = m3524k();
        try {
            if (k != null) {
                try {
                    sSkinInfo = SerializableSkin.m3847a(k);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        k.close();

                    } catch (Exception e2) {
                        e2.printStackTrace();

                    }
                }
            }
            return sSkinInfo;
        } finally {
            try {
                k.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private Bitmap m3518a(byte[] bArr) {
        int i = f6693a;
        int i2 = f6694c;
        if (bArr == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        options.inInputShareable = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        if (i <= 0 || i2 <= 0) {
            options.inSampleSize = 4;
        } else if (options.outWidth >= (i2 << 1) || options.outHeight >= (i << 1)) {
            options.inSampleSize = Math.max(options.outWidth / i2, options.outHeight / i);
        }
        SkinModule.logD("SkinDecoder.decodeByteArrayBitmap-----inSampleSize: " + options.inSampleSize + " options.outWidth: " + options.outWidth + " options.outHeight: " + options.outHeight);
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private void m3520a(SkinItem skinItem) {
        SkinModule.logD("SkinThumbnailCreator.notifySkinThumbnailCreated--->");
        CommandCenter.getInstance().m4595b(new Command(CommandID.DECODE_SKIN_THUMBNAIL_FINISHED, skinItem), ModuleID.SKIN);
    }
}
