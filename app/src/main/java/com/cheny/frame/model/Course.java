package com.cheny.frame.model;

/**
 * 作者： by y on 2016/4/1.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class Course {


    public Course(String cuorseNo, String name) {
        this.cuorseNo = cuorseNo;
        this.name = name;
    }

    private String name;
    private String cuorseNo;

    public String getCuorseNo() {
        return cuorseNo;
    }

    public void setCuorseNo(String cuorseNo) {
        this.cuorseNo = cuorseNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
