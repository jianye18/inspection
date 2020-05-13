package com.zhuhong.inspection.service.impl;

import com.zhuhong.inspection.mapper.VisitorMapper;
import com.zhuhong.inspection.model.Visitor;
import com.zhuhong.inspection.service.VisitorService;
import com.zhuhong.inspection.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

/**
 * @author 叶剑
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorMapper visitorMapper;

    @Override
    public boolean insertVisitor(Visitor visitor) {
        visitorMapper.insertSelective(visitor);
        Example example = new Example(Visitor.class);
        example.createCriteria()
                .andEqualTo("ip", visitor.getIp())
                .andEqualTo("type", 2)
                .andEqualTo("usable", 1)
                .andEqualTo("visitDate", DateUtil.toDateString(LocalDate.now(), DateUtil.DATE_FORMATER));
        List<Visitor> list = visitorMapper.selectByExample(example);
        if (list.size() < 1) {
            visitor.setId(null);
            visitor.setType(2);
            if (list.size() == 1) {
                Calendar now = Calendar.getInstance();
                now.add(Calendar.MINUTE, -30);
                if (!list.get(0).getCreateTime().after(now.getTime())) {
                    visitorMapper.insertSelective(visitor);
                }
            } else {
                visitorMapper.insertSelective(visitor);
            }

        }
        return true;
    }

    @Override
    public int getVisitorCount(Integer type) {
        Visitor visitor = new Visitor();
        visitor.setType(type);
        return visitorMapper.selectCount(visitor);
    }
}
