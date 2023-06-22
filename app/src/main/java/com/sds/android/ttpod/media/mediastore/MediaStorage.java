package com.sds.android.ttpod.media.mediastore;

import android.content.Context;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;

import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public final class MediaStorage {
    static final String DESC_SUFFIX = " DESC";
    static final String EXCHANGE_KEY_1 = "exchange_key_1";
    static final String EXCHANGE_KEY_2 = "exchange_key_2";
    public static final String GROUP_ID_ALBUM_PREFIX = "group_id_album";
    public static final String GROUP_ID_ALL_LOCAL = "group_id_customall_local";
    public static final String GROUP_ID_ARTIST_PREFIX = "group_id_artist";
    public static final String GROUP_ID_CUSTOM_PREFIX = "group_id_custom";
    public static final String GROUP_ID_DOWNLOAD = "group_id_custom_downloaded_song";
    public static final String GROUP_ID_EFFECT_LOCAL = "group_id_customeffect_local";
    public static final String GROUP_ID_EFFECT_ONLINE = "group_id_customeffect_online";
    public static final String GROUP_ID_FAV = "group_id_fav";
    public static final String GROUP_ID_FAV_LOCAL = "group_id_customfav_local";
    public static final String GROUP_ID_FOLDER_PREFIX = "group_id_folder";
    public static final String GROUP_ID_GENRE_PREFIX = "group_id_genre";
    static final String GROUP_ID_KEY = "group_id_key";
    public static final String GROUP_ID_KTV = "group_id_customktv";
    public static final String GROUP_ID_MUSICCIRCLE_PREFIX = "group_id_custom_music_circle_";
    public static final String GROUP_ID_ONLINE_FAV_PREFIX = "group_id_customonline_fav";
    public static final String GROUP_ID_ONLINE_TEMPORARY = "group_id_temporaryonline_temporary";
    private static final String GROUP_ID_PREFIX = "group_id_";
    public static final String GROUP_ID_RECENTLY_ADD = "group_id_customrecently_add";
    public static final String GROUP_ID_RECENTLY_ADD_OLD = "group_id_recently_add";
    public static final String GROUP_ID_RECENTLY_PLAY = "group_id_customrecently_play";
    public static final String GROUP_ID_RECENTLY_PLAY_OLD = "group_id_recently_play";
    public static final String GROUP_ID_TEMPORARY_PREFIX = "group_id_temporary";
    static final String GROUP_NAME_ALL_LOCAL = "all_local";
    public static final String GROUP_NAME_DOWNLOADED_SONG = "downloaded_song";
    public static final String GROUP_NAME_EFFECT_LOCAL = "effect_local";
    public static final String GROUP_NAME_EFFECT_ONLINE = "effect_online";
    static final String GROUP_NAME_FAV = "fav";
    static final String GROUP_NAME_FAV_LOCAL = "fav_local";
    public static final String GROUP_NAME_FAV_ONLINE = "fav_online";
    static final String GROUP_NAME_KTV = "ktv";
    static final String GROUP_NAME_ONLINE_TEMPORARY = "online_temporary";
    static final String GROUP_NAME_RECENTLY_ADD = "recently_add";
    static final String GROUP_NAME_RECENTLY_ADD_OLD = "recently_add";
    static final String GROUP_NAME_RECENTLY_PLAY = "recently_play";
    static final String GROUP_NAME_RECENTLY_PLAY_OLD = "recently_play";
    public static final int MAX_RECENT_PLAY_COUNT = 100;
    public static final String MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME = "group_map_added_time";
    public static final String MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC = "group_map_added_time DESC";
    public static final String MEDIA_ORDER_BY_ADD_TIME = "added_time";
    public static final String MEDIA_ORDER_BY_ADD_TIME_DESC = "added_time DESC";
    public static final String MEDIA_ORDER_BY_ALBUM = "album_key";
    public static final String MEDIA_ORDER_BY_ALBUM_DESC = "album_key DESC";
    public static final String MEDIA_ORDER_BY_AMOUNT = "amount";
    public static final String MEDIA_ORDER_BY_ARTIST = "artist_key";
    public static final String MEDIA_ORDER_BY_ARTIST_DESC = "artist_key DESC";
    public static final String MEDIA_ORDER_BY_CUSTOM = "sort_order";
    public static final String MEDIA_ORDER_BY_CUSTOM_DESC = "sort_order DESC";
    public static final String MEDIA_ORDER_BY_FILE_NAME = "file_name_key";
    public static final String MEDIA_ORDER_BY_FILE_NAME_DESC = "file_name_key DESC";
    public static final String MEDIA_ORDER_BY_GENRE = "genre_key";
    public static final String MEDIA_ORDER_BY_GENRE_DESC = "genre_key DESC";
    public static final String MEDIA_ORDER_BY_PLAY_TIME = "last_play_time";
    public static final String MEDIA_ORDER_BY_PLAY_TIME_DESC = "last_play_time DESC";
    public static final String MEDIA_ORDER_BY_TITLE = "title_key";
    public static final String MEDIA_ORDER_BY_TITLE_DESC = "title_key DESC";
    public static final String MEDIA_ORDER_BY_TRACK = "track";
    private static final String TAG = "MediaStorage";
    public static final String UNKNOWN = "<unknown>";

    public static String buildGroupID() {
        return GROUP_ID_CUSTOM_PREFIX + (System.currentTimeMillis() & System.nanoTime());
    }

    public static String buildOnlineFavGroupID() {
        if (EnvironmentUtils.C0602a.m8502i() && EnvironmentUtils.C0603b.m8486g() <= 0) {
            throw new IllegalStateException("you must login first");
        }
        return GROUP_ID_ONLINE_FAV_PREFIX + EnvironmentUtils.C0603b.m8486g();
    }

    public static String buildMusicCircleFavGroupID(long j) {
        return buildMusicCircleFavGroupIDPrefix() + j;
    }

    public static String buildMusicCircleFavGroupIDPrefix() {
        return GROUP_ID_MUSICCIRCLE_PREFIX + EnvironmentUtils.C0603b.m8486g() + "_";
    }

    public static void insertGroup(Context context, String str, String str2, GroupType groupType) {
        DebugUtils.m8426a(str, "groupName");
        DebugUtils.m8426a(str2, "groupID");
        DebugUtils.m8426a(groupType, "groupType");
        assertGroupIDValid(str2);
        assertCustomGroupType(groupType);
        MediaStorageImp.insertGroup(context, groupType, str, str2);
    }

    public static void insertKtvGroup(Context context, String str) {
        MediaStorageImp.insertGroup(context, GroupType.CUSTOM_LOCAL, str, GROUP_ID_KTV);
    }

    public static void insertMediaItem(Context context, String str, MediaItem mediaItem) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8426a(mediaItem, "mediaItem");
        assertInsertAble(str);
        MediaStorageImp.insertMediaItem(context, str, mediaItem);
    }

    public static void insertMediaItems(Context context, String str, Collection<MediaItem> collection) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8426a((Object) collection, "mediaItems");
        assertInsertAble(str);
        DebugUtils.m8424a((Collection) collection, "mediaItems");
        MediaStorageImp.insertMediaItems(context, str, collection);
    }

    public static void deleteGroup(Context context, String str) {
        DebugUtils.m8426a(str, "groupID");
        assertNotDefault(str);
        MediaStorageImp.deleteGroup(context, str);
    }

    public static void clearGroup(Context context, String str) {
        DebugUtils.m8426a(str, "groupID");
        assertClearAble(str);
        MediaStorageImp.clearGroup(context, str);
    }

    public static void deleteMediaItem(Context context, String str, String str2) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8426a(str2, "mediaID");
        assertDeleteAble(str);
        if (!str.startsWith(GROUP_ID_CUSTOM_PREFIX)) {
            str = GROUP_ID_ALL_LOCAL;
        }
        MediaStorageImp.deleteMediaItem(context, str, str2);
    }

    public static void deleteMediaItemList(Context context, String str, Collection<String> collection) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8424a((Collection) collection, "mediaIDs");
        assertDeleteAble(str);
        if (!str.startsWith(GROUP_ID_CUSTOM_PREFIX)) {
            str = GROUP_ID_ALL_LOCAL;
        }
        MediaStorageImp.deleteMediaItems(context, str, collection);
    }

    public static void updateGroupItem(Context context, GroupItem groupItem) {
        DebugUtils.m8426a(groupItem, "groupItem");
        DebugUtils.m8426a(groupItem.getGroupID(), "groupID");
        assertNotDefault(groupItem.getGroupID());
        MediaStorageImp.updateGroupItem(context, groupItem);
    }

    public static void updateMediaItem(Context context, MediaItem mediaItem) {
        DebugUtils.m8426a(mediaItem, "mediaItem");
        DebugUtils.m8426a(mediaItem.getID(), "mediaID");
        DebugUtils.m8426a(mediaItem.getGroupID(), "groupID");
        MediaStorageImp.updateMediaItem(context, mediaItem);
    }

    public static void exchangeMediaItemOrder(Context context, String str, List<ExchangeOrderEntity> list) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8424a((Collection) list, "exchangeOrderEntities");
        assertExchangeOrderAble(str);
        assertExchangeOrderValid(str, list);
        MediaStorageImp.exchangeSortOrder(context, str, list);
    }

    public static List<GroupItem> queryGroupItems(Context context, GroupType groupType) {
        DebugUtils.m8426a(groupType, "groupType");
        return MediaStorageImp.queryGroupItems(context, groupType);
    }

    public static GroupItem queryGroupItem(Context context, String str) {
        GroupType groupType = null;
        DebugUtils.m8426a(str, "groupID");
        if (str.startsWith(GROUP_ID_CUSTOM_PREFIX)) {
            groupType = GroupType.CUSTOM_ALL;
        } else if (str.startsWith(GROUP_ID_ALBUM_PREFIX)) {
            groupType = GroupType.DEFAULT_ALBUM;
        } else if (str.startsWith(GROUP_ID_ARTIST_PREFIX)) {
            groupType = GroupType.DEFAULT_ARTIST;
        } else if (str.startsWith(GROUP_ID_GENRE_PREFIX)) {
            groupType = GroupType.DEFAULT_GENRE;
        } else if (str.startsWith(GROUP_ID_FOLDER_PREFIX)) {
            groupType = GroupType.DEFAULT_FOLDER;
        } else if (str.equals(GROUP_ID_RECENTLY_ADD_OLD)) {
            return new GroupItem("recently_add", str, Integer.valueOf(MediaStorageImp.queryMediaIDs(context, str, null).size()));
        } else {
            if (str.equals(GROUP_ID_RECENTLY_PLAY_OLD)) {
                return new GroupItem("recently_play", str, Integer.valueOf(MediaStorageImp.queryMediaIDs(context, str, null).size()));
            }
            if (str.equals(GROUP_ID_ONLINE_TEMPORARY)) {
                return new GroupItem(GROUP_NAME_ONLINE_TEMPORARY, str, Integer.valueOf(MediaStorageImp.queryMediaIDs(context, str, null).size()));
            }
        }
        return MediaStorageImp.queryGroupItem(context, groupType, str);
    }

    public static MediaItem queryMediaItem(Context context, String str, String str2) {
        return MediaStorageImp.queryMediaItem(context, str, str2);
    }

    public static MediaItem queryMediaItemBySongID(Context context, String str, Long l) {
        return MediaStorageImp.queryMediaItemBySongID(context, str, l);
    }

    public static List<String> queryMediaIDs(Context context, String str, String str2) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8426a(str2, "orderBy");
        assertOrderBy(str2);
        return MediaStorageImp.queryMediaIDs(context, str, str2);
    }

    public static int queryMediaCount(Context context, String str, String str2) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8426a(str2, "orderBy");
        assertOrderBy(str2);
        return MediaStorageImp.queryMediaCount(context, str, str2);
    }

    public static List<String> queryMediaIDsUnderFolder(Context context, String str, String str2) {
        DebugUtils.m8426a(str, "folder");
        DebugUtils.m8426a(str2, "orderBy");
        assertOrderBy(str2);
        return MediaStorageImp.queryMediaIDsUnderFolder(context, str, str2);
    }

    public static List<MediaItem> queryMediaItemList(Context context, String str, String str2) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8426a(str2, "orderBy");
        assertOrderBy(str2);
        return MediaStorageImp.queryMediaItemList(context, str, str2);
    }

    public static AsyncLoadMediaItemList queryAsyncLoadMediaItemList(Context context, String str, String str2) {
        DebugUtils.m8426a(str, "groupID");
        DebugUtils.m8426a(str2, "orderBy");
        assertOrderBy(str2);
        return MediaStorageImp.queryAsyncLoadMediaItemList(context, str, str2);
    }

    public static boolean isGroupExisted(Context context, String str) {
        DebugUtils.m8426a(str, "groupID");
        if (str.equals(GROUP_ID_ALL_LOCAL) || str.equals(GROUP_ID_RECENTLY_PLAY_OLD) || str.equals(GROUP_ID_RECENTLY_ADD_OLD) || str.equals(GROUP_ID_FAV_LOCAL) || str.equals(GROUP_ID_FAV) || str.equals(GROUP_ID_ONLINE_TEMPORARY) || str.startsWith(GROUP_ID_ARTIST_PREFIX) || str.startsWith(GROUP_ID_FOLDER_PREFIX) || str.startsWith(GROUP_ID_ALBUM_PREFIX) || str.startsWith(GROUP_ID_GENRE_PREFIX)) {
            return true;
        }
        return MediaStorageImp.isGroupExisted(context, str);
    }

    public static Long getPostIdByGroupId(String str) {
        if (str.startsWith(GROUP_ID_MUSICCIRCLE_PREFIX)) {
            return Long.valueOf(str.substring(str.lastIndexOf(95) + 1));
        }
        throw new IllegalArgumentException(str + "is not a music-circle groupId");
    }

    private static void assertOrderBy(String str) {
        if (EnvironmentUtils.C0602a.m8502i() && !str.equals("title_key") && str.equals("artist_key") && str.equals(MEDIA_ORDER_BY_ARTIST_DESC) && str.equals("genre_key") && str.equals(MEDIA_ORDER_BY_GENRE_DESC) && str.equals("album_key") && str.equals(MEDIA_ORDER_BY_ALBUM_DESC) && str.equals(MEDIA_ORDER_BY_ADD_TIME) && str.equals(MEDIA_ORDER_BY_ADD_TIME_DESC) && str.equals(MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME) && str.equals(MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC) && str.equals(MEDIA_ORDER_BY_PLAY_TIME) && str.equals(MEDIA_ORDER_BY_PLAY_TIME_DESC) && str.equals(MEDIA_ORDER_BY_FILE_NAME) && str.equals(MEDIA_ORDER_BY_FILE_NAME_DESC) && str.equals("track") && str.equals(MEDIA_ORDER_BY_CUSTOM) && str.equals(MEDIA_ORDER_BY_CUSTOM_DESC)) {
            throw new IllegalArgumentException("orderBy must be MediaStorage.MEDIA_ORDER_BY_TITLE,MEDIA_ORDER_BY_ARTIST,MEDIA_ORDER_BY_ARTIST_DESC,MEDIA_ORDER_BY_GENRE,MEDIA_ORDER_BY_GENRE_DESC,MEDIA_ORDER_BY_ALBUM,MEDIA_ORDER_BY_ALBUM_DESC,MEDIA_ORDER_BY_ADD_TIME,MEDIA_ORDER_BY_ADD_TIME_DESC,MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME,MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC,MEDIA_ORDER_BY_PLAY_TIME,MEDIA_ORDER_BY_PLAY_TIME_DESC,MEDIA_ORDER_BY_FILE_NAME,MEDIA_ORDER_BY_FILE_NAME_DESC,MEDIA_ORDER_BY_TRACK,MEDIA_ORDER_BY_CUSTOM,MEDIA_ORDER_BY_CUSTOM_DESC");
        }
    }

    private void assertOrderBy(String str, String str2) {
        if (EnvironmentUtils.C0602a.m8502i()) {
            if (str.equals(GROUP_ID_ONLINE_TEMPORARY)) {
                LogUtils.m8384b(TAG, "groupID:" + str + " will ignore orderBy " + str2);
            } else if (!str.startsWith(GROUP_ID_CUSTOM_PREFIX)) {
                if (str2.equals(MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME) || str2.equals(MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC) || str2.equals(MEDIA_ORDER_BY_CUSTOM) || str2.equals(MEDIA_ORDER_BY_CUSTOM_DESC)) {
                    throw new UnsupportedOperationException("groupID:" + str + " not support orderBy" + str2);
                }
            }
        }
    }

    private static void assertSearchAble(GroupType groupType) {
        if (EnvironmentUtils.C0602a.m8502i() && groupType != GroupType.DEFAULT_ALBUM && groupType != GroupType.DEFAULT_FOLDER && groupType != GroupType.DEFAULT_GENRE && groupType != GroupType.DEFAULT_ARTIST) {
            throw new IllegalArgumentException("groupType must be DEFAULT_ALBUM DEFAULT_FOLDER DEFAULT_GENRE DEFAULT_ARTIST!");
        }
    }

    private static void assertMediaItemSearchAble(String str) {
        if (EnvironmentUtils.C0602a.m8502i() && str.equals(GROUP_ID_ONLINE_TEMPORARY)) {
            throw new IllegalArgumentException("groupID must not be group_id_temporaryonline_temporary");
        }
    }

    private static void assertCustomGroupType(GroupType groupType) {
        if (EnvironmentUtils.C0602a.m8502i() && groupType != GroupType.CUSTOM_LOCAL && groupType != GroupType.CUSTOM_ONLINE && groupType != GroupType.CUSTOM_MIX) {
            throw new IllegalArgumentException("groupType must be CUSTOM_LOCAL CUSTOM_ONLINE CUSTOM_MIX!");
        }
    }

    private static void assertDeleteAble(String str) {
        if (EnvironmentUtils.C0602a.m8502i() && str.equals(GROUP_ID_ONLINE_TEMPORARY)) {
            throw new IllegalArgumentException("groupID must not be group_id_temporaryonline_temporary");
        }
    }

    private static void assertExchangeOrderAble(String str) {
        if (EnvironmentUtils.C0602a.m8502i() && !str.startsWith(GROUP_ID_CUSTOM_PREFIX)) {
            throw new IllegalArgumentException("groupID must be startWith group_id_custom");
        }
    }

    private static void assertInsertAble(String str) {
        if (EnvironmentUtils.C0602a.m8502i() && !str.startsWith(GROUP_ID_CUSTOM_PREFIX) && !str.equals(GROUP_ID_ONLINE_TEMPORARY)) {
            throw new IllegalArgumentException("groupID must be startWith group_id_custom or equal group_id_temporaryonline_temporary");
        }
    }

    private static void assertNotDefault(String str) {
        if (EnvironmentUtils.C0602a.m8502i()) {
            if (str.equals(GROUP_ID_ALL_LOCAL) || str.equals(GROUP_ID_FAV_LOCAL) || str.equals(GROUP_ID_RECENTLY_ADD_OLD) || str.equals(GROUP_ID_RECENTLY_PLAY_OLD) || str.startsWith(GROUP_ID_ALBUM_PREFIX) || str.startsWith(GROUP_ID_ARTIST_PREFIX) || str.startsWith(GROUP_ID_FOLDER_PREFIX) || str.startsWith(GROUP_ID_GENRE_PREFIX) || str.equals(GROUP_ID_ONLINE_TEMPORARY)) {
                throw new IllegalArgumentException("group withID " + str + " can not be deleted or modified!");
            }
        }
    }

    private static void assertClearAble(String str) {
        if (EnvironmentUtils.C0602a.m8502i() && !str.startsWith(GROUP_ID_CUSTOM_PREFIX) && !str.equals(GROUP_ID_ONLINE_TEMPORARY)) {
            throw new IllegalArgumentException("groupID must startwithgroup_id_custom Or equal group_id_temporaryonline_temporary");
        }
    }

    private static void assertMediaItemEditAble(MediaItem mediaItem) {
        if (EnvironmentUtils.C0602a.m8502i() && mediaItem.getGroupID().equals(GROUP_ID_ONLINE_TEMPORARY)) {
            throw new UnsupportedOperationException("MediaItem with gorupID = GROUP_ID_ONLINE_TEMPORARY, update not supported!");
        }
    }

    private static void assertGroupIDValid(String str) {
        if (EnvironmentUtils.C0602a.m8502i() && !str.startsWith(GROUP_ID_PREFIX)) {
            throw new IllegalArgumentException("groupID must startwith gorupID group_id_!");
        }
    }

    private static void assertExchangeOrderValid(String str, List<ExchangeOrderEntity> list) {
        if (EnvironmentUtils.C0602a.m8502i()) {
            for (ExchangeOrderEntity exchangeOrderEntity : list) {
                if (!exchangeOrderEntity.getGroupID().equals(str)) {
                    throw new IllegalStateException("groupID is not identical");
                }
            }
        }
    }
}
