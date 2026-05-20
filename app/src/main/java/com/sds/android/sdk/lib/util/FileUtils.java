package com.sds.android.sdk.lib.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class for file and directory operations.
 * De-obfuscated version.
 */
public class FileUtils {

    private static final String SEPARATOR = File.separator;
    private static final char SEPARATOR_CHAR = File.separatorChar;

    /**
     * Checks if a file or directory exists at the given path.
     */
    public static boolean exists(String path) {
        return !StringUtils.isEmpty(path) && new File(path).exists();
    }

    /**
     * Checks if the given path is a file.
     */
    public static boolean isFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    /**
     * Sets the last modified time of the file at the given path.
     */
    public static boolean setLastModified(String path, long time) {
        return isFile(path) && new File(path).setLastModified(time);
    }

    /**
     * Returns the size of the file at the given path.
     */
    public static long getFileSize(String path) {
        if (isFile(path)) {
            return new File(path).length();
        }
        return 0L;
    }

    /**
     * Checks if the given path is a directory.
     */
    public static boolean isDir(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    /**
     * Creates a new file at the specified path, including parent directories if necessary.
     */
    public static synchronized File createFile(String path) {
        File file = null;
        synchronized (FileUtils.class) {
            if (!StringUtils.isEmpty(path)) {
                File targetFile = new File(path);
                if (targetFile.isFile()) {
                    file = targetFile;
                } else {
                    File parentFile = targetFile.getParentFile();
                    if (parentFile != null && (parentFile.isDirectory() || parentFile.mkdirs())) {
                        try {
                            if (targetFile.createNewFile()) {
                                file = targetFile;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return file;
    }

    /**
     * Deletes the file or directory at the specified path.
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return file.exists() && file.delete();
    }

    /**
     * Creates a folder at the specified path. Deletes any existing file with the same name.
     */
    public static synchronized File createFolder(String path) {
        File folder = null;
        synchronized (FileUtils.class) {
            if (!StringUtils.isEmpty(path)) {
                File dir = new File(path);
                if (!dir.isDirectory()) {
                    dir.delete();
                    dir.mkdirs();
                }
                folder = dir;
            }
        }
        return folder;
    }

    /**
     * Calculates the total size of a file or directory.
     */
    public static synchronized long getFolderSize(String path) {
        if (StringUtils.isEmpty(path)) {
            return 0L;
        }
        return getFolderSize(new File(path));
    }

    /**
     * Recursively calculates the size of a file or directory.
     */
    public static synchronized long getFolderSize(File file) {
        long size = 0;
        if (file != null) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File child : listFiles) {
                        size += child.isDirectory() ? getFolderSize(child) : child.length();
                    }
                }
            } else {
                size = file.length();
            }
        }
        return size;
    }

    /**
     * Limits the folder size by deleting oldest files based on lastModified.
     */
    public static synchronized void limitFolderSize(String path, long maxSize, String[] excludes) {
        synchronized (FileUtils.class) {
            long currentSize = getFolderSize(path);
            if (currentSize > maxSize) {
                ArrayList<String> excludeList = new ArrayList<>();
                if (excludes != null) {
                    for (String s : excludes)
                        excludeList.add(s);
                }
                File folder = new File(path);
                File[] listFiles = folder.listFiles();
                if (listFiles != null) {
                    List<File> fileList = Arrays.asList(listFiles);
                    try {
                        Collections.sort(fileList, new Comparator<File>() {
                            @Override
                            public int compare(File f1, File f2) {
                                if (f1.lastModified() == f2.lastModified()) {
                                    return 0;
                                }
                                return f1.lastModified() > f2.lastModified() ? -1 : 1;
                            }
                        });
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                    for (File file : fileList) {
                        if (currentSize <= maxSize) {
                            break;
                        }
                        if (!excludeList.contains(file.getAbsolutePath())) {
                            currentSize -= file.length();
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    /**
     * Recursively deletes files in a directory.
     */
    public static synchronized int deleteFilesRecursive(File folder) {
        int deletedCount = 0;
        synchronized (FileUtils.class) {
            File[] listFiles = folder.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        deletedCount += deleteFilesRecursive(file);
                    }
                    if (file.delete()) {
                        deletedCount++;
                    }
                }
            }
        }
        return deletedCount;
    }

    /**
     * Deep check if a file or directory exists.
     */
    public static synchronized boolean deepExists(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        return deepExists(new File(path));
    }

    /**
     * Deep check if a file or directory exists.
     */
    public static synchronized boolean deepExists(File file) {
        if (file == null) {
            return true;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File child : listFiles) {
                    if (!deepExists(child)) {
                        return false;
                    }
                }
            }
        }
        return file.exists();
    }

    /**
     * Reads the content of a file into a string.
     */
    public static String readStringFromFile(String path) {
        if (path == null) {
            throw new NullPointerException("path should not be null.");
        }
        String content;
        try {
            content = StringUtils.streamToString(new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
            content = null;
        }
        return content != null ? content : "";
    }

    /**
     * Writes a string to a file.
     */
    public static synchronized boolean writeStringToFile(String content, String path) {
        if (path == null) {
            throw new NullPointerException("path should not be null.");
        }
        boolean success = false;
        BufferedWriter writer = null;
        try {
            File file = createFile(path);
            if (file == null) {
                LogUtils.debug("FileUtils", "file == null path=%s", path);
            } else {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(content != null ? content : "");
                writer.flush();
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    /**
     * Writes an InputStream to a file.
     */
    public static synchronized boolean writeInputStreamToFile(InputStream is, String path) {
        if (path == null) {
            throw new NullPointerException("path should not be null.");
        }
        boolean success = false;
        FileOutputStream fos = null;
        try {
            File file = createFile(path);
            if (file == null) {
                LogUtils.debug("FileUtils", "inputStream file == null path=%s", path);
            } else {
                fos = new FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int read;
                while ((read = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, read);
                }
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    /**
     * Copies a file from one path to another.
     */
    public static boolean copyFile(String destPath, String srcPath) {
        if (destPath == null || srcPath == null) {
            throw new NullPointerException("path should not be null.");
        }
        try {
            return writeInputStreamToFile(new FileInputStream(srcPath), destPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the filename with extension from a path.
     */
    public static String getFilename(String path) {
        if (StringUtils.isEmpty(path)) {
            return "";
        }
        int queryIndex = path.lastIndexOf('?');
        if (queryIndex > 0) {
            path = path.substring(queryIndex);
        }
        int lastSeparator = path.lastIndexOf(SEPARATOR_CHAR);
        return lastSeparator >= 0 ? path.substring(lastSeparator + 1) : path;
    }

    /**
     * Gets the filename without extension from a path.
     */
    public static String getFilenameWithoutExtension(String path) {
        String filename = getFilename(path);
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(0, lastDot) : filename;
    }

    /**
     * Gets the parent directory path.
     */
    public static String getParentPath(String path) {
        if (StringUtils.isEmpty(path)) {
            return "";
        }
        int lastSeparator = (path == null || !path.startsWith(SEPARATOR)) ? -1 : path.lastIndexOf(SEPARATOR_CHAR);
        return lastSeparator == -1 ? SEPARATOR : path.substring(0, lastSeparator);
    }

    /**
     * Gets the file extension from a path.
     */
    public static String getSuffix(String path) {
        if (!StringUtils.isEmpty(path)) {
            int queryIndex = path.lastIndexOf('?');
            if (queryIndex > 0) {
                path = path.substring(0, queryIndex);
            }
            int lastSlash = path.lastIndexOf('/');
            if (lastSlash >= 0) {
                path = path.substring(lastSlash + 1);
            }
            if (path.length() > 0) {
                int lastDot = path.lastIndexOf('.');
                if (lastDot >= 0) {
                    return path.substring(lastDot + 1);
                }
            }
        }
        return "";
    }

    /**
     * Returns the last modified time of the file.
     */
    public static long getLastModified(String path) {
        if (StringUtils.isEmpty(path)) {
            return 0L;
        }
        return new File(path).lastModified();
    }

    /**
     * Renames a file.
     */
    public static boolean renameFile(String oldPath, String newPath) {
        File file = new File(oldPath);
        return file.isFile() && file.renameTo(new File(newPath));
    }

    /**
     * Removes invalid characters from a filename string.
     */
    public static String removeWrongCharacter(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", "");
    }

    /**
     * Returns the canonical path of the file.
     */
    public static String getCanonicalPath(String path) {
        try {
            return new File(path).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return path;
        }
    }

    /**
     * Tests directory permissions by creating a temporary file.
     */
    public static boolean testDirPermissions(String dir1, String dir2) {
        long time = System.currentTimeMillis();
        String tempFile = dir1 + File.separator + time;
        boolean success = false;
        createFile(tempFile);
        if (exists(tempFile) && exists(dir2 + File.separator + time)) {
            success = true;
        }
        // exists(tempFile) call seems misplaced in original code, likely intended to be part of cleanup
        return success;
    }
}
