package com.sds.android.ttpod.component.emoticons;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class EmoticonsLayout extends RelativeLayout implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    private Context f4078a;

    /* renamed from: b */
    private Rect f4079b;

    /* renamed from: c */
    private int f4080c;

    /* renamed from: d */
    private InterfaceC1201a f4081d;

    /* renamed from: e */
    private ViewPager f4082e;

    /* renamed from: f */
    private ArrayList<View> f4083f;

    /* renamed from: g */
    private LinearLayout f4084g;

    /* renamed from: h */
    private ArrayList<ImageView> f4085h;

    /* renamed from: i */
    private List<List<ChatEmoji>> f4086i;

    /* renamed from: j */
    private View f4087j;

    /* renamed from: k */
    private EditText f4088k;

    /* renamed from: l */
    private List<EmoticonsAdapter> f4089l;

    /* renamed from: m */
    private int f4090m;

    /* renamed from: com.sds.android.ttpod.component.emoticons.EmoticonsLayout$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1201a {
        /* renamed from: a */
        void m6669a(ChatEmoji chatEmoji);
    }

    public EmoticonsLayout(Context context) {
        super(context);
        this.f4080c = 0;
        this.f4090m = 0;
        this.f4078a = context;
    }

    public void setmMaxLength(int i) {
        this.f4080c = i;
    }

    public EmoticonsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4080c = 0;
        this.f4090m = 0;
        this.f4078a = context;
    }

    /* renamed from: a */
    public void m6676a(final SlidingClosableRelativeLayout slidingClosableRelativeLayout) {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sds.android.ttpod.component.emoticons.EmoticonsLayout.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                EmoticonsLayout.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = slidingClosableRelativeLayout.getHeight();
                int height2 = slidingClosableRelativeLayout.getHeight();
                EmoticonsLayout.this.f4079b = new Rect(0, height2 - DisplayUtils.dp2px((int) 190), height, height2);
            }
        });
    }

    public Rect getRectEmoticons() {
        return this.f4079b;
    }

    public EmoticonsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4080c = 0;
        this.f4090m = 0;
        this.f4078a = context;
    }

    public void setOnCorpusSelectedListener(InterfaceC1201a interfaceC1201a) {
        this.f4081d = interfaceC1201a;
    }

    public void setInputEditText(EditText editText) {
        this.f4088k = editText;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        EmoticonConversionUtil.m6639b().m6644a(((Activity) this.f4078a).getApplication());
        this.f4086i = EmoticonConversionUtil.m6639b().m6646a();
        m6681a();
    }

    /* renamed from: a */
    private void m6681a() {
        m6674b();
        m6672c();
        m6671d();
        m6670e();
    }

    /* renamed from: b */
    private void m6674b() {
        this.f4082e = (ViewPager) findViewById(R.id.vp_contains);
        this.f4084g = (LinearLayout) findViewById(R.id.image_avatar);
        this.f4087j = findViewById(R.id.layout_emoticons);
    }

    /* renamed from: c */
    private void m6672c() {
        this.f4083f = new ArrayList<>();
        View view = new View(this.f4078a);
        view.setBackgroundColor(0);
        this.f4083f.add(view);
        this.f4089l = new ArrayList();
        for (int i = 0; i < this.f4086i.size(); i++) {
            GridView gridView = new GridView(this.f4078a);
            EmoticonsAdapter emoticonsAdapter = new EmoticonsAdapter(this.f4078a, this.f4086i.get(i));
            gridView.setAdapter((ListAdapter) emoticonsAdapter);
            this.f4089l.add(emoticonsAdapter);
            gridView.setOnItemClickListener(this);
            gridView.setNumColumns(7);
            gridView.setBackgroundColor(0);
            gridView.setHorizontalSpacing(1);
            gridView.setVerticalSpacing(DisplayUtils.dp2px(15));
            gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            gridView.setCacheColorHint(0);
            gridView.setPadding(5, 0, 5, 0);
            gridView.setSelector(R.drawable.xml_musiccircle_emoticons_item);
            gridView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            gridView.setGravity(17);
            this.f4083f.add(gridView);
        }
        View view2 = new View(this.f4078a);
        view2.setBackgroundColor(0);
        this.f4083f.add(view2);
    }

    /* renamed from: d */
    private void m6671d() {
        this.f4085h = new ArrayList<>();
        for (int i = 0; i < this.f4083f.size(); i++) {
            ImageView imageView = new ImageView(this.f4078a);
            imageView.setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_normal);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            layoutParams.width = 8;
            layoutParams.height = 8;
            this.f4084g.addView(imageView, layoutParams);
            if (i == 0 || i == this.f4083f.size() - 1) {
                imageView.setVisibility(View.GONE);
            }
            if (i == 1) {
                imageView.setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
            }
            this.f4085h.add(imageView);
        }
    }

    /* renamed from: e */
    private void m6670e() {
        this.f4082e.setAdapter(new EmoticonsPagerAdapter(this.f4083f));
        this.f4082e.setCurrentItem(1);
        this.f4090m = 0;
        this.f4082e.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.component.emoticons.EmoticonsLayout.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                EmoticonsLayout.this.f4090m = i - 1;
                EmoticonsLayout.this.m6680a(i);
                if (i == EmoticonsLayout.this.f4085h.size() - 1 || i == 0) {
                    if (i == 0) {
                        EmoticonsLayout.this.f4082e.setCurrentItem(i + 1);
                        ((ImageView) EmoticonsLayout.this.f4085h.get(1)).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
                        return;
                    }
                    EmoticonsLayout.this.f4082e.setCurrentItem(i - 1);
                    ((ImageView) EmoticonsLayout.this.f4085h.get(i - 1)).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /* renamed from: a */
    public void m6680a(int i) {
        int i2 = 1;
        while (true) {
            int i3 = i2;
            if (i3 < this.f4085h.size()) {
                if (i == i3) {
                    this.f4085h.get(i3).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
                } else {
                    this.f4085h.get(i3).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_normal);
                }
                i2 = i3 + 1;
            } else {
                return;
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int selectionStart;
        ChatEmoji chatEmoji = (ChatEmoji) this.f4089l.get(this.f4090m).getItem(i);
        String obj = this.f4088k.getText().toString();
        if (chatEmoji.m6648b() == R.drawable.xml_musiccircle_emoticons_delete && (selectionStart = this.f4088k.getSelectionStart()) > 0) {
            if ("]".equals(obj.substring(selectionStart - 1, selectionStart))) {
                this.f4088k.getText().delete(obj.lastIndexOf("["), selectionStart);
                return;
            }
            this.f4088k.getText().delete(selectionStart - 1, selectionStart);
        }
        if (!TextUtils.isEmpty(chatEmoji.m6651a())) {
            if (this.f4081d != null) {
                this.f4081d.m6669a(chatEmoji);
            }
            String str = obj + chatEmoji.m6651a();
            if (this.f4080c <= 0 || m6675a(str) / 2.0f <= this.f4080c) {
                this.f4088k.append(EmoticonConversionUtil.m6639b().m6643a(getContext(), chatEmoji.m6648b(), chatEmoji.m6651a()));
            }
        }
    }

    /* renamed from: a */
    public static int m6675a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return 0 + str.replaceAll("[^\\x00-\\xff]", "**").length();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
