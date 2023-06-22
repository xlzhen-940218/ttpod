package com.sds.android.ttpod.framework.modules.core.p116d;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.core.p116d.p117a.CDRWinSheetEntry;
import com.sds.android.ttpod.framework.modules.core.p116d.p117a.CDRWinSheetParser;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.FileMatcher;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStoreOld;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.sds.android.ttpod.framework.modules.core.d.b */
/* loaded from: classes.dex */
public final class MediaScanner {

    /* renamed from: a */
    private int f5982a;

    /* renamed from: b */
    private volatile AsyncTaskC1871b f5983b;

    /* renamed from: c */
    private InterfaceC1870a f5984c;

    /* renamed from: d */
    private boolean f5985d;

    /* renamed from: e */
    private String f5986e;

    /* renamed from: f */
    private String f5987f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MediaScanner.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.core.d.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1870a {
        void onScanFinished();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaScanner() {
        this.f5982a = 0;
        if (this.f5982a == 0) {
            int m4227e = m4227e();
            this.f5982a = m4227e == 0 ? 1 : m4227e;
            LogUtils.m8380c("MediaScanner", "MediaScanner mSystemMediaFileCount=%d", Integer.valueOf(this.f5982a));
        }
        m4225g();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m4239a(InterfaceC1870a interfaceC1870a) {
        this.f5984c = interfaceC1870a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m4235a(Collection<String> collection, String str) {
        if (collection == null || collection.size() == 0) {
            collection = Arrays.asList(m4226f());
            this.f5985d = true;
        }
        if (this.f5983b != null) {
            m4240a();
            LogUtils.m8384b("MediaScanner", "is scanning, canceled!");
        }
        this.f5983b = new AsyncTaskC1871b(collection, str);
        this.f5983b.execute(new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m4240a() {
        if (this.f5983b != null) {
            this.f5983b.m4199d();
            this.f5983b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public Integer m4234b() {
        return Integer.valueOf(this.f5983b != null ? this.f5983b.m4206b().intValue() : 100);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public String m4231c() {
        if (this.f5983b != null) {
            return this.f5983b.m4219a();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public Integer m4229d() {
        return Integer.valueOf(this.f5983b != null ? this.f5983b.m4202c().intValue() : 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int m4227e() {
        int i;
        Cursor query = null;
        Exception e;
        int i2 = 0;
        try {
            query = BaseApplication.getApplication().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"count(*)"}, null, null, null);
        } catch (Exception e1) {
            e = e1;
        }
        if (query != null) {
            i = query.moveToNext() ? query.getInt(0) : 0;
            try {
                query.close();
            } catch (Exception e2) {
                i2 = i;
                e = e2;
                e.printStackTrace();
                i = i2;
                if (i == 0) {
                }
            }
            if (i == 0) {
                return 1;
            }
            return i;
        }
        i = i2;
        if (i == 0) {
        }
        return i;
    }

    /* renamed from: f */
    private String[] m4226f() {
        String m8467b = EnvironmentUtils.C0605d.m8467b();
        String m8460d = EnvironmentUtils.C0605d.m8460d(BaseApplication.getApplication());
        ArrayList arrayList = new ArrayList();
        if (StringUtils.m8346a(m8460d) || StringUtils.m8346a(this.f5987f) || StringUtils.m8344a(this.f5987f, this.f5986e)) {
            return new String[]{m8467b};
        }
        arrayList.add(m8467b);
        arrayList.add(m8460d);
        if (SDKVersionUtils.m8365i()) {
            arrayList.add(m4232b(this.f5987f));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: g */
    private void m4225g() {
        String m8467b = EnvironmentUtils.C0605d.m8467b();
        String m8460d = EnvironmentUtils.C0605d.m8460d(BaseApplication.getApplication());
        this.f5986e = m8467b;
        this.f5987f = m8460d;
        try {
            if (StringUtils.m8346a(m8460d) || m8467b.equals(m8460d) || !m4236a(m8467b) || !m4236a(m8460d)) {
                this.f5987f = "";
            } else if (SDKVersionUtils.m8372b() && Environment.isExternalStorageRemovable() && FileUtils.m8408d(EnvironmentUtils.C0605d.m8467b(), Environment.getExternalStorageDirectory().getCanonicalPath())) {
                this.f5986e = EnvironmentUtils.C0605d.m8460d(BaseApplication.getApplication());
                this.f5987f = EnvironmentUtils.C0605d.m8467b();
            }
        } catch (Exception e) {
            this.f5986e = EnvironmentUtils.C0605d.m8467b();
            this.f5987f = "";
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private boolean m4236a(String str) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String str2 = m4232b(str) + File.separator + valueOf.toString();
        FileUtils.m8407e(str2);
        if (FileUtils.m8419a(str2)) {
            FileUtils.m8404h(str2);
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private String m4232b(String str) {
        String str2 = str + File.separator + MediaStoreOld.AUTHORITY;
        if (SDKVersionUtils.m8365i() && this.f5987f.equals(str) && !this.f5987f.equals(EnvironmentUtils.C0605d.m8467b())) {
            str2 = EnvironmentUtils.C0605d.m8470a(BaseApplication.getApplication(), EnvironmentUtils.C0605d.EnumC0607a.SECOND_SD_CARD);
        }
        if (!FileUtils.m8409d(str2)) {
            FileUtils.m8406f(str2);
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MediaScanner.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.core.d.b$b */
    /* loaded from: classes.dex */
    public class AsyncTaskC1871b extends AsyncTask<Object, Object, Void> {

        /* renamed from: d */
        private final String f5991d;

        /* renamed from: e */
        private boolean f5992e;

        /* renamed from: h */
        private volatile String f5995h;

        /* renamed from: m */
        private List<String> f6000m;

        /* renamed from: n */
        private boolean f6001n;

        /* renamed from: b */
        private MediaTag f5989b = new MediaTag();

        /* renamed from: c */
        private final Collection<String> f5990c = new HashSet();

        /* renamed from: f */
        private List<MediaItem> f5993f = new ArrayList();

        /* renamed from: g */
        private List<MediaItem> f5994g = new ArrayList();

        /* renamed from: i */
        private AtomicInteger f5996i = new AtomicInteger(0);

        /* renamed from: j */
        private AtomicInteger f5997j = new AtomicInteger(0);

        /* renamed from: k */
        private List<String> f5998k = new LinkedList();

        /* renamed from: l */
        private List<String> f5999l = new LinkedList();

        /* renamed from: o */
        private FileMatcher f6002o = new FileMatcher(new FileMatcher.CallBack() { // from class: com.sds.android.ttpod.framework.modules.core.d.b.b.1
            @Override // com.sds.android.ttpod.media.FileMatcher.CallBack
            public void onFileMatched(String str) {
                AsyncTaskC1871b.this.m4213a(str);
            }

            @Override // com.sds.android.ttpod.media.FileMatcher.CallBack
            public void onFolderMatched(String str) {
                AsyncTaskC1871b.this.f5995h = str;
            }
        });

        AsyncTaskC1871b(Collection<String> collection, String str) {
            for (String str2 : collection) {
                try {
                    this.f5990c.add(new File(str2).getCanonicalPath());
                } catch (IOException e) {
                    this.f5990c.add(str2);
                }
            }
            this.f5991d = str;
            this.f6001n = str.equals(MediaStorage.GROUP_ID_ALL_LOCAL);
        }

        /* renamed from: a */
        String m4219a() {
            return this.f5995h;
        }

        /* renamed from: b */
        Integer m4206b() {
            int intValue = ((m4202c().intValue() + this.f5997j.get()) * 100) / MediaScanner.this.f5982a;
            if (intValue >= 95) {
                intValue = 100;
            }
            return Integer.valueOf(intValue);
        }

        /* renamed from: c */
        Integer m4202c() {
            return Integer.valueOf(this.f5996i.get());
        }

        /* renamed from: d */
        void m4199d() {
            cancel(true);
            this.f6002o.stop();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(Object... objArr) {
            LogUtils.m8381c("mediaScan", "doInBackground");
            for (String str : this.f5990c) {
                LogUtils.m8381c("scan_path", str);
            }
            Collection<String> m4208a = m4208a(this.f5990c);
            for (String str2 : m4208a) {
                LogUtils.m8381c("merged_scan_path", str2);
            }
            for (String str3 : m4208a) {
                if (FileUtils.m8409d(str3)) {
                    this.f5998k.addAll(MediaStorage.queryMediaIDsUnderFolder(BaseApplication.getApplication(), str3, Preferences.m2860l(this.f5991d)));
                }
            }
            this.f6000m = MediaStorage.queryMediaIDs(BaseApplication.getApplication(), this.f5991d, Preferences.m2860l(this.f5991d));
            StringBuilder sb = new StringBuilder("|");
            Iterator<String> it = Preferences.m2882g().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (!FileUtils.m8409d(next)) {
                    it.remove();
                } else {
                    sb.append(next + "|");
                }
            }
            this.f5992e = Preferences.m2878h();
            String str4 = !Preferences.m2874i() ? "|mp3|wma|aac|m4a|ape|flac|ogg|wma|cue|wav|amr|mid|midi|" : "|mp3|wma|aac|m4a|ape|flac|ogg|wma|cue|wav|";
            for (String str5 : this.f5990c) {
                LogUtils.m8381c("scaning", str5);
                m4211a(str5, sb, str4);
            }
            m4196f();
            if (MediaScanner.this.f5985d && this.f6001n && !this.f5990c.contains(EnvironmentUtils.C0605d.m8467b()) && this.f5996i.get() == 0) {
                m4211a(EnvironmentUtils.C0605d.m8467b(), sb, str4);
                m4196f();
            }
            if (this.f5998k.size() > 0) {
                MediaStorage.deleteMediaItemList(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, this.f5998k);
                this.f5998k.clear();
            }
            if (this.f5999l.size() > 0) {
                MediaStorage.deleteMediaItemList(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, this.f5999l);
                this.f5999l.clear();
            }
            this.f6000m = null;
            MediaScanner.this.f5983b = null;
            CommandCenter.m4607a().m4595b(new Command(CommandID.SCAN_FINISHED, m4202c()), ModuleID.MEDIA_SCAN);
            return null;
        }

        /* renamed from: a */
        private Collection<String> m4208a(Collection<String> collection) {
            ArrayList<String> arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            boolean z = false;
            ArrayList arrayList3 = arrayList2;
            for (String str : collection) {
                boolean z2 = z;
                for (String str2 : arrayList) {
                    if (!str2.contains(str) && !str.contains(str2)) {
                        arrayList3.add(str2);
                        z2 = false;
                    } else if (str.contains(str2)) {
                        arrayList3.add(str2);
                        z2 = true;
                    } else if (str2.contains(str)) {
                        arrayList3.add(str);
                        z2 = true;
                    }
                }
                if (!z2) {
                    arrayList3.add(str);
                }
                arrayList.clear();
                z = z2;
                ArrayList arrayList4 = arrayList;
                arrayList = arrayList3;
                arrayList3 = arrayList4;
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(Void r5) {
            super.onPostExecute(r5);
            this.f6002o.release();
            if (!isCancelled() && MediaScanner.this.f5984c != null && (MediaScanner.this.f5983b == null || MediaScanner.this.f5983b == this)) {
                MediaScanner.this.f5984c.onScanFinished();
            }
            if (StringUtils.m8344a(Preferences.m2858m(), this.f5991d)) {
                CommandCenter.m4607a().m4596b(new Command(CommandID.SYNC_PLAYING_GROUP, new Object[0]));
            }
        }

        /* renamed from: a */
        private void m4211a(String str, StringBuilder sb, String str2) {
            if (!isCancelled()) {
                if (FileUtils.m8409d(str)) {
                    if (str.endsWith("/")) {
                        str = str.substring(0, str.length() - 1);
                    }
                    this.f6002o.start(sb.toString(), str2, Preferences.m2870j(), str);
                } else if (FileUtils.m8414b(str)) {
                    m4213a(str);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m4213a(String str) {
            this.f5995h = str;
            String m8399m = FileUtils.m8399m(str);
            if (!StringUtils.m8346a(m8399m)) {
                if (m8399m.equalsIgnoreCase("cue")) {
                    m4200c(str);
                } else if (m8399m.equalsIgnoreCase("mid") || m8399m.equalsIgnoreCase("midi") || m8399m.equalsIgnoreCase("amr")) {
                    m4198d(str);
                } else {
                    m4203b(str);
                }
            }
        }

        /* renamed from: b */
        private void m4203b(String str) {
            this.f5995h = str;
            if (this.f5989b.openFile(str, true)) {
                if (m4217a(this.f5989b.duration())) {
                    long currentTimeMillis = System.currentTimeMillis();
                    m4214a(new MediaItem(null, null, str, FileUtils.m8400l(str), this.f5989b.getTitle(), this.f5989b.getArtist(), this.f5989b.getAlbum(), this.f5989b.getGenre(), null, FileUtils.m8399m(str), 0, Integer.valueOf(this.f5989b.duration()), Integer.valueOf(this.f5989b.track()), Integer.valueOf(this.f5989b.year()), null, Integer.valueOf(this.f5989b.bitRate()), Integer.valueOf(this.f5989b.sampleRate()), Integer.valueOf(this.f5989b.channels()), this.f5989b.getComment(), 0, 0, Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), 0L, false, null, null));
                    m4197e();
                } else {
                    this.f5997j.set(this.f5997j.get() + 1);
                }
            } else {
                long currentTimeMillis2 = System.currentTimeMillis();
                m4214a(new MediaItem(null, null, str, FileUtils.m8400l(str), FileUtils.m8401k(str), "", "", "", null, FileUtils.m8399m(str), 0, 0, 0, 0, null, 0, 0, 0, "", 0, 0, Long.valueOf(currentTimeMillis2), Long.valueOf(currentTimeMillis2), 0L, false, null, null));
                m4197e();
            }
            this.f5989b.close();
        }

        /* renamed from: c */
        private void m4200c(String str) {
            CDRWinSheetParser cDRWinSheetParser;
            String m4246b;
            MediaItem m4712a;
            CDRWinSheetEntry cDRWinSheetEntry;
            int i;
            try {
                cDRWinSheetParser = new CDRWinSheetParser(str);
            } catch (IOException e) {
                e.printStackTrace();
                cDRWinSheetParser = null;
            }
            if (cDRWinSheetParser != null) {
                if (cDRWinSheetParser.hasNext() && (m4712a = MediaItemUtils.m4712a((m4246b = cDRWinSheetParser.m4246b()))) != null) {
                    this.f5999l.add(m4712a.getID());
                    int intValue = m4712a.getBitRate().intValue();
                    int intValue2 = m4712a.getSampleRate().intValue();
                    int intValue3 = m4712a.getChannels().intValue();
                    String artist = TextUtils.isEmpty(cDRWinSheetParser.m4244c()) ? m4712a.getArtist() : cDRWinSheetParser.m4244c();
                    String album = TextUtils.isEmpty(cDRWinSheetParser.m4248a()) ? m4712a.getAlbum() : cDRWinSheetParser.m4248a();
                    String comment = m4712a.getComment();
                    String genre = m4712a.getGenre();
                    int intValue4 = m4712a.getYear().intValue();
                    String title = m4712a.getTitle();
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(m4712a.getDuration().intValue());
                    CDRWinSheetEntry next = cDRWinSheetParser.next();
                    while (true) {
                        int m4249h = next.m4249h();
                        if (cDRWinSheetParser.hasNext()) {
                            cDRWinSheetEntry = cDRWinSheetParser.next();
                            i = cDRWinSheetEntry.m4251f() - m4249h;
                        } else {
                            cDRWinSheetEntry = null;
                            i = seconds - m4249h;
                        }
                        int i2 = i * 1000;
                        if (m4217a(i2)) {
                            m4214a(new MediaItem(null, null, FileUtils.m8396p(m4246b), FileUtils.m8400l(m4246b), TextUtils.isEmpty(next.m4257c()) ? title : next.m4257c(), TextUtils.isEmpty(next.m4255d()) ? artist : next.m4255d(), album, genre, null, FileUtils.m8399m(m4246b), Integer.valueOf(m4249h == 0 ? 1 : m4249h * 1000), Integer.valueOf(i2), Integer.valueOf(next.m4253e()), Integer.valueOf(intValue4), 0, Integer.valueOf(intValue), Integer.valueOf(intValue2), Integer.valueOf(intValue3), comment, 0, 0, Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.currentTimeMillis()), 0L, false, null, null));
                            if (cDRWinSheetEntry != null) {
                                m4197e();
                            }
                        }
                        if (cDRWinSheetEntry == null) {
                            break;
                        }
                        next = cDRWinSheetEntry;
                    }
                }
                cDRWinSheetParser.m4242e();
            }
        }

        /* renamed from: d */
        private void m4198d(String str) {
            String m8396p = FileUtils.m8396p(str);
            m4214a(new MediaItem(null, null, m8396p, FileUtils.m8400l(m8396p), FileUtils.m8401k(m8396p), "", "", "", null, FileUtils.m8399m(m8396p), 0, 0, 0, 0, 0, 0, 0, 0, null, 0, 0, Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.currentTimeMillis()), 0L, false, null, null));
            m4197e();
        }

        /* renamed from: e */
        private void m4197e() {
            this.f5996i.set(this.f5996i.get() + 1);
        }

        /* renamed from: a */
        private boolean m4217a(long j) {
            return !this.f5992e || j > 60000;
        }

        /* renamed from: a */
        private void m4214a(MediaItem mediaItem) {
            SecurityUtils.C0610b.m8361a("xxx_yyy");
            SecurityUtils.C0610b.m8361a("xxx/yyy");
            if (!this.f5998k.contains(mediaItem.getID())) {
                this.f5993f.add(mediaItem);
            } else {
                this.f5998k.remove(mediaItem.getID());
            }
            if (!this.f6001n && !this.f6000m.contains(mediaItem.getID())) {
                this.f5994g.add(mediaItem);
            }
            if (this.f5993f.size() > 40 || this.f5994g.size() > 40) {
                m4196f();
            }
        }

        /* renamed from: f */
        private void m4196f() {
            if (!this.f5993f.isEmpty()) {
                MediaStorage.insertMediaItems(BaseApplication.getApplication(), MediaStorage.GROUP_ID_ALL_LOCAL, this.f5993f);
                MediaStorage.insertMediaItems(BaseApplication.getApplication(), MediaStorage.GROUP_ID_RECENTLY_ADD, this.f5993f);
                this.f5993f.clear();
            }
            if (!this.f5994g.isEmpty()) {
                MediaStorage.insertMediaItems(BaseApplication.getApplication(), this.f5991d, this.f5994g);
                this.f5994g.clear();
            }
        }
    }
}
