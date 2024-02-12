/**
 *
 */
package sy8.java2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmbitionSelector extends JDialog {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 150;

    private JRadioButton managerButton;
    private JRadioButton engineerButton;
    private JRadioButton teacherButton;

    public AmbitionSelector(Frame owner) {
        super(owner, "Ambition select", true);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // 添加单选按钮组
        managerButton = new JRadioButton("manager");
        engineerButton = new JRadioButton("engineer");
        teacherButton = new JRadioButton("teacher");

        ButtonGroup group = new ButtonGroup();
        group.add(managerButton);
        group.add(engineerButton);
        group.add(teacherButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(managerButton);
        buttonPanel.add(engineerButton);
        buttonPanel.add(teacherButton);
        add(buttonPanel, BorderLayout.CENTER);

        // 添加“确定”按钮
        JButton okButton = new JButton("enter");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取选中的单选按钮文本
                String ambition = "";
                if (managerButton.isSelected()) {
                    ambition = "manager";
                } else if (engineerButton.isSelected()) {
                    ambition = "engineer";
                } else if (teacherButton.isSelected()) {
                    ambition = "teacher";
                }

                if (!ambition.isEmpty()) {
                    dispose(); // 关闭当前对话框
                    JOptionPane.showMessageDialog(AmbitionSelector.this, "you will be：" + ambition);
                } else {
                    JOptionPane.showMessageDialog(AmbitionSelector.this, "please select an ambition!");
                }
            }
        });
        JPanel okPanel = new JPanel();
        okPanel.add(okButton);
        add(okPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton showDialogButton = new JButton("begin");
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AmbitionSelector dialog = new AmbitionSelector(frame);
                dialog.setVisible(true);
            }
        });
        frame.add(showDialogButton, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}

