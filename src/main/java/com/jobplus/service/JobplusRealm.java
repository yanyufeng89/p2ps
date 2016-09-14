package com.jobplus.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;



@Service("jobplusRealm")
public class JobplusRealm extends AuthorizingRealm {

	@Resource
	private IUserService userService;

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录时输入的用户名
		String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
		// 到数据库查是否有此对象

		List<?> list = userService.findUserRoleByName(loginName);
		if (list != null) {
			// // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// // 用户的角色集合
			// // 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
			Set<String> roles =new HashSet();
			Set<String> permissions =new HashSet();
			for (Object role : list) {
				roles.add((String) ((Map<?, ?>) role).get("roleName"));
				permissions.add((String) ((Map<?, ?>) role).get("resourceName"));
			}
			info.setRoles(roles);
			info.setStringPermissions(permissions);
			return info;
		}
		return null;
	}

	/**
	 * 登录认证;
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// UsernamePasswordToken对象用来存放提交的登录信息
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		
		return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
	}

}