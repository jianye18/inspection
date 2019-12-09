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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageInfo<SpotCheckVo> getSpotCheckPageList(SpotCheckCondition spotCheckCondition) {
        PageHelper.startPage(spotCheckCondition.getPageNum(), spotCheckCondition.getPageSize());
        List<SpotCheckVo> list = spotCheckMapper.getSpotCheckListByCondition(spotCheckCondition);
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
    public List<SelectionLabel> getProductTypeList() {
        return spotCheckMapper.getProductTypeList();
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

}
