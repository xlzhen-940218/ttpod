package com.sds.android.ttpod.share.p139d;

import android.net.Uri;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.share.MySSLSocketFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/* renamed from: com.sds.android.ttpod.share.d.c */
/* loaded from: classes.dex */
public class ShareHttpUtil {
    /* renamed from: a */
    public static HttpClient m1930a() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            MySSLSocketFactory mySSLSocketFactory = new MySSLSocketFactory(keyStore);
            mySSLSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", mySSLSocketFactory, 443));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    /* renamed from: a */
    public static String m1928a(String str, List<NameValuePair> list, NameValuePair... nameValuePairArr) {
        try {
            HttpClient m1930a = m1930a();
            HttpPost httpPost = new HttpPost(str);
            MultipartEntity multipartEntity = new MultipartEntity();
            if (list != null) {
                for (NameValuePair nameValuePair : list) {
                    multipartEntity.addPart(nameValuePair.getName(), new StringBody(nameValuePair.getValue(), Charset.forName("UTF-8")));
                    LogUtils.debug("TEST", "name: " + nameValuePair.getName() + " value:" + nameValuePair.getValue());
                }
            }
            if (nameValuePairArr != null) {
                for (NameValuePair nameValuePair2 : nameValuePairArr) {
                    if (!StringUtils.isEmpty(nameValuePair2.getValue())) {
                        File file = new File(nameValuePair2.getValue());
                        if (file.exists()) {
                            multipartEntity.addPart(nameValuePair2.getName(), new FileBody(file));
                        }
                    }
                }
            }
            httpPost.setEntity(multipartEntity);
            HttpResponse execute = m1930a.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return new String(EntityUtils.toByteArray(execute.getEntity()), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /* renamed from: a */
    public static String m1929a(String str, List<NameValuePair> list) {
        try {
            HttpClient m1930a = m1930a();
            HttpPost httpPost = new HttpPost(str);
            for (NameValuePair nameValuePair : list) {
                LogUtils.debug("TEST", "name2: " + nameValuePair.getName() + " value2:" + nameValuePair.getValue());
            }
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            HttpResponse execute = m1930a.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return new String(EntityUtils.toByteArray(execute.getEntity()), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /* renamed from: b */
    public static String m1927b(String str, List<NameValuePair> list) {
        HttpClient m1930a = m1930a();
        Uri.Builder encodedPath = new Uri.Builder().encodedPath(str);
        if (list != null) {
            for (NameValuePair nameValuePair : list) {
                encodedPath.appendQueryParameter(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        try {
            HttpResponse execute = m1930a.execute(new HttpGet(encodedPath.build().toString()));
            if (execute.getStatusLine().getStatusCode() == 200) {
                return StringUtils.streamToString(execute.getEntity().getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
