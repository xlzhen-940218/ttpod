package com.sds.android.ttpod.cmmusic.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

import com.sds.android.ttpod.R;


/* loaded from: classes.dex */
public class UnderlineIndicator extends View {

    /* renamed from: a */
    private final Paint f3535a;

    /* renamed from: b */
    private boolean f3536b;

    /* renamed from: c */
    private int f3537c;

    /* renamed from: d */
    private int f3538d;

    /* renamed from: e */
    private int f3539e;

    /* renamed from: f */
    private int f3540f;

    /* renamed from: g */
    private int f3541g;

    /* renamed from: h */
    private float f3542h;

    /* renamed from: i */
    private int f3543i;

    /* renamed from: j */
    private float f3544j;

    /* renamed from: k */
    private int f3545k;

    /* renamed from: l */
    private final float f3546l;

    /* renamed from: m */
    private final Runnable f3547m;

    public UnderlineIndicator(Context context) {
        this(context, null);
    }

    public UnderlineIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiUnderlinePageIndicatorStyle);
    }

    @SuppressLint("ResourceType")
    public UnderlineIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3535a = new Paint(1);
        this.f3544j = -1.0f;
        this.f3545k = -1;
        this.f3546l = 30.0f;
        this.f3547m = new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.UnderlineIndicator.1
            @Override // java.lang.Runnable
            public void run() {
                if (UnderlineIndicator.this.f3536b) {
                    int max = Math.max(UnderlineIndicator.this.f3535a.getAlpha() - UnderlineIndicator.this.f3539e, 0);
                    UnderlineIndicator.this.f3535a.setAlpha(max);
                    UnderlineIndicator.this.invalidate();
                    if (max > 0) {
                        UnderlineIndicator.this.postDelayed(this, 30L);
                    }
                }
            }
        };
        if (!isInEditMode()) {
            Resources resources = getResources();
            boolean z = resources.getBoolean(R.bool.default_underline_indicator_fades);
            int integer = resources.getInteger(R.integer.default_underline_indicator_fade_delay);
            int integer2 = resources.getInteger(R.integer.default_underline_indicator_fade_length);
            int color = resources.getColor(R.color.default_underline_indicator_selected_color);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.UnderlineIndicator, i, 0);
            setFades(obtainStyledAttributes.getBoolean(R.styleable.UnderlineIndicator_fades, z));
            setSelectedColor(obtainStyledAttributes.getColor(R.styleable.UnderlineIndicator_selectedColor, color));
            setFadeDelay(obtainStyledAttributes.getInteger(R.styleable.UnderlineIndicator_fadeDelay, integer));
            setFadeLength(obtainStyledAttributes.getInteger(R.styleable.UnderlineIndicator_fadeLength, integer2));
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.UnderlineIndicator_android_background);
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            obtainStyledAttributes.recycle();
            this.f3543i = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context));
        }
    }

    public boolean getFades() {
        return this.f3536b;
    }

    public void setFades(boolean z) {
        if (z != this.f3536b) {
            this.f3536b = z;
            if (z) {
                post(this.f3547m);
                return;
            }
            removeCallbacks(this.f3547m);
            this.f3535a.setAlpha(255);
            invalidate();
        }
    }

    public int getFadeDelay() {
        return this.f3537c;
    }

    public void setFadeDelay(int i) {
        this.f3537c = i;
    }

    public int getFadeLength() {
        return this.f3538d;
    }

    public void setFadeLength(int i) {
        this.f3538d = i;
        this.f3539e = 255 / (this.f3538d / 30);
    }

    public int getSelectedColor() {
        return this.f3535a.getColor();
    }

    public void setSelectedColor(int i) {
        this.f3535a.setColor(i);
        invalidate();
    }

    public void setCurrentItem(int i) {
        this.f3540f = i;
        invalidate();
    }

    public void setPageCount(int i) {
        this.f3541g = i;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f3541g != 0) {
            if (this.f3540f >= this.f3541g) {
                setCurrentItem(this.f3541g - 1);
                return;
            }
            int paddingLeft = getPaddingLeft();
            float width = (((getWidth() - paddingLeft) - getPaddingRight()) / (1.0f * this.f3541g)) - 60.0f;
            float f = (30.0f * ((this.f3540f * 2) + 1)) + paddingLeft + ((this.f3540f + this.f3542h) * width);
            canvas.drawRect(f, getPaddingTop(), f + width, getHeight() - getPaddingBottom(), this.f3535a);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f3540f = savedState.f3549a;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f3549a = this.f3540f;
        return savedState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.sds.android.ttpod.cmmusic.fragment.UnderlineIndicator.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };

        /* renamed from: a */
        private int f3549a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f3549a = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3549a);
        }
    }
}
