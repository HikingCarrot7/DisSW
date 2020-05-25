package pattern;

public class MezclaCasa
{

    public MezclaCasa()
    {
        System.out.println(String.format("House blend ($%,.2f)", getCosto()));
    }

    public double getCosto()
    {
        return 4;
    }

}
