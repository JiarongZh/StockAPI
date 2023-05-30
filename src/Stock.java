public class Stock {
    private String ticker;
    private double price;
    private double low;
    private double high;
    private double change;

    public Stock(String ticker, double price, double low, double high, double change){
        this.ticker = ticker;
        this.price = price;
        this.low = low;
        this.high = high;
        this.change = change;
    }

    public String getTicker(){
        return ticker;
    }
    public double getPrice(){
        return price;
    }
    public double getLow(){
        return low;
    }
    public double getHigh(){
        return high;
    }
    public double getChange(){
        return change;
    }
}
