package com.sds.android.ttpod.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.search.p127a.SearchTaskInfoUtils;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.p107a.StatisticUtils;
import com.sds.android.ttpod.framework.support.search.task.PictureSearchTask;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.http.client.methods.HttpGet;

/* loaded from: classes.dex */
public class PictureManagerAdapter extends BaseAdapter {

    /* renamed from: a */
    private final Animation f3123a;

    /* renamed from: b */
    private Context f3124b;

    /* renamed from: c */
    private ArrayList<PictureDataItem> f3125c;

    /* renamed from: d */
    private String f3126d;

    /* renamed from: e */
    private MediaItem f3127e;

    /* renamed from: f */
    private boolean f3128f;

    /* renamed from: g */
    private NetworkLoadView f3129g;

    /* renamed from: h */
    private boolean f3130h;

    /* renamed from: i */
    private ReentrantReadWriteLock f3131i = new ReentrantReadWriteLock();

    /* renamed from: j */
    private View.OnClickListener f3132j = new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PictureManagerAdapter.this.f3130h = true;
            final PictureDataItem pictureDataItem = (PictureDataItem) view.getTag();
            final C0952a.C0953a c0953a = (C0952a.C0953a) view.getTag(R.id.view_tag_view_holder);
            LogUtils.m8386a("PictureManagerAdapter", "onClick item=%s", pictureDataItem.m2172d());
            if (pictureDataItem.f3145g) {
                pictureDataItem.f3145g = FileUtils.m8404h(pictureDataItem.m2172d()) ? false : true;
                c0953a.m7665a(pictureDataItem);
                String str = TTPodConfig.m5289s() + File.separator + PictureManagerAdapter.this.f3126d + File.separator;
                ArrayList<Integer> m2128b = PictureSearchTask.m2128b(str);
                if (m2128b == null) {
                    m2128b = new ArrayList<>();
                }
                m2128b.add(Integer.valueOf(pictureDataItem.m2176a()));
                PictureSearchTask.m2134a(str, m2128b);
            } else if (!pictureDataItem.f3144f) {
                OfflineModeUtils.m8255a(PictureManagerAdapter.this.f3124b, new DialogInterface.OnClickListener() { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.3.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -1) {
                            m7675a(pictureDataItem, c0953a);
                        }
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m7675a(PictureDataItem pictureDataItem, C0952a.C0953a c0953a) {
            pictureDataItem.f3144f = true;
            c0953a.m7665a(pictureDataItem);
            TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<PictureDataItem, PictureDataItem>(pictureDataItem) { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.3.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public PictureDataItem mo1981a(PictureDataItem pictureDataItem2) {
                    PictureManagerAdapter.this.f3131i.readLock().lock();
                    if (PictureManagerAdapter.this.f3124b == null || PictureManagerAdapter.this.f3127e == null) {
                        return null;
                    }
                    PictureManagerAdapter.this.f3131i.readLock().unlock();
                    HttpRequest.C0586a m8708a = HttpRequest.m8708a(new HttpGet(pictureDataItem2.m2173c()), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
                    InputStream m8688e = m8708a != null ? m8708a.m8688e() : null;
                    if (m8688e != null) {
                        String str = pictureDataItem2.m2172d() + ".tmp";
                        if (FileUtils.m8420a(m8688e, str)) {
                            FileUtils.m8410c(str, pictureDataItem2.m2172d());
                            return pictureDataItem2;
                        }
                        FileUtils.m8404h(str);
                        return pictureDataItem2;
                    }
                    return pictureDataItem2;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: b  reason: avoid collision after fix types in other method */
                public void postExecute(PictureDataItem pictureDataItem2) {
                    PictureManagerAdapter.this.f3131i.readLock().lock();
                    if (PictureManagerAdapter.this.f3124b != null && PictureManagerAdapter.this.f3127e != null) {
                        PictureManagerAdapter.this.f3131i.readLock().unlock();
                        pictureDataItem2.f3144f = false;
                        pictureDataItem2.f3145g = FileUtils.m8414b(pictureDataItem2.m2172d());
                        PictureManagerAdapter.this.m7692a("download", PictureManagerAdapter.this.f3126d, pictureDataItem2.f3145g ? 1 : 2, pictureDataItem2.m2176a());
                        PictureManagerAdapter.this.notifyDataSetChanged();
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                /* renamed from: c */
                public void onCancelled(PictureDataItem pictureDataItem2) {
                    PictureManagerAdapter.this.f3131i.readLock().lock();
                    if (PictureManagerAdapter.this.f3124b != null && PictureManagerAdapter.this.f3127e != null) {
                        PictureManagerAdapter.this.f3131i.readLock().unlock();
                        pictureDataItem2.f3144f = false;
                        PictureManagerAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
    };

    /* renamed from: a */
    public boolean m7701a() {
        return this.f3130h;
    }

    /* renamed from: b */
    public MediaItem m7691b() {
        return this.f3127e;
    }

    public PictureManagerAdapter(Context context, NetworkLoadView networkLoadView) {
        this.f3124b = context;
        this.f3123a = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);
        this.f3129g = networkLoadView;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int size = this.f3125c != null ? this.f3125c.size() : 0;
        if (size > 0) {
            return ((size - 1) / 3) + 1;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public PictureDataItem getItem(int i) {
        return this.f3125c.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return getItem(i).m2176a();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.f3124b).inflate(R.layout.picture_manager_item, viewGroup, false);
            view.setTag(new C0952a(view));
        }
        C0952a c0952a = (C0952a) view.getTag();
        int size = this.f3125c.size();
        int i2 = i * 3;
        PictureDataItem pictureDataItem = this.f3125c.get(i2);
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        c0952a.m7666a(pictureDataItem, i3 < size ? this.f3125c.get(i3) : null, i4 < size ? this.f3125c.get(i4) : null);
        return view;
    }

    /* renamed from: a */
    public void m7694a(final MediaItem mediaItem, String str) {
        this.f3131i.writeLock().lock();
        this.f3127e = mediaItem;
        this.f3131i.writeLock().unlock();
        if (StringUtils.m8346a(str)) {
            String artist = mediaItem.getArtist();
            if (TTTextUtils.isValidateMediaString(artist)) {
                this.f3126d = FileUtils.m8397o(artist);
            } else {
                return;
            }
        } else {
            this.f3126d = str;
        }
        if (!FileUtils.m8419a(TTPodConfig.m5289s() + File.separator + this.f3126d + File.separator + "result.xml")) {
            this.f3129g.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
        } else {
            TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<Void, ArrayList<PictureDataItem>>(null) { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Removed duplicated region for block: B:63:0x010a A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public ArrayList<PictureDataItem> mo1981a(Void r11) {
                    FileInputStream fileInputStream = null;
                    FileInputStream fileInputStream2;
                    KXmlParser kXmlParser = null;
                    ArrayList<ResultData> m2139a = null;
                    Exception e;
                    Throwable th;
                    PictureManagerAdapter.this.f3131i.readLock().lock();
                    if (PictureManagerAdapter.this.f3124b == null || PictureManagerAdapter.this.f3127e == null) {
                        return null;
                    }
                    String str2 = TTPodConfig.m5289s() + File.separator + PictureManagerAdapter.this.f3126d + File.separator + "result.xml";
                    try {
                        kXmlParser = new KXmlParser();
                        fileInputStream = new FileInputStream(str2);
                    } catch (Exception e1) {
                        e = e1;
                        fileInputStream2 = null;
                    } catch (Throwable th1) {
                        th = th1;
                        fileInputStream = null;
                    }
                    try {
                        kXmlParser.setInput(fileInputStream, "UTF-8");
                        m2139a = PictureSearchTask.m2139a(kXmlParser, mediaItem, 7, PictureManagerAdapter.this.f3126d);
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream2 = fileInputStream;
                        try {
                            e.printStackTrace();
                            PictureManagerAdapter.this.f3131i.readLock().unlock();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream = fileInputStream2;
                            PictureManagerAdapter.this.f3131i.readLock().unlock();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                            //throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        PictureManagerAdapter.this.f3131i.readLock().unlock();
                        if (fileInputStream != null) {
                        }
                        //throw th;
                    }
                    if ((m2139a != null ? m2139a.size() : 0) <= 0) {
                        PictureManagerAdapter.this.f3131i.readLock().unlock();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                        }
                        return null;
                    }
                    ArrayList<PictureDataItem> arrayList = new ArrayList<>();
                    for (ResultData resultData : m2139a) {
                        if (resultData != null && resultData.m2179c() != null) {
                            ResultData.Item[] m2179c = resultData.m2179c();
                            for (ResultData.Item item : m2179c) {
                                if (item != null) {
                                    arrayList.add(new PictureDataItem(item));
                                }
                            }
                        }
                    }
                    PictureManagerAdapter.this.f3131i.readLock().unlock();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    return arrayList;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a */
                public void postExecute(ArrayList<PictureDataItem> arrayList) {
                    PictureManagerAdapter.this.f3131i.readLock().lock();
                    if (PictureManagerAdapter.this.f3124b != null && PictureManagerAdapter.this.f3127e != null) {
                        PictureManagerAdapter.this.f3125c = arrayList;
                        if (PictureManagerAdapter.this.f3125c == null || PictureManagerAdapter.this.f3125c.isEmpty()) {
                            PictureManagerAdapter.this.f3129g.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                        } else {
                            PictureManagerAdapter.this.f3129g.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
                        }
                        PictureManagerAdapter.this.f3131i.readLock().unlock();
                        PictureManagerAdapter.this.notifyDataSetChanged();
                    }
                }

                @Override // android.os.AsyncTask
                protected void onCancelled() {
                    super.onCancelled();
                    PictureManagerAdapter.this.f3131i.readLock().lock();
                    if (PictureManagerAdapter.this.f3124b != null && PictureManagerAdapter.this.f3127e != null) {
                        PictureManagerAdapter.this.f3125c = null;
                        PictureManagerAdapter.this.f3129g.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                        LogUtils.m8386a("PictureManagerAdapter", "onCancelled %s - %s", mediaItem.getArtist(), mediaItem.getTitle());
                        PictureManagerAdapter.this.f3131i.readLock().unlock();
                        PictureManagerAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    /* renamed from: c */
    public boolean m7687c() {
        return this.f3128f;
    }

    /* renamed from: a */
    public void m7693a(final String str) {
        final String m7688b = m7688b(str);
        if (!this.f3128f && !StringUtils.m8346a(m7688b)) {
            this.f3130h = true;
            this.f3128f = true;
            this.f3125c = null;
            notifyDataSetChanged();
            this.f3129g.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
            TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<Void, Integer>(null) { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public Integer mo1981a(Void r10) {
                    PictureManagerAdapter.this.f3131i.readLock().lock();
                    if (PictureManagerAdapter.this.f3124b == null || PictureManagerAdapter.this.f3127e == null) {
                        return 0;
                    }
                    HttpRequest.C0586a m8708a = HttpRequest.m8708a(new HttpGet(m7688b), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
                    String m8347a = StringUtils.m8347a(m8708a != null ? m8708a.m8688e() : null);
                    LogUtils.m8386a("PictureManagerAdapter", "requestList lookpic %s - %s resultContent=%s", PictureManagerAdapter.this.f3127e.getArtist(), PictureManagerAdapter.this.f3127e.getTitle(), m8347a);
                    if (m8347a != null) {
                        String trim = m8347a.trim();
                        try {
                            if (trim.startsWith("<?xml")) {
                                KXmlParser kXmlParser = new KXmlParser();
                                kXmlParser.setInput(new ByteArrayInputStream(trim.getBytes("UTF-8")), "UTF-8");
                                ArrayList<ResultData> m2139a = PictureSearchTask.m2139a(kXmlParser, (MediaItem) null, 2, (String) null);
                                int size = m2139a != null ? m2139a.size() : 0;
                                if (size > 0) {
                                    String m8397o = FileUtils.m8397o(str);
                                    if (TTTextUtils.isValidateMediaString(m8397o)) {
                                        PictureManagerAdapter.this.f3126d = m8397o;
                                    } else {
                                        PictureManagerAdapter.this.f3126d = FileUtils.m8397o(m2139a.get(0).m2181b());
                                    }
                                    PictureSearchTask.m2127b(PictureManagerAdapter.this.f3127e.getID(), PictureManagerAdapter.this.f3126d);
                                    FileUtils.m8416a(trim, TTPodConfig.m5289s() + File.separator + PictureManagerAdapter.this.f3126d + "/result.xml");
                                }
                                return Integer.valueOf(size);
                            }
                            LogUtils.m8382b("PictureManagerAdapter", "searchResult not xml: pic, %s - %s, result=%s", PictureManagerAdapter.this.f3127e.getArtist(), PictureManagerAdapter.this.f3127e.getTitle(), trim);
                            return -1;
                        } catch (Exception e) {
                            LogUtils.m8382b("PictureManagerAdapter", "searchResult found exception: pic, %s - %s, result=%s", PictureManagerAdapter.this.f3127e.getArtist(), PictureManagerAdapter.this.f3127e.getTitle(), trim);
                            e.printStackTrace();
                        } finally {
                            PictureManagerAdapter.this.f3131i.readLock().unlock();
                        }
                    }
                    return -1;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a */
                public void postExecute(Integer num) {
                    if (PictureManagerAdapter.this.f3124b != null && PictureManagerAdapter.this.f3127e != null) {
                        PictureManagerAdapter.this.f3128f = false;
                        long longValue = PictureManagerAdapter.this.f3127e.getSongID() == null ? 0L : PictureManagerAdapter.this.f3127e.getSongID().longValue();
                        if (num.intValue() > 0) {
                            PictureManagerAdapter.this.m7692a("search", str, 1, longValue);
                            PictureManagerAdapter.this.m7694a(PictureManagerAdapter.this.f3127e, PictureManagerAdapter.this.f3126d);
                            return;
                        }
                        PictureManagerAdapter.this.m7692a("search", str, 2, longValue);
                        if (num.intValue() == 0) {
                            PopupsUtils.m6721a("没有搜索到结果，修改歌手名后重试");
                            PictureManagerAdapter.this.f3129g.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                            return;
                        }
                        PopupsUtils.m6721a("搜索歌手图片失败，请重试");
                        PictureManagerAdapter.this.f3129g.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7692a(String str, String str2, int i, long j) {
        this.f3131i.readLock().lock();
        StatisticUtils.m4907a("lyric_pic", str, "picture", i, j, str2, this.f3127e.getTitle());
        this.f3131i.readLock().unlock();
    }

    /* renamed from: b */
    private String m7688b(String str) {
        if (StringUtils.m8346a(str)) {
            return null;
        }
        return PictureSearchTask.m2138a(SearchTaskInfoUtils.m3885b(this.f3127e, null, str));
    }

    /* loaded from: classes.dex */
    public static class PictureDataItem extends ResultData.Item {

        /* renamed from: e */
        private String f3143e;

        /* renamed from: f */
        private boolean f3144f;

        /* renamed from: g */
        private boolean f3145g;

        PictureDataItem(ResultData.Item item) {
            super(item.m2174b(), item.m2173c(), item.m2172d(), item.m2176a());
            int lastIndexOf;
            if (this.f7294b != null && (lastIndexOf = this.f7294b.lastIndexOf(47)) > 0) {
                this.f3143e = this.f7294b.substring(0, lastIndexOf) + "/144/192" + this.f7294b.substring(lastIndexOf);
            }
            this.f3145g = FileUtils.m8414b(item.m2172d());
        }
    }

    /* renamed from: com.sds.android.ttpod.adapter.PictureManagerAdapter$a */
    /* loaded from: classes.dex */
    public class C0952a {

        /* renamed from: b */
        private View f3147b;

        /* renamed from: c */
        private C0953a f3148c;

        /* renamed from: d */
        private C0953a f3149d;

        /* renamed from: e */
        private C0953a f3150e;

        protected C0952a(View view) {
            this.f3147b = view;
            this.f3148c = new C0953a(this.f3147b.findViewById(R.id.layout_left));
            this.f3149d = new C0953a(this.f3147b.findViewById(R.id.layout_center));
            this.f3150e = new C0953a(this.f3147b.findViewById(R.id.layout_right));
        }

        /* renamed from: a */
        protected void m7666a(PictureDataItem pictureDataItem, PictureDataItem pictureDataItem2, PictureDataItem pictureDataItem3) {
            this.f3148c.m7665a(pictureDataItem);
            this.f3149d.m7665a(pictureDataItem2);
            this.f3150e.m7665a(pictureDataItem3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.sds.android.ttpod.adapter.PictureManagerAdapter$a$a */
        /* loaded from: classes.dex */
        public final class C0953a {

            /* renamed from: b */
            private View f3152b;

            /* renamed from: c */
            private ImageView f3153c;

            /* renamed from: d */
            private ImageView f3154d;

            private C0953a(View view) {
                this.f3152b = view;
                this.f3153c = (ImageView) this.f3152b.findViewById(R.id.imageview);
                this.f3154d = (ImageView) this.f3152b.findViewById(R.id.imageview_bottom);
                this.f3154d.setOnClickListener(PictureManagerAdapter.this.f3132j);
                this.f3154d.setTag(R.id.view_tag_view_holder, this);
            }

            /* renamed from: a */
            protected void m7665a(PictureDataItem pictureDataItem) {
                this.f3152b.setVisibility(pictureDataItem != null ? View.VISIBLE : View.INVISIBLE);
                if (pictureDataItem != null) {
                    try {
                        this.f3154d.setTag(pictureDataItem);
                        ImageCacheUtils.m4752a(this.f3153c, pictureDataItem.f3143e, this.f3153c.getWidth(), this.f3153c.getHeight(), (int) R.drawable.picture_manager_default);
                        if (!pictureDataItem.f3145g) {
                            if (pictureDataItem.f3144f) {
                                this.f3154d.startAnimation(PictureManagerAdapter.this.f3123a);
                                this.f3154d.setImageResource(R.drawable.img_button_artist_pic_downing_normal);
                            } else {
                                this.f3154d.clearAnimation();
                                this.f3154d.setImageResource(R.drawable.xml_artist_pic_down);
                            }
                        } else {
                            this.f3154d.clearAnimation();
                            this.f3154d.setImageResource(R.drawable.xml_artist_pic_delete);
                        }
                        return;
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        return;
                    }
                }
                this.f3153c.setImageDrawable(null);
            }
        }
    }
}
