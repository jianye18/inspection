package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.StatementCondition;
import com.zhuhong.inspection.mapper.StatementMapper;
import com.zhuhong.inspection.model.Link;
import com.zhuhong.inspection.model.Statement;
import com.zhuhong.inspection.service.StatementService;
import com.zhuhong.inspection.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 叶剑
 */
@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    private StatementMapper statementMapper;

    @Override
    public boolean saveStatement(Statement statement, Integer currentUserId) {
        Date current = DateUtil.getCurrentDate();
        statement.setUpdateId(currentUserId);
        statement.setUpdateTime(current);
        if (statement.getId() == null) {
            statement.setCreateId(currentUserId);
            statement.setCreateTime(current);
            return statementMapper.insertSelective(statement) > 0;
        } else {
            return statementMapper.updateByPrimaryKey(statement) > 0;
        }
    }

    @Override
    public PageInfo<Statement> getStatementPageList(StatementCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        Example example = new Example(Link.class);
        example.createCriteria().andEqualTo("usable", Statement.ENABLE_1);
        example.setOrderByClause("update_time desc");
        List<Statement> list = statementMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteStatement(Integer statementId, Integer currentUserId) {
        Statement statement = new Statement();
        statement.setId(statementId);
        statement.setUpdateId(currentUserId);
        statement.setUpdateTime(DateUtil.getCurrentDate());
        statement.setUsable(Link.ENABLE_0);
        return statementMapper.updateByPrimaryKeySelective(statement) > 0;
    }

    @Override
    public Statement getOneViewStatement() {
        Example example = new Example(Link.class);
        example.createCriteria().andEqualTo("usable", Statement.ENABLE_1);
        example.setOrderByClause("update_time desc limit 1");
        List<Statement> list = statementMapper.selectByExample(example);
        return list.get(0);
    }
}
