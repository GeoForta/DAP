public class Truck extends CarEx5{
    
    private int weight;

    public Truck(int weight, int speed, double price, String color)
    {
        super(speed, price, color);
        this.weight = weight;
    }

    @Override
    public double getSalePrice()
    {
        double salePrice;
        if (weight > 2000)
        {
            salePrice = super.getPrice() - super.getPrice() * 10/100;
            return salePrice;
        }
        else
        {
            salePrice = super.getPrice() - super.getPrice() * 20/100;
            return salePrice;
            
        }
    }
}
