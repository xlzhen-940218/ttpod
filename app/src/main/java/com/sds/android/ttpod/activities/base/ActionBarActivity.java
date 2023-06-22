package com.sds.android.ttpod.activities.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.ListPopupWindow;
import java.util.Collection;

/* loaded from: classes.dex */
public class ActionBarActivity extends ThemeActivity implements OnDropdownMenuClickListener {
    private ActionBarController mActionBarController;
    protected ListPopupWindow mDropDownMenu;
    protected ActionBarController.C1070a mMenuAction;
    private View mRootView;
    private View mShadowView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRootView = View.inflate(this, R.layout.activity_actionbar, null);
        this.mActionBarController = ActionBarController.m7197a(this.mRootView.findViewById(R.id.action_bar_controller));
        bindActionBar();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        if (needMenuAction()) {
            this.mMenuAction = this.mActionBarController.m7199a((Drawable) null);
            ThemeUtils.m8168a(this.mMenuAction, ThemeElement.TOP_BAR_MORE_IMAGE, (int) R.string.icon_more_vertical, ThemeElement.TOP_BAR_TEXT);
            this.mMenuAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.base.ActionBarActivity.1
                @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
                /* renamed from: a */
                public void mo5433a(ActionBarController.C1070a c1070a) {
                    ActionBarActivity.this.onToggleMenuView(true);
                }
            });
        }
    }

    public void onToggleMenuView(boolean z) {
        Collection<ActionItem> onCreateDropDownMenu;
        if (this.mDropDownMenu != null && this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu.dismiss();
            this.mDropDownMenu = null;
        } else if (needMenuAction() && this.mMenuAction.m7157c() && (onCreateDropDownMenu = onCreateDropDownMenu()) != null && !onCreateDropDownMenu.isEmpty()) {
            this.mDropDownMenu = ActionBarFragment.popupMenu(this, onCreateDropDownMenu, z, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        PopupsUtils.m6723a(this.mDropDownMenu);
    }

    protected Collection<ActionItem> onCreateDropDownMenu() {
        return null;
    }

    protected boolean needMenuAction() {
        return false;
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        super.setContentView(buildContentView(getLayoutInflater().inflate(i, (ViewGroup) null)));
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        super.setContentView(buildContentView(view));
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(buildContentView(view), layoutParams);
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mActionBarController.m7193a(charSequence);
    }

    @Override // android.app.Activity
    public void setTitle(int i) {
        super.setTitle(i);
        this.mActionBarController.m7189b(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View buildContentView(View view) {
        if (this.mActionBarController.m7184c()) {
            this.mShadowView = this.mRootView.findViewById(R.id.actionbar_shadow);
            if (view != null) {
                ((ViewGroup) this.mRootView.findViewById(R.id.activity_body)).addView(view);
            }
            return this.mRootView;
        }
        return view;
    }

    public void hideActionBar() {
        this.mActionBarController.m7180c(false);
        setActivityBodyMarginTop(0);
        if (this.mShadowView != null) {
            this.mShadowView.setVisibility(View.GONE);
        }
    }

    public void showActionBar() {
        this.mActionBarController.m7180c(true);
        setActivityBodyMarginTop(getResources().getDimensionPixelOffset(R.dimen.dialog_header_height));
        if (this.mShadowView != null) {
            this.mShadowView.setVisibility(View.VISIBLE);
        }
    }

    private void setActivityBodyMarginTop(int i) {
        ((ViewGroup.MarginLayoutParams) ((ViewGroup) this.mRootView.findViewById(R.id.activity_body)).getLayoutParams()).setMargins(0, i, 0, 0);
    }

    public ActionBarController getActionBarController() {
        return this.mActionBarController;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final View getRootView() {
        return this.mRootView;
    }

    private void bindActionBar() {
        this.mActionBarController.m7196a(new ActionBarController.InterfaceC1073c() { // from class: com.sds.android.ttpod.activities.base.ActionBarActivity.2
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1073c
            /* renamed from: a */
            public void mo5380a(ActionBarController actionBarController) {
                ActionBarActivity.this.onActionBarBackPressed();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onActionBarBackPressed() {
        onBackPressed();
    }

    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        this.mDropDownMenu = null;
    }
}
