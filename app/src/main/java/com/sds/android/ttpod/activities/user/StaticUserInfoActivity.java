package com.sds.android.ttpod.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import java.io.Serializable;

/* loaded from: classes.dex */
public class StaticUserInfoActivity extends SlidingClosableActivity {
    private TTPodUser mUser;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.musiccircle_static_user_info_layout);
        onNewIntent(getIntent());
        TextView textView = (TextView) findViewById(R.id.text_sex);
        TextView textView2 = (TextView) findViewById(R.id.text_birthday);
        int m7229a = DisplayUtils.dp2px(120);
        ImageCacheUtils.m4752a((ImageView) findViewById(R.id.user_avatar), this.mUser.getAvatarUrl(), m7229a, m7229a, (int) R.drawable.img_avatar_default);
        ((TextView) findViewById(R.id.user_name)).setText(this.mUser.getNickName());
        ((TextView) findViewById(R.id.user_extra_info_1)).setText("粉丝：" + this.mUser.getFollowersCount());
        ((TextView) findViewById(R.id.user_extra_info_2)).setText("关注：" + this.mUser.getFollowingsCount());
        textView.setText(this.mUser.getSex() == 0 ? "美女" : "帅哥");
        textView2.setText(new BirthdayDate(this.mUser.getBirthdayInSecond()).toString());
        setTitle(this.mUser.getNickName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra("user");
            if (serializableExtra instanceof TTPodUser) {
                this.mUser = (TTPodUser) serializableExtra;
                return;
            }
            throw new IllegalArgumentException("illegal value for BundleKey.USER");
        }
    }
}
