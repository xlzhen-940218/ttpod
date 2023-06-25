package com.sds.android.ttpod.activities.local;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p072d.BaseSearchAdapter;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.MediaItemMenuHolder;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MoreOptionalDialog;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.MediaItemMenuClickStub;
import com.sds.android.ttpod.fragment.main.list.MediaListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MediaItemSearchActivity extends BaseSearchActivity {
    private String mGroupID;
    private C0738a mMediaItemSearchAdapter;
    private C0738a.C0740a mSelectedMatchableMediaItem;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onNewIntent(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mGroupID = intent.getStringExtra(AbsMediaListFragment.KEY_GROUP_ID);
        DebugUtils.m8426a(this.mGroupID, "mGroupID");
        CommandCenter.getInstance().m4606a(new Command(CommandID.QUERY_MEDIA_ITEM_LIST, this.mGroupID, Preferences.m2860l(this.mGroupID)));
    }

    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity
    protected BaseSearchAdapter onCreateAdapter() {
        this.mMediaItemSearchAdapter = new C0738a(this);
        return this.mMediaItemSearchAdapter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_MEDIA_LIST, ReflectUtils.m8375a(getClass(), "updateMediaList", String.class, List.class));
    }

    public void updateMediaList(String str, List<MediaItem> list) {
        if (this.mGroupID.equals(str)) {
            this.mMediaItemSearchAdapter.m8060a(list);
            onLoadDataFinished();
        }
    }

    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity, android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity
    public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - getListView().getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.mMediaItemSearchAdapter.getCount()) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.PLAY_GROUP, this.mGroupID, this.mMediaItemSearchAdapter.getItem(headerViewsCount).m8044a()));
        }
        finish();
    }

    @Override // com.sds.android.ttpod.activities.local.BaseSearchActivity
    public void onMediaItemLongClicked(AdapterView<?> adapterView, View view, int i, long j) {
        showRightContextMenu(this.mMediaItemSearchAdapter.getItem(i));
    }

    protected void showRightContextMenu(C0738a.C0740a c0740a) {
        this.mSelectedMatchableMediaItem = c0740a;
        MediaListFragment.showMediaRightContextMenu(this, this.mSelectedMatchableMediaItem.m8044a(), this.mGroupID, new BaseDialog.InterfaceC1064a<MoreOptionalDialog>() { // from class: com.sds.android.ttpod.activities.local.MediaItemSearchActivity.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MoreOptionalDialog moreOptionalDialog) {
                MediaItemSearchActivity.this.mMediaItemSearchAdapter.m7591a(MediaItemSearchActivity.this.mSelectedMatchableMediaItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeleteMediaItem(final MediaItem mediaItem) {
        PopupsUtils.m6738a(this, mediaItem, this.mGroupID, new BaseDialog.InterfaceC1064a<MoreOptionalDialog>() { // from class: com.sds.android.ttpod.activities.local.MediaItemSearchActivity.2
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MoreOptionalDialog moreOptionalDialog) {
                CommandCenter.getInstance().m4606a(new Command(CommandID.DELETE_MEDIA_ITEM, MediaItemSearchActivity.this.mGroupID, mediaItem, Boolean.valueOf(moreOptionalDialog.m6821b())));
                MediaItemSearchActivity.this.mMediaItemSearchAdapter.mo7590a(mediaItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.local.MediaItemSearchActivity$a */
    /* loaded from: classes.dex */
    public class C0738a extends BaseSearchAdapter {
        public C0738a(Activity activity) {
            super(activity);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public C0740a m8055b(MediaItem mediaItem) {
            for (BaseSearchAdapter.InterfaceC0967a interfaceC0967a : this.f3229f) {
                if (((C0740a) interfaceC0967a).m8044a().equals(mediaItem)) {
                    return (C0740a) interfaceC0967a;
                }
            }
            return null;
        }

        @Override // com.sds.android.ttpod.adapter.p072d.BaseSearchAdapter
        /* renamed from: a */
        public void mo7590a(MediaItem mediaItem) {
            C0740a m8055b = m8055b(mediaItem);
            this.f3229f.remove(m8055b);
            this.f3231h.remove(m8055b);
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public void m8060a(List<MediaItem> list) {
            if (list == null) {
                throw new IllegalArgumentException("mediaItems must not be null");
            }
            HashMap hashMap = new HashMap();
            for (MediaItem mediaItem : list) {
                this.f3229f.add(new C0740a(mediaItem, hashMap));
            }
            f3225c = "";
            f3226d = "";
            this.f3231h = this.f3229f;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f3231h.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public C0740a getItem(int i) {
            return (C0740a) this.f3231h.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        /* renamed from: a */
        private void m8061a(final MediaItemMenuHolder mediaItemMenuHolder, final int i) {
            final MediaItem m8044a = getItem(i).m8044a();
            mediaItemMenuHolder.m6980a(new MediaItemMenuClickStub(this.f3228b, MediaItemSearchActivity.this.mSearchResultListView, m8044a, i) { // from class: com.sds.android.ttpod.activities.local.MediaItemSearchActivity.a.1
                @Override // com.sds.android.ttpod.fragment.main.list.MediaItemMenuClickStub
                /* renamed from: a */
                protected void mo5441a(MediaItem mediaItem) {
                    MediaItemSearchActivity.this.showRightContextMenu(C0738a.this.m8055b(mediaItem));
                }

                @Override // com.sds.android.ttpod.fragment.main.list.MediaItemMenuClickStub
                /* renamed from: b */
                protected void mo5440b(MediaItem mediaItem) {
                    MediaItemSearchActivity.this.onDeleteMediaItem(mediaItem);
                }

                @Override // com.sds.android.ttpod.fragment.main.list.MediaItemMenuClickStub
                /* renamed from: a */
                protected void mo5444a() {
                    mediaItemMenuHolder.m6976a(m8044a, i);
                }
            });
        }

        /* renamed from: a */
        protected void m8066a(View view) {
            view.setTag(new MediaItemViewHolder(view));
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            MediaItem m8044a = MediaItemSearchActivity.this.mMediaItemSearchAdapter.getItem(i).m8044a();
            if (view == null) {
                view = View.inflate(this.f3228b, R.layout.media_list_item, null);
                m8066a(view);
            }
            MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) view.getTag();
            mediaItemViewHolder.updateExpandable(m8044a);
            m8061a((MediaItemMenuHolder) mediaItemViewHolder.getExpandable().getTag(), i);
            mediaItemViewHolder.m6970a(MediaItemSearchActivity.this.mSearchResultListView, m8044a, i, true);
            mediaItemViewHolder.m6953l().setVisibility(View.INVISIBLE);
            mediaItemViewHolder.m3250a(ThemeUtils.m8163b());
            mediaItemViewHolder.m6962c(m8044a);
            mediaItemViewHolder.m6955j().setText(getItem(i).m8043b());
            mediaItemViewHolder.m6969a(getItem(i).m8042d(), 0, false);
            return view;
        }

        /* renamed from: com.sds.android.ttpod.activities.local.MediaItemSearchActivity$a$a */
        /* loaded from: classes.dex */
        public final class C0740a implements BaseSearchAdapter.InterfaceC0967a {

            /* renamed from: b */
            private MediaItem f2677b;

            /* renamed from: c */
            private PinyinUtils.Pinyin f2678c;

            /* renamed from: d */
            private PinyinUtils.Pinyin f2679d;

            /* renamed from: e */
            private PinyinUtils.Pinyin f2680e;

            /* renamed from: f */
            private PinyinUtils.Pinyin f2681f;

            /* renamed from: g */
            private String f2682g;

            /* renamed from: h */
            private String f2683h;

            private C0740a(MediaItem mediaItem, Map<String, PinyinUtils.Pinyin[]> map) {
                PinyinUtils.Pinyin[] pinyinArr;
                this.f2677b = mediaItem;
                String str = (String) TTTextUtils.validateString(C0738a.this.f3228b, this.f2677b.getArtist());
                String title = this.f2677b.getTitle();
                if (!TextUtils.isEmpty(str)) {
                    this.f2683h = str.toUpperCase();
                }
                if (!TextUtils.isEmpty(title)) {
                    this.f2682g = title.toUpperCase();
                }
                PinyinUtils.Pinyin[] twoKindOfPinyin = PinyinUtils.getTwoKindOfPinyin(title);
                if (twoKindOfPinyin != null) {
                    this.f2678c = twoKindOfPinyin[0];
                    this.f2680e = twoKindOfPinyin[1];
                }
                if (str != null && map.containsKey(str)) {
                    pinyinArr = map.get(str);
                } else {
                    PinyinUtils.Pinyin[] twoKindOfPinyin2 = PinyinUtils.getTwoKindOfPinyin(str);
                    if (twoKindOfPinyin2 != null) {
                        map.put(str, twoKindOfPinyin2);
                    }
                    pinyinArr = twoKindOfPinyin2;
                }
                if (pinyinArr != null) {
                    this.f2679d = pinyinArr[0];
                    this.f2681f = pinyinArr[1];
                }
            }

            /* renamed from: a */
            public MediaItem m8044a() {
                return this.f2677b;
            }

            /* renamed from: b */
            public CharSequence m8043b() {
                if (TextUtils.isEmpty(C0738a.f3226d) || this.f2678c == null) {
                    return this.f2677b.getTitle();
                }
                int[] iArr = {0, 0, 0};
                if (!C0738a.this.m7589a(this.f2678c, iArr) && !C0738a.this.m7589a(this.f2680e, iArr)) {
                    C0738a.this.m7587a(this.f2682g, iArr);
                }
                if (iArr[2] > 0) {
                    SpannableString spannableString = new SpannableString(this.f2677b.getTitle());
                    spannableString.setSpan(C0738a.f3227e, iArr[1], iArr[2] + iArr[1], 33);
                    return spannableString;
                }
                return this.f2677b.getTitle();
            }

            /* renamed from: d */
            public CharSequence m8042d() {
                String str = (String) TTTextUtils.validateString(C0738a.this.f3228b, this.f2677b.getArtist());
                if (!TextUtils.isEmpty(C0738a.f3226d) && this.f2679d != null) {
                    int[] iArr = {0, 0, 0};
                    if (!C0738a.this.m7589a(this.f2679d, iArr) && !C0738a.this.m7589a(this.f2681f, iArr)) {
                        C0738a.this.m7587a(this.f2683h, iArr);
                    }
                    if (iArr[2] > 0) {
                        SpannableString spannableString = new SpannableString(str);
                        spannableString.setSpan(C0738a.f3227e, iArr[1], iArr[2] + iArr[1], 33);
                        return spannableString;
                    }
                    return str;
                }
                return str;
            }

            @Override // com.sds.android.ttpod.adapter.p072d.BaseSearchAdapter.InterfaceC0967a
            /* renamed from: c */
            public int[] mo7583c() {
                int[] iArr = {0, 0, 0};
                boolean a = C0738a.this.m7589a(this.f2678c, iArr);
                iArr[0] = 0;
                if (!a) {
                    a = C0738a.this.m7589a(this.f2679d, iArr);
                    if (!a) {
                        a = C0738a.this.m7589a(this.f2680e, iArr);
                        if (!a) {
                            a = C0738a.this.m7589a(this.f2681f, iArr);
                            if (!a) {
                                a = C0738a.this.m7587a(this.f2682g, iArr);
                                if (!a) {
                                    a = C0738a.this.m7587a(this.f2683h, iArr);
                                    if (a) {
                                        iArr[0] = 5;
                                    }
                                } else {
                                    iArr[0] = 4;
                                }
                            } else {
                                iArr[0] = 3;
                            }
                        } else {
                            iArr[0] = 2;
                        }
                    } else {
                        iArr[0] = 1;
                    }
                }
                if (a) {
                    return iArr;
                }
                return null;
            }
        }
    }
}
