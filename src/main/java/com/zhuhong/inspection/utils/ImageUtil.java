package com.zhuhong.inspection.utils;

import com.zhuhong.inspection.base.Constants;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片处理工具类
 * @Author: jian.ye
 * @Date: 2019/11/13 11:24
 */
public class ImageUtil {

    /**
     * 保存图片文件
     * @param file 图片流
     * @param fileDir 根路径
     * @param realName 存储文件名
     * @param isCondense 是否压缩
     * @Author: jian.ye
     * @Date: 2019/11/13 11:47
     */
    public static Map<String, String> saveImage(MultipartFile file, String fileDir, String realName, boolean isCondense) {
        try {
            Map<String, String> map = new HashMap<>();
            String fileType = file.getOriginalFilename().split("\\.")[1];
            String rootPath = fileDir + "images/" + realName + "." + fileType;
            map.put("mediaName", realName + "." + fileType);
            file.transferTo(new File(rootPath));
            if (isCondense) {
                String thumbnailPath = fileDir + "images/" + realName + "_thumbnail." + fileType;
                condenseImageFile(rootPath, thumbnailPath);
                map.put("thumbnail", realName + "_thumbnail" + "." + fileType);
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 压缩图片
     * @param rootPath 原图地址
     * @param thumbnailPath 缩略图路径
     * @Author: jian.ye
     * @Date: 2019/11/13 14:49
     */
    public static void condenseImageFile(String rootPath, String thumbnailPath) {
        try {
            Thumbnails.of(rootPath).size(Constants.YS_WIDTH, Constants.YS_HEIGHT).keepAspectRatio(false).toFile(thumbnailPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
