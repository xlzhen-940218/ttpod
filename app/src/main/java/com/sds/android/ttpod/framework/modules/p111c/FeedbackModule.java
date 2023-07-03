package com.sds.android.ttpod.framework.modules.p111c;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.cloudapi.ttpod.api.FeedbackAPI;
import com.sds.android.cloudapi.ttpod.feedback.GetFeedbackMessageResult;
import com.sds.android.cloudapi.ttpod.feedback.GetFeedbackResult;
import com.sds.android.cloudapi.ttpod.feedback.PostFeedbackMessageResult;
import com.sds.android.cloudapi.ttpod.feedback.PostFeedbackResult;
import com.sds.android.sdk.lib.p060b.RequestRest;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.framework.base.BaseModule;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.modules.c.a */
/* loaded from: classes.dex */
public class FeedbackModule extends BaseModule {

    /* renamed from: a */
    private boolean f5776a;

    /* renamed from: b */
    private boolean f5777b;

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    /* renamed from: id */
    protected ModuleID id() {
        return ModuleID.FEEDBACK;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseModule
    protected void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        Class<?> cls = getClass();
        map.put(CommandID.PROPOSAL_FEEDBACK, ReflectUtils.loadMethod(cls, "proposalFeedback", FeedbackItem.class));
        map.put(CommandID.REQUEST_FEEDBACK_LIST, ReflectUtils.loadMethod(cls, "requestFeedbackList", Long.class, Integer.class));
        map.put(CommandID.REQUEST_FEEDBACK_MESSAGES, ReflectUtils.loadMethod(cls, "requestFeedbackMessages", String.class, Long.class, Boolean.class));
        map.put(CommandID.SEND_FEEDBACK_MESSAGE, ReflectUtils.loadMethod(cls, "sendFeedbackMessage", FeedbackMessage.class));
        map.put(CommandID.REQUEST_NEW_REPLYIED_FEEDBACKS, ReflectUtils.loadMethod(cls, "requestNewRepliedFeedbacks", Long.class));
    }

    public void proposalFeedback(final FeedbackItem feedbackItem) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.c.a.1
            @Override // java.lang.Runnable
            public void run() {
                PostFeedbackResult postFeedbackResult = new PostFeedbackResult(FeedbackAPI.m8915a(feedbackItem.getProposalContent(), EnvironmentUtils.DeviceConfig.m8473f(), feedbackItem.getContactWay()).m8668b());
                FeedbackItem feedbackItem2 = null;
                if (postFeedbackResult.isConnected()) {
                    String m8681b = postFeedbackResult.m8681b();
                    String substring = m8681b.substring(m8681b.lastIndexOf(47) + 1);
                    feedbackItem2 = FeedbackAPI.m8917a(substring);
                    if (feedbackItem2 == null) {
                        feedbackItem2 = feedbackItem;
                        feedbackItem2.setId(substring);
                        feedbackItem2.setLastUpdated(System.currentTimeMillis());
                    }
                    Map<String, FeedbackItem> m3147q = Cache.getInstance().m3147q();
                    m3147q.put(substring, feedbackItem2);
                    Cache.getInstance().m3198a(m3147q);
                }
                CommandCenter.getInstance().m4595b(new Command(CommandID.PROPOSAL_FEEDBACK_FINISH, postFeedbackResult, feedbackItem2), ModuleID.FEEDBACK);
            }
        });
    }

    public void requestFeedbackList(final Long l, Integer num) {
        if (!this.f5776a) {
            this.f5776a = true;
            TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.c.a.2
                @Override // java.lang.Runnable
                public void run() {
                    RequestRest m8918a = FeedbackAPI.m8918a(l, null);
                    long currentTimeMillis = System.currentTimeMillis();
                    GetFeedbackResult getFeedbackResult = new GetFeedbackResult(m8918a.m8668b());
                    ArrayList arrayList = new ArrayList();
                    if (getFeedbackResult.isConnected() && getFeedbackResult.getContent() != null) {
                        ArrayList<FeedbackItem> mo8674a = getFeedbackResult.getData();
                        if (mo8674a.size() > 0) {
                            long lastUpdated = mo8674a.get(0).getLastUpdated();
                            Iterator<FeedbackItem> it = mo8674a.iterator();
                            while (true) {
                                currentTimeMillis = lastUpdated;
                                if (!it.hasNext()) {
                                    break;
                                }
                                FeedbackItem next = it.next();
                                lastUpdated = currentTimeMillis < next.getLastUpdated() ? next.getLastUpdated() : currentTimeMillis;
                            }
                        }
                        Preferences.m2890e(currentTimeMillis);
                        FeedbackModule.this.m4488a((List<FeedbackItem>) mo8674a, false);
                        arrayList = new ArrayList(Cache.getInstance().m3147q().values());
                    }
                    CommandCenter.getInstance().m4595b(new Command(CommandID.REQUEST_FEEDBACK_LIST_FINISH, getFeedbackResult, arrayList, Boolean.valueOf(FeedbackModule.this.f5777b)), ModuleID.FEEDBACK);
                    FeedbackModule.this.f5776a = false;
                    FeedbackModule.this.f5777b = false;
                }
            });
        }
    }

    public void requestFeedbackMessages(final String str, final Long l, Boolean bool) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.c.a.3
            @Override // java.lang.Runnable
            public void run() {
                GetFeedbackMessageResult getFeedbackMessageResult = new GetFeedbackMessageResult(FeedbackAPI.m8916a(str, l, (String) null).m8668b());
                ArrayList<FeedbackMessage> arrayList = new ArrayList<>();
                if (getFeedbackMessageResult.isConnected() && getFeedbackMessageResult.getContent() != null) {
                    arrayList = getFeedbackMessageResult.getData();
                    Collections.sort(arrayList, new Comparator<FeedbackMessage>() { // from class: com.sds.android.ttpod.framework.modules.c.a.3.1
                        @Override // java.util.Comparator
                        /* renamed from: a */
                        public int compare(FeedbackMessage feedbackMessage, FeedbackMessage feedbackMessage2) {
                            return m4486a(feedbackMessage.getTimestamp(), feedbackMessage2.getTimestamp());
                        }

                        /* renamed from: a */
                        private int m4486a(long j, long j2) {
                            if (j > j2) {
                                return 1;
                            }
                            if (j == j2) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                CommandCenter m4607a = CommandCenter.getInstance();
                CommandID commandID = CommandID.REQUEST_FEEDBACK_MESSAGES_FINISH;
                Object[] objArr = new Object[3];
                objArr[0] = getFeedbackMessageResult;
                objArr[1] = arrayList;
                objArr[2] = Boolean.valueOf(l != null);
                m4607a.m4595b(new Command(commandID, objArr), ModuleID.FEEDBACK);
            }
        });
    }

    public void sendFeedbackMessage(final FeedbackMessage feedbackMessage) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.c.a.4
            @Override // java.lang.Runnable
            public void run() {
                PostFeedbackMessageResult postFeedbackMessageResult = new PostFeedbackMessageResult(FeedbackAPI.m8911b(feedbackMessage.getThreadId(), feedbackMessage.getType(), feedbackMessage.getContent()).m8668b());
                feedbackMessage.setMsgSource(0);
                feedbackMessage.setTimestamp(System.currentTimeMillis());
                Map<String, FeedbackItem> m3147q = Cache.getInstance().m3147q();
                m3147q.get(feedbackMessage.getThreadId()).setLastUpdated(feedbackMessage.getTimestamp());
                Cache.getInstance().m3198a(m3147q);
                CommandCenter.getInstance().m4595b(new Command(CommandID.SEND_FEEDBACK_MESSAGE_FINISH, postFeedbackMessageResult, feedbackMessage), ModuleID.FEEDBACK);
            }
        });
    }

    public void requestNewRepliedFeedbacks(final Long l) {
        TaskScheduler.start(new Runnable() { // from class: com.sds.android.ttpod.framework.modules.c.a.5
            @Override // java.lang.Runnable
            public void run() {
                GetFeedbackResult getFeedbackResult = new GetFeedbackResult(FeedbackAPI.m8919a(l).m8668b());
                if (getFeedbackResult.isConnected()) {
                    ArrayList<FeedbackItem> mo8674a = getFeedbackResult.getData();
                    if (mo8674a.size() != 0) {
                        FeedbackModule.this.m4488a((List<FeedbackItem>) mo8674a, true);
                        CommandCenter.getInstance().m4595b(new Command(CommandID.NEW_REPLYIED_FEEDBACKS_RECEIVED, mo8674a), ModuleID.FEEDBACK);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4488a(List<FeedbackItem> list, boolean z) {
        Map<String, FeedbackItem> m3147q = Cache.getInstance().m3147q();
        HashMap hashMap = m3147q == null ? new HashMap() : (HashMap) m3147q;
        for (FeedbackItem feedbackItem : list) {
            FeedbackItem feedbackItem2 = (FeedbackItem) hashMap.get(feedbackItem.getId());
            if (feedbackItem2 == null || feedbackItem.getLastUpdated() > feedbackItem2.getLastUpdated()) {
                this.f5777b = true;
                if (z) {
                    feedbackItem.setUnread(true);
                }
            }
            hashMap.put(feedbackItem.getId(), feedbackItem);
        }
        Cache.getInstance().m3198a(hashMap);
    }
}
