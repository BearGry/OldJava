package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TesterOne {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("first");
        jFrame.setVisible(true);
        jFrame.setBounds(400,300,500,300);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton button = new JButton("click me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame = new JFrame("second");
                jFrame.setVisible(true);
                jFrame.setBounds(500,400,200,150);
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        jFrame.add(button);

    }
}
