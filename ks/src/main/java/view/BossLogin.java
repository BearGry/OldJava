package view;

import dao.DBTalk;
import test.CameraFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossLogin extends JFrame {
    private JPanel contentPane;
    private JPasswordField passwordField;
    private DBTalk dbTalk = new DBTalk();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    BossLogin frame = new BossLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BossLogin() {
        setTitle("BOSS您好");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 443, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUserID = new JLabel(" 新的一天，赶紧进货吧");
        lblUserID.setFont( new Font("宋体", Font.PLAIN, 18));
        lblUserID.setBounds(112, 60, 200, 19);
        contentPane.add(lblUserID);

        JLabel lblPassword = new JLabel("密码");
        lblPassword.setBounds(112, 110, 43, 15);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(151, 107, 160, 21);
        contentPane.add(passwordField);

        JButton loginBtn = new JButton("登录");
        loginBtn.setBounds(131, 160, 74, 23);
        contentPane.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());

                // 验证用户ID和密码的逻辑
                // TODO: 在这里添加验证逻辑，例如从数据库中查询用户信息进行验证
                int loginResult = dbTalk.logIn(1,password);
                // TODO: 登陆成功,Boss活动页面
                if(loginResult == 1){
                    CameraFrame cameraFrame = new CameraFrame();
                    cameraFrame.setVisible(true);
                    MyLibView.balance = DBTalk.getBalance();
//                    BossActionView bossActionView = new BossActionView();
//                    bossActionView.setVisible(true);
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

        JButton registerBtn = new JButton("返回");
        registerBtn.setBounds(237, 160, 74, 23);
        contentPane.add(registerBtn);
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理注册按钮点击事件
                // TODO: 在这里添加返回按钮的处理逻辑
                dispose();
            }
        });
    }
}
