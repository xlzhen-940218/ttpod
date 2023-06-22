package com.sds.android.ttpod.framework.modules.skin;

import android.content.res.AssetManager;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c */
/* loaded from: classes.dex */
public class CachedOnlineListReader {
    /* renamed from: a */
    public static String m3765a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || m3764b(str2)) {
            str2 = m3763b(str3, str);
        }
        return m3767a(str2);
    }

    /* renamed from: a */
    public static String m3766a(String str, String str2) {
        if (TextUtils.isEmpty(str) || m3764b(str)) {
            m3762c(str2, str);
        }
        return m3767a(str);
    }

    /* renamed from: b */
    private static boolean m3764b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (FileUtils.m8405g(str) == 0) {
            FileUtils.m8404h(str);
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static String m3767a(String str) {
        Throwable th;
        BufferedReader bufferedReader;
        String str2 = null;
        Exception e;
        if (!TextUtils.isEmpty(str) && !m3764b(str)) {
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
                } catch (Exception e1) {
                    e = e1;
                    bufferedReader = null;
                } catch (Throwable th2) {
                    th = th2;
                    m3768a((Closeable) null);
                    throw th;
                }
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    }
                    str2 = sb.toString();
                    m3768a(bufferedReader);
                } catch (FileNotFoundException e4) {
                    e = e4;
                    e.printStackTrace();
                    m3768a(bufferedReader);
                    return str2;
                } catch (IOException e5) {
                    e = e5;
                    e.printStackTrace();
                    m3768a(bufferedReader);
                    return str2;
                } catch (Exception e6) {
                    e = e6;
                    e.printStackTrace();
                    m3768a(bufferedReader);
                    return str2;
                }
            } catch (Throwable th3) {
                th = th3;
                m3768a((Closeable) null);
                //throw th;
            }
        }
        return str2;
    }

    /* renamed from: b */
    private static String m3763b(String str, String str2) {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        String[] list = new String[0];
        IOException e;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2 = null;
        AssetManager assets = BaseApplication.getApplication().getResources().getAssets();
        try {
            list = assets.list(str);
        } catch (IOException e1) {
            e = e1;
            bufferedOutputStream = null;
            bufferedInputStream = null;
        } catch (Throwable th1) {
            th = th1;
            bufferedInputStream = null;
        }
        if (list == null || list.length <= 0) {
            m3768a((Closeable) null);
            m3768a((Closeable) null);
            return null;
        }
        String str3 = str2 + File.separator + FileUtils.m8402j(list[0]);
        FileUtils.m8407e(str3);

        try {
            bufferedInputStream = new BufferedInputStream(assets.open(str + File.separator + list[0]));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
        } catch (IOException e2) {
            e = e2;
            bufferedOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            m3768a(bufferedInputStream);
            m3768a(bufferedOutputStream2);
            //throw th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    m3768a(bufferedInputStream);
                    m3768a(bufferedOutputStream);
                    return str3;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream2 = bufferedOutputStream;
            m3768a(bufferedInputStream);
            m3768a(bufferedOutputStream2);
            //throw th;
        }
        return str3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: c */
    private static void m3762c(String str, String str2) {
        Closeable bufferedInputStream = null;
        Closeable bufferedInputStream2 = null;
        BufferedOutputStream bufferedOutputStream = null;
        Closeable bufferedInputStream3 = null;
        Exception e;
        Throwable th;
        AssetManager assets = BaseApplication.getApplication().getResources().getAssets();
        try {
            FileUtils.m8407e(str2);
            bufferedInputStream = new BufferedInputStream(assets.open(str));
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            } catch (IOException e1) {
                e = e1;
                bufferedInputStream2 = null;
                bufferedInputStream3 = bufferedInputStream;
            } catch (Throwable th1) {
                th = th1;
            }
        } catch (IOException e2) {
            e = e2;
            bufferedInputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = ((BufferedInputStream)bufferedInputStream).read(bArr);
                if (read == -1) {
                    m3768a(bufferedInputStream);
                    m3768a(bufferedOutputStream);
                    return;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e3) {
            e = e3;
            bufferedInputStream3 = bufferedInputStream;
            bufferedInputStream2 = bufferedOutputStream;
            try {
                e.printStackTrace();
                m3768a(bufferedInputStream3);
                m3768a(bufferedInputStream2);
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = bufferedInputStream3;
                bufferedInputStream3 = bufferedInputStream2;
                m3768a(bufferedInputStream);
                m3768a(bufferedInputStream3);
                //throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream3 = bufferedOutputStream;
            m3768a(bufferedInputStream);
            m3768a(bufferedInputStream3);
            //throw th;
        }
    }

    /* renamed from: a */
    private static void m3768a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
