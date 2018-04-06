import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser; 
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener; 
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import javax.swing.filechooser.FileNameExtensionFilter; 
public class Menue extends JMenuBar{
    private JMenu menu;
    private JMenuItem neu,speichern,oeffnen,groesse;
    private Frame f;
    private Gitter[][][] gitter;
    public Menue(Frame frame,Gitter[][][] gitter){
        menu = new JMenu("MENÜ");

        

        f = frame;
        neu = new JMenuItem("NEU");
        neu.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int breite = Integer.parseInt(JOptionPane.showInputDialog("Bitte breite des Levels eingeben!"));
                    int hoehe = Integer.parseInt(JOptionPane.showInputDialog("Bitte hoehe des Levels eingeben!"));
                    f.dispose();
                    new Frame(breite,hoehe);
                }
            });
        menu.add(neu);

        speichern = new JMenuItem("SPEICHERN");
        speichern.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    String pfad;
                    JFileChooser chooser;

                    pfad = System.getProperty("user.home");
                    File file = new File(pfad.trim());

                    chooser = new JFileChooser(pfad);
                    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                    FileNameExtensionFilter plainFilter = new FileNameExtensionFilter(
                            "lvl", "lvl");
                    FileNameExtensionFilter markUpFilter = new FileNameExtensionFilter(
                            "lvl","lvl");

                    chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
                    chooser.setFileFilter(plainFilter);
                    chooser.setFileFilter(markUpFilter);
                    chooser.setDialogTitle("Speichern unter...");
                    chooser.setVisible(true);

                    int result = chooser.showSaveDialog(f);

                    if (result == JFileChooser.APPROVE_OPTION) {

                        pfad = chooser.getSelectedFile().toString();
                        file = new File(pfad);

                        chooser.setVisible(false);
                    }
                    chooser.setVisible(false);

                    speichern(pfad);
                }
            });
        menu.add(speichern);    

        oeffnen = new JMenuItem("OEFFNEN");
        oeffnen.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    final JFileChooser chooser = new JFileChooser("Verzeichnis wählen");
                    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
                    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    final File file = new File("/home");

                    chooser.setCurrentDirectory(file);

                    chooser.addPropertyChangeListener(new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent e) {
                                if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
                                || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                                    final File f = (File) e.getNewValue();
                                }
                            }
                        });

                    chooser.setVisible(true);
                    final int result = chooser.showOpenDialog(null);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        File inputVerzFile = chooser.getSelectedFile();
                        String inputVerzStr = inputVerzFile.getPath();
                        System.out.println("Eingabepfad:" + inputVerzStr);
                        zeichnen(inputVerzStr);
                    }
                    System.out.println("Abbruch");
                    chooser.setVisible(false); 
                }
            });
        menu.add(oeffnen);

        groesse = new JMenuItem("GRÖSSE");
        groesse.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){

                }

            });
        menu.add(groesse);

        this.gitter = gitter;

        add(menu);
    }

    public void speichern(String verzeichnis){
        try {   
            FileOutputStream output = new FileOutputStream(verzeichnis+".lvl");
            DataOutputStream  datop  = new DataOutputStream(output);
            int anzahl=0;

            for(int i = 0;i < gitter.length;i++){
                for(int j = 0;j < gitter[i].length;j++){
                    for(int w = 0; w < gitter[i][j].length;w++){
                        if(gitter[i][j][w].getCode()!='l'){
                            anzahl++;
                        }
                    }
                }
            }
            datop.writeInt(gitter[0].length);
            datop.writeInt(gitter[0][0].length);
            datop.writeInt(anzahl);

            for(int i = 0;i < gitter.length;i++){
                for(int j = 0;j < gitter[i].length;j++){
                    for(int w = 0; w < gitter[i][j].length;w++){
                        if(gitter[i][j][w].getCode()!='l'){
                            datop.writeChar(gitter[i][j][w].getCode());
                            datop.writeInt(gitter[i][j][w].getZahl());
                            datop.writeInt(j*Frame.getGITTERZAHL());
                            datop.writeInt(w*Frame.getGITTERZAHL());
                        }
                    }
                }
            }

        } catch (IOException ev) {ev.printStackTrace();}    
    }

    public Gitter welchesZeichenGitter(char code,int x,int y){
        int index = 0;
        for(int i = 0;i < Bilder.getSpeicherCode().length;i++){
            if(code == Bilder.getSpeicherCode()[i]){
                index = i;
            }
        }
        return gitter[index][x/Frame.getGITTERZAHL()][y/Frame.getGITTERZAHL()];   
    }

    public void zeichnen(String verz){
        try {
            FileInputStream iostream = new FileInputStream(verz);
            DataInputStream diostream = new DataInputStream(iostream);
            try {
                groesse(diostream.readInt(),diostream.readInt());
                int anzahl=diostream.readInt();
                for(int i=0;i<anzahl;i++){
                    char read = diostream.readChar();
                    int readZahl = diostream.readInt();
                    int x = diostream.readInt(),y = diostream.readInt();
                    
                    Gitter tmp = welchesZeichenGitter(read,x,y);
                    tmp.setCode(read);
                    tmp.setZahl(readZahl);
                }   
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {}
        for(int i = 0; i < f.getZeichenPanel().length;i++){
            f.getZeichenPanel()[i].repaint();
        }
    }

    public void groesse(int breite,int hoehe){

    }
}