package arreglos;

import java.io.*;
import java.util.ArrayList;
import clases.Tratamiento;

public class ArregloTratamientos {
	private static final String RUTA_ARCHIVO = System.getProperty("user.dir") + File.separator + "tratamientos.txt";
    private ArrayList<Tratamiento> tratamientos;

    public ArregloTratamientos() {
        tratamientos = new ArrayList<Tratamiento>();
        cargarTratamientos();
    }

    public void adicionar(Tratamiento x) {
        tratamientos.add(x);
        grabarTratamientos();
    }

    public void eliminar(Tratamiento x) {
        tratamientos.remove(x);
        grabarTratamientos();
    }

    public int tamanio() {
        return tratamientos.size();
    }

    public Tratamiento obtener(int i) {
        return tratamientos.get(i);
    }

    public Tratamiento buscar(int codigo) {
        for (Tratamiento t : tratamientos) {
            if (t.getCodTratamiento() == codigo)
                return t;
        }
        return null;
    }

    public int codigoCorrelativo() {
        if (tratamientos.isEmpty())
            return 101;
        return tratamientos.get(tratamientos.size() - 1).getCodTratamiento() + 1;
    }

    public void actualizarArchivo() {
        grabarTratamientos();
    }

    private void grabarTratamientos() {
        File archivo = new File(RUTA_ARCHIVO);
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Tratamiento t : tratamientos) {
                String linea = t.getCodTratamiento() + ";" +
                               t.getNombreTratamiento() + ";" +
                               t.getDuracionDias() + ";" +
                               t.getSesiones() + ";" +
                               t.getCosto();
                pw.println(linea);
            }
            System.out.println("Archivo de tratamientos guardado en: " + archivo.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error al grabar archivo de tratamientos: " + e.getMessage());
        }
    }

    private void cargarTratamientos() {
        File archivo = new File(RUTA_ARCHIVO);
        System.out.println("Intentando cargar tratamientos desde: " + archivo.getAbsolutePath());
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] s = linea.split(";");
                int codTratamiento = Integer.parseInt(s[0].trim());
                String nombreTratamiento = s[1].trim();
                int duracionDias = Integer.parseInt(s[2].trim());
                int sesiones = Integer.parseInt(s[3].trim());
                double costo = Double.parseDouble(s[4].trim());
                tratamientos.add(new Tratamiento(codTratamiento, nombreTratamiento, duracionDias, sesiones, costo));
            }
            System.out.println("Tratamientos cargados correctamente: " + tratamientos.size());
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo de tratamientos (" + archivo.getAbsolutePath() + ")");
        }
    }
}
