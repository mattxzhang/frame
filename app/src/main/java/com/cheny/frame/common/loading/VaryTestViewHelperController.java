package com.cheny.frame.common.loading;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheny.frame.R;

/**
 * 作者： by y on 2016/3/31.
 * QQ：  44766986
 * 邮箱：44766986@qq.com
 */
public class VaryTestViewHelperController {
    private IVaryViewHelper helper;

    public VaryTestViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    private VaryTestViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }

    public void showNetworkError(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_network_error);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        textView.setText("网络连接失败");

        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_network_error);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(errorMsg)) {
            textView.setText(errorMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_error_msg));
        }


        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_network_error);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_error_msg));
        }


        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showLoading(String msg) {
        View layout = helper.inflate(R.layout.common_three_loading);
        if (!TextUtils.isEmpty(msg)) {
            TextView textView = (TextView) layout.findViewById(R.id.tv_load_msg);
            textView.setText(msg);
        }
        helper.showLayout(layout);
    }

    public void restore() {
        helper.restoreView();
    }

}
