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
        if (weight > 2000)
        {
            price = price - price * 10/100;
            return price;
        }
        else
        {
            price = price - price * 20/100;
            return price;
        }
    }
}
