public class Sedan extends CarEx5{
    public int length;

    public Sedan(int length, int speed, double price, String color)
    {
        super(speed, price, color);
        this.length = length;
    }
    @Override
    public double getSalePrice()
    {
        double salePrice;
        if (length > 20)
        {
            salePrice = super.getPrice() - (super.getPrice() * 5/100);
            return salePrice;
        }
        else
        {
            salePrice = super.getPrice() - (super.getPrice() * 10/100);
            return salePrice;
        }
    }
    
}
