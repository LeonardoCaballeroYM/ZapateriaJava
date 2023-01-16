/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import zapateria.VentasVo;

/**
 *
 * @author Belen
 */
public class VentasDAO extends conexion {

    ResultSet rs;
    VentasVo ventasVo;

    public VentasDAO() {
        ventasVo = new VentasVo();
    }

    public VentasVo Vender(VentasVo datos, ArrayList<VentasVo> lista) {
        try {
            if (conexion() != null) {
                //deshabilita el guardado automatico en la base de datos
                getConectar().setAutoCommit(false);
                int ventaid = NuevoIdVenta(getConectar());
                if (ventaid > 0) {
                    PreparedStatement ppsventa = getConectar().prepareStatement("INSERT INTO venta "
                            + "VALUES(?,?,?,?,?)");
                    ppsventa.setInt(1, ventaid);
                    ppsventa.setString(2, datos.getFecha());
                    ppsventa.setInt(3, datos.getTotal());
                    ppsventa.setInt(4, datos.getImporte());
                    ppsventa.setInt(5, datos.getCambio());
                    int estadoquery = ppsventa.executeUpdate();
                    if (estadoquery == 1) {
                        VentasVo vv;
                        for (int i = 0; i < lista.size(); i++) {
                            vv = lista.get(i);
                            PreparedStatement ppsventaprod = getConectar().prepareStatement("INSERT INTO vntprod "
                                    + "VALUES(null,?,?,?,?,?,?)");
                            ppsventaprod.setString(1, vv.getNombre());
                            ppsventaprod.setInt(2, vv.getCodigo());
                            ppsventaprod.setInt(3, vv.getPrecio());
                            ppsventaprod.setInt(4, vv.getCantidad());
                            ppsventaprod.setInt(5, vv.getTotal());
                            ppsventaprod.setInt(6, ventaid);
                            ppsventaprod.executeUpdate();
                        }
                        //hace permanentes los cambios en la base de datos
                        getConectar().commit();
                        ventasVo.setEstatus("OK");
                    } else {
                        //deshace los cambios en la base de datos
                        getConectar().rollback();
                        ventasVo.setEstatus("Error al guardar la venta");
                    }
                } else {
                    //deshace los cambios en la base de datos
                    getConectar().rollback();
                    ventasVo.setEstatus("Error al generar id de venta");
                }

            } else {
                ventasVo.setEstatus(getEstatusConexion());
            }
        } catch (SQLException e) {
            String err = "";
            try {
                getConectar().rollback();
            } catch (SQLException ex) {
                err += " Error al deshacer cambios: " + ((ex.getMessage() != null) ? ex.getMessage() : ex.toString());
            }
            ventasVo.setEstatus(e + " > " + err);
        }
        return ventasVo;
    }

    private int NuevoIdVenta(Connection conexion) {
        int nuevoId;
        try {
            String SQLList = "SELECT idventa "
                    + " FROM venta"
                    + " ORDER BY idventa DESC LIMIT 1";
            Statement st = conexion.createStatement();
            rs = st.executeQuery(SQLList);
            if (rs.next()) {
                nuevoId = (rs.getInt(1) + 1);
            } else {
                nuevoId = 1;
            }
        } catch (SQLException e) {
            nuevoId = -1;
        }
        return nuevoId;
    }

}
