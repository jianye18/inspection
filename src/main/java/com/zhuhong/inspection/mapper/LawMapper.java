package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.LawCondition;
import com.zhuhong.inspection.model.Law;
import com.zhuhong.inspection.vo.LawVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 法规数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/22 23:09
 */
@Repository
public interface LawMapper extends Mapper<Law> {

    /**
     * 根据条件获取法规信息
     * @param condition
     * @return List<LawVo>
     * @Author: jian.ye
     * @Date: 2019/10/23 13:48
     */
    List<LawVo> getLawListByCondition(LawCondition condition);

    /**
     * 根据ID获取法规数据
     * @param condition
     * @return LawVo
     * @Author: jian.ye
     * @Date: 2019/11/1 14:10
     */
    LawVo getLawById(LawCondition condition);

}
