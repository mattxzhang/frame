package com.cheny.frame.model;

/**
 * 作者： by y on 2016/3/31.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class BizTest {

    public BizTest(String content, String title) {
        this.content = content;
        this.title = title;
    }

    private String title;
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
