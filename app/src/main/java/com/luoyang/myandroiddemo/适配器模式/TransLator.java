package com.luoyang.myandroiddemo.适配器模式;

/**
 * Created by LuoYang on 2016/11/18.
 */

public class TransLator implements CH {
    US us;

    public TransLator(US us) {
        this.us = us;
    }

    @Override
    public void sayCH(String s) {
        //TODO s=CH-->US
        us.sayUS(s);
    }
}
