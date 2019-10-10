package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.UserCondition;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.vo.UserVo;

import java.lang.reflect.InvocationTargetException;

/**
 * 用户信息业务接口类
 * @Author: jian.ye
 * @Date: 2019/10/9 21:01
 */
public interface UserService {

    /**
     * 根据用户昵称获取用户基本信息
     * @param nickName
     * @return User
     * @Author: jian.ye
     * @Date: 2019/10/10 9:09
     */
    User getUserByNickName(String nickName);

    /**
     * 根据用户昵称获取用户及相关信息
     * @param nickName
     * @return UserVo
     * @Author: jian.ye
     * @Date: 2019/10/10 9:45
     */
    UserVo getUserWithRolePermissionByNickName(String nickName) throws InvocationTargetException, IllegalAccessException;

    /**
     * 根据条件查询分页数据
     * @Author: jian.ye
     * @Date: 2019/10/10 13:56
     */
    PageInfo<User> getUserPageListByCondition(UserCondition userCondition);

}
