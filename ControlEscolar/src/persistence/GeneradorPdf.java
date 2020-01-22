package persistence;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import model.Alumno;
import model.Curso;
import model.Maestro;

/**
 *
 * @author HikingCarrot7
 */
public class GeneradorPdf
{

    public static final String RUTA_REPORTES = "reportes/";
    private final String SATO_LINEA = "\r\n";

    public void generarPdf(Maestro maestro, Curso curso)
    {
        Paragraph titulo = new Paragraph();
        Paragraph alumnos = new Paragraph();
        String nombreArchivo = RUTA_REPORTES
                + maestro.getNombreCompleto().toUpperCase() + "-"
                + curso.getAsignatura().getNombreAsignatura() + ".pdf";

        try
        {
            try (FileOutputStream file = new FileOutputStream(nombreArchivo))
            {
                Document doc = new Document();
                PdfWriter.getInstance(doc, file);
                doc.open();

                ponerEncabezado(titulo, maestro, curso);
                ponerAlumnos(alumnos, curso);

                doc.add(titulo);
                doc.add(alumnos);
                doc.close();
            }

        } catch (FileNotFoundException | DocumentException ex)
        {
            System.out.println(ex.getMessage());

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    private void ponerEncabezado(Paragraph titulo, Maestro maestro, Curso curso)
    {
        titulo.setAlignment("center");
        titulo.add(curso.getAsignatura().getNombreAsignatura().toUpperCase());
        titulo.add(SATO_LINEA);
        titulo.add(maestro.getNombreCompleto().toUpperCase());
        titulo.add(SATO_LINEA);
    }

    private void ponerAlumnos(Paragraph listaAlumnos, Curso curso)
    {
        listaAlumnos.setAlignment("left");
        listaAlumnos.add(SATO_LINEA);

        ArrayList<Alumno> alumnos = curso.obtenerAlumnosOrdenados();

        for (int i = 0; i < alumnos.size(); i++)
        {
            Alumno alumno = alumnos.get(i);
            listaAlumnos.add(String.format("%02d", (i + 1)) + ".-" + alumno.getNombreInvertido().toUpperCase());
            listaAlumnos.add(SATO_LINEA);
        }

    }

}
