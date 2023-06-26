package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p070b.AudioEffectAdapter;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.ListDialog;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.EqualizerPreset;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.audiofx.TTEqualizer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class EqualizerAllFragment extends BaseEqualizerFragment {
    private static final String TAG = "EqualizerAllFragment";
    private AudioEffectAdapter mEqualizerAllAdapter;
    private View mRootView;
    private List<String> mEqualizerAllList = new ArrayList();
    private Map<String, TTEqualizer.Settings> mCustomEqualizerMap = new HashMap();
    private boolean mIsSelected = false;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.EqualizerAllFragment.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            EqualizerAllFragment.this.performItemClick((String) EqualizerAllFragment.this.mEqualizerAllList.get(i));
        }
    };
    private AdapterView.OnItemLongClickListener mOnItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.sds.android.ttpod.fragment.audioeffect.EqualizerAllFragment.2
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            m5747a((String) EqualizerAllFragment.this.mEqualizerAllList.get(i));
            return true;
        }

        /* renamed from: a */
        private void m5747a(final String str) {
            if (EqualizerAllFragment.this.mCustomEqualizerMap.containsKey(str)) {
                final ListDialog listDialog = new ListDialog(EqualizerAllFragment.this.getActivity(), new ActionItem[]{new ActionItem(0, (int) R.drawable.img_contextmenu_rename, (int) R.string.rename), new ActionItem(1, (int) R.drawable.img_contextmenu_remove, (int) R.string.remove), new ActionItem(2, (int) R.drawable.img_contextmenu_edit, (int) R.string.edit)}, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<? extends ListDialog>) null);
                listDialog.setTitle(str);
                listDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.audioeffect.EqualizerAllFragment.2.1
                    @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                    /* renamed from: a */
                    public void mo5409a(ActionItem actionItem, int i) {
                        switch (actionItem.m7005e()) {
                            case 0:
                                m5745b(str);
                                break;
                            case 1:
                                m5743c(str);
                                break;
                            case 2:
                                EqualizerAllFragment.this.startCustomEqualizerActivity((TTEqualizer.Settings) EqualizerAllFragment.this.mCustomEqualizerMap.get(str));
                                break;
                        }
                        listDialog.dismiss();
                    }
                });
                listDialog.show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m5745b(final String str) {
            EditTextDialog editTextDialog = new EditTextDialog(EqualizerAllFragment.this.getActivity(), new EditTextDialog.C1144a[]{new EditTextDialog.C1144a(1, "", str, EqualizerAllFragment.this.getString(R.string.effect_custom_equalizer_input_name))}, R.string.save, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.audioeffect.EqualizerAllFragment.2.2
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog2) {
                    String obj = editTextDialog2.m6902c(1).m6896d().toString();
                    if (!StringUtils.isEmpty(obj)) {
                        TTEqualizer.Settings settings = (TTEqualizer.Settings) EqualizerAllFragment.this.mCustomEqualizerMap.get(str);
                        m5742d(str);
                        TTEqualizer.Settings settings2 = new TTEqualizer.Settings(obj, settings.getNumberOfBands(), settings.getBandLevels());
                        m5748a(settings2);
                        if (str.equals(EqualizerAllFragment.this.mEqualizerSettings.getName())) {
                            EqualizerAllFragment.this.mEqualizerSettings = settings2;
                            EqualizerAllFragment.this.setEqualizer(EqualizerAllFragment.this.mEqualizerSettings);
                        }
                    }
                }
            }, null);
            editTextDialog.setTitle(R.string.rename);
            editTextDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public void m5743c(final String str) {
            MessageDialog messageDialog = new MessageDialog(EqualizerAllFragment.this.getActivity(), EqualizerAllFragment.this.getString(R.string.media_delete_single, str), new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.audioeffect.EqualizerAllFragment.2.3
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(MessageDialog messageDialog2) {
                    m5742d(str);
                    if (str.equals(EqualizerAllFragment.this.mEqualizerSettings.getName())) {
                        EqualizerAllFragment.this.mEqualizerSettings = BaseEqualizerFragment.DEFAULT_SETTINGS;
                        EqualizerAllFragment.this.setEqualizer(BaseEqualizerFragment.DEFAULT_SETTINGS);
                    }
                    EqualizerAllFragment.this.mEqualizerAllAdapter.notifyDataSetChanged();
                }
            }, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
            messageDialog.setTitle(R.string.remove);
            messageDialog.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public void m5742d(String str) {
            EqualizerAllFragment.this.mCustomEqualizerMap.remove(str);
            EqualizerAllFragment.this.mEqualizerAllList.remove(str);
            CommandCenter.getInstance().execute(new Command(CommandID.DELETE_CUSTOM_EQUALIZER, str));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m5748a(TTEqualizer.Settings settings) {
            CommandCenter.getInstance().execute(new Command(CommandID.SAVE_CUSTOM_EQUALIZER, settings));
        }
    };

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_equalizer_all, (ViewGroup) null);
            CommandCenter.getInstance().m4596b(new Command(CommandID.QUERY_CUSTOM_EQUALIZER_LIST, new Object[0]));
            AudioEffectParam m2457s = SupportFactory.m2397a(BaseApplication.getApplication()).m2457s();
            initAllData(m2457s != null ? m2457s.m4421g() : "");
            initListView();
        }
        return this.mRootView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEqualizerFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_CUSTOM_EQUALIZER_LIST, ReflectUtils.m8375a(getClass(), "updateCustomEqualizerList", List.class));
        map.put(CommandID.UPDATE_SAVE_CUSTOM_EQUALIZER, ReflectUtils.m8375a(getClass(), "updateSaveCustomEqualizer", TTEqualizer.Settings.class));
    }

    public void updateCustomEqualizerList(List<TTEqualizer.Settings> list) {
        LogUtils.debug(TAG, " updateCustomEqualizerList " + list);
        if (isAdded() && list != null) {
            int size = list.size();
            this.mEqualizerAllList.clear();
            this.mCustomEqualizerMap.clear();
            for (int i = 0; i < size; i++) {
                TTEqualizer.Settings settings = list.get(i);
                this.mEqualizerAllList.add(settings.getName());
                this.mCustomEqualizerMap.put(settings.getName(), settings);
            }
            this.mEqualizerAllList.addAll(EqualizerPreset.m4337a());
            this.mEqualizerAllAdapter.notifyDataSetChanged();
        }
    }

    public void updateSaveCustomEqualizer(TTEqualizer.Settings settings) {
        if (isAdded()) {
            this.mEqualizerAllList.add(0, settings.getName());
            this.mCustomEqualizerMap.put(settings.getName(), settings);
            this.mEqualizerAllAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mEqualizerAllAdapter = null;
            this.mEqualizerAllList.clear();
            this.mCustomEqualizerMap.clear();
            this.mRootView = null;
        }
    }

    private void initListView() {
        ListView listView = (ListView) this.mRootView.findViewById(R.id.listview_equalizer_all);
        listView.setAdapter((ListAdapter) this.mEqualizerAllAdapter);
        listView.setOnItemClickListener(this.mOnItemClickListener);
        listView.setOnItemLongClickListener(this.mOnItemLongClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performItemClick(String str) {
        if (this.mCustomEqualizerMap.containsKey(str)) {
            this.mEqualizerSettings = this.mCustomEqualizerMap.get(str);
        } else {
            this.mEqualizerSettings = getEqualizerSettingsByName(str);
        }
        setEqualizer(this.mEqualizerSettings);
        Preferences.m3075C(true);
        this.mEqualizerAllAdapter.m7619a(str);
        if (!this.mIsSelected) {
            this.mIsSelected = true;
            //AudioEffectStatistic.m5263i();
            //SUserUtils.m4953a("PAGE_CLICK", SAction.ACTION_EFFECT_EQULIZER_ALL_SELECTED, SPage.PAGE_NONE, SPage.PAGE_NONE);
        }
    }

    private void initAllData(String str) {
        if (this.mEqualizerAllList.size() == 0) {
            this.mEqualizerAllList.addAll(EqualizerPreset.m4337a());
        }
        this.mEqualizerAllAdapter = new AudioEffectAdapter(getActivity(), this.mEqualizerAllList, str);
    }

    @Override // com.sds.android.ttpod.fragment.audioeffect.BaseEqualizerFragment
    protected void updateListView(String str) {
        this.mEqualizerAllAdapter.m7619a(str);
    }
}
