package com.zhuhong.inspection.service.impl;

import com.zhuhong.inspection.mapper.UserLogMapper;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
