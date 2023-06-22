package com.sds.android.ttpod.component.p091g.p093b.p094a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p091g.p092a.AnimationCommand;
import com.sds.android.ttpod.component.p091g.p092a.EventCallback;
import com.sds.android.ttpod.component.p091g.p092a.EventExecutor;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.component.g.b.a.c */
/* loaded from: classes.dex */
public abstract class ViewEventController extends ViewController implements EventCallback {

    /* renamed from: W */
    protected PlayStatus f4246W;

    /* renamed from: X */
    protected PlayStatus f4247X;

    /* renamed from: Y */
    private int f4248Y;

    /* renamed from: a */
    private final HashMap<String, EventExecutor> f4249a;

    /* renamed from: b */
    private final Rect f4250b;

    /* renamed from: c */
    private int f4251c;

    /* renamed from: e */
    public abstract int mo6467e();

    /* renamed from: f */
    public abstract int mo6466f();

    public ViewEventController(Context context, String str) {
        super(context, str);
        this.f4249a = new HashMap<>(10);
        this.f4250b = new Rect();
        this.f4246W = PlayStatus.STATUS_PAUSED;
    }

    @Override // com.sds.android.ttpod.component.p091g.p092a.EventCallback
    /* renamed from: a */
    public void mo6518a(String[] strArr, AnimationCommand animationCommand) {
        for (String str : strArr) {
            View a = mo6445a(str);
            if (a != null) {
                a.setVisibility(View.VISIBLE);
                m6521a(a, animationCommand);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p092a.EventCallback
    /* renamed from: b */
    public void mo6514b(String[] strArr, AnimationCommand animationCommand) {
        for (String str : strArr) {
            View a = mo6445a(str);
            if (a != null && a.getVisibility() == View.VISIBLE) {
                m6521a(a, animationCommand);
                a.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p092a.EventCallback
    /* renamed from: a */
    public void mo6516a(String[] strArr, boolean z, AnimationCommand animationCommand) {
        for (String str : strArr) {
            View a = mo6445a(str);
            if (a != null) {
                a.setEnabled(z);
                m6521a(a, animationCommand);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p092a.EventCallback
    /* renamed from: a */
    public void mo6520a(String[] strArr, int i, int i2, int i3, AnimationCommand animationCommand) {
        int i4;
        int i5;
        int i6 = i + this.f4251c;
        int i7 = i2 + this.f4248Y;
        for (String str : strArr) {
            View a = mo6445a(str);
            if (a != null) {
                int[] iArr = (int[]) a.getTag(R.id.tag_layout_offset);
                if (iArr != null) {
                    i5 = iArr[0] + i6;
                    i4 = iArr[1] + i7;
                } else {
                    i4 = i7;
                    i5 = i6;
                }
                int left = i5 - a.getLeft();
                int top = i4 - a.getTop();
                if (left != 0 || top != 0) {
                    a.offsetLeftAndRight(left);
                    a.offsetTopAndBottom(top);
                    m6522a(a, -left, 0, -top, 0, i3, animationCommand);
                }
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p092a.EventCallback
    /* renamed from: b */
    public void mo6515b(String[] strArr, int i, int i2, int i3, AnimationCommand animationCommand) {
        if (i != 0 || i2 != 0 || animationCommand.m6587b() != 0) {
            for (String str : strArr) {
                View a = mo6445a(str);
                if (a != null) {
                    a.offsetLeftAndRight(i);
                    a.offsetTopAndBottom(i2);
                    m6522a(a, -i, 0, -i2, 0, i3, animationCommand);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private void m6522a(View view, int i, int i2, int i3, int i4, int i5, AnimationCommand animationCommand) {
        if (view.isShown()) {
            Animation m6594a = animationCommand.m6594a();
            if (i5 > 0) {
                TranslateAnimation translateAnimation = new TranslateAnimation(i, i2, i3, i4);
                translateAnimation.setDuration(i5);
                if (!view.getGlobalVisibleRect(this.f4250b)) {
                    m6594a = translateAnimation;
                } else if (m6594a instanceof AnimationSet) {
                    ((AnimationSet)m6594a).addAnimation(translateAnimation);
                } else if (m6594a != null) {
                    AnimationSet animationSet = new AnimationSet(false);
                    animationSet.addAnimation(m6594a);
                    animationSet.addAnimation(translateAnimation);
                    m6594a = animationSet;
                } else {
                    m6594a = translateAnimation;
                }
            }
            if (m6594a != null) {
                view.startAnimation(m6594a);
            } else {
                view.invalidate();
            }
        }
    }

    /* renamed from: a */
    private void m6521a(View view, AnimationCommand animationCommand) {
        if (view.isShown() && view.getGlobalVisibleRect(this.f4250b)) {
            AnimationSet m6594a = animationCommand.m6594a();
            if (m6594a != null) {
                view.startAnimation(m6594a);
            } else {
                view.invalidate();
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p092a.EventCallback
    /* renamed from: c */
    public void mo6513c(String[] strArr, AnimationCommand animationCommand) {
        for (String str : strArr) {
            View a = mo6445a(str);
            if (a != null) {
                m6521a(a, animationCommand);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p092a.EventCallback
    /* renamed from: a */
    public void mo6519a(String[] strArr, int i, int i2, AnimationCommand animationCommand) {
        int i3;
        int i4;
        int i5 = i + this.f4251c;
        int i6 = i2 + this.f4248Y;
        for (String str : strArr) {
            View a = mo6445a(str);
            if (a != null) {
                a.setVisibility(View.VISIBLE);
                int[] iArr = (int[]) a.getTag(R.id.tag_layout_offset);
                if (iArr != null) {
                    i4 = i5 - iArr[0];
                    i3 = i6 - iArr[1];
                } else {
                    i3 = i6;
                    i4 = i5;
                }
                a.offsetLeftAndRight(i4 - a.getLeft());
                a.offsetTopAndBottom(i3 - a.getTop());
                m6521a(a, animationCommand);
                a.invalidate();
            }
        }
    }

    /* renamed from: a */
    public void m6517a(String[] strArr, EventExecutor eventExecutor) {
        if (strArr != null && eventExecutor != null) {
            eventExecutor.m6563a(this);
            for (String str : strArr) {
                this.f4249a.put(str, eventExecutor);
            }
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6448a(MediaItem mediaItem) {
        super.mo6448a(mediaItem);
        mo6434b("OnMetaChange");
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: r */
    public void mo6404r() {
        super.mo6404r();
        mo6434b("OnPanelShow");
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: q */
    public void mo6410q() {
        super.mo6410q();
        mo6434b("OnPanelDisappear");
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6449a(PlayMode playMode) {
        if (m6544I() != playMode) {
            super.mo6449a(playMode);
            mo6434b("OnPlayModeChange");
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6446a(PlayStatus playStatus) {
        String str = null;
        super.mo6446a(playStatus);
        if (PlayStatus.STATUS_STOPPED != playStatus && this.f4247X != playStatus) {
            this.f4247X = playStatus;
            mo6434b("OnPlayStateChange");
            if (this.f4246W == null || playStatus != this.f4246W) {
                this.f4246W = null;
                if (PlayStatus.STATUS_PLAYING == playStatus) {
                    str = "OnPlay";
                } else if (PlayStatus.STATUS_PAUSED == playStatus) {
                    str = "OnPause";
                }
                if (str != null) {
                    mo6434b(str);
                }
            }
        }
    }

    /* renamed from: b */
    public void mo6434b(String str) {
        EventExecutor eventExecutor = this.f4249a.get(str);
        if (eventExecutor != null) {
            eventExecutor.m6564a(mo6467e(), mo6466f());
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: b */
    public void mo6437b(View view) {
        m6540a(view, view.getTag(R.id.tag_event_on_click) != null);
        super.mo6437b(view);
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6456a(View view) {
        super.mo6456a(view);
        Object tag = view.getTag(R.id.tag_event_on_click);
        if (tag != null) {
            mo6434b(tag.toString());
        }
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6457a(Bitmap bitmap) {
        super.mo6457a(bitmap);
        mo6434b(bitmap == null ? "OnArtUnload" : "OnArtLoad");
    }

    @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
    /* renamed from: a */
    public void mo6451a(Lyric lyric) {
        super.mo6451a(lyric);
        mo6434b(lyric == null ? "OnLyricUnload" : "OnLyricLoad");
    }

    /* renamed from: i */
    public void m6512i(int i) {
        this.f4251c = i;
    }
}
