package com.sds.android.ttpod.utils;

import android.util.SparseArray;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.cloudapi.ttpod.result.CirclePosterListResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import com.sds.android.cloudapi.ttpod.result.StyleDataListResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import java.util.ArrayList;

/* renamed from: com.sds.android.ttpod.a.j */
/* loaded from: classes.dex */
public class FindSongConfig {

    /* compiled from: FindSongConfig.java */
    /* renamed from: com.sds.android.ttpod.a.j$a */
    /* loaded from: classes.dex */
    public static class C0626a {

        /* renamed from: a */
        public static final SparseArray<String> f2499a = new SparseArray<>();

        /* renamed from: a */
        public static int m8278a(int i) {
            return (int) (m8276b(i) / m8274d(i));
        }

        /* renamed from: b */
        public static int m8276b(int i) {
            return ((DisplayUtils.getWidth() - ((i - 1) * DisplayUtils.dp2px(8))) - (DisplayUtils.dp2px(8) * 2)) / i;
        }

        /* renamed from: a */
        public static int m8277a(int i, int i2, String str, ArrayList<RecommendData> arrayList) {
            int m7229a;
            if (arrayList == null) {
                return 0;
            }
            int m8276b = m8276b(i2);
            int i3 = i2 == 1 ? m8276b / 2 : m8276b;
            int ceil = (int) Math.ceil(i / i2);
            if (!str.equals("song_list")) {
                m7229a = (i3 * ceil) + (DisplayUtils.dp2px(0) * (ceil - 1)) + DisplayUtils.dp2px(8);
            } else {
                int i4 = 0;
                int i5 = 0;
                while (i4 < arrayList.size()) {
                    int i6 = i4;
                    while (true) {
                        if (i6 < i4 + i2 && i6 < arrayList.size()) {
                            RecommendData recommendData = arrayList.get(i6);
                            if (recommendData == null || recommendData.getName() == null || arrayList.get(i6).getName().length() <= m8278a(i2)) {
                                i6++;
                            } else {
                                i5++;
                                break;
                            }
                        }
                    }
                    i4 += i2;
                }
                m7229a = (DisplayUtils.dp2px(48) * i5) + ((ceil - i5) * DisplayUtils.dp2px(30)) + (i3 * ceil) + (DisplayUtils.dp2px(0) * (ceil - 1));
            }
            return m7229a;
        }

        /* renamed from: c */
        public static int m8275c(int i) {
            if (i == 3) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i != 5) {
                return i == 6 ? 4 : 0;
            }
            return 3;
        }

        /* renamed from: d */
        public static float m8274d(int i) {
            return DisplayUtils.dp2px(15) - (i * 1.5f);
        }

        static {
            f2499a.put(2, "星期一");
            f2499a.put(3, "星期二");
            f2499a.put(4, "星期三");
            f2499a.put(5, "星期四");
            f2499a.put(6, "星期五");
            f2499a.put(7, "星期六");
            f2499a.put(1, "星期日");
        }
    }

    /* compiled from: FindSongConfig.java */
    /* renamed from: com.sds.android.ttpod.a.j$b */
    /* loaded from: classes.dex */
    public static class C0627b {
        /* renamed from: a */
        public static int m8273a(int i) {
            return ((DisplayUtils.dp2px(75) + 1 + 8) * i) + 1;
        }
    }

    /* compiled from: FindSongConfig.java */
    /* renamed from: com.sds.android.ttpod.a.j$c */
    /* loaded from: classes.dex */
    public static class C0628c {
        /* renamed from: a */
        public static int m8272a(int i) {
            if (i == 0) {
                return R.drawable.img_tag_exclusive_plan;
            }
            if (i == 2) {
                return R.drawable.img_tag_exclusive_publish;
            }
            if (i == 4) {
                return R.drawable.img_tag_hot_activity;
            }
            if (i == 1) {
                return R.drawable.img_tag_new_song_publish;
            }
            if (i == 3) {
                return R.drawable.img_tag_satellite_program;
            }
            if (i == 5) {
                return R.drawable.img_tag_song_list_recommand;
            }
            return R.drawable.img_tag_voice_program;
        }
    }

    /* compiled from: FindSongConfig.java */
    /* renamed from: com.sds.android.ttpod.a.j$d */
    /* loaded from: classes.dex */
    public static class C0629d {
        /* renamed from: a */
        public static String m8271a(StyleDataListResult styleDataListResult) {
            if (styleDataListResult instanceof CirclePosterListResult) {
                return "circle_poster";
            }
            if (styleDataListResult instanceof FindSongHandpickResult) {
                return "hand_pick";
            }
            if (styleDataListResult instanceof OperationZoneResult) {
                return "operation_zone";
            }
            if (styleDataListResult instanceof FindSongHotListResultNew) {
                return "song_list";
            }
            throw new IllegalArgumentException("not support result " + styleDataListResult);
        }
    }
}
