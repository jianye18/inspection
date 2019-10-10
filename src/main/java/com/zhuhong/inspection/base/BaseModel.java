package com.zhuhong.inspection.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类公共字段
 * @Author: jian.ye
 * @Date: 2019/10/9 20:48
 */
@Data
public class BaseModel implements Serializable {

    public static final Integer ENABLE_0 = 0;
    public static final Integer ENABLE_1 = 1;

    /**主键ID*/
    @Id
    @ApiModelProperty(hidden = true)
    private Integer id;
    /**创建人ID*/
    @ApiModelProperty(hidden = true)
    @Column(name = "create_id")
    private Integer createId;
    /**创建时间*/
    @ApiModelProperty(hidden = true)
    @Column(name = "create_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**更新人ID*/
    @ApiModelProperty(hidden = true)
    @Column(name = "update_id")
    private Integer updateId;
    /**更新时间*/
    @ApiModelProperty(hidden = true)
    @Column(name = "update_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**数据是否有效：0-无效，1-有效*/
    @ApiModelProperty(hidden = true)
    @Column(name = "usable")
    private Integer usable;

}
