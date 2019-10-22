package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.SystemDataTypeCondition;
import com.zhuhong.inspection.mapper.SystemDataTypeMapper;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.service.SystemDataService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.SelectionLabel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public Map<String, List> getAllSystemDataTypeList(Integer type, String code) {
        Map<String, List> map = new HashMap<>();
        SystemDataType systemDataType = new SystemDataType();
        systemDataType.setUsable(SystemDataType.ENABLE_1);
        systemDataType.setType(type);
        if (StringUtils.isNotEmpty(code)) {
            systemDataType.setCode(code);
        }
        List<SystemDataType> list = systemDataTypeMapper.select(systemDataType);
        if (Constants.BASE_TYPE_1.equals(type)) {
            map = getSpotCheckType(map, list);
        }
        if (Constants.BASE_TYPE_2.equals(type)) {
            map = getCriterionType(map, list);
        }
        return map;
    }

    @Override
    public boolean saveSystemDataType(SystemDataType systemDataType, Integer currentUserId) {
        systemDataType.setUpdateId(currentUserId);
        systemDataType.setUpdateTime(DateUtil.getCurrentDate());
        if (systemDataType.getId() != null) {
            return systemDataTypeMapper.updateByPrimaryKeySelective(systemDataType) > 0;
        } else {
            systemDataType.setCreateId(currentUserId);
            systemDataType.setCreateTime(DateUtil.getCurrentDate());
            return systemDataTypeMapper.insertSelective(systemDataType) > 0;
        }
    }

    @Override
    public PageInfo<SystemDataType> getSystemDataTypePageList(SystemDataTypeCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize(), "update_time desc");
        Example example = new Example(SystemDataType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("usable", SystemDataType.ENABLE_1);
        if (condition.getType() != null) {
            criteria.andEqualTo("type", condition.getType());
        }
        if (StringUtils.isNotEmpty(condition.getSearchPhrase())) {
            criteria.andLike("name", condition.getSearchPhrase());
        }
        List<SystemDataType> list = systemDataTypeMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 获取抽检分类的数据
     * @Author: jian.ye
     * @Date: 2019/10/19 13:58
     */
    private Map<String, List> getSpotCheckType(Map<String, List> map, List<SystemDataType> list) {
        if (list.size() > 0) {
            List<SelectionLabel> productTypeList = new ArrayList<>();
            List<SelectionLabel> institutionList = new ArrayList<>();
            for (SystemDataType dataType : list) {
                if (Constants.SPOT_CHECK_TYPE_CODE_1.equals(dataType.getCode())) {
                    // 获取产品分类下拉数据
                    SelectionLabel label = new SelectionLabel();
                    label.setValue(String.valueOf(dataType.getValue()));
                    label.setLabel(dataType.getName());
                    productTypeList.add(label);
                } else if (Constants.SPOT_CHECK_TYPE_CODE_2.equals(dataType.getCode())) {
                    // 获取公布机构下拉数据
                    SelectionLabel label = new SelectionLabel();
                    label.setValue(dataType.getName());
                    label.setLabel(dataType.getName());
                    institutionList.add(label);
                }
            }
            map.put("productTypeList", productTypeList);
            map.put("institutionList", institutionList);
        }
        return map;
    }
    /**
     * 获取标准分类的数据
     * @Author: jian.ye
     * @Date: 2019/10/19 13:58
     */
    private Map<String, List> getCriterionType(Map<String, List> map, List<SystemDataType> list) {
        if (list.size() > 0) {
            List<SelectionLabel> categoryList = new ArrayList<>();
            List<SelectionLabel> typeList = new ArrayList<>();
            List<SelectionLabel> publishUnitList = new ArrayList<>();
            for (SystemDataType dataType : list) {
                if (Constants.CRITERION_TYPE_CODE_1.equals(dataType.getCode())) {
                    // 获取标准一级分类下拉数据
                    SelectionLabel label = new SelectionLabel();
                    label.setValue(String.valueOf(dataType.getValue()));
                    label.setLabel(dataType.getName());
                    categoryList.add(label);
                } else if (Constants.CRITERION_TYPE_CODE_2.equals(dataType.getCode())) {
                    // 获取标准二级分类下拉数据
                    SelectionLabel label = new SelectionLabel();
                    label.setValue(String.valueOf(dataType.getValue()));
                    label.setLabel(dataType.getName());
                    typeList.add(label);
                } else if (Constants.CRITERION_TYPE_CODE_3.equals(dataType.getCode())) {
                    // 获取标准发布单位下拉数据
                    SelectionLabel label = new SelectionLabel();
                    label.setValue(String.valueOf(dataType.getValue()));
                    label.setLabel(dataType.getName());
                    publishUnitList.add(label);
                }
            }
            map.put("categoryList", categoryList);
            map.put("typeList", typeList);
            map.put("publishUnitList", publishUnitList);
        }
        return map;
    }

}
