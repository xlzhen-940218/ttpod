package com.sds.android.ttpod.framework.modules;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItemData;
import com.sds.android.cloudapi.ttpod.data.Comment;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.PlaylistResult;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.cloudapi.ttpod.result.AlikeUserListResult;
import com.sds.android.cloudapi.ttpod.result.AroundUserListResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectCommResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.cloudapi.ttpod.result.CirclePosterListResult;
import com.sds.android.cloudapi.ttpod.result.CommentListResult;
import com.sds.android.cloudapi.ttpod.result.CommentResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.FindSongHotModuleResult;
import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewAlbumResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongCategoryResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongMoreResult;
import com.sds.android.cloudapi.ttpod.result.HotSongOnlineMediaItemsResultNew;
import com.sds.android.cloudapi.ttpod.result.IntroductionResult;
import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.cloudapi.ttpod.result.MessageCollectListResult;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.cloudapi.ttpod.result.NewFollowersListResult;
import com.sds.android.cloudapi.ttpod.result.NoticeListResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageContentListResult;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageOverViewListResult;
import com.sds.android.cloudapi.ttpod.result.RecommendPostResult;
import com.sds.android.cloudapi.ttpod.result.SearchTypeResult;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;
import com.sds.android.cloudapi.ttpod.result.StarCategoryResult;
import com.sds.android.cloudapi.ttpod.result.SystemNoticeListResult;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.sdk.core.download.Task;
import com.sds.android.sdk.lib.p060b.BaseResultRest;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.DataListResult;
import com.sds.android.sdk.lib.request.ExtraDataListResult;
import com.sds.android.sdk.lib.request.IdListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.framework.base.CommandType;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectCache;
import com.sds.android.ttpod.framework.modules.core.p113b.p114a.ShakeSensitivityType;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowDialogType;
import com.sds.android.ttpod.framework.modules.search.SoundRecognizer;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.modules.skin.p129b.SSkinInfo;
import com.sds.android.ttpod.framework.modules.skin.lyric.Lyric;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.task.ReportTask;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.modules.a */
/* loaded from: classes.dex */
public enum CommandID {
    ADD_DOWNLOAD_TASK(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, DownloadTaskInfo.class),
    ASYN_ADD_DOWNLOAD_TASK_LIST(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, List.class, Boolean.class),
    UPDATE_DOWNLOAD_TASK_LIST_RELOADED(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, new Class[0]),
    ADD_DOWNLOAD_TASK_BY_GROUP(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, String.class, Boolean.class),
    DELETE_DOWNLOAD_TASK(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, DownloadTaskInfo.class, Boolean.class),
    CANCEL_DOWNLOAD_TASK(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, DownloadTaskInfo.class),
    GET_TASK_LIST_WITH_STATE(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, Integer.class),
    GET_TASK_LIST_WITH_TYPE(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, Integer.class),
    UPDATE_DOWNLOAD_TASK_STATE(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, DownloadTaskInfo.class),
    UPDATE_ADD_DOWNLOAD_TASK_DATABASE(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, DownloadTaskInfo.class),
    UPDATE_ADD_DOWNLOAD_TASK_LIST_DATABASE(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, List.class),
    UPDATE_ADD_DOWNLOAD_TASK_ERROR(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, DownloadTaskInfo.class),
    UPDATE_ADD_DOWNLOAD_TASK_LIST_ERROR(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, List.class),
    DELETE_ALL_FINISHED_DOWNLOAD_TASK(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, Integer.class, Boolean.class),
    DOWNLOADING_TASK_CLEAR(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, new Class[0]),
    CLEAR_COMPLETE_TASK_COUNT(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, new Class[0]),
    GET_TASK_DOWNLOADED_LENGTH(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, DownloadTaskInfo.class),
    GET_TOTAL_DOWNLOAD_FILE_SIZE(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, List.class, AudioQuality.class),
    DOWNLOAD_TASK_FAILED(ModuleID.DOWNLOAD_MANAGER, CommandType.FROM_MODULE, DownloadTaskInfo.class, Task.ErrorCodeType.class),
    QUERY_DOWNLOADING_INFO_BY_GROUP(ModuleID.DOWNLOAD_MANAGER, CommandType.TO_MODULE, String.class),
    QUERY_GROUP_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, GroupType.class),
    QUERY_GROUP_ITEM_LIST_BY_AMOUNT_ORDER(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, GroupType.class),
    UPDATE_GROUP_LIST(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, GroupType.class, List.class),
    QUERY_LOCAL_AND_ONLINE_GROUP_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, new Class[0]),
    UPDATE_LOCAL_AND_ONLINE_GROUP_LIST(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, List.class),
    SEARCH_GROUP_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, GroupType.class, String.class),
    UPDATE_MATCHED_GROUP_LIST(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, GroupType.class, List.class),
    QUERY_MEDIA_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, String.class),
    UPDATE_MEDIA_LIST(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, String.class, List.class),
    QUERY_MEDIA_COUNT(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class),
    QUERY_MEDIA_ITEM(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, String.class),
    QUERY_ASYNCLOAD_MEDIA_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, String.class),
    PRELOAD_ASYNCLOAD_MEDIA_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, String.class),
    UPDATE_ASYNCLOAD_MEDIA_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, String.class, AsyncLoadMediaItemList.class),
    SEARCH_MEDIA_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, String.class),
    UPDATE_MATCHED_MEDIA_LIST(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, String.class, List.class),
    ADD_GROUP(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class),
    DELETE_GROUP(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class),
    ADD_MEDIA_ITEM(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, MediaItem.class),
    DELETE_MEDIA_ITEM(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, MediaItem.class, Boolean.class),
    ADD_MEDIA_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, Collection.class),
    DELETE_MEDIA_ITEM_LIST(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, Collection.class, Boolean.class),
    DELETE_MEDIA_ITEMS_FINISHED(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, String.class),
    UPDATE_MEDIA_ITEM(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, MediaItem.class),
    UPDATE_GROUP_ITEM(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, GroupItem.class),
    SYNC_NET_TEMPORARY_GROUP(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, List.class),
    SYNC_NET_TEMPORARY_GROUP_WITH_NAME(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, List.class, String.class),
    APPEND_NET_TEMPORARY_MEDIA_ITEMS(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, List.class),
    ADD_GROUP_ITEM_EXCHANGE_ORDER_EVENT(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, GroupType.class, String.class, String.class),
    COMMIT_GROUP_ITEM_EXCHANGE_ORDER(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, GroupType.class),
    COMMIT_EXCHANGE_ORDER(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, String.class, List.class),
    UPDATE_MEDIA_LIBRARY_CHANGED(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, String.class),
    DELETE_PICTURE(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, Collection.class),
    UPDATE_PICTURE_DELETED(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, MediaItem.class),
    DELETE_LYRIC(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, Collection.class),
    UPDATE_LYRIC_DELETED(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, MediaItem.class),
    UPDATE_MEDIA_ITEM_STARTED(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, MediaItem.class),
    UPDATE_MEDIA_ITEM_FINISHED(ModuleID.MEDIA_ACCESS, CommandType.FROM_MODULE, MediaItem.class),
    QUERY_ONLINE_MEDIA_ITEMS_BY_IDS(ModuleID.MEDIA_ACCESS, CommandType.TO_MODULE, Collection.class),
    LOAD_SPLASH(ModuleID.SPLASH, CommandType.TO_MODULE, Integer.class, Integer.class),
    SET_AUDIO_ENABLED(ModuleID.SPLASH, CommandType.TO_MODULE, Boolean.class),
    UPDATE_SPLASH(ModuleID.SPLASH, CommandType.FROM_MODULE, Bitmap.class, Bitmap.class, String.class, Boolean.class),
    FINISH_SPLASH(ModuleID.SPLASH, CommandType.FROM_MODULE, new Class[0]),
    PLAY(ModuleID.SUPPORT, CommandType.TO_MODULE, String.class),
    PLAY_GROUP(ModuleID.SUPPORT, CommandType.TO_MODULE, String.class, MediaItem.class),
    SYNC_GROUP(ModuleID.SUPPORT, CommandType.TO_MODULE, String.class, MediaItem.class),
    PLAY_LOCAL_RANDOM(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    SYNC_CUR_MEDIA_INFO(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    SYNC_PLAYING_GROUP(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    START(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    STOP(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    PAUSE(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    RESUME(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    PREVIOUS(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    NEXT(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    RESUME_IMAGE_SWITCHER(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    PAUSE_IMAGE_SWITCHER(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    UPDATE_PLAY_STATUS(ModuleID.SUPPORT, CommandType.FROM_MODULE, PlayStatus.class),
    PLAY_MEDIA_CHANGED(ModuleID.SUPPORT, CommandType.FROM_MODULE, new Class[0]),
    PLAY_GROUP_CHANGED(ModuleID.SUPPORT, CommandType.FROM_MODULE, new Class[0]),
    UPDATE_PLAYING_MEDIA_INFO(ModuleID.SUPPORT, CommandType.FROM_MODULE, new Class[0]),
    UPDATE_PLAY_ERROR(ModuleID.SUPPORT, CommandType.FROM_MODULE, Integer.class),
    UPDATE_PLAY_POSITION(ModuleID.SUPPORT, CommandType.FROM_MODULE, Integer.class),
    SWITCH_PLAY_MODE(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    UPDATE_PLAY_MODE(ModuleID.SUPPORT, CommandType.FROM_MODULE, new Class[0]),
    UPDATE_BUFFERING_STATE_STARTED(ModuleID.SUPPORT, CommandType.FROM_MODULE, new Class[0]),
    UPDATE_BUFFERING_STATE_DONE(ModuleID.SUPPORT, CommandType.FROM_MODULE, new Class[0]),
    SET_POSITION(ModuleID.SUPPORT, CommandType.TO_MODULE, Integer.class),
    EXIT(ModuleID.SUPPORT, CommandType.TO_MODULE, new Class[0]),
    SET_AUDIO_EFFECT_RESET(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, new Class[0]),
    SET_CLOUD_AUDIO_EFFECT_ENABLED(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Boolean.class),
    SET_CLOUD_AUDIO_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, AudioEffectItem.class, Boolean.class),
    SET_AUDIO_EFFECT_TRY_MODE(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Boolean.class),
    SET_LOCAL_AUDIO_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Boolean.class),
    SET_EQUALIZER(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, TTEqualizer.Settings.class),
    GET_EQUALIZER(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, new Class[0]),
    SET_BASSBOOST(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Integer.class),
    SET_BOOSTLIMIT_ENABLED(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Boolean.class),
    SET_TREBLEBOOST(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Integer.class),
    SET_VIRTUALIZER(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Integer.class),
    SET_REVERB(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Integer.class),
    SET_CHANNELBALANCE(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, Float.class),
    SAVE_CUSTOM_EQUALIZER(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, TTEqualizer.Settings.class),
    UPDATE_SAVE_CUSTOM_EQUALIZER(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, TTEqualizer.Settings.class),
    DELETE_CUSTOM_EQUALIZER(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, String.class),
    QUERY_CUSTOM_EQUALIZER_LIST(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, new Class[0]),
    UPDATE_CUSTOM_EQUALIZER_LIST(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, List.class),
    QUERY_EFFECT_USERINFO(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, new Class[0]),
    UPDATE_QUERY_EFFECT_USERINFO(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, AudioEffectUserResult.class),
    QUERY_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, MediaItem.class, Integer.class, Integer.class),
    UPDATE_QUERY_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, AudioEffectItemResult.class),
    EFFECT_SAVE_RESULT(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, Boolean.class),
    PICK_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, String.class),
    BIND_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, String.class),
    UPDATE_PICK_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, AudioEffectCommResult.class, String.class),
    IS_PICKED_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, String.class),
    QUERY_PRIVATE_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, new Class[0]),
    UPDATE_QUERY_PRIVATE_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, List.class, List.class),
    DELETE_PRIVATE_EFFECT_LIST(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, List.class),
    UPDATE_DELETE_PRIVATE_EFFECT_LIST(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, new Class[0]),
    SET_EFFECT_ITEM(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, AudioEffectItem.class),
    SAVE_EFFECT_TO_LOCAL(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, MediaItem.class, AudioEffectCache.class),
    SAVE_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, MediaItem.class, AudioEffectCache.class, Boolean.class),
    UPDATE_SAVE_EFFECT_TO_LOCAL(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, Boolean.class),
    SAVE_EFFECT_TO_NETWORK(ModuleID.AUDIO_EFFECT, CommandType.TO_MODULE, MediaItem.class, AudioEffectItemData.class, Integer.class, Integer.class),
    UPDATE_SAVE_EFFECT_TO_NETWORK(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, AudioEffectAddResult.class),
    UPDATE_MANUAL_SETTING_EFFECT(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, new Class[0]),
    UPDATE_AUDIO_EFFECT_INFO(ModuleID.AUDIO_EFFECT, CommandType.FROM_MODULE, new Class[0]),
    START_SCAN(ModuleID.MEDIA_SCAN, CommandType.TO_MODULE, Collection.class, String.class),
    STOP_SCAN(ModuleID.MEDIA_SCAN, CommandType.TO_MODULE, new Class[0]),
    SCAN_PROGRESS(ModuleID.MEDIA_SCAN, CommandType.TO_MODULE, new Class[0]),
    SCANNED_FILE_COUNT(ModuleID.MEDIA_SCAN, CommandType.TO_MODULE, new Class[0]),
    SCANNING_FILE_PATH(ModuleID.MEDIA_SCAN, CommandType.TO_MODULE, new Class[0]),
    SCAN_FINISHED(ModuleID.MEDIA_SCAN, CommandType.FROM_MODULE, Integer.class),
    START_WIFI_TRANSMISSION(ModuleID.MEDIA_SCAN, CommandType.TO_MODULE, new Class[0]),
    STOP_WIFI_TRANSMISSION(ModuleID.MEDIA_SCAN, CommandType.TO_MODULE, new Class[0]),
    UPDATE_WIFI_TRANSMISSION_STATE(ModuleID.MEDIA_SCAN, CommandType.FROM_MODULE, CommonResult.class),
    SET_SKIN(ModuleID.SKIN, CommandType.TO_MODULE, String.class, Integer.class),
    GET_SKIN_PROTOCOL_PATH(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    SET_SKIN_PROTOCOL_PATH(ModuleID.SKIN, CommandType.TO_MODULE, String.class),
    DELETE_SKIN(ModuleID.SKIN, CommandType.TO_MODULE, String.class, Integer.class),
    SKIN_CHANGED(ModuleID.SKIN, CommandType.FROM_MODULE, new Class[0]),
    DECODE_SKIN_THUMBNAIL(ModuleID.SKIN, CommandType.TO_MODULE, SkinItem.class),
    DECODE_SKIN_THUMBNAIL_FINISHED(ModuleID.SKIN, CommandType.FROM_MODULE, SkinItem.class),
    LOAD_SKIN(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    LOAD_SKIN_FINISHED(ModuleID.SKIN, CommandType.FROM_MODULE, SkinCache.class),
    LOAD_SKIN_WITH_PATH(ModuleID.SKIN, CommandType.TO_MODULE, String.class),
    LOAD_SKIN_WITH_PATH_FINISHED(ModuleID.SKIN, CommandType.FROM_MODULE, SkinCache.class),
    LOAD_SKIN_ERROR(ModuleID.SKIN, CommandType.FROM_MODULE, new Class[0]),
    REQUEST_RECOMMEND_SKIN_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    UPDATE_RECOMMEND_SKIN_LIST(ModuleID.SKIN, CommandType.FROM_MODULE, ArrayList.class),
    REQUEST_UPDATE_RECOMMEND_SKIN_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    FINISH_UPDATE_RECOMMEND_SKIN_LIST(ModuleID.SKIN, CommandType.FROM_MODULE, Boolean.class),
    REQUEST_UPDATE_SKIN_RANK_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    FINISH_UPDATE_SKIN_RANK_LIST(ModuleID.SKIN, CommandType.FROM_MODULE, Boolean.class),
    REQUEST_UPDATE_RECOMMEND_BACKGROUND_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    FINISH_UPDATE_RECOMMEND_BACKGROUND_LIST(ModuleID.SKIN, CommandType.FROM_MODULE, Boolean.class),
    REQUEST_SKIN_RANK_LIST(ModuleID.SKIN, CommandType.TO_MODULE, Integer.class),
    UPDATE_SKIN_RANK_LIST(ModuleID.SKIN, CommandType.FROM_MODULE, ArrayList.class),
    REQUEST_SKIN_INFO(ModuleID.SKIN, CommandType.TO_MODULE, String.class),
    UPDATE_SKIN_INFO(ModuleID.SKIN, CommandType.FROM_MODULE, String.class, SSkinInfo.class),
    REQUEST_DOWNLOADED_SKIN_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    UPDATE_DOWNLOADED_SKIN_LIST(ModuleID.SKIN, CommandType.FROM_MODULE, ArrayList.class),
    LOAD_ALL_LOCAL_SKIN_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    UPDATE_ALL_LOCAL_SKIN_LIST(ModuleID.SKIN, CommandType.FROM_MODULE, ArrayList.class),
    PARSE_CATEGORY_LIST(ModuleID.SKIN, CommandType.TO_MODULE, Integer.class),
    ON_SKIN_CATEGORY_LIST_PARSED(ModuleID.SKIN, CommandType.FROM_MODULE, ArrayList.class, Long.class),
    ON_BACKGROUND_CATEGORY_LIST_PARSED(ModuleID.SKIN, CommandType.FROM_MODULE, ArrayList.class, Long.class),
    REQUEST_BKG_CATEGORY_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    ON_BKG_CATEGORY_LIST_DOWNLOADED(ModuleID.SKIN, CommandType.FROM_MODULE, new Class[0]),
    REQUEST_SKIN_CATEGORY_LIST(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    ON_SKIN_CATEGORY_LIST_DOWNLOADED(ModuleID.SKIN, CommandType.FROM_MODULE, new Class[0]),
    REQUEST_PAGED_SKIN_LIST(ModuleID.SKIN, CommandType.TO_MODULE, Integer.class, Integer.class, Integer.class),
    REQUEST_PAGED_SKIN_LIST_FINISHED(ModuleID.SKIN, CommandType.FROM_MODULE, OnlinePagedSkinListResult.class),
    REQUEST_PAGED_BKG_LIST(ModuleID.SKIN, CommandType.TO_MODULE, Integer.class, Integer.class, Integer.class),
    REQUEST_PAGED_BKG_LIST_FINISHED(ModuleID.SKIN, CommandType.FROM_MODULE, OnlinePagedSkinListResult.class),
    NOTIFY_PLAYING_PANEL_ON_SHOW(ModuleID.SKIN, CommandType.TO_MODULE, new Class[0]),
    ON_PLAYING_PANEL_SHOW(ModuleID.SKIN, CommandType.FROM_MODULE, new Class[0]),
    SET_BACKGROUND(ModuleID.THEME, CommandType.TO_MODULE, String.class),
    LOAD_BACKGROUND(ModuleID.THEME, CommandType.TO_MODULE, new Class[0]),
    GET_BACKGROUND(ModuleID.THEME, CommandType.TO_MODULE, new Class[0]),
    UPDATE_BACKGROUND(ModuleID.THEME, CommandType.FROM_MODULE, Drawable.class),
    REQUEST_BACKGROUND_MORE(ModuleID.THEME, CommandType.TO_MODULE, new Class[0]),
    LOAD_BACKGROUND_LIST(ModuleID.THEME, CommandType.TO_MODULE, Boolean.class),
    UPDATE_BACKGROUND_LIST(ModuleID.THEME, CommandType.FROM_MODULE, ArrayList.class),
    UPDATE_LOCAL_BACKGROUND_LIST(ModuleID.THEME, CommandType.FROM_MODULE, ArrayList.class),
    DELETE_BACKGROUND(ModuleID.THEME, CommandType.TO_MODULE, String.class),
    LOAD_THEME_LIST(ModuleID.THEME, CommandType.TO_MODULE, new Class[0]),
    UPDATE_THEME_LIST(ModuleID.THEME, CommandType.FROM_MODULE, List.class),
    APP_THEME_CHANGED(ModuleID.THEME, CommandType.FROM_MODULE, new Class[0]),
    DECODE_BACKGROUND_THUMBNAIL(ModuleID.THEME, CommandType.TO_MODULE, BackgroundItem.class),
    BACKGROUND_THUMBNAIL_CREATED(ModuleID.THEME, CommandType.FROM_MODULE, BackgroundItem.class),
    STOP_SLEEP_MODE(ModuleID.GLOBAL, CommandType.TO_MODULE, new Class[0]),
    START_SLEEP_MODE(ModuleID.GLOBAL, CommandType.TO_MODULE, Integer.class),
    IS_SLEEP_MODE_ENABLED(ModuleID.GLOBAL, CommandType.TO_MODULE, new Class[0]),
    SLEEP_MODE_REMAIN_TIME(ModuleID.GLOBAL, CommandType.TO_MODULE, new Class[0]),
    UPDATE_SLEEP_MODE(ModuleID.GLOBAL, CommandType.FROM_MODULE, new Class[0]),
    START_SEARCH_PICTURE(ModuleID.SEARCH, CommandType.TO_MODULE, MediaItem.class, String.class, String.class),
    START_DOWNLOAD_SEARCH_PICTURE(ModuleID.SEARCH, CommandType.TO_MODULE, String.class, String.class, MediaItem.class),
    START_SEARCH_LYRIC(ModuleID.SEARCH, CommandType.TO_MODULE, MediaItem.class, String.class, String.class),
    START_DOWNLOAD_SEARCH_LYRIC(ModuleID.SEARCH, CommandType.TO_MODULE, ResultData.Item.class, MediaItem.class),
    START_SEARCH_SONG(ModuleID.SEARCH, CommandType.TO_MODULE, String.class, Integer.class, Integer.class, String.class),
    UPDATE_SEARCH_SONG_FINISHED(ModuleID.SEARCH, CommandType.FROM_MODULE, Integer.class, Integer.class, List.class, String.class),
    START_SEARCH_HOT_WORDS(ModuleID.SEARCH, CommandType.TO_MODULE, new Class[0]),
    UPDATE_SEARCH_HOT_WORDS_FINISHED(ModuleID.SEARCH, CommandType.FROM_MODULE, List.class),
    START_SEARCH_ASSOCIATE(ModuleID.SEARCH, CommandType.TO_MODULE, String.class),
    UPDATE_SEARCH_ASSOCIATE_FINISHED(ModuleID.SEARCH, CommandType.FROM_MODULE, List.class),
    START_SEARCH_BILLBOARD(ModuleID.SEARCH, CommandType.TO_MODULE, Integer.class),
    UPDATE_SEARCH_BILLBOARD_FINISHED(ModuleID.SEARCH, CommandType.FROM_MODULE, List.class),
    START_REPORT_SONG(ModuleID.SEARCH, CommandType.TO_MODULE, String.class),
    UPDATE_REPORT_SONG_FINISHED(ModuleID.SEARCH, CommandType.FROM_MODULE, Boolean.class),
    GET_SEARCH_TYPES(ModuleID.SEARCH, CommandType.TO_MODULE, new Class[0]),
    UPDATE_SEARCH_TYPES(ModuleID.SEARCH, CommandType.FROM_MODULE, SearchTypeResult.class),
    REPORT_LYRIC_PICTURE(ModuleID.SEARCH, CommandType.TO_MODULE, ReportTask.EnumC2097b.class, ReportTask.EnumC2096a.class, MediaItem.class),
    UPDATE_REPORT(ModuleID.SEARCH, CommandType.FROM_MODULE, ReportTask.EnumC2097b.class, MediaItem.class, Boolean.class),
    UPDATE_SEARCH_LYRIC_STATE(ModuleID.SEARCH, CommandType.FROM_MODULE, SearchStatus.class, List.class, String.class, Lyric.class),
    UPDATE_SEARCH_PICTURE_STATE(ModuleID.SEARCH, CommandType.FROM_MODULE, SearchStatus.class, List.class, String.class, Bitmap.class),
    SWITCH_ARTIST_PICTURE(ModuleID.SEARCH, CommandType.FROM_MODULE, String.class, String.class, Bitmap.class),
    SEARCH_RECOGNIZE_ERROR(ModuleID.SEARCH, CommandType.FROM_MODULE, SoundRecognizer.EnumC1975a.class),
    SEARCH_RECOGNIZE_SUCCESS(ModuleID.SEARCH, CommandType.FROM_MODULE, List.class),
    START_SEARCH_RECOGNIZE(ModuleID.SEARCH, CommandType.TO_MODULE, new Class[0]),
    STOP_SEARCH_RECOGNIZE(ModuleID.SEARCH, CommandType.TO_MODULE, new Class[0]),
    CANCEL_SEARCH_RECOGNIZE(ModuleID.SEARCH, CommandType.TO_MODULE, new Class[0]),
    GET_SEARCH_RECOGNIZE_VOLUME(ModuleID.SEARCH, CommandType.TO_MODULE, new Class[0]),
    START_SEARCH_ALBUM(ModuleID.SEARCH, CommandType.TO_MODULE, String.class, Integer.class, Integer.class, String.class),
    UPDATE_SEARCH_ALBUM_FINISHED(ModuleID.SEARCH, CommandType.FROM_MODULE, AlbumItemsResult.class),
    START_SEARCH_PLAY_LIST(ModuleID.SEARCH, CommandType.TO_MODULE, String.class, Integer.class, Integer.class, String.class),
    UPDATE_SEARCH_PLAY_LIST_RESULT(ModuleID.SEARCH, CommandType.FROM_MODULE, PlaylistResult.class),
    RECEIVED_SEARCH_EVENT(ModuleID.SEARCH, CommandType.TO_MODULE, Intent.class),
    START_LOCK_SCREEN(ModuleID.LOCK_SCREEN, CommandType.TO_MODULE, new Class[0]),
    STOP_LOCK_SCREEN(ModuleID.LOCK_SCREEN, CommandType.TO_MODULE, new Class[0]),
    RECEIVED_LOCK_SCREEN_ACTION(ModuleID.LOCK_SCREEN, CommandType.TO_MODULE, Intent.class),
    UPDATE_FAVORITE_CHANGED(ModuleID.FAVORITE, CommandType.FROM_MODULE, new Class[0]),
    USER_ADDED_FAVORITE_MEDIA(ModuleID.FAVORITE, CommandType.FROM_MODULE, MediaItem.class),
    ADD_FAVORITE_MEDIA_ITEM(ModuleID.FAVORITE, CommandType.TO_MODULE, MediaItem.class),
    DELETE_FAVORITE_MEDIA_ITEM(ModuleID.FAVORITE, CommandType.TO_MODULE, MediaItem.class, Boolean.class),
    DELETE_FAVORITE_MEDIA_ITEM_LIST(ModuleID.FAVORITE, CommandType.TO_MODULE, Collection.class, Boolean.class),
    SYNC_FAVORITE_ONLINE_MEDIA_LIST(ModuleID.FAVORITE, CommandType.TO_MODULE, new Class[0]),
    PUSH_FAVORITE_ONLINE_MEDIA_LIST(ModuleID.FAVORITE, CommandType.TO_MODULE, new Class[0]),
    PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE(ModuleID.FAVORITE, CommandType.FROM_MODULE, new Class[0]),
    PULL_FAVORITE_ONLINE_MEDIA_LIST_ERROR(ModuleID.FAVORITE, CommandType.FROM_MODULE, new Class[0]),
    PUSH_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE(ModuleID.FAVORITE, CommandType.FROM_MODULE, new Class[0]),
    PUSH_FAVORITE_ONLINE_MEDIA_LIST_ERROR(ModuleID.FAVORITE, CommandType.FROM_MODULE, new Class[0]),
    PUSH_FAVORITE_ONLINE_MEDIA_LIST_INVALID(ModuleID.FAVORITE, CommandType.FROM_MODULE, new Class[0]),
    RESISTER(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, String.class, String.class),
    RESISTER_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    LOGIN(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, String.class),
    QQ_LOGIN(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, String.class, String.class),
    SINA_LOGIN(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, String.class, String.class),
    LOGIN_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    LOGOUT(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, new Class[0]),
    LOGOUT_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, new Class[0]),
    FIND_PASSWORD(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class),
    FIND_PASSWORD_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    MODIFY_NICKNAME(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class),
    MODIFY_NICKNAME_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    MODIFY_USER_EMAIL(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, String.class),
    BIND_USER_EMAIL(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, String.class),
    MODIFY_USER_EMAIL_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    MODIFY_PASSWORD(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, String.class),
    MODIFY_PASSWORD_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    MODIFY_COVER(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, Integer.class, Integer.class),
    MODIFY_COVER_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    MODIFY_AVATAR(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, Integer.class, Integer.class),
    MODIFY_AVATAR_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    MODIFY_SEX_BIRTHDAY(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, TTPodUser.class, Boolean.class, Boolean.class),
    MODIFY_SEX_BIRTHDAY_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, CommonResult.class),
    REFRESH_INFORMATION(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, new Class[0]),
    REFRESH_INFORMATION_FINISHED(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, new Class[0]),
    GET_USER_INFO_BY_ID(ModuleID.USER_SYSTEM, CommandType.TO_MODULE, String.class, Long.class),
    UPDATE_USER_INFO_BY_ID(ModuleID.USER_SYSTEM, CommandType.FROM_MODULE, TTPodUserResult.class),
    GET_SONG_CATEGORY_INFO(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class),
    UPDATE_SONG_CATEGORY_INFO(ModuleID.FIND_SONG, CommandType.FROM_MODULE, DataListResult.class, String.class),
    GET_SONG_CATEGORY_DETAIL(ModuleID.FIND_SONG, CommandType.TO_MODULE, Integer.class, Integer.class, String.class),
    UPDATE_SONG_CATEGORY_DETAIL(ModuleID.FIND_SONG, CommandType.FROM_MODULE, MediaItemListResult.class, String.class),
    GET_DAILY_RECOMMEND(ModuleID.FIND_SONG, CommandType.TO_MODULE, Integer.class),
    UPDATE_DAILY_RECOMMEND(ModuleID.FIND_SONG, CommandType.FROM_MODULE, MediaItemListResult.class),
    GET_RADIO_CATEGORY_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, new Class[0]),
    UPDATE_RADIO_CATEGORY_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, DataListResult.class),
    GET_RADIO_CHANNEL_MUSIC_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class, Integer.class),
    UPDATE_RADIO_CHANNEL_MUSIC_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, ArrayList.class),
    GET_MUSIC_RANKS(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class),
    UPDATE_MUSIC_RANKS(ModuleID.FIND_SONG, CommandType.FROM_MODULE, MusicRanksResult.class, String.class),
    GET_RANK_MUSIC_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, Integer.class, Integer.class, String.class),
    UPDATE_RANK_MUSIC_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, MediaItemListResult.class, String.class),
    GET_FIND_SONG_CATEGORY_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, new Class[0]),
    UPDATE_FIND_SONG_CATEGORY_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, ArrayList.class),
    GET_SINGER_CATEGORY_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, Integer.class),
    UPDATE_SINGER_CATEGORY_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, DataListResult.class),
    GET_SINGER_CATEGORY_DETAIL(ModuleID.FIND_SONG, CommandType.TO_MODULE, Integer.class, Integer.class),
    FIND_SONG_RECOMMEND_MODULE(ModuleID.FIND_SONG, CommandType.TO_MODULE, new Class[0]),
    UPDATE_FIND_SONG_RECOMMEND_MODULE(ModuleID.FIND_SONG, CommandType.FROM_MODULE, FindSongHotModuleResult.class),
    GET_RECOMMEND_CONTENT(ModuleID.FIND_SONG, CommandType.TO_MODULE, Long.class),
    UPDATE_RECOMMEND_CONTENT(ModuleID.FIND_SONG, CommandType.FROM_MODULE, FindSongModuleResult.class),
    UPDATE_SONG_CATEGORY_CHANNEL_CONTENT(ModuleID.MUSIC_LIBRARY, CommandType.FROM_MODULE, OnlineMusicSubCategoryResult.class),
    GET_SONG_CATEGORY_CHANNEL_CONTENT(ModuleID.MUSIC_LIBRARY, CommandType.TO_MODULE, Long.class),
    GET_FIND_SONG_HOT_SONGS(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class, Integer.class, Integer.class),
    UPDATE_GET_FIND_SONG_HOT_SONGS(ModuleID.FIND_SONG, CommandType.FROM_MODULE, HotSongOnlineMediaItemsResultNew.class),
    GET_FIND_SONG_HOT_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class, Integer.class, Integer.class),
    UPDATE_GET_FIND_SONG_HOT_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, FindSongHotListResultNew.class),
    GET_FIND_SONG_HOT_SINGERS(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class, Integer.class, Integer.class, Boolean.class),
    UPDATE_GET_FIND_SONG_HOT_SINGERS(ModuleID.FIND_SONG, CommandType.FROM_MODULE, SingerListResult.class),
    GET_FIND_SONG_HANDPICK(ModuleID.FIND_SONG, CommandType.TO_MODULE, new Class[0]),
    UPDATE_GET_FIND_SONG_HANDPICK(ModuleID.FIND_SONG, CommandType.FROM_MODULE, FindSongHandpickResult.class),
    GET_OPERATION_ZONE_RESULT(ModuleID.FIND_SONG, CommandType.TO_MODULE, new Class[0]),
    UPDATE_OPERATION_ZONE_RESULT(ModuleID.FIND_SONG, CommandType.FROM_MODULE, OperationZoneResult.class),
    REQUEST_STAR_USERS_BY_RANK(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class, Integer.class, String.class),
    UPDATE_STAR_USER_LIST_BY_RANK(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, LabeledTTPodUserListResult.class, String.class),
    REQUEST_STAR_USERS_BY_CATEGORY(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class, Integer.class, String.class),
    UPDATE_STAR_USER_lIST_BY_CATEGORY(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, LabeledTTPodUserListResult.class, String.class),
    REQUEST_STAR_CATEGORIES(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_STAR_CATEGORIES_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, StarCategoryResult.class, String.class),
    MUSICCIRCLE_SEARCH(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class, String.class),
    UPDATE_SEARCH_RESULT(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, UserListResult.class, String.class),
    FOLLOW_FRIEND(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class),
    UPDATE_FOLLOW_FRIEND(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    UNFOLLOW_FRIEND(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class),
    UPDATE_UNFOLLOW_FRIEND(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    REQUEST_FOLLOWING_FRIEND_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class),
    UPDATE_FOLLOWING_FRIEND_ID_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, IdListResult.class, String.class),
    REQUEST_FOLLOWING_FRIENDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Integer.class, Integer.class, Long.class, String.class),
    UPDATE_FOLLOWING_FRIEND_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, UserListResult.class, String.class),
    REQUEST_FOLLOWER_FRIENDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Integer.class, Integer.class, Long.class, String.class),
    UPDATE_FOLLOWER_FRIENDS(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, UserListResult.class, String.class),
    REQUEST_USER_INFO_BY_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Collections.class, String.class),
    UPDATE_FOLLOWER_FRIEND_LIST_BY_IDS(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, UserListResult.class, String.class),
    REQUEST_SYSTEM_NOTICES(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Integer.class, String.class),
    UPDATE_SYSTEM_NOTICE_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, SystemNoticeListResult.class, String.class),
    REQUEST_REPOST_NOTICES(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class, String.class),
    UPDATE_REPOST_NOTICE_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, NoticeListResult.class, String.class),
    REQUEST_COMMENT_NOTICES(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class, String.class),
    UPDATE_COMMENT_NOTICE_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, NoticeListResult.class, String.class),
    REQUEST_NEW_FOLLOWER_NOTICES(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_NEW_FOLLOWER_NOTICE_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, NewFollowersListResult.class, String.class),
    REQUEST_PRIVATE_MESSAGES(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Integer.class, String.class),
    UPDATE_PRIVATE_MESSAGE_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, PrivateMessageOverViewListResult.class, String.class),
    QUERY_PRIVATE_MESSAGES_CONTENT(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Long.class, Integer.class, String.class),
    UPDATE_PRIVATE_MESSAGE_CONTEXT_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, PrivateMessageContentListResult.class, String.class),
    DELETE_PRIVATE_MESSAGE(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class, String.class),
    UPDATE_DELETE_PRIVATE_MESSAGE(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    SEND_PRIVATE_MESSAGE(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class, String.class),
    UPDATE_SEND_PRIVATE_MESSAGE(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    DELETE_PRIVATE_MESSAGES(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class),
    UPDATE_DELETE_PRIVATE_MESSAGE_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    DELETE_NOTICE(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Notice.class, String.class),
    UPDATE_NOTICE_DELETED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    POST_COMMENT(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class, Long.class, Long.class, String.class),
    UPDATE_COMMENT_POSTED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, CommentResult.class, String.class),
    DELETE_COMMENT(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Comment.class, String.class),
    UPDATE_COMMENT_DELETED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    RE_POST(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Long.class, String.class, String.class),
    UPDATE_RE_POSTED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    REQUEST_USER_POST_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class),
    UPDATE_USER_POST_ID_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, IdListResult.class, String.class),
    REQUEST_TIMELINE_USER_POST_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_TIMELINE_USER_POST_IDS(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, IdListResult.class, String.class),
    REQUEST_COMMENT_IDS_BY_POST_ID(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, String.class),
    UPDATE_COMMENT_ID_LIST_BY_POST_ID(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, IdListResult.class, String.class),
    REQUEST_COMMENT_INFOS_BY_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Collection.class, String.class),
    UPDATE_COMMENT_INFO_LIST_BY_ID_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, CommentListResult.class, String.class),
    REQUEST_POST_SONG_BY_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, List.class, String.class),
    UPDATE_POST_SONG_BY_IDS(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, MediaItemListResult.class, String.class),
    REQUEST_POST_DETAIL_BY_ID(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Collection.class, String.class),
    UPDATE_POST_DETAIL_BY_ID(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, PostResult.class, String.class),
    REQUEST_POST_INFOS_BY_ID(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Collection.class, String.class),
    REQUEST_CELEBRITY_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class),
    REQUEST_PRIVATE_CUSTOM_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class),
    UPDATE_PRIVATE_CUSTOM_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, RecommendPostResult.class),
    UPDATE_DISCOVERY_POST_INFO_LIST_BY_ID(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, PostResult.class),
    UPDATE_POST_INFO_LIST_BY_ID(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, PostResult.class, String.class),
    REQUEST_DISCOVERY_CELEBRATE_POST_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_DISCOVERY_CELEBRATE_POST_ID_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, IdListResult.class, String.class),
    REQUEST_RECOMMEND_CELEBRATE_POST_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_RECOMMEND_CELEBRATE_POST_ID_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, IdListResult.class, String.class),
    REQUEST_FIRST_PUBLISH_CELEBRATE_POST_IDS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_FIRST_PUBLISH_CELEBRATE_POST_ID_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, IdListResult.class, String.class),
    ADD_FAVORITE_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, List.class, String.class),
    UPDATE_ADD_FAVORITE_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    REMOVE_FAVORITE_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, List.class, String.class),
    UPDATE_REMOVE_FAVORITE_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, BaseResult.class, String.class),
    IS_FAVORITE_POST(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class),
    UPDATE_SYNC_FAVORITE_POST_FINISHED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, new Class[0]),
    REQUEST_FAVORITE_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_FAVORITE_COLLECT_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, MessageCollectListResult.class, String.class),
    REQUEST_POSTS_BY_CATEGORY_ID(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class, Integer.class, String.class),
    UPDATE_POSTS_BY_CATEGORY_ID(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, PostResult.class, String.class),
    UPDATE_SINGER_CATEGORY_DETAIL(ModuleID.FIND_SONG, CommandType.FROM_MODULE, SingerListResult.class),
    GET_SINGER_SONG_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class, Integer.class),
    UPDATE_SINGER_SONG_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, MediaItemListResult.class),
    GET_POPULAR_SONG_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, Integer.class, String.class),
    UPDATE_POPULAR_SONG_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, MediaItemListResult.class),
    GET_POPULAR_SONG_INTRODUCTION(ModuleID.FIND_SONG, CommandType.TO_MODULE, Long.class),
    UPDATE_POPULAR_SONG_INTRODUCTION(ModuleID.FIND_SONG, CommandType.FROM_MODULE, IntroductionResult.class),
    GET_MV_LIST(ModuleID.FIND_SONG, CommandType.TO_MODULE, Integer.class, Integer.class),
    UPDATE_MV_LIST(ModuleID.FIND_SONG, CommandType.FROM_MODULE, ExtraDataListResult.class),
    GET_MV_THUMBNAIL(ModuleID.FIND_SONG, CommandType.TO_MODULE, String.class),
    UPDATE_MV_THUMBNAIL(ModuleID.FIND_SONG, CommandType.FROM_MODULE, String.class, Bitmap.class),
    REQUEST_MUSIC_POSTER_LIST(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, new Class[0]),
    UPDATE_MUSIC_POSTER_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, CirclePosterListResult.class),
    REQUEST_NEW_SONG_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, new Class[0]),
    UPDATE_NEW_SONG_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, ArrayList.class),
    REQUEST_MORE_NEW_SONG_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class),
    UPDATE_MORE_NEW_SONG_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, FirstPublishNewSongMoreResult.class),
    REQUEST_NEW_SONG_CATEGORY_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, new Class[0]),
    UPDATE_NEW_SONG_CATEGORY_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, FirstPublishNewSongCategoryResult.class),
    REQUEST_NEW_ALBUM_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class),
    UPDATE_NEW_ALBUM_PUBLISH_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, FirstPublishNewAlbumResult.class),
    REQUEST_SHACK_USERS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Float.class, Float.class, String.class),
    UPDATE_SHAKE_RESULT(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, AroundUserListResult.class, String.class),
    UPDATE_MUSIC_CIRCLE_NO_LOGIN(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, CommonResult.class),
    UPDATE_NETWORK_INVALID(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, CommonResult.class),
    UPDATE_NETWORK_UNKNOWN_ERROR(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, CommonResult.class),
    REQUEST_ALIKE_USERS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, String.class),
    UPDATE_ALIKE_USER_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, AlikeUserListResult.class, String.class),
    GET_STAR_USERS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Integer.class, Integer.class, Integer.class),
    UPDATE_STAR_USERS_LIST(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, CommonResult.class),
    IS_FOLLOWED(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class),
    UPDATE_SYNC_FOLLOWING_FINISHED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, new Class[0]),
    SET_LOGIN_UID(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class),
    CHANGE_POST_REPOST_COUNT(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Post.class),
    UPDATE_POST_REPOST_COUNT(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, Post.class),
    CHANGE_POST_REPLY_COUNT(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Post.class),
    UPDATE_POST_REPLY_COUNT(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, Post.class),
    REQUEST_FAVORITE_SONG_LIST_POSTS(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, new Class[0]),
    ADD_POSTS_TO_MEDIA_GROUP(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, List.class),
    ADD_POSTS_TO_MEDIA_GROUP_FINISHED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, new Class[0]),
    ADD_POST_TO_MEDIA_GROUP(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Post.class),
    ADD_POST_TO_MEDIA_GROUP_FINISHED(ModuleID.MUSIC_CIRCLE, CommandType.FROM_MODULE, String.class),
    ADD_LISTENER_COUNT(ModuleID.MUSIC_CIRCLE, CommandType.TO_MODULE, Long.class),
    START_SMART_UPGRADE(ModuleID.VERSION, CommandType.TO_MODULE, Boolean.class),
    UPDATE_SMART_UPDATE_INFO(ModuleID.VERSION, CommandType.FROM_MODULE, VersionUpdateData.class),
    CHECK_UPGRADE(ModuleID.VERSION, CommandType.TO_MODULE, Boolean.class),
    START_COMMON_UPGRADE(ModuleID.VERSION, CommandType.TO_MODULE, String.class),
    UPDATE_COMMON_UPGRADE_INFO(ModuleID.VERSION, CommandType.FROM_MODULE, CommonResult.class),
    UPDATE_ALL_UPGRADE_PROGRESS_INFO(ModuleID.VERSION, CommandType.FROM_MODULE, DownloadTaskInfo.class),
    UPDATE_SHOW_DOWNLOAD_PROGRESS(ModuleID.VERSION, CommandType.FROM_MODULE, Boolean.class),
    CANCEL_UPGRADE(ModuleID.VERSION, CommandType.TO_MODULE, new Class[0]),
    INSTALL_APP(ModuleID.VERSION, CommandType.FROM_MODULE, String.class),
    THIRDPARTY_DOWNLOAD_PROGRESS(ModuleID.VERSION, CommandType.FROM_MODULE, Long.class, Long.class),
    SET_SHAKE_SWITCH_SONG_ENABLED(ModuleID.GLOBAL, CommandType.TO_MODULE, Boolean.class),
    SET_SHAKE_SWITCH_SONG_SENSITIVITY(ModuleID.GLOBAL, CommandType.TO_MODULE, ShakeSensitivityType.class),
    CHECK_UNICOM_FLOW(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    CHECK_STATUS(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    SEND_VERIFY_CODE(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, String.class, Integer.class),
    SEND_VERIFY_CODE_RESULT(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, CommonResult.class),
    NET_AUTHORIZE(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    NET_AUTHORIZE_RESULT(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, CommonResult.class),
    OPEN_UNICOM_FLOW(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, String.class, String.class, String.class),
    OPEN_UNICOM_FLOW_RESULT(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, CommonResult.class),
    TRIAL_UNICOM_FLOW(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, String.class, String.class),
    TRIAL_UNICOM_FLOW_RESULT(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, CommonResult.class),
    UPDATE_UNICOM_FLOW_STATUS(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, Boolean.class),
    UNSUBSCRIBE_UNICOM_FLOW(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, String.class, Integer.class, String.class, String.class),
    UNSUBSCRIBE_UNICOM_FLOW_RESULT(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, CommonResult.class),
    CHECK_USE_GPRS_POPUP_DIALOG(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    UNICOM_FLOW_30M_DIALOG(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    CHECK_BEGIN_MONTH_POPUP_DIALOG(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    UNICOM_FLOW_POPUP_DIALOG(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, UnicomFlowDialogType.class),
    SAVE_UNICOM_TOTAL_FLOW(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    CLEAR_UNICOM_TOTAL_FLOW(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    GET_UNICOM_TOTAL_FLOW(ModuleID.UNICOM_FLOW, CommandType.TO_MODULE, new Class[0]),
    GET_UNICOM_TOTAL_FLOW_RESULT(ModuleID.UNICOM_FLOW, CommandType.FROM_MODULE, Long.class),
    NET_WORK_TYPE_CHANGED(ModuleID.MONITOR, CommandType.FROM_MODULE, new Class[0]),
    DOWNLOAD_STATE_CHANGED(ModuleID.MONITOR, CommandType.FROM_MODULE, DownloadTaskInfo.class, Task.ErrorCodeType.class),
    UPDATE_HEADSET_PLUGGED(ModuleID.MONITOR, CommandType.FROM_MODULE, new Class[0]),
    UPDATE_HEADSET_UNPLUGGED(ModuleID.MONITOR, CommandType.FROM_MODULE, new Class[0]),
    AUDIOEFFECT_CHANGED(ModuleID.MONITOR, CommandType.FROM_MODULE, new Class[0]),
    ALARM_INFO_CHANGED(ModuleID.GLOBAL, CommandType.FROM_MODULE, new Class[0]),
    CHECK_VERSION_COMPACT(ModuleID.VERSION_COMPACT, CommandType.TO_MODULE, new Class[0]),
    DO_VERSION_COMPACT(ModuleID.VERSION_COMPACT, CommandType.TO_MODULE, new Class[0]),
    DO_VERSION_COMPACT_STARTED(ModuleID.VERSION_COMPACT, CommandType.FROM_MODULE, new Class[0]),
    DO_VERSION_COMPACT_FINISHED(ModuleID.VERSION_COMPACT, CommandType.FROM_MODULE, new Class[0]),
    UPDATE_MUSIC_CATEGORY(ModuleID.MUSIC_LIBRARY, CommandType.FROM_MODULE, OnlineMusicCategoryResult.class),
    GET_MUSIC_CATEGORY(ModuleID.MUSIC_LIBRARY, CommandType.TO_MODULE, Integer.class, Integer.class),
    UPDATE_MUSIC_SUB_CATEGORY(ModuleID.MUSIC_LIBRARY, CommandType.FROM_MODULE, OnlineMusicSubCategoryResult.class),
    GET_MUSIC_SUB_CATEGORY(ModuleID.MUSIC_LIBRARY, CommandType.TO_MODULE, Long.class, Integer.class, Integer.class),
    PROPOSAL_FEEDBACK(ModuleID.FEEDBACK, CommandType.TO_MODULE, FeedbackItem.class),
    PROPOSAL_FEEDBACK_FINISH(ModuleID.FEEDBACK, CommandType.FROM_MODULE, BaseResultRest.class, FeedbackItem.class),
    REQUEST_FEEDBACK_LIST(ModuleID.FEEDBACK, CommandType.TO_MODULE, Long.class, Integer.class),
    REQUEST_FEEDBACK_LIST_FINISH(ModuleID.FEEDBACK, CommandType.FROM_MODULE, BaseResultRest.class, List.class, Boolean.class),
    REQUEST_FEEDBACK_MESSAGES(ModuleID.FEEDBACK, CommandType.TO_MODULE, String.class, Long.class, Boolean.class),
    REQUEST_FEEDBACK_MESSAGES_FINISH(ModuleID.FEEDBACK, CommandType.FROM_MODULE, BaseResultRest.class, List.class, Boolean.class),
    SEND_FEEDBACK_MESSAGE(ModuleID.FEEDBACK, CommandType.TO_MODULE, FeedbackMessage.class),
    SEND_FEEDBACK_MESSAGE_FINISH(ModuleID.FEEDBACK, CommandType.FROM_MODULE, BaseResultRest.class, FeedbackMessage.class),
    REQUEST_NEW_REPLYIED_FEEDBACKS(ModuleID.FEEDBACK, CommandType.TO_MODULE, Long.class),
    NEW_REPLYIED_FEEDBACKS_RECEIVED(ModuleID.FEEDBACK, CommandType.FROM_MODULE, List.class);
    
    private CommandType mCommandType;
    private ModuleID mModuleID;
    private Class[] mParamTypes;

    CommandID(ModuleID moduleID, CommandType commandType, Class... clsArr) {
        assertParamTypeNotArray(clsArr);
        this.mCommandType = commandType;
        this.mParamTypes = (Class[]) clsArr.clone();
        this.mModuleID = moduleID;
    }

    public ModuleID getModuleID() {
        return this.mModuleID;
    }

    public CommandType getCommandType() {
        return this.mCommandType;
    }

    public Class[] getParamTypes() {
        return this.mParamTypes;
    }

    private void assertParamTypeNotArray(Class<?>... clsArr) {
        if (EnvironmentUtils.AppConfig.getTestMode()) {
            for (Class<?> cls : clsArr) {
                if (cls.isArray()) {
                    throw new IllegalArgumentException("ParamType is Array, not Supported!");
                }
            }
        }
    }
}
