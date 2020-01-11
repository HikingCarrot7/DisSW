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

        cargarDatos();

    }

    private void cargarDatos()
    {

        ArrayList<Relacion> relaciones = new DAO(DAO.RUTA_RELACIONES).obtenerRelaciones();
        ArrayList<Registro> registros = new DAO(DAO.RUTA_REGISTROS).obtenerRegistros();

        for (Relacion relacion : relaciones)
        {

            Maestro maestro = obtenerMaestro(relacion.getClaveMaestro());
            Asignatura asignatura = obtenerAsignatura(relacion.getClaveAsignatura());
            int indiceMaestro = maestros.indexOf(maestro);

            maestros.get(indiceMaestro).anadirAsignatura(asignatura);

        }

        for (Registro registro : registros)
        {

            Maestro maestro = obtenerMaestro(registro.getClaveMaestro());
            Asignatura asignatura = maestro.obtenerAsignatura(registro.getClaveAsignatura());
            Alumno alumno = obtenerAlumno(registro.getMatricula());
            int indiceAsignatura = maestro.getAsignaturas().indexOf(asignatura);

            maestro.getAsignaturas().get(indiceAsignatura).matricularAlumno(alumno);

        }

    }

    public void mostrarMaestros()
    {

        System.out.println("MAESTROS:\n");

        System.out.printf("%-15s%s\n", "Clave", "Nombre");

        for (Maestro maestro : maestros)
            System.out.printf("%-15s%S\n", maestro.getClaveMaestro(), maestro.getNombreCompleto());

    }

    public void mostrarAsignaturas()
    {

        System.out.println("ASIGNATURAS:\n");

        System.out.printf("%-15s%-40s%s\n", "Clave", "Nombre", "Licenciatura");

        for (Asignatura asignatura : asignaturas)
            System.out.printf("%-15s%-40S%S\n", asignatura.getClaveAsignatura(), asignatura.getNombreAsignatura(), asignatura.getLicenciatura());

    }

    public void mostrarAlumnos()
    {

        System.out.println("ALUMNOS:\n");

        System.out.printf("%-15s%s\n", "Matrícula", "Nombre");

        for (Alumno alumno : alumnos)
            System.out.printf("%-15s%S\n", alumno.getMatricula(), alumno.getNombreCompleto());

    }

    public void mostrarRelaciones()
    {

        for (Maestro maestro : maestros)
        {

            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");

            for (Asignatura asignatura : maestro.getAsignaturas())
                System.out.printf("%-30s%S\n", " ", asignatura.getNombreAsignatura());

        }

    }

    public void mostrarTodosDatos()
    {

        for (Maestro maestro : maestros)
        {

            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");

            for (Asignatura asignatura : maestro.getAsignaturas())
            {

                System.out.printf("%-30s%S:\n\n", " ", asignatura.getNombreAsignatura());

                for (Alumno alumno : asignatura.getAlumnos())
                    System.out.printf("%-70s%S\n", " ", alumno.getNombreCompleto());

            }

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

        } else
            System.out.println("Alguno de los datos es incorrecto.");

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

    public ArrayList<Maestro> getMaestros()
    {
        return maestros;
    }

    public ArrayList<Asignatura> getAsignaturas()
    {
        return asignaturas;
    }

    public ArrayList<Alumno> getAlumnos()
    {
        return alumnos;
    }

}
