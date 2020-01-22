package model;

import java.util.Objects;

/**
 *
 * @author HikingCarrot7
 */
public class Asignatura {

    private int claveAsignatura;
    private String nombreAsignatura;
    private String licenciatura;

    public Asignatura(int clave, String nombreAsignatura, String licenciatura) {
        this.claveAsignatura = clave;
        this.nombreAsignatura = nombreAsignatura;
        this.licenciatura = licenciatura;
    }

    public int getClaveAsignatura() {
        return claveAsignatura;
    }

    public void setClaveAsignatura(int claveAsignatura) {
        this.claveAsignatura = claveAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsinatura) {
        this.nombreAsignatura = nombreAsinatura;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura;
    }

    public String getDescripcion() {
        return getNombreAsignatura() + " - " + getLicenciatura();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.claveAsignatura;
        hash = 67 * hash + Objects.hashCode(this.nombreAsignatura);
        hash = 67 * hash + Objects.hashCode(this.licenciatura);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Asignatura other = (Asignatura) obj;

        return this.claveAsignatura == other.claveAsignatura;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", getClaveAsignatura(), getNombreAsignatura(), getLicenciatura());
    }

}
