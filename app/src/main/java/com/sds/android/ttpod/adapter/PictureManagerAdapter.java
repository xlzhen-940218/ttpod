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
    private final Animation animation;

    /* renamed from: b */
    private Context context;

    /* renamed from: c */
    private ArrayList<PictureDataItem> pictureDataItemArrayList;

    /* renamed from: d */
    private String f3126d;

    /* renamed from: e */
    private MediaItem mediaItem;

    /* renamed from: f */
    private boolean f3128f;

    /* renamed from: g */
    private NetworkLoadView networkLoadView;

    /* renamed from: h */
    private boolean f3130h;

    /* renamed from: i */
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    /* renamed from: j */
    private View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PictureManagerAdapter.this.f3130h = true;
            final PictureDataItem pictureDataItem = (PictureDataItem) view.getTag();
            final PictureManagerViewHolder.PictureManagerSubLayoutViewHolder c0953a = (PictureManagerViewHolder.PictureManagerSubLayoutViewHolder) view.getTag(R.id.view_tag_view_holder);
            LogUtils.debug("PictureManagerAdapter", "onClick item=%s", pictureDataItem.getLocalLyricPath());
            if (pictureDataItem.f3145g) {
                pictureDataItem.f3145g = FileUtils.exists(pictureDataItem.getLocalLyricPath()) ? false : true;
                c0953a.m7665a(pictureDataItem);
                String str = TTPodConfig.getArtistPath() + File.separator + PictureManagerAdapter.this.f3126d + File.separator;
                ArrayList<Integer> m2128b = PictureSearchTask.m2128b(str);
                if (m2128b == null) {
                    m2128b = new ArrayList<>();
                }
                m2128b.add(Integer.valueOf(pictureDataItem.getId()));
                PictureSearchTask.m2134a(str, m2128b);
            } else if (!pictureDataItem.f3144f) {
                OfflineModeUtils.m8255a(PictureManagerAdapter.this.context, new DialogInterface.OnClickListener() { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.3.1
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
        public void m7675a(PictureDataItem pictureDataItem, PictureManagerViewHolder.PictureManagerSubLayoutViewHolder c0953a) {
            pictureDataItem.f3144f = true;
            c0953a.m7665a(pictureDataItem);
            TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<PictureDataItem, PictureDataItem>(pictureDataItem) { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.3.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public PictureDataItem inBackground(PictureDataItem pictureDataItem2) {
                    PictureManagerAdapter.this.reentrantReadWriteLock.readLock().lock();
                    if (PictureManagerAdapter.this.context == null || PictureManagerAdapter.this.mediaItem == null) {
                        return null;
                    }
                    PictureManagerAdapter.this.reentrantReadWriteLock.readLock().unlock();
                    HttpRequest.Response m8708a = HttpRequest.m8708a(new HttpGet(pictureDataItem2.getUrl()), (HashMap<String, Object>) null, (HashMap<String, Object>) null);
                    InputStream m8688e = m8708a != null ? m8708a.getInputStream() : null;
                    if (m8688e != null) {
                        String str = pictureDataItem2.getLocalLyricPath() + ".tmp";
                        File parent = new File(str).getParentFile();
                        if (parent != null) {
                            FileUtils.createFolder(parent.getAbsolutePath());
                        }
                        if (FileUtils.writeInputStreamToFile(m8688e, str)) {
                            FileUtils.renameFile(str, pictureDataItem2.getLocalLyricPath());
                            return pictureDataItem2;
                        }
                        FileUtils.exists(str);
                        return pictureDataItem2;
                    }
                    return pictureDataItem2;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: b  reason: avoid collision after fix types in other method */
                public void postExecute(PictureDataItem pictureDataItem2) {
                    PictureManagerAdapter.this.reentrantReadWriteLock.readLock().lock();
                    if (PictureManagerAdapter.this.context != null && PictureManagerAdapter.this.mediaItem != null) {
                        PictureManagerAdapter.this.reentrantReadWriteLock.readLock().unlock();
                        pictureDataItem2.f3144f = false;
                        pictureDataItem2.f3145g = FileUtils.isFile(pictureDataItem2.getLocalLyricPath());
                        PictureManagerAdapter.this.m7692a("download", PictureManagerAdapter.this.f3126d, pictureDataItem2.f3145g ? 1 : 2, pictureDataItem2.getId());
                        PictureManagerAdapter.this.notifyDataSetChanged();
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                /* renamed from: c */
                public void onCancelled(PictureDataItem pictureDataItem2) {
                    PictureManagerAdapter.this.reentrantReadWriteLock.readLock().lock();
                    if (PictureManagerAdapter.this.context != null && PictureManagerAdapter.this.mediaItem != null) {
                        PictureManagerAdapter.this.reentrantReadWriteLock.readLock().unlock();
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
        return this.mediaItem;
    }

    public PictureManagerAdapter(Context context, NetworkLoadView networkLoadView) {
        this.context = context;
        this.animation = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);
        this.networkLoadView = networkLoadView;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int size = this.pictureDataItemArrayList != null ? this.pictureDataItemArrayList.size() : 0;
        if (size > 0) {
            return ((size - 1) / 3) + 1;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    /* renamed from: a */
    public PictureDataItem getItem(int i) {
        return this.pictureDataItemArrayList.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(R.layout.picture_manager_item, viewGroup, false);
            view.setTag(new PictureManagerViewHolder(view));
        }
        PictureManagerViewHolder c0952a = (PictureManagerViewHolder) view.getTag();
        int size = this.pictureDataItemArrayList.size();
        int i2 = i * 3;
        PictureDataItem pictureDataItem = this.pictureDataItemArrayList.get(i2);
        int i3 = i2 + 1;
        int i4 = i3 + 1;
        c0952a.m7666a(pictureDataItem, i3 < size ? this.pictureDataItemArrayList.get(i3) : null, i4 < size ? this.pictureDataItemArrayList.get(i4) : null);
        return view;
    }

    /* renamed from: a */
    public void m7694a(final MediaItem mediaItem, String str) {
        this.reentrantReadWriteLock.writeLock().lock();
        this.mediaItem = mediaItem;
        this.reentrantReadWriteLock.writeLock().unlock();
        if (StringUtils.isEmpty(str)) {
            String artist = mediaItem.getArtist();
            if (TTTextUtils.isValidateMediaString(artist)) {
                this.f3126d = FileUtils.removeWrongCharacter(artist);
            } else {
                return;
            }
        } else {
            this.f3126d = str;
        }
        // 旧的 result.xml 缓存接口已失效，改为直接在线搜索网易云封面
        m7693a(this.f3126d);
    }

    /* renamed from: c */
    public boolean m7687c() {
        return this.f3128f;
    }

    /* renamed from: a */
    public void m7693a(final String str) {
        if (!this.f3128f && !StringUtils.isEmpty(str)) {
            this.f3130h = true;
            this.f3128f = true;
            this.pictureDataItemArrayList = null;
            notifyDataSetChanged();
            this.networkLoadView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
            TaskScheduler.m8582a(new TaskScheduler.SchedulerAsyncTask<Void, Integer>(null) { // from class: com.sds.android.ttpod.adapter.PictureManagerAdapter.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public Integer inBackground(Void r10) {
                    PictureManagerAdapter.this.reentrantReadWriteLock.readLock().lock();
                    try {
                        if (PictureManagerAdapter.this.context == null || PictureManagerAdapter.this.mediaItem == null) {
                            return 0;
                        }
                        ArrayList<ResultData> results = PictureSearchTask.searchArtistPictures(PictureManagerAdapter.this.mediaItem, str);
                        ArrayList<PictureDataItem> items = new ArrayList<>();
                        if (results != null) {
                            for (ResultData resultData : results) {
                                if (resultData == null || resultData.getLyricArray() == null) {
                                    continue;
                                }
                                for (ResultData.Item item : resultData.getLyricArray()) {
                                    if (item != null) {
                                        items.add(new PictureDataItem(item));
                                    }
                                }
                            }
                        }
                        if (items.isEmpty()) {
                            return 0;
                        }
                        PictureManagerAdapter.this.pictureDataItemArrayList = items;
                        String m8397o = FileUtils.removeWrongCharacter(str);
                        if (TTTextUtils.isValidateMediaString(m8397o)) {
                            PictureManagerAdapter.this.f3126d = m8397o;
                        }
                        PictureSearchTask.m2127b(PictureManagerAdapter.this.mediaItem.getID(), PictureManagerAdapter.this.f3126d);
                        return items.size();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        PictureManagerAdapter.this.reentrantReadWriteLock.readLock().unlock();
                    }
                    return -1;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                /* renamed from: a */
                public void postExecute(Integer num) {
                    if (PictureManagerAdapter.this.context != null && PictureManagerAdapter.this.mediaItem != null) {
                        PictureManagerAdapter.this.f3128f = false;
                        long longValue = PictureManagerAdapter.this.mediaItem.getSongID() == null ? 0L : PictureManagerAdapter.this.mediaItem.getSongID().longValue();
                        if (num.intValue() > 0) {
                            PictureManagerAdapter.this.m7692a("search", str, 1, longValue);
                            PictureManagerAdapter.this.networkLoadView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
                            PictureManagerAdapter.this.notifyDataSetChanged();
                            return;
                        }
                        PictureManagerAdapter.this.m7692a("search", str, 2, longValue);
                        if (num.intValue() == 0) {
                            PopupsUtils.m6721a("没有搜索到结果，修改歌手名后重试");
                            PictureManagerAdapter.this.networkLoadView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                            return;
                        }
                        PopupsUtils.m6721a("搜索歌手图片失败，请重试");
                        PictureManagerAdapter.this.networkLoadView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m7692a(String str, String str2, int i, long j) {
        this.reentrantReadWriteLock.readLock().lock();
        //StatisticUtils.m4907a("lyric_pic", str, "picture", i, j, str2, this.f3127e.getTitle());
        this.reentrantReadWriteLock.readLock().unlock();
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
            super(item.getType(), item.getUrl(), item.getLocalLyricPath(), item.getId());
            if (this.url != null) {
                if (this.url.contains("music.126.net")) {
                    // 网易云图片使用 query 参数生成缩略图，旧的 /144/192 路径拼接会 404
                    this.f3143e = this.url + (this.url.contains("?") ? "&" : "?") + "param=200y200";
                } else {
                    int lastIndexOf = this.url.lastIndexOf(47);
                    if (lastIndexOf > 0) {
                        this.f3143e = this.url.substring(0, lastIndexOf) + "/144/192" + this.url.substring(lastIndexOf);
                    }
                }
            }
            this.f3145g = FileUtils.isFile(item.getLocalLyricPath());
        }
    }

    /* renamed from: com.sds.android.ttpod.adapter.PictureManagerAdapter$a */
    /* loaded from: classes.dex */
    public class PictureManagerViewHolder {

        /* renamed from: b */
        private View convertView;

        /* renamed from: c */
        private PictureManagerSubLayoutViewHolder leftLayout;

        /* renamed from: d */
        private PictureManagerSubLayoutViewHolder centerLayout;

        /* renamed from: e */
        private PictureManagerSubLayoutViewHolder rightLayout;

        protected PictureManagerViewHolder(View view) {
            this.convertView = view;
            this.leftLayout = new PictureManagerSubLayoutViewHolder(this.convertView.findViewById(R.id.layout_left));
            this.centerLayout = new PictureManagerSubLayoutViewHolder(this.convertView.findViewById(R.id.layout_center));
            this.rightLayout = new PictureManagerSubLayoutViewHolder(this.convertView.findViewById(R.id.layout_right));
        }

        /* renamed from: a */
        protected void m7666a(PictureDataItem pictureDataItem, PictureDataItem pictureDataItem2, PictureDataItem pictureDataItem3) {
            this.leftLayout.m7665a(pictureDataItem);
            this.centerLayout.m7665a(pictureDataItem2);
            this.rightLayout.m7665a(pictureDataItem3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.sds.android.ttpod.adapter.PictureManagerAdapter$a$a */
        /* loaded from: classes.dex */
        public final class PictureManagerSubLayoutViewHolder {

            /* renamed from: b */
            private View convertView;

            /* renamed from: c */
            private ImageView imageView;

            /* renamed from: d */
            private ImageView imageViewBottom;

            private PictureManagerSubLayoutViewHolder(View view) {
                this.convertView = view;
                this.imageView = (ImageView) this.convertView.findViewById(R.id.imageview);
                this.imageViewBottom = (ImageView) this.convertView.findViewById(R.id.imageview_bottom);
                this.imageViewBottom.setOnClickListener(PictureManagerAdapter.this.onClickListener);
                this.imageViewBottom.setTag(R.id.view_tag_view_holder, this);
            }

            /* renamed from: a */
            protected void m7665a(PictureDataItem pictureDataItem) {
                this.convertView.setVisibility(pictureDataItem != null ? View.VISIBLE : View.INVISIBLE);
                if (pictureDataItem != null) {
                    try {
                        this.imageViewBottom.setTag(pictureDataItem);
                        ImageCacheUtils.displayImage(this.imageView, pictureDataItem.f3143e, this.imageView.getWidth(), this.imageView.getHeight(), (int) R.drawable.picture_manager_default);
                        if (!pictureDataItem.f3145g) {
                            if (pictureDataItem.f3144f) {
                                this.imageViewBottom.startAnimation(PictureManagerAdapter.this.animation);
                                this.imageViewBottom.setImageResource(R.drawable.img_button_artist_pic_downing_normal);
                            } else {
                                this.imageViewBottom.clearAnimation();
                                this.imageViewBottom.setImageResource(R.drawable.xml_artist_pic_down);
                            }
                        } else {
                            this.imageViewBottom.clearAnimation();
                            this.imageViewBottom.setImageResource(R.drawable.xml_artist_pic_delete);
                        }
                        return;
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        return;
                    }
                }
                this.imageView.setImageDrawable(null);
            }
        }
    }
}
