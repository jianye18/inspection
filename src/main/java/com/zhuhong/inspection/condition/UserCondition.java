package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户查询参数类
 * @Author: jian.ye
 * @Date: 2019/10/10 13:51
 */
@Data
public class UserCondition extends BaseCondition {

    @ApiModelProperty(name = "isMember", value = "是否会员：0-不是，1-是", example = "1")
    private Integer isMember;
    @ApiModelProperty(name = "gender", value = "性别：1-男，2-女", example = "1")
    private Integer gender;
    @ApiModelProperty(name = "status", value = "账号状态：1-生效，2-禁用", hidden = true)
    private Integer status;

    @Override
    public String toString() {
        return "UserCondition{" +
                "isMember=" + isMember +
                ", gender=" + gender +
                ", status=" + status +
                "} " + super.toString();
    }
}
