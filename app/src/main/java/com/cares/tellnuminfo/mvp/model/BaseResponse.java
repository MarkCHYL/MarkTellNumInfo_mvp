package com.cares.tellnuminfo.mvp.model;

/**
 * @Desc
 * @作者 Mark
 * @时间 2019/1/22
 * @EMail 2285581945@qq.com
 */
public class BaseResponse<T> {

    /**
     * resultcode : 200
     * reason : Return Successd!
     * result : {"province":"湖南","city":"常德","areacode":"0736","zip":"415000","company":"联通","card":""}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private T result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

}
