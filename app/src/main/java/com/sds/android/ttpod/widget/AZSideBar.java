package com.sds.android.ttpod.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.p130c.ViewWrapper;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class AZSideBar extends View implements AbsListView.OnScrollListener, ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private TextView floatLetterTextView;

    /* renamed from: b */
    private List<String> namePinyinList;

    /* renamed from: c */
    private OnMatchedPositionChangeListener onMatchedPositionChangeListener;

    /* renamed from: d */
    private List<String> AtoZList;

    /* renamed from: e */
    private int selectIndex;

    /* renamed from: f */
    private Paint textPaint;

    /* renamed from: g */
    private boolean downAction;

    /* renamed from: h */
    private int normalBkgColor;

    /* renamed from: i */
    private int pressedBkgColor;

    /* renamed from: j */
    private int normalTextColor;

    /* renamed from: k */
    private int pressedTextColor;

    /* renamed from: l */
    private String themePanelType;

    /* renamed from: m */
    private int f7468m;

    /* renamed from: n */
    private boolean f7469n;

    /* renamed from: o */
    private Handler handler;

    /* renamed from: com.sds.android.ttpod.widget.AZSideBar$a */
    /* loaded from: classes.dex */
    public interface OnMatchedPositionChangeListener {
        /* renamed from: a */
        void positionChanged(int position, String str);
    }

    public AZSideBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.namePinyinList = new ArrayList();
        this.AtoZList = new ArrayList<String>() { // from class: com.sds.android.ttpod.widget.AZSideBar.1
            {
                add("#");
                for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
                    add(c + "");
                }
            }
        };
        this.selectIndex = -1;
        this.textPaint = new Paint();
        this.downAction = false;
        this.themePanelType = ThemeElement.PANEL_SONG_LIST_ITEM;
        this.f7468m = -1;
        this.f7469n = false;
        this.handler = new Handler() { // from class: com.sds.android.ttpod.widget.AZSideBar.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        AZSideBar.this.removeFloatLetterView();
                        AZSideBar.this.invalidate();
                        return;
                    default:
                        return;
                }
            }
        };
        m1919a(context);
        m1918a(context, attributeSet, i);
    }

    public AZSideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.namePinyinList = new ArrayList();
        this.AtoZList = new ArrayList<String>() { // from class: com.sds.android.ttpod.widget.AZSideBar.1
            {
                add("#");
                for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
                    add(c + "");
                }
            }
        };
        this.selectIndex = -1;
        this.textPaint = new Paint();
        this.downAction = false;
        this.themePanelType = ThemeElement.PANEL_SONG_LIST_ITEM;
        this.f7468m = -1;
        this.f7469n = false;
        this.handler = new Handler() { // from class: com.sds.android.ttpod.widget.AZSideBar.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        AZSideBar.this.removeFloatLetterView();
                        AZSideBar.this.invalidate();
                        return;
                    default:
                        return;
                }
            }
        };
        m1919a(context);
        m1918a(context, attributeSet, 0);
    }

    public AZSideBar(Context context) {
        super(context);
        this.namePinyinList = new ArrayList();
        this.AtoZList = new ArrayList<String>() { // from class: com.sds.android.ttpod.widget.AZSideBar.1
            {
                add("#");
                for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
                    add(c + "");
                }
            }
        };
        this.selectIndex = -1;
        this.textPaint = new Paint();
        this.downAction = false;
        this.themePanelType = ThemeElement.PANEL_SONG_LIST_ITEM;
        this.f7468m = -1;
        this.f7469n = false;
        this.handler = new Handler() { // from class: com.sds.android.ttpod.widget.AZSideBar.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        AZSideBar.this.removeFloatLetterView();
                        AZSideBar.this.invalidate();
                        return;
                    default:
                        return;
                }
            }
        };
        m1919a(context);
    }

    public void setIndexWords(String[] strArr) {
        this.AtoZList.clear();
        this.AtoZList.addAll(Arrays.asList(strArr));
    }

    /* renamed from: a */
    public void m1914a(String str, int i) {
        this.AtoZList.add(i, str);
        m1920a(this.f7468m == -1 ? 0 : this.f7468m);
    }

    /* renamed from: a */
    private void m1919a(Context context) {
        this.textPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12.0f, context.getResources().getDisplayMetrics()));
        this.textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.textPaint.setAntiAlias(true);
    }

    public void setThemePanelType(String themePanelType) {
        this.themePanelType = themePanelType;
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        String str = ThemeElement.SONG_LIST_ITEM_AZBAR_TEXT;
        String str2 = ThemeElement.SONG_LIST_ITEM_AZBAR_AREA;
        String str3 = ThemeElement.SONG_LIST_ITEM_AZBAR;
        String str4 = ThemeElement.SUB_BAR_TEXT;
        String str5 = ThemeElement.SONG_LIST_ITEM_BACKGROUND;
        if (ThemeElement.PANEL_PLAYER_MUSIC_LIST.equals(this.themePanelType)) {
            str = ThemeElement.PLAYER_MUSIC_LIST_AZBAR_TEXT;
            str2 = ThemeElement.PLAYER_MUSIC_LIST_AZBAR;
            str3 = ThemeElement.PLAYER_MUSIC_LIST_AZBAR_BACKGROUND;
        }
        this.normalBkgColor = m1910a(str2, true, this.normalBkgColor);
        this.pressedBkgColor = m1910a(str2, false, this.pressedBkgColor);
        this.normalTextColor = m1912a(str, str4, true, this.normalTextColor);
        this.pressedTextColor = m1912a(str, str4, false, this.pressedTextColor);
        ThemeUtils.m8178a(this, str3, str5);
        invalidate();
    }

    /* renamed from: a */
    private int m1910a(String str, boolean z, int i) {
        int m1911a = m1911a(str, z);
        return 1 == m1911a ? i : m1911a;
    }

    /* renamed from: a */
    private int m1912a(String str, String str2, boolean z, int i) {
        int m1906b = m1906b(str, z);
        if (1 == m1906b) {
            m1906b = m1906b(str2, z);
        }
        return 1 == m1906b ? i : m1906b;
    }

    /* renamed from: a */
    private int m1911a(String str, boolean z) {
        Drawable m3265a = ThemeManager.m3265a(str);
        if (m3265a instanceof ColorDrawable) {
            return ThemeManager.m3272a((ColorDrawable) m3265a);
        }
        if (m3265a instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) m3265a;
            try {
                Method m8375a = ReflectUtils.loadMethod(stateListDrawable.getClass(), "getStateDrawableIndex", int[].class);
                Object[] objArr = new Object[1];
                objArr[0] = z ? StateSet.WILD_CARD : new int[]{16842919};
                Object invoke = ReflectUtils.loadMethod(stateListDrawable.getClass(), "getStateDrawable", Integer.TYPE).invoke(stateListDrawable, Integer.valueOf(((Number) m8375a.invoke(stateListDrawable, objArr)).intValue()));
                if (invoke instanceof ColorDrawable) {
                    return ThemeManager.m3272a((ColorDrawable) invoke);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    /* renamed from: b */
    private int m1906b(String str, boolean z) {
        Drawable m3265a = ThemeManager.m3265a(str);
        if (m3265a instanceof ColorDrawable) {
            return ThemeManager.m3272a((ColorDrawable) m3265a);
        }
        ColorStateList colorStateList = ThemeManager.m3254c(str);
        if (colorStateList != null) {
            if (z) {
                return colorStateList.getDefaultColor();
            }
            return colorStateList.getColorForState(ViewWrapper.SELECTED_STATE_SET, 1);
        }
        return 1;
    }

    /* renamed from: a */
    private void m1918a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AZSideBar, i, 0);
        if (obtainStyledAttributes != null) {
            for (int indexCount = obtainStyledAttributes.getIndexCount() - 1; indexCount >= 0; indexCount--) {
                int index = obtainStyledAttributes.getIndex(indexCount);
                if (index == 0) {
                    this.normalBkgColor = obtainStyledAttributes.getColor(R.styleable.AZSideBar_normalBkgColor, this.normalBkgColor);
                } else if (index == 1) {
                    this.pressedBkgColor = obtainStyledAttributes.getColor(R.styleable.AZSideBar_pressedBkgColor, this.pressedBkgColor);
                } else if (index == 2) {
                    this.normalTextColor = obtainStyledAttributes.getColor(R.styleable.AZSideBar_normalTextColor, this.normalTextColor);
                } else if (index == 3) {
                    this.pressedTextColor = obtainStyledAttributes.getColor(R.styleable.AZSideBar_pressedTextColor, this.pressedTextColor);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int paddingTop = getPaddingTop();
        m1917a(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int width = getWidth() - (paddingRight + paddingLeft);
        float size = height / this.AtoZList.size();
        Paint.FontMetrics fontMetrics = this.textPaint.getFontMetrics();
        float f = paddingTop + (width >> 1) + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom);
        float f2 = paddingLeft + (width >> 1);
        int i = 0;
        while (i < this.AtoZList.size()) {
            this.textPaint.setColor(i == this.selectIndex ? this.pressedTextColor : this.normalTextColor);
            this.textPaint.setFakeBoldText(i == this.selectIndex);
            canvas.drawText(this.AtoZList.get(i), f2, (i * size) + f, this.textPaint);
            i++;
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.selectIndex;
        int width = getWidth() - (getPaddingLeft() + getPaddingRight());
        int height = (int) (((y - (width >> 1)) / (getHeight() - width)) * this.AtoZList.size());
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                this.downAction = true;
                this.handler.removeMessages(0);
                if (i != height && height >= 0 && height < this.AtoZList.size()) {
                    m1913a(this.AtoZList.get(height), m1915a(this.AtoZList.get(height)), true);
                    this.selectIndex = height;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                this.downAction = false;
                this.handler.sendEmptyMessageDelayed(0, 500L);
                break;
            case MotionEvent.ACTION_MOVE:
                if (i != height && height >= 0 && height < this.AtoZList.size()) {
                    m1913a(this.AtoZList.get(height), m1915a(this.AtoZList.get(height)), true);
                    this.selectIndex = height;
                    invalidate();
                    break;
                }
                break;
        }
        return true;
    }

    /* renamed from: a */
    private void m1917a(Canvas canvas) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        Paint paint = new Paint();
        paint.setColor(this.downAction ? this.pressedBkgColor : this.normalBkgColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(paddingLeft, paddingTop, getWidth() - paddingRight, getHeight() - paddingBottom);
        int width = (getWidth() - (paddingLeft + paddingRight)) >> 1;
        canvas.drawRoundRect(rectF, width, width, paint);
    }

    /* renamed from: a */
    public void setAZKeys(List<String> nameList) {
        String buildKey;
        this.namePinyinList.clear();
        Iterator<String> it = nameList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!TTTextUtils.isValidateMediaString(next)) {
                buildKey = (String) TTTextUtils.validateString(BaseApplication.getApplication(), next);
            } else {
                buildKey = next != null ? PinyinUtils.buildKey(next) : "";
            }
            this.namePinyinList.add(buildKey);
        }
        m1920a(this.f7468m == -1 ? 0 : this.f7468m);
    }

    /* renamed from: a */
    private int m1915a(String str) {
        DebugUtils.m8426a(str, "letter");
        char charAt = str.toUpperCase().charAt(0);
        if (charAt < 'A' || charAt > 'Z') {
            return 0;
        }
        int size = this.namePinyinList.size();
        for (int i = 0; i < size; i++) {
            String str2 = this.namePinyinList.get(i);
            if (str2 != null && str2.length() > 0 && str2.charAt(0) == charAt) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    private void m1913a(String str, int i, boolean z) {
        if (this.floatLetterTextView == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(VersionUpdateConst.UPDATE_HIAPK_TYPE, 920);
            int m7229a = DisplayUtils.dp2px(80);
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.height = m7229a;
            layoutParams.width = m7229a;
            layoutParams.format = -3;
            layoutParams.windowAnimations = R.anim.fade_out;
            this.floatLetterTextView = (TextView) View.inflate(getContext(), R.layout.list_float_letter, null);
            ((Activity) getContext()).getWindowManager().addView(this.floatLetterTextView, layoutParams);
            ThemeManager.m3269a(this.floatLetterTextView, ThemeElement.SONG_LIST_POP_TEXT);
            ThemeManager.m3269a(this.floatLetterTextView, ThemeElement.SONG_LIST_POP_BACKGROUND);
        }
        this.floatLetterTextView.setText(str);
        if (this.onMatchedPositionChangeListener != null && z) {
            this.f7469n = true;
            this.onMatchedPositionChangeListener.positionChanged(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void removeFloatLetterView() {
        if (this.floatLetterTextView != null) {
            ((Activity) getContext()).getWindowManager().removeView(this.floatLetterTextView);
            this.floatLetterTextView = null;
        }
    }

    public void setOnMatchedPositionChangeListener(OnMatchedPositionChangeListener interfaceC2161a) {
        this.onMatchedPositionChangeListener = interfaceC2161a;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        invalid();
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.f7469n) {
            this.f7469n = false;
        } else if (!this.namePinyinList.isEmpty() && absListView.getChildCount() != 0) {
            int footerViewsCount = (i3 - ((ListView) absListView).getFooterViewsCount()) - this.namePinyinList.size();
            if (footerViewsCount > 0 && i < footerViewsCount) {
                invalid();
            } else if (this.f7468m != i) {
                m1920a(i);
            }
        }
    }

    /* renamed from: a */
    public void m1920a(int i) {
        if (this.namePinyinList.size() == 0) {
            this.f7468m = i;
        } else if (i >= 0 && i < this.namePinyinList.size()) {
            this.f7468m = i;
            m1907b(this.namePinyinList.get(i));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m1907b(String str) {
        int i = 0;
        int i2 = 0;
        String[] split = str.split("\\s+");
        if (!StringUtils.isEmpty(split[0]) && split.length > 1) {
            char charAt = split[0].charAt(0);
            String str2 = split[1];
            while (true) {
                int i3 = i2;
                if (i3 >= this.AtoZList.size()) {
                    break;
                }
                String str3 = this.AtoZList.get(i3);
                if (str3.equals(str2)) {
                    i = i3;
                    break;
                } else if (str3.equals(charAt + "")) {
                    i = i3;
                    break;
                } else {
                    i2 = i3 + 1;
                }
            }
            if (i == -1) {
                i = this.AtoZList.indexOf("#");
            }
            if (i != this.selectIndex && i >= 0 && i < this.AtoZList.size()) {
                this.selectIndex = i;
            }
            invalidate();
        }
        i = -1;
        if (i == -1) {
        }
        if (i != this.selectIndex) {
            this.selectIndex = i;
        }
        invalidate();
    }

    /* renamed from: b */
    private void invalid() {
        invalidate();
    }
}
