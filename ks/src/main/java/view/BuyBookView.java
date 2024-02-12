package view;

import dao.DBTalk;
import entity.Book;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.text.SimpleDateFormat;


public class BuyBookView extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTextField nameText;
    private JTextField numbText;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BuyBookView frame = new BuyBookView();
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
    public BuyBookView() {

        setTitle("欢迎光临~");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 337);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
//        FIXME
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 564, 232);
        contentPane.add(scrollPane);

        Object[] columns = {"书名", "售价"};// 字段
        Object[][] data = null;// 需要展示的数据，一般是二维数组
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
//        TODO:猜你喜欢（买过同样书的人还买过那些其他的书）
        load();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("书名");
        lblNewLabel.setBounds(10, 10, 42, 15);
        contentPane.add(lblNewLabel);

        nameText = new JTextField();
        nameText.setBounds(44, 8, 115, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("数量");
        lblNewLabel_1.setBounds(169, 8, 63, 23);
        contentPane.add(lblNewLabel_1);

        numbText = new JTextField();
        numbText.setBounds(205, 8, 55, 21);
        contentPane.add(numbText);
        numbText.setColumns(10);

        //我的按钮
        JButton myBtn = new JButton("我的");
        myBtn.addActionListener(new ActionListener() {
            //            TODO:“我的”界面按钮
            public void actionPerformed(ActionEvent e) {
                UserOwnView userOwnView = new UserOwnView();
                userOwnView.setVisible(true);
            }
        });
        myBtn.setBounds(511, 8, 63, 23);
        contentPane.add(myBtn);

        //模糊查询按钮
        JButton likeSearBtn = new JButton("相关");
        likeSearBtn.addActionListener(new ActionListener() {
            //            TODO:模糊查询按钮
            public void actionPerformed(ActionEvent e) {
                String bname = nameText.getText();
                likeLoad(bname);
            }
        });
        likeSearBtn.setBounds(292, 8, 63, 23);
        contentPane.add(likeSearBtn);

        //精确查询按钮
        JButton exSearBtn = new JButton("查询");
        exSearBtn.addActionListener(new ActionListener() {
            //            TODO:精确查询按钮
            public void actionPerformed(ActionEvent e) {
                String bname = nameText.getText();
                exLoad(bname);

            }
        });
        exSearBtn.setBounds(365, 8, 63, 23);
        contentPane.add(exSearBtn);

        //购买按钮
        JButton buyBtn = new JButton("购买");
        buyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//          TODO:购买按钮
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择您要买的书籍", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(numbText.getText() == null || "".equals(numbText.getText())){
                    JOptionPane.showMessageDialog(contentPane, "请选择购买的本数", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int num = Integer.valueOf(numbText.getText());
                int pay = num * Integer.valueOf(table.getValueAt(row, 1).toString());
                boolean buyOK = false;
                int result = JOptionPane.showConfirmDialog(contentPane, "正在下单,共计"+pay+"元", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    if(MyLibView.nowUser.getMoney() < pay){
                        int result_1 = JOptionPane.showConfirmDialog(contentPane, "需要扫码支付"+(pay-MyLibView.nowUser.getMoney())+"元", "提示",
                                JOptionPane.YES_NO_OPTION);
                        if(result_1 == 0){
                            MyLibView.nowUser.setMoney(0);
                            buyOK = true;
                        }
                    }
                    else {
                        MyLibView.nowUser.setMoney(MyLibView.nowUser.getMoney()-pay);
                        buyOK = true;

                    }
                }
                // 成功交易，进行数据库更新，
                // 包含user的存款更新和book的数量更新
                // !其中book的数量跟新，会运用到触发器
                if(buyOK){
                    JOptionPane.showMessageDialog(contentPane,"购买成功，货物会尽快送达！","欢迎下次光临",JOptionPane.YES_OPTION);
                    if(!DBTalk.setUser(MyLibView.nowUser, pay))
                        System.out.println("update money false");
                    else
                        System.out.println("ok");
                    if(!DBTalk.setBook(table.getValueAt(row,0).toString(),num))
                        System.out.println("update book false");
                    else
                        System.out.println("ok");
                    if(!DBTalk.setBuy(table.getValueAt(row,0).toString(),num))
                        System.out.println("update buy false");
                    else
                        System.out.println("ok");
                }
            }
        });
        buyBtn.setBounds(438, 8, 63, 23);
        contentPane.add(buyBtn);
    }

//    TODO 实现该函数

    /**
     * 填充加载猜你喜欢，即买过相同书的人，还买过什么其他的书
     */
    public void load() {
        List<Book> bookList = DBTalk.load();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

        for (Book book : bookList) {
            String[] arr = new String[2];
            arr[0] = book.getBname();
            arr[1] = String.valueOf(book.getPrice());
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }

    /**
     * 填充模糊查询相关书籍
     *
     * @param bname
     */
    public void likeLoad(String bname) {
        List<Book> bookList = DBTalk.likeLoad(bname);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

        for (Book book : bookList) {
            String[] arr = new String[2];
            arr[0] = book.getBname();
            arr[1] = String.valueOf(book.getPrice());
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }

    /**
     * 填充精确查询相关书籍
     *
     * @param bname
     */
    public void exLoad(String bname) {
        List<Book> bookList = DBTalk.exLoad(bname);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

        for (Book book : bookList) {
            String[] arr = new String[2];
            arr[0] = book.getBname();
            arr[1] = String.valueOf(book.getPrice());
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }

}
