package model;

/**
 *
 * @author HikingCarrot7
 */
public class Alumno extends Persona
{

    private int matricula;

    public Alumno(int matricula, String nombre, String apellido)
    {
        super(nombre, apellido);

        this.matricula = matricula;

    }

    public int getMatricula()
    {
        return matricula;
    }

    public void setMatricula(int matricula)
    {
        this.matricula = matricula;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 43 * hash + this.matricula;
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

        final Alumno other = (Alumno) obj;

        return this.matricula == other.matricula;
    }

    @Override
    public String toString()
    {
        return String.format("%s,%s,%s", getMatricula(), getNombre(), getApellido());
    }

}
