package com.example.hybss.viewhelper.recycler;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.andview.refreshview.XRefreshView;

/**
 * @Author: yobin he
 * @Date:2019/8/26 9:18
 * @Email: heyibin@huawenpicture.com
 * @Des : 用于封装可以分页的数据列表
 */
public class RecyclerSupportFragment<T> extends Fragment {
    /*最小页码*/
    public static final int MIN_PAGE_INDEX = 1;

    /*一次加载量*/
    public static final int PAGE_COUNT = 20;

    protected int pageCount = PAGE_COUNT; //页码数目
    protected int pageIndex = MIN_PAGE_INDEX; //页码

    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter<T> adapter;

    //没有间隔线，没有颜色
    public void initXRefreshView(BaseRecyclerViewAdapter<T> adapter, XRefreshView xRefreshView, RecyclerView recyclerView) {
        initXRefreshView(adapter, xRefreshView, recyclerView, 0, 0); // 没有间隔线，没有间隔线颜色
    }

    public void initXRefreshView(BaseRecyclerViewAdapter<T> adapter, XRefreshView xRefreshView, RecyclerView recyclerView, int dividerHeight) {
        initXRefreshView(adapter, xRefreshView, recyclerView, dividerHeight, 0); // 默认间隔线颜色
    }

    public void initXRefreshView(BaseRecyclerViewAdapter<T> adapter, XRefreshView xRefreshView, RecyclerView recyclerView, int dividerHeight, int dividerColor) {
         
    }


}
