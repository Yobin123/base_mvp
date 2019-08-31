package com.example.hybss.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hybss.mvpmodule.R;

/**
 * @Author: yobin he
 * @Date:2019/8/19 11:35
 * @Email: heyibin@huawenpicture.com
 * @Des :一般弹窗处理
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PopDialog extends PopupWindow {
    private DialogClickListener listener;
    private Context context;
    private View parentView;
    private View view;
    private String title;
    private String content;

    public PopDialog(Context context, View parentView, String title, String content) {
        this.context = context;
        this.parentView = parentView;
        this.title = title;
        this.content = content;

        initView();
    }

    private void initView() {
        //设置屏幕透明度 
        WindowManager.LayoutParams lp = ((Activity) this.context).getWindow().getAttributes();
        lp.alpha = 0.5f;
        ((Activity) this.context).getWindow().setAttributes(lp);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_dialog_base, null);
        TextView title = view.findViewById(R.id.tv_dialog_title);
        TextView content = view.findViewById(R.id.tv_dialog_content);
        TextView cancel = view.findViewById(R.id.tv_dialog_cancel);
        TextView sure = view.findViewById(R.id.tv_dialog_sure);
        View bg = view.findViewById(R.id.tv_dialog_view);

        title.setText(this.title);
        content.setText(this.content);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null)
                    listener.onCancel();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null)
                    listener.onSure();
            }
        });

        setContentView(view);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ((Activity) PopDialog.this.context).getWindow().getAttributes();
                lp.alpha = 1f;
                ((Activity) PopDialog.this.context).getWindow().setAttributes(lp);
            }
        });

        showAsDropDown(parentView, Gravity.CENTER, 0, 0);
    }

    public interface DialogClickListener {
        void onSure(); //确定按钮

        void onCancel();//取消按钮
    }

    public void setOnDialogClickListener(DialogClickListener listener) {
        this.listener = listener;
    }
}
