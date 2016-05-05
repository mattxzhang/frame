package com.cheny.frame.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cheny.frame.R;

/**
 * 作者： by y on 2016/5/5.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class Activity_Task extends AppCompatActivity {

    private TaskPresenter mTaskPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);


        Fragment_Task tasksFragment =
                (Fragment_Task) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = Fragment_Task.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, tasksFragment);
            transaction.commit();
        }

        // Create the presenter
        mTaskPresenter = new TaskPresenter(tasksFragment);
    }


}
