package pattern;

public class Espresso
{

    public Espresso()
    {
        System.out.println(String.format("Espresso ($%,.2f)", getCosto()));
    }

    public double getCosto()
    {
        return 3;
    }

}
