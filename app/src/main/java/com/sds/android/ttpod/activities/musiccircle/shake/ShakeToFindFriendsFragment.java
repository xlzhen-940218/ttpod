package com.sds.android.ttpod.activities.musiccircle.shake;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AroundTTPodUser;
import com.sds.android.cloudapi.ttpod.result.AroundUserListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.activities.musiccircle.shake.ShakeController;
import com.sds.android.ttpod.activities.musiccircle.shake.Shaker;
import com.sds.android.ttpod.activities.user.ResultUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ShakeToFindFriendsFragment extends SlidingClosableFragment implements ShakeController.InterfaceC0848a, Shaker.InterfaceC0849a {
    private static final long SHAKE_INTERVAL = 500;
    private static final String TAG = "ShakeToFindFriendsFragment";
    private static final int TEXT_FONT_SIZE = 18;
    private static final long VIBRATE_DURATION = 200;
    private TextView mHint;
    private boolean mIsResumed = false;
    private BDLBSManager mLBSManager;
    private long mResumeTime;
    private ShakeController mShakeController;
    private Shaker mShaker;

    /* renamed from: com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0845a {
        /* renamed from: a */
        void mo7845a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_shake_layout, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        getActionBarController().m7189b(R.string.musiccircle_shake_title);
        this.mHint = (TextView) view.findViewById(R.id.shake_hint);
        this.mHint.setVisibility(View.VISIBLE);
        this.mHint.setText(R.string.shake_hint);
        this.mHint.setTextSize(18.0f);
        this.mShakeController = new ShakeController(getActivity(), view, this);
        this.mShaker = new Shaker(getActivity(), this);
        try {
            this.mLBSManager = new BDLBSManager(getActivity());
            this.mLBSManager.m7840b();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MusicCircleStatistic.m7964k();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        resumeShakeFriends();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeShakeFriends() {
        this.mIsResumed = true;
        this.mResumeTime = System.currentTimeMillis();
        this.mHint.setVisibility(View.VISIBLE);
        this.mHint.setText(R.string.shake_hint);
        getActivity().getWindow().getDecorView().setKeepScreenOn(true);
        this.mShaker.m7830a();
        this.mShakeController.m7836a();
        this.mLBSManager.m7839c();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        pauseShakeFriends();
    }

    private void pauseShakeFriends() {
        this.mIsResumed = false;
        getActivity().getWindow().getDecorView().setKeepScreenOn(false);
        this.mShaker.m7827b();
        this.mLBSManager.m7838d();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
        MusicCircleStatistic.m7962m();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mShaker.m7827b();
        this.mLBSManager.m7837e();
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.shake.ShakeController.InterfaceC0848a
    public void onSearchFail() {
        this.mHint.setVisibility(View.VISIBLE);
        this.mHint.setText(R.string.shake_error_hint);
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.shake.ShakeController.InterfaceC0848a
    public void onSearchSuccess(List<AroundTTPodUser> list) {
        this.mHint.setVisibility(View.INVISIBLE);
        if (this.mIsResumed && list != null) {
            Bundle bundle = new Bundle();
            int size = list.size();
            long[] jArr = new long[size];
            for (int i = 0; i < size; i++) {
                jArr[i] = list.get(i).getUserId();
            }
            bundle.putLongArray(ShakeToFindFriendsResultFragment.EXTRA_USER_IDS, jArr);
            for (AroundTTPodUser aroundTTPodUser : list) {
                bundle.putBundle(String.valueOf(aroundTTPodUser.getUserId()), ShakeToFindFriendsResultFragment.convertAroundUserToBundle(aroundTTPodUser));
            }
            ShakeToFindFriendsResultFragment shakeToFindFriendsResultFragment = new ShakeToFindFriendsResultFragment();
            shakeToFindFriendsResultFragment.setResumeShakeFriendCallback(new InterfaceC0845a() { // from class: com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsFragment.1
                @Override // com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsFragment.InterfaceC0845a
                /* renamed from: a */
                public void mo7845a() {
                    ShakeToFindFriendsFragment.this.resumeShakeFriends();
                }
            });
            shakeToFindFriendsResultFragment.setArguments(bundle);
            launchFragment(shakeToFindFriendsResultFragment);
            pauseShakeFriends();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SHAKE_RESULT, ReflectUtils.m8375a(ShakeToFindFriendsFragment.class, "onUpdateShakeResult", AroundUserListResult.class, String.class));
    }

    public void onUpdateShakeResult(AroundUserListResult aroundUserListResult, String str) {
        if ("shake".equals(str)) {
            ArrayList<AroundTTPodUser> dataList = aroundUserListResult.getDataList();
            if (dataList.isEmpty()) {
                ResultUtils.m7706a(getActivity(), aroundUserListResult);
            } else {
                this.mShakeController.m7834a(dataList);
            }
        }
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.shake.ShakeController.InterfaceC0848a
    public void onStartSearchAnimationStart(List<AroundTTPodUser> list) {
        this.mHint.setVisibility(View.VISIBLE);
        this.mHint.setText(R.string.be_searching);
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.shake.ShakeController.InterfaceC0848a
    public void onStartSearchAnimationFinished(List<AroundTTPodUser> list) {
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.shake.ShakeController.InterfaceC0848a
    public void onEndSearchAnimationStart(List<AroundTTPodUser> list) {
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.shake.ShakeController.InterfaceC0848a
    public void onEndSearchAnimationFinished(List<AroundTTPodUser> list) {
        if (list != null) {
            this.mHint.setVisibility(View.VISIBLE);
            this.mHint.setText(R.string.shake_hint);
        }
    }

    private void vibrate(long j) {
        ((Vibrator) getActivity().getSystemService("vibrator")).vibrate(j);
    }

    @Override // com.sds.android.ttpod.activities.musiccircle.shake.Shaker.InterfaceC0849a
    public void onShake() {
        if (!EnvironmentUtils.C0604c.m8474e()) {
            this.mShakeController.m7832c();
            this.mHint.setVisibility(View.VISIBLE);
            this.mHint.setText(R.string.shake_error_hint);
        } else if (System.currentTimeMillis() - this.mResumeTime > SHAKE_INTERVAL && !this.mShakeController.m7833b()) {
            LogUtils.m8379d(TAG, "onShake");
            vibrate(VIBRATE_DURATION);
            this.mShakeController.m7835a(this.mLBSManager.m7842a());
            MusicCircleStatistic.m7963l();
        }
    }
}
