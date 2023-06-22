package com.sds.android.sdk.core.statistic;

import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.util.LogUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SUtils {

    /* loaded from: classes.dex */
    public enum PostResult {
        POST_SUCCESSED,
        POST_FAILED,
        POST_ERROR
    }

    public static long initEventFiles(LinkedList<String> linkedList, String str, String str2, int i) {
        long j;
        String remove = str2;
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null) {
            j = 0;
        } else {
            for (File file : listFiles) {
                String name = file.getName();
                LogUtils.m8388a("SUtils", "initEventFiles fileName: " + name);
                if (file.isFile() && name.startsWith(str2)) {
                    linkedList.add(file.getAbsolutePath());
                }
            }
            Collections.sort(linkedList);
            j = 0;
            while (linkedList.size() > i) {
                try {
                    j += getTotalLines(new File(remove));
                    deleteFile(linkedList.remove());
                } catch (IOException e) {
                    e.printStackTrace();
                    j = j;
                }
            }
        }
        LogUtils.m8388a("SUtils", "initEventFiles local file count: " + linkedList.size() + "remove count: " + j);
        return j;
    }

    public static int getTotalLines(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        int i = 0;
        for (String readLine = lineNumberReader.readLine(); readLine != null; readLine = lineNumberReader.readLine()) {
            i++;
        }
        lineNumberReader.close();
        fileReader.close();
        return i;
    }

    public static boolean deleteFile(String str) {
        return !"".equals(str) && deleteFile(new File(str));
    }

    public static boolean deleteFile(File file) {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (!deleteFile(file2)) {
                    return false;
                }
            }
        }
        return !file.exists() || file.delete();
    }

    public static File createFile(String str) {
        if (str == null || !"".equals(str)) {
            File file = new File(str);
            if (file.isFile()) {
                return file;
            }
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                if (parentFile.isDirectory() || parentFile.mkdirs()) {
                    try {
                        if (file.createNewFile()) {
                            return file;
                        }
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public static String buildCurrentWirteFilePath(String str, String str2) {
        return str + File.separator + str2 + System.currentTimeMillis();
    }

    public static String buildJson(JSONArray jSONArray, HashMap<String, Object> hashMap) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", jSONArray);
            if (hashMap != null) {
                jSONObject.put(SService.STATISTIC_PARAM, new JSONObject(hashMap));
            } else {
                jSONObject.put(SService.STATISTIC_PARAM, "GeneralParameter is null!");
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildJson(JSONObject jSONObject, HashMap<String, Object> hashMap) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("data", jSONObject);
            if (hashMap != null) {
                jSONObject2.put(SService.STATISTIC_PARAM, new JSONObject(hashMap));
            } else {
                jSONObject2.put(SService.STATISTIC_PARAM, "General parameter is null!");
            }
            return jSONObject2.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildJson(List<SEvent> list, HashMap<String, Object> hashMap) {
        JSONArray jSONArray = new JSONArray();
        for (SEvent sEvent : list) {
            jSONArray.put(new JSONObject(sEvent.getDataMap()));
        }
        return buildJson(jSONArray, hashMap);
    }

    public static boolean httpPost(String str, String str2) {
        if (str == null) {
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HttpClientProxy.HEADER_ACCEPT_GZIP, HttpClientProxy.CONTENT_ENCODING_GZIP);
        hashMap.put("Connection", "close");
        HttpRequest.C0586a m8714a = HttpRequest.m8714a(str, hashMap, str2);
        return m8714a != null && 200 == m8714a.m8690c();
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean writeEventToFile(String str, SEvent sEvent) {
        BufferedWriter bufferedWriter = null;
        File createFile = null;
        boolean z = false;
        if (str == null) {
            throw new NullPointerException("path should not be null.");
        }
        Exception e;
        BufferedWriter bufferedWriter2 = null;
        Throwable th;
        try {
            try {
                createFile = createFile(str);
            } catch (ArrayIndexOutOfBoundsException e2) {
                e = e2;
                bufferedWriter = null;
            } catch (Throwable th1) {
                th = th1;
                bufferedWriter = null;
                if (bufferedWriter != null) {
                }
                throw th;
            }
            if (createFile == null) {
                if (0 != 0) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return z;
            }
            bufferedWriter = new BufferedWriter(new FileWriter(createFile, true));
            try {
                String jSONObject = new JSONObject(sEvent.getDataMap()).toString();
                if (jSONObject == null) {
                    jSONObject = "";
                }
                bufferedWriter.write(jSONObject);
                bufferedWriter.write(10);
                bufferedWriter.flush();
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                z = true;
            } catch (IOException e5) {
                e = e5;
                e.printStackTrace();
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return z;
            } catch (ArrayIndexOutOfBoundsException e7) {
                e = e7;
                e.printStackTrace();
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                return z;
            }
            return z;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            //throw th;
        }
        return z;
    }

    public static JSONArray readEventFromFile(String str) {
        if (str == null) {
            throw new NullPointerException("path should not be null.");
        }
        try {
            JSONArray jSONArray = new JSONArray();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    try {
                        jSONArray.put(new JSONObject(readLine));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    bufferedReader.close();
                    return jSONArray;
                }
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static PostResult postFileEvent(HashMap<String, Object> hashMap, String str, String str2) {
        JSONArray readEventFromFile;
        LogUtils.m8381c("SUtils", "postFileEvent");
        PostResult postResult = PostResult.POST_ERROR;
        if (str2 != null && (readEventFromFile = readEventFromFile(str2)) != null) {
            String buildJson = buildJson(readEventFromFile, hashMap);
            LogUtils.m8381c("SUtils", "postFileEvent content: " + buildJson);
            if (buildJson != null && httpPost(str, buildJson)) {
                return PostResult.POST_SUCCESSED;
            }
            return PostResult.POST_FAILED;
        }
        return postResult;
    }

    public static PostResult postListEvent(LinkedList<SEvent> linkedList, HashMap<String, Object> hashMap, String str) {
        LogUtils.m8381c("SUtils", "postListEvent");
        PostResult postResult = PostResult.POST_ERROR;
        if (linkedList.size() > 0) {
            String buildJson = buildJson(linkedList, hashMap);
            LogUtils.m8381c("SUtils", "postListEvent content: " + buildJson);
            if (buildJson != null && httpPost(str, buildJson)) {
                postResult = PostResult.POST_SUCCESSED;
            } else {
                postResult = PostResult.POST_FAILED;
            }
            linkedList.clear();
        }
        return postResult;
    }

    public static PostResult postEvent(SEvent sEvent, HashMap<String, Object> hashMap, String str) {
        LogUtils.m8381c("SUtils", "postEvent");
        PostResult postResult = PostResult.POST_ERROR;
        JSONObject jSONObject = new JSONObject(sEvent.getDataMap());
        if (jSONObject != null) {
            String buildJson = buildJson(jSONObject, hashMap);
            LogUtils.m8381c("SUtils", "postEvent content: " + buildJson);
            if (buildJson != null && httpPost(str, buildJson)) {
                return PostResult.POST_SUCCESSED;
            }
            return PostResult.POST_FAILED;
        }
        return postResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void writeDeep(String str, long j) {
        BufferedWriter bufferedWriter = null;
        BufferedWriter bufferedWriter2 = null;
        Exception e;
        Throwable th;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(str));
            } catch (IOException e1) {
                e = e1;
                bufferedWriter = null;
            } catch (Throwable th1) {
                th = th1;
                if (bufferedWriter2 != null) {
                }
                throw th;
            }
            try {
                bufferedWriter.write(String.valueOf(j));
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            //throw th;
        }
    }

    public static long readDeep(String str) {
        BufferedReader bufferedReader = null;
        Exception e;
        Throwable th;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                try {
                    long longValue = Long.valueOf(bufferedReader2.readLine()).longValue();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                            return longValue;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            return longValue;
                        }
                    }
                    return longValue;
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return 0L;
                } catch (Throwable th1) {
                    th = th1;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return 0;
    }
}
