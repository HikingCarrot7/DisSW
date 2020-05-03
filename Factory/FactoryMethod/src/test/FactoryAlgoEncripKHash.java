package test;

/**
 *
 * @author Nicolás
 */
public class FactoryAlgoEncripKHash extends FactoryAlgoEncrip
{

    @Override
    public AlgoEncriptamiento createInstance()
    {
        KHash algo = new KHash();
        algo.configurar();
        return algo;
    }

}
