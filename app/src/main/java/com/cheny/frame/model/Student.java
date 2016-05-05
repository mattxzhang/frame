package com.cheny.frame.model;

import java.util.List;

/**
 * 作者： by y on 2016/4/1.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class Student {


    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }


    private String name;
    private String id;
    private List<Course> course;

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
