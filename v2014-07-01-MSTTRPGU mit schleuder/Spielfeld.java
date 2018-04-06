import javax.swing.JPanel;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.RenderingHints;

public class Spielfeld extends JPanel  {
    static Spieler spieler;

    private final GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    private ArrayList<Hindernis> zuBerechnendeHindernisse;
    private ArrayList<Einheit> zuBerechnendeEinheiten;
    private ArrayList<Zeichenobjekt> zuZeichnendeObjekte;
    private ArrayList<SpielerGUI> GUI;
    private ArrayList<Projektil> projektile;

    private BufferedImage offImg;
    static double zoomfaktor = 0.5;
    private Karte welt;
    public Spielfeld(){
        //setBackground(new Color(40,90,30));

        new Bilder();

        setLayout(null);
        setFocusable(true);

        setDoubleBuffered(true);

        spieler = new Spieler(0,160,this);

        zuZeichnendeObjekte = new ArrayList<Zeichenobjekt>();

        projektile = new ArrayList<Projektil>();

        GUI = new ArrayList<SpielerGUI>();
        GUI.add(new GUITopLeiste(0,0,1,0.12,spieler));

        NPC.setSpieler(spieler);
        welt = new Karte();
        spieler.setRespawnpunkte(welt.getRespawnpunkte());
        new Spielschleife(this, spieler);
        repaint();
    }

    public void projektilHinzufuegen(Projektil projektil){
        projektile.add(projektil);
    }

    public void weltAusschnittErmitteln(){
        ArrayList<Chunk> chunks = welt.getChunks((int)spieler.getX(),(int)spieler.getY());
        zuBerechnendeHindernisse = new ArrayList<Hindernis>();
        zuBerechnendeEinheiten = new ArrayList<Einheit>();
        zuBerechnendeEinheiten.add(spieler);
        for(int i=0; i<chunks.size(); i++){
            zuBerechnendeHindernisse.addAll(chunks.get(i).getHindernisse());
            zuBerechnendeEinheiten.addAll(chunks.get(i).getEinheiten());
        }
        zuBerechnendeEinheiten.addAll(projektile);

    }

    public void einheitenPostionBerechnen(){
        for(int i=0; i<zuBerechnendeEinheiten.size(); i++){
            zuBerechnendeEinheiten.get(i).positionBerechnen(zuBerechnendeHindernisse);
        }
        for(int i=0; i<zuBerechnendeEinheiten.size(); i++){
            zuBerechnendeEinheiten.get(i).kollidiertMitSpieler();
        }

        spieler.respawnpunkteErmitteln();
    }

    public void zeichenreihenfolgeFestlegen(){
        zuZeichnendeObjekte = new ArrayList<Zeichenobjekt>();

        for(int i=0; i<zuBerechnendeHindernisse.size(); i++){
            sortiertInZeichenlisteEinfuegen(zuBerechnendeHindernisse.get(i));
        }

        for(int i=0; i<zuBerechnendeEinheiten.size(); i++){
            sortiertInZeichenlisteEinfuegen(zuBerechnendeEinheiten.get(i));
        }

    }

    private void sortiertInZeichenlisteEinfuegen(Zeichenobjekt objekt){
        boolean eingefuegt = false;
        Rectangle r = new Rectangle((int)spieler.getXVerschiebung(), (int) spieler.getYVerschiebung(),16*3,16*2);
        for(int j=0;j<zuZeichnendeObjekte.size()&&!eingefuegt;j++){     
            //if(objekt.grobeKollsion(r)){
            if(zuZeichnendeObjekte.get(j).getEbene()>objekt.getEbene()){
                if(zuZeichnendeObjekte.get(j).getY()+zuZeichnendeObjekte.get(j).getHoehe()>objekt.getY()+objekt.getHoehe()){
                    zuZeichnendeObjekte.add(j, objekt);
                    eingefuegt=true;
                }
            }
            //}
        }
        if(!eingefuegt){
            zuZeichnendeObjekte.add(objekt);
        }
    }

    protected void paintComponent( Graphics g ) { 
        super.paintComponent( g );
        setPreferredSize(null);

        Graphics2D g2 = (Graphics2D)g;

        zoomfaktor = this.getHeight()/(16.0*21);
        //zoomfaktor = 10;
        spieler.verschiebungBerechnen(this,zoomfaktor);

        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(int i=0; i<zuZeichnendeObjekte.size(); i++){
            zuZeichnendeObjekte.get(i).zeichnen(g2,zoomfaktor,spieler.getXVerschiebung(),spieler.getYVerschiebung(),this);
        }

        for(int i = 0; i <GUI.size();i++){
            GUI.get(i).berechnen(getWidth(),getHeight());
            GUI.get(i).zeichnen(g,this);
        }

        requestFocus();
    }

    public ArrayList<Einheit> getZuBerechnendeEinheiten(){
        return zuBerechnendeEinheiten;
    }

    public Spielfeld getSpielfeld(){
        return this;
    }

}
