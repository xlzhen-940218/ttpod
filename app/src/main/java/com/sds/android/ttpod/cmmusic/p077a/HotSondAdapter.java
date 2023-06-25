package com.sds.android.ttpod.cmmusic.p077a;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.StringUtils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p079c.CaiLingFuctionOpenAndCancel;
import com.sds.android.ttpod.cmmusic.p079c.GetPreListen;
import com.sds.android.ttpod.cmmusic.p079c.ListenOrderInfo;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import com.sds.android.ttpod.cmmusic.p081e.BaseDeviceGetInfo;
import com.sds.android.ttpod.cmmusic.p081e.CustomProgressDialog;
import com.sds.android.ttpod.cmmusic.p081e.GetDeviceInfo;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.sds.android.ttpod.cmmusic.a.a */
/* loaded from: classes.dex */
public class HotSondAdapter extends BaseAdapter implements View.OnClickListener {

    /* renamed from: g */
    private static WeakReference<HotSondAdapter> f3421g;

    /* renamed from: h */
    private static LayoutInflater f3422h = null;

    /* renamed from: c */
    private Context f3425c;

    /* renamed from: d */
    private ArrayList<HashMap<String, String>> f3426d;

    /* renamed from: j */
    private String f3430j;

    /* renamed from: l */
    private Dialog f3432l;

    /* renamed from: m */
    private String f3433m;

    /* renamed from: a */
    private AsyncTaskC1019a f3423a = null;

    /* renamed from: b */
    private CustomProgressDialog f3424b = null;

    /* renamed from: e */
    private HashMap<String, String> f3427e = new HashMap<>();

    /* renamed from: f */
    private BaseDeviceGetInfo f3428f = GetDeviceInfo.m7273a();

    /* renamed from: i */
    private int f3429i = -1;

    /* renamed from: k */
    private Handler f3431k = new Handler(Looper.getMainLooper());

    /* renamed from: n */
    private Handler f3434n = new Handler() { // from class: com.sds.android.ttpod.cmmusic.a.a.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 1:
                        HotSondAdapter.this.f3428f = (BaseDeviceGetInfo) message.obj;
                        HotSondAdapter.this.f3432l = new Dialog(HotSondAdapter.this.f3425c, R.style.DialogStyle);
                        HotSondAdapter.this.f3432l.setContentView(HotSondAdapter.this.m7385a());
                        HotSondAdapter.this.f3432l.show();
                        break;
                    case 6:
                        HashMap hashMap = (HashMap) message.obj;
                        HotSondAdapter.this.m7366b((String) hashMap.get("cailing_id"), (String) hashMap.get("resource_name"), (String) hashMap.get("resource_songer"));
                        break;
                    case 7:
                        Toast.makeText(HotSondAdapter.this.f3425c, HotSondAdapter.this.f3425c.getString(R.string.notcmcccard), Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(HotSondAdapter.this.f3425c, ((BaseDeviceGetInfo) message.obj).m7283i(), Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        HotSondAdapter.this.f3432l = new Dialog(HotSondAdapter.this.f3425c, R.style.DialogStyle);
                        HotSondAdapter.this.f3432l.setContentView(HotSondAdapter.this.m7376a((BaseDeviceGetInfo) message.obj));
                        HotSondAdapter.this.f3432l.show();
                        break;
                    case 12:
                        Toast.makeText(HotSondAdapter.this.f3425c, HotSondAdapter.this.f3425c.getString(R.string.networkinstable), Toast.LENGTH_SHORT).show();
                        break;
                    case 13:
                        BaseDeviceGetInfo baseDeviceGetInfo = (BaseDeviceGetInfo) message.obj;
                        if (baseDeviceGetInfo != null) {
                            if (baseDeviceGetInfo.m7283i() != null) {
                                Toast.makeText(HotSondAdapter.this.f3425c, baseDeviceGetInfo.m7283i(), Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                Toast.makeText(HotSondAdapter.this.f3425c, HotSondAdapter.this.f3425c.getString(R.string.openfuctionfailure), Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public HotSondAdapter(Context context, ArrayList<HashMap<String, String>> arrayList, String str) {
        this.f3425c = context;
        this.f3426d = arrayList;
        this.f3430j = str;
        f3422h = (LayoutInflater) this.f3425c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3426d == null) {
            return 0;
        }
        return this.f3426d.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.f3426d == null) {
            return null;
        }
        return this.f3426d.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = f3422h.inflate(R.layout.cmmusic_list_row, (ViewGroup) null);
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.list_item_play);
        Button button = (Button) view.findViewById(R.id.btn_soundplay);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_paying);
        HashMap<String, String> hashMap = this.f3426d.get(i);
        ((TextView) view.findViewById(R.id.text_resource_name)).setText(hashMap.get("resource_name"));
        ((TextView) view.findViewById(R.id.text_resource_songer)).setText(hashMap.get("resource_songer"));
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.cmmusic.a.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HotSondAdapter.m7355l(HotSondAdapter.this);
                HotSondAdapter.this.f3429i = i;
                HotSondAdapter.this.m7374a((HashMap) HotSondAdapter.this.f3426d.get(i));
            }
        });
        ((Button) view.findViewById(R.id.btn_soundbuy)).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.cmmusic.a.a.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HotSondAdapter.this.m7384a(i);
            }
        });
        if (this.f3429i == i) {
            view.setBackgroundColor(Color.parseColor("#EAF4F6"));
            imageView.setVisibility(View.VISIBLE);
            button.setBackgroundResource(R.drawable.cmmusic_shiting_ing);
            linearLayout.setTag(this.f3430j);
        } else {
            view.setBackgroundColor(-1);
            imageView.setVisibility(View.INVISIBLE);
            button.setBackgroundResource(R.drawable.cmmusic_shiting);
        }
        return view;
    }

    /* renamed from: a */
    public void m7372a(boolean z) {
        if (!z) {
            this.f3429i = -1;
        }
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7374a(final HashMap<String, String> hashMap) {
        CmmusicStatistic.m7312a(this.f3430j, hashMap.get("resource_name"));
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.a.a.3
            @Override // java.lang.Runnable
            public void run() {
                BaseDeviceGetInfo m7327a = GetPreListen.m7327a((String) hashMap.get("cailing_id"));
                if (HotSondAdapter.this.f3434n != null && HotSondAdapter.this.f3431k != null) {
                    if (m7327a.m7281k() != null) {
                        HotSondAdapter.this.m7375a(m7327a.m7281k(), (String) hashMap.get("resource_name"), (String) hashMap.get("resource_songer"));
                    } else if (StringUtils.m8344a(m7327a.m7285h(), "999011")) {
                        Message message = new Message();
                        message.what = 7;
                        HotSondAdapter.this.f3434n.sendMessage(message);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7375a(final String str, final String str2, final String str3) {
        this.f3431k.post(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.a.a.4
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                if (!StringUtils.isEmpty(str)) {
                    arrayList.add(MediaItemUtils.m4711a(str, str2, str3, 0));
                    HotSondAdapter.this.m7373a(arrayList, 0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m7373a(List<MediaItem> list, int i) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        if (i >= list.size()) {
            i = 0;
        }
        CommandCenter.getInstance().m4606a(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, list));
        CommandCenter.getInstance().m4606a(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list.get(i)));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7384a(int i) {
        this.f3427e = this.f3426d.get(i);
        CmmusicStatistic.m7309b(this.f3430j, this.f3427e.get("resource_name"));
        try {
            this.f3423a = new AsyncTaskC1019a();
            this.f3423a.execute(new Integer[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public View m7385a() {
        View inflate = View.inflate(this.f3425c, R.layout.cmmusic_open_cailing_fuction_activity, null);
        inflate.findViewById(R.id.btn_cancel_opencailing).setOnClickListener(this);
        inflate.findViewById(R.id.btn_confirm_opencailing).setOnClickListener(this);
        ((TextView) inflate.findViewById(R.id.text_coloropenprice)).setText(this.f3425c.getString(R.string.cailingopenorder) + "5" + this.f3425c.getString(R.string.opencailingprice));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public View m7376a(BaseDeviceGetInfo baseDeviceGetInfo) {
        View inflate = View.inflate(this.f3425c, R.layout.cmmusic_listen_order_check_activity, null);
        TextView textView = (TextView) inflate.findViewById(R.id.text_songname_orderpage);
        TextView textView2 = (TextView) inflate.findViewById(R.id.text_singername_orderpage);
        TextView textView3 = (TextView) inflate.findViewById(R.id.text_price_orderpage);
        TextView textView4 = (TextView) inflate.findViewById(R.id.text_invaliddate_orderpage);
        textView.setText(((Object) textView.getText()) + baseDeviceGetInfo.m7278n());
        textView2.setText(((Object) textView2.getText()) + baseDeviceGetInfo.m7277o());
        textView3.setText(((Object) textView3.getText()) + String.valueOf(Integer.valueOf(baseDeviceGetInfo.m7279m()).intValue() / 100) + this.f3425c.getString(R.string.price));
        textView4.setText(((Object) textView4.getText()) + baseDeviceGetInfo.m7280l());
        inflate.findViewById(R.id.btn_cancel_orderpage).setOnClickListener(this);
        inflate.findViewById(R.id.btn_submit_orderpage).setOnClickListener(this);
        this.f3433m = baseDeviceGetInfo.m7282j();
        return inflate;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.btn_cancel_opencailing == id) {
            this.f3432l.dismiss();
        } else if (R.id.btn_confirm_opencailing == id) {
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.a.a.6
                @Override // java.lang.Runnable
                public void run() {
                    BaseDeviceGetInfo m7330a = CaiLingFuctionOpenAndCancel.m7330a();
                    if (HotSondAdapter.this.f3434n != null) {
                        Message message = new Message();
                        message.obj = m7330a;
                        message.what = 13;
                        HotSondAdapter.this.f3434n.sendMessage(message);
                    }
                }
            });
            this.f3432l.dismiss();
        } else if (R.id.btn_cancel_orderpage == id) {
            this.f3432l.dismiss();
        } else if (R.id.btn_submit_orderpage == id) {
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.a.a.7
                @Override // java.lang.Runnable
                public void run() {
                    BaseDeviceGetInfo m7273a = GetDeviceInfo.m7273a();
                    m7273a.m7294c(HotSondAdapter.this.f3433m);
                    BaseDeviceGetInfo m7323a = ListenOrderInfo.m7323a(m7273a);
                    if (HotSondAdapter.this.f3434n != null) {
                        Message message = new Message();
                        message.what = 13;
                        message.obj = m7323a;
                        HotSondAdapter.this.f3434n.sendMessage(message);
                    }
                }
            });
            this.f3432l.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m7371b() {
        if (this.f3424b == null) {
            this.f3424b = CustomProgressDialog.m7275a(this.f3425c);
            this.f3424b.m7274a(this.f3425c.getResources().getString(R.string.loding));
        }
        this.f3424b.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m7365c() {
        if (this.f3424b != null) {
            this.f3424b.dismiss();
            this.f3424b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m7366b(final String str, final String str2, final String str3) {
        TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.a.a.8
            @Override // java.lang.Runnable
            public void run() {
                BaseDeviceGetInfo m7322a = ListenOrderInfo.m7322a(str, str2, str3);
                if (HotSondAdapter.this.f3434n != null) {
                    Message message = new Message();
                    message.obj = m7322a;
                    message.what = 9;
                    HotSondAdapter.this.f3434n.sendMessage(message);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public static void m7355l(HotSondAdapter hotSondAdapter) {
        if (f3421g != null && f3421g.get() != null) {
            f3421g.get().m7372a(false);
        }
        hotSondAdapter.m7372a(true);
        f3421g = new WeakReference<>(hotSondAdapter);
    }

    /* compiled from: HotSondAdapter.java */
    /* renamed from: com.sds.android.ttpod.cmmusic.a.a$a */
    /* loaded from: classes.dex */
    public class AsyncTaskC1019a extends AsyncTask<Integer, String, Integer> {
        public AsyncTaskC1019a() {
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            HotSondAdapter.this.m7365c();
            super.onCancelled();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Integer doInBackground(Integer... numArr) {
            try {
                BaseDeviceGetInfo m7328b = CaiLingFuctionOpenAndCancel.m7328b();
                if (m7328b.m7285h() == null) {
                    Message message = new Message();
                    message.what = 12;
                    HotSondAdapter.this.f3434n.sendMessage(message);
                } else {
                    Message message2 = new Message();
                    if (m7328b.m7285h().equals("100100") || m7328b.m7285h().equals("999011")) {
                        message2.obj = m7328b;
                        message2.what = 1;
                    } else if (m7328b.m7285h().equals("000000")) {
                        message2.obj = HotSondAdapter.this.f3427e;
                        message2.what = 6;
                    } else if (m7328b.m7285h().equals("999011")) {
                        message2.what = 7;
                    } else if (m7328b.m7285h().equals("999003")) {
                        message2.what = 7;
                    } else {
                        message2.obj = m7328b;
                        message2.what = 8;
                    }
                    HotSondAdapter.this.f3434n.sendMessage(message2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(HotSondAdapter.this.f3425c, HotSondAdapter.this.f3425c.getResources().getString(R.string.networkinstable), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            HotSondAdapter.this.m7371b();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public void onPostExecute(Integer num) {
            HotSondAdapter.this.m7365c();
        }
    }
}
