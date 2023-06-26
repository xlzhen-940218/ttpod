package com.sds.android.sdk.lib.fakebuild;

import com.sds.android.cloudapi.ttpod.data.FindSongHotListData;
import com.sds.android.cloudapi.ttpod.data.FindSongModuleData;
import com.sds.android.cloudapi.ttpod.data.ForwardAction;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.FindSongModuleResult;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FakeFindSongModuleResult extends BaseFakeResult<FindSongModuleResult> {
    @Override
    public FindSongModuleResult build() {
        FindSongModuleResult findSongModuleResult = new FindSongModuleResult();
        findSongModuleResult.setCode(1);
        findSongModuleResult.setMessage("Success");

        findSongModuleResult.setData(getFindSongModuleData());
        findSongModuleResult.setmTTL(1000*20);
        return findSongModuleResult;
    }

    private FindSongModuleData getFindSongModuleData() {
        FindSongModuleData findSongModuleData = new FindSongModuleData();
        findSongModuleData.setmSongLists(getFindSongHotList());
        findSongModuleData.setmBanners(getOperationZone());
        return findSongModuleData;
    }

    private ArrayList<OperationZoneResult> getOperationZone() {
        ArrayList<OperationZoneResult> operationZoneResults = new ArrayList<>();
        String url = "https://y.qq.com/n/ryqq/toplist/26";
        String data = requestData(url);
        Pattern pattern = Pattern.compile("<span class=\"songlist__songname_txt\"><a.*?><img.*?class=\"songlist__pic\".*?src=\"(.*?)\" alt=\"(.*?)\"");
        Matcher matcher = pattern.matcher(data);
        ArrayList<RecommendData> recommendDataArrayList = new ArrayList<>();
        while (matcher.find()) {
            String author = matcher.group(2);
            RecommendData recommendData = new RecommendData();
            recommendData.setmPicUrl(matcher.group(1));
            recommendData.setmName(author);
            recommendData.setmId(new Random().nextLong());
            recommendData.setmTag(1);
            recommendData.setmDesc(author);
            ForwardAction forwardAction =  new ForwardAction();
            forwardAction.setmType(1);
            forwardAction.setmValue(matcher.group(1));
            recommendData.setmForwardAction(forwardAction);
            recommendDataArrayList.add(recommendData);
        }
        OperationZoneResult operationZoneResult = new OperationZoneResult();
        operationZoneResult.setDataList(recommendDataArrayList);
        operationZoneResults.add(operationZoneResult);
        return operationZoneResults;
    }

    private ArrayList<FindSongHotListResultNew> getFindSongHotList() {
        ArrayList<FindSongHotListResultNew> findSongHotListResultNews = new ArrayList<>();
        FindSongHotListResultNew findSongHotListResultNew = new FindSongHotListResultNew();
        String url = "https://y.qq.com/n/ryqq/toplist/26";
        String data = requestData(url);
        Pattern pattern = Pattern.compile("<span class=\"songlist__songname_txt\"><a.*?><img.*?class=\"songlist__pic\".*?src=\"(.*?)\" alt=\"(.*?)\"");

        Matcher matcher = pattern.matcher(data);
        ArrayList<FindSongHotListData> findSongHotListDataArrayList = new ArrayList<>();
        while (matcher.find()) {
            String author = matcher.group(2);
            FindSongHotListData findSongHotListData = new FindSongHotListData();
            findSongHotListData.setmAuthor(author);
            findSongHotListData.setmListenCount(new Random().nextInt(10000));
            findSongHotListData.setmRecommentType(1);
            findSongHotListData.setmRecommentAlgorithm(1);
            findSongHotListDataArrayList.add(findSongHotListData);
        }
        findSongHotListResultNew.setDataList(findSongHotListDataArrayList);
        findSongHotListResultNews.add(findSongHotListResultNew);
        return findSongHotListResultNews;
    }
}
