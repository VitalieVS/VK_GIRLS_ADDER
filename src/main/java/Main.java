import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JButton addFriends;
    private JPanel panelMain;

    public Main() {
        addFriends.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateList list =
                        new CreateList(
                                "5e54954f0482784f8299d866c2e1307e12a435afebe0f6a948314815491c71f9e38136b62438983c7a694"
                        );

               System.out.println(
                       list.createList(list.getData())
               );

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Анти Мымра");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
