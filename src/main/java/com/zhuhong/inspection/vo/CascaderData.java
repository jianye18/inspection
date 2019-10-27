package com.zhuhong.inspection.vo;

import lombok.Data;

import java.util.List;

/**
 * 级联选择展示类
 *
 * @Author: jian.ye
 * @Date: 2019/10/27 21:58
 */
@Data
public class CascaderData {

    private String value;

    private String label;

    private List<CascaderData> children;

}
