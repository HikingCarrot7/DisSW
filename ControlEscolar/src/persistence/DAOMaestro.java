package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.stream.Collectors;
import model.Maestro;

/**
 * @author HikingC7
 */
public class DAOMaestro extends DAO<ArrayList<Maestro>>
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
                .map((maestro) -> maestro + SALTO_LINEA)
                .reduce(datosMaestros, String::concat);

        try (Formatter out = new Formatter(new FileWriter(FILE)))
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
        try
        {
            return Files.lines(Paths.get(RUTA_MAESTROS))
                    .map(line -> line.split(","))
                    .map(data -> new Maestro(Integer.parseInt(data[0]), data[1], data[2]))
                    .collect(Collectors.toCollection(ArrayList::new));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return new ArrayList<>();
    }
}
