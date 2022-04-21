public abstract class Event {
    private String type;
    private String name;
    private String yearProduced;
    private int duration;
    private Studio producedBy;
    private int licensingFee;

    public Event(String type, String name, String yearProduced, int duration, Studio producedBy, int licensingFee) {
        this.type = type;
        this.name = name;
        this.yearProduced = yearProduced;
        this.duration = duration;
        this.producedBy = producedBy;
        this.licensingFee = licensingFee;
    }

    public Event(String name, String yearProduced) {
        this.name = name;
        this.yearProduced = yearProduced;
    }

    public Event(){}

    public String getName() {
        return name;
    }

    public String getYearProduced() {
        return yearProduced;
    }

    public int getDuration() {
        return duration;
    }

    public Studio getProducedBy() {
        return producedBy;
    }

    public int getLicensingFee() {
        return licensingFee;
    }

    public String getType() {
        return type;
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

    public void setProducedBy(Studio producedBy) {
        this.producedBy = producedBy;
    }

    public void setLicensingFee(int licensingFee) {
        this.licensingFee = licensingFee;
    }
}
