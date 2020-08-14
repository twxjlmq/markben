package com.markben.beans.enums;

/**
 * 状态码枚举类
 * @author 乌草坡
 * @since 1.0
 */
public enum MarkbenStatusEnums {

    SUCCESS(1, "OK"),

    FAIL(-1, "FAIL"),


    NULL_ERROR(10000, "空指针异常"),


    NOT_LOGIN(20003, "用户未登录"),

    LOGIN_USER_ERROR(20004, "用户名错误"),

    LOGIN_PWD_ERROR(20005, "密码错误"),

    LOGIN_CAPTCHA_ERROR(20006, "验证码错误"),


    NOT_DATA(30000, "没有查询到数据"),

    REQUEST_ARG_ERROR(30001, "请求参数错误(异常)"),

    UNAUTHORIZED(40001, "没有权限");

    private int status;

    private String msg;

    MarkbenStatusEnums(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 通过状态码获取状态对象
     * @param status 状态码
     * @return 返回对象
     */
    public static MarkbenStatusEnums getStatusObj(int status) {
        MarkbenStatusEnums statusEnum = null;
        for(MarkbenStatusEnums tmp : MarkbenStatusEnums.values()) {
            if(tmp.getStatus() == status) {
                statusEnum = tmp;
                break;
            }
        }
        return statusEnum;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
