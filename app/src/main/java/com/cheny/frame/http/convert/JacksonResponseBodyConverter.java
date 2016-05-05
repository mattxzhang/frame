package com.cheny.frame.http.convert;

import android.util.Log;

import com.cheny.frame.data.ErrResponse;
import com.cheny.frame.data.ResultResponse;
import com.cheny.frame.http.error.ResultException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by y on 2016/3/17.
 */
public class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final ObjectMapper objectMapper;
    private final Type type;

    JacksonResponseBodyConverter(ObjectMapper objectMapper, Type type) {
        this.objectMapper = objectMapper;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            Log.d("Network", "response>>" + response);
            //ResultResponse 只解析result字段
           /* ResultResponse resultResponse = objectMapper.readValue(response, ResultResponse.class);
            if (resultResponse.getResultCode() == 0) {
                //result==0表示成功返回，继续用本来的Model类解析
                JavaType javaType = objectMapper.getTypeFactory().constructType(type);
                return objectMapper.readValue(response, javaType);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                ErrResponse errResponse = objectMapper.readValue(response, ErrResponse.class);
                throw new ResultException(resultResponse.getResultCode(), errResponse.getErrorMsg());
            }*/
            JavaType javaType = objectMapper.getTypeFactory().constructType(type);
            return objectMapper.readValue(response, javaType);
        } finally {
        }
    }
}
