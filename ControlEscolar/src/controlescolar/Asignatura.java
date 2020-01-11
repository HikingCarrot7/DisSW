package controlescolar;

import java.util.ArrayList;

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

        if (alumnos.contains(alumno))
        {
            System.out.println("Este alumno ya está matriculado a esta asignatura");
            return false;

        } else
            alumnos.add(alumno);

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
    public String toString()
    {
        return claveAsignatura + "," + nombreAsignatura + "," + licenciatura;

    }

}
