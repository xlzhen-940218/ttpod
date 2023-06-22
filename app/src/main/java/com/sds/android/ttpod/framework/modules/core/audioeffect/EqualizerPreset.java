package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.ttpod.fragment.audioeffect.EqualizerHandpickFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.core.audioeffect.e */
/* loaded from: classes.dex */
public class EqualizerPreset {

    /* renamed from: a */
    private static final Map<String, short[]> f5885a = new LinkedHashMap();

    /* renamed from: b */
    private static final List<String> f5886b = new ArrayList();

    static {
        f5885a.put("普通/Normal", new short[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        f5885a.put("流行/Pop", new short[]{600, 800, 400, 600, 900, 1000, 300, 600, 300, 900});
        f5885a.put("R&B/R&B", new short[]{0, 0, 400, 200, -200, 0, 300, 0, 500, 200});
        f5885a.put("嘻哈/Hip-Hop", new short[]{600, 400, -400, 600, 100, -500, 600, -400, 400, -100});
        f5885a.put("说唱/Rap", new short[]{300, 100, 400, 300, 500, -500, 500, -300, 0, 600});
        f5885a.put("布鲁斯/Blues", new short[]{-100, 600, 500, 200, 200, 0, 200, 300, 200, 300});
        f5885a.put("法国香颂/Chanson", new short[]{100, 0, 0, 100, 0, 300, 300, 200, 300, 200});
        f5885a.put("灵魂/Soul", new short[]{-100, 600, 500, 400, -100, 400, 100, -100, 0, 0});
        f5885a.put("雷鬼/Reggae", new short[]{0, 400, 300, 100, 0, 0, 200, 200, 400, 0});
        f5885a.put("前卫/Progressive", new short[]{300, 200, 100, 200, 200, -200, -100, 200, 500, 300});
        f5885a.put("福音/Gospel", new short[]{0, 200, 200, 100, 100, 200, -100, 200, 300, 0});
        f5885a.put("拉丁/Latin Pop", new short[]{100, 500, 500, 0, -100, 100, 0, 300, 400, 500});
        f5885a.put("独立流行/Indie Pop", new short[]{400, -300, 0, 400, 100, 200, -500, 500, 600, 500});
        f5885a.put("网络流行/New Media", new short[]{0, 200, 300, 200, 100, 200, 100, 200, 100, 200});
        f5885a.put("另类流行/Alternative Pop", new short[]{200, 300, 300, 100, 200, -200, -200, 300, 100, -100});
        f5885a.put("Teen Pop/Teen-Pop", new short[]{100, -100, -100, 200, 0, -100, 200, 300, 200, 300});
        f5885a.put("Dance-Pop/Dance-Pop", new short[]{100, 400, 300, 200, 0, -100, -100, 200, 200, 500});
        f5885a.put("J-POP/J-POP", new short[]{0, 0, 0, 200, -100, 0, 100, 300, -200, 200});
        f5885a.put("K-POP/K-POP", new short[]{100, -100, 400, 400, -100, -100, 100, 400, 400, 100});
        f5885a.put("Dream-pop/Dream-pop", new short[]{-100, -200, -200, -300, 0, 200, 400, -100, 200, -300});
        f5885a.put("Synthpop/Synthpop", new short[]{-300, -200, 0, -100, 400, 0, 300, 600, 400, 400});
        f5885a.put("Urban/Urban", new short[]{0, -100, 0, 100, 0, 100, 100, 100, 100, 100});
        f5885a.put("摇滚/Rock", new short[]{800, 1100, 400, 600, -200, 200, 300, 0, 1200, 1400});
        f5885a.put("流行摇滚/Pop Rock", new short[]{200, 300, 400, 300, 100, 100, 200, 400, 300, 200});
        f5885a.put("英伦摇滚/Brit-pop", new short[]{100, 300, 300, 300, 200, 100, -100, 400, 400, 100});
        f5885a.put("民谣摇滚/FolkRock", new short[]{0, 0, 100, 100, 100, 100, 100, 200, 0, 0});
        f5885a.put("乡村摇滚/Country Rock", new short[]{0, -100, -100, 0, 100, 0, -100, -100, 0, 0});
        f5885a.put("独立摇滚/Indie Rock", new short[]{0, 100, 200, 0, -100, -100, 100, 100, 100, -100});
        f5885a.put("艺术摇滚/ArtRock", new short[]{-100, 200, 200, 200, 100, 100, 100, 100, 200, 200});
        f5885a.put("硬摇滚/Hard Rock", new short[]{0, 200, -100, 200, -100, 0, 300, 500, 200, 300});
        f5885a.put("软摇滚/Soft Rock", new short[]{-100, 100, 100, 0, 0, 200, 200, 100, 100, 0});
        f5885a.put("另类摇滚/Alternative Rock", new short[]{100, 400, 400, 300, -100, 100, 200, 400, 400, 500});
        f5885a.put("后摇/PostRock", new short[]{100, 300, -200, -100, -100, 0, 0, 100, 300, 0});
        f5885a.put("重金属/Heavy Metal", new short[]{-100, 100, 200, 0, 0, 400, 300, 200, 300, 300});
        f5885a.put("金属/Metal", new short[]{-100, 200, 300, 200, 300, 100, 200, 200, 100, -100});
        f5885a.put("流行金属/Pop Metal", new short[]{-100, 200, 300, 300, 300, 200, 200, 300, 300, -100});
        f5885a.put("新金属/Nu-Metal", new short[]{-200, 300, 100, -100, 0, 0, 300, 300, 200, 100});
        f5885a.put("死亡金属/Death Metal", new short[]{100, 500, 400, 500, -100, 300, 300, 400, 600, 600});
        f5885a.put("新古典金属/Neo - Classical Metal", new short[]{0, 100, 200, 300, -100, -100, 200, 300, 200, 0});
        f5885a.put("前卫金属/Progressive Metal", new short[]{0, 100, 0, 200, 100, 0, 300, 200, 300, 200});
        f5885a.put("工业金属/Industrial Metal", new short[]{0, 0, 0, 100, 100, 0, 200, 300, 300, 300});
        f5885a.put("黑金属/Black Metal", new short[]{-100, 300, 200, -100, 0, 0, 300, 300, 200, 100});
        f5885a.put("歌特金属/Gothic Metal", new short[]{0, -100, 300, 200, 100, 0, 200, 300, 0, 0});
        f5885a.put("说唱金属/Rap Metal", new short[]{100, 300, 300, 300, 100, 200, 300, 300, 200, -100});
        f5885a.put("交响金属/Symphonic Metal", new short[]{-100, 100, 200, 100, 200, 100, 200, 100, 100, -100});
        f5885a.put("硬核/Hardcore", new short[]{0, 200, 300, 300, 300, 100, 200, 300, 400, -200});
        f5885a.put("后硬核/Post Hardcore", new short[]{-100, 300, 400, 300, 300, 100, 200, 300, 400, -100});
        f5885a.put("朋克/Punk", new short[]{0, 200, 300, 200, 0, 400, 600, 400, 0, 500});
        f5885a.put("后朋克/Post Punk", new short[]{100, 300, 200, 400, 200, 400, 0, -100, 200, -100});
        f5885a.put("流行朋克/Punk - Pop", new short[]{-300, -200, 300, -100, -200, 300, 400, 500, 400, 400});
        f5885a.put("后垃圾/Post Grunge", new short[]{0, 200, 300, 200, -100, -100, 200, 200, 300, 0});
        f5885a.put("迷幻/Psychedelic", new short[]{100, 300, 300, 400, 200, 100, -100, 300, 200, 100});
        f5885a.put("哥特/Gothic", new short[]{300, 300, 300, 300, 200, 100, 300, 400, 400, 300});
        f5885a.put("车库/Garage", new short[]{100, 200, 300, 200, 200, 100, 100, 200, 400, 400});
        f5885a.put("实验音乐/Experimental", new short[]{-100, 0, 100, 0, 0, 0, 0, 100, 100, -100});
        f5885a.put("新浪潮/New Wave", new short[]{100, 200, 200, 300, 200, 300, 0, -100, 100, -100});
        f5885a.put("垃圾乐/Grunge", new short[]{-100, 200, 300, 100, 0, 300, 300, 200, 300, 300});
        f5885a.put("悲核/Sadcore", new short[]{0, 200, 100, 0, 0, 100, 200, 200, 100, 0});
        f5885a.put("EMO/EMO", new short[]{0, 200, 200, 300, 200, 200, 200, 300, 400, 200});
        f5885a.put("Blues Rock/Blues - Rock", new short[]{-200, 100, 200, 200, 0, 200, 300, 200, 200, 0});
        f5885a.put("Funk/Funk", new short[]{400, 600, 600, 300, -100, 0, 0, 400, 300, 0});
        f5885a.put("民谣/Folk", new short[]{-100, -100, 100, -100, 200, -200, 100, 0, 400, 400});
        f5885a.put("校园民谣/Campus Folk", new short[]{-400, -200, -100, -100, -100, -200, 0, 200, -200, 100});
        f5885a.put("城市民谣/Urban Folk", new short[]{-200, -200, -100, -100, 0, 200, 100, 200, 200, -100});
        f5885a.put("新民谣/Neo - Folk", new short[]{-200, -300, 100, -100, 200, 200, 100, 100, 300, 300});
        f5885a.put("乡村/Country", new short[]{-200, -300, -100, -100, 0, 200, -200, 200, 200, -200});
        f5885a.put("蓝草/Bluegrass", new short[]{-200, -100, 0, 100, 0, 200, 200, 200, 200, 100});
        f5885a.put("凯尔特/Celtic", new short[]{-100, 0, 100, -100, 200, 100, 300, 300, 300, 200});
        f5885a.put("Anti - folk/Anti - folk", new short[]{-100, 200, 100, 100, 200, 100, 100, 0, 300, 200});
        f5885a.put("舞曲/Dance", new short[]{1000, 1500, 900, 400, 300, -400, 1300, -500, 1300, 700});
        f5885a.put("超重低音/Bass", new short[]{1000, 1300, 300, -500, -700, 100, 300, 200, 500, 300});
        f5885a.put("迪士高/Disco", new short[]{0, 400, 500, 100, 0, 400, 600, 300, -200, 500});
        f5885a.put("DJ/DJ", new short[]{-200, 0, 500, 600, 0, 200, 100, 300, 600, 400});
        f5885a.put("电子舞曲/E-Dance", new short[]{100, 400, 200, 400, 0, -200, 400, 200, 500, 500});
        f5885a.put("混音/Remix", new short[]{100, 200, 100, 300, 0, 200, 200, 0, 300, 200});
        f5885a.put("碎拍/Breakbeat", new short[]{-100, 200, 200, 300, 100, 100, 100, 300, 300, 300});
        f5885a.put("House/House", new short[]{-100, 0, 300, 400, -100, 100, 200, 400, 500, 300});
        f5885a.put("Trance/Trance", new short[]{300, 400, 400, 100, -100, 300, 500, 300, 400, 500});
        f5885a.put("Progressive House/Progressive-House", new short[]{-100, 0, 300, 300, 100, 300, 400, 400, 400, 300});
        f5885a.put("Progressive Trance/Progressive-Trance", new short[]{200, 300, 400, 100, -100, 300, 500, 400, 400, 500});
        f5885a.put("Drum & Bass/Drum & Bass", new short[]{200, 400, 400, 400, 0, 100, 300, 0, 200, 0});
        f5885a.put("Club Dance/Club-Dance", new short[]{100, 300, 300, 400, -200, -200, 300, 100, 500, 300});
        f5885a.put("电子/Electronica", new short[]{-200, -100, -200, -200, -100, 100, 200, 400, 400, 300});
        f5885a.put("驰放/Chillout", new short[]{-100, -100, -200, -200, -100, 200, 200, 300, 200, 100});
        f5885a.put("Trip-Hop/Trip-Hop", new short[]{-200, -100, -100, 0, 0, 100, 100, 200, 100, 0});
        f5885a.put("沙发/Lounge", new short[]{-200, -200, -100, -200, 100, 200, 200, 100, 100, 0});
        f5885a.put("Darkwave/Darkwave", new short[]{100, 100, 100, 200, 200, 100, 200, 0, -100, -100});
        f5885a.put("Ambient/Ambient", new short[]{-200, 100, 200, 0, -100, 100, 200, 200, 400, 400});
        f5885a.put("古典/Classic", new short[]{-300, 100, 700, 800, 500, 400, 300, 700, 200, 500});
        f5885a.put("室内乐/Chamber Music", new short[]{200, 0, 300, -100, 200, 0, 100, 300, 400, 300});
        f5885a.put("独奏/Solo", new short[]{200, 0, 400, 0, 0, -100, 0, 500, 500, 0});
        f5885a.put("歌剧/Opera", new short[]{300, -100, 200, 200, 100, 300, 300, 500, 400, 200});
        f5885a.put("艺术歌曲/Art Music", new short[]{0, 0, 0, 0, 0, 300, 400, 400, 300, 100});
        f5885a.put("交响乐/Symphony", new short[]{300, 100, 300, 200, 0, 100, 200, 500, 600, 300});
        f5885a.put("协奏曲/Concerto", new short[]{200, 0, 200, 100, 100, 0, 200, 100, 100, 200});
        f5885a.put("奏鸣曲/Sonata", new short[]{0, 0, 0, 0, 200, 300, 400, 400, 400, 200});
        f5885a.put("现代基督/Contemporary Christian", new short[]{400, 400, 200, 100, -200, -100, 200, 200, 400, 400});
        f5885a.put("爵士/Jazz", new short[]{200, 500, 400, 0, 0, 300, 300, -100, 400, 300});
        f5885a.put("人声爵士/Vocal Jazz", new short[]{100, 200, 200, 0, 0, 500, 500, -100, 400, 300});
        f5885a.put("器乐爵士/Jazz instrumental", new short[]{200, 600, 400, 0, 0, 400, 400, -100, 600, 300});
        f5885a.put("大乐队/BigBand", new short[]{200, 500, 300, 100, 100, 400, 400, 0, 300, 300});
        f5885a.put("波谱/Bop", new short[]{300, 400, 400, 0, 0, 0, -100, -200, 200, 100});
        f5885a.put("后波谱/HardBop", new short[]{200, 300, 300, 100, 0, 0, 200, 200, 100, 0});
        f5885a.put("冷爵士/Cool Jazz", new short[]{300, 300, -100, 0, 0, 300, 300, -100, 300, 400});
        f5885a.put("自由爵士/Free Jazz", new short[]{200, 100, 100, 0, 0, 200, 200, 0, 200, 100});
        f5885a.put("融合爵士/Fusion Jazz", new short[]{100, 0, 100, 0, 0, 100, 200, 200, 100, 0});
        f5885a.put("酸爵士/Acid Jazz", new short[]{200, 400, 300, 100, 100, 300, 300, 0, 300, 200});
        f5885a.put("Bossa Nova/Bossa - Nova", new short[]{100, 300, 400, 100, 0, 500, 500, 0, 300, 200});
        f5885a.put("人声/Vocal", new short[]{-1100, -500, 0, -600, 0, 600, 0, 600, 400, 1000});
        f5886b.addAll(f5885a.keySet());
    }

    /* renamed from: a */
    public static List<String> m4337a() {
        return f5886b;
    }

    /* renamed from: a */
    public static String m4336a(int i) {
        if (i >= 1000) {
            return EqualizerHandpickFragment.KEY_CUSTOM;
        }
        List<String> list = f5886b;
        if (i < 0) {
            i = 0;
        }
        return list.get(i);
    }

    /* renamed from: a */
    public static int m4335a(String str) {
        int indexOf = m4337a().indexOf(str);
        if (indexOf == -1) {
            return 1000;
        }
        return indexOf;
    }

    /* renamed from: b */
    public static short[] m4333b(String str) {
        return f5885a.get(str);
    }

    /* renamed from: b */
    public static String m4334b() {
        return "普通/Normal";
    }
}
