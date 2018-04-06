import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AuswahlMenue extends JComboBox implements ActionListener{
    private String[] namen;
    private String focus;
    private AuswahlPanel auswahlPanel;
    public AuswahlMenue(int x,int y,int width,int height,AuswahlPanel auswahlPanel,String[] namen){
        this.namen = namen;
        for(int i = 0; i< namen.length;i++){
            addItem(namen[i]);
        }
        setBounds(x,y,width,height);
        addActionListener(this);

        focus = namen[0];
        
        this.auswahlPanel = auswahlPanel;

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        JComboBox cb = (JComboBox)e.getSource();
        focus = (String)cb.getSelectedItem();
        auswahlPanel.updatePanel();
    }

    public String getFocus(){
        return focus;
    }
}