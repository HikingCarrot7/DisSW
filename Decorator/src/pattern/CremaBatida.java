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
        System.out.println("Añadiendo crema batida");
        return bebida.getCosto() + 5;
    }

}
