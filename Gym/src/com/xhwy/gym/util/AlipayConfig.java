package com.xhwy.gym.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：com.xhwy.gym.util.AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000121627863";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDb+TPJ5aEJQYiZi9rbtM1nzwvJwGojOfYT4npjDg54+JP0W7rlaBaA93KuIdv/T5/WS/mk8Z0ChSFmoa6VEErsNxMrfYmL6CdM/FqxV/Y9h5m8TuIH48zmi8ckn7DfrRyXchC+e2ovL9AHC3j971pGYLVV81L3qEt4h1Jm6US8ycQpBXT3F4ydwN9y+JE2IRUPMwMwlITKBWc9BkNEoJMWhNwM/jWEjysLflZVgTJyvP6jWRtrqKup6v37PVB7zHIBOrSuN0Bh0Or8P7SScTEvDedP/HtcGezlKVoDeZKttLxH97GMUtW2pmvqJhkmf/2Xroh1msoVp/mJB46p1aM/AgMBAAECggEAaWdIvIaOmmvPc5vDHCHPht18DYlokkaUtkSAVnSG1Mpvwtgtfv1YQyXOyseorfbOZRX0omevz2RRc+cnS4AKFpPxWJaH0QVFx6roHjBC6orOTKATyKoMJbB2lQL78sH1gFAE2/ExGTk02htSN4N4Y161pWC5sbfvqepVvsAp0reLwAdQmb13LzVGSDHPKS7v4MJyJOKYbeoA5xBSL4OZLgR+Paiw5Gxji8zpcnx8xB3E3ybyLCAZznpcx2/S5JLKw/7XC3lvtNpw1JaPbrlEFL7Ur1FvSwDUIoFRXfE70rhdzR8qCOCuopZvtnCjGsCQ8gqz53T052AYwAUiD0BDKQKBgQD7PO1HqfX5XUXuguAk86r9ZbbFHvjOe+iImKA/9aeA8KH/JHYRnZ5ofQGkJbT/2tqOr3Qej2zow3saiOgTuEn5OM8QgP/oInkGTSTiKDcod/aSzals+PAu7BVrcWBLlR6MOs5XQll5cGIuqdNyTzryrerOIGu9OvoIylphxxdfGwKBgQDgJJJSnOpDj0p3ANbLsjer4Kwcv53er7jrH1rG/hb1fQbJfSMCVLS+nAQNIM52PAGR+uxOQAmHWkKzCHLgK0e3ihWtXp8/74AmoEGy9j3C2gozpZTMPN0SETI54r4Q4HuCg5IlgPyVoV+rKKFKXEYQaS0oxIzzf57h6kya5R76rQKBgQCW0ZZnhOICVjna2noP9K7a+ypi3xrhaYTIK0dX3fI19z5oWcz++i3T7+cCURh81zaInU8J4XOxr1fBMBqRa/oMQEwdTjNReRBJ9hmEIjwqcQl2SzvADRiyMksRergh51y1NcAUpeHsgsiN1qj2YBW1BS6FmvTSXgsnJ8JmHAwtrQKBgQDYdFjd8O0Ga0VVIPlNYwECOgjcBmv1lir03KW0j8IqNiAsymzZnmWhwVbkOq9HXPGDQRedBZhuHstUy67sLr+mTnJap4sQmkBejNqFcej418mMULYeehRfkz22yvITqEV+J4MmNqc19M5HowgHoI6E4J0cxpr7t13++14ZI3TETQKBgQCWX4q0uP1/RYxZyVcxZ0Y/+OapjSGXfEucg/dIBjslFkF4Xz5sHZFaY0Z0BH+6b+dSLFnUTLODI33dA8qE82GKehuL9oVzH68E/artw2nxWiWRUDxhuLIrx7EPQ5neTBtVzrXqwDJwPegVA85eWnOhWriCHZLWvwyRxHtLwD85DA==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsVqGgWf/64EFBs1KPjdHISdKktwJF0Wz4OgdK8jifiGuHsOavJBj9uafMwEqwIpsXz272Edi3NNu2htQsHntRxDPdCmO6uuaVVA/NvTAy88csJr38SKtkFOwdSqaWumDcCR+fWGjw9Zd3iTbEZmOTcbC7o4B+quEd8UOq0e9rjVuD+PSVrT0z97+Ecl3poOAlK26/gHHnnZjnT7qsw34yDTvKlJny0lqoIvf8IR8uB7ITjzWxUE+Y8UkU/5H4hjtQYyCFwutzSFv7xHriYcUlUty/A39fi7mdI8ZIXhw7NLYZXSCGu/yO8/6kZL6bQta1Oa/DJkG5wjffofDVgAl5wIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/Gym_Web_exploded/admin.html#/pagesend/zb/index.jsp";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/Gym_Web_exploded/admin.html#/pagesend/zb/index.jsp";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "C:\\";
//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

