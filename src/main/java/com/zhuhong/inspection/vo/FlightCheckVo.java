package com.zhuhong.inspection.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.model.FlightCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 飞检数据展示类
 *
 * @Author: jian.ye
 * @Date: 2019/11/9 19:41
 */
@Data
public class FlightCheckVo extends FlightCheck {

    @ApiModelProperty(name = "title", value = "App列表展示名称字段")
    private String title;
    @ApiModelProperty(name = "publishTime", value = "App列表展示时间字段", example = "2019-11-19")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date publishTime;

}
