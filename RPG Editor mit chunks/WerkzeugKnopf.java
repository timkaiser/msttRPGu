import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
public class WerkzeugKnopf extends JButton implements ActionListener{
    private Werkzeug werkzeug;
    private BufferedImage image;
    public WerkzeugKnopf(BufferedImage image,Werkzeug werkzeug){
        this.image = image;
        this.werkzeug = werkzeug;
        addActionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(image,0,0,getWidth(),getHeight(),this);

    }

    public void actionPerformed(ActionEvent e){
        Frame.getMaus().setWerkzeug(werkzeug);
        System.out.println("DÜDÜDÜ");
    }

}