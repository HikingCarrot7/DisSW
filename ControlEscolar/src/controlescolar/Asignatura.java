package controlescolar;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author HikingCarrot7
 */
public class Asignatura
{

    private int claveAsignatura;
    private ArrayList<Alumno> alumnos;
    private String nombreAsignatura;
    private String licenciatura;

    public Asignatura(int clave, String nombreAsignatura, String licenciatura)
    {
        this.claveAsignatura = clave;
        this.nombreAsignatura = nombreAsignatura;
        this.licenciatura = licenciatura;

        alumnos = new ArrayList<>();

    }

    public boolean matricularAlumno(Alumno alumno)
    {

        if (existeAlumnoMatriculado(claveAsignatura))
        {
            System.out.println("Este alumno ya está matriculado a esta asignatura");
            return false;
        }

        alumnos.add(alumno);
        return true;

    }

    public void darBajaAlumno(Alumno alumno)
    {

        if (!existeAlumnoMatriculado(alumno.getMatricula()))
            System.out.println("El alumno no está matriculado con esta asignatura.");
        else
            alumnos.remove(alumno);

    }

    public boolean existeAlumnoMatriculado(int matricula)
    {
        return alumnos.stream().anyMatch((alumno) -> alumno.getMatricula() == matricula);
    }

    public int getClaveAsignatura()
    {
        return claveAsignatura;
    }

    public void setClaveAsignatura(int claveAsignatura)
    {
        this.claveAsignatura = claveAsignatura;
    }

    public String getNombreAsignatura()
    {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsinatura)
    {
        this.nombreAsignatura = nombreAsinatura;
    }

    public String getLicenciatura()
    {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura)
    {
        this.licenciatura = licenciatura;
    }

    public ArrayList<Alumno> getAlumnos()
    {
        return alumnos;
    }

    public String getDescripcion()
    {
        return getNombreAsignatura() + " - " + getLicenciatura();
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 67 * hash + this.claveAsignatura;
        hash = 67 * hash + Objects.hashCode(this.alumnos);
        hash = 67 * hash + Objects.hashCode(this.nombreAsignatura);
        hash = 67 * hash + Objects.hashCode(this.licenciatura);
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

        final Asignatura other = (Asignatura) obj;

        return this.claveAsignatura == other.claveAsignatura;
    }

    @Override
    public String toString()
    {
        return getClaveAsignatura() + "," + getNombreAsignatura() + "," + getLicenciatura();

    }

}
