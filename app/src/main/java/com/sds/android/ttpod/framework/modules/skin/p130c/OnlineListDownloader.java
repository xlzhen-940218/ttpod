package com.sds.android.ttpod.framework.modules.skin.p130c;

import android.text.TextUtils;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.c.j */
/* loaded from: classes.dex */
public class OnlineListDownloader implements Runnable {

    /* renamed from: a */
    protected String f6539a;

    /* renamed from: b */
    protected String f6540b;

    /* renamed from: c */
    protected CommandID f6541c;

    /* renamed from: d */
    private Long f6542d;

    public OnlineListDownloader(Long l, String str, String str2, CommandID commandID) {
        this.f6542d = l;
        this.f6540b = str;
        this.f6539a = str2;
        this.f6541c = commandID;
    }

    @Override // java.lang.Runnable
    public void run() {
        LogUtils.m8388a("OnlineListDownloader", getClass().getSimpleName() + ".OnlineListDownloader [skin]---> " + this.f6541c);
        boolean m3718b = m3718b(this.f6542d);
        if (m3718b && !TextUtils.isEmpty(this.f6539a)) {
            FileUtils.m8404h(this.f6539a);
        }
        CommandCenter.m4607a().m4595b(new Command(this.f6541c, Boolean.valueOf(m3718b)), ModuleID.SKIN);
    }

    /* renamed from: a */
    protected String mo3599a(Long l) {
        return "list_" + l + ".json";
    }

    /* renamed from: a */
    protected String mo3720a(String str) {
        return !TextUtils.isEmpty(str) ? FileUtils.m8400l(str) : TTPodConfig.m5294n();
    }

    /* renamed from: b */
    private boolean m3718b(Long l) {
        String mo3599a = mo3599a(l);
        return m3719a(this.f6540b + l + ".json", mo3720a(this.f6539a) + File.separator + mo3599a);
    }

    /* renamed from: a */
    private static void m3721a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    protected boolean m3719a(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            r3 = 0
            r1 = 0
            java.io.File r5 = new java.io.File
            r5.<init>(r9)
            boolean r0 = r5.exists()
            if (r0 == 0) goto Le
        Ld:
            return r1
        Le:
            r5.createNewFile()     // Catch: java.lang.Throwable -> L6a
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> L6a
            r0.<init>(r8)     // Catch: java.lang.Throwable -> L6a
            java.net.URLConnection r0 = r0.openConnection()     // Catch: java.lang.Throwable -> L6a
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch: java.lang.Throwable -> L6a
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L6a
            java.io.InputStream r0 = r0.getInputStream()     // Catch: java.lang.Throwable -> L6a
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L6a
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L6d
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L6d
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L6d
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L6d
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L3f
        L33:
            int r3 = r4.read(r0)     // Catch: java.lang.Throwable -> L3f
            r6 = -1
            if (r3 == r6) goto L50
            r6 = 0
            r2.write(r0, r6, r3)     // Catch: java.lang.Throwable -> L3f
            goto L33
        L3f:
            r0 = move-exception
            r3 = r4
        L41:
            com.sds.android.sdk.lib.util.FileUtils.m8412c(r5)     // Catch: java.lang.Throwable -> L66
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L66
            m3721a(r3)
            m3721a(r2)
            r0 = r1
        L4e:
            r1 = r0
            goto Ld
        L50:
            r0 = 1
            m3721a(r4)
            m3721a(r2)
            goto L4e
        L58:
            r0 = move-exception
            r4 = r3
        L5a:
            m3721a(r4)
            m3721a(r3)
            throw r0
        L61:
            r0 = move-exception
            goto L5a
        L63:
            r0 = move-exception
            r3 = r2
            goto L5a
        L66:
            r0 = move-exception
            r4 = r3
            r3 = r2
            goto L5a
        L6a:
            r0 = move-exception
            r2 = r3
            goto L41
        L6d:
            r0 = move-exception
            r2 = r3
            r3 = r4
            goto L41
        */
        return false;
        //throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.modules.skin.p130c.OnlineListDownloader.m3719a(java.lang.String, java.lang.String):boolean");
    }
}
