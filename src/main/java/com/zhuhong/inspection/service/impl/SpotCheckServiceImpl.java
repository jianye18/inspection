package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.mapper.SpotCheckMapper;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.service.SpotCheckService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SpotCheckVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽检数据业务实现类
 *
 * @Author: jian.ye
 * @Date: 2019/10/15 21:57
 */
@Service
public class SpotCheckServiceImpl implements SpotCheckService {

    @Autowired
    private SpotCheckMapper spotCheckMapper;

    @Override
    public boolean insertSpotCheck(SpotCheck spotCheck, Integer currentUserId) {
        spotCheck.setCreateId(currentUserId);
        spotCheck.setCreateTime(DateUtil.getCurrentDate());
        spotCheck.setUpdateId(currentUserId);
        spotCheck.setUpdateTime(DateUtil.getCurrentDate());
        return spotCheckMapper.insertSelective(spotCheck) > 0;
    }

    @Override
    public PageInfo<SpotCheckVo> getSpotCheckPageList(SpotCheckCondition condition) {
        if (StringUtils.isNotEmpty(condition.getStartDate())) {
            condition.setStartDate(DateUtil.getDayStartTime(condition.getStartDate()));
            condition.setEndDate(DateUtil.getDayEndTime(condition.getEndDate()));
        }
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<SpotCheckVo> list = spotCheckMapper.getSpotCheckListByCondition(condition);
        return new PageInfo<>(list);
    }

    @Override
    public boolean saveSpotCheck(SpotCheck spotCheck, Integer currentUserId) {
        spotCheck.setUpdateId(currentUserId);
        spotCheck.setUpdateTime(DateUtil.getCurrentDate());
        return spotCheckMapper.updateByPrimaryKeySelective(spotCheck) > 0;
    }

    @Override
    public SpotCheckVo getSpotCheckById(Integer id) {
        SpotCheckCondition condition = new SpotCheckCondition();
        condition.setId(id);
        return spotCheckMapper.getSpotCheckById(condition);
    }

    @Override
    public List<SelectionLabel> getAllInstitution() {
        return spotCheckMapper.getInstitutionList();
    }

    @Override
    public List<SelectionLabel> getAllSubject() {
        return spotCheckMapper.getSubjectList();
    }

    @Override
    public List<SelectionLabel> getProductTypeList() {
        return spotCheckMapper.getProductTypeList();
    }

    @Override
    public List<SelectionLabel> getSampleTypeList() {
        return spotCheckMapper.getSampleTypeList();
    }

    @Override
    public boolean deleteSpotCheck(Integer id, Integer currentUserId) {
        SpotCheck spotCheck = new SpotCheck();
        spotCheck.setId(id);
        spotCheck.setUpdateId(currentUserId);
        spotCheck.setUpdateTime(DateUtil.getCurrentDate());
        spotCheck.setUsable(SpotCheck.ENABLE_0);
        return spotCheckMapper.updateByPrimaryKeySelective(spotCheck) > 0;
    }

    @Override
    public int getSpotCheckTotalCount(Integer isNew) {
        return spotCheckMapper.getSpotCheckCount(isNew);
    }

    @Override
    public Map getCountByProducer(String producer) {
        Map<String, Integer> map = new HashMap<>();
        map.put("total", spotCheckMapper.getCountByProducer(producer, null));
        map.put("hgCount", spotCheckMapper.getCountByProducer(producer, "合格"));
        map.put("bhgCount", spotCheckMapper.getCountByProducer(producer, "不合格"));
        return map;
    }

}
