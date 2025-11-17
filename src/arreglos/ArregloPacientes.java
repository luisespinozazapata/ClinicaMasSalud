package arreglos;
	
import java.io.*;
import java.util.ArrayList;
import clases.Paciente;

public class ArregloPacientes {
	//Atributo prinicipal|
    private ArrayList<Paciente> pacientes;
    
    //Constructor inicializador

    public ArregloPacientes() {
        pacientes = new ArrayList<Paciente>();
        cargarPacientes();
    }
    
    //Metodos 
    public void adicionar(Paciente x) {
        pacientes.add(x);
        grabarPacientes();
    }

    public void eliminar(Paciente x) {
        pacientes.remove(x);
        grabarPacientes();
    }

    public int tamanio() {
        return pacientes.size();
    }

    public Paciente obtener(int i) {
        return pacientes.get(i);
    }

    public Paciente buscar(int codigo) {
        for (Paciente p : pacientes) {
            if (p.getCodPaciente() == codigo)
                return p;
        }
        return null;
    }

    public int codigoCorrelativo() {
        if (pacientes.isEmpty()) return 202010001;
        return pacientes.get(pacientes.size() - 1).getCodPaciente() + 1;
    }

    public void actualizarArchivo() {
        grabarPacientes();
    }

    private void grabarPacientes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("pacientes.txt"))) {
            for (Paciente p : pacientes) {
                String linea = p.getCodPaciente() + ";" +
                               p.getNombres() + ";" +
                               p.getApellidos() + ";" +
                               p.getDni() + ";" +
                               p.getEdad() + ";" +
                               p.getCelular() + ";" +
                               p.getEstado();
                pw.println(linea);
            }
        } catch (Exception e) {
            System.out.println("Error al grabar archivo de pacientes: " + e.getMessage());
        }
    }

    private void cargarPacientes() {
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] s = linea.split(";");
                int codPaciente = Integer.parseInt(s[0].trim());
                String nombres = s[1].trim();
                String apellidos = s[2].trim();
                String dni = s[3].trim();
                int edad = Integer.parseInt(s[4].trim());
                int celular = Integer.parseInt(s[5].trim());
                int estado = Integer.parseInt(s[6].trim());
                pacientes.add(new Paciente(codPaciente, nombres, apellidos, dni, edad, celular, estado));
            }
        } catch (Exception e) {
            System.out.println("No se pudo cargar archivo de pacientes (puede no existir todavía).");
        }
    }
}
