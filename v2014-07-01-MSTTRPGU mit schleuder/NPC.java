import java.awt.image.BufferedImage;
import java.util.*;

public class NPC extends Einheit{

    protected static Spieler spieler;
    protected boolean amLeben;

    public NPC(Animation animationL,Animation animationR,Animation animationO,Animation animationU, BufferedImage maske, double x, double y, int breite, int hoehe){
        super(animationL,animationR,animationO,animationU,maske,x,y,breite,hoehe);
        amLeben=true;
    }

    public static void setSpieler(Spieler sp){
        spieler = sp;
    }
    
}