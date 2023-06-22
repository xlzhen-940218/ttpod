package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.view.View;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class PlayingFragment extends MediaListFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.PLAY_GROUP_CHANGED, ReflectUtils.m8375a(getClass(), "playGroupChanged", new Class[0]));
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected boolean isAZSideBarEnable() {
        String groupID = getGroupID();
        return (groupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) || groupID.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) || groupID.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) && (Preferences.m2860l(groupID).equals("title_key") || Preferences.m2860l(groupID).equals("artist_key"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void configFailedView(View view) {
        super.configFailedView(view);
        view.findViewById(R.id.no_data_action_view).setVisibility(View.GONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void configListFooterView(View view) {
        super.configListFooterView(view);
        onFlushMediaItemCountView();
    }

    protected void onFlushMediaItemCountView() {
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        onFlushMediaItemCountView();
    }

    public void playGroupChanged() {
        this.mGroupID = Preferences.m2858m();
        onReloadData();
    }
}
