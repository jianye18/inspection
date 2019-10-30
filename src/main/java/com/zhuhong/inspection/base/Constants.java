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

    /**
     * 基础分类：1-抽检、2-标准、3-法规
     */
    public static final Integer BASE_TYPE_1 = 1;
    public static final Integer BASE_TYPE_2 = 2;
    public static final Integer BASE_TYPE_3 = 3;

    /**
     * 抽检数据分类编码
     */
    public static final String SPOT_CHECK = "spot_check";
    public static final String PRODUCT_TYPE = "product_type";
    public static final String INSTITUTION = "institution";

    /**
     * 标准数据分类编码
     */
    public static final String CRITERION = "criterion";
    public static final String CRITERION_CATEGORY = "criterion_category";
    public static final String CRITERION_TYPE = "criterion_type";
    public static final String CRITERION_PUBLISH_UNIT = "criterion_publish_unit";

    /**
     * 法规数据分类编码
     */
    public static final String LAW_TYPE = "law";
    public static final String LAW_CATEGORY = "law_category";
    public static final String LAW_PUBLISH_UNIT = "law_publish_unit";
    public static final String LAW_SOURCE = "law_source";

}
