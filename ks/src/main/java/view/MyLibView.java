package view;

import entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyLibView extends JFrame {

    public static User nowUser;
    public static int balance;

    private JPanel contentPane;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JButton loginButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyLibView frame = new MyLibView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MyLibView() {

        setTitle("登录");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("  欢迎来到我的书店");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setBounds(112, 55, 300, 24);
        contentPane.add(titleLabel);

        adminRadioButton = new JRadioButton("管理员");
        adminRadioButton.setBounds(131, 100, 160, 26);
        userRadioButton = new JRadioButton("用户");
        userRadioButton.setBounds(131, 135, 160, 26);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(adminRadioButton);
        radioGroup.add(userRadioButton);
        contentPane.add(adminRadioButton);
        contentPane.add(userRadioButton);

        loginButton = new JButton("确定");
        loginButton.setBounds(181,170,60,30);
        contentPane.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 当点击确定按钮时触发的操作
                if (adminRadioButton.isSelected()) {
                    // 管理员登录逻辑
                    // TODO: 在这里添加管理员登录的代码逻辑
                    BossLogin bossLogin = new BossLogin();
                    bossLogin.setVisible(true);

                } else if (userRadioButton.isSelected()) {
                    // 用户登录逻辑
                    // TODO: 在这里添加用户登录的代码逻辑
                    UserLogin userLogin = new UserLogin();
                    userLogin.setVisible(true);
                }
            }
        });


    }
}
