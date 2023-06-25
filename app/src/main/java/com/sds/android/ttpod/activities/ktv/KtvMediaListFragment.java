package com.sds.android.ttpod.activities.ktv;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.MediaItemMenuHolder;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.fragment.main.list.DraggableMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.List;

/* loaded from: classes.dex */
public class KtvMediaListFragment extends DraggableMediaListFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void setViewTagHolder(View view) {
        KtvMediaItemViewHolder ktvMediaItemViewHolder = new KtvMediaItemViewHolder(view);
        ktvMediaItemViewHolder.getExpandable().setTag(new MediaItemMenuHolder(ktvMediaItemViewHolder.getExpandable()));
        view.setTag(ktvMediaItemViewHolder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.ktv_list_media_empty, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.DraggableMediaListFragment, com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void configFailedView(View view) {
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected boolean needFailedState() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void updateMediaList(List<MediaItem> list) {
        super.updateMediaList(list);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof SubMediaListFragment) {
            ((SubMediaListFragment) parentFragment).getActionBarController().m7191a(list.isEmpty());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment
    public void bindView(MediaItemViewHolder mediaItemViewHolder, MediaItem mediaItem, boolean z) {
        super.bindView(mediaItemViewHolder, mediaItem, z);
        if (mediaItemViewHolder instanceof KtvMediaItemViewHolder) {
            KtvMediaItemViewHolder ktvMediaItemViewHolder = (KtvMediaItemViewHolder) mediaItemViewHolder;
            ktvMediaItemViewHolder.m8122a(!z);
            ktvMediaItemViewHolder.getMenuView().setVisibility(View.GONE);
            ktvMediaItemViewHolder.getFlagOnlineView().setVisibility(View.GONE);
            ktvMediaItemViewHolder.getFlagMvView().setVisibility(View.GONE);
            ktvMediaItemViewHolder.getFlagQualityView().setVisibility(View.GONE);
        }
    }
}
