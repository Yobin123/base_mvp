package com.example.hybss.net.RetrofitNet;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Author: yobin he
 * @Date:2019/8/28 17:11
 * @Email: heyibin@huawenpicture.com
 * @Des : 用于处理请求参数错误信息
 */
public class FailureBody {
    private List data;
    @SerializedName(value = "code",alternate = "status")
    private int code;
    private String msg;
    

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
