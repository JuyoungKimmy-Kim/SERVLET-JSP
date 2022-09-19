package board.service;

import board.dto.UserDto;

public class UserServiceImpl implements UserService{

	private static UserServiceImpl instance=new UserServiceImpl();
	private UserServiceImpl() {}
	
	public static UserServiceImpl getInstance () {
		return instance;
	}

	@Override
	public int userRegister(UserDto userDto) {
		
		/*
		 * 
		 * 
		 * 
		 */
		return 0;
	}
	
	
}
