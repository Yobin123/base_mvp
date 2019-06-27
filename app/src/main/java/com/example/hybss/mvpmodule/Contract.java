package com.example.hybss.mvpmodule;


import com.example.mvp_base_library.contracts.BaseContract;
import com.example.mvp_base_library.presenter.IPresenter;
import com.example.mvp_base_library.view.IView;

public class Contract extends BaseContract {
    interface ILoginModule {
        boolean login(String name, String password);
    }

    public interface ILoginView extends IView {
        void showTip(boolean isSuccess);
    }

    public interface ILoginPresenter extends IPresenter {
        void login(String name, String password);
    }
}
