package gui;

import arreglos.*;

import clases.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class DlgRegAlta extends JDialog implements ItemListener, ActionListener, MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNumAlta, lblNumInternamiento, lblPaciente, lblCodTratamiento, lblTratamiento, lblActivo;
    private JComboBox<Integer> cboNumAlta, cboNumInternamiento, cboCodTratamiento;
    private JTextField txtPaciente, txtTratamiento, txtActivo;
    private JButton btnAceptar, btnCancelar, btnAdicionar, btnModificar, btnEliminar;
    private JTable tblAltas;
    private DefaultTableModel modelo;
    private JScrollPane scrollPane;

    
    ArregloPacientes ap = new ArregloPacientes();
    ArregloTratamientos at = new ArregloTratamientos();
    ArregloInternamientos ai = new ArregloInternamientos();
    ArregloAltas aa = new ArregloAltas();

    public static void main(String[] args) {
        try {
            DlgRegAlta dialog = new DlgRegAlta();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DlgRegAlta() {
    	setFont(new Font("Book Antiqua", Font.PLAIN, 12));
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DlgRegAlta.class.getResource("/recursos/icons8-out-patient-department-48.png")));
    	getContentPane().setBackground(new Color(204, 208, 227));
        setTitle("Registro de Altas Medicas");
        setBounds(100, 100, 863, 540);
        getContentPane().setLayout(null);

        lblNumAlta = new JLabel("No de Alta:");
        lblNumAlta.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNumAlta.setBounds(10, 22, 120, 15);
        getContentPane().add(lblNumAlta);

        lblNumInternamiento = new JLabel("No de Internamiento:");
        lblNumInternamiento.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNumInternamiento.setBounds(10, 57, 160, 15);
        getContentPane().add(lblNumInternamiento);

        lblPaciente = new JLabel("Paciente:");
        lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblPaciente.setBounds(10, 92, 80, 15);
        getContentPane().add(lblPaciente);

        lblCodTratamiento = new JLabel("Codigo del Tratamiento:");
        lblCodTratamiento.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblCodTratamiento.setBounds(10, 127, 180, 15);
        getContentPane().add(lblCodTratamiento);

        lblTratamiento = new JLabel("Tratamiento:");
        lblTratamiento.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTratamiento.setBounds(10, 162, 100, 15);
        getContentPane().add(lblTratamiento);

        lblActivo = new JLabel("Internamiento Activo:");
        lblActivo.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblActivo.setBounds(10, 193, 160, 15);
        getContentPane().add(lblActivo);

        
        cboNumAlta = new JComboBox<>();
        cboNumAlta.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
        cboNumAlta.setForeground(new Color(255, 255, 255));
        cboNumAlta.setBackground(new Color(84, 163, 188));
        cboNumAlta.setEditable(true);
        cboNumAlta.addItemListener(this);
        cboNumAlta.setEnabled(false);
        cboNumAlta.setBounds(190, 22, 152, 21);
        getContentPane().add(cboNumAlta);

        cboNumInternamiento = new JComboBox<>();
        cboNumInternamiento.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
        cboNumInternamiento.setForeground(new Color(255, 255, 255));
        cboNumInternamiento.setBackground(new Color(84, 163, 188));
        cboNumInternamiento.setEditable(true);
        cboNumInternamiento.addItemListener(this);
        cboNumInternamiento.setEnabled(false);
        cboNumInternamiento.setBounds(190, 55, 152, 21);
        getContentPane().add(cboNumInternamiento);

        txtPaciente = new JTextField();
        txtPaciente.setEditable(false);
        txtPaciente.setBounds(90, 91, 250, 21);
        getContentPane().add(txtPaciente);

        cboCodTratamiento = new JComboBox<>();
        cboCodTratamiento.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
        cboCodTratamiento.setForeground(new Color(255, 255, 255));
        cboCodTratamiento.setBackground(new Color(84, 163, 188));
        cboCodTratamiento.setEditable(true);
        cboCodTratamiento.addItemListener(this);
        cboCodTratamiento.setEnabled(false);
        cboCodTratamiento.setBounds(190, 125, 152, 21);
        getContentPane().add(cboCodTratamiento);

        txtTratamiento = new JTextField();
        txtTratamiento.setEditable(false);
        txtTratamiento.setBounds(100, 161, 240, 21);
        getContentPane().add(txtTratamiento);

        txtActivo = new JTextField();
        txtActivo.setEditable(false);
        txtActivo.setBounds(160, 192, 80, 21);
        getContentPane().add(txtActivo);

        
        btnAceptar = crearBoton("Aceptar", 10, 227);
        btnCancelar = crearBoton("Cancelar", 166, 227);
        btnAdicionar = crearBoton("Adicionar", 720, 22);
        btnModificar = crearBoton("Modificar", 720, 82);
        btnEliminar = crearBoton("Eliminar", 720, 142);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 265, 827, 200);
        getContentPane().add(scrollPane);

        tblAltas = new JTable();
        tblAltas.addMouseListener(this);
        scrollPane.setViewportView(tblAltas);

        modelo = new DefaultTableModel();
        modelo.addColumn("NO ALTA");
        modelo.addColumn("NO INTERNAMIENTO");
        modelo.addColumn("PACIENTE");
        modelo.addColumn("TRATAMIENTO");
        modelo.addColumn("FECHA");
        modelo.addColumn("HORA");
        tblAltas.setModel(modelo);

        ajustarColumnas();
        listar();
        deshabilitarTodo();
    }

    private JButton crearBoton(String texto, int x, int y) {
        JButton btn = new JButton(texto);
        btn.setForeground(new Color(255, 255, 255));
        btn.setBackground(new Color(84, 163, 188));
        btn.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
        btn.setBounds(x, y, 120, 30);
        btn.addActionListener(this);
        getContentPane().add(btn);
        return btn;
    }

   
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cboNumInternamiento) itemStateChangedCboNumInternamiento(e);
        if (e.getSource() == cboCodTratamiento) itemStateChangedCboCodTratamiento(e);
    }

    protected void itemStateChangedCboNumInternamiento(ItemEvent e) {
        try {
            int num = leerNumInternamiento();
            Internamiento inter = ai.buscar(num);
            Paciente pac = ap.buscar(inter.getCodPaciente());
            txtPaciente.setText(pac.getNombres() + " " + pac.getApellidos());
            cboCodTratamiento.setSelectedItem(inter.getCodTratamiento());
            txtActivo.setText(pac.getEstado() == 1 ? "Si" : "No");
        } catch (Exception ex) {}
    }

    protected void itemStateChangedCboCodTratamiento(ItemEvent e) {
        try {
            int cod = leerCodTratamiento();
            Tratamiento trat = at.buscar(cod);
            txtTratamiento.setText(trat.getNombreTratamiento());
        } catch (Exception ex) {}
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionar) actionAdicionar();
        if (e.getSource() == btnAceptar) actionAceptar();
        if (e.getSource() == btnCancelar) deshabilitarTodo();
        if (e.getSource() == btnEliminar) actionEliminar();
        if (e.getSource() == btnModificar) actionModificar();
    }

    
    void actionAdicionar() {
        limpiar();
        habilitar(false, true, true, false, false, false, true, true, false, false, false);
        cboNumAlta.addItem(aa.codigoCorrelativo());
        cboNumAlta.setSelectedIndex(aa.tamanio());
        cboNumInternamiento.requestFocus();
    }

    void actionAceptar() {
        try {
            int numAlta = leerNumAlta();
            int numInternamiento = leerNumInternamiento();

            Internamiento inter = ai.buscar(numInternamiento);
            Paciente pac = ap.buscar(inter.getCodPaciente());

            if (pac.getEstado() == 1) {
                Alta nueva = new Alta(numAlta, numInternamiento, Calendario.fechaActual(), Calendario.horaActual());
                pac.setEstado(2); // Alta medica
                ap.actualizarArchivo();
                aa.adicionar(nueva);
                listar();
                mensaje("Alta medica registrada exitosamente.");
                deshabilitarTodo();
            } else {
                error("El paciente ya tiene el alta medica registrada.", cboNumInternamiento);
            }
        } catch (Exception ex) {
            error("Complete todos los campos correctamente.", cboNumInternamiento);
        }
    }

    void actionEliminar() {
        try {
            int numAlta = leerNumAlta();
            Alta alta = aa.buscar(numAlta);
            if (confirmar() == 0) {
                aa.eliminar(alta);
                Paciente p = ap.buscar(ai.buscar(alta.getNumInternamiento()).getCodPaciente());
                p.setEstado(1); 
                ap.actualizarArchivo();
                listar();
                mensaje("Alta medica eliminada exitosamente.");
                deshabilitarTodo();
            }
        } catch (Exception ex) {
            error("Seleccione un número de alta valido.", cboNumAlta);
        }
    }

    void actionModificar() {
        habilitar(true, true, false, true, false, false, true, true, false, false, false);
    }

   
    void listar() {
        modelo.setRowCount(0);
        for (int i = 0; i < aa.tamanio(); i++) {
            Alta a = aa.obtener(i);
            Internamiento inter = ai.buscar(a.getNumInternamiento());
            Paciente pac = ap.buscar(inter.getCodPaciente());
            Tratamiento trat = at.buscar(inter.getCodTratamiento());

            Object[] fila = {
                    a.getNumAlta(),
                    a.getNumInternamiento(),
                    pac.getNombres() + " " + pac.getApellidos(),
                    trat.getNombreTratamiento(),
                    a.getFecha(),
                    a.getHora()
            };
            modelo.addRow(fila);
        }
    }

    void ajustarColumnas() {
        TableColumnModel cm = tblAltas.getColumnModel();
        for (int i = 0; i < cm.getColumnCount(); i++) {
            cm.getColumn(i).setPreferredWidth(130);
        }
    }

    void listarCombos() {
        cboNumAlta.removeAllItems();
        for (int i = 0; i < aa.tamanio(); i++)
            cboNumAlta.addItem(aa.obtener(i).getNumAlta());

        cboNumInternamiento.removeAllItems();
        for (int i = 0; i < ai.tamanio(); i++)
            cboNumInternamiento.addItem(ai.obtener(i).getNumInternamiento());

        cboCodTratamiento.removeAllItems();
        for (int i = 0; i < at.tamanio(); i++)
            cboCodTratamiento.addItem(at.obtener(i).getCodTratamiento());
    }

    void deshabilitarTodo() {
        listarCombos();
        habilitar(false, false, false, false, false, false, false, false, true, true, true);
        limpiar();
    }

    void habilitar(boolean numAlta, boolean numIntern, boolean paciente, boolean codTrat, boolean trat,
                   boolean activo, boolean aceptar, boolean cancelar, boolean adicionar,
                   boolean modificar, boolean eliminar) {
        cboNumAlta.setEnabled(numAlta);
        cboNumInternamiento.setEnabled(numIntern);
        txtPaciente.setEditable(paciente);
        cboCodTratamiento.setEnabled(codTrat);
        txtTratamiento.setEditable(trat);
        txtActivo.setEditable(activo);
        btnCancelar.setEnabled(cancelar);
        btnAdicionar.setEnabled(adicionar);
        btnModificar.setEnabled(modificar);
        btnEliminar.setEnabled(eliminar);
    }

    void limpiar() {
        cboNumAlta.setSelectedIndex(-1);
        cboNumInternamiento.setSelectedIndex(-1);
        cboCodTratamiento.setSelectedIndex(-1);
        txtPaciente.setText("");
        txtTratamiento.setText("");
        txtActivo.setText("");
    }

    int leerNumAlta() { return Integer.parseInt(cboNumAlta.getSelectedItem().toString()); }
    int leerNumInternamiento() { return Integer.parseInt(cboNumInternamiento.getSelectedItem().toString()); }
    int leerCodTratamiento() { return Integer.parseInt(cboCodTratamiento.getSelectedItem().toString()); }

    void mensaje(String s) { JOptionPane.showMessageDialog(this, s); }
    void error(String s, JComboBox<?> cbo) {
        JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
        cbo.requestFocus();
    }

    int confirmar() {
        return JOptionPane.showOptionDialog(this,
                "¿Esta seguro de eliminar esta alta medica?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Si", "No"}, null);
    }

   
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tblAltas && btnAdicionar.isEnabled()) {
            try {
                Alta alta = aa.obtener(tblAltas.getSelectedRow());
                cboNumAlta.setSelectedIndex(tblAltas.getSelectedRow());
                cboNumInternamiento.setSelectedItem(alta.getNumInternamiento());
            } catch (Exception ex) {}
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
