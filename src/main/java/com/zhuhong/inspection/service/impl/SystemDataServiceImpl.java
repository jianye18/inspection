package com.zhuhong.inspection.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.SystemDataCondition;
import com.zhuhong.inspection.condition.SystemDataTypeCondition;
import com.zhuhong.inspection.mapper.SystemDataMapper;
import com.zhuhong.inspection.mapper.SystemDataTypeMapper;
import com.zhuhong.inspection.model.SystemData;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.service.SystemDataService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.MD5;
import com.zhuhong.inspection.vo.CascaderData;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SystemDataTypeVo;
import com.zhuhong.inspection.vo.SystemDataVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 系统数据业务实现类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:43
 */
@Service
public class SystemDataServiceImpl implements SystemDataService {

    @Autowired
    private SystemDataMapper systemDataMapper;

    @Override
    public Map<String, List> getSystemDataByTypeCode(String typeCodes) {
        Map<String, List> map = new HashMap<>();
        String[] arr = typeCodes.split(",");
        for (String typeCode : arr) {
            List<SelectionLabel> list = systemDataMapper.getSystemDataByTypeCode(typeCode);
            map.put(typeCode, list);
        }
        return map;
    }

    @Override
    public List<SelectionLabel> getAllSystemType() {
        return systemDataMapper.getAllSystemType();
    }

    @Override
    public PageInfo<SystemDataVo> getSystemDataPageList(SystemDataCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<SystemDataVo> list = systemDataMapper.getSystemDataPageList(condition);
        return new PageInfo<>(list);
    }

    @Override
    public boolean saveSystemData(SystemData systemData, Integer currentUserId) {
        Date current = DateUtil.getCurrentDate();
        systemData.setUpdateId(currentUserId);
        systemData.setUpdateTime(current);
        if (systemData.getId() == null) {
            systemData.setCreateId(currentUserId);
            systemData.setCreateTime(current);
            systemData.setCode(MD5.getMD5(String.valueOf(System.currentTimeMillis())));
            return systemDataMapper.insertSelective(systemData) > 0;
        } else {
            return systemDataMapper.updateByPrimaryKeySelective(systemData) > 0;
        }
    }

    @Override
    public Map<String, List> getHomeShowSystemData(String types) {
        String[] typeList = types.split(",");
        Map<String, List> map = new HashMap<>(typeList.length);
        if (typeList.length > 0) {
            for (String type : typeList) {
                List<SelectionLabel> list = systemDataMapper.getHomeShowSystemData(type, Constants.IS_VIEW_TRUE);
                map.put(type, list);
            }
        }
        return map;
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
                SelectionLabel label = new SelectionLabel();
                label.setValue(String.valueOf(dataType.getValue()));
                label.setLabel(dataType.getName());
                if (Constants.PRODUCT_TYPE.equals(dataType.getParam())) {
                    // 获取产品分类下拉数据
                    productTypeList.add(label);
                } else if (Constants.INSTITUTION.equals(dataType.getParam())) {
                    // 获取公布机构下拉数据
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
                SelectionLabel label = new SelectionLabel();
                label.setValue(String.valueOf(dataType.getValue()));
                label.setLabel(dataType.getName());
                if (Constants.CRITERION_CATEGORY.equals(dataType.getParam())) {
                    // 获取标准一级分类下拉数据
                    categoryList.add(label);
                } else if (Constants.CRITERION_TYPE.equals(dataType.getParam())) {
                    // 获取标准二级分类下拉数据
                    typeList.add(label);
                } else if (Constants.CRITERION_PUBLISH_UNIT.equals(dataType.getParam())) {
                    // 获取标准发布单位下拉数据
                    publishUnitList.add(label);
                }
            }
            map.put("categoryList", categoryList);
            map.put("typeList", typeList);
            map.put("publishUnitList", publishUnitList);
        }
        return map;
    }

    /**
     * 获取法规分类的数据
     * @Author: jian.ye
     * @Date: 2019/10/19 13:58
     */
    private Map<String, List> getLawType(Map<String, List> map, List<SystemDataType> list) {
        if (list.size() > 0) {
            List<SystemDataType> typeList = new ArrayList<>();
            List<SelectionLabel> publishUnitList = new ArrayList<>();
            List<SelectionLabel> ctList = new ArrayList<>();
            List<SelectionLabel> sourceList = new ArrayList<>();
            for (SystemDataType dataType : list) {
                if (Constants.LAW_CATEGORY.equals(dataType.getParam()) || Constants.LAW_TYPE.equals(dataType.getParam())) {
                    // 获取法规级联选择数据
                    typeList.add(dataType);
                    // 获取分类相关下拉数据
                    SelectionLabel label = new SelectionLabel();
                    label.setValue(String.valueOf(dataType.getValue()));
                    label.setLabel(dataType.getName());
                    label.setCode(dataType.getParam());
                    ctList.add(label);
                } else {
                    // 获取法规相关下拉数据
                    SelectionLabel label = new SelectionLabel();
                    label.setValue(String.valueOf(dataType.getValue()));
                    label.setLabel(dataType.getName());
                    if (Constants.LAW_PUBLISH_UNIT.equals(dataType.getParam())) {
                        // 入发布单位集合
                        publishUnitList.add(label);
                    } else if (Constants.LAW_SOURCE.equals(dataType.getParam())) {
                        // 入法规来源集合
                        sourceList.add(label);
                    }
                }
            }
            map.put("typeList", ctList);
            map.put("publishUnitList", publishUnitList);
            map.put("sourceList", sourceList);
            if (typeList.size() > 0) {
                List<CascaderData> categoryList = new ArrayList<>();
                for (SystemDataType systemDataType : typeList) {
                    if (systemDataType.getParentId() == null) {
                        CascaderData cascaderData = new CascaderData();
                        cascaderData.setValue(String.valueOf(systemDataType.getValue()));
                        cascaderData.setLabel(systemDataType.getName());
                        cascaderData.setChildren(initChildren(typeList, systemDataType.getValue()));
                        categoryList.add(cascaderData);
                    }
                }
                map.put("categoryList", categoryList);
            }
        }
        return map;
    }

    /**
     * 递归生成子节点数据集合
     * @param  typeList 待递归集合
     * @param  parentId 父级节点ID
     * @return List<CascaderData> 返回递归后生成的集合
     * @Author: jian.ye
     * @Date: 2019/10/29 10:02
     */
    private List<CascaderData> initChildren(List<SystemDataType> typeList, Integer parentId) {
        List<CascaderData> list = new ArrayList<>();
        for (SystemDataType systemDataType : typeList) {
            if (parentId.equals(systemDataType.getParentId())) {
                CascaderData cascaderData = new CascaderData();
                cascaderData.setValue(String.valueOf(systemDataType.getValue()));
                cascaderData.setLabel(systemDataType.getName());
                list.add(cascaderData);
            }
        }
        return list;
    }

}
