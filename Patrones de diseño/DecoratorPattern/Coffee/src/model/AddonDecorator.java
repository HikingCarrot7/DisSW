package model;

/**
 *
 * @author Nicolás
 */
public abstract class AddonDecorator extends Beverage
{

    protected Beverage beverage;
    protected double cantidad;

    public AddonDecorator(Beverage beverage, double cantidad)
    {
        this.beverage = beverage;
        this.cantidad = cantidad;
    }

    public Beverage getBeverage()
    {
        return beverage;
    }

    public void setBeverage(Beverage beverage)
    {
        this.beverage = beverage;
    }

    public double getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(double cantidad)
    {
        this.cantidad = cantidad;
    }

    @Override
    public abstract double cost();

}
