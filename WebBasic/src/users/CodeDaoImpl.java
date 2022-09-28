package users;

import java.util.ArrayList;
import java.util.List;

public class CodeDaoImpl {
	List<CodeDto> getCodeList (String code) {
		List<CodeDto> list=new ArrayList<>();
		
		
		// Connection -> preparedStatement => resultSet
		//select code, code_name from code where group_code==?
		
		// return 되는 code, code_name => CodeDto 여러 개를 
		
		return list;
	}
}
