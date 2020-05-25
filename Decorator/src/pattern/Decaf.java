package pattern;

public class Decaf extends Bebida
{

    public Decaf()
    {
        System.out.println(String.format("Decaf ($%,.2f)", getCosto()));
    }

    @Override
    public double getCosto()
    {
        return 12;
    }

}
