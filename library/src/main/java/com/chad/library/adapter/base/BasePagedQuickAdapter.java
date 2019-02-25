package com.chad.library.adapter.base;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.AsyncPagedListDiffer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

public abstract class BasePagedQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    final AsyncPagedListDiffer<T> mDiffer;

    public BasePagedQuickAdapter(int layoutResId, @Nullable List<T> data, @NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(layoutResId, data);
        mDiffer = new AsyncPagedListDiffer<>(this, diffCallback);
        mDiffer.addPagedListListener(mListener);
    }

    public BasePagedQuickAdapter(@Nullable List<T> data, @NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(data);
        mDiffer = new AsyncPagedListDiffer<>(this, diffCallback);
        mDiffer.addPagedListListener(mListener);
    }

    public BasePagedQuickAdapter(int layoutResId, @NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(layoutResId);
        mDiffer = new AsyncPagedListDiffer<>(this, diffCallback);
        mDiffer.addPagedListListener(mListener);
    }

    public BasePagedQuickAdapter(int layoutResId, @Nullable List<T> data, @NonNull AsyncDifferConfig<T> config) {
        super(layoutResId, data);
        mDiffer = new AsyncPagedListDiffer<>(new AdapterListUpdateCallback(this), config);
        mDiffer.addPagedListListener(mListener);
    }

    public BasePagedQuickAdapter(@Nullable List<T> data, @NonNull AsyncDifferConfig<T> config) {
        super(data);
        mDiffer = new AsyncPagedListDiffer<>(new AdapterListUpdateCallback(this), config);
        mDiffer.addPagedListListener(mListener);
    }

    public BasePagedQuickAdapter(int layoutResId, @NonNull AsyncDifferConfig<T> config) {
        super(layoutResId);
        mDiffer = new AsyncPagedListDiffer<>(new AdapterListUpdateCallback(this), config);
        mDiffer.addPagedListListener(mListener);
    }


    private final AsyncPagedListDiffer.PagedListListener<T> mListener =
            new AsyncPagedListDiffer.PagedListListener<T>() {
                @Override
                public void onCurrentListChanged(
                        @Nullable PagedList<T> previousList, @Nullable PagedList<T> currentList) {
                    BasePagedQuickAdapter.this.onCurrentListChanged(currentList);
                    BasePagedQuickAdapter.this.onCurrentListChanged(previousList, currentList);
                }
            };

    /**
     * Set the new list to be displayed.
     * <p>
     * If a list is already being displayed, a diff will be computed on a background thread, which
     * will dispatch Adapter.notifyItem events on the main thread.
     *
     * @param pagedList The new list to be displayed.
     */
    public void submitList(@Nullable PagedList<T> pagedList) {
        mDiffer.submitList(pagedList);
    }

    /**
     * Set the new list to be displayed.
     * <p>
     * If a list is already being displayed, a diff will be computed on a background thread, which
     * will dispatch Adapter.notifyItem events on the main thread.
     * <p>
     * The commit callback can be used to know when the PagedList is committed, but note that it
     * may not be executed. If PagedList B is submitted immediately after PagedList A, and is
     * committed directly, the callback associated with PagedList A will not be run.
     *
     * @param pagedList The new list to be displayed.
     * @param commitCallback Optional runnable that is executed when the PagedList is committed, if
     *                       it is committed.
     */
    public void submitList(@Nullable PagedList<T> pagedList,
                           @Nullable final Runnable commitCallback) {
        mDiffer.submitList(pagedList, commitCallback);
    }

    @Nullable
    public T getItem(int position) {
        return mDiffer.getItem(position);
    }

    @Override
    public int getItemCount() {
        return mDiffer.getItemCount();
    }

    /**
     * Returns the PagedList currently being displayed by the Adapter.
     * <p>
     * This is not necessarily the most recent list passed to {@link #submitList(PagedList)},
     * because a diff is computed asynchronously between the new list and the current list before
     * updating the currentList value. May be null if no PagedList is being presented.
     *
     * @return The list currently being displayed.
     *
     * @see #onCurrentListChanged(PagedList, PagedList)
     */
    @Nullable
    public PagedList<T> getCurrentList() {
        return mDiffer.getCurrentList();
    }

    /**
     * Called when the current PagedList is updated.
     * <p>
     * This may be dispatched as part of {@link #submitList(PagedList)} if a background diff isn't
     * needed (such as when the first list is passed, or the list is cleared). In either case,
     * PagedListAdapter will simply call
     * {@link #notifyItemRangeInserted(int, int) notifyItemRangeInserted/Removed(0, mPreviousSize)}.
     * <p>
     * This method will <em>not</em>be called when the Adapter switches from presenting a PagedList
     * to a snapshot version of the PagedList during a diff. This means you cannot observe each
     * PagedList via this method.
     *
     * @deprecated Use the two argument variant instead:
     * {@link #onCurrentListChanged(PagedList, PagedList)}
     *
     * @param currentList new PagedList being displayed, may be null.
     *
     * @see #getCurrentList()
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    public void onCurrentListChanged(@Nullable PagedList<T> currentList) {
    }

    /**
     * Called when the current PagedList is updated.
     * <p>
     * This may be dispatched as part of {@link #submitList(PagedList)} if a background diff isn't
     * needed (such as when the first list is passed, or the list is cleared). In either case,
     * PagedListAdapter will simply call
     * {@link #notifyItemRangeInserted(int, int) notifyItemRangeInserted/Removed(0, mPreviousSize)}.
     * <p>
     * This method will <em>not</em>be called when the Adapter switches from presenting a PagedList
     * to a snapshot version of the PagedList during a diff. This means you cannot observe each
     * PagedList via this method.
     *
     * @param previousList PagedList that was previously displayed, may be null.
     * @param currentList new PagedList being displayed, may be null.
     *
     * @see #getCurrentList()
     */
    public void onCurrentListChanged(
            @Nullable PagedList<T> previousList, @Nullable PagedList<T> currentList) {
    }
}