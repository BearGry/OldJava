package gui.chapter09.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JDialog1 extends JFrame {
    private static final long serialVersionUID = 1l;
    private JLabel lab = new JLabel("请输入您的名字： ");
    private JTextField tf = new JTextField(10);
    private JButton but = new JButton("确定");
    private JPanel pan = new JPanel();
    private JDialog1 thisFrame;
    public JDialog1(){
        thisFrame = this;
        this.setTitle("自定义的JFrame窗体");
        this.setBounds(100,200,300,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialize();
        this.setVisible(true);
    }
    public void initialize(){
        pan.add(lab);
        pan.add(tf);
        pan.add(but);
        this.add(pan);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(thisFrame,"JDialog对话框");
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setLocation(thisFrame.getX()+50,thisFrame.getY()+90);
                dialog.setSize(200,150);
                dialog.add(new JLabel(tf.getX()+"，您好！"));
                dialog.setVisible(true);
            }
        });
    }
}
