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

    }

    public boolean matricularAlumno(Alumno alumno)
    {

        for (Alumno alum : alumnos)
            if (alum.equals(alumno))
            {
                System.out.println("Este alumno ya está matriculado a esta asignatura");
                return false;
            }

        return true;

    }

    public void darBajaAlumno(Alumno alumno)
    {
        alumnos.remove(alumno);
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
