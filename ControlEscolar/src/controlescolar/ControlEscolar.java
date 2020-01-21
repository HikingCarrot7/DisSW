package controlescolar;

import model.Registro;
import model.Maestro;
import model.Alumno;
import model.Relacion;
import model.Asignatura;
import persistence.DAO;
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
        System.out.printf("MAESTROS:\n%-15s%s\n", "Clave", "Nombre");
        maestros.forEach(maestro -> System.out.printf("%-15s%S\n", maestro.getClaveMaestro(), maestro.getNombreCompleto()));
    }

    public void mostrarAsignaturas()
    {

        System.out.printf("ASIGNATURAS:\n%-15s%-40s%s\n", "Clave", "Nombre", "Licenciatura");

        asignaturas.forEach(asignatura -> System.out.printf("%-15s%-40S%S\n",
                asignatura.getClaveAsignatura(),
                asignatura.getNombreAsignatura(),
                asignatura.getLicenciatura()));

    }

    public void mostrarAlumnos()
    {
        System.out.printf("ALUMNOS:\n%-15s%s\n", "Matrícula", "Nombre");
        alumnos.forEach(alumno -> System.out.printf("%-15s%S\n", alumno.getMatricula(), alumno.getNombreCompleto()));

    }

    public void mostrarRelacionesDeMaestrosConAsignaturas()
    {

        maestros.forEach(maestro ->
        {
            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");
            maestro.getAsignaturas().forEach(asignatura -> System.out.printf("%-30s%S(%s)\n", " ",
                    asignatura.getNombreAsignatura(),
                    asignatura.getLicenciatura()));
        });

    }

    public void mostrarTodasLasRelaciones()
    {

        maestros.forEach(maestro ->
        {
            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");
            maestro.getAsignaturas().forEach(asignatura
                    ->
            {
                System.out.printf("%-30s%S(%s):\n\n", " ", asignatura.getNombreAsignatura(), asignatura.getLicenciatura());
                asignatura.getAlumnos().forEach(alumno -> System.out.printf("%-70s%S\n", " ", alumno.getNombreCompleto()));
            });
        });

    }

    public void anadirMaestro(int claveMaestro, String nombre, String apellido)
    {

        if (!existeMaestro(claveMaestro))
        {
            maestros.add(new Maestro(claveMaestro, nombre, apellido));
            guardarMaestros();

        } else
            System.out.println("La clave ya existe, no se puede añadir al nuevo maestro.");

    }

    public void eliminarMaestro(int claveMaestro)
    {

        if (maestros.removeIf(maestro -> maestro.getClaveMaestro() == claveMaestro))
            guardarMaestros();
        else
            System.out.println("La clave del maestro no existe.");

    }

    public void anadirAlumno(int matricula, String nombre, String apellido)
    {

        if (!existeAlumno(matricula))
        {
            alumnos.add(new Alumno(matricula, nombre, apellido));
            guardarAlumnos();

        } else
            System.out.println("Ya existe un alumno con esa clave.");

    }

    public void eliminarAlumno(int matricula)
    {

        if (alumnos.removeIf(alumno -> alumno.getMatricula() == matricula))
            guardarAlumnos();
        else
            System.out.println("La clave del alumno no existe.");

    }

    public void anadirAsignatura(int claveAsignatura, String nombreAsignatura, String licenciatura)
    {

        if (!existeAsignatura(claveAsignatura))
        {
            asignaturas.add(new Asignatura(claveAsignatura, nombreAsignatura, licenciatura));
            guardarAsignaturas();

        } else
            System.out.println("Ya existe una asignatura con esa clave.");

    }

    public void eliminarAsignatura(int claveAsignatura)
    {

        if (asignaturas.removeIf(asignatura -> asignatura.getClaveAsignatura() == claveAsignatura))
            guardarAsignaturas();
        else
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
            guardarRelacionesDeMaestrosConAsignaturas();

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
            guardarRegistros();

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
            guardarRegistros();

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

    private void guardarRelacionesDeMaestrosConAsignaturas()
    {
        new DAO(DAO.RUTA_RELACIONES).guardarRelacionesDeMaestrosConAsignaturas(getMaestros());
    }

    private void guardarMaestros()
    {
        new DAO(DAO.RUTA_MAESTROS).guardarMaestros(getMaestros());
    }

    private void guardarAlumnos()
    {
        new DAO(DAO.RUTA_ALUMNOS).guardarAlumnos(getAlumnos());
    }

    private void guardarAsignaturas()
    {
        new DAO(DAO.RUTA_ASIGNATURAS).guardarAsignaturas(getAsignaturas());
    }

    private void guardarRegistros()
    {
        new DAO(DAO.RUTA_REGISTROS).guardarRegistros(getMaestros());
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
