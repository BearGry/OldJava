package view;

import dao.BossDBTalk;
import dao.DBTalk;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserChooseView extends JFrame {
    private JPanel contentPane;
    private DBTalk dbTalk = new DBTalk();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                     UserChooseView frame = new  UserChooseView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  UserChooseView() {
        setTitle("顾客通道");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 443, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblUserID = new JLabel("  欢迎光临我的书店!");
        lblUserID.setFont( new Font("宋体", Font.PLAIN, 18));
        lblUserID.setBounds(112, 60, 200, 19);
        contentPane.add(lblUserID);


        JButton loginBtn = new JButton("书店");
        loginBtn.setBounds(131, 107, 160, 26);
        contentPane.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理清单按钮点击事件
                // TODO：在这里添加书店——一手书按钮的处理逻辑
                BuyBookView buyBookView = new BuyBookView();
                buyBookView.setVisible(true);
            }
        });

        JButton registerBtn = new JButton("二手书交易市场");
        registerBtn.setBounds(131, 160, 160, 26);
        contentPane.add(registerBtn);
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理进货按钮点击事件
                // TODO: 在这里添加二手——二手书市场按钮的处理逻辑
                SecBookMarketView secBookMarketView = new SecBookMarketView();
                secBookMarketView.setVisible(true);
            }
        });
    }
}
