/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramaciondealgoritmos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class Mostrar_Personal extends javax.swing.JFrame {

    public static ArrayList<Personal> PersonalEmp = new ArrayList<Personal>();
    String Carpeta = "";
    TableRowSorter trs = null;

    private void llenarTabla() {

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Nombre");
        dtm.addColumn("Apellido");
        dtm.addColumn("Cedula");
        dtm.addColumn("Correo");
        dtm.addColumn("Celular");
        dtm.addColumn("Telefono");
        dtm.addColumn("Cargo");
        dtm.addColumn("Salario");

        Object fila[] = new Object[dtm.getColumnCount()];
        Scanner cargaArchivo;
        String linea;
        try {
            cargaArchivo = new Scanner(new File("Registro_Empleados.csv"));
            while (cargaArchivo.hasNext()) {
                linea = cargaArchivo.nextLine();
                String[] tokens = linea.split(";");
                fila[0] = tokens[0];
                fila[1] = tokens[1];
                fila[2] = tokens[2];
                fila[3] = tokens[3];
                fila[4] = tokens[4];
                fila[5] = tokens[5];
                fila[6] = tokens[6];
                fila[7] = tokens[7];

                dtm.addRow(fila);
            }
            tablaPersonal.setModel(dtm);
            cargaArchivo.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Mostrar_Personal() {
        // Desabilitamos visualmente la ventana padre
        initComponents();

        llenarTabla();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPersonal = new javax.swing.JTable();
        lblApellido = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtaApellido = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        lblCargo = new javax.swing.JLabel();
        lblSalario = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCargo = new javax.swing.JTextField();
        txtSalrio = new javax.swing.JTextField();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPersonal.setEnabled(false);
        tablaPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPersonalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPersonal);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 340, 1071, 193));

        lblApellido.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(255, 255, 255));
        lblApellido.setText("Apellido :");
        jPanel1.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 141, 91, 34));

        lblNombre.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre :");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 76, 91, 34));

        lblCorreo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreo.setText("Correo :");
        jPanel1.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 255, 91, 34));

        lblCedula.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblCedula.setForeground(new java.awt.Color(255, 255, 255));
        lblCedula.setText("Cedula :");
        jPanel1.add(lblCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 203, 91, 34));

        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 76, 289, 34));

        txtaApellido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtaApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 141, 289, 34));

        txtCorreo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 255, 289, 34));

        txtCedula.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 203, 289, 34));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PERSONAL REGISTRADO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 230, 50));

        lblTelefono.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefono.setText("Telefono :");
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 128, 91, 34));

        lblCelular.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblCelular.setForeground(new java.awt.Color(255, 255, 255));
        lblCelular.setText("Celular :");
        jPanel1.add(lblCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 76, 91, 34));

        lblCargo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblCargo.setForeground(new java.awt.Color(255, 255, 255));
        lblCargo.setText("Cargo :");
        jPanel1.add(lblCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 188, 91, 34));

        lblSalario.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblSalario.setForeground(new java.awt.Color(255, 255, 255));
        lblSalario.setText("Salario:");
        jPanel1.add(lblSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 91, 34));

        txtCelular.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 290, 34));

        txtTelefono.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 290, 34));

        txtCargo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 290, 34));

        txtSalrio.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPanel1.add(txtSalrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, 290, 34));

        btnVolver.setBackground(new java.awt.Color(255, 255, 255));
        btnVolver.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 550, 130, 36));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoprogramaciondealgoritmos/Imagenes/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose();

    }//GEN-LAST:event_btnVolverActionPerformed

    private void tablaPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPersonalMouseClicked
        // TODO add your handling code here:
        int selection = tablaPersonal.rowAtPoint(evt.getPoint());
        txtNombre.setText(String.valueOf(tablaPersonal.getValueAt(selection, 0)));
        txtaApellido.setText(String.valueOf(tablaPersonal.getValueAt(selection, 1)));
        txtCedula.setText(String.valueOf(tablaPersonal.getValueAt(selection, 2)));
        txtCorreo.setText(String.valueOf(tablaPersonal.getValueAt(selection, 3)));
        txtCelular.setText(String.valueOf(tablaPersonal.getValueAt(selection, 4)));
        txtTelefono.setText(String.valueOf(tablaPersonal.getValueAt(selection, 5)));
        txtCargo.setText(String.valueOf(tablaPersonal.getValueAt(selection, 6)));
        txtSalrio.setText(String.valueOf(tablaPersonal.getValueAt(selection, 7)));
    }//GEN-LAST:event_tablaPersonalMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tablaPersonal;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSalrio;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtaApellido;
    // End of variables declaration//GEN-END:variables
}
