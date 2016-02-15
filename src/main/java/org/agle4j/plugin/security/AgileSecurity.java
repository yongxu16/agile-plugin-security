package org.agle4j.plugin.security;

import java.util.Set;

/**
 * Agile Security 接口
 * <br/>
 * 可在应用中实现该接口， 或者在agile.properties 文件中提供一下基于SQL的配置项：
 * <ul>
 * 	<li>agile.plugin.security.jdbc.authc_query:根据用户名获取密码</li>
 * 	<li>agile.plugin.security.jdbc.roles_query:根据用户名获取角色名集合</li>
 * 	<li>agile.plugin.security.jdbc.permissions_query:根据角色名获取权限名集合</li>
 * </ul>
 * @author hanyx
 * @since 1.0
 */
public interface AgileSecurity {
	
	/**
	 * 根据用户名获取密码
	 * @param username
	 * @return 用户密码
	 */
	String getPassword(String username) ;
	
	/**
	 * 根据用户名获取角色名集合
	 * @param roleName
	 * @return 角色集合
	 */
	Set<String> getRoleNameSet(String username) ;
	
	/**
	 * 根据角色名获取权限名集合
	 * @param roleName
	 * @return 权限集合
	 */
	Set<String> getPermissionNameSet(String roleName) ;
}
