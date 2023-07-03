package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
public class CheckImageView extends androidx.appcompat.widget.AppCompatImageView {

    /* renamed from: a */
    private boolean checked;

    /* renamed from: b */
    private OnCheckedChangeListener onCheckedChangeListener;

    /* renamed from: c */
    private Drawable unCheckDrawable;

    /* renamed from: d */
    private Drawable checkDrawable;

    /* renamed from: e */
    private View.OnClickListener onClickListener;

    /* renamed from: com.sds.android.ttpod.widget.CheckImageView$a */
    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        /* renamed from: a */
        void onChecked(CheckImageView checkImageView, boolean checked, boolean manual);
    }

    public CheckImageView(Context context) {
        super(context);
        this.onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.CheckImageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckImageView.this.setChecked(!CheckImageView.this.checked, true);
            }
        };
        setOnClickListener(this.onClickListener);
    }

    public CheckImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.CheckImageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckImageView.this.setChecked(!CheckImageView.this.checked, true);
            }
        };
        setOnClickListener(this.onClickListener);
    }

    public CheckImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.CheckImageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CheckImageView.this.setChecked(!CheckImageView.this.checked, true);
            }
        };
        setOnClickListener(this.onClickListener);
    }

    /* renamed from: a */
    public void m1899a(int unCheckImgResId, int checkImgResId) {
        if (checkImgResId != 0) {
            this.checkDrawable = getContext().getResources().getDrawable(checkImgResId);
        } else {
            this.checkDrawable = null;
        }
        if (unCheckImgResId != 0) {
            this.unCheckDrawable = getContext().getResources().getDrawable(unCheckImgResId);
        } else {
            this.unCheckDrawable = this.checkDrawable;
        }
        setImageDrawable(this.checked ? this.checkDrawable : this.unCheckDrawable);
    }

    /* renamed from: a */
    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        setChecked(checked, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void setChecked(boolean checked, boolean manual) {
        if (this.checked != checked) {
            this.checked = checked;
            if (this.onCheckedChangeListener != null) {
                this.onCheckedChangeListener.onChecked(this, this.checked, manual);
            }
            setImageDrawable(this.checked ? this.checkDrawable : this.unCheckDrawable);
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }
}
