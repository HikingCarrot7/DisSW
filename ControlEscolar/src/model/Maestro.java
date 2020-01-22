package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author HikingCarrot7
 */
public class Maestro extends Persona
{

    private int claveMaestro;
    private ArrayList<Curso> cursosImparto;

    public Maestro(int clave, String nombre, String apellido)
    {
        super(nombre, apellido);
        this.claveMaestro = clave;
        cursosImparto = new ArrayList<>();
    }

    public boolean anadirCurso(Asignatura asignatura)
    {
        for (Curso curso : cursosImparto)
            if (curso.getAsignatura().getClaveAsignatura() == asignatura.getClaveAsignatura())
            {
                System.out.println("Está asignatura ya está añadida.");
                return false;
            }

        cursosImparto.add(new Curso(new Asignatura(asignatura.getClaveAsignatura(), asignatura.getNombreAsignatura(),
                asignatura.getLicenciatura())));
        return true;
    }

    public boolean quitarCurso(int claveAsignatura)
    {
        for (Curso curso : cursosImparto)
            if (curso.getAsignatura().getClaveAsignatura() == claveAsignatura)
            {
                eliminarCurso(curso);
                return true;
            }

        System.out.println("Este maestro no imparte esta asignatura.");
        return false;
    }

    public Asignatura obtenerAsignaturaDeCurso(int claveAsignatura)
    {
        for (Curso curso : cursosImparto)
            if (curso.getAsignatura().getClaveAsignatura() == claveAsignatura)
                return curso.getAsignatura();

        return null;
    }

    public Curso obtenerCurso(int claveAsignatura)
    {
        for (Curso curso : cursosImparto)
            if (curso.getAsignatura().getClaveAsignatura() == claveAsignatura)
                return curso;

        return null;
    }

    public int indiceCurso(int claveAsignatura)
    {
        for (int i = 0; i < cursosImparto.size(); i++)
            if (cursosImparto.get(i).getAsignatura().getClaveAsignatura() == claveAsignatura)
                return i;

        return -1;
    }

    public boolean doyCurso(int claveAsignatura)
    {
        return cursosImparto.stream().anyMatch(curso -> curso.getAsignatura().getClaveAsignatura() == claveAsignatura);
    }

    public void eliminarCurso(Curso curso)
    {
        cursosImparto.remove(curso);
    }

    public int getClaveMaestro()
    {
        return claveMaestro;
    }

    public void setClave(int clave)
    {
        this.claveMaestro = clave;
    }

    public ArrayList<Curso> getCursos()
    {
        return cursosImparto;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + this.claveMaestro;
        hash = 23 * hash + Objects.hashCode(this.cursosImparto);
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

        final Maestro other = (Maestro) obj;

        return this.claveMaestro == other.claveMaestro;
    }

    @Override
    public String toString()
    {
        return String.format("%s,%s,%s", getClaveMaestro(), getNombre(), getApellido());
    }

}
