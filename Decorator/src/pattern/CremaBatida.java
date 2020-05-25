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
        System.out.println(String.format("Añadiendo crema batida ($%d)", 5));
        return bebida.getCosto() + 5;
    }

}
