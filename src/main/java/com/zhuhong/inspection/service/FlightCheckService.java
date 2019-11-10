package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.FlightCheckCondition;
import com.zhuhong.inspection.model.FlightCheck;
import com.zhuhong.inspection.vo.FlightCheckVo;

/**
 * 飞检数据业务逻辑接口层
 *
 * @Author: jian.ye
 * @Date: 2019/11/9 19:36
 */
public interface FlightCheckService {

    /**
     * 保存飞检数据
     *
     * @param flightCheck
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/9 19:47
     */
    boolean saveFlightCheck(FlightCheck flightCheck, Integer currentUserId);

    /**
     * 根据ID获取飞检数据
     *
     * @param id
     * @return FlightCheckVo
     * @Author: jian.ye
     * @Date: 2019/11/9 19:47
     */
    FlightCheckVo getFlightCheckById(Integer id);

    /**
     * 根据条件获取飞检数据
     *
     * @param condition
     * @return PageInfo<FlightCheckVo>
     * @Author: jian.ye
     * @Date: 2019/11/9 19:47
     */
    PageInfo<FlightCheckVo> getFlightCheckPageList(FlightCheckCondition condition);

    /**
     * 删除飞检数据
     *
     * @param id
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/9 19:47
     */
    boolean deleteFlightCheck(Integer id, Integer currentUserId);

}
