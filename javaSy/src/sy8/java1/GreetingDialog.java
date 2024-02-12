/**
 *
 */
package sy8.java1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GreetingDialog extends JDialog {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 150;

    private JTextField nameField;

    public GreetingDialog(Frame owner) {
        super(owner, "Greeting", true);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // add prompt label and text field
        JLabel nameLabel = new JLabel("Please enter your name:");
        nameField = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        add(inputPanel, BorderLayout.CENTER);

        // add "OK" button
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the input name and show greeting dialog
                String name = nameField.getText().trim();
                dispose(); // close current dialog
                JOptionPane.showMessageDialog(GreetingDialog.this, "Hello, " + name + "!");
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Greeting dialog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton showDialogButton = new JButton("Open greeting dialog");
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GreetingDialog dialog = new GreetingDialog(frame);
                dialog.setVisible(true);
            }
        });
        frame.add(showDialogButton, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        // pop up input name dialog
        String name = JOptionPane.showInputDialog(frame, "Please enter your name:");
        if (name != null) {
            JOptionPane.showMessageDialog(frame, "Hello, " + name + "!");
        }
    }
}
