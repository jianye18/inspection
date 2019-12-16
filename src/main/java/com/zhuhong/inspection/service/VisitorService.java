package com.zhuhong.inspection.service;

import com.zhuhong.inspection.model.Visitor;

/**
 * @author 叶剑
 */
public interface VisitorService {

    /**
     * 新增访客数据
     * @param visitor
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/16 9:36
     */
    boolean insertVisitor(Visitor visitor);

    /**
     * 获取访客数量
     * @param type
     * @return int
     * @Author: jian.ye
     * @Date: 2019/12/16 11:14
     */
    int getVisitorCount(Integer type);

}
