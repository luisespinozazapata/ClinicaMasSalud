package gui;

import arreglos.*;
import clases.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DlgConsultaInternamiento extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCodigo;
    private JComboBox<Integer> cboCodigo;
    private JButton btnConsultar;
    private JTextArea txtResultado;

    
    ArregloPacientes ap = new ArregloPacientes();
    ArregloTratamientos at = new ArregloTratamientos();
    ArregloInternamientos ai = new ArregloInternamientos();


    public static void main(String[] args) {
        try {
            DlgConsultaInternamiento dialog = new DlgConsultaInternamiento();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public DlgConsultaInternamiento() {
    	getContentPane().setBackground(new Color(204, 208, 227));
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DlgConsultaInternamiento.class.getResource("/recursos/icons8-life-cycle-48.png")));
        setTitle("Consulta de Internamientos");
        setBounds(100, 100, 519, 540);
        getContentPane().setLayout(null);

        lblCodigo = new JLabel("Número de internamiento:");
        lblCodigo.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblCodigo.setBounds(10, 16, 170, 15);
        getContentPane().add(lblCodigo);

        cboCodigo = new JComboBox<>();
        cboCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
        cboCodigo.setForeground(Color.BLACK);
        cboCodigo.setBounds(190, 13, 130, 23);
        getContentPane().add(cboCodigo);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setForeground(new Color(255, 255, 255));
        btnConsultar.setBackground(new Color(84, 163, 188));
        btnConsultar.addActionListener(this);
        btnConsultar.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
        btnConsultar.setBounds(365, 13, 130, 31);
        getContentPane().add(btnConsultar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 55, 485, 435);
        getContentPane().add(scrollPane);

        txtResultado = new JTextArea();
        txtResultado.setFont(new Font("Monospaced", Font.BOLD, 13));
        scrollPane.setViewportView(txtResultado);

        listarCboCodigo();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConsultar) {
            actionPerformedBtnConsultar(e);
        }
    }

    protected void actionPerformedBtnConsultar(ActionEvent e) {
        try {
            txtResultado.setText("");
            listar();
            cboCodigo.requestFocus();
        } catch (Exception error) {
            mensaje("Seleccione un número de internamiento valido.");
        }
    }

    void imprimir() {
        imprimir("");
    }

  
    void listar() {
        Internamiento m = ai.buscar(leerCodigo());
        if (m == null) {
            mensaje("El internamiento no existe.");
            return;
        }

        Paciente p = ap.buscar(m.getCodPaciente());
        Tratamiento t = at.buscar(m.getCodTratamiento());

        if (p == null || t == null) {
            mensaje("Datos incompletos: el paciente o tratamiento no existen.");
            return;
        }

        imprimir("==== INFORMACIÓN DEL INTERNAMIENTO ====");
        imprimir("CODIGO INTERNAMIENTO : " + m.getNumInternamiento());
        imprimir("FECHA                : " + m.getFecha());
        imprimir("HORA                 : " + m.getHora());
        imprimir("_______________________________________");

        switch (p.getEstado()) {
            case 0 -> imprimir("ESTADO PACIENTE     \t: REGISTRADO");
            case 1 -> imprimir("ESTADO PACIENTE     \t: INTERNADO");
            default -> imprimir("ESTADO PACIENTE    \t: ALTA MEDICA");
        }

        imprimir();
        imprimir("------- DATOS DEL PACIENTE --------");
        imprimir("CODIGO     \t: " + p.getCodPaciente());
        imprimir("NOMBRES    \t: " + p.getNombres());
        imprimir("APELLIDOS  \t: " + p.getApellidos());
        imprimir("DNI        \t: " + p.getDni());
        imprimir("EDAD       \t: " + p.getEdad());
        imprimir("CELULAR    \t: " + p.getCelular());
        imprimir();

        imprimir("------- DETALLES DEL TRATAMIENTO ------");
        imprimir("CODIGO TRATAMIENTO : " + t.getCodTratamiento());
        imprimir("NOMBRE             : " + t.getNombreTratamiento());
        imprimir("DURACIÓN (días)    : " + t.getDuracionDias());
        imprimir("SESIONES           : " + t.getSesiones());
        imprimir("COSTO POR SESIÓN (S/.) : " + t.getCosto());
    }

    void listarCboCodigo() {
        cboCodigo.removeAllItems();
        for (int i = 0; i < ai.tamanio(); i++) {
            cboCodigo.addItem(ai.obtener(i).getNumInternamiento());
        }
        cboCodigo.setSelectedIndex(-1);
    }

    void imprimir(String s) {
        txtResultado.append(s + "\n");
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public int leerCodigo() {
        return Integer.parseInt(cboCodigo.getSelectedItem().toString());
    }
}
