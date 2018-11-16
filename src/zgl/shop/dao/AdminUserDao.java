package zgl.shop.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import zgl.shop.vo.AdminUser;

public class AdminUserDao extends HibernateDaoSupport{

	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		String hql="from AdminUser where username = ? and password= ?";
		List<AdminUser> list=this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
