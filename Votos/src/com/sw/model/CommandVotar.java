package com.sw.model;

/**
 *
 * @author Nicolás
 */
public class CommandVotar implements Command
{

    private final Candidato candidato;

    public CommandVotar(Candidato candidato)
    {
        this.candidato = candidato;
    }

    @Override
    public void execute()
    {
        candidato.setnVotos(candidato.getnVotos() + 1);
    }

}
