package model;

public class WrokHour {
	private String day;
	private String from;
	private String to;
	
	public WrokHour(String day, String from, String to) {
		super();
		this.day = day;
		this.from = from;
		this.to = to;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	
}
