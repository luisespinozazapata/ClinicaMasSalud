package gui;

import arreglos.*;

import clases.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class DlgRegInternamiento extends JDialog implements ItemListener, ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNumInternamiento;
	private JLabel lblCodigoPaciente;
	private JLabel lblEstado;
	private JLabel lblCodigoTratamiento;
	private JComboBox<Integer> cboNumInternamiento;
	private JComboBox<Integer> cboCodigoPaciente;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTextField txtPaciente;
	private JComboBox<Integer> cboCodigoTratamiento;
	private JTextField txtTratamiento;
	private JTable tblInternamiento;
	private DefaultTableModel modelo;
	private JTextField txtEstadoPaciente;


	public static void main(String[] args) {
		try {
			DlgRegInternamiento dialog = new DlgRegInternamiento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public DlgRegInternamiento() {
		getContentPane().setBackground(new Color(204, 208, 227));
		setTitle("REGISTRO DE INTERNAMIENTOS");
		setBounds(100, 100, 850, 540);
		getContentPane().setLayout(null);
		
		lblNumInternamiento = new JLabel("No de Internamiento:");
		lblNumInternamiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumInternamiento.setBounds(10, 22, 160, 15);
		getContentPane().add(lblNumInternamiento);
		
		lblCodigoPaciente = new JLabel("Codigo del Paciente:");
		lblCodigoPaciente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigoPaciente.setBounds(10, 57, 160, 15);
		getContentPane().add(lblCodigoPaciente);
		
		lblEstado = new JLabel("Estado Actual:");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setBounds(10, 122, 136, 15);
		getContentPane().add(lblEstado);
		
		lblCodigoTratamiento = new JLabel("Codigo del Tratamiento:");
		lblCodigoTratamiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCodigoTratamiento.setBounds(10, 157, 180, 15);
		getContentPane().add(lblCodigoTratamiento);
		
		cboNumInternamiento = new JComboBox<Integer>();
		cboNumInternamiento.setEditable(true);
		cboNumInternamiento.setForeground(new Color(255, 255, 255));
		cboNumInternamiento.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		cboNumInternamiento.setBackground(new Color(84, 163, 188));
		cboNumInternamiento.addItemListener(this);
		cboNumInternamiento.setEnabled(false);
		cboNumInternamiento.setBounds(180, 22, 152, 21);
		getContentPane().add(cboNumInternamiento);
		
		cboCodigoPaciente = new JComboBox<Integer>();
		cboCodigoPaciente.setEditable(true);
		cboCodigoPaciente.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		cboCodigoPaciente.setBackground(new Color(84, 163, 188));
		cboCodigoPaciente.addItemListener(this);
		cboCodigoPaciente.setEnabled(false);
		cboCodigoPaciente.setBounds(180, 55, 152, 21);
		getContentPane().add(cboCodigoPaciente);
		
		txtPaciente = new JTextField();
		txtPaciente.setEditable(false);
		txtPaciente.setColumns(10);
		txtPaciente.setBounds(10, 91, 322, 19);
		getContentPane().add(txtPaciente);
		
		txtEstadoPaciente = new JTextField();
		txtEstadoPaciente.setEditable(false);
		txtEstadoPaciente.setColumns(10);
		txtEstadoPaciente.setBounds(180, 121, 152, 19);
		getContentPane().add(txtEstadoPaciente);
		
		cboCodigoTratamiento = new JComboBox<Integer>();
		cboCodigoTratamiento.setEditable(true);
		cboCodigoTratamiento.setForeground(new Color(255, 255, 255));
		cboCodigoTratamiento.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		cboCodigoTratamiento.setBackground(new Color(84, 163, 188));
		cboCodigoTratamiento.addItemListener(this);
		cboCodigoTratamiento.setEnabled(false);
		cboCodigoTratamiento.setBounds(180, 155, 152, 21);
		getContentPane().add(cboCodigoTratamiento);
		
		txtTratamiento = new JTextField();
		txtTratamiento.setEditable(false);
		txtTratamiento.setColumns(10);
		txtTratamiento.setBounds(10, 191, 322, 19);
		getContentPane().add(txtTratamiento);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setBackground(new Color(84, 163, 188));
		btnAceptar.addActionListener(this);
		btnAceptar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(10, 227, 142, 27);
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(84, 163, 188));
		btnCancelar.addActionListener(this);
		btnCancelar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(166, 227, 142, 27);
		getContentPane().add(btnCancelar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setBackground(new Color(84, 163, 188));
		btnAdicionar.addActionListener(this);
		btnAdicionar.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnAdicionar.setEnabled(true);
		btnAdicionar.setBounds(669, 39, 136, 33);
		getContentPane().add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(84, 163, 188));
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnModificar.setEnabled(true);
		btnModificar.setBounds(669, 99, 136, 33);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(84, 163, 188));
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnEliminar.setEnabled(true);
		btnEliminar.setBounds(669, 164, 136, 33);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 264, 816, 203);
		getContentPane().add(scrollPane);
		
		tblInternamiento = new JTable();
		tblInternamiento.addMouseListener(this);
		tblInternamiento.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblInternamiento);
		modelo = new DefaultTableModel();
		modelo.addColumn("NO INTER.");
		modelo.addColumn("COD. PAC.");
		modelo.addColumn("NOMBRES");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("COD. TRAT.");
		modelo.addColumn("TRATAMIENTO");
		modelo.addColumn("FECHA");
		modelo.addColumn("HORA");
		modelo.addColumn("ACTIVO");
		tblInternamiento.setModel(modelo);
		
		ajustarColumnas();
		listar();
		deshabilitarTodo();
	}
	
	
	ArregloPacientes aa = new ArregloPacientes();
	ArregloTratamientos ac = new ArregloTratamientos();
	ArregloInternamientos am = new ArregloInternamientos();
	
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboCodigoTratamiento) itemStateChangedCboCodigoTratamiento(e);
		if (e.getSource() == cboCodigoPaciente) itemStateChangedCboCodigoPaciente(e);
		if (e.getSource() == cboNumInternamiento) itemStateChangedCboNumInternamiento(e);
	}
	protected void itemStateChangedCboNumInternamiento(ItemEvent e) {
		try {
			int numInt = leerNumeroInternamiento();
			Internamiento buscado = am.buscar(numInt);
			cboCodigoPaciente.setSelectedItem(buscado.getCodPaciente());
			cboCodigoTratamiento.setSelectedItem(buscado.getCodTratamiento());
			tblInternamiento.setRowSelectionInterval(cboNumInternamiento.getSelectedIndex(), cboNumInternamiento.getSelectedIndex());
		} catch (Exception error) {}
	}
	protected void itemStateChangedCboCodigoPaciente(ItemEvent e) {
		try {
			int codPac = leerCodigoPaciente();
			Paciente buscado = aa.buscar(codPac);
			txtPaciente.setText(buscado.getNombres() + " " + buscado.getApellidos());
			txtEstadoPaciente.setText(nombreEstado(buscado.getEstado()));
		} catch (Exception error) {}
	}
	protected void itemStateChangedCboCodigoTratamiento(ItemEvent e) {
		try {
			int codTrat = leerCodigoTratamiento();
			Tratamiento buscado = ac.buscar(codTrat);
			txtTratamiento.setText(buscado.getNombreTratamiento());
		} catch (Exception error) {}
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) actionPerformedBtnEliminar(e);
		if (e.getSource() == btnModificar) actionPerformedBtnModificar(e);
		if (e.getSource() == btnAdicionar) actionPerformedBtnAdicionar(e);
		if (e.getSource() == btnCancelar) actionPerformedBtnCancelar(e);
		if (e.getSource() == btnAceptar) actionPerformedBtnAceptar(e);
	}
	
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		try {
			int numInt = leerNumeroInternamiento();
			int codPac = leerCodigoPaciente();
			int codTrat = leerCodigoTratamiento();

			if (!btnAdicionar.isEnabled()) {
				if (aa.buscar(codPac).getEstado() == 0) {
					Internamiento nuevo = new Internamiento(numInt, codPac, codTrat, Calendario.fechaActual(), Calendario.horaActual());
					aa.buscar(codPac).setEstado(1); 
					aa.actualizarArchivo();
					am.adicionar(nuevo);
					listar();
					mensaje("Nuevo internamiento registrado exitosamente");
					deshabilitarTodo();
				} else {
					error("El paciente ya se encuentra internado", cboCodigoPaciente);
				}
			} else if (!btnModificar.isEnabled()) {
				Internamiento buscado = am.buscar(numInt);
				buscado.setCodTratamiento(codTrat);
				am.actualizarArchivo();
				listar();
				mensaje("Internamiento modificado exitosamente");
				deshabilitarTodo();
			}
		} catch (Exception error) {
			error("Complete correctamente todos los campos", cboNumInternamiento);
		}
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		deshabilitarTodo();
	}
	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		limpiar();
		habilitar(false, true, false, false, true, false, true, true, false, true, false);
		cboNumInternamiento.addItem(am.codigoCorrelativo());
		cboNumInternamiento.setSelectedIndex(am.tamanio());
		cboCodigoPaciente.requestFocus();
	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		if (!btnAdicionar.isEnabled()) deshabilitarTodo();
		habilitar(true, false, false, false, true, false, true, true, true, false, false);
		cboNumInternamiento.requestFocus();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		try {
			int numInt = leerNumeroInternamiento();
			int codPac = leerCodigoPaciente();
			Internamiento buscado = am.buscar(numInt);
			if (aa.buscar(codPac).getEstado() == 1) {
				if (confirmar() == 0) {
					am.eliminar(buscado);
					aa.buscar(codPac).setEstado(0);
					aa.actualizarArchivo();
					listar();
					mensaje("Internamiento eliminado exitosamente");
					deshabilitarTodo();
				}
			} else {
				error("No es posible eliminar, el paciente ya fue dado de alta", cboNumInternamiento);
			}
		} catch (Exception error) {
			error("Seleccione un número de internamiento", cboNumInternamiento);
		}
	}
	
	
	void habilitar(boolean numInt, boolean codPac, boolean pac, boolean estPac, boolean codTrat,
			boolean trat, boolean aceptar, boolean cancelar, boolean adicionar, boolean modificar, boolean eliminar) {
		cboNumInternamiento.setEnabled(numInt);
		cboCodigoPaciente.setEnabled(codPac);
		cboCodigoTratamiento.setEnabled(codTrat);
		btnAceptar.setEnabled(aceptar);
		btnCancelar.setEnabled(cancelar);
		btnAdicionar.setEnabled(adicionar);
		btnModificar.setEnabled(modificar);
		btnEliminar.setEnabled(eliminar);
	}
	void deshabilitarTodo() {
		listarCboNumInternamiento();
		listarCboCodPaciente();
		listarCboCodTratamiento();
		habilitar(false, false, false, false, false, false, false, false, true, true, true);
		limpiar();
	}
	void limpiar() {
		cboNumInternamiento.setSelectedIndex(-1);
		cboCodigoPaciente.setSelectedIndex(-1);
		txtPaciente.setText("");
		txtEstadoPaciente.setText("");
		cboCodigoTratamiento.setSelectedIndex(-1);
		txtTratamiento.setText("");
	}
	void listarCboNumInternamiento() {
		cboNumInternamiento.removeAllItems();
		for (int i = 0; i < am.tamanio(); i++) {
			cboNumInternamiento.addItem(am.obtener(i).getNumInternamiento());
		}
	}
	void listarCboCodPaciente() {
		cboCodigoPaciente.removeAllItems();
		for (int i = 0; i < aa.tamanio(); i++) {
			cboCodigoPaciente.addItem(aa.obtener(i).getCodPaciente());
		}
	}
	void listarCboCodTratamiento() {
		cboCodigoTratamiento.removeAllItems();
		for (int i = 0; i < ac.tamanio(); i++) {
			cboCodigoTratamiento.addItem(ac.obtener(i).getCodTratamiento());
		}
	}
	void ajustarColumnas() {
		TableColumnModel modeloColuma = tblInternamiento.getColumnModel();
		for (int i = 0; i < modeloColuma.getColumnCount(); i++)
			modeloColuma.getColumn(i).setPreferredWidth(scrollPane.getWidth() / modeloColuma.getColumnCount());
	}
	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < am.tamanio(); i++) {
			Object[] fila = {
					am.obtener(i).getNumInternamiento(),
					am.obtener(i).getCodPaciente(),
					aa.buscar(am.obtener(i).getCodPaciente()).getNombres(),
					aa.buscar(am.obtener(i).getCodPaciente()).getApellidos(),
					am.obtener(i).getCodTratamiento(),
					ac.buscar(am.obtener(i).getCodTratamiento()).getNombreTratamiento(),
					am.obtener(i).getFecha(),
					am.obtener(i).getHora(),
					activo(aa.buscar(am.obtener(i).getCodPaciente()).getEstado())
			};
			modelo.addRow(fila);
		}
	}
	int leerNumeroInternamiento() {
		return Integer.parseInt(cboNumInternamiento.getSelectedItem().toString());
	}
	int leerCodigoPaciente() {
		return Integer.parseInt(cboCodigoPaciente.getSelectedItem().toString());
	}
	int leerCodigoTratamiento() {
		return Integer.parseInt(cboCodigoTratamiento.getSelectedItem().toString());
	}
	String nombreEstado(int i) {
		switch (i) {
			case 0: return "REGISTRADO";
			case 1: return "INTERNADO";
			case 2: return "ALTA MEDICA";
			default:return null;
		}
	}
	String activo(int i) {
		return i == 1 ? "Si" : "No";
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}
	void error(String s, JComboBox<?> cbo) {
		JOptionPane.showMessageDialog(this, s,"", JOptionPane.ERROR_MESSAGE);
		cbo.requestFocus();
	}
	int confirmar() {
		int valor = JOptionPane.showOptionDialog(null,
				"¿Esta seguro que desea eliminar este internamiento?\n"
				+ "Paciente: " + txtPaciente.getText() + "\nTratamiento: " + txtTratamiento.getText(),
				"Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, null);
		return valor;
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblInternamiento) {
			mouseClickedTblInternamiento(e);
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	protected void mouseClickedTblInternamiento(MouseEvent e) {
		if (btnAdicionar.isEnabled()) {
			try {
				Internamiento buscado = am.obtener(tblInternamiento.getSelectedRow());
				cboNumInternamiento.setSelectedIndex(tblInternamiento.getSelectedRow());
				cboCodigoPaciente.setSelectedItem(buscado.getCodPaciente());
				cboCodigoTratamiento.setSelectedItem(buscado.getCodTratamiento());
			} catch (Exception error) {}
		}
	}
}
