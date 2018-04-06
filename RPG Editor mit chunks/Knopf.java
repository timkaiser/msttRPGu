import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class Knopf extends JButton implements ActionListener{
    private char code;
    private int zahl;
    private BufferedImage img;

    public Knopf(char code,int zahl,BufferedImage img){
        setPreferredSize(new Dimension(64,64));
        // setIconImage(imageAdresse);
        this.code =code;
        this.zahl = zahl;
        this.img = img;
        addActionListener(this);
        setVisible(true);

    }

    public Knopf(char code,int zahl){
        setSize(50,50);
        this.code =code;
        this.zahl = zahl;
        addActionListener(this);
        setVisible(true);
    }

    public void setIconImage(String adresse){
        setIcon(new ImageIcon(adresse));
    }

    public BufferedImage getImage(){
        return img;
    }

    public void setImage(BufferedImage img){
        this.img = img;
    }

    public int getZahl(){
        return zahl;
    }

    public char getCode(){
        return code;
    }

    public void setZahl(int z){
        zahl = z;
    }

    public void setCode(char a){
        code = a;
    }

    public void actionPerformed(ActionEvent e){
        
        Frame.getMaus().setCode(code);
        Frame.getMaus().setZahl(zahl);
         
        System.out.println(code);
        System.out.println(zahl);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(img,0,0,64,64,this);

    }
}