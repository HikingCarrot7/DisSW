package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HikingC7
 * @param <E>
 */
public abstract class DAOGeneral<E>
{

    protected File file;

    public DAOGeneral(String ruta)
    {
        file = new File(ruta);

        if (!file.exists())
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public abstract void guardarItems(ArrayList<E> entidades);

    public abstract ArrayList<?> obtenerItems();

}
