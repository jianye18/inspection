package com.zhuhong.inspection.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Tree数据展示类
 *
 * @Author: jian.ye
 * @Date: 2019/10/13 21:57
 */
@Data
public class TreeData {

    @ApiModelProperty(name = "id", value = "节点Id", example = "1")
    private Integer id;
    @ApiModelProperty(name = "title", value = "节点名称", example = "用户管理")
    private String title;
    @ApiModelProperty(name = "checked", value = "节点是否选中")
    private boolean checked;
    @ApiModelProperty(name = "expand", value = "节点是否展开")
    private boolean expand;
    @ApiModelProperty(name = "children", value = "子节点集合")
    private List<TreeData> children;

}
