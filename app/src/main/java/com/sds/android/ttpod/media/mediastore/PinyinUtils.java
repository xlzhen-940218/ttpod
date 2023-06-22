package com.sds.android.ttpod.media.mediastore;

import androidx.fragment.app.FragmentTransaction;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class PinyinUtils {
    private static final String[] COMMON_GBK_CHINESE_KEY = {"喆", "靓", "儿", "玟", "岚", "瑄", "羣", "尔", "二", "沢", "而", "纾", "倞", "畊", "俪", "蓓", "岡", "濑", "玥", "曺", "耳", "恵", "苖", "媔", "炆", "媞", "珺", "咲", "琤", "璟", "栎", "琲", "発", "堃", "蕭", "亞", "軒", "嘎", "瑀", "玠", "玙", "瀬", "旻", "螂", "浛", "妘", "霑", "逼", "趙", "傳"};
    private static final String[] COMMON_GBK_CHINESE_PINYIN = {"ZHE", "LIANG", "ER", "WEN", "LAN", "XUAN", "QUN", "ER", "ER", "ZE", "ER", "SHU", "JING", "GENG", "LI", "BEI", "GANG", "LAI", "YUE", "CAO", "ER", "HUI", "DI", "MIAN", "WEN", "TI", "JUN", "XIAO", "CHENG", "JING", "LI", "BEI", "FA", "KUN", "XIAO", "YA", "XUAN", "GA", "YU", "JIE", "YU", "LAI", "MIN", "LANG", "HAN", "YUN", "ZHAN", "BI", "ZHAO", "CHUAN"};
    private static final HashMap<String, String> INCOMMONUSEGBKCHINESE = new HashMap<>(COMMON_GBK_CHINESE_KEY.length);
    private static final String LOG_TAG = "PinyinUtils";
    private static final char[] T9_KEY;

    private static String findLetter(int i) {
        String str = "";
        switch (i) {
            case 5601:
            case 5927:
            case 6680:
            case 6732:
            case 7109:
            case 7238:
            case 7290:
            case 7343:
            case 8150:
            case 8260:
            case 8573:
            case 8777:
                return "CHU";
            case 5602:
            case 5613:
            case 5615:
            case 5628:
            case 5636:
            case 5646:
            case 5654:
            case 5661:
            case 5673:
            case 5676:
            case 5691:
            case 5695:
            case 5696:
            case 5697:
            case 5698:
            case 5699:
            case 5700:
            case 5719:
            case 5721:
            case 5772:
            case 5779:
            case 5790:
            case 5791:
            case 5795:
            case 5796:
            case 5797:
            case 5798:
            case 5799:
            case 5800:
            case 5802:
            case 5805:
            case 5819:
            case 5864:
            case 5866:
            case 5895:
            case 5896:
            case 5897:
            case 5898:
            case 5899:
            case 5900:
            case 5910:
            case 5940:
            case 5941:
            case 5962:
            case 5986:
            case 5995:
            case 5996:
            case 5997:
            case 5998:
            case 5999:
            case 6000:
            case 6008:
            case 6014:
            case 6019:
            case 6034:
            case 6082:
            case 6095:
            case 6096:
            case 6097:
            case 6098:
            case 6099:
            case 6100:
            case 6116:
            case 6125:
            case 6177:
            case 6181:
            case 6185:
            case 6192:
            case 6195:
            case 6196:
            case 6197:
            case 6198:
            case 6199:
            case 6200:
            case 6228:
            case 6235:
            case 6238:
            case 6248:
            case 6295:
            case 6296:
            case 6297:
            case 6298:
            case 6299:
            case 6300:
            case 6323:
            case 6331:
            case 6375:
            case 6395:
            case 6396:
            case 6397:
            case 6398:
            case 6399:
            case 6400:
            case 6405:
            case 6406:
            case 6412:
            case 6447:
            case 6477:
            case 6480:
            case 6495:
            case 6496:
            case 6497:
            case 6498:
            case 6499:
            case 6500:
            case 6516:
            case 6532:
            case 6533:
            case 6560:
            case 6566:
            case 6574:
            case 6575:
            case 6591:
            case 6595:
            case 6596:
            case 6597:
            case 6598:
            case 6599:
            case 6600:
            case 6626:
            case 6627:
            case 6628:
            case 6664:
            case 6674:
            case 6695:
            case 6696:
            case 6697:
            case 6698:
            case 6699:
            case 6700:
            case 6706:
            case 6707:
            case 6714:
            case 6722:
            case 6745:
            case 6747:
            case 6760:
            case 6763:
            case 6778:
            case 6783:
            case 6795:
            case 6796:
            case 6797:
            case 6798:
            case 6799:
            case 6800:
            case 6821:
            case 6864:
            case 6877:
            case 6882:
            case 6894:
            case 6895:
            case 6896:
            case 6897:
            case 6898:
            case 6899:
            case 6900:
            case 6902:
            case 6905:
            case 6917:
            case 6918:
            case 6919:
            case 6933:
            case 6946:
            case 6969:
            case 6970:
            case 6988:
            case 6995:
            case 6996:
            case 6997:
            case 6998:
            case 6999:
            case 7000:
            case 7001:
            case 7018:
            case 7043:
            case 7048:
            case 7074:
            case 7089:
            case 7095:
            case 7096:
            case 7097:
            case 7098:
            case 7099:
            case 7100:
            case 7103:
            case 7142:
            case 7148:
            case 7161:
            case 7186:
            case 7191:
            case 7195:
            case 7196:
            case 7197:
            case 7198:
            case 7199:
            case 7200:
            case 7232:
            case 7261:
            case 7265:
            case 7295:
            case 7296:
            case 7297:
            case 7298:
            case 7299:
            case 7300:
            case 7303:
            case 7312:
            case 7313:
            case 7359:
            case 7371:
            case 7386:
            case 7395:
            case 7396:
            case 7397:
            case 7398:
            case 7399:
            case 7400:
            case 7423:
            case 7425:
            case 7467:
            case 7495:
            case 7496:
            case 7497:
            case 7498:
            case 7499:
            case 7500:
            case 7522:
            case 7595:
            case 7596:
            case 7597:
            case 7598:
            case 7599:
            case 7600:
            case 7601:
            case 7615:
            case 7621:
            case 7665:
            case 7669:
            case 7674:
            case 7695:
            case 7696:
            case 7697:
            case 7698:
            case 7699:
            case 7700:
            case 7717:
            case 7734:
            case 7739:
            case 7789:
            case 7795:
            case 7796:
            case 7797:
            case 7798:
            case 7799:
            case 7800:
            case 7826:
            case 7829:
            case 7830:
            case 7836:
            case 7878:
            case 7895:
            case 7896:
            case 7897:
            case 7898:
            case 7899:
            case 7900:
            case 7904:
            case 7909:
            case 7910:
            case 7914:
            case 7922:
            case 7931:
            case 7971:
            case 7992:
            case 7995:
            case 7996:
            case 7997:
            case 7998:
            case 7999:
            case 8000:
            case 8003:
            case 8031:
            case 8058:
            case 8061:
            case 8063:
            case 8076:
            case 8088:
            case 8095:
            case 8096:
            case 8097:
            case 8098:
            case 8099:
            case 8100:
            case 8114:
            case 8134:
            case 8145:
            case 8147:
            case 8160:
            case 8171:
            case 8176:
            case 8189:
            case 8195:
            case 8196:
            case 8197:
            case 8198:
            case 8199:
            case 8200:
            case 8214:
            case 8235:
            case 8250:
            case 8259:
            case 8275:
            case 8281:
            case 8295:
            case 8296:
            case 8297:
            case 8298:
            case 8299:
            case 8300:
            case 8325:
            case 8327:
            case 8350:
            case 8381:
            case 8386:
            case 8395:
            case 8396:
            case 8397:
            case 8398:
            case 8399:
            case 8400:
            case 8405:
            case 8447:
            case 8473:
            case 8495:
            case 8496:
            case 8497:
            case 8498:
            case 8499:
            case 8500:
            case 8510:
            case 8511:
            case 8518:
            case 8523:
            case 8540:
            case 8545:
            case 8552:
            case 8553:
            case 8595:
            case 8596:
            case 8597:
            case 8598:
            case 8599:
            case 8600:
            case 8608:
            case 8666:
            case 8667:
            case 8679:
            case 8695:
            case 8696:
            case 8697:
            case 8698:
            case 8699:
            case 8700:
            case 8706:
            case 8715:
            case 8750:
            case 8753:
            case 8783:
            default:
                if (i >= 1601 && i <= 1602) {
                    return "A";
                }
                if (i >= 1603 && i <= 1615) {
                    return "AI";
                }
                if (i >= 1616 && i <= 1624) {
                    return "AN";
                }
                if (i >= 1625 && i <= 1627) {
                    return "ANG";
                }
                if (i >= 1628 && i <= 1636) {
                    return "AO";
                }
                if (i >= 1637 && i <= 1654) {
                    return "BA";
                }
                if (i >= 1655 && i <= 1662) {
                    return "BAI";
                }
                if (i >= 1663 && i <= 1677) {
                    return "BAN";
                }
                if (i >= 1678 && i <= 1689) {
                    return "BANG";
                }
                if (i >= 1690 && i <= 1712) {
                    return "BAO";
                }
                if (i >= 1713 && i <= 1727) {
                    return "BEI";
                }
                if (i >= 1728 && i <= 1731) {
                    return "BEN";
                }
                if (i >= 1732 && i <= 1737) {
                    return "BENG";
                }
                if (i > 1738 && i <= 1761) {
                    return "BI";
                }
                if (i >= 1762 && i <= 1773) {
                    return "BIAN";
                }
                if (i >= 1774 && i <= 1777) {
                    return "BIAO";
                }
                if (i >= 1778 && i <= 1781) {
                    return "BIE";
                }
                if (i >= 1782 && i <= 1787) {
                    return "BIN";
                }
                if (i >= 1788 && i <= 1794) {
                    return "BING";
                }
                if (i >= 1801 && i <= 1802) {
                    return "BING";
                }
                if (i >= 1803 && i <= 1821) {
                    return "BO";
                }
                if (i >= 1822 && i <= 1832) {
                    return "BU";
                }
                if (i == 1833) {
                    return "CA";
                }
                if (i >= 1834 && i <= 1844) {
                    return "CAI";
                }
                if (i >= 1845 && i <= 1851) {
                    return "CAN";
                }
                if (i >= 1852 && i <= 1856) {
                    return "CANG";
                }
                if (i >= 1857 && i <= 1861) {
                    return "CAO";
                }
                if (i >= 1862 && i <= 1866) {
                    return "CE";
                }
                if (i >= 1867 && i <= 1868) {
                    return "CENG";
                }
                if (i >= 1869 && i <= 1879) {
                    return "CHA";
                }
                if (i >= 1880 && i <= 1882) {
                    return "CHAI";
                }
                if (i >= 1883 && i <= 1892) {
                    return "CHAN";
                }
                if (i >= 1893 && i <= 1911) {
                    return "CHANG";
                }
                if (i >= 1912 && i <= 1920) {
                    return "CHAO";
                }
                if (i >= 1921 && i <= 1926) {
                    return "CHE";
                }
                if (i >= 1927 && i <= 1936) {
                    return "CHEN";
                }
                if (i >= 1937 && i <= 1951) {
                    return "CHENG";
                }
                if (i >= 1952 && i <= 1967) {
                    return "CHI";
                }
                if (i >= 1968 && i <= 1972) {
                    return "CHONG";
                }
                if (i >= 1973 && i <= 1984) {
                    return "CHOU";
                }
                if (i >= 1985 && i <= 2006) {
                    return "CHU";
                }
                if (i == 2007) {
                    return "CHUAI";
                }
                if (i >= 2008 && i <= 2014) {
                    return "CHUAN";
                }
                if (i >= 2015 && i <= 2020) {
                    return "CHUANG";
                }
                if (i >= 2021 && i <= 2025) {
                    return "CHUI";
                }
                if (i >= 2026 && i <= 2032) {
                    return "CHUN";
                }
                if (i >= 2033 && i <= 2034) {
                    return "CHUO";
                }
                if (i >= 2035 && i <= 2046) {
                    return "CI";
                }
                if (i >= 2047 && i <= 2052) {
                    return "CONG";
                }
                if (i >= 2054 && i <= 2057) {
                    return "CU";
                }
                if (i >= 2058 && i <= 2060) {
                    return "CUAN";
                }
                if (i >= 2061 && i <= 2068) {
                    return "CUI";
                }
                if (i >= 2069 && i <= 2071) {
                    return "CUN";
                }
                if (i >= 2072 && i <= 2077) {
                    return "CUO";
                }
                if (i >= 2078 && i <= 2083) {
                    return "DA";
                }
                if (i >= 2084 && i <= 2094) {
                    return "DAI";
                }
                if (i >= 2102 && i <= 2116) {
                    return "DAN";
                }
                if (i >= 2117 && i <= 2121) {
                    return "DANG";
                }
                if (i >= 2122 && i <= 2133) {
                    return "DAO";
                }
                if (i >= 2134 && i <= 2136) {
                    return "DE";
                }
                if (i >= 2137 && i <= 2143) {
                    return "DENG";
                }
                if (i >= 2144 && i <= 2162) {
                    return "DI";
                }
                if (i >= 2163 && i <= 2178) {
                    return "DIAN";
                }
                if (i >= 2179 && i <= 2187) {
                    return "DIAO";
                }
                if (i >= 2188 && i <= 2194) {
                    return "DIE";
                }
                if (i >= 2201 && i <= 2209) {
                    return "DING";
                }
                if (i == 2210) {
                    return "DIU";
                }
                if (i >= 2211 && i <= 2220) {
                    return "DONG";
                }
                if (i >= 2221 && i <= 2227) {
                    return "DOU";
                }
                if (i >= 2228 && i <= 2242) {
                    return "DU";
                }
                if (i >= 2243 && i <= 2248) {
                    return "DUAN";
                }
                if (i >= 2249 && i <= 2252) {
                    return "DUI";
                }
                if (i >= 2253 && i <= 2261) {
                    return "DUN";
                }
                if (i >= 2262 && i <= 2273) {
                    return "DUO";
                }
                if (i >= 2274 && i <= 2286) {
                    return "E";
                }
                if (i == 2287) {
                    return "EN";
                }
                if (i >= 2288 && i <= 2231) {
                    return "ER";
                }
                if (i >= 2302 && i <= 2309) {
                    return "FA";
                }
                if (i >= 2310 && i <= 2326) {
                    return "FAN";
                }
                if (i >= 2327 && i <= 2337) {
                    return "FANG";
                }
                if (i >= 2338 && i <= 2349) {
                    return "FEI";
                }
                if (i >= 2350 && i <= 2364) {
                    return "FEN";
                }
                if (i >= 2365 && i <= 2379) {
                    return "FENG";
                }
                if (i == 2380) {
                    return "FO";
                }
                if (i == 2381) {
                    return "FOU";
                }
                if (i >= 2382 && i <= 2432) {
                    return "FU";
                }
                if (i >= 2435 && i <= 2440) {
                    return "GAI";
                }
                if (i >= 2441 && i <= 2451) {
                    return "GAN";
                }
                if (i >= 2452 && i <= 2460) {
                    return "GANG";
                }
                if (i >= 2461 && i <= 2470) {
                    return "GAO";
                }
                if (i >= 2471 && i <= 2487) {
                    return "GE";
                }
                if (i == 2488) {
                    return "GEI";
                }
                if (i >= 2489 && i <= 2490) {
                    return "GEN";
                }
                if (i >= 2491 && i <= 2503) {
                    return "GENG";
                }
                if (i >= 2504 && i <= 2518) {
                    return "GONG";
                }
                if (i >= 2519 && i <= 2527) {
                    return "GOU";
                }
                if (i >= 2528 && i <= 2545) {
                    return "GU";
                }
                if (i >= 2546 && i <= 2551) {
                    return "GUA";
                }
                if (i >= 2552 && i <= 2554) {
                    return "GUAI";
                }
                if (i >= 2555 && i <= 2565) {
                    return "GUAN";
                }
                if (i >= 2566 && i <= 2568) {
                    return "GUANG";
                }
                if (i >= 2569 && i <= 2584) {
                    return "GUI";
                }
                if (i >= 2585 && i <= 2587) {
                    return "GUN";
                }
                if (i >= 2588 && i <= 2593) {
                    return "GUO";
                }
                if (i == 2594) {
                    return "HA";
                }
                if (i >= 2601 && i <= 2607) {
                    return "HAI";
                }
                if (i >= 2608 && i <= 2626) {
                    return "HAN";
                }
                if (i >= 2627 && i <= 2629) {
                    return "HANG";
                }
                if (i >= 2630 && i <= 2638) {
                    return "HAO";
                }
                if (i >= 2639 && i <= 2656) {
                    return "HE";
                }
                if (i >= 2657 && i <= 2658) {
                    return "HEI";
                }
                if (i >= 2659 && i <= 2662) {
                    return "HEN";
                }
                if (i >= 2663 && i <= 2667) {
                    return "HENG";
                }
                if (i >= 2668 && i <= 2676) {
                    return "HONG";
                }
                if (i >= 2677 && i <= 2683) {
                    return "HOU";
                }
                if (i >= 2684 && i <= 2707) {
                    return "HU";
                }
                if (i >= 2708 && i <= 2716) {
                    return "HUA";
                }
                if (i >= 2717 && i <= 2721) {
                    return "HUAI";
                }
                if (i >= 2722 && i <= 2735) {
                    return "HUAN";
                }
                if (i >= 2736 && i <= 2749) {
                    return "HUANG";
                }
                if (i >= 2750 && i <= 2770) {
                    return "HUI";
                }
                if (i >= 2771 && i <= 2776) {
                    return "HUN";
                }
                if (i >= 2777 && i <= 2786) {
                    return "HUO";
                }
                if (i >= 2787 && i <= 2845) {
                    return "JI";
                }
                if (i >= 2846 && i <= 2862) {
                    return "JIA";
                }
                if (i >= 2863 && i <= 2908) {
                    return "JIAN";
                }
                if (i >= 2909 && i <= 2921) {
                    return "JIANG";
                }
                if (i >= 2922 && i <= 2949) {
                    return "JIAO";
                }
                if (i >= 2950 && i <= 2976) {
                    return "JIE";
                }
                if (i >= 2977 && i <= 3002) {
                    return "JIN";
                }
                if (i >= 3003 && i <= 3027) {
                    return "JING";
                }
                if (i >= 3028 && i <= 3029) {
                    return "JIONG";
                }
                if (i >= 3030 && i <= 3046) {
                    return "JIU";
                }
                if (i >= 3047 && i <= 3071) {
                    return "JU";
                }
                if (i >= 3072 && i <= 3078) {
                    return "JUAN";
                }
                if (i >= 3079 && i <= 3088) {
                    return "JUE";
                }
                if (i >= 3089 && i <= 3105) {
                    return "JUN";
                }
                if (i >= 3106 && i <= 3109) {
                    return "KA";
                }
                if (i >= 3110 && i <= 3114) {
                    return "KAI";
                }
                if (i >= 3115 && i <= 3120) {
                    return "KAN";
                }
                if (i >= 3121 && i <= 3127) {
                    return "KANG";
                }
                if (i >= 3128 && i <= 3131) {
                    return "KAO";
                }
                if (i >= 3132 && i <= 3146) {
                    return "KE";
                }
                if (i >= 3147 && i <= 3150) {
                    return "KEN";
                }
                if (i >= 3151 && i <= 3152) {
                    return "KENG";
                }
                if (i >= 3153 && i <= 3156) {
                    return "KONG";
                }
                if (i >= 3157 && i <= 3160) {
                    return "KOU";
                }
                if (i >= 3161 && i <= 3167) {
                    return "KU";
                }
                if (i >= 3168 && i <= 3172) {
                    return "KUA";
                }
                if (i >= 3173 && i <= 3176) {
                    return "KUAI";
                }
                if (i >= 3177 && i <= 3178) {
                    return "KUAN";
                }
                if (i >= 3179 && i <= 3186) {
                    return "KUANG";
                }
                if (i >= 3187 && i <= 3203) {
                    return "KUI";
                }
                if (i >= 3204 && i <= 3207) {
                    return "KUN";
                }
                if (i >= 3208 && i <= 3211) {
                    return "KUO";
                }
                if (i >= 3212 && i <= 3218) {
                    return "LA";
                }
                if (i >= 3219 && i <= 3221) {
                    return "LAI";
                }
                if (i >= 3222 && i <= 3236) {
                    return "LAN";
                }
                if (i >= 3237 && i <= 3243) {
                    return "LANG";
                }
                if (i >= 3244 && i <= 3252) {
                    return "LAO";
                }
                if (i >= 3253 && i <= 3254) {
                    return "LE";
                }
                if (i >= 3255 && i <= 3265) {
                    return "LEI";
                }
                if (i >= 3266 && i <= 3268) {
                    return "LENG";
                }
                if (i >= 3269 && i <= 3308) {
                    str = "LI";
                }
                if (i == 3309) {
                    return "LIA";
                }
                if (i >= 3310 && i <= 3323) {
                    return "LIAN";
                }
                if (i >= 3324 && i <= 3334) {
                    return "LIANG";
                }
                if (i >= 3335 && i <= 3347) {
                    return "LIAO";
                }
                if (i >= 3348 && i <= 3352) {
                    return "LIE";
                }
                if (i >= 3353 && i <= 3363) {
                    return "LIN";
                }
                if (i >= 3364 && i <= 3378) {
                    return "LING";
                }
                if (i >= 3379 && i <= 3389) {
                    return "LIU";
                }
                if (i >= 3390 && i <= 3404) {
                    return "LONG";
                }
                if (i >= 3405 && i <= 3410) {
                    return "LOU";
                }
                if (i >= 3411 && i <= 3444) {
                    return "LU";
                }
                if (i >= 3445 && i <= 3450) {
                    return "LUAN";
                }
                if (i >= 3451 && i <= 3452) {
                    return "LUE";
                }
                if (i >= 3453 && i <= 3459) {
                    return "LUN";
                }
                if (i >= 3460 && i <= 3471) {
                    return "LUO";
                }
                if (i >= 3472 && i <= 3480) {
                    return "MA";
                }
                if (i >= 3481 && i <= 3486) {
                    return "MAI";
                }
                if (i >= 3487 && i <= 3501) {
                    return "MAN";
                }
                if (i >= 3502 && i <= 3507) {
                    return "MANG";
                }
                if (i >= 3508 && i <= 3519) {
                    return "MAO";
                }
                if (i == 3520) {
                    return "ME";
                }
                if (i >= 3521 && i <= 3536) {
                    return "MEI";
                }
                if (i >= 3537 && i <= 3539) {
                    return "MEN";
                }
                if (i >= 3540 && i <= 3547) {
                    return "MENG";
                }
                if (i >= 3548 && i <= 3561) {
                    str = "MI";
                }
                if (i >= 3562 && i <= 3570) {
                    return "MIAN";
                }
                if (i >= 3571 && i <= 3578) {
                    return "MIAO";
                }
                if (i >= 3579 && i <= 3580) {
                    return "MIE";
                }
                if (i >= 3581 && i <= 3586) {
                    return "MIN";
                }
                if (i >= 3587 && i <= 3592) {
                    return "MING";
                }
                if (i == 3593) {
                    return "MIU";
                }
                if (i >= 3594 && i <= 3616) {
                    return "MO";
                }
                if (i >= 3617 && i <= 3619) {
                    return "MOU";
                }
                if (i >= 3620 && i <= 3634) {
                    return "MU";
                }
                if (i >= 3635 && i <= 3641) {
                    return "NA";
                }
                if (i >= 3642 && i <= 3646) {
                    return "NAI";
                }
                if (i >= 3647 && i <= 3649) {
                    return "NAN";
                }
                if (i == 3650) {
                    return "NANG";
                }
                if (i >= 3651 && i <= 3655) {
                    return "NAO";
                }
                if (i == 3656) {
                    return "NE";
                }
                if (i >= 3657 && i <= 3658) {
                    return "NEI";
                }
                if (i == 3659) {
                    return "NEN";
                }
                if (i == 3660) {
                    return "NENG";
                }
                if (i >= 3661 && i <= 3671) {
                    return "NI";
                }
                if (i >= 3672 && i <= 3678) {
                    return "NIAN";
                }
                if (i >= 3679 && i <= 3680) {
                    return "NIANG";
                }
                if (i >= 3681 && i <= 3682) {
                    return "NIAO";
                }
                if (i >= 3683 && i <= 3689) {
                    return "NIE";
                }
                if (i == 3690) {
                    return "NIN";
                }
                if (i >= 3691 && i <= 3702) {
                    return "NING";
                }
                if (i >= 3703 && i <= 3706) {
                    return "NIU";
                }
                if (i >= 3707 && i <= 3710) {
                    return "NONG";
                }
                if (i >= 3711 && i <= 3714) {
                    return "NU";
                }
                if (i == 3715) {
                    return "NUAN";
                }
                if (i >= 3716 && i <= 3717) {
                    return "NUE";
                }
                if (i >= 3718 && i <= 3721) {
                    return "NUO";
                }
                if (i == 3722) {
                    return "O";
                }
                if (i >= 3723 && i <= 3729) {
                    return "OU";
                }
                if (i >= 3730 && i <= 3735) {
                    return "PA";
                }
                if (i >= 3736 && i <= 3741) {
                    return "PAI";
                }
                if (i >= 3742 && i <= 3749) {
                    return "PAN";
                }
                if (i >= 3750 && i <= 3754) {
                    return "PANG";
                }
                if (i >= 3755 && i <= 3761) {
                    return "PAO";
                }
                if (i >= 3762 && i <= 3770) {
                    return "PEI";
                }
                if (i >= 3771 && i <= 3772) {
                    return "PEN";
                }
                if (i >= 3773 && i <= 3786) {
                    return "PENG";
                }
                if (i >= 3787 && i <= 3809) {
                    return "PI";
                }
                if (i >= 3810 && i <= 3813) {
                    return "PIAN";
                }
                if (i >= 3814 && i <= 3817) {
                    return "PIAO";
                }
                if (i >= 3818 && i <= 3819) {
                    return "PIE";
                }
                if (i >= 3820 && i <= 3824) {
                    return "PIN";
                }
                if (i >= 3825 && i <= 3833) {
                    return "PING";
                }
                if (i >= 3834 && i <= 3841) {
                    return "PO";
                }
                if (i == 3842) {
                    return "POU";
                }
                if (i >= 3843 && i <= 3857) {
                    return "PU";
                }
                if (i >= 3858 && i <= 3893) {
                    return "QI";
                }
                if (i == 3894 || (i >= 3901 && i <= 3902)) {
                    return "QIA";
                }
                if (i >= 3903 && i <= 3924) {
                    return "QIAN";
                }
                if (i >= 3925 && i <= 3932) {
                    return "QIANG";
                }
                if (i >= 3933 && i <= 3947) {
                    return "QIAO";
                }
                if (i >= 3948 && i <= 3952) {
                    return "QIE";
                }
                if (i >= 3953 && i <= 3963) {
                    return "QIN";
                }
                if (i >= 3964 && i <= 3976) {
                    return "QING";
                }
                if (i >= 3977 && i <= 3978) {
                    return "QIONG";
                }
                if (i >= 3979 && i <= 3986) {
                    return "QIU";
                }
                if (i >= 3987 && i <= 4005) {
                    return "QU";
                }
                if (i >= 4006 && i <= 4016) {
                    return "QUAN";
                }
                if (i >= 4017 && i <= 4024) {
                    return "QUE";
                }
                if (i >= 4025 && i <= 4026) {
                    return "QUN";
                }
                if (i >= 4027 && i <= 4030) {
                    return "RAN";
                }
                if (i >= 4031 && i <= 4035) {
                    str = "RANG";
                }
                if (i >= 4036 && i <= 4038) {
                    return "RAO";
                }
                if (i >= 4039 && i <= 4040) {
                    return "RE";
                }
                if (i >= 4041 && i <= 4050) {
                    return "REN";
                }
                if (i >= 4051 && i <= 4052) {
                    return "RENG";
                }
                if (i == 4053) {
                    return "RI";
                }
                if (i >= 4054 && i <= 4063) {
                    return "RONG";
                }
                if (i >= 4064 && i <= 4066) {
                    return "ROU";
                }
                if (i >= 4067 && i <= 4076) {
                    return "RU";
                }
                if (i >= 4077 && i <= 4078) {
                    return "RUAN";
                }
                if (i >= 4079 && i <= 4081) {
                    return "RUI";
                }
                if (i >= 4082 && i <= 4083) {
                    return "RUN";
                }
                if (i >= 4084 && i <= 4085) {
                    return "RUO";
                }
                if (i >= 4086 && i <= 4088) {
                    return "SA";
                }
                if (i >= 4089 && i <= 4092) {
                    return "SAI";
                }
                if (i >= 4093 && i <= 4094) {
                    return "SAN";
                }
                if (i >= 4101 && i <= 4102) {
                    return "SAN";
                }
                if (i >= 4103 && i <= 4105) {
                    return "SANG";
                }
                if (i >= 4106 && i <= 4109) {
                    return "SAO";
                }
                if (i >= 4110 && i <= 4112) {
                    return "SE";
                }
                if (i == 4113) {
                    str = "SEN";
                }
                if (i == 4114) {
                    return "SENG";
                }
                if (i >= 4115 && i <= 4123) {
                    return "SHA";
                }
                if (i >= 4124 && i <= 4125) {
                    return "SHAI";
                }
                if (i >= 4126 && i <= 4141) {
                    return "SHAN";
                }
                if (i >= 4142 && i <= 4149) {
                    return "SHANG";
                }
                if (i >= 4150 && i <= 4160) {
                    return "SHAO";
                }
                if (i >= 4161 && i <= 4172) {
                    return "SHE";
                }
                if (i >= 4173 && i <= 4188) {
                    return "SHEN";
                }
                if (i >= 4189 && i <= 4205) {
                    return "SHENG";
                }
                if (i >= 4206 && i <= 4252) {
                    return "SHI";
                }
                if (i >= 4253 && i <= 4262) {
                    return "SHOU";
                }
                if (i >= 4263 && i <= 4301) {
                    return "SHU";
                }
                if (i >= 4302 && i <= 4303) {
                    return "SHUA";
                }
                if (i >= 4304 && i <= 4307) {
                    return "SHUAI";
                }
                if (i >= 4308 && i <= 4309) {
                    return "SHUAN";
                }
                if (i >= 4310 && i <= 4312) {
                    return "SHUANG";
                }
                if (i >= 4313 && i <= 4316) {
                    return "SHUI";
                }
                if (i >= 4317 && i <= 4320) {
                    return "SHUN";
                }
                if (i >= 4321 && i <= 4324) {
                    return "SHUO";
                }
                if (i >= 4325 && i <= 4340) {
                    return "SI";
                }
                if (i >= 4341 && i <= 4348) {
                    return "SONG";
                }
                if (i >= 4349 && i <= 4352) {
                    return "SOU";
                }
                if (i >= 4353 && i <= 4364) {
                    return "SU";
                }
                if (i >= 4365 && i <= 4367) {
                    return "SUAN";
                }
                if (i >= 4368 && i <= 4378) {
                    return "SUI";
                }
                if (i >= 4379 && i <= 4381) {
                    return "SUN";
                }
                if (i >= 4382 && i <= 4389) {
                    return "SUO";
                }
                if (i >= 4390 && i <= 4404) {
                    return "TA";
                }
                if (i >= 4405 && i <= 4413) {
                    return "TAI";
                }
                if (i >= 4414 && i <= 4431) {
                    return "TAN";
                }
                if (i >= 4432 && i <= 4444) {
                    return "TANG";
                }
                if (i >= 4445 && i <= 4455) {
                    return "TAO";
                }
                if (i == 4456) {
                    return "TE";
                }
                if (i >= 4457 && i <= 4460) {
                    return "TENG";
                }
                if (i >= 4461 && i <= 4475) {
                    return "TI";
                }
                if (i >= 4476 && i <= 4483) {
                    return "TIAN";
                }
                if (i >= 4484 && i <= 4488) {
                    return "TIAO";
                }
                if (i >= 4489 && i <= 4491) {
                    return "TIE";
                }
                if (i >= 4492 && i <= 4507) {
                    return "TING";
                }
                if (i >= 4508 && i <= 4520) {
                    return "TONG";
                }
                if (i >= 4521 && i <= 4524) {
                    return "TOU";
                }
                if (i >= 4525 && i <= 4535) {
                    return "TU";
                }
                if (i >= 4536 && i <= 4537) {
                    return "TUAN";
                }
                if (i >= 4538 && i <= 4543) {
                    return "TUI";
                }
                if (i >= 4544 && i <= 4546) {
                    return "TUN";
                }
                if (i >= 4547 && i <= 4557) {
                    return "TUO";
                }
                if (i >= 4558 && i <= 4564) {
                    return "WA";
                }
                if (i >= 4565 && i <= 4566) {
                    return "WAI";
                }
                if (i >= 4567 && i <= 4583) {
                    return "WAN";
                }
                if (i >= 4584 && i <= 4593) {
                    return "WANG";
                }
                if (i >= 4594 && i <= 4632) {
                    return "WEI";
                }
                if (i >= 4633 && i <= 4642) {
                    return "WEN";
                }
                if (i >= 4643 && i <= 4645) {
                    return "WENG";
                }
                if (i >= 4646 && i <= 4654) {
                    return "WO";
                }
                if (i >= 4655 && i <= 4683) {
                    return "WU";
                }
                if (i >= 4684 && i <= 4724) {
                    return "XI";
                }
                if (i >= 4725 && i <= 4737) {
                    return "XIA";
                }
                if (i >= 4738 && i <= 4763) {
                    return "XIAN";
                }
                if (i >= 4764 && i <= 4783) {
                    return "XIANG";
                }
                if (i >= 4784 && i <= 4807) {
                    return "XIAO";
                }
                if (i >= 4809 && i <= 4828) {
                    return "XIE";
                }
                if (i >= 4829 && i <= 4838) {
                    return "XIN";
                }
                if (i >= 4839 && i <= 4853) {
                    return "XING";
                }
                if (i >= 4854 && i <= 4860) {
                    return "XIONG";
                }
                if (i >= 4861 && i <= 4869) {
                    return "XIU";
                }
                if (i >= 4870 && i <= 4888) {
                    return "XU";
                }
                if (i >= 4889 && i <= 4904) {
                    return "XUAN";
                }
                if (i >= 4905 && i <= 4910) {
                    return "XUE";
                }
                if (i >= 4911 && i <= 4924) {
                    return "XUN";
                }
                if (i >= 4925 && i <= 4940) {
                    return "YA";
                }
                if (i >= 4941 && i <= 4973) {
                    return "YAN";
                }
                if (i >= 4974 && i <= 4990) {
                    return "YANG";
                }
                if (i >= 4991 && i <= 5011) {
                    return "YAO";
                }
                if (i >= 5012 && i <= 5026) {
                    return "YE";
                }
                if (i >= 5027 && i <= 5079) {
                    return "YI";
                }
                if (i >= 5080 && i <= 5101) {
                    return "YIN";
                }
                if (i >= 5102 && i <= 5119) {
                    return "YING";
                }
                if (i == 5120) {
                    return "YO";
                }
                if (i >= 5121 && i <= 5135) {
                    return "YONG";
                }
                if (i >= 5136 && i <= 5155) {
                    return "YOU";
                }
                if (i >= 5156 && i <= 5206) {
                    return "YU";
                }
                if (i >= 5207 && i <= 5226) {
                    return "YUAN";
                }
                if (i >= 5227 && i <= 5236) {
                    return "YUE";
                }
                if (i >= 5237 && i <= 5248) {
                    return "YUN";
                }
                if (i >= 5249 && i <= 5251) {
                    return "ZA";
                }
                if (i >= 5252 && i <= 5258) {
                    return "ZAI";
                }
                if (i >= 5259 && i <= 5262) {
                    return "ZAN";
                }
                if (i >= 5263 && i <= 5265) {
                    return "ZANG";
                }
                if (i >= 5266 && i <= 5279) {
                    return "ZAO";
                }
                if (i >= 5280 && i <= 5283) {
                    return "ZE";
                }
                if (i == 5284) {
                    return "ZEI";
                }
                if (i == 5285) {
                    return "ZEN";
                }
                if (i >= 5286 && i <= 5289) {
                    return "ZENG";
                }
                if (i >= 5290 && i <= 5309) {
                    return "ZHA";
                }
                if (i >= 5310 && i <= 5315) {
                    return "ZHAI";
                }
                if (i >= 5316 && i <= 5332) {
                    return "ZHAN";
                }
                if (i >= 5333 && i <= 5347) {
                    return "ZHANG";
                }
                if (i >= 5348 && i <= 5357) {
                    return "ZHAO";
                }
                if (i >= 5358 && i <= 5367) {
                    return "ZHE";
                }
                if (i >= 5368 && i <= 5383) {
                    return "ZHEN";
                }
                if (i >= 5384 && i <= 5404) {
                    return "ZHENG";
                }
                if (i >= 5405 && i <= 5447) {
                    return "ZHI";
                }
                if (i >= 5448 && i <= 5458) {
                    return "ZHONG";
                }
                if (i >= 5459 && i <= 5472) {
                    return "ZHOU";
                }
                if (i >= 5473 && i <= 5504) {
                    return "ZHU";
                }
                if (i >= 5505 && i <= 5506) {
                    return "ZHUA";
                }
                if (i == 5507) {
                    return "ZHUAI";
                }
                if (i >= 5508 && i <= 5513) {
                    return "ZHUAN";
                }
                if (i >= 5514 && i <= 5520) {
                    return "ZHUANG";
                }
                if (i >= 5521 && i <= 5526) {
                    return "ZHUI";
                }
                if (i >= 5527 && i <= 5528) {
                    return "ZHUN";
                }
                if (i >= 5529 && i <= 5539) {
                    return "ZHUO";
                }
                if (i >= 5540 && i <= 5554) {
                    return "ZI";
                }
                if (i >= 5555 && i <= 5561) {
                    return "ZONG";
                }
                if (i >= 5562 && i <= 5565) {
                    return "ZOU";
                }
                if (i >= 5566 && i <= 5573) {
                    return "ZU";
                }
                if (i >= 5574 && i <= 5575) {
                    return "ZUAN";
                }
                if (i >= 5576 && i <= 5579) {
                    return "ZUI";
                }
                if (i >= 5580 && i <= 5581) {
                    return "ZUN";
                }
                if (i >= 5582 && i <= 5589) {
                    return "ZUO";
                }
                return str;
            case 5603:
            case 5685:
            case 5867:
            case 5889:
            case 5956:
            case 6044:
            case 6377:
            case 6648:
            case 6668:
            case 6672:
            case 6820:
            case 6927:
            case 6935:
            case 6992:
            case 7036:
            case 7080:
            case 7227:
            case 7485:
            case 7641:
            case 8036:
            case 8045:
            case 8077:
            case 8258:
            case 8640:
            case 8789:
                return "WU";
            case 5604:
            case 5875:
            case 5982:
            case 7414:
            case 7464:
                return "GAI";
            case 5605:
            case 5994:
            case 7393:
            case 8004:
            case 8651:
            case 8683:
                return "NIAN";
            case 5606:
            case 5677:
            case 7493:
            case 7559:
            case 7610:
                return "SA";
            case 5607:
            case 5682:
            case 5880:
            case 5892:
            case 5915:
            case 5960:
            case 6017:
            case 6037:
            case 6308:
            case 6472:
            case 6647:
            case 6836:
            case 7039:
            case 7102:
            case 7233:
            case 7422:
            case 7802:
            case 7828:
            case 7875:
            case 8117:
            case 8166:
            case 8223:
            case 8271:
            case 8589:
                return "PI";
            case 5608:
            case 6102:
            case 6371:
            case 8462:
                return "GEN";
            case 5609:
            case 5984:
            case 7239:
            case 7263:
            case 7583:
            case 7810:
            case 7881:
            case 7905:
            case 8146:
            case 8241:
            case 8508:
                return "CHENG";
            case 5610:
            case 5678:
            case 5933:
            case 5957:
            case 6010:
            case 6435:
            case 7092:
            case 7501:
            case 7585:
            case 7749:
            case 7951:
            case 8143:
            case 8220:
            case 8420:
            case 8732:
                return "GE";
            case 5611:
            case 5981:
            case 6346:
            case 6614:
            case 7207:
            case 7748:
            case 7883:
            case 8245:
                return "NAO";
            case 5612:
            case 5832:
            case 5844:
            case 5949:
            case 6035:
            case 6113:
            case 6164:
            case 6332:
            case 6721:
            case 6977:
            case 7025:
            case 7378:
            case 7581:
            case 7916:
            case 7941:
            case 8042:
            case 8206:
            case 8689:
                return "E";
            case 5614:
            case 5625:
            case 5681:
            case 5722:
            case 5836:
            case 5845:
            case 6139:
            case 6187:
            case 6277:
            case 6484:
            case 6486:
            case 6546:
            case 6592:
            case 6632:
            case 6637:
            case 6655:
            case 6748:
            case 6987:
            case 6993:
            case 7005:
            case 7090:
            case 7204:
            case 7437:
            case 7476:
            case 7573:
            case 7603:
            case 7622:
            case 7647:
            case 7659:
            case 7718:
            case 7858:
            case 8033:
            case 8054:
            case 8085:
            case 8086:
            case 8130:
            case 8133:
            case 8266:
            case 8285:
            case 8336:
            case 8407:
            case 8408:
            case 8607:
            case 8625:
                return "YU";
            case 5616:
            case 5734:
            case 6074:
            case 6109:
            case 6221:
            case 6333:
            case 6357:
            case 6589:
            case 6656:
            case 6725:
            case 6868:
            case 6908:
            case 6986:
            case 6994:
            case 7030:
            case 7052:
            case 7221:
            case 7815:
            case 7873:
            case 7985:
            case 8152:
            case 8357:
            case 8375:
            case 8387:
            case 8416:
            case 8437:
            case 8547:
            case 8734:
                return "BI";
            case 5617:
            case 5702:
            case 5971:
            case 6653:
            case 6791:
            case 7256:
            case 7262:
            case 7350:
            case 7740:
            case 8374:
            case 8502:
            case 8541:
            case 8630:
                return "TUO";
            case 5618:
            case 5619:
            case 6326:
            case 6542:
            case 6570:
            case 7159:
            case 7182:
            case 7235:
            case 7387:
            case 7455:
            case 7540:
            case 7902:
            case 8046:
            case 8126:
            case 8477:
            case 8705:
                return "YAO";
            case 5620:
            case 5876:
            case 5904:
            case 5990:
            case 6038:
            case 6293:
            case 6489:
            case 6669:
            case 6973:
            case 6975:
            case 7079:
            case 7246:
            case 7255:
            case 7257:
            case 7268:
            case 7382:
            case 7389:
            case 7462:
            case 7553:
            case 7589:
            case 7677:
            case 7683:
            case 7773:
            case 7984:
            case 8026:
            case 8075:
            case 8246:
            case 8474:
            case 8505:
            case 8537:
            case 8557:
            case 8560:
            case 8584:
            case 8603:
                return "ZHI";
            case 5621:
            case 5765:
            case 5814:
            case 5848:
            case 5901:
            case 5970:
            case 6122:
            case 6454:
            case 7023:
            case 7116:
            case 7260:
            case 7306:
            case 7475:
            case 7738:
            case 7758:
            case 7791:
            case 7965:
            case 8438:
            case 8730:
                return "DI";
            case 5622:
            case 6016:
            case 7431:
            case 7607:
            case 8646:
                return "XIN";
            case 5623:
            case 5920:
            case 5983:
            case 6007:
            case 6065:
            case 6337:
            case 6419:
            case 6594:
            case 6625:
            case 6806:
            case 7519:
            case 7887:
            case 8111:
            case 8230:
            case 8615:
            case 8624:
                return "YIN";
            case 5624:
            case 5649:
            case 5771:
            case 6162:
            case 6281:
            case 6413:
            case 6416:
            case 6720:
            case 6951:
            case 7450:
            case 7805:
            case 8606:
            case 8743:
                return "KUI";
            case 5626:
            case 5830:
            case 5912:
            case 6227:
            case 7141:
            case 7332:
            case 7334:
            case 7429:
            case 7915:
                return "GAO";
            case 5627:
            case 6391:
            case 6812:
            case 7226:
            case 7666:
                return "TAO";
            case 5629:
            case 5632:
            case 5662:
            case 5705:
            case 5742:
            case 5952:
            case 6024:
            case 6033:
            case 6193:
            case 6210:
            case 6265:
            case 6320:
            case 6350:
            case 6383:
            case 6507:
            case 6553:
            case 6809:
            case 6976:
            case 7087:
            case 7160:
            case 7165:
            case 7314:
            case 7374:
            case 7410:
            case 7411:
            case 7469:
            case 7473:
            case 7487:
            case 7620:
            case 7722:
            case 7831:
            case 7990:
            case 8002:
            case 8104:
            case 8217:
            case 8337:
            case 8339:
            case 8463:
            case 8550:
            case 8611:
            case 8661:
            case 8674:
            case 8757:
            case 8768:
                return "JI";
            case 5630:
            case 6021:
            case 6133:
            case 7245:
                return "NAI";
            case 5631:
            case 6367:
            case 8326:
            case 8390:
                return "MIE";
            case 5633:
            case 5725:
            case 5963:
            case 6027:
            case 6046:
            case 6089:
            case 6129:
            case 6134:
            case 6161:
            case 6213:
            case 6366:
            case 6450:
            case 6508:
            case 6510:
            case 6764:
            case 6831:
            case 7075:
            case 7118:
            case 7187:
            case 7189:
            case 7229:
            case 7271:
            case 7342:
            case 7440:
            case 7605:
            case 7687:
            case 7712:
            case 7751:
            case 8193:
            case 8251:
            case 8264:
            case 8475:
            case 8476:
            case 8572:
            case 8702:
            case 8772:
                return "QI";
            case 5634:
            case 5855:
            case 6234:
            case 6368:
            case 6455:
            case 6608:
            case 6772:
            case 6921:
            case 6984:
            case 7563:
            case 7682:
            case 8445:
            case 8767:
            case 8771:
                return "MI";
            case 5635:
            case 5873:
            case 5893:
            case 5993:
            case 6141:
            case 6703:
            case 7753:
            case 8039:
            case 8156:
            case 8645:
            case 8725:
                return "BEI";
            case 5637:
            case 5812:
            case 6152:
            case 6536:
            case 6773:
            case 7284:
            case 7379:
            case 7484:
            case 7486:
            case 7591:
            case 7617:
            case 7813:
            case 7825:
            case 7860:
            case 7932:
            case 8019:
            case 8083:
            case 8233:
            case 8494:
            case 8593:
            case 8681:
            case 8729:
                return "GU";
            case 5638:
            case 5651:
            case 6385:
            case 6493:
            case 6937:
            case 7430:
            case 8348:
            case 8369:
            case 8423:
                return "ZE";
            case 5639:
            case 5760:
            case 6606:
            case 6860:
            case 7608:
            case 7820:
            case 8774:
                return "SHE";
            case 5640:
            case 6547:
            case 7566:
            case 7917:
            case 7983:
            case 8078:
            case 8526:
            case 8567:
                return "CUO";
            case 5641:
            case 5645:
            case 5718:
            case 5740:
            case 5780:
            case 5861:
            case 5917:
            case 5919:
            case 6030:
            case 6146:
            case 6535:
            case 6691:
            case 6738:
            case 6753:
            case 6846:
            case 6857:
            case 6991:
            case 7044:
            case 7192:
            case 7360:
            case 7444:
            case 7557:
            case 7645:
            case 7827:
            case 8359:
            case 8506:
            case 8742:
            case 8748:
            case 8790:
                return "YAN";
            case 5642:
            case 5667:
            case 5860:
            case 5939:
            case 6207:
            case 6421:
            case 6457:
            case 6469:
            case 6540:
            case 6617:
            case 7062:
            case 7169:
            case 7286:
            case 7351:
            case 7663:
            case 7967:
            case 8574:
            case 8591:
                return "JUE";
            case 5643:
            case 5778:
            case 5944:
            case 6348:
            case 6765:
            case 6784:
            case 6889:
            case 7006:
            case 7065:
            case 7133:
            case 7675:
            case 7940:
            case 8024:
            case 8174:
            case 8247:
            case 8351:
                return "SI";
            case 5644:
            case 5843:
            case 5894:
            case 6262:
            case 7442:
            case 7639:
            case 7884:
                return "YE";
            case 5647:
            case 5922:
            case 7174:
            case 7839:
            case 7862:
            case 8011:
            case 8345:
                return "PO";
            case 5648:
            case 5659:
            case 6649:
            case 7003:
            case 7277:
            case 7433:
            case 7448:
            case 8007:
            case 8394:
            case 8657:
            case 8712:
                return "GUI";
            case 5650:
            case 5945:
            case 6048:
            case 6677:
            case 6774:
            case 7134:
            case 7614:
            case 7652:
            case 7730:
            case 7760:
            case 8125:
            case 8159:
            case 8289:
            case 8354:
            case 8693:
                return "BIAN";
            case 5652:
            case 5820:
            case 6341:
            case 7273:
            case 7550:
            case 8027:
                return "GUA";
            case 5653:
            case 5692:
            case 5707:
            case 6112:
            case 6115:
            case 6121:
            case 6347:
            case 6483:
            case 6922:
            case 7254:
            case 7364:
            case 7527:
            case 7880:
            case 8064:
            case 8236:
            case 8242:
            case 8286:
            case 8647:
            case 8778:
            case 8788:
                return "YOU";
            case 5655:
            case 5657:
            case 5670:
            case 5693:
            case 5711:
            case 5817:
            case 5961:
            case 5992:
            case 6018:
            case 6051:
            case 6072:
            case 6218:
            case 6236:
            case 6240:
            case 6258:
            case 6314:
            case 6329:
            case 6355:
            case 6362:
            case 6441:
            case 6470:
            case 6527:
            case 6558:
            case 6602:
            case 6634:
            case 6688:
            case 6689:
            case 6708:
            case 6884:
            case 6938:
            case 7068:
            case 7143:
            case 7376:
            case 7383:
            case 7461:
            case 7629:
            case 7658:
            case 7784:
            case 7838:
            case 7955:
            case 7978:
            case 8074:
            case 8089:
            case 8115:
            case 8120:
            case 8270:
            case 8415:
            case 8464:
            case 8472:
            case 8493:
            case 8780:
                return "YI";
            case 5656 :
            case 6751:
            case 6775:
            case 7223:
            case 8609:
                return "WEN";
            case 5658:
            case 6005:
            case 6423:
            case 7111:
            case 8728:
                return "KU";
            case 5660:
            case 5978:
            case 6160:
            case 6673:
            case 6693:
            case 7888:
            case 7920:
            case 7939:
                return "KAI";
            case 5663:
            case 5808:
            case 5923:
            case 5979:
            case 6047:
            case 6890:
            case 7009:
            case 7051:
            case 7083:
            case 7594:
            case 7844:
            case 8062:
            case 8321:
            case 8414:
            case 8539:
            case 8713:
                return "SHAN";
            case 5664:
            case 6025:
            case 6150:
            case 7093:
            case 7126:
            case 7194:
            case 7568:
            case 7821:
            case 8274:
                return "WAN";
            case 5665:
            case 5906:
            case 6364:
            case 6586:
            case 7558:
                return "KUAI";
            case 5666:
            case 6449:
            case 7046:
            case 7146:
            case 7372:
            case 7809:
            case 8310:
                return "PIAO";
            case 5668:
            case 5829:
            case 5859:
            case 6081:
            case 6529:
            case 6724:
            case 6730:
            case 7352:
            case 7745:
            case 8546:
            case 8719:
                return "QIAO";
            case 5669:
            case 6229:
            case 6311:
            case 6475:
            case 6623:
            case 7856:
            case 7933:
            case 7976:
            case 8175:
            case 8322:
                return "HUO";
            case 5671:
            case 6339:
            case 7544:
                return "KA";
            case 5672:
            case 6244:
            case 6715:
            case 7394:
            case 8745:
                return "WANG";
            case 5674:
            case 6404:
            case 7164:
            case 7575:
            case 7754:
            case 7814:
            case 8059:
            case 8184:
            case 8490:
                return "DING";
            case 5675:
            case 5921:
            case 6504:
            case 6554:
            case 6615:
            case 7049:
            case 7216:
            case 8315:
                return "ZHANG";
            case 5679:
            case 5973:
            case 6057:
            case 6769:
            case 7504:
            case 7866:
                return "MU";
            case 5680:
            case 6083:
            case 6156:
            case 6631:
            case 7377:
            case 7994:
            case 8137:
                return "REN";
            case 5683:
            case 5975:
            case 6275:
            case 6512:
            case 6934:
            case 7011:
            case 7180:
            case 7266:
            case 7518:
            case 7728:
            case 7793:
            case 8073:
                return "YA";
            case 5684:
            case 7020:
            case 7580:
                return "WA";
            case 5686:
            case 5943:
            case 6041:
            case 6137:
            case 6568:
            case 6660:
            case 6749:
            case 7029:
            case 7047:
            case 7438:
            case 7509:
            case 8680:
                return "CHANG";
            case 5687:
                return "CANG";
            case 5688:
            case 6742:
            case 7854:
                return "KANG";
            case 5689:
            case 5710:
            case 5905:
            case 6049:
            case 6079:
            case 6808:
            case 6830:
            case 6883:
            case 7244:
            case 7338:
            case 7345:
            case 7636:
            case 7889:
            case 8070:
            case 8081:
            case 8335:
            case 8371:
            case 8422:
            case 8467:
            case 8578:
            case 8770:
                return "ZHU";
            case 5690:
            case 6344:
            case 6924:
            case 8187:
                return "NING";
            case 5694:
            case 5824:
            case 6524:
            case 6960:
            case 7037:
            case 7135:
            case 7259:
            case 7477:
            case 7616:
            case 8349:
            case 8384:
            case 8724:
                return "GOU";
            case 5701:
            case 5758:
            case 6077:
            case 6444:
            case 6690:
            case 6892:
            case 7737:
                return "TONG";
            case 5703:
            case 5972:
            case 6605:
            case 6685:
            case 7439:
            case 7627:
            case 7711:
            case 7794:
            case 7874:
            case 8682:
                return "NI";
            case 5704:
            case 5903:
            case 6171:
            case 6521:
            case 6804:
            case 6940:
            case 7176:
            case 7409:
            case 7546:
            case 7702:
            case 7882:
            case 7956:
            case 8072:
            case 8142:
            case 8244:
            case 8353:
            case 8434:
            case 8542:
                return "JIA";
            case 5706:
            case 6939:
            case 7177:
            case 7879:
            case 8025:
            case 8660:
                return "ER";
            case 5708:
                return "KUA";
            case 5709:
            case 6108:
            case 7412:
            case 7772:
            case 7811:
                return "KAN";
            case 5712:
            case 7686:
            case 8127:
            case 8272:
            case 8352:
            case 8448:
            case 8622:
            case 8670:
            case 8756:
                return "TIAO";
            case 5713:
            case 7846:
            case 8091:
            case 8218:
                return "CHAI";
            case 5714:
            case 5753:
            case 6020:
            case 6090:
            case 6256:
            case 6461:
            case 6572:
            case 7015:
            case 7524:
            case 8008:
            case 8052:
            case 8252:
            case 8520:
            case 8551:
            case 8662:
                return "JIAO";
            case 5715:
            case 6370:
                return "NONG";
            case 5716:
            case 6372:
            case 7788:
            case 8254:
            case 8290:
            case 8642:
                return "MOU";
            case 5717:
            case 6492:
            case 6716:
            case 8112:
            case 8637:
                return "CHOU";
            case 5720:
            case 5947:
            case 6576:
            case 6848:
            case 6947:
            case 6957:
            case 7317:
            case 7468:
            case 8216:
            case 8239:
            case 8288:
            case 8435:
            case 8460:
            case 8690:
            case 8792:
                return "QIU";
            case 5723:
            case 7019:
            case 7250:
            case 8650:
                return "PING";
            case 5724:
            case 5953:
            case 6013:
            case 6415:
            case 6728:
            case 7163:
            case 7962:
            case 8014:
            case 8711:
            case 8751:
                return "YONG";
            case 5726:
            case 5926:
            case 6155:
            case 6384:
            case 6767:
            case 7731:
                return "FENG";
            case 5727:
            case 5761:
            case 5868:
            case 6023:
            case 6045:
            case 6071:
            case 6271:
            case 6509:
            case 6705:
            case 6727:
            case 6925:
            case 6926:
            case 6929:
            case 7155:
            case 7293:
            case 7541:
            case 7709:
            case 7852:
            case 8215:
            case 8373:
                return "QIAN";
            case 5728:
            case 8372:
                return "RUO";
            case 5729:
            case 6169:
            case 6363:
                return "PAI";
            case 5730:
            case 5834:
            case 6310:
            case 6823:
            case 6835:
            case 6910:
            case 7644:
            case 7690:
            case 7729:
            case 7977:
                return "ZHUO";
            case 5731:
            case 5951:
            case 6136:
            case 6283:
            case 6780:
            case 6888:
            case 7013:
            case 7508:
            case 7582:
            case 7988:
                return "SHU";
            case 5732:
            case 5789:
            case 6093:
            case 6259:
            case 6291:
            case 6604:
            case 6788:
            case 6880:
            case 7183:
            case 7301:
            case 7565:
            case 7961:
            case 8107:
            case 8635:
                return "LUO";
            case 5733:
            case 6111:
            case 6502:
            case 6855:
            case 7531:
            case 7750:
            case 8627:
                return "WO";
            case 5735:
            case 6709:
            case 6949:
            case 7130:
            case 8035:
            case 8151:
            case 8514:
                return "TI";
            case 5736:
            case 6124:
            case 6272:
            case 6842:
            case 7834:
            case 8057:
            case 8170:
            case 8704:
                return "GUAN";
            case 5737:
            case 6539:
            case 8377:
                return "KONG";
            case 5738:
            case 5810:
            case 6036:
            case 6058:
            case 6076:
            case 6268:
            case 6965:
            case 6980:
            case 7202:
            case 7307:
            case 7316:
            case 7323:
            case 7357:
            case 7381:
            case 7488:
            case 7611:
            case 7850:
            case 7924:
            case 8022:
            case 8132:
            case 8153:
            case 8482:
            case 8522:
            case 8565:
            case 8620:
            case 8634:
            case 8722:
                return "JU";
            case 5739:
            case 6915:
            case 7291:
            case 8687:
            case 8787:
                return "FEN";
            case 5741:
            case 5784:
            case 5936:
            case 5938:
            case 6215:
            case 6302:
            case 6619:
            case 6661:
            case 6845:
            case 6912:
            case 6966:
            case 7105:
            case 7151:
            case 7331:
            case 7339:
            case 8583:
                return "XIE";
            case 5743:
            case 5835:
            case 5881:
            case 5883:
            case 6158:
            case 6217:
            case 6488:
            case 6501:
            case 6543:
            case 6545:
            case 6611:
            case 6612:
            case 6739:
            case 6777:
            case 6802:
            case 6822:
            case 6952:
            case 7024:
            case 7166:
            case 7224:
            case 7406:
            case 7631:
            case 7648:
            case 8084:
            case 8426:
            case 8659:
                return "WEI";
            case 5744:
            case 7574:
            case 8453:
                return "ZONG";
            case 5745:
            case 6168:
            case 6422:
            case 6548:
            case 7946:
            case 8092:
            case 8179:
            case 8287:
            case 8735:
                return "LOU";
            case 5746:
            case 6491:
            case 6871:
            case 7209:
            case 7344:
            case 7906:
            case 7959:
            case 8177:
            case 8305:
            case 8311:
            case 8442:
            case 8517:
                return "TANG";
            case 5747:
            case 6557:
            case 7145:
            case 7167:
            case 7336:
            case 7375:
            case 7587:
            case 7957:
            case 8738:
            case 8762:
                return "BIN";
            case 5748:
            case 6289:
            case 6386:
            case 7927:
                return "NUO";
            case 5749:
            case 6015:
            case 6061:
            case 6319:
            case 6374:
            case 6420:
            case 6445:
            case 6633:
            case 7042:
            case 7523:
            case 7787:
            case 8023:
            case 8101:
            case 8161:
            case 8231:
            case 8304:
            case 8355:
            case 8388:
            case 8489:
            case 8556:
            case 8746:
                return "CHI";
            case 5750:
            case 5766:
            case 5884:
            case 5913:
            case 6130:
            case 6163:
            case 6191:
            case 6241:
            case 6381:
            case 6567:
            case 6630:
            case 6750:
            case 6827:
            case 6832:
            case 6979:
            case 7050:
            case 7184:
            case 7356:
            case 7456:
            case 7474:
            case 7604:
            case 7668:
            case 7689:
            case 7691:
            case 8010:
            case 8122:
            case 8265:
            case 8303:
            case 8312:
            case 8410:
            case 8424:
            case 8443:
            case 8449:
            case 8466:
            case 8521:
            case 8791:
                return "XI";
            case 5751:
            case 5869:
            case 6128:
            case 6616:
            case 6729:
            case 6794:
            case 6941:
            case 6982:
            case 7026:
            case 7534:
            case 7554:
            case 7570:
            case 7626:
                return "JIANG";
            case 5752:
            case 5841:
            case 5857:
            case 6149:
            case 6183:
            case 6286:
            case 6853:
            case 6931:
            case 6932:
            case 7144:
            case 7237:
            case 7305:
            case 7407:
            case 7415:
            case 7480:
            case 7489:
            case 7506:
            case 7576:
            case 7790:
            case 7921:
            case 8047:
            case 8148:
            case 8340:
            case 8469:
            case 8534:
            case 8561:
            case 8668:
            case 8721:
                return "JIAN";
            case 5754:
            case 6417:
            case 6746:
            case 7249:
            case 7274:
            case 8015:
            case 8053:
            case 8481:
            case 8761:
                return "JIU";
            case 5755:
                return "ZHUANG";
            case 5756:
            case 5846:
            case 6170:
            case 6279:
            case 6789:
            case 6854:
            case 6886:
            case 7215:
            case 7324:
            case 7449:
            case 7637:
            case 7651:
            case 7759:
            case 7871:
            case 7964:
            case 8071:
                return "XUAN";
            case 5757:
            case 6144:
            case 6402:
            case 7373:
            case 7470:
            case 7781:
            case 8067:
            case 8087:
            case 8185:
            case 8376:
                return "DAN";
            case 5759:
            case 6629:
            case 7453:
            case 7564:
                return "TUN";
            case 5762:
            case 6147:
            case 7963:
                return "ZU";
            case 5763:
            case 6914:
            case 7348:
            case 7530:
            case 7865:
                return "YUE";
            case 5764:
            case 6305:
            case 7664:
            case 7973:
                return "CUAN";
            case 5767:
            case 5908:
            case 5987:
            case 6087:
            case 6101:
            case 6206:
            case 6225:
            case 6530:
            case 6563:
            case 6620:
            case 6694:
            case 6813:
            case 6817:
            case 7454:
            case 8131:
            case 8524:
            case 8664:
                return "XUN";
            case 5768:
            case 5774:
            case 5807:
            case 6106:
            case 6214:
            case 6216:
            case 6740:
            case 6792:
                return "HONG";
            case 5769:
            case 6266:
            case 6335:
            case 6494:
            case 6538:
            case 6603:
            case 7304:
            case 7529:
            case 8188:
            case 8268:
            case 8269:
                return "GUO";
            case 5770:
            case 5838:
            case 6159:
            case 6667:
            case 6893:
            case 6904:
            case 6981:
            case 7031:
            case 7086:
            case 7472:
            case 7688:
            case 7966:
            case 8324:
            case 8580:
                return "CHAN";
            case 5773:
            case 6459:
            case 6863:
            case 6907:
            case 7217:
            case 7511:
            case 7968:
            case 7972:
            case 8575:
                return "PU";
            case 5775:
            case 5776:
            case 5914:
            case 6029:
            case 6062:
            case 6119:
            case 6142:
            case 6252:
            case 6327:
            case 6505:
            case 6686:
            case 6870:
            case 6985:
            case 7058:
            case 7066:
            case 7106:
            case 7108:
            case 7285:
            case 7471:
            case 7680:
            case 7741:
            case 7774:
            case 7775:
            case 7823:
            case 7991:
            case 8005:
            case 8222:
            case 8261:
            case 8280:
            case 8283:
            case 8479:
            case 8535:
            case 8538:
            case 8654:
            case 8691:
                return "FU";
            case 5777:
            case 5853:
            case 6188:
            case 6428:
            case 6726:
            case 6819:
            case 8389:
            case 8602:
            case 8653:
                return "SU";
            case 5781:
            case 6403:
            case 6636:
            case 7362:
            case 7502:
            case 7771:
            case 7864:
            case 8030:
            case 8404:
            case 8543:
            case 8559:
                return "BO";
            case 5782:
            case 7121:
            case 7762:
            case 8671:
                return "GUN";
            case 5783:
            case 6066:
            case 6525:
            case 6787:
            case 7203:
            case 7436:
            case 7483:
            case 7503:
            case 7624:
            case 7714:
            case 7806:
            case 8317:
            case 8754:
                return "MAO";
            case 5785:
            case 7014:
            case 7279:
            case 8029:
            case 8639:
                return "LUAN";
            case 5786:
            case 6269:
                return "POU";
            case 5787:
            case 5891:
            case 6280:
                return "BING";
            case 5788:
            case 5911:
            case 6067:
            case 6094:
            case 6126:
            case 6151:
            case 6186:
            case 6292:
            case 6451:
            case 6663:
            case 6862:
            case 6875:
            case 6913:
            case 7188:
            case 7212:
            case 7326:
            case 7584:
            case 8048:
            case 8108:
            case 8203:
            case 8331:
                return "YING";
            case 5792:
            case 6392:
            case 6481:
            case 6518:
            case 6609:
            case 6679:
            case 6717:
            case 6816:
            case 6879:
            case 7190:
            case 7346:
            case 7385:
            case 7618:
            case 7635:
            case 7646:
            case 7670:
            case 7672:
            case 7679:
            case 8013:
            case 8032:
            case 8041:
            case 8055:
            case 8343:
            case 8513:
            case 8590:
                return "HU";
            case 5793:
            case 5988:
            case 6270:
            case 6354:
            case 6803:
            case 8483:
            case 8581:
            case 8764:
                return "LIE";
            case 5794:
            case 5823:
            case 6040:
            case 6118:
            case 6226:
            case 6513:
            case 6593:
            case 6963:
            case 7021:
            case 7515:
            case 7662:
            case 7676:
            case 8034:
            case 8079:
            case 8225:
            case 8358:
            case 8444:
            case 8503:
            case 8548:
            case 8549:
            case 8617:
                return "XIAN";
            case 5801:
            case 6131:
            case 6534:
            case 6552:
            case 6676:
            case 6704:
            case 6833:
            case 8121:
                return "SONG";
            case 5803:
            case 7981:
            case 8314:
            case 8417:
            case 8564:
                return "ZHONG";
            case 5804:
            case 6088:
            case 6873:
            case 7452:
            case 7808:
            case 8504:
                return "MING";
            case 5806:
            case 5821:
            case 6255:
            case 6414:
            case 7028:
            case 7061:
            case 7278:
            case 7757:
            case 8060:
            case 8201:
            case 8227:
            case 8441:
            case 8658:
            case 8726:
                return "JIE";
            case 5809:
            case 6670:
            case 7417:
            case 8178:
                return "OU";
            case 5811:
                return "NE";
            case 5813:
            case 5932:
            case 5954:
            case 6432:
            case 6756:
            case 7434:
            case 7833:
            case 8202:
            case 8234:
            case 8471:
                return "HE";
            case 5815:
            case 7294:
            case 7840:
            case 8341:
                return "ZHAO";
            case 5816:
            case 5930:
            case 6201:
            case 6230:
            case 6511:
            case 6573:
            case 6754:
            case 7219:
            case 7479:
            case 7512:
            case 7552:
            case 7678:
            case 7765:
            case 8119:
            case 8248:
            case 8329:
            case 8480:
            case 8636:
            case 8781:
                return "QU";
            case 5818:
            case 5831:
            case 5887:
            case 5959:
            case 6237:
            case 6349:
            case 7094:
            case 7460:
                return "KUANG";
            case 5822:
            case 6078:
            case 6086:
            case 6205:
            case 6352:
            case 6360:
            case 6425:
            case 6736:
            case 6807:
            case 6811:
            case 6971:
            case 7132:
            case 7185:
            case 7445:
            case 7703:
            case 8219:
            case 8319:
            case 8766:
                return "HUI";
            case 5825:
            case 6085:
            case 6710:
            case 7125:
            case 7390:
            case 7816:
            case 7893:
            case 8273:
            case 8360:
            case 8760:
                return "QUAN";
            case 5826:
            case 6531:
            case 6571:
            case 7859:
            case 7903:
            case 8361:
                return "ZHENG";
            case 5827:
            case 6638:
            case 6752:
            case 6867:
                return "HUN";
            case 5828:
            case 5935:
            case 5955:
            case 6203:
            case 6810:
            case 6851:
            case 7179:
            case 7282:
            case 7667:
            case 7776:
            case 8167:
            case 8458:
            case 8515:
                return "XU";
            case 5833:
            case 5878:
            case 5924:
            case 7067:
            case 8677:
                return "ZOU";
            case 5837:
            case 6123:
            case 6351:
            case 6841:
            case 7309:
            case 7547:
            case 7982:
            case 8255:
                return "SHEN";
            case 5839:
            case 6120:
            case 6901:
            case 6968:
            case 7661:
            case 7785:
            case 7801:
                return "SUI";
            case 5840:
            case 5863:
            case 6251:
            case 6433:
            case 6923:
            case 7201:
            case 7320:
            case 7755:
            case 8619:
                return "CHEN";
            case 5842:
            case 7720:
            case 8529:
            case 8708:
                return "XUE";
            case 5847:
            case 5991:
            case 6278:
            case 6577:
            case 6654:
            case 7281:
            case 7907:
            case 8038:
            case 8786:
                return "AN";
            case 5849:
            case 6549:
            case 7002:
            case 7060:
            case 7127:
            case 7287:
            case 7402:
            case 7463:
            case 7707:
            case 7786:
            case 7937:
            case 7986:
            case 8172:
            case 8342:
            case 8450:
            case 8484:
            case 8594:
            case 8604:
            case 8623:
            case 8686:
            case 8758:
                return "ZI";
            case 5850:
            case 7073:
            case 7490:
            case 7561:
            case 8470:
            case 8568:
                return "PIAN";
            case 5851:
            case 6052:
            case 6175:
            case 6641:
            case 7038:
            case 7366:
            case 7950:
            case 7987:
            case 8102:
            case 8182:
            case 8586:
            case 8588:
            case 8765:
                return "MO";
            case 5852:
            case 5942:
            case 6148:
            case 6920:
            case 7724:
            case 7885:
            case 8141:
                return "DANG";
            case 5854:
            case 5985:
            case 6110:
            case 6173:
            case 6317:
            case 7388:
            case 7459:
            case 7634:
            case 7870:
            case 8307:
            case 8334:
            case 8363:
            case 8525:
            case 8669:
            case 8685:
                return "SHI";
            case 5856:
            case 6301:
            case 7247:
            case 7392:
            case 7761:
            case 8049:
            case 8162:
            case 8256:
            case 8487:
                return "ZHE";
            case 5858:
                return "ZEN";
            case 5862:
            case 6288:
            case 7625:
                return "ZHAN";
            case 5865:
            case 6103:
            case 6132:
            case 6468:
            case 6643:
            case 6659:
            case 7138:
            case 7210:
            case 7340:
            case 7465:
            case 7478:
            case 8138:
                return "JIN";
            case 5870:
            case 5964:
            case 7851:
            case 8103:
            case 8113:
            case 8418:
                return "BAN";
            case 5871:
            case 5967:
            case 6559:
            case 7172:
            case 7868:
            case 8116:
            case 8118:
            case 8401:
            case 8558:
                return "DIAN";
            case 5872:
            case 6382:
            case 6460:
            case 6684:
            case 7549:
            case 7681:
                return "ZUO";
            case 5874:
            case 6084:
            case 6309:
            case 6712:
            case 7742:
                return "XING";
            case 5877:
            case 6233:
            case 6431:
            case 8208:
            case 8411:
            case 8570:
                return "NIE";
            case 5879:
            case 7302:
            case 7319:
                return "CHUI";
            case 5882:
            case 6569:
            case 6850:
            case 6874:
            case 6956:
            case 7211:
            case 7533:
            case 8105:
            case 8308:
            case 8382:
            case 8692:
                return "HUANG";
            case 5885:
            case 6153:
            case 6294:
            case 6759:
            case 6911:
            case 7447:
            case 7642:
            case 8192:
            case 8205:
            case 8232:
            case 8793:
                return "HAN";
            case 5886:
            case 6068:
            case 8123:
            case 8243:
            case 8344:
            case 8528:
            case 8638:
                return "QIONG";
            case 5888:
            case 6861:
            case 7743:
            case 8294:
                return "MANG";
            case 5890:
            case 7242:
            case 7853:
            case 8419:
            case 8648:
                return "FANG";
            case 5902:
            case 6223:
            case 6330:
            case 7070:
            case 7536:
            case 7638:
            case 7849:
            case 8544:
            case 8656:
                return "TAI";
            case 5907:
            case 6711:
            case 7010:
            case 7492:
            case 7938:
            case 8370:
                return "QIE";
            case 5909:
            case 6031:
            case 6581:
            case 6702:
            case 6719:
            case 7101:
            case 7225:
            case 7370:
            case 7432:
            case 7521:
            case 7657:
                return "YUN";
            case 5916:
            case 6903:
            case 7428:
            case 7694:
            case 7867:
            case 7936:
            case 8191:
                return "TAN";
            case 5918:
            case 6590:
            case 6824:
            case 7280:
            case 7835:
            case 7935:
            case 7952:
            case 8633:
                return "JUAN";
            case 5925:
            case 6063:
            case 6342:
            case 6482:
            case 6786:
            case 7117:
            case 7258:
            case 7289:
            case 7418:
            case 8186:
            case 8240:
            case 8465:
            case 8676:
                return "LING";
            case 5928:
            case 6140:
            case 6307:
            case 6487:
            case 6621:
            case 6801:
            case 6829:
            case 6881:
            case 6930:
            case 6953:
            case 7157:
            case 7944:
            case 8673:
            case 8763:
                return "HUAN";
            case 5929:
            case 6104:
            case 8618:
                return "MAI";
            case 5931:
            case 6070:
            case 6891:
            case 7228:
            case 8366:
            case 8425:
                return "SHAO";
            case 5934:
            case 6189:
            case 6211:
            case 6734:
            case 7592:
            case 7770:
            case 8221:
            case 8276:
            case 8323:
            case 8427:
            case 8431:
                return "MENG";
            case 5937:
            case 6220:
            case 6418:
            case 6453:
            case 6640:
            case 6849:
            case 7612:
            case 7804:
            case 7943:
            case 8284:
                return "SOU";
            case 5946:
            case 5948:
            case 7458:
            case 7928:
                return "BEN";
            case 5950:
            case 7732:
                return "FA";
            case 5958:
            case 6172:
            case 6805:
            case 7139:
            case 7269:
            case 7327:
            case 7384:
            case 7466:
            case 7551:
            case 7562:
            case 7685:
            case 7819:
            case 8001:
            case 8018:
            case 8380:
                return "ZHEN";
            case 5965:
            case 6053:
            case 6247:
            case 6306:
            case 6779:
            case 6838:
            case 6887:
            case 7104:
            case 7347:
            case 7426:
            case 7723:
            case 8065:
            case 8491:
                return "GAN";
            case 5966:
            case 6055:
            case 6781:
            case 7171:
            case 7248:
            case 7542:
            case 7735:
            case 8110:
                return "LONG";
            case 5968:
            case 6303:
            case 6464:
            case 6782:
            case 6843:
            case 6885:
            case 6954:
            case 7220:
            case 7251:
            case 7354:
            case 7391:
            case 7404:
            case 7510:
            case 7545:
            case 7969:
            case 8021:
            case 8056:
            case 8392:
            case 8421:
            case 8652:
                return "LU";
            case 5969:
            case 7726:
                return "CHE";
            case 5974:
            case 6254:
            case 6427:
            case 6514:
            case 6658:
            case 6959:
            case 7033:
            case 7081:
            case 7365:
            case 8190:
            case 8292:
            case 8643:
            case 8701:
            case 8773:
                return "AO";
            case 5976:
            case 6006:
            case 6273:
            case 6409:
            case 7526:
            case 8012:
            case 8183:
            case 8562:
            case 8688:
                return "DIE";
            case 5977:
            case 6343:
            case 6520:
            case 6528:
            case 7517:
            case 7543:
            case 7556:
            case 7747:
            case 8020:
                return "DONG";
            case 5980:
            case 7120:
            case 7368:
            case 7656:
            case 8592:
                return "SHANG";
            case 5989:
            case 6011:
            case 6282:
            case 6768:
            case 7034:
            case 7205:
            case 7358:
            case 7528:
            case 7783:
            case 8016:
            case 8302:
            case 8378:
            case 8629:
                return "YUAN";
            case 6001:
            case 6456:
            case 6681:
            case 8318:
                return "PENG";
            case 6002:
            case 6117:
            case 6143:
            case 7842:
            case 8509:
                return "TU";
            case 6003:
            case 7150:
            case 7156:
            case 7593:
            case 8094:
            case 8694:
                return "SAO";
            case 6004:
            case 6316:
            case 6523:
            case 6942:
            case 7110:
            case 7173:
            case 8776:
                return "DAI";
            case 6009:
            case 6565:
            case 6943:
            case 8090:
            case 8383:
            case 8455:
            case 8655:
            case 8731:
                return "HOU";
            case 6012:
            case 6503:
            case 7147:
            case 7655:
            case 7960:
            case 8209:
            case 8293:
            case 8709:
            case 8720:
                return "MAN";
            case 6022:
            case 6429:
            case 6834:
            case 7292:
            case 7525:
            case 8328:
            case 8338:
            case 8739:
            case 8782:
                return "DU";
            case 6026:
                return "XIONG";
            case 6028:
            case 6157:
            case 6635:
            case 6652:
            case 7088:
            case 7129:
            case 8313:
            case 8663:
            case 8747:
                return "XIANG";
            case 6032:
            case 6584:
            case 6713:
            case 6839:
            case 6990:
            case 7119:
            case 7328:
            case 7572:
            case 7619:
            case 7673:
            case 7948:
            case 8082:
            case 8267:
            case 8385:
            case 8468:
            case 8613:
            case 8678:
                return "FEI";
            case 6039:
            case 6208:
            case 7236:
            case 7803:
            case 8224:
                return "RUI";
            case 6042:
            case 6840:
            case 7085:
            case 7193:
            case 7214:
            case 7240:
                return "CONG";
            case 6043:
            case 6276:
            case 6336:
            case 6426:
            case 6463:
            case 6858:
            case 7353:
            case 7923:
            case 8291:
            case 8432:
                return "QIN";
            case 6050:
            case 6202:
            case 6321:
            case 7778:
            case 8356:
                return "KOU";
            case 6054:
            case 7513:
                return "PIE";
            case 6056:
            case 6135:
            case 6517:
            case 7857:
            case 8446:
            case 8649:
            case 8741:
                return "BA";
            case 6059:
            case 8237:
            case 8755:
                return "RAN";
            case 6060:
            case 6485:
            case 7349:
            case 7764:
            case 8263:
            case 8332:
            case 8368:
            case 8605:
            case 8675:
            case 8784:
                return "QING";
            case 6064:
            case 7053:
            case 7569:
            case 8433:
                return "NIAO";
            case 6069:
            case 6526:
            case 6741:
            case 6793:
            case 7137:
            case 7168:
            case 7175:
            case 7710:
            case 8628:
            case 8710:
                return "MIN";
            case 6073:
            case 7012:
            case 7267:
                return "RAO";
            case 6075:
            case 6358:
            case 7684:
            case 8043:
            case 8457:
                return "CI";
            case 6080:
            case 6167:
            case 7035:
            case 7272:
            case 7890:
            case 8249:
            case 8610:
                return "TING";
            case 6091:
            case 6671:
            case 6731:
            case 8409:
            case 8430:
                return "CHONG";
            case 6092:
            case 6814:
            case 7113:
            case 7154:
            case 7481:
            case 7768:
            case 8180:
            case 8461:
            case 8488:
                return "JIANG";
            case 6105:
            case 6588:
            case 6624:
            case 7330:
            case 8632:
                return "SUN";
            case 6107:
            case 6390:
            case 7008:
            case 7091:
            case 7107:
            case 7548:
            case 7756:
            case 8406:
            case 8492:
                return "ZHOU";
            case 6114:
            case 6550:
            case 6613:
            case 6828:
            case 6856:
            case 7325:
            case 7949:
            case 8044:
            case 8139:
            case 8740:
                return "MEI";
            case 6127:
            case 8040:
            case 8277:
                return "CHUN";
            case 6138:
            case 8749:
                return "TIE";
            case 6145:
            case 6393:
            case 6718:
            case 7213:
            case 7333:
            case 7505:
            case 8631:
                return "CUI";
            case 6154:
            case 8736:
                return "QIA";
            case 6165:
            case 7063:
            case 7650:
            case 8017:
            case 8157:
            case 8532:
            case 8621:
                return "BAO";
            case 6166:
            case 7243:
            case 8365:
                return "PA";
            case 6174:
            case 6224:
            case 6473:
            case 6818:
            case 6865:
            case 6906:
            case 7140:
            case 7908:
            case 8164:
            case 8212:
                return "RU";
            case 6176:
            case 6284:
                return "EN";
            case 6178:
            case 6219:
                return "WENG";
            case 6179:
            case 6222:
            case 6438:
            case 6467:
            case 6909:
            case 6916:
            case 7427:
            case 8009:
            case 8211:
            case 8226:
                return "HAO";
            case 6180:
            case 6562:
            case 6607:
            case 7367:
            case 8501:
            case 8530:
            case 8577:
                return "CU";
            case 6182:
            case 6826:
                return "BANG";
            case 6184:
            case 6287:
            case 6989:
            case 7335:
            case 7869:
                return "SHUO";
            case 6190:
            case 8128:
            case 8229:
            case 8391:
                return "DOU";
            case 6194:
            case 6388:
            case 6555:
            case 6662:
            case 6733:
            case 6964:
            case 7361:
            case 7405:
            case 7602:
            case 7812:
            case 8452:
            case 8579:
            case 8775:
                return "LIN";
            case 6204:
            case 6245:
            case 6458:
            case 6618:
            case 6928:
            case 7152:
            case 7841:
            case 8051:
                return "LIAO";
            case 6209:
                return "ZUI";
            case 6212:
            case 6232:
            case 6506:
            case 7283:
            case 7660:
            case 7818:
            case 8576:
                return "FAN";
            case 6231:
            case 7181:
            case 7276:
                return "HENG";
            case 6239:
            case 6353:
            case 6410:
            case 6682:
            case 7007:
            case 8155:
            case 8346:
            case 8716:
            case 8718:
                return "DA";
            case 6242:
            case 7064:
            case 7416:
                return "ZANG";
            case 6243:
            case 6583:
            case 6650:
            case 7567:
            case 8069:
                return "PAO";
            case 6246:
            case 7056:
            case 7057:
            case 7424:
            case 7837:
                return "GA";
            case 6249:
            case 7643:
            case 7715:
            case 7845:
                return "MEN";
            case 6250:
            case 6972:
                return "TUAN";
            case 6253:
            case 7016:
            case 7041:
            case 7315:
            case 7482:
            case 8213:
                return "PIN";
            case 6257:
            case 6338:
                return "ZA";
            case 6260:
            case 8168:
            case 8362:
            case 8769:
                return "JUN";
            case 6261:
            case 6735:
            case 6757:
            case 7369:
            case 7817:
                return "TIAN";
            case 6263:
            case 6440:
            case 7040:
            case 7208:
            case 7451:
            case 7733:
            case 7945:
            case 8616:
                return "AI";
            case 6264:
            case 7539:
            case 7953:
            case 8136:
                return "NA";
            case 6267:
            case 6334:
            case 7494:
                return "BAI";
            case 6274:
            case 6324:
            case 6369:
            case 6378:
            case 6610:
            case 7736:
            case 8068:
            case 8238:
            case 8794:
                return "ZHA";
            case 6285:
            case 6408:
            case 7590:
            case 8563:
                return "CHUAI";
            case 6290:
            case 7763:
            case 8210:
                return "SANG";
            case 6304:
            case 7355:
            case 8714:
                return "ZUN";
            case 6312:
            case 7158:
            case 8582:
                return "ZUAN";
            case 6313:
            case 6476:
            case 6646:
            case 7457:
                return "NANG";
            case 6315:
            case 7693:
            case 7911:
                return "TE";
            case 6318:
            case 6945:
            case 7419:
            case 7446:
            case 7848:
            case 7863:
            case 8519:
                return "BU";
            case 6322:
            case 6665:
            case 7514:
            case 8478:
                return "DAO";
            case 6325:
            case 6436:
            case 7571:
            case 7925:
                return "A";
            case 6328:
                return "M";
            case 6340:
            case 6582:
            case 6958:
            case 7206:
            case 7252:
            case 7744:
            case 8093:
            case 8333:
            case 8779:
                return "XIA";
            case 6345:
            case 6365:
            case 6785:
            case 7122:
            case 7876:
            case 8154:
            case 8566:
                return "DUO";
            case 6356:
            case 6537:
            case 6876:
            case 6948:
            case 7071:
            case 7115:
            case 7241:
            case 7253:
            case 8257:
            case 8367:
            case 8379:
            case 8744:
                return "XIAO";
            case 6359:
            case 6578:
            case 7270:
            case 7555:
                return "GUANG";
            case 6361:
            case 6522:
            case 6642:
            case 6651:
            case 6869:
            case 8028:
            case 8587:
            case 8759:
                return "XIU";
            case 6373:
            case 6579:
            case 7054:
            case 7231:
            case 8301:
                return "MA";
            case 6376:
            case 6657:
            case 7114:
            case 8665:
                return "GENG";
            case 6379:
            case 6434:
            case 6442:
            case 7022:
            case 7288:
            case 7792:
            case 8440:
                return "SUO";
            case 6380:
                return "ZAO";
            case 6387:
            case 6967:
            case 7131:
            case 7149:
            case 7234:
            case 7721:
            case 7780:
            case 8037:
                return "MIAO";
            case 6389:
            case 6645:
            case 8207:
                return "ZHUAN";
            case 6394:
            case 7606:
            case 7901:
            case 8080:
            case 8436:
            case 8614:
            case 8672:
                return "SHA";
            case 6401:
                return "YO";
            case 6407:
                return "SHUA";
            case 6411:
            case 6478:
            case 6479:
            case 7310:
            case 7578:
            case 8279:
            case 8486:
                return "NAN";
            case 6424:
            case 6462:
                return "O";
            case 6430:
            case 6519:
            case 6701:
            case 6859:
            case 7076:
            case 7128:
            case 7170:
            case 7380:
            case 7520:
            case 7807:
            case 7861:
            case 7930:
            case 7993:
            case 8066:
            case 8129:
            case 8204:
            case 8282:
            case 8733:
                return "KE";
            case 6437:
                return "N";
            case 6439:
                return "DIA";
            case 6443:
            case 7560:
            case 8516:
                return "HAI";
            case 6446:
            case 6490:
            case 7623:
            case 7934:
            case 8512:
            case 8612:
                return "PEI";
            case 6448:
            case 6878:
            case 8309:
            case 8429:
                return "CAO";
            case 6452:
            case 7420:
                return "BENG";
            case 6465:
                return "CENG";
            case 6466:
            case 6556:
            case 7413:
            case 7767:
            case 7975:
            case 8403:
                return "DENG";
            case 6471:
                return "SAI";
            case 6474:
            case 7769:
                return "CA";
            case 6515:
            case 6825:
                return "CEN";
            case 6541:
            case 6585:
            case 7337:
            case 7532:
            case 8278:
                return "RONG";
            case 6544:
            case 7162:
                return "ZAI";
            case 6551:
            case 7441:
            case 7782:
            case 8347:
                return "SHENG";
            case 6561:
            case 6872:
            case 6944:
            case 8306:
                return "PANG";
            case 6564:
            case 6683:
            case 7630:
            case 7640:
            case 7706:
            case 8253:
            case 8717:
                return "YANG";
            case 6580:
            case 6678:
            case 7004:
                return "NIU";
            case 6587:
            case 7123:
            case 8428:
                return "SHOU";
            case 6601:
                return "SUAN";
            case 6622:
            case 6955:
            case 7516:
            case 7843:
            case 8413:
                return "CHUAN";
            case 6639:
            case 6766:
            case 7017:
            case 7230:
            case 7311:
            case 7322:
            case 7363:
            case 7942:
            case 7979:
            case 8135:
                return "CHA";
            case 6644:
            case 7507:
            case 8454:
                return "SAN";
            case 6666:
            case 8169:
                return "CUN";
            case 6675:
                return "CHUANG";
            case 6687:
            case 7443:
            case 8173:
                return "CHAO";
            case 6692:
                return "CE";
            case 6723:
            case 7077:
            case 7136:
                return "ZHUI";
            case 6737:
            case 6844:
                return "SHUAN";
            case 6743:
            case 6866:
            case 6961:
            case 7329:
            case 7719:
            case 7872:
            case 8533:
            case 8703:
                return "TA";
            case 6744:
            case 7321:
            case 7586:
            case 7918:
            case 7989:
            case 8158:
                return "LV";
            case 6755:
            case 6758:
            case 7708:
                return "QUE";
            case 6761:
            case 6790:
            case 8140:
            case 8165:
            case 8320:
            case 8571:
                return "PAN";
            case 6762:
            case 7045:
            case 7341:
            case 7408:
            case 7633:
            case 7926:
            case 7947:
            case 7974:
            case 8163:
            case 8262:
            case 8439:
            case 8536:
                return "QIANG";
            case 6770:
            case 6837:
            case 6847:
            case 7579:
            case 7777:
                return "MIAN";
            case 6771:
            case 7632:
            case 7727:
            case 7766:
            case 7779:
            case 7970:
            case 8527:
                return "DUN";
            case 6776:
            case 7112:
            case FragmentTransaction.TRANSIT_FRAGMENT_CLOSE /* 8194 */:
                return "HANG";
            case 6815:
            case 6962:
            case 7082:
            case 7124:
            case 7628:
            case 7654:
            case 7919:
            case 7954:
            case 8050:
            case 8644:
                return "LIU";
            case 6852:
                return "PEN";
            case 6936:
            case 7671:
                return "JIONG";
            case 6950:
                return "QUN";
            case 6974:
            case 7264:
            case 7491:
            case 7877:
                return "KAO";
            case 6978:
            case 7078:
            case 7218:
            case 8451:
            case 8785:
                return "CAN";
            case 6983:
            case 7032:
            case 7059:
            case 7069:
                return "NU";
            case 7027:
            case 7084:
            case 7609:
            case 7613:
            case 7958:
            case 7980:
            case 8106:
            case 8149:
            case 8707:
            case 8752:
                return "BIAO";
            case 7055:
                return "SHUANG";
            case 7072:
            case 7275:
            case 7725:
            case 7892:
                return "HUA";
            case 7153:
            case 7421:
            case 7832:
            case 7913:
                return "ZENG";
            case 7178:
            case 7537:
            case 8228:
            case 8601:
                return "GONG";
            case 7222:
            case 7435:
            case 8402:
            case 8456:
            case 8485:
            case 8641:
                return "ZAN";
            case 7308:
            case 7403:
            case 7577:
                return "COU";
            case 7318:
            case 7649:
            case 8393:
                return "DUAN";
            case 7401:
            case 8554:
            case 8626:
                return "CHUO";
            case 7535:
                return "RUAN";
            case 7538:
            case 8124:
                return "ZHUN";
            case 7588:
                return "TENG";
            case 7653:
                return "TUI";
            case 7692:
            case 8006:
                return "RANG";
            case 7701:
            case 7713:
            case 7752:
                return "DUI";
            case 7704:
            case 7847:
            case 8412:
                return "NV";
            case 7705:
                return "NEN";
            case 7716:
            case 7824:
            case 8364:
                return "GANG";
            case 7746:
            case 8109:
                return "ZHAI";
            case 7822:
            case 7855:
            case 8727:
                return "TOU";
            case 7886:
            case 8585:
            case 8684:
                return "DIAO";
            case 7891:
                return "DIU";
            case 7894:
                return "HA";
            case 7912:
                return "KENG";
            case 7929:
                return "DE";
            case 8144:
                return "KEN";
            case 8181:
                return "NOU";
            case 8316:
                return "SHUAI";
            case 8330:
                return "FOU";
            case 8459:
            case 8569:
            case 8723:
                return "ROU";
            case 8507:
                return "SHAI";
            case 8531:
                return "BIE";
            case 8555:
                return "HUAI";
            case 8737:
                return "KUAN";
        }
    }

    /* loaded from: classes.dex */
    public static final class Token {
        public static final int LATIN = 1;
        public static final int LETTER = 3;
        public static final int NUMBER = 2;
        public static final int PINYIN = 4;
        public static final int UNKNOWN = 5;
        private String mSource;
        private String mTarget;
        private int mType;

        public Token() {
        }

        public Token(int i, String str, String str2) {
            this.mType = i;
            this.mSource = str;
            this.mTarget = str2;
        }

        public int getType() {
            return this.mType;
        }

        public String getSource() {
            return this.mSource;
        }

        public String getTarget() {
            return this.mTarget;
        }

        public String toString() {
            return "Token[" + this.mSource + "," + this.mType + "," + this.mTarget + "]";
        }
    }

    private static void addToken(StringBuilder sb, StringBuilder sb2, ArrayList<Token> arrayList, int i) {
        arrayList.add(new Token(i, sb.toString(), sb2.toString()));
        sb.setLength(0);
        sb2.setLength(0);
    }

    static {
        for (int length = COMMON_GBK_CHINESE_KEY.length - 1; length >= 0; length--) {
            INCOMMONUSEGBKCHINESE.put(COMMON_GBK_CHINESE_KEY[length], COMMON_GBK_CHINESE_PINYIN[length]);
        }
        T9_KEY = new char[]{'2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7', '7', '7', '7', '8', '8', '8', '9', '9', '9', '9'};
    }

    public static List<Token> get(String str) {
        int i;
        int i2;
        ArrayList arrayList = new ArrayList(8);
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        StringBuilder sb = new StringBuilder(8);
        StringBuilder sb2 = new StringBuilder(8);
        try {
            byte[] bytes = str.getBytes("GBK");
            int length = bytes.length;
            int length2 = str.length();
            int i3 = 1;
            int i4 = 0;
            for (int i5 = 0; i4 < length && i5 < length2; i5++) {
                short s = (short) (bytes[i4] & 255);
                if (s <= 127 || s == 255 || s == 128) {
                    if (s >= 48 && s <= 57) {
                        if (i3 != 2 && sb2.length() > 0) {
                            addToken(sb, sb2, arrayList, i3);
                        }
                        sb.append((char) s);
                        sb2.append((char) s);
                        i = 2;
                        i2 = i4;
                    } else if ((s >= 65 && s <= 90) || (s >= 97 && s <= 122)) {
                        if (i3 != 3 && sb2.length() > 0) {
                            addToken(sb, sb2, arrayList, i3);
                        }
                        sb.append((char) s);
                        sb2.append((char) (s >= 97 ? s - 32 : s));
                        i2 = i4;
                        i = 3;
                    } else {
                        if (i3 != 1 && sb2.length() > 0) {
                            addToken(sb, sb2, arrayList, i3);
                        }
                        sb.append(str.charAt(i5));
                        if (s != 255 && s != 128) {
                            sb2.append((char) s);
                            i = 1;
                            i2 = i4;
                        } else {
                            sb2.append(str.charAt(i5));
                            i = 1;
                            i2 = i4;
                        }
                    }
                } else {
                    if (sb2.length() > 0) {
                        addToken(sb, sb2, arrayList, i3);
                    }
                    String valueOf = String.valueOf(str.charAt(i5));
                    String str2 = INCOMMONUSEGBKCHINESE.get(valueOf);
                    if (str2 != null) {
                        arrayList.add(new Token(4, valueOf, str2));
                        i2 = i4 + 1;
                        i = i3;
                    } else {
                        i2 = i4 + 1;
                        short s2 = 0;
                        if (i2 < bytes.length) {
                            s2 = (short) (bytes[i2] & 255);
                        }
                        if (s <= 160 || s2 <= 160) {
                            arrayList.add(new Token(5, valueOf, valueOf));
                            i = i3;
                        } else {
                            String findLetter = findLetter((((s - 160) * 100) + s2) - 160);
                            if (findLetter != null && findLetter.length() > 0) {
                                arrayList.add(new Token(4, valueOf, findLetter));
                                i = i3;
                            } else {
                                if (findLetter.length() == 0) {
                                    arrayList.add(new Token(5, valueOf, valueOf));
                                }
                                i = i3;
                            }
                        }
                    }
                }
                i4 = i2 + 1;
                i3 = i;
            }
            if (sb2.length() > 0) {
                addToken(sb, sb2, arrayList, i3);
            }
            return arrayList;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    /* loaded from: classes.dex */
    public static final class Pinyin {
        private int[] mPlaceHolder;
        private char[][] mT9Key;

        public char[][] getT9Key() {
            return this.mT9Key;
        }

        public int[] getPlaceHolder() {
            return this.mPlaceHolder;
        }

        public Pinyin(int i) {
            this.mT9Key = new char[i][];
            this.mPlaceHolder = new int[i];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mT9Key.length);
            for (int i = 0; i < this.mT9Key.length; i++) {
                sb.append(',');
                char[] cArr = this.mT9Key[i];
                if (cArr == null) {
                    sb.append('0');
                } else {
                    sb.append(cArr);
                }
                sb.append(',');
                sb.append(this.mPlaceHolder[i]);
            }
            return sb.toString();
        }

        public static Pinyin parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
            simpleStringSplitter.setString(str);
            Iterator<String> it = simpleStringSplitter.iterator();
            int parseInt = Integer.parseInt(it.next());
            if (parseInt <= 0) {
                return null;
            }
            Pinyin pinyin = new Pinyin(parseInt);
            for (int i = 0; i < parseInt; i++) {
                String next = it.next();
                String next2 = it.next();
                if (next.length() == 1 && next.charAt(0) == '0') {
                    pinyin.mT9Key[i] = null;
                } else {
                    pinyin.mT9Key[i] = next.toCharArray();
                }
                pinyin.mPlaceHolder[i] = Integer.parseInt(next2);
            }
            return pinyin;
        }
    }

    public static Pinyin[] getTwoKindOfPinyin(String str) {
        List<Token> list;
        int size;
        if (TextUtils.isEmpty(str) || (size = (list = get(str)).size()) == 0) {
            return null;
        }
        Pinyin pinyin = new Pinyin(size);
        Pinyin pinyin2 = new Pinyin(size);
        for (int i = 0; i < size; i++) {
            Token token = list.get(i);
            pinyin.mPlaceHolder[i] = token.mSource.length();
            pinyin2.mPlaceHolder[i] = token.mSource.length();
            if (token.mType == 4 || token.mType == 3) {
                int length = token.mTarget.length();
                char[] cArr = new char[length];
                for (int i2 = 0; i2 < length; i2++) {
                    cArr[i2] = T9_KEY[token.mTarget.charAt(i2) - 'A'];
                }
                pinyin.mT9Key[i] = cArr;
                pinyin2.mT9Key[i] = token.mTarget.toCharArray();
            }
        }
        return new Pinyin[]{pinyin, pinyin2};
    }

    public static String buildKey(String str) {
        if (str == null) {
            throw new IllegalArgumentException("source must not be null");
        }
        String upperCase = str.trim().toUpperCase(Locale.US);
        if (upperCase.startsWith("THE ")) {
            upperCase = upperCase.substring(4);
        }
        if (upperCase.startsWith("AN ")) {
            upperCase = upperCase.substring(3);
        }
        if (upperCase.startsWith("A ")) {
            upperCase = upperCase.substring(2);
        }
        if (upperCase.endsWith(", THE") || upperCase.endsWith(",THE") || upperCase.endsWith(", AN") || upperCase.endsWith(",AN") || upperCase.endsWith(", A") || upperCase.endsWith(",A")) {
            upperCase = upperCase.substring(0, upperCase.lastIndexOf(44));
        }
        String trim = upperCase.replaceAll("[\\[\\]\\(\\)\"'.,?!]", "").trim();
        if (trim.length() == 0) {
            return "";
        }
        String str2 = "";
        List<Token> list = get(trim);
        if (list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (Token token : list) {
                int type = token.getType();
                if (4 == type || 3 == type || 2 == type) {
                    sb2.append(token.getTarget());
                    sb.append(token.getTarget().charAt(0));
                }
            }
            if (sb2.length() > 0) {
                sb2.append(' ');
                sb2.append(trim);
            }
            if (sb.length() > 0) {
                sb2.append(' ');
                sb2.append((CharSequence) sb);
            }
            str2 = sb2.toString();
        }
        return str2.length() == 0 ? trim : str2;
    }
}
