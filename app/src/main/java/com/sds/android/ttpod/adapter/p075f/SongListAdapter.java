package com.sds.android.ttpod.adapter.p075f;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p085b.MediaItemMenuHolder;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.main.findsong.MvManager;
import com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.List;

/* renamed from: com.sds.android.ttpod.adapter.f.c */
/* loaded from: classes.dex */
public class SongListAdapter extends BaseAdapter implements MediaItemMenuHolder.InterfaceC1136a {

    /* renamed from: a */
    private List<MediaItem> mediaItems;

    /* renamed from: b */
    private Activity activity;

    /* renamed from: c */
    private String groupId;

    /* renamed from: d */
    private String origin;

    /* renamed from: e */
    private String module;

    /* renamed from: f */
    private String localGroupId;

    /* renamed from: g */
    private String mediaId;

    /* renamed from: h */
    private OnItemClickListener onItemClickListener;

    @Override
    public void mo6973a(MediaItem mediaItem, boolean z) {
        
    }

    /* compiled from: SongListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.f.c$a */
    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        /* renamed from: a */
        void onItemClick(MediaItem mediaItem, View view, MediaItemMenuHolder mediaItemMenuHolder, int position);
    }

    public SongListAdapter(Activity activity, String groupId) {
        this(activity, groupId, null);
    }

    public SongListAdapter(Activity activity, String groupId, List<MediaItem> list) {
        this.activity = activity;
        this.groupId = groupId;
        this.mediaItems = list;
    }

    /* renamed from: a */
    public void setOrigin(String str) {
        this.origin = str;
    }

    /* renamed from: b */
    public void setMediaId(String str) {
        this.mediaId = str;
    }

    /* renamed from: a */
    public String getLocalGroupId() {
        return this.localGroupId;
    }

    /* renamed from: c */
    public void setLocalGroupId(String str) {
        this.localGroupId = str;
    }

    /* renamed from: d */
    public void setModule(String str) {
        this.module = str;
    }

    /* renamed from: b */
    public List<MediaItem> getMediaItems() {
        return this.mediaItems;
    }

    /* renamed from: a */
    public void setMediaItems(List<MediaItem> list) {
        this.mediaItems = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mediaItems == null) {
            return 0;
        }
        return this.mediaItems.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public MediaItem getItem(int i) {
        int count = getCount();
        if (!ListUtils.m4717b(this.mediaItems) || i >= count) {
            return null;
        }
        return this.mediaItems.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.activity, R.layout.online_media_list_item, null);
            view.setTag(new MediaItemViewHolder(view));
        }
        MediaItem mediaItem = this.mediaItems.get(i);
        MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) view.getTag();
        MediaItemMenuHolder mediaItemMenuHolder = (MediaItemMenuHolder) mediaItemViewHolder.getExpandable().getTag();
        mediaItemMenuHolder.m6979a(this);
        m7403a(mediaItemMenuHolder, mediaItem, i);
        if (StringUtils.isEmpty(this.localGroupId)) {
            this.localGroupId = Preferences.getLocalGroupId();
        }
        if (StringUtils.isEmpty(this.mediaId)) {
            this.mediaId = Preferences.getMediaId();
        }
        boolean z = StringUtils.equals(this.groupId, this.localGroupId) && StringUtils.equals(this.mediaId, mediaItem.getID());
        mediaItemViewHolder.m6970a(null, mediaItem, i, (this.mediaItems instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) this.mediaItems).isLoadFinished());
        mediaItemViewHolder.m6971a(mediaItem, (PlayStatus) null, z);
        view.setEnabled(z ? false : true);
        view.setSelected(z);
        return view;
    }

    /* renamed from: a */
    protected void m7403a(final MediaItemMenuHolder mediaItemMenuHolder, final MediaItem mediaItem, final int i) {
        mediaItemMenuHolder.m6980a(new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.f.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                //SUserUtils.m4958a(i);
                SongListAdapter.this.m7400a(mediaItem, view, mediaItemMenuHolder, i);
                switch (view.getId()) {
                    case R.id.media_menu_download /* 2131230747 */:
                        SongListAdapter.this.download(mediaItem);
                        return;
                    case R.id.media_menu_favor /* 2131230748 */:
                        mediaItemMenuHolder.favority(mediaItem, i);
                        return;
                    case R.id.media_menu_favor_icon /* 2131230749 */:
                    case R.id.media_menu_more /* 2131230750 */:
                    case R.id.media_menu_ring /* 2131230752 */:
                    default:
                        return;
                    case R.id.media_menu_mv /* 2131230751 */:
                        MvManager.showMv(SongListAdapter.this.activity, new MvPopupDialogCallBack() { // from class: com.sds.android.ttpod.adapter.f.c.1.1
                            @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                            /* renamed from: a */
                            public void onSuccess() {
                                VideoPlayManager.playVideo(SongListAdapter.this.activity, mediaItem);
                            }

                            @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                            /* renamed from: b */
                            public void mo1218b() {
                                MvManager.m5559a(mediaItem);
                            }
                        }, 0);
                        return;
                    case R.id.media_menu_share /* 2131230753 */:
                        PopupsUtils.shareMediaItem(SongListAdapter.this.activity, mediaItem);
                        return;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
 

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7400a(MediaItem mediaItem, View view, MediaItemMenuHolder mediaItemMenuHolder, int i) {
        if (this.onItemClickListener != null) {
            this.onItemClickListener.onItemClick(mediaItem, view, mediaItemMenuHolder, i);
        }
    }


    /* renamed from: a */
    public void download(MediaItem mediaItem) {
        new DownloadMenuHandler(this.activity).m6927a(mediaItem, this.origin);
    }

    /* renamed from: a */
    public void m7408a(int i, boolean z) {
        //OnlineMediaStatistic.m5053a(i + 1);
        MediaItem item = getItem(i);
        if (item != null) {
            LogUtils.debug("ListStatistic", "onMediaItemClicked=" + item.getSongID() + "," + item.getTitle());
            if (StringUtils.equals(this.groupId, Preferences.getLocalGroupId()) && StringUtils.equals(item.getID(), Preferences.getMediaId())) {
                PlayStatus m2463m = SupportFactory.getInstance(BaseApplication.getApplication()).m2463m();
                if (m2463m == PlayStatus.STATUS_PAUSED) {
                    CommandCenter.getInstance().execute(new Command(CommandID.RESUME, new Object[0]));
                    return;
                } else if (m2463m == PlayStatus.STATUS_PLAYING) {
                    CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
                    return;
                } else {
                    CommandCenter.getInstance().execute(new Command(CommandID.START, new Object[0]));
                    return;
                }
            }
            CommandCenter.getInstance().execute(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, getMediaItems()));
            CommandCenter.getInstance().execute(new Command(CommandID.PLAY_GROUP, this.groupId, item));
        }
    }

    /* renamed from: a */
    public void m7407a(OnItemClickListener interfaceC1003a) {
        this.onItemClickListener = interfaceC1003a;
    }
}
