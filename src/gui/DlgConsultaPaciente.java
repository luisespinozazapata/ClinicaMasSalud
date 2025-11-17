package gui;

import arreglos.*;
import clases.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DlgConsultaPaciente extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnConsultar;
    private JTextArea txtResultado;
    private JComboBox<Integer> cboCodigo;

    
    ArregloPacientes ap = new ArregloPacientes();
    ArregloTratamientos at = new ArregloTratamientos();
    ArregloInternamientos ai = new ArregloInternamientos();

    public static void main(String[] args) {
        try {
            DlgConsultaPaciente dialog = new DlgConsultaPaciente();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgConsultaPaciente() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DlgConsultaPaciente.class.getResource("/recursos/icons8-patient-64.png")));
    	getContentPane().setBackground(new Color(204, 208, 227));
        setTitle("Consulta de Pacientes");
        setBounds(100, 100, 519, 540);
        getContentPane().setLayout(null);

        JLabel lblCodigo = new JLabel("Codigo de paciente:");
        lblCodigo.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblCodigo.setBounds(10, 21, 142, 14);
        getContentPane().add(lblCodigo);

        cboCodigo = new JComboBox<>();
        cboCodigo.setForeground(Color.BLACK);
        cboCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
        cboCodigo.setBackground(Color.WHITE);
        cboCodigo.setEditable(true);
        cboCodigo.setBounds(162, 16, 155, 23);
        getContentPane().add(cboCodigo);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setForeground(new Color(255, 255, 255));
        btnConsultar.setBackground(new Color(84, 163, 188));
        btnConsultar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        btnConsultar.addActionListener(this);
        btnConsultar.setBounds(365, 13, 130, 31);
        getContentPane().add(btnConsultar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 55, 483, 435);
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
            mensaje("Seleccione un codigo de paciente valido");
        }
    }

    
    void listar() {
        Paciente p = ap.buscar(leerCodigo());
        if (p == null) {
            mensaje("El paciente no existe.");
            return;
        }

        imprimir("CODIGO           : " + p.getCodPaciente());
        imprimir("NOMBRES          : " + p.getNombres());
        imprimir("APELLIDOS        : " + p.getApellidos());
        imprimir("DNI              : " + p.getDni());
        imprimir("EDAD             : " + p.getEdad());
        imprimir("CELULAR          : " + p.getCelular());
        imprimir("ESTADO           : " + nombreEstado(p.getEstado()));
        imprimir();

    
        if (p.getEstado() == 1) {
            Internamiento i = ai.buscarPorPaciente(leerCodigo());
            if (i != null) {
                Tratamiento t = at.buscar(i.getCodTratamiento());
                if (t != null) {
                    imprimir("TRATAMIENTO      : " + t.getNombreTratamiento());
                    imprimir("DURACIÓN (días)  : " + t.getDuracionDias());
                    imprimir("SESIONES         : " + t.getSesiones());
                    imprimir("COSTO (S/.)      : " + t.getCosto());
                }
            }
        }
    }

    void listarCboCodigo() {
        cboCodigo.removeAllItems();
        for (int i = 0; i < ap.tamanio(); i++) {
            cboCodigo.addItem(ap.obtener(i).getCodPaciente());
        }
        cboCodigo.setSelectedIndex(-1);
    }

    String nombreEstado(int i) {
        switch (i) {
            case 0: return "REGISTRADO";
            case 1: return "INTERNADO";
            case 2: return "ALTA MEDICA";
            default: return "DESCONOCIDO";
        }
    }

    void imprimir(String s) {
        txtResultado.append(s + "\n");
    }

    void imprimir() {
        txtResultado.append("\n");
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public int leerCodigo() {
        return Integer.parseInt(cboCodigo.getSelectedItem().toString());
    }
}
