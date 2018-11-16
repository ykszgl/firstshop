package zgl.shop.service;

import org.springframework.transaction.annotation.Transactional;

import zgl.shop.dao.AdminUserDao;
import zgl.shop.vo.AdminUser;
@Transactional
public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		return adminUserDao.login(adminUser);
	}
	
}
