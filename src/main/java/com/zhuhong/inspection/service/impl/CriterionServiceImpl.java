package com.zhuhong.inspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.CriterionCondition;
import com.zhuhong.inspection.dto.CriterionDto;
import com.zhuhong.inspection.mapper.CriterionMapper;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.Criterion;
import com.zhuhong.inspection.service.AnnexService;
import com.zhuhong.inspection.service.CriterionService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.CriterionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 标准数据业务实现层
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 10:24
 */
@Service
public class CriterionServiceImpl implements CriterionService {

    @Autowired
    private CriterionMapper criterionMapper;
    @Autowired
    private AnnexService annexService;

    @Override
    public boolean saveCriterion(CriterionDto criterionDto, Integer currentUserId) {
        boolean flag = false;
        List<Annex> annexList = criterionDto.getAnnexList();
        Criterion criterion = JSONObject.parseObject(JSONObject.toJSONString(criterionDto), Criterion.class);
        Date currentDate = DateUtil.getCurrentDate();
        criterion.setUpdateId(currentUserId);
        criterion.setUpdateTime(currentDate);
        if (criterion.getId() == null) {
            criterion.setCreateId(currentUserId);
            criterion.setCreateTime(currentDate);
            int r = criterionMapper.insertSelective(criterion);
            if (r > 0) {
                flag = true;
                annexService.saveAnnex(false, annexList, criterion.getId(), Constants.BASE_TYPE_2);
            }
        } else {
            int r = criterionMapper.updateByPrimaryKey(criterion);
            if (r > 0) {
                flag = true;
                annexService.saveAnnex(true, annexList, criterion.getId(), Constants.BASE_TYPE_2);
            }
        }
        return flag;
    }

    @Override
    public PageInfo<CriterionVo> getCriterionPageList(CriterionCondition condition) {
        if (StringUtils.isNotEmpty(condition.getStartDate())) {
            condition.setStartDate(DateUtil.getDayStartTime(condition.getStartDate()));
            condition.setEndDate(DateUtil.getDayEndTime(condition.getEndDate()));
        }
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<CriterionVo> list = criterionMapper.getCriterionListByCondition(condition);
        if (list.size() > 0) {
            for (CriterionVo criterionVo : list) {
                criterionVo.setAnnexList(annexService.getAnnexList(criterionVo.getId(), Constants.BASE_TYPE_2));
            }
        }
        return new PageInfo<>(list);
    }

    @Override
    public boolean judgeCriterionNameIsExist(String name, Integer criterionId) {
        Example example = new Example(Criterion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        if (criterionId != 0 ) {
            criteria.andNotEqualTo("id", criterionId);
        }
        if (criterionMapper.selectByExample(example).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCriterion(Integer criterionId, Integer currentUserId) {
        Criterion criterion = new Criterion();
        criterion.setId(criterionId);
        criterion.setUpdateId(currentUserId);
        criterion.setUpdateTime(DateUtil.getCurrentDate());
        criterion.setUsable(Criterion.ENABLE_0);
        boolean flag = criterionMapper.updateByPrimaryKeySelective(criterion) > 0;
        if (flag) {
            annexService.deleteAnnex(criterionId, Constants.BASE_TYPE_2);
        }
        return flag;
    }

    @Override
    public CriterionVo getCriterionById(Integer id) {
        CriterionCondition condition = new CriterionCondition();
        condition.setId(id);
        CriterionVo criterionVo = criterionMapper.getCriterionById(condition);
        List<Annex> annexList = annexService.getAnnexList(criterionVo.getId(), Constants.BASE_TYPE_2);
        if (annexList.size() > 0) {
            criterionVo.setAnnexList(annexList);
        }
        criterionVo.setSummary(criterionVo.getSummary().replaceAll("\\r\\n", "<br/>"));
        return criterionVo;
    }

    @Override
    public int getCriterionTotalCount() {
        Criterion criterion = new Criterion();
        criterion.setUsable(Criterion.ENABLE_1);
        return criterionMapper.selectCount(criterion);
    }

}
