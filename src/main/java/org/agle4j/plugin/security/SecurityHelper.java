package org.agle4j.plugin.security;

import org.agle4j.plugin.security.exception.AuthcException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Security 助手类
 * 
 * @author hanyx
 * @since 0.0.9
 */
public final class SecurityHelper {
	
	private static final Logger LOGGER = LogManager.getLogger(SecurityHelper.class) ;

	/**
	 * 登录
	 */
	public static void login(String username, String password, boolean isRememberMe) throws AuthcException {
		Subject currentUser = SecurityUtils.getSubject() ;
		if (currentUser != null) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password) ;
			token.setRememberMe(isRememberMe);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				LOGGER.error("login failure", e);
				throw new AuthcException(e) ;
			}
		}
	}
	
	/**
	 * 注销
	 */
	public static void logout() {
		Subject currentUser = SecurityUtils.getSubject() ;
		if (currentUser != null) {
			currentUser.logout();
		}
	}
}
