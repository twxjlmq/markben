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

    INVALID_TOKEN(10001, "无效的TOKEN"),

    //上传文件相关错误码定义
    MULTIPART_UPLOAD_ERROR(10500, "文件上传失败"),

    MULTIPART_UPLOAD_MAX_SIZE(10101, "文件大小超出限制"),

    MULTIPART_UPLOAD_NOT_ALLOW_FILE_TYPE(10102, "不允许的文件类型"),


    NOT_LOGIN(20003, "用户未登录"),

    LOGIN_USER_ERROR(20004, "用户名错误"),

    LOGIN_PWD_ERROR(20005, "密码错误"),

    LOGIN_USER_OR_PWD_ERROR(20006, "用户名或密码错误"),

    LOGIN_CAPTCHA_ERROR(20007, "验证码错误"),


    USER_NOT_IN_CORP(20100, "用户不在企业中"),

    USER_NOT_IN_ORG(20101, "用户不在部门中"),

    CORP_USER_NOT_EXIST(20200, "企业用户不存在"),

    CORP_NOT_EXIST(20300, "企业不存在"),


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
