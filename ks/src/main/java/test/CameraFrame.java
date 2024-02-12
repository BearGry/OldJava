/**
 * @File Name : CameraFrame.java
 * @Brief : 人脸检测
 * @Author : 徐梓航 E-mail : m0rtzz@163.com
 * @Version : 1.0
 * @Create Date : 2022-06-27
 *
 */
package test;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import org.json.*;
import view.BossActionView;

public class CameraFrame extends JFrame {

    private CanvasFrame canvasFrame;
    private OpenCVFrameGrabber grabber;
    private JButton captureButton; // 添加一个按钮
    private boolean  boolshowImage = true;
    private boolean isBoss = false;

    public CameraFrame() {

        canvasFrame = new CanvasFrame("Camera");
        canvasFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        canvasFrame.setBounds(150, 100, 443, 300);
        dispose();

        captureButton = new JButton("点击以进行人脸检测"); // 创建按钮对象
        captureButton.addActionListener(e -> {
            // 将人脸检测逻辑放在新线程中执行
//            new Thread(this::checkFaceDetection).start();
            checkFaceDetection();
            if(isBoss){
                canvasFrame.dispose();
                dispose();
                BossActionView bossActionView = new BossActionView();
                bossActionView.setVisible(true);
            }
            else {
                canvasFrame.dispose();
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(captureButton, BorderLayout.SOUTH); // 将按钮添加到窗体底部

        canvasFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH); // 将按钮添加到摄像头窗口的底部

        try {
            grabber = OpenCVFrameGrabber.createDefault(0);
            grabber.start();
            dispose();

            Runnable frameGrabber = new Runnable() {
                @Override
                public void run() {
                    try {
                        while ( boolshowImage) {
                            Frame frame = grabber.grab();

                            canvasFrame.showImage(frame);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            grabber.stop();
                            canvasFrame.dispose();
                            dispose();
                        } catch (FrameGrabber.Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };

            Thread thread = new Thread(frameGrabber);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pack();
        setLocationRelativeTo(null);
    }

    private void captureImage() {
        try {
            Frame frame = grabber.grab();
            String filename = "D:\\ks\\photo\\test.png"; // 设置保存图片的路径和文件名

            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage image = converter.getBufferedImage(frame);

            if (image != null) {
                boolean result = ImageIO.write(image, "png", new File(filename));
                System.out.println("Image saved: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        fis.close();
        return bos.toByteArray();
    }

    public String faceSearch() {
        // 设置APPID/AK/SK
        String APP_ID = "35315500 ";
        String API_KEY = "IMG503WFlwmsvqFDh1Sry3is";
        String SECRET_KEY = "unxGh1BnuN2GsHQU5ansGljHHLEwXeIp";
        captureImage();
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 调用接口
        String imageType = "BASE64";
        HashMap<String, Object> options = new HashMap<>();
        options.put("match_threshold", "60");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "NONE");
        options.put("max_user_num", "1");
        try {
            // 读取图片数据
            byte[] data = readFile("D:\\ks\\photo\\test.png");
            // 对图片数据进行Base64编码
            String image = Base64Util.encode(data);
            String groupIdList = "Curriculum_Design";
            // 人脸检测
            JSONObject res = client.search(image, imageType, groupIdList, options);
            System.out.println(res.toString(2));
            return res.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getScore(String json_string) {
        JSONObject json_object = new JSONObject(json_string);

        // 检查是否包含"result"字段
        if (json_object.has("result")) {
            JSONArray user_list = json_object.getJSONObject("result").getJSONArray("user_list");
            if (user_list.length() > 0) {
                return user_list.getJSONObject(0).getDouble("score");
            }
        }
        return 0.0;
    }

    public String getErrorMessage(String json_string) {
        JSONObject json_object = new JSONObject(json_string);
        return json_object.getString("error_msg");
    }

    public void checkFaceDetection() {

        Thread captureThread = new Thread(this::captureImage);
        captureThread.start();

        try {
            captureThread.join(); // 等待captureImage方法执行完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String json_string = faceSearch();
        String error_message = getErrorMessage(json_string);
        if (error_message.equals("SUCCESS")) {
            double score = getScore(json_string);
            if (score >= 70) {
                JOptionPane.showMessageDialog(null, "认证成功！");
                isBoss = true;
//                System.exit(0);
//                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,"认证失败");
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"认证失败");

        }
//        else {
//            SwingUtilities.invokeLater(() -> {
//                JOptionPane.showMessageDialog(null, "人脸检测失败，请再试一次！");
//            });
//        }
         boolshowImage = false;
    }

    public static void main(String[] args) {
        CameraFrame cameraFrame = new CameraFrame();
        cameraFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
