package com.sds.android.ttpod.adapter.p073e.p074a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageContent;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.PrivateMessageAPI;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.emoticons.EmoticonConversionUtil;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.p107a.ErrorStatistic;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.a.d */
/* loaded from: classes.dex */
public class PrivateMessageAdapter extends BaseAdapter {

    /* renamed from: a */
    private Context f3278a;

    /* renamed from: b */
    private TTPodUser f3279b;

    /* renamed from: c */
    private TTPodUser f3280c;

    /* renamed from: d */
    private int f3281d;

    /* renamed from: e */
    private String f3282e;

    /* renamed from: f */
    private List<PrivateMessageContent> f3283f = new ArrayList();

    /* renamed from: g */
    private List<PrivateMessageItem> f3284g = new ArrayList();

    /* renamed from: h */
    private WrapUserPostListFragment.InterfaceC1704a f3285h;

    /* renamed from: a */
    public void m7529a(WrapUserPostListFragment.InterfaceC1704a interfaceC1704a) {
        this.f3285h = interfaceC1704a;
    }

    public PrivateMessageAdapter(Context context, List<PrivateMessageContent> list, TTPodUser tTPodUser, TTPodUser tTPodUser2) {
        if (context == null || list == null || tTPodUser == null || tTPodUser2 == null) {
            throw new IllegalArgumentException("context,privateMessageContents,userCurrent,userReplyTo should not be null");
        }
        this.f3278a = context;
        this.f3279b = tTPodUser2;
        this.f3280c = tTPodUser;
        m7524b(list);
    }

    /* renamed from: a */
    public List<PrivateMessageItem> m7527a(List<PrivateMessageContent> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        long j = -1;
        this.f3281d = -1;
        for (PrivateMessageContent privateMessageContent : list) {
            long m7535a = m7535a(privateMessageContent.getTimestamp());
            if (m7535a != j) {
                arrayList.add(new PrivateMessageItem(null, null, privateMessageContent.getTimestamp(), null, 0));
            } else {
                m7535a = j;
            }
            if (privateMessageContent.getSenderId() == this.f3280c.getUserId()) {
                arrayList.add(new PrivateMessageItem(this.f3280c, privateMessageContent.getPmId(), privateMessageContent.getTimestamp(), privateMessageContent.getMessage(), 2));
            } else if (privateMessageContent.getSenderId() == this.f3279b.getUserId()) {
                arrayList.add(new PrivateMessageItem(this.f3279b, privateMessageContent.getPmId(), privateMessageContent.getTimestamp(), privateMessageContent.getMessage(), 1));
            }
            if (this.f3281d < 0 && !StringUtils.m8346a(privateMessageContent.getPmId()) && privateMessageContent.getPmId().equals(this.f3282e)) {
                this.f3281d = arrayList.size();
            }
            j = m7535a;
        }
        if (this.f3281d < 0) {
            this.f3281d = arrayList.size();
        }
        return arrayList;
    }

    /* renamed from: b */
    public void m7524b(List<PrivateMessageContent> list) {
        if (!list.isEmpty()) {
            this.f3283f.clear();
            this.f3283f.addAll(list);
            Collections.reverse(this.f3283f);
            this.f3282e = this.f3283f.get(this.f3283f.size() - 1).getPmId();
            this.f3284g.clear();
            this.f3284g = m7527a(this.f3283f);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    private static long m7535a(long j) {
        return (long) Math.ceil(j / 86400.0d);
    }

    /* renamed from: a */
    private void m7531a(C0984c c0984c, boolean z, boolean z2, boolean z3) {
        c0984c.f3302b.f3294b.setVisibility(z ? View.VISIBLE : View.GONE);
        c0984c.f3303c.f3294b.setVisibility(z2 ? View.VISIBLE : View.GONE);
        c0984c.f3304d.f3299b.setVisibility(z3 ? View.VISIBLE : View.GONE);
    }

    /* renamed from: a */
    private void m7533a(TextView textView, long j) {
        textView.setText(TimeUtils.m8157a(j));
    }

    /* renamed from: b */
    private void m7526b(TextView textView, long j) {
        textView.setText(TimeUtils.m8154b(j));
    }

    /* renamed from: a */
    private void m7532a(TextView textView, PrivateMessageItem privateMessageItem) {
        CharSequence m6641a = EmoticonConversionUtil.m6639b().m6641a(this.f3278a, privateMessageItem.m7512a());
        if (m6641a == null) {
            m6641a = "";
        }
        textView.setText(m6641a);
        textView.setOnClickListener(new View$OnClickListenerC09781(privateMessageItem));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PrivateMessageAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.d$1 */
    /* loaded from: classes.dex */
    public class View$OnClickListenerC09781 implements View.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ PrivateMessageItem f3286a;

        View$OnClickListenerC09781(PrivateMessageItem privateMessageItem) {
            this.f3286a = privateMessageItem;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PopupsUtils.m6725a(PrivateMessageAdapter.this.f3278a, new ActionItem[]{new ActionItem(1, 0, "删除")}, "私信", new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.adapter.e.a.d.1.1
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    if (actionItem.m7005e() == 1) {
                        final Request<BaseResult> m8838a = PrivateMessageAPI.m8838a(PrivateMessageAdapter.this.f3280c.getAccessToken(), View$OnClickListenerC09781.this.f3286a.m7509d());
                        m8838a.m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.adapter.e.a.d.1.1.1
                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            public void onRequestSuccess(BaseResult baseResult) {
                                int size = PrivateMessageAdapter.this.f3284g.size() - 1;
                                while (true) {
                                    if (size < 0) {
                                        break;
                                    } else if (((PrivateMessageItem) PrivateMessageAdapter.this.f3284g.get(size)).m7509d().equals(View$OnClickListenerC09781.this.f3286a.m7509d())) {
                                        PrivateMessageAdapter.this.f3284g.remove(size);
                                        break;
                                    } else {
                                        size--;
                                    }
                                }
                                PrivateMessageAdapter.this.notifyDataSetChanged();
                            }

                            @Override // com.sds.android.sdk.lib.request.RequestCallback
                            public void onRequestFailure(BaseResult baseResult) {
                                PopupsUtils.m6721a("删除失败");
                                ErrorStatistic.m5232g(m8838a.m8532e());
                            }
                        });
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private void m7528a(UserAvatarView userAvatarView, final TTPodUser tTPodUser) {
        userAvatarView.setVFlagVisible(tTPodUser.isVerified());
        ImageCacheUtils.m4752a(userAvatarView, tTPodUser.getAvatarUrl(), userAvatarView.getWidth(), userAvatarView.getHeight(), (int) R.drawable.img_avatar_default);
        userAvatarView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.a.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PrivateMessageAdapter.this.f3285h.mo5428a(tTPodUser);
            }
        });
    }

    /* renamed from: a */
    private void m7534a(View view, PrivateMessageItem privateMessageItem, int i) {
        C0984c c0984c = (C0984c) view.getTag();
        if (privateMessageItem.m7510c() == 0) {
            m7531a(c0984c, false, false, true);
            m7533a(c0984c.f3304d.f3300c, privateMessageItem.m7511b());
        } else if (1 == privateMessageItem.m7510c()) {
            m7531a(c0984c, true, false, false);
            m7526b(c0984c.f3302b.f3297e, privateMessageItem.m7511b());
            m7532a(c0984c.f3302b.f3296d, privateMessageItem);
            m7528a(c0984c.f3302b.f3295c, privateMessageItem.m7508e());
        } else if (2 == privateMessageItem.m7510c()) {
            m7531a(c0984c, false, true, false);
            m7526b(c0984c.f3303c.f3297e, privateMessageItem.m7511b());
            m7532a(c0984c.f3303c.f3296d, privateMessageItem);
            m7528a(c0984c.f3303c.f3295c, privateMessageItem.m7508e());
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3284g.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f3284g.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.f3278a, R.layout.musiccircle_chatting_item, null);
            view.setTag(new C0984c(view));
        }
        m7534a(view, this.f3284g.get(i), i);
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PrivateMessageAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.d$c */
    /* loaded from: classes.dex */
    public class C0984c {

        /* renamed from: b */
        private C0982a f3302b;

        /* renamed from: c */
        private C0982a f3303c;

        /* renamed from: d */
        private C0983b f3304d;

        public C0984c(View view) {
            this.f3302b = new C0982a(view.findViewById(R.id.chatting_left));
            this.f3303c = new C0982a(view.findViewById(R.id.chatting_right));
            this.f3304d = new C0983b(view.findViewById(R.id.chatting_middle));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PrivateMessageAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.d$a */
    /* loaded from: classes.dex */
    public class C0982a {

        /* renamed from: b */
        private View f3294b;

        /* renamed from: c */
        private UserAvatarView f3295c;

        /* renamed from: d */
        private TextView f3296d;

        /* renamed from: e */
        private TextView f3297e;

        public C0982a(View view) {
            this.f3294b = view;
            this.f3295c = (UserAvatarView) view.findViewById(R.id.image_userhead);
            this.f3296d = (TextView) view.findViewById(R.id.text_content);
            this.f3297e = (TextView) view.findViewById(R.id.text_time);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PrivateMessageAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.a.d$b */
    /* loaded from: classes.dex */
    public class C0983b {

        /* renamed from: b */
        private View f3299b;

        /* renamed from: c */
        private TextView f3300c;

        public C0983b(View view) {
            this.f3299b = view;
            this.f3300c = (TextView) view.findViewById(R.id.text_date);
        }
    }
}
