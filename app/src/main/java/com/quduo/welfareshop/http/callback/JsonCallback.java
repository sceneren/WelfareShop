package com.quduo.welfareshop.http.callback;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.base.SimpleResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Case By:
 * package:wiki.scene.shop.http.callback
 * Author：scene on 2017/6/27 11:38
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        HashMap<String, String> params = ApiUtil.createParams();
//        if (ShopApplication.hasLogin && ShopApplication.userInfo != null && !ShopApplication.userInfo.getAccess_token().isEmpty()) {
//            params.put("access_token", ShopApplication.userInfo.getAccess_token());
//            params.put("mobile", ShopApplication.userInfo.getMobile());
//            params.put("user_id", String.valueOf(ShopApplication.userInfo.getUser_id()));
//        }
        request.getParams().put(params);
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("没有填写泛型参数");
        }
        //得到第二层泛型的真实数据
        Type rawType = ((ParameterizedType) type).getRawType();
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (body == null) {
            return null;
        }
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType != LzyResponse.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        } else {
            if (typeArgument == Void.class) {
                SimpleResponse simpleResponse = gson.fromJson(jsonReader, SimpleResponse.class);
                response.close();
                return (T) simpleResponse.toBaseResponse();
            } else {
                LzyResponse baseResponse = gson.fromJson(jsonReader, type);
                response.close();
                int code = baseResponse.code;
                if (code == 200) {
                    //成功
                    return (T) baseResponse;
                } else if (code == 201) {
                    throw new IllegalStateException("unRegister");
                } else {
                    throw new IllegalStateException(baseResponse.message);
                }

            }
        }

    }
}
