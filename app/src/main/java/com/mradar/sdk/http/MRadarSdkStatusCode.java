package com.mradar.sdk.http;

/* loaded from: classes.dex */
public class MRadarSdkStatusCode {
    public static final String HTTP_CLIENT_PROTOCOL_ERROR = "[{\"error\":\"client protocol error, URL is not http.\", \"errorcode\":\"21000\"}]";
    public static final String HTTP_CONNECT_TIMEOUT = "[{\"error\":\"http client connect server timeout.\", \"errorcode\":\"21001\"}]";
    public static final String HTTP_READ_FROM_DORESO_ERROR = "[{\"error\":\"http client read data error.\", \"errorcode\":\"21003\"}]";
    public static final String HTTP_WRITE_TO_DORESO_ERROR = "[{\"error\":\"http client send data error.\", \"errorcode\":\"21002\"}]";
    public static final String PARSE_JSON_ERROR = "[{\"error\":\"client parse json data error, or the data returned from the server is not json.\", \"errorcode\":\"21004\"}]";
    public static final String WORK_THREAD_BLOCKINGQUEUE_POLL_ERROR = "[{\"error\":\"client work thread poll task form blockingqueue error.\", \"errorcode\":\"21005\"}]";
    public static final String WORK_THREAD_BLOCKINGQUEUE_PUT_ERROR = "[{\"error\":\"client work thread put task to blockingqueue error.\", \"errorcode\":\"21006\"}]";
    public static final String WORK_THREAD_JOIN_ERROR = "[{\"error\":\"client join work thread error.\", \"errorcode\":\"21007\"}]";
    public static final String WORK_THREAD_MERGE_TASK_ERROR = "[{\"error\":\"client work thread merge task error.\", \"errorcode\":\"21008\"}]";
}
