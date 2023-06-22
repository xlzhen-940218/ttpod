package com.mradar.sdk.http;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MRadarSdkCmd {
    private String cmdType;
    private Map<String, Object> params = new HashMap();

    public MRadarSdkCmd(String cmdType) {
        this.cmdType = "";
        this.cmdType = cmdType;
        setParam("cmd", cmdType);
    }

    public void setParam(String key, Object value) {
        this.params.put(key, value);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public Object getValue(String key) {
        return this.params.get(key);
    }

    public String getCmdType() {
        return this.cmdType;
    }
}
