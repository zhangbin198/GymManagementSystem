package com.xhwy.gym.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

public class SendSMS {
    private static String url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    /**
     * 发送短信方法
     *
     * @param mobile 接收短信验证码
     * @param code   验证码
     */
    public static boolean sendSMS(String mobile, String code) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        String content = new String("您的验证码是：" + code + "。请不要把验证码泄露给其他人。");
        NameValuePair[] data = {//提交短信
                new NameValuePair("account", SMSConfig.getProperty("sms.account")), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", SMSConfig.getProperty("sms.password")),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);

            String submitResult = method.getResponseBodyAsString();

            //System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(submitResult);
            Element root = doc.getRootElement();

            String resultCode = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            System.out.println(resultCode);
            System.out.println(msg);
            System.out.println(smsid);

            if ("2".equals(resultCode)) {
                System.out.println("短信提交成功");
                return true;
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return false;
    }
}

