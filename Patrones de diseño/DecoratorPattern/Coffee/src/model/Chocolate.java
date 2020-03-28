package model;

/**
 *
 * @author Nicolás
 */
public class Chocolate extends AddonDecorator
{

    public Chocolate(Beverage beverage, double cantidad)
    {
        super(beverage, cantidad);
    }

    public Chocolate(double cantidad)
    {
        super(null, cantidad);
    }

    @Override
    public double cost()
    {
        return beverage.cost() + 5 * cantidad;
    }

    @Override
    public String desc()
    {
        return beverage.desc() + ", " + cantidad + " de Chocolate";
    }

}
