package gui;

import arreglos.*;
import clases.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DlgConsultaTratamiento extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCodigo;
    private JComboBox<Integer> cboCodigo;
    private JButton btnConsultar;
    private JTextArea txtResultado;

   
    ArregloTratamientos at = new ArregloTratamientos();
    ArregloInternamientos ai = new ArregloInternamientos();

    public static void main(String[] args) {
        try {
            DlgConsultaTratamiento dialog = new DlgConsultaTratamiento();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgConsultaTratamiento() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DlgConsultaTratamiento.class.getResource("/recursos/icons8-patient-64 (1).png")));
    	getContentPane().setBackground(new Color(204, 208, 227));
        setTitle("Consulta de Tratamientos");
        setBounds(100, 100, 519, 540);
        getContentPane().setLayout(null);

        lblCodigo = new JLabel("Codigo de tratamiento:");
        lblCodigo.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
        lblCodigo.setBounds(10, 21, 161, 20);
        getContentPane().add(lblCodigo);

        cboCodigo = new JComboBox<>();
        cboCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
        cboCodigo.setForeground(Color.BLACK);
        cboCodigo.setBackground(Color.WHITE);
        cboCodigo.setEditable(true);
        cboCodigo.setBounds(181, 18, 155, 23);
        getContentPane().add(cboCodigo);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setForeground(new Color(255, 255, 255));
        btnConsultar.setBackground(new Color(84, 163, 188));
        btnConsultar.addActionListener(this);
        btnConsultar.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
        btnConsultar.setBounds(365, 13, 130, 31);
        getContentPane().add(btnConsultar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 55, 484, 435);
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
            mensaje("Seleccione un codigo de tratamiento valido");
        }
    }

    void imprimir() {
        imprimir("");
    }

    void listar() {
        Tratamiento t = at.buscar(leerCodigo());
        if (t == null) {
            mensaje("El tratamiento no existe.");
            return;
        }

        imprimir("CODIGO          : " + t.getCodTratamiento());
        imprimir("NOMBRE          : " + t.getNombreTratamiento());
        imprimir("DURACIÓN (días) : " + t.getDuracionDias());
        imprimir("SESIONES        : " + t.getSesiones());
        imprimir("COSTO (S/.)     : " + t.getCosto());
        imprimir();

        int cantidad = 0;
        for (int i = 0; i < ai.tamanio(); i++) {
            if (ai.obtener(i).getCodTratamiento() == t.getCodTratamiento()) {
                cantidad++;
            }
        }

        if (cantidad == 0)
            imprimir("No hay pacientes internados con este tratamiento.");
        else if (cantidad == 1)
            imprimir("Hay 1 paciente internado con este tratamiento.");
        else
            imprimir("Hay " + cantidad + " pacientes internados con este tratamiento.");
    }

    void listarCboCodigo() {
        cboCodigo.removeAllItems();
        for (int i = 0; i < at.tamanio(); i++) {
            cboCodigo.addItem(at.obtener(i).getCodTratamiento());
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
