package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.PermissionCondition;
import com.zhuhong.inspection.model.Permission;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.TreeData;

import java.util.List;

/**
 * 权限信息业务接口类
 *
 * @Author: jian.ye
 * @Date: 2019/10/13 10:51
 */
public interface PermissionService {

    /**
     * 根据条件分页查询权限信息
     *
     * @param permissionCondition
     * @return PageInfo<Permission>
     * @Author: jian.ye
     * @Date: 2019/10/13 10:54
     */
    PageInfo<Permission> getPermissionPageList(PermissionCondition permissionCondition);

    /**
     * 保存权限信息
     *
     * @param permission
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/13 13:31
     */
    boolean savePermission(Permission permission, Integer currentUserId);

    /**
     * 删除权限信息
     *
     * @param pId
     * @param currentUserId
     * @return int
     * @Author: jian.ye
     * @Date: 2019/10/13 13:36
     */
    int deletePermission(Integer pId, Integer currentUserId);

    /**
     * 获取页面权限集合
     *
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/10/13 18:56
     */
    List<SelectionLabel> getPermissionWithPageType();

    /**
     * 获取权限树数据集合
     *
     * @return List<TreeData>
     * @Author: jian.ye
     * @Date: 2019/10/13 21:59
     */
    List<TreeData> getPermissionTreeData();

}
