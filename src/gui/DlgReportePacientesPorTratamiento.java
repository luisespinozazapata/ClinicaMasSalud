package gui;

import arreglos.*;

import clases.*;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

public class DlgReportePacientesPorTratamiento extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnListar;
	private JTextArea txtResultado;
	private JScrollPane scrollPane;


	public static void main(String[] args) {
	    try {
	        UIManager.setLookAndFeel(new FlatLightLaf());
	        UIManager.put("defaultFont", new Font("Montserrat", Font.PLAIN, 14));
	    } catch (Exception ex) {
	        System.err.println("No se pudo aplicar FlatLaf.");
	    }

	    EventQueue.invokeLater(() -> {
	        try {
	            DlgReportePacientesPorTratamiento dialog = new DlgReportePacientesPorTratamiento();
	            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	            dialog.setVisible(true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}



	public DlgReportePacientesPorTratamiento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgReportePacientesPorTratamiento.class.getResource("/recursos/icons8-patient-64 (1).png")));
		getContentPane().setBackground(new Color(204, 208, 227));
		setTitle("Pacientes Internados por Tratamiento");
		setBounds(100, 100, 640, 540);
		getContentPane().setLayout(null);
		
		btnListar = new JButton("Generar Reporte");
		btnListar.putClientProperty("JButton.buttonType", "default"); 
		btnListar.addActionListener(this);
		btnListar.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnListar.setBackground(new Color(84, 163, 188));

		btnListar.setBounds(230, 10, 180, 30);
		getContentPane().add(btnListar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 606, 440);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		getContentPane().add(scrollPane);
		
		txtResultado = new JTextArea();
		txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtResultado.setMargin(new Insets(10, 10, 10, 10));
		txtResultado.setBackground(UIManager.getColor("TextArea.background"));
		txtResultado.setEditable(false);
		scrollPane.setViewportView(txtResultado);
	}
	
	ArregloPacientes aa = new ArregloPacientes();
	ArregloTratamientos ac = new ArregloTratamientos();
	ArregloInternamientos am = new ArregloInternamientos();


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnListar) {
			actionPerformedBtnListar(e);
		}
	}

	protected void actionPerformedBtnListar(ActionEvent e) {
		txtResultado.setText("");
		
		imprimir("         REPORTE DE PACIENTES INTERNADOS POR TRATAMIENTO");

		boolean hayInternamientos = false;

		for (int i = 0; i < ac.tamanio(); i++) {
			boolean tienePacientes = false;
			Tratamiento tratamiento = ac.obtener(i);
			
			imprimir("TRATAMIENTO : " + tratamiento.getNombreTratamiento());
			imprimir("DURACIÓN    : " + tratamiento.getDuracionDias() + " días");
			imprimir("COSTO       : S/. " + tratamiento.getCosto());
			imprimir("");

			for (int j = 0; j < am.tamanio(); j++) {
				Internamiento internamiento = am.obtener(j);

				if (internamiento.getCodTratamiento() == tratamiento.getCodTratamiento()) {
					Paciente p = aa.buscar(internamiento.getCodPaciente());
					if (p != null && p.getEstado() == 1) { // INTERNADO
						tienePacientes = true;
						hayInternamientos = true;
						imprimir(" * PACIENTE : " + p.getNombres() + " " + p.getApellidos());
						imprimir("   DNI      : " + p.getDni());
						imprimir("   EDAD     : " + p.getEdad() + " años");
						imprimir("   FECHA INGRESO : " + internamiento.getFecha() + "  HORA : " + internamiento.getHora());
						imprimir("");
					}
				}
			}

			if (!tienePacientes) {
				imprimir(" No existen pacientes internados actualmente en este tratamiento.\n");
			}

			imprimir("________________________________________________________________\n");
		}

		if (!hayInternamientos) {
			imprimir("No se encontraron pacientes internados en ningún tratamiento.\n");
		}
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
}
