package controlescolar;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class ControlEscolar
{

    private ArrayList<Maestro> maestros;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Alumno> alumnos;

    public ControlEscolar()
    {

        maestros = new DAO(DAO.RUTA_MAESTROS).obtenerMaestros();
        asignaturas = new DAO(DAO.RUTA_ASIGNATURAS).obtenerAsignaturas();
        alumnos = new DAO(DAO.RUTA_ALUMNOS).obtenerAlumnos();

        cargarAsiganturas();
        matricularAlumnos();

    }

    private void cargarAsiganturas()
    {

        ArrayList<Relacion> relaciones = new DAO(DAO.RUTA_RELACIONES).obtenerRelaciones();

    }

    private void matricularAlumnos()
    {

    }

    public void mostrarMaestros()
    {

        System.out.println("MAESTROS:\n");

        for (Maestro maestro : maestros)
            System.out.println(maestro.getNombreCompleto().toUpperCase());

    }

    public void mostrarRelaciones()
    {

        for (Maestro maestro : maestros)
        {

            System.out.println(maestro.getNombreCompleto() + ":");

            for (Asignatura asignatura : maestro.getAsignaturas())
                System.out.printf("%-30s%-20s%s\n", " ", asignatura.getNombreAsignatura(), asignatura.getClaveAsignatura());

        }

    }

    public void relacionar(int claveMaestro, int claveAsignatura)
    {

        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura))
        {

            Asignatura asignatura = obtenerAsignatura(claveAsignatura);
            Maestro maestro = obtenerMaestro(claveMaestro);

            boolean relacionSatisfactoria = maestro.anadirAsignatura(asignatura);

            if (relacionSatisfactoria)
                generarRelacion(claveMaestro, claveAsignatura);

            else
                System.out.println("El maestro ya está relacionado con la asignatura.");

        }

    }

    public void anadirMaestro(int claveMaestro, String nombre, String apellido)
    {

        if (!existeMaestro(claveMaestro))
            maestros.add(new Maestro(claveMaestro, nombre, apellido));
        else
            System.out.println("La clave ya existe, no se puede añadir al nuevo maestro.");

    }

    public void eliminarMaestro(int claveMaestro)
    {

        for (Maestro maestro : maestros)
            if (maestro.getClaveMaestro() == claveMaestro)
            {
                maestros.remove(maestro);
                return;
            }

        System.out.println("La clave del maestro no existe.");

    }

    public void anadirAlumno(int claveMaestro, int claveAsignatura, int matricula)
    {

        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula))
        {

            Maestro maestro = obtenerMaestro(claveMaestro);
            int indiceAsignatura = asignaturas.indexOf(obtenerAsignatura(claveAsignatura));
            Alumno alumno = obtenerAlumno(matricula);

            maestro.getAsignaturas().get(indiceAsignatura).matricularAlumno(alumno);

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void darBajaAlumno(int claveMaestro, int claveAsignatura, int matricula)
    {

        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula))
        {

            Maestro maestro = obtenerMaestro(claveMaestro);
            int indiceAsignatura = asignaturas.indexOf(obtenerAsignatura(claveAsignatura));
            Alumno alumno = obtenerAlumno(matricula);

            maestro.getAsignaturas().get(indiceAsignatura).darBajaAlumno(alumno);

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public Asignatura obtenerAsignatura(int claveAsignatura)
    {

        for (Asignatura asignatura : asignaturas)
            if (asignatura.getClaveAsignatura() == claveAsignatura)
                return asignatura;

        return null;

    }

    public Maestro obtenerMaestro(int claveMaestro)
    {

        for (Maestro maestro : maestros)
            if (maestro.getClaveMaestro() == claveMaestro)
                return maestro;

        return null;

    }

    public Alumno obtenerAlumno(int matricula)
    {

        for (Alumno alumno : alumnos)
            if (alumno.getMatricula() == matricula)
                return alumno;

        return null;

    }

    public boolean existeMaestro(int claveMaestro)
    {
        return obtenerMaestro(claveMaestro) != null;
    }

    public boolean existeAsignatura(int claveAsignatura)
    {
        return obtenerAsignatura(claveAsignatura) != null;
    }

    public boolean existeAlumno(int matricula)
    {
        return obtenerAlumno(matricula) != null;
    }

    private void generarRelacion(int claveMaestro, int claveAsignatura)
    {
        new DAO(DAO.RUTA_RELACIONES).guadarRelacion(new Relacion(claveMaestro, claveAsignatura));
    }

}
