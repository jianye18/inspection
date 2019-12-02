package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.UserLogCondition;
import com.zhuhong.inspection.mapper.UserLogMapper;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 叶剑
 */
@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public boolean saveUserLog(UserLog userlog) {
        return userLogMapper.insertSelective(userlog) > 0;
    }

    @Override
    public PageInfo<UserLog> getUserLogPageList(UserLogCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<UserLog> list = userLogMapper.getUserLogList(condition);
        return new PageInfo<>(list);
    }
}
