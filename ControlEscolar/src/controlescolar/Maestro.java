package controlescolar;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author HikingCarrot7
 */
public class Maestro extends Persona
{

    private int claveMaestro;
    private ArrayList<Asignatura> asignaturas;

    public Maestro(int clave, String nombre, String apellido)
    {

        super(nombre, apellido);

        asignaturas = new ArrayList<>();
        this.claveMaestro = clave;

    }

    public boolean anadirAsignatura(Asignatura asignatura)
    {

        for (Asignatura asig : asignaturas)
            if (asig.equals(asignatura))
            {
                System.out.println("Está asignatura ya está añadida.");
                return false;
            }

        asignaturas.add(new Asignatura(
                asignatura.getClaveAsignatura(),
                asignatura.getNombreAsignatura(),
                asignatura.getLicenciatura()));

        return true;

    }

    public boolean quitarAsignatura(int claveAsignatura)
    {

        for (Asignatura asignatura : asignaturas)
            if (asignatura.getClaveAsignatura() == claveAsignatura)
            {
                asignaturas.remove(asignatura);
                return true;
            }

        System.out.println("Este maestro no imparte esta asignatura.");
        return false;

    }

    public boolean doyAsignatura(int claveAsignatura)
    {
        return asignaturas.stream().anyMatch((asignatura) -> asignatura.getClaveAsignatura() == claveAsignatura);
    }

    public void eliminarAsignatura(Asignatura asignatura)
    {
        asignaturas.remove(asignatura);
    }

    public int getClaveMaestro()
    {
        return claveMaestro;
    }

    public void setClave(int clave)
    {
        this.claveMaestro = clave;
    }

    public ArrayList<Asignatura> getAsignaturas()
    {
        return asignaturas;
    }

    public Asignatura obtenerAsignatura(int claveAsignatura)
    {

        for (Asignatura asignatura : asignaturas)
            if (asignatura.getClaveAsignatura() == claveAsignatura)
                return asignatura;

        return null;

    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + this.claveMaestro;
        hash = 23 * hash + Objects.hashCode(this.asignaturas);
        return hash;

    }

    @Override
    public boolean equals(Object obj)
    {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Maestro other = (Maestro) obj;

        return this.claveMaestro == other.claveMaestro;

    }

    @Override
    public String toString()
    {
        return getClaveMaestro() + "," + getNombre() + "," + getApellido();
    }

}
