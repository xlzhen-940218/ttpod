package com.mradar.sdk.record;

import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes.dex */
public class RecordByteQueue {
    private Queue<byte[]> recordQueue = new LinkedList();
    private int length = 0;
    private final int THREELENGTH = 49152;

    public void addRecord(byte[] buffer) {
        if (this.length >= 49152) {
            this.recordQueue.poll();
        }
        this.recordQueue.offer(buffer);
        this.length += buffer.length;
    }

    public byte[] getHeadRecord() {
        byte[] result = this.recordQueue.poll();
        return result;
    }

    public int getSize() {
        return this.recordQueue.size();
    }

    public void clear() {
        this.length = 0;
        this.recordQueue.clear();
    }
}
