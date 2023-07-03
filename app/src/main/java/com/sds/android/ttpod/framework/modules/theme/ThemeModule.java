package com.sds.android.ttpod.framework.modules.theme;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.sds.android.cloudapi.ttpod.api.SkinAPI;
import com.sds.android.cloudapi.ttpod.result.BackgroundMoreCheckResult;
import com.sds.android.sdk.lib.p065e.ThreadPool;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.ObserverCommandID;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.skin.BackgroundListLoader;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinThumbnailCreator;
import com.sds.android.ttpod.framework.p106a.BackgroundCreateUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

@ObserverCommandID(m4563a = {CommandID.LOAD_SKIN_FINISHED})
/* renamed from: com.sds.android.ttpod.framework.modules.theme.d */
/* loaded from: classes.dex */
public final class ThemeModule extends BaseModule {

    /* renamed from: b */
    private int f6963b;

    /* renamed from: c */
    private int f6964c;

    /* renamed from: a */
    private boolean loadedTheme = false;

    /* renamed from: d */
    private ThreadPool f6965d = new ThreadPool("themeWorkThreadPool", 1, 0);

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.THEME;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        this.f6963b = SkinThumbnailCreator.f6693a;
        this.f6964c = SkinThumbnailCreator.f6694c;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.LOAD_BACKGROUND_LIST, ReflectUtils.loadMethod(cls, "loadBackgroundList", Boolean.class));
        map.put(CommandID.SET_BACKGROUND, ReflectUtils.loadMethod(cls, "setBackground", String.class));
        map.put(CommandID.DELETE_BACKGROUND, ReflectUtils.loadMethod(cls, "deleteBackground", String.class));
        map.put(CommandID.LOAD_BACKGROUND, ReflectUtils.loadMethod(cls, "loadBackground", new Class[0]));
        map.put(CommandID.GET_BACKGROUND, ReflectUtils.loadMethod(cls, "getBackground", new Class[0]));
        map.put(CommandID.LOAD_THEME_LIST, ReflectUtils.loadMethod(cls, "loadThemeList", new Class[0]));
        map.put(CommandID.LOAD_SKIN_FINISHED, ReflectUtils.loadMethod(cls, "loadSkinFinished", SkinCache.class));
        map.put(CommandID.DECODE_BACKGROUND_THUMBNAIL, ReflectUtils.loadMethod(cls, "decodeBackgroundThumbnail", BackgroundItem.class));
        map.put(CommandID.REQUEST_BACKGROUND_MORE, ReflectUtils.loadMethod(cls, "requestBackgroundMore", new Class[0]));
    }

    public void loadThemeList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("cool_white");
        arrayList.add("transparent_glass");
        CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_THEME_LIST, arrayList), ModuleID.THEME);
    }

    public void loadSkinFinished(SkinCache skinCache) {
        if (Preferences.m3032Y()) {
            BackgroundItem backgroundItem = new BackgroundItem(Preferences.m3034X());
            backgroundItem.m3335a(skinCache.getBackground(sContext));
            Cache.getInstance().m3212a(backgroundItem);
            CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_BACKGROUND, backgroundItem.m3328e()), ModuleID.THEME);
        }
    }

    public Drawable getBackground() {
        Drawable m3241b = m3241b();
        if (m3241b == null) {
            loadBackground();
            return null;
        }
        return m3241b;
    }

    public void setBackground(String str) {
        Preferences.m2872i(str);
        loadBackground();
    }

    public void loadBackground() {
        if (!this.loadedTheme) {
            this.f6965d.execute(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.theme.d.1
                /* JADX WARN: Removed duplicated region for block: B:21:0x0066 A[Catch: Throwable -> 0x0028, TRY_LEAVE, TryCatch #2 {Throwable -> 0x0028, blocks: (B:3:0x0011, B:4:0x001d, B:5:0x0020, B:6:0x0027, B:12:0x004d, B:19:0x0060, B:21:0x0066, B:25:0x007b), top: B:33:0x0011 }] */
                /* JADX WARN: Removed duplicated region for block: B:25:0x007b A[Catch: Throwable -> 0x0028, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Throwable -> 0x0028, blocks: (B:3:0x0011, B:4:0x001d, B:5:0x0020, B:6:0x0027, B:12:0x004d, B:19:0x0060, B:21:0x0066, B:25:0x007b), top: B:33:0x0011 }] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void run() {
                    BackgroundItem backgroundItem = null;
                    Throwable th;
                    ThemeModule.this.loadedTheme = true;
                    BackgroundItem backgroundItem2 = new BackgroundItem(Preferences.m3034X());
                    Bitmap bitmap = null;
                    try {
                    } catch (Throwable th2) {
                        backgroundItem = backgroundItem2;
                        th = th2;
                    }
                    switch (C20234.f6970a[backgroundItem2.getResourceTypeEnum().ordinal()]) {
                        case 1:
                            backgroundItem = ThemeModule.this.m3246a(backgroundItem2);
                            if (backgroundItem == null) {
                                return;
                            }
                            try {
                                Cache.getInstance().m3212a(backgroundItem);
                            } catch (Throwable th3) {
                                th = th3;
                                th.printStackTrace();
                                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_BACKGROUND, backgroundItem.m3328e()), ModuleID.THEME);
                                ThemeModule.this.loadedTheme = false;
                                return;
                            }
                            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_BACKGROUND, backgroundItem.m3328e()), ModuleID.THEME);
                            ThemeModule.this.loadedTheme = false;
                            return;
                        case 2:
                            bitmap = BackgroundCreateUtils.m5279a(backgroundItem2, 1);
                            if (bitmap != null) {
                                Preferences.m2872i("follow_skin");
                                BackgroundItem backgroundItem3 = new BackgroundItem("follow_skin");
                                try {
                                    backgroundItem = ThemeModule.this.m3246a(backgroundItem3);
                                    if (backgroundItem == null) {
                                        return;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    backgroundItem = backgroundItem3;
                                    th.printStackTrace();
                                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_BACKGROUND, backgroundItem.m3328e()), ModuleID.THEME);
                                    ThemeModule.this.loadedTheme = false;
                                    return;
                                }
                            } else {
                                backgroundItem2.m3335a(new BitmapDrawable(bitmap));
                                backgroundItem = backgroundItem2;
                            }
                            Cache.getInstance().m3212a(backgroundItem);
                            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_BACKGROUND, backgroundItem.m3328e()), ModuleID.THEME);
                            ThemeModule.this.loadedTheme = false;
                            return;
                        case 3:
                            if (bitmap != null) {
                            }
                            Cache.getInstance().m3212a(backgroundItem);
                            CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_BACKGROUND, backgroundItem.m3328e()), ModuleID.THEME);
                            ThemeModule.this.loadedTheme = false;
                            return;
                        default:
                            throw new IllegalArgumentException("the background type not supported!");
                    }
                }
            });
        }
    }

    /* compiled from: ThemeModule.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.theme.d$4 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C20234 {

        /* renamed from: a */
        static final /* synthetic */ int[] f6970a = new int[BackgroundItem.ResourceTypeEnum.values().length];

        static {
            try {
                f6970a[BackgroundItem.ResourceTypeEnum.FOLLOW_SKIN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f6970a[BackgroundItem.ResourceTypeEnum.ADD_BY_USER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f6970a[BackgroundItem.ResourceTypeEnum.ORIGINAL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public BackgroundItem m3246a(BackgroundItem backgroundItem) {
        SkinCache skinCache = Cache.getInstance().getSkinCache();
        if (skinCache == null || skinCache.getBackground(sContext) == null) {
            CommandCenter.getInstance().postInvokeResult(new Command(CommandID.LOAD_SKIN, new Object[0]));
            this.loadedTheme = false;
            return null;
        }
        backgroundItem.m3335a(skinCache.getBackground(sContext));
        return backgroundItem;
    }

    public void loadBackgroundList(Boolean bool) {
        this.f6965d.execute((Runnable) new BackgroundListLoader(bool));
    }

    public void deleteBackground(String str) {
        BackgroundItem backgroundItem = new BackgroundItem(str);
        BackgroundCreateUtils.m5277b(backgroundItem);
        ImageCacheUtils.m4739b(backgroundItem.toString(), this.f6963b, this.f6964c);
    }

    public void decodeBackgroundThumbnail(final BackgroundItem backgroundItem) {
        this.f6965d.execute(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.theme.d.2
            @Override // java.lang.Runnable
            public void run() {
                Bitmap m4748a;
                if (BackgroundItem.ResourceTypeEnum.FOLLOW_SKIN == backgroundItem.getResourceTypeEnum() && Cache.getInstance().getSkinCache() != null) {
                    m4748a = ThemeModule.this.m3247a(Cache.getInstance().getSkinCache().getBackground(ThemeModule.sContext));
                } else {
                    String backgroundItem2 = backgroundItem.toString();
                    m4748a = ImageCacheUtils.m4748a(backgroundItem2, ThemeModule.this.f6963b, ThemeModule.this.f6964c);
                    if (m4748a == null) {
                        m4748a = BackgroundCreateUtils.m5280a(backgroundItem);
                        ImageCacheUtils.m4747a(backgroundItem2, ThemeModule.this.f6963b, ThemeModule.this.f6964c, m4748a);
                    }
                }
                backgroundItem.m3336a(m4748a);
                CommandCenter.getInstance().m4595b(new Command(CommandID.BACKGROUND_THUMBNAIL_CREATED, backgroundItem), ModuleID.THEME);
            }
        });
    }

    /* renamed from: b */
    private Drawable m3241b() {
        Bitmap bitmap;
        BackgroundItem m3150n = Cache.getInstance().m3150n();
        if (m3150n == null || !Preferences.m3034X().equals(m3150n.toString())) {
            return null;
        }
        Drawable m3328e = m3150n.m3328e();
        Drawable drawable = (m3328e != null && (m3328e instanceof BitmapDrawable) && ((bitmap = ((BitmapDrawable) m3328e).getBitmap()) == null || bitmap.isRecycled())) ? null : m3328e;
        if (drawable == null) {
            return null;
        }
        return drawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Bitmap m3247a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int m7225c = DisplayUtils.getWidth() / 3;
        ColorDrawable colorDrawable = (ColorDrawable) drawable;
        if (drawable == null) {
            ColorDrawable colorDrawable2 = new ColorDrawable(0xff000000);
            colorDrawable2.setBounds(0, 0, m7225c, m7225c);
            colorDrawable = colorDrawable2;
        }
        int intrinsicWidth = colorDrawable.getIntrinsicWidth();
        int intrinsicHeight = colorDrawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = m7225c;
        }
        if (intrinsicHeight <= 0) {
            intrinsicHeight = m7225c;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        colorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        colorDrawable.draw(canvas);
        return createBitmap;
    }

    public void requestBackgroundMore() {
        this.f6965d.execute(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.theme.d.3
            @Override // java.lang.Runnable
            public void run() {
                BackgroundMoreCheckResult m8531f = SkinAPI.m8825d().execute();
                if (m8531f.getCode() == 1) {
                    Preferences.m2961aj(1 == m8531f.getData().getStatus());
                    Preferences.m2896d(m8531f.getCreateTime());
                }
            }
        });
    }
}
