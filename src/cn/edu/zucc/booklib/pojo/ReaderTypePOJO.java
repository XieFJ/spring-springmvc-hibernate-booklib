package cn.edu.zucc.booklib.pojo;

public class ReaderTypePOJO {
	private String readertypeName;
	private int lendLimitted;
	private int dueTime;
	public String getReadertypeName() {
		return readertypeName;
	}
	public void setReadertypeName(String readertypeName) {
		this.readertypeName = readertypeName;
	}
	public int getLendLimitted() {
		return lendLimitted;
	}
	public void setLendLimitted(int lendLimitted) {
		this.lendLimitted = lendLimitted;
	}
	public int getDueTime() {
		return dueTime;
	}
	public void setDueTime(int dueTime) {
		this.dueTime = dueTime;
	}
}
