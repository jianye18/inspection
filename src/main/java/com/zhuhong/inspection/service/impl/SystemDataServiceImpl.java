package com.zhuhong.inspection.service.impl;

import com.zhuhong.inspection.mapper.SystemDataTypeMapper;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.service.SystemDataService;
import com.zhuhong.inspection.vo.SelectionLabel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统数据业务实现类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:43
 */
@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataTypeMapper systemDataTypeMapper;

    @Override
    public Map<String, List> getAllSystemDataTypeList() {
        Map<String, List> map = new HashMap<>();
        SystemDataType systemDataType = new SystemDataType();
        systemDataType.setUsable(SystemDataType.ENABLE_1);
        // 获取产品分类下拉数据
        systemDataType.setType(1);
        List<SystemDataType> list1 = systemDataTypeMapper.select(systemDataType);
        if (list1.size() > 0) {
            List<SelectionLabel> productTypeList = new ArrayList<>();
            for (SystemDataType dataType : list1) {
                SelectionLabel label = new SelectionLabel();
                label.setValue(String.valueOf(dataType.getId()));
                label.setLabel(dataType.getName());
                productTypeList.add(label);
            }
            map.put("productTypeList", productTypeList);
        }
        // 获取公布机构下拉数据
        systemDataType.setType(2);
        List<SystemDataType> list2 = systemDataTypeMapper.select(systemDataType);
        if (list2.size() > 0) {
            List<SelectionLabel> institutionList = new ArrayList<>();
            for (SystemDataType dataType : list2) {
                SelectionLabel label = new SelectionLabel();
                label.setValue(dataType.getName());
                label.setLabel(dataType.getName());
                institutionList.add(label);
            }
            map.put("institutionList", institutionList);
        }
        return map;
    }

}
