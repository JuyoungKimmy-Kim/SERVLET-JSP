package board.dto;

import java.sql.Date;

public class BoardDto {
	private int boardId;
	private int userSeq;
	private String title;
	private String content;
	private Date regDt;
	private int readCount;
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	@Override
	public String toString() {
		return "BoardDto [boardId=" + boardId + ", userSeq=" + userSeq + ", title=" + title + ", content=" + content
				+ ", regDt=" + regDt + ", readCount=" + readCount + "]";
	}
	
	
	
}
