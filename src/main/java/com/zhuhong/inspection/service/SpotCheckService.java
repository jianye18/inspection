package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.vo.SpotCheckVo;

/**
 * 抽检数据业务接口层
 *
 * @Author: jian.ye
 * @Date: 2019/10/15 21:55
 */
public interface SpotCheckService {

    /**
     * 插入抽检数据
     *
     * @param spotCheck
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/16 11:05
     */
    boolean insertSpotCheck(SpotCheck spotCheck, Integer currentUserId);

    /**
     * 根据条件分页查询抽检数据
     *
     * @param spotCheckCondition
     * @return PageInfo<SpotCheckVo>
     * @Author: jian.ye
     * @Date: 2019/10/16 15:36
     */
    PageInfo<SpotCheckVo> getSpotCheckPageList(SpotCheckCondition spotCheckCondition);

}
