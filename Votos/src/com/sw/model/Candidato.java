package com.sw.model;

/**
 *
 * @author Nicolás
 */
public class Candidato
{

    private String nombre;
    private int nCandidato;
    private int nVotos;

    public Candidato(String nombre, int nCandidato, int nVotos)
    {
        this.nombre = nombre;
        this.nCandidato = nCandidato;
        this.nVotos = nVotos;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getnCandidato()
    {
        return nCandidato;
    }

    public void setnCandidato(int nCandidato)
    {
        this.nCandidato = nCandidato;
    }

    public int getnVotos()
    {
        return nVotos;
    }

    public void aumentarVotos()
    {
        nVotos++;
    }

    public void setnVotos(int nVotos)
    {
        this.nVotos = nVotos;
    }

}
