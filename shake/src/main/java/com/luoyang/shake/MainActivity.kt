package com.luoyang.shake

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.luoyang.shake.utils.ShakeUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ShakeUtil.ShakeCallBack {

    private var isRegisterListener: Boolean = false
    private var shakeUtil: ShakeUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shakeUtil = ShakeUtil(this, this, true, true, true)
        button.setOnClickListener({
            if (!isRegisterListener) {
                shakeUtil!!.onCreate()
                isRegisterListener = true
                button.text = "关闭摇一摇"
            } else {
                shakeUtil!!.onDestory()
                isRegisterListener = false
                button.text = "开启摇一摇"
            }
        })
    }

    override fun shakeStateCallBack(state: Int) {
        Log.d(javaClass.simpleName, "State:" + state)
    }
}
