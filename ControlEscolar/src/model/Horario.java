package model;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @author HikingCarrot7
 */
public class Horario
{

    private ArrayList<Sesion> sesiones;

    public Horario()
    {
        sesiones = new ArrayList<>();
    }

    public static boolean chocanHorarios(Curso curso1, Curso curso2)
    {
        Curso cursoMenoresSesiones = curso1.getHorario().getSesiones().size() > curso2.getHorario().getSesiones().size() ? curso2 : curso1;
        Curso cursoMayoresSesiones = cursoMenoresSesiones == curso1 ? curso2 : curso1;

        for (int i = 0; i < cursoMenoresSesiones.getHorario().getSesiones().size(); i++)
            if (Sesion.chocanSesiones(cursoMenoresSesiones.getHorario().getSesiones().get(i),
                    cursoMayoresSesiones.getHorario().getSesiones().get(i)))
                return true;

        return false;
    }

    public int getMinutosTotalesSemana()
    {
        return sesiones.stream().flatMapToInt(sesion -> IntStream.of(sesion.getMinutosTotalesSesion())).sum();
    }

    public ArrayList<Sesion> getSesiones()
    {
        return sesiones;
    }

    public void setSesiones(ArrayList<Sesion> sesiones)
    {
        this.sesiones = sesiones;
    }

}
