package org.csu.mypetstore.util;

public class Result {
    private int code;
    private  String msg;
    private String total1;

    public String getTotal() {
        return total1;
    }

    public void setTotal(String total) {
        this.total1 = total;
    }

    public Result(){}

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
