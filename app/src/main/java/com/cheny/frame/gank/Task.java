package com.cheny.frame.gank;

/**
 * 作者： by y on 2016/5/5.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class Task {

    private String id;
    private String title;
    private String content;
    private boolean complete;

    public Task() {
    }


    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
