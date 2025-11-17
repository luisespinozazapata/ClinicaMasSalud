package clases;

public class Tratamiento {
    private int codTratamiento, duracionDias, sesiones; 
    private double costo;
    private String nombreTratamiento;

    public Tratamiento(int codTratamiento, String nombreTratamiento, int duracionDias, int sesiones, double costo) {
        this.codTratamiento = codTratamiento;
        this.nombreTratamiento = nombreTratamiento;
        this.duracionDias = duracionDias;
        this.sesiones = sesiones;
        this.costo = costo;
    }

    public int getCodTratamiento() {
        return codTratamiento;
    }

    public void setCodTratamiento(int codTratamiento) {
        this.codTratamiento = codTratamiento;
    }

    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    public void setNombreTratamiento(String nombreTratamiento) {
        this.nombreTratamiento = nombreTratamiento;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public int getSesiones() {
        return sesiones;
    }

    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

}
