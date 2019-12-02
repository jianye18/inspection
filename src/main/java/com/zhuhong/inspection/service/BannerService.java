package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.BannerCondition;
import com.zhuhong.inspection.model.Banner;
import com.zhuhong.inspection.vo.BannerVo;

import java.util.List;

/**
 * @author 叶剑
 */
public interface BannerService {

    /**
     * 保存轮播图信息
     * @param banner
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/29 15:22
     */
    boolean saveBanner(Banner banner, Integer currentUserId);

    /**
     * 分页获取轮播图信息
     * @param condition
     * @return PageInfo<Banner>
     * @Author: jian.ye
     * @Date: 2019/11/29 15:23
     */
    PageInfo<BannerVo> getBannerPageList(BannerCondition condition);

    /**
     * 根据ID获取轮播图信息
     * @param bannerId
     * @return Banner
     * @Author: jian.ye
     * @Date: 2019/11/29 15:24
     */
    Banner getBannerById(Integer bannerId);

    /**
     * 删除轮播图
     * @param bannerId
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/29 15:24
     */
    boolean deleteBanner(Integer bannerId, Integer currentUserId);

    /**
     * 获取指定数量的轮播图
     * @param condition
     * @return List<Banner>
     * @Author: jian.ye
     * @Date: 2019/11/29 16:13
     */
    List<BannerVo> getViewBannerList(BannerCondition condition);

    /**
     * 设置轮播图是否可见
     * @param bannerId
     * @param isView
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/2 17:01
     */
    boolean viewBanner(Integer bannerId, Integer isView, Integer currentUserId);

}
