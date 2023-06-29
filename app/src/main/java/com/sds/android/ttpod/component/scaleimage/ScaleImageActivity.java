package com.sds.android.ttpod.component.scaleimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.collection.SparseArrayCompat;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sds.android.sdk.core.p057a.ImageCache;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ScaleImageActivity extends BaseActivity implements View.OnClickListener {
    public static final String KEY_PIC_URL = "pic_url";
    public static final String KEY_START_INDEX = "start_index";
    private static final String LOG_TAG = "ScaleImageActivity";
    private static final float MIN_SPACE = 0.1f;
    private static final int PAGER_MARGIN_DP = 40;
    private static final int PIC_QUALITY = 80;
    private static final int REQUIRED_BITMAP_SIZE = 400;
    private static final int WIDE_BITMAP_SHOW = 50;
    private ImageButton mDownloadButton;
    private GestureDetector mGestureDetector;
    private TextView mPageShow;
    private C1324a mPagerAdapter;
    private boolean mPaused;
    private ArrayList<String> mPicList;
    private ImageScaleGestureDetector mScaleGestureDetector;
    private int mStartIndex;
    private ImageViewPager mViewPager;
    private C1327c myOnScaleGestureListener;
    private boolean mOnScale = false;
    private boolean mOnPagerScoll = false;
    private ImageViewPager.InterfaceC1318c mPageChangeListener = new ImageViewPager.InterfaceC1318c() { // from class: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity.2
        @Override // com.sds.android.ttpod.component.scaleimage.ImageViewPager.InterfaceC1318c
        /* renamed from: a */
        public void mo5862a(int i, int i2) {
            ImageViewTouch imageViewTouch = (ImageViewTouch) ScaleImageActivity.this.mPagerAdapter.f4817b.get(i2);
            if (imageViewTouch != null) {
                imageViewTouch.m5880a(imageViewTouch.f4798d.m5834b(), true);
            }
            ScaleImageActivity.this.mStartIndex = i;
            ScaleImageActivity.this.updateShowInfo();
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImageViewPager.InterfaceC1318c
        /* renamed from: a */
        public void mo5863a(int i, float f, int i2) {
            ScaleImageActivity.this.mOnPagerScoll = true;
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImageViewPager.InterfaceC1318c
        /* renamed from: a */
        public void mo5864a(int i) {
            if (i == 1) {
                ScaleImageActivity.this.mOnPagerScoll = true;
            } else if (i == 2) {
                ScaleImageActivity.this.mOnPagerScoll = false;
            } else {
                ScaleImageActivity.this.mOnPagerScoll = false;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_show_images);
        this.mViewPager = (ImageViewPager) findViewById(R.id.viewPager);
        this.mPageShow = (TextView) findViewById(R.id.tv_page);
        this.mDownloadButton = (ImageButton) findViewById(R.id.btn_download);
        this.mDownloadButton.setOnClickListener(this);
        Intent intent = getIntent();
        this.mPicList = intent.getStringArrayListExtra(KEY_PIC_URL);
        this.mStartIndex = intent.getIntExtra(KEY_START_INDEX, 0);
        this.mPageShow.setText("1/" + this.mPicList.size());
        this.mViewPager.setPageMargin(Math.round(getResources().getDisplayMetrics().density * 40.0f));
        this.mViewPager.setPageMarginDrawable(new ColorDrawable(0xff000000));
        this.mPagerAdapter = new C1324a();
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this.mPageChangeListener);
        this.mViewPager.setCurrentItem(this.mStartIndex);
        setupOnTouchListeners(this.mViewPager);
        this.mViewPager.m5913a(this.mStartIndex, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShowInfo() {
        if (this.mPicList.size() > 0) {
            this.mPageShow.setText(String.format("%d/%d", Integer.valueOf(this.mStartIndex + 1), Integer.valueOf(this.mPicList.size())));
        }
    }

    private Bitmap decodeFile(File file) {
        int i = 1;
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            while (i2 / 2 >= 400 && i3 / 2 >= 400) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, options2);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.mPaused = false;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.mPaused = true;
    }

    private void setupOnTouchListeners(View view) {
        if (SDKVersionUtils.sdkThan12()) {
            this.myOnScaleGestureListener = new C1327c();
            this.mScaleGestureDetector = new ImageScaleGestureDetector(this, this.myOnScaleGestureListener);
        }
        this.mGestureDetector = new GestureDetector(this, new C1326b());
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                Bitmap m5834b;
                if (!ScaleImageActivity.this.mOnScale && !ScaleImageActivity.this.mOnPagerScoll) {
                    ScaleImageActivity.this.mGestureDetector.onTouchEvent(motionEvent);
                }
                if (SDKVersionUtils.sdkThan12() && (!ScaleImageActivity.this.mOnPagerScoll || ScaleImageActivity.this.myOnScaleGestureListener.f4825e)) {
                    ScaleImageActivity.this.mScaleGestureDetector.m5846a(motionEvent);
                }
                ImageViewTouch currentImageView = ScaleImageActivity.this.getCurrentImageView();
                if (currentImageView != null && !ScaleImageActivity.this.mOnScale) {
                    Matrix m5866e = currentImageView.m5866e();
                    RotateBitmap rotateBitmap = currentImageView.f4798d;
                    if (rotateBitmap != null && (m5834b = rotateBitmap.m5834b()) != null) {
                        RectF rectF = new RectF(0.0f, 0.0f, m5834b.getWidth(), m5834b.getHeight());
                        m5866e.mapRect(rectF);
                        if (rectF.right <= currentImageView.getWidth() + ScaleImageActivity.MIN_SPACE || rectF.left >= -0.1f) {
                            try {
                                ScaleImageActivity.this.mViewPager.onTouchEvent(motionEvent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mPaused) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ImageViewTouch currentImageView = getCurrentImageView();
        if (currentImageView != null) {
            currentImageView.m5870c();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImageViewTouch getCurrentImageView() {
        return (ImageViewTouch) this.mPagerAdapter.f4817b.get(this.mViewPager.getCurrentItem());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_download) {
            String m5312A = TTPodConfig.getImagePath();
            if (!FileUtils.isDir(m5312A)) {
                FileUtils.createFolder(m5312A);
            }
            final String str = TTPodConfig.getImagePath() + File.separator + FileUtils.m8401k(this.mPicList.get(this.mStartIndex)) + ".jpg";
            if (new File(str).exists()) {
                PopupsUtils.m6721a("图片已存在");
                return;
            }
            ImageViewTouch m5861a = this.mPagerAdapter.m5861a(this.mStartIndex);
            ImageCacheUtils.m4743b().m8809a(this.mPicList.get(this.mStartIndex), m5861a.getWidth(), m5861a.getHeight(), new ImageCache.InterfaceC0565a() { // from class: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity.3
                @Override // com.sds.android.sdk.core.p057a.ImageCache.InterfaceC0565a
                /* renamed from: a */
                public void mo4733a(String str2, int i, int i2, final Bitmap bitmap) {
                    if (bitmap == null) {
                        PopupsUtils.m6721a("请等待图片下载完成后再保存!");
                        return;
                    }
                    PopupsUtils.m6721a("图片保存在:" + str);
                    TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity.3.1
                        /* JADX WARN: Removed duplicated region for block: B:28:0x0036 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                        @Override // java.lang.Runnable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void run() {
                            BufferedOutputStream bufferedOutputStream = null;
                            BufferedOutputStream bufferedOutputStream2 = null;
                            Throwable th;
                            Exception e;
                            try {
                                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
                            } catch (FileNotFoundException e1) {
                                e = e1;
                                bufferedOutputStream = null;
                            } catch (Throwable th1) {
                                th = th1;
                                if (bufferedOutputStream2 != null) {
                                }
                                //throw th;
                            }
                            try {
                                bitmap.compress(Bitmap.CompressFormat.JPEG, ScaleImageActivity.PIC_QUALITY, bufferedOutputStream);
                                if (bufferedOutputStream != null) {
                                    try {
                                        bufferedOutputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedOutputStream2 = bufferedOutputStream;
                                if (bufferedOutputStream2 != null) {
                                    try {
                                        bufferedOutputStream2.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                //throw th;
                            }
                        }
                    });
                    Uri fromFile = Uri.fromFile(new File(str));
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent.setData(fromFile);
                    ScaleImageActivity.this.sendBroadcast(intent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity$b */
    /* loaded from: classes.dex */
    public class C1326b extends GestureDetector.SimpleOnGestureListener {
        private C1326b() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (ScaleImageActivity.this.mOnScale) {
                return true;
            }
            if (!ScaleImageActivity.this.mPaused) {
                ImageViewTouch currentImageView = ScaleImageActivity.this.getCurrentImageView();
                if (currentImageView != null) {
                    currentImageView.m5872b(-f, -f2);
                    currentImageView.m5875a(true, true);
                    currentImageView.m5875a(true, true);
                    return true;
                }
                return true;
            }
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            ScaleImageActivity.this.finish();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (!ScaleImageActivity.this.mPaused) {
                ImageViewTouch currentImageView = ScaleImageActivity.this.getCurrentImageView();
                if (currentImageView != null) {
                    if (currentImageView.m5868d() > (currentImageView.m5873b() + currentImageView.m5886a()) / 2.0f) {
                        currentImageView.m5885a(currentImageView.m5873b());
                        return true;
                    }
                    currentImageView.m5871b(currentImageView.m5886a(), motionEvent.getX(), motionEvent.getY());
                    return true;
                }
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity$c */
    /* loaded from: classes.dex */
    public class C1327c extends ImageScaleGestureDetector.C1331b {

        /* renamed from: b */
        private float f4822b;

        /* renamed from: c */
        private float f4823c;

        /* renamed from: d */
        private float f4824d;

        /* renamed from: e */
        private boolean f4825e;

        private C1327c() {
            this.f4825e = false;
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.C1331b, com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.InterfaceC1330a
        /* renamed from: b */
        public void mo5838b(ImageScaleGestureDetector imageScaleGestureDetector) {
            ImageViewTouch currentImageView = ScaleImageActivity.this.getCurrentImageView();
            if (currentImageView != null) {
                this.f4825e = false;
                if (this.f4822b > currentImageView.m5886a()) {
                    currentImageView.m5882a(this.f4822b / currentImageView.m5886a(), 1.0f, this.f4823c, this.f4824d);
                    this.f4822b = currentImageView.m5886a();
                    currentImageView.m5867d(this.f4822b, this.f4823c, this.f4824d);
                } else if (this.f4822b < currentImageView.m5873b()) {
                    currentImageView.m5882a(this.f4822b, currentImageView.m5873b(), this.f4823c, this.f4824d);
                    this.f4822b = currentImageView.m5873b();
                    currentImageView.m5867d(this.f4822b, this.f4823c, this.f4824d);
                } else {
                    currentImageView.m5869c(this.f4822b, this.f4823c, this.f4824d);
                }
                currentImageView.m5875a(true, true);
                currentImageView.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity.c.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ScaleImageActivity.this.mOnScale = false;
                    }
                }, 300L);
            }
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.C1331b, com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.InterfaceC1330a
        /* renamed from: a */
        public boolean mo5840a(ImageScaleGestureDetector imageScaleGestureDetector) {
            ScaleImageActivity.this.mOnScale = true;
            return true;
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.C1331b, com.sds.android.ttpod.component.scaleimage.ImageScaleGestureDetector.InterfaceC1330a
        /* renamed from: a */
        public boolean mo5839a(ImageScaleGestureDetector imageScaleGestureDetector, float f, float f2) {
            ImageViewTouch currentImageView = ScaleImageActivity.this.getCurrentImageView();
            if (currentImageView != null) {
                this.f4825e = true;
                float m5868d = currentImageView.m5868d() * imageScaleGestureDetector.m5844b();
                this.f4822b = m5868d;
                this.f4823c = f;
                this.f4824d = f2;
                if (imageScaleGestureDetector.m5848a()) {
                    currentImageView.m5869c(m5868d, f, f2);
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity$a */
    /* loaded from: classes.dex */
    public class C1324a extends ImagePagerAdapter {

        /* renamed from: b */
        private SparseArrayCompat<ImageViewTouch> f4817b;

        private C1324a() {
            this.f4817b = new SparseArrayCompat<>();
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: a */
        public int mo5858a() {
            return ScaleImageActivity.this.mPicList.size();
        }

        /* renamed from: a */
        public ImageViewTouch m5861a(int i) {
            return this.f4817b.get(i);
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: a */
        public Object mo5855a(View view, int i) {
            final ImageViewTouch imageViewTouch = new ImageViewTouch(ScaleImageActivity.this);
            imageViewTouch.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageViewTouch.setBackgroundColor(0xff000000);
            imageViewTouch.setFocusableInTouchMode(true);
            String str = (String) ScaleImageActivity.this.mPicList.get(i);
            imageViewTouch.setTag(str);
            ImageCacheUtils.m4743b().m8809a(str, imageViewTouch.getWidth(), imageViewTouch.getHeight(), new ImageCache.InterfaceC0565a() { // from class: com.sds.android.ttpod.component.scaleimage.ScaleImageActivity.a.1
                @Override // com.sds.android.sdk.core.p057a.ImageCache.InterfaceC0565a
                /* renamed from: a */
                public void mo4733a(String str2, int i2, int i3, Bitmap bitmap) {
                    if (imageViewTouch.getTag().equals(str2)) {
                        imageViewTouch.m5880a(bitmap, true);
                    }
                }
            });
            ((ImageViewPager) view).addView(imageViewTouch);
            this.f4817b.put(i, imageViewTouch);
            return imageViewTouch;
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: a */
        public void mo5854a(View view, int i, Object obj) {
            ImageViewTouch imageViewTouch = (ImageViewTouch) obj;
            imageViewTouch.m5870c();
            ((ImageViewPager) view).removeView(imageViewTouch);
            this.f4817b.remove(i);
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: a */
        public void mo5856a(View view) {
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: b */
        public void mo5850b(View view) {
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: a */
        public boolean mo5853a(View view, Object obj) {
            return view == ((ImageViewTouch) obj);
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: b */
        public Parcelable mo5851b() {
            return null;
        }

        @Override // com.sds.android.ttpod.component.scaleimage.ImagePagerAdapter
        /* renamed from: a */
        public void mo5857a(Parcelable parcelable, ClassLoader classLoader) {
        }
    }
}
