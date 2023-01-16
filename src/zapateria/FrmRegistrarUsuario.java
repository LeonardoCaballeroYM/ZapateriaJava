/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;

import Atxy2k.CustomTextField.RestrictedTextField;
import Modelos.ModeloTablaUsuario;
import Renderizadores.TableCellRendererUsuarios;
import Utilidades.Utilidades;
import conexion.conexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Belen
 */
public class FrmRegistrarUsuario extends javax.swing.JFrame {

    conexion con = new conexion();
    Utilidades utilidades;
    DefaultTableModel modeloUsuario;
    //DefaultTableModel modeloProducto;

    public FrmRegistrarUsuario() {
        initComponents();
        ConfingForm();
    }

    public final void ConfingForm() {
        this.getRootPane().setOpaque(false);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.setBackground(new Color(0, 0, 0));
        this.setLocationRelativeTo(null);
        this.setExtendedState(NORMAL);
        this.setResizable(false);
        Restringir();
        utilidades = new Utilidades();

        //Color myColor = new Color(0, 0, 0);
        modeloUsuario = new ModeloTablaUsuario();
        //Se le asigna el renderizador personalizado de las celdas
        tablaUsuarios.setDefaultRenderer(Object.class, new TableCellRendererUsuarios());
        //se le asigna el modelo a la tabla
        tablaUsuarios.setModel(modeloUsuario);
        //se establece el alto de las filas
        tablaUsuarios.setRowHeight(35);
        //Modificando atributos del header(encabezado) de la tabla
        tablaUsuarios.getTableHeader().setFont(new Font("Rockwell", Font.BOLD, 14));
        tablaUsuarios.getTableHeader().setForeground(new Color(0, 0, 0));
        //modificando tamaño maximo de las columnas para id, editar y eliminar
        tablaUsuarios.getColumnModel().getColumn(0).setMaxWidth(40);
        tablaUsuarios.getColumnModel().getColumn(5).setMaxWidth(35);
        tablaUsuarios.getColumnModel().getColumn(6).setMaxWidth(35);
        TraerUsuarios();
    }

    public void TraerUsuarios() {
        try {
            LimpiarTablaUsuarios();
            Statement st = con.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                Object[] dat = new Object[7];
                dat[0] = rs.getInt(1);
                dat[1] = rs.getString(2);
                dat[2] = rs.getString(3);
                dat[3] = rs.getString(4);
                dat[4] = rs.getString(5);
                dat[5] = new JLabel(utilidades.RedimencionarImagen("/img/editar.png"));
                dat[6] = new JLabel(utilidades.RedimencionarImagen("/img/eliminar.png"));
                modeloUsuario.addRow(dat);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void LimpiarTablaUsuarios() {
        for (int i = modeloUsuario.getRowCount() - 1; i >= 0; i--) {
            modeloUsuario.removeRow(i);
        }
    }

    public void LimpiarCajasUsuario() {
        txtId_Usuario.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtNumTelefono.setText("");
        cboTipoUsuario.setSelectedItem("Elegir tipo de usuario...");
        txtContrasenia.setText("");
        txtNombre.requestFocus();
    }

    public boolean verificarCajasUsuario() {
        try {
            if (txtNombre.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Nombre");
            }
            if (txtApellido.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Apellido");
            }
            if (txtNumTelefono.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Número de télefono");
            }
            if (cboTipoUsuario.getSelectedItem() == "Elegir tipo de usuario...") {
                throw new Exception("Debe elegir el Tipo de Usuario");
            }
            if (txtContrasenia.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar una Contraseña");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return false;
        }
        return true;
    }

    public int existeUsuario() {
        String usuario = txtNombre.getText();

        String sql = "SELECT nombre FROM usuarios WHERE nombre = '" + usuario + "'";
        try {
            int loginResultante = 0;
            PreparedStatement ps = con.conexion().prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                loginResultante++;
            }
            return loginResultante;
        } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, "Ya existe");
            return -1;
        }

    }

    public void Restringir() {
        RestrictedTextField resNom = new RestrictedTextField(txtNombre);
        resNom.setLimit(40);
        resNom.setOnlyText(true);
        RestrictedTextField resMod = new RestrictedTextField(txtApellido);
        resMod.setLimit(40);
        resMod.setOnlyText(true);
        RestrictedTextField resPre = new RestrictedTextField(txtNumTelefono);
        resPre.setLimit(10);
        resPre.setOnlyNums(true);
        RestrictedTextField resExis = new RestrictedTextField(txtContrasenia);
        resExis.setLimit(20);
        //resExis.setOnlyNums(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtNumTelefono = new javax.swing.JTextField();
        cboTipoUsuario = new javax.swing.JComboBox<>();
        txtContrasenia = new javax.swing.JPasswordField();
        txtRegistrar = new javax.swing.JButton();
        txtEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        txtId_Usuario = new javax.swing.JTextField();
        txtVolver = new javax.swing.JButton();
        txtNuevo = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Registro de usuarios");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuario:"));

        txtNombre.setBackground(new java.awt.Color(0, 0, 0));
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 153)), "Nombre:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        txtApellido.setBackground(new java.awt.Color(0, 0, 0));
        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 153)), "Apellido:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N

        txtNumTelefono.setBackground(new java.awt.Color(0, 0, 0));
        txtNumTelefono.setForeground(new java.awt.Color(255, 255, 255));
        txtNumTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 153)), "Número de teléfono:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N

        cboTipoUsuario.setBackground(new java.awt.Color(255, 204, 0));
        cboTipoUsuario.setForeground(new java.awt.Color(255, 255, 153));
        cboTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elegir tipo de usuario...", "Administrador", "Empleado" }));
        cboTipoUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Usuario:"));

        txtContrasenia.setBackground(new java.awt.Color(0, 0, 0));
        txtContrasenia.setForeground(new java.awt.Color(255, 255, 255));
        txtContrasenia.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 153)), "Contraseña:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N

        txtRegistrar.setBackground(new java.awt.Color(255, 204, 0));
        txtRegistrar.setText("Registrar");
        txtRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegistrarActionPerformed(evt);
            }
        });

        txtEditar.setBackground(new java.awt.Color(255, 204, 0));
        txtEditar.setText("Modificar");
        txtEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEditarActionPerformed(evt);
            }
        });

        tablaUsuarios.setBackground(new java.awt.Color(0, 0, 0));
        tablaUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        txtId_Usuario.setEditable(false);
        txtId_Usuario.setBackground(new java.awt.Color(0, 0, 0));
        txtId_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        txtId_Usuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 153)), "Id_usuario:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 204, 0))); // NOI18N

        txtVolver.setBackground(new java.awt.Color(255, 204, 0));
        txtVolver.setText("Regresar");
        txtVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVolverActionPerformed(evt);
            }
        });

        txtNuevo.setBackground(new java.awt.Color(255, 204, 0));
        txtNuevo.setText("Nuevo");
        txtNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(540, 540, 540)
                        .addComponent(txtVolver))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtApellido)
                            .addComponent(txtNombre)
                            .addComponent(txtId_Usuario))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtId_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRegistrar)
                            .addComponent(txtEditar)
                            .addComponent(txtVolver)
                            .addComponent(txtNuevo))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegistrarActionPerformed
        FrmRegistrarUsuario funcion = new FrmRegistrarUsuario();
        int nombre = funcion.existeUsuario();

        int exis = existeUsuario();
        if (exis == 0) {
            try {
                if (verificarCajasUsuario()) {
                    String pwd = new String(txtContrasenia.getPassword());
                    PreparedStatement pps = con.conexion().prepareStatement("INSERT INTO "
                            + "usuarios (id_usuario, nombre, apellido, telefono, tipo_usuario, contrasenia) "
                            + "VALUES(NULL,?,?,?,?,?)");

                    pps.setString(1, txtNombre.getText());
                    pps.setString(2, txtApellido.getText());
                    pps.setString(3, txtNumTelefono.getText());
                    pps.setString(4, cboTipoUsuario.getSelectedItem().toString());
                    pps.setString(5, pwd);
                    pps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Datos guardados");
                    LimpiarCajasUsuario();
                    TraerUsuarios();
                } else {
                    JOptionPane.showMessageDialog(null, "complete campos");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        } else if (exis == -1) {
            JOptionPane.showMessageDialog(null, "error de validacion");
        } else {
            JOptionPane.showMessageDialog(null, "Este usuario: " + txtNombre.getText() + " ya esta ocupado.");
            txtNombre.setText("");
            txtNombre.requestFocus();
            LimpiarCajasUsuario();
        }
    }//GEN-LAST:event_txtRegistrarActionPerformed

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        try {
            int fila = tablaUsuarios.getSelectedRow();
            int columna = tablaUsuarios.getSelectedColumn();
            if (fila != -1) {
                int id = Integer.parseInt(tablaUsuarios.getValueAt(fila, 0).toString());
                if (columna == 5) {//Editar registro (icono libretita con lapicito)
                    try {
                        Statement st = con.conexion().createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM "
                                + "usuarios WHERE id_usuario = '" + id + "'");
                        int cont = 0;
                        Object[] dat = new Object[6];
                        while (rs.next()) {
                            dat[0] = rs.getInt(1);
                            dat[1] = rs.getString(2);
                            dat[2] = rs.getString(3);
                            dat[3] = rs.getString(4);
                            dat[4] = rs.getString(5);
                            dat[5] = rs.getString(6);
                            cont++;
                        }
                        if (cont > 0) {
                            txtId_Usuario.setText("" + id);
                            txtNombre.setText(dat[1].toString());
                            txtApellido.setText(dat[2].toString());
                            txtNumTelefono.setText(dat[3].toString());
                            cboTipoUsuario.setSelectedItem(dat[4].toString());
                            txtContrasenia.setText(dat[5].toString());
                            txtRegistrar.setEnabled(false);
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Usuario no encontrado");
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }
                } else if (columna == 6) {//opcion para eliminar (icono botecito)
                    int resp = JOptionPane.showConfirmDialog(rootPane,
                            "Seguro que desea eliminar al usuario " + (tablaUsuarios.getValueAt(fila, 1).toString().toUpperCase()),
                            "Confirmación", JOptionPane.YES_NO_OPTION);
                    if (resp == JOptionPane.YES_OPTION) {
                        String sql = "delete from usuarios where id_usuario = ?";
                        try {
                            PreparedStatement pst = con.conexion().prepareStatement(sql);
                            pst.setInt(1, id);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Eliminado");
                            LimpiarCajasUsuario();
                            TraerUsuarios();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e, "Error de sistema", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un registro");
            }
        } catch (HeadlessException | NumberFormatException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void txtEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEditarActionPerformed
        if (!"".equals(txtId_Usuario.getText())) {
            String sql = "Update usuarios set nombre = ?, apellido = ?, telefono = ?, tipo_usuario = ?,"
                    + " contrasenia = ? where id_usuario = ?";
            try {
                PreparedStatement pst = con.conexion().prepareStatement(sql);
                pst.setString(1, txtNombre.getText());
                pst.setString(2, txtApellido.getText());
                pst.setString(3, txtNumTelefono.getText());
                pst.setString(4, cboTipoUsuario.getSelectedItem().toString());
                pst.setString(5, txtContrasenia.getText());
                pst.setInt(6, Integer.parseInt(txtId_Usuario.getText()));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Modificado");
                LimpiarCajasUsuario();
                TraerUsuarios();
                txtRegistrar.setEnabled(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Error de sistema", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un registro");
        }
    }//GEN-LAST:event_txtEditarActionPerformed

    private void txtVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVolverActionPerformed
        FrmLogin ver = new FrmLogin();
        ver.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_txtVolverActionPerformed

    private void txtNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoActionPerformed
        LimpiarCajasUsuario();
        txtRegistrar.setEnabled(true);
    }//GEN-LAST:event_txtNuevoActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        char C = evt.getKeyChar();

        if (Character.isDigit(C)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo letras");
        } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                || (int) evt.getKeyChar() > 123 && (int) evt.getKeyChar() <= 255) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo letras");
        }

    }//GEN-LAST:event_txtNombreKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistrarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistrarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboTipoUsuario;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JButton txtEditar;
    private javax.swing.JTextField txtId_Usuario;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JButton txtNuevo;
    private javax.swing.JTextField txtNumTelefono;
    private javax.swing.JButton txtRegistrar;
    private javax.swing.JButton txtVolver;
    // End of variables declaration//GEN-END:variables
}
