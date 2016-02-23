package org.agle4j.plugin.security.tag;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * 判断当前用户是否拥有其中某一种权限（逗号分隔，表示“或”的关系）
 * 
 * @author hanyx
 * @since
 */
public class HasAnyPermisssionsTag extends PermissionTag {
	
	private static final String PERMISSION_NAMES_DELIMETER = ",";

	@Override
	protected boolean showTagBody(String permissionNames) {
		boolean hasAllPermission = false;
		Subject subject = getSubject();
		if (subject != null) {
			for(String permissionName : permissionNames.split(PERMISSION_NAMES_DELIMETER)) {
				if (subject.isPermitted(permissionName.trim())) {
					hasAllPermission = true;
					break;
				}
			}
		}
		return hasAllPermission;
	}

}
