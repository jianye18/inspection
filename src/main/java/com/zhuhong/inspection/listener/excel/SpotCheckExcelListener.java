package com.zhuhong.inspection.listener.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.base.BussinessException;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.service.SpotCheckService;
import com.zhuhong.inspection.utils.CookieUtil;
import com.zhuhong.inspection.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义监听excel模板的读取类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 9:42
 */
@Slf4j
public class SpotCheckExcelListener extends AnalysisEventListener<SpotCheck> {

    private SpotCheckService spotCheckService;

    private User user;

    public SpotCheckExcelListener (SpotCheckService spotCheckService, User user) {
        super();
        this.spotCheckService = spotCheckService;
        this.user = user;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 50;
    private int SUCCESS_COUNT = 0;
    List<SpotCheck> list = new ArrayList<>();

    @Override
    public void invoke(SpotCheck t, AnalysisContext analysisContext) {
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
            for (SpotCheck spotCheck : list) {
                boolean flag = spotCheckService.insertSpotCheck(spotCheck, user.getId());
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
