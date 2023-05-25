package com.hanul.app.dto;

public class TimerListIComDTO {

    String   id ,  title , hour , minuit , second ;
    int  reference;
    
    public  TimerListIComDTO ( ) { }

    public TimerListIComDTO(String id, String title, String hour, String minuit, String second ,int reference) {
        this.id = id;
        this.title = title;
        this.hour = hour;
        this.minuit = minuit;
        this.second = second;
        this.reference = reference;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinuit() {
		return minuit;
	}

	public void setMinuit(String minuit) {
		this.minuit = minuit;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

    
    
    
}//DTO class