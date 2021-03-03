/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author luisd
 */

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends javax.swing.JPanel {
    ImageIcon Img;
    String path;
    public Imagen(JPanel jPanel1, String path) {
        this.path = path;
        this.Img = new ImageIcon(path);
        this.setSize(new Dimension(Img.getIconWidth(), Img.getIconHeight()));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Img.getImage(), 0, 0, null);
    }    
}
