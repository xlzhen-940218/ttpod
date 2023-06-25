package com.sds.android.ttpod.media.audiofx;

import com.sds.android.sdk.lib.util.StringUtils;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public class TTEqualizer extends TTAudioEffect {

    /* loaded from: classes.dex */
    public static class Settings {
        private static final String GENRE_CUSTOM = "自定义/custom";
        private static final int MIN_BANDS_NUMBER = 5;
        private short[] mBandLevels;
        private String mName;
        private short mNumBands;

        public Settings() {
            this.mName = "自定义/custom";
            this.mNumBands = (short) 0;
            this.mBandLevels = null;
        }

        public Settings(String str, short s, short[] sArr) {
            this.mName = "自定义/custom";
            this.mNumBands = (short) 0;
            this.mBandLevels = null;
            this.mName = str;
            this.mNumBands = s;
            if (sArr.length < this.mNumBands) {
                throw new IllegalArgumentException("band levels array length lower ran bands number");
            }
            this.mBandLevels = sArr;
        }

        public Settings(String str) {
            this.mName = "自定义/custom";
            this.mNumBands = (short) 0;
            this.mBandLevels = null;
            if (str == null) {
                throw new IllegalArgumentException("settings: " + str);
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, "=;");
            if (stringTokenizer.countTokens() < 5) {
                throw new IllegalArgumentException("settings: " + str);
            }
            try {
                String nextToken = stringTokenizer.nextToken();
                if (!nextToken.equals("Equalizer")) {
                    throw new IllegalArgumentException("invalid settings for Equalizer: " + nextToken);
                }
                String nextToken2 = stringTokenizer.nextToken();
                if (!nextToken2.equals("mName")) {
                    throw new IllegalArgumentException("invalid key name: " + nextToken2);
                }
                this.mName = stringTokenizer.nextToken();
                String nextToken3 = stringTokenizer.nextToken();
                if (!nextToken3.equals("mNumBands")) {
                    throw new IllegalArgumentException("invalid key name: " + nextToken3);
                }
                this.mNumBands = Short.parseShort(stringTokenizer.nextToken());
                if (stringTokenizer.countTokens() != this.mNumBands * 2) {
                    throw new IllegalArgumentException("settings: " + str);
                }
                this.mBandLevels = new short[this.mNumBands];
                for (int i = 0; i < this.mNumBands; i++) {
                    String nextToken4 = stringTokenizer.nextToken();
                    if (!nextToken4.equals("band" + (i + 1) + "Level")) {
                        throw new IllegalArgumentException("invalid key name: " + nextToken4);
                    }
                    this.mBandLevels[i] = Short.parseShort(stringTokenizer.nextToken());
                }
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("invalid value for key: " + ((String) null));
            }
        }

        public String getName() {
            return this.mName;
        }

        public void setName(String str) {
            this.mName = str;
        }

        public void setNumberOfBands(short s) {
            this.mNumBands = s;
            if (this.mBandLevels == null || this.mBandLevels.length < this.mNumBands) {
                this.mBandLevels = new short[this.mNumBands];
            }
        }

        public short getNumberOfBands() {
            return this.mNumBands;
        }

        public short[] getBandLevels() {
            return this.mBandLevels;
        }

        public String toString() {
            String str = "Equalizer;mName=" + (StringUtils.isEmpty(this.mName) ? "自定义/custom" : this.mName) + ";mNumBands=" + Short.toString(this.mNumBands);
            for (int i = 0; i < this.mNumBands; i++) {
                str = str.concat(";band" + (i + 1) + "Level=" + Short.toString(this.mBandLevels[i]));
            }
            return str;
        }

        public boolean isFlat() {
            if (this.mBandLevels != null) {
                for (short s : this.mBandLevels) {
                    if (s != 0) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public TTEqualizer() {
        super(EffectUUID.EFFECT_ID_EQUALIZER);
    }

    public short getNumberOfBands() {
        short[] sArr = {0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{0}, sArr);
        return sArr[0];
    }

    public void getBandLevelRange(short[] sArr) {
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{1}, sArr);
    }

    public short getNumberOfPresets() {
        short[] sArr = {0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{7}, sArr);
        return sArr[0];
    }

    public String getPresetName(short s) {
        short[] sArr = new short[32];
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{8, s}, sArr);
        char[] cArr = new char[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            cArr[i] = (char) sArr[i];
        }
        return new String(cArr);
    }

    public int setPreset(short s) {
        return TTAudioEffect.setEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{6}, new short[]{s});
    }

    public int setBandLevel(short s, short s2) {
        return TTAudioEffect.setEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{2, s}, new short[]{s2});
    }

    public short getBandLevel(short s) {
        short[] sArr = {0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{2, s}, sArr);
        return sArr[0];
    }

    public int getCenterFreq(short s) {
        short[] sArr = {0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{3, s}, sArr);
        return sArr[0];
    }

    public void getBandFreqRange(short s, int[] iArr) {
        short[] sArr = {0, 0};
        TTAudioEffect.getEffectParams(EffectUUID.EFFECT_ID_EQUALIZER, new int[]{4, s}, sArr);
        iArr[0] = sArr[0];
        iArr[1] = sArr[1];
    }
}
