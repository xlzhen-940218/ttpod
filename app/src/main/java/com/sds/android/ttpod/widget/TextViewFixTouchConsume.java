package com.sds.android.ttpod.widget;

import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/* loaded from: classes.dex */
public class TextViewFixTouchConsume extends androidx.appcompat.widget.AppCompatTextView {

    /* renamed from: a */
    private boolean f8056a;

    /* renamed from: b */
    private boolean f8057b;

    public TextViewFixTouchConsume(Context context) {
        super(context);
        this.f8056a = true;
    }

    public TextViewFixTouchConsume(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8056a = true;
    }

    public TextViewFixTouchConsume(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8056a = true;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f8057b = false;
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.f8056a) {
            return this.f8057b;
        }
        return onTouchEvent;
    }

    @Override // android.view.View
    public boolean hasFocusable() {
        return false;
    }

    /* renamed from: com.sds.android.ttpod.widget.TextViewFixTouchConsume$a */
    /* loaded from: classes.dex */
    public static class C2250a extends LinkMovementMethod {

        /* renamed from: a */
        private static C2250a f8058a;

        /* renamed from: a */
        public static C2250a m1445a() {
            if (f8058a == null) {
                f8058a = new C2250a();
            }
            return f8058a;
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 1 || action == 0) {
                int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
                int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
                int scrollX = x + textView.getScrollX();
                int scrollY = y + textView.getScrollY();
                Layout layout = textView.getLayout();
                int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
                Object[] objArr = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
                if (objArr.length != 0) {
                    if (action == 1) {
                        ((ClickableSpan)objArr[0]).onClick(textView);
                    } else {
                        Selection.setSelection(spannable, spannable.getSpanStart(objArr[0]), spannable.getSpanEnd(objArr[0]));
                    }
                    if (textView instanceof TextViewFixTouchConsume) {
                        ((TextViewFixTouchConsume) textView).f8057b = true;
                    }
                    return true;
                }
                Selection.removeSelection(spannable);
                Touch.onTouchEvent(textView, spannable, motionEvent);
                return false;
            }
            return Touch.onTouchEvent(textView, spannable, motionEvent);
        }
    }
}
