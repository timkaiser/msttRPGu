import java.awt.Graphics;
import java.util.*;

public class Chunk{
    private ArrayList<Hindernis> hindernisse;
    private ArrayList<Einheit> einheiten;
    public Chunk(ArrayList<Hindernis> hindernisse, ArrayList<Einheit> einheiten){
        this.hindernisse = hindernisse;
        this.einheiten = einheiten;
    }

    public ArrayList<Hindernis> getHindernisse(){
        return hindernisse;
    }

    public ArrayList<Einheit> getEinheiten(){
        return einheiten;
    }
}