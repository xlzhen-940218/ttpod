package com.sds.android.ttpod.activities.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.widget.CropImageView;
import java.io.File;

/* loaded from: classes.dex */
public class CropImageActivity extends Activity {

    /* renamed from: a */
    private CropImageView f3072a;

    /* renamed from: b */
    private String f3073b;

    /* renamed from: c */
    private View f3074c;

    /* renamed from: d */
    private View f3075d;

    /* renamed from: e */
    private View.OnClickListener f3076e = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.user.CropImageActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            view.setEnabled(false);
            int id = view.getId();
            if (id == R.id.linearylayout_crop_cancel) {
                CropImageActivity.this.setResult(0);
                CropImageActivity.this.finish();
            } else if (id == R.id.linearylayout_crop_save) {
                if (CropImageActivity.this.f3072a.m1887a(CropImageActivity.this.f3073b)) {
                    CropImageActivity.this.setResult(-1);
                } else {
                    PopupsUtils.m6760a((int) R.string.userinfo_save_croped_image_error);
                    CropImageActivity.this.setResult(0);
                }
                CropImageActivity.this.finish();
            }
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_crop_image);
        this.f3074c = findViewById(R.id.linearylayout_crop_cancel);
        this.f3074c.setOnClickListener(this.f3076e);
        this.f3075d = findViewById(R.id.linearylayout_crop_save);
        this.f3075d.setOnClickListener(this.f3076e);
        Intent intent = getIntent();
        Uri uri = (Uri) intent.getParcelableExtra("input");
        this.f3073b = intent.getStringExtra("output");
        if (TextUtils.isEmpty(this.f3073b)) {
            PopupsUtils.m6721a("没有正确的文件路径，无法启动切图程序");
            return;
        }
        new File(this.f3073b).getParentFile().mkdirs();
        this.f3072a = (CropImageView) findViewById(R.id.cropView);
        this.f3072a.m1891a(intent.getIntExtra("width", 0), intent.getIntExtra("height", 0));
        this.f3072a.setImageURI(uri);
    }
}
