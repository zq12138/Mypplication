package com.example.admin.mypplication;

import java.io.Serializable;

/**
 * Created by zq on 2017/1/16.
 */

public class BaseVo implements Serializable {

    private String reason;
    private ResultVo result;
    private String error_code;

    public ResultVo getResult() {
        return result;
    }

    public void setResult(ResultVo result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseVo{" +
                "reason='" + reason + '\'' +
                ", error_code='" + error_code + '\'' +
                '}';
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
