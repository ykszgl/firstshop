package zgl.shop.utils;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import zgl.shop.vo.AdminUser;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		AdminUser existadminUser=(AdminUser)ServletActionContext.getRequest().getSession().getAttribute("existadminUser");
		if(existadminUser==null){
		ActionSupport actionSupport=(ActionSupport)arg0.getAction();
		actionSupport.addActionError("您还没有登录，没有访问权限");
			return "login";
		}else{
			return arg0.invoke();
		}
	}

}
