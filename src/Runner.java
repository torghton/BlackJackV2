import javax.swing.*;
import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Table table = new Table(new Dimension(800, 800));

        // Adds the panels to the frame
        frame.add(table);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        // Sets frame settings
        frame.setTitle("BackJack");
        frame.setLocation(100, 100);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.setSize(800,800);

        table.gameLoop(10);

    }
}