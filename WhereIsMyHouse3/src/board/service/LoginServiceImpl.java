package board.service;

import board.dao.LoginDao;
import board.dao.LoginDaoImpl;
import board.dto.UserDto;

//Singleton Design Pattern
public class LoginServiceImpl implements LoginService{

	private static LoginServiceImpl instance=new LoginServiceImpl();
	private LoginServiceImpl() {}
	
	public static LoginServiceImpl getInstance () {
		return instance;
	}
	
	@Override
	public UserDto login(String userId, String userPassword) {
		LoginDao loginDao=LoginDaoImpl.getInstance();	// local로 만듦
		UserDto userDto=loginDao.login(userId);		// Email만 전달해서 userDto받음
		
		if (userDto!=null && userDto.getPassword().equals(userPassword)) {
			return userDto;
		} else {
			return null;
		}
	}

}
