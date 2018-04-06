import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;
public class Frame extends JFrame{
    private static Maus maus;
    private Gitter[][][] gitter;
    private static final int GITTERZAHL = 16;
    private static int gitterZahl = 16;
    private Menue menue;
    private AuswahlPanel auswahlPanel;
    private Toolbar toolbar;
    private static ZeichenPanel[] zPanel;
    private ScrollPane[] zeichenPane;
    private JTabbedPane tPane;
    private WerkzeugKnopf fuellen,malen;
    public Frame(int breite,int hoehe){
        setSize(1150,700);
        setLocationRelativeTo(null);
        setTitle("Editor");
        setDefaultCloseOperation(3); // 3 == JFrame.EXIT_ON_CLOSE
        setLayout(null);
        new Bilder();

        fuellen = new WerkzeugKnopf(Bilder.getFuellWerkzeug(),new WerkzeugFuellen());
        malen = new WerkzeugKnopf(Bilder.getMalWerkzeug(),new WerkzeugMalen());
        maus = new Maus();
        toolbar = new Toolbar(0,0,getWidth(),25,this);
        toolbar.addKnopf(fuellen);
        toolbar.addKnopf(malen);
        add(toolbar);
        auswahlPanel = new AuswahlPanel(0,toolbar.getHeight(),250,500);
        add(auswahlPanel);

        //Knöpfe adden
        BufferedImage[][][][] sprites = Bilder.getSprites(); 
        int counter = 0;
        for(int i = 0; i < sprites.length;i++){
            for(int j = 0; j < sprites[i].length;j++){
                for(int w = 0; w < sprites[i][j].length;w++){
                    for(int k = 0; k < sprites[i][j][w].length;k++){
                        auswahlPanel.addKnopf(i,new Knopf(Bilder.getSpeicherCode()[i],counter,sprites[i][j][w][k]));
                        counter++;
                    }
                }
            }
            counter = 0;
        }

        gitter = new Gitter[sprites.length][breite][hoehe];

        for(int i = 0; i < gitter.length;i++){
            for(int j = 0; j < gitter[i].length;j++){
                for(int w = 0; w < gitter[i][j].length;w++){
                    gitter[i][j][w] = new Gitter(j*GITTERZAHL,w*GITTERZAHL,GITTERZAHL,GITTERZAHL);
                }
            }
        }

        menue = new Menue(this,gitter);
        setJMenuBar(menue);

        zPanel = new ZeichenPanel[sprites.length];
        zeichenPane = new ScrollPane[zPanel.length];

        for(int i = 0; i < zPanel.length;i++){
            zPanel[i] = new ZeichenPanel(0,0,gitter[i],gitter);
        }

        for(int i = 0; i < zeichenPane.length;i++){
            zeichenPane[i] = new ScrollPane(zPanel[i],gitter[i],gitter,zPanel);
            zeichenPane[i].setBounds(0,0,800,600);
            zPanel[i].addContainer(zeichenPane[i]);
        }

        tPane = new JTabbedPane();
        tPane.setBounds(auswahlPanel.getWidth(),toolbar.getHeight(),800,600);
        for(int i = 0; i< zeichenPane.length;i++){
            //tPane.addTab(Bilder.getOrdner()[i],zeichenPane[i]);
            tPane.add(Bilder.getOrdner()[i],zeichenPane[i]);

        }
        add(tPane);
        setVisible(true);
        zeichenPane[0].requestFocus();
    }

    public static void main(String[] args){
        new Frame(50,50);
    }

    public static int getGITTERZAHL(){
        return GITTERZAHL;
    }

    public static int getGitterZahl(){
        return gitterZahl;
    }

    public static void setGitterZahl(int neueGitterZahl){
        gitterZahl = neueGitterZahl;
    }

    public static Maus getMaus(){
        return maus;
    }

    public static ZeichenPanel[] getZeichenPanel(){
        return zPanel;
    }

    public void repaintZeichenPanel(){
        for(int i = 0; i < zPanel.length;i++){
            zPanel[i].repaint();
        }
    }
    
}
