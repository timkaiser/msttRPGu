import javax.swing.*;
import java.awt.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.DisplayMode;
public class Fenster {
    private Hauptmenue menue;
    private static DisplayMode displayMode;
    private static GraphicsEnvironment env;
    private static GraphicsDevice device;
    private static int fensterX=806;
    private static int fensterY=633;
    private static Sound musik;
    private static JFrame fenster;
    public Fenster(){
        fenster = new JFrame();
        fenster.setSize(fensterX,fensterY);
        fenster.setLocationRelativeTo(null);        
        fenster.setResizable(false);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        menue = new Hauptmenue(this);
        fenster.add(menue);
        //musik=new Sound("File Select - Super Mario 64.wav");
        //musik.loop();
        //fullscreenAn();
        Optionsmenue.einstellungenLaden();
        fenster.setVisible(true);
    }    

    public static void fullscreenAn(){
        fenster.dispose();
        fenster.setSize(fensterX,fensterY);
        fenster.setLocationRelativeTo(null);        
        fenster.setResizable(false);
        fenster.setUndecorated(true);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        env=GraphicsEnvironment.getLocalGraphicsEnvironment();
        device=env.getDefaultScreenDevice();
        displayMode=device.getDisplayMode();
        device.setFullScreenWindow(fenster); 
        device.setDisplayMode(displayMode);      
    }

    public static void fullscreenAus(){
        fenster.dispose();
        fenster. setSize(fensterX,fensterY);
        fenster.setLocationRelativeTo(null);        
        fenster.setResizable(false);
        fenster.setUndecorated(false);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setVisible(true);
        device.setFullScreenWindow(null);
    }

    public static void add(Component c){
        fenster.add(c);
    }

    public static void remove(Component c){
        fenster.remove(c);
    }

    public static void revalidate(){
        fenster.revalidate();
    }

    public static void repaint(){
        fenster.repaint();
    }

    public static int getFensterX(){
        return fensterX;
    }

    public static int getFensterY(){
        return fensterY;
    }

    public void setFensterX(int x){
        fensterX=x;
        fenster.setSize(fensterX, fensterY);
    }

    public void setFensterY(int y){
        fensterY=y; 
        fenster.setSize(fensterX, fensterY);
    }

    public static void musikHalt(){
        //    musik.stop();
    }

    public static void musikWeiter(String welche){
        //musik=new Sound(welche);
        //musik.loop();
    }

}
