package com.mradar.sdk.http;

import com.mradar.sdk.record.MRadarSdk;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/* loaded from: classes.dex */
public class HttpWrapper {
    public static final String BOUNDARY = "--*****2014.5.21.doroso.search.copyright*****\r\n";
    public static final String BOUNDARYSTR = "*****2014.5.21.doroso.search.copyright*****";
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";

    public static String doPost(Map<String, Object> params, int timeout, int readTimeOut) throws MRadarSdkHttpApiException {
        URL url = null;
        String res = "";
        Exception e;
        Throwable th;
        try {
            url = new URL(MRadarSdk.SERVERS_NAME);
        } catch (Exception e1) {
            e = e1;
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(readTimeOut);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("Content-type", "multipart/form-data;boundary=*****2014.5.21.doroso.search.copyright*****");
            BufferedOutputStream out = null;
            try {
                try {
                    conn.connect();
                    BufferedOutputStream out2 = new BufferedOutputStream(conn.getOutputStream());
                    try {
                        StringBuilder reqData = new StringBuilder();
                        if (params != null) {
                            for (String key : params.keySet()) {
                                Object value = params.get(key);
                                reqData.setLength(0);
                                if (value instanceof String) {
                                    reqData.append(BOUNDARY);
                                    reqData.append("Content-Disposition:form-data;name=\"");
                                    reqData.append(key);
                                    reqData.append("\"\r\n\r\n");
                                    reqData.append(value);
                                    reqData.append("\r\n");
                                    out2.write(reqData.toString().getBytes());
                                } else if (value instanceof byte[]) {
                                    reqData.append(BOUNDARY);
                                    reqData.append("Content-Disposition:form-data;");
                                    reqData.append("name=\"" + key + "\";");
                                    reqData.append("filename=\"sample.ef\"\r\n");
                                    reqData.append("Content-Type:application/octet-stream");
                                    reqData.append("\r\n\r\n");
                                    out2.write(reqData.toString().getBytes());
                                    out2.write((byte[]) value);
                                    out2.write("\r\n".getBytes());
                                }
                            }
                            out2.write("--*****2014.5.21.doroso.search.copyright*****--\r\n\r\n".getBytes());
                        }
                        if (out2 != null) {
                            try {
                                out2.flush();
                                out2.close();
                            } catch (IOException e2) {
                                MRadarSdkHttpApiException doresoExcept = new MRadarSdkHttpApiException(e2.getMessage());
                                doresoExcept.errorMsg = MRadarSdkStatusCode.HTTP_WRITE_TO_DORESO_ERROR;
                                throw doresoExcept;
                            }
                        }
                        BufferedReader reader = null;
                        try {
                            try {
                                BufferedReader reader2 = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                                while (true) {
                                    try {
                                        String tmpRes = reader2.readLine();
                                        if (tmpRes == null) {
                                            break;
                                        } else if (tmpRes.length() > 0) {
                                            res = String.valueOf(res) + tmpRes;
                                        }
                                    } catch (Exception e3) {
                                        e = e3;
                                        MRadarSdkHttpApiException doresoExcept2 = new MRadarSdkHttpApiException(e.getMessage());
                                        doresoExcept2.errorMsg = MRadarSdkStatusCode.HTTP_READ_FROM_DORESO_ERROR;
                                        throw doresoExcept2;
                                    } catch (Throwable th1) {
                                        th = th1;
                                        reader = reader2;
                                        if (reader != null) {
                                            try {
                                                reader.close();
                                            } catch (IOException e4) {
                                                MRadarSdkHttpApiException doresoExcept3 = new MRadarSdkHttpApiException(e4.getMessage());
                                                doresoExcept3.errorMsg = MRadarSdkStatusCode.HTTP_READ_FROM_DORESO_ERROR;
                                                throw doresoExcept3;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                                if (reader2 != null) {
                                    try {
                                        reader2.close();
                                    } catch (IOException e5) {
                                        MRadarSdkHttpApiException doresoExcept4 = new MRadarSdkHttpApiException(e5.getMessage());
                                        doresoExcept4.errorMsg = MRadarSdkStatusCode.HTTP_READ_FROM_DORESO_ERROR;
                                        throw doresoExcept4;
                                    }
                                }
                                return res;
                            } catch (Exception e6) {
                                e = e6;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (SocketTimeoutException e7) {
                        e = e7;
                        MRadarSdkHttpApiException doresoExcept5 = new MRadarSdkHttpApiException(e.getMessage());
                        doresoExcept5.errorMsg = MRadarSdkStatusCode.HTTP_CONNECT_TIMEOUT;
                        throw doresoExcept5;
                    } catch (IOException e8) {
                        e = e8;
                        MRadarSdkHttpApiException doresoExcept6 = new MRadarSdkHttpApiException(e.getMessage());
                        doresoExcept6.errorMsg = MRadarSdkStatusCode.HTTP_WRITE_TO_DORESO_ERROR;
                        throw doresoExcept6;
                    } catch (Throwable th3) {
                        th = th3;
                        out = out2;
                        if (out != null) {
                            try {
                                out.flush();
                                out.close();
                            } catch (IOException e9) {
                                MRadarSdkHttpApiException doresoExcept7 = new MRadarSdkHttpApiException(e9.getMessage());
                                doresoExcept7.errorMsg = MRadarSdkStatusCode.HTTP_WRITE_TO_DORESO_ERROR;
                                throw doresoExcept7;
                            }
                        }
                        throw th;
                    }
                } catch (SocketTimeoutException e10) {
                    e = e10;
                } catch (IOException e11) {
                    e = e11;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e12) {
            e = e12;
            MRadarSdkHttpApiException doresoExcept8 = new MRadarSdkHttpApiException(e.getMessage());
            doresoExcept8.errorMsg = MRadarSdkStatusCode.HTTP_CLIENT_PROTOCOL_ERROR;
            throw doresoExcept8;
        }
        return res;
    }
}
