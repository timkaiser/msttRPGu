import java.awt.Graphics;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.IOException;
import java.awt.Graphics;
public class Test extends JPanel{
    Kollisionsmaske k1, k2 ,k3;
    boolean b =true;
    boolean kb1,kb2,kb3;
    public Test(){
        setSize(800,600);
        setLayout(null);
        
        JFrame f = new JFrame();
        f.setSize(800,600);
        f.setLayout(null);
        f.setLocationRelativeTo(null);        
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        f.add(this);

        BufferedImage b1 = null;
        try {       b1 = ImageIO.read(getClass().getResource("test1.png"));
        } catch (IOException e) {}

        BufferedImage b23 = null;
        try {       b23 = ImageIO.read(getClass().getResource("test2.png"));
        } catch (IOException e) {}

        k1 = new Kollisionsmaske(0,0,b1);
        k2 = new Kollisionsmaske(0,0,b23);
        k3 = new Kollisionsmaske(40,20,b23);
        
        kb1 = k1.kollidiert(k2);
        kb2 = k2.kollidiert(k3);
        kb2 = k3.kollidiert(k1);
        
        System.out.println(kb1+" "+kb2+" "+kb2);
        
        f.setVisible(true);
    }

    public void test(){
        System.out.println(kb1+" "+kb2+" "+kb2);
    }

    public BufferedImage getMaske(){
       return k2.getKollisionsAusschnitt(k3);
    }
    
     protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );
        
        BufferedImage img = k1.getKollisionsAusschnitt(k2);
        
        g.fillRect(0,0,img.getWidth(), img.getHeight());        
        g.drawImage(img,0,0,this);
        g.drawImage(k2.getKollisionsAusschnitt(k1),0,0,this);
        
        img = k2.getKollisionsAusschnitt(k3);
        g.fillRect(200,0,img.getWidth(), img.getHeight());    
        g.drawImage(img,200,0,this);
        g.drawImage(k3.getKollisionsAusschnitt(k2),200,0,this);
        
        img = k1.getKollisionsAusschnitt(k3);
        g.fillRect(400,0,img.getWidth(), img.getHeight());    
        g.drawImage(img,400,0,this);
        g.drawImage(k3.getKollisionsAusschnitt(k1),400,0,this);
    }
    
}
