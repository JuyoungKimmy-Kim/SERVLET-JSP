package board.service;

import board.dto.UserDto;

/*
 * Q. 왜 LoginDao Interface는 Email만 받고 Login Service는 두 개 다 받느냐?
 *
 * A. Dao의 역할은 Email을 받으면 Email이 있는 정보를 return --> 
 * 	  Service의 역할은 Dao에서 받은 정보를 통해 Email & Password 확인 
 * 	  --> Service쪽에서 Business Logic 처리
 * 
 */


public interface LoginService {
	public UserDto login(String userEmail, String userPassword);
}

