package model;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class Horario
{

    private ArrayList<Sesion> sesiones;

    public Horario()
    {
        sesiones = new ArrayList<>();
    }

    public ArrayList<Sesion> getSesiones()
    {
        return sesiones;
    }

    public void setSesiones(ArrayList<Sesion> sesiones)
    {
        this.sesiones = sesiones;
    }

    public static boolean chocanHorarios(Curso curso1, Curso curso2)
    {
        return false;
    }

}
