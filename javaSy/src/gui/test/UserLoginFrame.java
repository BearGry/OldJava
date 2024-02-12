package gui.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginFrame extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;

    public UserLoginFrame() {
        // 设置窗口标题
        setTitle("用户登录");

        // 创建用户名标签和文本框
        JLabel usernameLabel = new JLabel("用户名:");
        usernameTextField = new JTextField(20);

        // 创建密码标签和密码框
        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(20);

        // 创建登录按钮，并添加事件监听器
        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的用户名和密码
                String username = usernameTextField.getText();
                String password = new String(passwordField.getPassword());

                // 在这里编写验证逻辑，判断用户名和密码是否正确
                boolean isValid = checkUsernameAndPassword(username, password);

                if (isValid) {
                    // 登录成功，跳转到下一个页面
                    JOptionPane.showMessageDialog(UserLoginFrame.this, "登录成功！");

                    // 在这里执行进入下一个页面的操作
                    // ...
                } else {
                    // 登录失败，弹出提示框
                    JOptionPane.showMessageDialog(UserLoginFrame.this, "登录失败，请重试！");
                }
            }
        });

        // 创建面板，用于容纳控件
        JPanel panel = new JPanel(new GridBagLayout());

        // 设置面板背景颜色
        panel.setBackground(new Color(245, 245, 245));

        // 创建 GridBagConstraints 对象，用于控制组件的布局
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // 添加用户名标签和文本框到面板
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameTextField, gbc);

        // 添加密码标签和密码框到面板
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        // 添加登录按钮到面板
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        // 将面板添加到窗口的内容面板中
        getContentPane().add(panel);

        // 设置窗口大小和位置，并显示窗口
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // 验证用户名和密码的方法，您可以根据实际情况进行修改
    private boolean checkUsernameAndPassword(String username, String password) {
        return "admin".equals(username) && "admin123".equals(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 设置窗口风格为系统默认风格
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new UserLoginFrame();
            }
        });
    }
}
