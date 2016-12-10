import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usr on 12/9/2016.
 */
public class Editor {
    private JTextArea editorArea;
    private JPanel main;
    private JButton runButton;
    private JButton exitButton;
    public Editor(){



        exitButton.addActionListener(e -> {
            if(e.getActionCommand().equals("Exit"))
                System.exit(0);
        });


        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        JFrame frame=new JFrame("editor");
        frame.setContentPane(main);
        frame.setVisible(true);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
