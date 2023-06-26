package com.sds.android.cloudapi.ttpod.p055a;

import com.sds.android.cloudapi.ttpod.data.AudioEffectItemData;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectCommResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;

import com.sds.android.sdk.lib.request.GetMethodRequest;
import com.sds.android.sdk.lib.request.PostMethodRequest;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.util.JSONUtils;


/* renamed from: com.sds.android.cloudapi.ttpod.a.e */
/* loaded from: classes.dex */
public class CloudAudioEffectAPI {
    /* renamed from: a */
    public static Request<AudioEffectUserResult> m8929a(String str) {
        return new GetMethodRequest(AudioEffectUserResult.class, "http://ae.hotchanson.com/user/info/" + str, "eq");
    }

    /* renamed from: a */
    public static Request<AudioEffectItemResult> m8930a(long j, int i, int i2) {
        return new GetMethodRequest(AudioEffectItemResult.class, "http://ae.hotchanson.com/public/query_by_song_id", "eq").putParams("song_id", Long.valueOf(j)).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<AudioEffectItemResult> m8927a(String str, String str2, int i, int i2) {
        return new GetMethodRequest(AudioEffectItemResult.class, "http://ae.hotchanson.com/public/query_by_song_info", "eq").putParams("song_name", str).putParams("singer_name", str2).putParams("page", Integer.valueOf(i)).putParams("size", Integer.valueOf(i2));
    }

    /* renamed from: a */
    public static Request<AudioEffectAddResult> m8926a(String str, String str2, int i, long j, String str3, String str4, int i2, String str5, AudioEffectItemData audioEffectItemData) {
        return new PostMethodRequest(AudioEffectAddResult.class, "http://ae.hotchanson.com/ae/create/" + str).m8552a("_id", str2).m8552a("style", Integer.valueOf(i)).m8552a("song_id", Long.valueOf(j)).m8552a("song_name", str3).m8552a("singer_name", str4).m8552a("output", Integer.valueOf(i2)).m8552a("device", str5).m8552a("audio_effect", JSONUtils.toJson(audioEffectItemData));
    }

    /* renamed from: a */
    public static Request<AudioEffectAddResult> m8925a(String str, String str2, int i, String str3, String str4, int i2, String str5, AudioEffectItemData audioEffectItemData) {
        return new PostMethodRequest(AudioEffectAddResult.class, "http://ae.hotchanson.com/ae/create/" + str, "eq").m8552a("_id", str2).m8552a("style", Integer.valueOf(i)).m8552a("song_name", str3).m8552a("singer_name", str4).m8552a("output", Integer.valueOf(i2)).m8552a("device", str5).m8552a("audio_effect", JSONUtils.toJson(audioEffectItemData));
    }

    /* renamed from: a */
    public static Request<AudioEffectCommResult> m8928a(String str, String str2) {
        return new GetMethodRequest(AudioEffectCommResult.class, "http://ae.hotchanson.com/ae/pick/" + str + "/" + str2, "eq");
    }

    /* renamed from: b */
    public static Request<AudioEffectCommResult> m8924b(String str, String str2) {
        return new GetMethodRequest(AudioEffectCommResult.class, "http://ae.hotchanson.com/ae/bind/" + str + "/" + str2, "eq");
    }
}
