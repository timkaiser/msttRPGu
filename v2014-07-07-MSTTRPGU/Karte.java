import java.awt.Graphics;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.RenderingHints;
import java.awt.*;

public class Karte{
    private Chunk[][] chunks; 
    ArrayList<Respawnpunkt> respawnpunkte;
    FileInputStream iostream;
    DataInputStream diostream;
    final int chunkbreite = 16*25;
    final int chunkhoehe = 16*15;
    
    private final GraphicsConfiguration gfxConf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public Karte(){
        levelEinlesen();
    }

    private void levelEinlesen(){
        ArrayList<Hindernis> hindernisse = new ArrayList<Hindernis>();
        ArrayList<Hindernis> hintergrund = new ArrayList<Hindernis>();
        ArrayList<Einheit> einheiten = new ArrayList<Einheit>();
        respawnpunkte = new ArrayList<Respawnpunkt>();

        int breite = 0;
        int hoehe = 0;

        try {
            iostream = new FileInputStream("level.lvl");
            diostream = new DataInputStream(iostream);
            try {
                breite=diostream.readInt()*64;
                hoehe=diostream.readInt()*64;
                int anzahl=diostream.readInt();
                for(int i=0;i<anzahl;i++){
                    char read1 = diostream.readChar();
                    int  read2 = diostream.readInt();  
                    switch(read1){
                        case('F'):
                        switch(read2){
                            default://Foreground/Hindernisse
                            hindernisse.add(new StandartHindernis(Bilder.getSprite(read1, read2),Bilder.getMaske(read1, read2),diostream.readInt(),diostream.readInt(),16,16,5));
                            break;
                        }break;
                        case('B')://Background/Hintergrund
                        switch(read2){
                            default:
                            hintergrund.add(new StandartHindernis(Bilder.getSprite(read1, read2),Bilder.getMaske(read1, read2),diostream.readInt(),diostream.readInt(),16,16,0));
                            break;
                        }break;
                        case('G')://Gegner
                        switch(read2){
                            case 0:
                            einheiten.add(new Spinne(diostream.readInt(),diostream.readInt()));
                            break;
                            case 1:
                            einheiten.add(new Ratte(diostream.readInt(),diostream.readInt()));
                            break;
                            case 2:
                            einheiten.add(new Fledermaus(diostream.readInt(),diostream.readInt()));
                            break;
                        }break;
                        case('S')://Spezial
                        switch(read2){ 
                            case 0:
                            respawnpunkte.add(new Respawnpunkt(diostream.readInt(),diostream.readInt()));                            
                            break;
                            case 1:
                            int x = diostream.readInt();
                            int y = diostream.readInt();
                            Spielfeld.spieler.setX(x);
                            Spielfeld.spieler.setY(y);
                            respawnpunkte.add(new Respawnpunkt(x,y));   
                            break;

                        }
                        break;
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {}

        einheiten.add(new Spinne(320.0,320.0));
        // einheiten.add(new Mensch(400,200,new GUITextFeld("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70")));
        einheiten.add(new Mensch(200,200,new GUITextFeld("Hallo Fremder. Was bringt dich in unsere Gegend? Das ist eine sehr gefährliche Gegend hier. Du solltest aufpassen... sonst könnte dir noch etwas zustoßen hehehe. Was? Warum ich so lache? Öhm. So halt... Egal. Geht dich ja auch nichts an! Lass mich in ruhe...")));
        //hindernisse.add(new StandartHindernis(new Animation(Bilder.getBaumbild(),48,100000),Bilder.getSpielermaske(),320,320,48,48,5));

        inChunksAufteilen(hindernisse, hintergrund, einheiten, breite, hoehe);
    }

    private void inChunksAufteilen(ArrayList<Hindernis> hindernisse, ArrayList<Hindernis> hintergrund, ArrayList<Einheit> einheiten, int breite, int hoehe){
        chunks = new Chunk[(breite/chunkbreite)+1][(hoehe/chunkhoehe)+1];
        for(int i=0; i< chunks.length; i++){
            for(int j=0; j< chunks[i].length; j++){
                int x = i*chunkbreite;
                int y = j*chunkhoehe;

                ArrayList<Hindernis> chunkHindernisse = new ArrayList<Hindernis>();
                ArrayList<Hindernis> chunkHintergrund = new ArrayList<Hindernis>();
                ArrayList<Einheit> chunkEinheiten = new ArrayList<Einheit>();

                for(int k=0; k<hintergrund.size(); k++){
                    if( (hintergrund.get(k).getX()>=x && hintergrund.get(k).getX()<x+chunkbreite) && 
                    (hintergrund.get(k).getY()>=y && hintergrund.get(k).getY()<y+chunkhoehe)){
                        chunkHintergrund.add(hintergrund.get(k));
                    }
                }
                BufferedImage background = gfxConf.createCompatibleImage( chunkbreite, chunkhoehe );

                for(int k=0; k<chunkHintergrund.size(); k++){
                    chunkHintergrund.get(k).zeichnen(background.createGraphics(), 1, x, y, null);
                }
                chunkHindernisse.add(new StandartHindernis(background,Bilder.getMaske('B',0),x,y,chunkbreite,chunkhoehe,0));

                for(int k=0; k<hindernisse.size(); k++){
                    if( (hindernisse.get(k).getX()>=x && hindernisse.get(k).getX()<x+chunkbreite) && 
                    (hindernisse.get(k).getY()>=y && hindernisse.get(k).getY()<y+chunkhoehe)){
                        chunkHindernisse.add(hindernisse.get(k));
                    }
                }

                for(int k=0; k<einheiten.size(); k++){
                    if( (einheiten.get(k).getX()>=x && einheiten.get(k).getX()<x+chunkbreite) && 
                    (einheiten.get(k).getY()>=y && einheiten.get(k).getY()<y+chunkhoehe)){
                        chunkEinheiten.add(einheiten.get(k));
                    }
                }

                chunks[i][j] = new Chunk(chunkHindernisse, chunkEinheiten);
            }
        }
    }

    public ArrayList<Chunk> getChunks(int spielerX, int spielerY){
        int x = spielerX/chunkbreite;
        int y = spielerY/chunkhoehe;

        ArrayList<Chunk> rueckgabe = new ArrayList<Chunk>();

        if(y>0){
            if(x>0){
                rueckgabe.add(chunks[x-1][y-1]);
            }
            rueckgabe.add(chunks[x][y-1]);
            if(x<chunks.length-1){
                rueckgabe.add(chunks[x+1][y-1]);
            }
        } 

        if(x>0){
            rueckgabe.add(chunks[x-1][y]);
        }
        rueckgabe.add(chunks[x][y]);
        if(x<chunks.length-1){
            rueckgabe.add(chunks[x+1][y]);
        }

        if(y<chunks[0].length-1){
            if(x>0){
                rueckgabe.add(chunks[x-1][y+1]);
            }
            rueckgabe.add(chunks[x][y+1]);
            if(x<chunks.length-1){
                rueckgabe.add(chunks[x+1][y+1]);
            }
        }

        return rueckgabe;
    }

    public ArrayList<Respawnpunkt> getRespawnpunkte(){
        return respawnpunkte;
    }

}