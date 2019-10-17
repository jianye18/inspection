package com.zhuhong.inspection.service;

import com.zhuhong.inspection.model.SystemDataType;

import java.util.List;
import java.util.Map;

/**
 * 系统数据业务接口层
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:41
 */
public interface SystemDataService {

    /**
     * 根据类型获取系统分类数据
     *
     * @return List<SystemDataType>
     * @Author: jian.ye
     * @Date: 2019/10/16 16:42
     */
    Map<String, List> getAllSystemDataTypeList();

}
