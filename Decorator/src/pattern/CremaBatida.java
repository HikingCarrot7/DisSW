package pattern;

/**
 *
 * @author nicol
 */
public class CremaBatida extends DecoratorBebida
{

    public CremaBatida(Bebida bebida)
    {
        super(bebida);
    }

    @Override
    public double getCosto()
    {
        return bebida.getCosto() + 5;
    }

}
