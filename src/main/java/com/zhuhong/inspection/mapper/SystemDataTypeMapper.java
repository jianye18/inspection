package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.SystemDataTypeCondition;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.vo.SystemDataTypeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 系统分类数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:40
 */
@Repository
public interface SystemDataTypeMapper extends Mapper<SystemDataType> {

    /**
     * 根据条件获取系统常量分类数据
     * @param condition
     * @return List<SystemDataTypeVo>
     * @Author: jian.ye
     * @Date: 2019/10/23 9:07
     */
    List<SystemDataTypeVo> getSystemDataTypeList(SystemDataTypeCondition condition);

    /**
     * 获取指定类型的最大value值
     * @param code
     * @param param
     * @return int
     * @Author: jian.ye
     * @Date: 2019/11/1 17:41
     */
    int getMaxValueByParam(@Param(value = "code") String code, @Param(value = "param") String param);

}
