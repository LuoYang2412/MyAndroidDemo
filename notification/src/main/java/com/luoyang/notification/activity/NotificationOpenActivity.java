package com.luoyang.notification.activity;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;

import com.luoyang.baselibrary.BaseActivity;
import com.luoyang.notification.R;
import com.luoyang.notification.databinding.ActivityNotificationOpenBinding;

public class NotificationOpenActivity extends BaseActivity {
    private static final String TAG = "NotificationOpenActivit";
    protected ActivityNotificationOpenBinding binding;

    protected static Intent getOpenIntent(Context context) {
        Intent intent = new Intent(context, NotificationOpenActivity.class);
        return intent;
    }

    @Override
    protected void initVar() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification_open);
        Log.d(TAG, "initVar: context=" + context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClick(View v) {

    }
}
