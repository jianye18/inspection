package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.SystemDataType;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 系统分类数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:40
 */
@Repository
public interface SystemDataTypeMapper extends Mapper<SystemDataType> {
}
