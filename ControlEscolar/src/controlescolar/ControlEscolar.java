package controlescolar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import model.Alumno;
import model.Asignatura;
import model.Curso;
import model.Horario;
import model.Maestro;
import model.Registro;
import model.Relacion;
import persistence.DAOAlumno;
import persistence.DAOAsignatura;
import persistence.DAOMaestro;
import persistence.DAORegistros;
import persistence.DAORelaciones;
import persistence.GeneradorPdf;

/**
 * @author HikingCarrot7
 */
public class ControlEscolar
{

    private HashMap<Maestro, ArrayList<Curso>> relaciones;
    private ArrayList<Maestro> maestros;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Alumno> alumnos;

    public ControlEscolar()
    {
        relaciones = new HashMap<>();
        maestros = new DAOMaestro().obtenerItems();
        asignaturas = new DAOAsignatura().obtenerItems();
        alumnos = new DAOAlumno().obtenerItems();
        cargarDatos();
    }

    private void cargarDatos()
    {
        ArrayList<Relacion> relacionesMaestrosConCursos = new DAORelaciones().obtenerItems();
        ArrayList<Registro> registros = new DAORegistros().obtenerItems();

        maestros.forEach((maestro) ->
        {
            relaciones.put(maestro, new ArrayList<>());
        });

        relacionesMaestrosConCursos.forEach((relacion) ->
        {
            anadirRelacionMaestroCurso(relacion.getClaveMaestro(), relacion.getClaveAsignatura());
        });

        registros.forEach((registro) ->
        {
            obtenerCursoMaestro(registro.getClaveMaestro(),
                    registro.getClaveAsignatura()).matricularAlumno(obtenerAlumno(registro.getMatricula()));
        });

        relaciones.entrySet().stream()
                .map(Entry::getValue)
                .forEach(cursos -> cursos
                .sort(Comparator.comparing(curso -> curso.getAsignatura().getNombreAsignatura())));

    }

    public void mostrarMaestros()
    {
        System.out.printf("MAESTROS:\n%-15s%s\n", "Clave", "Nombre");
        maestros.forEach(maestro -> System.out.printf("%-15s%S\n", maestro.getClaveMaestro(), maestro.getNombreCompleto()));
    }

    public void mostrarAsignaturas()
    {
        System.out.printf("ASIGNATURAS:\n%-15s%-40s%s\n", "Clave", "Nombre", "Licenciatura");
        asignaturas.forEach(asignatura -> System.out.printf("%-15s%-40S%S\n", asignatura.getClaveAsignatura(),
                asignatura.getNombreAsignatura(), asignatura.getLicenciatura()));
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
            obtenerCursosMaestro(maestro.getClaveMaestro())
                    .forEach(curso -> System.out.printf("%-30s%S(%s)\n", " ",
                    curso.getAsignatura().getNombreAsignatura(), curso.getAsignatura().getLicenciatura()));
        });
    }

    public void mostrarTodasLasRelaciones()
    {
        maestros.forEach(maestro ->
        {
            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");
            obtenerCursosMaestro(maestro.getClaveMaestro()).forEach(curso ->
            {
                System.out.printf("%-30s%S(%s):\n\n", " ", curso.getAsignatura().getNombreAsignatura(),
                        curso.getAsignatura().getLicenciatura());
                curso.getAlumnosInscritos().forEach(alumno -> System.out.printf("%-70s%S\n", " ", alumno.getNombreCompleto()));
            });
        });
    }

    public void mostrarInformacionAlumno(int matricula)
    {
        if (existeAlumno(matricula))
        {
            Alumno alumno = obtenerAlumno(matricula);

            System.out.printf("%-20s%s\n%-20s%s\n\n", "Matrícula", "Nombre", alumno.getMatricula(),
                    alumno.getNombreCompleto());

            ArrayList<Curso> cursosInscritos = obtenerCursosMatriculadosConAlumno(matricula);

            if (cursosInscritos.isEmpty())
                System.out.println("El alumno no se encuentra inscrito a ningún curso.");
            else
            {
                System.out.printf("Cursos a los que este alumno se encuentra inscrito son:\n" + "%-45s%-15s%s\n",
                        "Asignatura", "Licenciatura", "Maestro");
                cursosInscritos.forEach(System.out::println);
            }

        } else
            System.out.println("El alumno no existe.");

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
        {
            relaciones.remove(obtenerMaestro(claveMaestro));
            guardarMaestros();

        } else
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
        {
            relaciones.entrySet().stream()
                    .map(Entry::getValue)
                    .flatMap(Collection::stream)
                    .map(Curso::getAlumnosInscritos)
                    .forEach(alumnosInscritos -> alumnosInscritos.removeIf(alumno -> alumno.getMatricula() == matricula));

            guardarAlumnos();
            guardarRegistros();

        } else
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
        {
            relaciones.entrySet()
                    .stream()
                    .map(Entry::getValue)
                    .forEach(cursos -> cursos
                    .removeIf(curso -> curso.getAsignatura().getClaveAsignatura() == claveAsignatura));

            guardarAsignaturas();
            guardarRelacionesDeMaestrosConCursos();

        } else
            System.out.println("La clave de la asignatura no existe.");

    }

    public boolean relacionarMaestroCurso(int claveMaestro, int claveAsignatura)
    {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && !maestroDaCurso(claveMaestro, claveAsignatura))
        {
            Maestro maestro = obtenerMaestro(claveMaestro);
            Asignatura asignatura = obtenerAsignatura(claveAsignatura);

            anadirRelacionMaestroCurso(claveMaestro, claveAsignatura);
            guardarRelacionesDeMaestrosConCursos();
            System.out.printf("El maestro %s ahora imparte el curso %s\n", maestro.getNombreCompleto(), asignatura.getDescripcion());
            return true;
        }

        System.out.println("Alguno de los datos son incorrectos o el maestro ya imparte ese curso.");
        return false;
    }

    private void anadirRelacionMaestroCurso(int claveMaestro, int claveAsignatura)
    {
        Maestro maestro = obtenerMaestro(claveMaestro);
        Asignatura asignatura = obtenerAsignatura(claveAsignatura);

        relaciones.get(maestro).add(new Curso(maestro,
                new Asignatura(
                        asignatura.getClaveAsignatura(),
                        asignatura.getNombreAsignatura(),
                        asignatura.getLicenciatura())));
    }

    public boolean quitarRelacionDeMaestroConCurso(int claveMaestro, int claveAsignatura)
    {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && maestroDaCurso(claveMaestro, claveAsignatura))
        {
            obtenerCursosMaestro(claveMaestro).remove(obtenerCursoMaestro(claveMaestro, claveAsignatura));
            guardarRelacionesDeMaestrosConCursos();
            return true;
        }

        System.out.println("Alguno de los datos son incorrectos o el maestro ya imparte ese curso.");
        return false;
    }

    public void relacionarAlumnoConCurso(int claveMaestro, int claveAsignatura, int matricula)
    {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula))
            if (maestroDaCurso(claveMaestro, claveAsignatura) && !obtenerCursoMaestro(claveMaestro, claveAsignatura).existeAlumnoMatriculado(matricula))
            {
                obtenerCursoMaestro(claveMaestro, claveAsignatura).matricularAlumno(obtenerAlumno(matricula));
                guardarRegistros();

            } else
                System.out.println("El maestro no da ese curso o el alumno ya está matriculado a él.");

        else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void darBajaAlumnoDeCurso(int claveMaestro, int claveAsignatura, int matricula)
    {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula))
        {
            obtenerCursoMaestro(claveMaestro, claveAsignatura).darBajaAlumno(obtenerAlumno(matricula));
            guardarRegistros();

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void generarReporte(int claveMaestro, int claveAsignatura)
    {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura))
            if (maestroDaCurso(claveMaestro, claveAsignatura))
                new GeneradorPdf().generarPdf(obtenerMaestro(claveMaestro), obtenerCursoMaestro(claveMaestro, claveAsignatura));

            else
                System.out.println("El maestro no imparte esta asignatura.");

        else
            System.out.println("La clave del maestro o asignatura no existe.");

    }

    /**
     * @param nuevoCurso
     * @param matricula
     * @return
     * @deprecated
     */
    public boolean chocaHorarioConCursosAlumno(Curso nuevoCurso, int matricula)
    {
        ArrayList<Curso> cursos = obtenerCursosMatriculadosConAlumno(matricula);
        return cursos.stream().anyMatch(curso -> Horario.chocanHorarios(curso, nuevoCurso));
    }

    private ArrayList<Curso> obtenerCursosMatriculadosConAlumno(int matricula)
    {
        return (ArrayList<Curso>) relaciones
                .entrySet()
                .stream()
                .map(Entry::getValue)
                .flatMap(Collection::stream)
                .filter(curso -> curso.existeAlumnoMatriculado(matricula))
                .collect(Collectors.toList());
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

    public ArrayList<Curso> obtenerCursosMaestro(int claveMaestro)
    {
        return relaciones.get(obtenerMaestro(claveMaestro));
    }

    public Curso obtenerCursoMaestro(int claveMaestro, int claveAsignatura)
    {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura))
        {
            Maestro maestro = obtenerMaestro(claveMaestro);

            for (Curso curso : relaciones.get(maestro))
                if (curso.getAsignatura().getClaveAsignatura() == claveAsignatura)
                    return curso;
        }

        return null;
    }

    public boolean maestroDaCurso(int claveMaestro, int claveAsignatura)
    {
        return obtenerCursoMaestro(claveMaestro, claveAsignatura) != null;
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

    private void guardarRelacionesDeMaestrosConCursos()
    {
        new DAORelaciones().guardarItems(getRelaciones());
    }

    private void guardarMaestros()
    {
        new DAOMaestro().guardarItems(getMaestros());
    }

    private void guardarAlumnos()
    {
        new DAOAlumno().guardarItems(getAlumnos());
    }

    private void guardarAsignaturas()
    {
        new DAOAsignatura().guardarItems(getAsignaturas());
    }

    private void guardarRegistros()
    {
        new DAORegistros().guardarItems(getRelaciones());
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

    public HashMap<Maestro, ArrayList<Curso>> getRelaciones()
    {
        return relaciones;
    }

}
