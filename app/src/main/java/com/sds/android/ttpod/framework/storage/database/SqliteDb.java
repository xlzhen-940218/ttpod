package com.sds.android.ttpod.framework.storage.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.p061c.SqliteStorageImpl;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.storage.database.b */
/* loaded from: classes.dex */
public class SqliteDb {

    /* renamed from: a */
    private static SqliteStorageImpl f6979a;

    /* renamed from: b */
    private static final String[] f6980b = {"info_type", "add_time", "origin", "position", "download_time", "response_time", "connect_timestamp", "cutoff_times", "filename", "file_id", "audio_quality", "list_id", "list_type", "complete_time", AbsMediaListFragment.KEY_GROUP_ID, "url_updated", "song_type", "save_path", "source_url", "file_length", "state", "resumable", "connect_failed_IPs", "connected_IP", "response_code", "statistic_request", "download_length"};

    /* renamed from: a */
    public static void m3126a(Context context) {
        if (f6979a == null) {
            f6979a = new SqliteStorageImpl(context, "ttpod.db", 16777222, new SqliteStorageImpl.InterfaceC0592a() { // from class: com.sds.android.ttpod.framework.storage.database.b.1
                @Override // com.sds.android.sdk.lib.p061c.SqliteStorageImpl.InterfaceC0592a
                /* renamed from: a */
                public void mo3116a(int i, int i2) {
                }
            });
            f6979a.m8596a(DownloadTaskInfo.class);
            f6979a.m8599a();
        }
    }

    /* renamed from: a */
    public static void m3122a(DownloadTaskInfo downloadTaskInfo) {
        f6979a.m8595a(downloadTaskInfo);
    }

    /* renamed from: b */
    public static List<DownloadTaskInfo> m3118b(DownloadTaskInfo downloadTaskInfo) {
        return f6979a.m8593a( downloadTaskInfo, false);
    }

    /* renamed from: a */
    public static List<DownloadTaskInfo> m3124a(Context context, String str) {
        return m3123a(context.getContentResolver().query(DownloadContentProvider.f6973a, null, "save_path=" + str, null, null));
    }

    /* renamed from: a */
    public static List<DownloadTaskInfo> m3125a(Context context, Integer num) {
        String num2;
        if (num == null) {
            num2 = "0";
        } else {
            num2 = num.toString();
        }
        return m3123a(context.getContentResolver().query(DownloadContentProvider.f6973a, null, "info_type=" + num2, null, null));
    }

    /* renamed from: c */
    public static void m3117c(DownloadTaskInfo downloadTaskInfo) {
        f6979a.m8589b(downloadTaskInfo);
    }

    /* renamed from: b */
    public static void m3119b(Context context, String str) {
        context.getContentResolver().delete(DownloadContentProvider.f6973a, str, null);
    }

    /* renamed from: a */
    public static void m3121a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
        f6979a.m8594a(downloadTaskInfo, downloadTaskInfo2);
    }

    /* renamed from: a */
    public static Cursor m3120a(List<DownloadTaskInfo> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(f6980b);
        for (DownloadTaskInfo downloadTaskInfo : list) {
            Object[] objArr = new Object[27];
            objArr[0] = downloadTaskInfo.getType();
            objArr[1] = downloadTaskInfo.getAddTime();
            objArr[2] = downloadTaskInfo.getOrigin();
            objArr[3] = downloadTaskInfo.getPosition();
            objArr[4] = downloadTaskInfo.getDownloadTime();
            objArr[5] = downloadTaskInfo.getRespondTime();
            objArr[6] = downloadTaskInfo.getConnectTimeStamp();
            objArr[7] = downloadTaskInfo.getCutOffTimes();
            objArr[8] = downloadTaskInfo.getFileName();
            objArr[9] = downloadTaskInfo.getFileId();
            objArr[10] = downloadTaskInfo.getAudioQuality();
            objArr[11] = downloadTaskInfo.getListId();
            objArr[12] = Integer.valueOf(downloadTaskInfo.getListType());
            objArr[13] = downloadTaskInfo.getCompleteTime();
            objArr[14] = downloadTaskInfo.getGroupId();
            objArr[15] = Integer.valueOf(downloadTaskInfo.isUrlUpdated() ? 1 : 0);
            objArr[16] = Integer.valueOf(downloadTaskInfo.getSongType());
            objArr[17] = downloadTaskInfo.getSavePath();
            objArr[18] = downloadTaskInfo.getSourceUrl();
            objArr[19] = downloadTaskInfo.getFileLength();
            objArr[20] = downloadTaskInfo.getState();
            objArr[21] = Integer.valueOf(downloadTaskInfo.getIsResumeBrokenTransferSupported().booleanValue() ? 1 : 0);
            objArr[22] = downloadTaskInfo.getStatisticConnectFailedIP();
            objArr[23] = downloadTaskInfo.getConnectedIP();
            objArr[24] = Integer.valueOf(downloadTaskInfo.getResponseCode());
            objArr[25] = Integer.valueOf(downloadTaskInfo.getStatisticRequest() ? 1 : 0);
            objArr[26] = Integer.valueOf(downloadTaskInfo.getDownloadLength());
            matrixCursor.addRow(objArr);
        }
        return matrixCursor;
    }

    /* renamed from: a */
    @SuppressLint("Range")
    public static List<DownloadTaskInfo> m3123a(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor != null && cursor.moveToNext()) {
            DownloadTaskInfo downloadTaskInfo = new DownloadTaskInfo();
            downloadTaskInfo.setType(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("info_type"))));
            downloadTaskInfo.setAddTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("add_time"))));
            downloadTaskInfo.setOrigin(cursor.getString(cursor.getColumnIndex("origin")));
            downloadTaskInfo.setPosition(cursor.getInt(cursor.getColumnIndex("position")));
            downloadTaskInfo.setDownloadTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("download_time"))));
            downloadTaskInfo.setRespondTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("response_time"))));
            downloadTaskInfo.setConnectTimeStamp(Long.valueOf(cursor.getLong(cursor.getColumnIndex("connect_timestamp"))));
            downloadTaskInfo.setCutOffTimes(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("cutoff_times"))));
            downloadTaskInfo.setFileName(cursor.getString(cursor.getColumnIndex("filename")));
            downloadTaskInfo.setFileId(Long.valueOf(cursor.getLong(cursor.getColumnIndex("file_id"))));
            downloadTaskInfo.setAudioQuality(cursor.getString(cursor.getColumnIndex("audio_quality")));
            downloadTaskInfo.setListId(cursor.getString(cursor.getColumnIndex("list_id")));
            downloadTaskInfo.setListType(cursor.getInt(cursor.getColumnIndex("list_type")));
            downloadTaskInfo.setCompleteTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("complete_time"))));
            downloadTaskInfo.setGroupId(cursor.getString(cursor.getColumnIndex(AbsMediaListFragment.KEY_GROUP_ID)));
            downloadTaskInfo.setUrlUpdated(cursor.getInt(cursor.getColumnIndex("url_updated")) == 1);
            downloadTaskInfo.setSongType(cursor.getInt(cursor.getColumnIndex("song_type")));
            downloadTaskInfo.setSavePath(cursor.getString(cursor.getColumnIndex("save_path")));
            downloadTaskInfo.setSourceUrl(cursor.getString(cursor.getColumnIndex("source_url")));
            downloadTaskInfo.setFileLength(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("file_length"))));
            downloadTaskInfo.setState(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("state"))));
            downloadTaskInfo.setIsResumeBrokenTransferSupported(Boolean.valueOf(cursor.getInt(cursor.getColumnIndex("resumable")) == 1));
            downloadTaskInfo.statisticConnectFailedIPs(cursor.getString(cursor.getColumnIndex("connect_failed_IPs")));
            downloadTaskInfo.setConnectedIP(cursor.getString(cursor.getColumnIndex("connected_IP")));
            downloadTaskInfo.setResponseCode(cursor.getInt(cursor.getColumnIndex("response_code")));
            downloadTaskInfo.setStatisticRequest(cursor.getInt(cursor.getColumnIndex("statistic_request")) == 1);
            downloadTaskInfo.setDownloadLength(cursor.getInt(cursor.getColumnIndex("download_length")));
            arrayList.add(downloadTaskInfo);
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }
}
