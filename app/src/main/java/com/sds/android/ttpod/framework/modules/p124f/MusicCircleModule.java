package com.sds.android.ttpod.framework.modules.p124f;

import com.sds.android.cloudapi.ttpod.data.Comment;
import com.sds.android.cloudapi.ttpod.data.MessageCollectItem;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.CelebriteAPI;
import com.sds.android.cloudapi.ttpod.p055a.FriendsAPI;
import com.sds.android.cloudapi.ttpod.p055a.MessageCollectAPI;
import com.sds.android.cloudapi.ttpod.p055a.MusicCircleRecommendAPI;
import com.sds.android.cloudapi.ttpod.p055a.NoticeAPI;
import com.sds.android.cloudapi.ttpod.p055a.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.p055a.PostAPI;
import com.sds.android.cloudapi.ttpod.p055a.PrivateMessageAPI;
import com.sds.android.cloudapi.ttpod.p055a.ShakeAPI;
import com.sds.android.cloudapi.ttpod.p055a.UserSystemAPI;
import com.sds.android.cloudapi.ttpod.result.CircleFirstPublishListResult;
import com.sds.android.cloudapi.ttpod.result.MessageCollectListResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.ModuleRequestHelper;
import com.sds.android.ttpod.framework.modules.ResultConvert;
import com.sds.android.ttpod.framework.p106a.C1791q;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.f.c */
/* loaded from: classes.dex */
public final class MusicCircleModule extends BaseModule {
    public static final long TUID_TTPOD = 204713344;

    /* renamed from: a */
    private TTPodUser f6134a;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.MUSIC_CIRCLE;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
        this.f6134a = Preferences.m2954aq();
        Preferences.m3019a(PreferencesID.USER_INFO, new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.framework.modules.f.c.1
            @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
            /* renamed from: a */
            public void mo2553a(PreferencesID preferencesID) {
                MusicCircleModule.this.f6134a = Preferences.m2954aq();
            }
        });
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.REQUEST_MUSIC_POSTER_LIST, ReflectUtils.m8375a(cls, "requestMusicPosterList", new Class[0]));
        map.put(CommandID.REQUEST_NEW_SONG_PUBLISH_LIST, ReflectUtils.m8375a(cls, "requestNewSongPublishList", new Class[0]));
        map.put(CommandID.REQUEST_MORE_NEW_SONG_PUBLISH_LIST, ReflectUtils.m8375a(getClass(), "requestMoreNewSongPublishList", Integer.class, Integer.class));
        map.put(CommandID.REQUEST_NEW_SONG_CATEGORY_PUBLISH_LIST, ReflectUtils.m8375a(getClass(), "requestNewSongCategoryPublishList", new Class[0]));
        map.put(CommandID.REQUEST_NEW_ALBUM_PUBLISH_LIST, ReflectUtils.m8375a(getClass(), "requestNewAlbumPublishList", Integer.class, Integer.class));
        map.put(CommandID.REQUEST_SHACK_USERS, ReflectUtils.m8375a(cls, "requestShakeUsers", Float.class, Float.class, String.class));
        map.put(CommandID.REQUEST_ALIKE_USERS, ReflectUtils.m8375a(cls, "requestAlikeUsers", String.class));
        map.put(CommandID.REQUEST_STAR_USERS_BY_RANK, ReflectUtils.m8375a(cls, "requestStarUsersByRank", Integer.class, Integer.class, Integer.class, String.class));
        map.put(CommandID.REQUEST_STAR_USERS_BY_CATEGORY, ReflectUtils.m8375a(cls, "requestStarUsersByCategory", Integer.class, Integer.class, Integer.class, String.class));
        map.put(CommandID.REQUEST_STAR_CATEGORIES, ReflectUtils.m8375a(cls, "requestStarCategories", String.class));
        map.put(CommandID.MUSICCIRCLE_SEARCH, ReflectUtils.m8375a(cls, "search", String.class, String.class));
        map.put(CommandID.FOLLOW_FRIEND, ReflectUtils.m8375a(cls, "followFriend", Long.class, String.class));
        map.put(CommandID.UNFOLLOW_FRIEND, ReflectUtils.m8375a(cls, "unFollowFriend", Long.class, String.class));
        map.put(CommandID.REQUEST_FOLLOWING_FRIEND_IDS, ReflectUtils.m8375a(cls, "requestFollowingFriendIds", Long.class, String.class));
        map.put(CommandID.REQUEST_FOLLOWING_FRIENDS, ReflectUtils.m8375a(cls, "requestFollowingFriends", Long.class, Integer.class, Integer.class, Long.class, String.class));
        map.put(CommandID.REQUEST_FOLLOWER_FRIENDS, ReflectUtils.m8375a(cls, "requestFollowerFriends", Long.class, Integer.class, Integer.class, Long.class, String.class));
        map.put(CommandID.REQUEST_USER_INFO_BY_IDS, ReflectUtils.m8375a(cls, "requestFollowerFriends", Collection.class, String.class));
        map.put(CommandID.REQUEST_SYSTEM_NOTICES, ReflectUtils.m8375a(cls, "requestSystemNotices", Long.class, Integer.class, String.class));
        map.put(CommandID.POST_COMMENT, ReflectUtils.m8375a(cls, "postComment", Long.class, String.class, Long.class, Long.class, String.class));
        map.put(CommandID.DELETE_COMMENT, ReflectUtils.m8375a(cls, "deleteComment", Long.class, Comment.class, String.class));
        map.put(CommandID.RE_POST, ReflectUtils.m8375a(cls, "rePost", Long.class, Long.class, String.class, String.class));
        map.put(CommandID.REQUEST_PRIVATE_MESSAGES, ReflectUtils.m8375a(cls, "requestPrivateMessages", Long.class, Integer.class, String.class));
        map.put(CommandID.QUERY_PRIVATE_MESSAGES_CONTENT, ReflectUtils.m8375a(cls, "requestPrivateMessagesContent", Long.class, Long.class, Integer.class, String.class));
        map.put(CommandID.DELETE_PRIVATE_MESSAGE, ReflectUtils.m8375a(cls, "deletePrivateMessage", String.class, String.class));
        map.put(CommandID.SEND_PRIVATE_MESSAGE, ReflectUtils.m8375a(cls, "sendPrivateMessage", Long.class, String.class, String.class));
        map.put(CommandID.DELETE_PRIVATE_MESSAGES, ReflectUtils.m8375a(cls, "deletePrivateMessages", Long.class, String.class));
        map.put(CommandID.REQUEST_USER_POST_IDS, ReflectUtils.m8375a(cls, "requestUserPostIds", Long.class, String.class));
        map.put(CommandID.REQUEST_TIMELINE_USER_POST_IDS, ReflectUtils.m8375a(cls, "requestTimelinePostIds", String.class));
        map.put(CommandID.REQUEST_COMMENT_IDS_BY_POST_ID, ReflectUtils.m8375a(cls, "requestCommentsByPostId", Long.class, String.class));
        map.put(CommandID.REQUEST_COMMENT_INFOS_BY_IDS, ReflectUtils.m8375a(cls, "requestCommentInfosByIds", Collection.class, String.class));
        map.put(CommandID.REQUEST_POST_SONG_BY_IDS, ReflectUtils.m8375a(cls, "requestPostSongByIds", List.class, String.class));
        map.put(CommandID.REQUEST_POST_DETAIL_BY_ID, ReflectUtils.m8375a(cls, "requestPostDetailById", Collection.class, String.class));
        map.put(CommandID.REQUEST_POST_INFOS_BY_ID, ReflectUtils.m8375a(cls, "requestPostInfosById", Collection.class, String.class));
        map.put(CommandID.REQUEST_CELEBRITY_POSTS, ReflectUtils.m8375a(cls, "requestCelebrityPosts", Long.class));
        map.put(CommandID.REQUEST_RECOMMEND_CELEBRATE_POST_IDS, ReflectUtils.m8375a(cls, "requestRecommendCelebratePostIds", String.class));
        map.put(CommandID.REQUEST_FIRST_PUBLISH_CELEBRATE_POST_IDS, ReflectUtils.m8375a(cls, "requestFirstPublishCelebratePostIds", String.class));
        map.put(CommandID.REQUEST_POSTS_BY_CATEGORY_ID, ReflectUtils.m8375a(cls, "requestPostsByCategoryId", Long.class, Integer.class, String.class));
        map.put(CommandID.REQUEST_REPOST_NOTICES, ReflectUtils.m8375a(cls, "requestRePostNotices", Integer.class, Integer.class, String.class));
        map.put(CommandID.REQUEST_COMMENT_NOTICES, ReflectUtils.m8375a(cls, "requestCommentNotices", Integer.class, Integer.class, String.class));
        map.put(CommandID.REQUEST_NEW_FOLLOWER_NOTICES, ReflectUtils.m8375a(cls, "requestNewFollowerNotices", String.class));
        map.put(CommandID.DELETE_NOTICE, ReflectUtils.m8375a(cls, "deleteNotice", Notice.class, String.class));
        map.put(CommandID.ADD_FAVORITE_POSTS, ReflectUtils.m8375a(cls, "addFavoritePosts", List.class, String.class));
        map.put(CommandID.REMOVE_FAVORITE_POSTS, ReflectUtils.m8375a(cls, "removeFavoritePosts", List.class, String.class));
        map.put(CommandID.IS_FAVORITE_POST, ReflectUtils.m8375a(cls, "isFavoritePost", Long.class));
        map.put(CommandID.REQUEST_FAVORITE_POSTS, ReflectUtils.m8375a(cls, "requestFavoritePosts", String.class));
        map.put(CommandID.IS_FOLLOWED, ReflectUtils.m8375a(cls, "isFollowed", Long.class));
        map.put(CommandID.SET_LOGIN_UID, ReflectUtils.m8375a(cls, "setLoginUid", Long.class));
        map.put(CommandID.CHANGE_POST_REPLY_COUNT, ReflectUtils.m8375a(cls, "changePostReplyCount", Post.class));
        map.put(CommandID.CHANGE_POST_REPOST_COUNT, ReflectUtils.m8375a(cls, "changePostRepostCount", Post.class));
        map.put(CommandID.REQUEST_FAVORITE_SONG_LIST_POSTS, ReflectUtils.m8375a(cls, "requestFavoriteSongListPosts", new Class[0]));
        map.put(CommandID.ADD_POST_TO_MEDIA_GROUP, ReflectUtils.m8375a(cls, "addPostToMediaGroup", Post.class));
        map.put(CommandID.ADD_POSTS_TO_MEDIA_GROUP, ReflectUtils.m8375a(cls, "addPostsToMediaGroup", List.class));
        map.put(CommandID.ADD_LISTENER_COUNT, ReflectUtils.m8375a(cls, "addListenerCount", Long.class));
        map.put(CommandID.REQUEST_PRIVATE_CUSTOM_POSTS, ReflectUtils.m8375a(cls, "requestRecommendPostPosts", Integer.class, Integer.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <Result extends BaseResult> Result m4056a(Request<Result> request) {
        LogUtils.info("MusicCircleModule", "request begin lookNetProblem %s", request.m8532e());
        Result m8531f = request.execute();
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(m8531f != null);
        objArr[1] = Integer.valueOf(m8531f != null ? m8531f.getCode() : 0);
        LogUtils.info("MusicCircleModule", "request end lookNetProblem result not null=%b code=%d", objArr);
        if (!m8531f.isSuccess()) {
            //ErrorStatistic.m5232g(request.m8532e());
        }
        return m8531f;
    }

    public void requestRecommendPostPosts(Integer num, Integer num2) {
        ModuleRequestHelper.m4083a(MusicCircleRecommendAPI.m8879a(num.intValue(), num2.intValue()), CommandID.UPDATE_PRIVATE_CUSTOM_POSTS, id(), null);
    }

    public void requestMusicPosterList() {
        ModuleRequestHelper.m4083a(MusicCircleRecommendAPI.m8880a(), CommandID.UPDATE_MUSIC_POSTER_LIST, ModuleID.MUSIC_CIRCLE, null);
    }

    public void requestNewSongPublishList() {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.12
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_NEW_SONG_PUBLISH_LIST, ((CircleFirstPublishListResult) MusicCircleModule.m4056a(MusicCircleRecommendAPI.m8878b())).getDataList()), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestMoreNewSongPublishList(Integer num, Integer num2) {
        ModuleRequestHelper.m4083a(MusicCircleRecommendAPI.m8877b(num.intValue(), num2.intValue()), CommandID.UPDATE_MORE_NEW_SONG_PUBLISH_LIST, id(), null);
    }

    public void requestNewSongCategoryPublishList() {
        ModuleRequestHelper.m4083a(MusicCircleRecommendAPI.m8876c(), CommandID.UPDATE_NEW_SONG_CATEGORY_PUBLISH_LIST, id(), null);
    }

    public void requestNewAlbumPublishList(Integer num, Integer num2) {
        ModuleRequestHelper.m4083a(MusicCircleRecommendAPI.m8875c(num.intValue(), num2.intValue()), CommandID.UPDATE_NEW_ALBUM_PUBLISH_LIST, id(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public String m4045f() {
        return this.f6134a == null ? "" : this.f6134a.getAccessToken();
    }

    public void requestShakeUsers(final Float f, final Float f2, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.23
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SHAKE_RESULT, MusicCircleModule.m4056a(ShakeAPI.m8831a(MusicCircleModule.this.m4045f(), f.floatValue(), f2.floatValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestAlikeUsers(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.34
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_ALIKE_USER_LIST, MusicCircleModule.m4056a(UserSystemAPI.m8940e(MusicCircleModule.this.m4045f())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestStarUsersByRank(final Integer num, final Integer num2, final Integer num3, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.42
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_STAR_USER_LIST_BY_RANK, MusicCircleModule.m4056a(CelebriteAPI.m8931b(num.intValue(), num2.intValue(), num3.intValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestStarCategories(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.43
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_STAR_CATEGORIES_LIST, MusicCircleModule.m4056a(CelebriteAPI.m8933a()), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestStarUsersByCategory(final Integer num, final Integer num2, final Integer num3, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.44
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_STAR_USER_lIST_BY_CATEGORY, MusicCircleModule.m4056a(CelebriteAPI.m8932a(num.intValue(), num2.intValue(), num3.intValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void search(final String str, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.45
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEARCH_RESULT, MusicCircleModule.m4056a(UserSystemAPI.m8947b(MusicCircleModule.this.m4045f(), str)), str2), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void followFriend(final Long l, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.46
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FOLLOW_FRIEND, FollowManager.m4064a().m4063a(l.longValue()), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void unFollowFriend(final Long l, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.2
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_UNFOLLOW_FRIEND, FollowManager.m4064a().m4061b(l.longValue()), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public Boolean isFollowed(Long l) {
        if (this.f6134a == null) {
            return false;
        }
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.3
            @Override // java.lang.Runnable
            public void run() {
                FollowManager.m4064a().m4062a(new FollowManager.InterfaceC1901a() { // from class: com.sds.android.ttpod.framework.modules.f.c.3.1
                    @Override // com.sds.android.ttpod.framework.modules.p124f.FollowManager.InterfaceC1901a
                    /* renamed from: a */
                    public void mo4041a() {
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SYNC_FOLLOWING_FINISHED, new Object[0]), ModuleID.MUSIC_CIRCLE);
                    }
                });
            }
        });
        return Boolean.valueOf(FollowManager.m4064a().m4060c(l.longValue()));
    }

    public void requestFollowingFriendIds(final Long l, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.4
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FOLLOWING_FRIEND_ID_LIST, MusicCircleModule.m4056a(FriendsAPI.m8893a(l.longValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFollowingFriends(final Long l, final Integer num, final Integer num2, final Long l2, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.5
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FOLLOWING_FRIEND_LIST, MusicCircleModule.m4056a(FriendsAPI.m8892a(l.longValue(), num.intValue(), num2.intValue(), l2.longValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFollowerFriends(final Long l, final Integer num, final Integer num2, final Long l2, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.6
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FOLLOWER_FRIENDS, MusicCircleModule.m4056a(FriendsAPI.m8889b(l.longValue(), num.intValue(), num2.intValue(), l2.longValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFollowerFriends(final Collection<Long> collection, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.7
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FOLLOWER_FRIEND_LIST_BY_IDS, MusicCircleModule.m4056a(FriendsAPI.m8890a(collection)), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestSystemNotices(final Long l, final Integer num, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.8
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SYSTEM_NOTICE_LIST, MusicCircleModule.m4056a(NoticeAPI.m8871a(MusicCircleModule.this.m4045f(), l.longValue(), num.intValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestRePostNotices(Integer num, Integer num2, String str) {
        m4052a(NoticeType.REPOST, num, num2, str);
    }

    public void requestCommentNotices(Integer num, Integer num2, String str) {
        m4052a(NoticeType.COMMENT, num, num2, str);
    }

    /* renamed from: a */
    private void m4052a(final NoticeType noticeType, final Integer num, final Integer num2, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.9
            @Override // java.lang.Runnable
            public void run() {
                switch (noticeType) {
                    case COMMENT:
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_COMMENT_NOTICE_LIST, MusicCircleModule.m4056a(NoticeAPI.m8872a(MusicCircleModule.this.m4045f(), NoticeType.COMMENT.value(), num.intValue(), num2.intValue())), str), ModuleID.MUSIC_CIRCLE);
                        return;
                    case REPOST:
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_REPOST_NOTICE_LIST, MusicCircleModule.m4056a(NoticeAPI.m8872a(MusicCircleModule.this.m4045f(), NoticeType.REPOST.value(), num.intValue(), num2.intValue())), str), ModuleID.MUSIC_CIRCLE);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void requestNewFollowerNotices(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.10
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_NEW_FOLLOWER_NOTICE_LIST, MusicCircleModule.m4056a(NoticeAPI.m8869b(MusicCircleModule.this.m4045f())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPrivateMessages(final Long l, final Integer num, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.11
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PRIVATE_MESSAGE_LIST, MusicCircleModule.m4056a(PrivateMessageAPI.m8841a(MusicCircleModule.this.m4045f(), l.longValue(), num.intValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPrivateMessagesContent(final Long l, final Long l2, final Integer num, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.13
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_PRIVATE_MESSAGE_CONTEXT_LIST, MusicCircleModule.m4056a(PrivateMessageAPI.m8840a(MusicCircleModule.this.m4045f(), l.longValue(), l2.longValue(), num.intValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void deletePrivateMessage(final String str, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.14
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_DELETE_PRIVATE_MESSAGE, MusicCircleModule.m4056a(PrivateMessageAPI.m8838a(MusicCircleModule.this.m4045f(), str)), str2), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void sendPrivateMessage(final Long l, final String str, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.15
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SEND_PRIVATE_MESSAGE, MusicCircleModule.m4056a(PrivateMessageAPI.m8839a(MusicCircleModule.this.m4045f(), l.longValue(), str)), str2), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void deletePrivateMessages(final Long l, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.16
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_DELETE_PRIVATE_MESSAGE_LIST, MusicCircleModule.m4056a(PrivateMessageAPI.m8842a(MusicCircleModule.this.m4045f(), l.longValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public CommonResult postComment(final Long l, final String str, final Long l2, final Long l3, final String str2) {
        String trim = str.trim();
        if (trim.length() == 0) {
            return new CommonResult(ErrCode.ErrArgument, "请输入有效评论");
        }
        if (C1791q.m4653a(sContext).m4652a(trim)) {
            return new CommonResult(ErrCode.ErrArgument, "内容含有敏感词，提交失败");
        }
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.17
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_COMMENT_POSTED, MusicCircleModule.m4056a(PostAPI.m8849a(MusicCircleModule.this.m4045f(), l.longValue(), str, l2.longValue(), l3.longValue())), str2), ModuleID.MUSIC_CIRCLE);
            }
        });
        return new CommonResult(ErrCode.ErrNone, "Success");
    }

    public void deleteComment(final Long l, final Comment comment, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.18
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_COMMENT_DELETED, MusicCircleModule.m4056a(PostAPI.m8851a(MusicCircleModule.this.m4045f(), l.longValue(), comment.getId())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void rePost(final Long l, final Long l2, final String str, final String str2) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.19
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_RE_POSTED, MusicCircleModule.m4056a(PostAPI.m8850a(MusicCircleModule.this.m4045f(), l.longValue(), l2.longValue(), str)), str2), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestUserPostIds(final Long l, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.20
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_USER_POST_ID_LIST, MusicCircleModule.m4056a(PostAPI.m8843b(MusicCircleModule.this.m4045f(), l.longValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestTimelinePostIds(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.21
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_TIMELINE_USER_POST_IDS, MusicCircleModule.m4056a(PostAPI.m8844b(MusicCircleModule.this.m4045f())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestCommentsByPostId(final Long l, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.22
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_COMMENT_ID_LIST_BY_POST_ID, MusicCircleModule.m4056a(PostAPI.m8852a("", l.longValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestCommentInfosByIds(final Collection collection, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.24
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_COMMENT_INFO_LIST_BY_ID_LIST, MusicCircleModule.m4056a(PostAPI.m8848a(MusicCircleModule.this.m4045f(), collection)), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPostSongByIds(List<Long> list, String str) {
        if (Preferences.m2997aJ()) {
            ModuleRequestHelper.execute(OnlineMediaItemAPI.m8864b(list), CommandID.UPDATE_POST_SONG_BY_IDS, id(), new ResultConvert<OnlineMediaItemsResult, MediaItemListResult>() { // from class: com.sds.android.ttpod.framework.modules.f.c.25
                @Override // com.sds.android.ttpod.framework.modules.ResultConvert
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public MediaItemListResult mo4042a(OnlineMediaItemsResult onlineMediaItemsResult) {
                    return MusicCircleModule.this.m4057a(onlineMediaItemsResult);
                }
            }, str);
        } else {
            ModuleRequestHelper.execute(OnlineMediaItemAPI.m8867a(list), CommandID.UPDATE_POST_SONG_BY_IDS, id(), new ResultConvert<OnlineMediaItemsResult, MediaItemListResult>() { // from class: com.sds.android.ttpod.framework.modules.f.c.26
                @Override // com.sds.android.ttpod.framework.modules.ResultConvert
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public MediaItemListResult mo4042a(OnlineMediaItemsResult onlineMediaItemsResult) {
                    return MusicCircleModule.this.m4057a(onlineMediaItemsResult);
                }
            }, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public MediaItemListResult m4057a(OnlineMediaItemsResult onlineMediaItemsResult) {
        MediaItemListResult mediaItemListResult = new MediaItemListResult();
        mediaItemListResult.m4515a(m4051a(onlineMediaItemsResult.getDataList()));
        mediaItemListResult.m4516a(onlineMediaItemsResult.getExtra());
        mediaItemListResult.setCode(onlineMediaItemsResult.getCode());
        mediaItemListResult.setMessage(onlineMediaItemsResult.getMessage());
        return mediaItemListResult;
    }

    /* renamed from: a */
    private ArrayList m4051a(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof OnlineMediaItem) {
                arrayList2.add(MediaItemUtils.m4716a((OnlineMediaItem) next));
            }
        }
        return arrayList2;
    }

    public void requestPostDetailById(Collection collection, String str) {
        ModuleRequestHelper.execute(PostAPI.m8847a(collection), CommandID.UPDATE_POST_DETAIL_BY_ID, id(), null, str);
    }

    public void requestPostInfosById(final Collection collection, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.27
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_POST_INFO_LIST_BY_ID, (PostResult) MusicCircleModule.m4056a(PostAPI.m8847a(collection)), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestCelebrityPosts(Long l) {
        ModuleRequestHelper.m4083a(PostAPI.m8855a(l.longValue()), CommandID.UPDATE_DISCOVERY_POST_INFO_LIST_BY_ID, id(), null);
    }

    public void requestRecommendCelebratePostIds(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.28
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_RECOMMEND_CELEBRATE_POST_ID_LIST, MusicCircleModule.m4056a(PostAPI.m8856a()), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFirstPublishCelebratePostIds(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.29
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FIRST_PUBLISH_CELEBRATE_POST_ID_LIST, MusicCircleModule.m4056a(PostAPI.m8846b()), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void deleteNotice(final Notice notice, final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.30
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_NOTICE_DELETED, MusicCircleModule.m4056a(NoticeAPI.m8870a(MusicCircleModule.this.m4045f(), notice.getNoticeId())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void addFavoritePosts(final List<Long> list, final String str) {
        if (this.f6134a != null) {
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.31
                @Override // java.lang.Runnable
                public void run() {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_ADD_FAVORITE_POSTS, FavoritePostManager.m4070a().m4067a(list, MusicCircleModule.this.f6134a.getAccessToken()), str), ModuleID.MUSIC_CIRCLE);
                    PostResult postResult = (PostResult) MusicCircleModule.m4056a(PostAPI.m8847a(list));
                    if (postResult.getDataList().size() > 0) {
                        Iterator<Post> it = postResult.getDataList().iterator();
                        while (it.hasNext()) {
                            MusicCircleModule.this.addPostToMediaGroup(it.next());
                        }
                    }
                }
            });
        }
    }

    public void removeFavoritePosts(final List<Long> list, final String str) {
        if (this.f6134a != null) {
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.32
                @Override // java.lang.Runnable
                public void run() {
                    CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_REMOVE_FAVORITE_POSTS, FavoritePostManager.m4070a().m4065b(list, MusicCircleModule.this.f6134a.getAccessToken()), str), ModuleID.MUSIC_CIRCLE);
                    String buildMusicCircleFavGroupIDPrefix = MediaStorage.buildMusicCircleFavGroupIDPrefix();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        String str2 = buildMusicCircleFavGroupIDPrefix + ((Long) it.next()).longValue();
                        if (MediaStorage.isGroupExisted(MusicCircleModule.sContext, str2)) {
                            CommandCenter.getInstance().m4596b(new Command(CommandID.DELETE_GROUP, str2));
                        }
                    }
                }
            });
        }
    }

    public Boolean isFavoritePost(Long l) {
        if (this.f6134a == null) {
            return false;
        }
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.33
            @Override // java.lang.Runnable
            public void run() {
                FavoritePostManager.m4070a().m4068a(new FavoritePostManager.InterfaceC1900a() { // from class: com.sds.android.ttpod.framework.modules.f.c.33.1
                    @Override // com.sds.android.ttpod.framework.modules.p124f.FavoritePostManager.InterfaceC1900a
                    /* renamed from: a */
                    public void mo4040a() {
                        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_SYNC_FAVORITE_POST_FINISHED, new Object[0]), ModuleID.MUSIC_CIRCLE);
                    }
                }, MusicCircleModule.this.f6134a.getAccessToken());
            }
        });
        return Boolean.valueOf(FavoritePostManager.m4070a().m4066b(l.longValue()));
    }

    public void requestFavoriteSongListPosts() {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.35
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<MessageCollectItem> dataList = ((MessageCollectListResult) MusicCircleModule.m4056a(MessageCollectAPI.m8883a(MusicCircleModule.this.m4045f()))).getDataList();
                ArrayList arrayList = new ArrayList();
                if (!dataList.isEmpty()) {
                    List m4667a = Pager.m4667a(dataList, 20);
                    Pager pager = new Pager();
                    pager.m4665b(m4667a.size());
                    pager.m4668a(0);
                    pager.m4663c(0);
                    for (Object messageCollectItem : (List) m4667a.get(pager.m4669a())) {
                        arrayList.add(Long.valueOf(((MessageCollectItem)messageCollectItem).getId()));
                    }
                }
                MusicCircleModule.this.requestPostInfosById(arrayList, "favorite");
            }
        });
    }

    public void requestFavoritePosts(final String str) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.36
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_FAVORITE_COLLECT_LIST, MusicCircleModule.m4056a(MessageCollectAPI.m8883a(MusicCircleModule.this.m4045f())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPostsByCategoryId(final Long l, final Integer num, final String str) {
        DebugUtils.m8426a(l, "timestamp not null");
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.37
            @Override // java.lang.Runnable
            public void run() {
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_POSTS_BY_CATEGORY_ID, MusicCircleModule.m4056a(PostAPI.m8854a(l.longValue(), num.intValue())), str), ModuleID.MUSIC_CIRCLE);
            }
        });
    }

    public void setLoginUid(Long l) {
        FollowManager.m4064a().m4059d(l.longValue());
        FavoritePostManager.m4070a().m4069a(l.longValue());
    }

    public void changePostReplyCount(Post post) {
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_POST_REPLY_COUNT, post), ModuleID.MUSIC_CIRCLE);
    }

    public void changePostRepostCount(Post post) {
        CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_POST_REPOST_COUNT, post), ModuleID.MUSIC_CIRCLE);
    }

    public void addPostToMediaGroup(final Post post) {
        final String buildMusicCircleFavGroupID = MediaStorage.buildMusicCircleFavGroupID(post.getPostId());
        OnlineMediaUtils.m4672b(OnlineMediaUtils.m4676a(PostUtils.m4027b(post)), new OnlineMediaUtils.InterfaceC1790a<List<MediaItem>>() { // from class: com.sds.android.ttpod.framework.modules.f.c.38
            @Override // com.sds.android.ttpod.framework.p106a.OnlineMediaUtils.InterfaceC1790a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo4039a(List<MediaItem> list) {
                if (!list.isEmpty()) {
                    MediaStorage.insertGroup(MusicCircleModule.sContext, post.getSongListName(), buildMusicCircleFavGroupID, GroupType.CUSTOM_ONLINE);
                    MediaStorage.insertMediaItems(MusicCircleModule.sContext, buildMusicCircleFavGroupID, list);
                    CommandCenter.getInstance().m4595b(new Command(CommandID.ADD_POST_TO_MEDIA_GROUP_FINISHED, buildMusicCircleFavGroupID), ModuleID.MUSIC_CIRCLE);
                }
            }
        });
    }

    public void addPostsToMediaGroup(List<Post> list) {
        if (list == null) {
            throw new IllegalArgumentException("postList should not be null");
        }
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<List<Post>, Boolean>(list) { // from class: com.sds.android.ttpod.framework.modules.f.c.39
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Boolean mo1981a(List<Post> list2) {
                if (Preferences.m2997aJ()) {
                    m4031c(list2);
                } else {
                    m4032b(list2);
                }
                return true;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a */
            public void postExecute(Boolean bool) {
                CommandCenter.getInstance().m4595b(new Command(CommandID.ADD_POSTS_TO_MEDIA_GROUP_FINISHED, new Object[0]), ModuleID.MUSIC_CIRCLE);
            }

            /* renamed from: b */
            private void m4032b(List<Post> list2) {
                for (Post post : list2) {
                    m4034a(post.getSongListName(), MediaStorage.buildMusicCircleFavGroupID(post.getPostId()), m4036a(OnlineMediaItemAPI.m8867a(m4037a(post)).execute()));
                }
            }

            /* renamed from: c */
            private void m4031c(List<Post> list2) {
                for (Post post : list2) {
                    m4034a(post.getSongListName(), MediaStorage.buildMusicCircleFavGroupID(post.getPostId()), m4036a(OnlineMediaItemAPI.m8864b(m4037a(post)).execute()));
                }
            }

            /* renamed from: a */
            private List<Long> m4037a(Post post) {
                return OnlineMediaUtils.m4676a(PostUtils.m4027b(post));
            }

            /* renamed from: a */
            private List<MediaItem> m4036a(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                ArrayList arrayList = new ArrayList();
                for (OnlineMediaItem onlineMediaItem : dataList) {
                    if (onlineMediaItem != null) {
                        arrayList.add(MediaItemUtils.m4716a(onlineMediaItem));
                    }
                }
                return arrayList;
            }

            /* renamed from: a */
            private void m4034a(String str, String str2, List<MediaItem> list2) {
                if (!list2.isEmpty()) {
                    MediaStorage.insertGroup(MusicCircleModule.sContext, str, str2, GroupType.CUSTOM_ONLINE);
                    MediaStorage.insertMediaItems(MusicCircleModule.sContext, str2, list2);
                }
            }
        });
    }

    public void addListenerCount(final Long l) {
        if (l.longValue() != 0) {
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.f.c.40
                @Override // java.lang.Runnable
                public void run() {
                    PostAPI.m8845b(l.longValue()).execute();
                }
            });
        }
    }
}
