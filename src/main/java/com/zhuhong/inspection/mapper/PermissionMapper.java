package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.Permission;
import com.zhuhong.inspection.model.RolePermission;
import com.zhuhong.inspection.model.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 权限数据访问层
 * @Author: jian.ye
 * @Date: 2019/10/10 9:50
 */
@Repository
public interface PermissionMapper extends Mapper<Permission> {

    /**
     * 根据角色ID查询权限
     * @param roleId
     * @return List<Permission>
     * @Author: jian.ye
     * @Date: 2019/10/10 10:18
     */
    List<Permission> getPermissionListByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID获取角色权限关联关系数据
     * @param roleId
     * @return List<UserRole>
     * @Author: jian.ye
     * @Date: 2019/10/10 11:47
            */
    List<RolePermission> getRolePermissionByRoleId(@Param("roleId") Integer roleId);

}
