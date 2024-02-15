public class Ford extends CarEx5{
    int year;
    int manufacturerDiscount;

    public Ford(int year, int manufacturerDiscount, int speed, double price, String color)
    {
        super(speed, price, color);
        this.year = year;
        this.manufacturerDiscount = manufacturerDiscount;
    }

    @Override
    public double getSalePrice()
    {
        double salePrice;
        salePrice = super.getPrice() - super.getPrice() * manufacturerDiscount/100;

        return salePrice;
    }
}
