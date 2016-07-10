package com.zhan.aoyoustore.base;

public class BaseResponseBean{

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int res;
        private String msg;

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
