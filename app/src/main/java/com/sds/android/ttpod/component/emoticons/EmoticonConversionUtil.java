package com.sds.android.ttpod.component.emoticons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import com.sds.android.ttpod.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.sds.android.ttpod.component.emoticons.b */
/* loaded from: classes.dex */
public final class EmoticonConversionUtil {

    /* renamed from: a */
    private static EmoticonConversionUtil f4118a;

    /* renamed from: b */
    private HashMap<String, String> f4119b = new HashMap<>();

    /* renamed from: c */
    private List<ChatEmoji> f4120c = new ArrayList();

    /* renamed from: d */
    private List<List<ChatEmoji>> f4121d = new ArrayList();

    /* renamed from: e */
    private int f4122e;

    private EmoticonConversionUtil() {
    }

    /* renamed from: a */
    public List<List<ChatEmoji>> m6646a() {
        return this.f4121d;
    }

    /* renamed from: b */
    public static EmoticonConversionUtil m6639b() {
        if (f4118a == null) {
            f4118a = new EmoticonConversionUtil();
        }
        return f4118a;
    }

    /* renamed from: a */
    public SpannableString m6641a(Context context, CharSequence charSequence) {
        SpannableString spannableString;
        m6644a(context);
        if (charSequence instanceof SpannableString) {
            spannableString = (SpannableString) charSequence;
        } else if (charSequence != null && charSequence.length() > 0) {
            spannableString = new SpannableString(charSequence);
        } else {
            return null;
        }
        try {
            m6642a(context, spannableString, Pattern.compile("\\[[^\\]]+\\]", 2), 0);
            return spannableString;
        } catch (Throwable th) {
            th.printStackTrace();
            return spannableString;
        }
    }

    /* renamed from: a */
    public SpannableString m6643a(Context context, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeResource, 25, 25, true);
        decodeResource.recycle();
        createScaledBitmap.setDensity(240);
        ImageSpan imageSpan = new ImageSpan(context, createScaledBitmap, 1);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(imageSpan, 0, str.length(), 33);
        return spannableString;
    }

    /* renamed from: a */
    private void m6642a(Context context, SpannableString spannableString, Pattern pattern, int i) throws Exception {
        int identifier;
        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            String group = matcher.group();
            if (matcher.start() >= i) {
                String str = this.f4119b.get(group);
                if (!TextUtils.isEmpty(str) && (identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName())) != 0) {
                    Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), identifier);
                    Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeResource, this.f4122e, this.f4122e, true);
                    decodeResource.recycle();
                    createScaledBitmap.setDensity(240);
                    ImageSpan imageSpan = new ImageSpan(createScaledBitmap);
                    int length = group.length() + matcher.start();
                    spannableString.setSpan(imageSpan, matcher.start(), length, 17);
                    if (length < spannableString.length()) {
                        m6642a(context, spannableString, pattern, length);
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public void m6644a(Context context) {
        if (this.f4120c.size() <= 0) {
            this.f4122e = (int) (25.0f * context.getResources().getDisplayMetrics().density);
            m6640a(m6638b(context), context);
        }
    }

    /* renamed from: a */
    private void m6640a(List<String> list, Context context) {
        if (list != null) {
            try {
                for (String str : list) {
                    String[] split = str.split(",");
                    String substring = split[0].substring(0, split[0].lastIndexOf("."));
                    this.f4119b.put(split[1], substring);
                    int identifier = context.getResources().getIdentifier(substring, "drawable", context.getPackageName());
                    if (identifier != 0) {
                        ChatEmoji chatEmoji = new ChatEmoji();
                        chatEmoji.m6650a(identifier);
                        chatEmoji.m6649a(split[1]);
                        chatEmoji.m6647b(substring);
                        this.f4120c.add(chatEmoji);
                    }
                }
                int ceil = (int) Math.ceil(this.f4120c.size() / 27.0f);
                for (int i = 0; i < ceil; i++) {
                    this.f4121d.add(m6645a(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private List<ChatEmoji> m6645a(int i) {
        int i2 = i * 27;
        int i3 = i2 + 27;
        if (i3 > this.f4120c.size()) {
            i3 = this.f4120c.size();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4120c.subList(i2, i3));
        if (arrayList.size() < 27) {
            for (int size = arrayList.size(); size < 27; size++) {
                arrayList.add(new ChatEmoji());
            }
        }
        if (arrayList.size() == 27) {
            ChatEmoji chatEmoji = new ChatEmoji();
            chatEmoji.m6650a(R.drawable.xml_musiccircle_emoticons_delete);
            arrayList.add(chatEmoji);
        }
        return arrayList;
    }

    /* renamed from: b */
    public static List<String> m6638b(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open("emoticons.data"), "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return arrayList;
                }
                arrayList.add(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
