package com.voicedragon.musicclient.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.ClipboardManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.MotionEventCompat;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.voicedragon.musicclient.orm.action.ActionHelper;
import com.voicedragon.musicclient.orm.action.OrmAction;
import com.voicedragon.musicclient.orm.download.OrmDownloadEntry;
import com.voicedragon.musicclient.orm.playlist.OrmFavorite;
import com.voicedragon.musicclient.orm.playlist.OrmSongMenu;
import com.voicedragon.musicclient.orm.playlist.PlaylistHelper;
import com.voicedragon.musicclient.orm.social.OrmUser;
import com.voicedragon.musicclient.record.DoresoMusicTrack;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MRadarUtil {
    private static long[] pattern;
    private static Toast toast;
    public static float hRadius = 13.0f;
    public static float vRadius = 13.0f;
    public static int iterations = 7;

    static {
        long[] jArr = new long[4];
        jArr[1] = 400;
        pattern = jArr;
    }



    public static void blur(int[] in, int[] out, int width, int height, float radius) {
        int widthMinus1 = width - 1;
        int r = (int) radius;
        int tableSize = (r * 2) + 1;
        int[] divide = new int[tableSize * 256];
        for (int i = 0; i < tableSize * 256; i++) {
            divide[i] = i / tableSize;
        }
        int inIndex = 0;
        for (int y = 0; y < height; y++) {
            int outIndex = y;
            int ta = 0;
            int tr = 0;
            int tg = 0;
            int tb = 0;
            for (int i2 = -r; i2 <= r; i2++) {
                int rgb = in[clamp(i2, 0, width - 1) + inIndex];
                ta += (rgb >> 24) & MotionEventCompat.ACTION_MASK;
                tr += (rgb >> 16) & MotionEventCompat.ACTION_MASK;
                tg += (rgb >> 8) & MotionEventCompat.ACTION_MASK;
                tb += rgb & MotionEventCompat.ACTION_MASK;
            }
            for (int x = 0; x < width; x++) {
                out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb];
                int i1 = x + r + 1;
                if (i1 > widthMinus1) {
                    i1 = widthMinus1;
                }
                int i22 = x - r;
                if (i22 < 0) {
                    i22 = 0;
                }
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i22];
                ta += ((rgb1 >> 24) & MotionEventCompat.ACTION_MASK) - ((rgb2 >> 24) & MotionEventCompat.ACTION_MASK);
                tr += ((16711680 & rgb1) - (16711680 & rgb2)) >> 16;
                tg += ((65280 & rgb1) - (65280 & rgb2)) >> 8;
                tb += (rgb1 & MotionEventCompat.ACTION_MASK) - (rgb2 & MotionEventCompat.ACTION_MASK);
                outIndex += height;
            }
            inIndex += width;
        }
    }

    public static int clamp(int x, int a, int b) {
        return x < a ? a : x > b ? b : x;
    }

    public static void blurFractional(int[] in, int[] out, int width, int height, float radius) {
        float radius2 = radius - ((int) radius);
        float f = 1.0f / (1.0f + (2.0f * radius2));
        int inIndex = 0;
        for (int y = 0; y < height; y++) {
            int outIndex = y;
            out[outIndex] = in[0];
            int outIndex2 = outIndex + height;
            for (int x = 1; x < width - 1; x++) {
                int i = inIndex + x;
                int rgb1 = in[i - 1];
                int rgb2 = in[i];
                int rgb3 = in[i + 1];
                int a1 = (rgb1 >> 24) & MotionEventCompat.ACTION_MASK;
                int r1 = (rgb1 >> 16) & MotionEventCompat.ACTION_MASK;
                int g1 = (rgb1 >> 8) & MotionEventCompat.ACTION_MASK;
                int b1 = rgb1 & MotionEventCompat.ACTION_MASK;
                int a2 = (rgb2 >> 24) & MotionEventCompat.ACTION_MASK;
                int r2 = (rgb2 >> 16) & MotionEventCompat.ACTION_MASK;
                int g2 = (rgb2 >> 8) & MotionEventCompat.ACTION_MASK;
                int b2 = rgb2 & MotionEventCompat.ACTION_MASK;
                int a3 = (rgb3 >> 24) & MotionEventCompat.ACTION_MASK;
                int r3 = (rgb3 >> 16) & MotionEventCompat.ACTION_MASK;
                int g3 = (rgb3 >> 8) & MotionEventCompat.ACTION_MASK;
                int b3 = rgb3 & MotionEventCompat.ACTION_MASK;
                int b12 = (int) ((b2 + ((int) ((b1 + b3) * radius2))) * f);
                out[outIndex2] = (((int) ((a2 + ((int) ((a1 + a3) * radius2))) * f)) << 24) | (((int) ((r2 + ((int) ((r1 + r3) * radius2))) * f)) << 16) | (((int) ((g2 + ((int) ((g1 + g3) * radius2))) * f)) << 8) | b12;
                outIndex2 += height;
            }
            out[outIndex2] = in[width - 1];
            inIndex += width;
        }
    }

    public static void setWH(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (width != 0) {
            params.width = width;
        }
        if (height != 0) {
            params.height = height;
        }
        view.setLayoutParams(params);
    }

    public static void sendMessage(Handler handler, int what, String message, int arg1) {
        Message msg = new Message();
        msg.what = what;
        if (message != null) {
            msg.obj = message;
            msg.arg1 = arg1;
        }
        handler.sendMessage(msg);
    }

    public static void stringToCopy(Context context, String text) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(text);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, 100.0f, 100.0f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        } catch (Error e) {
            return null;
        }
    }

    public static int getWidth(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    public static int getHeight(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }



    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            int statusBarHeight = context.getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    public static boolean saveRefreshLastTime(Context context, long lastTime, String key) {
        SharedPreferences preferences = context.getSharedPreferences(MRadar.REFRESHTIME.REFRESH_KEY, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, lastTime);
        return editor.commit();
    }

    public static long getRefreshLastTime(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(MRadar.REFRESHTIME.REFRESH_KEY, 0);
        return preferences.getLong(key, 0L);
    }

    public static boolean savePersonalRefreshLastTime(Context context, long lastTime) {
        SharedPreferences preferences = context.getSharedPreferences(MRadar.REFRESHTIME.REFRESH_KEY, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(MRadar.REFRESHTIME.PERSONAL_LASTTIME, lastTime);
        return editor.commit();
    }

    public static long getPersonalRefreshLastTime(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(MRadar.REFRESHTIME.REFRESH_KEY, 0);
        return preferences.getLong(MRadar.REFRESHTIME.PERSONAL_LASTTIME, 0L);
    }

    public static boolean clearPersonalLastTime(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(MRadar.REFRESHTIME.REFRESH_KEY, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        return editor.commit();
    }

    public static boolean isRefresh(Context context, String key) {
        long currenttime = System.currentTimeMillis();
        long lasttime = getRefreshLastTime(context, key);
        return currenttime - lasttime > MRadar.REFRESHTIME.TIME_30;
    }

    public static boolean saveMoreData(Context context, String key, boolean bool) {
        SharedPreferences preferences = context.getSharedPreferences(MRadar.MOREKEY.MORE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, bool);
        return editor.commit();
    }

    public static boolean getMoreData(Context context, String key, boolean defaultdata) {
        SharedPreferences preferences = context.getSharedPreferences(MRadar.MOREKEY.MORE, 0);
        return preferences.getBoolean(key, defaultdata);
    }

    public static void vibrate(Context context) {
        if (MRadar.MOREKEY.IS_VIBRATOR) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(pattern, -1);
        }
    }

    public static int refushSongMenu(PlaylistHelper helper, String key) {
        if (helper == null || TextUtils.isEmpty(key)) {
            return -1;
        }
        try {
            int size = helper.getSongMenuSongsDao().queryForEq("key", key).size();
            OrmSongMenu songmenu = helper.getSongMenuDao().queryForEq("key", key).get(0);
            songmenu.setNum(size);
            helper.getSongMenuDao().update((OrmSongMenu) songmenu);
            return size;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void show(Context context, int text) {
        if (toast == null) {
            toast = Toast.makeText(context, getString(context, text), Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void show(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static String encode(String text) {
        return Uri.encode(text);
    }

    public static boolean checkEmail(String email) {
        try {
            Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
            Matcher matcher = regex.matcher(email);
            boolean flag = matcher.matches();
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    public static boolean isCH(Context context) {
        String conutry = context.getResources().getConfiguration().locale.getLanguage();
        return conutry.equals(MRadar.Login.f1072CN);
    }

    public static String getCountry(Context context) {
        String conutry = context.getResources().getConfiguration().locale.getLanguage();
        return conutry;
    }

    public static void setTvBold(TextView tv) {
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
    }

    public static String buildMultiPicsToOne(String md51, String md52, String md53, String md54) {
        if (TextUtils.isEmpty(md51) || "nonvalid".equals(md51) || TextUtils.isEmpty(md52) || "nonvalid".equals(md52) || TextUtils.isEmpty(md53) || "nonvalid".equals(md53) || TextUtils.isEmpty(md54) || "nonvalid".equals(md54)) {
            return null;
        }
        try {
            md51 = URLEncoder.encode(md51, "UTF-8");
            md52 = URLEncoder.encode(md52, "UTF-8");
            md53 = URLEncoder.encode(md53, "UTF-8");
            md54 = URLEncoder.encode(md54, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        StringBuffer buffer = new StringBuffer(MRadarUrl.IMAGE.History_Mult);
        buffer.append("md5_1=").append(md51);
        buffer.append("&md5_2=").append(md52);
        buffer.append("&md5_3=").append(md53);
        buffer.append("&md5_4=").append(md54);
        Logger.m2e("mult url", buffer.toString());
        return buffer.toString();
    }



    public static OrmDownloadEntry getOrmDownloadEntry(DoresoMusicTrack track) {
        OrmDownloadEntry mTrack = new OrmDownloadEntry();
        mTrack.setTrackID(System.currentTimeMillis());
        mTrack.setMd5(track.getMd5());
        mTrack.setAlbumName(track.getAlbum());
        mTrack.setArtistName(track.getArtist());
        mTrack.setTrackName(track.getName());
        return mTrack;
    }

    public static DoresoMusicTrack getDoresoMusicTrack(OrmDownloadEntry track) {
        DoresoMusicTrack mTrack = new DoresoMusicTrack();
        mTrack.setMd5(track.getMd5());
        mTrack.setAlbum(track.getAlbumName());
        mTrack.setArtist(track.getArtistName());
        mTrack.setDuration(track.getTrackDuration());
        mTrack.setName(track.getTrackName());
        return mTrack;
    }



    public static OrmFavorite getOrmFavourite(DoresoMusicTrack track) {
        OrmFavorite orm = new OrmFavorite();
        orm.setAblum(track.getAlbum());
        orm.setArtist(track.getArtist());
        orm.setMd5(track.getMd5());
        orm.setTitle(track.getName());
        orm.setType(1);
        return orm;
    }

    public static boolean isFav(Dao<OrmFavorite, Integer> dao, String md5) {
        List<OrmFavorite> list;
        try {
            list = dao.queryForEq("md5", md5);
        } catch (SQLException e) {
            e.printStackTrace();
            list = new ArrayList<>();
        }
        return list.size() > 0 && list.get(0).getType() != 2;
    }

    public static OrmFavorite getOrmFavorite(Dao<OrmFavorite, Integer> dao, String md5) {
        List<OrmFavorite> list;
        try {
            list = dao.queryForEq("md5", md5);
        } catch (SQLException e) {
            e.printStackTrace();
            list = new ArrayList<>();
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    /* loaded from: classes.dex */
    public static class Time {
        @SuppressLint({"SimpleDateFormat"})
        public static String getDate(long time) {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date(time);
            return format1.format(date);
        }

        @SuppressLint({"SimpleDateFormat"})
        public static String getDateNew(long time) {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(time);
            return format1.format(date);
        }

        public static String secondsToString(long time) {
            String s;
            int seconds = Integer.parseInt(String.valueOf(time)) / MRadar.RESULT.PLAZA;
            int minute = seconds / 60;
            if (minute < 10) {
                s = "0" + minute + ":";
            } else {
                s = (seconds / 60) + ":";
            }
            int t = seconds % 60;
            return String.valueOf(s) + (t < 10 ? "0" + t : Integer.valueOf(t));
        }

        @SuppressLint({"SimpleDateFormat"})
        public static int getCurrentTime() {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String str = format.format((Date) now);
            return Integer.parseInt(str);
        }
    }

    /* loaded from: classes.dex */
    public static class LoginUtil {
        public static void saveLoginInfo(Context context, String key, String value) {
            SharedPreferences preferences = context.getSharedPreferences(MRadar.LoginInfo.LOGININFO, 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.commit();
            getLoginInfo(context);
        }

        public static void saveLoginInfo(Context context, String key, int value) {
            SharedPreferences preferences = context.getSharedPreferences(MRadar.LoginInfo.LOGININFO, 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            editor.commit();
            getLoginInfo(context);
        }



        public static void getLoginInfo(Context context) {
            SharedPreferences preferences = context.getSharedPreferences(MRadar.LoginInfo.LOGININFO, 0);
            MRadar.Login.NAME = preferences.getString(MRadar.LoginInfo.LOGININFONAME, "");
            MRadar.Login.UID = preferences.getString(MRadar.LoginInfo.LOGININFOUID, "");
            MRadar.Login.TOKEN = preferences.getString(MRadar.LoginInfo.LOGININFOTOKEN, "");
            try {
                MRadar.Login.GENDER = preferences.getInt(MRadar.LoginInfo.LOGININFOGENDER, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MRadar.Login.SORT = preferences.getString(MRadar.LoginInfo.LOGININFOSORT, "");
            MRadar.Login.IDESC = preferences.getString(MRadar.LoginInfo.LOGININFOIDESC, "");
            MRadar.Login.TAG = preferences.getString(MRadar.LoginInfo.LOGININFOTAG, "");
            MRadar.Login.PHOTOTIME = preferences.getString(MRadar.LoginInfo.LOGININFOPHOTOTIME, "");
        }



        public static boolean isLogin() {
            return !TextUtils.isEmpty(MRadar.Login.UID);
        }



        public static String getIconUrl() {
            return MRadar.Login.PHOTOTIME.equals("") ? "" : MRadarUrl.Login.GETICON + MRadar.Login.UID + "&phototime=" + MRadar.Login.PHOTOTIME;
        }
    }

    /* loaded from: classes.dex */
    public static class CountUtil {
        public static void sendCountUpdata(String action, String fid) {
            RequestParams params = new RequestParams();
            params.add("action", action);
            params.add("fid", fid);
            MRadarRestClient.get(MRadarUrl.CountUrl.COUNT_UPDATA, params, new JsonHttpResponseHandler());
        }

        public static void sendCountSongUpdata(String action, String md5sum) {
            RequestParams params = new RequestParams();
            params.add("action", action);
            params.add("md5sum", md5sum);
            MRadarRestClient.get(MRadarUrl.CountUrl.COUNT_SONG_UPDATA, params, new JsonHttpResponseHandler());
        }
    }
}
