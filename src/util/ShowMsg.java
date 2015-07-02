package util;

public class ShowMsg {
	private String title;
	private String content;
	private int steps;
	public int getSteps() {
		return steps;
	}
	public ShowMsg(){
		
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public ShowMsg(String string, String string2,int steps) {
		title=string;
		content=string2;
		this.steps=steps;
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
	

}
