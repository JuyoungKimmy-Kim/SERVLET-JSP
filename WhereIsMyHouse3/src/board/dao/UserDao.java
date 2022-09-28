package board.dao;

import board.dto.UserDto;

public interface UserDao {
	public UserDto login (String userId);
	public int userRegister (UserDto userDto);
	public int modifyPw (String id,String pw);
	public int modify (UserDto userDto);
	public int delete (String userId);
	
}
