package controlescolar;

import java.util.ArrayList;
import java.util.Collection;
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
import persistence.DAORegistro;
import persistence.DAORelacion;
import persistence.GeneradorPdf;

/**
 *
 * @author HikingCarrot7
 */
public class ControlEscolar {

    private ArrayList<Maestro> maestros;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Alumno> alumnos;

    public ControlEscolar() {
        maestros = new DAOMaestro().obtenerItems();
        asignaturas = new DAOAsignatura().obtenerItems();
        alumnos = new DAOAlumno().obtenerItems();
        cargarDatos();
    }

    private void cargarDatos() {
        ArrayList<Relacion> relacionesMaestrosConCursos = new DAORelacion().obtenerItems();
        ArrayList<Registro> registros = new DAORegistro().obtenerItems();

        relacionesMaestrosConCursos.forEach((relacion) -> {
            Maestro maestro = obtenerMaestro(relacion.getClaveMaestro());
            Asignatura asignatura = obtenerAsignatura(relacion.getClaveAsignatura());
            int indiceMaestro = maestros.indexOf(maestro);

            if (!maestros.get(indiceMaestro).doyCurso(asignatura.getClaveAsignatura()))
                maestros.get(indiceMaestro).anadeCurso(asignatura);
        });

        registros.forEach((registro) -> {
            Maestro maestro = obtenerMaestro(registro.getClaveMaestro());
            Alumno alumno = obtenerAlumno(registro.getMatricula());
            int indiceCurso = maestro.dameIndiceDelCurso(registro.getClaveAsignatura());

            if (!maestro.getCursos().get(indiceCurso).existeAlumnoMatriculado(alumno.getMatricula()))
                maestro.getCursos().get(indiceCurso).matricularAlumno(alumno);

        });

    }

    public void mostrarMaestros() {
        System.out.printf("MAESTROS:\n%-15s%s\n", "Clave", "Nombre");
        maestros.forEach(
                maestro -> System.out.printf("%-15s%S\n", maestro.getClaveMaestro(), maestro.getNombreCompleto()));
    }

    public void mostrarAsignaturas() {
        System.out.printf("ASIGNATURAS:\n%-15s%-40s%s\n", "Clave", "Nombre", "Licenciatura");

        asignaturas.forEach(asignatura -> System.out.printf("%-15s%-40S%S\n", asignatura.getClaveAsignatura(),
                asignatura.getNombreAsignatura(), asignatura.getLicenciatura()));
    }

    public void mostrarAlumnos() {
        System.out.printf("ALUMNOS:\n%-15s%s\n", "Matrícula", "Nombre");
        alumnos.forEach(alumno -> System.out.printf("%-15s%S\n", alumno.getMatricula(), alumno.getNombreCompleto()));
    }

    public void mostrarRelacionesDeMaestrosConAsignaturas() {
        maestros.forEach(maestro -> {
            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");
            maestro.getCursos().forEach(curso -> System.out.printf("%-30s%S(%s)\n", " ",
                    curso.getAsignatura().getNombreAsignatura(), curso.getAsignatura().getLicenciatura()));
        });

    }

    public void mostrarTodasLasRelaciones() {
        maestros.forEach(maestro -> {
            System.out.println("\n" + maestro.getNombreCompleto().toUpperCase() + ":");
            maestro.getCursos().forEach(curso -> {
                System.out.printf("%-30s%S(%s):\n\n", " ", curso.getAsignatura().getNombreAsignatura(),
                        curso.getAsignatura().getLicenciatura());
                curso.getAlumnosInscritos()
                        .forEach(alumno -> System.out.printf("%-70s%S\n", " ", alumno.getNombreCompleto()));
            });
        });

    }

    public void mostrarInformacionAlumno(int matricula) {
        if (existeAlumno(matricula)) {
            Alumno alumno = obtenerAlumno(matricula);
            System.out.printf("%-20s%s\n%-20s%s\n\n", "Matrícula", "Nombre", alumno.getMatricula(),
                    alumno.getNombreCompleto());
            ArrayList<Curso> cursosInscritos = obtenerCursosMatriculadosConAlumno(matricula);

            if (cursosInscritos.isEmpty())
                System.out.println("El alumno no se encuentra inscrito a ningún curso.");

            else {
                System.out.printf("Cursos a los que este alumno se encuentra inscrito son:\n" + "%-45s%-15s%s\n",
                        "Asignatura", "Licenciatura", "Maestro");
                cursosInscritos.forEach(System.out::println);
            }

        } else
            System.out.println("El alumno no existe.");

    }

    public void anadirMaestro(int claveMaestro, String nombre, String apellido) {
        if (!existeMaestro(claveMaestro)) {
            maestros.add(new Maestro(claveMaestro, nombre, apellido));
            guardarMaestros();

        } else
            System.out.println("La clave ya existe, no se puede añadir al nuevo maestro.");

    }

    public void eliminarMaestro(int claveMaestro) {
        if (maestros.removeIf(maestro -> maestro.getClaveMaestro() == claveMaestro))
            guardarMaestros();

        else
            System.out.println("La clave del maestro no existe.");

    }

    public void anadirAlumno(int matricula, String nombre, String apellido) {
        if (!existeAlumno(matricula)) {
            alumnos.add(new Alumno(matricula, nombre, apellido));
            guardarAlumnos();

        } else
            System.out.println("Ya existe un alumno con esa clave.");

    }

    public void eliminarAlumno(int matricula) {
        if (alumnos.removeIf(alumno -> alumno.getMatricula() == matricula))
            guardarAlumnos();

        else
            System.out.println("La clave del alumno no existe.");

    }

    public void anadirAsignatura(int claveAsignatura, String nombreAsignatura, String licenciatura) {
        if (!existeAsignatura(claveAsignatura)) {
            asignaturas.add(new Asignatura(claveAsignatura, nombreAsignatura, licenciatura));
            guardarAsignaturas();

        } else
            System.out.println("Ya existe una asignatura con esa clave.");

    }

    public void eliminarAsignatura(int claveAsignatura) {
        if (asignaturas.removeIf(asignatura -> asignatura.getClaveAsignatura() == claveAsignatura))
            guardarAsignaturas();

        else
            System.out.println("La clave de la asignatura no existe.");

    }

    public void relacionarMaestroConCurso(int claveMaestro, int claveAsignatura) {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura)) {
            Maestro maestro = obtenerMaestro(claveMaestro);

            if (maestro.doyCurso(claveAsignatura)) {
                System.out.println("El maestro ya está relacionado con esta asignatura.");
                return;
            }

            Asignatura asignatura = obtenerAsignatura(claveAsignatura);
            maestro.anadeCurso(asignatura);

            guadarRelacionDeMaestroConCurso(claveMaestro, claveAsignatura);
            System.out.println("El maestro " + maestro.getNombreCompleto() + " ahora imparte la asignatura "
                    + asignatura.getDescripcion());

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void quitarRelacionDeMaestroConCurso(int claveAsignatura, int claveMaestro) {
        if (existeAsignatura(claveAsignatura) && existeMaestro(claveMaestro)) {
            Maestro maestro = obtenerMaestro(claveMaestro);
            maestro.quitaCurso(claveAsignatura);
            guardarRelacionesDeMaestrosConCursos();

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void relacionarAlumnoConCurso(int claveMaestro, int claveAsignatura, int matricula) {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula)) {
            Maestro maestro = obtenerMaestro(claveMaestro);
            Asignatura asignatura = obtenerAsignatura(claveAsignatura);
            Alumno alumno = obtenerAlumno(matricula);
            int indiceAsignatura = maestro.getCursos().indexOf(asignatura);

            maestro.getCursos().get(indiceAsignatura).matricularAlumno(alumno);
            guardarRegistros();

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void darBajaAlumnoDeCurso(int claveMaestro, int claveAsignatura, int matricula) {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura) && existeAlumno(matricula)) {
            Maestro maestro = obtenerMaestro(claveMaestro);
            Asignatura asignatura = obtenerAsignatura(claveAsignatura);
            Alumno alumno = obtenerAlumno(matricula);
            int indiceAsignatura = maestro.getCursos().indexOf(asignatura);

            maestro.getCursos().get(indiceAsignatura).darBajaAlumno(alumno);
            guardarRegistros();

        } else
            System.out.println("Alguno de los datos es incorrecto.");

    }

    public void generarReporte(int claveMaestro, int claveAsignatura) {
        if (existeMaestro(claveMaestro) && existeAsignatura(claveAsignatura)) {
            Maestro maestro = obtenerMaestro(claveMaestro);

            if (maestro.doyCurso(claveAsignatura))
                new GeneradorPdf().generarPdf(obtenerMaestro(claveMaestro), maestro.dameCurso(claveAsignatura));

            else
                System.out.println("El maestro no imparte esta asignatura.");

        } else
            System.out.println("La clave del maestro o asignatura no existe.");

    }

    /**
     * @deprecated
     *
     * @param nuevoCurso
     * @param matricula
     *
     * @return
     */
    public boolean chocaHorarioConCursosAlumno(Curso nuevoCurso, int matricula) {
        ArrayList<Curso> cursos = obtenerCursosMatriculadosConAlumno(matricula);
        return cursos.stream().anyMatch(curso -> Horario.chocanHorarios(curso, nuevoCurso));
    }

    private ArrayList<Curso> obtenerCursosMatriculadosConAlumno(int matricula) {
        return (ArrayList<Curso>) maestros.stream().map(Maestro::getCursos).flatMap(Collection::stream)
                .filter(curso -> curso.existeAlumnoMatriculado(matricula)).collect(Collectors.toList());
    }

    public Asignatura obtenerAsignatura(int claveAsignatura) {
        for (Asignatura asignatura : asignaturas)
            if (asignatura.getClaveAsignatura() == claveAsignatura)
                return asignatura;

        return null;
    }

    public Maestro obtenerMaestro(int claveMaestro) {
        for (Maestro maestro : maestros)
            if (maestro.getClaveMaestro() == claveMaestro)
                return maestro;

        return null;
    }

    public Alumno obtenerAlumno(int matricula) {
        for (Alumno alumno : alumnos)
            if (alumno.getMatricula() == matricula)
                return alumno;

        return null;
    }

    public boolean existeMaestro(int claveMaestro) {
        return obtenerMaestro(claveMaestro) != null;
    }

    public boolean existeAsignatura(int claveAsignatura) {
        return obtenerAsignatura(claveAsignatura) != null;
    }

    public boolean existeAlumno(int matricula) {
        return obtenerAlumno(matricula) != null;
    }

    private void guadarRelacionDeMaestroConCurso(int claveMaestro, int claveAsignatura) {
        new DAORelacion().guadarRelacionDeMaestroConAsignatura(new Relacion(claveMaestro, claveAsignatura));
    }

    private void guardarRelacionesDeMaestrosConCursos() {
        new DAORelacion().guardarItems(getMaestros());
    }

    private void guardarMaestros() {
        new DAOMaestro().guardarItems(getMaestros());
    }

    private void guardarAlumnos() {
        new DAOAlumno().guardarItems(getAlumnos());
    }

    private void guardarAsignaturas() {
        new DAOAsignatura().guardarItems(getAsignaturas());
    }

    private void guardarRegistros() {
        new DAORegistro().guardarItems(getMaestros());
    }

    public ArrayList<Maestro> getMaestros() {
        return maestros;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

}
