package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.FlightCheckCondition;
import com.zhuhong.inspection.model.FlightCheck;
import com.zhuhong.inspection.vo.FlightCheckVo;
import com.zhuhong.inspection.vo.SelectionLabel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 飞检数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/11/9 19:35
 */
@Repository
public interface FlightCheckMapper extends Mapper<FlightCheck> {

    /**
     * 根据ID获取飞检数据
     *
     * @param condition
     * @return FlightCheckVo
     * @Author: jian.ye
     * @Date: 2019/11/9 19:47
     */
    FlightCheckVo getFlightCheckById(FlightCheckCondition condition);

    /**
     * 根据条件获取飞检数据
     *
     * @param condition
     * @return PageInfo<FlightCheckVo>
     * @Author: jian.ye
     * @Date: 2019/11/9 19:47
     */
    List<FlightCheckVo> getFlightCheckPageList(FlightCheckCondition condition);

    /**
     * 获取发布单位集合
     * @Author: jian.ye
     * @Date: 2019/11/13 20:59
     */
    List<SelectionLabel> getPublishUnitList();

    /**
     * 获取处理措施集合
     *
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/12/9 20:01
     */
    List<SelectionLabel> getPrecautionsList();

    /**
     * 获取飞检类型集合
     *
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/12/9 20:01
     */
    List<SelectionLabel> getTypeList();

    /**
     * 根据条件获取飞检数量
     * @param isNew
     * @return int
     * @Author: jian.ye
     * @Date: 2019/12/16 11:38
     */
    int getFlightCheckCount(@Param(value = "isNew") Integer isNew);

    int getCountByBusinessName(@Param(value = "businessName") String businessName, @Param(value = "isDefect") String isDefect);

}
