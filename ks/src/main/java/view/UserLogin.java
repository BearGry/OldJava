package view;

import dao.DBTalk;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JFrame {
    private JPanel contentPane;
    private JTextField userIDField;
    private JPasswordField passwordField;
    private DBTalk dbTalk = new DBTalk();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserLogin frame = new UserLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UserLogin() {
        setTitle("用户登录");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 443, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUserID = new JLabel("用户ID");
        lblUserID.setBounds(112, 70, 43, 15);
        contentPane.add(lblUserID);

        userIDField = new JTextField();
        userIDField.setBounds(151, 67, 160, 21);
        contentPane.add(userIDField);
        userIDField.setColumns(10);

        JLabel lblPassword = new JLabel("密码");
        lblPassword.setBounds(112, 110, 43, 15);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(151, 107, 160, 21);
        contentPane.add(passwordField);

        JButton loginBtn = new JButton("登录");
        loginBtn.setBounds(151, 160, 74, 23);
        contentPane.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer userID = Integer.valueOf(userIDField.getText());
                String password = new String(passwordField.getPassword());

                // 验证用户ID和密码的逻辑
                // TODO: 在这里添加验证逻辑，例如从数据库中查询用户信息进行验证
                int loginResult = dbTalk.logIn(userID,password);
                // TODO: 登陆成功，进行买书活动
                if(loginResult == 1){
                    MyLibView.nowUser = DBTalk.getNowUser(userID);
                    UserChooseView userChooseView = new UserChooseView();
                    userChooseView.setVisible(true);
//                    BuyBookView buyBookView = new BuyBookView();
//                    buyBookView.setVisible(true);
                }
                else if (loginResult == 2) {
                    JOptionPane.showMessageDialog(contentPane, "密码错误", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                else if (loginResult == 3) {
                    JOptionPane.showMessageDialog(contentPane, "无该用户", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(contentPane, "数据库系统故障", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton registerBtn = new JButton("注册");
        registerBtn.setBounds(237, 160, 74, 23);
        contentPane.add(registerBtn);
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理注册按钮点击事件
                // TODO: 在这里添加注册按钮的处理逻辑
                RegisterView registerView = new RegisterView();
                registerView.setVisible(true);
            }
        });
    }
}
