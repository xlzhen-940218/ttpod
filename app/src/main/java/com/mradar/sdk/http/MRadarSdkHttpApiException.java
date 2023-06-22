package com.mradar.sdk.http;

/* loaded from: classes.dex */
public class MRadarSdkHttpApiException extends Exception {
    private static final long serialVersionUID = 1;
    public String errorMsg;

    public MRadarSdkHttpApiException(String meg) {
        super(meg);
    }
}
