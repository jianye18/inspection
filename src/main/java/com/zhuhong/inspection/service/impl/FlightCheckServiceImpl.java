package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.FlightCheckCondition;
import com.zhuhong.inspection.mapper.FlightCheckMapper;
import com.zhuhong.inspection.model.FlightCheck;
import com.zhuhong.inspection.service.FlightCheckService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.FlightCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean saveFlightCheck(FlightCheck flightCheck, Integer currentUserId) {
        flightCheck.setUpdateId(currentUserId);
        flightCheck.setUpdateTime(DateUtil.getCurrentDate());
        if (flightCheck.getId() == null) {
            flightCheck.setCreateId(currentUserId);
            flightCheck.setCreateTime(DateUtil.getCurrentDate());
            return flightCheckMapper.insertSelective(flightCheck) > 0;
        } else {
            return flightCheckMapper.updateByPrimaryKey(flightCheck) > 0;
        }
    }

    @Override
    public FlightCheckVo getFlightCheckById(Integer id) {
        FlightCheckCondition condition = new FlightCheckCondition();
        condition.setId(id);
        return flightCheckMapper.getFlightCheckById(condition);
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
}
