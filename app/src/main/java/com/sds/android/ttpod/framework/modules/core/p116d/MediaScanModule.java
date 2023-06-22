package com.sds.android.ttpod.framework.modules.core.p116d;

import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.ModuleManager;
import com.sds.android.ttpod.framework.modules.core.p116d.MediaScanner;
import com.sds.android.ttpod.framework.modules.core.p116d.p118b.WifiTransmission;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.core.d.a */
/* loaded from: classes.dex */
public final class MediaScanModule extends BaseModule implements MediaScanner.InterfaceC1870a {

    /* renamed from: a */
    private MediaScanner f5965a = new MediaScanner();

    /* renamed from: b */
    private WifiTransmission f5966b;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID mo3239id() {
        return ModuleID.MEDIA_SCAN;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.START_SCAN, ReflectUtils.m8375a(cls, "startScan", Collection.class, String.class));
        map.put(CommandID.STOP_SCAN, ReflectUtils.m8375a(cls, "stopScan", new Class[0]));
        map.put(CommandID.SCAN_PROGRESS, ReflectUtils.m8375a(cls, "progress", new Class[0]));
        map.put(CommandID.SCANNED_FILE_COUNT, ReflectUtils.m8375a(cls, "scannedFileCount", new Class[0]));
        map.put(CommandID.SCANNING_FILE_PATH, ReflectUtils.m8375a(cls, "scanningFilePath", new Class[0]));
        map.put(CommandID.START_WIFI_TRANSMISSION, ReflectUtils.m8375a(cls, "startWifiTransmission", new Class[0]));
        map.put(CommandID.STOP_WIFI_TRANSMISSION, ReflectUtils.m8375a(cls, "stopWifiTransmission", new Class[0]));
    }

    public MediaScanModule() {
        this.f5965a.m4239a(this);
    }

    public void startScan(Collection<String> collection, String str) {
        DebugUtils.m8426a(str, "groupID");
        if (!str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX)) {
            throw new IllegalArgumentException("groupID " + str + " can not be scan into!");
        }
        this.f5965a.m4235a(collection, str);
    }

    public void stopScan() {
        this.f5965a.m4240a();
    }

    public Integer progress() {
        return this.f5965a.m4234b();
    }

    public Integer scannedFileCount() {
        return this.f5965a.m4229d();
    }

    public String scanningFilePath() {
        return this.f5965a.m4231c();
    }

    public void startWifiTransmission() {
        stopWifiTransmission();
        this.f5966b = new WifiTransmission();
        this.f5966b.m4179b();
    }

    public void stopWifiTransmission() {
        try {
            if (this.f5966b != null) {
                this.f5966b.m4178c();
                this.f5966b = null;
                m4265a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.core.p116d.MediaScanner.InterfaceC1870a
    public void onScanFinished() {
        this.f5965a = null;
        m4265a();
    }

    /* renamed from: a */
    private void m4265a() {
        if (this.f5966b == null && this.f5965a == null) {
            ModuleManager.m4117a().m4113a(mo3239id());
        }
    }
}
