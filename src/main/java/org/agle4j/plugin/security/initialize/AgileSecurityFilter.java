package org.agle4j.plugin.security.initialize;

import java.util.LinkedHashSet;
import java.util.Set;

import org.agle4j.plugin.security.AgileSecurity;
import org.agle4j.plugin.security.SecurityConfig;
import org.agle4j.plugin.security.constant.SecurityConstant;
import org.agle4j.plugin.security.realm.AgileInvoicingRealm;
import org.agle4j.plugin.security.realm.AgileJdbcReaml;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

/**
 * Agile 安全过滤器
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
		String securityRealms = SecurityConfig.getRealms() ;
		if(StringUtils.isNotEmpty(securityRealms)) {
			// 根据逗号进行拆分
			String[] securityRealmArray = securityRealms.split(",") ;
			if(securityRealmArray.length > 0) {
				// 使 Realm 具备唯一性与顺序性
				Set<Realm> realmSet = new LinkedHashSet<>() ;
				for (String securityRealm : securityRealmArray) {
					if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)) {
						// 添加居于 JDBC 的 Realm, 需配置相关 SQL 查询语句
						addJdbcRealm(realmSet);
					} else if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_INVOICING)) {
						// 添加居于定制化的 Realm, 需实现 AgileSecurity 接口
						addInvoicingRealm(realmSet);
					}
				}
				
				RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager ;
				realmSecurityManager.setRealms(realmSet); // 设置 Realm
			}
			
		}
	}
	
	private void addJdbcRealm(Set<Realm> realmSet) {
		// 添加自己实现基于 JDBC的 Realm
		AgileJdbcReaml agileJdbcReaml = new AgileJdbcReaml() ;
		realmSet.add(agileJdbcReaml) ;
	}
	
	private void addInvoicingRealm(Set<Realm> realmSet) {
		// 读取 agile.plugin.security.invoicing.class 配置项
		AgileSecurity agileSecurity = SecurityConfig.getAgileSecurity() ;
		// 添加自己实现的 Realm
		AgileInvoicingRealm agileInvoicingRealm = new AgileInvoicingRealm(agileSecurity) ;
		realmSet.add(agileInvoicingRealm) ;
	}
	
	private void setCache(WebSecurityManager webSecurityManager) {
		// 读取 agile.plugin.security.cache配置项
		if (SecurityConfig.isCacheable()) {
			CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager ;
			// 使用基于内存的 CacheManager
			CacheManager cacheManager = new MemoryConstrainedCacheManager() ;
			cachingSecurityManager.setCacheManager(cacheManager);
		}
	}
}
