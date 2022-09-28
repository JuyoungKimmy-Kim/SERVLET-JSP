package board.dto;

public class GroupCodeDto {
	private String groupCode;
	private String groupCodeName;
	
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupCodeName() {
		return groupCodeName;
	}
	public void setGroupCodeName(String groupCodeName) {
		this.groupCodeName = groupCodeName;
	}
	
	@Override
	public String toString() {
		return "GroupCodeDto [groupCode=" + groupCode + ", groupCodeName=" + groupCodeName + "]";
	}
	
	
	
}
