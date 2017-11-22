package com.luoyang.shake.utils;

import android.app.Activity;
import android.view.ViewGroup;

/**
 * Created by LuoYang on 2017/11/17.
 */

public class ViewUtil {
    /**
     * 获取当前Activity的RootView
     * @param activity activity
     * @return @{@link ViewGroup}RootView
     */
    public static ViewGroup getRootView(Activity activity) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup content = decorView.findViewById(android.R.id.content);
        return (ViewGroup) content.getChildAt(0);
    }
}
