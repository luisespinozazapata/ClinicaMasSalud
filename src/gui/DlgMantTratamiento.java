package gui;

import arreglos.*;
import clases.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class DlgMantTratamiento extends JDialog implements ActionListener, ItemListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblCodigo, lblNombre, lblDuracion, lblSesiones, lblCosto;
	private JComboBox<Integer> cboCodigo;
	private JTextField txtNombre, txtDuracion, txtSesiones, txtCosto;
	private JButton btnAceptar, btnCancelar, btnAdicionar, btnModificar, btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblTratamiento;
	private DefaultTableModel modelo;

	
	ArregloTratamientos tratamientos = new ArregloTratamientos();

	public static void main(String[] args) {
		try {
			DlgMantTratamiento dialog = new DlgMantTratamiento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgMantTratamiento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgMantTratamiento.class.getResource("/recursos/icons8-heart-94.png")));
		getContentPane().setBackground(new Color(204, 208, 227));
		setTitle("Mantenimiento de Tratamientos Medicos");
		setBounds(100, 100, 700,540);
		getContentPane().setLayout(null);
		
		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblCodigo.setBounds(10, 22, 77, 15);
		getContentPane().add(lblCodigo);
		
		lblNombre = new JLabel("Nombre del tratamiento:");
		lblNombre.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblNombre.setBounds(10, 57, 180, 15);
		getContentPane().add(lblNombre);
		
		lblDuracion = new JLabel("Duración (dias):");
		lblDuracion.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblDuracion.setBounds(10, 92, 120, 15);
		getContentPane().add(lblDuracion);
		
		lblSesiones = new JLabel("Número de Sesiones:");
		lblSesiones.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblSesiones.setBounds(265, 87, 141, 15);
		getContentPane().add(lblSesiones);
		
		lblCosto = new JLabel("Costo por sesión  (S/):");
		lblCosto.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblCosto.setBounds(247, 26, 141, 15);
		getContentPane().add(lblCosto);
		
		cboCodigo = new JComboBox<>();
		cboCodigo.setForeground(new Color(255, 255, 255));
		cboCodigo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		cboCodigo.setEditable(true);
		cboCodigo.setBackground(new Color(84, 163, 188));
		cboCodigo.addItemListener(this);
		cboCodigo.setBounds(97, 20, 115, 21);
		getContentPane().add(cboCodigo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(190, 55, 270, 21);
		getContentPane().add(txtNombre);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(130, 90, 60, 21);
		getContentPane().add(txtDuracion);
		
		txtSesiones = new JTextField();
		txtSesiones.setBounds(415, 86, 45, 21);
		getContentPane().add(txtSesiones);
		
		txtCosto = new JTextField();
		txtCosto.setBounds(390, 23, 70, 21);
		getContentPane().add(txtCosto);
		
		btnAceptar = crearBoton("Aceptar", 10, 163);
		btnCancelar = crearBoton("Cancelar", 166, 163);
		btnAdicionar = crearBoton("Adicionar", 572, 22);
		btnModificar = crearBoton("Modificar", 572, 82);
		btnEliminar = crearBoton("Eliminar", 572, 140);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 666, 290);
		getContentPane().add(scrollPane);
		
		tblTratamiento = new JTable();
		tblTratamiento.addMouseListener(this);
		scrollPane.setViewportView(tblTratamiento);
		
		modelo = new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("NOMBRE DEL TRATAMIENTO");
		modelo.addColumn("DURACIÓN (días)");
		modelo.addColumn("SESIONES");
		modelo.addColumn("COSTO (S/)");
		tblTratamiento.setModel(modelo);
		
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
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdicionar) actionAdicionar();
		if (e.getSource() == btnModificar) actionModificar();
		if (e.getSource() == btnEliminar) actionEliminar();
		if (e.getSource() == btnCancelar) deshabilitarTodo();
		if (e.getSource() == btnAceptar) actionAceptar();
	}
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboCodigo) itemStateChangedCboCodigo(e);
	}
	
	protected void itemStateChangedCboCodigo(ItemEvent e) {
		try {
			int cod = leerCodigo();
			Tratamiento t = tratamientos.buscar(cod);
			txtNombre.setText(t.getNombreTratamiento());
			txtDuracion.setText(String.valueOf(t.getDuracionDias()));
			txtSesiones.setText(String.valueOf(t.getSesiones()));
			txtCosto.setText(String.valueOf(t.getCosto()));
			tblTratamiento.setRowSelectionInterval(cboCodigo.getSelectedIndex(), cboCodigo.getSelectedIndex());
		} catch (Exception ignored) {}
	}


	void actionAdicionar() {
		limpiar();
		habilitar(false, true, true, true, true, true, true, false, true, false);
		cboCodigo.addItem(tratamientos.codigoCorrelativo());
		cboCodigo.setSelectedIndex(tratamientos.tamanio());
		txtNombre.requestFocus();
	}
	
	void actionModificar() {
		if (!btnAdicionar.isEnabled()) deshabilitarTodo();
		habilitar(true, true, true, true, true, true, true, true, false, false);
		cboCodigo.requestFocus();
	}
	
	void actionEliminar() {
		try {
			int cod = leerCodigo();
			if (confirmar() == 0) {
				Tratamiento t = tratamientos.buscar(cod);
				tratamientos.eliminar(t);
				listar();
				mensaje("Tratamiento eliminado exitosamente.");
				deshabilitarTodo();
			}
		} catch (Exception ex) {
			error("Seleccione un codigo de tratamiento valido.", cboCodigo);
		}
	}
	
	void actionAceptar() {
		try {
			int cod = leerCodigo();
			String nombre = leerNombre();
			int duracion = leerDuracion();
			int sesiones = leerSesiones();
			double costo = leerCosto();
			
			if (!btnAdicionar.isEnabled()) { 
				Tratamiento nuevo = new Tratamiento(cod, nombre, duracion, sesiones, costo);
				tratamientos.adicionar(nuevo);
				listar();
				mensaje("Tratamiento añadido correctamente.");
				deshabilitarTodo();
			} else if (!btnModificar.isEnabled()) { 
				Tratamiento t = tratamientos.buscar(cod);
				t.setNombreTratamiento(nombre);
				t.setDuracionDias(duracion);
				t.setSesiones(sesiones);
				t.setCosto(costo);
				tratamientos.actualizarArchivo();
				listar();
				mensaje("Tratamiento modificado correctamente.");
				deshabilitarTodo();
			}
			
		} catch (Exception ex) {
			error("Verifique los datos ingresados.", txtNombre);
		}
	}
	

	void habilitar(boolean codigo, boolean nombre, boolean duracion, boolean sesiones, boolean costo,
				   boolean aceptar, boolean cancelar, boolean adicionar, boolean modificar, boolean eliminar) {
		cboCodigo.setEnabled(codigo);
		txtNombre.setEditable(nombre);
		txtDuracion.setEditable(duracion);
		txtSesiones.setEditable(sesiones);
		txtCosto.setEditable(costo);
		btnAceptar.setEnabled(aceptar);
		btnCancelar.setEnabled(cancelar);
		btnAdicionar.setEnabled(adicionar);
		btnModificar.setEnabled(modificar);
		btnEliminar.setEnabled(eliminar);
	}

	void deshabilitarTodo() {
		listarCboCodigo();
		habilitar(false, false, false, false, false, false, false, true, true, true);
		limpiar();
	}

	void limpiar() {
		cboCodigo.setSelectedIndex(-1);
		txtNombre.setText("");
		txtDuracion.setText("");
		txtSesiones.setText("");
		txtCosto.setText("");
	}

	void listarCboCodigo() {
		cboCodigo.removeAllItems();
		for (int i = 0; i < tratamientos.tamanio(); i++)
			cboCodigo.addItem(tratamientos.obtener(i).getCodTratamiento());
	}

	void ajustarColumnas() {
		TableColumnModel m = tblTratamiento.getColumnModel();
		m.getColumn(0).setPreferredWidth(scrollPane.getWidth() * 1);
		m.getColumn(1).setPreferredWidth(scrollPane.getWidth() * 5);
		m.getColumn(2).setPreferredWidth(scrollPane.getWidth() * 1);
		m.getColumn(3).setPreferredWidth(scrollPane.getWidth() * 1);
		m.getColumn(4).setPreferredWidth(scrollPane.getWidth() * 1);
	}

	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < tratamientos.tamanio(); i++) {
			Tratamiento t = tratamientos.obtener(i);
			Object[] fila = {
					t.getCodTratamiento(),
					t.getNombreTratamiento(),
					t.getDuracionDias(),
					t.getSesiones(),
					t.getCosto()
			};
			modelo.addRow(fila);
		}
	}

	int leerCodigo() { return Integer.parseInt(cboCodigo.getSelectedItem().toString()); }
	String leerNombre() { return txtNombre.getText().trim().toUpperCase(); }
	int leerDuracion() { return Integer.parseInt(txtDuracion.getText().trim()); }
	int leerSesiones() { return Integer.parseInt(txtSesiones.getText().trim()); }
	double leerCosto() { return Double.parseDouble(txtCosto.getText().trim()); }

	void mensaje(String s) { JOptionPane.showMessageDialog(this, s); }
	void error(String s, JComponent c) {
		JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
		c.requestFocus();
	}

	int confirmar() {
		return JOptionPane.showOptionDialog(this,
				"¿Esta seguro de eliminar este tratamiento?\n" +
				tratamientos.buscar(leerCodigo()).getNombreTratamiento(),
				"Confirmar eliminación",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, new Object[]{"Si", "No"}, null);
	}


	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblTratamiento && btnAdicionar.isEnabled()) {
			try {
				Tratamiento t = tratamientos.obtener(tblTratamiento.getSelectedRow());
				cboCodigo.setSelectedIndex(tblTratamiento.getSelectedRow());
				txtNombre.setText(t.getNombreTratamiento());
				txtDuracion.setText(String.valueOf(t.getDuracionDias()));
				txtSesiones.setText(String.valueOf(t.getSesiones()));
				txtCosto.setText(String.valueOf(t.getCosto()));
			} catch (Exception ignored) {}
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
