package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.Comment;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.p055a.PostAPI;
import com.sds.android.cloudapi.ttpod.result.CommentListResult;
import com.sds.android.cloudapi.ttpod.result.CommentResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.IdListResult;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.message.MessageClickableSpan;
import com.sds.android.ttpod.adapter.p073e.CommentViewHolder;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.emoticons.EmoticonConversionUtil;
import com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.utils.TimeUtils;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class CommentsFragment extends SlidingClosableFragment implements EmoticonsWithInputLayout.InterfaceC1207a {
    private static final int CONTEXT_ACTION_ITEM_REPLAY_ID = 1;
    private static final int FIRST_PAGE = 1;
    private static final int PAGE_SIZE = 20;
    private StateView mCenterLoadingViewForFirstPage;
    private Comment mCommentWantToReply;
    private EmoticonsWithInputLayout mEmoticonsWithInputLayout;
    private MusiccircleFooter mFooterLoadingViewForNextPage;
    private ListView mListView;
    private View mRootView;
    private ArrayList<Comment> mCommentsDetails = new ArrayList<>();
    private List<Long> mCommentsIds = new ArrayList();
    private final C0782a mCommentAdapter = new C0782a();
    private Pager mPager = new Pager();
    private boolean mCanRequestWhenScroll = false;
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.5
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            boolean z = i3 - i <= 20;
            if (CommentsFragment.this.mCanRequestWhenScroll && z) {
                CommentsFragment.this.requestNextPageWhenScrollToBottom();
            }
        }
    };

    /* renamed from: com.sds.android.ttpod.activities.musiccircle.CommentsFragment$b */
    /* loaded from: classes.dex */
    public interface InterfaceC0785b {
        /* renamed from: a */
        void mo5429a(long j, long j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.layout_comments_fragment, viewGroup, false);
        initListView(this.mRootView);
        initEmotionInputLayout();
        initCenterLoadingView();
        return this.mRootView;
    }

    private void initCenterLoadingView() {
        this.mCenterLoadingViewForFirstPage = (StateView) this.mRootView.findViewById(R.id.state_loading_view);
        this.mCenterLoadingViewForFirstPage.setOnRetryRequestListener(new StateView.InterfaceC2247a() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.1
            @Override // com.sds.android.ttpod.widget.StateView.InterfaceC2247a
            /* renamed from: a */
            public void mo1450a() {
                CommentsFragment.this.requestBegin();
            }
        });
    }

    private void initEmotionInputLayout() {
        this.mEmoticonsWithInputLayout = (EmoticonsWithInputLayout) this.mRootView.findViewById(R.id.layout_comment_input);
        this.mEmoticonsWithInputLayout.m6664a(getSlidingContainer(), this.mRootView.findViewById(R.id.layout_empty), this);
    }

    private void initListView(View view) {
        this.mListView = (ListView) view.findViewById(R.id.comments_list_view);
        initFooterLoadingView(this.mListView);
        this.mListView.setAdapter((ListAdapter) this.mCommentAdapter);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                if (i < CommentsFragment.this.mCommentsDetails.size()) {
                    Comment comment = (Comment) CommentsFragment.this.mCommentsDetails.get(i);
                    if (CommentsFragment.this.isCommentOwner(comment)) {
                        CommentsFragment.this.showDeleteConfirmDialog(comment);
                    } else {
                        CommentsFragment.this.replyComment(comment);
                    }
                }
            }
        });
        this.mListView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                CommentsFragment.this.mEmoticonsWithInputLayout.setIsShowCommentAvatarAnimation(true);
                CommentsFragment.this.mEmoticonsWithInputLayout.m6663b();
                return false;
            }
        });
    }

    private void initFooterLoadingView(ListView listView) {
        this.mFooterLoadingViewForNextPage = new MusiccircleFooter(getActivity().getLayoutInflater(), new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommentsFragment.this.mCanRequestWhenScroll) {
                    CommentsFragment.this.requestNextPageWhenScrollToBottom();
                }
            }
        });
        this.mFooterLoadingViewForNextPage.onThemeLoaded();
        listView.addFooterView(this.mFooterLoadingViewForNextPage.m7934a());
        this.mFooterLoadingViewForNextPage.m7934a().setVisibility(View.GONE);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        requestBegin();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_COMMENT_ID_LIST_BY_POST_ID, ReflectUtils.m8375a(cls, "updateCommentIdList", IdListResult.class, String.class));
        map.put(CommandID.UPDATE_COMMENT_INFO_LIST_BY_ID_LIST, ReflectUtils.m8375a(cls, "updateCommentsDetailsByPage", CommentListResult.class, String.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.mEmoticonsWithInputLayout.m6660c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
        this.mEmoticonsWithInputLayout.m6658d();
    }

    private void displayCommentCount(int i) {
        getActionBarController().m7193a((CharSequence) String.format("%s (%d)", getString(R.string.musiccircle_comment), Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestBegin() {
        if (this.mCommentsIds.isEmpty()) {
            this.mCenterLoadingViewForFirstPage.setState(StateView.EnumC2248b.LOADING);
        }
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_COMMENT_IDS_BY_POST_ID, Long.valueOf(getPostId()), origin()));
    }

    public void updateCommentIdList(IdListResult idListResult, String str) {
        if (idListResult.isSuccess()) {
            if (idListResult.getDataList().isEmpty()) {
                displayCommentCount(0);
                this.mCenterLoadingViewForFirstPage.setState(StateView.EnumC2248b.NO_DATA);
                return;
            }
            this.mCommentsIds = idListResult.getDataList();
            this.mPager.m4665b(((this.mCommentsIds.size() + 20) - 1) / 20);
            displayCommentCount(this.mCommentsIds.size());
            requestCommentsDetailsByPage(1);
            return;
        }
        this.mCenterLoadingViewForFirstPage.setState(StateView.EnumC2248b.FAILED);
    }

    private void requestCommentsDetailsByPage(int i) {
        this.mCanRequestWhenScroll = false;
        int i2 = (i - 1) * 20;
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_COMMENT_INFOS_BY_IDS, this.mCommentsIds.subList(i2, Math.min(i2 + 20, this.mCommentsIds.size())), origin()));
    }

    public void updateCommentsDetailsByPage(CommentListResult commentListResult, String str) {
        if (commentListResult.isSuccess()) {
            if (isFirstPage()) {
                this.mCommentsDetails = commentListResult.getDataList();
            } else {
                this.mCommentsDetails.addAll(commentListResult.getDataList());
            }
            updateCenterLoadingView();
            this.mCommentAdapter.notifyDataSetChanged();
            this.mPager.m4660e();
        } else {
            onLoadCommentsDetailFailed();
        }
        this.mCanRequestWhenScroll = true;
    }

    private void updateCenterLoadingView() {
        if (this.mCommentsDetails.isEmpty() && !this.mPager.m4657h()) {
            this.mCenterLoadingViewForFirstPage.setState(StateView.EnumC2248b.NO_DATA);
            return;
        }
        this.mCenterLoadingViewForFirstPage.setState(StateView.EnumC2248b.SUCCESS);
        this.mCenterLoadingViewForFirstPage.setVisibility(View.GONE);
    }

    private void onLoadCommentsDetailFailed() {
        if (isFirstPage()) {
            this.mCenterLoadingViewForFirstPage.setState(StateView.EnumC2248b.FAILED);
        } else {
            this.mFooterLoadingViewForNextPage.m7932a(true, 8, getString(R.string.load_comment_fail));
        }
    }

    private boolean isFirstPage() {
        return this.mPager.m4669a() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestNextPageWhenScrollToBottom() {
        if (this.mPager.m4657h()) {
            this.mFooterLoadingViewForNextPage.m7934a().setVisibility(View.VISIBLE);
            this.mFooterLoadingViewForNextPage.m7932a(false, 0, getString(R.string.loading));
            requestCommentsDetailsByPage(this.mPager.m4669a());
            return;
        }
        this.mListView.removeFooterView(this.mFooterLoadingViewForNextPage.m7934a());
        this.mListView.setOnScrollListener(null);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    private String origin() {
        return "post_comments";
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mEmoticonsWithInputLayout.m6656e();
        this.mCenterLoadingViewForFirstPage.onThemeLoaded();
        this.mFooterLoadingViewForNextPage.onThemeLoaded();
        ThemeUtils.m8173a((IconTextView) this.mRootView.findViewById(R.id.comment_empty_icon), ThemeElement.COMMON_TITLE_TEXT);
        getActionBarController().onThemeLoaded();
        ThemeManager.m3269a(this.mListView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        this.mCommentAdapter.notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.InterfaceC1207a
    public void onSend(String str) {
        if (!EnvironmentUtils.DeviceConfig.m8474e()) {
            PopupsUtils.m6760a((int) R.string.network_error);
            this.mEmoticonsWithInputLayout.setSendEnable(true);
        } else if (Preferences.m2954aq() == null) {
            EntryUtils.m8297a(true);
            this.mEmoticonsWithInputLayout.setSendEnable(true);
        } else if (getSendToUserId() > 0) {
            doSend(str, getSendToUserId(), getReplyCommentId(), Preferences.m2954aq().getAccessToken());
        }
    }

    private long getReplyCommentId() {
        if (StringUtils.isEmpty(this.mEmoticonsWithInputLayout.getReplyTo()) || this.mCommentWantToReply == null) {
            return 0L;
        }
        return this.mCommentWantToReply.getId();
    }

    private long getSendToUserId() {
        return (this.mCommentWantToReply == null || this.mCommentWantToReply.getUser() == null) ? getPostOwnerUserId() : this.mCommentWantToReply.getUser().getUserId();
    }

    private void doSend(String str, long j, long j2, String str2) {
        PostAPI.m8849a(str2, getPostId(), str, j, j2).m8544a(new RequestCallback<CommentResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.6
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: a */
            public void onRequestSuccess(CommentResult commentResult) {
                if (commentResult.isSuccess()) {
                    CommentsFragment.this.mEmoticonsWithInputLayout.m6668a();
                    PopupsUtils.m6760a((int) R.string.comment_send_success);
                }
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            /* renamed from: b */
            public void onRequestFailure(CommentResult commentResult) {
                CommentsFragment.this.mEmoticonsWithInputLayout.setSendEnable(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteComment(final Comment comment) {
        PostAPI.m8851a(Preferences.m2954aq().getAccessToken(), getPostId(), comment.getId()).m8544a(new RequestCallback<BaseResult>() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.7
            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestSuccess(BaseResult baseResult) {
                CommentsFragment.this.refreshUIAfterDeleteCommentSuccess(comment);
            }

            @Override // com.sds.android.sdk.lib.request.RequestCallback
            public void onRequestFailure(BaseResult baseResult) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUIAfterDeleteCommentSuccess(Comment comment) {
        this.mCommentsDetails.remove(comment);
        this.mCommentsIds.remove(Long.valueOf(comment.getId()));
        displayCommentCount(this.mCommentsIds.size());
        this.mCommentAdapter.notifyDataSetChanged();
    }

    private long getPostId() {
        return getArguments().getLong("post_id");
    }

    private long getPostOwnerUserId() {
        return getArguments().getLong("user_id");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDeleteConfirmDialog(final Comment comment) {
        PopupsUtils.m6725a(getActivity(), new ActionItem[]{new ActionItem(2, 0, "删除评论")}, "评论", new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.8
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                CommentsFragment.this.deleteComment(comment);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCommentOwner(Comment comment) {
        return Preferences.m2954aq() != null && comment.getUser().getUserId() == Preferences.m2954aq().getUserId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replyComment(Comment comment) {
        this.mCommentWantToReply = comment;
        if (this.mCommentWantToReply.getUser() != null) {
            String nickName = this.mCommentWantToReply.getUser().getNickName();
            String format = String.format("回复 %1$s:", nickName);
            this.mEmoticonsWithInputLayout.setReplyTo(format);
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new ForegroundColorSpan(getHighLightColor()), 3, nickName.length() + 3, 33);
            this.mEmoticonsWithInputLayout.setSoftInputReplyTo(spannableString);
            this.mEmoticonsWithInputLayout.setIsShowCommentAvatarAnimation(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getHighLightColor() {
        return getResources().getColor(R.color.post_comments_user_name_high_light);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoUserHome(TTPodUser tTPodUser) {
        if (Preferences.m2954aq() != null) {
            launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(tTPodUser, origin()));
        } else {
            EntryUtils.m8297a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.activities.musiccircle.CommentsFragment$a */
    /* loaded from: classes.dex */
    public class C0782a extends BaseAdapter {
        private C0782a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return CommentsFragment.this.mCommentsDetails.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public Comment getItem(int i) {
            return (Comment) CommentsFragment.this.mCommentsDetails.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return getItem(i).getCommentId();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(CommentsFragment.this.getActivity()).inflate(R.layout.musiccircle_comment_item, viewGroup, false);
                view.setTag(new CommentViewHolder(view));
            }
            CommentViewHolder commentViewHolder = (CommentViewHolder) view.getTag();
            ThemeManager.m3269a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
            ThemeManager.m3269a(commentViewHolder.m7565b(), ThemeElement.SONG_LIST_ITEM_TEXT);
            ThemeManager.m3269a(commentViewHolder.m7564c(), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            ThemeManager.m3269a(commentViewHolder.m7563d(), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            m7991d(i, commentViewHolder);
            m7992c(i, commentViewHolder);
            m7993b(i, commentViewHolder);
            m7998a(i, commentViewHolder);
            return view;
        }

        /* renamed from: a */
        private void m7998a(final int i, CommentViewHolder commentViewHolder) {
            commentViewHolder.m7566a().setVFlagVisible(m7994b(i) != null && m7994b(i).isVerified());
            commentViewHolder.m7566a().setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CommentsFragment.this.gotoUserHome(C0782a.this.m7994b(i));
                }
            });
            ImageCacheUtils.m4752a(commentViewHolder.m7566a(), m7994b(i) == null ? "" : m7994b(i).getAvatarUrl(), commentViewHolder.m7566a().getWidth(), commentViewHolder.m7566a().getHeight(), (int) R.drawable.img_avatar_default);
        }

        /* renamed from: b */
        private void m7993b(int i, CommentViewHolder commentViewHolder) {
            commentViewHolder.m7565b().setText(m7994b(i) == null ? "" : m7994b(i).getNickName());
        }

        /* renamed from: c */
        private void m7992c(int i, CommentViewHolder commentViewHolder) {
            long createTimeInSecond = getItem(i).getCreateTimeInSecond();
            commentViewHolder.m7563d().setText(createTimeInSecond > 0 ? TimeUtils.m8155a(CommentsFragment.this.getActivity(), createTimeInSecond) : "");
        }

        /* renamed from: d */
        private void m7991d(int i, CommentViewHolder commentViewHolder) {
            commentViewHolder.m7564c().setText(m7997a(getItem(i)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public TTPodUser m7994b(int i) {
            return getItem(i).getUser();
        }

        /* renamed from: a */
        private CharSequence m7997a(Comment comment) {
            CharSequence tweet = comment.getTweet();
            TTPodUser replyTo = comment.getReplyTo();
            if (replyTo != null && comment.getCommentId() != 0) {
                tweet = m7995a(tweet, replyTo);
            }
            SpannableString m6641a = EmoticonConversionUtil.m6639b().m6641a(CommentsFragment.this.getActivity(), tweet);
            return m6641a == null ? "" : m6641a;
        }

        /* renamed from: a */
        private SpannableString m7995a(CharSequence charSequence, TTPodUser tTPodUser) {
            String nickName = tTPodUser.getNickName();
            if (tTPodUser.isVerified()) {
                nickName = nickName + "v";
            }
            SpannableString spannableString = new SpannableString(String.format("回复 %1$s: %2$s", nickName, charSequence));
            int length = CommentViewHolder.f3244a + tTPodUser.getNickName().length();
            MessageClickableSpan messageClickableSpan = new MessageClickableSpan(tTPodUser, new WrapUserPostListFragment.InterfaceC1704a() { // from class: com.sds.android.ttpod.activities.musiccircle.CommentsFragment.a.2
                @Override // com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.InterfaceC1704a
                /* renamed from: a */
                public void mo5428a(TTPodUser tTPodUser2) {
                    CommentsFragment.this.gotoUserHome(tTPodUser2);
                }
            });
            MessageClickableSpan.m7917a(CommentsFragment.this.getHighLightColor());
            spannableString.setSpan(messageClickableSpan, CommentViewHolder.f3244a, length, 33);
            if (tTPodUser.isVerified()) {
                spannableString.setSpan(new ImageSpan(CommentsFragment.this.getActivity(), (int) R.drawable.img_user_v, 1), length, length + 1, 33);
            }
            return spannableString;
        }
    }

    public static CommentsFragment createCommentsFragment(long j, long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("post_id", j);
        bundle.putLong("user_id", j2);
        CommentsFragment commentsFragment = new CommentsFragment();
        commentsFragment.setArguments(bundle);
        return commentsFragment;
    }
}
