package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;

/* loaded from: classes.dex */
public class HeaderFooterGridView extends LinearLayout implements ThemeManager.InterfaceC2019b {

    /* renamed from: a */
    private ImageView f7695a;

    /* renamed from: b */
    private ImageView f7696b;

    /* renamed from: c */
    private GridView f7697c;

    /* renamed from: d */
    private boolean f7698d;

    /* renamed from: e */
    private View.OnClickListener f7699e;

    /* renamed from: f */
    private View.OnClickListener f7700f;

    public HeaderFooterGridView(Context context) {
        super(context);
        this.f7698d = false;
        this.f7699e = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.HeaderFooterGridView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != null) {
                    HeaderFooterGridView.this.f7697c.smoothScrollToPosition(HeaderFooterGridView.this.f7697c.getTop());
                }
            }
        };
        this.f7700f = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.HeaderFooterGridView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != null) {
                    HeaderFooterGridView.this.f7697c.smoothScrollToPosition(HeaderFooterGridView.this.f7697c.getBottom());
                }
            }
        };
    }

    public HeaderFooterGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7698d = false;
        this.f7699e = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.HeaderFooterGridView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != null) {
                    HeaderFooterGridView.this.f7697c.smoothScrollToPosition(HeaderFooterGridView.this.f7697c.getTop());
                }
            }
        };
        this.f7700f = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.HeaderFooterGridView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view != null) {
                    HeaderFooterGridView.this.f7697c.smoothScrollToPosition(HeaderFooterGridView.this.f7697c.getBottom());
                }
            }
        };
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.header_footer_gridview, this);
        this.f7695a = (ImageView) findViewById(R.id.img_right_menu_arrow_up);
        this.f7696b = (ImageView) findViewById(R.id.img_right_menu_arrow_down);
        this.f7697c = (GridView) findViewById(R.id.gridview);
        if (SDKVersionUtils.sdkThan8()) {
            this.f7695a.setOnClickListener(this.f7699e);
            this.f7696b.setOnClickListener(this.f7700f);
            this.f7697c.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.widget.HeaderFooterGridView.3
                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i) {
                    int firstVisiblePosition = absListView.getFirstVisiblePosition();
                    int childCount = absListView.getChildCount();
                    int count = HeaderFooterGridView.this.f7697c.getCount();
                    if (childCount != 0) {
                        if (HeaderFooterGridView.this.f7697c.getBottom() - HeaderFooterGridView.this.f7697c.getTop() >= absListView.getChildAt(childCount - 1).getBottom() - absListView.getChildAt(0).getTop()) {
                            HeaderFooterGridView.this.f7695a.setVisibility(View.INVISIBLE);
                            HeaderFooterGridView.this.f7696b.setVisibility(View.INVISIBLE);
                            return;
                        }
                        if (firstVisiblePosition == 0 && HeaderFooterGridView.this.f7698d && absListView.getChildAt(0).getTop() >= 0) {
                            HeaderFooterGridView.this.f7698d = false;
                        }
                        if (firstVisiblePosition + childCount == count && !HeaderFooterGridView.this.f7698d && absListView.getChildAt(childCount - 1).getBottom() <= HeaderFooterGridView.this.f7697c.getBottom() - HeaderFooterGridView.this.f7697c.getTop()) {
                            HeaderFooterGridView.this.f7698d = true;
                        }
                        if (HeaderFooterGridView.this.f7698d) {
                            HeaderFooterGridView.this.f7695a.setVisibility(View.VISIBLE);
                            HeaderFooterGridView.this.f7696b.setVisibility(View.INVISIBLE);
                            return;
                        }
                        HeaderFooterGridView.this.f7695a.setVisibility(View.INVISIBLE);
                        HeaderFooterGridView.this.f7696b.setVisibility(View.VISIBLE);
                    }
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    onScrollStateChanged(absListView, 2);
                }
            });
            return;
        }
        this.f7695a.setVisibility(View.GONE);
        this.f7696b.setVisibility(View.GONE);
    }

    public GridView getGridView() {
        return this.f7697c;
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeManager.m3269a(this.f7695a, ThemeElement.SETTING_ARROW_UP_IMAGE);
        ThemeManager.m3269a(this.f7696b, ThemeElement.SETTING_ARROW_DOWN_IMAGE);
    }
}
