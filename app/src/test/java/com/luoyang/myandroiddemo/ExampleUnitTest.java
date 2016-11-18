package com.luoyang.myandroiddemo;

import com.luoyang.myandroiddemo.适配器模式.CH;
import com.luoyang.myandroiddemo.适配器模式.TransLator;
import com.luoyang.myandroiddemo.适配器模式.US;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void translatorTest() throws Exception{
        US usMan = new US();
        CH chMan = new TransLator(usMan);
        chMan.sayCH("US的朋友");
    }
}