package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import model.Alumno;

/**
 *
 * @author HikingC7
 */
public class DAOAlumno extends DAOGeneral<Alumno>
{

    public static final String RUTA_ALUMNOS = "datos/Alumnos.csv";

    public DAOAlumno()
    {
        super(RUTA_ALUMNOS);
    }

    @Override
    public void guardarEntidades(ArrayList<Alumno> alumnos)
    {
        String datosAlumno = "";

        datosAlumno = alumnos
                .stream()
                .map((alumno) -> alumno + System.getProperty("line.separator"))
                .reduce(datosAlumno, String::concat);

        try (Formatter out = new Formatter(new FileWriter(file)))
        {

            out.format("%s", datosAlumno);

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Alumno> obtenerEntidades()
    {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            while (in.hasNext())
            {

                String[] datos = in.nextLine().split(",");

                int matricula = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];

                alumnos.add(new Alumno(matricula, nombre, apellido));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return alumnos;
    }

}
