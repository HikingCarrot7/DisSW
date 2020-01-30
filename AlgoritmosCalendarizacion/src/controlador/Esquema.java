package controlador;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author HikingCarrot7
 */
public final class Esquema extends JPanel
{

    private final int HEIGHT = 525;
    private final int WIDTH = 580;
    private final int MIDDLE = WIDTH / 2;
    private final int CPU_WIDTH = 200;
    private final int CPU_HEIGHT = 200;

    private Rectangle cpu;

    public Esquema()
    {
        cpu = new Rectangle(MIDDLE - CPU_WIDTH / 2, 10, CPU_WIDTH, CPU_HEIGHT);
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        dibujarProcesador(g2d);
    }

    private void dibujarProcesador(Graphics2D g)
    {
        final int LINE_LENGTH = 8;

        dibujarRectanguloCentrado(g, cpu);
        dibujarTextoCentradoRect(g, "Core i9 999999 xe", 20, cpu);

        for (int i = 0; i <= cpu.width; i += 5)
        {
            g.drawLine(cpu.x + i, cpu.y, cpu.x + i, cpu.y - LINE_LENGTH);
            g.drawLine(cpu.x + i, cpu.y + cpu.height, cpu.x + i, cpu.y + cpu.height + LINE_LENGTH);
        }

        for (int i = 0; i <= cpu.height; i += 5)
        {
            g.drawLine(cpu.x, cpu.y + i, cpu.x - LINE_LENGTH, cpu.y + i);
            g.drawLine(cpu.x + cpu.width, cpu.y + i, cpu.x + cpu.width + LINE_LENGTH, cpu.y + i);
        }

    }

    private void dibujarRectanguloCentrado(Graphics2D g, int y, int width, int height)
    {
        g.drawRect(MIDDLE - width / 2, y, width, height);
    }

    private void dibujarRectanguloCentrado(Graphics2D g, Rectangle rect)
    {
        g.drawRect(MIDDLE - rect.width / 2, rect.y, rect.width, rect.height);
    }

    private void dibujarTextoCentradoRect(Graphics2D g, String text, int y, Rectangle rect)
    {
        FontMetrics fm = g.getFontMetrics();
        Rectangle bounds = fm.getStringBounds(text, g).getBounds();
        g.drawString(text, (float) (rect.x + rect.width / 2 - bounds.getWidth() / 2), rect.y + y);
    }

}
