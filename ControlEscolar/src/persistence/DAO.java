package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @param <E>
 * @author HikingC7
 */
public abstract class DAO<E>
{

    protected final File FILE;
    protected final String SALTO_LINEA = "\r\n";

    public DAO(String ruta)
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

    public abstract void guardarItems(E items);

    public abstract ArrayList<?> obtenerItems();

}
