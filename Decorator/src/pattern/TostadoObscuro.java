package pattern;

public class TostadoObscuro
{

    public TostadoObscuro()
    {
        System.out.println(String.format("Dark roast ($%,.2f)", getCosto()));
    }

    public double getCosto()
    {
        return 1;
    }

}
