package com.zhuhong.inspection.service;

import com.zhuhong.inspection.model.Annex;

import java.util.List;

/**
 * 附件业务接口类
 * @Author: jian.ye
 * @Date: 2019/10/23 19:58
 */
public interface AnnexService {

    /**
     * 保存附件信息
     * @param isUpdate
     * @param annexList
     * @param businessId
     * @Author: jian.ye
     * @Date: 2019/11/22 16:37
     */
    void saveAnnex(boolean isUpdate, List<Annex> annexList, Integer businessId, Integer type);

    /**
     * 根据业务ID和类型删除附件数据
     * @param businessId
     * @param type
     * @Author: jian.ye
     * @Date: 2019/10/23 20:11
     */
    void deleteAnnex(Integer businessId, Integer type);

    /**
     * 根据业务ID和类型获取附件内容
     *
     * @param businessId
     * @param type
     * @return List<Annex>
     * @Author: jian.ye
     * @Date: 2019/10/27 18:35
     */
    List<Annex> getAnnexList(Integer businessId, Integer type);

}
