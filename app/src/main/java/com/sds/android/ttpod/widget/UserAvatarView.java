package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class UserAvatarView extends RoundedImageView {

    /* renamed from: b */
    private Bitmap f8061b;

    /* renamed from: c */
    private boolean f8062c;

    public UserAvatarView(Context context) {
        super(context);
        this.f8062c = false;
    }

    public UserAvatarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UserAvatarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8062c = false;
        this.f8061b = BitmapFactory.decodeResource(getResources(), R.drawable.img_user_v);
    }

    public void setVFlagVisible(boolean z) {
        this.f8062c = z;
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RoundedDrawable roundedDrawable = (RoundedDrawable) getDrawable();
        if (this.f8062c) {
            canvas.drawBitmap(this.f8061b, (getWidth() * 0.84f) - (this.f8061b.getWidth() * 0.6f), (getHeight() * 0.84f) - (this.f8061b.getHeight() * 0.6f), roundedDrawable.m1246a());
        }
    }
}
