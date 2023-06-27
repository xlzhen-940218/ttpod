package com.sds.android.sdk.lib.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.sds.android.sdk.lib.util.d */
/* loaded from: classes.dex */
public class FileUtils {

    /* renamed from: a */
    private static String f2470a = File.separator;

    /* renamed from: b */
    private static char f2471b = File.separatorChar;

    /* renamed from: a */
    public static boolean m8419a(String str) {
        return !StringUtils.isEmpty(str) && new File(str).exists();
    }

    /* renamed from: b */
    public static boolean m8414b(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isFile();
    }

    /* renamed from: a */
    public static boolean m8418a(String str, long j) {
        return m8414b(str) && new File(str).setLastModified(j);
    }

    /* renamed from: c */
    public static long m8411c(String str) {
        if (m8414b(str)) {
            return new File(str).length();
        }
        return 0L;
    }

    /* renamed from: d */
    public static boolean isDir(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    /* renamed from: e */
    public static synchronized File createFile(String str) {
        File file = null;
        synchronized (FileUtils.class) {
            if (!StringUtils.isEmpty(str)) {
                File file2 = new File(str);
                if (file2.isFile()) {
                    file = file2;
                } else {
                    File parentFile = file2.getParentFile();
                    if (parentFile != null && (parentFile.isDirectory() || parentFile.mkdirs())) {
                        try {
                            if (file2.createNewFile()) {
                                file = file2;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return file;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (r1.mkdirs() != false) goto L14;
     */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized File createFolder(String str) {
        File file = null;
        synchronized (FileUtils.class) {
            if (!StringUtils.isEmpty(str)) {
                File file2 = new File(str);
                if (!file2.isDirectory()) {
                    file2.delete();
                    file2.mkdirs();
                }
                file = file2;
            }
        }
        return file;
    }

    /* renamed from: g */
    public static synchronized long m8405g(String str) {
        long m8421a;
        synchronized (FileUtils.class) {
            m8421a = StringUtils.isEmpty(str) ? 0L : m8421a(new File(str));
        }
        return m8421a;
    }

    /* renamed from: a */
    public static synchronized long m8421a(File file) {
        long j = 0;
        synchronized (FileUtils.class) {
            if (file != null) {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        long j2 = 0;
                        for (File file2 : listFiles) {
                            j2 += file2.isDirectory() ? m8421a(file2) : file2.length();
                        }
                        j = j2;
                    }
                } else {
                    j = file.length();
                }
            }
        }
        return j;
    }

    /* renamed from: a */
    public static synchronized void m8417a(String str, long j, String[] strArr) {
        synchronized (FileUtils.class) {
            long m8405g = m8405g(str);
            if (m8405g > j) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (strArr != null) {
                    for (String s : strArr)
                        arrayList.add(s);
                }
                File[] listFiles = new File(str).listFiles();
                if (listFiles != null) {
                    List<File> asList = Arrays.asList(listFiles);
                    try {
                        Collections.sort(asList, new Comparator<File>() { // from class: com.sds.android.sdk.lib.util.d.1
                            @Override // java.util.Comparator
                            /* renamed from: a */
                            public int compare(File file, File file2) {
                                if (file.lastModified() == file2.lastModified()) {
                                    return 0;
                                }
                                return file.lastModified() > file2.lastModified() ? -1 : 1;
                            }
                        });
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    for (File file : asList) {
                        if (m8405g <= j) {
                            break;
                        }
                        if (!arrayList.contains(file.getAbsolutePath())) {
                            file.lastModified();
                            m8405g -= file.length();
                            file.delete();
                        }
                        m8405g = m8405g;
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public static synchronized int m8415b(File file) {
        int i = 0;
        synchronized (FileUtils.class) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        i += m8415b(file2);
                    }
                    if (file2.delete()) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    /* renamed from: h */
    public static synchronized boolean exists(String path) {
        boolean z = false;
        synchronized (FileUtils.class) {
            if (!StringUtils.isEmpty(path)) {
                z = exists(new File(path));
            }
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x002f, code lost:
        if (r7.delete() != false) goto L25;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized boolean exists(File file) {
        File[] listFiles;
        boolean z = false;
        synchronized (FileUtils.class) {
            if (file == null) {
                z = true;
            } else {
                if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                    for (File file2 : listFiles) {
                        if (!exists(file2)) {
                            break;
                        }
                    }
                }
                if (file.exists()) {
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: i */
    public static String m8403i(String str) {
        String str2;
        if (str == null) {
            throw new NullPointerException("path should not be null.");
        }
        try {
            str2 = StringUtils.streamToString(new FileInputStream(str));
        } catch (Exception e) {
            e.printStackTrace();
            str2 = null;
        }
        return str2 != null ? str2 : "";
    }

    /* renamed from: a */
    public static synchronized boolean m8416a(String str, String str2) {
        File m8407e = null;
        boolean z = false;
        Exception e;
        Throwable th;
        synchronized (FileUtils.class) {
            if (str2 == null) {
                throw new NullPointerException("path should not be null.");
            }
            BufferedWriter bufferedWriter = null;
            try {
                try {
                    m8407e = createFile(str2);
                } catch (ArrayIndexOutOfBoundsException e2) {
                    e = e2;
                }
                if (m8407e == null) {
                    LogUtils.debug("FileUtils", "file == null path=%s", str2);
                    if (0 != 0) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } else {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(m8407e));
                    if (str == null) {
                        str = "";
                    }
                    try {
                        bufferedWriter2.write(str);
                        bufferedWriter2.flush();
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        z = true;
                    } catch (IOException e5) {
                        e = e5;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        }
                        return z;
                    } catch (ArrayIndexOutOfBoundsException e7) {
                        e = e7;
                        bufferedWriter = bufferedWriter2;
                        e.printStackTrace();
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e8) {
                                e8.printStackTrace();
                            }
                        }
                        return z;
                    } catch (Throwable th1) {
                        th = th1;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e9) {
                                e9.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static synchronized boolean m8420a(InputStream inputStream, String str) {
        File m8407e = null;
        int read;
        boolean z = false;
        Exception e;
        Throwable th;
        synchronized (FileUtils.class) {
            if (str == null) {
                throw new NullPointerException("path should not be null.");
            }
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    m8407e = createFile(str);
                } catch (Throwable th1) {
                    th = th1;
                }
            } catch (Exception e1) {
                e = e1;
            }
            if (m8407e == null) {
                LogUtils.debug("FileUtils", "inputStream file == null path=%s", str);
                if (0 != 0) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                try {
                    inputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else {
                byte[] bArr = new byte[4096];
                FileOutputStream fileOutputStream2 = null;
                try {
                    fileOutputStream2 = new FileOutputStream(m8407e);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                while (true) {
                    try {
                        read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    } catch (Exception e4) {
                        e = e4;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        try {
                            inputStream.close();
                        } catch (Exception e8) {
                            e8.printStackTrace();
                        }
                       // throw th;
                    }
                }
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
                try {
                    inputStream.close();
                } catch (Exception e10) {
                    e10.printStackTrace();
                }
                z = true;
               // fileOutputStream = read;
            }
        }
        return z;
    }

    /* renamed from: b */
    public static boolean m8413b(String str, String str2) {
        FileInputStream fileInputStream;
        Exception e;
        if (str == null || str2 == null) {
            throw new NullPointerException("path should not be null.");
        }
        try {
            fileInputStream = new FileInputStream(str2);
        } catch (FileNotFoundException e1) {
            e = e1;
            fileInputStream = null;
        }
        return m8420a(fileInputStream, str);
    }

    /* renamed from: j */
    public static String getFilename(String filename) {
        if (StringUtils.isEmpty(filename)) {
            return "";
        }
        int lastIndexOf = filename.lastIndexOf(63);
        if (lastIndexOf > 0) {
            filename = filename.substring(0, lastIndexOf);
        }
        int lastIndexOf2 = filename.lastIndexOf(f2471b);
        return lastIndexOf2 >= 0 ? filename.substring(lastIndexOf2 + 1) : filename;
    }

    /* renamed from: k */
    public static String m8401k(String str) {
        String m8402j = getFilename(str);
        int lastIndexOf = m8402j.lastIndexOf(46);
        return lastIndexOf > 0 ? m8402j.substring(0, lastIndexOf) : m8402j;
    }

    /* renamed from: l */
    public static String m8400l(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = (str == null || !str.startsWith(f2470a)) ? -1 : str.lastIndexOf(f2471b);
        return lastIndexOf == -1 ? f2470a : str.substring(0, lastIndexOf);
    }

    /* renamed from: m */
    public static String getSuffix(String str) {
        int lastIndexOf;
        if (!StringUtils.isEmpty(str)) {
            int lastIndexOf2 = str.lastIndexOf(63);
            if (lastIndexOf2 > 0) {
                str = str.substring(0, lastIndexOf2);
            }
            int lastIndexOf3 = str.lastIndexOf(47);
            if (lastIndexOf3 >= 0) {
                str = str.substring(lastIndexOf3 + 1);
            }
            if (str.length() > 0 && (lastIndexOf = str.lastIndexOf(46)) >= 0) {
                return str.substring(lastIndexOf + 1);
            }
        }
        return "";
    }

    /* renamed from: n */
    public static long m8398n(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0L;
        }
        return new File(str).lastModified();
    }

    /* renamed from: c */
    public static boolean m8410c(String str, String str2) {
        File file = new File(str);
        return file.isFile() && file.renameTo(new File(str2));
    }

    /* renamed from: o */
    public static String removeWrongCharacter(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", "");
    }

    /* renamed from: p */
    public static String m8396p(String str) {
        try {
            return new File(str).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: d */
    public static boolean m8408d(String str, String str2) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String str3 = str + File.separator + valueOf.toString();
        boolean z = false;
        createFile(str3);
        if (m8419a(str3) && m8419a(str2 + File.separator + valueOf)) {
            z = true;
        }
        exists(str3);
        return z;
    }
}
