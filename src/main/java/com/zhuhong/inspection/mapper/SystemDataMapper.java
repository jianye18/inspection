package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.SystemDataCondition;
import com.zhuhong.inspection.model.SystemData;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SystemDataVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 叶剑
 */
@Repository
public interface SystemDataMapper extends Mapper<SystemData> {

    /**
     * 根据父级编码获取常量数据
     * @param typeCode
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/19 15:29
     */
    List<SelectionLabel> getSystemDataByTypeCode(@Param(value = "typeCode") String typeCode);

    /**
     * 获取所有系统分类
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/21 9:29
     */
    List<SelectionLabel> getAllSystemType();

    /**
     * 根据条件获取系统分类常量数据
     * @param condition
     * @return List<SystemDataVo>
     * @Author: jian.ye
     * @Date: 2019/11/21 11:17
     */
    List<SystemDataVo> getSystemDataPageList(SystemDataCondition condition);

    /**
     * 根据传入的type获取所有分类常量数据
     * @param type
     * @param isView
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/27 19:34
     */
    List<SelectionLabel> getHomeShowSystemData(@Param(value = "type") String type, @Param(value = "isView") Integer isView);

}
