package com.sds.android.ttpod.framework.support.search.task;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.media.mediastore.MediaItem;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

/* renamed from: com.sds.android.ttpod.framework.support.search.task.d */
/* loaded from: classes.dex */
public class ReportTask implements Runnable {

    /* renamed from: a */
    private EnumC2097b f7311a;

    /* renamed from: b */
    private EnumC2096a f7312b;

    /* renamed from: c */
    private MediaItem f7313c;

    /* compiled from: ReportTask.java */
    /* renamed from: com.sds.android.ttpod.framework.support.search.task.d$a */
    /* loaded from: classes.dex */
    public enum EnumC2096a {
        REPORT_NO_CONTENT_STATE,
        REPORT_NO_MATCH_STATE,
        REPORT_LOW_QUALITY_STATE
    }

    /* compiled from: ReportTask.java */
    /* renamed from: com.sds.android.ttpod.framework.support.search.task.d$b */
    /* loaded from: classes.dex */
    public enum EnumC2097b {
        REPORT_TYPE_LYRIC,
        REPORT_TYPE_PICTURE
    }

    public ReportTask(EnumC2097b enumC2097b, EnumC2096a enumC2096a, MediaItem mediaItem) {
        this.f7311a = enumC2097b;
        this.f7312b = enumC2096a;
        this.f7313c = mediaItem;
    }

    @Override // java.lang.Runnable
    public void run() {
        Boolean valueOf;
        Boolean.valueOf(false);
        if (EnumC2097b.REPORT_TYPE_LYRIC == this.f7311a) {
            //StatisticUtils.m4907a("lyric_pic", "report", "lyric", this.f7312b.ordinal(), this.f7313c.getSongID().longValue(), this.f7313c.getArtist(), this.f7313c.getTitle());
            valueOf = Boolean.valueOf(m2120a("http://lrc.ttpod.com/report?", m2121a()));
        } else {
            //StatisticUtils.m4907a("lyric_pic", "report", "picture", this.f7312b.ordinal(), this.f7313c.getSongID().longValue(), this.f7313c.getArtist(), this.f7313c.getTitle());
            valueOf = Boolean.valueOf(m2120a("http://picdown.ttpod.cn/picreport?", m2118b()));
        }
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_REPORT, this.f7311a, this.f7313c, valueOf), ModuleID.SEARCH);
    }

    /* renamed from: a */
    protected boolean m2120a(String str, List<? extends NameValuePair> list) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, (int) TimeUnit.SECONDS.toMillis(5L));
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
        HttpPost httpPost = new HttpPost(str);
        if (list != null) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        int statusCode = 0;
        try {
            statusCode = defaultHttpClient.execute(httpPost).getStatusLine().getStatusCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return statusCode == 200 || statusCode == 202;
    }

    /* renamed from: a */
    ArrayList<BasicNameValuePair> m2121a() {
        ArrayList<BasicNameValuePair> arrayList = new ArrayList<>();
        arrayList.add(new BasicNameValuePair("lrcid", ""));
        arrayList.add(new BasicNameValuePair("level", String.valueOf(this.f7312b)));
        arrayList.add(new BasicNameValuePair("ti", ""));
        arrayList.add(new BasicNameValuePair("ar", ""));
        m2119a(arrayList);
        HashMap<String, Object> m8488e = EnvironmentUtils.C0603b.m8488e();
        for (String str : m8488e.keySet()) {
            arrayList.add(new BasicNameValuePair(str, m8488e.get(str).toString()));
        }
        return arrayList;
    }

    /* renamed from: b */
    ArrayList<BasicNameValuePair> m2118b() {
        ArrayList<BasicNameValuePair> arrayList = new ArrayList<>();
        arrayList.add(new BasicNameValuePair("arpic", ""));
        arrayList.add(new BasicNameValuePair("level", String.valueOf(this.f7312b)));
        arrayList.add(new BasicNameValuePair("ar", ""));
        m2119a(arrayList);
        HashMap<String, Object> m8488e = EnvironmentUtils.C0603b.m8488e();
        for (String str : m8488e.keySet()) {
            arrayList.add(new BasicNameValuePair(str, m8488e.get(str).toString()));
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m2119a(ArrayList<BasicNameValuePair> arrayList) {
        arrayList.add(new BasicNameValuePair("title", this.f7313c.getTitle()));
        arrayList.add(new BasicNameValuePair("artist", this.f7313c.getArtist()));
        arrayList.add(new BasicNameValuePair("album", this.f7313c.getAlbum()));
        String m8402j = FileUtils.getFilename(this.f7313c.getLocalDataSource());
        arrayList.add(new BasicNameValuePair("filename", m8402j));
        arrayList.add(new BasicNameValuePair("mediatype", m8402j.substring(m8402j.lastIndexOf(46) + 1)));
        arrayList.add(new BasicNameValuePair("duration", String.valueOf(this.f7313c.getDuration())));
        arrayList.add(new BasicNameValuePair("bitrate", String.valueOf(this.f7313c.getBitRate())));
        arrayList.add(new BasicNameValuePair("srate", String.valueOf(this.f7313c.getSampleRate())));
    }
}
