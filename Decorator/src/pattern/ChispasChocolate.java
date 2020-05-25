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
        System.out.println(String.format("Añadiendo chispas de chocolate ($%d)", 4));
        return bebida.getCosto() + 4;
    }

}
