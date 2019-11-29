package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.BannerCondition;
import com.zhuhong.inspection.mapper.BannerMapper;
import com.zhuhong.inspection.model.Banner;
import com.zhuhong.inspection.service.BannerService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public PageInfo<Banner> getBannerPageList(BannerCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        Example example = new Example(Banner.class);
        Example.Criteria criteria = example.createCriteria();
        if (condition.getIsView() != null) {
            criteria.andEqualTo("isView", condition.getIsView());
        }
        if (StringUtils.isNotEmpty(condition.getSearchPhrase())) {
            criteria.andLike("name", condition.getSearchPhrase());
        }
        example.setOrderByClause("update_time desc");
        List<Banner> list = bannerMapper.selectByExample(example);
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
        banner.setUpdateId(currentUserId);
        banner.setUpdateTime(current);
        if (bannerMapper.updateByPrimaryKeySelective(banner) > 0) {
            flag = true;
            FileUtil.delAllFile(banner.getPath() + banner.getName());
        }
        return flag;
    }

    @Override
    public List<Banner> getViewBannerList(BannerCondition condition) {
        condition.setBaseUrl(fileDir);
        return bannerMapper.getViewBannerList(condition);
    }
}
