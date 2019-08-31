package com.example.hybss.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hybss.mvpmodule.R;


/**
 * @Author: yobin he
 * @Date:2019/7/9 14:51
 * @Email: heyibin@huawenpicture.com
 * @Des : 用于显示和取消相应的loading
 */
public class LoadingUtils {
    //加载中的View
    private RelativeLayout loadingView = null;

    private LoadingUtils() {

    }

    public static LoadingUtils instance;

    public static LoadingUtils newInstance() {
        if (instance == null) {
            synchronized (LoadingUtils.class) {
                if (instance == null) {
                    instance = new LoadingUtils();
                }
            }
        }
        return instance;
    }

    public void showLoading(Context context, String title) {
        if (context != null) {
            if (loadingView == null) {
                View view = View.inflate(context, R.layout.dialog_progress_layout, null);
                loadingView = new RelativeLayout(context);
                RelativeLayout.LayoutParams loadParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                loadParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                loadingView.addView(view, loadParams);

            }

            if (loadingView.getParent() == null) {
                TextView textView = loadingView.findViewById(R.id.dialog_progress_hint_tv);
                textView.setText(title != null && title.length() > 0 ? title : "");
                FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                FrameLayout decor = (FrameLayout) ((Activity) context).getWindow().getDecorView();
                decor.addView(loadingView, param);
            }
        }
    }

    //显示
    public void showLoading(Context context) {
        this.showLoading(context, "加载中...");
    }

    public void dismissLoading(Context context) {
        if (context != null && null != loadingView) {
            FrameLayout decor = (FrameLayout) ((Activity) context).getWindow().getDecorView();
            if (decor != null) {
                decor.removeView(loadingView);
                loadingView.removeAllViews();
                loadingView = null;
            }
        }
    }
}
