package com.sds.android.ttpod.fragment.settings.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.p060b.BaseResultRest;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class FeedbackComposeFragment extends BaseFragment implements View.OnClickListener {
    private static final int FEEDBACK_CONTACTWAY_LENGTH_LIMIT = 40;
    private static final int FEEDBACK_CONTENT_LENGTH_LIMIT = 5;
    private static final int REQUEST_FILE = 2;
    private static final int REQUEST_PICTURE = 1;
    private Button mBtnSend;
    private EditText mEtContactWay;
    private EditText mEtContent;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_feedback_compose, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.PROPOSAL_FEEDBACK_FINISH, ReflectUtils.m8375a(getClass(), "onProposalFinished", BaseResultRest.class, FeedbackItem.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEtContent = (EditText) view.findViewById(R.id.et_feedback);
        this.mEtContactWay = (EditText) view.findViewById(R.id.et_contactway);
        this.mBtnSend = (Button) view.findViewById(R.id.btn_send_feedback);
        this.mBtnSend.setOnClickListener(this);
    }

    public void onProposalFinished(BaseResultRest baseResultRest, FeedbackItem feedbackItem) {
        if (baseResultRest.m8678c() == 201) {
            PopupsUtils.m6760a((int) R.string.feedback_send_complete);
            this.mEtContent.setText("");
        } else {
            PopupsUtils.m6760a((int) R.string.feedback_send_fail);
        }
        this.mBtnSend.setText(R.string.submit_feedback);
        this.mBtnSend.setClickable(true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_feedback /* 2131231502 */:
                if (!EnvironmentUtils.C0604c.m8474e()) {
                    PopupsUtils.m6760a((int) R.string.network_error);
                    return;
                }
                String trim = this.mEtContent.getText().toString().trim();
                String trim2 = this.mEtContactWay.getText().toString().trim();
                if (trim.length() <= 5) {
                    PopupsUtils.m6760a((int) R.string.send_feedback_content_too_short);
                    return;
                } else if (trim2.length() > FEEDBACK_CONTACTWAY_LENGTH_LIMIT) {
                    PopupsUtils.m6760a((int) R.string.send_feedback_contactway_too_long);
                    return;
                } else {
                    this.mBtnSend.setClickable(false);
                    this.mBtnSend.setText(R.string.feedback_sending);
                    CommandCenter.getInstance().m4596b(new Command(CommandID.PROPOSAL_FEEDBACK, new FeedbackItem(trim, EnvironmentUtils.C0604c.m8473f().toString(), trim2)));
                    return;
                }
            default:
                return;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
