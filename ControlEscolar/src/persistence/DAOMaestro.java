package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import model.Maestro;

/**
 * @author HikingC7
 */
public class DAOMaestro extends DAOGeneral<Maestro>
{

    public static final String RUTA_MAESTROS = "datos/Maestros.csv";

    public DAOMaestro()
    {
        super(RUTA_MAESTROS);
    }

    @Override
    public void guardarItems(ArrayList<Maestro> maestros)
    {
        String datosMaestros = "";
        datosMaestros = maestros
                .stream()
                .map((maestro) -> maestro + System.getProperty("line.separator"))
                .reduce(datosMaestros, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {
            out.format("%s", datosMaestros);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Maestro> obtenerItems()
    {
        ArrayList<Maestro> maestros = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {
            while (in.hasNext())
            {
                String[] datos = in.nextLine().split(",");
                int claveMaestro = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];

                maestros.add(new Maestro(claveMaestro, nombre, apellido));
            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return maestros;
    }
}
