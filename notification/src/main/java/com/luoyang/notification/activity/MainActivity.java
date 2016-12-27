package com.luoyang.notification.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.luoyang.baselibrary.BaseActivity;
import com.luoyang.notification.MyApplication;
import com.luoyang.notification.R;
import com.luoyang.notification.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;
    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void initVar() {
        notificationManagerCompat = ((MyApplication) getApplication()).getNotificationManagerCompat();
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Log.d(TAG, "initView: context=" + context);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClick(View v) {
        Notification notification = null;
        switch (v.getId()) {
            case R.id.button:
                Intent intent = NotificationOpenActivity.getOpenIntent(context);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("setAutoCancel(true)")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();
                break;
            case R.id.button1:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("setSound setVibrate setLights")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        .setVibrate(new long[]{0, 1000, 1000, 1000})
                        .setLights(Color.BLUE, 1000, 1000)
                        .build();
                break;
            case R.id.button2:
                notificationManagerCompat.cancel(R.id.button1);
                break;
            case R.id.button3:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("setDefaults")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setAutoCancel(true)//TODO 提示：此处设置无效，需要设置通知点击跳转后才有效
                        .build();
                break;
            case R.id.button4:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("BigTextStyle")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("《望庐山瀑布》\n" +
                                "年代: 唐 作者: 李白\n" +
                                "日照香炉生紫烟，遥看瀑布挂前川。\n" +
                                "飞流直下三千尺，疑是银河落九天。"))
                        .build();
                break;
            case R.id.button5:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("BigPictureStyle")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.pic)))
                        .build();
                break;
            case R.id.button6:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("PRIORITY_DEFAULT")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build();
                break;
            case R.id.button7:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("PRIORITY_MIN")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MIN)
                        .build();
                break;
            case R.id.button8:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("PRIORITY_LOW")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .build();
                break;
            case R.id.button9:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("PRIORITY_HIGH")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .build();
                break;
            case R.id.button10:
                notification = new NotificationCompat.Builder(this)
                        .setContentTitle("这是ContextTitle")
                        .setContentText("PRIORITY_MAX")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                break;
        }
        if (v.getId() != R.id.button2 && null != notification) {
            notificationManagerCompat.notify(v.getId(), notification);
        }
    }
}
