package com.example.hybss.net.RetrofitNet;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName(value = "code", alternate = "status")
    private int code; //返回码
    private T data; //具体数据结果
    private String msg; //返回信息
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
