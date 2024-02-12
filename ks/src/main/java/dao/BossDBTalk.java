package dao;

import entity.Buy;
import entity.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.JDBCBossLib;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;

public class BossDBTalk {

    public static boolean excelCreator() {

        String excelFilePath = "D:\\ks\\work\\needToBuy.xlsx";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JDBCBossLib.getConnection();
            String sql = "select bname, price, lack, maketime " +
                    "from need " +
                    "UNION " +
                    "select a.bname, a.price*0.8 as price, 0 as lack, current_timestamp as maketime " +
                    "from book as a " +
                    "where a.inventory = 0 and a.bname not in ( " +
                    "    select bname " +
                    "    from need " +
                    "    )";
            pstmt = con.prepareStatement(sql);
            var resultSet = pstmt.executeQuery();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("进货清单");

            // 创建标题行
//            Row headerRow = sheet.createRow(0);
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            for (int i = 1; i <= columnCount; i++) {
//                String columnName = metaData.getColumnName(i);
//                Cell cell = headerRow.createCell(i - 1);
//                cell.setCellValue(columnName);
//            }
            String[] columnName = {"书名", "进价", "缺少本书", "创建时间"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i <= 3; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnName[i]);
            }

            // 填充数据
            int rowNumber = 1;
            while (resultSet.next()) {
                Row dataRow = sheet.createRow(rowNumber++);
                for (int i = 1; i <= 4; i++) {
                    if (i == 3) {
                        Integer value = resultSet.getInt(3);
                        Cell cell = dataRow.createCell(2);
                        cell.setCellValue(value != null ? String.valueOf(value + 14) : "");
                        continue;
                    }
                    if (i == 2) {
                        Double value = Double.valueOf(resultSet.getString(2));
                        double a = value;
                        Integer v = (int) a;
                        Cell cell = dataRow.createCell(1);
                        cell.setCellValue(value != null ? String.valueOf(v) : "");
                        continue;
                    }
                    Object value = resultSet.getObject(i);
                    Cell cell = dataRow.createCell(i - 1);
                    cell.setCellValue(value != null ? value.toString() : "");
                }

            }

            // 保存 Excel 文件
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }
            System.out.println("Excel 文件已创建成功！");

            // 打开 Excel 文件
            File excelFile = new File(excelFilePath);
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(excelFile);
            } else {
                System.out.println("无法打开 Excel 文件，请手动查找文件：" + excelFilePath);
            }

            return true;

        } catch (Exception e) {
            System.err.println("创建 Excel 文件时出现错误：" + e.getMessage());
        }
        return false;
    }


    public static int calculateProductSum() {

        int sum = 0;
        try (Workbook workbook = WorkbookFactory.create(new File("D:\\ks\\work\\needToBuy.xlsx"))) {
            Sheet sheet = workbook.getSheetAt(0); // 假设第一个表格是需要计算的表格
            int rowCount = sheet.getLastRowNum() + 1;

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                Cell priceCell = row.getCell(1); // 第二列是进价列
                Cell lackCell = row.getCell(2); // 第三列是缺少本书列

                int price = Integer.valueOf(priceCell.getStringCellValue());
                int lack = Integer.valueOf(lackCell.getStringCellValue());

                sum += price * lack;
            }
        } catch (Exception e) {
            System.err.println("计算乘积之和时出现错误：" + e.getMessage());
        }

        return sum;
    }

    public static void main(String[] args) {
        excelCreator();
        System.out.println(calculateProductSum());
    }

    public static boolean purchase(int cost) {
        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        boolean result = false;

        try {
            con = JDBCBossLib.getConnection();
            con.setAutoCommit(false); // 关闭自动提交

            // 第一个更新语句：老板的balance更新
            String sql1 = "UPDATE boss_count SET money = money - ? WHERE id = 1";
            pstmt1 = con.prepareStatement(sql1);
            pstmt1.setDouble(1, cost);
            pstmt1.executeUpdate();

            // 第二个更新语句: need清空
            String sql2 = "delete from need where true";
            pstmt2 = con.prepareStatement(sql2);
            pstmt2.executeUpdate();

            // 第三个更新语句：book更新
            String sql3 = "UPDATE book SET inventory = 14 WHERE inventory = 0";
            pstmt1 = con.prepareStatement(sql3);
            pstmt1.executeUpdate();

            con.commit(); // 提交事务
            result = true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // 回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCBossLib.close(con, pstmt1,null);
            JDBCBossLib.close(null,pstmt2,null);
        }

        return result;
    }
}

