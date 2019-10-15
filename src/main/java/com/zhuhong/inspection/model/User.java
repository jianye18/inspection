package com.zhuhong.inspection.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 系统用户实体类
 * @Author: jian.ye
 * @Date: 2019/10/9 20:58
 */
@Data
@Table(name = "tb_user")
public class User extends BaseModel {

    private static final long serialVersionUID = 1267293988171991494L;

    @ApiModelProperty(name = "userName", value = "用户名", example = "jian.ye")
    @Column(name = "user_name")
    private String userName;
    @ApiModelProperty(name = "nickName", value = "昵称", example = "admin")
    @Column(name = "nick_name")
    private String nickName;
    @ApiModelProperty(name = "password", value = "密码", example = "123456")
    @Column(name = "password")
    private String password;
    @ApiModelProperty(name = "gender", value = "性别：1-男，2-女", example = "1")
    @Column(name = "gender")
    private Integer gender;
    @ApiModelProperty(name = "mobile", value = "手机号", example = "15212786971")
    @Column(name = "mobile")
    private String mobile;
    @ApiModelProperty(name = "email", value = "邮箱", example = "jianye@qq.com")
    @Column(name = "email")
    private String email;
    @ApiModelProperty(name = "workPlace", value = "工作单位", example = "合肥市检")
    @Column(name = "work_place")
    private String workPlace;
    @ApiModelProperty(name = "job", value = "职务", example = "检察专员")
    @Column(name = "job")
    private String job;
    @ApiModelProperty(name = "isMember", value = "是否会员：0-不是，1-是", example = "1")
    @Column(name = "is_member")
    private Integer isMember;
    @ApiModelProperty(name = "expireTime", value = "会员到期时间", example = "2020-10-01 12:56:34")
    @Column(name = "expire_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
    @ApiModelProperty(name = "userStatus", value = "账号状态：1-生效，2-禁用", example = "1")
    @Column(name = "user_status")
    private Integer userStatus;
    @ApiModelProperty(name = "roleIds", value = "角色ID拼接字符串", hidden = true)
    @Transient
    private String roleIds;

}
