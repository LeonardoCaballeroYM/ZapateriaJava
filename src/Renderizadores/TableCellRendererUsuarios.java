/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderizadores;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Belen
 */
public class TableCellRendererUsuarios extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent (JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column){
        
        this.setBackground((row % 2) == 0 ? Color.BLACK: Color.DARK_GRAY);
        
        if (value instanceof JLabel) {
            JLabel lbl = (JLabel) value;
            if (isSelected) {
                lbl.setBackground(new Color(255, 255, 255));
            }
            if (column == 5) {
                lbl.setToolTipText("Editar Usuario");
            }
            else if (column == 6) {
                lbl.setToolTipText("Eliminar Usuario");
            }
            lbl.setOpaque(true);
            return lbl;
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
