package com.example.hybss.mvpmodule;

import com.example.mvp_base_library.presenter.base.BasePresenter;

public class LoginPresenterImpl extends BasePresenter<Contract.ILoginView> implements Contract.ILoginPresenter {
    private LoginModuleImpl module;

    public LoginPresenterImpl(Contract.ILoginView view) {
        super(view);
        module = new LoginModuleImpl();
    }

    @Override
    public void login(String name, String password) {
        if (isViewAttach()) { //用于判断是否绑定视图
            mvpRef.get().showTip(module.login(name, password));
        }
    }
}
