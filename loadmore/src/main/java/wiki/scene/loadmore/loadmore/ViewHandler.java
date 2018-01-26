package wiki.scene.loadmore.loadmore;

import android.view.View;
import android.view.View.OnClickListener;

import wiki.scene.loadmore.loadmore.ILoadViewMoreFactory.ILoadMoreView;

public interface ViewHandler {

    /**
     * @param contentView
     * @param loadMoreView
     * @param onClickLoadMoreListener
     * @return 是否有 init ILoadMoreView
     */
    public boolean handleSetAdapter(View contentView, ILoadMoreView loadMoreView, OnClickListener onClickLoadMoreListener);

    public void setOnScrollBottomListener(View contentView, OnScrollBottomListener onScrollBottomListener);

}
