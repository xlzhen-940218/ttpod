package com.sds.android.ttpod.component.emoticons;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.sds.android.ttpod.R;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.emoticons.c */
/* loaded from: classes.dex */
public class EmoticonsAdapter extends BaseAdapter {

    /* renamed from: a */
    private List<ChatEmoji> f4123a;

    /* renamed from: b */
    private LayoutInflater f4124b;

    /* renamed from: c */
    private int f4125c;

    public EmoticonsAdapter(Context context, List<ChatEmoji> list) {
        this.f4125c = 0;
        this.f4124b = LayoutInflater.from(context);
        this.f4123a = list;
        this.f4125c = list.size();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4125c;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f4123a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        ChatEmoji chatEmoji = this.f4123a.get(i);
        if (view == null) {
            view = this.f4124b.inflate(R.layout.musiccircle_emoticons_item, (ViewGroup) null);
            imageView = (ImageView) view.findViewById(R.id.item_iv_face);
            view.setTag(imageView);
        } else {
            imageView = (ImageView) view.getTag();
        }
        if (chatEmoji.m6648b() == R.drawable.xml_musiccircle_emoticons_delete) {
            view.setBackground(null);
            imageView.setImageResource(chatEmoji.m6648b());
        } else if (TextUtils.isEmpty(chatEmoji.m6651a())) {
            view.setBackground(null);
            imageView.setImageDrawable(null);
        } else {
            imageView.setTag(chatEmoji);
            imageView.setImageResource(chatEmoji.m6648b());
        }
        return view;
    }
}
