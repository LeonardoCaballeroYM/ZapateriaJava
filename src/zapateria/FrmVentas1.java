/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;

import Atxy2k.CustomTextField.RestrictedTextField;
import Utilidades.Fecha;
import conexion.conexion;
import dao.VentasDAO;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Belen
 */
public class FrmVentas1 extends javax.swing.JFrame {

    VentasDAO ventasDAO;
    TiketDiseño t = new TiketDiseño();
    Ticket ticket = new Ticket();
    Fecha fecha = new Fecha();

    ArrayList<VentasVo> ven = new ArrayList<>();

    conexion con = new conexion();
    Connection cn = con.conexion();
    DefaultTableModel llenar;
    ArrayList<VentasVo> zapatos = new ArrayList<>();
    int totalproductobusqueda;

    private void llenarTabla() {
        Object[] fila = new Object[6];
        int total = 0;
        for (int i = TablaVentas.getRowCount() - 1; i >= 0; i--) {
            llenar.removeRow(i);
//            i = i - 1;
        }
        for (VentasVo aux : zapatos) {
            fila[0] = aux.getFecha();
            fila[1] = aux.getCodigo();
            fila[2] = aux.getNombre();
            fila[3] = aux.getCantidad();
            fila[4] = aux.getPrecio();
            fila[5] = aux.getTotal();
            total += aux.getTotal();
            llenar.addRow(fila);
        }
        txtTotal.setText("" + total);
        txtCambio.setText("" + (Integer.parseInt(txtImporte.getText()) - total));
    }

    public void llenarT() {
        try {
            LimpiarTabla();
            Statement st = con.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM venta");
            while (rs.next()) {
                Object[] dat = new Object[9];
                dat[0] = rs.getInt(1);//id
                dat[1] = rs.getString(2);//fecha
                dat[2] = rs.getInt(3);//codigo
                dat[3] = rs.getString(4);//nombre
                dat[4] = rs.getInt(5);//cantidad
                dat[5] = rs.getInt(6);//precio
                dat[6] = rs.getInt(7);//total
                dat[7] = rs.getInt(8);//importe
                dat[8] = rs.getInt(9);//cambio
                llenar.addRow(dat);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void LimpiarTabla() {
        for (int i = TablaVentas.getRowCount() - 1; i >= 0; i--) {
            llenar.removeRow(i);
        }
    }

    public void limpiarCajas() {
        //txtCodigo3.setText("");
        txtNombre3.setText("");
        //date.setCalendar(Calendar.getInstance());
        txtCantidad.setText("1");
        txtPrecio3.setText("");
//        txtTotal.setText("");
//        txtImporte.setText("");
//        txtCambio.setText("");

    }

    /**
     * Creates new form FrmVentas
     */
    public FrmVentas1() {
        initComponents();
        configForm();
        llenar = (DefaultTableModel) TablaVentas.getModel();
    }

    public final void configForm() {
        FrmVentas1.this.getRootPane().setOpaque(false);
        FrmVentas1.this.getContentPane().setBackground(new Color(0, 0, 0));
        FrmVentas1.this.setBackground(new Color(0, 0, 0));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ventasDAO = new VentasDAO();
        Restringir();
        txtfecha.setText(fecha.getFechaActual());
        txtCantidad.setText("1");
        txtImporte.setText("0");
        btnVender.setEnabled(false);
        
       // FrmMenu menu=new FrmMenu();
        
    }

    public void Restringir() {
        RestrictedTextField resCodigo = new RestrictedTextField(txtCodigo3);
        resCodigo.setLimit(5);
        resCodigo.setOnlyNums(true);
        RestrictedTextField resCant = new RestrictedTextField(txtCantidad);
        resCant.setLimit(5);
        resCant.setOnlyNums(true);
        RestrictedTextField resImp = new RestrictedTextField(txtImporte);
        resImp.setLimit(5);
        resImp.setOnlyNums(true);
        RestrictedTextField resCam = new RestrictedTextField(txtCambio);
        resCam.setLimit(6);
        resCam.setOnlyNums(false);
    }

    public boolean verificarCajasVentas() {
        try {
            if (txtCodigo3.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Código");
            }
            if (txtNombre3.getText().compareTo("") == 0) {
                throw new Exception("Debe dar clic en buscar");
            }
            if (txtPrecio3.getText().compareTo("") == 0) {
                throw new Exception("Debe dar clic en buscar");
            }
            if (txtVendedor.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar el nombre del vendedor");
            }

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean verificarAntesDeVender() {
        try {
            if (txtCodigo3.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar un Código");
            }
            if (txtCantidad.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar la cantidad de productos a vender");
            }
            if (txtVendedor.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar el nombre del vendedor");
            }
            if (txtTotal.getText().compareTo("") == 0) {
                throw new Exception("Debe realizar una venta");
            }
            if (txtImporte.getText().compareTo("") == 0) {
                throw new Exception("Debe colocar el importe dado por el clinte");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return false;
        }
        return true;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo3 = new javax.swing.JTextField();
        txtNombre3 = new javax.swing.JTextField();
        txtPrecio3 = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnBuscar3 = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        txtfecha = new javax.swing.JTextField();
        btnCancelarVenta = new javax.swing.JButton();
        txtVendedor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnVender = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtImporte = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        mostrarImg = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ventas");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)), null, null, null, new java.awt.Font("Verdana", 0, 12), new java.awt.Color(255, 255, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Código:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("Producto:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setText("Precio:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 0));
        jLabel4.setText("Cantidad:");

        txtNombre3.setEditable(false);

        txtPrecio3.setEditable(false);

        txtCantidad.setText("1");
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 153, 0));
        jLabel8.setText("Fecha:");

        btnBuscar3.setBackground(new java.awt.Color(255, 153, 51));
        btnBuscar3.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btnBuscar3.setText("Buscar");
        btnBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar3ActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtfecha.setEditable(false);

        btnCancelarVenta.setBackground(new java.awt.Color(255, 153, 0));
        btnCancelarVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelarVenta.setText("Cancelar Venta");
        btnCancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVentaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 0));
        jLabel9.setText("Vendedor:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelarVenta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCodigo3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre3)
                                    .addComponent(txtVendedor)
                                    .addComponent(txtPrecio3)
                                    .addComponent(txtfecha))))
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrecio3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelarVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addGap(32, 32, 32))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        btnVender.setBackground(new java.awt.Color(255, 153, 0));
        btnVender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnVender.setText("Vender");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVender)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVender)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0)), null, null, null, new java.awt.Font("Verdana", 0, 12), new java.awt.Color(255, 255, 51))); // NOI18N

        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Código", "Nombre", "Cantidad", "Precio", "Total"
            }
        ));
        jScrollPane1.setViewportView(TablaVentas);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 0));
        jLabel5.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTotal.setText("0");

        txtImporte.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtImporteFocusLost(evt);
            }
        });
        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImporteKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 0));
        jLabel6.setText("Importe:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 0));
        jLabel7.setText("Cambio:");

        txtCambio.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mostrarImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mostrarImg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FrmLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            if (verificarCajasVentas()) {
                String date = txtfecha.getText();
                int codigo = Integer.parseInt(txtCodigo3.getText());
                String nombre = txtNombre3.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                int precio = Integer.parseInt(txtPrecio3.getText());
                int total = cantidad * precio;
                VentasVo registro = new VentasVo(codigo, nombre, precio, date, cantidad, total, 0, 0);
                zapatos.add(registro);
                llenarTabla();

                limpiarCajas();
                txtCodigo3.setText("");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
//        int existencias = Integer.parseInt(txt.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());
//        int res = existencias - cantidad;

        VentasVo ventaprod;
        VentasVo venta = new VentasVo();
        venta.setFecha(txtfecha.getText());
        venta.setImporte(Integer.parseInt(txtImporte.getText()));
        venta.setCambio(Integer.parseInt(txtCambio.getText()));
        venta.setTotal(Integer.parseInt(txtTotal.getText()));

        for (int i = TablaVentas.getRowCount() - 1; i >= 0; i--) {
            ventaprod = new VentasVo();
            ventaprod.setCodigo(Integer.parseInt(TablaVentas.getValueAt(i, 1).toString()));
            ventaprod.setNombre(TablaVentas.getValueAt(i, 2).toString());
            ventaprod.setCantidad(Integer.parseInt(TablaVentas.getValueAt(i, 3).toString()));
            ventaprod.setPrecio(Integer.parseInt(TablaVentas.getValueAt(i, 4).toString()));
            ventaprod.setTotal(Integer.parseInt(TablaVentas.getValueAt(i, 5).toString()));
            ven.add(ventaprod);
        }
        String estado = ventasDAO.Vender(venta, ven).getEstatus();
        if ("OK".equals(estado)) {
            for (int i = TablaVentas.getRowCount() - 1; i >= 0; i--) {
                int exis = 0;
                int valor;
                int cant;
                valor = Integer.parseInt(TablaVentas.getValueAt(i, 1).toString());
                cant = Integer.parseInt(TablaVentas.getValueAt(i, 3).toString());
                System.out.println(valor);
            
                String sqlexis = "select existencias from producto where codigo = '" + valor + "' ";

                try {
                    Statement st = con.conexion().createStatement();
                    ResultSet rs = st.executeQuery(sqlexis);
                    while (rs.next()) {
                        exis = rs.getInt("existencias");
                    }

                } catch (SQLException o) {
                    JOptionPane.showMessageDialog(null, o);
                }
                int res = exis-cant;
                String sql = "update producto set existencias ='" + res + "'  where codigo = '" + valor + "'";
                try {
                    Statement st = con.conexion().createStatement();
                    st.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Venta realizada");

                    limpiarCajas();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            System.out.println("Venta realizada con exito");
            //JOptionPane.showMessageDialog(rootPane, "Venta realizada con exito");

                //generar ticket
                TicketVo datos = new TicketVo();
                datos.setFecha(txtfecha.getText());
                datos.setVendedor(txtVendedor.getText());
                datos.setTotal(txtTotal.getText());
                datos.setImporte(txtImporte.getText());
                datos.setCambio(txtCambio.getText());

                String f = fecha.getDiaActual() + fecha.getMesActual() + fecha.getAnioActual() + fecha.getHora() + fecha.getMinuto();
                ticket.generarTicket((f), datos, ven);
                LimpiarTabla();
                ven.clear();
                zapatos.clear();
                txtImporte.setText("0");
                txtCodigo3.setText("");
                btnVender.setEnabled(false);
            }else {
            JOptionPane.showMessageDialog(rootPane, estado);
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
//        if (txtCantidad.getText().compareTo("") == 0) {
//            JOptionPane.showMessageDialog(null, "Debe colocar la cantitad de productos a vender");
//
//        } else {
//            int precio = Integer.parseInt(txtPrecio3.getText());
//            int cantidad = Integer.parseInt(txtCantidad.getText());
//            int total = precio * cantidad;
//            txtTotal.setText(total + "");
//        }
    }//GEN-LAST:event_txtCantidadFocusLost

    private void btnBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar3ActionPerformed
        try {
            String id = txtCodigo3.getText();
            Statement st = con.conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM "
                    + "producto WHERE codigo = '" + id + "'");
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
                txtCodigo3.setText(dat[1].toString());
                txtNombre3.setText(dat[2].toString());
                txtPrecio3.setText(dat[4].toString());
                totalproductobusqueda = Integer.parseInt(dat[5].toString());
                BufferedImage bi = ImageIO.read(is);
                Image i = bi.getScaledInstance(250, 250, 250);
                ii = new ImageIcon(i);
                mostrarImg.setIcon(ii);
                BufferedImage b12 = ImageIO.read(codigoi);
                Image i1 = b12.getScaledInstance(180, 70, 180);
                ii2 = new ImageIcon(i1);
                lblCodigo.setIcon(ii2);
                
            } else {
                JOptionPane.showMessageDialog(null, "Registro no encontrado");
                System.out.println("Registro no encontrado");
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(frmAltas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscar3ActionPerformed

    private void txtImporteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtImporteFocusLost

    }//GEN-LAST:event_txtImporteFocusLost

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
//        int precio = Integer.parseInt(txtPrecio3.getText());
//        int cantidad = Integer.parseInt(txtCantidad.getText());
//        int total = precio * cantidad;
//        txtTotal.setText(total + "");
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtImporteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyReleased
        int importe;
        int total;
        int cambio;
        if (txtImporte.getText().compareTo("") == 0) {
            importe = 0;
        } else {
            importe = Integer.parseInt(txtImporte.getText());
        }
        total = Integer.parseInt(txtTotal.getText());
        cambio = (importe) - (total);
        txtCambio.setText(String.valueOf(cambio));
        btnVender.setEnabled(true);
    }//GEN-LAST:event_txtImporteKeyReleased

    private void btnCancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVentaActionPerformed
        zapatos.clear();
        txtCodigo3.setText("");
        txtCambio.setText("");
        txtTotal.setText("0");
        txtImporte.setText("0");
        limpiarCajas();
        LimpiarTabla();
        btnVender.setEnabled(false);
    }//GEN-LAST:event_btnCancelarVentaActionPerformed

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
            java.util.logging.Logger.getLogger(FrmVentas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentas1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVentas1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaVentas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar3;
    private javax.swing.JButton btnCancelarVenta;
    private javax.swing.JButton btnVender;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel mostrarImg;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo3;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNombre3;
    private javax.swing.JTextField txtPrecio3;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVendedor;
    private javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables
}
