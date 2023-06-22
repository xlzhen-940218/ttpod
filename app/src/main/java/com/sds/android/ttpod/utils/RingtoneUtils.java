package com.sds.android.ttpod.utils;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.sds.android.ttpod.a.q */
/* loaded from: classes.dex */
public final class RingtoneUtils {

    /* renamed from: a */
    private static HashMap<String, String> f2513a = new HashMap<>();

    static {
        f2513a.put("flac", "audio/flac");
        f2513a.put("ape", "audio/ape");
        f2513a.put("ogg", "audio/ogg");
    }

    /* renamed from: a */
    private static boolean m8246a(String str) {
        return str != null && (str.startsWith("audio") || str.startsWith("video/3gpp"));
    }

    /* renamed from: a */
    public static int m8247a(Context context, String str, int i) {
        LogUtils.m8380c("RingtoneUtils", "setRingtone mediaPath=%s type=%d", str, Integer.valueOf(i));
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            String absolutePath = file.getAbsolutePath();
            String lowerCase = FileUtils.m8399m(absolutePath).toLowerCase();
            String mimeTypeFromExtension = f2513a.get(lowerCase) != null ? f2513a.get(lowerCase) : MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowerCase);
            LogUtils.m8380c("RingtoneUtils", "setRingtone absPath=%s mimeType=%s", absolutePath, mimeTypeFromExtension);
            if (m8246a(mimeTypeFromExtension)) {
                long m8248a = m8248a(context, str);
                LogUtils.m8380c("RingtoneUtils", "setRingtone id=%d", Long.valueOf(m8248a));
                if (m8248a > 0) {
                    return m8250a(context, i, absolutePath, m8248a);
                }
                return m8249a(context, file, i);
            }
        }
        LogUtils.m8379d("RingtoneUtils", "setRingtone fail");
        return 2;
    }

    /* renamed from: a */
    private static int m8250a(Context context, int i, String str, long j) {
        Uri contentUriForPath = MediaStore.Audio.Media.getContentUriForPath(str);
        LogUtils.m8380c("RingtoneUtils", "updateMedia uri=%s", contentUriForPath.toString());
        int update = context.getContentResolver().update(contentUriForPath, m8251a(i, new File(str)), "_data=?", new String[]{str});
        Uri withAppendedId = ContentUris.withAppendedId(contentUriForPath, j);
        LogUtils.m8380c("RingtoneUtils", "updateMedia uriWithId=%s updatedRows=%d", withAppendedId.toString(), Integer.valueOf(update));
        RingtoneManager.setActualDefaultRingtoneUri(context, i, withAppendedId);
        Ringtone ringtone = RingtoneManager.getRingtone(context, withAppendedId);
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(ringtone != null);
        objArr[1] = ringtone != null ? ringtone.getTitle(context) : "_null_";
        LogUtils.m8380c("RingtoneUtils", "updateMedia ringtone!=null_%b title=%s", objArr);
        return ringtone != null ? 1 : 2;
    }

    /* renamed from: a */
    private static int m8249a(Context context, File file, int i) {
        try {
            Uri insert = context.getContentResolver().insert(MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath()), m8251a(i, file));
            RingtoneManager.setActualDefaultRingtoneUri(context, i, insert);
            Ringtone ringtone = RingtoneManager.getRingtone(context, insert);
            Object[] objArr = new Object[1];
            objArr[0] = Boolean.valueOf(ringtone != null);
            LogUtils.m8380c("RingtoneUtils", "insertMedia ringtone!=null_%b", objArr);
            return ringtone != null ? 1 : 3;
        } catch (Exception e) {
            LogUtils.m8380c("RingtoneUtils", "insertMedia exception=%s", e.toString());
            e.printStackTrace();
            return 3;
        }
    }

    /* renamed from: a */
    private static ContentValues m8251a(int i, File file) {
        ContentValues contentValues = new ContentValues();
        switch (i) {
            case 2:
                contentValues.put("is_notification", (Boolean) true);
                break;
            case 3:
            case 5:
            case 6:
            default:
                contentValues.put("is_ringtone", (Boolean) true);
                break;
            case 4:
                contentValues.put("is_alarm", (Boolean) true);
                break;
            case 7:
                contentValues.put("is_ringtone", (Boolean) true);
                contentValues.put("is_notification", (Boolean) true);
                contentValues.put("is_alarm", (Boolean) true);
                break;
        }
        String name = file.getName();
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("title", name.substring(0, name.lastIndexOf(".")));
        contentValues.put("_size", Long.valueOf(file.length()));
        contentValues.put("mime_type", "audio/" + name.substring(name.lastIndexOf(".") + 1));
        return contentValues;
    }

    /* renamed from: a */
    private static long m8248a(Context context, String str) {
        long j;
        Cursor query = context.getContentResolver().query(MediaStore.Audio.Media.getContentUriForPath(str), new String[]{"_id"}, "Upper(_data)=?", new String[]{str.toUpperCase(Locale.US)}, "_id");
        if (query == null) {
            return 0L;
        }
        if (!query.moveToFirst()) {
            j = 0;
        } else {
            j = query.getLong(0);
        }
        query.close();
        return j;
    }
}
