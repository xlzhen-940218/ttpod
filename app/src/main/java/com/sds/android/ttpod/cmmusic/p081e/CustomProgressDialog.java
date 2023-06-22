package com.sds.android.ttpod.cmmusic.p081e;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sds.android.ttpod.R;


/* renamed from: com.sds.android.ttpod.cmmusic.e.d */
/* loaded from: classes.dex */
public class CustomProgressDialog extends Dialog {

    /* renamed from: b */
    private static CustomProgressDialog f3507b = null;

    /* renamed from: a */
    private Context f3508a;

    public CustomProgressDialog(Context context, int i) {
        super(context, i);
        this.f3508a = null;
    }

    /* renamed from: a */
    public static CustomProgressDialog m7275a(Context context) {
        f3507b = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        f3507b.setContentView(R.layout.cmmusic_custom_progressdialog);
        f3507b.getWindow().getAttributes().gravity = Gravity.CENTER;
        return f3507b;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        if (f3507b != null) {
            ((AnimationDrawable) ((ImageView) f3507b.findViewById(R.id.loadingImageView)).getBackground()).start();
        }
    }

    /* renamed from: a */
    public CustomProgressDialog m7274a(String str) {
        TextView textView = (TextView) f3507b.findViewById(R.id.id_tv_loadingmsg);
        if (textView != null) {
            textView.setText(str);
        }
        return f3507b;
    }
}
