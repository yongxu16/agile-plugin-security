package org.agle4j.plugin.security;

import org.agle4j.framework.helper.ConfigHelper;
import org.agle4j.framework.utils.ReflectionUtil;

/**
 * 从配置文件中获取相关属性
 * 
 * @author hanyx
 * @since 1.0.0
 */
public final class SecurityConfig {


	public static String getRealms() {
		return ConfigHelper.getString(SecurityConstant.REALMS);
	}

	public static AgileSecurity getAgileSecurity() {
		String className = ConfigHelper.getString(SecurityConstant.AGILE_SECURITY);
		return (AgileSecurity) ReflectionUtil.newInstance(className);
	}

	public static String getJdbcAuthcQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY) ;
	}
	
	public static String getJdbcRolseQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY) ;
	}
	
	public static String getJdbcPermissionQuery() {
		return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY) ;
	}
	
	public static boolean isCacheable() {
		return ConfigHelper.getBoolean(SecurityConstant.CACHE) ;
	}
}
