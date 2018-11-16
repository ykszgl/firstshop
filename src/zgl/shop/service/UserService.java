package zgl.shop.service;

import org.springframework.transaction.annotation.Transactional;

import zgl.shop.dao.UserDao;
import zgl.shop.vo.User;


@Transactional
public class UserService {
	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	// 按用户名查询用户的方法:
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	// 业务层完成用户注册代码:
	public void save(User user) {
		
		userDao.save(user);

		}


	// 修改用户的状态的方法
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// 用户登录的方法
	public User login(User user) {
		return userDao.login(user);
	}
}