package com.sds.android.ttpod.share.p138c;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.api.OnlineMediaItemAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;


import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mediascan.ApShareEntryActivity;
import com.sds.android.ttpod.adapter.p075f.ShareSelectAdapter;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.p082a.ShareWaitingDialog;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.share.IShareAction;
import com.sds.android.ttpod.share.ShareApiFactory;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.ShareLoadListener;
import com.sds.android.ttpod.share.ShareType;
import com.sds.android.ttpod.share.p136a.ApiCallback;
import com.sds.android.ttpod.share.p136a.BaseApi;
import com.sds.android.ttpod.share.p136a.ShareResult;
import com.sds.android.ttpod.share.p136a.WeChatApi;
import com.sds.android.ttpod.share.p137b.AuthCallback;
import com.sds.android.ttpod.share.p137b.AuthHandler;
import com.sds.android.ttpod.share.p139d.AccessTokenUtil;
import com.sds.android.ttpod.share.p139d.ShareContentUtil;
import com.sds.android.ttpod.wxapi.WXEntryActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.share.c.c */
/* loaded from: classes.dex */
public class ShareSelectDialog extends BaseDialog {

    /* renamed from: f */
    private static BaseApi f7394f;

    /* renamed from: n */
    private static Map<ShareType, Integer> f7395n = new ArrayMap();

    /* renamed from: o */
    private static Map<ShareType, Integer> f7396o = new ArrayMap();

    /* renamed from: p */
    private static Map<ShareType, Integer> f7397p = new ArrayMap();

    /* renamed from: a */
    protected Map<ShareType, Integer> f7398a;

    /* renamed from: b */
    protected Map<ShareType, SAction> f7399b;

    /* renamed from: c */
    private GridView f7400c;

    /* renamed from: d */
    private ShareSelectAdapter f7401d;

    /* renamed from: e */
    private List<ShareSelectAdapter.C1000a> f7402e;

    /* renamed from: g */
    private Activity f7403g;

    /* renamed from: h */
    private String f7404h;

    /* renamed from: i */
    private ShareWaitingDialog f7405i;

    /* renamed from: j */
    private AuthHandler f7406j;

    /* renamed from: k */
    private ShareType f7407k;

    /* renamed from: l */
    private ShareInfo f7408l;

    /* renamed from: m */
    private IShareAction f7409m;

    /* renamed from: q */
    private ApiCallback f7410q;

    /* renamed from: r */
    private AuthCallback f7411r;

    /* renamed from: s */
    private Handler f7412s;

    static {
        f7395n.put(ShareType.SINA_WEIBO, Integer.valueOf((int) R.string.sina_weibo_accredit_fail));
        f7395n.put(ShareType.QQ_WEIBO, Integer.valueOf((int) R.string.qq_weibo_accredit_fail));
        f7395n.put(ShareType.QZONE, Integer.valueOf((int) R.string.qzone_accredit_fail));
        f7396o.put(ShareType.SINA_WEIBO, Integer.valueOf((int) R.string.sina_weibo_share_success));
        f7396o.put(ShareType.QQ_WEIBO, Integer.valueOf((int) R.string.qq_weibo_share_success));
        f7396o.put(ShareType.QZONE, Integer.valueOf((int) R.string.qzone_share_success));
        f7397p.put(ShareType.SINA_WEIBO, Integer.valueOf((int) R.string.sina_weibo_share_fail));
        f7397p.put(ShareType.QQ_WEIBO, Integer.valueOf((int) R.string.qq_weibo_share_fail));
        f7397p.put(ShareType.QZONE, Integer.valueOf((int) R.string.qzone_share_fail));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public boolean m2000l() {
        if (f7394f.mo2083e()) {
            AccessTokenUtil.m1941a(getContext(), f7394f.mo2084d());
            m1995q();
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void m2032a(IShareAction iShareAction) {
        this.f7409m = iShareAction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2033a(Bundle bundle) {
        if (getContext() != null) {
            f7394f.m2112a(getContext(), bundle);
            m1997o();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2021a(Integer num) {
        if (num != null && num.intValue() > 0) {
            Toast.makeText(getContext(), num.intValue(), Toast.LENGTH_SHORT).show();
        }
    }

    public ShareSelectDialog(Activity activity, ShareInfo shareInfo) {
        super(activity);
        this.f7407k = ShareType.NONE;
        this.f7398a = new ArrayMap();
        this.f7399b = new ArrayMap();
        this.f7410q = new ApiCallback() { // from class: com.sds.android.ttpod.share.c.c.1
            @Override // com.sds.android.ttpod.share.p136a.ApiCallback
            /* renamed from: a */
            public void mo1987a(ShareResult shareResult) {
                if (shareResult.m2092a()) {
                    LogUtils.debug("ShareSelectDialog", "share success: " + shareResult.m2089b());
                    ShareSelectDialog.this.m2021a((Integer) ShareSelectDialog.f7396o.get(ShareSelectDialog.this.f7407k));
                } else {
                    LogUtils.debug("ShareSelectDialog", "share failed: " + shareResult.m2089b());
                    if (!ShareSelectDialog.this.m2000l()) {
                        ShareSelectDialog.this.m2021a((Integer) ShareSelectDialog.f7397p.get(ShareSelectDialog.this.f7407k));
                    }
                }
                if (ShareSelectDialog.this.f7409m != null) {
                    ShareSelectDialog.this.f7409m.mo2116a(ShareSelectDialog.this.f7407k, ShareSelectDialog.this.f7408l, shareResult);
                }
            }
        };
        this.f7411r = new AuthCallback() { // from class: com.sds.android.ttpod.share.c.c.6
            @Override // com.sds.android.ttpod.share.p137b.AuthCallback
            /* renamed from: a */
            public void onSuccess(Bundle bundle) {
                ShareSelectDialog.this.m2035a(0, bundle);
            }

            @Override // com.sds.android.ttpod.share.p137b.AuthCallback
            /* renamed from: a */
            public void onError(String errorMessage) {
                ShareSelectDialog.this.m2035a(1, errorMessage);
            }
        };
        this.f7412s = new Handler() { // from class: com.sds.android.ttpod.share.c.c.7
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        LogUtils.debug("ShareSelectDialog", "auth success");
                        ShareSelectDialog.this.m2033a((Bundle) message.obj);
                        return;
                    case 1:
                        LogUtils.debug("ShareSelectDialog", "auth failed");
                        ShareSelectDialog.this.m2021a((Integer) ShareSelectDialog.f7395n.get(ShareSelectDialog.this.f7407k));
                        return;
                    case 2:
                        ShareSelectDialog.this.m2021a(Integer.valueOf((int) R.string.share_no_network));
                        return;
                    case 3:
                        ShareSelectDialog.this.m2021a(Integer.valueOf((int) R.string.no_wechat));
                        ShareSelectDialog.this.dismiss();
                        return;
                    case 4:
                        ShareSelectDialog.this.m2021a(Integer.valueOf((int) R.string.wechat_not_support_friend));
                        return;
                    case 5:
                    case 6:
                        ShareSelectDialog.this.m2021a(Integer.valueOf((int) R.string.local_song_no_share));
                        ShareSelectDialog.this.dismiss();
                        return;
                    default:
                        return;
                }
            }
        };
        if (activity != null && !activity.isFinishing() && shareInfo != null) {
            this.f7403g = activity;
            this.f7408l = shareInfo;
            this.f7404h = EnvironmentUtils.C0605d.m8466b(activity);
            m1988x();
            this.f7401d.m7414a(this.f7402e);
            this.f7401d.notifyDataSetChanged();
            setTitle(R.string.share_to);
            m7263a(2, 8, 0, (OnClickListener) null);
            m7263a(0, 8, 0, (OnClickListener) null);
            m2010e();
            mo2016c();
        }
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: a */
    protected View inflate(Context context) {
        View inflate = View.inflate(context, R.layout.dialog_share_select, null);
        this.f7401d = new ShareSelectAdapter(getContext());
        this.f7400c = (GridView) inflate.findViewById(R.id.gridview_share_select);
        this.f7400c.setAdapter((ListAdapter) this.f7401d);
        this.f7400c.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.share.c.c.8
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ShareSelectDialog.this.m2015c(i);
            }
        });
        return inflate;
    }

    /* renamed from: e */
    protected void m2010e() {
        this.f7398a.put(ShareType.WECHAT, 272);
        this.f7398a.put(ShareType.WECHAT_FRIENDS, 273);
        this.f7398a.put(ShareType.QQ, 270);
        this.f7398a.put(ShareType.QZONE, 271);
        this.f7398a.put(ShareType.SINA_WEIBO, 274);
        this.f7398a.put(ShareType.QQ_WEIBO, 275);
        this.f7398a.put(ShareType.OTHER, 276);
        this.f7398a.put(ShareType.FRIEND, 269);
    }

    /* renamed from: c */
    protected void mo2016c() {
        this.f7399b.put(ShareType.WECHAT, SAction.ACTION_SONG_SHARE_TO_WECHAT);
        this.f7399b.put(ShareType.WECHAT_FRIENDS, SAction.ACTION_SONG_SHARE_TO_WECHAT_FRIEND_CIRCLE);
        this.f7399b.put(ShareType.QQ, SAction.ACTION_SONG_SHARE_TO_QQ_FRIEND);
        this.f7399b.put(ShareType.QZONE, SAction.ACTION_SONG_SHARE_TO_QQ_ZONE);
        this.f7399b.put(ShareType.SINA_WEIBO, SAction.ACTION_SONG_SHARE_TO_SINA_WEIBO);
        this.f7399b.put(ShareType.QQ_WEIBO, SAction.ACTION_SONG_SHARE_TO_TENCENT_WEIBO);
        this.f7399b.put(ShareType.OTHER, SAction.ACTION_SONG_SHARE_TO_OTHER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: f */
    public ShareSelectDialog getDialog() {
        return this;
    }

    /* renamed from: a */
    public void m2036a(int i, int i2, Intent intent) {
        if (this.f7406j != null) {
            this.f7406j.mo2065a(i, i2, intent);
            this.f7406j = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2035a(int i, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        this.f7412s.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ShareSelectDialog.java */
    /* renamed from: com.sds.android.ttpod.share.c.c$a */
    /* loaded from: classes.dex */
    public static class RunnableC2156a implements Runnable {

        /* renamed from: a */
        private Activity f7432a;

        /* renamed from: b */
        private String f7433b;

        public RunnableC2156a(Activity activity, String str) {
            this.f7432a = activity;
            this.f7433b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f7432a != null) {
                Intent intent = new Intent(this.f7432a, ApShareEntryActivity.class);
                intent.putExtra("key_media_id", this.f7433b);
                this.f7432a.startActivity(intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2015c(int i) {
        this.f7407k = this.f7402e.get(i).m7413a();
        f7394f = ShareApiFactory.m2057a(this.f7407k, this.f7403g);
        mo2022a(this.f7407k);
        if (this.f7407k == ShareType.OTHER) {
            this.f7408l.m1959d((this.f7408l.m1942p() ? this.f7408l.m1958e() : ShareContentUtil.m1935a(this.f7408l, this.f7407k)) + " " + getContext().getString(R.string.share_text_tail_info));
            if (this.f7409m != null) {
                this.f7409m.mo2117a(this.f7407k, this.f7408l);
            }
            f7394f.mo2096a(this.f7408l, this.f7410q);
            dismiss();
        } else if (this.f7407k == ShareType.FRIEND && !this.f7408l.m1942p()) {
            this.f7400c.postDelayed(new RunnableC2156a(this.f7403g, this.f7408l.m1973a()), 500L);
            dismiss();
        } else if (!EnvironmentUtils.DeviceConfig.isConnected()) {
            this.f7412s.sendEmptyMessage(2);
        } else {
            if (this.f7409m != null) {
                this.f7409m.mo2117a(this.f7407k, this.f7408l);
            }
            switch (this.f7407k) {
                case WECHAT:
                case WECHAT_FRIENDS:
                    m1992t();
                    break;
                case MUSIC_CYCLE:
                    m1999m();
                    break;
                case QQ:
                case QZONE:
                    m1994r();
                    break;
                case SINA_WEIBO:
                case QQ_WEIBO:
                    m1996p();
                    break;
            }
            hide();
        }
    }

    /* renamed from: a */
    protected void mo2022a(ShareType shareType) {
        if (this.f7408l != null && shareType != null) {
            //new SUserEvent("PAGE_CLICK", m2017b(shareType), SPage.PAGE_SHARE_DIALOG.getValue(), SPage.PAGE_NONE.getValue()).append("song_id", this.f7408l.m1952h()).post();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public int m2017b(ShareType shareType) {
        Integer num = this.f7398a.get(shareType);
        SAction sAction = this.f7399b.get(shareType);
        if (num != null && num.intValue() > 0) {
            //StatisticUtils.m4917a(num.intValue(), (int) 65537, 1L);
        }
        return sAction != null ? sAction.getValue() : SAction.ACTION_NONE.getValue();
    }

    /* renamed from: m */
    private void m1999m() {
        dismiss();
        mo2020b();
    }

    /* renamed from: n */
    private boolean m1998n() {
        String m1960d="";
        final String str = m1991u() + m1960d.hashCode() + ".jpg";
        if (!StringUtils.isEmpty(this.f7408l.m1960d())) {
            if (FileUtils.m8419a(str)) {
                this.f7408l.m1966b(str);
                m2011d(this.f7408l.m1956f());
            } else {
                m2023a(new ShareLoadListener<String>() { // from class: com.sds.android.ttpod.share.c.c.9
                    @Override // com.sds.android.ttpod.share.ShareLoadListener
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo1924a(String str2) {
                        ShareSelectDialog.this.m1989w();
                        if (!StringUtils.isEmpty(str2)) {
                            ShareSelectDialog.this.f7408l.m1966b(str);
                        }
                        ShareSelectDialog.this.m2011d(ShareSelectDialog.this.f7408l.m1956f());
                    }
                }, str, this.f7408l.m1960d());
            }
        } else {
            m2011d(this.f7408l.m1956f());
        }
        return this.f7408l.m1942p();
    }

    /* renamed from: o */
    private void m1997o() {
        if (!m1998n()) {
            final String string = getContext().getString(ShareType.getShareContentDialogTitle(this.f7407k));
            if (this.f7408l.m1952h().longValue() > 0) {
                m1990v();
                m2024a(new ShareLoadListener<ShareInfo>() { // from class: com.sds.android.ttpod.share.c.c.10
                    @Override // com.sds.android.ttpod.share.ShareLoadListener
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo1924a(ShareInfo shareInfo) {
                        ShareSelectDialog.this.m1989w();
                        ShareSelectDialog.this.m2011d(string);
                    }
                });
                return;
            }
            m2011d(string);
        }
    }

    /* renamed from: p */
    private void m1996p() {
        f7394f.m2113a(getContext());
        if (f7394f.m2109c()) {
            m1995q();
        } else {
            m1997o();
        }
    }

    /* renamed from: q */
    private void m1995q() {
        this.f7406j = ShareApiFactory.m2056b(this.f7407k, this.f7403g);
        this.f7406j.m2072a(this.f7411r);
    }

    /* renamed from: r */
    private void m1994r() {
        if (this.f7408l.m1942p()) {
            m1993s();
        } else if (this.f7408l.m1952h().longValue() > 0) {
            m2024a(new ShareLoadListener<ShareInfo>() { // from class: com.sds.android.ttpod.share.c.c.11
                @Override // com.sds.android.ttpod.share.ShareLoadListener
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo1924a(ShareInfo shareInfo) {
                    ShareSelectDialog.this.m1993s();
                }
            });
        } else {
            this.f7412s.sendEmptyMessage(5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public void m1993s() {
        f7394f.mo2096a(this.f7408l, this.f7410q);
        if ((f7394f instanceof WeChatApi) && !this.f7403g.isFinishing()) {
            WXEntryActivity.m1132a(this.f7410q);
        }
        dismiss();
    }

    /* renamed from: a */
    public void m2025a(ShareInfo shareInfo) {
        if (StringUtils.isEmpty(shareInfo.m1960d())) {
            this.f7408l.m1962c(ShareContentUtil.m1938a(shareInfo.m1950i()));
        }
    }

    /* renamed from: a */
    private void m2024a(final ShareLoadListener<ShareInfo> shareLoadListener) {
        m1990v();
        OnlineMediaItemAPI.m8866a(this.f7408l.m1952h()).m8544a(new RequestCallback<OnlineMediaItemsResult>() { // from class: com.sds.android.ttpod.share.c.c.12
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                if (dataList == null || dataList.isEmpty()) {
                    ShareSelectDialog.this.m1989w();
                    ShareSelectDialog.this.f7412s.sendEmptyMessage(6);
                    return;
                }
                OnlineMediaItem onlineMediaItem = dataList.get(0);
                String m1937a = ShareContentUtil.m1937a(onlineMediaItem);
                if (onlineMediaItem != null) {
                    ShareSelectDialog.this.f7408l.m1955f(m1937a);
                    ShareSelectDialog.this.f7408l.m1972a(onlineMediaItem.getArtistId());
                    ShareSelectDialog.this.m2025a(ShareSelectDialog.this.f7408l);
                    shareLoadListener.mo1924a(ShareSelectDialog.this.f7408l);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(OnlineMediaItemsResult onlineMediaItemsResult) {
                ShareSelectDialog.this.m1989w();
                ShareSelectDialog.this.f7412s.sendEmptyMessage(6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo2020b() {
    }

    /* renamed from: t */
    private void m1992t() {
        if (f7394f instanceof WeChatApi) {
            WeChatApi weChatApi = (WeChatApi) f7394f;
            if (!weChatApi.m2075g()) {
                this.f7412s.sendEmptyMessage(3);
            } else if (!weChatApi.mo2073f()) {
                this.f7412s.sendEmptyMessage(4);
            } else if (this.f7408l.m1942p()) {
                m2013c(this.f7408l.m1960d());
            } else if (this.f7408l.m1952h().longValue() > 0) {
                m2024a(new ShareLoadListener<ShareInfo>() { // from class: com.sds.android.ttpod.share.c.c.13
                    @Override // com.sds.android.ttpod.share.ShareLoadListener
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo1924a(ShareInfo shareInfo) {
                        ShareSelectDialog.this.m2013c(ShareSelectDialog.this.f7408l.m1960d());
                    }
                });
            } else {
                this.f7412s.sendEmptyMessage(5);
            }
        }
    }

    /* renamed from: u */
    private String m1991u() {
        String str = this.f7404h + File.separator + "image" + File.separator;
        if (!new File(str).exists()) {
            FileUtils.createFolder(str);
        }
        return str;
    }

    /* renamed from: a */
    private void m2023a(final ShareLoadListener<String> shareLoadListener, final String str, String str2) {
        m1990v();
        TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<String, String>(str2) { // from class: com.sds.android.ttpod.share.c.c.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public String inBackground(String str3) {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str3).openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    Bitmap decodeStream = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(str);
                    decodeStream.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    if (decodeStream != null) {
                        decodeStream.recycle();
                    }
                    return str;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: b  reason: avoid collision after fix types in other method */
            public void postExecute(String str3) {
                ShareSelectDialog.this.m1989w();
                shareLoadListener.mo1924a(str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2013c(String str) {
        if (!StringUtils.isEmpty(str)) {
            final String str2 = m1991u() + str.hashCode() + ".jpg";
            if (new File(str2).exists()) {
                this.f7408l.m1966b(str2);
                m1993s();
                return;
            }
            m2023a(new ShareLoadListener<String>() { // from class: com.sds.android.ttpod.share.c.c.3
                @Override // com.sds.android.ttpod.share.ShareLoadListener
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo1924a(String str3) {
                    ShareSelectDialog.this.m1989w();
                    if (!StringUtils.isEmpty(str3)) {
                        ShareSelectDialog.this.f7408l.m1966b(str2);
                    }
                    ShareSelectDialog.this.m1993s();
                }
            }, str2, str);
            return;
        }
        m1993s();
    }

    /* renamed from: v */
    private void m1990v() {
        if (this.f7405i == null && this.f7403g != null && !this.f7403g.isFinishing()) {
            this.f7405i = new ShareWaitingDialog(this.f7403g);
            this.f7405i.m7236a((CharSequence) this.f7403g.getString(R.string.share_message));
            this.f7405i.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public void m1989w() {
        if (this.f7405i != null) {
            this.f7405i.dismiss();
            this.f7405i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m2011d(String str) {
        if (this.f7403g != null && !this.f7403g.isFinishing()) {
            ShareContentDialog shareContentDialog = new ShareContentDialog(this.f7403g, f7394f, this.f7410q);
            shareContentDialog.setTitle(str);
            shareContentDialog.m2046a(this.f7407k, this.f7408l);
            shareContentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sds.android.ttpod.share.c.c.4
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    ShareSelectDialog.this.dismiss();
                }
            });
            shareContentDialog.show();
        }
    }

    /* renamed from: x */
    private void m1988x() {
        this.f7402e = new ArrayList(8);
        if (this.f7408l.m1968b()) {
            this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.share_friend, R.drawable.icon_share_sns_friend, ShareType.FRIEND));
        }
        if (this.f7408l != null && this.f7408l.m1948j()) {
            this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.music_circle, R.drawable.icon_share_sns_music_circle, ShareType.MUSIC_CYCLE));
        }
        this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.qq, R.drawable.icon_share_sns_qq, ShareType.QQ));
        this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.qq_zone, R.drawable.icon_share_sns_qzone, ShareType.QZONE));
        this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.wechat, R.drawable.icon_share_sns_weixin, ShareType.WECHAT));
        this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.wechat_friend, R.drawable.icon_share_sns_weixinfriend, ShareType.WECHAT_FRIENDS));
        this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.sina_weibo, R.drawable.icon_share_sns_sina, ShareType.SINA_WEIBO));
        this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.qq_weibo, R.drawable.icon_share_sns_tencent, ShareType.QQ_WEIBO));
        this.f7402e.add(new ShareSelectAdapter.C1000a(R.string.other, R.drawable.icon_share_sns_other, ShareType.OTHER));
    }

    /* renamed from: g */
    public ShareType m2006g() {
        return this.f7407k;
    }

    /* renamed from: h */
    public BaseApi m2004h() {
        return f7394f;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
    }
}
