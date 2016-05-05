package com.cheny.frame.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by y on 2016/3/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Test {
    @JsonProperty("test")
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
