

import javax.swing.*;
import java.awt.*;
public class CanvasPanel extends JPanel {
    private Color[][] canvas;
    private Interpreter inter;
    public CanvasPanel(Interpreter i){
        inter=i;
        canvas=inter.getCanvas();
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
        canvas=inter.getCanvas();
        int size=(600/inter.LENGTH);
        for(int r=0;r<inter.LENGTH;r++) {
            for (int c = 0; c < inter.LENGTH; c++) {
                g.setColor(canvas[r][c]);
                g.fillRect(r * size, c * size, size, size);

            }
        }

    }
}
