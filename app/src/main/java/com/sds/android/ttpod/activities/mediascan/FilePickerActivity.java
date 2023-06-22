package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.ListViewUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

/* loaded from: classes.dex */
public class FilePickerActivity extends SlidingClosableActivity {
    public static final int CHOICE_MODE_MULTI = 2;
    public static final int CHOICE_MODE_SINGLE = 1;
    public static final int CONFIRMYPE_ADD = 1;
    public static final int CONFIRMYPE_CONFIRM = 0;
    public static final int CONFIRMYPE_SCAN = 2;
    public static final int CONFIRMYPE_UNKNOWN = 3;
    public static final String DEFAULT_MEDIA_FILE_EXTENSIONS = "mp3|wma|aac|m4a|amr|ape|flac|awb|imy|mid|midi|oga|ogg|ota|rtttl|rtx|smf|wav|xmf|cue|";
    public static final int FILE_TYPE_ALL = 0;
    public static final int FILE_TYPE_DIRECTORY = 2;
    public static final int FILE_TYPE_FILE = 1;
    public static final String KEY_EXTRA_CHOICE_MODE = "key_choice_mode";
    public static final String KEY_EXTRA_CONFIRMYPE = "confirm_text";
    public static final String KEY_EXTRA_EXCLUDED_EXTENSIONS = "key_extensions";
    public static final String KEY_EXTRA_INCLUDE_EXTENSIONS = "";
    public static final String KEY_EXTRA_ISSELECT_MEDIA = "isselect_media";
    public static final String KEY_EXTRA_NEW_FOLDER = "key_create_new_folder";
    public static final String KEY_EXTRA_PATH = "key_path";
    public static final String KEY_EXTRA_SELECTED_FILES = "selected_files";
    public static final String KEY_EXTRA_SHOW_FILE_TYPE = "key_file_or_directory";
    private static final String TAG = "FilePickerActivity";
    private C0750b mAdapter;
    private int mConfirmButtonTextId;
    private String mEntryPath;
    private boolean mIsNewFolderEnable;
    private ListView mListView;
    private ActionBarController.C1070a mSelectAction;
    private TextView mTextViewHeader;
    private TextView mTextViewHeaderIcon;
    private int mToastId;
    private static final File EXTERNAL_STORAGE_DIRECTORY = new File(EnvironmentUtils.C0605d.m8467b());
    private static final List<String> EMPTY_STRING_LIST = new ArrayList();
    private String mExcludedExtensions = "";
    private String mIncludeExtensions = "";
    private int mShowFileType = 0;
    private int mChoiceMode = 2;
    private Stack<Integer> mPositionsStack = new Stack<>();
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_filepicker_confirm /* 2131230909 */:
                    FilePickerActivity.this.saveSelectAndFinish();
                    return;
                case R.id.textview_mediascan_filepicker_item_folder /* 2131231774 */:
                case R.id.textview_mediascan_filepicker_item_title /* 2131231775 */:
                    FilePickerActivity.this.doUpperPath();
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mediascan_filepicker);
        setPage(SPage.PAGE_SCAN_MUSIC_CUSTOM);
        onNewIntent(getIntent());
        initActionBar();
        this.mListView = (ListView) findViewById(R.id.listview_mediascan_filepicker);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int m8266a = ListViewUtils.m8266a(FilePickerActivity.this.mListView.getHeaderViewsCount(), i, FilePickerActivity.this.mAdapter.getCount());
                if (m8266a <= -1) {
                    return;
                }
                if (FileUtils.m8409d(FilePickerActivity.this.mAdapter.getItem(m8266a).f2692a)) {
                    FilePickerActivity.this.mAdapter.m8028a(FilePickerActivity.this.mAdapter.getItem(m8266a).f2692a, false, m8266a, FilePickerActivity.EMPTY_STRING_LIST);
                } else {
                    FilePickerActivity.this.mAdapter.m8029a(FilePickerActivity.this.mAdapter.getItem(m8266a).f2692a);
                }
            }
        });
        View inflate = getLayoutInflater().inflate(R.layout.mediascan_filepicker_header, (ViewGroup) this.mListView, false);
        this.mListView.addHeaderView(inflate);
        this.mTextViewHeaderIcon = (TextView) inflate.findViewById(R.id.textview_mediascan_filepicker_item_folder);
        this.mTextViewHeaderIcon.setOnClickListener(this.mOnClickListener);
        this.mTextViewHeader = (TextView) inflate.findViewById(R.id.textview_mediascan_filepicker_item_title);
        this.mTextViewHeader.setOnClickListener(this.mOnClickListener);
        Button button = (Button) findViewById(R.id.button_filepicker_confirm);
        button.setOnClickListener(this.mOnClickListener);
        button.setText(this.mConfirmButtonTextId);
        this.mAdapter = new C0750b(this.mEntryPath);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectAllAction() {
        this.mSelectAction.m7166a((Object) this.mSelectAction);
        this.mSelectAction.m7151e(R.string.icon_checked);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectNoneAction() {
        this.mSelectAction.m7166a((Object) null);
        this.mSelectAction.m7151e(R.string.icon_unchecked);
    }

    private void initActionBar() {
        ActionBarController actionBarController = getActionBarController();
        if (isMultiMode()) {
            this.mSelectAction = actionBarController.m7198a((Drawable) null, "selectAction");
            this.mSelectAction.m7161b(getResources().getColorStateList(R.color.default_action_bar_color));
            setSelectNoneAction();
            this.mSelectAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.2
                @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
                /* renamed from: a */
                public void mo5433a(ActionBarController.C1070a c1070a) {
                    if (c1070a.m7150f() == null) {
                        FilePickerActivity.this.mAdapter.m8027a(true);
                        FilePickerActivity.this.setSelectAllAction();
                        return;
                    }
                    FilePickerActivity.this.mAdapter.m8027a(false);
                    FilePickerActivity.this.setSelectNoneAction();
                }
            });
        } else {
            actionBarController.m7178d(R.drawable.img_mediascan_filepicker_new_folder).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.3
                @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
                /* renamed from: a */
                public void mo5433a(ActionBarController.C1070a c1070a) {
                    EditTextDialog editTextDialog = new EditTextDialog(FilePickerActivity.this, new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, FilePickerActivity.this.getString(R.string.folder), "", FilePickerActivity.this.getString(R.string.new_folder_hint))}, R.string.create, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.3.1
                        @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                        /* renamed from: a  reason: avoid collision after fix types in other method */
                        public void mo2038a(EditTextDialog editTextDialog2) {
                            String obj = editTextDialog2.m6902c(1).m6896d().toString();
                            String obj2 = FilePickerActivity.this.mTextViewHeader.getText().toString();
                            PopupsUtils.m6721a(new File(obj2.endsWith("/") ? new StringBuilder().append(obj2).append(obj).toString() : new StringBuilder().append(obj2).append("/").append(obj).toString()).mkdir() ? FilePickerActivity.this.getString(R.string.new_folder_success) : FilePickerActivity.this.getString(R.string.new_folder_fail));
                            FilePickerActivity.this.mAdapter.m8028a(obj2, false, 0, FilePickerActivity.EMPTY_STRING_LIST);
                        }
                    }, null);
                    editTextDialog.setTitle(FilePickerActivity.this.getString(R.string.new_folder_title));
                    editTextDialog.show();
                }
            });
        }
        actionBarController.m7179d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        int i;
        int i2;
        boolean z = false;
        super.onNewIntent(intent);
        if (intent != null) {
            String stringExtra = intent.getStringExtra(KEY_EXTRA_EXCLUDED_EXTENSIONS);
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "";
            }
            this.mExcludedExtensions = stringExtra;
            String stringExtra2 = intent.getStringExtra("");
            if (TextUtils.isEmpty(stringExtra2)) {
                stringExtra2 = DEFAULT_MEDIA_FILE_EXTENSIONS;
            }
            this.mIncludeExtensions = stringExtra2;
            this.mShowFileType = intent.getIntExtra(KEY_EXTRA_SHOW_FILE_TYPE, 0);
            this.mEntryPath = intent.getStringExtra(KEY_EXTRA_PATH);
            if (!FileUtils.m8409d(this.mEntryPath)) {
                this.mEntryPath = EnvironmentUtils.C0605d.m8467b();
            }
            this.mChoiceMode = intent.getIntExtra(KEY_EXTRA_CHOICE_MODE, 2);
            this.mIsNewFolderEnable = intent.getBooleanExtra(KEY_EXTRA_NEW_FOLDER, false);
            boolean booleanExtra = intent.getBooleanExtra(KEY_EXTRA_ISSELECT_MEDIA, true);
            i = intent.getIntExtra(KEY_EXTRA_CONFIRMYPE, 0);
            z = booleanExtra;
        } else {
            i = 0;
        }
        switch (this.mShowFileType) {
            case 1:
                this.mToastId = z ? R.string.filepicker_notify_select_media : R.string.filepicker_notify_select_file;
                if (!z) {
                    i2 = R.string.filepicker_select_file;
                    break;
                } else {
                    i2 = R.string.filepicker_select_media;
                    break;
                }
            case 2:
                this.mToastId = R.string.filepicker_notify_select_folder;
                i2 = R.string.filepicker_select_folder;
                break;
            default:
                this.mToastId = z ? R.string.filepicker_notify_select_folder_or_media : R.string.filepicker_notify_select_folder_or_file;
                if (!z) {
                    i2 = R.string.filepicker_select_file_or_folder;
                    break;
                } else {
                    i2 = R.string.filepicker_select_media_or_folder;
                    break;
                }
        }
        setTitle(getString(i2));
        switch (i) {
            case 1:
                this.mConfirmButtonTextId = R.string.filepicker_confirm_add;
                return;
            case 2:
                this.mConfirmButtonTextId = R.string.filepicker_confirm_sacan;
                return;
            case 3:
                this.mConfirmButtonTextId = R.string.unknown;
                return;
            default:
                this.mConfirmButtonTextId = R.string.filepicker_confirm_confirm;
                return;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (!doUpperPath()) {
            setResult(0);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity
    public void onActionBarBackPressed() {
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveSelectAndFinish() {
        Intent intent = new Intent();
        String[] m8036a = this.mAdapter.m8036a();
        if (m8036a == null || m8036a.length == 0) {
            PopupsUtils.m6760a(this.mToastId);
            return;
        }
        if (isMultiMode()) {
            intent.putExtra(KEY_EXTRA_SELECTED_FILES, m8036a);
        } else {
            intent.putExtra(KEY_EXTRA_SELECTED_FILES, m8036a[0]);
        }
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doUpperPath() {
        File parentFile;
        if (StringUtils.m8346a(this.mAdapter.f2697d) || (parentFile = new File(this.mAdapter.f2697d).getParentFile()) == null) {
            return false;
        }
        this.mAdapter.m8028a(parentFile.getAbsolutePath(), true, 0, EMPTY_STRING_LIST);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMultiMode() {
        return this.mChoiceMode == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.activities.mediascan.FilePickerActivity$b */
    /* loaded from: classes.dex */
    public final class C0750b extends BaseAdapter {

        /* renamed from: d */
        private String f2697d;

        /* renamed from: b */
        private C0749a[] f2695b = new C0749a[0];

        /* renamed from: c */
        private int f2696c = 0;

        /* renamed from: e */
        private FileFilter f2698e = new FileFilter() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.b.2
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return FilePickerActivity.this.mShowFileType != 1;
                } else if (!file.isFile() || FilePickerActivity.this.mShowFileType == 2) {
                    return false;
                } else {
                    String m8399m = FileUtils.m8399m(file.getName());
                    return m8399m.length() > 0 && FilePickerActivity.this.mIncludeExtensions.contains(new StringBuilder().append(m8399m.toLowerCase(Locale.US)).append("|").toString());
                }
            }
        };

        /* renamed from: f */
        private final Comparator<File> f2699f = new Comparator<File>() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.b.3
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(File file, File file2) {
                if (file.isDirectory()) {
                    if (!file2.isDirectory()) {
                        return -1;
                    }
                } else if (file2.isDirectory()) {
                    return 1;
                }
                return file.getName().compareToIgnoreCase(file2.getName());
            }
        };

        public C0750b(String str) {
            this.f2697d = "";
            this.f2697d = str;
            if (FilePickerActivity.this.isMultiMode()) {
                m8028a(str, false, 0, FilePickerActivity.EMPTY_STRING_LIST);
                return;
            }
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(str);
            m8028a(FileUtils.m8400l(str), false, 0, arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m8026a(C0749a[] c0749aArr) {
            this.f2695b = c0749aArr;
            this.f2696c = 0;
            notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m8027a(boolean z) {
            int count = getCount();
            this.f2696c = z ? count : 0;
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    getItem(i).f2693b = z;
                }
                m8023b(z);
                FilePickerActivity.this.mAdapter.notifyDataSetChanged();
            }
        }

        /* renamed from: b */
        private void m8023b(boolean z) {
            if (FilePickerActivity.this.isMultiMode()) {
                if (z) {
                    FilePickerActivity.this.setSelectAllAction();
                } else {
                    FilePickerActivity.this.setSelectNoneAction();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public String[] m8036a() {
            int count = getCount();
            if (count <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < count; i++) {
                if (getItem(i).f2693b) {
                    arrayList.add(getItem(i).f2692a);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m8029a(String str) {
            C0749a[] c0749aArr;
            for (C0749a c0749a : this.f2695b) {
                if (FilePickerActivity.this.isMultiMode()) {
                    if (str.equals(c0749a.f2692a)) {
                        if (c0749a.f2693b) {
                            this.f2696c--;
                            c0749a.f2693b = false;
                        } else {
                            this.f2696c++;
                            c0749a.f2693b = true;
                        }
                    }
                } else {
                    c0749a.f2693b = str.equals(c0749a.f2692a);
                }
            }
            if (FilePickerActivity.this.isMultiMode()) {
                m8023b(this.f2696c == getCount());
            }
            FilePickerActivity.this.mAdapter.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m8028a(String str, final boolean z, final int i, final List<String> list) {
            File file;
            if (TextUtils.isEmpty(str)) {
                file = FilePickerActivity.EXTERNAL_STORAGE_DIRECTORY;
            } else {
                file = new File(str);
            }
            if (!file.exists() && !file.mkdirs()) {
                LogUtils.m8388a(FilePickerActivity.TAG, "create " + file + " failed.");
            } else if (file.isDirectory()) {
                if (FilePickerActivity.this.isMultiMode() && FilePickerActivity.this.mSelectAction.m7150f() != null) {
                    FilePickerActivity.this.setSelectNoneAction();
                }
                final File file2 = file;
                TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<File, C0749a[]>(file) { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.b.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                    /* renamed from: a  reason: avoid collision after fix types in other method */
                    public C0749a[] mo1981a(File file3) {
                        if (file3 == null || !file3.isDirectory()) {
                            return null;
                        }
                        File[] listFiles = file3.listFiles(C0750b.this.f2698e);
                        if (listFiles != null && listFiles.length > 0) {
                            Arrays.sort(listFiles, C0750b.this.f2699f);
                        }
                        return C0749a.m8038a(listFiles);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
                    /* renamed from: a */
                    public void postExecute(C0749a[] c0749aArr) {
                        if (c0749aArr == null || c0749aArr.length == 0) {
                            FilePickerActivity.this.mTextViewHeader.setText(C0750b.this.f2697d);
                            C0750b.this.m8029a(file2.getAbsolutePath());
                            return;
                        }
                        for (C0749a c0749a : c0749aArr) {
                            c0749a.f2693b = list.contains(c0749a.f2692a);
                        }
                        C0750b.this.f2697d = file2.getAbsolutePath();
                        FilePickerActivity.this.mTextViewHeader.setText(C0750b.this.f2697d);
                        FilePickerActivity.this.mTextViewHeaderIcon.setVisibility(file2.getParentFile() == null ? View.INVISIBLE : View.VISIBLE);
                        FilePickerActivity.this.mAdapter.m8026a(c0749aArr);
                        if (z) {
                            if (!FilePickerActivity.this.mPositionsStack.isEmpty()) {
                                FilePickerActivity.this.mListView.setSelection(((Integer) FilePickerActivity.this.mPositionsStack.pop()).intValue());
                                return;
                            }
                            return;
                        }
                        FilePickerActivity.this.mPositionsStack.push(Integer.valueOf(i));
                        FilePickerActivity.this.mListView.setSelection(0);
                    }
                });
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.f2695b == null) {
                return 0;
            }
            return this.f2695b.length;
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public C0749a getItem(int i) {
            return this.f2695b[i];
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            C0755c c0755c;
            if (view == null) {
                view = LayoutInflater.from(FilePickerActivity.this).inflate(R.layout.mediascan_filepicker_item, viewGroup, false);
                C0755c c0755c2 = new C0755c((TextView) view.findViewById(R.id.textview_mediascan_filepicker_item_folder), (TextView) view.findViewById(R.id.textview_mediascan_filepicker_item_title), (IconTextView) view.findViewById(R.id.itv_mediascan_filepicker_item));
                view.setTag(c0755c2);
                c0755c = c0755c2;
            } else {
                c0755c = (C0755c) view.getTag();
            }
            C0749a item = getItem(i);
            c0755c.f2711c.setOnCheckedChangeListener(null);
            c0755c.f2711c.setChecked(item.f2693b);
            c0755c.f2711c.setContentDescription("" + i);
            c0755c.f2711c.setOnCheckedChangeListener(new IconTextView.InterfaceC1067a() { // from class: com.sds.android.ttpod.activities.mediascan.FilePickerActivity.b.4
                @Override // com.sds.android.ttpod.common.widget.IconTextView.InterfaceC1067a
                /* renamed from: a */
                public void mo7202a(IconTextView iconTextView, boolean z, boolean z2) {
                    if (i < C0750b.this.f2695b.length && i >= 0) {
                        C0750b.this.m8029a(C0750b.this.getItem(i).f2692a);
                    }
                }
            });
            c0755c.f2710b.setText(FileUtils.m8402j(item.f2692a));
            if (new File(item.f2692a).isDirectory()) {
                c0755c.f2709a.setBackgroundResource(R.drawable.img_mediascan_item_folder);
                c0755c.f2709a.setText("");
            } else {
                c0755c.f2709a.setBackgroundResource(R.drawable.img_mediascan_item_file);
                c0755c.f2709a.setText(FileUtils.m8399m(item.f2692a.toUpperCase(Locale.US)));
            }
            return view;
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.mediascan.FilePickerActivity$c */
    /* loaded from: classes.dex */
    private static final class C0755c {

        /* renamed from: a */
        private TextView f2709a;

        /* renamed from: b */
        private TextView f2710b;

        /* renamed from: c */
        private IconTextView f2711c;

        private C0755c(TextView textView, TextView textView2, IconTextView iconTextView) {
            this.f2709a = textView;
            this.f2710b = textView2;
            this.f2711c = iconTextView;
            this.f2711c.m7217a(R.string.icon_unchecked, R.string.icon_checked);
            this.f2711c.setCheckable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.activities.mediascan.FilePickerActivity$a */
    /* loaded from: classes.dex */
    public static final class C0749a {

        /* renamed from: a */
        private String f2692a;

        /* renamed from: b */
        private boolean f2693b;

        private C0749a(String str) {
            this(str, false);
        }

        private C0749a(String str, boolean z) {
            this.f2692a = str;
            this.f2693b = z;
        }

        /* renamed from: a */
        public static C0749a[] m8038a(File[] fileArr) {
            if (fileArr == null || fileArr.length == 0) {
                return null;
            }
            int length = fileArr.length;
            C0749a[] c0749aArr = new C0749a[length];
            for (int i = 0; i < length; i++) {
                c0749aArr[i] = new C0749a(fileArr[i].getAbsolutePath());
            }
            return c0749aArr;
        }
    }
}
