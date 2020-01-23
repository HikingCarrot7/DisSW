package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;
import model.Curso;
import model.Maestro;
import model.Relacion;

/**
 * @author HikingC7
 */
public class DAORelaciones extends DAO<HashMap<Maestro, ArrayList<Curso>>>
{

    public static final String RUTA_RELACIONES = "datos/Relaciones.csv";

    public DAORelaciones()
    {
        super(RUTA_RELACIONES);
    }

    @Override
    public void guardarItems(HashMap<Maestro, ArrayList<Curso>> maestros)
    {
        String relaciones = "";

        for (Maestro maestro : maestros.keySet())
            relaciones = maestros.get(maestro)
                    .stream()
                    .map((curso) -> maestro.getClaveMaestro() + ","
                    + curso.getAsignatura().getClaveAsignatura() + ","
                    + curso.getInicialesCurso()
                    + SALTO_LINEA)
                    .reduce(relaciones, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {
            out.format("%s", relaciones);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Relacion> obtenerItems()
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
