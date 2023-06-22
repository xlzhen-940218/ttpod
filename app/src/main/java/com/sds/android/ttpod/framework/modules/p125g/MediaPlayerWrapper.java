package com.sds.android.ttpod.framework.modules.p125g;

import android.media.MediaPlayer;

/* renamed from: com.sds.android.ttpod.framework.modules.g.a */
/* loaded from: classes.dex */
final class MediaPlayerWrapper {

    /* renamed from: a */
    private static MediaPlayerWrapper f6275a;

    /* renamed from: b */
    private MediaPlayer f6276b;

    /* renamed from: c */
    private InterfaceC1953a f6277c;

    /* renamed from: d */
    private MediaPlayer.OnCompletionListener f6278d = new MediaPlayer.OnCompletionListener() { // from class: com.sds.android.ttpod.framework.modules.g.a.1
        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            MediaPlayerWrapper.this.m4017b();
        }
    };

    /* renamed from: e */
    private MediaPlayer.OnErrorListener f6279e = new MediaPlayer.OnErrorListener() { // from class: com.sds.android.ttpod.framework.modules.g.a.2
        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            MediaPlayerWrapper.this.m4017b();
            return true;
        }
    };

    /* renamed from: f */
    private MediaPlayer.OnPreparedListener f6280f = new MediaPlayer.OnPreparedListener() { // from class: com.sds.android.ttpod.framework.modules.g.a.3
        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MediaPlayerWrapper.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.g.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1953a {
        /* renamed from: a */
        void mo3988a();
    }

    private MediaPlayerWrapper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized MediaPlayerWrapper m4021a() {
        MediaPlayerWrapper mediaPlayerWrapper;
        synchronized (MediaPlayerWrapper.class) {
            if (f6275a == null) {
                f6275a = new MediaPlayerWrapper();
            }
            mediaPlayerWrapper = f6275a;
        }
        return mediaPlayerWrapper;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m4018a(String str) {
        if (this.f6276b == null) {
            this.f6276b = new MediaPlayer();
        }
        try {
            this.f6276b.setDataSource(str);
            this.f6276b.prepareAsync();
            this.f6276b.setOnErrorListener(this.f6279e);
            this.f6276b.setOnCompletionListener(this.f6278d);
            this.f6276b.setOnPreparedListener(this.f6280f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void m4017b() {
        if (this.f6276b != null) {
            this.f6276b.stop();
            this.f6276b.release();
            this.f6276b = null;
            this.f6277c.mo3988a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m4020a(float f, float f2) {
        if (this.f6276b != null) {
            this.f6276b.setVolume(0.0f, 0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m4016c() {
        return this.f6276b != null && this.f6276b.isPlaying();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m4019a(InterfaceC1953a interfaceC1953a) {
        this.f6277c = interfaceC1953a;
    }
}
