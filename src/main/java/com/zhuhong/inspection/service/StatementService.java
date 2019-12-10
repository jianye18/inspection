package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.StatementCondition;
import com.zhuhong.inspection.model.Statement;

/**
 * @author 叶剑
 */
public interface StatementService {

    /**
     * 保存负责声明信息
     * @param statement
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/10 14:08
     */
    boolean saveStatement(Statement statement, Integer currentUserId);

    /**
     * 根据条件分页获取负责声明信息
     * @param condition
     * @return PageInfo<Statement>
     * @Author: jian.ye
     * @Date: 2019/12/10 14:09
     */
    PageInfo<Statement> getStatementPageList(StatementCondition condition);

    /**
     * 删除负责声明信息
     * @param statementId
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/10 14:10
     */
    boolean deleteStatement(Integer statementId, Integer currentUserId);

    /**
     * 获取一个展示的负责声明
     * @return Statement
     * @Author: jian.ye
     * @Date: 2019/12/10 14:11
     */
    Statement getOneViewStatement();

}
