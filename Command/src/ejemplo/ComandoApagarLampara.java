package ejemplo;

import pattern.Command;

/**
 *
 * @author Nicolás
 */
public class ComandoApagarLampara implements Command
{

    private Lampara lampara;

    public ComandoApagarLampara(Lampara lampara)
    {
        this.lampara = lampara;
    }

    @Override
    public void execute()
    {
        lampara.apagar();
    }

    @Override
    public void undo()
    {
        lampara.prender();
    }

}
