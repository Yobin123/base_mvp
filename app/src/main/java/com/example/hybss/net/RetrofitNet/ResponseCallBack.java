package com.example.hybss.net.RetrofitNet;

import com.huawenpicture.eims.utils.GsonUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 统一处理回调回来的函数，也可以用ImoudleCallBack进行回调
 * 1.用该回调是将处理放在presenter层处理
 * 2；   
 *
 * @param <T>
 */
public abstract class ResponseCallBack<T> implements Callback<BaseResponse<T>> {
    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                if (response.body().getCode() == 200) {
                    if (response != null) {
                        onSuccess(response.body().getData());
                    }
                } else {
                    BaseResponse<T> body = response.body();
                    if (body != null) {
                        FailureBody failureBody = new FailureBody();
                        failureBody.setMsg(body.getMsg());
                        failureBody.setCode(body.getCode());
                        onFailure(failureBody);
                    }
                }
            }
        } else {
            try {
                String string = response.errorBody().string();
                FailureBody failureBody = GsonUtil.GsonToBean(string, FailureBody.class);
                onFailure(failureBody);
            } catch (IOException e) {
                e.printStackTrace();
                onError(e);
            }
        }
    }

    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
        onError(t);
    }

    abstract void onSuccess(T data); //成功的回调

    abstract void onFailure(FailureBody error); //请求错误的响应

    abstract void onError(Throwable t); //服务端错误响应
}
