package com.sds.android.ttpod.framework.support.search.task;

import android.text.TextUtils;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.support.search.p135a.LyricSearchTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.ttpod.framework.support.search.task.b */
/* loaded from: classes.dex */
public class LyricSearchTask extends LyrPicBaseSearchTask {

    /* renamed from: a */
    private LyricSearchTaskInfo lyricSearchTaskInfo;

    public LyricSearchTask(LyricSearchTaskInfo lyricSearchTaskInfo) {
        this.lyricSearchTaskInfo = lyricSearchTaskInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: f */
    public LyricSearchTaskInfo getLyricSearchTaskInfo() {
        return this.lyricSearchTaskInfo;
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
        if (getLyricSearchTaskInfo().getMediaItem() == null) {
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
                        resultData.title = kXmlParser.getAttributeValue(null, "title");
                        resultData.artist = kXmlParser.getAttributeValue(null, "artist");
                        String lyricPath = !getLyricSearchTaskInfo().getMediaItem().isOnline() ? m2143g() : null;
                        if (lyricPath == null) {
                            lyricPath = TTPodConfig.getLyricPath() + File.separator + m2145b(resultData.artist, resultData.title);
                        }
                        String attributeValue = kXmlParser.getAttributeValue(null, "lrcID");
                        int parseInt = Integer.parseInt(kXmlParser.getAttributeValue(null, "trc"));
                        ResultData.Item[] itemArr = new ResultData.Item[1];
                        itemArr[0] = new ResultData.Item(parseInt == 0 ? "lrc" : "trc"
                                , m2147a(resultData.title, resultData.artist, attributeValue)
                                , lyricPath + (parseInt == 0 ? ".lrc" : ".trc")
                                , Integer.parseInt(attributeValue));
                        resultData.lyricArray = itemArr;
                        arrayList.add(resultData);
                        if (this.lyricSearchTaskInfo.isAuto()) {
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
        String[] h = getLyricSearchTaskInfo().getSongInfo();
        if (m2145b == null) {
            m2145b = FileUtils.removeWrongCharacter(h[1]);
            if (h[3] != null) {
                m2145b = m2145b + "_" + h[3];
            }
        }
        return TTPodConfig.getLyricPath() + File.separator + m2145b;
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
            sb.append(EnvironmentUtils.UUIDConfig.m8494b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected void startDownloadLyric(List<ResultData> list) {
        downloadLyric(list.get(0).getLyricArray()[0], true);
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String getMediaItem(MediaItem mediaItem) {
        return null;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String buildUrl() {
        StringBuilder sb = new StringBuilder();
        MediaItem mediaItem = this.lyricSearchTaskInfo.getMediaItem();
        try {
            sb.append("http://lrc.ttpod.com/search?");
            String title = mediaItem.getTitle();
            if (this.lyricSearchTaskInfo.isAuto() && !TextUtils.isEmpty(this.lyricSearchTaskInfo.m2197f())) {
                title = this.lyricSearchTaskInfo.m2197f();
            }
            if (!TTTextUtils.isValidateMediaString(title)) {
                title = "";
            }
            sb.append("title=");
            sb.append(URLEncoder.encode(title, "UTF-8"));
            getLyricSearchTaskInfo().setTitle(title);
            String artist = mediaItem.getArtist();
            if (this.lyricSearchTaskInfo.isAuto() && !TextUtils.isEmpty(this.lyricSearchTaskInfo.m2196g())) {
                artist = this.lyricSearchTaskInfo.m2196g();
            }
            if (!TTTextUtils.isValidateMediaString(artist)) {
                artist = "";
            }
            sb.append("&artist=");
            sb.append(URLEncoder.encode(artist, "UTF-8"));
            getLyricSearchTaskInfo().setSinger(artist);
            if (!mediaItem.isOnline()) {
                sb.append("&filename=");
                sb.append(URLEncoder.encode(this.lyricSearchTaskInfo.getSongInfo()[1], "UTF-8"));
            }
            sb.append("&duration=");
            sb.append(mediaItem.getDuration());
            sb.append("&bitrate=");
            sb.append(mediaItem.getBitRate());
            sb.append("&srate=");
            sb.append(mediaItem.getSampleRate());
            if (!this.lyricSearchTaskInfo.isAuto() && mediaItem.isOnline()) {
                sb.append("&song_id=");
                sb.append(mediaItem.getSongID());
                long artistID = mediaItem.getArtistID();
                if (artistID != 0) {
                    sb.append("&singer_id=");
                    sb.append(artistID);
                }
            }
            sb.append("&raw=2");
            sb.append("&trc=1");
            sb.append("&auto=");
            sb.append(this.lyricSearchTaskInfo.isAuto() ? 0 : 1);
            sb.append("&s=");
            sb.append(EnvironmentUtils.UUIDConfig.m8494b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected ArrayList<ResultData> requestResultDataArrayList(String url) {
        ArrayList<ResultData> resultDataArrayList = new ArrayList<>();
        url = "http://www.22lrc.com/search.php?keyword=" + lyricSearchTaskInfo.getTitle() + "&radio=0";
        String html = requestData(url);
        Pattern pattern = Pattern.compile("<td class=\"gm\"><a.*?href=\"geci/(.*?)\">");
        Matcher matcher = pattern.matcher(html);
        String lrcId = null;
        if (matcher.find()) {
            lrcId = matcher.group(1);
        }
        if (lrcId != null) {
            String lrcUrl = String.format("http://www.22lrc.com/lrc/%s/%s.lrc"
                    , lrcId.substring(0, lrcId.length() - 4)
                    , lrcId.substring(lrcId.length() - 4));
            ResultData resultData = new ResultData();
            resultData.setAlbum(lyricSearchTaskInfo.getMediaItem().getAlbum());
            resultData.setArtist(lyricSearchTaskInfo.getMediaItem().getArtist());
            resultData.setTitle(lyricSearchTaskInfo.getMediaItem().getTitle());
            ResultData.Item[] items = new ResultData.Item[1];
            String localLyricPath = TTPodConfig.getLyricPath() + File.separator +lyricSearchTaskInfo.getSongInfo()[1];
            items[0] = new ResultData.Item("lrc", lrcUrl,localLyricPath+".lrc", Integer.valueOf(lrcId));
            resultData.setLyricArray(items);
            resultDataArrayList.add(resultData);
        }
        return resultDataArrayList;
    }

    /* renamed from: b */
    private String m2145b(String str, String str2) {
        String str3;
        MediaItem i = this.lyricSearchTaskInfo.getMediaItem();
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
        return FileUtils.removeWrongCharacter(str3);
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected ArrayList<String> mo2135a(String songName, String singer) {
        String songSingerName;
        String m2146a;
        boolean z = true;
        String[] songInfo = this.lyricSearchTaskInfo.getSongInfo();
        MediaItem mediaItem = getLyricSearchTaskInfo().getMediaItem();
        if (mediaItem.isOnline()) {
            songSingerName = null;
        } else {
            songSingerName = songInfo[1];
            if (songInfo[3] != null) {
                songSingerName = songSingerName + '_' + songInfo[3];
            }
        }
        String m8397o = FileUtils.removeWrongCharacter(songName);
        String m8397o2 = TTTextUtils.isValidateMediaString(singer) ? FileUtils.removeWrongCharacter(singer) : null;
        if (mediaItem.getStartTime() == null || mediaItem.getStartTime().intValue() <= 0) {
            z = false;
        }
        if (mediaItem.isOnline()) {
            m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".trc", z);
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".lrc", z);
            }
        } else {
            m2146a = m2146a(songInfo[0], songSingerName, m8397o, m8397o2, ".trc", z);
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".trc", z);
            }
            if (m2146a == null) {
                m2146a = m2146a(songInfo[0], songSingerName, m8397o, m8397o2, ".lrc", z);
            }
            if (m2146a == null) {
                m2146a = m2146a(TTPodConfig.getLyricPath() + File.separator, songSingerName, m8397o, m8397o2, ".lrc", z);
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
