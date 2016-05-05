package com.cheny.frame.http.convert;

/**
 * Created by y on 2016/3/17.
 */

import android.util.Log;

import com.cheny.frame.data.ErrResponse;
import com.cheny.frame.data.ResultResponse;
import com.cheny.frame.http.error.ResultException;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            Log.d("Network", "response>>" + response);
            //ResultResponse 只解析result字段
            ResultResponse resultResponse = gson.fromJson(response, ResultResponse.class);
            if (resultResponse.getResultCode() == 0) {
                //result==0表示成功返回，继续用本来的Model类解析
                return gson.fromJson(response, type);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                ErrResponse errResponse = gson.fromJson(response, ErrResponse.class);
                throw new ResultException(resultResponse.getResultCode(), errResponse.getErrorMsg());
            }
        } finally {
        }
    }
}
