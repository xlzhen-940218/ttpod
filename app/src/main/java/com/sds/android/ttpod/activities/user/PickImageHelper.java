package com.sds.android.ttpod.activities.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.fragment.app.Fragment;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.ListDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import java.io.File;

/* renamed from: com.sds.android.ttpod.activities.user.c */
/* loaded from: classes.dex */
public class PickImageHelper {

    /* renamed from: a */
    private static final String f3099a = TTPodConfig.getCachePath() + File.separator + ".temp.jpg";

    /* renamed from: b */
    private Activity f3100b;

    /* renamed from: c */
    private int f3101c;

    /* renamed from: d */
    private int f3102d;

    /* renamed from: e */
    private String f3103e;

    /* renamed from: f */
    private int f3104f = 3;

    public PickImageHelper(Activity activity) {
        this.f3100b = activity;
    }

    /* renamed from: a */
    public void m7719a(final int i, final CharSequence charSequence, final int i2, final int i3) {
        final ListDialog listDialog = new ListDialog(this.f3100b, new ActionItem[]{new ActionItem(0, 0, (int) R.string.userinfo_from_photo_galley), new ActionItem(1, 0, (int) R.string.userinfo_from_photo_camera)}, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
        listDialog.m7254b(R.string.cancel, null);
        listDialog.setTitle(charSequence);
        listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.user.c.1
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i4) {
                listDialog.dismiss();
                switch (actionItem.m7005e()) {
                    case 0:
                        PickImageHelper.this.m7710a(charSequence, i, i2, i3);
                        return;
                    case 1:
                        PickImageHelper.this.m7707b(charSequence, i, i2, i3);
                        return;
                    default:
                        return;
                }
            }
        });
        listDialog.show();
    }

    /* renamed from: a */
    public boolean m7710a(CharSequence charSequence, int i, int i2, int i3) {
        try {
            m7718a(new Intent("android.intent.action.PICK").setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*"), charSequence, i, i2, i3);
            this.f3104f = 1;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public boolean m7711a(Fragment fragment, CharSequence charSequence, int i, int i2, int i3) {
        try {
            m7714a(fragment, new Intent("android.intent.action.PICK").setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*"), charSequence, i, i2, i3);
            this.f3104f = 1;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    public boolean m7707b(CharSequence charSequence, int i, int i2, int i3) {
        try {
            m7718a(new Intent("android.media.action.IMAGE_CAPTURE").putExtra("output", Uri.fromFile(new File(f3099a))), charSequence, i, i2, i3);
            this.f3104f = 2;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    public boolean m7708b(Fragment fragment, CharSequence charSequence, int i, int i2, int i3) {
        try {
            m7714a(fragment, new Intent("android.media.action.IMAGE_CAPTURE").putExtra("output", Uri.fromFile(new File(f3099a))), charSequence, i, i2, i3);
            this.f3104f = 2;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    private void m7718a(Intent intent, CharSequence charSequence, int i, int i2, int i3) {
        this.f3100b.startActivityForResult(Intent.createChooser(intent.putExtra("return-data", false), charSequence), i);
        this.f3101c = i2;
        this.f3102d = i3;
    }

    /* renamed from: a */
    private void m7714a(Fragment fragment, Intent intent, CharSequence charSequence, int i, int i2, int i3) {
        fragment.startActivityForResult(Intent.createChooser(intent.putExtra("return-data", false), charSequence), i);
        this.f3101c = i2;
        this.f3102d = i3;
    }

    /* renamed from: a */
    public void m7717a(Uri uri, String str) {
        m7716a(uri, str, this.f3101c, this.f3102d);
    }

    /* renamed from: a */
    public void m7713a(Fragment fragment, Uri uri, String str) {
        m7712a(fragment, uri, str, this.f3101c, this.f3102d);
    }

    /* renamed from: a */
    public void m7716a(Uri uri, String str, int i, int i2) {
        Uri uri2;
        this.f3103e = str;
        if (this.f3104f == 2) {
            File file = new File(f3099a);
            uri2 = file.exists() ? Uri.fromFile(file) : null;
        } else {
            uri2 = uri;
        }
        if (uri2 == null) {
            PopupsUtils.m6760a((int) R.string.userinfo_can_not_open_image);
            return;
        }
        try {
            this.f3100b.startActivityForResult(new Intent(this.f3100b, CropImageActivity.class).putExtra("input", uri2).putExtra("output", str).putExtra("width", i).putExtra("height", i2), 3);
        } catch (Exception e) {
            LogUtils.error("PickImageHelper", "无法启动剪切程序。", e);
        }
    }

    /* renamed from: a */
    public void m7712a(Fragment fragment, Uri uri, String str, int i, int i2) {
        Uri uri2;
        this.f3103e = str;
        if (this.f3104f == 2) {
            File file = new File(f3099a);
            uri2 = file.exists() ? Uri.fromFile(file) : null;
        } else {
            uri2 = uri;
        }
        if (uri2 == null) {
            PopupsUtils.m6760a((int) R.string.userinfo_can_not_open_image);
            return;
        }
        try {
            fragment.startActivityForResult(new Intent(fragment.getActivity(), CropImageActivity.class).putExtra("input", uri2).putExtra("output", str).putExtra("width", i).putExtra("height", i2), 3);
        } catch (Exception e) {
            LogUtils.error("PickImageHelper", "无法启动剪切程序。", e);
        }
    }

    /* renamed from: a */
    public void m7715a(Bundle bundle) {
        if (bundle != null) {
            bundle.putInt("width", this.f3101c);
            bundle.putInt("height", this.f3102d);
            bundle.putString("path", this.f3103e);
            bundle.putInt("pick_id", this.f3104f);
        }
    }

    /* renamed from: b */
    public void m7709b(Bundle bundle) {
        if (bundle != null) {
            this.f3101c = bundle.getInt("width", this.f3101c);
            this.f3102d = bundle.getInt("height", this.f3102d);
            this.f3104f = bundle.getInt("pick_id", this.f3104f);
            this.f3103e = bundle.getString("path");
        }
    }
}
