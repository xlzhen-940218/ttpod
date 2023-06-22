package com.sds.android.ttpod.framework.base;

/* renamed from: com.sds.android.ttpod.framework.base.d */
/* loaded from: classes.dex */
public class CommonResult<ResultObj, ResultType> {

    /* renamed from: a */
    private ErrCode f5722a;

    /* renamed from: b */
    private String f5723b;

    /* renamed from: c */
    private ResultType f5724c;

    /* renamed from: d */
    private ResultObj f5725d;

    public CommonResult(ErrCode errCode) {
        this.f5722a = errCode;
    }

    public CommonResult(ErrCode errCode, String str) {
        this.f5722a = errCode;
        this.f5723b = str;
    }

    public CommonResult(ErrCode errCode, String str, ResultObj resultobj) {
        this.f5722a = errCode;
        this.f5723b = str;
        this.f5725d = resultobj;
    }

    public CommonResult(ErrCode errCode, String str, ResultObj resultobj, ResultType resulttype) {
        this.f5722a = errCode;
        this.f5723b = str;
        this.f5724c = resulttype;
        this.f5725d = resultobj;
    }

    /* renamed from: a */
    public ErrCode m4585a() {
        return this.f5722a;
    }

    /* renamed from: b */
    public String m4584b() {
        return this.f5723b;
    }

    /* renamed from: c */
    public ResultType m4583c() {
        if (this.f5724c == null) {
            return null;
        }
        return this.f5724c;
    }

    /* renamed from: d */
    public ResultObj m4582d() {
        if (this.f5725d == null) {
            return null;
        }
        return this.f5725d;
    }

    public String toString() {
        return "ErrCode:" + this.f5722a + ", ErrMessage:" + this.f5723b + ", ResultObj:" + this.f5725d + (this.f5725d != null ? "(" + this.f5725d.getClass() + ")" : "");
    }
}
