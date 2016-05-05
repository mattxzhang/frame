package com.cheny.frame.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cheny.frame.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者： by y on 2016/5/5.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class Fragment_Task extends Fragment implements TasksContract.View {

    @Bind(R.id.btn_add_task)
    Button btnAddTask;
    @Bind(R.id.task_list)
    RecyclerView taskList;
    @Bind(R.id.task_refresh_layout)
    SwipeRefreshLayout taskRefreshLayout;
    private TasksContract.Presenter mPresenter;

    private TaskAdapter mTaskAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, root);

        mTaskAdapter = new TaskAdapter(getActivity(), new ArrayList<>(), new TaskAdapter.TaskItemListener() {
            @Override
            public void onDeleteTask(Task clickedTask) {
                mPresenter.deleteTask(clickedTask);
            }
        });
        taskList.setLayoutManager(new LinearLayoutManager(getActivity()));
        taskList.setAdapter(mTaskAdapter);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewTask();
            }
        });

        taskRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTasks();
                taskRefreshLayout.setRefreshing(false);
            }
        });

        return root;

    }

    @Override
    public void onAddTask(Task task) {
        mTaskAdapter.addData(task);
    }

    @Override
    public void onLoadTasks(List<Task> tasks) {
        mTaskAdapter.updateSource(tasks);


    }

    @Override
    public void onDeleteTask(Task task) {
        mTaskAdapter.deleteData(task);
    }

    @Override
    public void toDetail(Task task) {

    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static Fragment_Task newInstance() {
        return new Fragment_Task();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
