package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.UserCondition;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.model.UserRole;
import com.zhuhong.inspection.vo.UserVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

    /**
     * 保存用户信息
     * @param user
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/11 20:11
     */
    boolean saveUser(User user, Integer currentUserId);

    /**
     * 删除用户信息
     * @param userId
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/11 20:27
     */
    boolean deleteUser(Integer userId, Integer currentUserId);

    /**
     * 判断昵称是否存在
     *
     * @param nickName
     * @param userId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/14 22:38
     */
    boolean judgeNickNameIsExist(String nickName, Integer userId);

    /**
     * 获取所有用户角色信息
     *
     * @param userId
     * @return List<UserRole>
     * @Author: jian.ye
     * @Date: 2019/10/14 23:00
     */
    List<UserRole> getUserRoleListByUserId(Integer userId);

}
