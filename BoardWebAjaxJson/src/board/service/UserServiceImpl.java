package board.service;

import board.dao.UserDao;
import board.dao.UserDaoImpl;
import board.dto.UserDto;

public class UserServiceImpl implements UserService{

	private static UserServiceImpl instance=new UserServiceImpl();
	private UserServiceImpl() {}
	
	public static UserServiceImpl getInstance () {
		return instance;
	}

	// thread에 의해서 userDao가 공유 된다면?
	// userDao가 만약 멤버 변수가 있다면 해당 멤버 변수에 대해서 thread-safe한 코딩 필요 - sync
	// userDao가 만약 멤버 변수가 없다면 문제 X
	// Singleton Design Pattern을 가진 객체가 상태값을 가지지 않는 (메소드만 가짐)다면 Thread-Safe
	
	UserDao userDao=UserDaoImpl.getInstance();	
	
	@Override
	public int userRegister(UserDto userDto) {
		return userDao.userRegister(userDto);
	}
	
	
}
