package com.sds.android.ttpod.activities.soundsearch;

import android.os.Bundle;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity;
import com.sds.android.ttpod.component.soundsearch.SoundSearchHistory;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.framework.modules.p122d.OnLoadListener;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import java.util.List;

/* loaded from: classes.dex */
public class SoundSearchHistoryActivity extends SoundSearchMediaListActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity, com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActionBarController().m7179d();
        setTitle(R.string.soundsearch_history);
    }

    @Override // com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity
    protected void onBindData(final SoundSearchMediaListActivity.C0896a c0896a) {
        new SoundSearchHistory(new OnLoadListener<List<SoundSearchInfo>>() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchHistoryActivity.1
            @Override // com.sds.android.ttpod.framework.modules.p122d.OnLoadListener
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void loaded(List<SoundSearchInfo> list) {
                SoundSearchInfo[] soundSearchInfoArr = new SoundSearchInfo[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    SoundSearchInfo soundSearchInfo = list.get(i);
                    soundSearchInfo.m5823f().setFav(MediaItemUtils.m4715a(soundSearchInfo.m5823f()));
                    soundSearchInfoArr[i] = soundSearchInfo;
                }
                c0896a.m7771a(soundSearchInfoArr);
            }
        });
    }

    @Override // com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity
    protected void onAddHeaderView(ListView listView) {
    }
}
