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

    private static native String nativeDecryptLyricKey(String str, String str2, int i);

    private static native String nativeDecryptPictureKey(int i, int i2, int i3, String str);

    static {
        try {
            System.loadLibrary("tttextutils");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
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
        return (TextUtils.isEmpty(charSequence) || TextUtils.equals("<unknown>", charSequence) || TextUtils.equals("unknown", charSequence)) ? false : true;
    }

    public static String decryptLyricKey(String str, String str2, int i) {
        try {
            return nativeDecryptLyricKey(str, str2, i);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            System.loadLibrary("tttextutils");
            return DEFAUTERROR;
        }
    }

    public static String decryptPictureKey(int i, int i2, int i3, String str) {
        try {
            return nativeDecryptPictureKey(i, i2, i3, str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            System.loadLibrary("tttextutils");
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
