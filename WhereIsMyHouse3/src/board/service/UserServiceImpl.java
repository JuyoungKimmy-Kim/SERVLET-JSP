package board.service;

import board.dao.UserDao;
import board.dao.UserDaoImpl;
import board.dto.UserDto;

//Singleton Design Pattern
public class UserServiceImpl implements UserService{

	private static UserServiceImpl instance=new UserServiceImpl();
	private UserServiceImpl() {}
	UserDao UserDao=UserDaoImpl.getInstance();	// local로 만듦
	
	public static UserServiceImpl getInstance () {
		return instance;
	}
	
	@Override
	public UserDto login(String userId, String userPassword) {
		UserDto userDto=UserDao.login(userId);		// Email만 전달해서 userDto받음
		System.out.println("Service login !!" + userDto);
		if (userDto!=null && userDto.getPassword().equals(userPassword)) {
			return userDto;
		} else {
			return null;
		}
	}
	@Override
	public int userRegister(UserDto userDto) {
		return UserDao.userRegister(userDto);
	}

	@Override
	public int modifyPw(String id, String pw) {
		
		return UserDao.modifyPw(id, pw);
	}

	@Override
	public int modify(UserDto userDto) {
		
		return UserDao.modify(userDto);
	}

	@Override
	public int delete(String userId) {
		return UserDao.delete(userId);
	}
	
}
