package pattern;

/**
 *
 * @author nicol
 */
public class ChispasChocolate extends DecoratorBebida
{

    public ChispasChocolate(Bebida bebida)
    {
        super(bebida);
    }

    @Override
    public double getCosto()
    {
        return bebida.getCosto() + 4;
    }

}
