package controlescolar;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class ControlEscolar
{

    private ArrayList<Maestro> maestros;

    public ControlEscolar()
    {
        maestros = new ArrayList<>();
    }

    public void anadirMaestro(Maestro maestro)
    {

        if (maestros.contains(maestro))
            System.out.println("El maestro ya está añadido.");

        else
            maestros.add(maestro);

    }

    public void eliminarMaestro(Maestro maestro)
    {
        maestros.remove(maestro);
    }

    public void anadirAlumno(Maestro maestro, Asignatura asignatura, Alumno alumno)
    {
        maestro.getAsignaturas().get(maestro.getAsignaturas().indexOf(asignatura)).matricularAlumno(alumno);
    }

    public void eliminarAlumno(Maestro maestro, Asignatura asignatura, Alumno alumno)
    {
        maestro.getAsignaturas().get(maestro.getAsignaturas().indexOf(asignatura)).darBajaAlumno(alumno);
    }

    public void relacionar(Maestro maestro, Asignatura asignatura)
    {

        boolean relacionSatisfactoria = maestro.anadirAsignatura(asignatura);

        if (relacionSatisfactoria)
            generarRelacion(maestro, asignatura);

        else
            System.out.println("El maestro ya está relacionado con la asignatura.");

    }

    private void generarRelacion(Maestro maestro, Asignatura asignatura)
    {
        new DAO(DAO.RUTA_RELACIONES).guadarRelacion(maestro + "," + asignatura);
    }

}
