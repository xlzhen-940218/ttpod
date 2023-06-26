package com.sds.android.ttpod.fragment.main.list;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.view.AnimationImageView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class PlayerMediaListFragment extends PlayingFragment {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.PlayingFragment, com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.ON_PLAYING_PANEL_SHOW, ReflectUtils.m8375a(getClass(), "scrollToPlayingRow", new Class[0]));
    }

    @Override // com.sds.android.ttpod.fragment.main.list.PlayingFragment, com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected boolean isAZSideBarEnable() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.PlayingFragment, com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void configListFooterView(View view) {
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        return null;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment
    public void updateAsyncloadMediaItemList(String str, AsyncLoadMediaItemList asyncLoadMediaItemList) {
        super.updateAsyncloadMediaItemList(str, asyncLoadMediaItemList);
        if (str.equals(this.mGroupID)) {
            this.mListView.setFastScrollEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View onCreateFailedView(LayoutInflater layoutInflater) {
        View onCreateFailedView = super.onCreateFailedView(layoutInflater);
        onCreateFailedView.setEnabled(false);
        return onCreateFailedView;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment
    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setText(R.string.icon_no_playing);
        textView.setText(R.string.no_song_playing);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.player_media_list_item, (ViewGroup) null);
            view.setTag(new C1669a(view));
        }
        bindView((C1669a) view.getTag(), mediaItem);
        return view;
    }

    private void bindView(C1669a c1669a, MediaItem mediaItem) {
        c1669a.m5450b().setText(mediaItem.getTitle());
        c1669a.m5449c().setText(mediaItem.getArtist());
        c1669a.m5451a(StringUtils.equals(this.mGroupID, Preferences.getLocalGroupId()) && StringUtils.equals(Preferences.getMediaId(), mediaItem.getID()), this.mPlayStatus);
        c1669a.m3250a(ThemeUtils.m8163b());
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ThemeUtils.m8178a(this.mListView, ThemeElement.PLAYER_MUSIC_LIST_SEPARATOR, ThemeElement.COMMON_SEPARATOR);
        ThemeUtils.m8162b(this.mFailedView);
    }

    public void scrollToPlayingRow() {
        selectRow(SupportFactory.m2397a(BaseApplication.getApplication()).m2458r());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.list.PlayerMediaListFragment$a */
    /* loaded from: classes.dex */
    public class C1669a extends ThemeManager.AbstractC2018a {

        /* renamed from: b */
        private View f5408b;

        /* renamed from: c */
        private TextView f5409c;

        /* renamed from: d */
        private TextView f5410d;

        /* renamed from: e */
        private AnimationImageView f5411e;

        public C1669a(View view) {
            this.f5408b = view;
            this.f5409c = (TextView) view.findViewById(R.id.title_view);
            this.f5410d = (TextView) view.findViewById(R.id.media_item_singer);
            this.f5411e = (AnimationImageView) view.findViewById(R.id.play_icon);
        }

        /* renamed from: b */
        public TextView m5450b() {
            return this.f5409c;
        }

        /* renamed from: c */
        public TextView m5449c() {
            return this.f5410d;
        }

        /* renamed from: a */
        public void m5451a(boolean z, PlayStatus playStatus) {
            this.f5408b.setSelected(z);
            if (z) {
                this.f5411e.setVisibility(View.VISIBLE);
                AnimationDrawable m8158e = ThemeUtils.m8158e();
                if (m8158e.getNumberOfFrames() == 0) {
                    m8158e = (AnimationDrawable) PlayerMediaListFragment.this.getResources().getDrawable(R.drawable.xml_spectrum_animation);
                    int dimensionPixelSize = PlayerMediaListFragment.this.getResources().getDimensionPixelSize(R.dimen.spectrum_padding);
                    this.f5411e.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                } else {
                    this.f5411e.setPadding(0, 0, 0, 0);
                }
                this.f5411e.setAnimationDrawable(m8158e);
                boolean z2 = playStatus == PlayStatus.STATUS_PLAYING;
                this.f5411e.m3504a();
                if (!z2) {
                    this.f5411e.m3499b();
                    return;
                }
                return;
            }
            this.f5411e.setVisibility(View.INVISIBLE);
            this.f5411e.m3499b();
        }

        @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.AbstractC2018a
        /* renamed from: a */
        public void mo3251a() {
            ThemeManager.m3269a(this.f5408b, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND);
            ThemeManager.m3269a(this.f5409c, ThemeElement.PLAYER_MUSIC_LIST_TEXT);
            ThemeManager.m3269a(this.f5410d, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT);
        }
    }
}
