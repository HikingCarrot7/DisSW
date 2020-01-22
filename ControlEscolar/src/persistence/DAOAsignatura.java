package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import model.Asignatura;

/**
 * @author HikingC7
 */
public class DAOAsignatura extends DAOGeneral<Asignatura>
{
    public static final String RUTA_ASIGNATURAS = "datos/Asignaturas.csv";

    public DAOAsignatura()
    {
        super(RUTA_ASIGNATURAS);
    }

    @Override
    public void guardarItems(ArrayList<Asignatura> asignaturas)
    {
        String datosAsignaturas = "";
        datosAsignaturas = asignaturas
                .stream()
                .map((asignatura) -> asignatura + System.getProperty("line.separator"))
                .reduce(datosAsignaturas, String::concat);
        try (Formatter out = new Formatter(new FileWriter(file)))
        {
            out.format("%s", datosAsignaturas);
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Asignatura> obtenerItems()
    {
        ArrayList<Asignatura> asignaturas = new ArrayList<>();
        try (Scanner in = new Scanner(new FileReader(file)))
        {
            while (in.hasNext())
            {
                String[] datos = in.nextLine().split(",");
                int clave = Integer.parseInt(datos[0]);
                String nombreAsignatura = datos[1];
                String licenciatura = datos[2];
                asignaturas.add(new Asignatura(clave, nombreAsignatura, licenciatura));
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        return asignaturas;
    }
}
