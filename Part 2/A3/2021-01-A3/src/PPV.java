public class PPV extends Event{
    private int price;
    public PPV(String type, String name, String yearProduced, int duration, Studio producedBy, int licensingFee) {
        super(type, name, yearProduced, duration, producedBy, licensingFee);
    }

    public PPV(){}

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
