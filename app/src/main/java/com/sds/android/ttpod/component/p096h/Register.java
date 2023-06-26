package com.sds.android.ttpod.component.p096h;

import android.content.Context;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.io.File;

/* renamed from: com.sds.android.ttpod.component.h.a */
/* loaded from: classes.dex */
public final class Register {

    /* renamed from: a */
    private static final int[] f4302a = {-1857714090, -47692346, -1232996205, 796674487};

    /* renamed from: a */
    public static final String m6401a(String str) {
        boolean z;
        long longValue;
        long longValue2;
        if (str == null || str.equals("")) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(str.trim());
        if (stringBuffer.equals(FeedbackItem.STATUS_WAITING)) {
            return "";
        }
        for (int i = 0; i < stringBuffer.length(); i++) {
            char charAt = stringBuffer.charAt(i);
            if (charAt < '0' || charAt > ';') {
                z = true;
                break;
            }
        }
        z = false;
        if (stringBuffer.length() < 16) {
            while (stringBuffer.length() < 16) {
                stringBuffer.append(FeedbackItem.STATUS_WAITING);
            }
        }
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            if (z) {
                Long valueOf = Long.valueOf(Long.parseLong(stringBuffer.substring(0, 8), 16));
                Long valueOf2 = Long.valueOf(Long.parseLong(stringBuffer.substring(8, 16), 16));
                longValue = valueOf.longValue();
                longValue2 = valueOf2.longValue();
            } else {
                longValue = Long.valueOf(stringBuffer.substring(0, 8), 16).longValue();
                longValue2 = Long.valueOf(stringBuffer.substring(8, 16), 16).longValue();
            }
            iArr[0] = (int) longValue;
            iArr[1] = (int) longValue2;
            int[] m6400a = m6400a(iArr, f4302a, 16);
            StringBuffer stringBuffer2 = new StringBuffer(String.format("%08x%08x", Integer.valueOf(m6400a[0]), Integer.valueOf(m6400a[1])).toLowerCase());
            StringBuffer stringBuffer3 = new StringBuffer();
            for (int i2 = 0; i2 < stringBuffer2.length(); i2++) {
                int intValue = Integer.valueOf(stringBuffer2.substring(i2, i2 + 1), 16).intValue();
                if (intValue >= 10) {
                    stringBuffer3.append(intValue - 10);
                } else {
                    stringBuffer3.append(intValue);
                }
            }
            return stringBuffer3.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static int[] m6400a(int[] iArr, int[] iArr2, int i) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr2[0];
        int i5 = iArr2[1];
        int i6 = iArr2[2];
        int i7 = iArr2[3];
        int i8 = i3;
        int i9 = i2;
        int i10 = 0;
        for (int i11 = 0; i11 < i; i11++) {
            i10 -= 1640531527;
            i9 += (((i8 << 4) + i4) ^ (i8 + i10)) ^ ((i8 >>> 5) + i5);
            i8 += (((i9 << 4) + i6) ^ (i9 + i10)) ^ ((i9 >>> 5) + i7);
        }
        return new int[]{i9, i8};
    }

    /* renamed from: a */
    public static void m6402a(Context context) {
        if (EnvironmentUtils.AppConfig.getVerificationEnable() && EnvironmentUtils.AppConfig.getTestMode()) {
            final String str = TTPodConfig.m5311B() + File.separator + "user_data.xml";
            RegisterData m6396a = RegisterData.m6396a(str);
            String m8478c = StringUtils.isEmpty(EnvironmentUtils.DeviceConfig.getDeviceId()) ? EnvironmentUtils.DeviceConfig.getMacAddress() : EnvironmentUtils.DeviceConfig.getDeviceId();
            final String m6401a = m6401a(m8478c);
            if (!m6396a.m6395a("serialnumber", "").equals(m6401a)) {
                EditTextDialog editTextDialog = new EditTextDialog(context, new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(0, "设备串号:", m8478c, "", 1, 17).m6901a(), new EditTextDialog.C1144a(1, "注册码:", "", "请输入注册码", 1, 17)}, R.string.verify, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.component.h.a.1
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(EditTextDialog editTextDialog2) {
                        String obj = editTextDialog2.m6902c(1).m6896d().toString();
                        if (!StringUtils.equals(m6401a, obj)) {
                            editTextDialog2.m7242f(false);
                            PopupsUtils.m6721a("注册码输入错误");
                            return;
                        }
                        editTextDialog2.m7242f(true);
                        RegisterData m6396a2 = RegisterData.m6396a(str);
                        m6396a2.m6391b("serialnumber", obj);
                        m6396a2.m6397a();
                    }
                }, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.component.h.a.2
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(EditTextDialog editTextDialog2) {
                        CommandCenter.getInstance().execute(new Command(CommandID.EXIT, new Object[0]));
                    }
                });
                editTextDialog.setTitle("天天动听内测验证");
                editTextDialog.setCancelable(false);
                editTextDialog.show();
            }
        }
    }
}
