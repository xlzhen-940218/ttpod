package com.sds.android.ttpod.fragment.main.findsong.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.SingerData;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;

import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.adapter.SectionListAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.SingerCategoryHotDetailFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragmentNew;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.utils.ArtistUtils;
import com.sds.android.ttpod.widget.AZSideBar;
import com.sds.android.ttpod.widget.RoundedImageView;
import com.sds.android.ttpod.widget.SimpleGridView;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SingerListImageHeaderFragment extends SlidingClosableFragment {
    private static final int HEADER_MAX_SIZE = 8;
    private static final String[] SEGMENT_CHAR = {"A", VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY, "C", "D", "E", "F", "G", VersionUpdateConst.KEY_HIAPK_UPDATE_CATEGORY, "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", VersionUpdateConst.KEY_WANTOUJIA_UPDATE_CATEGORY, "X", "Y", "Z", "#"};
    protected AZSideBar mAZSideBar;
    protected View mFailedView;
    private String mHotCharacter;
    private int mId;
    protected ListView mListView;
    private C1601a mSingerListAdapter;
    protected StateView mStateView;
    private String mTitle;
    private C1604b mTopSingerManager;
    private ArrayList<SingerData> mSingerDataList = null;
    private ArrayList<SingerData> mHeaderSingerDataList = null;
    private View.OnClickListener mOnSingerClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View findViewById;
            SingerData singerData = (SingerData) view.getTag(R.id.view_bind_data);
            if (singerData == null && (findViewById = view.findViewById(R.id.singer_item)) != null) {
                singerData = (SingerData) findViewById.getTag(R.id.view_bind_data);
            }
            if (singerData != null) {
                //OnlineMediaStatistic.m5045a("library-music-singer_" + SingerListImageHeaderFragment.this.mTitle + "_" + //MusicLibraryStatistic.m5069a());
                Bundle bundle = new Bundle();
                SingerDetailFragmentNew singerDetailFragmentNew = new SingerDetailFragmentNew(singerData.getName());
                bundle.putString(SingerCategoryHotDetailFragment.KEY_CHANNEL, SingerListImageHeaderFragment.this.mTitle);
                bundle.putSerializable("key_data", singerData);
                singerDetailFragmentNew.setArguments(bundle);
                SingerListImageHeaderFragment.this.launchFragment(singerDetailFragmentNew);
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_SINGER_NAME.getValue(), SingerListImageHeaderFragment.this.mTitle, singerData.getName()).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(singerData.getId())).post();
            }
        }
    };

    public SingerListImageHeaderFragment(String str, int i) {
        this.mId = i;
        this.mTitle = str;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSingerListAdapter = new C1601a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SINGER_CATEGORY_DETAIL, ReflectUtils.m8375a(getClass(), "updateSingerListInfo", SingerListResult.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mStateView = (StateView) layoutInflater.inflate(R.layout.fragment_singer_list, viewGroup, false);
        this.mListView = (ListView) this.mStateView.findViewById(R.id.media_listview);
        this.mAZSideBar = (AZSideBar) this.mStateView.findViewById(R.id.azsidebar);
        this.mTopSingerManager = new C1604b(getActivity());
        this.mFailedView = layoutInflater.inflate(R.layout.loadingview_failed, (ViewGroup) null);
        this.mStateView.setFailedView(this.mFailedView);
        return this.mStateView;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mStateView = null;
        this.mListView = null;
        this.mFailedView = null;
        this.mAZSideBar = null;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7193a((CharSequence) this.mTitle);
        setupListView();
        this.mStateView.setState(StateView.EnumC2248b.LOADING);
        this.mStateView.setOnRetryRequestListener(new StateView.InterfaceC2247a() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment.1
            @Override // com.sds.android.ttpod.widget.StateView.InterfaceC2247a
            /* renamed from: a */
            public void mo1450a() {
                SingerListImageHeaderFragment.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                SingerListImageHeaderFragment.this.requestDataList(1);
            }
        });
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                SingerListImageHeaderFragment.this.mOnSingerClickListener.onClick(view2);
            }
        });
        this.mAZSideBar.setIndexWords(SEGMENT_CHAR);
        this.mAZSideBar.setOnMatchedPositionChangeListener(new AZSideBar.InterfaceC2161a() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment.3
            @Override // com.sds.android.ttpod.widget.AZSideBar.InterfaceC2161a
            /* renamed from: a */
            public void mo1905a(int i, String str) {
                SingerListImageHeaderFragment.this.selectSection(i, str);
            }
        });
        this.mListView.setOnScrollListener(this.mAZSideBar);
        this.mHotCharacter = getString(R.string.hot_singer_character);
        requestDataList(1);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
        this.mAZSideBar.onThemeLoaded();
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        this.mSingerListAdapter.notifyDataSetChanged();
        if (this.mTopSingerManager != null) {
            this.mTopSingerManager.m5514a();
        }
    }

    public void updateSingerListInfo(SingerListResult singerListResult) {
        if (isViewAccessAble()) {
            ArrayList<SingerData> dataList = singerListResult.getDataList();
            if (dataList != null && dataList.size() > 0) {
                int i = dataList.size() <= 8 ? 0 : 8;
                this.mHeaderSingerDataList = new ArrayList<>();
                if (i > 0) {
                    this.mHeaderSingerDataList.addAll(dataList.subList(0, i));
                }
                this.mSingerDataList = new ArrayList<>();
                this.mSingerDataList.addAll(dataList);
                TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<Object, List<String>>(null) { // from class: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment.4
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                    /* renamed from: c */
                    public List<String> mo1981a(Object obj) {
                        return SingerListImageHeaderFragment.this.buildRawAZKeys(SingerListImageHeaderFragment.this.mSingerDataList);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                    /* renamed from: a */
                    public void postExecute(List<String> list) {
                        if (SingerListImageHeaderFragment.this.isViewAccessAble()) {
                            SingerListImageHeaderFragment.this.updateAZKeys(list);
                            SingerListImageHeaderFragment.this.mTopSingerManager.m5512a(SingerListImageHeaderFragment.this.mHeaderSingerDataList);
                            SingerListImageHeaderFragment.this.mSingerListAdapter.m5521a(SingerListImageHeaderFragment.this.mSingerDataList);
                            SingerListImageHeaderFragment.this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                        }
                    }
                });
                return;
            }
            this.mStateView.setState(StateView.EnumC2248b.FAILED);
        }
    }

    private void setupListView() {
        this.mListView.addHeaderView(this.mTopSingerManager.m5511b());
        this.mListView.setAdapter((ListAdapter) this.mSingerListAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> buildRawAZKeys(ArrayList<SingerData> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Collections.sort(arrayList, new Comparator<SingerData>() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment.5
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(SingerData singerData, SingerData singerData2) {
                return PinyinUtils.buildKey(singerData.getName()).compareTo(PinyinUtils.buildKey(singerData2.getName()));
            }
        });
        Iterator<SingerData> it = arrayList.iterator();
        String str = null;
        while (it.hasNext()) {
            SingerData next = it.next();
            String buildKey = PinyinUtils.buildKey(next.getName());
            if (str == null) {
                arrayList2.add(buildKey.charAt(0) + "");
            } else if (str.charAt(0) != buildKey.charAt(0)) {
                arrayList2.add(buildKey.charAt(0) + "");
            }
            arrayList2.add(next.getName());
            str = buildKey;
        }
        return arrayList2;
    }

    protected void updateAZKeys(List<String> list) {
        DebugUtils.m8426a((Object) list, "AZKeys");
        if (isViewAccessAble()) {
            list.add(0, this.mHotCharacter);
            this.mAZSideBar.m1909a(list);
            this.mAZSideBar.m1914a(this.mHotCharacter, 0);
            this.mAZSideBar.setVisibility(View.VISIBLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectSection(int i, String str) {
        int i2 = 0;
        if (!str.equals(this.mHotCharacter)) {
            int m5522a = this.mSingerListAdapter.m5522a(str);
            i2 = m5522a >= 0 ? m5522a + this.mListView.getHeaderViewsCount() : -1;
        }
        if (i2 >= 0) {
            this.mListView.requestFocus();
            this.mListView.setSelection(i2);
        }
    }

    protected void requestDataList(int i) {
        CommandCenter.getInstance().m4606a(new Command(CommandID.GET_SINGER_CATEGORY_DETAIL, Integer.valueOf(this.mId), Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment$b */
    /* loaded from: classes.dex */
    public class C1604b {

        /* renamed from: b */
        private Context f5283b;

        /* renamed from: c */
        private int f5284c;

        /* renamed from: d */
        private View f5285d;

        /* renamed from: e */
        private TextView f5286e;

        /* renamed from: f */
        private SimpleGridView f5287f;

        /* renamed from: g */
        private final int f5288g = DisplayUtils.m7225c() / 4;

        public C1604b(Context context) {
            this.f5283b = context;
            this.f5285d = LayoutInflater.from(context).inflate(R.layout.fragment_singer_image_list_header, (ViewGroup) null);
            this.f5286e = (TextView) this.f5285d.findViewById(R.id.singer_header_name);
            this.f5287f = (SimpleGridView) this.f5285d.findViewById(R.id.singer_header_grid_view);
            if (this.f5287f != null) {
                this.f5287f.setNumColumns(4);
            }
            m5514a();
        }

        /* renamed from: a */
        public void m5514a() {
            ThemeManager.m3269a(this.f5286e, ThemeElement.SUB_BAR_TEXT);
            ThemeManager.m3269a(this.f5286e, ThemeElement.TILE_MASK);
            this.f5284c = ThemeManager.m3253d(ThemeElement.SONG_LIST_ITEM_TEXT);
        }

        /* renamed from: b */
        View m5511b() {
            return this.f5285d;
        }

        /* renamed from: a */
        void m5512a(List<SingerData> list) {
            if (list != null && list.size() > 0 && this.f5287f != null) {
                if (this.f5287f.getChildCount() > 0) {
                    this.f5287f.removeAllViews();
                }
                for (SingerData singerData : list) {
                    View m5510c = m5510c();
                    m5513a(m5510c, singerData);
                    this.f5287f.addView(m5510c);
                }
            }
        }

        /* renamed from: c */
        View m5510c() {
            return LayoutInflater.from(this.f5283b).inflate(R.layout.picture_with_bottom_text, (ViewGroup) null);
        }

        /* renamed from: a */
        void m5513a(View view, SingerData singerData) {
            if (view != null && singerData != null) {
                TextView textView = (TextView) view.findViewById(R.id.item_name);
                View findViewById = view.findViewById(R.id.item_click_view);
                textView.setGravity(17);
                textView.setTextColor(this.f5284c);
                textView.setText(singerData.getName());
                findViewById.setTag(R.id.view_bind_data, singerData);
                findViewById.setTag(R.id.view_tag_index, singerData.getName());
                findViewById.setOnClickListener(SingerListImageHeaderFragment.this.mOnSingerClickListener);
                ImageCacheUtils.m4752a((RoundedImageView) view.findViewById(R.id.item_picture), SingerListImageHeaderFragment.this.getImageUrl(singerData), this.f5288g, this.f5288g, (int) R.drawable.img_background_song_publish);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getImageUrl(SingerData singerData) {
        if (singerData == null) {
            return null;
        }
        return ArtistUtils.m8310a(singerData.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment$a */
    /* loaded from: classes.dex */
    public class C1601a extends SectionListAdapter {

        /* renamed from: c */
        private List<Map.Entry<String, ArrayList<SingerData>>> f5278c;

        private C1601a() {
        }

        /* renamed from: a */
        public void m5521a(ArrayList arrayList) {
            this.f5278c = m5518b(arrayList);
            m7572b();
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public int m5522a(String str) {
            int size = this.f5278c.size();
            for (int i = 0; i < size; i++) {
                Map.Entry<String, ArrayList<SingerData>> entry = this.f5278c.get(i);
                if (entry != null && entry.getKey().equalsIgnoreCase(str)) {
                    return m7567f(i);
                }
            }
            return -1;
        }

        /* renamed from: b */
        private List<Map.Entry<String, ArrayList<SingerData>>> m5518b(ArrayList<SingerData> arrayList) {
            HashMap<String, ArrayList<SingerData>> hashMap = new HashMap<>();
            Iterator<SingerData> it = arrayList.iterator();
            while (it.hasNext()) {
                SingerData next = it.next();
                if (next != null) {
                    String buildKey = PinyinUtils.buildKey(next.getName());
                    if (!"".equals(buildKey) && buildKey.length() > 1) {
                        String upperCase = buildKey.substring(0, 1).toUpperCase();
                        char charAt = upperCase.charAt(0);
                        String str = (charAt < 'A' || charAt > 'Z') ? "#" : upperCase;
                        ArrayList<SingerData> arrayList2 = hashMap.get(str);
                        if (arrayList2 != null) {
                            arrayList2.add(next);
                        } else {
                            ArrayList<SingerData> arrayList3 = new ArrayList<>();
                            arrayList3.add(next);
                            hashMap.put(str, arrayList3);
                        }
                    }
                }
            }
            return m5520a(hashMap);
        }

        /* renamed from: a */
        private List<Map.Entry<String, ArrayList<SingerData>>> m5520a(HashMap<String, ArrayList<SingerData>> hashMap) {
            ArrayList arrayList = new ArrayList(hashMap.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, ArrayList<SingerData>>>() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment.a.1
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(Map.Entry<String, ArrayList<SingerData>> entry, Map.Entry<String, ArrayList<SingerData>> entry2) {
                    if ("#".equals(entry.getKey())) {
                        return 1;
                    }
                    if ("#".equals(entry2.getKey())) {
                        return -1;
                    }
                    return entry.getKey().compareTo(entry2.getKey());
                }
            });
            return arrayList;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected int mo5528a() {
            return this.f5278c.size();
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected int mo5527a(int i) {
            Map.Entry<String, ArrayList<SingerData>> entry = this.f5278c.get(i);
            ArrayList<SingerData> value = entry != null ? entry.getValue() : null;
            if (value != null) {
                return value.size();
            }
            return 0;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected Object mo5526a(int i, int i2) {
            Map.Entry<String, ArrayList<SingerData>> entry = this.f5278c.get(i);
            ArrayList<SingerData> value = entry != null ? entry.getValue() : null;
            if (value != null) {
                return value.get(i2);
            }
            return null;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: c */
        protected Object mo5517c(int i) {
            Map.Entry<String, ArrayList<SingerData>> entry = this.f5278c.get(i);
            if (entry != null) {
                return entry.getKey();
            }
            return null;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected View mo5523a(ViewGroup viewGroup) {
            View inflate = this.f3241a.inflate(R.layout.singer_list_section_header, viewGroup, false);
            inflate.setTag(new C1603a(inflate));
            inflate.setClickable(false);
            return inflate;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: b */
        protected View mo5519b(ViewGroup viewGroup) {
            View inflate = this.f3241a.inflate(R.layout.singer_list_item, viewGroup, false);
            inflate.setTag(new C1603a(inflate));
            return inflate;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected void mo5524a(int i, View view) {
            Map.Entry<String, ArrayList<SingerData>> entry = this.f5278c.get(i);
            String key = entry != null ? entry.getKey() : null;
            if (!TextUtils.isEmpty(key)) {
                C1603a c1603a = (C1603a) view.getTag();
                ThemeManager.m3269a(c1603a.f5281b, ThemeElement.SUB_BAR_TEXT);
                ThemeManager.m3269a(c1603a.f5281b, ThemeElement.TILE_MASK);
                c1603a.f5281b.setText(key);
            }
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected void mo5525a(int i, int i2, View view) {
            Map.Entry<String, ArrayList<SingerData>> entry = this.f5278c.get(i);
            ArrayList<SingerData> value = entry != null ? entry.getValue() : null;
            SingerData singerData = value != null ? value.get(i2) : null;
            if (singerData != null) {
                C1603a c1603a = (C1603a) view.getTag();
                c1603a.f5281b.setText(singerData.getName());
                ThemeManager.m3269a(c1603a.f5281b, ThemeElement.SONG_LIST_ITEM_TEXT);
                ThemeManager.m3269a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
                ThemeManager.m3269a(view.findViewById(R.id.item_click_view), ThemeElement.HOME_ARROW_IMAGE);
                view.setTag(R.id.view_bind_data, singerData);
            }
        }

        /* renamed from: com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment$a$a */
        /* loaded from: classes.dex */
        private class C1603a {

            /* renamed from: b */
            private TextView f5281b;

            public C1603a(View view) {
                this.f5281b = (TextView) view.findViewById(R.id.artist_name);
            }
        }
    }
}
