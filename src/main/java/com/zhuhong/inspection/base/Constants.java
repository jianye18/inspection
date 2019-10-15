package com.zhuhong.inspection.base;

/**
 * 常量类
 * @Author: jian.ye
 * @Date: 2019/10/8 16:21
 */
public class Constants {

    public static final String TOKEN = "token";

    /**
     * 账号状态：1-生效，2-禁用
     */
    public static final Integer SYS_USER_STATUS_1 = 1;
    public static final Integer SYS_USER_STATUS_2 = 2;

    /**
     * 角色状态：1-生效，2-禁用
     */
    public static final Integer ROLE_STATUS_1 = 1;
    public static final Integer ROLE_STATUS_2 = 2;

    /**
     * 删除用户：1-成功，2-失败，3-用户包含角色，无法删除
     */
    public static final int DELETE_USER_1 = 1;
    public static final int DELETE_USER_2 = 2;
    public static final int DELETE_USER_3 = 3;

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 权限类型：1-page,2-link,3-button
     */
    public static final Integer PERMISSION_TYPE_1 = 1;
    public static final Integer PERMISSION_TYPE_2 = 2;
    public static final Integer PERMISSION_TYPE_3 = 3;

}
