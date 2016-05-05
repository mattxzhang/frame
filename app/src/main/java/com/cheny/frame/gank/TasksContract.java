package com.cheny.frame.gank;

import java.util.List;

/**
 * 作者： by y on 2016/5/5.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public interface TasksContract {


    interface View extends BaseView<Presenter> {

        void onLoadTasks(List<Task> tasks);

        void onDeleteTask(Task task);

        void onAddTask(Task task);

        void toDetail(Task task);
    }

    interface Presenter extends BasePresenter {

        void loadTasks();

        void addNewTask();

        void deleteTask(Task task);

        void toDetail(Task task);
    }

}
