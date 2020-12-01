package com.example.StudentManageSystem.pojo;

import java.io.Serializable;

public class Response  implements Serializable {
    private Integer code;
    private String msg;
    private Object data;


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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public static Response success(Integer code,String msg,Object data){
        Response response = new Response();
        response.setMsg(msg);
        response.setCode(200);
        response.setData(data);
        return response;
    }
    public static Response success(Object data){
        return success(200,"success",data);
    }

    public static Response success (String msg,Object data){
        return success(200,msg,data);
    }
    public static Response fail(Integer code,String msg,Object data){
        Response response = new Response();
        response.setMsg(msg);
        response.setCode(code);
        response.setData(data);
        return response;
    }
    public static Response fail(String msg){
        return fail(400,"Fail",msg);
    }

    public static Response fail(int code,String msg){
        return fail(code,"Fail",msg);
    }

    @Override
    public String toString() {
        return "{" + '"' + "code" + '"' + ":" + code + ","+ '"' +  "msg" + '"' + ":" + msg + "," + '"' + "data" + '"' + data + "}";
    }
}