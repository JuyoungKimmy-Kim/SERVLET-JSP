package board.dto;

public class CodeDto {
	private String groupCode;
	private String code;
	private String codeName;
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	@Override
	public String toString() {
		return "CodeDto [groupCode=" + groupCode + ", code=" + code + ", codeName=" + codeName + "]";
	}
	
	
	
}
