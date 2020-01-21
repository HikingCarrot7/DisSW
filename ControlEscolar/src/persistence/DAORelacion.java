package persistence;

import java.util.ArrayList;
import model.Relacion;

/**
 *
 * @author HikingC7
 */
public class DAORelacion extends DAOGeneral<Relacion, Relacion>
{

    public DAORelacion(String ruta)
    {
        super(ruta);
    }

    @Override
    public void guardarEntidades(ArrayList<Relacion> entidades)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Relacion> obtenerEntidades()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
