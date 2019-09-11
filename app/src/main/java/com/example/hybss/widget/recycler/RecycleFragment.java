package com.example.hybss.widget.recycler;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.XRefreshView;
import com.example.mvp_base_library.presenter.IPresenter;
import com.example.mvp_base_library.view.IView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Author: yobin he
 * @Date:2019/8/29 17:45
 * @Email: heyibin@huawenpicture.com
 * @Des : 用于展示可分页RecyclerView
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public abstract class RecycleFragment<P extends IPresenter, T> extends Fragment implements IView, View.OnClickListener {
    protected P mvpPre;
    private Unbinder binder;


    /*默认页数*/
    public static final int MIN_PAGE_INDEX = 1;
    /*默认一页加载数据量*/
    public static final int PAGE_COUNT = 5;
    /*每页加载数量*/
    private int pageCount = PAGE_COUNT;
    /*当前数据偏移量*/
    private int pageIndex = MIN_PAGE_INDEX;

    private XRefreshView xRefreshView;
    private RecyclerView recyclerView;
    private BaseRecyclerViewAdapter<T> adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPre = bindPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(onLayout(), container, false);
        ButterKnife.bind(this, view); // 进行findviewByid
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addListener();
        setControl();
    }

    @Override
    public Activity getSelfActivity() {
        return getActivity();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != binder) {
            binder.unbind();// 进行相应的解除祖册
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 在生命周期结束的时候，将presenter与view之间的联系断开，防止内存泄漏
         */
        if (mvpPre != null) {
            mvpPre.detachView();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 抽象方法 start
    /////////////////////////////////////////////////////////////////////////// 
    protected abstract P bindPresenter();

    protected abstract int onLayout(); //设置布局id;

    protected abstract void initView(View view); //初始化控件

    protected abstract void addListener(); //添加监听器

    protected abstract void setControl(); // 添加主要逻辑
    // /////////////////////////////////////////////////////////////////////////
    // 抽象方法 start
    ///////////////////////////////////////////////////////////////////////////

    //带有父控件查找
    public <T> T fv(int resId, View parent) {
        return (T) parent.findViewById(resId);
    }

    /*******************************************************用于分页加载recylerview start**************************************************************************/
    /*默认没有分割线和分割颜色*/
    public void initRefreshView(BaseRecyclerViewAdapter<T> adapter, XRefreshView xRefreshView, RecyclerView recyclerView) {
        initRefreshView(adapter, xRefreshView, recyclerView, 0, 0);
    }

    public void initRefreshView(BaseRecyclerViewAdapter<T> adapter, XRefreshView xRefreshView, RecyclerView recyclerView, int dividerHeight, int dividerColor) {
        XRefreshTool.initRefreshView(getActivity(), xRefreshView, recyclerView, dividerHeight, dividerColor);
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
                RecycleFragment.this.xRefreshView.setLoadComplete(false);
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
        return (null != getActivity() && !getActivity().isDestroyed() && xRefreshView != null);
    }

    /**
     * list 是否可用 , 可用返回true
     *
     * @param list
     * @return
     */

    public boolean validList(List list) {
        if (list != null && list.size() > 0 && getActivity() != null && !getActivity().isDestroyed()) {
            return true;
        }
        return false;
    }
    /*******************************************************用于分页加载recylerview end**************************************************************************/

}
