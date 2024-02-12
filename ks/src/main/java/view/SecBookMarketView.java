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

public class SecBookMarketView extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTextField nameText;
    private JTextField sellText;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SecBookMarketView frame = new SecBookMarketView();
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
    public SecBookMarketView() {

        setTitle("二手书自由交易市场");
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

        Object[] columns = {"书名", "售价", "卖家ID"};// 字段
        Object[][] data = null;// 需要展示的数据，一般是二维数组
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
//        TODO:猜你喜欢（买过同样书的人还买过哪些其他的书，这里有没有）
        load();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("书名");
        lblNewLabel.setBounds(10, 10, 42, 15);
        contentPane.add(lblNewLabel);

        nameText = new JTextField();
        nameText.setBounds(44, 8, 115, 21);
        contentPane.add(nameText);
        nameText.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("定价");
        lblNewLabel_1.setBounds(169, 8, 63, 23);
        contentPane.add(lblNewLabel_1);

        sellText = new JTextField();
        sellText.setBounds(205, 8, 115, 21);
        contentPane.add(sellText);
        sellText.setColumns(10);

        //模糊查询按钮
        JButton likeSearBtn = new JButton("查询");
        likeSearBtn.addActionListener(new ActionListener() {
            //            TODO:模糊查询按钮
            public void actionPerformed(ActionEvent e) {
                String bname = nameText.getText();
                likeLoad(bname);
            }
        });
        likeSearBtn.setBounds(365, 8, 63, 23);
        contentPane.add(likeSearBtn);

        //精确查询按钮
        JButton exSearBtn = new JButton("出售");
        exSearBtn.addActionListener(new ActionListener() {
            //            TODO:出售按钮
            public void actionPerformed(ActionEvent e) {
                if (nameText.getText() == null || "".equals(nameText.getText())) {
                    JOptionPane.showMessageDialog(contentPane, "请输入书名", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (sellText.getText() == null || "".equals(sellText.getText())) {
                    JOptionPane.showMessageDialog(contentPane, "请选择购买的本数", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String bookname = nameText.getText();
                int sellPrice = Integer.valueOf(sellText.getText());
                boolean putOK = false;
                int result = JOptionPane.showConfirmDialog(contentPane, "您选择以"+sellPrice+"元价格出售" + bookname, "提示",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    if(DBTalk.setSecBook(bookname,sellPrice)){
                        JOptionPane.showMessageDialog(contentPane,"已挂在二手书店了:>!",null,JOptionPane.YES_OPTION);
                    }
                    else {
                        JOptionPane.showMessageDialog(contentPane,"抛出失败:<","系统提示",JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });
        exSearBtn.setBounds(438, 8, 63, 23);
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
                String bookname = table.getValueAt(row, 0).toString();
                int pay = Integer.valueOf(table.getValueAt(row, 1).toString());
                int id = Integer.valueOf(table.getValueAt(row, 2).toString());

                boolean buyOK = false;
                int result = JOptionPane.showConfirmDialog(contentPane, "正在下单,共计" + pay + "元", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    if (MyLibView.nowUser.getMoney() < pay) {
                        int result_1 = JOptionPane.showConfirmDialog(contentPane, "需要扫码支付" + (pay - MyLibView.nowUser.getMoney()) + "元", "提示",
                                JOptionPane.YES_NO_OPTION);
                        if (result_1 == 0) {
                            MyLibView.nowUser.setMoney(0);
                            buyOK = true;
                        }
                    } else {
                        MyLibView.nowUser.setMoney(MyLibView.nowUser.getMoney() - pay);
                        buyOK = true;
                    }
                }
                // FIXME: 这块的逻辑不好，应该确定写入数据库之后，再去改变nowUser的money
                // 成功交易，进行数据库更新，
                // 包含user买方、卖方和Boss的存款更新
                //    secbook的删除
                if (buyOK) {
                    if(DBTalk.secTrade(id,pay,bookname)){
                        JOptionPane.showMessageDialog(contentPane, "购买成功，货物会尽快送达！", "欢迎下次光临", JOptionPane.YES_OPTION);
                    }
                    else {
                        JOptionPane.showMessageDialog(contentPane,"购买失败:<","系统提示",JOptionPane.WARNING_MESSAGE);
                        //FIXME
                        MyLibView.nowUser.setMoney(pay);
                        DBTalk.userSaveMoney(pay);
                    }
                }
            }
        });
        buyBtn.setBounds(511, 8, 63, 23);
        contentPane.add(buyBtn);
    }

//    TODO 实现该函数

    /**
     * 填充加载猜你喜欢，即买过相同书的人，还买过什么其他的书，这里有没有
     */
    public void load() {
        java.util.List<Book> bookList = DBTalk.secload();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

        for (Book book : bookList) {
            String[] arr = new String[3];
            arr[0] = book.getBname();
            arr[1] = String.valueOf(book.getPrice());
            arr[2] = String.valueOf(book.getInventory());
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
        java.util.List<Book> bookList = DBTalk.seclikeLoad(bname);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

        for (Book book : bookList) {
            String[] arr = new String[3];
            arr[0] = book.getBname();
            arr[1] = String.valueOf(book.getPrice());
            arr[2] = String.valueOf(book.getInventory());

            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }

}
