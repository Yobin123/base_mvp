package com.example.hybss.widget.recycler;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andview.refreshview.XRefreshView;

/**
 * @Author: yobin he
 * @Date:2019/8/29 17:27
 * @Email: heyibin@huawenpicture.com
 * @Des :
 */
public class XRefreshTool {
    /**
     * 有recyclerview的分页相关初始化
     *
     * @param activity      上下文
     * @param xRefreshView  分页加载框架
     * @param recyclerView  recyclerview
     * @param dividerColor  分割线颜色
     * @param dividerHeight 分割线高度
     */
    public static void initRefreshView(Activity activity, XRefreshView xRefreshView, RecyclerView recyclerView, int dividerHeight, int dividerColor) {
        xRefreshView.setPullRefreshEnable(true); //允许加载更多
        xRefreshView.setPullRefreshEnable(true); //允许下拉加载
        xRefreshView.setPullRefreshEnable(true); //设置是否可以上拉加载
        xRefreshView.setAutoLoadMore(true); //滑动底部自动加载更多
        xRefreshView.setAutoLoadMore(true);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoRefresh(true);
        xRefreshView.setCustomHeaderView(new MyHeader(activity));
        xRefreshView.setCustomFooterView(new MyFooter(activity));

        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)); //这里可以用gridManager.
        RecycleViewDivider.Builder builder = new RecycleViewDivider.Builder(activity);
        if (dividerColor != 0) {
            builder.color(activity.getResources().getColor(dividerColor));
        }
        if (dividerHeight != 0) {
            builder.thickness(dividerHeight);
        }
        recyclerView.addItemDecoration(builder.create());
    }

    //用于包裹非recycler刷新操作
    public static void initScrollContainer(Activity activity, XRefreshView xRefreshView) {
        //允许加载更多
        xRefreshView.setPullLoadEnable(false);
        //允许下拉刷新
        xRefreshView.setPullRefreshEnable(true);
        //设置是否可以上拉加载
        xRefreshView.setPullLoadEnable(false);
        //滑动到底部自动加载更多
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoRefresh(true);
        xRefreshView.setCustomHeaderView(new MyHeader(activity));
    }
}
