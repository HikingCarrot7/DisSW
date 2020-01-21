package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import model.Maestro;
import model.Relacion;

/**
 *
 * @author HikingC7
 */
public class DAORelacion extends DAOGeneral<Maestro>
{

    public static final String RUTA_RELACIONES = "datos/Relaciones.csv";

    public DAORelacion()
    {
        super(RUTA_RELACIONES);
    }

    @Override
    public void guardarEntidades(ArrayList<Maestro> maestros)
    {
        String relacionesDeMaestrosConAsignaturas = "";

        for (Maestro maestro : maestros)
            relacionesDeMaestrosConAsignaturas = maestro.getAsignaturas()
                    .stream()
                    .map((asignatura) -> maestro.getClaveMaestro() + ","
                    + asignatura.getClaveAsignatura()
                    + System.getProperty("line.separator"))
                    .reduce(relacionesDeMaestrosConAsignaturas, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {

            out.format("%s", relacionesDeMaestrosConAsignaturas);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Relacion> obtenerEntidades()
    {
        ArrayList<Relacion> relacionesDeMaestrosConAsignaturas = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            while (in.hasNext())
            {

                String[] relacion = in.nextLine().split(",");

                int claveMaestro = Integer.parseInt(relacion[0]);
                int claveAsignatura = Integer.parseInt(relacion[1]);

                relacionesDeMaestrosConAsignaturas.add(new Relacion(claveMaestro, claveAsignatura));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return relacionesDeMaestrosConAsignaturas;
    }

    public void guadarRelacionDeMaestroConAsignatura(Relacion relacion)
    {

        try (Formatter out = new Formatter(new FileWriter(file, true)))
        {

            out.format("%s", relacion + System.getProperty("line.separator"));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
