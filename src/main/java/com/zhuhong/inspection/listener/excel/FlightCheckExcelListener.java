package com.zhuhong.inspection.listener.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.model.FlightCheck;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.service.FlightCheckService;
import com.zhuhong.inspection.service.SpotCheckService;
import com.zhuhong.inspection.vo.UserVo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义监听excel模板的读取类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 9:42
 */
@Slf4j
public class FlightCheckExcelListener extends AnalysisEventListener<FlightCheck> {

    private FlightCheckService flightCheckService;

    private User user;

    public FlightCheckExcelListener(FlightCheckService flightCheckService, User user) {
        super();
        this.flightCheckService = flightCheckService;
        this.user = user;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 50;
    private int SUCCESS_COUNT = 0;
    List<FlightCheck> list = new ArrayList<>();

    @Override
    public void invoke(FlightCheck t, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(t));
        list.add(t);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("存储数据库成功！");
        if (list.size() > 0) {
            for (FlightCheck flightCheck : list) {
                boolean flag = flightCheckService.insertFlightCheck(flightCheck, user.getId());
                if (flag) {
                    SUCCESS_COUNT++;
                }
            }
        }
    }

    public int getSUCCESS_COUNT() {
        return SUCCESS_COUNT;
    }

    public void setSUCCESS_COUNT(int SUCCESS_COUNT) {
        this.SUCCESS_COUNT = SUCCESS_COUNT;
    }
}
