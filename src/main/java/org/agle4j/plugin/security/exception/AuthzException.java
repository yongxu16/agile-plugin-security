package org.agle4j.plugin.security.exception;

/**
 * 授权异常(当权限无效时抛出)
 * 
 * @author hanyx
 * @since 0.0.9
 */
public class AuthzException extends Exception{

	public AuthzException() {
		super() ;
	}
	
	public AuthzException(String message) {
		super(message) ;
	}
	
	public AuthzException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public AuthzException(Throwable cause) {
		super(cause) ;
	}
}
