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
@Table(name = "tb_spot_check")
public class SpotCheck extends BaseModel {

    @ApiModelProperty(name = "company", value = "标称委托企业", example = "广州市迪彩化妆品有限公司")
    @Column(name = "company")
    @ExcelProperty(index = 0 , value = "标称委托企业")
    private String company;
    @ApiModelProperty(name = "producer", value = "标称生产企业/进口代理商名称", example = "平舆冰王生物工程有限公司")
    @Column(name = "producer")
    @ExcelProperty(index = 1 , value = "标称生产企业/进口代理商名称")
    private String producer;
    @ApiModelProperty(name = "unit", value = "被采样单位名称", example = "吴兴丝美亚美容美发店")
    @Column(name = "unit")
    @ExcelProperty(index = 2 , value = "被采样单位名称")
    private String unit;
    @ApiModelProperty(name = "sample", value = "样品名称", example = "冰王®防晒保湿隔离露")
    @Column(name = "sample")
    @ExcelProperty(index = 3 , value = "样品名称")
    private String sample;
    @ApiModelProperty(name = "specification", value = "包装规格", example = "30ml")
    @Column(name = "specification")
    @ExcelProperty(index = 4 , value = "包装规格")
    private String specification;
    @ApiModelProperty(name = "expireTime", value = "保质期", example = "20180401/20210400")
    @Column(name = "expire_time")
    @ExcelProperty(index = 5 , value = "保质期")
    private String expireTime;
    @ApiModelProperty(name = "productType", value = "产品分类,关联数据常量表", example = "1")
    @Column(name = "product_type")
    @ExcelProperty(index = 6 , value = "产品分类")
    private String productType;
    @ApiModelProperty(name = "location", value = "产地", example = "安徽")
    @Column(name = "location")
    @ExcelProperty(index = 7 , value = "产地")
    private String location;
    @ApiModelProperty(name = "checkResult", value = "抽检结果：0-不合格，1-合格", example = "1")
    @Column(name = "check_result")
    @ExcelProperty(index = 8 , value = "抽检结果")
    private Integer checkResult;
    @ApiModelProperty(name = "subject", value = "不合格项目", example = "检出成分与批件成分不符")
    @Column(name = "subject")
    @ExcelProperty(index = 9 , value = "不合格项目")
    private String subject;
    @ApiModelProperty(name = "institution", value = "公布机构", example = "国家药品监督管理局")
    @Column(name = "institution")
    @ExcelProperty(index = 10 , value = "公布机构")
    private String institution;
    @ApiModelProperty(name = "publishDate", value = "公布日期", example = "2019/10/15")
    @Column(name = "publish_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd")
    @ExcelProperty(index = 11 , value = "公布日期")
    private Date publishDate;
    @ApiModelProperty(name = "isFake", value = "是否涉嫌假冒：0-否，1-是", example = "0")
    @Column(name = "is_fake")
    @ExcelProperty(index = 12 , value = "涉嫌假冒")
    private Integer isFake;
    @ApiModelProperty(name = "sourceLink", value = "来源链接", example = "https://sbt.21food.cn/spbz/search.shtml")
    @Column(name = "source_link")
    @ExcelProperty(index = 13 , value = "来源链接")
    private String sourceLink;

}
