package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.skin.p130c.DateTimeUtils;


/* loaded from: classes.dex */
public class MediaController extends FrameLayout {

    /* renamed from: a */
    private InterfaceC2200b f7717a;

    /* renamed from: b */
    private Context f7718b;

    /* renamed from: c */
    private PopupWindow f7719c;

    /* renamed from: d */
    private int f7720d;

    /* renamed from: e */
    private View f7721e;

    /* renamed from: f */
    private View f7722f;

    /* renamed from: g */
    private ProgressBar f7723g;

    /* renamed from: h */
    private ProgressBar f7724h;

    /* renamed from: i */
    private TextView f7725i;

    /* renamed from: j */
    private OutlineTextView f7726j;

    /* renamed from: k */
    private long f7727k;

    /* renamed from: l */
    private boolean f7728l;

    /* renamed from: m */
    private boolean f7729m;

    /* renamed from: n */
    private boolean f7730n;

    /* renamed from: o */
    private boolean f7731o;

    /* renamed from: p */
    private ImageButton f7732p;

    /* renamed from: q */
    private AudioManager f7733q;

    /* renamed from: r */
    private LapseChangedListener f7734r;

    /* renamed from: s */
    private InterfaceC2202d f7735s;

    /* renamed from: t */
    private InterfaceC2201c f7736t;

    /* renamed from: u */
    private Handler f7737u;

    /* renamed from: v */
    private View.OnClickListener f7738v;

    /* renamed from: w */
    private SeekBar.OnSeekBarChangeListener f7739w;

    /* renamed from: com.sds.android.ttpod.widget.MediaController$a */
    /* loaded from: classes.dex */
    public interface LapseChangedListener {
        void onLapseChanged(MediaPlayer mediaPlayer);
    }

    /* renamed from: com.sds.android.ttpod.widget.MediaController$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2200b {
        /* renamed from: a */
        void mo1690a(long j);

        /* renamed from: b */
        void mo1689b();

        /* renamed from: c */
        void mo1688c();

        /* renamed from: e */
        boolean mo1687e();

        /* renamed from: f */
        boolean mo1686f();

        int getBufferPercentage();

        long getCurrentPosition();

        long getDuration();
    }

    /* renamed from: com.sds.android.ttpod.widget.MediaController$c */
    /* loaded from: classes.dex */
    public interface InterfaceC2201c {
        /* renamed from: a */
        void m1685a();
    }

    /* renamed from: com.sds.android.ttpod.widget.MediaController$d */
    /* loaded from: classes.dex */
    public interface InterfaceC2202d {
        /* renamed from: a */
        void m1684a();
    }

    public MediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7730n = true;
        this.f7731o = false;
        this.f7737u = new Handler() { // from class: com.sds.android.ttpod.widget.MediaController.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        MediaController.this.m1706e();
                        return;
                    case 2:
                        MediaController.this.m1700h();
                        removeMessages(2);
                        sendMessageDelayed(obtainMessage(2), 500L);
                        if (!MediaController.this.f7729m && MediaController.this.f7728l) {
                            MediaController.this.m1698i();
                            return;
                        }
                        return;
                    case 3:
                        MediaController.this.f7724h.setProgress(MediaController.this.getVolumeProgressValue());
                        return;
                    default:
                        return;
                }
            }
        };
        this.f7738v = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.MediaController.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaController.this.m1696j();
                MediaController.this.m1718a(3000, true);
            }
        };
        this.f7739w = new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.widget.MediaController.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                MediaController.this.f7729m = true;
                MediaController.this.m1718a(3600000, false);
                if (MediaController.this.f7730n) {
                    MediaController.this.f7733q.setStreamMute(3, true);
                }
                if (MediaController.this.f7726j != null) {
                    MediaController.this.f7726j.setText("");
                    MediaController.this.f7726j.setVisibility(View.VISIBLE);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    long j = (MediaController.this.f7727k * i) / 1000;
                    String m3747a = DateTimeUtils.m3747a(j, ":");
                    if (MediaController.this.f7730n) {
                        MediaController.this.f7717a.mo1690a(j);
                    }
                    if (MediaController.this.f7726j != null) {
                        MediaController.this.f7726j.setText(m3747a);
                    }
                    MediaController.this.f7725i.setText(m3747a + "/" + DateTimeUtils.m3747a(MediaController.this.f7727k, ":"));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!MediaController.this.f7730n) {
                    MediaController.this.f7717a.mo1690a((MediaController.this.f7727k * seekBar.getProgress()) / 1000);
                }
                if (MediaController.this.f7726j != null) {
                    MediaController.this.f7726j.setText("");
                    MediaController.this.f7726j.setVisibility(View.GONE);
                }
                MediaController.this.m1718a(3000, true);
                MediaController.this.f7733q.setStreamMute(3, false);
                MediaController.this.f7729m = false;
                MediaController.this.f7737u.removeMessages(2);
                MediaController.this.f7737u.sendEmptyMessageDelayed(2, 500L);
            }
        };
        this.f7722f = this;
        this.f7731o = true;
        m1717a(context);
    }

    public MediaController(Context context) {
        super(context);
        this.f7730n = true;
        this.f7731o = false;
        this.f7737u = new Handler() { // from class: com.sds.android.ttpod.widget.MediaController.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        MediaController.this.m1706e();
                        return;
                    case 2:
                        MediaController.this.m1700h();
                        removeMessages(2);
                        sendMessageDelayed(obtainMessage(2), 500L);
                        if (!MediaController.this.f7729m && MediaController.this.f7728l) {
                            MediaController.this.m1698i();
                            return;
                        }
                        return;
                    case 3:
                        MediaController.this.f7724h.setProgress(MediaController.this.getVolumeProgressValue());
                        return;
                    default:
                        return;
                }
            }
        };
        this.f7738v = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.MediaController.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MediaController.this.m1696j();
                MediaController.this.m1718a(3000, true);
            }
        };
        this.f7739w = new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.widget.MediaController.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                MediaController.this.f7729m = true;
                MediaController.this.m1718a(3600000, false);
                if (MediaController.this.f7730n) {
                    MediaController.this.f7733q.setStreamMute(3, true);
                }
                if (MediaController.this.f7726j != null) {
                    MediaController.this.f7726j.setText("");
                    MediaController.this.f7726j.setVisibility(View.VISIBLE);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    long j = (MediaController.this.f7727k * i) / 1000;
                    String m3747a = DateTimeUtils.m3747a(j, ":");
                    if (MediaController.this.f7730n) {
                        MediaController.this.f7717a.mo1690a(j);
                    }
                    if (MediaController.this.f7726j != null) {
                        MediaController.this.f7726j.setText(m3747a);
                    }
                    MediaController.this.f7725i.setText(m3747a + "/" + DateTimeUtils.m3747a(MediaController.this.f7727k, ":"));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!MediaController.this.f7730n) {
                    MediaController.this.f7717a.mo1690a((MediaController.this.f7727k * seekBar.getProgress()) / 1000);
                }
                if (MediaController.this.f7726j != null) {
                    MediaController.this.f7726j.setText("");
                    MediaController.this.f7726j.setVisibility(View.GONE);
                }
                MediaController.this.m1718a(3000, true);
                MediaController.this.f7733q.setStreamMute(3, false);
                MediaController.this.f7729m = false;
                MediaController.this.f7737u.removeMessages(2);
                MediaController.this.f7737u.sendEmptyMessageDelayed(2, 500L);
            }
        };
        if (!this.f7731o && m1717a(context)) {
            m1704f();
        }
    }

    public void setLapseChangedListener(LapseChangedListener lapseChangedListener) {
        this.f7734r = lapseChangedListener;
    }

    /* renamed from: a */
    private boolean m1717a(Context context) {
        this.f7718b = context;
        this.f7733q = (AudioManager) this.f7718b.getSystemService("audio");
        return true;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        if (this.f7722f != null) {
            m1716a(this.f7722f);
        }
    }

    /* renamed from: f */
    private void m1704f() {
        this.f7719c = new PopupWindow(this.f7718b);
        this.f7719c.setFocusable(false);
        this.f7719c.setBackgroundDrawable(null);
        this.f7719c.setOutsideTouchable(true);
        this.f7720d = 16973824;
    }

    public void setAnchorView(View view) {
        this.f7721e = view;
        if (!this.f7731o) {
            removeAllViews();
            this.f7722f = m1719a();
            this.f7719c.setContentView(this.f7722f);
            this.f7719c.setWidth(-1);
            this.f7719c.setHeight(-2);
        }
        m1716a(this.f7722f);
    }

    /* renamed from: a */
    protected View m1719a() {
        return ((LayoutInflater) this.f7718b.getSystemService("layout_inflater")).inflate(R.layout.media_controller, this);
    }

    /* renamed from: a */
    private void m1716a(View view) {
        this.f7732p = (ImageButton) view.findViewById(R.id.mediacontroller_play_pause);
        if (this.f7732p != null) {
            this.f7732p.requestFocus();
            this.f7732p.setOnClickListener(this.f7738v);
        }
        this.f7723g = (ProgressBar) view.findViewById(R.id.mediacontroller_seekbar);
        if (this.f7723g != null) {
            if (this.f7723g instanceof SeekBar) {
                SeekBar seekBar = (SeekBar) this.f7723g;
                seekBar.setOnSeekBarChangeListener(this.f7739w);
                seekBar.setThumbOffset(1);
            }
            this.f7723g.setMax(1000);
        }
        this.f7725i = (TextView) view.findViewById(R.id.mediacontroller_time);
        this.f7724h = (ProgressBar) view.findViewById(R.id.volume_seekbar);
        this.f7724h.setMax(1000);
        this.f7724h.setProgress(getVolumeProgressValue());
        if (this.f7724h instanceof SeekBar) {
            ((SeekBar) this.f7724h).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sds.android.ttpod.widget.MediaController.1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                    MediaController.this.setVolume(i);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                }
            });
        }
        this.f7737u.removeMessages(2);
        this.f7737u.sendEmptyMessageDelayed(2, 500L);
    }

    /* renamed from: b */
    public void m1712b() {
        this.f7737u.removeMessages(3);
        this.f7737u.sendEmptyMessage(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVolume(int i) {
        this.f7733q.setStreamVolume(3, ((this.f7733q.getStreamMaxVolume(3) * i) + 500) / 1000, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getVolumeProgressValue() {
        return (this.f7733q.getStreamVolume(3) * 1000) / this.f7733q.getStreamMaxVolume(3);
    }

    public void setMediaPlayer(InterfaceC2200b interfaceC2200b) {
        this.f7717a = interfaceC2200b;
        m1698i();
    }

    public void setInstantSeeking(boolean z) {
        this.f7730n = z;
    }

    /* renamed from: c */
    public void m1710c() {
        m1718a(3000, true);
    }

    public void setInfoView(OutlineTextView outlineTextView) {
        this.f7726j = outlineTextView;
    }

    /* renamed from: g */
    private void m1702g() {
        try {
            if (this.f7732p != null && this.f7717a != null && !this.f7717a.mo1686f()) {
                this.f7732p.setEnabled(false);
            }
        } catch (IncompatibleClassChangeError e) {
            e.printStackTrace();
        }
    }

    public void setAnimationStyle(int i) {
        this.f7720d = i;
    }

    /* renamed from: a */
    public void m1718a(int i, boolean z) {
        if (!this.f7728l && this.f7721e != null && this.f7721e.getWindowToken() != null) {
            if (this.f7732p != null) {
                this.f7732p.requestFocus();
            }
            m1702g();
            if (this.f7731o) {
                setVisibility(View.VISIBLE);
            } else {
                int[] iArr = new int[2];
                this.f7721e.getLocationOnScreen(iArr);
                Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.f7721e.getWidth(), iArr[1] + this.f7721e.getHeight());
                this.f7719c.setAnimationStyle(this.f7720d);
                this.f7719c.showAtLocation(this.f7721e, 0, rect.left, rect.bottom);
            }
            this.f7728l = true;
            if (this.f7735s != null) {
                this.f7735s.m1684a();
            }
        }
        m1698i();
        if (i != 0) {
            this.f7737u.removeMessages(1);
            this.f7737u.sendMessageDelayed(this.f7737u.obtainMessage(1), i);
        }
        if (z) {
            m1712b();
        }
    }

    /* renamed from: d */
    public boolean m1708d() {
        return this.f7728l;
    }

    /* renamed from: e */
    public void m1706e() {
        if (this.f7721e != null && this.f7728l) {
            try {
                if (this.f7731o) {
                    setVisibility(View.GONE);
                } else {
                    this.f7719c.dismiss();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            this.f7728l = false;
            if (this.f7736t != null) {
                this.f7736t.m1685a();
            }
        }
    }

    public void setOnShownListener(InterfaceC2202d interfaceC2202d) {
        this.f7735s = interfaceC2202d;
    }

    public void setOnHiddenListener(InterfaceC2201c interfaceC2201c) {
        this.f7736t = interfaceC2201c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public long m1700h() {
        if (this.f7717a == null || this.f7729m) {
            return 0L;
        }
        long currentPosition = this.f7717a.getCurrentPosition();
        long duration = this.f7717a.getDuration();
        if (this.f7723g != null) {
            if (duration > 0) {
                this.f7723g.setProgress((int) ((1000 * currentPosition) / duration));
            }
            this.f7723g.setSecondaryProgress(this.f7717a.getBufferPercentage() * 10);
        }
        this.f7727k = duration;
        this.f7725i.setText(DateTimeUtils.m3747a(currentPosition, ":") + "/" + DateTimeUtils.m3747a(this.f7727k, ":"));
        if (this.f7734r != null) {
            this.f7734r.onLapseChanged(null);
            return currentPosition;
        }
        return currentPosition;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        m1718a(3000, true);
        return true;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        m1718a(3000, true);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getRepeatCount() == 0 && (keyCode == 79 || keyCode == 85 || keyCode == 62)) {
            m1696j();
            m1718a(3000, true);
            if (this.f7732p != null) {
                this.f7732p.requestFocus();
                return true;
            }
            return true;
        } else if (keyCode == 86) {
            if (this.f7717a.mo1687e()) {
                this.f7717a.mo1688c();
                m1698i();
                return true;
            }
            return true;
        } else if (keyCode == 4 || keyCode == 82) {
            m1706e();
            return true;
        } else {
            m1718a(3000, true);
            return super.dispatchKeyEvent(keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m1698i() {
        if (this.f7722f != null && this.f7732p != null) {
            this.f7732p.setImageResource(this.f7717a.mo1687e() ? R.drawable.img_media_controller_pause : R.drawable.img_media_controller_play);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m1696j() {
        if (this.f7717a.mo1687e()) {
            this.f7717a.mo1688c();
        } else {
            this.f7717a.mo1689b();
        }
        m1698i();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (this.f7732p != null) {
            this.f7732p.setEnabled(z);
        }
        if (this.f7723g != null) {
            this.f7723g.setEnabled(z);
        }
        m1702g();
        super.setEnabled(z);
    }
}
