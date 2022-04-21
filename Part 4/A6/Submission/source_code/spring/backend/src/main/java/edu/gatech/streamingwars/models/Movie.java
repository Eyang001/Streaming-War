package edu.gatech.streamingwars.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("movie")
public class Movie extends Event {
    public Movie (String type, String name, String yearProduced, int duration, String studioShortName, int licensingFee){
        super(type, name, yearProduced, duration, studioShortName, licensingFee);
    }

    public Movie(){}

}

