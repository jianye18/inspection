package com.zhuhong.inspection.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 叶剑
 */
@Data
@Table(name = "tb_visitor")
public class Visitor extends BaseModel {

    @ApiModelProperty(name = "type", value = "访客统计类型：1-阅读，2-访客", example = "1")
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(name = "ip", value = "IP地址", example = "172.168.1.117")
    @Column(name = "ip")
    private String ip;
    @ApiModelProperty(name = "visitDate", value = "访问日期", example = "2019-12-16")
    @Column(name = "visit_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date visitDate;

}
