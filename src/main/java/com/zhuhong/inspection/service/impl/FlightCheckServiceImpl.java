package com.zhuhong.inspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.FlightCheckCondition;
import com.zhuhong.inspection.dto.FlightCheckDto;
import com.zhuhong.inspection.mapper.FlightCheckMapper;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.FlightCheck;
import com.zhuhong.inspection.service.AnnexService;
import com.zhuhong.inspection.service.FlightCheckService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.FlightCheckVo;
import com.zhuhong.inspection.vo.SelectionLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 飞检数据业务逻辑实现层
 *
 * @Author: jian.ye
 * @Date: 2019/11/9 19:49
 */
@Service
public class FlightCheckServiceImpl implements FlightCheckService {

    @Autowired
    private FlightCheckMapper flightCheckMapper;
    @Autowired
    private AnnexService annexService;

    @Override
    public boolean saveFlightCheck(FlightCheckDto flightCheckDto, Integer currentUserId) {
        boolean flag = false;
        Date currentDate = DateUtil.getCurrentDate();
        // List<Annex> annexList = flightCheckDto.getAnnexList();
        FlightCheck flightCheck = JSONObject.parseObject(JSONObject.toJSONString(flightCheckDto), FlightCheck.class);
        flightCheck.setUpdateId(currentUserId);
        flightCheck.setUpdateTime(currentDate);
        if (flightCheck.getId() == null) {
            flightCheck.setCreateId(currentUserId);
            flightCheck.setCreateTime(currentDate);
            int r = flightCheckMapper.insertSelective(flightCheck);
            if (r > 0) {
                flag = true;
                // annexService.saveAnnex(false, annexList, flightCheck.getId(), Constants.BASE_TYPE_4);
            }
        } else {
            int r = flightCheckMapper.updateByPrimaryKey(flightCheck);
            if (r > 0) {
                flag = true;
                // annexService.saveAnnex(true, annexList, flightCheck.getId(), Constants.BASE_TYPE_4);
            }
        }
        return flag;
    }

    @Override
    public boolean insertFlightCheck(FlightCheck flightCheck, Integer currentUserId) {
        flightCheck.setCreateId(currentUserId);
        flightCheck.setCreateTime(DateUtil.getCurrentDate());
        flightCheck.setUpdateId(currentUserId);
        flightCheck.setUpdateTime(DateUtil.getCurrentDate());
        return flightCheckMapper.insertSelective(flightCheck) > 0;
    }

    @Override
    public FlightCheckVo getFlightCheckById(Integer id) {
        FlightCheckCondition condition = new FlightCheckCondition();
        condition.setId(id);
        FlightCheckVo flightCheckVo = flightCheckMapper.getFlightCheckById(condition);
        flightCheckVo.setProblem(flightCheckVo.getProblem().replaceAll("\\r\\n", "<br/>"));
        return flightCheckVo;
    }

    @Override
    public PageInfo<FlightCheckVo> getFlightCheckPageList(FlightCheckCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<FlightCheckVo> list = flightCheckMapper.getFlightCheckPageList(condition);
        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteFlightCheck(Integer id, Integer currentUserId) {
        FlightCheck flightCheck = new FlightCheck();
        flightCheck.setId(id);
        flightCheck.setUpdateId(currentUserId);
        flightCheck.setUpdateTime(DateUtil.getCurrentDate());
        flightCheck.setUsable(FlightCheck.ENABLE_0);
        return flightCheckMapper.updateByPrimaryKeySelective(flightCheck) > 0;
    }

    @Override
    public List<SelectionLabel> getAllPublishUnit() {
        return flightCheckMapper.getPublishUnitList();
    }

    @Override
    public List<SelectionLabel> getPrecautionsList() {
        return flightCheckMapper.getPrecautionsList();
    }

    @Override
    public List<SelectionLabel> getTypeList() {
        return flightCheckMapper.getTypeList();
    }

    @Override
    public int getFlightCheckTotalCount() {
        FlightCheck flightCheck = new FlightCheck();
        flightCheck.setUsable(FlightCheck.ENABLE_1);
        return flightCheckMapper.selectCount(flightCheck);
    }
}
