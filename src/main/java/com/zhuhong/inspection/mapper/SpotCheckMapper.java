package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.vo.SpotCheckVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 抽检数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/15 21:54
 */
@Repository
public interface SpotCheckMapper extends Mapper<SpotCheck> {

    /**
     * 根据条件查询抽检数据
     *
     * @param condition
     * @return List<SpotCheckVo>
     * @Author: jian.ye
     * @Date: 2019/10/16 15:40
     */
    List<SpotCheckVo> getSpotCheckListByCondition(SpotCheckCondition condition);

}
