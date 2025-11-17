package arreglos;

import java.io.*;
import java.util.ArrayList;
import clases.Alta;

public class ArregloAltas {
	//Atributos de la Clase ArregloAltas
    private ArrayList<Alta> altas;
    private static final String RUTA_ARCHIVO = System.getProperty("user.dir") + File.separator + "altas.txt";
    
    //Constructor 
    public ArregloAltas() {
    	altas = new ArrayList<Alta>();
        cargarAltas();
    }
    
   //Metodos fundamentales para manejar información en un sistema.
    public void adicionar(Alta x) {
        altas.add(x);
        grabarAltas();
    }

    public void eliminar(Alta x) {
        altas.remove(x);
        grabarAltas();
    }

    public int tamanio() {
        return altas.size();
    }

    public Alta obtener(int i) {
        return altas.get(i);
    }

    public Alta buscar(int codigo) {
        for (Alta x : altas) {
            if (x.getNumAlta() == codigo)
                return x;
        }
        return null;
    }

    public int codigoCorrelativo() {
        if (altas.isEmpty()) return 200001;
        return altas.get(altas.size() - 1).getNumAlta() + 1;
    }

    public void actualizarArchivo() {
        grabarAltas();	
    }

    private void grabarAltas() {
        File archivo = new File(RUTA_ARCHIVO);
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Alta x : altas) {
                String linea = x.getNumAlta() + ";" +
                               x.getNumInternamiento() + ";" +
                               x.getFecha() + ";" +
                               x.getHora();
                pw.println(linea);
            }
            System.out.println(" Archivo de altas guardado en: " + archivo.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(" Error al grabar archivo de altas: " + e.getMessage());
        }
    }

    private void cargarAltas() {
        File archivo = new File(RUTA_ARCHIVO);
        System.out.println("Intentando cargar altas desde: " + archivo.getAbsolutePath());
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("Archivo 'altas.txt' no existía, se creó vacío.");
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] s = linea.split(";");
                    int numAlta = Integer.parseInt(s[0].trim());
                    int numInternamiento = Integer.parseInt(s[1].trim());
                    String fecha = s[2].trim();
                    String hora = s[3].trim();
                    altas.add(new Alta(numAlta, numInternamiento, fecha, hora));
                }
                System.out.println("Altas cargadas correctamente: " + altas.size());
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo de altas: " + e.getMessage());
        }
    }
}
