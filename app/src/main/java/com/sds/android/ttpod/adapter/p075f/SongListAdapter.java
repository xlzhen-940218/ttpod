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
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
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
    private List<MediaItem> f3397a;

    /* renamed from: b */
    private Activity f3398b;

    /* renamed from: c */
    private String f3399c;

    /* renamed from: d */
    private String f3400d;

    /* renamed from: e */
    private String f3401e;

    /* renamed from: f */
    private String f3402f;

    /* renamed from: g */
    private String f3403g;

    /* renamed from: h */
    private InterfaceC1003a f3404h;

    @Override
    public void mo6973a(MediaItem mediaItem, boolean z) {
        
    }

    /* compiled from: SongListAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.f.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1003a {
        /* renamed from: a */
        void mo5476a(MediaItem mediaItem, View view, MediaItemMenuHolder mediaItemMenuHolder, int i);
    }

    public SongListAdapter(Activity activity, String str) {
        this(activity, str, null);
    }

    public SongListAdapter(Activity activity, String str, List<MediaItem> list) {
        this.f3398b = activity;
        this.f3399c = str;
        this.f3397a = list;
    }

    /* renamed from: a */
    public void m7399a(String str) {
        this.f3400d = str;
    }

    /* renamed from: b */
    public void m7396b(String str) {
        this.f3403g = str;
    }

    /* renamed from: a */
    public String m7410a() {
        return this.f3402f;
    }

    /* renamed from: c */
    public void m7395c(String str) {
        this.f3402f = str;
    }

    /* renamed from: d */
    public void m7394d(String str) {
        this.f3401e = str;
    }

    /* renamed from: b */
    public List<MediaItem> m7397b() {
        return this.f3397a;
    }

    /* renamed from: a */
    public void m7398a(List<MediaItem> list) {
        this.f3397a = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.f3397a == null) {
            return 0;
        }
        return this.f3397a.size();
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public MediaItem getItem(int i) {
        int count = getCount();
        if (!ListUtils.m4717b(this.f3397a) || i >= count) {
            return null;
        }
        return this.f3397a.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.f3398b, R.layout.online_media_list_item, null);
            view.setTag(new MediaItemViewHolder(view));
        }
        MediaItem mediaItem = this.f3397a.get(i);
        MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) view.getTag();
        MediaItemMenuHolder mediaItemMenuHolder = (MediaItemMenuHolder) mediaItemViewHolder.getExpandable().getTag();
        mediaItemMenuHolder.m6979a(this);
        m7403a(mediaItemMenuHolder, mediaItem, i);
        if (StringUtils.isEmpty(this.f3402f)) {
            this.f3402f = Preferences.m2858m();
        }
        if (StringUtils.isEmpty(this.f3403g)) {
            this.f3403g = Preferences.m2854n();
        }
        boolean z = StringUtils.m8344a(this.f3399c, this.f3402f) && StringUtils.m8344a(this.f3403g, mediaItem.getID());
        mediaItemViewHolder.m6970a(null, mediaItem, i, (this.f3397a instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) this.f3397a).isLoadFinished());
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
                        SongListAdapter.this.m7401a(mediaItem);
                        return;
                    case R.id.media_menu_favor /* 2131230748 */:
                        mediaItemMenuHolder.m6976a(mediaItem, i);
                        return;
                    case R.id.media_menu_favor_icon /* 2131230749 */:
                    case R.id.media_menu_more /* 2131230750 */:
                    case R.id.media_menu_ring /* 2131230752 */:
                    default:
                        return;
                    case R.id.media_menu_mv /* 2131230751 */:
                        MvManager.m5557b(SongListAdapter.this.f3398b, new MvPopupDialogCallBack() { // from class: com.sds.android.ttpod.adapter.f.c.1.1
                            @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                            /* renamed from: a */
                            public void mo1219a() {
                                VideoPlayManager.m5816a(SongListAdapter.this.f3398b, mediaItem);
                            }

                            @Override // com.sds.android.ttpod.fragment.main.findsong.MvPopupDialogCallBack
                            /* renamed from: b */
                            public void mo1218b() {
                                MvManager.m5559a(mediaItem);
                            }
                        }, 0);
                        return;
                    case R.id.media_menu_share /* 2131230753 */:
                        PopupsUtils.m6756a(SongListAdapter.this.f3398b, mediaItem);
                        return;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
 

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7400a(MediaItem mediaItem, View view, MediaItemMenuHolder mediaItemMenuHolder, int i) {
        if (this.f3404h != null) {
            this.f3404h.mo5476a(mediaItem, view, mediaItemMenuHolder, i);
        }
    }


    /* renamed from: a */
    public void m7401a(MediaItem mediaItem) {
        if (this.f3400d != null && this.f3401e != null) {
            //StatisticUtils.m4907a(this.f3401e, "menu", this.f3400d, 0L, //OnlineMediaStatistic.m5029f(), mediaItem.getTitle(), UUID.randomUUID().toString());
        }
        new DownloadMenuHandler(this.f3398b).m6927a(mediaItem, this.f3400d);
    }

    /* renamed from: a */
    public void m7408a(int i, boolean z) {
        //OnlineMediaStatistic.m5053a(i + 1);
        MediaItem item = getItem(i);
        if (item != null) {
            LogUtils.debug("ListStatistic", "onMediaItemClicked=" + item.getSongID() + "," + item.getTitle());
            if (z) {
                //OnlineMediaStatistic.m5047a(Integer.valueOf(//ListStatistic.m5212a()));
                //OnlineMediaStatistic.m5038b(//ListStatistic.m5203b());
                //OnlineMediaStatistic.m5046a(item.getSongID());
                //OnlineMediaStatistic.m5034c(//ListStatistic.m5201c());
            } else {
                //OnlineMediaStatistic.m5047a((Integer) (-1));
                //OnlineMediaStatistic.m5038b((String) null);
                //OnlineMediaStatistic.m5046a((Long) (-1L));
                //OnlineMediaStatistic.m5034c(null);
            }
            //OnlineMediaStatistic.m5045a(this.f3400d);
            //OnlineMediaStatistic.m5054a();
            if (StringUtils.m8344a(this.f3399c, Preferences.m2858m()) && StringUtils.m8344a(item.getID(), Preferences.m2854n())) {
                PlayStatus m2463m = SupportFactory.m2397a(BaseApplication.getApplication()).m2463m();
                if (m2463m == PlayStatus.STATUS_PAUSED) {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.RESUME, new Object[0]));
                    return;
                } else if (m2463m == PlayStatus.STATUS_PLAYING) {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.PAUSE, new Object[0]));
                    return;
                } else {
                    CommandCenter.getInstance().m4606a(new Command(CommandID.START, new Object[0]));
                    return;
                }
            }
            CommandCenter.getInstance().m4606a(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, m7397b()));
            CommandCenter.getInstance().m4606a(new Command(CommandID.PLAY_GROUP, this.f3399c, item));
        }
    }

    /* renamed from: a */
    public void m7407a(InterfaceC1003a interfaceC1003a) {
        this.f3404h = interfaceC1003a;
    }
}
