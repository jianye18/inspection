package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.LawType;
import com.zhuhong.inspection.vo.SelectionLabel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 叶剑
 */
@Repository
public interface LawTypeMapper extends Mapper<LawType> {

    /**
     * 根据code获取所有子分类
     * @param code
     * @return List<SelectionLabel>
     * @Author: jian.ye
     * @Date: 2019/11/22 16:12
     */
    List<SelectionLabel> getLawTypeListByCode(@Param(value = "code") String code);

}
