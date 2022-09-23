package board.dto;

public class GugunDto {
	private String code;
	private String name;
	private String sidoCode;
	
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSidoCode() {
		return sidoCode;
	}


	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}


	@Override
	public String toString() {
		return "GugunDto [code=" + code + ", name=" + name + ", sidoCode=" + sidoCode + "]";
	}
	
	
}
