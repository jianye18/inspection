package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.SystemDataCondition;
import com.zhuhong.inspection.condition.SystemDataTypeCondition;
import com.zhuhong.inspection.model.SystemData;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SystemDataTypeVo;
import com.zhuhong.inspection.vo.SystemDataVo;

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
     * 根据父级编码获取常量数据
     * @param typeCodes
     * @return Map<String, List>
     * @Author: jian.ye
     * @Date: 2019/11/19 15:27
     */
    Map<String, List> getSystemDataByTypeCode(String typeCodes);

    /**
     * 获取所有系统分类
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/21 9:29
     */
    List<SelectionLabel> getAllSystemType();

    /**
     * 分页获取系统分类常量数据
     * @param condition
     * @return PageInfo<SystemData>
     * @Author: jian.ye
     * @Date: 2019/11/21 11:09
     */
    PageInfo<SystemDataVo> getSystemDataPageList(SystemDataCondition condition);

    /**
     * 保存系统分类常量信息
     * @param systemData
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/21 11:33
     */
    boolean saveSystemData(SystemData systemData, Integer currentUserId);

}
