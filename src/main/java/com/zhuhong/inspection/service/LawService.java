package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.LawCondition;
import com.zhuhong.inspection.model.Law;
import com.zhuhong.inspection.vo.LawVo;

/**
 * 法规业务接口层
 * @Author: jian.ye
 * @Date: 2019/10/23 8:49
 */
public interface LawService {

    /**
     * 保存法规信息
     * @param law
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/23 8:38
     */
    boolean saveLaw(Law law, Integer currentUserId);

    /**
     * 根据条件分页查询法规信息
     * @param lawCondition
     * @return PageInfo<LawVo>
     * @Author: jian.ye
     * @Date: 2019/10/23 8:47
     */
    PageInfo<LawVo> getLawPageList(LawCondition lawCondition);

    /**
     * 删除法规信息
     * @param lawId
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/23 8:48
     */
    boolean deleteLaw(Integer lawId, Integer currentUserId);

}
