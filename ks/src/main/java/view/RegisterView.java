package view;

import dao.DBTalk;
import entity.Student;
import entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame{

    private JPanel contentPane;
    private JTextField passwordText;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterView frame = new RegisterView();
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
    public RegisterView() {
        setTitle("注册页面");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(222, 160, 321, 240);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("请设置您的登录密码");
        lblNewLabel.setBounds(95, 50, 160, 24);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("密码：");
        lblNewLabel_1.setBounds(42, 93, 43, 15);
        contentPane.add(lblNewLabel_1);

        passwordText = new JTextField();
        passwordText.setBounds(81, 93, 180, 21);
        contentPane.add(passwordText);
        passwordText.setColumns(10);

        //保存
        JButton saveBtn = new JButton("注册");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = passwordText.getText();
                if(DBTalk.register(password)){
                    JOptionPane.showMessageDialog(contentPane, "您的ID是 "+ DBTalk.maxID(), "注册成功", JOptionPane.WARNING_MESSAGE);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(contentPane, "注册失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        saveBtn.setBounds(116, 140, 74, 23);
        contentPane.add(saveBtn);

    }

}
