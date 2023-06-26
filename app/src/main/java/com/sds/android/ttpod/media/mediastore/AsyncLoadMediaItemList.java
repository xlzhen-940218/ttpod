package com.sds.android.ttpod.media.mediastore;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/* loaded from: classes.dex */
public class AsyncLoadMediaItemList implements List<MediaItem> {
    private static final String TAG = "AsyncLoadMediaItemList";
    private volatile Cursor mCursor;
    private final String mGroupID;
    private Handler mHandler;
    private long mLastAccessTimeStamp;
    private final String mOrderBy;
    private boolean mLoadingStarted = false;
    private volatile boolean mLoadFinished = false;
    private volatile Set<OnLoadFinishedListener> mLoadFinishedListeners = new HashSet();
    private final List<MediaItem> mMediaItemArray = new ArrayList();
    private final List<Integer> mMediaItemIndexArray = new ArrayList();
    private int mRefCount = 1;

    /* loaded from: classes.dex */
    public interface OnLoadFinishedListener {
        void onLoadFinished();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsyncLoadMediaItemList(Cursor cursor, String str, String str2) {
        this.mCursor = cursor;
        this.mGroupID = str;
        this.mOrderBy = str2;
        if (this.mCursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                this.mMediaItemArray.add(null);
                this.mMediaItemIndexArray.add(new Integer(i));
            }
        }
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                AsyncLoadMediaItemList.this.extractCursor();
            }
        };
    }

    public void addLoadFinishedListener(OnLoadFinishedListener onLoadFinishedListener) {
        if (onLoadFinishedListener != null && !this.mLoadFinishedListeners.contains(onLoadFinishedListener)) {
            this.mLoadFinishedListeners.add(onLoadFinishedListener);
        }
    }

    public void removeLoadFinishedListener(OnLoadFinishedListener onLoadFinishedListener) {
        if (this.mLoadFinishedListeners.contains(onLoadFinishedListener)) {
            this.mLoadFinishedListeners.remove(onLoadFinishedListener);
        }
    }

    @Override // java.util.List
    public void add(int i, MediaItem mediaItem) {
        waitForLoadFinished();
        synchronized (this) {
            this.mMediaItemArray.add(i, mediaItem);
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(MediaItem mediaItem) {
        boolean add;
        waitForLoadFinished();
        synchronized (this) {
            add = this.mMediaItemArray.add(mediaItem);
        }
        return add;
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends MediaItem> collection) {
        boolean addAll;
        waitForLoadFinished();
        synchronized (this) {
            addAll = this.mMediaItemArray.addAll(i, collection);
        }
        return addAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends MediaItem> collection) {
        boolean addAll;
        waitForLoadFinished();
        synchronized (this) {
            addAll = this.mMediaItemArray.addAll(collection);
        }
        return addAll;
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        DebugUtils.m8427a();
        int i = this.mRefCount - 1;
        this.mRefCount = i;
        if (i <= 0) {
            this.mLoadFinishedListeners.clear();
            closeCursor();
            synchronized (this) {
                this.mMediaItemArray.clear();
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        boolean contains;
        synchronized (this) {
            if (this.mMediaItemArray.contains(obj)) {
                contains = true;
            } else {
                waitForLoadFinished();
                contains = this.mMediaItemArray.contains(obj);
            }
        }
        return contains;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        boolean containsAll;
        synchronized (this) {
            if (this.mMediaItemArray.contains(collection)) {
                containsAll = true;
            } else {
                waitForLoadFinished();
                containsAll = this.mMediaItemArray.containsAll(collection);
            }
        }
        return containsAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        boolean equals;
        waitForLoadFinished();
        synchronized (this) {
            equals = this.mMediaItemArray.equals(obj);
        }
        return equals;
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        int indexOf;
        synchronized (this) {
            if (!this.mMediaItemArray.contains(obj)) {
                waitForLoadFinished();
            }
            indexOf = this.mMediaItemArray.indexOf(obj);
        }
        return indexOf;
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<MediaItem> iterator() {
        Iterator<MediaItem> it;
        waitForLoadFinished();
        synchronized (this) {
            it = this.mMediaItemArray.iterator();
        }
        return it;
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        int lastIndexOf;
        waitForLoadFinished();
        synchronized (this) {
            lastIndexOf = this.mMediaItemArray.lastIndexOf(obj);
        }
        return lastIndexOf;
    }

    @Override // java.util.List
    public ListIterator<MediaItem> listIterator() {
        ListIterator<MediaItem> listIterator;
        waitForLoadFinished();
        synchronized (this) {
            listIterator = this.mMediaItemArray.listIterator();
        }
        return listIterator;
    }

    @Override // java.util.List
    public ListIterator<MediaItem> listIterator(int i) {
        ListIterator<MediaItem> listIterator;
        waitForLoadFinished();
        synchronized (this) {
            listIterator = this.mMediaItemArray.listIterator(i);
        }
        return listIterator;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public MediaItem remove(int i) {
        MediaItem remove;
        synchronized (this) {
            this.mMediaItemIndexArray.remove(new Integer(i));
            remove = this.mMediaItemArray.remove(i);
        }
        return remove;
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        boolean remove;
        if (obj == null) {
            waitForLoadFinished();
        }
        synchronized (this) {
            if (!this.mMediaItemArray.contains(obj)) {
                waitForLoadFinished();
            }
            int indexOf = this.mMediaItemArray.indexOf(obj);
            if (indexOf >= 0) {
                this.mMediaItemIndexArray.remove(indexOf);
            }
            remove = this.mMediaItemArray.remove(obj);
        }
        return remove;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean removeAll;
        synchronized (this) {
            this.mMediaItemIndexArray.clear();
            removeAll = this.mMediaItemArray.removeAll(collection);
        }
        return removeAll;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean retainAll;
        waitForLoadFinished();
        synchronized (this) {
            retainAll = this.mMediaItemArray.retainAll(collection);
        }
        return retainAll;
    }

    @Override // java.util.List
    public MediaItem set(int i, MediaItem mediaItem) {
        MediaItem mediaItem2;
        synchronized (this) {
            mediaItem2 = this.mMediaItemArray.set(i, mediaItem);
        }
        return mediaItem2;
    }

    @Override // java.util.List
    public List<MediaItem> subList(int i, int i2) {
        List<MediaItem> subList;
        waitForLoadFinished();
        synchronized (this) {
            subList = this.mMediaItemArray.subList(i, i2);
        }
        return subList;
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        Object[] array;
        waitForLoadFinished();
        synchronized (this) {
            array = this.mMediaItemArray.toArray();
        }
        return array;
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        T[] tArr2;
        waitForLoadFinished();
        synchronized (this) {
            tArr2 = (T[]) this.mMediaItemArray.toArray(tArr);
        }
        return tArr2;
    }

    public synchronized void addRef() {
        DebugUtils.m8427a();
        this.mRefCount++;
    }

    private void extractCursorDelay(long j) {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void extractCursor() {
        this.mHandler.removeMessages(0);
        if (!this.mLoadFinished && !this.mLoadingStarted) {
            this.mLoadingStarted = true;
            Thread thread = new Thread() { // from class: com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    int i;
                    try {
                        int count = AsyncLoadMediaItemList.this.mCursor.getCount();
                        int i2 = 0;
                        while (i2 < count) {
                            if (System.currentTimeMillis() - AsyncLoadMediaItemList.this.mLastAccessTimeStamp > 1000) {
                                synchronized (AsyncLoadMediaItemList.this) {
                                    i = i2 + 1;
                                    AsyncLoadMediaItemList.this.loadMediaItemWithCursorIndex(i2);
                                }
                                i2 = i;
                            } else {
                                try {
                                    sleep(1000L);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    } finally {
                        AsyncLoadMediaItemList.this.closeCursor();
                    }
                    AsyncLoadMediaItemList.this.mLoadFinished = true;
                    AsyncLoadMediaItemList.this.mHandler.post(new Runnable() { // from class: com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            for (OnLoadFinishedListener onLoadFinishedListener : AsyncLoadMediaItemList.this.mLoadFinishedListeners) {
                                onLoadFinishedListener.onLoadFinished();
                            }
                        }
                    });
                }
            };
            thread.setPriority(10);
            thread.start();
        }
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        int size;
        synchronized (this) {
            size = this.mMediaItemArray.size();
        }
        return size;
    }

    public void preLoad() {
        extractCursor();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public MediaItem get(int i) {
        assertNotOutOfBound(i);
        this.mLastAccessTimeStamp = System.currentTimeMillis();
        MediaItem mediaItem = null;
        synchronized (this) {
            if (this.mMediaItemArray.get(i) == null) {
                extractCursorDelay(1000L);
            }
            try {
                mediaItem = readMediaItemWithArrayIndex(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mediaItem == null ? MediaItem.MEDIA_ITEM_NULL : mediaItem;
    }

    public boolean isLoadFinished() {
        return this.mLoadFinished;
    }

    public String getOrderBy() {
        return this.mOrderBy;
    }

    private void assertNotOutOfBound(int i) {
        if (EnvironmentUtils.AppConfig.getTestMode() && i >= size()) {
            throw new IndexOutOfBoundsException("out of bound");
        }
    }

    private void waitForLoadFinished() {
        extractCursor();
        while (!this.mLoadFinished) {
            if (EnvironmentUtils.AppConfig.getTestMode()) {
                LogUtils.error(TAG, "WARNING AsyncLoadMediaItemList Sync Loading...");
            }
            try {
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void closeCursor() {
        try {
            if (this.mCursor != null) {
                this.mCursor.close();
                this.mCursor = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MediaItem readMediaItemWithArrayIndex(int i) {
        int map2CursorIndex = map2CursorIndex(i);
        if (map2CursorIndex >= 0) {
            loadMediaItem(map2CursorIndex, i);
            return this.mMediaItemArray.get(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadMediaItemWithCursorIndex(int i) {
        int map2ArrayIndex = map2ArrayIndex(i);
        if (map2ArrayIndex >= 0) {
            loadMediaItem(i, map2ArrayIndex);
        }
    }

    private void loadMediaItem(int i, int i2) {
        if (this.mMediaItemArray.get(i2) == null && this.mCursor.moveToPosition(i)) {
            this.mMediaItemArray.set(i2, MediaStorageImp.Builder.buildMediaItem(this.mCursor, this.mGroupID));
        }
    }

    private synchronized int map2CursorIndex(int i) {
        int intValue = -1;
        if (i >= 0) {
            if (i < this.mMediaItemIndexArray.size()) {
                intValue = this.mMediaItemIndexArray.get(i).intValue();
            }
        }
        return intValue;
    }

    private synchronized int map2ArrayIndex(int i) {
        return this.mMediaItemIndexArray.contains(new Integer(i)) ? this.mMediaItemIndexArray.indexOf(new Integer(i)) : -1;
    }
}
