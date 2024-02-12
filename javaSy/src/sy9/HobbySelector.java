/**
 *
 */
package sy9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HobbySelector extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 150;

    private JCheckBox footballCheckbox;
    private JCheckBox basketballCheckbox;
    private JCheckBox tennisCheckbox;

    private JTextArea resultTextArea;

    public HobbySelector() {
        setTitle("mul-select");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null); // 居中显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加复选框
        footballCheckbox = new JCheckBox("football");
        basketballCheckbox = new JCheckBox("basketball");
        tennisCheckbox = new JCheckBox("tennis");

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(3, 1));
        checkboxPanel.add(footballCheckbox);
        checkboxPanel.add(basketballCheckbox);
        checkboxPanel.add(tennisCheckbox);
        add(checkboxPanel, BorderLayout.CENTER);

        // 添加只读文本框
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        add(scrollPane, BorderLayout.SOUTH);

        // 添加复选框的动作监听器
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = "";
                if (footballCheckbox.isSelected()) {
                    result += "football ";
                }
                if (basketballCheckbox.isSelected()) {
                    result += "basketball ";
                }
                if (tennisCheckbox.isSelected()) {
                    result += "tennis ";
                }
                resultTextArea.setText(result.trim()); // 去除末尾空格
            }
        };
        footballCheckbox.addActionListener(listener);
        basketballCheckbox.addActionListener(listener);
        tennisCheckbox.addActionListener(listener);
    }

    public static void main(String[] args) {
        HobbySelector frame = new HobbySelector();
        frame.setVisible(true);
    }
}

