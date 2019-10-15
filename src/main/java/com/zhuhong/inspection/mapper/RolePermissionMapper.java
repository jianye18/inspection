package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.RolePermission;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 角色权限数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/13 13:39
 */
@Repository
public interface RolePermissionMapper extends Mapper<RolePermission> {
}
