package com.zhuhong.inspection.utils;

import com.zhuhong.inspection.base.Constants;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 视频处理工具类
 * @Author: jian.ye
 * @Date: 2019/11/13 16:27
 */
public class VideoUtil {

    /**
     * 保存视频
     * @param file 视频流
     * @param fileDir 根路径
     * @param realName 存储文件名
     * @return Map<String, String>
     * @Author: jian.ye
     * @Date: 2019/11/13 16:52
     */
    public static Map<String, String> uploadVideo(MultipartFile file, String fileDir, String realName) {
        Map<String, String> map = new HashMap<>();
        try {
            String fileType = file.getOriginalFilename().split("\\.")[1];
            String rootPath = fileDir + "video\\" + realName + "." + fileType;
            map.put("mediaName", realName + "." + fileType);
            // 转byte
            byte[] b = readAsBytes(file.getInputStream());
            OutputStream out = null;
            try{
                out = new FileOutputStream(rootPath);
                out.write(b);
                out.flush();

                // 第一帧图片路径
                grabberVideoFramer(rootPath, fileDir + "video\\" + realName + "_thumbnail_5.jpg");
                map.put("thumbnail", realName + "_thumbnail_5.jpg");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if(null != out){
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *  抽取视频第5帧图片
     * @param rootPath 原视频地址
     * @param picPath 抽取图片地址
     * @Author: jian.ye
     * @Date: 2019/11/13 16:52
     */
    private static void grabberVideoFramer(String rootPath, String picPath) {
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        try {
			 // 获取视频文件
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(rootPath);
            fFmpegFrameGrabber.start();

            // 获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();
            System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);
            String rotate = fFmpegFrameGrabber.getVideoMetadata("rotate");
            System.out.println(rotate);
            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();
				// 对视频的第30帧进行处理
                if (frame != null && flag == 30) {
                    // 文件储存对象
                    File outPut = new File(picPath);
                    ImageIO.write(frameToBufferedImage(frame), "jpg", outPut);
                    break;
                }
                flag++;
            }
            fFmpegFrameGrabber.stop();
            fFmpegFrameGrabber.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage frameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        // 图片压缩
        BufferedImage bi = new BufferedImage(Constants.YS_WIDTH, Constants.YS_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        bi.getGraphics().drawImage(bufferedImage.getScaledInstance(Constants.YS_WIDTH, Constants.YS_HEIGHT, Image.SCALE_SMOOTH),
                0, 0, null);
        return bi;
    }

    // 二进制读取
    private static byte[] readAsBytes(InputStream in) {
        byte[] buffer = new byte[0];
        try {
            buffer = new byte[in.available()];
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.read(buffer);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

}
