package com.sds.android.ttpod.activities.mediascan.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.mediascan.FilePickerActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class MediaScanAutoScanActivity extends SlidingClosableActivity {
    private static final int REQUEST_CODE_FILE_PICKER = 0;
    private static final String TAG = "MediaScanAutoScanActivity";
    private C0760a mAdapter;
    private View mAutoScanView;
    private Set<String> mAutoScanFolderSet = new HashSet();
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.mediascan.setting.MediaScanAutoScanActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linearlayout_mediascan_autoscan_listview_footer /* 2131231768 */:
                    Intent intent = new Intent(MediaScanAutoScanActivity.this, FilePickerActivity.class);
                    intent.putExtra(FilePickerActivity.KEY_EXTRA_SHOW_FILE_TYPE, 2);
                    intent.putExtra(FilePickerActivity.KEY_EXTRA_CONFIRMYPE, 1);
                    MediaScanAutoScanActivity.this.startActivityForResult(intent, 0);
                    return;
                case R.id.imageview_mediascan_autoscan_add /* 2131231769 */:
                case R.id.textview_mediascan_setting_auto_scan_tips /* 2131231770 */:
                default:
                    return;
                case R.id.textview_mediascan_setting_auto_scan_start /* 2131231771 */:
                    if (MediaScanAutoScanActivity.this.mAdapter.getCount() > 0) {
                        Intent intent2 = new Intent(MediaScanAutoScanActivity.this, MediaScanAnimationActivity.class);
                        intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, (String[]) MediaScanAutoScanActivity.this.mAutoScanFolderSet.toArray(new String[0]));
                        MediaScanAutoScanActivity.this.startActivity(intent2);
                        MediaScanAutoScanActivity.this.setResult(-1, null);
                        MediaScanAutoScanActivity.this.finish();
                        return;
                    }
                    return;
            }
        }
    };

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mediascan_auto_scan);
        setTitle(R.string.mediascan_setting_choose_auto_scan_foders);
        this.mAutoScanFolderSet = Preferences.m2866k() != null ? Preferences.m2866k() : this.mAutoScanFolderSet;
        ListView listView = (ListView) findViewById(R.id.listview_mediascan_auto_scan);
        View inflate = LayoutInflater.from(this).inflate(R.layout.mediascan_auto_scan_listview_footer, (ViewGroup) null);
        inflate.setOnClickListener(this.mOnClickListener);
        listView.addFooterView(inflate);
        this.mAdapter = new C0760a();
        listView.setAdapter((ListAdapter) this.mAdapter);
        this.mAutoScanView = findViewById(R.id.textview_mediascan_setting_auto_scan_start);
        this.mAutoScanView.setOnClickListener(this.mOnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtils.debug(TAG, "onPause");
        super.onPause();
        Preferences.m2930b(this.mAutoScanFolderSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 0:
                if (i2 != -1) {
                    return;
                }
                this.mAdapter.m8010a(intent.getStringArrayExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES));
                return;
            default:
                return;
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.mediascan.setting.MediaScanAutoScanActivity$a */
    /* loaded from: classes.dex */
    private final class C0760a extends BaseAdapter {

        /* renamed from: b */
        private List<String> f2717b;

        private C0760a() {
            this.f2717b = new ArrayList();
            this.f2717b.addAll(MediaScanAutoScanActivity.this.mAutoScanFolderSet);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m8010a(String[] strArr) {
            int length = strArr.length;
            if (strArr != null && length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!MediaScanAutoScanActivity.this.mAutoScanFolderSet.contains(strArr[i])) {
                        MediaScanAutoScanActivity.this.mAutoScanFolderSet.add(strArr[i]);
                        this.f2717b.add(strArr[i]);
                    }
                }
                LogUtils.debug(MediaScanAutoScanActivity.TAG, "setPaths=" + this.f2717b);
                notifyDataSetChanged();
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f2717b.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public String getItem(int i) {
            return this.f2717b.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            C0762b c0762b;
            if (view == null) {
                view = LayoutInflater.from(MediaScanAutoScanActivity.this).inflate(R.layout.mediascan_auto_scan_item, viewGroup, false);
                C0762b c0762b2 = new C0762b((TextView) view.findViewById(R.id.textview_mediascan_exclude_item_title), (TextView) view.findViewById(R.id.textview_mediascan_exclude_item_sub_title), (ImageView) view.findViewById(R.id.imageview_mediascan_auto_scan_item_remove));
                view.setTag(c0762b2);
                c0762b = c0762b2;
            } else {
                c0762b = (C0762b) view.getTag();
            }
            c0762b.f2722c.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.mediascan.setting.MediaScanAutoScanActivity.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    MediaScanAutoScanActivity.this.mAutoScanFolderSet.remove(C0760a.this.f2717b.get(i));
                    C0760a.this.f2717b.remove(i);
                    C0760a.this.notifyDataSetChanged();
                }
            });
            String item = getItem(i);
            c0762b.f2720a.setText(FileUtils.getFilename(item));
            c0762b.f2721b.setText(FileUtils.m8400l(item));
            return view;
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.mediascan.setting.MediaScanAutoScanActivity$b */
    /* loaded from: classes.dex */
    private static final class C0762b {

        /* renamed from: a */
        private TextView f2720a;

        /* renamed from: b */
        private TextView f2721b;

        /* renamed from: c */
        private ImageView f2722c;

        private C0762b(TextView textView, TextView textView2, ImageView imageView) {
            this.f2720a = textView;
            this.f2721b = textView2;
            this.f2722c = imageView;
        }
    }
}
