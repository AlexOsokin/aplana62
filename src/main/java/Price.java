public class Price {
    private int price;
    private int guarant;

    public int getGuarant() {
        return guarant;
    }

    public void setGuarant(int guarant) {
        this.guarant = guarant;
    }

    public Price() {
        price = 0;
    }
    public Price(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Price priceSum(Price itemPrise){
       return new Price(price + itemPrise.getPrice());
    }
    public int toPrice(String priceString){
        String[] priceStrings = priceString.split(" ");
        return Integer.parseInt(priceStrings[0]) * 1000 + Integer.parseInt(priceStrings[1]);
    }
}
