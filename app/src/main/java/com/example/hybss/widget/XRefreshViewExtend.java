package com.example.hybss.viewhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.andview.refreshview.XRefreshView;

/**
 * @Author: yobin he
 * @Date:2019/9/5 9:51
 * @Email: heyibin@huawenpicture.com
 * @Des : 侧滑需要，防止滑动冲突
 */
public class XRefreshViewExtend extends XRefreshView {
    public XRefreshViewExtend(Context context) {
        super(context);
    }

    public XRefreshViewExtend(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;
    private int startX;
    private int startY;


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                int dX = (int) (ev.getX() - startX);
                int dY = (int) (ev.getY() - startX);
                if (Math.abs(dX) > Math.abs(dY)) {//左右滑动
                    return true;
                } else {//上下滑动
                    return false;
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
