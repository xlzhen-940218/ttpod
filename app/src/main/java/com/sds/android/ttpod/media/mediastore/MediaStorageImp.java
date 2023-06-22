package com.sds.android.ttpod.media.mediastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MediaStorageImp {
    private static final int MEDIAITEM_CURSOR_INDEX_0 = 0;
    private static final int MEDIAITEM_CURSOR_INDEX_1 = 1;
    private static final int MEDIAITEM_CURSOR_INDEX_10 = 10;
    private static final int MEDIAITEM_CURSOR_INDEX_11 = 11;
    private static final int MEDIAITEM_CURSOR_INDEX_12 = 12;
    private static final int MEDIAITEM_CURSOR_INDEX_13 = 13;
    private static final int MEDIAITEM_CURSOR_INDEX_14 = 14;
    private static final int MEDIAITEM_CURSOR_INDEX_15 = 15;
    private static final int MEDIAITEM_CURSOR_INDEX_16 = 16;
    private static final int MEDIAITEM_CURSOR_INDEX_17 = 17;
    private static final int MEDIAITEM_CURSOR_INDEX_18 = 18;
    private static final int MEDIAITEM_CURSOR_INDEX_19 = 19;
    private static final int MEDIAITEM_CURSOR_INDEX_2 = 2;
    private static final int MEDIAITEM_CURSOR_INDEX_20 = 20;
    private static final int MEDIAITEM_CURSOR_INDEX_21 = 21;
    private static final int MEDIAITEM_CURSOR_INDEX_22 = 22;
    private static final int MEDIAITEM_CURSOR_INDEX_23 = 23;
    private static final int MEDIAITEM_CURSOR_INDEX_24 = 24;
    private static final int MEDIAITEM_CURSOR_INDEX_25 = 25;
    private static final int MEDIAITEM_CURSOR_INDEX_3 = 3;
    private static final int MEDIAITEM_CURSOR_INDEX_4 = 4;
    private static final int MEDIAITEM_CURSOR_INDEX_5 = 5;
    private static final int MEDIAITEM_CURSOR_INDEX_6 = 6;
    private static final int MEDIAITEM_CURSOR_INDEX_7 = 7;
    private static final int MEDIAITEM_CURSOR_INDEX_8 = 8;
    private static final int MEDIAITEM_CURSOR_INDEX_9 = 9;
    private static final String TAG = "MediaStorageImp";

    MediaStorageImp() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void insertGroup(Context context, GroupType groupType, String str, String str2) {
        ContentValues contentValues = null;
        if (str2 != null) {
            contentValues = new ContentValues();
            contentValues.put("group_id_key", str2);
        }
        context.getContentResolver().insert(Builder.buildGroupAccessUri(groupType, str), contentValues);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void insertMediaItem(Context context, String str, MediaItem mediaItem) {
        context.getContentResolver().insert(Builder.buildMediaAccessUri(str), Builder.buildMediaContentValues(mediaItem));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void insertMediaItems(Context context, String str, Collection<MediaItem> collection) {
        int i = 0;
        ContentValues[] contentValuesArr = new ContentValues[collection.size()];
        boolean z = !MediaStorage.GROUP_ID_ONLINE_TEMPORARY.equals(str);
        for (MediaItem mediaItem : collection) {
            contentValuesArr[i] = Builder.buildMediaContentValues(mediaItem, z);
            i++;
        }
        context.getContentResolver().bulkInsert(Builder.buildMediaAccessUri(str), contentValuesArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteGroup(Context context, String str) {
        context.getContentResolver().delete(Builder.buildGroupAccessUri(str), null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearGroup(Context context, String str) {
        context.getContentResolver().delete(Builder.buildClearAccessUri(str), null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteMediaItem(Context context, String str, String str2) {
        context.getContentResolver().delete(Builder.buildMediaAccessUri(str), Builder.buildWhereWithMediaID(str2), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteMediaItems(Context context, String str, Collection<String> collection) {
        context.getContentResolver().delete(Builder.buildMediaAccessUri(str), Builder.buildWhereWithMediaIDs(collection), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateGroupItem(Context context, GroupItem groupItem) {
        context.getContentResolver().update(Builder.buildGroupAccessUri(groupItem.getGroupID()), Builder.buildGroupContentValue(groupItem), null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateMediaItem(Context context, MediaItem mediaItem) {
        context.getContentResolver().update(Builder.buildMediaAccessUri(mediaItem.getGroupID()), Builder.buildMediaContentValues(mediaItem), Builder.buildWhereWithMediaID(mediaItem.getID()), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void exchangeSortOrder(Context context, String str, List<ExchangeOrderEntity> list) {
        ContentValues[] contentValuesArr = new ContentValues[list.size()];
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("exchange_key_1", list.get(i).getMediaID1());
            contentValues.put("exchange_key_2", list.get(i).getMediaID2());
            contentValuesArr[i] = contentValues;
        }
        context.getContentResolver().bulkInsert(Builder.buildSortAccessUri(str), contentValuesArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<GroupItem> queryGroupItems(Context context, GroupType groupType) {
        return Builder.buildGroupItemList(context.getContentResolver().query(Builder.buildGroupAccessUri(groupType), null, null, null, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GroupItem queryGroupItem(Context context, GroupType groupType, String str) {
        GroupItem groupItem = null;
        Cursor query = context.getContentResolver().query(Builder.buildGroupAccessUri(groupType), null, null, null, null);
        if (query != null) {
            while (true) {
                if (!query.moveToNext()) {
                    break;
                }
                String string = query.getString(1);
                if (str.equals(string)) {
                    groupItem = new GroupItem(query.getString(0), string, Integer.valueOf(query.getInt(2)));
                    break;
                }
            }
            query.close();
        }
        return groupItem;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isGroupExisted(Context context, String str) {
        boolean z;
        Cursor query = context.getContentResolver().query(Builder.buildGroupAccessUri(GroupType.CUSTOM_ALL), null, null, null, null);
        if (query == null) {
            return false;
        }
        while (true) {
            if (!query.moveToNext()) {
                z = false;
                break;
            } else if (str.equals(query.getString(1))) {
                z = true;
                break;
            }
        }
        query.close();
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<MediaItem> queryMediaItemList(Context context, String str, String str2) {
        return Builder.buildMediaItemList(context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2), str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AsyncLoadMediaItemList queryAsyncLoadMediaItemList(Context context, String str, String str2) {
        return Builder.buildAsyncMediaItemList(context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MediaItem queryMediaItem(Context context, String str, String str2) {
        MediaItem r2 = null;
        Cursor query = context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, Builder.buildWhereWithMediaID(str2), null, null);
        if (query != null) {
            r2 = query.moveToNext() ? Builder.buildMediaItem(query, str) : null;
            query.close();
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MediaItem queryMediaItemBySongID(Context context, String str, Long l) {
        MediaItem r2=null;
        Cursor query = context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, Builder.buildWhereWithSongID(l), null, null);
        if (query != null) {
            r2 = query.moveToNext() ? Builder.buildMediaItem(query, str) : null;
            query.close();
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> queryMediaIDs(Context context, String str, String str2) {
        Cursor query = context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2);
        ArrayList arrayList = new ArrayList();
        if (query != null) {
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            query.close();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int queryMediaCount(Context context, String str, String str2) {
        Cursor query = context.getContentResolver().query(Builder.buildMediaAccessUri(str), null, null, null, str2);
        if (query == null) {
            return 0;
        }
        int count = query.getCount();
        query.close();
        return count;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> queryMediaIDsUnderFolder(Context context, String str, String str2) {
        Cursor query = context.getContentResolver().query(Builder.buildMediaAccessUri(MediaStorage.GROUP_ID_ALL_LOCAL), null, Builder.buildWhereUnderFolder(str), null, str2);
        ArrayList arrayList = new ArrayList();
        if (query != null) {
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            query.close();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Builder {
        Builder() {
        }

        private static String embraceWithSingleQuotationMarks(String str) {
            return "'" + str + "'";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Uri buildGroupAccessUri(GroupType groupType, String str) {
            return Uri.parse(MediaContentProvider.GROUP_ACCESS + groupType.name() + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Uri buildGroupAccessUri(GroupType groupType) {
            return Uri.parse(MediaContentProvider.GROUP_ACCESS + groupType.name());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Uri buildGroupAccessUri(String str) {
            return Uri.parse(MediaContentProvider.GROUP_ACCESS + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Uri buildClearAccessUri(String str) {
            return Uri.parse(MediaContentProvider.CLEAR_ACCESS + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Uri buildMediaAccessUri(String str) {
            return Uri.parse(MediaContentProvider.MEDIA_ACCESS + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Uri buildSortAccessUri(String str) {
            return Uri.parse(MediaContentProvider.SORT_ACCESS + str);
        }

        static String buildWhereWithMediaIDs(Collection<String> collection) {
            StringBuilder sb = new StringBuilder();
            sb.append("_id IN ").append("(");
            for (String str : collection) {
                sb.append(embraceWithSingleQuotationMarks(str)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1).append(")");
            if (sb.length() == 0) {
                return null;
            }
            return sb.toString();
        }

        static String buildWhereWithMediaID(String str) {
            return "_id=" + embraceWithSingleQuotationMarks(str);
        }

        static String buildWhereWithSongID(Long l) {
            return "song_id=" + embraceWithSingleQuotationMarks(l.toString());
        }

        static String buildWhereUnderFolder(String str) {
            return "folder=" + embraceWithSingleQuotationMarks(str) + " OR " + "folder" + " LIKE " + embraceWithSingleQuotationMarks(str + "/%");
        }

        static ContentValues buildGroupContentValue(GroupItem groupItem) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", groupItem.getName());
            return contentValues;
        }

        static ContentValues buildMediaContentValues(MediaItem mediaItem) {
            return buildMediaContentValues(mediaItem, true);
        }

        static ContentValues buildMediaContentValues(MediaItem mediaItem, boolean z) {
            if (mediaItem.getID() == null) {
                throw new IllegalArgumentException("invalid mediaItem, SongID must not null!");
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", mediaItem.getID());
            if (mediaItem.getSongID() != null) {
                contentValues.put("song_id", mediaItem.getSongID());
            }
            if (mediaItem.getLocalDataSource() != null) {
                contentValues.put("local_data_source", mediaItem.getLocalDataSource());
            }
            if (mediaItem.getFolder() != null) {
                contentValues.put("folder", mediaItem.getFolder());
            }
            String title = mediaItem.getTitle();
            if (StringUtils.m8346a(title)) {
                title = "<unknown>";
            }
            contentValues.put("title", title);
            contentValues.put("title_key", buildKey(title, z));
            String artist = mediaItem.getArtist();
            if (StringUtils.m8346a(artist)) {
                artist = "<unknown>";
            }
            contentValues.put("artist", artist);
            contentValues.put("artist_key", buildKey(artist, z));
            String album = mediaItem.getAlbum();
            if (StringUtils.m8346a(album)) {
                album = "<unknown>";
            }
            contentValues.put("album", album);
            contentValues.put("album_key", buildKey(album, z));
            String genre = mediaItem.getGenre();
            if (StringUtils.m8346a(genre)) {
                genre = "<unknown>";
            }
            contentValues.put("genre", genre);
            contentValues.put("genre_key", buildKey(genre, z));
            if (mediaItem.getLocalDataSource() != null) {
                contentValues.put(MediaStorage.MEDIA_ORDER_BY_FILE_NAME, buildKey(FileUtils.m8401k(mediaItem.getLocalDataSource()), z));
            }
            if (mediaItem.getComposer() != null) {
                contentValues.put( "composer", mediaItem.getComposer());
            }
            if (mediaItem.getComment() != null) {
                contentValues.put("comment", mediaItem.getComment());
            }
            if (mediaItem.getMimeType() != null) {
                contentValues.put("mime_type", mediaItem.getMimeType());
            }
            if (mediaItem.getDateAddedInMills() != null) {
                contentValues.put(MediaStorage.MEDIA_ORDER_BY_ADD_TIME, mediaItem.getDateAddedInMills());
            }
            if (mediaItem.getDateModifiedInMills() != null) {
                contentValues.put("modified_time", mediaItem.getDateModifiedInMills());
            }
            if (mediaItem.getDateLastAccessInMills() != null) {
                contentValues.put(MediaStorage.MEDIA_ORDER_BY_PLAY_TIME, mediaItem.getDateLastAccessInMills());
            }
            if (mediaItem.getGrade() != null) {
                contentValues.put("grade", mediaItem.getGrade());
            }
            if (mediaItem.getBitRate() != null) {
                contentValues.put("bit_rate", mediaItem.getBitRate());
            }
            if (mediaItem.getSampleRate() != null) {
                contentValues.put("sample_rate", mediaItem.getSampleRate());
            }
            if (mediaItem.getChannels() != null) {
                contentValues.put("channels", mediaItem.getChannels());
            }
            if (mediaItem.getTrack() != null) {
                contentValues.put("track", mediaItem.getTrack());
            }
            if (mediaItem.getYear() != null) {
                contentValues.put("year", mediaItem.getYear());
            }
            if (mediaItem.getStartTime() != null) {
                contentValues.put("start_time", mediaItem.getStartTime());
            }
            if (mediaItem.getDuration() != null) {
                contentValues.put("duration", mediaItem.getDuration());
            }
            if (mediaItem.getUseCount() != null) {
                contentValues.put("use_count", mediaItem.getUseCount());
            }
            if (mediaItem.getErrorStatus() != null) {
                contentValues.put("error_status", mediaItem.getErrorStatus());
            }
            if (mediaItem.getExtra() != null) {
                contentValues.put("extra", mediaItem.getExtra());
            }
            return contentValues;
        }

        private static String buildKey(String str, boolean z) {
            return (!z || str == null || str.equals("<unknown>")) ? "" : PinyinUtils.buildKey(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0147  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0161  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static MediaItem buildMediaItem(Cursor cursor, String str) {
            int i = 0;
            String str2 = null;
            String string = cursor.getString(2);
            int i2 = cursor.getInt(10);
            if (!StringUtils.m8346a(string)) {
                try {
                    int indexOf = string.indexOf(124);
                    if (indexOf > 0) {
                        String[] split = string.substring(indexOf + 1).split("-");
                        if (split.length == 2) {
                            string = string.substring(0, indexOf);
                        }
                        i2 = Integer.valueOf(split[0]).intValue();
                        if (i2 == 0) {
                            i2 = 1;
                        }
                    }
                    i = i2;
                    str2 = string;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new MediaItem(cursor.getString(0), Long.valueOf(cursor.getLong(1)), str2
                        , cursor.getString(3), cursor.getString(4), cursor.getString(5)
                        , cursor.getString(6), cursor.getString(7), cursor.getString(8)
                        , cursor.getString(9), Integer.valueOf(i), Integer.valueOf(cursor.getInt(11)), Integer.valueOf(cursor.getInt(12)), Integer.valueOf(cursor.getInt(13)), Integer.valueOf(cursor.getInt(14)), Integer.valueOf(cursor.getInt(15)), Integer.valueOf(cursor.getInt(16)), Integer.valueOf(cursor.getInt(17)), cursor.getString(18), Integer.valueOf(cursor.getInt(19)), Integer.valueOf(cursor.getInt(20)), Long.valueOf(cursor.getLong(21)), Long.valueOf(cursor.getLong(22)), Long.valueOf(cursor.getLong(23)), cursor.getInt(24) <= 0, cursor.getString(25), str);
            }
            i = i2;
            str2 = string;
            return new MediaItem(cursor.getString(0), Long.valueOf(cursor.getLong(1)), str2, cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), Integer.valueOf(i), Integer.valueOf(cursor.getInt(11)), Integer.valueOf(cursor.getInt(12)), Integer.valueOf(cursor.getInt(13)), Integer.valueOf(cursor.getInt(14)), Integer.valueOf(cursor.getInt(15)), Integer.valueOf(cursor.getInt(16)), Integer.valueOf(cursor.getInt(17)), cursor.getString(18), Integer.valueOf(cursor.getInt(19)), Integer.valueOf(cursor.getInt(20)), Long.valueOf(cursor.getLong(21)), Long.valueOf(cursor.getLong(22)), Long.valueOf(cursor.getLong(23)), cursor.getInt(24) <= 0, cursor.getString(25), str);
        }

        static List<MediaItem> buildMediaItemList(Cursor cursor, String str) {
            ArrayList arrayList = new ArrayList();
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(buildMediaItem(cursor, str));
                }
                cursor.close();
            }
            return arrayList;
        }

        static AsyncLoadMediaItemList buildAsyncMediaItemList(Cursor cursor, String str, String str2) {
            return new AsyncLoadMediaItemList(cursor, str, str2);
        }

        static List<GroupItem> buildGroupItemList(Cursor cursor) {
            ArrayList arrayList = new ArrayList();
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(new GroupItem(cursor.getString(0), cursor.getString(1), Integer.valueOf(cursor.getInt(2))));
                }
                cursor.close();
            }
            return arrayList;
        }
    }
}
