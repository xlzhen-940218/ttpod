package com.sds.android.cloudapi.ttpod.result;
import com.google.gson.annotations.SerializedName;

import com.sds.android.sdk.lib.request.BaseResult;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: classes.dex */
public class AudioEffectDetailResult extends BaseResult {
    @SerializedName(value = "data")
    private LinkedHashMap<String, List<AudioEffect>> mAudioEffects = new LinkedHashMap<>();

    public LinkedHashMap<String, List<AudioEffect>> getAudioEffects() {
        return this.mAudioEffects;
    }

    /* loaded from: classes.dex */
    public static class AudioEffect {
        @SerializedName(value = "eq")
        private short[] mEqualizer = {0};

        public short[] getEqualizer() {
            return this.mEqualizer;
        }
    }
}
