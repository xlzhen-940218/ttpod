package com.sds.android.ttpod.component.p091g.p093b.p095b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import androidx.core.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

/* renamed from: com.sds.android.ttpod.component.g.b.b.a */
/* loaded from: classes.dex */
public class LyricColorPanel extends PopupWindow {

    /* renamed from: a */
    private GridView f4254a;

    /* renamed from: b */
    private InterfaceC1226b f4255b;

    /* renamed from: c */
    private C1224a f4256c;

    /* renamed from: d */
    private LayoutInflater f4257d;

    /* compiled from: LyricColorPanel.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.a$b */
    /* loaded from: classes.dex */
    public interface InterfaceC1226b {
        /* renamed from: f */
        void mo6426f(int i);
    }

    public LyricColorPanel(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_color_panel, null), i, i2, true);
        this.f4254a = null;
        this.f4257d = null;
        setAnimationStyle(16973826);
        setBackgroundDrawable(new ColorDrawable(0));
        this.f4257d = (LayoutInflater) context.getSystemService("layout_inflater");
        View contentView = getContentView();
        if (contentView != null) {
            m6509a(context, contentView);
        }
        this.f4256c.m6504a(Preferences.m3050P());
    }

    /* renamed from: a */
    public void m6508a(InterfaceC1226b interfaceC1226b) {
        this.f4255b = interfaceC1226b;
    }

    /* renamed from: a */
    private void m6509a(final Context context, View view) {
        this.f4254a = (GridView) view.findViewById(R.id.lyric_color_gridview);
        this.f4256c = new C1224a();
        this.f4254a.setAdapter((ListAdapter) this.f4256c);
        this.f4254a.setVisibility(View.VISIBLE);
        this.f4254a.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.component.g.b.b.a.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                if (LyricColorPanel.this.f4255b != null) {
                    LyricColorPanel.this.f4255b.mo6426f(LyricColorPanel.this.f4256c.m6502b(i));
                    ImageView imageView = (ImageView) view2.findViewById(R.id.lyric_color_check_imgview);
                    if (imageView != null) {
                        imageView.setVisibility(View.VISIBLE);
                    }
                    LyricColorPanel.this.f4256c.m6503a(context, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LyricColorPanel.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.a$a */
    /* loaded from: classes.dex */
    public class C1224a extends BaseAdapter {

        /* renamed from: b */
        private int[] f4261b;

        /* renamed from: c */
        private int[] f4262c;

        /* renamed from: d */
        private int[] f4263d;

        /* renamed from: e */
        private int f4264e;

        private C1224a() {
            this.f4261b = new int[]{R.drawable.img_lyric_color_0, R.drawable.img_lyric_color_1, R.drawable.img_lyric_color_2, R.drawable.img_lyric_color_3, R.drawable.img_lyric_color_4, R.drawable.img_lyric_color_5, R.drawable.img_lyric_color_6, R.drawable.img_lyric_color_7, R.drawable.img_lyric_color_8, R.drawable.img_lyric_color_9, R.drawable.img_lyric_color_10, R.drawable.img_lyric_color_11, R.drawable.img_lyric_color_12, R.drawable.img_lyric_color_13, R.drawable.img_lyric_color_14};
            this.f4262c = new int[]{-1, -7085825, -2337006, -14374431, -6220011, -16351165, -48547, -18174, -6655249, -10253556, -1441626, -231769, -2, -13893882, ViewCompat.MEASURED_STATE_MASK};
            this.f4263d = new int[]{R.string.defaultColor, R.string.cyan, R.string.october, R.string.blue, R.string.sangria, R.string.viridian, R.string.pink, R.string.lemon, R.string.amethyst, R.string.emerald, R.string.deeppink, R.string.lightpink, R.string.white, R.string.green, R.string.black};
            this.f4264e = 0;
        }

        /* renamed from: a */
        public void m6504a(int i) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.f4262c.length) {
                    i2 = 0;
                    break;
                } else if (this.f4262c[i2] == i) {
                    break;
                } else {
                    i2++;
                }
            }
            this.f4264e = i2;
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public void m6503a(Context context, int i) {
            this.f4264e = i;
            if (this.f4264e < 0 || this.f4264e >= this.f4262c.length) {
                this.f4264e = 0;
            }
            Preferences.m2942b(this.f4262c[this.f4264e]);
            notifyDataSetChanged();
        }

        /* renamed from: b */
        public int m6502b(int i) {
            return this.f4262c[i];
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f4261b.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            C1225a c1225a;
            if (view != null) {
                c1225a = (C1225a) view.getTag();
            } else {
                C1225a c1225a2 = new C1225a();
                view = LyricColorPanel.this.f4257d.inflate(R.layout.popups_lyric_color_grid_item, (ViewGroup) null);
                c1225a2.f4266b = (ImageView) view.findViewById(R.id.lyric_color_imgview);
                c1225a2.f4267c = (TextView) view.findViewById(R.id.lyric_color_textview);
                c1225a2.f4268d = (ImageView) view.findViewById(R.id.lyric_color_check_imgview);
                view.setTag(c1225a2);
                c1225a = c1225a2;
            }
            c1225a.f4266b.setImageResource(this.f4261b[i]);
            c1225a.f4267c.setText(this.f4263d[i]);
            c1225a.f4268d.setVisibility(this.f4264e == i ? View.VISIBLE : View.INVISIBLE);
            return view;
        }

        /* compiled from: LyricColorPanel.java */
        /* renamed from: com.sds.android.ttpod.component.g.b.b.a$a$a */
        /* loaded from: classes.dex */
        private class C1225a {

            /* renamed from: b */
            private ImageView f4266b;

            /* renamed from: c */
            private TextView f4267c;

            /* renamed from: d */
            private ImageView f4268d;

            private C1225a() {
            }
        }
    }
}
