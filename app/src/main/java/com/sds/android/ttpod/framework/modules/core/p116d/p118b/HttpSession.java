package com.sds.android.ttpod.framework.modules.core.p116d.p118b;


import com.sds.android.sdk.lib.util.LogUtils;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;

/* renamed from: com.sds.android.ttpod.framework.modules.core.d.b.c */
/* loaded from: classes.dex */
class HttpSession implements Runnable {

    /* renamed from: c */
    private static SimpleDateFormat f6017c = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.CHINESE);

    /* renamed from: a */
    private Socket f6018a;

    /* renamed from: b */
    private HttpServer f6019b;

    /* renamed from: d */
    private FileOutputStream f6020d = null;

    /* renamed from: e */
    private boolean f6021e = true;

    static {
        f6017c.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public HttpSession(Socket socket, HttpServer httpServer) {
        this.f6018a = socket;
        this.f6019b = httpServer;
        Thread thread = new Thread(this);
        thread.setPriority(10);
        thread.setDaemon(true);
        thread.start();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                InputStream inputStream = this.f6018a.getInputStream();
                if (inputStream == null) {
                    if (this.f6020d != null) {
                        try {
                            this.f6020d.close();
                            this.f6020d = null;
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                byte[] bArr = new byte[32768];
                int read = inputStream.read(bArr, 0, 32768);
                if (read <= 0) {
                    if (this.f6020d != null) {
                        try {
                            this.f6020d.close();
                            this.f6020d = null;
                            return;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                LogUtils.debug("HttpSession", "header read:" + read + "bytes");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, read)));
                Properties properties = new Properties();
                Properties properties2 = new Properties();
                Properties properties3 = new Properties();
                m4194a(bufferedReader, properties, properties2, properties3);
                String property = properties.getProperty("method");
                String property2 = properties.getProperty("uri");
                long j = Long.MAX_VALUE;
                String property3 = properties3.getProperty("content-length");
                if (property3 != null) {
                    try {
                        j = Integer.parseInt(property3);
                    } catch (NumberFormatException e3) {
                        e3.printStackTrace();
                    }
                }
                int m4189a = m4189a(bArr, 0);
                int i = m4189a < read ? read - m4189a : 0;
                long j2 = (-1 == m4189a || j == Long.MAX_VALUE) ? 0L : j;
                if (property.equalsIgnoreCase("POST")) {
                    String m4186b = m4186b(properties3.getProperty("content-type"));
                    long j3 = 0;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
                    if (i > 0) {
                        byteArrayOutputStream.write(bArr, m4189a, i);
                        j3 = 0 + i;
                    }
                    boolean z = ((long) i) == j2 && j2 > 0;
                    while (true) {
                        if (!z && byteArrayOutputStream.size() < 32768) {
                            int read2 = inputStream.read(bArr, 0, 32768 - byteArrayOutputStream.size());
                            if (read2 > 0) {
                                byteArrayOutputStream.write(bArr, 0, read2);
                                j3 += read2;
                            }
                            if (j3 == j2 || -1 == read2) {
                                z = true;
                            }
                        }
                        m4187a(byteArrayOutputStream.toByteArray(), m4186b);
                        byteArrayOutputStream.reset();
                        if (z) {
                            break;
                        }
                    }
                    if (this.f6020d != null) {
                        this.f6020d.close();
                        this.f6020d = null;
                    }
                }
                HttpResponse m4212a = this.f6019b.m4212a(property2, property, properties3, properties2);
                if (m4212a == null) {
                    m4192a("500 Internal Server Error", "SERVER INTERNAL ERROR: Serve() returned a null response.");
                } else {
                    m4191a(m4212a.m4224a(), m4212a.m4222b(), m4212a.m4220d(), m4212a.m4221c());
                }
                inputStream.close();
                if (this.f6020d != null) {
                    try {
                        this.f6020d.close();
                        this.f6020d = null;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (IOException e5) {
                try {
                    m4195a();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                m4192a("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + e5.getMessage());
                if (this.f6020d != null) {
                    try {
                        this.f6020d.close();
                        this.f6020d = null;
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
            } catch (InterruptedException e8) {
                try {
                    m4195a();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
                if (this.f6020d != null) {
                    try {
                        this.f6020d.close();
                        this.f6020d = null;
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                }
            }
        } catch (Throwable th) {
            if (this.f6020d != null) {
                try {
                    this.f6020d.close();
                    this.f6020d = null;
                } catch (IOException e11) {
                    e11.printStackTrace();
                }
            }
            //throw th;
        }
    }

    /* renamed from: a */
    private void m4195a() throws IOException {
        File file;
        this.f6018a.close();
        if (this.f6020d != null && (file = new File(this.f6020d.getFD().toString())) != null) {
            file.delete();
        }
    }

    /* renamed from: a */
    private void m4187a(byte[] bArr, String str) throws IOException {
        byte[] bytes = ("--" + str + "\r\n").getBytes();
        long m4181a = KmpMatch.m4181a(bArr, 0, bytes);
        if (-1 == m4181a) {
            m4188a(bArr, 0, bArr.length, bytes);
            return;
        }
        int i = 0;
        while (-1 != m4181a) {
            if (!this.f6021e) {
                m4188a(bArr, i, (int) ((m4181a - i) - 2), bytes);
                this.f6021e = true;
            }
            long m4181a2 = KmpMatch.m4181a(bArr, ((int) m4181a) + bytes.length, bytes);
            if (m4181a2 == -1) {
                m4188a(bArr, (int) m4181a, (int) (bArr.length - (m4181a - 0)), bytes);
                this.f6021e = false;
                return;
            }
            this.f6021e = true;
            m4188a(bArr, (int) m4181a, (int) ((m4181a2 - m4181a) - 2), bytes);
            i = (int) m4181a2;
            m4181a = m4181a2;
        }
    }

    /* renamed from: a */
    private void m4188a(byte[] bArr, int i, int i2, byte[] bArr2) throws IOException {
        String m4193a;
        int m4189a;
        int i3;
        if (!this.f6021e) {
            if (this.f6020d != null) {
                this.f6020d.write(bArr, i, i2);
                this.f6020d.flush();
                return;
            }
            return;
        }
        if (this.f6020d != null) {
            try {
                this.f6020d.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.f6020d = null;
            this.f6021e = true;
        }
        if (m4185b(bArr, i) != -1 && (m4193a = m4193a(m4183c(bArr, i))) != null) {
            String m4201c = this.f6019b.m4201c();
            if (m4201c == null) {
                m4201c = System.getProperty("java.io.tmpdir");
            }
            try {
                this.f6020d = new FileOutputStream(new File(m4201c, m4193a));
                if (this.f6020d != null && -1 != (m4189a = m4189a(bArr, i))) {
                    long m4181a = KmpMatch.m4181a(bArr, m4189a, bArr2);
                    if (m4181a == -1) {
                        i3 = bArr.length - m4189a;
                    } else {
                        i3 = ((int) m4181a) - m4189a;
                    }
                    this.f6020d.write(bArr, m4189a, i3);
                    this.f6020d.flush();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private int m4189a(byte[] bArr, int i) {
        boolean z;
        while (true) {
            if (i >= bArr.length - 4) {
                z = false;
                break;
            }
            if (bArr[i] == 13) {
                i++;
                if (bArr[i] == 10) {
                    i++;
                    if (bArr[i] == 13) {
                        i++;
                        if (bArr[i] == 10) {
                            z = true;
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            i++;
        }
        int i2 = i + 1;
        if (z) {
            return i2;
        }
        return -1;
    }

    /* renamed from: b */
    private long m4185b(byte[] bArr, int i) {
        return KmpMatch.m4181a(bArr, i, "Content-Disposition:".getBytes());
    }

    /* renamed from: c */
    private String m4183c(byte[] bArr, int i) {
        int m4185b = (int) m4185b(bArr, i);
        if (-1 == m4185b) {
            return null;
        }
        int i2 = m4185b;
        while (i2 < bArr.length && bArr[i2] != 13) {
            i2++;
        }
        if (bArr[i2] == 13) {
            return new String(bArr, m4185b, i2 - m4185b);
        }
        return null;
    }

    /* renamed from: a */
    private String m4193a(String str) {
        int indexOf;
        if (str.length() == 0 || str.indexOf("Content-Disposition") != 0 || -1 == (indexOf = str.indexOf("filename="))) {
            return null;
        }
        return str.substring("filename=".length() + indexOf).replace("\"", "");
    }

    /* renamed from: b */
    private String m4186b(String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf("boundary=")) < 0) {
            return null;
        }
        return str.substring(indexOf + "boundary=".length());
    }

    /* renamed from: a */
    private void m4194a(BufferedReader bufferedReader, Properties properties, Properties properties2, Properties properties3) throws InterruptedException {
        String m4184c;
        try {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                if (!stringTokenizer.hasMoreTokens()) {
                    m4192a("400 Bad Request", "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                }
                properties.put("method", stringTokenizer.nextToken());
                if (!stringTokenizer.hasMoreTokens()) {
                    m4192a("400 Bad Request", "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                }
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(63);
                if (indexOf >= 0) {
                    m4190a(nextToken.substring(indexOf + 1), properties2);
                    m4184c = m4184c(nextToken.substring(0, indexOf));
                } else {
                    m4184c = m4184c(nextToken);
                }
                if (stringTokenizer.hasMoreTokens()) {
                    String readLine2 = bufferedReader.readLine();
                    while (readLine2 != null && readLine2.trim().length() > 0) {
                        int indexOf2 = readLine2.indexOf(58);
                        if (indexOf2 >= 0) {
                            properties3.put(readLine2.substring(0, indexOf2).trim().toLowerCase(), readLine2.substring(indexOf2 + 1).trim());
                        }
                        readLine2 = bufferedReader.readLine();
                    }
                }
                properties.put("uri", m4184c);
            }
        } catch (IOException e) {
            m4192a("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
        }
    }

    /* renamed from: c */
    private String m4184c(String str) throws InterruptedException {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                switch (charAt) {
                    case '%':
                        stringBuffer.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
                        i += 2;
                        break;
                    case '+':
                        stringBuffer.append(' ');
                        break;
                    default:
                        stringBuffer.append(charAt);
                        break;
                }
                i++;
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            m4192a("400 Bad Request", "BAD REQUEST: Bad percent-encoding.");
            return null;
        }
    }

    /* renamed from: a */
    private void m4190a(String str, Properties properties) throws InterruptedException {
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(61);
                if (indexOf >= 0) {
                    properties.put(m4184c(nextToken.substring(0, indexOf)).trim(), m4184c(nextToken.substring(indexOf + 1)));
                }
            }
        }
    }

    /* renamed from: a */
    private void m4192a(String str, String str2) throws InterruptedException {
        m4191a(str, "text/plain", (Properties) null, new ByteArrayInputStream(str2.getBytes()));
        throw new InterruptedException();
    }

    /* renamed from: a */
    private void m4191a(String str, String str2, Properties properties, InputStream inputStream) {
        try {
            try {
                if (str == null) {
                    throw new Error("sendResponse: Status can't be null.");
                }
                OutputStream outputStream = this.f6018a.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.print("HTTP/1.1 " + str + " \r\n");
                if (str2 != null) {
                    printWriter.print("Content-Type: " + str2 + "\r\n");
                }
                if (properties == null || properties.getProperty("Date") == null) {
                    printWriter.print("Date: " + f6017c.format(new Date()) + "\r\n");
                }
                if (properties != null) {
                    printWriter.print("Connection: close\r\n");
                    Enumeration keys = properties.keys();
                    while (keys.hasMoreElements()) {
                        String str3 = (String) keys.nextElement();
                        printWriter.print(str3 + ": " + properties.getProperty(str3) + "\r\n");
                    }
                }
                printWriter.print("\r\n");
                printWriter.flush();
                if (inputStream != null) {
                    int available = inputStream.available();
                    byte[] bArr = new byte[32768];
                    while (available > 0) {
                        int read = inputStream.read(bArr, 0, available > 32768 ? 32768 : available);
                        if (read <= 0) {
                            break;
                        }
                        outputStream.write(bArr, 0, read);
                        available -= read;
                    }
                }
                outputStream.flush();
                outputStream.close();
                if (inputStream != null) {
                    inputStream.close();
                }
                try {
                    this.f6018a.close();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } catch (IOException e) {
                LogUtils.debug("HttpSession", "Message:" + e.getMessage());
            }
        } finally {
            try {
                this.f6018a.close();
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }
}
