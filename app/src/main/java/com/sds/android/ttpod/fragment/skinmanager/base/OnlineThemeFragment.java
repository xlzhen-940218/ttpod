package com.sds.android.ttpod.fragment.skinmanager.base;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.skinmanager.SkinCategoryDetailFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeRankFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeRecommendFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeViewHolder;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;

/* loaded from: classes.dex */
public abstract class OnlineThemeFragment extends BaseThemeFragment {
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void initThemeAdapter() {
        this.mThemeAdapter = new C1762a();
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void onThemeItemSelected(SkinItem skinItem) {
        if (skinItem.getType() == 0) {
            checkSkinItem(skinItem);
        } else if (4 == skinItem.getType() && !sDownloadingSkinMap.containsKey(getSkinInfoMapKey(skinItem))) {
            doStatistic(skinItem.getTitle());
            sLastDownloadThemeName = skinItem.getTitle();
            tryDownloadSkin(skinItem, false);
        }
    }

    private void doStatistic(String str) {
        if (this instanceof ThemeRecommendFragment) {
            //ThemeStatistic.m4899a(str);
        } else if (this instanceof ThemeRankFragment) {
            //ThemeStatistic.m4896b(str);
        } else if (this instanceof SkinCategoryDetailFragment) {
            //ThemeStatistic.m4894c(str);
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected String getStatisticOrigin() {
        return "recommend";
    }

    /* renamed from: com.sds.android.ttpod.fragment.skinmanager.base.OnlineThemeFragment$a */
    /* loaded from: classes.dex */
    protected class C1762a extends BaseThemeFragment.C1761a {
        protected C1762a() {
            super();
        }

        @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.C1761a
        /* renamed from: a */
        protected void mo5325a(SkinItem skinItem, ImageView imageView) {
            String pictureUrl = skinItem.getOnlineSkinItem().getPictureUrl();
            Bitmap bitmap = null;
            if (!OnlineThemeFragment.this.checkUpdateForSkin(skinItem)) {
                bitmap = ImageCacheUtils.m4748a(skinItem.getPath(), this.f5552b, this.f5553c);
            }
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                ImageCacheUtils.displayImage(imageView, pictureUrl, this.f5552b, this.f5553c, (int) R.drawable.img_skin_default_thumbnail);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.C1761a
        /* renamed from: a */
        public void mo5324a(SkinItem skinItem, ThemeViewHolder themeViewHolder) {
            m5323d(skinItem);
            super.mo5324a(skinItem, themeViewHolder);
        }

        /* renamed from: d */
        private void m5323d(SkinItem skinItem) {
            switch (skinItem.getType()) {
                case 0:
                    if (!FileUtils.isFile(skinItem.getPath())) {
                        skinItem.setType(4);
                        return;
                    }
                    return;
                case 4:
                    if (FileUtils.isFile(skinItem.getPath())) {
                        skinItem.setType(0);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void updateSkinInfoForThemeName(String str, int i) {
        SkinItem skinItemForThemeName = getSkinItemForThemeName(str);
        if (skinItemForThemeName != null) {
            skinItemForThemeName.setType(i);
        }
    }
}
