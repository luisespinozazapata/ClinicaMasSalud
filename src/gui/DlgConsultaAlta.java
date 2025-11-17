package gui;

import arreglos.*;
import clases.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DlgConsultaAlta extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCodigoAlta;
    private JComboBox<Integer> cboCodigo;
    private JButton btnConsultar;
    private JTextArea txtResultado;

   
    ArregloPacientes ap = new ArregloPacientes();
    ArregloTratamientos at = new ArregloTratamientos();
    ArregloInternamientos ai = new ArregloInternamientos();
    ArregloAltas aa = new ArregloAltas();

  
    public static void main(String[] args) {
        try {
            DlgConsultaAlta dialog = new DlgConsultaAlta();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgConsultaAlta() {
    	setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
    	setModalityType(ModalityType.DOCUMENT_MODAL);
    	setAlwaysOnTop(true);
    	setForeground(new Color(255, 255, 255));
    	getContentPane().setBackground(new Color(204, 208, 227));
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DlgConsultaAlta.class.getResource("/recursos/icons8-happy-48.png")));
    	setModal(true);
    	setBackground(new Color(153, 255, 255));
        setTitle("Consulta de Altas Medicas");
        setBounds(100, 100, 519, 540);
        getContentPane().setLayout(null);

        lblCodigoAlta = new JLabel("Numero de alta medica:");
        lblCodigoAlta.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblCodigoAlta.setBounds(10, 16, 155, 15);
        getContentPane().add(lblCodigoAlta);

        cboCodigo = new JComboBox<>();
        cboCodigo.setForeground(Color.BLACK);
        cboCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
        cboCodigo.setEditable(true);
        cboCodigo.setBackground(Color.WHITE);
        cboCodigo.setBounds(175, 13, 138, 23);
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
        txtResultado.setBackground(new Color(255, 255, 255));
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
            mensaje("Seleccione un número de alta valido.");
        }
    }

    void imprimir() {
        imprimir("");
    }

    
    void listar() {
        Alta alta = aa.buscar(leerCodigo());
        if (alta == null) {
            mensaje("No se encontró la alta medica seleccionada.");
            return;
        }

        Internamiento internamiento = ai.buscar(alta.getNumInternamiento());
        if (internamiento == null) {
            mensaje("El internamiento asociado no existe.");
            return;
        }

        Paciente paciente = ap.buscar(internamiento.getCodPaciente());
        Tratamiento tratamiento = at.buscar(internamiento.getCodTratamiento());

        imprimir("===== INFORMACIÓN DEL ALTA MEDICA =====");
        imprimir("CODIGO ALTA       \t: " + alta.getNumAlta());
        imprimir("No INTERNAMIENTO  \t: " + internamiento.getNumInternamiento());
        imprimir("FECHA DE ALTA     \t: " + alta.getFecha());
        imprimir("HORA              \t: " + alta.getHora());
        imprimir("_______________________________________");

        imprimir("---- DATOS DEL PACIENTE ----");
        imprimir("CODIGO PACIENTE    \t: " + paciente.getCodPaciente());
        imprimir("NOMBRES            \t: " + paciente.getNombres());
        imprimir("APELLIDOS          \t: " + paciente.getApellidos());
        imprimir("DNI                \t: " + paciente.getDni());
        imprimir("EDAD               \t: " + paciente.getEdad());
        imprimir("CELULAR            \t: " + paciente.getCelular());
        imprimir("ESTADO             \t: ALTA MEDICA");
        imprimir();

        imprimir("---- TRATAMIENTO REALIZADO ----");
        imprimir("CODIGO TRATAMIENTO \t: " + tratamiento.getCodTratamiento());
        imprimir("NOMBRE TRATAMIENTO \t: " + tratamiento.getNombreTratamiento());
        imprimir("DURACIÓN (días)    \t: " + tratamiento.getDuracionDias());
        imprimir("SESIONES REALIZADAS\t: " + tratamiento.getSesiones());
        imprimir("COSTO POR SESIÓN (S/.)  \t: " + tratamiento.getCosto());
        imprimir("_______________________________________");
        imprimir("Paciente dado de alta correctamente.");
    }

    void listarCboCodigo() {
        cboCodigo.removeAllItems();
        for (int i = 0; i < aa.tamanio(); i++) {
            cboCodigo.addItem(aa.obtener(i).getNumAlta());
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
