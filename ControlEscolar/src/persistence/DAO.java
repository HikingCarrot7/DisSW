package persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @param <E>
 * @author HikingC7
 */
public abstract class DAO
{

    protected File file;
    protected final String SALTO_LINEA = "\r\n";

    public DAO(String ruta)
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

    public abstract ArrayList<?> obtenerItems();

}
