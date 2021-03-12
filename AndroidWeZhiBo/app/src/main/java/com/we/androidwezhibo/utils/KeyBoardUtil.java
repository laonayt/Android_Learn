package com.we.androidwezhibo.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by xu.wang
 * Date on 2017/7/28 10:55
 */

public class KeyBoardUtil {
    /**
     * 关闭软键盘
     */
    public static void closeKeyBoard(AppCompatActivity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = ((InputMethodManager) activity.getApplicationContext().getSystemService(INPUT_METHOD_SERVICE));
            if (inputMethodManager.isActive()) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                                .getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void toggleKeyBoard(AppCompatActivity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = ((InputMethodManager) activity.getApplicationContext().getSystemService(INPUT_METHOD_SERVICE));
            if (inputMethodManager.isActive()) {
                inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void toggleKeyBoard(Context context) {
        InputMethodManager inputMethodManager = ((InputMethodManager) context.getApplicationContext().getSystemService(INPUT_METHOD_SERVICE));
        if (inputMethodManager.isActive()) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//打开软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }

    public static boolean isKeyBoardClosed(AppCompatActivity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = ((InputMethodManager) activity.getApplicationContext().getSystemService(INPUT_METHOD_SERVICE));
            if (inputMethodManager.isActive()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
