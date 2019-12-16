package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SpotCheckVo;

import java.util.List;

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

    /**
     * 保存抽检数据
     * @param spotCheck
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/7 13:54
     */
    boolean saveSpotCheck(SpotCheck spotCheck, Integer currentUserId);

    /**
     * 根据ID获取抽检数据
     * @param id
     * @return SpotCheckVo
     * @Author: jian.ye
     * @Date: 2019/10/31 16:12
     */
    SpotCheckVo getSpotCheckById(Integer id);

    /**
     * 获取抽检数据的公布机构
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/22 11:34
     */
    List<SelectionLabel> getAllInstitution();

    /**
     * 获取产品类型集合
     * @Author: jian.ye
     * @Date: 2019/11/13 20:59
     */
    List<SelectionLabel> getProductTypeList();

    /**
     * 删除抽检数据
     * @param id
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/7 11:51
     */
    boolean deleteSpotCheck(Integer id, Integer currentUserId);

    /**
     * 获取抽检数据总数
     * @param isNew
     * @return int
     * @Author: jian.ye
     * @Date: 2019/12/13 14:59
     */
    int getSpotCheckTotalCount(Integer isNew);

}
