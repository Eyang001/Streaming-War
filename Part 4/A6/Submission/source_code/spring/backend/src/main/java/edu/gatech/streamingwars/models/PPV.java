package edu.gatech.streamingwars.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("ppv")
public class PPV extends Event{
    public PPV(String type, String name, String yearProduced, int duration, String studioShortName, int licensingFee) {
        super(type, name, yearProduced, duration, studioShortName, licensingFee);
    }

    public PPV(){}

}
