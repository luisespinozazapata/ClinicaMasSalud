package gui;

import arreglos.*;

import clases.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class DlgMantPaciente extends JDialog implements ItemListener, ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;

	private JLabel lblCodigo, lblNombres, lblApellidos, lblDni, lblEdad, lblCelular, lblEstado;
	private JComboBox<Integer> cboCodigo;
	private JTextField txtNombres, txtApellidos, txtDni, txtEdad, txtCelular;
	private JComboBox<String> cboEstado;
	private JButton btnAceptar, btnCancelar, btnAdicionar, btnModificar, btnEliminar;
	private JTable tblPaciente;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;

	
	ArregloPacientes pacientes = new ArregloPacientes();

	public static void main(String[] args) {
		try {
			DlgMantPaciente dialog = new DlgMantPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgMantPaciente() {
		getContentPane().setBackground(new Color(204, 208, 227));
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgMantPaciente.class.getResource("/recursos/icons8-register-48.png")));
		setTitle("Mantenimiento de Pacientes");
		setBounds(100, 100, 800,555);
		getContentPane().setLayout(null);
		
		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblCodigo.setBounds(10, 21, 77, 15);
		getContentPane().add(lblCodigo);
		
		lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblNombres.setBounds(10, 56, 77, 15);
		getContentPane().add(lblNombres);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblApellidos.setBounds(10, 89, 77, 15);
		getContentPane().add(lblApellidos);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblDni.setBounds(10, 120, 77, 15);
		getContentPane().add(lblDni);
		
		lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblEdad.setBounds(223, 120, 39, 15);
		getContentPane().add(lblEdad);
		
		lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblCelular.setBounds(10, 151, 77, 15);
		getContentPane().add(lblCelular);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblEstado.setBounds(10, 182, 77, 15);
		getContentPane().add(lblEstado);
		
		cboCodigo = new JComboBox<>();
		cboCodigo.setForeground(new Color(255, 255, 255));
		cboCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		cboCodigo.setBackground(new Color(84, 163, 188));
		cboCodigo.setEditable(true);
		cboCodigo.addItemListener(this);
		cboCodigo.setBounds(97, 19, 115, 21);
		getContentPane().add(cboCodigo);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(97, 55, 211, 21);
		getContentPane().add(txtNombres);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(97, 88, 211, 21);
		getContentPane().add(txtApellidos);
		
		txtDni = new JTextField();
		txtDni.setBounds(97, 119, 115, 21);
		getContentPane().add(txtDni);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(272, 119, 36, 21);
		getContentPane().add(txtEdad);
		
		txtCelular = new JTextField();
		txtCelular.setBounds(97, 150, 115, 21);
		getContentPane().add(txtCelular);
		
		cboEstado = new JComboBox<>(new String[] {"REGISTRADO", "INTERNADO", "DE ALTA"});
		cboEstado.setForeground(new Color(255, 255, 255));
		cboEstado.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		cboEstado.setEditable(true);
		cboEstado.setBackground(new Color(84, 163, 188));
		cboEstado.setBounds(97, 180, 140, 21);
		getContentPane().add(cboEstado);
		
		btnAceptar = crearBoton("Aceptar", 10, 215);
		btnCancelar = crearBoton("Cancelar", 166, 215);
		btnAdicionar = crearBoton("Adicionar", 672, 21);
		btnModificar = crearBoton("Modificar", 672, 79);
		btnEliminar = crearBoton("Eliminar", 672, 141);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 252, 764, 192);
		getContentPane().add(scrollPane);
		
		tblPaciente = new JTable();
		tblPaciente.addMouseListener(this);
		tblPaciente.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(tblPaciente);
		
		modelo = new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("NOMBRES");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("DNI");
		modelo.addColumn("EDAD");
		modelo.addColumn("CELULAR");
		modelo.addColumn("ESTADO");
		tblPaciente.setModel(modelo);
		
		
		ajustarColumnas();
		listar();
		deshabilitarTodo();
	}

	private JButton crearBoton(String texto, int x, int y) {
		JButton b = new JButton(texto);
		b.setForeground(new Color(255, 255, 255));
		b.setBackground(new Color(84, 163, 188));
		b.setFont(new Font("Segoe UI Black", Font.PLAIN, 13));
		b.setBounds(x, y, 100, 30);
		b.addActionListener(this);
		getContentPane().add(b);
		return b;
	}

	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboCodigo) itemStateChangedCboCodigo(e);
	}

	protected void itemStateChangedCboCodigo(ItemEvent e) {
		try {
			int cod = leerCodigo();
			Paciente p = pacientes.buscar(cod);
			txtNombres.setText(p.getNombres());
			txtApellidos.setText(p.getApellidos());
			txtDni.setText(p.getDni());
			txtEdad.setText(String.valueOf(p.getEdad()));
			txtCelular.setText(String.valueOf(p.getCelular()));
			cboEstado.setSelectedIndex(p.getEstado());
			tblPaciente.setRowSelectionInterval(cboCodigo.getSelectedIndex(), cboCodigo.getSelectedIndex());
		} catch (Exception aea) {}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdicionar) actionAdicionar();
		if (e.getSource() == btnModificar) actionModificar();
		if (e.getSource() == btnEliminar) actionEliminar();
		if (e.getSource() == btnAceptar) actionAceptar();
		if (e.getSource() == btnCancelar) deshabilitarTodo();
	}

	void actionAdicionar() {
		limpiar();
		habilitar(false, true, true, true, true, true, true, true, true, false, true, false);
		cboEstado.setSelectedIndex(0);
		cboCodigo.addItem(pacientes.codigoCorrelativo());
		cboCodigo.setSelectedIndex(pacientes.tamanio());
		txtNombres.requestFocus();
	}

	void actionModificar() {
		if (!btnAdicionar.isEnabled()) deshabilitarTodo();
		habilitar(true, true, true, false, true, true, true, true, false, true, false, false);
		cboCodigo.requestFocus();
	}

	void actionEliminar() {
		try {
			int cod = leerCodigo();
			Paciente p = pacientes.buscar(cod);
			if (p.getEstado() == 0) {
				if (confirmar() == 0) {
					pacientes.eliminar(p);
					listar();
					mensaje("Paciente eliminado exitosamente.");
					deshabilitarTodo();
				}
			} else {
				error("No puede eliminar a un paciente internado o dado de alta.", cboCodigo);
			}
		} catch (Exception ex) {
			error("Seleccione un c�digo de paciente valido.", cboCodigo);
		}
	}

	void actionAceptar() {
		try {
			int cod = leerCodigo();
			String nom = leerNombres();
			String ape = leerApellidos();
			String dni = leerDni();
			int edad = leerEdad();
			int cel = leerCelular();
			int est = leerEstado();

			if (!btnAdicionar.isEnabled()) { 
				if (!existeDNI(dni)) {
					Paciente nuevo = new Paciente(cod, nom, ape, dni, edad, cel, est);
					pacientes.adicionar(nuevo);
					listar();
					mensaje("Nuevo paciente registrado correctamente.");
					deshabilitarTodo();
				} else {
					error("El DNI ingresado ya existe.", txtDni);
				}
			} else if (!btnModificar.isEnabled()) { 
				Paciente buscado = pacientes.buscar(cod);
				buscado.setNombres(nom);
				buscado.setApellidos(ape);
				buscado.setEdad(edad);
				buscado.setCelular(cel);
				buscado.setEstado(est);
				pacientes.actualizarArchivo();
				listar();
				mensaje("Paciente modificado correctamente.");
				deshabilitarTodo();
			}
		} catch (Exception ex) {
			error("Complete todos los datos correctamente.", txtNombres);
		}
	}

	
	void habilitar(boolean codigo, boolean nombre, boolean apellidos, boolean dni, boolean edad, boolean celular,
				   boolean estado, boolean aceptar, boolean cancelar, boolean adicionar, boolean modificar, boolean eliminar) {
		cboCodigo.setEnabled(codigo);
		txtNombres.setEditable(nombre);
		txtApellidos.setEditable(apellidos);
		txtDni.setEditable(dni);
		txtEdad.setEditable(edad);
		txtCelular.setEditable(celular);
		cboEstado.setEnabled(estado);
		btnAceptar.setEnabled(aceptar);
		btnCancelar.setEnabled(cancelar);
		btnAdicionar.setEnabled(adicionar);
		btnModificar.setEnabled(modificar);
		btnEliminar.setEnabled(eliminar);
	}

	void deshabilitarTodo() {
		listarCboCodigo();
		habilitar(false, false, false, false, false, false, false, false, true, true, true, true);
		limpiar();
	}

	void limpiar() {
		cboCodigo.setSelectedIndex(-1);
		txtNombres.setText("");
		txtApellidos.setText("");
		txtDni.setText("");
		txtEdad.setText("");
		txtCelular.setText("");
		cboEstado.setSelectedIndex(-1);
	}

	void listarCboCodigo() {
		cboCodigo.removeAllItems();
		for (int i = 0; i < pacientes.tamanio(); i++)
			cboCodigo.addItem(pacientes.obtener(i).getCodPaciente());
	}

	void ajustarColumnas() {
		TableColumnModel m = tblPaciente.getColumnModel();
		m.getColumn(0).setPreferredWidth(scrollPane.getWidth() * 2);
		m.getColumn(1).setPreferredWidth(scrollPane.getWidth() * 4);
		m.getColumn(2).setPreferredWidth(scrollPane.getWidth() * 4);
		m.getColumn(3).setPreferredWidth(scrollPane.getWidth() * 2);
		m.getColumn(4).setPreferredWidth(scrollPane.getWidth());
		m.getColumn(5).setPreferredWidth(scrollPane.getWidth() * 2);
		m.getColumn(6).setPreferredWidth(scrollPane.getWidth() * 3);
	}

	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < pacientes.tamanio(); i++) {
			Paciente p = pacientes.obtener(i);
			Object[] fila = {
					p.getCodPaciente(),
					p.getNombres(),
					p.getApellidos(),
					p.getDni(),
					p.getEdad(),
					p.getCelular(),
					nombreEstado(p.getEstado())
			};
			modelo.addRow(fila);
		}
	}

	boolean existeDNI(String dni) {
		for (int i = 0; i < pacientes.tamanio(); i++)
			if (pacientes.obtener(i).getDni().equals(dni))
				return true;
		return false;
	}

	String nombreEstado(int i) {
		switch (i) {
			case 0: return "REGISTRADO";
			case 1: return "INTERNADO";
			case 2: return "DE ALTA";
			default: return "DESCONOCIDO";
		}
	}

	int leerCodigo() { return Integer.parseInt(cboCodigo.getSelectedItem().toString()); }
	String leerNombres() { return txtNombres.getText().trim().toUpperCase(); }
	String leerApellidos() { return txtApellidos.getText().trim().toUpperCase(); }
	String leerDni() { return txtDni.getText().trim(); }
	int leerEdad() { return Integer.parseInt(txtEdad.getText().trim()); }
	int leerCelular() { return Integer.parseInt(txtCelular.getText().trim()); }
	int leerEstado() { return cboEstado.getSelectedIndex(); }

	void mensaje(String s) { JOptionPane.showMessageDialog(this, s); }
	void error(String s, JComponent comp) {
		JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
		comp.requestFocus();
	}

	int confirmar() {
		return JOptionPane.showOptionDialog(null,
				"Esta seguro de eliminar a este paciente?\n" +
						pacientes.buscar(leerCodigo()).getNombres() + " " +
						pacientes.buscar(leerCodigo()).getApellidos(),
				"Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, new Object[]{"Si", "No"}, null);
	}

	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblPaciente && btnAdicionar.isEnabled()) {
			try {
				Paciente p = pacientes.obtener(tblPaciente.getSelectedRow());
				cboCodigo.setSelectedIndex(tblPaciente.getSelectedRow());
				txtNombres.setText(p.getNombres());
				txtApellidos.setText(p.getApellidos());
				txtDni.setText(p.getDni());
				txtEdad.setText(String.valueOf(p.getEdad()));
				txtCelular.setText(String.valueOf(p.getCelular()));
				cboEstado.setSelectedIndex(p.getEstado());
			} catch (Exception aea) {}
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
