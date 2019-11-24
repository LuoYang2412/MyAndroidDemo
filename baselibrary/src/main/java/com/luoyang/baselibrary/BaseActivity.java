package com.luoyang.baselibrary;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

/**
 * Created by LuoYang on 2016/12/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initVar();
        initView();
        initData();
    }

    /**
     * 初始化不依赖Context的全局属性
     */
    protected abstract void initVar();

    /**
     * View相关操作，初始化依赖Context的全局属性
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 点击事件处理
     * @param v 被点击的view
     */
    protected abstract void onClick(View v);
}
