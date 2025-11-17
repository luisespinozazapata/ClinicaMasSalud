package clases;

public class Internamiento {

    private int numInternamiento, codPaciente, codTratamiento;
    private String fecha, hora;

    public Internamiento(int numInternamiento, int codPaciente, int codTratamiento, String fecha, String hora) {
        this.numInternamiento = numInternamiento;
        this.codPaciente = codPaciente;
        this.codTratamiento = codTratamiento;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getNumInternamiento() {
        return numInternamiento;
    }

    public void setNumInternamiento(int numInternamiento) {
        this.numInternamiento = numInternamiento;
    }

    public int getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(int codPaciente) {
        this.codPaciente = codPaciente;
    }

    public int getCodTratamiento() {
        return codTratamiento;
    }

    public void setCodTratamiento(int codTratamiento) {
        this.codTratamiento = codTratamiento;
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
