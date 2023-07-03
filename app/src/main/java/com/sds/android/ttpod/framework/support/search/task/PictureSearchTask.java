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
        int m8476d = EnvironmentUtils.DeviceConfig.hasNetwork();
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
            String m8403i = FileUtils.m8403i(mo2137a);
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
                            int m8476d = EnvironmentUtils.DeviceConfig.hasNetwork();
                            if (i != 0) {
                            }
                            return true;
                        }
                        i = 0;
                        int m8476d2 = EnvironmentUtils.DeviceConfig.hasNetwork();
                        if (i != 0) {
                        }
                        return true;
                    }
                    if (arrayList2 != null && !arrayList2.isEmpty()) {
                        ResultData.Item[] m2179c2 = arrayList2.get(0).getLyricArray();
                        i = m2179c2 == null ? m2179c2.length : 0;
                        int m8476d22 = EnvironmentUtils.DeviceConfig.hasNetwork();
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
        int m8476d222 = EnvironmentUtils.DeviceConfig.hasNetwork();
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

    @Override // com.sds.android.ttpod.framework.support.search.task.LyrPicBaseSearchTask
    /* renamed from: a */
    protected String buildUrl() {
        return m2138a(getLyricSearchTaskInfo());
    }

    /* renamed from: a */
    public static String m2138a(PictureSearchTaskInfo pictureSearchTaskInfo) {
        StringBuilder sb = new StringBuilder();
        try {
            MediaItem i = pictureSearchTaskInfo.getMediaItem();
            boolean e = pictureSearchTaskInfo.isAuto();
            sb.append("http://picdown.ttpod.cn/picsearch?");
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
            sb.append(EnvironmentUtils.UUIDConfig.m8494b());
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
        ArrayList<String> arrayList;
        MediaItem i = getLyricSearchTaskInfo().getMediaItem();
        String m2124d = m2124d(i.getID());
        if (StringUtils.isEmpty(m2124d)) {
            m2124d = FileUtils.removeWrongCharacter(i.getArtist());
            if (!TTTextUtils.isValidateMediaString(m2124d)) {
                return null;
            }
        }
        File[] listFiles = new File(TTPodConfig.getArtistPath() + File.separator + m2124d).listFiles(new FilenameFilter() { // from class: com.sds.android.ttpod.framework.support.search.task.c.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str3) {
                String lowerCase = str3.toLowerCase();
                if (PictureSearchTask.f7307c.contains(lowerCase)) {
                    return false;
                }
                String m8399m = FileUtils.getSuffix(lowerCase);
                return ("thumb".equals(m8399m) || "tmp".equals(m8399m)) ? false : true;
            }
        });
        if (listFiles != null) {
            arrayList = new ArrayList<>(listFiles.length);
            for (File file : listFiles) {
                arrayList.add(file.getAbsolutePath());
            }
        } else {
            arrayList = null;
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
            return null;
        }
        return str;
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
