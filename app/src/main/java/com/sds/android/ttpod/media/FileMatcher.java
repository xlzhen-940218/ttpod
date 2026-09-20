package com.sds.android.ttpod.media;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.io.File;
import java.util.Locale;

/* loaded from: classes.dex */
public class FileMatcher {
    private static final String TAG = "FileMatcher";
    private CallBack mCallBack;
    private volatile boolean mStopped = false;

    /* loaded from: classes.dex */
    public interface CallBack {
        void onFileMatched(String str);

        void onFolderMatched(String str);
    }

    public FileMatcher(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public void start(String filterFolders, String extensions, boolean excludeHidden, String rootPath) {
        this.mStopped = false;
        if (StringUtils.isEmpty(rootPath)) {
            return;
        }
        File root = new File(rootPath);
        if (!root.exists()) {
            return;
        }
        scanDir(root, filterFolders, extensions != null ? extensions.toLowerCase(Locale.US) : "", excludeHidden);
    }

    private void scanDir(File dir, String filterFolders, String extensionsLower, boolean excludeHidden) {
        if (this.mStopped || dir == null) {
            return;
        }
        String dirPath = dir.getAbsolutePath();
        if (excludeHidden && dir.getName().startsWith(".")) {
            return;
        }
        if (filterFolders != null && filterFolders.contains("|" + dirPath + "|")) {
            return;
        }

        CallBack cb = this.mCallBack;
        if (cb != null) {
            cb.onFolderMatched(dirPath);
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return;
        }

        for (File file : files) {
            if (this.mStopped) {
                break;
            }
            if (file.isDirectory()) {
                scanDir(file, filterFolders, extensionsLower, excludeHidden);
            } else if (file.isFile()) {
                String name = file.getName();
                if (excludeHidden && name.startsWith(".")) {
                    continue;
                }
                int dotIndex = name.lastIndexOf('.');
                if (dotIndex >= 0 && dotIndex < name.length() - 1) {
                    String ext = name.substring(dotIndex + 1).toLowerCase(Locale.US);
                    if (extensionsLower.contains("|" + ext + "|")) {
                        if (cb != null) {
                            cb.onFileMatched(file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }

    public void stop() {
        this.mStopped = true;
    }

    public void release() {
        this.mStopped = true;
        this.mCallBack = null;
    }
}
