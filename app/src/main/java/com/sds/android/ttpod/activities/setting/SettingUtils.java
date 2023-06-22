package com.sds.android.ttpod.activities.setting;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.activities.setting.d */
/* loaded from: classes.dex */
public final class SettingUtils {

    /* renamed from: a */
    private static final HashMap<AudioQuality, int[]> f3013a = new HashMap<>(AudioQuality.values().length);

    static {
        f3013a.put(AudioQuality.UNDEFINED, new int[]{R.string.auto_title, R.string.auto_subtitle});
        f3013a.put(AudioQuality.COMPRESSED, new int[]{R.string.compress_title, R.string.compress_subtitle});
        f3013a.put(AudioQuality.STANDARD, new int[]{R.string.normal_title, R.string.normal_subtitle});
        f3013a.put(AudioQuality.HIGH, new int[]{R.string.high_title, R.string.high_subtitle});
        f3013a.put(AudioQuality.SUPER, new int[]{R.string.super_title, R.string.super_subtitle});
        f3013a.put(AudioQuality.LOSSLESS, new int[]{R.string.lossless_title, R.string.lossless_subtitle});
    }

    /* renamed from: a */
    public static void m7778a(ActionBarActivity actionBarActivity) {
        Intent intent = actionBarActivity.getIntent();
        actionBarActivity.getActionBarController().m7179d();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("title");
            if (!TextUtils.isEmpty(stringExtra)) {
                actionBarActivity.setTitle(stringExtra);
            }
        }
    }

    /* renamed from: a */
    public static void m7779a(Activity activity, Class<? extends Activity> cls, CharSequence charSequence) {
        activity.startActivity(new Intent(activity, cls).putExtra("title", charSequence.toString()));
    }
}
