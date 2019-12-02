package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.UserLogCondition;
import com.zhuhong.inspection.model.UserLog;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 叶剑
 */
@Repository
public interface UserLogMapper extends Mapper<UserLog> {

    /**
     * 根据条件查询用户操作日志信息
     * @param condition
     * @return List<UserLog>
     * @Author: jian.ye
     * @Date: 2019/12/2 19:33
     */
    List<UserLog> getUserLogList(UserLogCondition condition);

}
