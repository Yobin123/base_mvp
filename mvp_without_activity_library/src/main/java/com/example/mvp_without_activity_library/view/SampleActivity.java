package com.example.mvp_without_activity_library.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mvp_without_activity_library.contracts.SampleContracts;
import com.example.mvp_without_activity_library.presenter.SamplePresenterImpl;
import com.example.mvp_without_activity_library.view.base.BaseActivity;


/**
 * 一般activity，如果是已经存在的项目为了解决
 */
public class SampleActivity extends BaseActivity implements SampleContracts.ISampleView {
    Button button;
    private SampleContracts.ISamplePresenter mvpPre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPre = new SamplePresenterImpl(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        button = new Button(this);
        button.setText("true");
        layout.addView(button);
        setContentView(layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = button.getText().toString();
                if (s.equals("true")) {
                    mvpPre.change(true);
                } else {
                    mvpPre.change(false);
                }
            }
        });
    }

    @Override
    public void showTip(boolean isSuccess) {
        if (isSuccess) {
            button.setText("true");
        } else {
            button.setText("false");
        }
    }


    @Override
    protected int onLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void setControl() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPre != null) { //为了防止泄漏需要在最后进行相应处理。由于view的处理，所以需要手动进行相应操作
            mvpPre.detachView();
        }
    }

    @Override
    public Activity getSelfActivity() {
        return this;
    }
}
