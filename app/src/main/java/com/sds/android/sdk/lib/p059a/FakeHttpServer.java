package com.sds.android.sdk.lib.p059a;

import static org.nanohttpd.protocols.http.response.Response.newFixedLengthResponse;

import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.result.MusicRanksResult;
import com.sds.android.sdk.lib.util.JSONUtils;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        }
        return newFixedLengthResponse(result);
    }
}
