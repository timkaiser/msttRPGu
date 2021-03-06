import java.awt.*;
import javax.swing.*;
import java.util.*;
public class Schwert extends Items{
    private Kollisionsmaske[] maske;
    public Schwert(String name,Spieler spieler){
        super(Bilder.schwerter[2],name,spieler);
        maske = new Kollisionsmaske[4];
        for(int i = 0; i < maske.length;i++){
            maske[i] = new Kollisionsmaske(spieler.getX(),spieler.getY(),Bilder.schwerterMaske[i]);
        }
    }

    public void aktivieren(boolean aktivieren){
        if(aktivieren){
            for(int i = 0; i < maske.length;i++){
                maske[i] = new Kollisionsmaske(besitzer.getX(),besitzer.getY(),Bilder.schwerterMaske[i]);
            }
            kollidiertMitEinheit();
        }
        else{
            
        }
    }

    public void zeichnen(Graphics g,double zoomfaktor,JPanel panel){
        double x = (besitzer.getX()-besitzer.getXVerschiebung())*zoomfaktor;
        double y = (besitzer.getY()-besitzer.getYVerschiebung())*zoomfaktor;
        if(besitzer.getRechts()){
            int bTmp = (int)(((besitzer.getX()-besitzer.getXVerschiebung()+Bilder.schwerter[0].getWidth())*zoomfaktor)-(int)x);
            int hTmp = (int)(((besitzer.getY()-besitzer.getYVerschiebung()+Bilder.schwerter[0].getHeight())*zoomfaktor)-(int)y);
            g.drawImage(Bilder.schwerter[0],(int)(x),(int)(y),bTmp,hTmp,panel);
        }
        else if(besitzer.getLinks()){            
            int bTmp = (int)(((besitzer.getX()-besitzer.getXVerschiebung()+Bilder.schwerter[1].getWidth())*zoomfaktor)-(int)x);
            int hTmp = (int)(((besitzer.getY()-besitzer.getYVerschiebung()+Bilder.schwerter[1].getHeight())*zoomfaktor)-(int)y);
            g.drawImage(Bilder.schwerter[1],(int)(x+besitzer.getBreite()*zoomfaktor-bTmp),(int)(y),bTmp,hTmp,panel);
        }
        else if(besitzer.getOben()){  
            int bTmp = (int)(((besitzer.getX()-besitzer.getXVerschiebung()+Bilder.schwerter[2].getWidth())*zoomfaktor)-(int)x);
            int hTmp = (int)(((besitzer.getY()-besitzer.getYVerschiebung()+Bilder.schwerter[2].getHeight())*zoomfaktor)-(int)y);
            g.drawImage(Bilder.schwerter[2],(int)(x),(int)(y+besitzer.getHoehe()*zoomfaktor-hTmp),bTmp,hTmp,panel);
        }
        else if(besitzer.getUnten()){
            int bTmp = (int)(((besitzer.getX()-besitzer.getXVerschiebung()+Bilder.schwerter[3].getWidth())*zoomfaktor)-(int)x);
            int hTmp = (int)(((besitzer.getY()-besitzer.getYVerschiebung()+Bilder.schwerter[3].getHeight())*zoomfaktor)-(int)y);
            g.drawImage(Bilder.schwerter[3],(int)(x),(int)(y),bTmp,hTmp,panel);
        }
    }

    public void kollidiertMitEinheit(){
        Kollisionsmaske maskeTmp = null;
        if(besitzer.getRechts()){
            maskeTmp = maske[0];
        }
        else if(besitzer.getLinks()){
            maskeTmp = maske[1];
        }
        else if(besitzer.getOben()){
            maskeTmp = maske[2];
        }
        else if(besitzer.getUnten()){
            maskeTmp = maske[3];
        }
        ArrayList<Einheit> tmp = besitzer.getContainer().getZuBerechnendeEinheiten();
        for(int i = 0 ; i < tmp.size();i++){
            if(maskeTmp.kollidiert(tmp.get(i).getKollisionsmaske())){
                tmp.get(i).wirdAngegriffen(besitzer.getX(),besitzer.getY(),20);
            }
        }

    }

}