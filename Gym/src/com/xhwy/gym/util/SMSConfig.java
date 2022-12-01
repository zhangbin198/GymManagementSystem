package com.xhwy.gym.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 张斌
 * @version 1.0
 * @date 2022/7/6 2:00
 */
public class SMSConfig {
    static Properties properties = new Properties();

    static {
        String fileName = "sms.properties";
        //将文件转成流
        InputStream inputStream = SMSConfig.class
                .getClassLoader().getResourceAsStream(fileName);
        try {
            //属性文件加载流 -->属性名及属性值已放到内存中【MAP形式】
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        String property = SMSConfig.getProperty("mysql.url123213");
        System.out.println(property);
    }
}
