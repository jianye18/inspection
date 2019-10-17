package com.zhuhong.inspection;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.zhuhong.inspection.base.BussinessException;
import com.zhuhong.inspection.listener.excel.SpotCheckExcelListener;
import com.zhuhong.inspection.model.SpotCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InspectionApplicationTests {

    @Test
    public void contextLoads() {
        // 写法1：
        String fileName = "E://111.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        /*SpotCheckExcelListener listener = new SpotCheckExcelListener();
        try {
            EasyExcel.read(fileName, SpotCheck.class, listener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(listener.getSUCCESS_COUNT());
        }*/

        // 写法2：
        /*ExcelReader excelReader = EasyExcel.read(fileName, SpotCheck.class, new SpotCheckExcelListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();*/

    }

}
