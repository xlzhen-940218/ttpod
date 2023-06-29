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
    private GridView lyricColorGridview;

    /* renamed from: b */
    private LyricColorCallback lyricColorCallback;

    /* renamed from: c */
    private LyricColorGridAdapter lyricColorGridAdapter;

    /* renamed from: d */
    private LayoutInflater layoutInflater;

    /* compiled from: LyricColorPanel.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.a$b */
    /* loaded from: classes.dex */
    public interface LyricColorCallback {
        /* renamed from: f */
        void onItemClick(int i);
    }

    public LyricColorPanel(Context context, int i, int i2) {
        super(View.inflate(context, R.layout.popups_lyric_color_panel, null), i, i2, true);
        this.lyricColorGridview = null;
        this.layoutInflater = null;
        setAnimationStyle(android.R.style.Animation_Dialog);
        setBackgroundDrawable(new ColorDrawable(0));
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = getContentView();
        if (contentView != null) {
            initLyricColorGridView(context, contentView);
        }
        this.lyricColorGridAdapter.setLyricHighlightColor(Preferences.getLyricHighlightColor());
    }

    /* renamed from: a */
    public void m6508a(LyricColorCallback interfaceC1226b) {
        this.lyricColorCallback = interfaceC1226b;
    }

    /* renamed from: a */
    private void initLyricColorGridView(final Context context, View view) {
        this.lyricColorGridview = (GridView) view.findViewById(R.id.lyric_color_gridview);
        this.lyricColorGridAdapter = new LyricColorGridAdapter();
        this.lyricColorGridview.setAdapter((ListAdapter) this.lyricColorGridAdapter);
        this.lyricColorGridview.setVisibility(View.VISIBLE);
        this.lyricColorGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.component.g.b.b.a.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                if (LyricColorPanel.this.lyricColorCallback != null) {
                    LyricColorPanel.this.lyricColorCallback.onItemClick(LyricColorPanel.this.lyricColorGridAdapter.getHighlightColor(i));
                    ImageView imageView = (ImageView) view2.findViewById(R.id.lyric_color_check_imgview);
                    if (imageView != null) {
                        imageView.setVisibility(View.VISIBLE);
                    }
                    LyricColorPanel.this.lyricColorGridAdapter.setPosition(context, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LyricColorPanel.java */
    /* renamed from: com.sds.android.ttpod.component.g.b.b.a$a */
    /* loaded from: classes.dex */
    public class LyricColorGridAdapter extends BaseAdapter {

        /* renamed from: b */
        private int[] lyricColors;

        /* renamed from: c */
        private int[] highlightColors;

        /* renamed from: d */
        private int[] colorText;

        /* renamed from: e */
        private int position;

        private LyricColorGridAdapter() {
            this.lyricColors = new int[]{R.drawable.img_lyric_color_0, R.drawable.img_lyric_color_1, R.drawable.img_lyric_color_2, R.drawable.img_lyric_color_3, R.drawable.img_lyric_color_4, R.drawable.img_lyric_color_5, R.drawable.img_lyric_color_6, R.drawable.img_lyric_color_7, R.drawable.img_lyric_color_8, R.drawable.img_lyric_color_9, R.drawable.img_lyric_color_10, R.drawable.img_lyric_color_11, R.drawable.img_lyric_color_12, R.drawable.img_lyric_color_13, R.drawable.img_lyric_color_14};
            this.highlightColors = new int[]{-1, -7085825, -2337006, -14374431, -6220011, -16351165, -48547, -18174, -6655249, -10253556, -1441626, -231769, -2, -13893882, 0xff000000};
            this.colorText = new int[]{R.string.defaultColor, R.string.cyan, R.string.october, R.string.blue, R.string.sangria, R.string.viridian, R.string.pink, R.string.lemon, R.string.amethyst, R.string.emerald, R.string.deeppink, R.string.lightpink, R.string.white, R.string.green, R.string.black};
            this.position = 0;
        }

        /* renamed from: a */
        public void setLyricHighlightColor(int position) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.highlightColors.length) {
                    i2 = 0;
                    break;
                } else if (this.highlightColors[i2] == position) {
                    break;
                } else {
                    i2++;
                }
            }
            this.position = i2;
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public void setPosition(Context context, int position) {
            this.position = position;
            if (this.position < 0 || this.position >= this.highlightColors.length) {
                this.position = 0;
            }
            Preferences.setLyricHighlightColor(this.highlightColors[this.position]);
            notifyDataSetChanged();
        }

        /* renamed from: b */
        public int getHighlightColor(int position) {
            return this.highlightColors[position];
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.lyricColors.length;
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
        public View getView(int position, View view, ViewGroup viewGroup) {
            LyricColorGridAdapterViewHolder lyricColorGridAdapterViewHolder;
            if (view != null) {
                lyricColorGridAdapterViewHolder = (LyricColorGridAdapterViewHolder) view.getTag();
            } else {
                LyricColorGridAdapterViewHolder c1225a2 = new LyricColorGridAdapterViewHolder();
                view = LyricColorPanel.this.layoutInflater.inflate(R.layout.popups_lyric_color_grid_item, (ViewGroup) null);
                c1225a2.lyricColorImageView = (ImageView) view.findViewById(R.id.lyric_color_imgview);
                c1225a2.lyricColorTextView = (TextView) view.findViewById(R.id.lyric_color_textview);
                c1225a2.lyricColorCheckImageView = (ImageView) view.findViewById(R.id.lyric_color_check_imgview);
                view.setTag(c1225a2);
                lyricColorGridAdapterViewHolder = c1225a2;
            }
            lyricColorGridAdapterViewHolder.lyricColorImageView.setImageResource(this.lyricColors[position]);
            lyricColorGridAdapterViewHolder.lyricColorTextView.setText(this.colorText[position]);
            lyricColorGridAdapterViewHolder.lyricColorCheckImageView.setVisibility(this.position == position ? View.VISIBLE : View.INVISIBLE);
            return view;
        }

        /* compiled from: LyricColorPanel.java */
        /* renamed from: com.sds.android.ttpod.component.g.b.b.a$a$a */
        /* loaded from: classes.dex */
        private class LyricColorGridAdapterViewHolder {

            /* renamed from: b */
            private ImageView lyricColorImageView;

            /* renamed from: c */
            private TextView lyricColorTextView;

            /* renamed from: d */
            private ImageView lyricColorCheckImageView;

            private LyricColorGridAdapterViewHolder() {
            }
        }
    }
}
