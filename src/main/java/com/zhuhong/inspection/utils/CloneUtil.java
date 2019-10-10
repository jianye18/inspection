package com.zhuhong.inspection.utils;

import java.io.*;

/**
 * 深拷贝工具类
 * @Author: jian.ye
 * @Date: 2019/10/10 10:28
 */
public class CloneUtil {

    public static <T extends Serializable> T clone(T obj){
        T cloneObj=null;
        try{
            //写入字节流
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            //分配内存,写入原始对象,生成新对象
            ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois=new ObjectInputStream(bais);

            //返回生成的新对象
            cloneObj=(T)ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cloneObj;
    }

}
