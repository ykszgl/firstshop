package zgl.shop.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zgl.shop.service.AdminUserService;
import zgl.shop.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	private AdminUser adminUser=new AdminUser();
	
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	private AdminUserService adminUserService;

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	public String login(){
		AdminUser existadminUser=adminUserService.login(adminUser);
		if(existadminUser==null){
			this.addActionError("µ«¬º ß∞‹£¨«Î∫À∂‘’À∫≈√‹¬Î");
			return "loginFail";
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existadminUser", existadminUser);
		return "loginSuccess";
		}
	}
}
