package gui.chapter09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingApplication {
    public static void main(String[] args) {
        JFrame f = new JFrame("simple swing application");
        Container contenPane = f.getContentPane();
        contenPane.setLayout(new GridLayout(2,1));
        JButton button = new JButton("click me");
        final JLabel label = new JLabel();
        contenPane.add(button);
        contenPane.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String information = JOptionPane.showInputDialog("请输入一串字符");
                label.setText(information);
            }
        });
        f.setSize(200,100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
