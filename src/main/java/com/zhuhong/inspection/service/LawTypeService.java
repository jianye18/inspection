package com.zhuhong.inspection.service;

import com.zhuhong.inspection.vo.SelectionLabel;

import java.util.List;

/**
 * @author 叶剑
 */
public interface LawTypeService {

    /**
     * 根据法规分类获取子分类
     * @param code
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/22 16:08
     */
    List<SelectionLabel> getLawTypeListByCode(String code);

}
