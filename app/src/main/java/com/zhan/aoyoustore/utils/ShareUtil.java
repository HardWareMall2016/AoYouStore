package com.zhan.aoyoustore.utils;

/**
 * 作者：伍岳 on 2016/3/8 22:18
 * 邮箱：wuyue8512@163.com
 //
 //         .............................................
 //                  美女坐镇                  BUG辟易
 //         .............................................
 //
 //                       .::::.
 //                     .::::::::.
 //                    :::::::::::
 //                 ..:::::::::::'
 //              '::::::::::::'
 //                .::::::::::
 //           '::::::::::::::..
 //                ..::::::::::::.
 //              ``::::::::::::::::
 //               ::::``:::::::::'        .:::.
 //              ::::'   ':::::'       .::::::::.
 //            .::::'      ::::     .:::::::'::::.
 //           .:::'       :::::  .:::::::::' ':::::.
 //          .::'        :::::.:::::::::'      ':::::.
 //         .::'         ::::::::::::::'         ``::::.
 //     ...:::           ::::::::::::'              ``::.
 //    ```` ':.          ':::::::::'                  ::::..
 //                       '.:::::'                    ':'````..
 //
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.zhan.framework.common.context.GlobalContext;

public class ShareUtil {
    private static final String PREFERENCE_NAME = "VIPTEACHER";
    public static final String REMIND_SETTING ="remind_setting";
    public static final String REMIND_SETTING_VOICE ="remind_setting_voice";
    public static final String REMIND_SETTING_POPUP ="remind_setting_popup";
    public static final String REMIND_SETTING_SHOCK ="remind_setting_shock";

    //version版本  判断引导页是否显示
    public static final String VERSION = "version";

    //boolean 开关
    public static final String VALUE_TURN_OFF ="0";
    public static final String VALUE_TURN_ON ="1";

    //极光推送别名
    public static final String JPUSH_ALIAS = "JPush_Alias";

    private static SharedPreferences getInstance() {
        return GlobalContext._context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static void setValue(String key, String value) {
        SharedPreferences.Editor edit = getInstance().edit();
        edit.putString(key, value);
        edit.commit();
    }
    public static String getStringValue(String key) {
        return getInstance().getString(key, null);
    }
    public static String getStringValue(String key,String def) {
        return getInstance().getString(key, def);
    }
}
