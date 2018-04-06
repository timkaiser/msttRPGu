import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
public class Credits extends Hauptmenue implements ActionListener{
    private Knopf zurueck, christoph, tim, derAuslaender;
    private Font font;
    private Sound klicksound;
    public Credits(Fenster container){
        super(container);
        setBackground(Color.GREEN);
        setLayout(null);
        setFocusable(true);

        InputStream fin = this.getClass().getResourceAsStream("burnstown dam.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        zurueck=new Knopf("Zurück", 1.0/2.0, 5.0/6.0, 1.0/2.0, 1.0/6.0, font, this);
        zurueck.addActionListener(this);
        christoph=new Knopf("Christoph", 1.0/4.0, 1.0/10.0, 1.0/2.0, 1.0/4.0, font, this);//No Knopf
        christoph.setEnabled(false);//No disabled, no Behinderter
        tim=new Knopf("Tim", 1.0/1.3, 1.0/10.0, 1.0/2.0, 1.0/4.0, font, this);
        tim.setEnabled(false);
        derAuslaender= new Knopf("Der Ausländer", 1.0/7.0, 1.0/1.1, 1.0/2.0, 1.0/15.0, font, this);
        derAuslaender.setEnabled(false);

        klicksound=new Sound("fins__button.wav");

        setVisible(true);
        Hauptmenue.knoepfeEntfernen();
    }

    public void actionPerformed(ActionEvent e){
        klicksound.start();
        if(e.getSource()==zurueck){
            container.remove(this);
            container.add(new Hauptmenue(container));
            container.revalidate();
            container.repaint();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setPreferredSize(null);
        christoph.skallieren(getWidth(), getHeight());
        tim.skallieren(getWidth(), getHeight());
        derAuslaender.skallieren(getWidth(), getHeight());
        zurueck.skallieren(getWidth(), getHeight());
    }

}