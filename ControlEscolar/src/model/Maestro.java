package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author HikingCarrot7
 */
public class Maestro extends Persona
{
    private int claveMaestro;
    private ArrayList<Curso> cursosImpartidos;

    public Maestro(int clave, String nombre, String apellido)
    {
        super(nombre, apellido);
        this.claveMaestro = clave;
        cursosImpartidos = new ArrayList<>();
    }

    public boolean anadeCurso(Asignatura asignatura)
    {
        for (Curso curso : cursosImpartidos)
            if (curso.getAsignatura().getClaveAsignatura() == asignatura.getClaveAsignatura())
            {
                System.out.println("Este curso ya está añadido.");
                return false;
            }
        cursosImpartidos.add(new Curso(this, new Asignatura(asignatura.getClaveAsignatura(), asignatura.getNombreAsignatura(),
                asignatura.getLicenciatura())));
        return true;
    }

    public boolean quitaCurso(int claveAsignatura)
    {
        for (Curso curso : cursosImpartidos)
            if (curso.getAsignatura().getClaveAsignatura() == claveAsignatura)
            {
                eliminarCurso(curso);
                return true;
            }
        System.out.println("Este maestro no imparte este curso.");
        return false;
    }

    public Asignatura dameAsignaturaDelCurso(int claveAsignatura)
    {
        for (Curso curso : cursosImpartidos)
            if (curso.getAsignatura().getClaveAsignatura() == claveAsignatura)
                return curso.getAsignatura();
        return null;
    }

    public Curso dameCurso(int claveAsignatura)
    {
        for (Curso curso : cursosImpartidos)
            if (curso.getAsignatura().getClaveAsignatura() == claveAsignatura)
                return curso;
        return null;
    }

    public int dameIndiceDelCurso(int claveAsignatura)
    {
        for (int i = 0; i < cursosImpartidos.size(); i++)
            if (cursosImpartidos.get(i).getAsignatura().getClaveAsignatura() == claveAsignatura)
                return i;
        return -1;
    }

    public boolean doyCurso(int claveAsignatura)
    {
        return cursosImpartidos.stream().anyMatch(curso -> curso.getAsignatura().getClaveAsignatura() == claveAsignatura);
    }

    public void eliminarCurso(Curso curso)
    {
        cursosImpartidos.remove(curso);
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
        return cursosImpartidos;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + this.claveMaestro;
        hash = 23 * hash + Objects.hashCode(this.cursosImpartidos);
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
