package edu.gatech.streamingwars.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TimeData {

    @Id
    private long id;

    private int year;
    private int month;

    public TimeData() {}

    public TimeData(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
