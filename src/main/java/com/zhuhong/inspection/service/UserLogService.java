package com.zhuhong.inspection.service;

import com.zhuhong.inspection.model.UserLog;

/**
 * @author 叶剑
 */
public interface UserLogService {

    /**
     * 保存用户操作日志
     * @param userlog
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/22 10:04
     */
    boolean saveUserLog(UserLog userlog);

}
