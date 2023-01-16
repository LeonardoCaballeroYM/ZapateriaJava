/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Belen
 */
public class Utilidades {
    public ImageIcon RedimencionarImagen(String imagen) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagen));
        ImageIcon fin = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        return fin;
    }

    public ImageIcon RedimencionarImagen(String imagen, int alto, int ancho) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagen));
        ImageIcon fin = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return fin;
    }
}
