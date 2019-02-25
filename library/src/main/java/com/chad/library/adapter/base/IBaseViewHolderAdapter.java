package com.chad.library.adapter.base;

import java.util.List;

public interface IBaseViewHolderAdapter<T> {


    public int getHeaderLayoutCount();

    public BaseQuickAdapter.OnItemChildClickListener getOnItemChildClickListener();

    public BaseQuickAdapter.OnItemChildLongClickListener getOnItemChildLongClickListener();

    public List<T> getData();
}
