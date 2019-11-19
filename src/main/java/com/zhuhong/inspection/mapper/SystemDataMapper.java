package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.SystemData;
import com.zhuhong.inspection.vo.SelectionLabel;
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

}
