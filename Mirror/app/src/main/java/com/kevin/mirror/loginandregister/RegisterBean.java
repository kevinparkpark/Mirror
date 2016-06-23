package com.kevin.mirror.loginandregister;

/**
 * Created by kevin on 16/6/23.
 */
public class RegisterBean {

    /**
     * result :
     * msg : 此手机号已被注册
     * data :
     */

    private String result;
    private String msg;
    private String data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
