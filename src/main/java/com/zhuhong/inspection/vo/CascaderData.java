package com.zhuhong.inspection.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 级联选择展示类
 *
 * @Author: jian.ye
 * @Date: 2019/10/27 21:58
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CascaderData {

    private String value;

    private String label;

    private List<CascaderData> children;

}
