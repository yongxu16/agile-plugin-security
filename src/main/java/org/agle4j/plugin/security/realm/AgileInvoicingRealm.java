package org.agle4j.plugin.security.realm;

import org.agle4j.plugin.security.AgileSecurity;
import org.agle4j.plugin.security.SecurityConstant;
import org.agle4j.plugin.security.password.Md5CredentialsMatcher;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * 基于 Agile 的自定义 Realm (需要实现 AgileSecurity 接口)
 * 
 * @author hanyx
 * @since 1.0.0
 */
public class AgileInvoicingRealm extends AuthorizingRealm {

	private final AgileSecurity agileSecurity ;
	
	public AgileInvoicingRealm(AgileSecurity agileSecurity) {
		this.agileSecurity = agileSecurity ;
		super.setName(SecurityConstant.REALMS_INVOICING);
		super.setCredentialsMatcher(new Md5CredentialsMatcher()); // 使用MD5加密
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if(token == null) {
			throw new AuthenticationException("parameter token is null") ;
		}
		
		// 通过 AuthenticationToken 对象获取从表单中提交过来的用户名
		String username = ((UsernamePasswordToken) token).getUsername() ;
		
		// 通过 AgileSecurity 接口并根据用户名称获取数据库中存放的密码
		String password = agileSecurity.getPassword(username) ;
		
		// 将用户名与密码放入 AuthenticationInfo 对象中， 便于后句认真操作
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo() ;
		authenticationInfo.setPrincipals(new SimplePrincipalCollection(username, super.getName()));
		
		return authenticationInfo;
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalcollection) {
		// TODO Auto-generated method stub
		return null;
	}

}
