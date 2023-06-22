package com.sds.android.ttpod.activities.musiccircle.message;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;

/* renamed from: com.sds.android.ttpod.activities.musiccircle.message.b */
/* loaded from: classes.dex */
public class MessageClickableSpan extends ClickableSpan {

    /* renamed from: f */
    private static int f2841f = -11365447;

    /* renamed from: a */
    private WrapUserPostListFragment.InterfaceC1704a f2842a;

    /* renamed from: b */
    private Post f2843b;

    /* renamed from: c */
    private Notice f2844c;

    /* renamed from: d */
    private LookNoticeListener f2845d;

    /* renamed from: e */
    private TTPodUser f2846e;

    /* renamed from: a */
    public static void m7917a(int i) {
        f2841f = i;
    }

    public MessageClickableSpan(Notice notice, Post post, LookNoticeListener lookNoticeListener) {
        this.f2844c = notice;
        this.f2843b = post;
        this.f2845d = lookNoticeListener;
    }

    public MessageClickableSpan(TTPodUser tTPodUser, WrapUserPostListFragment.InterfaceC1704a interfaceC1704a) {
        this.f2846e = tTPodUser;
        this.f2842a = interfaceC1704a;
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(f2841f);
        textPaint.setUnderlineText(false);
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        if (this.f2843b != null) {
            this.f2845d.mo7541a(this.f2844c, this.f2843b);
        }
        if (this.f2846e != null && this.f2842a != null) {
            this.f2842a.mo5428a(this.f2846e);
        }
    }
}
