package edu.gatech.streamingwars.archivemodels;

import javax.persistence.*;

@Entity
public class EventArchive {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private long originId;
    private String type;
    private String name;

    private String yearProduced;

    private int duration;
    private String studioShortName;
    private int licensingFee;
    private Long produceId;

    private boolean isOffered;
    private boolean isWatched;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArchiveData archiveData;

    public EventArchive() {
    }

    public EventArchive(Long originId, String type, String name, String yearProduced, int duration,
                        String studioShortName, int licensingFee, Long produceId, boolean isOffered, boolean isWatched) {
        this.originId = originId;
        this.type = type;
        this.name = name;
        this.yearProduced = yearProduced;
        this.duration = duration;
        this.studioShortName = studioShortName;
        this.licensingFee = licensingFee;
        this.produceId = produceId;
        this.isOffered = isOffered;
        this.isWatched = isWatched;
    }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearProduced(String yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStudioShortName(String studioShortName) {
        this.studioShortName = studioShortName;
    }

    public void setLicensingFee(int licensingFee) {
        this.licensingFee = licensingFee;
    }

    public void setProduceId(Long produceId) {
        this.produceId = produceId;
    }

    public void setOffered(boolean offered) {
        isOffered = offered;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }

    public void setArchiveData(ArchiveData archiveData) {
        this.archiveData = archiveData;
    }

    // public Long getId() {
    //     return id;
    // }


    public long getOriginId() {
        return originId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getYearProduced() {
        return yearProduced;
    }

    public int getDuration() {
        return duration;
    }

    public String getStudioShortName() {
        return studioShortName;
    }

    public int getLicensingFee() {
        return licensingFee;
    }

    public Long getProduceId() {
        return produceId;
    }

    public boolean isOffered() {
        return isOffered;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public ArchiveData getArchiveData() {
        return archiveData;
    }


}
