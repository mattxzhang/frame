package com.cheny.frame.gank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cheny.frame.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者： by y on 2016/5/5.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {


    private List<Task> mDataSource;
    protected LayoutInflater mInflater;
    protected Context mContext;
    private TaskItemListener mTaskItemListener;


    public TaskAdapter(Context context, List<Task> tasks, TaskItemListener taskItemListener) {
        this.mDataSource = tasks;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mTaskItemListener = taskItemListener;
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                mInflater.inflate(R.layout.item_task, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindData(mDataSource.get(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.cb_task)
        CheckBox cbTask;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.btn_delete)
        Button btnDelete;

        public MyViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(final Task item) {
            this.cbTask.setChecked(item.isComplete());
            this.tvContent.setText(item.getContent());
            this.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTaskItemListener != null) {
                        mTaskItemListener.onDeleteTask(item);
                    }
                }
            });
        }
    }

    public void updateSource(List<Task> data) {
        if (data == null) {
            return;
        }
        mDataSource.clear();
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<Task> data) {
        if (data == null) {
            return;
        }
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(Task task) {
        mDataSource.add(task);
        notifyDataSetChanged();
    }

    public void deleteData(Task task) {
        int index = mDataSource.indexOf(task);
        mDataSource.remove(task);
        notifyItemRemoved(index);
    }

    public interface TaskItemListener {

        void onDeleteTask(Task clickedTask);
    }


}
