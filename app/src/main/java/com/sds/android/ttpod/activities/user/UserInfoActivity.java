package com.sds.android.ttpod.activities.user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.CommonResult;
import com.sds.android.ttpod.framework.base.ErrCode;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.SensitiveWordUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Map;

/* loaded from: classes.dex */
public class UserInfoActivity extends SlidingClosableActivity {
    public static final String BUNDLEKEY_LOGOUT_VISIBLE = "logout_visible";
    private static final int DEFAULT_END_YEAR = 2100;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final String EMAIL_BIND_STATE = "1";
    private static final String KEY_REQUEST_CODE = "request_code";
    public static final String LOCAL_AVATAR_IMAGE_PATH_FORMAT = TTPodConfig.m5298j() + File.separator + ".%d.avatar.jpg";
    public static final String LOCAL_COVER_IMAGE_PATH_FORMAT = TTPodConfig.m5298j() + File.separator + ".%d.cover.jpg";
    private static final int MAX_MONTH = 12;
    private static final int MAX_MONTH_DATA = 31;
    private static final int REQUEST_IMAGE_AVATAR = 2;
    private static final int REQUEST_IMAGE_COVER = 1;
    private static final String TAG = "UserInfoActivity";
    private static final String UNBIND_EMAIL_STATE = "0";
    private int mCachedRequestCode;
    private ImageView mImageAvatar;
    private ImageView mImageProfileCover;
    private String mLocalAvatarImagePath;
    private String mLocalCoverImagePath;
    private TTPodUser mOriginUser;
    private PickImageHelper mPickImageHelper;
    private RadioGroup mRadioGroupSex;
    private TextView mTextBirthday;
    private TextView mTextConstellation;
    private TextView mTextEmail;
    private TextView mTextEmailState;
    private TextView mTextNickName;
    private TextView mTextPassword;
    private TextView mTextPasswordState;
    private TTPodUser mUser;
    private View mVipActionView;
    private boolean mIsLogoutVisible = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.user.UserInfoActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_userinfo_cover /* 2131231024 */:
                    UserInfoActivity.this.onClickProfileCover();
                    return;
                case R.id.imageview_userinfo_avatar /* 2131231025 */:
                    UserInfoActivity.this.onClickAvatar();
                    return;
                case R.id.linearlayout_userinfo_nickname /* 2131231026 */:
                    UserInfoActivity.this.onClickNickName();
                    return;
                case R.id.linearlayout_userinfo_sex /* 2131231028 */:
                    UserInfoActivity.this.onClickSex();
                    return;
                case R.id.linearlayout_userinfo_birthday /* 2131231032 */:
                    UserInfoActivity.this.onClickBirthday();
                    return;
                case R.id.linearlayout_userinfo_vip /* 2131231035 */:
                    UserInfoActivity.this.onClickVip();
                    return;
                case R.id.linearlayout_userinfo_email /* 2131231039 */:
                    UserInfoActivity.this.onClickEmail();
                    return;
                case R.id.linearlayout_userinfo_password /* 2131231042 */:
                    UserInfoActivity.this.onClickPassword();
                    return;
                case R.id.button_userinfo_logout /* 2131231045 */:
                    UserInfoActivity.this.onClickLogout();
                    return;
                default:
                    return;
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.sds.android.ttpod.activities.user.UserInfoActivity.2
        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int i2;
            if (i == R.id.radiobutton_userinfo_sex_male) {
                i2 = R.drawable.xml_background_radiobutton_userinfo_sex_male;
                UserInfoActivity.this.mUser.setSex(1);
            } else {
                i2 = R.drawable.xml_background_radiobutton_userinfo_sex_female;
                UserInfoActivity.this.mUser.setSex(0);
            }
            UserInfoActivity.this.mTextConstellation.setBackgroundResource(i2);
        }
    };
    private DatePickerDialog.OnDateSetListener mOnDateSetListener = new DatePickerDialog.OnDateSetListener() { // from class: com.sds.android.ttpod.activities.user.UserInfoActivity.3
        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            BirthdayDate birthdayDate = new BirthdayDate(i, i2, i3);
            if (!new BirthdayDate(UserInfoActivity.this.mUser.getBirthdayInSecond()).equals(birthdayDate)) {
                UserInfoActivity.this.mUser.setBirthday(birthdayDate.m7724d());
                UserInfoActivity.this.setBirthdayAndConstellation(birthdayDate);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPickImageHelper = new PickImageHelper(this);
        this.mOriginUser = Preferences.m2954aq();
        this.mUser = Preferences.m2954aq();
        if (this.mOriginUser == null || this.mUser == null) {
            finish();
            return;
        }
        LogUtils.m8388a(TAG, "user id = " + this.mUser.getUserId() + ", avatar=" + this.mUser.getAvatarUrl() + ",cover=" + this.mUser.getProfileCoverUrl());
        this.mLocalAvatarImagePath = buildLocalAvatarPath(this.mUser);
        LogUtils.m8388a(TAG, "mLocalAvatarImagePath=" + this.mLocalAvatarImagePath);
        this.mLocalCoverImagePath = buildLocalCoverPath(this.mUser);
        LogUtils.m8388a(TAG, "mLocalCoverImagePath=" + this.mLocalCoverImagePath);
        setTitle(R.string.userinfo_my_profile);
        setContentView(R.layout.activity_userinfo);
        onNewIntent(getIntent());
        initView();
        initData();
        CommandCenter.m4607a().m4606a(new Command(CommandID.REFRESH_INFORMATION, new Object[0]));
    }

    private String buildLocalCoverPath(TTPodUser tTPodUser) {
        String str = LOCAL_COVER_IMAGE_PATH_FORMAT;
        Object[] objArr = new Object[1];
        objArr[0] = Long.valueOf(tTPodUser.getUserId() + (tTPodUser.getProfileCoverUrl() == null ? 0 : tTPodUser.getProfileCoverUrl().hashCode()));
        return String.format(str, objArr);
    }

    private String buildLocalAvatarPath(TTPodUser tTPodUser) {
        String str = LOCAL_AVATAR_IMAGE_PATH_FORMAT;
        Object[] objArr = new Object[1];
        objArr[0] = Long.valueOf(tTPodUser.getUserId() + (tTPodUser.getAvatarUrl() == null ? 0 : tTPodUser.getAvatarUrl().hashCode()));
        return String.format(str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            this.mIsLogoutVisible = intent.getBooleanExtra(BUNDLEKEY_LOGOUT_VISIBLE, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.MODIFY_NICKNAME_FINISHED, ReflectUtils.m8375a(cls, "modifyNickNameFinished", CommonResult.class));
        map.put(CommandID.MODIFY_USER_EMAIL_FINISHED, ReflectUtils.m8375a(cls, "modifyUserEmailFinished", CommonResult.class));
        map.put(CommandID.MODIFY_PASSWORD_FINISHED, ReflectUtils.m8375a(cls, "modifyPasswordFinished", CommonResult.class));
        map.put(CommandID.MODIFY_COVER_FINISHED, ReflectUtils.m8375a(cls, "modifyCoverFinished", CommonResult.class));
        map.put(CommandID.MODIFY_AVATAR_FINISHED, ReflectUtils.m8375a(cls, "modifyAvatarFinished", CommonResult.class));
        map.put(CommandID.REFRESH_INFORMATION_FINISHED, ReflectUtils.m8375a(cls, "refreshInformationFinished", new Class[0]));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.mUser != null && this.mOriginUser != null) {
            CommandCenter m4607a = CommandCenter.m4607a();
            CommandID commandID = CommandID.MODIFY_SEX_BIRTHDAY;
            Object[] objArr = new Object[3];
            objArr[0] = this.mUser;
            objArr[1] = Boolean.valueOf(this.mUser.getSex() != this.mOriginUser.getSex());
            objArr[2] = Boolean.valueOf(this.mUser.getBirthdayInSecond() != this.mOriginUser.getBirthdayInSecond());
            m4607a.m4606a(new Command(commandID, objArr));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 1:
                case 2:
                    cropPhoto(i, intent);
                    this.mCachedRequestCode = i;
                    return;
                case 3:
                    if (this.mCachedRequestCode == 1) {
                        setCover();
                        CommandCenter.m4607a().m4606a(new Command(CommandID.MODIFY_COVER, this.mLocalCoverImagePath, Integer.valueOf(DisplayUtils.m7225c()), Integer.valueOf((int) getResources().getDimension(R.dimen.cover_height))));
                        return;
                    } else if (this.mCachedRequestCode == 2) {
                        setAvatar();
                        CommandCenter.m4607a().m4606a(new Command(CommandID.MODIFY_AVATAR, this.mLocalAvatarImagePath, Integer.valueOf((int) getResources().getDimension(R.dimen.avatar_width)), Integer.valueOf((int) getResources().getDimension(R.dimen.avatar_height))));
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void cropPhoto(int i, Intent intent) {
        if (i == 1) {
            this.mPickImageHelper.m7717a(intent != null ? intent.getData() : null, this.mLocalCoverImagePath);
        } else if (i == 2) {
            this.mPickImageHelper.m7717a(intent != null ? intent.getData() : null, this.mLocalAvatarImagePath);
        }
    }

    public void modifyNickNameFinished(CommonResult commonResult) {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            this.mUser.setNickName(commonResult.m4584b());
            this.mTextNickName.setText(commonResult.m4584b());
            return;
        }
        BaseResult baseResult = (BaseResult) commonResult.m4582d();
        if (baseResult.getCode() == 30307) {
            PopupsUtils.m6721a(getString(R.string.userinfo_nickname_has_been_used, new Object[]{commonResult.m4584b()}));
        } else if (!ResultUtils.m7706a(this, baseResult)) {
            PopupsUtils.m6721a(baseResult.getMessage());
        }
    }

    public void modifyUserEmailFinished(CommonResult commonResult) {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            this.mUser.setUserName(commonResult.m4584b());
            this.mTextEmail.setText(commonResult.m4584b());
            this.mUser.setIsLocalBind("1");
            return;
        }
        BaseResult baseResult = (BaseResult) commonResult.m4582d();
        int code = baseResult.getCode();
        if (30307 == code || 30308 == code) {
            PopupsUtils.m6721a(getString(R.string.userinfo_email_has_been_used, new Object[]{commonResult.m4584b()}));
        } else if (30302 == code) {
            PopupsUtils.m6760a((int) R.string.userinfo_password_error);
        } else if (30310 == code) {
            PopupsUtils.m6760a((int) R.string.email_format);
        } else {
            PopupsUtils.m6721a(baseResult.getMessage());
        }
    }

    public void modifyPasswordFinished(CommonResult commonResult) {
        if (status() == 2) {
            PopupsUtils.m6761a();
        }
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            PopupsUtils.m6721a("修改密码成功");
        } else {
            PopupsUtils.m6760a((int) R.string.userinfo_password_mismatch);
        }
    }

    public void modifyCoverFinished(CommonResult commonResult) {
        setCover();
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            PopupsUtils.m6721a(getString(R.string.userinfo_modify_cover_ok));
        } else {
            PopupsUtils.m6721a(getString(R.string.userinfo_modify_cover_failed) + ((BaseResult) commonResult.m4582d()).getMessage());
        }
    }

    public void modifyAvatarFinished(CommonResult commonResult) {
        setAvatar();
        if (commonResult.m4585a() == ErrCode.ErrNone) {
            PopupsUtils.m6721a(getString(R.string.userinfo_modify_avatar_ok));
        } else {
            PopupsUtils.m6721a(getString(R.string.userinfo_modify_avatar_failed) + ((BaseResult) commonResult.m4582d()).getMessage());
        }
    }

    public void refreshInformationFinished() {
        this.mUser = Preferences.m2954aq();
        if (this.mUser == null) {
            finish();
        } else {
            initData();
        }
    }

    private void initView() {
        this.mImageProfileCover = (ImageView) findViewById(R.id.imageview_userinfo_cover);
        this.mImageAvatar = (ImageView) findViewById(R.id.imageview_userinfo_avatar);
        this.mTextNickName = (TextView) findViewById(R.id.textview_userinfo_nickname);
        this.mRadioGroupSex = (RadioGroup) findViewById(R.id.radiogroup_userinfo_sex);
        this.mRadioGroupSex.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        findViewById(R.id.radiobutton_userinfo_sex_male).setClickable(false);
        findViewById(R.id.radiobutton_userinfo_sex_female).setClickable(false);
        this.mTextBirthday = (TextView) findViewById(R.id.textview_userinfo_birthday);
        this.mTextConstellation = (TextView) findViewById(R.id.textview_userinfo_constellation);
        this.mTextConstellation.setSelected(true);
        this.mVipActionView = findViewById(R.id.linearlayout_userinfo_vip);
        this.mTextEmail = (TextView) findViewById(R.id.textview_userinfo_email);
        this.mTextEmailState = (TextView) findViewById(R.id.textview_userinfo_email_state);
        this.mTextPassword = (TextView) findViewById(R.id.textview_userinfo_password);
        this.mTextPasswordState = (TextView) findViewById(R.id.textview_userinfo_password_state);
        findViewById(R.id.button_userinfo_logout).setVisibility(this.mIsLogoutVisible ? View.VISIBLE : View.GONE);
        setOnClickListener(this.mImageProfileCover, this.mImageAvatar, findViewById(R.id.linearlayout_userinfo_nickname), findViewById(R.id.linearlayout_userinfo_sex), findViewById(R.id.linearlayout_userinfo_birthday), this.mVipActionView, findViewById(R.id.linearlayout_userinfo_email), findViewById(R.id.linearlayout_userinfo_password), findViewById(R.id.button_userinfo_logout));
    }

    private void setOnClickListener(View... viewArr) {
        for (View view : viewArr) {
            view.setOnClickListener(this.mOnClickListener);
        }
    }

    private void initData() {
        setAvatar();
        setCover();
        this.mTextNickName.setText(this.mUser.getNickName());
        this.mRadioGroupSex.check(this.mUser.getSex() == 1 ? R.id.radiobutton_userinfo_sex_male : R.id.radiobutton_userinfo_sex_female);
        setBirthdayAndConstellation(new BirthdayDate(this.mUser.getBirthdayInSecond()));
        setVip(this.mUser.getVipLevel());
        setEmail(this.mUser.getUserName());
    }

    private void setAvatar() {
        ImageCacheUtils.m4750a(this.mImageAvatar, this.mUser.getAvatarUrl(), (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height), this.mLocalAvatarImagePath);
    }

    private void setCover() {
        ImageCacheUtils.m4750a(this.mImageProfileCover, this.mUser.getProfileCoverUrl(), DisplayUtils.m7225c(), (int) getResources().getDimension(R.dimen.cover_height), this.mLocalCoverImagePath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBirthdayAndConstellation(BirthdayDate birthdayDate) {
        this.mTextBirthday.setText(birthdayDate.toString());
        this.mTextConstellation.setText(birthdayDate.m7722f());
    }

    private void setVip(int i) {
        this.mVipActionView.setVisibility(View.GONE);
    }

    private void setEmail(String str) {
        this.mTextEmail.setText(str);
        if (StringUtils.m8346a(str)) {
            this.mTextEmailState.setSelected(false);
            this.mTextPasswordState.setSelected(false);
            this.mTextEmailState.setText(R.string.userinfo_unbind);
            this.mTextPasswordState.setText(R.string.userinfo_not_set);
            this.mTextPassword.setHint((CharSequence) null);
            return;
        }
        this.mTextEmailState.setSelected(true);
        this.mTextPasswordState.setSelected(true);
        this.mTextEmailState.setText(R.string.userinfo_bind);
        this.mTextPasswordState.setText(R.string.userinfo_setted);
        this.mTextPassword.setHint(R.string.userinfo_modify_password_hint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickAvatar() {
        this.mPickImageHelper.m7719a(2, getString(R.string.userinfo_change_avatar_image), (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height));
        MusicCircleStatistic.m7977c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickProfileCover() {
        int width = getWindow().getDecorView().getWidth();
        MusicCircleStatistic.m7982b();
        this.mPickImageHelper.m7719a(1, getString(R.string.userinfo_change_profile_cover_image), width, width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickLogout() {
        CommandCenter.m4607a().m4606a(new Command(CommandID.LOGOUT, new Object[0]));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickEmail() {
        EditTextDialog editTextDialog = new EditTextDialog(this, new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(0, "", this.mUser.getUserName(), getString(R.string.userinfo_input_email)), new EditTextDialog.C1144a(1, "", "", getString(R.string.userinfo_input_current_password))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.activities.user.UserInfoActivity.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(EditTextDialog editTextDialog2) {
                String obj = editTextDialog2.m6902c(0).m6896d().toString();
                String obj2 = editTextDialog2.m6902c(1).m6896d().toString();
                if (!obj.equals(UserInfoActivity.this.mUser.getUserName()) && ValidateUtil.m7704a(obj, R.string.use_name_hint_text, R.string.email_format, null, 0, ValidateUtil.f3111a) && ValidateUtil.m7704a(obj2, R.string.pass_word_hint_text, R.string.password_length, null, 0, ValidateUtil.f3114d)) {
                    if (UserInfoActivity.this.mUser.getIsLocalBind() == null || "0".equals(UserInfoActivity.this.mUser.getIsLocalBind().toString())) {
                        CommandCenter.m4607a().m4606a(new Command(CommandID.BIND_USER_EMAIL, obj, obj2));
                    } else {
                        CommandCenter.m4607a().m4606a(new Command(CommandID.MODIFY_USER_EMAIL, obj, obj2));
                    }
                    PopupsUtils.m6734a((Context) UserInfoActivity.this, UserInfoActivity.this.getString(R.string.loading));
                }
            }
        }, null);
        editTextDialog.setTitle(R.string.userinfo_modify_email_title);
        editTextDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickPassword() {
        EditTextDialog editTextDialog = new EditTextDialog(this, new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(0, "", "", getString(R.string.userinfo_input_current_password)), new EditTextDialog.C1144a(1, "", "", getString(R.string.userinfo_input_new_password))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.activities.user.UserInfoActivity.5
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(EditTextDialog editTextDialog2) {
                String obj = editTextDialog2.m6902c(0).m6896d().toString();
                String obj2 = editTextDialog2.m6902c(1).m6896d().toString();
                if (ValidateUtil.m7704a(obj, R.string.pass_word_hint_text, R.string.password_length, null, 0, ValidateUtil.f3114d) && ValidateUtil.m7704a(obj2, R.string.pass_word_hint_text, R.string.password_length, null, 0, ValidateUtil.f3114d) && !obj.equals(obj2)) {
                    CommandCenter.m4607a().m4606a(new Command(CommandID.MODIFY_PASSWORD, obj, obj2));
                    PopupsUtils.m6734a((Context) UserInfoActivity.this, UserInfoActivity.this.getString(R.string.loading));
                }
            }
        }, null);
        editTextDialog.setTitle(R.string.userinfo_change_password_title);
        editTextDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickVip() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickBirthday() {
        BirthdayDate birthdayDate;
        BirthdayDate birthdayDate2 = new BirthdayDate(this.mUser.getBirthdayInSecond());
        if (validBirthdayDate(birthdayDate2)) {
            birthdayDate = birthdayDate2;
        } else {
            if (birthdayDate2 != null) {
                LogUtils.m8388a(TAG, "data:" + birthdayDate2.m7727a() + "-" + birthdayDate2.m7726b() + "-" + birthdayDate2.m7725c());
            }
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(2);
            int i2 = calendar.get(1);
            if (i >= 12) {
                i = 11;
            }
            birthdayDate = new BirthdayDate(i2, i, calendar.get(5));
        }
        new DatePickerDialog(this, this.mOnDateSetListener, birthdayDate.m7727a(), birthdayDate.m7726b(), birthdayDate.m7725c()).show();
        MusicCircleStatistic.m7970f();
    }

    private boolean validBirthdayDate(BirthdayDate birthdayDate) {
        return birthdayDate != null && birthdayDate.m7727a() > DEFAULT_START_YEAR && birthdayDate.m7727a() < DEFAULT_END_YEAR && birthdayDate.m7726b() >= 0 && birthdayDate.m7726b() < 12 && birthdayDate.m7725c() > 0 && birthdayDate.m7725c() <= 31;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickSex() {
        int i = R.id.radiobutton_userinfo_sex_male;
        if (this.mRadioGroupSex.getCheckedRadioButtonId() == R.id.radiobutton_userinfo_sex_male) {
            i = R.id.radiobutton_userinfo_sex_female;
        }
        this.mRadioGroupSex.check(i);
        MusicCircleStatistic.m7972e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickNickName() {
        EditTextDialog editTextDialog = new EditTextDialog(this, new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(0, "", this.mUser.getNickName(), getString(R.string.userinfo_input_nickname))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.activities.user.UserInfoActivity.6
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(EditTextDialog editTextDialog2) {
                String obj = editTextDialog2.m6902c(0).m6896d().toString();
                if (!obj.equals(UserInfoActivity.this.mUser.getNickName()) && ValidateUtil.m7704a(obj, R.string.nickname_hint_text, R.string.nick_name_restriction, null, 0, ValidateUtil.f3113c)) {
                    if (SensitiveWordUtils.m8243a(UserInfoActivity.this).m8242a(obj)) {
                        PopupsUtils.m6721a("内容含有敏感词，请重新输入");
                        return;
                    }
                    CommandCenter.m4607a().m4606a(new Command(CommandID.MODIFY_NICKNAME, obj));
                    PopupsUtils.m6734a((Context) UserInfoActivity.this, UserInfoActivity.this.getString(R.string.loading));
                }
            }
        }, null);
        editTextDialog.setTitle(R.string.userinfo_modify_nickname_title);
        editTextDialog.show();
        MusicCircleStatistic.m7974d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            this.mPickImageHelper.m7715a(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.mCachedRequestCode = bundle.getInt(KEY_REQUEST_CODE, this.mCachedRequestCode);
            this.mPickImageHelper.m7709b(bundle);
        }
    }
}
