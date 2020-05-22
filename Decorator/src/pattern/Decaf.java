package pattern;

public class Decaf extends Bebida
{

    public Decaf()
    {
        System.out.println("Decaf");
    }

    @Override
    public double getCosto()
    {
        return 2;
    }

}
