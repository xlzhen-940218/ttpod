package com.sds.android.sdk.lib.util;

import com.sds.android.sdk.lib.p064d.OriginalByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* renamed from: com.sds.android.sdk.lib.util.o */
/* loaded from: classes.dex */
public class ZIPUtils {
    /* renamed from: a */
    public static OriginalByteArrayOutputStream m8329a(String str) throws IOException {
        OriginalByteArrayOutputStream originalByteArrayOutputStream = new OriginalByteArrayOutputStream(str.length());
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(originalByteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes(), 0, str.getBytes().length);
        gZIPOutputStream.close();
        return originalByteArrayOutputStream;
    }

    /* renamed from: a */
    public static InputStream m8331a(InputStream inputStream) throws IOException {
        return new GZIPInputStream(inputStream);
    }

    /* renamed from: a */
    public static void m8330a(InputStream inputStream, String str) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    new File(str + File.separator + name.substring(0, name.length() - 1)).mkdirs();
                } else {
                    File file = new File(str + File.separator + name);
                    if (file.getParentFile() != null && !file.exists()) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            } else {
                zipInputStream.close();
                return;
            }
        }
    }
}
