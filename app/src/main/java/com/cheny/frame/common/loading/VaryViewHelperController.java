/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cheny.frame.common.loading;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheny.frame.R;


public class VaryViewHelperController {

    private IVaryViewHelper helper;

    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    private VaryViewHelperController(IVaryViewHelper helper) {
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
        View layout = helper.inflate(R.layout.common_loading);
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
