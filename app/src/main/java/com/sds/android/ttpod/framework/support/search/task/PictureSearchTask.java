package com.sds.android.ttpod.framework.support.search.task;

import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.search.SearchMediaLinkInfo;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.search.p127a.LyrPicFileNameUtils;
import com.sds.android.ttpod.framework.p106a.PlatformUtils;
import com.sds.android.ttpod.framework.storage.database.SearchSqliteDb;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.search.SearchManager;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.p135a.PictureSearchTaskInfo;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: com.sds.android.ttpod.framework.support.search.task.c */
/* loaded from: classes.dex */
public class PictureSearchTask extends LyrPicBaseSearchTask {

    /* renamed from: c */
    private static final Set<String> f7307c = new HashSet();

    /* renamed from: a */
    private PictureSearchTaskInfo f7308a;

    /* renamed from: b */
    private String f7309b;

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected void startDownloadLyric(List<ResultData> list) {
        if (list == null || list.isEmpty()) {
            m2164a(SearchManager.SEARCH_ONLINE_FAILURE);
            return;
        }
        ResultData.Item[] m2179c = list.get(0).getLyricArray();
        int length = (!PlatformUtils.sdkThan14() || m2179c == null) ? 1 : m2179c.length;
        if (m2179c == null) {
            length = 0;
        }
        int m8476d = EnvironmentUtils.DeviceConfig.getNetworkType();
        int m3005aB = Preferences.m3005aB();
        int m3004aC = Preferences.m3004aC();
        if (m8476d != 2) {
            m3005aB = m3004aC;
        }
        ArrayList<Integer> m2128b = m2128b(TTPodConfig.getArtistPath() + File.separator + FileUtils.removeWrongCharacter(getLyricSearchTaskInfo().getMediaItem().getArtist()) + File.separator);
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i2 < length && i < m3005aB) {
            ResultData.Item item = m2179c[i2];
            i2++;
            i++;
            if (m2128b == null || !m2128b.contains(Integer.valueOf(item.getId()))) {
                if (!FileUtils.isFile(item.getLocalLyricPath())) {
                    z = i2 >= length || i >= m3005aB;
                    downloadLyric(item, z);
                }
            }
        }
        if (!z) {
            m2164a(SearchManager.SEARCH_ONLINE_FINISHED);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0086  */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v8 */
    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected boolean mo2133a(ArrayList<String> arrayList) {
        int i;
        int i2;
        Throwable th;
        Exception e;
        ByteArrayInputStream byteArrayInputStream = null;
        KXmlParser kXmlParser = null;
        ArrayList<ResultData> arrayList2 = null;
        MediaItem i3 = getLyricSearchTaskInfo().getMediaItem();
        String mo2137a = getMediaItem(i3);
        if (mo2137a != null) {
            String m8403i = FileUtils.readStringFromFile(mo2137a);
            Object m8346a = StringUtils.isEmpty(m8403i);
            try {
                if (!((boolean) m8346a)) {
                    try {
                        kXmlParser = new KXmlParser();
                        byteArrayInputStream = new ByteArrayInputStream(m8403i.getBytes("UTF-8"));
                    } catch (Exception e1) {
                        e = e1;
                        byteArrayInputStream = null;
                    } catch (Throwable th1) {
                        th = th1;
                        m8346a = 0;
                        if (!((boolean) m8346a)) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    try {
                        kXmlParser.setInput(byteArrayInputStream, "UTF-8");
                        arrayList2 = m2139a(kXmlParser, i3, getLyricSearchTaskInfo().m2185l(), this.f7309b);
                        m8346a = byteArrayInputStream;
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                                m8346a = byteArrayInputStream;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                m8346a = byteArrayInputStream;
                            }
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                        m8346a = byteArrayInputStream;
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                                m8346a = byteArrayInputStream;
                            } catch (Exception e5) {
                                e5.printStackTrace();
                                m8346a = byteArrayInputStream;
                            }
                        }
                        if (arrayList2 != null) {
                            ResultData.Item[] m2179c = arrayList2.get(0).getLyricArray();
                            i = m2179c == null ? m2179c.length : 0;
                            int m8476d = EnvironmentUtils.DeviceConfig.getNetworkType();
                            if (i != 0) {
                            }
                            return true;
                        }
                        i = 0;
                        int m8476d2 = EnvironmentUtils.DeviceConfig.getNetworkType();
                        if (i != 0) {
                        }
                        return true;
                    }
                    if (arrayList2 != null && !arrayList2.isEmpty()) {
                        ResultData.Item[] m2179c2 = arrayList2.get(0).getLyricArray();
                        i = m2179c2 == null ? m2179c2.length : 0;
                        int m8476d22 = EnvironmentUtils.DeviceConfig.getNetworkType();
                        if (i != 0 || m8476d22 == -1) {
                            return true;
                        }
                        int m3005aB = m8476d22 == 2 ? Preferences.m3005aB() : Preferences.m3004aC();
                        int size = arrayList != null ? arrayList.size() : 0;
                        boolean z = size >= m3005aB || size >= i;
                        if (!z) {
                            String str = TTPodConfig.getArtistPath() + File.separator + FileUtils.removeWrongCharacter(i3.getArtist()) + File.separator;
                            ArrayList<Integer> m2128b = m2128b(str);
                            if (m2128b != null) {
                                Iterator<Integer> it = m2128b.iterator();
                                while (true) {
                                    i2 = size;
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    size = !FileUtils.isFile(new StringBuilder().append(str).append((long) it.next().intValue()).toString()) ? i2 + 1 : i2;
                                }
                                return i2 >= m3005aB || i2 >= i;
                            }
                        }
                        return z;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        i = 0;
        int m8476d222 = EnvironmentUtils.DeviceConfig.getNetworkType();
        if (i != 0) {
        }
        return true;
    }

    /* renamed from: b */
    public static ArrayList<Integer> m2128b(String str) {
        ObjectInputStream objectInputStream;
        Throwable th;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(str + "uopicid"));
            try {
                ArrayList<Integer> arrayList = (ArrayList) objectInputStream.readObject();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                        return arrayList;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return arrayList;
                    }
                }
                return arrayList;
            } catch (Throwable th1) {
                th = th1;
                try {
                    th.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                            return null;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    ObjectInputStream objectInputStream2 = objectInputStream;
                    if (objectInputStream2 != null) {
                        try {
                            objectInputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            objectInputStream = null;
        }
        return null;
    }

    /* renamed from: a */
    public static void m2134a(String str, ArrayList<Integer> arrayList) {
        ObjectOutputStream objectOutputStream;
        Throwable th;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(str + "uopicid"));
        } catch (Throwable th1) {
            th = th1;
            objectOutputStream = null;
        }
        try {
            objectOutputStream.writeObject(arrayList);
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                th.printStackTrace();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                ObjectOutputStream objectOutputStream2 = objectOutputStream;
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                throw th3;
            }
        }
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String getMediaItem(MediaItem mediaItem) {
        String m2124d = m2124d(mediaItem.getID());
        if (StringUtils.isEmpty(m2124d)) {
            m2124d = FileUtils.removeWrongCharacter(mediaItem.getArtist());
        }
        if (!TTTextUtils.isValidateMediaString(m2124d)) {
            m2124d = null;
        }
        if (StringUtils.isEmpty(m2124d)) {
            return null;
        }
        return TTPodConfig.getArtistPath() + File.separator + m2124d + File.separator + "result.xml";
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected ArrayList<ResultData> mo2140a(KXmlParser kXmlParser) throws Exception {
        MediaItem i = getLyricSearchTaskInfo().getMediaItem();
        ArrayList<ResultData> m2139a = m2139a(kXmlParser, i, getLyricSearchTaskInfo().m2185l(), this.f7309b);
        if (StringUtils.isEmpty(this.f7309b) && m2139a != null && !m2139a.isEmpty() && !TTTextUtils.isValidateMediaString(i.getArtist())) {
            this.f7309b = m2139a.get(0).getArtist();
            m2127b(i.getID(), this.f7309b);
        }
        return m2139a;
    }

    /* renamed from: b */
    public static void m2127b(String str, String str2) {
        SearchMediaLinkInfo m3135a = SearchSqliteDb.m3135a(BaseApplication.getApplication().getContentResolver(), str);
        if (m3135a != null) {
            m3135a.setArtist(str2);
            SearchSqliteDb.m3136a(BaseApplication.getApplication().getContentResolver(), m3135a, str);
            return;
        }
        SearchMediaLinkInfo searchMediaLinkInfo = new SearchMediaLinkInfo();
        searchMediaLinkInfo.setMediaId(str);
        searchMediaLinkInfo.setArtist(str2);
        SearchSqliteDb.m3137a(BaseApplication.getApplication().getContentResolver(), searchMediaLinkInfo);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static ArrayList<ResultData> m2139a(KXmlParser kXmlParser, MediaItem mediaItem, int i, String str) {
        String m8397o;
        String str2;
        String str3;
        ResultData resultData;
        String str4;
        String str5;
        ResultData resultData2;
        int i2;
        String str6;
        String str7;
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList<ResultData> arrayList2 = new ArrayList<>();
            kXmlParser.nextTag();
            kXmlParser.require(2, null, "tt_song_list");
            String attributeValue = kXmlParser.getAttributeValue(null, "ip");
            if (str != null) {
                m8397o = str;
            } else {
                m8397o = mediaItem != null ? FileUtils.removeWrongCharacter(mediaItem.getArtist()) : null;
            }
            if (!TTTextUtils.isValidateMediaString(m8397o)) {
                str2 = null;
                str3 = null;
                resultData = null;
            } else {
                str2 = TTPodConfig.getArtistPath() + File.separator + FileUtils.removeWrongCharacter(m8397o) + File.separator;
                str3 = m8397o;
                resultData = null;
            }
            while (true) {
                int next = kXmlParser.next();
                switch (next) {
                    case 2:
                        if ("tt_songinfo".equals(kXmlParser.getName())) {
                            resultData = new ResultData();
                            resultData.title = kXmlParser.getAttributeValue(null, "title");
                            resultData.artist = FileUtils.removeWrongCharacter(kXmlParser.getAttributeValue(null, "artist"));
                            resultData.album = kXmlParser.getAttributeValue(null, "album");
                            resultData.allname = kXmlParser.getAttributeValue(null, "allname");
                            if (str3 == null) {
                                String str8 = resultData.artist;
                                str4 = TTPodConfig.getArtistPath() + File.separator + str8 + File.separator;
                                str5 = str8;
                                resultData2 = resultData;
                                break;
                            }
                            str4 = str2;
                            str5 = str3;
                            resultData2 = resultData;
                            break;
                        } else if (User.KEY_AVATAR.equals(kXmlParser.getName())) {
                            String attributeValue2 = kXmlParser.getAttributeValue(null, "type");
                            if (m2136a(attributeValue2, i)) {
                                String str9 = null;
                                try {
                                    str9 = attributeValue + '/' + TTTextUtils.decryptPictureKey((int) Long.parseLong(kXmlParser.getAttributeValue(null, "uid0"), 16), (int) Long.parseLong(kXmlParser.getAttributeValue(null, "uid1"), 16), (int) Long.parseLong(kXmlParser.getAttributeValue(null, "uid2"), 16), kXmlParser.getAttributeValue(null, "uid3")) + ".jpg";
                                    str6 = str9;
                                    i2 = Integer.parseInt(kXmlParser.getAttributeValue(null, "id"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    String str10 = str9;
                                    i2 = 0;
                                    str6 = str10;
                                }
                                if ("artist".equals(attributeValue2)) {
                                    str7 = str2 + i2;
                                } else if ("album".equals(attributeValue2)) {
                                    str7 = str2 + i2;
                                    i2 = 0;
                                } else {
                                    str7 = str2 + i2;
                                }
                                if (i2 != 0 && str6 != null) {
                                    arrayList.add(new ResultData.Item(attributeValue2, str6, str7, i2));
                                }
                            }
                            str4 = str2;
                            str5 = str3;
                            resultData2 = resultData;
                            break;
                        } else {
                            kXmlParser.m3910a();
                            str4 = str2;
                            str5 = str3;
                            resultData2 = resultData;
                            break;
                        }
                    case 3:
                        if ("tt_songinfo".equals(kXmlParser.getName()) && resultData != null) {
                            int size = arrayList.size();
                            if (size > 0) {
                                Collections.sort(arrayList);
                                resultData.lyricArray = new ResultData.Item[size];
                                arrayList.toArray(resultData.lyricArray);
                                arrayList.clear();
                            }
                            arrayList2.add(resultData);
                            return arrayList2;
                        }
                        str4 = str2;
                        str5 = str3;
                        resultData2 = resultData;
                        break;
                    default:
                        str4 = str2;
                        str5 = str3;
                        resultData2 = resultData;
                        break;
                }
                if (1 == next) {
                    return arrayList2;
                }
                str2 = str4;
                str3 = str5;
                resultData = resultData2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m2136a(String str, int i) {
        return 6 == (i & 6) || m2141a(i) || (m2130b(i) && "artist".equals(str)) || (m2126c(i) && "album".equals(str));
    }

    /* renamed from: a */
    private static boolean m2141a(int i) {
        return 1 == (i & 1);
    }

    /* renamed from: b */
    private static boolean m2130b(int i) {
        return 2 == (i & 2);
    }

    /* renamed from: c */
    private static boolean m2126c(int i) {
        return 4 == (i & 4);
    }

    @Override
    protected String buildUrl() {
        return m2138a(getLyricSearchTaskInfo());
    }

    @Override
    protected ArrayList<ResultData> requestResultDataArrayList(String initialUrl) {
        ArrayList<ResultData> resultDataArrayList = new ArrayList<>();
        MediaItem mediaItem = getLyricSearchTaskInfo().getMediaItem();
        if (mediaItem == null) {
            return resultDataArrayList;
        }
        String artist = mediaItem.getArtist();
        if (TextUtils.isEmpty(artist)) {
            artist = getLyricSearchTaskInfo().getSinger();
        }
        // 替换已不可达的 Deezer 接口，改用网易云专辑封面
        ArrayList<ResultData> searchResults = searchNeteaseSongs(mediaItem.getTitle(), artist, mediaItem.getID(), 10);
        if (searchResults.isEmpty() && !TextUtils.isEmpty(artist) && !TextUtils.isEmpty(mediaItem.getTitle())) {
            // 歌手信息缺失或不准确时，退化为只按歌名搜索
            searchResults = searchNeteaseSongs(mediaItem.getTitle(), null, mediaItem.getID(), 10);
        }
        if (!searchResults.isEmpty()) {
            resultDataArrayList.add(searchResults.get(0));
        }
        return resultDataArrayList;
    }

    /**
     * 按歌曲/歌手搜索网易云，返回带专辑封面的结果（供自动搜索与手动换图页共用）。
     */
    public static ArrayList<ResultData> searchArtistPictures(MediaItem mediaItem, String artist) {
        String title = mediaItem != null ? mediaItem.getTitle() : null;
        String mediaId = mediaItem != null ? mediaItem.getID() : null;
        ArrayList<ResultData> results = searchNeteaseSongs(title, artist, mediaId, 12);
        if (results.isEmpty() && !TextUtils.isEmpty(artist)) {
            results = searchNeteaseSongs(null, artist, mediaId, 12);
        }
        return results;
    }

    private static ArrayList<ResultData> searchNeteaseSongs(String title, String artist, String mediaId, int limit) {
        ArrayList<ResultData> results = new ArrayList<>();
        if (TextUtils.isEmpty(artist) && TextUtils.isEmpty(title)) {
            return results;
        }
        try {
            String query = (TextUtils.isEmpty(title) ? "" : title) + (TextUtils.isEmpty(artist) ? "" : " " + artist);
            String json = requestData("https://music.163.com/api/search/get/web?s="
                    + URLEncoder.encode(query.trim(), "UTF-8")
                    + "&type=1&offset=0&total=true&limit=" + limit);
            if (TextUtils.isEmpty(json)) {
                return results;
            }
            com.google.gson.JsonObject root = com.google.gson.JsonParser.parseString(json).getAsJsonObject();
            if (root == null || !root.has("result") || root.get("result").isJsonNull()) {
                return results;
            }
            com.google.gson.JsonObject resultObj = root.getAsJsonObject("result");
            if (resultObj == null || !resultObj.has("songs") || resultObj.get("songs").isJsonNull()) {
                return results;
            }
            com.google.gson.JsonArray songs = resultObj.getAsJsonArray("songs");
            ArrayList<com.google.gson.JsonObject> matched = new ArrayList<>();
            for (int i = 0; i < songs.size() && matched.size() < limit; i++) {
                com.google.gson.JsonElement element = songs.get(i);
                if (element == null || !element.isJsonObject()) {
                    continue;
                }
                com.google.gson.JsonObject song = element.getAsJsonObject();
                String name = stringValue(song, "name");
                String songArtist = joinArtists(song);
                if (!TextUtils.isEmpty(title) && !isTitleMatched(title, name)) {
                    continue;
                }
                if (!TextUtils.isEmpty(artist) && !isArtistMatched(artist, songArtist)) {
                    continue;
                }
                matched.add(song);
            }
            if (matched.isEmpty()) {
                return results;
            }

            java.util.HashMap<Long, String> picUrls = new java.util.HashMap<>();
            StringBuilder ids = new StringBuilder("[");
            for (int i = 0; i < matched.size(); i++) {
                if (i > 0) {
                    ids.append(',');
                }
                ids.append(songId(matched.get(i)));
            }
            ids.append(']');
            String detailJson = requestData("https://music.163.com/api/song/detail?ids="
                    + URLEncoder.encode(ids.toString(), "UTF-8"));
            if (!TextUtils.isEmpty(detailJson)) {
                com.google.gson.JsonObject detailRoot = com.google.gson.JsonParser.parseString(detailJson).getAsJsonObject();
                if (detailRoot != null && detailRoot.has("songs") && !detailRoot.get("songs").isJsonNull()) {
                    com.google.gson.JsonArray detailSongs = detailRoot.getAsJsonArray("songs");
                    for (int i = 0; i < detailSongs.size(); i++) {
                        com.google.gson.JsonElement element = detailSongs.get(i);
                        if (element == null || !element.isJsonObject()) {
                            continue;
                        }
                        com.google.gson.JsonObject song = element.getAsJsonObject();
                        if (!song.has("album") || song.get("album").isJsonNull() || !song.get("album").isJsonObject()) {
                            continue;
                        }
                        String picUrl = stringValue(song.getAsJsonObject("album"), "picUrl");
                        if (!TextUtils.isEmpty(picUrl)) {
                            picUrls.put(songId(song), normalizePicUrl(picUrl));
                        }
                    }
                }
            }

            for (com.google.gson.JsonObject song : matched) {
                long id = songId(song);
                String picUrl = picUrls.get(id);
                if (TextUtils.isEmpty(picUrl) && song.has("album") && !song.get("album").isJsonNull()
                        && song.get("album").isJsonObject()) {
                    picUrl = normalizePicUrl(stringValue(song.getAsJsonObject("album"), "picUrl"));
                }
                if (TextUtils.isEmpty(picUrl)) {
                    continue;
                }
                String name = stringValue(song, "name");
                String songArtist = joinArtists(song);
                // 目录以当前歌曲自身歌手为准，保证 mo2135a 能按同一目录找回封面
                String folder = FileUtils.removeWrongCharacter(!TextUtils.isEmpty(artist) ? artist : songArtist);
                if (StringUtils.isEmpty(folder)) {
                    folder = "unknown";
                }
                int itemId = (int) (id & 0x7fffffff);
                // 文件名带上当前媒体 ID 的稳定前缀，避免同歌手目录下其他歌曲的封面被误当成本曲封面
                String mediaKey = TextUtils.isEmpty(mediaId)
                        ? String.valueOf(itemId) : Integer.toHexString(mediaId.hashCode());
                String localPath = TTPodConfig.getArtistPath() + File.separator + folder
                        + File.separator + mediaKey + "_" + itemId + ".jpg";
                ResultData resultData = new ResultData();
                resultData.setTitle(name);
                resultData.setArtist(songArtist);
                resultData.setAlbum(albumName(song));
                resultData.setLyricArray(new ResultData.Item[]{
                        new ResultData.Item("album", picUrl, localPath, itemId)
                });
                results.add(resultData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    private static long songId(com.google.gson.JsonObject song) {
        return song != null && song.has("id") && !song.get("id").isJsonNull() ? song.get("id").getAsLong() : 0L;
    }

    private static String stringValue(com.google.gson.JsonObject obj, String key) {
        return obj != null && obj.has(key) && !obj.get(key).isJsonNull() ? obj.get(key).getAsString() : "";
    }

    private static String joinArtists(com.google.gson.JsonObject song) {
        if (song == null || !song.has("artists") || song.get("artists").isJsonNull() || !song.get("artists").isJsonArray()) {
            return "";
        }
        com.google.gson.JsonArray artists = song.getAsJsonArray("artists");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < artists.size(); i++) {
            com.google.gson.JsonElement element = artists.get(i);
            if (element == null || !element.isJsonObject()) {
                continue;
            }
            String name = stringValue(element.getAsJsonObject(), "name");
            if (TextUtils.isEmpty(name)) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append('/');
            }
            sb.append(name);
        }
        return sb.toString();
    }

    private static String albumName(com.google.gson.JsonObject song) {
        if (song == null || !song.has("album") || song.get("album").isJsonNull() || !song.get("album").isJsonObject()) {
            return "";
        }
        return stringValue(song.getAsJsonObject("album"), "name");
    }

    private static String normalizePicUrl(String picUrl) {
        if (TextUtils.isEmpty(picUrl)) {
            return "";
        }
        return picUrl.startsWith("http://") ? "https://" + picUrl.substring("http://".length()) : picUrl;
    }

    /**
     * 自行下载封面图片，避免 DownloadManager 对重定向 / Referer 校验的兼容问题。
     */
    @Override
    public void downloadLyric(final ResultData.Item item, final boolean z) {
        com.sds.android.sdk.lib.p065e.TaskScheduler.start(new Runnable() {
            @Override
            public void run() {
                try {
                    m2155b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_STARTED);
                    byte[] data = requestBytes(item.getUrl());
                    if (data != null && data.length > 0
                            && BitmapFactory.decodeByteArray(data, 0, data.length) != null) {
                        File target = new File(item.getLocalLyricPath());
                        File parent = target.getParentFile();
                        if (parent != null) {
                            FileUtils.createFolder(parent.getAbsolutePath());
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(target);
                        try {
                            fileOutputStream.write(data);
                        } finally {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayList<String> list = new ArrayList<>();
                        list.add(item.getLocalLyricPath());
                        m2154b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FINISHED, null, list, item.getId());
                        if (z) {
                            m2164a(SearchStatus.SEARCH_ONLINE_FINISHED);
                        }
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                m2154b(getLyricSearchTaskInfo(), SearchStatus.SEARCH_DOWNLOAD_FAILURE, null, null, item.getId());
                if (z) {
                    m2164a(SearchStatus.SEARCH_ONLINE_FAILURE);
                }
            }
        });
    }

    /* renamed from: a */
    public static String m2138a(PictureSearchTaskInfo pictureSearchTaskInfo) {
        StringBuilder sb = new StringBuilder();
        try {
            MediaItem i = pictureSearchTaskInfo.getMediaItem();
            boolean e = pictureSearchTaskInfo.isAuto();
            sb.append("https://music.163.com/api/search/get/web?");
            String artist = i.getArtist();
            if (e && !TextUtils.isEmpty(pictureSearchTaskInfo.m2196g())) {
                artist = pictureSearchTaskInfo.m2196g();
            }
            if (!TTTextUtils.isValidateMediaString(artist)) {
                artist = "";
            }
            sb.append("artist=");
            sb.append(URLEncoder.encode(artist, "UTF-8"));
            pictureSearchTaskInfo.setSinger(artist);
            String title = i.getTitle();
            sb.append("&title=");
            sb.append(URLEncoder.encode(title, "UTF-8"));
            pictureSearchTaskInfo.setTitle(title);
            if (!i.isOnline()) {
                sb.append("&filename=");
                sb.append(URLEncoder.encode(pictureSearchTaskInfo.getSongInfo()[1], "UTF-8"));
            }
            if (!i.isOnline()) {
                sb.append("&mediatype=");
                sb.append(URLEncoder.encode(pictureSearchTaskInfo.getSongInfo()[2], "UTF-8"));
            }
            sb.append("&x=");
            sb.append(DisplayUtils.getWidth());
            sb.append("&y=");
            sb.append(DisplayUtils.getHeight());
            if (i.isOnline()) {
                sb.append("&song_id=");
                sb.append(i.getSongID());
                long artistID = i.getArtistID();
                if (0 != artistID) {
                    sb.append("&singer_id=");
                    sb.append(artistID);
                }
            }
            sb.append("&auto=");
            sb.append(1);
            sb.append("&s=");
            sb.append(EnvironmentUtils.UUIDConfig.getS());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    static {
        f7307c.add("result.xml");
        f7307c.add("result.json");
        f7307c.add("info.json");
        f7307c.add("userpic.json");
        f7307c.add("uopicid");
    }

    /* renamed from: d */
    private String m2124d(String str) {
        SearchMediaLinkInfo m3135a;
        if (StringUtils.isEmpty(this.f7309b) && (m3135a = SearchSqliteDb.m3135a(BaseApplication.getApplication().getContentResolver(), str)) != null && !StringUtils.isEmpty(m3135a.getArtist())) {
            this.f7309b = m3135a.getArtist();
        }
        return this.f7309b;
    }

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected ArrayList<String> mo2135a(String str, String str2) {
        MediaItem i = getLyricSearchTaskInfo().getMediaItem();
        if (i == null || StringUtils.isEmpty(i.getID())) {
            return null;
        }
        String m2124d = m2124d(i.getID());
        if (StringUtils.isEmpty(m2124d)) {
            m2124d = FileUtils.removeWrongCharacter(i.getArtist());
            if (!TTTextUtils.isValidateMediaString(m2124d)) {
                return null;
            }
        }
        // 只认属于当前歌曲的文件（文件名以媒体 ID 的哈希为前缀），
        // 避免把同歌手目录下其他歌曲的封面误当成本曲封面从而跳过联网搜索。
        String prefix = Integer.toHexString(i.getID().hashCode()) + "_";
        File dir = new File(TTPodConfig.getArtistPath() + File.separator + m2124d);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }
        ArrayList<File> validFiles = new ArrayList<>();
        for (File file : files) {
            if (file == null || !file.isFile()) {
                continue;
            }
            String lowerCase = file.getName().toLowerCase();
            if (!lowerCase.startsWith(prefix)) {
                continue;
            }
            if (!(lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg")
                    || lowerCase.endsWith(".png") || lowerCase.endsWith(".webp"))) {
                continue;
            }
            if (m2125c(file.getAbsolutePath())) {
                validFiles.add(file);
            }
        }
        if (validFiles.isEmpty()) {
            return null;
        }
        java.util.Collections.sort(validFiles, new java.util.Comparator<File>() {
            @Override
            public int compare(File a, File b) {
                return Long.compare(b.lastModified(), a.lastModified());
            }
        });
        ArrayList<String> arrayList = new ArrayList<>(validFiles.size());
        for (File file : validFiles) {
            arrayList.add(file.getAbsolutePath());
        }
        return arrayList;
    }

    public PictureSearchTask(PictureSearchTaskInfo pictureSearchTaskInfo) {
        this.f7308a = pictureSearchTaskInfo;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: f */
    public PictureSearchTaskInfo getLyricSearchTaskInfo() {
        return this.f7308a;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected String mo2129b(MediaItem mediaItem) {
        MediaTag createMediaTag;
        Exception e;
        FileOutputStream fileOutputStream;
        Throwable th;
        String localDataSource = mediaItem.getLocalDataSource();
        if (StringUtils.isEmpty(localDataSource)) {
            return null;
        }
        String str = TTPodConfig.getCacheEmbedPath() + File.separator + LyrPicFileNameUtils.m3889a(localDataSource);
        if (!m2125c(str)) {
            File file = new File(str);
            if ((!file.exists() || file.delete()) && (createMediaTag = MediaTag.createMediaTag(localDataSource, true)) != null) {
                byte[] cover = createMediaTag.cover();
                createMediaTag.close();
                if (cover != null && cover.length > 0) {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (Exception e1) {
                        e = e1;
                        fileOutputStream = null;
                    } catch (Throwable th2) {
                        fileOutputStream = null;
                        th = th2;
                        if (fileOutputStream != null) {
                        }
                       // throw th;
                    }
                    try {
                        try {
                            fileOutputStream.write(cover);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                           // throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        createMediaTag.close();
                        if (m2125c(str)) {
                        }
                    }
                }
                createMediaTag.close();
            }
        }
        if (m2125c(str)) {
            return str;
        }
        return findFolderArtwork(localDataSource);
    }

    /**
     * 当音频没有内嵌封面时，尝试使用歌曲所在目录的封面图（cover.jpg / folder.jpg 等）。
     */
    private String findFolderArtwork(String localDataSource) {
        File audio = new File(localDataSource);
        File dir = audio.getParentFile();
        if (dir == null || !dir.isDirectory()) {
            return null;
        }
        String baseName = FileUtils.getFilenameWithoutExtension(audio.getName());
        String baseLower = baseName == null ? null : baseName.toLowerCase();
        String[] commonNames = {"cover", "folder", "album", "albumart", "albumart1", "albumartsmall", "front", "artwork"};
        String[] extensions = {"jpg", "jpeg", "png", "webp"};
        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }
        for (File file : files) {
            if (file == null || !file.isFile()) {
                continue;
            }
            String name = file.getName().toLowerCase();
            int dot = name.lastIndexOf('.');
            if (dot <= 0) {
                continue;
            }
            String extension = name.substring(dot + 1);
            boolean extensionMatched = false;
            for (String candidate : extensions) {
                if (candidate.equals(extension)) {
                    extensionMatched = true;
                    break;
                }
            }
            if (!extensionMatched) {
                continue;
            }
            String stem = name.substring(0, dot);
            boolean nameMatched = false;
            for (String candidate : commonNames) {
                if (candidate.equals(stem)) {
                    nameMatched = true;
                    break;
                }
            }
            if (!nameMatched && baseLower != null && baseLower.equals(stem)) {
                nameMatched = true;
            }
            if (nameMatched && m2125c(file.getAbsolutePath())) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    /* renamed from: c */
    protected boolean m2125c(String str) {
        boolean z = !StringUtils.isEmpty(str);
        if (z) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            return options.outMimeType != null;
        }
        return z;
    }
}
