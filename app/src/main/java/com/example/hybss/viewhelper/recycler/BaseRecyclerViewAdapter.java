package com.example.hybss.viewhelper.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yobin he
 * @Date:2019/8/23 11:50
 * @Email: heyibin@huawenpicture.com
 * @Des : 可以进行多类型的adapter.但是不能添加相应的头部和尾部
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {
    private List<T> mDatas;
    private boolean isHeader = false;
    private boolean isFooter = false;

    public OnItemClickListener listener;

    public BaseRecyclerViewAdapter(List<T> datas) {
        mDatas = datas;
    }

    public BaseRecyclerViewAdapter(List<T> datas, boolean... isHave) {//头和尾部
        mDatas = datas;
        if (isHave != null) {
            if (isHave.length >= 1) {
                isHeader = isHave[0];
            }
            if (isHave.length >= 2) {
                isFooter = isHave[1];
            }
        }
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
        bindData(baseViewHolder, position);
    }

    @Override
    public int getItemCount() {

        if ((isHeader && !isFooter) || (!isHeader && isFooter)) { //有头或者尾部
            return mDatas == null ? 1 : mDatas.size() + 1;
        }
        if (isHeader && isFooter) { //既有头也有尾部
            return mDatas == null ? 2 : mDatas.size() + 2;
        }
        return mDatas == null ? 0 : mDatas.size(); //其他情况
    }

    /**
     * 封装ViewHolder
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private Map<Integer, View> mViewMap;

        public BaseViewHolder(@NonNull View view) {
            super(view);
            mViewMap = new HashMap<>();
        }

        public View getView(int id) {
            View view = mViewMap.get(id);
            if (null != view) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }
    }

    /***************************需要重写的方法**********************************/
    public abstract int getLayoutId(int viewType);

    public abstract int bindData(@NonNull BaseViewHolder baseViewHolder, int position);


    /*****************************暴露的方法 start*******************************************/
    //添加单个数据
    public void addData(T data) {
        if (data != null) {
            mDatas.add(data);
            notifyDataSetChanged();
        }
    }

    //添加多条数据
    public void addDatas(List<T> datas) {
        if (datas != null && datas.size() > 0) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    //  返回数据
    public List<T> getDatas() {
        return mDatas;
    }

    /**
     * 删除单一数据
     *
     * @param data 数据源
     */
    public void removeData(T data) {
        if (data != null) {
            if (mDatas.contains(data)) {
                mDatas.remove(data);
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 删除单一数据
     *
     * @param position 数据下标
     */
    public void removeData(int position) {
        if (mDatas != null && mDatas.size() > position) {
            mDatas.remove(position);
            notifyDataSetChanged();
        }
    }

    /*****************************暴露的方法 end*******************************************/

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
