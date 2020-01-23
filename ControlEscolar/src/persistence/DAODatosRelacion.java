package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import model.Curso;
import model.Maestro;

/**
 *
 * @author HikingCarrot7
 */
public interface DAODatosRelacion
{

    public void guardarItems(HashMap<Maestro, ArrayList<Curso>> relaciones);

}
