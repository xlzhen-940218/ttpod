package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.utils.ThemeUtils;

/* loaded from: classes.dex */
public class AvatarFrameView extends RelativeLayout {

    /* renamed from: a */
    private ImageView f7481a;

    /* renamed from: b */
    private ImageView f7482b;

    /* renamed from: c */
    private ImageView f7483c;

    public AvatarFrameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_avatar_frame, this);
        this.f7481a = (ImageView) findViewById(R.id.image_avatar);
        this.f7482b = (ImageView) findViewById(R.id.image_avatar_frame);
        this.f7483c = (ImageView) findViewById(R.id.image_avatar_mask);
    }

    public ImageView getAvatarView() {
        return this.f7481a;
    }

    public int getMaxChildHeight() {
        return Math.max(Math.max(ThemeUtils.m8167a(ThemeManager.m3258b(ThemeElement.SETTING_AVATAR_FRAME), 0), ThemeUtils.m8167a(ThemeManager.m3258b(ThemeElement.SETTING_AVATAR_MASK), 0)), (int) getResources().getDimension(R.dimen.avatar_frame_height));
    }
}
