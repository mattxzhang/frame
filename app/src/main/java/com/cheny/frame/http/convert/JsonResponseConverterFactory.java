package com.cheny.frame.http.convert;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by y on 2016/3/17.
 */
public class JsonResponseConverterFactory extends Converter.Factory {

    private final ObjectMapper objectMapper;

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new JacksonResponseBodyConverter<>(objectMapper, type);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new JacksonResponseBodyConverter<>(objectMapper, type);
    }

    public static Converter.Factory create(ObjectMapper mapper) {
        return new JsonResponseConverterFactory(mapper);
    }

    private JsonResponseConverterFactory(ObjectMapper mapper) {
        if (mapper == null) throw new NullPointerException("mapper == null");
        this.objectMapper = mapper;
    }

    public static Converter.Factory create() {
        return create(new ObjectMapper());
    }
}
