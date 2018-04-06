import java.util.*;

public class Mensch extends NPC{
    private GUITextFeld text;
    int cooldown;
    public Mensch(int x,int y,GUITextFeld text){
        super(new Animation(Bilder.getSpielerbild(2),19,150),
            new Animation(Bilder.getSpielerbild(1),19,150),
            new Animation(Bilder.getSpielerbild(3),19,150),
            new Animation(Bilder.getSpielerbild(0),19,150),
            Bilder.getSpielermaske(),x,y,19,33);
        this.text = text;
    }

    public void positionBerechnen(ArrayList<Hindernis> hindernisse){
        super.positionBerechnen(hindernisse);
        cooldown-=25;
    }

    public void kollidiertMitSpieler(){

    }

    public void wirdAngegriffen(double x,double y,int schaden){
        if(cooldown<0){
            spieler.setText(text);
            Spielschleife.pausieren();
            cooldown=100;
        }
    }

}