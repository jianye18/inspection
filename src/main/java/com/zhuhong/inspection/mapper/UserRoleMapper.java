package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.UserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户角色数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/11 20:32
 */
@Repository
public interface UserRoleMapper extends Mapper<UserRole> {
}
