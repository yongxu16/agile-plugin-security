package org.agle4j.plugin.security.password;

import org.agle4j.framework.utils.CodecUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * MD5 密码适配器
 * @author hanyx
 * @since 1.0.0
 */
public class Md5CredentialsMatcher implements CredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		// 获取从表单提交过来的密码, 明文 , 尚未通过 MD5 加密
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token ;
		String submitted = String.valueOf(usernamePasswordToken.getPassword());
		// 获取数据库中存储的密码,已通过 MD5加密
		String encrypted = String.valueOf(info.getCredentials()) ;
		return CodecUtil.md5(submitted).equals(encrypted);
	}

}
