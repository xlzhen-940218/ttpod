package com.sds.android.ttpod.media.mediastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
final class MediaTemporaryStore {
    private static final String[] COLUMNS = {"_id", "song_id", "local_data_source", "folder", "title"
            , "artist", "album", "genre",  "composer", "mime_type", "start_time", "duration", "track"
            , "year", "grade", "bit_rate", "sample_rate", "channels", "comment", "error_status", "use_count"
            , MediaStorage.MEDIA_ORDER_BY_ADD_TIME, "modified_time", MediaStorage.MEDIA_ORDER_BY_PLAY_TIME, "favcount", "extra"};
    private ArrayList<MediaItem> mCacheMediaItems;
    private String mCachePath;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaTemporaryStore(Context context) {
        this.mCachePath = EnvironmentUtils.C0605d.m8466b(context);
    }

    private static MediaItem buildMediaItem(ContentValues contentValues) {
        return new MediaItem(contentValues.getAsString("_id"), contentValues.getAsLong("song_id"), contentValues.getAsString("local_data_source"), contentValues.getAsString("folder"), contentValues.getAsString("title"), contentValues.getAsString("artist"), contentValues.getAsString("album"), contentValues.getAsString("genre"), contentValues.getAsString( "composer"), contentValues.getAsString("mime_type"), contentValues.getAsInteger("start_time"), contentValues.getAsInteger("duration"), contentValues.getAsInteger("track"), contentValues.getAsInteger("year"), contentValues.getAsInteger("grade"), contentValues.getAsInteger("bit_rate"), contentValues.getAsInteger("sample_rate"), contentValues.getAsInteger("channels"), contentValues.getAsString("comment"), contentValues.getAsInteger("error_status"), contentValues.getAsInteger("use_count"), contentValues.getAsLong(MediaStorage.MEDIA_ORDER_BY_ADD_TIME), contentValues.getAsLong("modified_time"), contentValues.getAsLong(MediaStorage.MEDIA_ORDER_BY_PLAY_TIME), false, contentValues.getAsString("extra"), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearGroup(String str) {
        deleteGroup(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteGroup(String str) {
        this.mCacheMediaItems = null;
        FileUtils.exists(buildCacheFileName(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateMediaItem(String str, ContentValues contentValues) {
        MediaItem buildMediaItem = buildMediaItem(contentValues);
        ArrayList<MediaItem> readCacheMediaItemList = readCacheMediaItemList(str);
        Iterator<MediaItem> it = readCacheMediaItemList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MediaItem next = it.next();
            if (StringUtils.equals(next.getID(), buildMediaItem.getID())) {
                next.setExtra(buildMediaItem.getExtra());
                break;
            }
        }
        writeMediaItemList(str, readCacheMediaItemList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertMediaItems(String str, ContentValues[] contentValuesArr) {
        ArrayList<MediaItem> readCacheMediaItemList = readCacheMediaItemList(str);
        for (ContentValues contentValues : contentValuesArr) {
            readCacheMediaItemList.add(buildMediaItem(contentValues));
        }
        writeMediaItemList(str, readCacheMediaItemList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertMediaItem(String str, ContentValues contentValues) {
        ArrayList<MediaItem> readCacheMediaItemList = readCacheMediaItemList(str);
        readCacheMediaItemList.add(buildMediaItem(contentValues));
        writeMediaItemList(str, readCacheMediaItemList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor queryMediaItem(String str, String str2) {
        String extraceSingleQuotationMarks = extraceSingleQuotationMarks((str2 == null || !str2.startsWith("_id")) ? null : str2.substring("_id".length() + 1));
        ArrayList<MediaItem> readCacheMediaItemList = readCacheMediaItemList(str);
        if (readCacheMediaItemList == null || readCacheMediaItemList.size() <= 0) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(COLUMNS);
        Iterator<MediaItem> it = readCacheMediaItemList.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            if (extraceSingleQuotationMarks == null || next.getID().equals(extraceSingleQuotationMarks)) {
                Object[] objArr = new Object[26];
                objArr[0] = next.getID();
                objArr[1] = next.getSongID();
                objArr[2] = next.getLocalDataSource();
                objArr[3] = next.getFolder();
                objArr[4] = next.getTitle();
                objArr[5] = next.getArtist();
                objArr[6] = next.getAlbum();
                objArr[7] = next.getGenre();
                objArr[8] = next.getComposer();
                objArr[9] = next.getMimeType();
                objArr[10] = next.getStartTime();
                objArr[11] = next.getDuration();
                objArr[12] = next.getTrack();
                objArr[13] = next.getYear();
                objArr[14] = next.getGrade();
                objArr[15] = next.getBitRate();
                objArr[16] = next.getSampleRate();
                objArr[17] = next.getChannels();
                objArr[18] = next.getComment();
                objArr[19] = next.getErrorStatus();
                objArr[20] = next.getUseCount();
                objArr[21] = next.getDateAddedInMills();
                objArr[22] = next.getDateModifiedInMills();
                objArr[23] = next.getDateLastAccessInMills();
                objArr[24] = Integer.valueOf(next.getFav() ? 1 : 0);
                objArr[25] = next.getExtra();
                matrixCursor.addRow(objArr);
            }
        }
        return matrixCursor;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ArrayList<MediaItem> readCacheMediaItemList(String str) {
        ObjectInputStream objectInputStream;
        Throwable th;Exception e;
        if (this.mCacheMediaItems == null) {
            synchronized (this) {
                File file = new File(buildCacheFileName(str));
                if (file.isFile()) {
                    try {
                        objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    } catch (Exception e1) {
                        e = e1;
                        objectInputStream = null;
                    } catch (Throwable th1) {
                        th = th1;
                        objectInputStream = null;
                        try {
                            objectInputStream.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        //throw th;
                    }
                    try {
                        try {
                            this.mCacheMediaItems = (ArrayList) objectInputStream.readObject();
                            try {
                                objectInputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            objectInputStream.close();
                            //throw th;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                        try {
                            objectInputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        if (this.mCacheMediaItems == null) {
                        }
                        return this.mCacheMediaItems;
                    }
                }
            }
            if (this.mCacheMediaItems == null) {
                this.mCacheMediaItems = new ArrayList<>();
            }
        }
        return this.mCacheMediaItems;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sds.android.ttpod.media.mediastore.MediaTemporaryStore$1] */
    private void writeMediaItemList(final String str, final ArrayList<MediaItem> arrayList) {
        new Thread() { // from class: com.sds.android.ttpod.media.mediastore.MediaTemporaryStore.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Exception e;
                Throwable th;
                ObjectOutputStream objectOutputStream = null;
                synchronized (MediaTemporaryStore.this) {
                    MediaTemporaryStore mediaTemporaryStore = MediaTemporaryStore.this;
                    //String str2 = str;

                    ObjectOutputStream objectOutputStream2 = null;
                    try {
                        try {
                            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(mediaTemporaryStore.buildCacheFileName(str))));
                        } catch (Exception e1) {
                            e = e1;

                        } catch (Throwable th1) {
                            th = th1;
                            try {
                                objectOutputStream2.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            throw th;
                        }
                        try {
                            objectOutputStream.writeObject(arrayList);
                            try {
                                objectOutputStream.close();

                            } catch (Exception e3) {
                                e3.printStackTrace();

                            }
                        } catch (Exception e4) {
                            e = e4;
                            e.printStackTrace();
                            try {
                                objectOutputStream.close();

                            } catch (Exception e5) {
                                e5.printStackTrace();

                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;

                        try {
                            objectOutputStream2.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTemporaryStore(String str) {
        return MediaStorage.GROUP_ID_ONLINE_TEMPORARY.equals(str);
    }

    String buildCacheFileName(String str) {
        return this.mCachePath + File.separator + str;
    }

    private static String extraceSingleQuotationMarks(String str) {
        if (str != null && str.startsWith("'") && str.endsWith("'")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }
}
