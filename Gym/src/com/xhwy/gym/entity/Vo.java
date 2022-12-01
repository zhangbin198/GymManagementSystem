package com.xhwy.gym.entity;
import java.util.List;

/**
 * 和ajax打交道实体类
 *
 * @author 张斌
 * @version 1.0
 * @date 2022/7/19 10:33
 */
public class Vo {
    //code msg count data
    int code;
    String msg;
    int count;
    List<Object> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
