package model;

/**
 *
 * @author Nicolás
 */
public class Caramel extends AddonDecorator
{

    public Caramel(Beverage beverage, double cantidad)
    {
        super(beverage, cantidad);
    }

    public Caramel(double cantidad)
    {
        super(null, cantidad);
    }

    @Override
    public double cost()
    {
        return beverage.cost() + 2 * cantidad;
    }

    @Override
    public String desc()
    {
        return beverage.desc() + ", " + cantidad + " de caramelo";
    }

}
