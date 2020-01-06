package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SpotCheckVo;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据ID获取抽检数据
     * @param condition
     * @return SpotCheckVo
     * @Author: jian.ye
     * @Date: 2019/10/31 16:13
     */
    SpotCheckVo getSpotCheckById(SpotCheckCondition condition);

    /**
     * 获取发布机构集合
     * @Author: jian.ye
     * @Date: 2019/11/13 20:59
     */
    List<SelectionLabel> getInstitutionList();

    /**
     * 获取产品类型集合
     * @Author: jian.ye
     * @Date: 2019/11/13 20:59
     */
    List<SelectionLabel> getProductTypeList();

    /**
     * 获取样品名称集合
     * @Author: jian.ye
     * @Date: 2019/11/13 20:59
     */
    List<SelectionLabel> getSampleTypeList();

    /**
     * 根据条件获取抽检数量
     * @param isNew
     * @return int
     * @Author: jian.ye
     * @Date: 2019/12/16 11:42
     */
    int getSpotCheckCount(@Param(value = "isNew") Integer isNew);

}
