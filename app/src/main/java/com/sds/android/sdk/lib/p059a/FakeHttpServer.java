package com.sds.android.sdk.lib.p059a;

import static org.nanohttpd.protocols.http.response.Response.newFixedLengthResponse;

import com.sds.android.sdk.lib.fakebuild.FakeFindSongModuleResult;
import com.sds.android.sdk.lib.fakebuild.FakeMusicRanksResult;
import com.sds.android.sdk.lib.fakebuild.FakeOnlineMusicCategoryResult;
import com.sds.android.sdk.lib.util.JSONUtils;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.response.Response;

public class FakeHttpServer extends NanoHTTPD {
    public FakeHttpServer() {
        super(18098);
    }

    @Override
    public Response handle(IHTTPSession session) {
        String result = "";
        switch (session.getUri()) {
            case "/ttpod":

                result = JSONUtils.toJson(new FakeMusicRanksResult().build());
                break;
            case "/recomm/recomm_modules":
                result = JSONUtils.toJson(new FakeFindSongModuleResult().build());
                break;
            case "/module/category":
                result = JSONUtils.toJson(new FakeOnlineMusicCategoryResult().build());
                break;
        }
        return newFixedLengthResponse(result);
    }
}
