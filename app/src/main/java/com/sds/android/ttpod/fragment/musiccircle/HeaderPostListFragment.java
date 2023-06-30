package com.sds.android.ttpod.fragment.musiccircle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.api.UserSystemAPI;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.sdk.lib.request.Request;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.LoginActivity;
import com.sds.android.ttpod.activities.user.PickImageHelper;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.widget.PullToRefreshHelper;
import com.sds.android.ttpod.widget.PullToRefreshListView;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class HeaderPostListFragment extends PostListByIdFragment implements PullToRefreshHelper.InterfaceC2284a {
    private static final String KEY_REQUEST_CODE = "request_code";
    private static final int REQUEST_IMAGE_COVER = 4097;
    private int mCachedRequestCode;
    private View.OnClickListener mCoverClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (HeaderPostListFragment.this.isMe()) {
                int width = HeaderPostListFragment.this.getActivity().getWindow().getDecorView().getWidth();
                HeaderPostListFragment.this.mPickImageHelper.m7719a(4097, HeaderPostListFragment.this.getString(R.string.userinfo_change_profile_cover_image), width, width);
            }
        }
    };
    private ImageView mImageAvatar;
    private ImageView mImageCover;
    private String mLocalAvatarImagePath;
    private String mLocalCoverImagePath;
    private ViewStub mLoginStub;
    private PickImageHelper mPickImageHelper;
    private PullToRefreshListView mPullToRefreshListView;
    private TextView mTextNickName;
    private TTPodUser mUser;

    protected abstract View onLoadHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("user");
            if (serializable instanceof TTPodUser) {
                this.mUser = (TTPodUser) serializable;
            }
        }
        this.mPickImageHelper = new PickImageHelper(getActivity());
        if (bundle != null) {
            this.mPickImageHelper.m7709b(bundle);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            this.mPickImageHelper.m7715a(bundle);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mCachedRequestCode = bundle.getInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            this.mPickImageHelper.m7709b(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUser(TTPodUser tTPodUser) {
        this.mUser = tTPodUser;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_post_list, (ViewGroup) null);
        this.mPullToRefreshListView = (PullToRefreshListView) inflate.findViewById(R.id.list_content);
        this.mPullToRefreshListView.setOnPullRefreshListener(this);
        initView(layoutInflater, this.mPullToRefreshListView);
        tryToShowLoginView(inflate);
        return inflate;
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mLoginStub = null;
        this.mPullToRefreshListView = null;
        this.mTextNickName = null;
        this.mImageCover = null;
        this.mImageAvatar = null;
    }

    private void tryToShowLoginView(View view) {
        TTPodUser m2954aq = Preferences.m2954aq();
        this.mLoginStub = (ViewStub) view.findViewById(R.id.social_login_stub);
        if (m2954aq == null) {
            this.mLoginStub.inflate().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    HeaderPostListFragment.this.startActivity(new Intent(HeaderPostListFragment.this.getActivity(), LoginActivity.class));
                }
            });
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    protected View onCreateHeaderView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View onLoadHeaderView = onLoadHeaderView(layoutInflater, viewGroup);
        this.mImageAvatar = (ImageView) onLoadHeaderView.findViewById(R.id.image_avatar);
        this.mImageCover = (ImageView) onLoadHeaderView.findViewById(R.id.image_profile_cover);
        this.mTextNickName = (TextView) onLoadHeaderView.findViewById(R.id.text_profile_nick_name);
        return onLoadHeaderView;
    }

    private void requestUserInfo() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null) {
            final Request<TTPodUserResult> m8955a = UserSystemAPI.m8955a(m2954aq.getAccessToken(), this.mUser.getUserId());
            m8955a.m8544a(new RequestCallback<TTPodUserResult>() { // from class: com.sds.android.ttpod.fragment.musiccircle.HeaderPostListFragment.2
                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: a */
                public void onRequestSuccess(TTPodUserResult tTPodUserResult) {
                    TTPodUser data;
                    if (HeaderPostListFragment.this.getActivity() != null && (data = tTPodUserResult.getData()) != null) {
                        if (TextUtils.isEmpty(HeaderPostListFragment.this.mUser.getAvatarUrl())) {
                            HeaderPostListFragment.this.mUser.setAvatarUrl(data.getAvatarUrl());
                            HeaderPostListFragment.this.bindAvatar(HeaderPostListFragment.this.mUser.getAvatarUrl());
                        }
                        if (TextUtils.isEmpty(HeaderPostListFragment.this.mUser.getProfileCoverUrl())) {
                            HeaderPostListFragment.this.mUser.setProfileCoverUrl(data.getProfileCoverUrl());
                            HeaderPostListFragment.this.bindCover(HeaderPostListFragment.this.mUser.getProfileCoverUrl());
                        }
                        HeaderPostListFragment.this.mUser.setFollowingsCount(data.getFollowingsCount());
                        HeaderPostListFragment.this.mUser.setFollowersCount(data.getFollowersCount());
                        HeaderPostListFragment.this.onRequestUserInfoFinished(data);
                    }
                }

                @Override // com.sds.android.sdk.lib.request.RequestCallback
                /* renamed from: b */
                public void onRequestFailure(TTPodUserResult tTPodUserResult) {
                    //ErrorStatistic.m5232g(m8955a.m8532e());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRequestUserInfoFinished(TTPodUser tTPodUser) {
    }

    protected void bindAvatar(String str) {
        if (this.mImageAvatar != null) {
            ImageCacheUtils.m4750a(this.mImageAvatar, str, (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height), this.mLocalAvatarImagePath);
        }
    }

    protected void bindCover(String str) {
        if (this.mImageCover != null) {
            ImageCacheUtils.m4750a(this.mImageCover, str, DisplayUtils.getWidth(), (int) getResources().getDimension(R.dimen.cover_height), this.mLocalCoverImagePath);
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        onBindView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onBindView() {
        if (this.mUser != null) {
            refreshUserInfo();
            requestUserInfo();
            this.mImageCover.setOnClickListener(this.mCoverClickListener);
        }
    }

    public TTPodUser getUser() {
        return this.mUser;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isMe() {
        return (this.mUser == null || Preferences.m2954aq() == null || this.mUser.getUserId() != Preferences.m2954aq().getUserId()) ? false : true;
    }

    private void initCoverLocalPath() {
        this.mLocalCoverImagePath = String.format(TTPodConfig.getCacheTmpPath() + File.separator + ".%d.cover.jpg", Long.valueOf(this.mUser.getUserId() + (this.mUser.getProfileCoverUrl() == null ? 0 : this.mUser.getProfileCoverUrl().hashCode())));
    }

    private void initAvatarLocalPath() {
        this.mLocalAvatarImagePath = String.format(TTPodConfig.getCacheTmpPath() + File.separator + ".%d.avatar.jpg", Long.valueOf(this.mUser.getUserId() + (this.mUser.getAvatarUrl() == null ? 0 : this.mUser.getAvatarUrl().hashCode())));
    }

    private void cropPhoto(int i, Intent intent) {
        if (i == 4097) {
            if (StringUtils.isEmpty(this.mLocalCoverImagePath)) {
                initCoverLocalPath();
            }
            this.mPickImageHelper.m7717a(intent == null ? null : intent.getData(), this.mLocalCoverImagePath);
        }
    }

    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListFragment
    public void onLoginFinished(CommonResult commonResult) {
        super.onLoginFinished(commonResult);
        if (Preferences.m2954aq() != null && this.mLoginStub != null) {
            this.mLoginStub.setVisibility(View.GONE);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 3:
                    if (this.mCachedRequestCode == 4097) {
                        bindCover(this.mLocalCoverImagePath);
                        CommandCenter.getInstance().execute(new Command(CommandID.MODIFY_COVER, this.mLocalCoverImagePath, Integer.valueOf(DisplayUtils.getWidth()), Integer.valueOf((int) getResources().getDimension(R.dimen.cover_height))));
                        return;
                    }
                    return;
                case 4097:
                    cropPhoto(i, intent);
                    this.mCachedRequestCode = i;
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ImageView getAvatar() {
        return this.mImageAvatar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TextView getNickName() {
        return this.mTextNickName;
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2284a
    public void onPullToRefresh(View view) {
        refresh();
    }

    @Override // com.sds.android.ttpod.widget.PullToRefreshHelper.InterfaceC2284a
    public void onPullStateChanged(View view, int i) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.musiccircle.PostListByIdFragment, com.sds.android.ttpod.fragment.musiccircle.PostListFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.MODIFY_COVER_FINISHED, ReflectUtils.loadMethod(cls, "onModifyCoverFinished", CommonResult.class));
        map.put(CommandID.MODIFY_AVATAR_FINISHED, ReflectUtils.loadMethod(cls, "onModifyAvatarFinished", CommonResult.class));
        map.put(CommandID.REFRESH_INFORMATION_FINISHED, ReflectUtils.loadMethod(cls, "onRefreshUserInfo", new Class[0]));
    }

    public void onRefreshUserInfo() {
        TTPodUser m2954aq = Preferences.m2954aq();
        if (m2954aq != null && this.mUser != null && this.mUser.getUserId() == m2954aq.getUserId()) {
            this.mUser = m2954aq;
            refreshUserInfo();
        }
    }

    private void refreshUserInfo() {
        initCoverLocalPath();
        initAvatarLocalPath();
        this.mTextNickName.setText(this.mUser.getNickName());
        this.mTextNickName.setVisibility(View.VISIBLE);
        bindAvatar(this.mUser.getAvatarUrl());
        bindCover(this.mUser.getProfileCoverUrl());
    }

    public void onModifyCoverFinished(CommonResult commonResult) {
        bindCover(this.mUser.getProfileCoverUrl());
    }

    public void onModifyAvatarFinished(CommonResult commonResult) {
        bindAvatar(this.mUser.getAvatarUrl());
    }
}
