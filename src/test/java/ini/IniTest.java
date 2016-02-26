package ini;

import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.apache.shiro.web.tags.AuthenticatedTag;
import org.apache.shiro.web.tags.GuestTag;
import org.apache.shiro.web.tags.HasAnyRolesTag;
import org.apache.shiro.web.tags.HasPermissionTag;
import org.apache.shiro.web.tags.HasRoleTag;
import org.apache.shiro.web.tags.LacksPermissionTag;
import org.apache.shiro.web.tags.LacksRoleTag;
import org.apache.shiro.web.tags.PrincipalTag;
import org.apache.shiro.web.tags.UserTag;

public class IniTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EnvironmentLoaderListener a ;
		ShiroFilter s ;
		UserTag u ;
		GuestTag g ; 
		AuthenticatedTag at ;
		PrincipalTag pt ;
		HasRoleTag hrt ;
		LacksRoleTag lrt ;
		HasAnyRolesTag hart;
		HasPermissionTag hpt ;
		LacksPermissionTag lpt ;
	}
}
