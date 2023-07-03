package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.DateUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.EntryUtils;
import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
public class SubGroupListFragment extends SlidingClosableFragment {
    private GroupListFragment mGroupListFragment;
    private View mRootView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_group_list, (ViewGroup) null);
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (this.mGroupListFragment == null) {
            this.mGroupListFragment = getFragmentInstance();
        }
        if (!isDetached()) {
            getChildFragmentManager().beginTransaction().replace(R.id.content_custom_group, this.mGroupListFragment).commitAllowingStateLoss();
        }
        view.findViewById(R.id.tv_create_playlist).setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.SubGroupListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                //SUserUtils.m4956a(SAction.ACTION_MY_SONGLIST_CREATE, SPage.PAGE_NONE);
                //LocalStatistic.m5162U();
                PopupsUtils.m6710b(SubGroupListFragment.this.getActivity(), DateUtils.m8432a(0), new BaseDialog.OnClickListener<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.SubGroupListFragment.1.1
                    @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public void onClick(EditTextDialog editTextDialog) {
                        CommandCenter.getInstance().m4602a(new Command(CommandID.ADD_GROUP, editTextDialog.m6902c(0).m6896d().toString()), String.class);
                    }
                });
            }
        });
        if (arguments != null) {
            getActionBarController().m7193a((CharSequence) arguments.getString("key_list_title"));
            if (!arguments.getBoolean("key_list_creatable")) {
                this.mRootView.findViewById(R.id.layout_create_playlist).setVisibility(View.GONE);
            }
        }
    }

    public GroupListFragment getFragmentInstance() {
        if (this.mGroupListFragment == null) {
            Bundle arguments = getArguments();
            if (arguments != null && !arguments.getBoolean("key_list_draggable")) {
                this.mGroupListFragment = (GroupListFragment) Fragment.instantiate(getActivity(), GroupListFragment.class.getName(), arguments);
            } else {
                this.mGroupListFragment = (DraggableGroupListFragment) Fragment.instantiate(getActivity(), DraggableGroupListFragment.class.getName(), arguments);
            }
        }
        return this.mGroupListFragment;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mRootView, "BackgroundMaskColor");
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.content_custom_group), "BackgroundMaskColor");
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.layout_create_playlist), ThemeElement.SONG_LIST_ITEM_BACKGROUND);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected Collection<ActionItem> onCreateDropDownMenu() {
        //SUserUtils.m4956a(SAction.ACTION_OPEN_LOCAL_DROP_MENU, SPage.PAGE_NONE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ActionItem(4, 0, (int) R.string.menu_scan_media));
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        switch (i) {
            case 4:
                //SUserUtils.m4956a(SAction.ACTION_MENU_SCAN_MUSIC, SPage.PAGE_SCAN_MUSIC);
                EntryUtils.m8289f(getActivity());
                return;
            default:
                return;
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected void onSearchActionClicked() {
        if (this.mGroupListFragment != null) {
            this.mGroupListFragment.search();
            //LocalStatistic.m5127af();
        }
    }
}
