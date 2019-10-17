package com.zhuhong.inspection.base;

/**
 * 枚举错误类型
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 10:44
 */
public enum ExceptionEnum {

    UNKNOW_ERROR(-1, "未知错误"),

    USER_ROLE_EXIST(301, "角色已被使用，无法删除"),

    ROLE_PERMISSION_EXIST(302, "权限已被绑定，无法删除"),

    ;

    private Integer code;

    private String msg;

    ExceptionEnum (Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
