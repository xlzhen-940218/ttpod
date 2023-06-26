package com.sds.android.ttpod.adapter.p073e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.MusicCircleModule;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.e.i */
/* loaded from: classes.dex */
public class UserListAdapter<Data extends TTPodUser> extends BaseListAdapter<Data> {

    /* renamed from: a */
    private InterfaceC0997a f3374a;

    /* compiled from: UserListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.e.i$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0997a {
        void onFollow(long j);

        void onUnFollow(long j);
    }

    public UserListAdapter(Context context, List<Data> list) {
        super(context, list);
    }

    /* renamed from: a */
    public void m7430a(InterfaceC0997a interfaceC0997a) {
        this.f3374a = interfaceC0997a;
    }

    @Override // com.sds.android.ttpod.adapter.BaseListAdapter
    /* renamed from: a */
    protected final View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = View.inflate(getContext(), R.layout.musiccircle_user_list_item, null);
        inflate.setTag(new UserInfoViewHolder(inflate));
        return inflate;
    }

    @Override
    protected void buildDataUI(View view, Data data, int i) {
        m7431a(view,  data, i);
    }

    /* renamed from: a */
    protected final void m7431a(View view, Data data, int i) {
        mo5434a((UserInfoViewHolder) view.getTag(),  data);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo5434a(final UserInfoViewHolder userInfoViewHolder, Data data) {
        UserAvatarView m7436a = userInfoViewHolder.m7436a();
        ImageCacheUtils.m4752a(m7436a, data.getAvatarUrl(), m7436a.getWidth(), m7436a.getHeight(), (int) R.drawable.img_avatar_default);
        m7436a.setVFlagVisible(data.isVerified());
        userInfoViewHolder.m7435b().setText(data.getNickName());
        userInfoViewHolder.m7434c().setText(getContext().getString(R.string.follower_str, Integer.valueOf(data.getFollowersCount())));
        final long userId = data.getUserId();
        final boolean booleanValue = ((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_FOLLOWED, Long.valueOf(userId)), Boolean.class)).booleanValue();
        if (booleanValue) {
            userInfoViewHolder.m7432e().setText(R.string.remove_follow);
            userInfoViewHolder.m7432e().setBackgroundResource(R.drawable.xml_musiccircle_follow_button_bg);
        } else {
            userInfoViewHolder.m7432e().setText(R.string.add_follow);
            userInfoViewHolder.m7432e().setBackgroundResource(R.drawable.xml_musiccircle_unfollow_button_bg);
        }
        if (MusicCircleModule.TUID_TTPOD == userId) {
            if (userInfoViewHolder.m7432e().getVisibility() != View.INVISIBLE) {
                userInfoViewHolder.m7432e().setVisibility(View.INVISIBLE);
            }
        } else {
            userInfoViewHolder.m7432e().setVisibility(View.VISIBLE);
        }
        userInfoViewHolder.m7432e().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.e.i.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Preferences.m2954aq() != null) {
                    userInfoViewHolder.m7432e().setText(R.string.is_processing);
                    if (booleanValue) {
                        CommandCenter.getInstance().execute(new Command(CommandID.UNFOLLOW_FRIEND, Long.valueOf(userId), ""));
                    } else {
                        CommandCenter.getInstance().execute(new Command(CommandID.FOLLOW_FRIEND, Long.valueOf(userId), ""));
                    }
                    if (UserListAdapter.this.f3374a != null) {
                        if (booleanValue) {
                            UserListAdapter.this.f3374a.onUnFollow(userId);
                            return;
                        } else {
                            UserListAdapter.this.f3374a.onFollow(userId);
                            return;
                        }
                    }
                    return;
                }
                EntryUtils.m8297a(true);
            }
        });
    }
}
