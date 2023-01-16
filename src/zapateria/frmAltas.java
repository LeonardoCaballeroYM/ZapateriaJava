/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;

import Atxy2k.CustomTextField.RestrictedTextField;
import conexion.conexion;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Hp
 */
public class frmAltas extends javax.swing.JFrame {

    conexion con = new conexion();
    Connection cn = con.conexion();

    public void limpiarCajas() {
        txtNombre.setText("");
        txtCodigo.setText("");
        txtModelo.setText("");
        txtPrecio.setText("");
        txtMarca.setText("");
        txtExistencia.setText("");
        txtnomCodigo.setText("");
        txtImagen.setText("");

    }

    /**
     * Creates new form frmAltas
     */
    public frmAltas() {
        initComponents();
        configForm();
    }

    public final void configForm() {
        frmAltas.this.getRootPane().setOpaque(false);
        frmAltas.this.getContentPane().setBackground(new Color(0, 0, 0));
        frmAltas.this.setBackground(new Color(0, 0, 0));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
//        this.set
        Restringir();

        int x = lblImagen.getHeight();
        int y = lblImagen.getWidth();

    }

    public void Restringir() {
        RestrictedTextField resCodigo = new RestrictedTextField(txtCodigo);
        resCodigo.setLimit(5);
        resCodigo.setOnlyNums(true);
        RestrictedTextField resNom = new RestrictedTextField(txtNombre);
        resNom.setLimit(45);
        resNom.setOnlyText(true);
        RestrictedTextField resMod = new RestrictedTextField(txtModelo);
        resMod.setLimit(45);
        resMod.setOnlyText(true);
        RestrictedTextField resPre = new RestrictedTextField(txtPrecio);
        resPre.setLimit(5);
        resPre.setOnlyNums(true);
        RestrictedTextField resExis = new RestrictedTextField(txtExistencia);
        resExis.setLimit(5);
        resExis.setOnlyNums(true);
        RestrictedTextField resMar = new RestrictedTextField(txtMarca);
        resMar.setLimit(45);
        resMar.setOnlyText(true);
        RestrictedTextField resCod_Bus = new RestrictedTextField(txtCodigo_busqueda);
        resCod_Bus.setLimit(5);
        resCod_Bus.setOnlyNums(true);
    }

    public boolean verificarCajasAlta_Productos() {
        try {
            if (txtCodigo.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Código");
            }
            if (txtNombre.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Nombre");
            }
            if (txtModelo.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Modelo");
            }
            if (txtPrecio.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar el Precio");
            }
            if (txtExistencia.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar las Existencias");
            }
            if (txtMarca.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar una Marca");
            }
            if (txtImagen.getText().compareTo("") == 0) {
                throw new Exception("Debe elegir una Imagen");
            }
            if (txtnomCodigo.getText().compareTo("") == 0) {
                throw new Exception("Debe elejir un Codigo de Barras");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return false;
        }
        return true;
    }

    public static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtExistencia = new javax.swing.JTextField();
        txtImagen = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        txtnomCodigo = new javax.swing.JTextField();
        lblcodigo = new javax.swing.JLabel();
        btnSeleccionarcodigo = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtCodigo_busqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        btnRegresar1 = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Altas");

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parámetros a guardar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18), new java.awt.Color(255, 255, 0))); // NOI18N

        btnGuardar.setBackground(new java.awt.Color(255, 255, 0));
        btnGuardar.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtCodigo.setBackground(new java.awt.Color(255, 255, 102));
        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder("Código:"));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtNombre.setBackground(new java.awt.Color(255, 255, 102));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre:"));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtModelo.setBackground(new java.awt.Color(255, 255, 102));
        txtModelo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtModelo.setBorder(javax.swing.BorderFactory.createTitledBorder("Modelo:"));
        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });

        txtPrecio.setBackground(new java.awt.Color(255, 255, 102));
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtPrecio.setBorder(javax.swing.BorderFactory.createTitledBorder("Precio:"));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        txtMarca.setBackground(new java.awt.Color(255, 255, 102));
        txtMarca.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtMarca.setBorder(javax.swing.BorderFactory.createTitledBorder("Marca:"));
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });

        txtExistencia.setBackground(new java.awt.Color(255, 255, 102));
        txtExistencia.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txtExistencia.setBorder(javax.swing.BorderFactory.createTitledBorder("Existecias:"));
        txtExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExistenciaActionPerformed(evt);
            }
        });

        txtImagen.setEditable(false);
        txtImagen.setBorder(javax.swing.BorderFactory.createTitledBorder("Ruta_Imagen:"));

        btnSeleccionar.setBackground(new java.awt.Color(255, 255, 0));
        btnSeleccionar.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        txtnomCodigo.setEditable(false);
        txtnomCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder("Ruta_Código:"));
        txtnomCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomCodigoActionPerformed(evt);
            }
        });

        btnSeleccionarcodigo.setBackground(new java.awt.Color(255, 255, 0));
        btnSeleccionarcodigo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnSeleccionarcodigo.setText("Seleccionar");
        btnSeleccionarcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarcodigoActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevo.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 102));
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo)
                            .addComponent(txtExistencia)
                            .addComponent(txtPrecio)
                            .addComponent(txtModelo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNombre)
                            .addComponent(txtMarca)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnomCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSeleccionarcodigo)
                            .addComponent(btnSeleccionar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCodigo)
                        .addGap(4, 4, 4)
                        .addComponent(txtNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtExistencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeleccionar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSeleccionarcodigo)
                            .addComponent(txtnomCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnNuevo)
                .addGap(31, 31, 31))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18), new java.awt.Color(255, 255, 0))); // NOI18N

        txtCodigo_busqueda.setBackground(new java.awt.Color(255, 255, 102));
        txtCodigo_busqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Código:"));
        txtCodigo_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigo_busquedaActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(255, 255, 0));
        btnBuscar.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCodigo_busqueda)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCodigo_busqueda)
            .addComponent(btnBuscar)
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imagen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 1, 18), new java.awt.Color(255, 255, 0))); // NOI18N

        lblImagen.setBackground(new java.awt.Color(51, 51, 51));
        lblImagen.setMaximumSize(new java.awt.Dimension(4160, 3120));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnRegresar1.setBackground(new java.awt.Color(255, 255, 0));
        btnRegresar1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnRegresar1.setText("REGRESAR");
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(255, 255, 0));
        btnModificar.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(255, 255, 0));
        btnBorrar.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegresar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificar)
                            .addComponent(btnBorrar))
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        new FrmMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresar1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            verificarCajasAlta_Productos();
            FileInputStream archivofoto;
            FileInputStream archivocodigo;
            File rutimg = new File(txtImagen.getText());
            File codigo = new File(txtnomCodigo.getText());
            archivofoto = new FileInputStream(rutimg);
            archivocodigo = new FileInputStream(codigo);

            PreparedStatement pps = cn.prepareStatement("INSERT INTO "
                    + "producto (IDProd,codigo,nombre,modelo,precio,existencias,"
                    + "marca,nomimagen,imagen,nomcodigo,imgcodigo) "
                    + "VALUES(NULL,?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1, txtCodigo.getText());
            pps.setString(2, txtNombre.getText());
            pps.setString(3, txtModelo.getText());
            pps.setString(4, txtPrecio.getText());
            pps.setString(5, txtExistencia.getText());
            pps.setString(6, txtMarca.getText());
            pps.setString(7, txtImagen.getText());
            pps.setBinaryStream(8, archivofoto, (int) rutimg.length());
            pps.setString(9, txtnomCodigo.getText());
            pps.setBinaryStream(10, archivocodigo, (int) codigo.length());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados");
            limpiarCajas();
        } catch (SQLException | FileNotFoundException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Revise los datos y/o el tipo de imagen");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos "
                + "JPEG(*.JPG; *.JPEG)", "jpg", "jpeg");

        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("W:\\Documentos\\NetBeansProjects\\Zapateria\\zapatos");
        archivo.setCurrentDirectory(ruta);

        int ventana = archivo.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = archivo.getSelectedFile();
            txtImagen.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtImagen.getText());
            foto = foto.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            lblImagen.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            String codigo = txtCodigo_busqueda.getText();
            Statement st = con.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM "
                    + "producto WHERE codigo = '" + codigo + "'");
            int cont = 0;
            ImageIcon ii = null;
            ImageIcon ii2 = null;
            InputStream is = null;
            InputStream codigoi = null;
            Object[] dat = new Object[9];
            while (rs.next()) {
                dat[0] = rs.getInt(1);
                dat[1] = rs.getInt(2);
                dat[2] = rs.getString(3);
                dat[3] = rs.getString(4);
                dat[4] = rs.getInt(5);
                dat[5] = rs.getInt(6);
                dat[6] = rs.getString(7);
                dat[7] = rs.getString(8);
                is = rs.getBinaryStream(9);
                dat[8] = rs.getString(10);
                codigoi = rs.getBinaryStream(11);
                cont++;
            }
            if (cont > 0) {
                txtCodigo_busqueda.setText("" + codigo);
                txtCodigo.setText(dat[1].toString());
                txtNombre.setText(dat[2].toString());
                txtModelo.setText(dat[3].toString());
                txtPrecio.setText(dat[4].toString());
                txtExistencia.setText(dat[5].toString());
                txtMarca.setText(dat[6].toString());
                txtImagen.setText(dat[7].toString());
                txtnomCodigo.setText(dat[8].toString());
                BufferedImage bi = ImageIO.read(is);
                Image i = bi.getScaledInstance(300, 300, 300);
                ii = new ImageIcon(i);
                lblImagen.setIcon(ii);

                BufferedImage b12 = ImageIO.read(codigoi);
                Image i1 = b12.getScaledInstance(180, 70, 180);
                ii2 = new ImageIcon(i1);
                lblcodigo.setIcon(ii2);
                btnGuardar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                System.out.println("Registro no encontrado");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(frmAltas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (!"".equals(txtCodigo_busqueda.getText())) {
            try {
                FileInputStream archivofoto;
                File rutimg = new File(txtImagen.getText());
                archivofoto = new FileInputStream(rutimg);
                FileInputStream archivocodigo;
                File codigo = new File(txtnomCodigo.getText());
                archivocodigo = new FileInputStream(codigo);

                String sql = "Update producto set codigo = ?, nombre = ?, modelo = ?,"
                        + " precio = ?, existencias = ?, marca = ?, nomimagen = ?, imagen = ?,"
                        + " nomcodigo = ?, imgcodigo = ? where codigo = ?";
                try {
                    PreparedStatement pst = con.conexion().prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(txtCodigo.getText()));
                    pst.setString(2, txtNombre.getText());
                    pst.setString(3, txtModelo.getText());
                    pst.setInt(4, Integer.parseInt(txtPrecio.getText()));
                    pst.setInt(5, Integer.parseInt(txtExistencia.getText()));
                    pst.setString(6, txtMarca.getText());
                    pst.setString(7, txtImagen.getText());
                    pst.setBinaryStream(8, archivofoto, (int) rutimg.length());
                    pst.setString(9, txtnomCodigo.getText());
                    pst.setBinaryStream(10, archivocodigo, (int) codigo.length());
                    pst.setInt(11, Integer.parseInt(txtCodigo_busqueda.getText()));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Modificado");
                    limpiarCajas();
                    txtCodigo_busqueda.setText("");
                    btnGuardar.setEnabled(true);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e, "Error de sistema", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(frmAltas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un registro");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if (!"".equals(txtCodigo_busqueda.getText())) {
            int resp = JOptionPane.showConfirmDialog(rootPane,
                    "Seguro que desea eliminar el producto ", "confirmacion", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                String sql = "delete from producto where codigo = ?";
                try {
                    PreparedStatement pst = con.conexion().prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(txtCodigo_busqueda.getText()));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Eliminado");
                    limpiarCajas();
                    txtCodigo_busqueda.setText("");
                    btnGuardar.setEnabled(true);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e, "Error de sistema", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSeleccionarcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarcodigoActionPerformed
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos "
                + "JPEG(*.JPG; *.JPEG)", "jpg", "jpeg");

        JFileChooser archivo = new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setDialogTitle("Abrir Archivo");
        File ruta = new File("W:\\Documentos\\NetBeansProjects\\Zapateria\\CB");
        archivo.setCurrentDirectory(ruta);

        int ventana = archivo.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            File file = archivo.getSelectedFile();
            txtnomCodigo.setText(String.valueOf(file));
            Image foto = getToolkit().getImage(txtnomCodigo.getText());
            foto = foto.getScaledInstance(180, 70, Image.SCALE_DEFAULT);
            lblcodigo.setIcon(new ImageIcon(foto));
        }
    }//GEN-LAST:event_btnSeleccionarcodigoActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtExistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExistenciaActionPerformed

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtCodigo_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigo_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo_busquedaActionPerformed

    private void txtnomCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnomCodigoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped

    }//GEN-LAST:event_txtCodigoKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarCajas();
        txtCodigo_busqueda.setText("");
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

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
            java.util.logging.Logger.getLogger(frmAltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAltas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmAltas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnSeleccionarcodigo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigo_busqueda;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtImagen;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtnomCodigo;
    // End of variables declaration//GEN-END:variables
}
