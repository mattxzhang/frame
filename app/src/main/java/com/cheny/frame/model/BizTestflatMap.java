package com.cheny.frame.model;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * 作者： by y on 2016/4/1.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class BizTestflatMap {

    private static List<Student> studentList;

    public static void main(String[] args) {
        initData();
        testFlatMap();
    }


    public static void initData() {
        studentList = new ArrayList<>();
        Student student1 = new Student("test1", "test1");
        student1.setCourse(initCourse(student1));
        Student student2 = new Student("test2", "test2");
        student2.setCourse(initCourse(student2));
        Student student3 = new Student("test3", "test3");
        student3.setCourse(initCourse(student3));

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);


    }

    public static List<Course> initCourse(Student student) {
        List<Course> courses = new ArrayList<>();
        String id = student.getId();
        String name = student.getName();
        for (int i = 0; i < 10; i++) {
            courses.add(new Course(id + String.valueOf(i), name + String.valueOf(i)));
        }
        return courses;


    }

    public static void testFlatMap() {
        Observable.from(studentList)
                .flatMap(student -> Observable.from(student.getCourse()))
                .subscribe(new Observer<Course>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Course course) {
                        System.out.println("CuorseNo:" + course.getCuorseNo() + "CuorseName:" + course.getName());
                    }
                });
    }
}


