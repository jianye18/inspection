package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.RoleCondition;
import com.zhuhong.inspection.mapper.RoleMapper;
import com.zhuhong.inspection.mapper.RolePermissionMapper;
import com.zhuhong.inspection.mapper.UserRoleMapper;
import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.RolePermission;
import com.zhuhong.inspection.model.UserRole;
import com.zhuhong.inspection.service.RoleService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 角色信息业务实现类
 * @Author: jian.ye
 * @Date: 2019/10/11 20:50
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public PageInfo<Role> getRolePageList(RoleCondition roleCondition) {
        PageHelper.startPage(roleCondition.getPageNum(), roleCondition.getPageSize(), "create_time desc");
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("usable", 1);
        if (StringUtils.isNotEmpty(roleCondition.getSearchPhrase())) {
            criteria.andLike("roleName", StringUtil.getLikeString(roleCondition.getSearchPhrase()));
        }
        List<Role> list = roleMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public List<Role> getAllRoleList() {
        Role role = new Role();
        role.setUsable(Role.ENABLE_1);
        role.setRoleStatus(Constants.ROLE_STATUS_1);
        return roleMapper.select(role);
    }

    @Override
    public boolean saveRole(Role role, Integer currentUserId) {
        boolean flag = false;
        Integer roleId = role.getId();
        role.setUpdateId(currentUserId);
        role.setUpdateTime(DateUtil.getCurrentDate());
        if (roleId == null) {
            role.setCreateId(currentUserId);
            role.setCreateTime(DateUtil.getCurrentDate());
            flag = roleMapper.insertSelective(role) > 0;
        } else {
            flag = roleMapper.updateByPrimaryKey(role) > 0;
        }
        return flag;
    }

    @Override
    public int deleteRole(Integer roleId, Integer currentUserId) {
        int flag = Constants.DELETE_USER_2;
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        if (userRoleMapper.select(userRole).size() > 0) {
            flag = Constants.DELETE_USER_3;
        } else {
            Role role = new Role();
            role.setId(roleId);
            role.setUsable(Role.ENABLE_0);
            role.setUpdateId(currentUserId);
            role.setUpdateTime(DateUtil.getCurrentDate());
            if (roleMapper.updateByPrimaryKeySelective(role) > 0) {
                flag = Constants.DELETE_USER_1;
            }
        }
        return flag;
    }

    @Override
    public boolean saveRolePermission(Integer roleId, String ids) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermissionMapper.delete(rolePermission);
        String[] arr = ids.split(",");
        int count = 0;
        for (String s : arr) {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(Integer.parseInt(s));
            rolePermissionMapper.insert(rp);
            count++;
        }
        if (count == arr.length) {
            return true;
        } else {
            return false;
        }
    }

}
