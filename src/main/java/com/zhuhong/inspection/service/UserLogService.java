package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.UserLogCondition;
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

    /**
     * 根据条件分页查询用户操作日志信息
     * @param condition
     * @return PageInfo<UserLog>
     * @Author: jian.ye
     * @Date: 2019/12/2 19:32
     */
    PageInfo<UserLog> getUserLogPageList(UserLogCondition condition);

}
