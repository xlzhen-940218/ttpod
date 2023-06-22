package com.sds.android.ttpod.cmmusic.p077a;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.sds.android.sdk.lib.p065e.TaskScheduler;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p079c.SettingUserListenDefaultAndDelete;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.cmmusic.a.c */
/* loaded from: classes.dex */
public class PersionalListenControlAdapter extends BaseAdapter implements View.OnClickListener {

    /* renamed from: a */
    private ArrayList<HashMap<String, String>> f3461a;

    /* renamed from: b */
    private Context f3462b;

    /* renamed from: c */
    private LayoutInflater f3463c;

    /* renamed from: e */
    private String f3465e;

    /* renamed from: f */
    private Dialog f3466f;

    /* renamed from: g */
    private String f3467g;

    /* renamed from: d */
    private int f3464d = -1;

    /* renamed from: h */
    private Handler f3468h = new Handler() { // from class: com.sds.android.ttpod.cmmusic.a.c.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = (String) message.obj;
                    if (str != null) {
                        Toast.makeText(PersionalListenControlAdapter.this.f3462b, str, Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Toast.makeText(PersionalListenControlAdapter.this.f3462b, PersionalListenControlAdapter.this.f3462b.getString(R.string.networkinstable), Toast.LENGTH_SHORT).show();
                        return;
                    }
                default:
                    return;
            }
        }
    };

    public PersionalListenControlAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.f3463c = null;
        this.f3462b = context;
        this.f3461a = arrayList;
        this.f3463c = (LayoutInflater) this.f3462b.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3461a == null) {
            return 0;
        }
        return this.f3461a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.f3461a == null) {
            return null;
        }
        return this.f3461a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f3463c.inflate(R.layout.cmmusic_persional_list_row, (ViewGroup) null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.img_persionlistdefault);
        final HashMap<String, String> hashMap = this.f3461a.get(i);
        ((TextView) view.findViewById(R.id.text_persionlistsongname)).setText(hashMap.get("resource_name"));
        ((TextView) view.findViewById(R.id.text_persionlistsonger)).setText(hashMap.get("resource_songer"));
        ((TextView) view.findViewById(R.id.text_timeout)).setText(this.f3462b.getString(R.string.timeout) + ((Object) hashMap.get("time_out").subSequence(0, 10)));
        ((Button) view.findViewById(R.id.btn_set_default)).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.cmmusic.a.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                PersionalListenControlAdapter.this.f3466f = new Dialog(PersionalListenControlAdapter.this.f3462b, R.style.DialogStyle);
                PersionalListenControlAdapter.this.f3466f.setContentView(PersionalListenControlAdapter.this.m7347a(hashMap));
                PersionalListenControlAdapter.this.f3466f.show();
            }
        });
        if (this.f3464d == i) {
            view.setBackgroundColor(this.f3462b.getResources().getColor(R.color.listitem_touch_down));
            imageView.setImageResource(R.drawable.cmmusic_default_true);
        } else {
            view.setBackgroundColor(0);
            imageView.setImageResource(R.drawable.cmmusic_default_false);
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public View m7347a(HashMap<String, String> hashMap) {
        View inflate = View.inflate(this.f3462b, R.layout.cmmusic_set_default_or_delete_listen_activity, null);
        inflate.findViewById(R.id.btn_setdefault_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.btn_setdefault_submit).setOnClickListener(this);
        this.f3467g = hashMap.get("resource_name");
        this.f3465e = hashMap.get("cailing_id");
        String string = this.f3462b.getResources().getString(R.string.setdefaultconfirm);
        ((TextView) inflate.findViewById(R.id.text_prompt_setdefaultpage)).setText(string + this.f3467g + this.f3462b.getResources().getString(R.string.setdefaultorder));
        return inflate;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.btn_setdefault_cancel == id) {
            this.f3466f.dismiss();
        } else if (R.id.btn_setdefault_submit == id) {
            CmmusicStatistic.m7307c(this.f3467g);
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.a.c.2
                @Override // java.lang.Runnable
                public void run() {
                    String m7317a = SettingUserListenDefaultAndDelete.m7317a(PersionalListenControlAdapter.this.f3465e);
                    if (PersionalListenControlAdapter.this.f3468h != null) {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = m7317a;
                        PersionalListenControlAdapter.this.f3468h.sendMessage(message);
                    }
                }
            });
            this.f3466f.dismiss();
        }
    }
}
