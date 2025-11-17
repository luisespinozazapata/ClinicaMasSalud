package arreglos;

import java.io.*;
import java.util.ArrayList;
import clases.Internamiento;

public class ArregloInternamientos {
    private ArrayList<Internamiento> internamientos;
    private static final String RUTA_ARCHIVO = System.getProperty("user.dir") + File.separator + "internamientos.txt";

    public ArregloInternamientos() {
        internamientos = new ArrayList<Internamiento>();
        cargarInternamientos();
    }

    public void adicionar(Internamiento x) {
        internamientos.add(x);
        grabarInternamientos();
    }

    public void eliminar(Internamiento x) {
        internamientos.remove(x);
        grabarInternamientos();
    }

    public int tamanio() {
        return internamientos.size();
    }

    public Internamiento obtener(int i) {
        return internamientos.get(i);
    }

    public Internamiento buscar(int codigo) {
        for (Internamiento x : internamientos) {
            if (x.getNumInternamiento() == codigo)
                return x;
        }
        return null;
    }

    public Internamiento buscarPorPaciente(int codPaciente) {
        for (Internamiento x : internamientos) {
            if (x.getCodPaciente() == codPaciente)
                return x;
        }
        return null;
    }

    public int codigoCorrelativo() {
        if (internamientos.isEmpty()) return 100001;
        return internamientos.get(internamientos.size() - 1).getNumInternamiento() + 1;
    }

    public void actualizarArchivo() {
        grabarInternamientos();
    }

    private void grabarInternamientos() {
        File archivo = new File(RUTA_ARCHIVO);
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Internamiento x : internamientos) {
                String linea = x.getNumInternamiento() + ";" +
                               x.getCodPaciente() + ";" +
                               x.getCodTratamiento() + ";" +
                               x.getFecha() + ";" +
                               x.getHora();
                pw.println(linea);
            }
            System.out.println("Archivo de internamientos guardado en: " + archivo.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error al grabar archivo de internamientos: " + e.getMessage());
        }
    }

    private void cargarInternamientos() {
        File archivo = new File(RUTA_ARCHIVO);
        System.out.println("Intentando cargar internamientos desde: " + archivo.getAbsolutePath());
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("Archivo 'internamientos.txt' no existía, se creó vacío.");
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] s = linea.split(";");
                    int numInternamiento = Integer.parseInt(s[0].trim());
                    int codPaciente = Integer.parseInt(s[1].trim());
                    int codTratamiento = Integer.parseInt(s[2].trim());
                    String fecha = s[3].trim();
                    String hora = s[4].trim();
                    internamientos.add(new Internamiento(numInternamiento, codPaciente, codTratamiento, fecha, hora));
                }
                System.out.println("Internamientos cargados correctamente: " + internamientos.size());
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo de internamientos: " + e.getMessage());
        }
    }
}
