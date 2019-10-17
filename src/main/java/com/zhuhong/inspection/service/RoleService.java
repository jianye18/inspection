package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.RoleCondition;
import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.RolePermission;

import java.util.List;

/**
 * 角色信息业务接口类
 * @Author: jian.ye
 * @Date: 2019/10/11 20:49
 */
public interface RoleService {

    /**
     * 根据条件分页查询角色信息
     *
     * @param roleCondition
     * @return PageInfo<Role>
     * @Author: jian.ye
     * @Date: 2019/10/11 21:24
     */
    PageInfo<Role> getRolePageList(RoleCondition roleCondition);

    /**
     * 获取所有角色信息
     *
     * @return List<Role>
     * @Author: jian.ye
     * @Date: 2019/10/14 22:13
     */
    List<Role> getAllRoleList();

    /**
     * 保存角色信息
     *
     * @param role
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/11 22:13
     */
    boolean saveRole(Role role, Integer currentUserId);

    /**
     * 删除角色信息
     *
     * @param roleId
     * @param currentUserId
     * @return int
     * @Author: jian.ye
     * @Date: 2019/10/11 22:18
     */
    int deleteRole(Integer roleId, Integer currentUserId);

    /**
     * 角色绑定权限信息
     *
     * @param roleId
     * @param ids
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/14 21:28
     */
    boolean saveRolePermission(Integer roleId, String ids);

}
