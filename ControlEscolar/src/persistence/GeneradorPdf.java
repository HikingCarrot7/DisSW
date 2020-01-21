package persistence;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import model.Alumno;
import model.Asignatura;
import model.Maestro;

/**
 *
 * @author HikingCarrot7
 */
public class GeneradorPdf
{

    public static final String RUTA_REPORTES = "reportes/";
    private final String SATO_LINEA = "\r\n";

    public void generarPdf(Maestro maestro, Asignatura asignatura)
    {
        Paragraph titulo = new Paragraph();
        Paragraph alumnos = new Paragraph();

        try
        {
            FileOutputStream file = new FileOutputStream(RUTA_REPORTES + maestro.getNombreCompleto().toUpperCase() + ".pdf");
            Document doc = new Document();
            PdfWriter.getInstance(doc, file);
            doc.open();

            ponerEncabezado(titulo, maestro, asignatura);
            ponerAlumnos(alumnos, asignatura);

            doc.add(titulo);
            doc.add(alumnos);
            doc.close();

        } catch (FileNotFoundException | DocumentException ex)
        {
            System.out.println(ex.getMessage());

        }

    }

    private void ponerEncabezado(Paragraph titulo, Maestro maestro, Asignatura asignatura)
    {
        titulo.setAlignment("center");
        titulo.add(asignatura.getNombreAsignatura().toUpperCase());
        titulo.add(SATO_LINEA);
        titulo.add(maestro.getNombreCompleto().toUpperCase());
        titulo.add(SATO_LINEA);
    }

    private void ponerAlumnos(Paragraph listaAlumnos, Asignatura asignatura)
    {
        listaAlumnos.setAlignment("left");
        listaAlumnos.add(SATO_LINEA);

        ArrayList<Alumno> alumnos = asignatura.obtenerAlumnosOrdenados();

        for (int i = 0; i < alumnos.size(); i++)
        {
            Alumno alumno = alumnos.get(i);
            listaAlumnos.add((i + 1) + ".-" + alumno.getNombreInvertido().toUpperCase());
            listaAlumnos.add(SATO_LINEA);
        }

    }

}
