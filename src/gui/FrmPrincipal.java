package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import com.formdev.flatlaf.FlatLightLaf;

import clases.Calendario;

import javax.swing.UIManager;

public class FrmPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuBar menuBar;
    private JMenu mnMantenimiento, mnRegistro, mnConsulta, mnReporte;
    private JMenuItem mntmMantPaciente, mntmMantTratamiento, mntmRegInternamiento, mntmRegAlta;
    private JMenuItem mntmConsPaciente, mntmConsTratamiento, mntmConsInternamiento, mntmConsAlta;
    private JMenuItem mntmPacInterPendiente, mntmPacInterVigente, mntmPacInterTratam;
    private JLabel lbBienvenidos, lblFe, lblHor;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("defaultFont", new Font("Montserrat", Font.PLAIN, 14));
        } catch (Exception ex) {
            System.err.println("No se pudo aplicar FlatLaf.");
        }

        EventQueue.invokeLater(() -> {
            FrmPrincipal frame = new FrmPrincipal();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public FrmPrincipal() {
        setTitle("Sistema de Registro e Internamiento de Pacientes");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/icons8-life-cycle-48.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(918, 644);
        getContentPane().setLayout(new BorderLayout());

        // Men� superior
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnMantenimiento = new JMenu("Mantenimiento");
        mnMantenimiento.setMnemonic('M');
        menuBar.add(mnMantenimiento);

        mntmMantPaciente = new JMenuItem("Paciente");
        mntmMantPaciente.addActionListener(this);
        mnMantenimiento.add(mntmMantPaciente);

        mntmMantTratamiento = new JMenuItem("Tratamiento");
        mntmMantTratamiento.addActionListener(this);
        mnMantenimiento.add(mntmMantTratamiento);

        mnRegistro = new JMenu("Registro");
        mnRegistro.setMnemonic('R');
        menuBar.add(mnRegistro);

        mntmRegInternamiento = new JMenuItem("Internamiento");
        mntmRegInternamiento.addActionListener(this);
        mnRegistro.add(mntmRegInternamiento);

        mntmRegAlta = new JMenuItem("Alta");
        mntmRegAlta.addActionListener(this);
        mnRegistro.add(mntmRegAlta);

        mnConsulta = new JMenu("Consulta");
        mnConsulta.setMnemonic('C');
        menuBar.add(mnConsulta);

        mntmConsPaciente = new JMenuItem("Paciente");
        mntmConsPaciente.addActionListener(this);
        mnConsulta.add(mntmConsPaciente);

        mntmConsTratamiento = new JMenuItem("Tratamiento");
        mntmConsTratamiento.addActionListener(this);
        mnConsulta.add(mntmConsTratamiento);

        mntmConsInternamiento = new JMenuItem("Internamiento");
        mntmConsInternamiento.addActionListener(this);
        mnConsulta.add(mntmConsInternamiento);

        mntmConsAlta = new JMenuItem("Alta");
        mntmConsAlta.addActionListener(this);
        mnConsulta.add(mntmConsAlta);

        mnReporte = new JMenu("Reporte");
        mnReporte.setMnemonic('P');
        menuBar.add(mnReporte);

        mntmPacInterPendiente = new JMenuItem("Pacientes con internamiento pendiente");
        mntmPacInterPendiente.addActionListener(this);
        mnReporte.add(mntmPacInterPendiente);

        mntmPacInterVigente = new JMenuItem("Pacientes con internamiento vigente");
        mntmPacInterVigente.addActionListener(this);
        mnReporte.add(mntmPacInterVigente);

        mntmPacInterTratam = new JMenuItem("Pacientes internados por tratamiento");
        mntmPacInterTratam.addActionListener(this);
        mnReporte.add(mntmPacInterTratam);

        // Fondo con imagen
        ImageIcon fondo = new ImageIcon(getClass().getResource("/recursos/Prin.png"));
        JPanel fondoPanel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondoPanel.setLayout(null);
        fondoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(fondoPanel, BorderLayout.CENTER);

        lbBienvenidos = new JLabel("Bienvenidos al Sistema");
        lbBienvenidos.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
        lbBienvenidos.setBounds(34, 528, 338, 53);
        fondoPanel.add(lbBienvenidos);

        lblFe = new JLabel();
        lblFe.setForeground(new Color(24, 58, 41));
        lblFe.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblFe.setBounds(669, 11, 224, 23);
        fondoPanel.add(lblFe);

        lblHor = new JLabel();
        lblHor.setForeground(new Color(83, 41, 0));
        lblHor.setFont(new Font("Arial Black", Font.BOLD, 30));
        lblHor.setBounds(731, 528, 162, 53);
        fondoPanel.add(lblHor);

        Timer timer = new Timer(1000, e -> actualizarFechaHora());
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == mntmPacInterTratam) abrirDlg(new DlgReportePacientesPorTratamiento());
        else if (src == mntmPacInterVigente) abrirDlg(new DlgReporteInternamientosVigentes());
        else if (src == mntmPacInterPendiente) abrirDlg(new DlgReporteInternamientosPendientes());
        else if (src == mntmConsAlta) abrirDlg(new DlgConsultaAlta());
        else if (src == mntmConsInternamiento) abrirDlg(new DlgConsultaInternamiento());
        else if (src == mntmConsTratamiento) abrirDlg(new DlgConsultaTratamiento());
        else if (src == mntmConsPaciente) abrirDlg(new DlgConsultaPaciente());
        else if (src == mntmRegAlta) abrirDlg(new DlgRegAlta());
        else if (src == mntmRegInternamiento) abrirDlg(new DlgRegInternamiento());
        else if (src == mntmMantTratamiento) abrirDlg(new DlgMantTratamiento());
        else if (src == mntmMantPaciente) abrirDlg(new DlgMantPaciente());
    }

    private void abrirDlg(JDialog dlg) {
        dlg.setModal(true);
        dlg.setLocationRelativeTo(this);
        dlg.setVisible(true);
    }

    private void actualizarFechaHora() {
        
        lblFe.setText("Fecha : "+Calendario.fechaActual());
        lblHor.setText(Calendario.horaActual());
    }
}