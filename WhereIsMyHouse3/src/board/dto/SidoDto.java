package board.dto;

public class SidoDto {
	private String code;
	private String name; 	// 서울 특별시..
	
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
	
	@Override
	public String toString() {
		return "SidoDto [code=" + code + ", name=" + name + "]";
	}
	
	
}
