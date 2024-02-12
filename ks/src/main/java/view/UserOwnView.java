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

public class UserOwnView extends JFrame {
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserOwnView frame = new UserOwnView();
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
    public UserOwnView() {

        setTitle("我的");
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

        Object[] columns = {"书名", "本数"};// 字段
        Object[][] data = null;// 需要展示的数据，一般是二维数组
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
//        TODO:猜你喜欢（买过同样书的人还买过那些其他的书）
        load();
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("账户余额：" + MyLibView.nowUser.getMoney()+" 元");
//        JLabel lblNewLabel = new JLabel("账户余额：");
        lblNewLabel.setBounds(10, 10, 200, 23);
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));

        contentPane.add(lblNewLabel);


        //充值按钮
        JButton myBtn = new JButton("充值");
        myBtn.addActionListener(new ActionListener() {
            //            TODO:“充值”界面按钮
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "充值金额："); // 显示一个输入对话框

                if (input != null) { // 用户点击了对话框的确定按钮
                    try {
                        double number = Double.parseDouble(input); // 将字符串转换为double类型的数字
                        int money = (int)number;
                        if(DBTalk.userSaveMoney(money)){
                            JOptionPane.showMessageDialog(null, "充值成功！金额为：" + number+" 元");
                            MyLibView.nowUser.setMoney(MyLibView.nowUser.getMoney()+money);
                            lblNewLabel.setText("账户余额：" + MyLibView.nowUser.getMoney()+" 元");
                        }
                    } catch (NumberFormatException abcd) {
                        JOptionPane.showMessageDialog(null, "输入无效");
                    }
                } else { // 用户点击了对话框的取消按钮
                    JOptionPane.showMessageDialog(null, "操作已取消。",null,JOptionPane.YES_OPTION);
                }
            }
        });
        myBtn.setBounds(511, 8, 63, 26);
        contentPane.add(myBtn);

    }

    /**
     * 填充加载购书的历史记录
     */
    public void load() {
        java.util.List<Book> bookList = DBTalk.loadHistory();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

        for (Book book : bookList) {
            String[] arr = new String[2];
            arr[0] = book.getBname();
            arr[1] = String.valueOf(book.getInventory());
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
        java.util.List<Book> bookList = DBTalk.likeLoad(bname);
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
