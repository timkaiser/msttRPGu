import java.io.File;
import javax.sound.sampled.*;

public class Sound{
    private static Clip clip;
    private static FloatControl gainControl;
    static double gain = 0;
    public Sound(String pfad){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(pfad));
            //AudioInputStream ais = AudioSystem.getAudioInputStream(new File("C:\\windows\\media\\chimes.wav"));
            // AudioInputStream ais = AudioSystem.getAudioInputStream(new File("C:\\alarm.wav"));
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format, ((int) ais.getFrameLength() * format.getFrameSize()));
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);

            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            System.out.println(gainControl.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop((int)clip.getMicrosecondLength());
    }

    public static void lautstaerkeErhoehen(){
        //gainControl.setValue(1.0F);
        gain += 0.1;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
        System.out.println(gainControl.getValue() + "/" + gain);

    }

    public static void lautstaerkeSenken(){
        gain -= 0.1;
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
        System.out.println(gainControl.getValue() + "/" + gain);
    }
}