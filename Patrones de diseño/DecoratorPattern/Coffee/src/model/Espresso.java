package model;

/**
 *
 * @author Nicolás
 */
public class Espresso extends Beverage
{

    @Override
    public double cost()
    {
        return 1;
    }

    @Override
    public String desc()
    {
        return "Un Espresso con: ";
    }

}
