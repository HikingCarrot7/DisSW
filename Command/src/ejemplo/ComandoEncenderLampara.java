package ejemplo;

import pattern.Command;

/**
 *
 * @author Nicolás
 */
public class ComandoEncenderLampara implements Command
{

    private Lampara lampara;

    public ComandoEncenderLampara(Lampara lampara)
    {
        this.lampara = lampara;
    }

    @Override
    public void execute()
    {
        lampara.prender();
    }

    @Override
    public void undo()
    {
        lampara.apagar();
    }

}
