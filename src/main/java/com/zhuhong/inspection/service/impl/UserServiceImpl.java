package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.UserCondition;
import com.zhuhong.inspection.mapper.RoleMapper;
import com.zhuhong.inspection.mapper.UserMapper;
import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.model.UserRole;
import com.zhuhong.inspection.service.UserService;
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
        //UserVo userVo = (UserVo) CloneUtil.clone(user);
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
    public PageInfo getUserPageListByCondition(UserCondition userCondition) {
        PageHelper.startPage(userCondition.getPageNum(), userCondition.getPageSize(), "create_time desc");
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("usable", 1);
        if (userCondition.getIsMember() != null) {
            criteria.andEqualTo("isMember", userCondition.getIsMember());
        }
        if (userCondition.getGender() != null) {
            criteria.andEqualTo("gender", userCondition.getGender());
        }
        if (userCondition.getStatus() != null) {
            criteria.andEqualTo("userStatus", userCondition.getStatus());
        }
        if (StringUtils.isNotEmpty(userCondition.getSearchPhrase())) {
            criteria.andLike("userName", userCondition.getSearchPhrase());
        }
        List<User> list = userMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

}
