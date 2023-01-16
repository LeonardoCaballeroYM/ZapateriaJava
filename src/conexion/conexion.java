/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class conexion {

    ResultSet resultado = null;
    private Connection con = null;

    public String Estatus = null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/zapateria", "root", "");
            //JOptionPane.showMessageDialog(null, "Conectado");
            setEstatus(null);
        } catch (ClassNotFoundException | SQLException e) {
            setEstatus("Error de conexion con el servidor " + e);
        }
        return con;
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     */
    public void CerrarConexion() {
        try {
            setEstatus(null);
            if (getConectar() != null && !getConectar().isClosed()) {
                getConectar().close();
            }
        } catch (Exception e) {
            setEstatus("Error al cerrar la conexion");
        }
    }

    /**
     * @return the Estatus
     */
    public String getEstatusConexion() {
        return Estatus;
    }

    /**
     * @param Estatus the Estatus to set
     */
    public void setEstatus(String Estatus) {
        this.Estatus = Estatus;
    }

    /**
     * @return the Conectar
     */
    public Connection getConectar() {
        return con;
    }

    public ResultSet Acceder(String Nombre) {
        String sql = "Select * from Usuarios where nombre = '" + Nombre + "'";
        try {
            Statement st = con.createStatement();
            resultado = st.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return resultado;
    }
}
