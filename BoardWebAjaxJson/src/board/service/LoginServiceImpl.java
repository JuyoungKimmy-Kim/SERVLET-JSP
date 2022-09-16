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
	public UserDto login(String userEmail, String userPassword) {
		LoginDao loginDao=LoginDaoImpl.getInstance();
		UserDto userDto=loginDao.login(userEmail);		// Email만 전달해서 userDto받음
		
		if (userDto!=null && userDto.getUserPassword().equals(userPassword)) {
			return userDto;
		} else {
			return null;
		}
	}

}
