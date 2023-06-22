package com.sds.android.ttpod.component.p087d.p088a;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.p059a.HttpRequest;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.List;

/* renamed from: com.sds.android.ttpod.component.d.a.k */
/* loaded from: classes.dex */
public class NoCopyrightDialog extends ListDialog<ActionItem> {

    /* renamed from: a */
    private TextView f3972a;

    /* renamed from: b */
    private TextView f3973b;

    public NoCopyrightDialog(Context context, List<ActionItem> list, MediaItem mediaItem) {
        super(context, (ActionItem[]) list.toArray(new ActionItem[0]), (int) R.string.cancel, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
        setTitle(mediaItem.getTitle());
        if (!ListUtils.m4718a(list)) {
            this.f3972a.setText(R.string.search_notification_no_copyright);
            m6811b(context);
        } else {
            this.f3972a.setText(R.string.search_notification_no_resource);
        }
        m7254b(R.string.cancel, new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.component.d.a.k.1
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void mo2038a(Object obj) {
                new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_CANCEL_COPYRIGHT_DIALOG.getValue(), SPage.PAGE_DIALOG_COPYRIGHT.getValue()).post();
            }
        });
    }

    @Override // com.sds.android.ttpod.common.p082a.BaseDialog
    /* renamed from: d */
    protected View mo6810d() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_copyright_notification_title, (ViewGroup) null);
        this.f3972a = (TextView) inflate.findViewById(R.id.tv_dialog_notification);
        this.f3973b = (TextView) inflate.findViewById(R.id.unicom_flow_message);
        return inflate;
    }

    /* renamed from: b */
    private void m6811b(Context context) {
        if (HttpRequest.m8704b()) {
            String string = context.getString(R.string.unicom_flow_search_prefix);
            String string2 = context.getString(R.string.unicom_flow_search_suffix);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) string);
            spannableStringBuilder.append((CharSequence) Html.fromHtml("<font color=#f20d0d>" + string2 + "</font>"));
            spannableStringBuilder.append((CharSequence) "。");
            this.f3973b.setText(spannableStringBuilder);
            this.f3973b.setVisibility(View.VISIBLE);
        }
    }
}
