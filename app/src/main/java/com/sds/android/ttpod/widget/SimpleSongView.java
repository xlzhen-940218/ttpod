package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.request.Extra;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class SimpleSongView extends FrameLayout implements ThemeManager.InterfaceC2019b {

    /* renamed from: l */
    private static final int f7926l = (int) (0.4d * DisplayUtils.getHeight());

    /* renamed from: a */
    protected TextView f7927a;

    /* renamed from: b */
    protected SimpleGridView f7928b;

    /* renamed from: c */
    protected ColorStateList f7929c;

    /* renamed from: d */
    protected TextView f7930d;

    /* renamed from: e */
    protected View f7931e;

    /* renamed from: f */
    protected View f7932f;

    /* renamed from: g */
    protected Pager f7933g;

    /* renamed from: h */
    protected Integer f7934h;

    /* renamed from: i */
    protected View.OnClickListener f7935i;

    /* renamed from: j */
    protected InterfaceC2230a f7936j;

    /* renamed from: k */
    protected InterfaceC2232c f7937k;

    /* renamed from: m */
    private InterfaceC2231b f7938m;

    /* renamed from: n */
    private ImageView f7939n;

    /* renamed from: o */
    private Animation f7940o;

    /* renamed from: p */
    private View.OnLongClickListener f7941p;

    /* renamed from: com.sds.android.ttpod.widget.SimpleSongView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC2230a {
        /* renamed from: a */
        void m1538a(int i, int i2);
    }

    /* renamed from: com.sds.android.ttpod.widget.SimpleSongView$b */
    /* loaded from: classes.dex */
    public interface InterfaceC2231b {
        /* renamed from: a */
        void mo1537a(Object obj);
    }

    /* renamed from: com.sds.android.ttpod.widget.SimpleSongView$c */
    /* loaded from: classes.dex */
    public interface InterfaceC2232c {
        /* renamed from: a */
        void m1536a(Object obj);
    }

    /* renamed from: a */
    protected abstract int mo1457a();

    /* renamed from: b */
    protected abstract void mo1456b(View view, Object obj);

    public SimpleSongView(Context context) {
        this(context, null);
    }

    public SimpleSongView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7938m = null;
        this.f7935i = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.SimpleSongView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SimpleSongView.this.f7938m != null) {
                    SimpleSongView.this.f7938m.mo1537a(view.getTag(R.id.view_bind_data));
                }
            }
        };
        this.f7941p = new View.OnLongClickListener() { // from class: com.sds.android.ttpod.widget.SimpleSongView.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (SimpleSongView.this.f7937k != null) {
                    SimpleSongView.this.f7937k.m1536a(view.getTag(R.id.view_bind_data));
                    return true;
                }
                return true;
            }
        };
        mo1547a(context);
    }

    public SimpleSongView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7938m = null;
        this.f7935i = new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.SimpleSongView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SimpleSongView.this.f7938m != null) {
                    SimpleSongView.this.f7938m.mo1537a(view.getTag(R.id.view_bind_data));
                }
            }
        };
        this.f7941p = new View.OnLongClickListener() { // from class: com.sds.android.ttpod.widget.SimpleSongView.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (SimpleSongView.this.f7937k != null) {
                    SimpleSongView.this.f7937k.m1536a(view.getTag(R.id.view_bind_data));
                    return true;
                }
                return true;
            }
        };
        mo1547a(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo1547a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.find_song_inner_section_layout, (ViewGroup) this, true);
        this.f7927a = (TextView) findViewById(R.id.find_song_section_title);
        this.f7931e = findViewById(R.id.layout_title);
        this.f7933g = new Pager();
        this.f7930d = (TextView) findViewById(R.id.text_change_data);
        this.f7930d.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.widget.SimpleSongView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SimpleSongView.this.f7936j != null) {
                    SimpleSongView.this.m1539c();
                    SimpleSongView.this.f7936j.m1538a(SimpleSongView.this.f7933g.m4669a(), SimpleSongView.this.f7934h.intValue());
                }
            }
        });
        this.f7939n = (ImageView) findViewById(R.id.image_change_data_anim);
        this.f7940o = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);
        this.f7940o.setRepeatCount(-1);
        this.f7932f = findViewById(R.id.layout_gridview);
        this.f7928b = (SimpleGridView) findViewById(R.id.find_song_section_grid_view);
        this.f7927a.setText(mo1457a());
        onThemeLoaded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m1539c() {
        this.f7930d.setVisibility(View.GONE);
        this.f7939n.setVisibility(View.VISIBLE);
        this.f7939n.startAnimation(this.f7940o);
    }

    public void onThemeLoaded() {
        ThemeManager.m3269a(this.f7927a, ThemeElement.TILE_TEXT);
        ThemeManager.m3269a(this.f7930d, ThemeElement.TILE_TEXT);
        ThemeManager.m3269a(this.f7931e, ThemeElement.TILE_MASK);
        ThemeManager.m3269a(this.f7932f, ThemeElement.TILE_BACKGROUND);
    }

    /* renamed from: a */
    public void mo1543a(ArrayList arrayList, int i) {
        if (!ListUtils.m4718a(arrayList)) {
            int size = arrayList.size();
            if (size > i) {
                size = i - 1;
            }
            this.f7928b.removeAllViews();
            this.f7928b.setVisibility(View.VISIBLE);
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = arrayList.get(i2);
                View mo1541b = mo1541b();
                mo1541b.setTag(R.id.view_tag_index, Integer.valueOf(i2));
                mo1456b(mo1541b, obj);
                this.f7928b.addView(mo1541b);
                mo1546a(mo1541b, obj);
            }
        }
    }

    public void setOnSectionViewItemClickListener(InterfaceC2231b interfaceC2231b) {
        this.f7938m = interfaceC2231b;
    }

    public int getSectionItemCount() {
        return this.f7928b.getChildCount();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSectionItemTextColor(ColorStateList colorStateList) {
        if (this.f7928b != null && this.f7928b.getChildCount() != 0) {
            m1545a((ViewGroup) this.f7928b, colorStateList);
        }
    }

    /* renamed from: a */
    private void m1545a(ViewGroup viewGroup, ColorStateList colorStateList) {
        if (viewGroup != null && viewGroup.getChildCount() != 0) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    m1545a((ViewGroup) childAt, colorStateList);
                } else if (childAt instanceof TextView) {
                    ((TextView) childAt).setTextColor(colorStateList);
                }
            }
        }
    }

    /* renamed from: b */
    protected View mo1541b() {
        View inflate = View.inflate(getContext(), R.layout.picture_with_bottom_text, null);
        TextView textView = (TextView) inflate.findViewById(R.id.item_name);
        if (this.f7929c != null) {
            textView.setTextColor(this.f7929c);
        }
        return inflate;
    }

    /* renamed from: a */
    protected void mo1546a(View view, Object obj) {
        View findViewById = view.findViewById(R.id.item_click_view);
        findViewById.setTag(R.id.view_bind_data, obj);
        findViewById.setOnClickListener(this.f7935i);
        findViewById.setOnLongClickListener(this.f7941p);
    }

    public void setOnChangeDataClickListener(InterfaceC2230a interfaceC2230a) {
        this.f7936j = interfaceC2230a;
    }

    public void setOnSectionViewItemLongClickListener(InterfaceC2232c interfaceC2232c) {
        this.f7937k = interfaceC2232c;
    }

    public void setTitle(String str) {
        this.f7927a.setText(str);
    }

    /* renamed from: a */
    public void m1542a(boolean z) {
        this.f7931e.setVisibility(z ? View.VISIBLE : View.GONE);
    }

    public TextView getChangeDataTextView() {
        return this.f7930d;
    }

    public String getTitle() {
        return this.f7927a == null ? "" : this.f7927a.getText().toString();
    }

    public void setPager(Extra extra) {
        int m8556b = extra.m8556b();
        if (this.f7933g.m4669a() == 1) {
            if (m8556b == 0) {
                m8556b = 1;
            }
            this.f7933g.m4665b(m8556b);
        }
        this.f7933g.m4663c(this.f7933g.m4669a() >= this.f7933g.m4658g() ? 1 : this.f7933g.m4662d());
        this.f7930d.setVisibility(m8556b > 1 ? View.VISIBLE : View.INVISIBLE);
    }

    public void setPageSize(Integer num) {
        this.f7934h = num;
    }
}
