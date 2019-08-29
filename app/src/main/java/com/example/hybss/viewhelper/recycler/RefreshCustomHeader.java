package com.example.hybss.viewhelper.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.andview.refreshview.callback.IHeaderCallBack;

/**
 * @Author: yobin he
 * @Date:2019/8/26 9:42
 * @Email: heyibin@huawenpicture.com
 * @Des :
 */
public class RefreshCustomHeader extends LinearLayout implements IHeaderCallBack {
    public RefreshCustomHeader(Context context) {
        super(context);
    }

    public RefreshCustomHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onStateNormal() {

    }

    @Override
    public void onStateReady() {

    }

    @Override
    public void onStateRefreshing() {

    }

    @Override
    public void onStateFinish(boolean success) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {

    }

    @Override
    public void setRefreshTime(long lastRefreshTime) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public int getHeaderHeight() {
        return 0;
    }
}
