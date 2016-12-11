import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

/**
 * Created by usr on 12/9/2016.
 *
 */
public class Editor {
    private JPanel main;
    private JTabbedPane tabbedPane1;
    private JEditorPane codeInput;
    private JTable table1;
    private JMenuBar menu;

    private Interpreter inter;
    public boolean resetc=false;

    public Editor(Interpreter i){
        inter=i;
        createUIComponents();


        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        JFrame frame=new JFrame("editor");
        frame.setContentPane(main);
        frame.setJMenuBar(menu);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        menu=new JMenuBar();
        JMenuItem save=new JMenuItem("save");
        JMenuItem load=new JMenuItem("load");
        JMenuItem reset=new JMenuItem("reset");
        JMenuItem exit=new JMenuItem("exit");
        JMenuItem run=new JMenuItem("run");
        ActionListener a=e -> {
            switch (e.getActionCommand()){
                case "save":save();break;
                case "load":load();break;
                case "reset":inter.reset();break;
                case "exit":
                    System.exit(0);
                    break;
                case "run":run();break;
            }
        };
        save.addActionListener(a);
        load.addActionListener(a);
        exit.addActionListener(a);
        run.addActionListener(a);
        reset.addActionListener(a);
        menu.add(save);
        menu.add(load);
        menu.add(reset);
        menu.add(run);
        menu.add(exit);

    }
    private void save(){System.out.println("saving");}
    private void load(){System.out.println("loading");}
    private void run(){
        inter.interpret(codeInput.getText());
    }

}
