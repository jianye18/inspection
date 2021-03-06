package com.zhuhong.inspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.LawCondition;
import com.zhuhong.inspection.dto.LawDto;
import com.zhuhong.inspection.mapper.LawMapper;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.Law;
import com.zhuhong.inspection.service.AnnexService;
import com.zhuhong.inspection.service.LawService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.LawVo;
import com.zhuhong.inspection.vo.SelectionLabel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 法规业务实现层
 * @Author: jian.ye
 * @Date: 2019/10/23 8:50
 */
@Service
public class LawServiceImpl implements LawService {

    @Autowired
    private LawMapper lawMapper;
    @Autowired
    private AnnexService annexService;

    @Override
    public boolean saveLaw(LawDto lawDto, Integer currentUserId) {
        boolean flag = false;
        Date current = DateUtil.getCurrentDate();
        List<Annex> annexList = lawDto.getAnnexList();
        Law law = JSONObject.parseObject(JSONObject.toJSONString(lawDto), Law.class);
        law.setUpdateId(currentUserId);
        law.setUpdateTime(current);
        if (law.getId() == null) {
            law.setCreateId(currentUserId);
            law.setCreateTime(current);
            int r = lawMapper.insertSelective(law);
            if (r > 0) {
                flag = true;
                annexService.saveAnnex(false, annexList, law.getId(), Constants.BASE_TYPE_3);
            }
        } else {
            int r = lawMapper.updateByPrimaryKeySelective(law);
            if (r > 0) {
                flag = true;
                annexService.saveAnnex(true, annexList, law.getId(), Constants.BASE_TYPE_3);
            }
        }
        return flag;
    }

    @Override
    public PageInfo<LawVo> getLawPageList(LawCondition condition) {
        if (StringUtils.isNotEmpty(condition.getStartDate())) {
            condition.setStartDate(DateUtil.getDayStartTime(condition.getStartDate()));
            condition.setEndDate(DateUtil.getDayEndTime(condition.getEndDate()));
        }
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<LawVo> list = lawMapper.getLawListByCondition(condition);
        if (list.size() > 0) {
            for (LawVo lawVo : list) {
                initAnnex(lawVo);
            }
        }
        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteLaw(Integer lawId, Integer currentUserId) {
        Law law = lawMapper.selectByPrimaryKey(lawId);
        law.setUsable(Law.ENABLE_0);
        boolean flag = lawMapper.updateByPrimaryKeySelective(law) > 0;
        if (flag) {
            annexService.deleteAnnex(lawId, Constants.BASE_TYPE_3);
        }
        return flag;
    }

    @Override
    public LawVo getLawById(Integer id) {
        LawCondition condition = new LawCondition();
        condition.setId(id);
        LawVo lawVo = lawMapper.getLawById(condition);
        initAnnex(lawVo);
        return lawVo;
    }

    @Override
    public int getLawTotalCount(Integer isNew) {
        return lawMapper.getLawCount(isNew);
    }

    private void initAnnex(LawVo lawVo) {
        List<Annex> annexList = annexService.getAnnexList(lawVo.getId(), Constants.BASE_TYPE_3);
        if (annexList.size() > 0) {
            lawVo.setAnnexList(annexList);
        }
    }
}
