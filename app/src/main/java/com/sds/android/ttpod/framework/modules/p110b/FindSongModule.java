package com.sds.android.ttpod.framework.modules.p110b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.api.FindSongAPI;
import com.sds.android.cloudapi.ttpod.api.FindSongHotModuleAPI;
import com.sds.android.cloudapi.ttpod.api.RadioAPI;
import com.sds.android.cloudapi.ttpod.api.RankAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.DataListResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.ModuleRequestHelper;
import com.sds.android.ttpod.framework.modules.ResultConvert;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.b.a */
/* loaded from: classes.dex */
public class FindSongModule extends BaseModule {
    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.FIND_SONG;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public long timeOutInMills() {
        return 60000;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.GET_MUSIC_RANKS, ReflectUtils.m8375a(cls, "getMusicRanks", String.class));
        map.put(CommandID.GET_RANK_MUSIC_LIST, ReflectUtils.m8375a(cls, "getRankMusicList", Integer.class, Integer.class, String.class));
        map.put(CommandID.GET_FIND_SONG_CATEGORY_LIST, ReflectUtils.m8375a(cls, "getFindSongCategoryList", new Class[0]));
        map.put(CommandID.GET_SINGER_CATEGORY_LIST, ReflectUtils.m8375a(cls, "getSingerCategoryList", Integer.class));
        map.put(CommandID.GET_SINGER_CATEGORY_DETAIL, ReflectUtils.m8375a(cls, "getSingerCategoryDetail", Integer.class, Integer.class));
        map.put(CommandID.GET_SINGER_SONG_LIST, ReflectUtils.m8375a(cls, "getSingerSongList", String.class, Integer.class));
        map.put(CommandID.GET_SONG_CATEGORY_INFO, ReflectUtils.m8375a(cls, "getSongCategoryInfo", String.class));
        map.put(CommandID.GET_DAILY_RECOMMEND, ReflectUtils.m8375a(cls, "getDailyRecommend", Integer.class));
        map.put(CommandID.GET_SONG_CATEGORY_DETAIL, ReflectUtils.m8375a(cls, "getSongCategoryDetail", Integer.class, Integer.class, String.class));
        map.put(CommandID.GET_RADIO_CATEGORY_LIST, ReflectUtils.m8375a(cls, "getRadioCategoryList", new Class[0]));
        map.put(CommandID.GET_RADIO_CHANNEL_MUSIC_LIST, ReflectUtils.m8375a(cls, "getRadioChannelMusicList", String.class, Integer.class));
        map.put(CommandID.GET_POPULAR_SONG_LIST, ReflectUtils.m8375a(cls, "getPopularSongList", Integer.class, String.class));
        map.put(CommandID.GET_POPULAR_SONG_INTRODUCTION, ReflectUtils.m8375a(cls, "getIntroduction", Long.class));
        map.put(CommandID.GET_MV_LIST, ReflectUtils.m8375a(cls, "getMVList", Integer.class, Integer.class));
        map.put(CommandID.GET_MV_THUMBNAIL, ReflectUtils.m8375a(cls, "getMVThumbnail", String.class));
        map.put(CommandID.FIND_SONG_RECOMMEND_MODULE, ReflectUtils.m8375a(cls, "findSongRecommendModule", new Class[0]));
        map.put(CommandID.GET_FIND_SONG_HOT_SONGS, ReflectUtils.m8375a(cls, "getFindSongHotSongs", String.class, Integer.class, Integer.class));
        map.put(CommandID.GET_FIND_SONG_HOT_LIST, ReflectUtils.m8375a(cls, "getFindSongHotList", String.class, Integer.class, Integer.class));
        map.put(CommandID.GET_FIND_SONG_HOT_SINGERS, ReflectUtils.m8375a(cls, "getFindSongHotSingers", String.class, Integer.class, Integer.class, Boolean.class));
        map.put(CommandID.GET_FIND_SONG_HANDPICK, ReflectUtils.m8375a(cls, "getFindSongHandpick", new Class[0]));
        map.put(CommandID.GET_RECOMMEND_CONTENT, ReflectUtils.m8375a(cls, "getRecommendContent", Long.class));
    }

    public void getIntroduction(Long l) {
        ModuleRequestHelper.m4083a(FindSongAPI.m8906a(l.longValue()), CommandID.UPDATE_POPULAR_SONG_INTRODUCTION, id(), null);
    }

    public void getDailyRecommend(Integer num) {
        LogUtils.error("FindSongModule", "getDailyRecommend  not support");
    }

    public void getMusicRanks(String str) {
        ModuleRequestHelper.execute(RankAPI.getMusicRanksRequest(), CommandID.UPDATE_MUSIC_RANKS, id(), null, str);
    }

    public void getRankMusicList(Integer num, Integer num2, String str) {
        ModuleRequestHelper.execute(RankAPI.m8832a(num.intValue(), num2.intValue()), CommandID.UPDATE_RANK_MUSIC_LIST, id(), new ResultConvert<OnlineMediaItemsResult, MediaItemListResult>() { // from class: com.sds.android.ttpod.framework.modules.b.a.1
            @Override // com.sds.android.ttpod.framework.modules.ResultConvert
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public MediaItemListResult mo4042a(OnlineMediaItemsResult onlineMediaItemsResult) {
                return FindSongModule.this.m4512a(onlineMediaItemsResult);
            }
        }, str);
    }

    public void getFindSongCategoryList() {
        m4511a(FindSongAPI.m8910a(), CommandID.UPDATE_FIND_SONG_CATEGORY_LIST);
    }

    public void getSingerCategoryList(Integer num) {
        ModuleRequestHelper.m4083a(FindSongAPI.m8909a(num.intValue()), CommandID.UPDATE_SINGER_CATEGORY_LIST, id(), null);
    }

    public void getSingerCategoryDetail(Integer num, Integer num2) {
        ModuleRequestHelper.m4083a(FindSongAPI.m8902b(num.intValue(), num2.intValue()), CommandID.UPDATE_SINGER_CATEGORY_DETAIL, id(), null);
    }

    public void getSingerSongList(String str, Integer num) {
        ModuleRequestHelper.m4083a(FindSongAPI.m8904a(str, num.intValue()), CommandID.UPDATE_SINGER_SONG_LIST, id(), new ResultConvert<OnlineMediaItemsResult, MediaItemListResult>() { // from class: com.sds.android.ttpod.framework.modules.b.a.3
            @Override // com.sds.android.ttpod.framework.modules.ResultConvert
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public MediaItemListResult mo4042a(OnlineMediaItemsResult onlineMediaItemsResult) {
                return FindSongModule.this.m4512a(onlineMediaItemsResult);
            }
        });
    }

    public void getSongCategoryInfo(String str) {
        ModuleRequestHelper.execute(RadioAPI.m8837a(), CommandID.UPDATE_SONG_CATEGORY_INFO, id(), null, str);
    }

    public void getSongCategoryDetail(Integer num, Integer num2, String str) {
        ModuleRequestHelper.execute(RadioAPI.m8836a(num.intValue(), num2.intValue()), CommandID.UPDATE_SONG_CATEGORY_DETAIL, id(), new ResultConvert<OnlineMediaItemsResult, BaseResult>() { // from class: com.sds.android.ttpod.framework.modules.b.a.4
            @Override // com.sds.android.ttpod.framework.modules.ResultConvert
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public BaseResult mo4042a(OnlineMediaItemsResult onlineMediaItemsResult) {
                return FindSongModule.this.m4512a(onlineMediaItemsResult);
            }
        }, str);
    }

    public void getRadioCategoryList() {
        LogUtils.debug("FindSongModule", "RadioModule.getRadioCategoryList----->");
        ModuleRequestHelper.m4083a(RadioAPI.m8834b(), CommandID.UPDATE_RADIO_CATEGORY_LIST, id(), null);
    }

    public void getRadioChannelMusicList(String str, Integer num) {
        m4511a(RadioAPI.m8835a(str, num.intValue()), CommandID.UPDATE_RADIO_CHANNEL_MUSIC_LIST);
    }

    public void getPopularSongList(Integer num, String str) {
        ModuleRequestHelper.m4083a(FindSongAPI.m8907a(num.intValue(), str), CommandID.UPDATE_POPULAR_SONG_LIST, id(), new ResultConvert<OnlineMediaItemsResult, MediaItemListResult>() { // from class: com.sds.android.ttpod.framework.modules.b.a.5
            @Override // com.sds.android.ttpod.framework.modules.ResultConvert
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public MediaItemListResult mo4042a(OnlineMediaItemsResult onlineMediaItemsResult) {
                return FindSongModule.this.m4512a(onlineMediaItemsResult);
            }
        });
    }

    public void getMVList(Integer num, Integer num2) {
        ModuleRequestHelper.m4083a(FindSongAPI.m8901c(num.intValue(), num2.intValue()), CommandID.UPDATE_MV_LIST, id(), null);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sds.android.ttpod.framework.modules.b.a$6] */
    public void getMVThumbnail(final String str) {
        new Thread("extractMvThumbnail") { // from class: com.sds.android.ttpod.framework.modules.b.a.6
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Bitmap decodeFile = BitmapFactory.decodeFile(TTPodConfig.getMvCachePath() + File.separator + (FileUtils.m8401k(str) + ".jpg"));
                if (decodeFile == null && SDKVersionUtils.m8373a()) {
                    decodeFile = ThumbnailUtils.createVideoThumbnail(str, 3);
                }
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_MV_THUMBNAIL, str, decodeFile), ModuleID.FIND_SONG);
            }
        }.start();
    }

    /* renamed from: a */
    protected void m4511a(final Request request, final CommandID commandID) {
        request.m8544a((RequestCallback) new RequestCallback<DataListResult>() { // from class: com.sds.android.ttpod.framework.modules.b.a.7
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(DataListResult dataListResult) {
                FindSongModule.this.m4510a(commandID, dataListResult);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(DataListResult dataListResult) {
                FindSongModule.this.m4510a(commandID, (DataListResult) null);
                //ErrorStatistic.m5236c(request.m8532e());
            }
        });
    }

    /* renamed from: a */
    private void m4509a(CommandID commandID, Object... objArr) {
        LogUtils.debug("FindSongModule", "FindSongModule.notifyDataCreated---> responseId: " + commandID + "  responseData: " + objArr);
        if (objArr != null && m4504a(objArr[0])) {
            objArr[0] = m4502a((ArrayList) objArr[0]);
        }
        CommandCenter.getInstance().m4595b(new Command(commandID, objArr), ModuleID.FIND_SONG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4510a(CommandID commandID, DataListResult dataListResult) {
        ArrayList arrayList = null;
        if (dataListResult != null) {
            arrayList = dataListResult.getDataList();
        }
        m4509a(commandID, arrayList);
    }

    /* renamed from: a */
    private boolean m4504a(Object obj) {
        if (obj == null || !(obj instanceof ArrayList)) {
            return false;
        }
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.size() > 0) {
            return arrayList.get(0) instanceof OnlineMediaItem;
        }
        return false;
    }

    /* renamed from: a */
    private ArrayList m4502a(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            OnlineMediaItem onlineMediaItem = (OnlineMediaItem) it.next();
            arrayList2.add(MediaItemUtils.m4716a(onlineMediaItem));
            if (onlineMediaItem.getAuditionUrls() == null || onlineMediaItem.getAuditionUrls().size() == 0) {
                linkedList.add(Long.valueOf(onlineMediaItem.getSongId()));
            } else if (onlineMediaItem.getDownloadUrls() == null || onlineMediaItem.getDownloadUrls().size() == 0) {
                linkedList2.add(Long.valueOf(onlineMediaItem.getSongId()));
            }
        }
        if (linkedList.size() > 0) {
            //new //SSystemEvent("SYS_EXCEPTION", "not_url").append("origin", "song").append("song_id", linkedList.toString()).post();
            linkedList.clear();
        }
        if (linkedList2.size() > 0) {
            //new //SSystemEvent("SYS_EXCEPTION", "not_url").append("origin", "download").append("song_id", linkedList2.toString()).post();
            linkedList2.clear();
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public MediaItemListResult m4512a(OnlineMediaItemsResult onlineMediaItemsResult) {
        MediaItemListResult mediaItemListResult = new MediaItemListResult();
        mediaItemListResult.m4515a(m4502a((ArrayList) onlineMediaItemsResult.getDataList()));
        mediaItemListResult.m4516a(onlineMediaItemsResult.getExtra());
        mediaItemListResult.setCode(onlineMediaItemsResult.getCode());
        mediaItemListResult.setMessage(onlineMediaItemsResult.getMessage());
        return mediaItemListResult;
    }

    public void findSongRecommendModule() {
        ModuleRequestHelper.m4083a(FindSongHotModuleAPI.m8900a(), CommandID.UPDATE_FIND_SONG_RECOMMEND_MODULE, id(), null);
    }

    public void getFindSongHotSongs(String str, Integer num, Integer num2) {
        ModuleRequestHelper.m4083a(FindSongHotModuleAPI.m8898a(str, num.intValue(), num2.intValue()), CommandID.UPDATE_GET_FIND_SONG_HOT_SONGS, id(), null);
    }

    public void getFindSongHotList(String str, Integer num, Integer num2) {
        ModuleRequestHelper.m4083a(FindSongHotModuleAPI.m8895b(str, num.intValue(), num2.intValue()), CommandID.UPDATE_GET_FIND_SONG_HOT_LIST, id(), null);
    }

    public void getFindSongHotSingers(final String str, final Integer num, final Integer num2, final Boolean bool) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.b.a.8
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                if (bool.booleanValue()) {
                    ArrayList m4513a = FindSongModule.this.m4513a(2, 5);
                    arrayList = FindSongModule.this.m4501b(m4513a);
                    arrayList2 = m4513a;
                } else {
                    arrayList = arrayList3;
                    arrayList2 = arrayList4;
                }
                StringBuilder sb = new StringBuilder();
                if (arrayList2.isEmpty()) {
                    sb.append("[]");
                } else {
                    sb.append("[");
                    for (int i = 0; i < arrayList2.size() - 1; i++) {
                        sb.append((String) arrayList2.get(i)).append(",");
                    }
                    if (arrayList2.size() > 1) {
                        sb.append((String) arrayList2.get(arrayList2.size() - 1));
                    }
                    sb.append("]");
                }
                LogUtils.debug("FindSongModule", "getFindSongHotSingers localSingerNames=" + arrayList2 + ",localSingerIds=" + arrayList);
                SingerListResult m8531f = FindSongHotModuleAPI.m8897a(str, num.intValue(), num2.intValue(), sb.toString(), arrayList).execute();
                LogUtils.debug("FindSongModule", "getFindSongHotSingers " + m8531f.getDataList());
                CommandCenter.getInstance().m4595b(new Command(CommandID.UPDATE_GET_FIND_SONG_HOT_SINGERS, m8531f), ModuleID.FIND_SONG);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public ArrayList<String> m4513a(int i, int i2) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<GroupItem> queryGroupItems = MediaStorage.queryGroupItems(sContext, GroupType.DEFAULT_ARTIST);
        int min = Math.min(i, queryGroupItems.size());
        Collections.sort(queryGroupItems, new Comparator<GroupItem>() { // from class: com.sds.android.ttpod.framework.modules.b.a.9
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(GroupItem groupItem, GroupItem groupItem2) {
                return groupItem2.getCount().intValue() - groupItem.getCount().intValue();
            }
        });
        for (int i3 = 0; i3 < min; i3++) {
            GroupItem groupItem = queryGroupItems.get(i3);
            String trim = groupItem.getName().trim();
            if (groupItem.getCount().intValue() >= i2 && TTTextUtils.isValidateMediaString(trim) && !"未知,群星".contains(trim)) {
                arrayList.add(trim);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public ArrayList<Long> m4501b(ArrayList<String> arrayList) {
        ArrayList<Long> arrayList2 = new ArrayList<>();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(m4503a(it.next()));
        }
        return arrayList2;
    }

    /* renamed from: a */
    private Long m4503a(String str) {
        Long l;
        if (StringUtils.isEmpty(str)) {
            return 0L;
        }
        OnlineMediaItemsResult m8531f = FindSongAPI.m8903a(str, 1, 1).execute();
        ArrayList<OnlineMediaItem> dataList = m8531f.getDataList();
        if (m8531f.getCode() != 1 || dataList.size() <= 0) {
            l = 0L;
        } else {
            l = Long.valueOf(dataList.get(0).getArtistId());
        }
        LogUtils.debug("FindSongModule", "getArtistIdBySingerName=" + str + ",artistId=" + l);
        return l;
    }

    public void getFindSongHandpick() {
        ModuleRequestHelper.m4083a(FindSongHotModuleAPI.m8896b(), CommandID.UPDATE_GET_FIND_SONG_HANDPICK, id(), null);
    }

    public void requestVoiceOfChinaResult() {
        FindSongHotModuleAPI.m8894c().m8544a(new RequestCallback<OperationZoneResult>() { // from class: com.sds.android.ttpod.framework.modules.b.a.2
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(OperationZoneResult operationZoneResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_OPERATION_ZONE_RESULT, operationZoneResult), ModuleID.FIND_SONG);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OperationZoneResult operationZoneResult) {
                CommandCenter.getInstance().m4604a(new Command(CommandID.UPDATE_OPERATION_ZONE_RESULT, operationZoneResult), ModuleID.FIND_SONG);
            }
        });
    }

    public void getRecommendContent(Long l) {
        ModuleRequestHelper.m4083a(FindSongHotModuleAPI.m8899a(l.longValue()), CommandID.UPDATE_RECOMMEND_CONTENT, id(), null);
    }
}
