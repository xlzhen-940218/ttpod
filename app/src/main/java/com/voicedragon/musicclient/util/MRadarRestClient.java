package com.voicedragon.musicclient.util;

import android.content.Context;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

/* loaded from: classes.dex */
public class MRadarRestClient {
    public static final String TAG = "MRadarRestClient";
    private static AsyncHttpClient client = new AsyncHttpClient();
    public static SyncHttpClient sSyncClient = new SyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void cancel(Context context) {
        client.cancelRequests(context, true);
    }

    public static void post(String url, RequestParams postParams, AsyncHttpResponseHandler responseHandler) {
        post(url, null, postParams, responseHandler);
    }

    public static void post(String url, RequestParams urlParams, RequestParams postParams, AsyncHttpResponseHandler responseHandler) {
        if (urlParams != null) {
            url = AsyncHttpClient.getUrlWithQueryString(true, url, urlParams);
        }
        Logger.m2e(TAG, String.valueOf(url) + " " + postParams.toString());
        client.post(url, postParams, responseHandler);
    }

    public static void postSync(String url, RequestParams urlParams, RequestParams postParams, AsyncHttpResponseHandler responseHandler) {
        if (urlParams != null) {
            url = AsyncHttpClient.getUrlWithQueryString(true, url, urlParams);
        }
        Log.e(TAG, String.valueOf(url) + " " + postParams.toString());
        sSyncClient.post(url, postParams, responseHandler);
    }

    public static String getSyncText(String url, RequestParams params) {
        HttpGet httpGet = new HttpGet(AsyncHttpClient.getUrlWithQueryString(true, url, params));
        try {
            HttpResponse response = new DefaultHttpClient(new BasicHttpParams()).execute(httpGet);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e12) {
            e12.printStackTrace();
        }
        return null;
    }
}
