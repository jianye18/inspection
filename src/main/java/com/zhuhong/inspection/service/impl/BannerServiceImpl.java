package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.BannerCondition;
import com.zhuhong.inspection.mapper.BannerMapper;
import com.zhuhong.inspection.model.Banner;
import com.zhuhong.inspection.service.BannerService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.FileUtil;
import com.zhuhong.inspection.vo.BannerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 叶剑
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Value("${upload_path}")
    private String fileDir;

    @Override
    public boolean saveBanner(Banner banner, Integer currentUserId) {
        Date current = DateUtil.getCurrentDate();
        banner.setUpdateId(currentUserId);
        banner.setUpdateTime(current);
        if (banner.getId() == null) {
            banner.setCreateId(currentUserId);
            banner.setCreateTime(current);
            return bannerMapper.insertSelective(banner) > 0;
        } else {
            return bannerMapper.updateByPrimaryKeySelective(banner) > 0;
        }
    }

    @Override
    public PageInfo<BannerVo> getBannerPageList(BannerCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        condition.setBaseUrl(fileDir);
        List<BannerVo> list = bannerMapper.getViewBannerList(condition);
        return new PageInfo<>(list);
    }

    @Override
    public Banner getBannerById(Integer bannerId) {
        return bannerMapper.selectByPrimaryKey(bannerId);
    }

    @Override
    public boolean deleteBanner(Integer bannerId, Integer currentUserId) {
        boolean flag = false;
        Banner banner = getBannerById(bannerId);
        Date current = DateUtil.getCurrentDate();
        banner.setUsable(Banner.ENABLE_0);
        banner.setUpdateId(currentUserId);
        banner.setUpdateTime(current);
        if (bannerMapper.updateByPrimaryKeySelective(banner) > 0) {
            flag = true;
            FileUtil.delAllFile(fileDir + banner.getPath() + banner.getName());
        }
        return flag;
    }

    @Override
    public List<BannerVo> getViewBannerList(BannerCondition condition) {
        condition.setBaseUrl(fileDir);
        return bannerMapper.getViewBannerList(condition);
    }

    @Override
    public boolean viewBanner(Integer bannerId, Integer isView, Integer currentUserId) {
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setIsView(isView);
        banner.setUpdateId(currentUserId);
        banner.setUpdateTime(DateUtil.getCurrentDate());
        return bannerMapper.updateByPrimaryKeySelective(banner) > 0;
    }
}
