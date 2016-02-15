package org.agle4j.plugin.security.realm;

import org.agle4j.framework.helper.DatabaseHelper;
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
		super.setAuthenticationQuery(authenticationQuery);
		super.setUserRolesQuery(userRolesQuery);
		
	}
}
