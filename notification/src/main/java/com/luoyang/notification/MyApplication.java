package com.luoyang.notification;

import android.app.Application;
import androidx.core.app.NotificationManagerCompat;

import com.luoyang.baselibrary.utils.ActivityLifeManager;

/**
 * Created by LuoYang on 2016/12/26.
 */

public class MyApplication extends Application {
    /**
     * NotifiCation管理器
     */
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(ActivityLifeManager.getActivityLifeManager());
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    public NotificationManagerCompat getNotificationManagerCompat() {
        if (notificationManagerCompat != null) return notificationManagerCompat;
        return null;
    }
}
