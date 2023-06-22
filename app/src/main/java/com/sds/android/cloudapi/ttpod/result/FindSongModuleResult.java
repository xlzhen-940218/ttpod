package com.sds.android.cloudapi.ttpod.result;
import com.google.gson.annotations.SerializedName;

import com.sds.android.cloudapi.ttpod.data.FindSongModuleData;
import com.sds.android.sdk.lib.request.DataResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class FindSongModuleResult extends DataResult<FindSongModuleData> {
    private static final int INDEX_INIT_VALUE = -1;
    private int mIndex = -1;
    private HashMap<Integer, StyleDataListResult> mResultHashMap;
    @SerializedName(value = "usertype")
    private int mUserType;
    @SerializedName(value = "version")
    private long mVersion;

    public long getVersion() {
        return this.mVersion;
    }

    public int getUserType() {
        return this.mUserType;
    }

    public StyleDataListResult next() {
        setUp();
        HashMap<Integer, StyleDataListResult> hashMap = this.mResultHashMap;
        int i = this.mIndex + 1;
        this.mIndex = i;
        return hashMap.get(Integer.valueOf(i));
    }

    public boolean hasNext() {
        setUp();
        return this.mIndex + 1 < this.mResultHashMap.size();
    }

    public void moveTo(int i) {
        setUp();
        if (i >= size()) {
            throw new IndexOutOfBoundsException("index=" + i + ", size=" + size());
        }
        this.mIndex = i;
    }

    public int position() {
        setUp();
        return this.mIndex;
    }

    public int size() {
        setUp();
        return this.mResultHashMap.size();
    }

    private void setUp() {
        if (this.mResultHashMap == null) {
            this.mResultHashMap = new HashMap<>();
            this.mIndex = -1;
            FindSongModuleData data = getData();
            if (data != null) {
                TreeMap treeMap = new TreeMap();
                Iterator<CirclePosterListResult> it = data.getPosts().iterator();
                while (it.hasNext()) {
                    CirclePosterListResult next = it.next();
                    treeMap.put(Integer.valueOf(next.getOrder()), next);
                }
                Iterator<FindSongHotListResultNew> it2 = data.getSongLists().iterator();
                while (it2.hasNext()) {
                    FindSongHotListResultNew next2 = it2.next();
                    treeMap.put(Integer.valueOf(next2.getOrder()), next2);
                }
                Iterator<OperationZoneResult> it3 = data.getBanners().iterator();
                while (it3.hasNext()) {
                    OperationZoneResult next3 = it3.next();
                    treeMap.put(Integer.valueOf(next3.getOrder()), next3);
                }
                Iterator<FindSongHandpickResult> it4 = data.getZones().iterator();
                while (it4.hasNext()) {
                    FindSongHandpickResult next4 = it4.next();
                    treeMap.put(Integer.valueOf(next4.getOrder()), next4);
                }
                int size = treeMap.size() - 1;
                for (Object styleDataListResult : treeMap.values()) {
                    this.mResultHashMap.put(Integer.valueOf(size), (StyleDataListResult) styleDataListResult);
                    size--;
                }
            }
        }
    }
}
