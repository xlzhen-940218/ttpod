package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.emoticons.EmoticonsLayout;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.utils.SensitiveWordUtils;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class BaseInputActivity extends SlidingClosableActivity {
    private static final int MAX_INPUT_SIZE = 140;
    private static final int TIME_DELAY_HIED = 100;
    private ImageView mAvatar;
    private EditText mContent;
    private ImageButton mEmoctionEntry;
    private EmoticonsLayout mEmoticonsLayout;
    private InputMethodManager mInputMgr;
    private TextView mInputSize;
    private boolean mIsSending;
    private TextView mNickName;
    private ActionBarController.C1070a mSendMessageAction;
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;
    private TextView mTweet;
    private View.OnClickListener mEmoctionEntryClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.BaseInputActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (BaseInputActivity.this.mEmoticonsLayout.getVisibility() == 8) {
                BaseInputActivity.this.mInputMgr.hideSoftInputFromWindow(BaseInputActivity.this.mContent.getWindowToken(), 0);
                BaseInputActivity.this.mContent.clearFocus();
                BaseInputActivity.this.mEmoctionEntry.setImageResource(R.drawable.img_musiccircle_post_detail_softinput);
                BaseInputActivity.this.mEmoctionEntry.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.musiccircle.BaseInputActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseInputActivity.this.mEmoticonsLayout.setVisibility(View.VISIBLE);
                        BaseInputActivity.this.mSlidingClosableRelativeLayout.m1528a(BaseInputActivity.this.mEmoticonsLayout.getRectEmoticons());
                    }
                }, 100L);
                return;
            }
            BaseInputActivity.this.mEmoctionEntry.setImageResource(R.drawable.img_musiccircle_post_detail_expression);
            BaseInputActivity.this.mEmoticonsLayout.setVisibility(View.GONE);
            BaseInputActivity.this.mSlidingClosableRelativeLayout.m1516b(BaseInputActivity.this.mEmoticonsLayout.getRectEmoticons());
            BaseInputActivity.this.mContent.requestFocus();
            BaseInputActivity.this.mInputMgr.showSoftInput(BaseInputActivity.this.mContent, 2);
        }
    };
    private TextWatcher mTextWatcher = new TextWatcher() { // from class: com.sds.android.ttpod.activities.musiccircle.BaseInputActivity.4
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            int m6675a = EmoticonsLayout.m6675a(editable.toString());
            int i = (m6675a / 2) + (m6675a % 2);
            if (i <= BaseInputActivity.MAX_INPUT_SIZE) {
                BaseInputActivity.this.mInputSize.setText(String.valueOf(140 - i));
            } else if (i > BaseInputActivity.MAX_INPUT_SIZE && i <= 142) {
                editable.delete(BaseInputActivity.this.mContent.getSelectionStart() - 1, BaseInputActivity.this.mContent.getSelectionEnd());
            } else {
                editable.delete(BaseInputActivity.MAX_INPUT_SIZE, i);
            }
        }
    };

    protected abstract String onLoadPicUrl();

    protected abstract String onLoadTweet();

    protected abstract TTPodUser onLoadUser();

    protected abstract void onSend(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.musiccircle_repost_input);
        this.mInputMgr = (InputMethodManager) getSystemService("input_method");
        onNewIntent(getIntent());
        this.mContent = (EditText) findViewById(R.id.et_repost_content);
        this.mAvatar = (ImageView) findViewById(R.id.user_image);
        this.mNickName = (TextView) findViewById(R.id.user_name);
        this.mTweet = (TextView) findViewById(R.id.user_info);
        this.mEmoctionEntry = (ImageButton) findViewById(R.id.btn_emoctions);
        this.mInputSize = (TextView) findViewById(R.id.text_input_size);
        this.mEmoticonsLayout = (EmoticonsLayout) findViewById(R.id.layout_emoticons);
        this.mSlidingClosableRelativeLayout = getSlidingContainer();
        initView();
        this.mSendMessageAction = getActionBarController().m7178d(R.drawable.img_musiccircle_send_message);
        this.mSendMessageAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.musiccircle.BaseInputActivity.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                if (!BaseInputActivity.this.mIsSending) {
                    String trim = BaseInputActivity.this.mContent.getText().toString().trim();
                    if (!SensitiveWordUtils.m8243a(BaseInputActivity.this).m8242a(trim)) {
                        BaseInputActivity.this.mIsSending = true;
                        BaseInputActivity.this.startSendingAnimation();
                        BaseInputActivity.this.onSend(trim);
                        return;
                    }
                    PopupsUtils.m6721a("内容含有敏感词，提交失败");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mEmoticonsLayout.getVisibility() == 8) {
            this.mContent.requestFocus();
            this.mInputMgr.showSoftInput(this.mContent, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSendingAnimation() {
        this.mSendMessageAction.m7152e();
    }

    private void stopSendingAnimation() {
        this.mSendMessageAction.m7149g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSendFinished() {
        stopSendingAnimation();
        this.mIsSending = false;
        this.mInputMgr.hideSoftInputFromWindow(this.mContent.getWindowToken(), 0);
        PopupsUtils.m6721a("发送成功");
        this.mContent.setText("");
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSendFail() {
        this.mIsSending = false;
        stopSendingAnimation();
    }

    public static String getFirstPicInPost(Post post) {
        ArrayList<String> picList = post.getPicList();
        if (picList == null || picList.isEmpty()) {
            return null;
        }
        return picList.get(0);
    }

    private void initView() {
        String onLoadPicUrl = onLoadPicUrl();
        if (!TextUtils.isEmpty(onLoadPicUrl)) {
            int m7229a = DisplayUtils.m7229a(84);
            ImageCacheUtils.m4752a(this.mAvatar, onLoadPicUrl, m7229a, m7229a, (int) R.drawable.img_avatar_default);
        }
        TTPodUser onLoadUser = onLoadUser();
        if (onLoadUser != null) {
            this.mNickName.setText(onLoadUser.getNickName());
            this.mNickName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, onLoadUser.isVerified() ? R.drawable.img_user_v : 0);
        }
        this.mTweet.setText(onLoadTweet());
        this.mContent.addTextChangedListener(this.mTextWatcher);
        this.mEmoctionEntry.setOnClickListener(this.mEmoctionEntryClickListener);
        this.mEmoticonsLayout.setInputEditText(this.mContent);
        this.mEmoticonsLayout.setVisibility(View.GONE);
        this.mEmoticonsLayout.setmMaxLength(MAX_INPUT_SIZE);
        this.mEmoticonsLayout.m6676a(this.mSlidingClosableRelativeLayout);
        this.mContent.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.activities.musiccircle.BaseInputActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BaseInputActivity.this.mEmoctionEntry.setImageResource(R.drawable.img_musiccircle_post_detail_expression);
                BaseInputActivity.this.mEmoticonsLayout.setVisibility(View.GONE);
                BaseInputActivity.this.mSlidingClosableRelativeLayout.m1516b(BaseInputActivity.this.mEmoticonsLayout.getRectEmoticons());
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mInputMgr.hideSoftInputFromWindow(this.mContent.getWindowToken(), 0);
    }
}
