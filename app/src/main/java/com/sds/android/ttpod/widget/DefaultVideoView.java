package com.sds.android.ttpod.widget;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.sds.android.sdk.lib.util.LogUtils;

import java.io.IOException;

/* loaded from: classes.dex */
public class DefaultVideoView extends SurfaceView implements MediaController.LapseChangedListener, MediaController.InterfaceC2200b {

    /* renamed from: A */
    private MediaPlayer.OnPreparedListener onPreparedListener;

    /* renamed from: B */
    private MediaPlayer.OnCompletionListener onCompletionListener;

    /* renamed from: C */
    private MediaPlayer.OnErrorListener onErrorListener;

    /* renamed from: D */
    private MediaPlayer.OnInfoListener onInfoListener;

    /* renamed from: E */
    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener;

    /* renamed from: F */
    private SurfaceHolder.Callback callback;

    /* renamed from: a */
    private Context context;

    /* renamed from: b */
    private Uri f7530b;

    /* renamed from: c */
    private int f7531c;

    /* renamed from: d */
    private int f7532d;

    /* renamed from: e */
    private int f7533e;

    /* renamed from: f */
    private SurfaceHolder surfaceHolder;

    /* renamed from: g */
    private MediaPlayer mediaPlayer;

    /* renamed from: h */
    private int f7536h;

    /* renamed from: i */
    private int f7537i;

    /* renamed from: j */
    private int f7538j;

    /* renamed from: k */
    private int f7539k;

    /* renamed from: l */
    private MediaController f7540l;

    /* renamed from: m */
    private MediaPlayer.OnCompletionListener onCompletionListener1;

    /* renamed from: n */
    private MediaPlayer.OnPreparedListener f7542n;

    /* renamed from: o */
    private MediaPlayer.OnBufferingUpdateListener f7543o;

    /* renamed from: p */
    private MediaPlayer.OnErrorListener f7544p;

    /* renamed from: q */
    private MediaPlayer.OnInfoListener f7545q;

    /* renamed from: r */
    private View.OnClickListener f7546r;

    /* renamed from: s */
    private int f7547s;

    /* renamed from: t */
    private long f7548t;

    /* renamed from: u */
    private boolean f7549u;

    /* renamed from: v */
    private boolean f7550v;

    /* renamed from: w */
    private boolean f7551w;

    /* renamed from: x */
    private MediaTitleBanner f7552x;

    /* renamed from: y */
    private MediaController.LapseChangedListener lapseChangedListener;

    /* renamed from: z */
    private MediaPlayer.OnVideoSizeChangedListener f7554z;

    public DefaultVideoView(Context context) {
        super(context);
        this.f7532d = 0;
        this.f7533e = 0;
        this.surfaceHolder = null;
        this.mediaPlayer = null;
        this.f7554z = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                DefaultVideoView.this.f7536h = mediaPlayer.getVideoWidth();
                DefaultVideoView.this.f7537i = mediaPlayer.getVideoHeight();
                if (DefaultVideoView.this.f7536h != 0 && DefaultVideoView.this.f7537i != 0) {
                    DefaultVideoView.this.getHolder().setFixedSize(DefaultVideoView.this.f7536h, DefaultVideoView.this.f7537i);
                }
            }
        };
        this.onPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                LogUtils.error("DefaultVideoView", "onPrepared");
                DefaultVideoView.this.f7532d = 2;
                DefaultVideoView.this.f7549u = true;
                DefaultVideoView.this.f7550v = true;
                DefaultVideoView.this.f7551w = true;
                if (DefaultVideoView.this.f7542n != null) {
                    DefaultVideoView.this.f7542n.onPrepared(DefaultVideoView.this.mediaPlayer);
                }
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.setEnabled(true);
                }
                DefaultVideoView.this.f7536h = mediaPlayer.getVideoWidth();
                DefaultVideoView.this.f7537i = mediaPlayer.getVideoHeight();
                long j = DefaultVideoView.this.f7548t;
                if (j != 0) {
                    DefaultVideoView.this.mo1690a(j);
                }
                if (DefaultVideoView.this.f7536h == 0 || DefaultVideoView.this.f7537i == 0) {
                    if (DefaultVideoView.this.f7533e == 3) {
                        LogUtils.error("DefaultVideoView", "inner onPrepared call start 2");
                        DefaultVideoView.this.mo1689b();
                        return;
                    }
                    return;
                }
                DefaultVideoView.this.getHolder().setFixedSize(DefaultVideoView.this.f7536h, DefaultVideoView.this.f7537i);
                if (DefaultVideoView.this.f7538j == DefaultVideoView.this.f7536h && DefaultVideoView.this.f7539k == DefaultVideoView.this.f7537i) {
                    if (DefaultVideoView.this.f7533e == 3) {
                        LogUtils.error("DefaultVideoView", "inner onPrepared call start 1");
                        DefaultVideoView.this.mo1689b();
                        if (DefaultVideoView.this.f7540l != null) {
                            DefaultVideoView.this.f7540l.m1710c();
                        }
                        if (DefaultVideoView.this.f7552x != null) {
                            DefaultVideoView.this.f7552x.m1294b();
                        }
                    } else if (!DefaultVideoView.this.mo1687e()) {
                        if ((j != 0 || DefaultVideoView.this.getCurrentPosition() > 0) && DefaultVideoView.this.f7540l != null) {
                            DefaultVideoView.this.f7540l.m1718a(0, true);
                        }
                    }
                }
            }
        };
        this.onCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                DefaultVideoView.this.f7532d = 5;
                DefaultVideoView.this.f7533e = 5;
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.m1706e();
                }
                if (DefaultVideoView.this.f7552x != null) {
                    DefaultVideoView.this.f7552x.m1299a();
                }
                if (DefaultVideoView.this.onCompletionListener1 != null) {
                    DefaultVideoView.this.onCompletionListener1.onCompletion(DefaultVideoView.this.mediaPlayer);
                }
            }
        };
        this.onErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                DefaultVideoView.this.f7532d = -1;
                DefaultVideoView.this.f7533e = -1;
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.m1706e();
                }
                if (DefaultVideoView.this.f7552x != null) {
                    DefaultVideoView.this.f7552x.m1299a();
                }
                if (DefaultVideoView.this.f7544p == null || DefaultVideoView.this.f7544p.onError(DefaultVideoView.this.mediaPlayer, i, i2)) {
                }
                return true;
            }
        };
        this.onInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                if (DefaultVideoView.this.f7545q != null) {
                    DefaultVideoView.this.f7545q.onInfo(mediaPlayer, i, i2);
                    return true;
                }
                return true;
            }
        };
        this.onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.6
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                DefaultVideoView.this.f7547s = i;
                if (DefaultVideoView.this.f7543o != null) {
                    DefaultVideoView.this.f7543o.onBufferingUpdate(mediaPlayer, i);
                }
            }
        };
        this.callback = new SurfaceHolder.Callback() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.7
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                boolean z = true;
                LogUtils.error("DefaultVideoView", "surfaceChanged");
                DefaultVideoView.this.f7538j = i2;
                DefaultVideoView.this.f7539k = i3;
                boolean z2 = DefaultVideoView.this.f7533e == 3;
                if (DefaultVideoView.this.f7536h != i2 || DefaultVideoView.this.f7537i != i3) {
                    z = false;
                }
                if (DefaultVideoView.this.mediaPlayer != null && z2 && z) {
                    if (DefaultVideoView.this.f7548t != 0) {
                        DefaultVideoView.this.mo1690a(DefaultVideoView.this.f7548t);
                    }
                    LogUtils.error("DefaultVideoView", "surfaceChanged call start");
                    DefaultVideoView.this.mo1689b();
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                LogUtils.error("DefaultVideoView", "surfaceCreated");
                DefaultVideoView.this.surfaceHolder = surfaceHolder;
                DefaultVideoView.this.m1847h();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                LogUtils.error("DefaultVideoView", "surfaceDestroyed");
                DefaultVideoView.this.surfaceHolder = null;
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.m1706e();
                }
                if (DefaultVideoView.this.f7552x != null) {
                    DefaultVideoView.this.f7552x.m1299a();
                }
                DefaultVideoView.this.m1865a(true);
            }
        };
        m1870a(context);
    }

    public DefaultVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DefaultVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7532d = 0;
        this.f7533e = 0;
        this.surfaceHolder = null;
        this.mediaPlayer = null;
        this.f7554z = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i22) {
                DefaultVideoView.this.f7536h = mediaPlayer.getVideoWidth();
                DefaultVideoView.this.f7537i = mediaPlayer.getVideoHeight();
                if (DefaultVideoView.this.f7536h != 0 && DefaultVideoView.this.f7537i != 0) {
                    DefaultVideoView.this.getHolder().setFixedSize(DefaultVideoView.this.f7536h, DefaultVideoView.this.f7537i);
                }
            }
        };
        this.onPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                LogUtils.error("DefaultVideoView", "onPrepared");
                DefaultVideoView.this.f7532d = 2;
                DefaultVideoView.this.f7549u = true;
                DefaultVideoView.this.f7550v = true;
                DefaultVideoView.this.f7551w = true;
                if (DefaultVideoView.this.f7542n != null) {
                    DefaultVideoView.this.f7542n.onPrepared(DefaultVideoView.this.mediaPlayer);
                }
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.setEnabled(true);
                }
                DefaultVideoView.this.f7536h = mediaPlayer.getVideoWidth();
                DefaultVideoView.this.f7537i = mediaPlayer.getVideoHeight();
                long j = DefaultVideoView.this.f7548t;
                if (j != 0) {
                    DefaultVideoView.this.mo1690a(j);
                }
                if (DefaultVideoView.this.f7536h == 0 || DefaultVideoView.this.f7537i == 0) {
                    if (DefaultVideoView.this.f7533e == 3) {
                        LogUtils.error("DefaultVideoView", "inner onPrepared call start 2");
                        DefaultVideoView.this.mo1689b();
                        return;
                    }
                    return;
                }
                DefaultVideoView.this.getHolder().setFixedSize(DefaultVideoView.this.f7536h, DefaultVideoView.this.f7537i);
                if (DefaultVideoView.this.f7538j == DefaultVideoView.this.f7536h && DefaultVideoView.this.f7539k == DefaultVideoView.this.f7537i) {
                    if (DefaultVideoView.this.f7533e == 3) {
                        LogUtils.error("DefaultVideoView", "inner onPrepared call start 1");
                        DefaultVideoView.this.mo1689b();
                        if (DefaultVideoView.this.f7540l != null) {
                            DefaultVideoView.this.f7540l.m1710c();
                        }
                        if (DefaultVideoView.this.f7552x != null) {
                            DefaultVideoView.this.f7552x.m1294b();
                        }
                    } else if (!DefaultVideoView.this.mo1687e()) {
                        if ((j != 0 || DefaultVideoView.this.getCurrentPosition() > 0) && DefaultVideoView.this.f7540l != null) {
                            DefaultVideoView.this.f7540l.m1718a(0, true);
                        }
                    }
                }
            }
        };
        this.onCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                DefaultVideoView.this.f7532d = 5;
                DefaultVideoView.this.f7533e = 5;
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.m1706e();
                }
                if (DefaultVideoView.this.f7552x != null) {
                    DefaultVideoView.this.f7552x.m1299a();
                }
                if (DefaultVideoView.this.onCompletionListener1 != null) {
                    DefaultVideoView.this.onCompletionListener1.onCompletion(DefaultVideoView.this.mediaPlayer);
                }
            }
        };
        this.onErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i22) {
                DefaultVideoView.this.f7532d = -1;
                DefaultVideoView.this.f7533e = -1;
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.m1706e();
                }
                if (DefaultVideoView.this.f7552x != null) {
                    DefaultVideoView.this.f7552x.m1299a();
                }
                if (DefaultVideoView.this.f7544p == null || DefaultVideoView.this.f7544p.onError(DefaultVideoView.this.mediaPlayer, i2, i22)) {
                }
                return true;
            }
        };
        this.onInfoListener = new MediaPlayer.OnInfoListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.5
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i22) {
                if (DefaultVideoView.this.f7545q != null) {
                    DefaultVideoView.this.f7545q.onInfo(mediaPlayer, i2, i22);
                    return true;
                }
                return true;
            }
        };
        this.onBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.6
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                DefaultVideoView.this.f7547s = i2;
                if (DefaultVideoView.this.f7543o != null) {
                    DefaultVideoView.this.f7543o.onBufferingUpdate(mediaPlayer, i2);
                }
            }
        };
        this.callback = new SurfaceHolder.Callback() { // from class: com.sds.android.ttpod.widget.DefaultVideoView.7
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i22, int i3) {
                boolean z = true;
                LogUtils.error("DefaultVideoView", "surfaceChanged");
                DefaultVideoView.this.f7538j = i22;
                DefaultVideoView.this.f7539k = i3;
                boolean z2 = DefaultVideoView.this.f7533e == 3;
                if (DefaultVideoView.this.f7536h != i22 || DefaultVideoView.this.f7537i != i3) {
                    z = false;
                }
                if (DefaultVideoView.this.mediaPlayer != null && z2 && z) {
                    if (DefaultVideoView.this.f7548t != 0) {
                        DefaultVideoView.this.mo1690a(DefaultVideoView.this.f7548t);
                    }
                    LogUtils.error("DefaultVideoView", "surfaceChanged call start");
                    DefaultVideoView.this.mo1689b();
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                LogUtils.error("DefaultVideoView", "surfaceCreated");
                DefaultVideoView.this.surfaceHolder = surfaceHolder;
                DefaultVideoView.this.m1847h();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                LogUtils.error("DefaultVideoView", "surfaceDestroyed");
                DefaultVideoView.this.surfaceHolder = null;
                if (DefaultVideoView.this.f7540l != null) {
                    DefaultVideoView.this.f7540l.m1706e();
                }
                if (DefaultVideoView.this.f7552x != null) {
                    DefaultVideoView.this.f7552x.m1299a();
                }
                DefaultVideoView.this.m1865a(true);
            }
        };
        m1870a(context);
    }

    public void setLapseChangedListener(MediaController.LapseChangedListener lapseChangedListener) {
        this.lapseChangedListener = lapseChangedListener;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.f7536h, i);
        int defaultSize2 = getDefaultSize(this.f7537i, i2);
        if (this.f7536h > 0 && this.f7537i > 0) {
            if (this.f7536h * defaultSize2 > this.f7537i * defaultSize) {
                defaultSize2 = (this.f7537i * defaultSize) / this.f7536h;
            } else if (this.f7536h * defaultSize2 < this.f7537i * defaultSize) {
                defaultSize = (this.f7536h * defaultSize2) / this.f7537i;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(DefaultVideoView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DefaultVideoView.class.getName());
    }

    /* renamed from: a */
    private void m1870a(Context context) {
        this.context = context;
        this.f7536h = 0;
        this.f7537i = 0;
        getHolder().addCallback(this.callback);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f7532d = 0;
        this.f7533e = 0;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        this.f7530b = uri;
        this.f7548t = 0L;
        m1847h();
        requestLayout();
        invalidate();
    }

    /* renamed from: a */
    public void m1871a() {
        if (this.f7540l != null) {
            this.f7540l.m1706e();
        }
        if (this.f7552x != null) {
            this.f7552x.m1299a();
        }
        m1865a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m1847h() {
        if (this.f7530b != null && this.surfaceHolder != null) {
            m1865a(false);
            try {
                this.mediaPlayer = new MediaPlayer();
                this.mediaPlayer.setAudioStreamType(3);
                this.mediaPlayer.setOnPreparedListener(this.onPreparedListener);
                this.mediaPlayer.setOnVideoSizeChangedListener(this.f7554z);
                this.mediaPlayer.setOnCompletionListener(this.onCompletionListener);
                this.mediaPlayer.setOnErrorListener(this.onErrorListener);
                this.mediaPlayer.setOnBufferingUpdateListener(this.onBufferingUpdateListener);
                this.mediaPlayer.setOnInfoListener(this.onInfoListener);
                this.mediaPlayer.setDataSource(this.context, this.f7530b);
                this.mediaPlayer.setDisplay(this.surfaceHolder);
                this.mediaPlayer.setScreenOnWhilePlaying(true);
                this.mediaPlayer.prepareAsync();
                this.f7532d = 1;
                this.f7531c = -1;
                this.f7547s = 0;
                m1845i();
            } catch (IOException e) {
                this.f7532d = -1;
                this.f7533e = -1;
                LogUtils.debug("DefaultVideoView", "View: onError: IOException");
                this.onErrorListener.onError(this.mediaPlayer, 2, 0);
            } catch (IllegalArgumentException e2) {
                this.f7532d = -1;
                this.f7533e = -1;
                LogUtils.debug("DefaultVideoView", "View: onError: IllegalArgumentException");
                this.onErrorListener.onError(this.mediaPlayer, 2, 0);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public void setMediaController(MediaController mediaController) {
        if (this.f7540l != null) {
            this.f7540l.m1706e();
        }
        this.f7540l = mediaController;
        m1845i();
    }

    /* renamed from: i */
    private void m1845i() {
        if (this.mediaPlayer != null && this.f7540l != null) {
            this.f7540l.setMediaPlayer(this);
            this.f7540l.setAnchorView(getAnchorView());
            this.f7540l.setEnabled(m1841k());
            this.f7540l.setLapseChangedListener(this);
        }
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.f7542n = onPreparedListener;
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.onCompletionListener1 = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.f7544p = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.f7545q = onInfoListener;
    }

    public void setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener) {
        this.f7543o = onBufferingUpdateListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m1865a(boolean z) {
        try {
            if (this.mediaPlayer != null) {
                this.mediaPlayer.release();
                this.mediaPlayer = null;
                this.f7532d = 0;
                if (z) {
                    this.f7533e = 0;
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (m1841k() && this.f7540l != null) {
            m1843j();
            return false;
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (m1841k() && this.f7540l != null) {
            m1843j();
            return false;
        }
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (m1841k() && z && this.f7540l != null) {
            if (i == 79 || i == 85) {
                if (this.mediaPlayer.isPlaying()) {
                    mo1688c();
                    this.f7540l.m1710c();
                    this.f7552x.m1294b();
                    return true;
                }
                mo1689b();
                this.f7540l.m1706e();
                this.f7552x.m1294b();
                return true;
            } else if (i == 126) {
                if (this.mediaPlayer.isPlaying()) {
                    return true;
                }
                mo1689b();
                this.f7540l.m1706e();
                return true;
            } else if (i == 86 || i == 127) {
                if (this.mediaPlayer.isPlaying()) {
                    mo1688c();
                    this.f7540l.m1710c();
                    this.f7552x.m1294b();
                    return true;
                }
                return true;
            } else {
                m1843j();
            }
        }
        if (i == 82) {
            m1843j();
            return true;
        } else if (i == 4) {
            this.f7546r.onClick(this);
            return true;
        } else if (i == 24 || i == 25) {
            this.f7540l.m1712b();
            return false;
        } else {
            return super.onKeyDown(i, keyEvent);
        }
    }

    /* renamed from: j */
    private void m1843j() {
        if (this.f7540l.m1708d()) {
            this.f7540l.m1706e();
            this.f7552x.m1299a();
            return;
        }
        this.f7540l.m1710c();
        this.f7552x.m1294b();
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    /* renamed from: b */
    public void mo1689b() {
        LogUtils.error("DefaultVideoView", "try start");
        if (m1841k() && !this.mediaPlayer.isPlaying()) {
            LogUtils.error("DefaultVideoView", "real start");
            this.mediaPlayer.start();
            this.f7532d = 3;
        }
        this.f7533e = 3;
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    /* renamed from: c */
    public void mo1688c() {
        if (m1841k() && this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            this.f7532d = 4;
        }
        this.f7533e = 4;
    }

    /* renamed from: d */
    public void m1858d() {
        LogUtils.error("DefaultVideoView", "resume");
        m1847h();
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    public long getDuration() {
        if (m1841k()) {
            if (this.f7531c <= 0) {
                this.f7531c = this.mediaPlayer.getDuration();
            }
        } else {
            this.f7531c = -1;
        }
        return this.f7531c;
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    public long getCurrentPosition() {
        if (m1841k()) {
            return this.mediaPlayer.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    /* renamed from: a */
    public void mo1690a(long j) {
        LogUtils.error("DefaultVideoView", "seekTo");
        if (m1841k()) {
            this.mediaPlayer.seekTo((int) j);
            this.f7548t = 0L;
            return;
        }
        this.f7548t = j;
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    /* renamed from: e */
    public boolean mo1687e() {
        return m1841k() && this.mediaPlayer.isPlaying();
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    public int getBufferPercentage() {
        if (this.mediaPlayer != null) {
            return this.f7547s;
        }
        return 0;
    }

    /* renamed from: k */
    private boolean m1841k() {
        return (this.mediaPlayer == null || this.f7532d == -1 || this.f7532d == 0 || this.f7532d == 1) ? false : true;
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2200b
    /* renamed from: f */
    public boolean mo1686f() {
        return this.f7549u;
    }

    private View getAnchorView() {
        return getParent() instanceof View ? (View) getParent() : this;
    }

    public void setMediaTitleBanner(MediaTitleBanner mediaTitleBanner) {
        this.f7552x = mediaTitleBanner;
        if (this.f7552x != null) {
            this.f7552x.m1299a();
        }
        this.f7552x.m1296a(getAnchorView());
    }

    public void setOnBackEventListener(View.OnClickListener onClickListener) {
        this.f7546r = onClickListener;
        this.f7552x.m1297a(onClickListener);
    }

    public void setVideoTitle(CharSequence charSequence) {
        if (this.f7552x != null) {
            this.f7552x.m1295a(charSequence);
        }
    }

    @Override // com.sds.android.ttpod.widget.MediaController.InterfaceC2199a
    public void onLapseChanged(MediaPlayer mediaPlayer) {
        if (this.lapseChangedListener != null) {
            this.lapseChangedListener.onLapseChanged(this.mediaPlayer);
        }
    }

    /* renamed from: g */
    public void m1850g() {
        if (this.f7552x != null) {
            this.f7552x.m1299a();
        }
        if (this.f7540l != null) {
            this.f7540l.m1706e();
        }
    }
}
