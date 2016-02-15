package org.agle4j.plugin.security;

import java.util.Set;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

/**
 * 安全过滤器
 * 
 * @author hanyx
 * @since 1.0.0
 */
public class AgileSecurityFilter extends ShiroFilter {

	@Override
	public void init() throws Exception {
		super.init();
		WebSecurityManager webSecurityManager = super.getSecurityManager() ;
		// 设置 Realm, 可同时支持多个 Realm, 并按照先后顺序用逗号分隔
		setRealms(webSecurityManager);
		// 设置 Cache， 用于减少数据库查询次数， 降低I/O访问
		setCache(webSecurityManager);
	}
	
	private void setRealms(WebSecurityManager webSecurityManager) {
		// 读取 smart.plugin.security.realms 配置项
//		String securityRealms
	}
	
	private void setCache(WebSecurityManager webSecurityManager) {
		
	}
	
	private void addJdbcRealm(Set<Realm> realms) {
		
	}
	
	private void addCustomRealm(Set<Realm> realms) {
		
	}
}
