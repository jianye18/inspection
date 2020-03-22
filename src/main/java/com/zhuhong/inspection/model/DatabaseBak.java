package com.zhuhong.inspection.model;

import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 数据库备份实体类
 * @Author: jian.ye
 * @Date: 2020/3/18 0018 14:49
 */
@Data
@Table(name = "tb_database_bak")
public class DatabaseBak extends BaseModel {

    @ApiModelProperty(name = "name", value = "备份文件名", example = "inspection-2020-03-18-bak.sql")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "remark", value = "备注", example = "备注")
    @Column(name = "remark")
    private String remark;
    @ApiModelProperty(name = "type", value = "数据库操作类型：1-备份，2-恢复", example = "1")
    @Column(name = "type")
    private Integer type;

}
