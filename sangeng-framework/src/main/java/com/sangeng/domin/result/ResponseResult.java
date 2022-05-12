package com.sangeng.domin.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sangeng.enums.AppHttpCodeEnum;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

/**
 *@JsonInclude(JsonInclude.Include.NON_NULL)标记是jackson包提供的json序列化方法，已经集成于Springboot2.0中，此方法的配置意在可以对实体json序列化的时候进行对应的数值处理，
 *
 * //将该标记放在属性上，如果该属性为NULL则不参与序列化
 * ————————————————
 * 版权声明：本文为CSDN博主「夏至微凉、」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_31404603/article/details/88560166
 *
 * Serializable是一个对象序列化的接口，一个类只有实现了Serializable接口，它的对象才是可序列化的。因此如果要序列化某些类的对象，这些类就必须实现Serializable接口。而实际上，Serializable是一个空接口，没有什么具体内容，它的目的只是简单的标识一个类的对象可以被序列化。
 * ————————————————
 * 版权声明：本文为CSDN博主「斜阳雨陌」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_15037231/article/details/81913194
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T>  implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseResult errorResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }
    public static ResponseResult okResult() {
        ResponseResult result = new ResponseResult();
        return result;
    }
    public static ResponseResult okResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, msg);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getMsg());
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums, String msg){
        return setAppHttpCodeEnum(enums,msg);
    }

    public static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getMsg());
    }

    private static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String msg){
        return okResult(enums.getCode(),msg);
    }

    public ResponseResult<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
