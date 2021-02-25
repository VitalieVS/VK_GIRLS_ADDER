import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    private JButton addFriends;
    private JPanel panelMain;
    static String ACCESS_TOKEN = "24723cc38f918b011d76e705109ded4110acdb469d512e95335d4f29606920cae89314e3248928d3a5dc2";
    public Main() {
        addFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateList list = new CreateList();

               System.out.println(
                       list.createList(list.getData())
               );

               AddFriends addFriends = new AddFriends();

                try {
                    addFriends.addFriends(list.createList(list.getData()));
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Girls!");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
