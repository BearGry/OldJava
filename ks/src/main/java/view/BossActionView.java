package view;

import dao.BossDBTalk;
import dao.DBTalk;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class BossActionView extends JFrame {
    private JPanel contentPane;
    private DBTalk dbTalk = new DBTalk();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    BossActionView frame = new BossActionView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BossActionView() {
        setTitle("进货进货！");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 443, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblUserID = new JLabel(" 营业额： "+MyLibView.balance+" 元!");
        lblUserID.setFont( new Font("宋体", Font.PLAIN, 18));
        lblUserID.setBounds(112, 60, 200, 19);
        contentPane.add(lblUserID);


        JButton loginBtn = new JButton("清单");
        loginBtn.setBounds(131, 104, 160, 26);
        contentPane.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理清单按钮点击事件
                // TODO：在这里添加清单按钮的处理逻辑
                if(BossDBTalk.excelCreator()){
                    JOptionPane.showMessageDialog(contentPane,"导出清单成功，请尽快进货:>!","确认",JOptionPane.YES_OPTION);
                }
                else {
                    JOptionPane.showMessageDialog(contentPane,"导出清单失败:<!","系统警告",JOptionPane.ERROR);
                }
            }
        });

        JButton registerBtn = new JButton("进货");
        registerBtn.setBounds(131, 150, 160, 26);
        contentPane.add(registerBtn);
        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理进货按钮点击事件
                // TODO: 在这里添加进货按钮的处理逻辑
                int cost = BossDBTalk.calculateProductSum();
                if(BossDBTalk.purchase(cost)){
                    MyLibView.balance -= cost;
                    lblUserID.setText(" 营业额： "+MyLibView.balance+" 元!");
                    JOptionPane.showMessageDialog(contentPane,"进货成功，请注意及时清理清单:>!","确认",JOptionPane.YES_OPTION);
                }
                else {
                    JOptionPane.showMessageDialog(contentPane,"进货失败:<!","系统警告",JOptionPane.ERROR);
                }
            }
        });

        JButton dumpBtn = new JButton("转储");
        dumpBtn.setBounds(131, 196, 160, 26);
        contentPane.add(dumpBtn);
        dumpBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 处理转储按钮点击事件
                // TODO: 在这里添加转储按钮的处理逻辑

                String[] command = {
                        "cmd.exe",
                        "/c",
                        "mysqldump",
                        "-u",
                        "root",
                        "-pmysql123",
                        "mylib",
                        ">",
                        "D:\\dump.sql"
                };

                try {
                    ProcessBuilder processBuilder = new ProcessBuilder(command);
                    Process process = processBuilder.start();
                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        System.out.println("Database dump completed successfully.");
                        JOptionPane.showMessageDialog(contentPane,"转储成功","系统提示",JOptionPane.YES_OPTION);
                    } else {
                        System.out.println("Failed to dump the database.");
                        JOptionPane.showMessageDialog(contentPane,"转储失败","系统提示",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException | InterruptedException abc) {
                    abc.printStackTrace();
                }
            }
        });
    }
}
