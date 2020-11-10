package administrador;

import cliente.utilidades.UtilidadesRegistroC;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import servidor.sop_rmi.estadisticasInt;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class GUIAdministrador extends javax.swing.JFrame {

    {
        //Set Look & Feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    private servidor.sop_rmi.estadisticasInt admin;
    private JPanel jpanelconexion;
    private JLabel jlabelservidor;
    private JTextField jtextfieldservidor;
    private JTextField jtextfieldpuerto;
    private JButton jbuttonconec;
    private JLabel jlabelpuerto;
    String nombre;

    int opin = 0;

    /**
     * Auto-generated main method to display this JFrame
     *
     * @param args
     */
    public static void main(String[] args) {
        GUIAdministrador inst = new GUIAdministrador();
        inst.setVisible(true);
    }

    public GUIAdministrador() {
        super();
        initGUI();
    }

    private void initGUI() {
        try {
            {
                this.setEnabled(true);
            }
            {
                //jButtonHis.setVisible(false);
            }
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            BorderLayout thisLayout = new BorderLayout();
            getContentPane().setLayout(thisLayout);
            this.setVisible(false);
            {
                jpanelconexion = new JPanel();
                getContentPane().add(jpanelconexion, BorderLayout.CENTER);
                GridBagLayout PanelConexionLayout = new GridBagLayout();
                PanelConexionLayout.columnWidths = new int[]{156, 21, 7};
                PanelConexionLayout.rowHeights = new int[]{59, 42, 48};
                PanelConexionLayout.columnWeights = new double[]{0.0, 0.0,
                    0.1};
                PanelConexionLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
                jpanelconexion.setLayout(PanelConexionLayout);
                jpanelconexion
                        .setPreferredSize(new java.awt.Dimension(392, -1));
                {
                    jlabelservidor = new JLabel();
                    jpanelconexion.add(jlabelservidor, new GridBagConstraints(
                            0,
                            1,
                            1,
                            1,
                            0.0,
                            0.0,
                            GridBagConstraints.CENTER,
                            GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0),
                            0,
                            0));
                    jlabelservidor.setText("Dirección ip del rmiregistry");
                }
                {
                    jlabelpuerto = new JLabel();
                    jpanelconexion.add(jlabelpuerto, new GridBagConstraints(
                            0,
                            2,
                            1,
                            1,
                            0.0,
                            0.0,
                            GridBagConstraints.CENTER,
                            GridBagConstraints.NONE,
                            new Insets(0, 0, 0, 0),
                            0,
                            0));
                    jlabelpuerto.setText("Puerto del rmiregistry");
                }
                {
                    jtextfieldservidor = new JTextField();
                    jpanelconexion.add(
                            jtextfieldservidor,
                            new GridBagConstraints(
                                    2,
                                    1,
                                    1,
                                    1,
                                    0.0,
                                    0.0,
                                    GridBagConstraints.CENTER,
                                    GridBagConstraints.HORIZONTAL,
                                    new Insets(0, 0, 0, 25),
                                    0,
                                    0));
                }
                {
                    jtextfieldpuerto = new JTextField();
                    jpanelconexion.add(
                            jtextfieldpuerto,
                            new GridBagConstraints(
                                    2,
                                    2,
                                    1,
                                    1,
                                    0.0,
                                    0.0,
                                    GridBagConstraints.CENTER,
                                    GridBagConstraints.HORIZONTAL,
                                    new Insets(0, 0, 0, 25),
                                    0,
                                    0));
                }
                {
                    jbuttonconec = new JButton();
                    jpanelconexion.add(jbuttonconec, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                    jbuttonconec.setText("Conectar");
                    jbuttonconec
                            .setBackground(new java.awt.Color(230, 230, 250));
                    jbuttonconec.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            try {
                                jButtonConecActionPerformed(evt);
                            } catch (RemoteException ex) {
                                Logger.getLogger(GUIAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                 }
            }
            pack();
            setSize(400, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jButtonConecActionPerformed(ActionEvent evt) throws RemoteException {
        boolean bandera = false;
        jpanelconexion.setVisible(false);
        javax.swing.JOptionPane.showMessageDialog(this, "Bienvenido ADMINISTRADOR");

        try {

            admin = (estadisticasInt) UtilidadesRegistroC.obtenerObjRemoto(Integer.parseInt(jtextfieldpuerto.getText()), jtextfieldservidor.getText(), "ServidorEstadisticas");
            this.setVisible(false);
            vistaEstadisticas objEstadistica = new vistaEstadisticas(admin);
            objEstadistica.show();
        } catch (Exception e) {
            System.out.println("No se pudo realizar la conexion...");
            System.out.println(e.getMessage());
        }

    }

}
