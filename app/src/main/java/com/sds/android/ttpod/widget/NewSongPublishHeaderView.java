package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class NewSongPublishHeaderView extends SongPublishSectionView {
    public NewSongPublishHeaderView(Context context) {
        this(context, null);
    }

    public NewSongPublishHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NewSongPublishHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        ColorStateList m3254c = ThemeManager.m3254c(ThemeElement.COMMON_CONTENT_TEXT);
        if (m3254c != null) {
            this.f7929c = m3254c;
        }
        setSectionItemTextColor(this.f7929c);
    }

    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: a */
    public void mo1543a(ArrayList arrayList, int i) {
        super.mo1543a(arrayList, i);
        this.f7927a.setVisibility(View.GONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.widget.SimpleSongView
    /* renamed from: a */
    public void mo1547a(Context context) {
        inflate(context, R.layout.new_song_publish_list_header, this);
        this.f7927a = (TextView) findViewById(R.id.new_song_publish_list_header_title);
        this.f7928b = (SimpleGridView) findViewById(R.id.new_song_publish_list_header_grid_view);
        this.f7927a.setText(R.string.select);
    }
}
