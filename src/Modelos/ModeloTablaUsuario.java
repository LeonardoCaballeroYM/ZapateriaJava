/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Belen
 */
public class ModeloTablaUsuario extends DefaultTableModel {

    Class[] tipos = new Class[]{
        Integer.class,
        String.class,
        String.class,
        String.class,
        String.class,
        JLabel.class,
        JLabel.class
    };

    public ModeloTablaUsuario() {
        
        super(new Object[]{
            "Id",
            "Nombre",
            "Apellido",
            "Teléfono",
            "Tipo de Usuario",
            "",
            ""
        }, 0);
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return tipos[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
