package com.sds.android.ttpod.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.p129b.SSkinInfo;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class SkinInfoActivity extends ActionBarActivity {
    private AlertDialog mAlertDialog;
    private Button mApplyButton;
    private TextView mAuthor;
    private Button mCancelButton;
    private File mDesFile;
    private String mDesSkinPath;
    private TextView mEMail;
    private TextView mName;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.SkinInfoActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == SkinInfoActivity.this.mApplyButton) {
                SkinInfoActivity.this.applySkinFileAsTTpodSkin();
            } else if (view == SkinInfoActivity.this.mCancelButton) {
                SkinInfoActivity.this.finish();
            }
        }
    };
    private SSkinInfo mSkinInfo;
    private String mSrcSkinPath;
    private TextView mVersion;
    private TextView mWebPage;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_skin_info_layout);
        this.mName = (TextView) findViewById(R.id.skininfo_name);
        this.mAuthor = (TextView) findViewById(R.id.skininfo_author);
        this.mVersion = (TextView) findViewById(R.id.skininfo_version);
        this.mEMail = (TextView) findViewById(R.id.skininfo_mail);
        this.mWebPage = (TextView) findViewById(R.id.skininfo_page);
        this.mApplyButton = (Button) findViewById(R.id.skininfo_confirm);
        this.mCancelButton = (Button) findViewById(R.id.skininfo_cancel);
        setupDefaultDisplayInfo();
        this.mApplyButton.setClickable(false);
        this.mApplyButton.setOnClickListener(this.mOnClickListener);
        this.mCancelButton.setOnClickListener(this.mOnClickListener);
        loadSkin();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        dismissWaitingDialog();
        finish();
    }

    protected void loadSkin() {
        this.mSrcSkinPath = parseSkinPath();
        CommandCenter.m4607a().m4606a(new Command(CommandID.LOAD_SKIN_WITH_PATH, SkinUtils.m4646a(this.mSrcSkinPath, 0)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.LOAD_SKIN_WITH_PATH_FINISHED, ReflectUtils.m8375a(getClass(), "onLoadSkinFinished", SkinCache.class));
    }

    public void onLoadSkinFinished(SkinCache skinCache) {
        this.mSkinInfo = skinCache.m3582f();
        if (this.mSkinInfo != null) {
            this.mName.setText(this.mSkinInfo.m3778d());
            this.mAuthor.setText(getLabelText(R.string.author, this.mSkinInfo.m3781a()));
            this.mVersion.setText(getLabelText(R.string.version, this.mSkinInfo.m3780b()));
            this.mEMail.setText(getLabelTextSpanable(R.string.email, this.mSkinInfo.m3777e()));
            this.mWebPage.setText(getLabelTextSpanable(R.string.website, this.mSkinInfo.m3776f()));
            this.mApplyButton.setClickable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWaitingDialog() {
        PopupsUtils.m6745a((Context) this, (int) R.string.loading_theme, true);
    }

    private void dismissWaitingDialog() {
        PopupsUtils.m6761a();
    }

    private void setupDefaultDisplayInfo() {
        this.mAuthor.setText(getLabelText(R.string.author, "TTPod"));
        this.mVersion.setText(getLabelText(R.string.version, "2.0"));
        this.mEMail.setText(getLabelTextSpanable(R.string.email, "support@ttpod.com"));
        this.mWebPage.setText(getLabelTextSpanable(R.string.website, "www.ttpod.com"));
    }

    private String getLabelText(int i, String str) {
        return ((Object) getText(i)) + " : " + str;
    }

    private SpannableStringBuilder getLabelTextSpanable(int i, String str) {
        String str2 = ((Object) getText(i)) + " : " + str;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
        C0673a c0673a = new C0673a(str);
        int length = str2.length();
        spannableStringBuilder.setSpan(c0673a, length - str.length(), length, 34);
        return spannableStringBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openPlayerActivity() {
        savePreference();
        startActivity(new Intent(this, MainActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyWithoutOverwriteExistFile(String str) {
        this.mDesSkinPath = getUniqueSkinFilePath(str);
        this.mDesFile = new File(this.mDesSkinPath);
        FileUtils.m8406f(FileUtils.m8400l(this.mDesSkinPath));
        FileUtils.m8413b(this.mDesSkinPath, this.mSrcSkinPath);
    }

    private String getUniqueSkinFilePath(String str) {
        int i = 0;
        String m8400l = FileUtils.m8400l(str);
        String m8401k = FileUtils.m8401k(str);
        String m8399m = FileUtils.m8399m(str);
        File file = new File(str);
        while (file.exists()) {
            i++;
            str = m8400l + File.separator + m8401k + "_" + i + "." + m8399m;
            file = new File(str);
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copyWithOverwriteExistFile(String str) {
        this.mDesSkinPath = str;
        this.mDesFile = new File(str);
        FileUtils.m8406f(FileUtils.m8400l(this.mDesSkinPath));
        FileUtils.m8413b(this.mDesSkinPath, this.mSrcSkinPath);
    }

    private void savePreference() {
        CommandCenter.m4607a().m4606a(new Command(CommandID.SET_SKIN, this.mDesSkinPath, 0));
    }

    private String parseSkinPath() {
        Intent intent = getIntent();
        String decode = Uri.decode(intent.getDataString());
        if (decode == null || !decode.toLowerCase().endsWith(".tsk")) {
            return null;
        }
        return decode.substring((intent.getScheme() + "://").length());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applySkinFileAsTTpodSkin() {
        String m8402j = FileUtils.m8402j(this.mSrcSkinPath);
        String m8400l = FileUtils.m8400l(this.mSrcSkinPath);
        String m5294n = TTPodConfig.m5294n();
        final String str = m5294n + File.separator + m8402j;
        this.mDesFile = new File(str);
        if (m5294n.startsWith("/mnt") && !m8400l.startsWith("/mnt")) {
            m8400l = "/mnt" + m8400l;
        }
        if (!m5294n.equals(m8400l)) {
            if (this.mDesFile.exists()) {
                MessageDialog messageDialog = new MessageDialog(this, (int) R.string.theme_overwrite_choose, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.activities.SkinInfoActivity.1
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(MessageDialog messageDialog2) {
                        SkinInfoActivity.this.showWaitingDialog();
                        SkinInfoActivity.this.copyWithOverwriteExistFile(str);
                        SkinInfoActivity.this.openPlayerActivity();
                    }
                }, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.activities.SkinInfoActivity.2
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void mo2038a(MessageDialog messageDialog2) {
                        SkinInfoActivity.this.showWaitingDialog();
                        SkinInfoActivity.this.copyWithoutOverwriteExistFile(str);
                        SkinInfoActivity.this.openPlayerActivity();
                    }
                });
                messageDialog.setTitle(R.string.prompt_title);
                messageDialog.show();
                return;
            }
            showWaitingDialog();
            copyWithOverwriteExistFile(str);
            openPlayerActivity();
            return;
        }
        showWaitingDialog();
        this.mDesSkinPath = m8400l + File.separator + m8402j;
        openPlayerActivity();
    }

    /* renamed from: com.sds.android.ttpod.activities.SkinInfoActivity$a */
    /* loaded from: classes.dex */
    public class C0673a extends ClickableSpan {

        /* renamed from: b */
        private String f2567b;

        public C0673a(String str) {
            this.f2567b = str;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.f2567b));
            try {
                SkinInfoActivity.this.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                PopupsUtils.m6760a((int) R.string.no_program);
            }
        }
    }
}
