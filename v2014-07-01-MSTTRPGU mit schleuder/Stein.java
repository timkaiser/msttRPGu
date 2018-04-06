public class Stein extends Projektil{

    public Stein(int x,int y,int xBeschleunigung,int yBeschleunigung){
        super(new Animation(Bilder.stein),
                new Animation(Bilder.stein),
                new Animation(Bilder.stein),
                new Animation(Bilder.stein),
                Bilder.steinMaske,x,y,5,5, xBeschleunigung,yBeschleunigung);
    }

}