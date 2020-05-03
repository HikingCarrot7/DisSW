package test;

/**
 *
 * @author Nicolás
 */
public class FactoryAlgoEncripGHash extends FactoryAlgoEncrip
{

    @Override
    public AlgoEncriptamiento createInstance()
    {
        GHash algo = new GHash();
        algo.configurar();
        return algo;
    }

}
