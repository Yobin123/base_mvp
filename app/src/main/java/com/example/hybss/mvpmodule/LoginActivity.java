package com.example.hybss.mvpmodule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.mvp_base_library.view.base.BaseActivity;
import com.example.mvp_without_activity_library.view.SampleActivity;

public class LoginActivity extends BaseActivity<LoginPresenterImpl> implements Contract.ILoginView {
    private EditText et_username, et_password;
    private Button btn_login;

    @Override
    protected LoginPresenterImpl bindPresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    protected int onLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        et_username = fv(R.id.et_username);
        et_password = fv(R.id.et_password);
        btn_login = fv(R.id.btn_login);
    }

    @Override
    protected void addListener() {
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void setControl() {

    }

    @Override
    public void showTip(boolean isSuccess) {
//        Toast.makeText(getSelfActivity(), "this is " + isSuccess, Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, SampleActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mvpPre.login(et_username.getText().toString().trim(), et_password.getText().toString().trim());
                break;

        }
    }
}
