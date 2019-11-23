package com.zhuhong.inspection.model;

import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author 叶剑
 */
@Data
@Table(name = "tb_user_log")
public class UserLog extends BaseModel {

    public static final String USER_LOG_LOGIN = "登录";
    public static final String USER_LOG_SAVE = "保存";
    public static final String USER_LOG_UPDATE = "修改";
    public static final String USER_LOG_VIEW = "查询";
    public static final String USER_LOG_DELETE = "删除";

    @ApiModelProperty(name = "userId", value = "用户ID", example = "1")
    @Column(name = "user_id")
    private Integer userId;
    @ApiModelProperty(name = "userName", value = "用户名", example = "jian.ye")
    @Column(name = "user_name")
    private String userName;
    @ApiModelProperty(name = "ip", value = "ip地址", example = "192.168.1.117")
    @Column(name = "ip")
    private String ip;
    @ApiModelProperty(name = "type", value = "日志操作类型", example = "保存")
    @Column(name = "type")
    private String type;
    @ApiModelProperty(name = "description", value = "操作描述", example = "新增用户信息")
    @Column(name = "description")
    private String description;

}
