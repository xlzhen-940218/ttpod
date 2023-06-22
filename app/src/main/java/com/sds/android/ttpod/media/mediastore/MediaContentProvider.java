package com.sds.android.ttpod.media.mediastore;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;


/* loaded from: classes.dex */
public final class MediaContentProvider extends ContentProvider {
    private static final String TAG = "MediaContentProvider";
    private MediaDBHelper mMediaDBHelper;
    private MediaTemporaryStore mMediaTemporaryStore;
    private static final String AUTHORITY = MediaContentProvider.class.getName();
    private static final String AUTHORITY_HOST = "content://" + AUTHORITY + "/";
    private static final String MEDIA = "media/";
    static final String MEDIA_ACCESS = AUTHORITY_HOST + MEDIA;
    private static final String GROUP = "group/";
    static final String GROUP_ACCESS = AUTHORITY_HOST + GROUP;
    private static final String CLEAR = "clear/";
    static final String CLEAR_ACCESS = AUTHORITY_HOST + CLEAR;
    private static final String SORT = "sort/";
    static final String SORT_ACCESS = AUTHORITY_HOST + SORT;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mMediaDBHelper = new MediaDBHelper(getContext());
        this.mMediaTemporaryStore = new MediaTemporaryStore(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        String uri2 = uri.toString();
        if (uri2.startsWith(GROUP_ACCESS)) {
            throw new IllegalAccessError("can't bulkInsert GroupItem");
        }
        try {
            if (uri2.startsWith(MEDIA_ACCESS)) {
                String substring = uri2.substring(MEDIA_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(substring)) {
                    this.mMediaTemporaryStore.insertMediaItems(substring, contentValuesArr);
                } else {
                    this.mMediaDBHelper.insertMediaItemsToGroup(uri2.substring(MEDIA_ACCESS.length()), contentValuesArr);
                }
            } else if (uri2.startsWith(SORT_ACCESS)) {
                this.mMediaDBHelper.exchangeSortOrder(uri2.substring(SORT_ACCESS.length()), contentValuesArr);
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        String uri2 = uri.toString();
        try {
            if (uri2.startsWith(GROUP_ACCESS)) {
                insertGroup(uri2.substring(GROUP_ACCESS.length()), contentValues != null ? contentValues.getAsString("group_id_key") : null);
            } else if (uri2.startsWith(MEDIA_ACCESS)) {
                String substring = uri2.substring(MEDIA_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(substring)) {
                    this.mMediaTemporaryStore.insertMediaItem(substring, contentValues);
                } else {
                    this.mMediaDBHelper.insertMediaItemToGroup(substring, contentValues);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    private void insertGroup(String str, String str2) {
        if (str.startsWith(GroupType.CUSTOM_LOCAL.name())) {
            this.mMediaDBHelper.insertLocalCustomGroup(null, str.substring(GroupType.CUSTOM_LOCAL.name().length()), str2);
        } else if (str.startsWith(GroupType.CUSTOM_ONLINE.name())) {
            this.mMediaDBHelper.insertOnlineCustomGroup(null, str.substring(GroupType.CUSTOM_ONLINE.name().length()), str2);
        } else if (str.startsWith(GroupType.CUSTOM_MIX.name())) {
            this.mMediaDBHelper.insertMixCustomGroup(null, str.substring(GroupType.CUSTOM_MIX.name().length()), str2);
        } else {
            throw new IllegalArgumentException("");
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        try {
            String uri2 = uri.toString();
            if (uri2.startsWith(GROUP_ACCESS)) {
                String substring = uri2.substring(GROUP_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(substring)) {
                    this.mMediaTemporaryStore.deleteGroup(substring);
                } else {
                    this.mMediaDBHelper.deleteGroup(uri2.substring(GROUP_ACCESS.length()));
                }
            } else if (uri2.startsWith(MEDIA_ACCESS)) {
                this.mMediaDBHelper.deleteMediaItems(uri2.substring(MEDIA_ACCESS.length()), str);
            } else if (uri2.startsWith(CLEAR_ACCESS)) {
                String substring2 = uri2.substring(GROUP_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(substring2)) {
                    this.mMediaTemporaryStore.clearGroup(substring2);
                } else {
                    this.mMediaDBHelper.clearGroup(substring2);
                }
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        try {
            String uri2 = uri.toString();
            if (uri2.startsWith(GROUP_ACCESS)) {
                this.mMediaDBHelper.updateGroup(uri2.substring(GROUP_ACCESS.length()), contentValues);
            } else if (uri2.startsWith(MEDIA_ACCESS)) {
                String substring = uri2.substring(MEDIA_ACCESS.length());
                if (this.mMediaTemporaryStore.isTemporaryStore(substring)) {
                    this.mMediaTemporaryStore.updateMediaItem(substring, contentValues);
                } else {
                    this.mMediaDBHelper.updateMediaItem(substring, contentValues, str);
                }
            }
            return 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0047 -> B:5:0x001a). Please submit an issue!!! */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String uri2 = null;
        try {
            uri2 = uri.toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (uri2.startsWith(GROUP_ACCESS)) {
            return queryGroup(uri2.substring(GROUP_ACCESS.length()), str);
        }
        if (uri2.startsWith(MEDIA_ACCESS)) {
            String substring = uri2.substring(MEDIA_ACCESS.length());
            if (this.mMediaTemporaryStore.isTemporaryStore(substring)) {
                return this.mMediaTemporaryStore.queryMediaItem(substring, str);
            }
            return this.mMediaDBHelper.queryMediaItem(substring, str, str2);
        }
        return null;
    }

    private Cursor queryGroup(String str, String str2) {
        switch (GroupType.valueOf(str)) {
            case DEFAULT_ARTIST:
                return this.mMediaDBHelper.queryArtistGroup(str2);
            case DEFAULT_ALBUM:
                return this.mMediaDBHelper.queryAlbumGroup(str2);
            case DEFAULT_GENRE:
                return this.mMediaDBHelper.queryGenreGroup(str2);
            case DEFAULT_FOLDER:
                return this.mMediaDBHelper.queryFolderGroup(str2);
            case CUSTOM_LOCAL:
                return this.mMediaDBHelper.queryCustomLocalGroup();
            case CUSTOM_ONLINE:
                return this.mMediaDBHelper.queryCustomOnlineGroup();
            case CUSTOM_MIX:
                return this.mMediaDBHelper.queryCustomMixGroup();
            case CUSTOM_ALL:
                return this.mMediaDBHelper.queryCustomAllGroup();
            default:
                throw new IllegalArgumentException("invalid type");
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }
}
