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

        ArrayList<Relacion> relacionesDeMaestrosConAsignaturas = new DAO(DAO.RUTA_RELACIONES).obtenerRelacionesDeMaestrosConAsignaturas();
        ArrayList<Registro> registros = new DAO(DAO.RUTA_REGISTROS).obtenerRegistros();

        relacionesDeMaestrosConAsignaturas.forEach((relacion) ->
        {
            Maestro maestro = obtenerMaestro(relacion.getClaveMaestro());
            Asignatura asignatura = obtenerAsignatura(relacion.getClaveAsignatura());
            int indiceMaestro = maestros.indexOf(maestro);

            maestros.get(indiceMaestro).anadirAsignatura(asignatura);
        });

        registros.forEach((registro) ->
        {
            Maestro maestro = obtenerMaestro(registro.getClaveMaestro());
            Asignatura asignatura = maestro.obtenerAsignatura(registro.getClaveAsignatura());
            Alumno alumno = obtenerAlumno(registro.getMatricula());
            int indiceAsignatura = maestro.getAsignaturas().indexOf(asignatura);

            maestro.getAsignaturas().get(indiceAsignatura).matricularAlumno(alumno);
        });

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

    public void mostrarRelacionesDeMaestrosConAsignaturas()
    {

        for (Maestro maestro : maestros)
        {

            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");

            for (Asignatura asignatura : maestro.getAsignaturas())
                System.out.printf("%-30s%S(%s)\n", " ", asignatura.getNombreAsignatura(), asignatura.getLicenciatura());

        }

    }

    public void mostrarTodasLasRelaciones()
    {

        for (Maestro maestro : maestros)
        {

            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");

            for (Asignatura asignatura : maestro.getAsignaturas())
            {

                System.out.printf("%-30s%S(%s):\n\n", " ", asignatura.getNombreAsignatura(), asignatura.getLicenciatura());

                for (Alumno alumno : asignatura.getAlumnos())
                    System.out.printf("%-70s%S\n", " ", alumno.getNombreCompleto());

            }

        }

    }

    public void anadirMaestro(int claveMaestro, String nombre, String apellido)
    {

        if (!existeMaestro(claveMaestro))
        {
            maestros.add(new Maestro(claveMaestro, nombre, apellido));
            new DAO(DAO.RUTA_MAESTROS).guardarMaestros(getMaestros());

        } else
            System.out.println("La clave ya existe, no se puede añadir al nuevo maestro.");

    }

    public void eliminarMaestro(int claveMaestro)
    {

        for (Maestro maestro : maestros)
            if (maestro.getClaveMaestro() == claveMaestro)
            {
                maestros.remove(maestro);
                new DAO(DAO.RUTA_MAESTROS).guardarMaestros(getMaestros());
                return;
            }

        System.out.println("La clave del maestro no existe.");

    }

    public void anadirAlumno(int matricula, String nombre, String apellido)
    {

        if (!existeAlumno(matricula))
        {
            alumnos.add(new Alumno(matricula, nombre, apellido));
            new DAO(DAO.RUTA_ALUMNOS).guardarAlumnos(getAlumnos());

        } else
            System.out.println("Ya existe un alumno con esa clave.");

    }

    public void eliminarAlumno(int matricula)
    {

        for (Alumno alumno : alumnos)
            if (alumno.getMatricula() == matricula)
            {
                alumnos.remove(alumno);
                new DAO(DAO.RUTA_ALUMNOS).guardarAlumnos(getAlumnos());
                return;
            }

        System.out.println("La clave del alumno no existe.");

    }

    public void anadirAsignatura(int claveAsignatura, String nombreAsignatura, String licenciatura)
    {

        if (!existeAsignatura(claveAsignatura))
        {
            asignaturas.add(new Asignatura(claveAsignatura, nombreAsignatura, licenciatura));
            new DAO(DAO.RUTA_ASIGNATURAS).guardarAsignaturas(getAsignaturas());

        } else
            System.out.println("Ya existe una asignatura con esa clave.");

    }

    public void eliminarAsignatura(int claveAsignatura)
    {

        for (Asignatura asignatura : asignaturas)
            if (asignatura.getClaveAsignatura() == claveAsignatura)
            {
                asignaturas.remove(asignatura);
                new DAO(DAO.RUTA_ASIGNATURAS).guardarAsignaturas(getAsignaturas());
                return;
            }

        System.out.println("La clave de la asignatura no existe.");

    }

    public void relacionarMaestroConAsignatura(int claveMaestro, int claveAsignatura)
    {

        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura))
        {

            Maestro maestro = obtenerMaestro(claveMaestro);

            if (maestro.doyAsignatura(claveAsignatura))
            {
                System.out.println("El maestro ya está relacionado con esta asignatura.");
                return;
            }

            Asignatura asignatura = obtenerAsignatura(claveAsignatura);
            maestro.anadirAsignatura(asignatura);

            guadarRelacionDeMaestroConAsignatura(claveMaestro, claveAsignatura);
            System.out.println("El maestro " + maestro.getNombreCompleto()
                    + " ahora imparte la asignatura " + asignatura.getDescripcion());

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void quitarRelacionDeMaestroConAsignatura(int claveAsignatura, int claveMaestro)
    {

        if (existeAsignatura(claveAsignatura) && existeMaestro(claveMaestro))
        {
            Maestro maestro = obtenerMaestro(claveMaestro);
            maestro.quitarAsignatura(claveAsignatura);
            new DAO(DAO.RUTA_RELACIONES).guardarRelacionesDeMaestrosConAsignaturas(this);

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void relacionarAlumnoConAsignatura(int claveMaestro, int claveAsignatura, int matricula)
    {

        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula))
        {

            Maestro maestro = obtenerMaestro(claveMaestro);
            Asignatura asignatura = obtenerAsignatura(claveAsignatura);
            Alumno alumno = obtenerAlumno(matricula);
            int indiceAsignatura = maestro.getAsignaturas().indexOf(asignatura);

            maestro.getAsignaturas().get(indiceAsignatura).matricularAlumno(alumno);
            new DAO(DAO.RUTA_REGISTROS).guardarRegistros(this);

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void darBajaAlumnoDeAsignatura(int claveMaestro, int claveAsignatura, int matricula)
    {

        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula))
        {

            Maestro maestro = obtenerMaestro(claveMaestro);
            Asignatura asignatura = obtenerAsignatura(claveAsignatura);
            Alumno alumno = obtenerAlumno(matricula);
            int indiceAsignatura = maestro.getAsignaturas().indexOf(asignatura);

            maestro.getAsignaturas().get(indiceAsignatura).darBajaAlumno(alumno);
            new DAO(DAO.RUTA_REGISTROS).guardarRegistros(this);

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

    private void guadarRelacionDeMaestroConAsignatura(int claveMaestro, int claveAsignatura)
    {
        new DAO(DAO.RUTA_RELACIONES).guadarRelacionDeMaestroConAsignatura(new Relacion(claveMaestro, claveAsignatura));
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
