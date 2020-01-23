package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author HikingCarrot7
 */
public class Curso
{

    private Horario horario;
    private Asignatura asignatura;
    private Maestro maestro;
    private ArrayList<Alumno> alumnosInscritos;

    public Curso(Maestro maestro, Asignatura asignatura)
    {
        this.asignatura = asignatura;
        this.maestro = maestro;
        horario = new Horario();
        alumnosInscritos = new ArrayList<>();
    }

    public boolean matricularAlumno(Alumno alumno)
    {
        if (existeAlumnoMatriculado(alumno.getMatricula()))
        {
            System.out.println("Este alumno ya está matriculado a este curso.");
            return false;
        }
        alumnosInscritos.add(alumno);
        return true;
    }

    public void darBajaAlumno(Alumno alumno)
    {
        if (!existeAlumnoMatriculado(alumno.getMatricula()))
            System.out.println("El alumno no está matriculado con este curso.");
        else
            alumnosInscritos.remove(alumno);
    }

    public String getInicialesCurso()
    {
        String[] palabras = asignatura.getNombreAsignatura().split("\\s+");
        String result = "";
        for (String palabra : palabras)
            if (palabra.length() > 3)
                result += palabra.substring(0, 3).toUpperCase();
        return result;
    }

    public ArrayList<Alumno> obtenerAlumnosOrdenados()
    {
        ArrayList<Alumno> alumnosOrdenados = new ArrayList<>(alumnosInscritos);
        return (ArrayList<Alumno>) alumnosOrdenados.stream()
                .sorted(Comparator.comparing(Alumno::getApellido))
                .collect(Collectors.toList());
    }

    public boolean existeAlumnoMatriculado(int matricula)
    {
        return alumnosInscritos.stream().anyMatch(alumno -> alumno.getMatricula() == matricula);
    }

    public Maestro getMaestro()
    {
        return maestro;
    }

    public void setMaestro(Maestro maestro)
    {
        this.maestro = maestro;
    }

    public Horario getHorario()
    {
        return horario;
    }

    public void setHorario(Horario horario)
    {
        this.horario = horario;
    }

    public Asignatura getAsignatura()
    {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura)
    {
        this.asignatura = asignatura;
    }

    public ArrayList<Alumno> getAlumnosInscritos()
    {
        return alumnosInscritos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos)
    {
        this.alumnosInscritos = alumnos;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.asignatura);
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
        final Curso other = (Curso) obj;
        return asignatura.getClaveAsignatura() == other.getAsignatura().getClaveAsignatura();
    }

    @Override
    public String toString()
    {
        return String.format("%-45S%-15S%S", getAsignatura().getNombreAsignatura(),
                getAsignatura().getLicenciatura(),
                getMaestro().getNombreCompleto());
    }
}
