package org.agle4j.plugin.security.realm;

import org.agle4j.framework.helper.DatabaseHelper;
import org.agle4j.plugin.security.SecurityConfig;
import org.agle4j.plugin.security.password.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * 基于 Agile 的  JDBC Realm(需要提供相关 agile.plugin.security.jdbc.* 配置项)
 * 
 * @author hanyx
 * @since 1.0.0
 */
public class AgileJdbcReaml extends JdbcRealm{

	public AgileJdbcReaml(){
		super.setDataSource(DatabaseHelper.getDataSource());
		super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
		super.setUserRolesQuery(SecurityConfig.getJdbcRolseQuery());
		super.setPermissionsQuery(SecurityConfig.getJdbcPermissionQuery());
		super.setPermissionsLookupEnabled(true);
		super.setCredentialsMatcher(new Md5CredentialsMatcher()); // 使用 MD5 加密算法
	}
}
