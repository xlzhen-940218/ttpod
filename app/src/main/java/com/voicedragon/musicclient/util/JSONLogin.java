package com.voicedragon.musicclient.util;

import android.content.Context;
import com.voicedragon.musicclient.api.MV;
import com.voicedragon.musicclient.api.LoginInfoTag;
import com.voicedragon.musicclient.api.RankBottomUrl;
import com.voicedragon.musicclient.orm.action.OrmAction;
import com.voicedragon.musicclient.util.MRadar;
import com.voicedragon.musicclient.util.MRadarUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JSONLogin {
    public static boolean getLoginJson(Context context, String result) {
        try {
            JSONObject json = new JSONObject(result);
            try {
                int status = json.getInt("status");
                switch (status) {
                    case 0:
                        try {
                            Logger.m2e("login fail", json.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Logger.m2e("login fail", "json msg fail");
                        }
                        return false;
                    case 1:
                        try {
                            MRadarUtil.LoginUtil.saveLoginInfo(context, MRadar.LoginInfo.LOGININFOUID, json.getString(OrmAction.UID));
                            MRadarUtil.LoginUtil.saveLoginInfo(context, MRadar.LoginInfo.LOGININFOTOKEN, json.getString("token"));
                            return true;
                        } catch (JSONException e1) {
                            Logger.m2e("login fail", "json uid or token fail ");
                            e1.printStackTrace();
                            return false;
                        }
                    default:
                        return false;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
            Logger.m2e("login fail", "json  fail");
            return false;
        }
    }

    public static boolean isSuccess(String result) {
        try {
            JSONObject json = new JSONObject(result);
            try {
                int status = json.getInt("status");
                switch (status) {
                    case 0:
                        return false;
                    case 1:
                        return true;
                    default:
                        return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static List<LoginInfoTag> getLoginInfoTagList(JSONArray array) {
        List<LoginInfoTag> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject obj = array.optJSONObject(i);
                JSONArray child = obj.optJSONArray("child");
                for (int j = 0; j < child.length(); j++) {
                    JSONObject o = child.optJSONObject(j);
                    LoginInfoTag tag = new LoginInfoTag();
                    tag.setFid(o.optString("fid"));
                    tag.setTag_id(o.optString("tag_id"));
                    tag.setTag_name(o.optString("tag_name"));
                    list.add(tag);
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public static List<MV> getMVList(JSONArray array) {
        List<MV> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            MV mv = new MV();
            try {
                JSONObject obj = array.getJSONObject(i);
                try {
                    mv.setArtist(obj.getString("artist"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    mv.setArtist("");
                }
                try {
                    mv.setImgUrl(obj.getString("cover_src"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    mv.setImgUrl("");
                }
                try {
                    mv.setMvName(obj.getString("title"));
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    mv.setMvName("");
                }
                try {
                    mv.setMvUrl(obj.getString(RankBottomUrl.URL));
                } catch (JSONException e4) {
                    e4.printStackTrace();
                    mv.setMvUrl("");
                }
                list.add(mv);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        return list;
    }

    public static List<MV> getMVListFromYouTube(JSONObject object) {
        List<MV> list = new ArrayList<>();
        try {
            JSONArray array = object.getJSONObject("feed").getJSONArray("entry");
            for (int i = 0; i < array.length(); i++) {
                MV mv = new MV();
                try {
                    JSONObject obj = array.getJSONObject(i);
                    mv.setArtist("");
                    try {
                        JSONArray thumbArray = obj.getJSONObject("media$group").getJSONArray("media$thumbnail");
                        if (thumbArray.length() > 0) {
                            mv.setImgUrl(thumbArray.getJSONObject(0).getString(RankBottomUrl.URL));
                        } else {
                            mv.setImgUrl("");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mv.setImgUrl("");
                    }
                    try {
                        mv.setMvName(obj.getJSONObject("media$group").getJSONObject("media$title").getString("$t"));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        mv.setMvName("");
                    }
                    try {
                        JSONArray linkArray = obj.getJSONArray("link");
                        if (linkArray.length() > 0) {
                            mv.setMvUrl(linkArray.getJSONObject(0).getString("href"));
                        } else {
                            mv.setMvUrl("");
                        }
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        mv.setMvUrl("");
                    }
                    list.add(mv);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (JSONException e22) {
            e22.printStackTrace();
        }
        return list;
    }
}
