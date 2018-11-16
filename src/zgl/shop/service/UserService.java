package zgl.shop.service;

import org.springframework.transaction.annotation.Transactional;

import zgl.shop.dao.UserDao;
import zgl.shop.vo.User;


@Transactional
public class UserService {
	// ע��UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	// ���û�����ѯ�û��ķ���:
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	// ҵ�������û�ע�����:
	public void save(User user) {
		
		userDao.save(user);

		}


	// �޸��û���״̬�ķ���
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// �û���¼�ķ���
	public User login(User user) {
		return userDao.login(user);
	}
}