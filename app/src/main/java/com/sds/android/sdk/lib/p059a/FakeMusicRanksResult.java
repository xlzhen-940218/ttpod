package com.sds.android.sdk.lib.p059a;

import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.sdk.lib.fakeentity.Itemlist;
import com.sds.android.sdk.lib.fakeentity.QQMusicSearchBean;
import com.sds.android.sdk.lib.fakeentity.Song;
import com.sds.android.sdk.lib.util.JSONUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.text.Regex;

public class FakeMusicRanksResult extends BaseFakeResult<MusicRanksResult> {

    @Override
    MusicRanksResult build() {
        MusicRanksResult musicRanksResult = new MusicRanksResult();
        musicRanksResult.setCode(1);
        musicRanksResult.setMessage("Success");

        musicRanksResult.setDataList(getHotSinger());
        musicRanksResult.setmTTL(1000*20);
        return musicRanksResult;
    }

    private ArrayList<MusicRank> getHotSinger() {
        ArrayList<MusicRank> musicRanks = new ArrayList<>();
        /*String url = "https://y.qq.com/n/ryqq/singer_list";
        String data = requestData(url);
        Pattern pattern = Pattern.compile("<h3.*?><a.*?>(.*?)</a></h3>");
        List<String> singers = new ArrayList<>();
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String singer = matcher.group(1);
            singers.add(singer);
        }*/
        List<String> singers = new ArrayList<>();
        singers.add("周杰伦");
        singers.add("林俊杰");
        singers.add("邓紫棋");
        singers.add("薛之谦");
        singers.add("汪苏泷");
        singers.add("陈奕迅");
        singers.add("苏星婕");
        singers.add("蔡健雅");
        singers.add("张碧晨");
        singers.add("王源");
        for (String singer : singers) {
            musicRanks.add(requestSingerData(singer));
        }
        return musicRanks;
    }

    private MusicRank requestSingerData(String singer) {
        String url = String.format("https://c.y.qq.com/splcloud/fcgi-bin/smartbox_new.fcg?key=%s", singer);
        String data = requestData(url);
        QQMusicSearchBean bean = JSONUtils.fromJson(data, QQMusicSearchBean.class);

        MusicRank musicRank = new MusicRank();

        ArrayList<MusicRank.SimpleSongInfo> simpleSongInfos = new ArrayList<>();
        for (Itemlist song : bean.getData().getSong().getItemlist()) {
            MusicRank.SimpleSongInfo simpleSongInfo = new MusicRank.SimpleSongInfo();
            simpleSongInfo.setSongName(song.getName());
            simpleSongInfo.setSingerName(song.getSinger());
            simpleSongInfos.add(simpleSongInfo);
        }
        if (bean.getData().getAlbum().getItemlist() != null && bean.getData().getAlbum().getItemlist().size() > 0)
            musicRank.setmPicUrl(bean.getData().getAlbum().getItemlist().get(0).getPic());
        musicRank.setSongList(simpleSongInfos);
        musicRank.setmTitle(singer);
        return musicRank;
    }

}
