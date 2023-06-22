package com.sds.android.ttpod.activities.soundsearch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;

/* loaded from: classes.dex */
public class SoundSearchMultiResultActivity extends SoundSearchMediaListActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.search_sound_search);
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7179d();
        actionBarController.m7178d(R.drawable.img_actionitem_history).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchMultiResultActivity.1
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                SoundSearchMultiResultActivity.this.startActivity(new Intent(SoundSearchMultiResultActivity.this, SoundSearchHistoryActivity.class));
            }
        });
    }

    @Override // com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity
    protected void onBindData(SoundSearchMediaListActivity.C0896a c0896a) {
        Parcelable[] parcelableArrayExtra = getIntent().getParcelableArrayExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT);
        int length = parcelableArrayExtra.length;
        SoundSearchInfo[] soundSearchInfoArr = new SoundSearchInfo[length];
        for (int i = 0; i < length; i++) {
            soundSearchInfoArr[i] = (SoundSearchInfo) parcelableArrayExtra[i];
        }
        c0896a.m7771a(soundSearchInfoArr);
    }

    @Override // com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity
    protected void onAddHeaderView(ListView listView) {
        listView.addHeaderView(View.inflate(this, R.layout.soundsearch_multiresult_head, null));
    }
}
