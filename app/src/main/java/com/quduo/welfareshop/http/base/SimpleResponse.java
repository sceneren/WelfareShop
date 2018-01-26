package com.quduo.welfareshop.http.base;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

    public int code;
    public String message;

    public LzyResponse toBaseResponse() {
        LzyResponse lzyResponse = new LzyResponse();
        lzyResponse.code = code;
        lzyResponse.message = message;
        return lzyResponse;
    }
}