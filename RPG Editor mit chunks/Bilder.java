import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Bilder{
    private static BufferedImage fuellWerkzeug,malWerkzeug;
    private static String[] ordner;
    private static char[] speicherCodes;
    private static BufferedImage bilder[][],sprites[][][][];// 4 DIMENSIONEN!!!!!!
    public Bilder(){
        File dir = new File("Bilder");
        ordner = dir.list(new FilenameFilter() {
                public boolean accept(File d, String name) {
                    return name.startsWith("Ordner");
                }
            });

        File[] ordnerDurchsuchen = new File[ordner.length];
        String[][] dateienInOrdner = new String[ordner.length][];
        for(int i = 0; i < ordner.length;i++){
            ordnerDurchsuchen[i] = new File("Bilder" + "//" +ordner[i]);            
            dateienInOrdner[i] = ordnerDurchsuchen[i].list(new FilenameFilter() {
                    public boolean accept(File d, String name) {
                        return name.endsWith(".png");
                    }
                });
        }

        speicherCodes = new char[ordner.length];

        for(int i = 0; i < speicherCodes.length;i++){
            speicherCodes[i] = ordner[i].charAt(7);
        }

        sprites = new BufferedImage[ordner.length][][][];

        bilder = new BufferedImage[sprites.length][];

        for(int i = 0; i < sprites.length;i++){
            sprites[i] = new BufferedImage[dateienInOrdner[i].length][][];
            bilder[i] = new BufferedImage[sprites[i].length];
        }

        for(int i = 0; i < bilder.length;i++){
            for(int j = 0; j < bilder[i].length;j++){
                for(int w = 0; w < dateienInOrdner[i].length;w++){
                    bilder[i][j] = bildHinzufuegen("Bilder" + "//" + ordner[i] +"//"+ dateienInOrdner[i][j]);
                }
            }
        }

        for(int i = 0;i < sprites.length;i++){
            for(int j = 0; j < sprites[i].length;j++){
                sprites[i][j] = new BufferedImage[bilder[i][j].getHeight()/16][bilder[i][j].getWidth()/16];
                for(int w = 0; w < sprites[i][j].length;w++){
                    for(int k = 0; k < sprites[i][j][w].length;k++){
                        sprites[i][j][w][k] = bildZerlegen(bilder[i][j],k*16,w*16,16,16);
                    }
                }
            }
        }

        fuellWerkzeug = bildHinzufuegen("Farbeimer.png");
        malWerkzeug = bildHinzufuegen("Bleistift.png");

    }

    public static BufferedImage getFuellWerkzeug(){
        return fuellWerkzeug;
    }

    public static BufferedImage getMalWerkzeug(){
        return malWerkzeug;
    }

    public BufferedImage bildHinzufuegen(String pfad){
        BufferedImage img = null;
        try {       img = ImageIO.read(getClass().getResource(pfad));
        } catch (IOException e) {}
        return  img;
    }

    public BufferedImage bildZerlegen(BufferedImage image, int x, int y, int b, int h){
        BufferedImage img = null;
        img = image.getSubimage(x,y,b,h);
        return  img;
    }

    public static int getHintergrundBilderHoeheAnzahl(int index){
        int counter = 0;

        for(int j = 0; j < sprites[index].length;j++){
            for(int w = 0; w < sprites[index][j].length;w++){

                counter+=sprites[index][j].length;
                w = sprites[index][j].length;

            }
        }

        return counter;
    }

    public static String[] getOrdner(){
        return ordner;
    }

    public static BufferedImage[][][][] getSprites(){
        return sprites;
    }

    public static char[] getSpeicherCode(){
        return speicherCodes;
    }

    public static BufferedImage getSprite(char code,int zahl){
        int index = 0;
        for(int i = 0; i< speicherCodes.length;i++){
            if(code == speicherCodes[i]){
                index = i;
            }
        }

        int counter=zahl;
        BufferedImage img = null;

        for(int j = 0; j < sprites[index].length;j++){
            for(int w = 0; w < sprites[index][j].length;w++){
                for(int k = 0; k < sprites[index][j][w].length;k++){

                    if(counter <= 0){
                        img = sprites[index][j][w][k];
                        break;
                    }
                    counter--;
                }
                if(img != null){
                    break;
                }
            }
            if(img!= null){
                break;
            }
        }

        return img;
    }

}