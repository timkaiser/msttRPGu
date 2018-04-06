import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
public class Pausenmenue extends JPanel implements ActionListener{
    public static JFrame container;
    private static Knopf fortsetzen, optionen, zumHauptmenue, beenden, titel;
    private Font font;
    private Sound klicksound;
    public Pausenmenue(){
        setBounds(0,0,800,600);
        //setBackground(new Color(0,0,0,0));
        setLayout(null);
        container=Fenster.getFenster();;

        InputStream fin = this.getClass().getResourceAsStream("burnstown dam.ttf");
        try {font = Font.createFont ( Font.PLAIN,fin).deriveFont(24f);}
        catch (FontFormatException e) {e.printStackTrace();} 
        catch (IOException e) {e.printStackTrace();}

        fortsetzen = new Knopf("Fortsetzen",1.0/2.0, 2.0/6.0, 1.0, 1.0/5.8,font,this);
        fortsetzen.addActionListener(this);

        optionen = new Knopf("Optionen",1.0/2.0, 3.0/6.0, 1.0, 1.0/6.0,font,this);
        optionen.addActionListener(this);
        optionen.setEnabled(false);

        zumHauptmenue = new Knopf("Zum Hauptmenü",1.0/2.0, 4.0/6.0, 1.0, 1.0/5.8,font,this);
        zumHauptmenue.addActionListener(this);

        beenden = new Knopf("Beenden",1.0/2.0, 5.0/6.0, 1.0, 1.0/6.0,font,this);
        beenden.addActionListener(this);

        titel=new Knopf("PAUSE", 1.0/2.0, 1.0/1000.0, 15.0, 1.0/3.0, font, this);
        titel.setEnabled(false);

        klicksound=new Sound("fins__button.wav");
        setVisible(true);
    }

    public static void knoepfeEntfernen(){
        fortsetzen.setVisible(false);
        zumHauptmenue.setVisible(false);
        optionen.setVisible(false);
        beenden.setVisible(false);
    }

    public void actionPerformed(ActionEvent e){
        if(Optionsmenue.soundAnAus()==true){
            klicksound.start();
        }
        if(e.getSource() == fortsetzen){
            Spielschleife.spielPausieren();
            Steuerung.setPauseAn(false);
            Fenster.pausenmenueAufrufen(false);
        }
        else if(e.getSource() == optionen){
            knoepfeEntfernen();
            Fenster.optionsmenueAufrufen();
        }
        else if(e.getSource() == zumHauptmenue){
            Fenster.hauptmenueAufrufen();
        }
        else if(e.getSource() == beenden){
            Optionsmenue.einstellungenSpeichern();
            System.exit(0);
        }
    }    

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );        
        setPreferredSize(null);
        fortsetzen.skallieren(getWidth(),getHeight());
        optionen.skallieren(getWidth(),getHeight());
        zumHauptmenue.skallieren(getWidth(),getHeight());
        beenden.skallieren(getWidth(),getHeight());
        titel.skallieren(getWidth(), getHeight());
    }
}