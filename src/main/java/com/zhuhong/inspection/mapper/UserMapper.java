package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.UserCondition;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 用户数据访问层
 * @Author: jian.ye
 * @Date: 2019/10/9 20:59
 */
@Repository
public interface UserMapper extends Mapper<User> {

    /**
     * 根据昵称获取用户信息
     *
     * @param nickName
     * @return User
     * @Author: jian.ye
     * @Date: 2019/12/25 21:48
     */
    User selectUserByNickName(@Param(value = "nickName") String nickName);

    /**
     * 根据条件分页获取用户信息
     *
     * @param userCondition
     * @return List<UserVo>
     * @Author: jian.ye
     * @Date: 2019/10/15 19:43
     */
    List<UserVo> getUserListByCondition(UserCondition userCondition);

}
