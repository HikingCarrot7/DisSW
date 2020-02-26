package persistence;

import java.io.File;
import java.io.IOException;

/**
 * @param <E>
 * @author HikingC7
 */
public abstract class DAOGeneral<S, L> implements DataKeeper<S>, DataLoader<L>
{

    protected final File FILE;
    protected final String SALTO_LINEA = "\r\n";

    public DAOGeneral(String ruta)
    {
        FILE = new File(ruta);

        if (!FILE.exists())
            try
            {
                FILE.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    @Override
    public abstract void saveData(S item);

    @Override
    public abstract L loadData();

}
