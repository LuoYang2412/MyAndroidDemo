package com.luoyang.shake.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.luoyang.shake.R;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by LuoYang on 2017/11/13.
 * 摇一摇工具类
 */

public class ShakeUtil {
    private Activity activity;
    private PopupWindow popupWindow;
    private SensorManager sensorManager;
    private ShakeSensorEventListener shakeSensorEventListener;
    private ImageView aboveImageView;
    private ImageView belowImageView;
    private AnimatorSet animatorSet;
    private int rawId;
    private SoundPool soundPool;
    private Vibrator vibrator;
    private ShakeCallBack callBack;
    private boolean useView = false;
    private boolean useSound = false;
    private boolean useVibrator = true;

    /**
     * @param activity activity
     * @param callBack 摇一摇回调
     */
    public ShakeUtil(Activity activity, ShakeCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * @param activity    activity
     * @param callBack    摇一摇状态回调
     * @param useView     使用默认View，默认不使用
     * @param useSound    使用声音，默认不使用
     * @param useVibrator 使用震动，默认使用
     */
    public ShakeUtil(Activity activity, ShakeCallBack callBack, boolean useView, boolean useSound, boolean useVibrator) {
        this.activity = activity;
        this.callBack = callBack;
        this.useView = useView;
        this.useSound = useSound;
        this.useVibrator = useVibrator;
    }

    /**
     * 创建摇一摇
     */
    public void onCreate() {
        initSensor();
        if (useSound) {
            initSoundPool();
        }
        if (useView) {
            initView();
            initAnim();
        }
        if (useVibrator) {
            initVar();
        }
    }

    /**
     * 初始化加速传感器
     */
    private void initSensor() {
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = null;
        if (sensorManager != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        shakeSensorEventListener = new ShakeSensorEventListener();
        sensorManager.registerListener(shakeSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void initVar() {
        vibrator = (Vibrator) activity.getSystemService(VIBRATOR_SERVICE);
    }

    private void initView() {
        View shakeView = LayoutInflater.from(activity).inflate(R.layout.shake_view_layout, ViewUtil.getRootView(activity), false);
        popupWindow = new PopupWindow(activity);
        popupWindow.setContentView(shakeView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        aboveImageView = shakeView.findViewById(R.id.shake_view_above_imageView);
        belowImageView = shakeView.findViewById(R.id.shake_viw_below_imageView);
    }

    /**
     * 销毁摇一摇
     */
    public void onDestory() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(shakeSensorEventListener);
        }
    }

    class ShakeSensorEventListener implements SensorEventListener {
        private long startTime = 0;
        private boolean isEnd = false;

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float[] values = event.values;
                float x = Math.abs(values[0]);
                float y = Math.abs(values[1]);
                float z = Math.abs(values[2]);
                if (x > 10.0 || y > 10.0 || z > 10.0) {
                    callBack.shakeStateCallBack(ShakeCallBack.START);
                    if (useSound) {
                        soundPool.play(rawId, 1, 1, 0, 0, 1);
                    }
                    if (useView && activity.hasWindowFocus()) {//显示PopupWindow时要判断依附Activity是否创建成功
                        popupWindow.showAtLocation(ViewUtil.getRootView(activity), Gravity.CENTER, 0, 0);
                        animatorSet.start();//执行动画3
                    }
                    if (useVibrator) {
                        vibrator.vibrate(500);
                    }
                    startTime = System.currentTimeMillis();
                    isEnd = false;
                } else {
                    if (startTime != 0 && System.currentTimeMillis() - startTime > 500 && !isEnd) {//500ms没有继续摇晃视为结束
                        isEnd = true;
                        if (useSound) {
                            soundPool.stop(rawId);
                        }
                        if (useView) {
                            animatorSet.end();
                            popupWindow.dismiss();
                        }
                        callBack.shakeStateCallBack(ShakeCallBack.END);
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    /**
     * 初始化音效
     */
    private void initSoundPool() {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        rawId = soundPool.load(activity, R.raw.beep, 1);//将资源转化为可播放对象
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        aboveImageView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = aboveImageView.getMeasuredHeight() / 2;
        //属性动画，根据屏幕圆点的
        ObjectAnimator up_image1 = ObjectAnimator.ofFloat(aboveImageView, "translationY", 0, -height);//向上移动
        ObjectAnimator up_image2 = ObjectAnimator.ofFloat(aboveImageView, "translationY", -height, 0);//返回
        ObjectAnimator down_image1 = ObjectAnimator.ofFloat(belowImageView, "translationY", 0, height);//向下移动
        ObjectAnimator down_image2 = ObjectAnimator.ofFloat(belowImageView, "translationY", height, 0);//返回
        //微信摇一摇动画
        animatorSet = new AnimatorSet();
        animatorSet.play(up_image1).with(down_image1);//先执行up1--down1
        animatorSet.play(up_image2).after(up_image1).with(down_image2);//后执行
        animatorSet.setDuration(1000);
    }

    public interface ShakeCallBack {
        int START = 0;
        int END = 1;

        public void shakeStateCallBack(int state);
    }
}
