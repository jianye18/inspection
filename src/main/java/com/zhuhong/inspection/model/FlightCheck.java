package com.zhuhong.inspection.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 抽检数据实体类
 *
 * @Author: jian.ye
 * @Date: 2019/10/15 21:39
 */
@Data
@Table(name = "tb_flight_check")
public class FlightCheck extends BaseModel {

    @ApiModelProperty(name = "businessName", value = "企业名称", example = "企业名称")
    @Column(name = "business_name")
    @ExcelProperty(index = 0 , value = "企业名称")
    private String businessName;
    @ApiModelProperty(name = "isDefect", value = "是否有缺陷：0-否，1-是", example = "0")
    @Column(name = "is_defect")
    @ExcelProperty(index = 1 , value = "是否有缺陷")
    private Integer isDefect;
    @ApiModelProperty(name = "problem", value = "缺陷问题", example = "缺陷问题")
    @Column(name = "problem")
    @ExcelProperty(index = 2 , value = "缺陷问题")
    private String problem;
    @ApiModelProperty(name = "precautions", value = "处理措施：1-无，2-未明示，3-责令整改，4-限期整改，5-停产整改", example = "3")
    @Column(name = "precautions")
    @ExcelProperty(index = 3 , value = "处理措施")
    private String precautions;
    @ApiModelProperty(name = "type", value = "飞检类型：1-国家飞检，2-地方飞检", example = "1")
    @Column(name = "type")
    @ExcelProperty(index = 4 , value = "飞检类型")
    private String type;
    @ApiModelProperty(name = "publishUnit", value = "发布单位", example = "发布单位")
    @Column(name = "publish_unit")
    @ExcelProperty(index = 5 , value = "发布单位")
    private String publishUnit;
    @ApiModelProperty(name = "publishDate", value = "发布日期", example = "2019/10/15")
    @Column(name = "publish_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd")
    @ExcelProperty(index = 6 , value = "发布日期")
    private Date publishDate;
    @ApiModelProperty(name = "sourceLink", value = "来源链接", example = "https://sbt.21food.cn/spbz/search.shtml")
    @Column(name = "source_link")
    @ExcelProperty(index = 7 , value = "来源链接")
    private String sourceLink;

}
