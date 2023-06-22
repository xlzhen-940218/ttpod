package com.sds.android.ttpod.media.mediastore.old;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import java.util.Locale;

@Deprecated
/* loaded from: classes.dex */
public final class MediaStoreOld {
    public static final String AUTHORITY = "ttpod";
    public static final String CONTENT_AUTHORITY_SLASH = "content://ttpod/";
    public static final String PATH_LYRIC = "lyric_path";
    public static final String PATH_MEDIA_SCANNER = "mediascanner";
    public static final String PATH_PICTURE = "picture_path";
    public static final String PATH_PICTURE2 = "picture_path2";
    public static final String PATH_PICTURE3 = "picture_path3";
    public static final String PATH_PICTURE4 = "picture_path4";
    public static final String PATH_PICTURE5 = "picture_path5";
    public static final String PATH_PLAYING_MEDIA = "playingmedia";
    public static final String PATH_PLAY_MODE = "playmode";
    public static final String PATH_PLAY_STATE = "playstate";
    public static final String UNKNOWN = "<unknown>";
    public static final Uri CONTENT_URI_PLAYING_MEDIA = Uri.parse("content://ttpod/playingmedia");
    public static final Uri CONTENT_URI_PLAY_STATE = Uri.parse("content://ttpod/playstate");
    public static final Uri CONTENT_URI_PLAY_MODE = Uri.parse("content://ttpod/playmode");
    public static final Uri CONTENT_URI_LYRIC_PATH = Uri.parse("content://ttpod/lyric_path");
    public static final Uri CONTENT_URI_PICTURE_PATH = Uri.parse("content://ttpod/picture_path");
    public static final Uri CONTENT_URI_MEDIA_SCANNER = Uri.parse("content://ttpod/mediascanner");

    /* loaded from: classes.dex */
    public interface AlbumColumns extends BaseMetaColumns {
        public static final String ALBUM = "album";
        public static final String ALBUM_ART = "album_art";
        public static final String ALBUM_ID = "album_id";
        public static final String ALBUM_KEY = "album_key";
        public static final String ARTIST = "artist";
        public static final String FIRST_YEAR = "minyear";
        public static final String LAST_YEAR = "maxyear";
        public static final String NUMBER_OF_SONGS_FOR_ARTIST = "numsongs_by_artist";
    }

    /* loaded from: classes.dex */
    public interface ArtistColumns extends BaseMetaColumns {
        public static final String ARTIST = "artist";
        public static final String ARTIST_DIGITAL_KEY = "artist_digital_key";
        public static final String ARTIST_KEY = "artist_key";
        public static final String NUMBER_OF_ALBUMS = "number_of_albums";
    }

    /* loaded from: classes.dex */
    public interface BaseMetaColumns extends BaseColumns {
        public static final String NUMBER_OF_TRACKS = "number_of_tracks";
    }

    /* loaded from: classes.dex */
    public interface FolderColumns extends BaseMetaColumns {
        public static final String FOLDER = "_folder";
    }

    /* loaded from: classes.dex */
    public interface GenresColumns extends BaseMetaColumns {
        public static final String GENRE = "genre";
        public static final String GENRE_KEY = "genre_key";
    }

    /* loaded from: classes.dex */
    public interface MediaLinkColumns extends BaseColumns {
        public static final String IMAGE_ARTIST_PATH = "image_artist_path";
        public static final String IMAGE_SEARCH_TIME = "image_searchtime";
        public static final String LYRIC_PATH = "lyric_path";
        public static final String LYRIC_SEARCH_TIME = "lyric_searchtime";
    }

    /* loaded from: classes.dex */
    public interface MediasColumns extends BaseColumns {
        public static final String ALBUM = "album";
        public static final String ALBUM_ID = "album_id";
        public static final String ARTIST = "artist";
        public static final String DATA = "_data";
        public static final String DATE_ADDED = "date_added";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String FOLDER = "_folder";
        public static final String GENRE = "genre";
        public static final int ID_COL_ALBUM = 19;
        public static final int ID_COL_ALBUM_ID = 2;
        public static final int ID_COL_ARTIST = 18;
        public static final int ID_COL_ARTIST_ID = 1;
        public static final int ID_COL_BITRATE = 7;
        public static final int ID_COL_BOOKMARK = 13;
        public static final int ID_COL_CHANNELS = 15;
        public static final int ID_COL_COMMENT = 22;
        public static final int ID_COL_COMPOSER = 21;
        public static final int ID_COL_DATA_ADDED = 5;
        public static final int ID_COL_DATE_MODIFIED = 4;
        public static final int ID_COL_DISPLAY_NAME = 25;
        public static final int ID_COL_DURATION = 11;
        public static final int ID_COL_GENRE = 20;
        public static final int ID_COL_GENRE_ID = 3;
        public static final int ID_COL_ID = 0;
        public static final int ID_COL_MEDIA_DATA = 16;
        public static final int ID_COL_MIME_TYPE = 23;
        public static final int ID_COL_PROTECT_STATUS = 14;
        public static final int ID_COL_RATING = 6;
        public static final int ID_COL_SAMPLERATE = 8;
        public static final int ID_COL_SONG_ID = 24;
        public static final int ID_COL_TITLE = 17;
        public static final int ID_COL_TRACK = 9;
        public static final int ID_COL_USE_COUNT = 12;
        public static final int ID_COL_YEAR = 10;
        public static final String ORDER = "_order";
        public static final String SIZE = "_size";
        public static final String TITLE = "title";
        public static final String TITLE_DIGITAL_KEY = "title_digital_key";
        public static final String TITLE_KEY = "title_key";
        public static final String TRACK = "track";
        public static final String ARTIST_ID = "artist_id";
        public static final String GENRE_ID = "genre_id";
        public static final String RATING = "rating";
        public static final String BITRATE = "audio_bitrate";
        public static final String SAMPLERATE = "sample_rate";
        public static final String YEAR = "year";
        public static final String DURATION = "duration";
        public static final String USECOUNT = "use_count";
        public static final String BOOKMARK = "bookmark";
        public static final String PROTECT_STATUS = "protect_status";
        public static final String CHANNELS = "channels";
        public static final String COMPOSER = "composer";
        public static final String COMMENT = "comment";
        public static final String MIME_TYPE = "mime_type";
        public static final String SONG_ID = "song_id";
        public static final String DISPLAY_NAME = "_display_name";
        public static final String[] CURSOR_COLS = {"_id", ARTIST_ID, "album_id", GENRE_ID, "date_modified", "date_added", RATING, BITRATE, SAMPLERATE, "track", YEAR, DURATION, USECOUNT, BOOKMARK, PROTECT_STATUS, CHANNELS, "_data", "title", "artist", "album", "genre", COMPOSER, COMMENT, MIME_TYPE, SONG_ID, DISPLAY_NAME};
    }

    /* loaded from: classes.dex */
    public interface PlaylistsColumns extends BaseMetaColumns {
        public static final String DATA = "_data";
        public static final String DATE_ADDED = "date_added";
        public static final String DATE_MODIFIED = "date_modified";
        public static final String NAME = "name";
        public static final String ORDER = "_order";
    }

    /* loaded from: classes.dex */
    public static final class Medias implements MediasColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.media";
        public static final String DEFAULT_SORT_ORDER = getSortKey(0, false);
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.media";
        public static final String FAVORITES_URI_PATH = "favorites";
        public static final String ONLINE_FAVORITES_URI_PATH = "online_favorites";
        public static final int SORT_ORDER_INDEX_ALBUM = 2;
        public static final int SORT_ORDER_INDEX_ARTIST = 1;
        public static final int SORT_ORDER_INDEX_DATE_ADDED = 5;
        public static final int SORT_ORDER_INDEX_DATE_MODIFIED = 6;
        public static final int SORT_ORDER_INDEX_FILE_NAME = 4;
        public static final int SORT_ORDER_INDEX_GENRE = 3;
        public static final int SORT_ORDER_INDEX_RECENT_PLAYED = 7;
        public static final int SORT_ORDER_INDEX_TITLE = 0;
        public static final String URI_PATH = "media";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/media");
        }

        public static Uri getFavoritesUri() {
            return Uri.parse("content://ttpod/favorites");
        }

        public static Uri getOnlineFavoritesUri() {
            return Uri.parse("content://ttpod/online_favorites");
        }

        public static int getSortKeyIndex(String str) {
            if ("title_key".equals(str)) {
                return 0;
            }
            if ("artist_key".equals(str)) {
                return 1;
            }
            if ("album_key".equals(str)) {
                return 2;
            }
            if ("genre_key".equals(str)) {
                return 3;
            }
            if (MediasColumns.DISPLAY_NAME.equals(str)) {
                return 4;
            }
            if ("date_added".equals(str)) {
                return 5;
            }
            if ("date_modified".equals(str)) {
                return 6;
            }
            if (!MediasColumns.BOOKMARK.equals(str)) {
                return -1;
            }
            return 7;
        }

        public static String getSortKey(int i, boolean z) {
            String str = z ? " desc" : "";
            StringBuilder sb = new StringBuilder();
            switch (i) {
                case 0:
                    sb.append("title_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("album_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("track");
                    break;
                case 1:
                    sb.append("artist_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("album_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("track");
                    break;
                case 2:
                    sb.append("album_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("track");
                    break;
                case 3:
                    sb.append("genre_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("artist_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("album_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("track");
                    break;
                case 4:
                    sb.append(MediasColumns.DISPLAY_NAME);
                    sb.append(str);
                    sb.append(',');
                    sb.append("album_key");
                    sb.append(str);
                    sb.append(',');
                    sb.append("track");
                    break;
                case 5:
                    sb.append("date_added");
                    break;
                case 6:
                    sb.append("date_modified");
                    break;
                case 7:
                    sb.append(MediasColumns.BOOKMARK);
                    break;
            }
            sb.append(str);
            sb.append(',');
            sb.append("_id");
            sb.append(str);
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static final class StreamMedias implements MediasColumns {
        private static final String URI_PATH = "StreamMedias";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/StreamMedias");
        }
    }

    /* loaded from: classes.dex */
    public static final class UpdateOnlinePlayingList {
        public static final String COLUMN_TAG = "_tag";
        public static final String COLUMN_TYPE = "_type";
        public static final String SELECTION_BODY = "_body";
        public static final String SELECTION_HEAD = "_head";
        private static final String URL_PATH = "update_online_playing_list";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/update_online_playing_list");
        }
    }

    /* loaded from: classes.dex */
    public static final class StreamRadio implements MediasColumns {
        private static final String URI_PATH = "streamRadio";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/streamRadio");
        }
    }

    /* loaded from: classes.dex */
    public static final class Genres implements GenresColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.genre";
        public static final String DEFAULT_SORT_ORDER = "genre_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.genre";
        public static final String URI_PATH = "genres";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/genres");
        }
    }

    /* loaded from: classes.dex */
    public static final class Playlists implements PlaylistsColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.playlist";
        public static final String DEFAULT_SORT_ORDER = "name";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.playlist";
        public static final String URI_PATH = "playlists";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/playlists");
        }

        /* loaded from: classes.dex */
        public static final class Members implements MediasColumns {
            public static final String CONTENT_DIRECTORY = "members";
            public static final String DEFAULT_SORT_ORDER = "play_order,title_key";
            public static final String MEDIA_ID = "media_id";
            public static final String PLAYLIST_ID = "playlist_id";
            public static final String PLAY_ORDER = "play_order";

            public static Uri getContentUri(long j) {
                return Uri.parse("content://ttpod/playlists/" + j + "/members");
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Artists implements ArtistColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.artist";
        public static final String DEFAULT_SORT_ORDER = "artist_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/vnd.ttpod.artist";
        public static final String URI_PATH = "artists";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/artists");
        }

        /* loaded from: classes.dex */
        public static final class Albums implements AlbumColumns {
            public static Uri getContentUri(String str, long j) {
                return Uri.parse(MediaStoreOld.CONTENT_AUTHORITY_SLASH + str + "/audio/artists/" + j + "/albums");
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Albums implements AlbumColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.album";
        public static final String DEFAULT_SORT_ORDER = "album_key";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/album";
        public static final String URI_PATH = "albums";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/albums");
        }
    }

    /* loaded from: classes.dex */
    public static final class Folder implements FolderColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.ttpod.folder";
        public static final String DEFAULT_SORT_ORDER = "_folder";
        public static final String ENTRY_CONTENT_TYPE = "vnd.android.cursor.item/folder";
        public static final String URI_PATH = "folder";

        public static Uri getContentUri() {
            return Uri.parse("content://ttpod/folder");
        }
    }

    public static Uri getContentUri() {
        return Uri.parse(CONTENT_AUTHORITY_SLASH);
    }

    public static long getMediaIdInSystemMediaStore(Context context, String str) {
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

    public static boolean isMediaScannerRunning(Context context) {
        Cursor query = context.getContentResolver().query(CONTENT_URI_MEDIA_SCANNER, null, null, null, null);
        if (query != null) {
            boolean z = query.getCount() > 0;
            query.close();
            return z;
        }
        return false;
    }

    public static boolean isOnlineMediaStore(Uri uri) {
        return uri.getPath().startsWith("/online/");
    }
}