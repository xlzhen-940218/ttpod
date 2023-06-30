package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Handler;
import android.os.Message;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;


import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p078b.CmMusicSdkInitCode;
import com.sds.android.ttpod.cmmusic.p081e.CMMusicUtils;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.Hashtable;

/* loaded from: classes.dex */
public abstract class BaseMusicFragment extends BaseFragment implements AbsListView.OnScrollListener {
    private Thread mDataThread;
    private ListView mListView;
    private boolean mIsNull = false;
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (BaseMusicFragment.this.isViewAccessAble()) {
                switch (message.what) {
                    case 1:
                        BaseMusicFragment.this.onDataChange();
                        BaseMusicFragment.this.mIsNull = false;
                        return;
                    case 14:
                        String str = (String) ((Hashtable) message.obj).get("code");
                        if ("3".equals(str)) {
                            Toast.makeText(BaseMusicFragment.this.getActivity(), BaseMusicFragment.this.getString(R.string.cardisnotcmcc), Toast.LENGTH_SHORT).show();
                            return;
                        } else if ("2".equals(str)) {
                            Toast.makeText(BaseMusicFragment.this.getActivity(), BaseMusicFragment.this.getString(R.string.pleasechecknetwork), Toast.LENGTH_SHORT).show();
                            return;
                        } else if (CMMusicUtils.m7276a() && !"0".equals(str)) {
                            CmMusicSdkInitCode.m7343a(BaseMusicFragment.this.getActivity());
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    };

    protected abstract void onDataChange();

    protected abstract void onScrollAddData(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public void setListView(ListView listView) {
        this.mListView = listView;
        this.mListView.setOnScrollListener(this);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (!this.mIsNull && i == 0 && absListView.getLastVisiblePosition() == absListView.getCount() - 1) {
            if (this.mDataThread == null || this.mDataThread.isAlive()) {
                this.mDataThread = new Thread() { // from class: com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        if (BaseMusicFragment.this.isViewAccessAble()) {
                            if (BaseMusicFragment.this.mListView.getCount() > 0) {
                                BaseMusicFragment.this.mIsNull = true;
                                BaseMusicFragment.this.onScrollAddData(BaseMusicFragment.this.mListView.getCount());
                            }
                            BaseMusicFragment.this.refresh();
                        }
                    }
                };
                this.mDataThread.start();
                this.mDataThread = null;
            }
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refresh() {
        if (isViewAccessAble()) {
            Message message = new Message();
            message.what = 1;
            this.mHandler.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sdkInitCheck() {
        /*if (!InitCmmInterface.m10388b(getActivity())) {
            TaskScheduler.m8581a(new Runnable() { // from class: com.sds.android.ttpod.cmmusic.fragment.BaseMusicFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    Hashtable<String, String> m10389a = InitCmmInterface.m10389a(BaseMusicFragment.this.getActivity());
                    if (BaseMusicFragment.this.isViewAccessAble()) {
                        Message message = new Message();
                        message.what = 14;
                        message.obj = m10389a;
                        BaseMusicFragment.this.mHandler.sendMessage(message);
                    }
                }
            });
        }*/
    }
}
