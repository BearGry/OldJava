package test;
/**
 * @File Name : ExampleApp.java
 * @Brief : 人脸检测测试
 * @Author : 徐梓航 E-mail : m0rtzz@163.com
 * @Version : 1.0
 * @Create Date : 2022-06-27
 *
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ExampleApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Example App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton start_button = new JButton("开始");
        start_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 创建CameraFrame对象并显示窗口
                CameraFrame camera_frame = new CameraFrame();
                camera_frame.setVisible(true);
            }
        });

        JPanel button_panel = new JPanel();
        button_panel.add(start_button);

        frame.add(button_panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
