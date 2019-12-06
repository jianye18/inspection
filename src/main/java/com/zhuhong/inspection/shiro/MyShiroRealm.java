package com.zhuhong.inspection.shiro;

import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.mapper.PermissionMapper;
import com.zhuhong.inspection.model.Permission;
import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.service.UserService;
import com.zhuhong.inspection.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名：MyShiroRealm
 * todo: 自定义shiro权限认证类
 *
 * @author jianye2
 * @version 1.0
 * @Date 2018年08月08日 14:22
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //SysUserVo token = (SysUserVo)SecurityUtils.getSubject().getPrincipal();
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        List<String> roleList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
        //从数据库中获取当前登录用户的详细信息
        User user = userService.getUserByNickName(loginName);
        /*try {
            userVo = userService.getUserWithRolePermissionByNickName(loginName);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
        if (null != user) {
            //实体类User中包含有用户角色的实体类信息
            /*if(null != userVo.getRoleList() && userVo.getRoleList().size() > 0){
                //获取当前登录用户的角色
                for(Role role : userVo.getRoleList()){
                    roleList.add(role.getRoleName());
                    //实体类Role中包含有角色权限的实体类信息
                    List<Permission> list = permissionMapper.getPermissionListByRoleId(role.getId());
                    if(null != list && list.size()>0){
                        //获取权限
                        for(Permission pes : list){
                            if(!StringUtils.isEmpty(pes.getCode())){
                                permissionList.add(pes.getCode());
                            }
                        }
                    }
                }
            }*/
            //为当前用户设置角色和权限
            authorizationInfo.addRoles(roleList);
            authorizationInfo.addStringPermissions(permissionList);
            return authorizationInfo;
        } else {
            throw new AuthorizationException();
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        User user = userService.getUserByNickName(token.getUsername());
        if (null == user) {
            throw new UnknownAccountException("账号不存在");
        } else if (Constants.SYS_USER_STATUS_2.equals(user.getUserStatus())) {
            throw new LockedAccountException("账号禁用中，请联系管理员！");
        } else if (!user.getPassword().equals(String.valueOf(token.getPassword()))) {
            throw new AccountException("密码不正确！");
        }
        //用户信息缓存到session里面
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(session.getId(), user);
        session.setTimeout(24 * 60 * 60 * 1000);
        System.out.println("测试：" + SecurityUtils.getSubject().getSession().getId());
        return new SimpleAuthenticationInfo(user.getNickName(), user.getPassword(), user.getNickName());
    }

}
