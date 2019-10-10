package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 角色数据访问层
 * @Author: jian.ye
 * @Date: 2019/10/10 9:47
 */
@Repository
public interface RoleMapper extends Mapper<Role> {

    /**
     * 根据用户ID查询用户拥有的角色
     * @param userId
     * @return List<Role>
     * @Author: jian.ye
     * @Date: 2019/10/10 9:52
     */
    List<Role> getRoleListByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户ID获取用户角色关联关系数据
     * @param userId
     * @return List<UserRole>
     * @Author: jian.ye
     * @Date: 2019/10/10 11:47
     */
    List<UserRole> getUserRoleByUserId(@Param("userId") Integer userId);

}
