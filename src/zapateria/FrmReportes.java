/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;

import Utilidades.Fecha;
import conexion.conexion;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Belen
 */
public class FrmReportes extends javax.swing.JFrame {

    Fecha fecha = new Fecha();
    conexion con = new conexion();
    ReportePorDia report = new ReportePorDia();
    DefaultTableModel modeloreporte;
    ArrayList<VentasVo> ven = new ArrayList<>();
    ArrayList<ReporteVo> rep = new ArrayList<>();

    /**
     * Creates new form FrmRepportes
     */
    public FrmReportes() {
        initComponents();
        Configform();
    }

    public final void Configform() {
        FrmReportes.this.getRootPane().setOpaque(false);
        FrmReportes.this.getContentPane().setBackground(new Color(0, 0, 0));
        FrmReportes.this.setBackground(new Color(0, 0, 0));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        btnCrearPdfMes.setEnabled(false);
        txtFecha.setText(fecha.getFechaActual());
        modeloreporte = (DefaultTableModel) tblDistintosReportes.getModel();
//        tblDistintosReportes.setModel(modeloreporte);
    }

    public void Busqueda() {
        try {
            String f = txtFecha.getText();

            for (int i = modeloreporte.getRowCount() - 1; i >= 0; i--) {
                modeloreporte.removeRow(i);
            }

            String sgl = "SELECT "
                    + "venta.*, "
                    + "SUM(vntprod.cantidad) "
                    + "FROM venta "
                    + "INNER JOIN vntprod ON vntprod.idventa = venta.idventa "
                    + "WHERE venta.fecha = '" + f + "' GROUP BY venta.idventa ";

            Statement st = con.conexion().createStatement();
            ResultSet rs = st.executeQuery(sgl);

            Object[] dat = new Object[6];
            while (rs.next()) {
                dat[0] = rs.getInt(1);//idventa
                dat[1] = rs.getString(2);//fecha
                dat[2] = rs.getInt(6);//cantidad
                dat[3] = rs.getInt(3);//total
                dat[4] = rs.getInt(4);//importe
                dat[5] = rs.getInt(5);//cambio

                modeloreporte.addRow(dat);
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, (e.getMessage() != null) ? e.getMessage() : "Error: " + e);

        }
    }

    private void suma() {
        txtTotalVentasDia.setText("");
        txtGananciasDia.setText("0");
        txtTotalParesVendidosDia.setText("0");
        int ta = modeloreporte.getRowCount();
        int c = 0;
        for (int i = 0; i < modeloreporte.getRowCount(); i++) {
            try {
                int f = c++;
                int totales = Integer.parseInt(modeloreporte.getValueAt(f, 3).toString());
                String ganancias = txtGananciasDia.getText();
                int nu2 = Integer.parseInt(ganancias);
                long re = totales + nu2;

                int par = Integer.parseInt(modeloreporte.getValueAt(f, 2).toString());
                String pares = txtTotalParesVendidosDia.getText();
                int nu = Integer.parseInt(pares);
                long ParesVendidos = par + nu;

                txtGananciasDia.setText(String.valueOf(re));
                txtTotalVentasDia.setText(String.valueOf(f + 1));
                txtTotalParesVendidosDia.setText(String.valueOf(ParesVendidos));

            } catch (NumberFormatException e) {
                System.out.println(e);
                txtFecha.setText(fecha.getFechaActual());
            }
            btnCrearPdfMes.setEnabled(false);
        }

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDistintosReportes = new javax.swing.JTable();
        btnCrearPdfMes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTotalVentasDia = new javax.swing.JTextField();
        txtGananciasDia = new javax.swing.JTextField();
        txtTotalParesVendidosDia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reportes por día");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reportes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 12), new java.awt.Color(255, 255, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 204, 0));

        tblDistintosReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_ventas", "Fecha", "Cantidad", "Total", "Importe", "Cambio"
            }
        ));
        jScrollPane1.setViewportView(tblDistintosReportes);

        btnCrearPdfMes.setBackground(new java.awt.Color(255, 255, 0));
        btnCrearPdfMes.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        btnCrearPdfMes.setText("Crear PDF");
        btnCrearPdfMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPdfMesActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Total de ventas:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 0));
        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Ganancias:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 0));
        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Total de pares vendidos:");

        txtTotalVentasDia.setEditable(false);
        txtTotalVentasDia.setBackground(new java.awt.Color(255, 255, 0));

        txtGananciasDia.setEditable(false);
        txtGananciasDia.setBackground(new java.awt.Color(255, 255, 0));

        txtTotalParesVendidosDia.setEditable(false);
        txtTotalParesVendidosDia.setBackground(new java.awt.Color(255, 255, 0));

        jLabel4.setBackground(new java.awt.Color(255, 255, 0));
        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Fecha a consultar:");

        btnConsultar.setBackground(new java.awt.Color(255, 255, 0));
        btnConsultar.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        txtFecha.setBackground(new java.awt.Color(255, 255, 0));

        btnRegresar.setBackground(new java.awt.Color(255, 255, 0));
        btnRegresar.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalParesVendidosDia))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalVentasDia, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGananciasDia)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCrearPdfMes)
                        .addGap(28, 28, 28)
                        .addComponent(btnRegresar)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnConsultar)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTotalVentasDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtGananciasDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTotalParesVendidosDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCrearPdfMes)
                        .addComponent(btnRegresar)))
                .addContainerGap())
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Busqueda();
        suma();
        btnCrearPdfMes.setEnabled(true);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        new FrmMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCrearPdfMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPdfMesActionPerformed
        if (txtFecha.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debe colocar la fecha a ");
            txtFecha.requestFocus();
            return;
        }
        if (txtTotalVentasDia.getText().length() == 0) {
//            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre de Usuario");
            txtFecha.requestFocus();
            return;
        }
        if (txtGananciasDia.getText().length() == 0) {
//            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre de Usuario");
            txtFecha.requestFocus();
            return;
        }
        if (txtTotalParesVendidosDia.getText().length() == 0) {
//            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre de Usuario");
            txtFecha.requestFocus();
            return;
        }

        ven.clear();
        rep.clear();
        VentasVo ventaprod;
        for (int i = 0; i < tblDistintosReportes.getRowCount(); i++) {
            ventaprod = new VentasVo();
            ventaprod.setId(Integer.parseInt(tblDistintosReportes.getValueAt(i, 0).toString()));
            ventaprod.setNombre(tblDistintosReportes.getValueAt(i, 1).toString());
            ventaprod.setCodigo(Integer.parseInt(tblDistintosReportes.getValueAt(i, 2).toString()));
            ventaprod.setPrecio(Integer.parseInt(tblDistintosReportes.getValueAt(i, 3).toString()));
            ventaprod.setCantidad(Integer.parseInt(tblDistintosReportes.getValueAt(i, 4).toString()));
            ventaprod.setTotal(Integer.parseInt(tblDistintosReportes.getValueAt(i, 5).toString()));
            ven.add(ventaprod);
        }

        //generar ticket
        ReporteVo datos = new ReporteVo();
        datos.setGanancias(Integer.parseInt(txtGananciasDia.getText()));
        datos.setTotalVentas(Integer.parseInt(txtTotalVentasDia.getText()));
        datos.setParesVendidos(Integer.parseInt(txtTotalParesVendidosDia.getText()));
        rep.add(datos);

        String f = fecha.getMesActual() + fecha.getHora() + fecha.getMinuto();
        report.generarTicket((f), datos, ven);

    }//GEN-LAST:event_btnCrearPdfMesActionPerformed

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
            java.util.logging.Logger.getLogger(FrmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReportes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnCrearPdfMes;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDistintosReportes;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGananciasDia;
    private javax.swing.JTextField txtTotalParesVendidosDia;
    private javax.swing.JTextField txtTotalVentasDia;
    // End of variables declaration//GEN-END:variables
}