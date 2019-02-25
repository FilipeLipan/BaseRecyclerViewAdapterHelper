package com.chad.library.adapter.base;

import java.util.List;

import androidx.annotation.Nullable;

public interface IBaseViewHolderAdapter {


    public int getHeaderLayoutCount();

    public BaseQuickAdapter.OnItemChildClickListener getOnItemChildClickListener();

    public BaseQuickAdapter.OnItemChildLongClickListener getOnItemChildLongClickListener();

    public List getData();

    public Object getItem(int position);

    public void setNewData(@Nullable List data);
}
