package com.sds.android.sdk.lib.util;

import android.os.Process;
import android.util.Log;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Utility class for logging.
 * De-obfuscated version.
 */
public final class LogUtils {

    private static boolean enableLog = true;

    private static boolean enableLogToFile = false;

    private static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);

    private static String logFilePath;

    /**
     * Enables or disables logging.
     */
    public static void setEnableLog(boolean enableLog) {
        LogUtils.enableLog = enableLog;
    }

    /**
     * Configures logging to a file.
     * @param enable Whether to enable file logging.
     * @param path The path to the log file.
     */
    public static void setEnableLogToFile(boolean enable, String path) {
        LogUtils.enableLogToFile = enable;
        LogUtils.logFilePath = path;
    }

    public static void debug(String tag, String msg) {
        if (enableLog) {
            String fullMsg = "TTPOD:" + msg;
            Log.d(tag, fullMsg);
            writeLogToFile(tag, fullMsg);
        }
    }

    public static void debug(String tag, String format, Object... args) {
        if (enableLog) {
            String msg = String.format("TTPOD:" + format, args);
            Log.d(tag, msg);
            writeLogToFile(tag, msg);
        }
    }

    public static void warning(String tag, String msg) {
        if (enableLog) {
            String fullMsg = "TTPOD:" + msg;
            Log.w(tag, fullMsg);
            writeLogToFile(tag, fullMsg);
        }
    }

    public static void warning(String tag, String msg, Throwable tr) {
        if (enableLog) {
            String fullMsg = "TTPOD:" + msg;
            Log.w(tag, fullMsg, tr);
            writeLogToFile(tag, fullMsg + "\n" + Log.getStackTraceString(tr));
        }
    }

    public static void error(String tag, String msg) {
        if (enableLog) {
            String fullMsg = "TTPOD:" + msg;
            Log.e(tag, fullMsg);
            writeLogToFile(tag, fullMsg);
        }
    }

    public static void error(String tag, String msg, Throwable tr) {
        if (enableLog) {
            String fullMsg = "TTPOD:" + msg;
            Log.e(tag, fullMsg, tr);
            writeLogToFile(tag, fullMsg + "\n" + Log.getStackTraceString(tr));
        }
    }

    public static void error(String tag, String format, Object... args) {
        if (enableLog) {
            String msg = String.format("TTPOD:" + format, args);
            Log.e(tag, msg);
            writeLogToFile(tag, msg);
        }
    }

    public static void info(String tag, String msg) {
        if (enableLog) {
            String fullMsg = "TTPOD:" + msg;
            Log.i(tag, fullMsg);
            writeLogToFile(tag, fullMsg);
        }
    }

    public static void info(String tag, String format, Object... args) {
        if (enableLog) {
            String msg = String.format("TTPOD:" + format, args);
            Log.i(tag, msg);
            writeLogToFile(tag, msg);
        }
    }

    public static void verbose(String tag, String msg) {
        if (enableLog) {
            String fullMsg = "TTPOD:" + msg;
            Log.v(tag, fullMsg);
            writeLogToFile(tag, fullMsg);
        }
    }

    /**
     * Internal method to write a log entry to the specified log file if enabled.
     */
    private static void writeLogToFile(String tag, String msg) {
        if (enableLogToFile && logFilePath != null) {
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(logFilePath, true);
                fileWriter.write(String.format("%s pid=%d %s: %s\n", 
                        LOG_DATE_FORMAT.format(new Date()), 
                        Process.myPid(), 
                        tag, 
                        msg));
                fileWriter.flush();
            } catch (Throwable th) {
                th.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
