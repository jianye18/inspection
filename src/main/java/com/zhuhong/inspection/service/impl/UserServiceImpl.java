package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.UserCondition;
import com.zhuhong.inspection.mapper.RoleMapper;
import com.zhuhong.inspection.mapper.UserMapper;
import com.zhuhong.inspection.mapper.UserRoleMapper;
import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.model.UserRole;
import com.zhuhong.inspection.service.UserService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.MD5;
import com.zhuhong.inspection.utils.StringUtil;
import com.zhuhong.inspection.vo.UserVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * 用户信息业务实现类
 * @Author: jian.ye
 * @Date: 2019/10/9 21:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User getUserByNickName(String nickName) {
        User user = new User();
        user.setNickName(nickName);
        return userMapper.selectOne(user);
    }

    @Override
    public UserVo getUserWithRolePermissionByNickName(String nickName) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        user.setNickName(nickName);
        user = userMapper.selectOne(user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userVo, user);
        Integer userId = user.getId();
        List<UserRole>  userRoles = roleMapper.getUserRoleByUserId(userId);
        if (userRoles != null && userRoles.size() > 0) {
            List<Role> roleList = roleMapper.getRoleListByUserId(userId);
            userVo.setRoleList(roleList);
        }
        return userVo;
    }

    @Override
    public PageInfo<UserVo> getUserPageListByCondition(UserCondition userCondition) {
        PageHelper.startPage(userCondition.getPageNum(), userCondition.getPageSize());
        List<UserVo> list = userMapper.getUserListByCondition(userCondition);
        return new PageInfo<UserVo>(list);
    }

    @Override
    public boolean saveUser(User user, Integer currentUserId) {
        boolean flag = false;
        Integer userId = user.getId();
        user.setUpdateId(currentUserId);
        user.setUpdateTime(DateUtil.getCurrentDate());
        if (userId == null) {
            user.setPassword(MD5.getMD5(Constants.DEFAULT_PASSWORD));
            user.setCreateId(currentUserId);
            user.setCreateTime(DateUtil.getCurrentDate());
            user.setUserStatus(Constants.SYS_USER_STATUS_1);
            if (userMapper.insertSelective(user) > 0) {
                flag = true;
                handleUserRole(userId, user.getRoleIds());
            }
        } else {
            if (userMapper.updateByPrimaryKey(user) > 0) {
                flag = true;
                handleUserRole(userId, user.getRoleIds());
            }
        }
        return flag;
    }

    private void handleUserRole(Integer userId, String roleIds) {
        if (userId != null) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRoleMapper.delete(userRole);
        }
        if (StringUtils.isNotEmpty(roleIds)) {
            String[] arr = roleIds.split(",");
            for (String s : arr) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(s));
                userRoleMapper.insert(userRole);
            }
        }

    }

    @Override
    public boolean deleteUser(Integer userId, Integer currentUserId) {
        boolean flag = false;
        User user = new User();
        user.setUsable(User.ENABLE_0);
        user.setUpdateId(currentUserId);
        user.setUpdateTime(DateUtil.getCurrentDate());
        flag = userMapper.updateByPrimaryKey(user) > 0;
        if (flag) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRoleMapper.delete(userRole);
        }
        return flag;
    }

    @Override
    public boolean judgeNickNameIsExist(String nickName, Integer userId) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("nickName", nickName);
        if (userId != 0) {
            criteria.andNotEqualTo("id", userId);
        }
        if (userMapper.selectByExample(example).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserRole> getUserRoleListByUserId(Integer userId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        return userRoleMapper.select(userRole);
    }

}
