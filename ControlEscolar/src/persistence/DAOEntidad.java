package persistence;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public interface DAOEntidad<E>
{

    public void guardarItems(ArrayList<E> entidades);

}
