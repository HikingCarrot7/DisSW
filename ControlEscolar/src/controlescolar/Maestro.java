package controlescolar;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class Maestro extends Persona
{

    private int clave;
    private ArrayList<Asignatura> asignaturas;

    public Maestro(int clave, String nombre, String apellido)
    {

        super(nombre, apellido);

        asignaturas = new ArrayList<>();
        this.clave = clave;

    }

    public boolean anadirAsignatura(Asignatura asignatura)
    {

        if (asignaturas.contains(asignatura))
        {
            System.out.println("Está asignatura ya está añadida.");
            return false;

        } else
            asignaturas.add(asignatura);

        return true;

    }

    public void eliminarAsignatura(Asignatura asignatura)
    {
        asignaturas.remove(asignatura);
    }

    public int getClave()
    {
        return clave;
    }

    public void setClave(int clave)
    {
        this.clave = clave;
    }

    public ArrayList<Asignatura> getAsignaturas()
    {
        return asignaturas;
    }

    @Override
    public String toString()
    {
        return clave + "," + nombre + "," + apellido;
    }

}
