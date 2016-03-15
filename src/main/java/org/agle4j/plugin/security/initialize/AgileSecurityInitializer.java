package org.agle4j.plugin.security.initialize;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

/**
 * Agile Security 初始化
 * 
 * @author hanyx
 * @since 1.0.0
 */
public class AgileSecurityInitializer implements ServletContainerInitializer {

	public void onStartup(Set<Class<?>> handlersTypes, ServletContext servletContext) throws ServletException {
		// 设置初始化参数
		servletContext.setInitParameter("shiroConfigLocations", "classpath:agile-security.ini");
		// 注册 Listenner
		servletContext.addListener(EnvironmentLoaderListener.class);
		// 注册 Filter
		FilterRegistration.Dynamic agileSecurityFilter = servletContext.addFilter("agileSecurityFilter", AgileSecurityFilter.class);
		agileSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
