package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户数据访问层
 * @Author: jian.ye
 * @Date: 2019/10/9 20:59
 */
@Repository
public interface UserMapper extends Mapper<User> {



}
