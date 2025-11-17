package clases;

public class Alta {
    private int numAlta, numInternamiento;
    private String fecha, hora;

    public Alta(int numAlta, int numInternamiento, String fecha, String hora) {
        this.numAlta = numAlta;
        this.numInternamiento = numInternamiento;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getNumAlta() {
        return numAlta;
    }

    public void setNumAlta(int numAlta) {
        this.numAlta = numAlta;
    }

    public int getNumInternamiento() {
        return numInternamiento;
    }

    public void setNumInternamiento(int numInternamiento) {
        this.numInternamiento = numInternamiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
