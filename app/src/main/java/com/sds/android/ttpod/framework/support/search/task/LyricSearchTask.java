package com.sds.android.ttpod.framework.support.search.task;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.support.search.p135a.LyricSearchTaskInfo;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.support.search.task.b */
/* loaded from: classes.dex */
public class LyricSearchTask extends LyrPicBaseSearchTask {

    /* renamed from: a */
    private LyricSearchTaskInfo f7306a;

    public LyricSearchTask(LyricSearchTaskInfo lyricSearchTaskInfo) {
        this.f7306a = lyricSearchTaskInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: f */
    public LyricSearchTaskInfo mo2131b() {
        return this.f7306a;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected boolean mo2133a(ArrayList<String> arrayList) {
        return true;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: b */
    protected String mo2129b(MediaItem mediaItem) {
        return null;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected ArrayList<ResultData> mo2140a(KXmlParser kXmlParser) throws Exception {
        int next;
        ArrayList<ResultData> arrayList = new ArrayList<>();
        if (mo2131b().m2194i() == null) {
            return arrayList;
        }
        kXmlParser.nextTag();
        kXmlParser.require(2, null, "lrc_list");
        do {
            next = kXmlParser.next();
            switch (next) {
                case 2:
                    if ("lrc".equals(kXmlParser.getName())) {
                        ResultData resultData = new ResultData();
                        resultData.f7288a = kXmlParser.getAttributeValue(null, "title");
                        resultData.f7289b = kXmlParser.getAttributeValue(null, "artist");
                        String m2143g = !mo2131b().m2194i().isOnline() ? m2143g() : null;
                        if (m2143g == null) {
                            m2143g = TTPodConfig.m5290r() + File.separator + m2145b(resultData.f7289b, resultData.f7288a);
                        }
                        String attributeValue = kXmlParser.getAttributeValue(null, "lrcID");
                        int parseInt = Integer.parseInt(kXmlParser.getAttributeValue(null, "trc"));
                        ResultData.Item[] itemArr = new ResultData.Item[1];
                        itemArr[0] = new ResultData.Item(parseInt == 0 ? "lrc" : "trc", m2147a(resultData.f7288a, resultData.f7289b, attributeValue), m2143g + (parseInt == 0 ? ".lrc" : ".trc"), Integer.parseInt(attributeValue));
                        resultData.f7292e = itemArr;
                        arrayList.add(resultData);
                        if (this.f7306a.m2198e()) {
                            break;
                        } else {
                            return arrayList;
                        }
                    } else {
                        kXmlParser.m3910a();
                        continue;
                    }
            }
        } while (next != 1);
        return arrayList;
    }

    /* renamed from: g */
    private String m2143g() {
        String m2145b = m2145b((String) null, (String) null);
        String[] h = mo2131b().m2195h();
        if (m2145b == null) {
            m2145b = FileUtils.m8397o(h[1]);
            if (h[3] != null) {
                m2145b = m2145b + "_" + h[3];
            }
        }
        return TTPodConfig.m5290r() + File.separator + m2145b;
    }

    /* renamed from: a */
    private String m2147a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("http://lrc.ttpod.com/down?");
            sb.append("lrcid=");
            sb.append(URLEncoder.encode(str3, "UTF-8"));
            sb.append("&code=");
            sb.append(TTTextUtils.decryptLyricKey(str, str2, Integer.parseInt(str3)));
            sb.append("&s=");
            sb.append(EnvironmentUtils.C0603b.m8494b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected void mo2132a(List<ResultData> list) {
        m2163a(list.get(0).m2179c()[0], true);
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String mo2137a(MediaItem mediaItem) {
        return null;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String mo2142a() {
        StringBuilder sb = new StringBuilder();
        MediaItem i = this.f7306a.m2194i();
        try {
            sb.append("http://lrc.ttpod.com/search?");
            String title = i.getTitle();
            if (this.f7306a.m2198e() && !TextUtils.isEmpty(this.f7306a.m2197f())) {
                title = this.f7306a.m2197f();
            }
            if (!TTTextUtils.isValidateMediaString(title)) {
                title = "";
            }
            sb.append("title=");
            sb.append(URLEncoder.encode(title, "UTF-8"));
            mo2131b().m2202c(title);
            String artist = i.getArtist();
            if (this.f7306a.m2198e() && !TextUtils.isEmpty(this.f7306a.m2196g())) {
                artist = this.f7306a.m2196g();
            }
            if (!TTTextUtils.isValidateMediaString(artist)) {
                artist = "";
            }
            sb.append("&artist=");
            sb.append(URLEncoder.encode(artist, "UTF-8"));
            mo2131b().m2200d(artist);
            if (!i.isOnline()) {
                sb.append("&filename=");
                sb.append(URLEncoder.encode(this.f7306a.m2195h()[1], "UTF-8"));
            }
            sb.append("&duration=");
            sb.append(i.getDuration());
            sb.append("&bitrate=");
            sb.append(i.getBitRate());
            sb.append("&srate=");
            sb.append(i.getSampleRate());
            if (!this.f7306a.m2198e() && i.isOnline()) {
                sb.append("&song_id=");
                sb.append(i.getSongID());
                long artistID = i.getArtistID();
                if (artistID != 0) {
                    sb.append("&singer_id=");
                    sb.append(artistID);
                }
            }
            sb.append("&raw=2");
            sb.append("&trc=1");
            sb.append("&auto=");
            sb.append(this.f7306a.m2198e() ? 0 : 1);
            sb.append("&s=");
            sb.append(EnvironmentUtils.C0603b.m8494b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /* renamed from: b */
    private String m2145b(String str, String str2) {
        String str3;
        MediaItem i = this.f7306a.m2194i();
        String artist = i.getArtist();
        String title = i.getTitle();
        boolean isValidateMediaString = TTTextUtils.isValidateMediaString(title);
        boolean isValidateMediaString2 = TTTextUtils.isValidateMediaString(artist);
        boolean isValidateMediaString3 = TTTextUtils.isValidateMediaString(str2);
        boolean isValidateMediaString4 = TTTextUtils.isValidateMediaString(str);
        if (isValidateMediaString) {
            if (isValidateMediaString2 || isValidateMediaString4) {
                StringBuilder sb = new StringBuilder();
                if (isValidateMediaString2) {
                    str = artist;
                }
                str3 = sb.append(str).append(" - ").append(title).toString();
            } else {
                str3 = title;
            }
        } else if (!isValidateMediaString3) {
            str3 = null;
        } else {
            str3 = str + " - " + str2;
        }
        return FileUtils.m8397o(str3);
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected ArrayList<String> mo2135a(String str, String str2) {
        String str3;
        String m2146a;
        boolean z = true;
        String[] h = this.f7306a.m2195h();
        MediaItem i = mo2131b().m2194i();
        if (i.isOnline()) {
            str3 = null;
        } else {
            str3 = h[1];
            if (h[3] != null) {
                str3 = str3 + '_' + h[3];
            }
        }
        String m8397o = FileUtils.m8397o(str);
        String m8397o2 = TTTextUtils.isValidateMediaString(str2) ? FileUtils.m8397o(str2) : null;
        if (i.getStartTime() == null || i.getStartTime().intValue() <= 0) {
            z = false;
        }
        if (i.isOnline()) {
            m2146a = m2146a(TTPodConfig.m5290r() + File.separator, str3, m8397o, m8397o2, ".trc", z);
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.m5290r() + File.separator, str3, m8397o, m8397o2, ".lrc", z);
            }
        } else {
            m2146a = m2146a(h[0], str3, m8397o, m8397o2, ".trc", z);
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.m5290r() + File.separator, str3, m8397o, m8397o2, ".trc", z);
            }
            if (m2146a == null) {
                m2146a = m2146a(h[0], str3, m8397o, m8397o2, ".lrc", z);
            }
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.m5290r() + File.separator, str3, m8397o, m8397o2, ".lrc", z);
            }
        }
        if (m2146a != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(m2146a);
            return arrayList;
        }
        return null;
    }

    /* renamed from: a */
    private String m2146a(String str, String str2, String str3, String str4, String str5, boolean z) {
        if (str2 != null && !z) {
            String str6 = str + str2 + str5;
            if (new File(str6).exists()) {
                return str6;
            }
        }
        String str7 = str + (TextUtils.isEmpty(str4) ? "" : str4 + " - ") + (TextUtils.isEmpty(str3) ? "" : str3) + str5;
        if (!new File(str7).exists()) {
            String str8 = str + (TextUtils.isEmpty(str4) ? "" : str4 + "-") + (TextUtils.isEmpty(str3) ? "" : str3) + str5;
            if (!new File(str8).exists()) {
                String str9 = str + (TextUtils.isEmpty(str3) ? "" : str3 + " - ") + (TextUtils.isEmpty(str4) ? "" : str4) + str5;
                if (!new File(str9).exists()) {
                    StringBuilder append = new StringBuilder().append(str).append(TextUtils.isEmpty(str3) ? "" : str3 + "-");
                    if (TextUtils.isEmpty(str4)) {
                        str4 = "";
                    }
                    String sb = append.append(str4).append(str5).toString();
                    if (!new File(sb).exists()) {
                        return null;
                    }
                    return sb;
                }
                return str9;
            }
            return str8;
        }
        return str7;
    }
}
