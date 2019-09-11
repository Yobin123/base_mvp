package com.example.hybss.net.RetrofitNet;

/**
 * 请求参数基础类,同时可以添加相应的头部信息
 */
public class BaseRequest<T> {
    
    private T options;
    
    public T getOptions() {
        return options;
    }

    public void setOptions(T options) {
        this.options = options;
    }
}
