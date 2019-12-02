package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.BannerCondition;
import com.zhuhong.inspection.model.Banner;
import com.zhuhong.inspection.vo.BannerVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 叶剑
 */
@Repository
public interface BannerMapper extends Mapper<Banner> {

    /**
     * 获取指定数量的轮播图
     * @param condition
     * @return List<Banner>
     * @Author: jian.ye
     * @Date: 2019/11/29 15:16
     */
    List<BannerVo> getViewBannerList(BannerCondition condition);

}
