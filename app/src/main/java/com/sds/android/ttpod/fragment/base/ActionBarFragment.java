package com.sds.android.ttpod.fragment.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.ListPopupWindow;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class ActionBarFragment extends BaseFragment implements OnDropdownMenuClickListener {
    private static final String TAG = "ActionBarFragment";
    private ActionBarController mActionBarController;
    private ListPopupWindow mDropDownMenu;
    private ActionBarController.C1070a mMenuAction;
    private View mRootView;
    private ActionBarController.C1070a mSearchAction;
    private View mShadowView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.APP_THEME_CHANGED, ReflectUtils.m8375a(getClass(), "onThemeChanged", new Class[0]));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = View.inflate(getActivity(), R.layout.activity_actionbar, null);
        this.mActionBarController = ActionBarController.m7197a(this.mRootView.findViewById(R.id.action_bar_controller));
        this.mActionBarController.m7196a(new ActionBarController.InterfaceC1073c() { // from class: com.sds.android.ttpod.fragment.base.ActionBarFragment.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1073c
            /* renamed from: a */
            public void mo5380a(ActionBarController actionBarController) {
                ActionBarFragment.this.onTitleClicked();
            }
        });
        addCustomActions();
        if (needSearchAction()) {
            this.mSearchAction = this.mActionBarController.m7198a(getResources().getDrawable(R.drawable.cmmusic_search), "searchAction");
            this.mSearchAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.base.ActionBarFragment.2
                @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
                /* renamed from: a */
                public void mo5433a(ActionBarController.C1070a c1070a) {
                    ActionBarFragment.this.onSearchActionClicked();
                }
            });
        }
        if (needMenuAction()) {
            this.mMenuAction = this.mActionBarController.m7198a((Drawable) null, "menuAction");
            this.mMenuAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.base.ActionBarFragment.3
                @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
                /* renamed from: a */
                public void mo5433a(ActionBarController.C1070a c1070a) {
                    ActionBarFragment.this.onToggleMenuView(true);
                }
            });
        }
        return buildContentView(onCreateContentView(layoutInflater, (ViewGroup) this.mRootView.findViewById(R.id.activity_body), bundle));
    }

    protected void addCustomActions() {
    }

    protected boolean needSearchAction() {
        return true;
    }

    protected boolean needMenuAction() {
        return false;
    }

    protected void onSearchActionClicked() {
        launchFragment((BaseFragment) Fragment.instantiate(getActivity(), SearchResultFragment.class.getName()));
        //LocalStatistic.m5111av();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTitleClicked() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideFixedAction() {
        if (needMenuAction()) {
            this.mMenuAction.m7172a();
        }
        if (needSearchAction()) {
            this.mSearchAction.m7172a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showFixedAction() {
        if (needMenuAction()) {
            this.mMenuAction.m7163b();
        }
        if (needSearchAction()) {
            this.mSearchAction.m7163b();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideActionBar() {
        this.mActionBarController.m7180c(false);
        setActivityBodyMarginTop(0);
        if (this.mShadowView != null) {
            this.mShadowView.setVisibility(View.GONE);
        }
    }

    protected void showActionBar() {
        this.mActionBarController.m7180c(true);
        setActivityBodyMarginTop(getResources().getDimensionPixelOffset(R.dimen.dialog_header_height));
        if (this.mShadowView != null) {
            this.mShadowView.setVisibility(View.VISIBLE);
        }
    }

    private void setActivityBodyMarginTop(int i) {
        ((ViewGroup.MarginLayoutParams) ((ViewGroup) this.mRootView.findViewById(R.id.activity_body)).getLayoutParams()).setMargins(0, i, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        getActionBarController().onThemeLoaded();
        if (needSearchAction()) {
            ThemeUtils.m8168a(this.mSearchAction, ThemeElement.TOP_BAR_SEARCH_IMAGE, (int) R.string.icon_search, ThemeElement.TOP_BAR_TEXT);
        }
        if (needMenuAction()) {
            ThemeUtils.m8168a(this.mMenuAction, ThemeElement.TOP_BAR_MORE_IMAGE, (int) R.string.icon_more_horizontal, ThemeElement.TOP_BAR_TEXT);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        onThemeLoaded();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mActionBarController != null) {
            this.mActionBarController.m7196a((ActionBarController.InterfaceC1073c) null);
        }
        if (this.mSearchAction != null) {
            this.mSearchAction.m7167a((ActionBarController.InterfaceC1072b) null);
        }
        if (this.mMenuAction != null) {
            this.mMenuAction.m7167a((ActionBarController.InterfaceC1072b) null);
        }
    }

    protected View buildContentView(View view) {
        this.mShadowView = this.mRootView.findViewById(R.id.actionbar_shadow);
        ((ViewGroup) this.mRootView.findViewById(R.id.activity_body)).addView(view);
        return this.mRootView;
    }

    public ActionBarController getActionBarController() {
        return this.mActionBarController;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final View getRootView() {
        return this.mRootView;
    }

    public static ListPopupWindow popupMenu(Activity activity, Collection<ActionItem> collection, boolean z, OnDropdownMenuClickListener onDropdownMenuClickListener) {
        int i;
        int i2;
        int i3 = 0;
        ListPopupWindow createDropDownMenu = createDropDownMenu(activity, collection, onDropdownMenuClickListener);
        Resources resources = activity.getResources();
        createDropDownMenu.setWidth((int) resources.getDimension(z ? R.dimen.drop_down_top_right_menu_width : R.dimen.drop_down_down_center_menu_width));
        Rect rect = new Rect();
        View decorView = activity.getWindow().getDecorView();
        decorView.getWindowVisibleDisplayFrame(rect);
        if (z) {
            int dimension = ((int) (resources.getDimension(R.dimen.dialog_header_height) + resources.getDimension(R.dimen.dialog_header_shadow_height))) + rect.top;
            i2 = 53;
            i = dimension;
            i3 = (int) resources.getDimension(R.dimen.drop_down_top_right_right_margin);
        } else {
            i = 0;
            i2 = 81;
        }
        createDropDownMenu.showAtLocation((ViewGroup) decorView.findViewById(android.R.id.content), i2, i3, i);
        return createDropDownMenu;
    }

    protected Collection<ActionItem> onCreateDropDownMenu() {
        return null;
    }

    @TargetApi(11)
    public static ListPopupWindow createDropDownMenu(Activity activity, Collection<ActionItem> collection, final OnDropdownMenuClickListener onDropdownMenuClickListener) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(activity, R.layout.dialog_page_choice_list, R.id.lst_content);
        final ArrayAdapter<ActionItem> arrayAdapter = new ArrayAdapter<ActionItem>(activity, R.layout.dialog_page_choice_item, R.id.txt_title) { // from class: com.sds.android.ttpod.fragment.base.ActionBarFragment.4
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public long getItemId(int i) {
                return getItem(i).m7005e();
            }
        };
        if (collection != null && !collection.isEmpty()) {
            if (SDKVersionUtils.checkVersionThanAndroid11()) {
                arrayAdapter.addAll(collection);
            } else {
                for (ActionItem actionItem : collection) {
                    arrayAdapter.add(actionItem);
                }
            }
        }
        listPopupWindow.m1338a(arrayAdapter);
        listPopupWindow.m1339a(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.base.ActionBarFragment.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                listPopupWindow.dismiss();
                onDropdownMenuClickListener.onDropDownMenuClicked((int) j, (ActionItem) arrayAdapter.getItem(i));
            }
        });
        return listPopupWindow;
    }

    public static ListPopupWindow popupMenu(Activity activity, Collection<ActionItem> collection, boolean z, OnDropdownMenuClickListener onDropdownMenuClickListener, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        ListPopupWindow createDropDownMenu = createDropDownMenu(activity, collection, onDropdownMenuClickListener, i, i2, i3, i4);
        Resources resources = activity.getResources();
        createDropDownMenu.setWidth((int) resources.getDimension(z ? R.dimen.drop_down_top_right_menu_width : R.dimen.drop_down_down_center_menu_width));
        Rect rect = new Rect();
        View decorView = activity.getWindow().getDecorView();
        decorView.getWindowVisibleDisplayFrame(rect);
        if (i5 < 0) {
            i5 = 0;
        }
        if (z) {
            int dimension = rect.top + ((int) (resources.getDimension(R.dimen.dialog_header_shadow_height) + resources.getDimension(R.dimen.dialog_header_height)));
            i6 = 53;
            i7 = dimension;
        } else {
            i6 = 81;
            i7 = 0;
        }
        createDropDownMenu.showAtLocation((ViewGroup) decorView.findViewById(android.R.id.content), i6, i5, i7);
        return createDropDownMenu;
    }

    @TargetApi(11)
    public static ListPopupWindow createDropDownMenu(Activity activity, Collection<ActionItem> collection, final OnDropdownMenuClickListener onDropdownMenuClickListener, int i, int i2, int i3, int i4) {
        final ListPopupWindow listPopupWindow = new ListPopupWindow(activity, i, i2);
        final ArrayAdapter<ActionItem> arrayAdapter = new ArrayAdapter<ActionItem>(activity, i3, i4) { // from class: com.sds.android.ttpod.fragment.base.ActionBarFragment.6
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public long getItemId(int i5) {
                return getItem(i5).m7005e();
            }
        };
        if (collection != null && !collection.isEmpty()) {
            if (SDKVersionUtils.checkVersionThanAndroid11()) {
                arrayAdapter.addAll(collection);
            } else {
                for (ActionItem actionItem : collection) {
                    arrayAdapter.add(actionItem);
                }
            }
        }
        listPopupWindow.m1338a(arrayAdapter);
        listPopupWindow.m1339a(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.base.ActionBarFragment.7
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i5, long j) {
                listPopupWindow.dismiss();
                onDropdownMenuClickListener.onDropDownMenuClicked((int) j, (ActionItem) arrayAdapter.getItem(i5));
            }
        });
        return listPopupWindow;
    }

    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        this.mDropDownMenu = null;
        switch (i) {
            case 1:
                EntryUtils.m8287h(getActivity());
                return;
            case 2:
                EntryUtils.m8292c((Context) getActivity());
                return;
            case 3:
                EntryUtils.m8296b();
                return;
            default:
                return;
        }
    }

    public void onToggleMenuView(boolean z) {
        Collection<ActionItem> onCreateDropDownMenu;
        if (this.mDropDownMenu != null && this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu.dismiss();
            this.mDropDownMenu = null;
        } else if (needMenuAction() && this.mMenuAction.m7157c() && (onCreateDropDownMenu = onCreateDropDownMenu()) != null && !onCreateDropDownMenu.isEmpty()) {
            this.mDropDownMenu = popupMenu(getActivity(), onCreateDropDownMenu, z, this);
        }
        //LocalStatistic.m5126ag();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        PopupsUtils.m6723a(this.mDropDownMenu);
    }
}
