package com.example.hybss.net.RetrofitNet;

/**
 *请求参数基础类,同时可以添加相应的头部信息
 */
public class BaseRequest<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseRequest(T data) {
        this.data = data;
    }
}
