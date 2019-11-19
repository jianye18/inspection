package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.PermissionCondition;
import com.zhuhong.inspection.mapper.PermissionMapper;
import com.zhuhong.inspection.mapper.RolePermissionMapper;
import com.zhuhong.inspection.model.Permission;
import com.zhuhong.inspection.model.RolePermission;
import com.zhuhong.inspection.service.PermissionService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.StringUtil;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.TreeData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 权限信息业务实现类
 *
 * @Author: jian.ye
 * @Date: 2019/10/13 10:52
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public PageInfo<Permission> getPermissionPageList(PermissionCondition permissionCondition) {
        PageHelper.startPage(permissionCondition.getPageNum(), permissionCondition.getPageSize(), "create_time desc");
        Example example = new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("usable", 1);
        if (StringUtils.isNotEmpty(permissionCondition.getSearchPhrase())) {
            criteria.andLike("name", StringUtil.getLikeString(permissionCondition.getSearchPhrase()));
        }
        List<Permission> list = permissionMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public boolean savePermission(Permission permission, Integer currentUserId) {
        Date currentDate = DateUtil.getCurrentDate();
        Integer pId = permission.getId();
        permission.setUpdateId(currentUserId);
        permission.setUpdateTime(currentDate);
        if (pId == null) {
            permission.setCreateId(currentUserId);
            permission.setCreateTime(currentDate);
            if (permission.getType() == 1) {
                permission.setParentId(0);
            }
            return permissionMapper.insertSelective(permission) > 0;
        } else {
            return permissionMapper.updateByPrimaryKey(permission) > 0;
        }
    }

    @Override
    public int deletePermission(Integer pId, Integer currentUserId) {
        int flag = Constants.DELETE_USER_2;
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(pId);
        if (rolePermissionMapper.select(rolePermission).size() > 0) {
            flag = Constants.DELETE_USER_3;
        } else {
            Permission permission = new Permission();
            permission.setUsable(Permission.ENABLE_0);
            permission.setUpdateId(currentUserId);
            permission.setUpdateTime(DateUtil.getCurrentDate());
            if (permissionMapper.updateByPrimaryKeySelective(permission) > 0) {
                flag = Constants.DELETE_USER_1;
            }
        }
        return flag;
    }

    @Override
    public List<SelectionLabel> getPermissionWithPageType() {
        Permission permission = new Permission();
        permission.setType(1);
        permission.setStatus(1);
        permission.setUsable(Permission.ENABLE_1);
        List<Permission> permissionList = permissionMapper.select(permission);
        List<SelectionLabel> list = new ArrayList<>();
        if (permissionList.size() > 0) {
            for (Permission p : permissionList) {
                SelectionLabel selectionLabel = new SelectionLabel();
                selectionLabel.setValue(String.valueOf(p.getId()));
                selectionLabel.setLabel(p.getName());
                list.add(selectionLabel);
            }
        }
        return list;
    }

    @Override
    public List<TreeData> getPermissionTreeData(Integer roleId) {
        Permission permission = new Permission();
        permission.setStatus(1);
        permission.setUsable(Permission.ENABLE_1);
        List<Permission> permissionList = permissionMapper.select(permission);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        List<TreeData> list = handleToTreeData(permissionList, 0, rolePermissionMapper.select(rolePermission));
        return list;
    }

    private List<TreeData> handleToTreeData(List<Permission> pList, Integer pId, List<RolePermission> rolePermissionList) {
        List<TreeData> list = new ArrayList<>();
        for (Permission permission : pList) {
            TreeData treeData = new TreeData();
            treeData.setId(permission.getId());
            treeData.setTitle(permission.getName());
            treeData.setExpand(true);
            if (rolePermissionList.size() > 0) {
                for (RolePermission rolePermission : rolePermissionList) {
                    if (rolePermission.getPermissionId().equals(permission.getId())) {
                        treeData.setChecked(true);
                        break;
                    }
                }
            }
            if (pId.equals(permission.getParentId())) {
                List<TreeData> treeDataList = handleToTreeData(pList, permission.getId(), rolePermissionList);
                treeData.setChildren(treeDataList);
                list.add(treeData);
            }

        }
        return list;
    }

}
