package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.LinkCondition;
import com.zhuhong.inspection.model.Link;

import java.util.List;

/**
 * @author 叶剑
 */
public interface LinkService {

    /**
     * 保存友情链接信息
     * @param link
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/10 10:39
     */
    boolean saveLink(Link link, Integer currentUserId);

    /**
     * 分页获取友情链接信息
     * @param condition
     * @return PageInfo<Link>
     * @Author: jian.ye
     * @Date: 2019/12/10 10:41
     */
    PageInfo<Link> getLinkPageList(LinkCondition condition);

    /**
     * 删除友情链接信息
     * @param linkId
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/12/10 10:42
     */
    boolean deleteLink(Integer linkId, Integer currentUserId);

    /**
     * 获取所有展示的友情链接
     * @return List<Link>
     * @Author: jian.ye
     * @Date: 2019/12/10 10:43
     */
    List<Link> getLinkViewList();

}
