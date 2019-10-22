package com.zhuhong.inspection.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 附件相关类
 *
 * @Author: jian.ye
 * @Date: 2019/10/22 23:05
 */
@Data
@Table(name = "tb_annex")
public class Annex {
    private static final Integer TYPE_1 = 1;
    private static final Integer TYPE_2 = 2;
    @Id
    @ApiModelProperty(hidden = true)
    private Integer id;
    @ApiModelProperty(name = "businessId", value = "业务ID", example = "1")
    @Column(name = "business_id")
    private Integer businessId;
    @ApiModelProperty(name = "name", value = "附件名称", example = "111.doc")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "path", value = "附件路径", example = "/usr/docs/")
    @Column(name = "path")
    private String path;
    @ApiModelProperty(name = "type", value = "附件类型：1-标准，2-法规", example = "1")
    @Column(name = "type")
    private Integer type;
}
