package pattern;

/**
 *
 * @author nicol
 */
public abstract class DecoratorBebida extends Bebida
{

    protected Bebida bebida;

    public DecoratorBebida(Bebida bebida)
    {
        this.bebida = bebida;
    }

    public Bebida getBebida()
    {
        return bebida;
    }

    public void setBebida(Bebida bebida)
    {
        this.bebida = bebida;
    }

}
