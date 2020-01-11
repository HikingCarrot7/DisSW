package controlescolar;

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

}
