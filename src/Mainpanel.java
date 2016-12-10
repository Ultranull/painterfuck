import javax.swing.*;
import java.awt.*;

/**
 * Created by usr on 12/9/2016.
 *
 */
public class Mainpanel extends JPanel {

    public Mainpanel(){
        Timer t=new Timer(0,e->repaint());
        t.start();
        JFrame frame=new JFrame("canvas");
        frame.setContentPane(this);
        frame.setVisible(true);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color((int)(Math.random()*0xffffff)));
        g.drawString("dont mind me just setting things up",0,300);
    }
}
