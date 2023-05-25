package com.example.myteamproject_chanmin.DTO;

public class TimerAddDTO {

    String  title , hour , minuit , second;

    public TimerAddDTO() {}

    public TimerAddDTO(String title, String hour, String minuit, String second) {
        this.title = title;
        this.hour = hour;
        this.minuit = minuit;
        this.second = second;
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
}
