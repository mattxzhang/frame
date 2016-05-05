package com.cheny.frame.gank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者： by y on 2016/5/5.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class TaskPresenter implements TasksContract.Presenter {


    private TasksContract.View mTaskView;
    List<Task> mTasks = new ArrayList<>();


    public TaskPresenter(TasksContract.View taskView) {
        mTaskView = taskView;
        mTaskView.setPresenter(this);
    }

    @Override
    public void addNewTask() {
        Task task = new Task();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        task.setTitle(formatter.format(new Date()));
        task.setId(String.valueOf(System.currentTimeMillis()));
        task.setContent("Task Content:" + String.valueOf(task.getId()));
        task.setComplete(true);
        if (mTaskView != null) {
            mTaskView.onAddTask(task);
        }
    }

    @Override
    public void loadTasks() {
        if (mTaskView != null) {
            mTaskView.onLoadTasks(mTasks);
        }
    }

    @Override
    public void deleteTask(Task task) {
        if (mTaskView != null) {
            mTaskView.onDeleteTask(task);
        }
    }

    @Override
    public void toDetail(Task task) {
        if (mTaskView != null) {
            mTaskView.toDetail(task);
        }
    }

    @Override
    public void start() {

    }
}
