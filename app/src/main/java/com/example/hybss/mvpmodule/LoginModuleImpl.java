package com.example.hybss.mvpmodule;

import android.text.TextUtils;

public class LoginModuleImpl implements Contract.ILoginModule {
    @Override
    public boolean login(String name, String password) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
}
