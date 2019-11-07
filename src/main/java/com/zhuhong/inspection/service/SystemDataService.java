package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.SystemDataTypeCondition;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SystemDataTypeVo;

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
     * @param type
     * @param code
     * @param param
     * @return List<SystemDataType>
     * @Author: jian.ye
     * @Date: 2019/10/16 16:42
     */
    Map<String, List> getAllSystemDataTypeList(Integer type, String code, String param);

    /**
     * 保存系统相关分类数据
     * @param systemDataType
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/21 15:28
     */
    boolean saveSystemDataType(SystemDataType systemDataType, Integer currentUserId);

    /**
     * 根据条件分页查询分类数据
     * @param condition
     * @return PageInfo<SystemDataType>
     * @Author: jian.ye
     * @Date: 2019/10/21 15:36
     */
    PageInfo<SystemDataTypeVo> getSystemDataTypePageList(SystemDataTypeCondition condition);

    /**
     * 获取首页筛选项
     * @return List<Map<String, Object>>
     * @Author: jian.ye
     * @Date: 2019/10/30 14:00
     */
    List<Map<String, Object>> getHomePageFilterItem();

    /**
     * 根据条件查询分类数据
     * @param systemDataType
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/7 20:37
     */
    List<SelectionLabel> getLawCategoryData(SystemDataType systemDataType);

}
