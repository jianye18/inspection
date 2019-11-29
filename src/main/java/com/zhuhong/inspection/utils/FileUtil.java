package com.zhuhong.inspection.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件相关操作工具类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 9:33
 */
public class FileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    /**
     * 压缩文件
     *
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    public static void zipFile(List<String> filePaths, ZipOutputStream zos) {
        //设置读取数据缓存大小
        byte[] buffer = new byte[4096];
        try {
            //循环读取文件路径集合，获取每一个文件的路径
            for (String filePath : filePaths) {
                File inputFile = new File(filePath);
                //判断文件是否存在
                if (inputFile.exists()) {
                    //判断是否属于文件，还是文件夹
                    if (inputFile.isFile()) {
                        //创建输入流读取文件
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                        //将文件写入zip内，即将文件进行打包
                        zos.putNextEntry(new ZipEntry(inputFile.getName()));
                        //写入文件的方法，同上
                        int size = 0;
                        //设置读取数据缓存大小
                        while ((size = bis.read(buffer)) > 0) {
                            zos.write(buffer, 0, size);
                        }
                        //关闭输入输出流
                        zos.closeEntry();
                        bis.close();
                    } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<String>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        zipFile(filePathsTem, zos);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != zos) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除指定文件或文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            String[] fileList = file.list();
            String tempFilePath = null;
            for (String tempFileName : fileList) {
                if (path.endsWith(File.separator)) {
                    tempFilePath = path + tempFileName;
                } else {
                    tempFilePath = path + File.separator + tempFileName;
                }
                delAllFile(tempFilePath);
            }
            file.delete();
        }
        return true;
    }

    public static boolean uploadFile(MultipartFile file, String savePath, String name) {
        try {
            File f = new File(savePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            File dest = new File(savePath + name);
            file.transferTo(dest);
            /*//获得文件的字节流
            byte[] bytes=file.getBytes();
            Path path= Paths.get(savePath + name);
            //调用静态方法完成将文件写入到目标路径
            Files.write(path,bytes);*/
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
