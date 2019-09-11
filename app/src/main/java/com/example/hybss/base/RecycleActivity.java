package com.example.hybss.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.example.hybss.viewhelper.XRefreshTool;
import com.example.hybss.viewhelper.recycler.BaseRecyclerViewAdapter;
import com.example.mvp_base_library.presenter.IPresenter;
import com.example.mvp_base_library.utils.ActivityCollector;
import com.example.mvp_base_library.view.IView;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Author: yobin he
 * @Date:2019/9/9 10:32
 * @Email: heyibin@huawenpicture.com
 * @Des :
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public abstract class RecycleActivity<P extends IPresenter, T> extends AppCompatActivity implements IView, View.OnClickListener {
    protected P mvpPre;
    private Unbinder bind; //用于解除注册

    /*默认页数*/
    public static final int MIN_PAGE_INDEX = 1;
    /*默认一页加载数据量*/
    public static final int PAGE_COUNT = 20;
    /*每页加载数量*/
    private int pageCount = PAGE_COUNT;
    /*当前数据偏移量*/
    private int pageIndex = MIN_PAGE_INDEX;

    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter<T> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPre = bindPresenter();
        ActivityCollector.addActivity(this); //收集相应的activity
//        设置相应的
//        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.color_app_Apperance));
        if (0 != onLayout()) {
            setContentView(onLayout());
            bind = ButterKnife.bind(this);
            initView();
            addListener();
            setControl();
        }
    }

    @Override
    public Activity getSelfActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 在生命周期结束的时候，将presenter与view之间的联系断开，防止内存泄漏
         */
        if (mvpPre != null) {
            mvpPre.detachView();
        }
        ActivityCollector.removeActivity(this);
        if (null != bind) {
            bind.unbind();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 抽象方法 start
    /////////////////////////////////////////////////////////////////////////// 
    protected abstract P bindPresenter();

    protected abstract int onLayout(); //设置布局id;

    protected abstract void initView(); //初始化控件

    protected abstract void addListener(); //添加监听器

    protected abstract void setControl(); // 添加主要逻辑
    // /////////////////////////////////////////////////////////////////////////
    // 抽象方法 start
    ///////////////////////////////////////////////////////////////////////////


    //进行控件的查找
    public <T> T fv(int resId) {
        return (T) findViewById(resId);
    }

    public <T> T fv(int resId, View parent) {
        return (T) parent.findViewById(resId);
    }

    public Context getContext() {
        return this;
    }

    /*******************************************************用于分页加载recylerview start**************************************************************************/
    /*默认没有分割线和分割颜色*/
    public void initRefreshView(BaseRecyclerViewAdapter<T> adapter, XRefreshView xRefreshView, RecyclerView recyclerView) {
        initRefreshView(adapter, xRefreshView, recyclerView, 0, 0);
    }

    public void initRefreshView(BaseRecyclerViewAdapter<T> adapter, final XRefreshView xRefreshView, RecyclerView recyclerView, int dividerHeight, int dividerColor) {
        XRefreshTool.initRefreshView(this, xRefreshView, recyclerView, dividerHeight, dividerColor);
        recyclerView.setAdapter(adapter);

        this.xRefreshView = xRefreshView;
        this.recyclerView = recyclerView;
        this.adapter = adapter;

        xRefreshView.setDampingRatio(4);
        xRefreshView.setAutoLoadMore(true);

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                pageIndex = MIN_PAGE_INDEX;
                xRefreshView.setLoadComplete(false);
                loadForList(false, pageCount, pageIndex);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageIndex += 1;
                loadForList(true, pageCount, pageIndex);
            }
        });

    }


    //进行请求数据
    public abstract void loadForList(boolean more, int pageSize, int pageIndex);

    public void onLoadForListComplete(boolean more, List<T> data) {
        if (!more) {//下拉刷新
            onRefreshComplete(data);
        } else { //上拉加载
            onLoadMoreComplete(data);
        }
    }

    //下拉刷新
    protected void onRefreshComplete(List<T> datas) {
        if (validAvailable()) {
            this.adapter.getDatas().clear();
            this.adapter.addDatas(datas);
            this.xRefreshView.stopRefresh();
        }
    }

    //上拉加载
    protected void onLoadMoreComplete(List<T> datas) {
        if (validAvailable()) {
            if (validList(datas)) {
                adapter.addDatas(datas);
                xRefreshView.stopLoadMore();
            } else {
                xRefreshView.setLoadComplete(true);
            }
        }
    }

    //验证是否可行

    private boolean validAvailable() {
        return (null != getSelfActivity() && !getSelfActivity().isDestroyed() && xRefreshView != null);
    }

    /**
     * list 是否可用 , 可用返回true
     *
     * @param list
     * @return
     */
    public boolean validList(List list) {
        if (list != null && list.size() > 0 && getSelfActivity() != null && !getSelfActivity().isDestroyed()) {
            return true;
        }
        return false;
    }
    /*******************************************************用于分页加载recylerview end**************************************************************************/

}
