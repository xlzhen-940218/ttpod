package com.sds.android.ttpod.activities.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.soundsearch.EditModeToggle;
import com.sds.android.ttpod.component.soundsearch.IThemeEditable;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class ThemeManagementBaseActivity extends SlidingPagerActivity implements EditModeToggle, IThemeEditable.InterfaceC1333a, ThemeManager.InterfaceC2019b {
    private static final long DELAY_SET_ACTIONBAR = 300;
    private ActionBarController.C1070a mEditAction;
    protected boolean mInEditMode = false;
    private ActionBarController.C1070a mSelectActionItem;

    protected abstract void clickStatistic(int i);

    protected abstract String getOnSelectedCountChangedString(int i);

    protected abstract String getTitleString();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setLaunchFragmentAttr(R.id.action_bar_activity_container, R.anim.slide_in_right, R.anim.slide_out_right);
        hideEditActionItem();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        if (isInEditMode()) {
            toggleEditMode();
        }
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.APP_THEME_CHANGED, ReflectUtils.m8375a(cls, "onThemeLoaded", new Class[0]));
        map.put(CommandID.UPDATE_BACKGROUND, ReflectUtils.m8375a(cls, "updateBackground", Drawable.class));
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity
    protected void onBuildActionBar(ActionBarController actionBarController) {
        this.mEditAction = actionBarController.m7198a((Drawable) null, "editAction");
        this.mEditAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                ThemeManagementBaseActivity.this.toggleEditMode();
            }
        });
        this.mSelectActionItem = actionBarController.m7199a((Drawable) null);
        this.mSelectActionItem.m7172a();
        setSelectAllAction();
        this.mSelectActionItem.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                if (c1070a == ThemeManagementBaseActivity.this.mSelectActionItem) {
                    if (c1070a.m7150f() == null) {
                        ThemeManagementBaseActivity.this.selectAll();
                    } else {
                        ThemeManagementBaseActivity.this.selectNone();
                    }
                }
            }
        });
    }

    public void selectAll() {
        setUnSelectAllAction();
        IThemeEditable iThemeEditable = getIThemeEditable();
        if (iThemeEditable != null) {
            iThemeEditable.selectAll();
        }
        onSelectedCountChanged();
    }

    public void selectNone() {
        setSelectAllAction();
        IThemeEditable iThemeEditable = getIThemeEditable();
        if (iThemeEditable != null) {
            iThemeEditable.selectNone();
        }
        onSelectedCountChanged();
    }

    private void setUnSelectAllAction() {
        this.mSelectActionItem.m7166a((Object) this.mSelectActionItem);
        ThemeUtils.m8170a(this.mSelectActionItem, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
    }

    private void setSelectAllAction() {
        this.mSelectActionItem.m7166a((Object) null);
        ThemeUtils.m8170a(this.mSelectActionItem, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeUtils.m8168a(this.mEditAction, ThemeElement.TOP_BAR_EDIT_IMAGE, (int) R.string.icon_edit, ThemeElement.TOP_BAR_TEXT);
        ThemeUtils.m8166a(this.mPagerTitle);
        ThemeManager.m3269a(this.mPagerContent, ThemeElement.BACKGROUND_MASK);
        getActionBarController().onThemeLoaded();
        ThemeManager.m3260b(getRootView(), ThemeUtils.m8182a());
        ThemeUtils.m8168a(this.mEditAction, ThemeElement.TOP_BAR_EDIT_IMAGE, (int) R.string.icon_edit, ThemeElement.TOP_BAR_TEXT);
    }

    public void updateBackground(Drawable drawable) {
        if (drawable != null) {
            getRootView().setBackgroundDrawable(drawable);
        }
    }

    private void gotoSpecificPage(int i) {
        if (i != -1) {
            this.mPagerContent.setCurrentItem(i);
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public boolean isInEditMode() {
        EditModeToggle editModeToggle = getEditModeToggle();
        return editModeToggle != null && editModeToggle.isInEditMode();
    }

    @Override // com.sds.android.ttpod.component.soundsearch.EditModeToggle
    public boolean hasEditableContent() {
        EditModeToggle editModeToggle = getEditModeToggle();
        if (editModeToggle != null) {
            return editModeToggle.hasEditableContent();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EditModeToggle getEditModeToggle() {
        Fragment item = this.mPagerAdapter.getItem(this.mPagerContent.getCurrentItem());
        if (item instanceof EditModeToggle) {
            return (EditModeToggle) item;
        }
        return null;
    }

    protected IThemeEditable getIThemeEditable() {
        Fragment item = this.mPagerAdapter.getItem(this.mPagerContent.getCurrentItem());
        if (item instanceof IThemeEditable) {
            return (IThemeEditable) item;
        }
        return null;
    }

    protected IThemeEditable.InterfaceC1333a getOnEditRequestListener() {
        Fragment item = this.mPagerAdapter.getItem(this.mPagerContent.getCurrentItem());
        if (item instanceof IThemeEditable) {
            return (IThemeEditable.InterfaceC1333a) item;
        }
        return null;
    }

    public void showEditActionItem() {
        if (this.mInEditMode) {
            setSlidingCloseMode(0);
            getActionBarController().m7193a((CharSequence) getOnSelectedCountChangedString(0));
            this.mSelectActionItem.m7163b();
            setSelectAllAction();
            this.mEditAction.m7172a();
            this.mPagerTitle.setVisibility(View.GONE);
            ((ViewGroup.MarginLayoutParams) this.mPagerContent.getLayoutParams()).topMargin = 0;
            this.mPagerContent.setCanScroll(false);
            return;
        }
        setSlidingCloseMode(1);
        getActionBarController().m7193a((CharSequence) getTitleString());
        this.mSelectActionItem.m7172a();
        if (hasEditableContent()) {
            this.mEditAction.m7163b();
        } else {
            this.mEditAction.m7172a();
        }
        this.mPagerTitle.setVisibility(View.VISIBLE);
        ((ViewGroup.MarginLayoutParams) this.mPagerContent.getLayoutParams()).topMargin = getResources().getDimensionPixelSize(R.dimen.tab_label_height);
        this.mPagerContent.setCanScroll(true);
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onRemoveRequested() {
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onSelectedCountChanged() {
        IThemeEditable iThemeEditable = getIThemeEditable();
        if (iThemeEditable != null) {
            if (iThemeEditable.totalCount() != iThemeEditable.selectedCount()) {
                setSelectAllAction();
            } else {
                setUnSelectAllAction();
            }
            getActionBarController().m7193a((CharSequence) getOnSelectedCountChangedString(iThemeEditable.selectedCount()));
        }
    }

    @Override // com.sds.android.ttpod.component.soundsearch.IThemeEditable.InterfaceC1333a
    public void onStopEditRequested() {
        toggleEditMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity
    public void onActionBarBackPressed() {
        if (PopupsUtils.m6716b()) {
            new Handler().post(new Runnable() { // from class: com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    ThemeManagementBaseActivity.this.toggleEditMode();
                }
            });
        } else {
            super.onActionBarBackPressed();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (PopupsUtils.m6716b()) {
            toggleEditMode();
        } else {
            super.onBackPressed();
        }
    }

    public void hideEditActionItem() {
        this.mEditAction.m7155c(false);
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingPagerActivity, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        super.onPageSelected(i);
        refreshEditButton();
        clickStatistic(i);
    }

    public void refreshEditButton() {
        this.mPagerContent.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (!ThemeManagementBaseActivity.this.isFinishing()) {
                    if (ThemeManagementBaseActivity.this.hasEditableContent()) {
                        ThemeManagementBaseActivity.this.showEditActionItem();
                    } else {
                        ThemeManagementBaseActivity.this.hideEditActionItem();
                    }
                }
            }
        }, DELAY_SET_ACTIONBAR);
    }
}
