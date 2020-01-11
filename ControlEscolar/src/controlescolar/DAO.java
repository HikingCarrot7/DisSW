package controlescolar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author HikingCarrot7
 */
public class DAO
{

    public static final String RUTA_MAESTROS = "datos/Maestros.csv";
    public static final String RUTA_ALUMNOS = "datos/Alumnos.csv";
    public static final String RUTA_ASIGNATURAS = "datos/Asignaturas.csv";
    public static final String RUTA_RELACIONES = "datos/Relaciones.csv";
    public static final String RUTA_REGISTROS = "datos/Registros.csv";

    private File file;

    public DAO(String ruta)
    {

        file = new File(ruta);

        if (!file.exists())
            try
            {
                file.createNewFile();

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }

    }

    public ArrayList<Maestro> obtenerMaestros()
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

    public ArrayList<Alumno> obtenerAlumnos()
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

    public ArrayList<Asignatura> obtenerAsignaturas()
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

    public void guadarRelacion(Relacion relacion)
    {

        try (Formatter out = new Formatter(new FileWriter(file, true)))
        {

            out.format("%s", relacion + System.getProperty("line.separator"));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Relacion> obtenerRelaciones()
    {

        ArrayList<Relacion> relaciones = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            while (in.hasNext())
            {

                String[] relacion = in.nextLine().split(",");

                int claveMaestro = Integer.parseInt(relacion[0]);
                int claveAsignatura = Integer.parseInt(relacion[1]);

                relaciones.add(new Relacion(claveMaestro, claveAsignatura));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return relaciones;

    }

    public void guardarRegistros()
    {

    }

    public ArrayList<Registro> obtenerRegistros()
    {

        ArrayList<Registro> registros = new ArrayList<>();

        try (Scanner in = new Scanner(new FileReader(file)))
        {

            in.nextLine();

            while (in.hasNext())
            {

                String[] registro = in.nextLine().split(",");

                int claveMaestro = Integer.parseInt(registro[0]);
                int claveAsignatura = Integer.parseInt(registro[1]);
                int maticula = Integer.parseInt(registro[2]);

                registros.add(new Registro(claveMaestro, claveAsignatura, maticula));

            }

        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }

        return registros;

    }

}
