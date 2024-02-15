package vue;
/**
la classe ImagePanel est un Jpanel qui va contenir l'image des différentes cartes sélectionnées par le joueur physique au cours 
de la partie et les afficher 

* 
* @author diffo diffo brian - dorcas adrake
*
*/
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel implements Serializable {

	private transient BufferedImage image;

    public ImagePanel(String imagePath) {
        try {
            // Chargez votre image depuis un fichier (ajustez le chemin selon votre structure de fichiers)
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour l'image en fonction du nouveau chemin d'accès
    public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            repaint(); // Redessine le JPanel pour afficher la nouvelle image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth(); // Largeur du JPanel
        int panelHeight = getHeight(); // Hauteur du JPanel

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        // Calculez les coordonnées pour centrer l'image dans le JPanel
        int x = (panelWidth - imageWidth) / 2;
        int y = (panelHeight - imageHeight) / 2;

        // Dessinez l'image sur le JPanel sans déformation
        g.drawImage(image, x, y, imageWidth, imageHeight, this);
    }
}