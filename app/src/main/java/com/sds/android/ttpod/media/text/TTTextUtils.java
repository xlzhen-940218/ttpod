package com.sds.android.ttpod.media.text;

import android.content.Context;
import android.text.TextUtils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.R;

/* loaded from: classes.dex */
public class TTTextUtils {
    private static final String DEFAUTERROR = "0000";
    private static final String UNKNOWN = "unknown";

    private static void teaEncrypt(int[] v, int[] k, int rounds) {
        int y = v[0];
        int z = v[1];
        int sum = 0;
        int delta = 0x9E3779B9;
        int a = k[0];
        int b = k[1];
        int c = k[2];
        int d = k[3];
        for (int i = 0; i < rounds; i++) {
            sum += delta;
            y += (((z << 4) + a) ^ (z + sum) ^ ((z >>> 5) + b));
            z += (((y << 4) + c) ^ (y + sum) ^ ((y >>> 5) + d));
        }
        v[0] = y;
        v[1] = z;
    }

    private static void teaDecrypt(int[] v, int[] k, int rounds) {
        int y = v[0];
        int z = v[1];
        int delta = 0x9E3779B9;
        int sum;
        if (rounds == 16) {
            sum = 0xE3779B90;
        } else if (rounds == 32) {
            sum = 0xC6EF3720;
        } else {
            sum = rounds * delta;
        }
        int a = k[0];
        int b = k[1];
        int c = k[2];
        int d = k[3];
        for (int i = 0; i < rounds; i++) {
            z -= (((y << 4) + c) ^ (y + sum) ^ ((y >>> 5) + d));
            y -= (((z << 4) + a) ^ (z + sum) ^ ((z >>> 5) + b));
            sum -= delta;
        }
        v[0] = y;
        v[1] = z;
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        return str == str2 || (str == null && str2.length() == 0) || ((str2 == null && str.length() == 0) || (str != null && str2 != null && str.length() == str2.length() && str.equalsIgnoreCase(str2)));
    }

    public static CharSequence validateString(Context context, CharSequence charSequence) {
        if (charSequence != null && (charSequence instanceof String)) {
            charSequence = ((String) charSequence).trim();
        }
        if (!isValidateMediaString(charSequence)) {
            return context.getString(R.string.media_unknown);
        }
        return charSequence;
    }

    public static boolean isValidateMediaString(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && !TextUtils.equals("<unknown>", charSequence) && !TextUtils.equals("unknown", charSequence);
    }

    public static String decryptLyricKey(String str, String str2, int i) {
        try {
            if (str == null) str = "";
            if (str2 == null) str2 = "";
            int lenArtist = str.length();
            int lenTitle = str2.length();

            int[] key = new int[4];
            int k0 = 0;
            for (int j = 0; j < lenArtist; j++) {
                k0 = (k0 + str.charAt(j)) ^ j;
            }
            key[0] = k0;

            int k1 = k0;
            for (int k = lenTitle - 1; k >= 0; k--) {
                k1 = (k1 + str2.charAt(k)) ^ k;
            }
            key[1] = k1;

            key[2] = i;
            key[3] = ~((k0 + k1) ^ i);

            int[] v = new int[]{i, 0};
            teaEncrypt(v, key, lenArtist + lenTitle);
            return String.format("%08x%08x", v[0], v[1]);
        } catch (Throwable e) {
            e.printStackTrace();
            return DEFAUTERROR;
        }
    }

    public static String decryptPictureKey(int i, int i2, int i3, String str) {
        try {
            if (str == null) str = "";
            int[] key = new int[]{i3, i3, i3, i3};
            int[] v = new int[]{i, i2};
            teaDecrypt(v, key, 16);
            return String.format("%08x%08x%s", v[0], v[1], str);
        } catch (Throwable e) {
            e.printStackTrace();
            return DEFAUTERROR;
        }
    }

    public static String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public static boolean isLetterOrDigit(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || ('0' <= c && c <= '9');
    }

    public static String readableByte(long j) {
        if (j >= 1024) {
            int log = (int) (Math.log(j) / Math.log(1024));
            return String.format("%.1f%cB", Double.valueOf(j / Math.pow(1024, log)), Character.valueOf("KMGTPE".charAt(log - 1)));
        }
        return j + VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY;
    }

    public static String validateVisualString(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("([\\u0000-\\u001f\\uD7B0-\\uFEFF\\uFFF0-\\uFFFF]+)", "");
    }

    public static boolean containLetterDigitBlankChar(String str) {
        return str != null && str.matches(".*?[A-Za-z\\d]+.*?\\s+.*?[A-Za-z\\d]+.*?");
    }

    public static String addDoubleQuotationWhenContainLetterDigitBlankChar(String str) {
        return containLetterDigitBlankChar(str) ? "\"" + str + "\"" : str;
    }
}
