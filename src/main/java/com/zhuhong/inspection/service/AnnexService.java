package com.zhuhong.inspection.service;

/**
 * 附件业务接口类
 * @Author: jian.ye
 * @Date: 2019/10/23 19:58
 */
public interface AnnexService {

    /**
     * 处理标准和法规的附件数据
     * @param isUpdate
     * @param annexs
     * @param businessId
     * @param type
     * @Author: jian.ye
     * @Date: 2019/10/23 20:01
     */
    void handleAnnex(boolean isUpdate, String annexs, Integer businessId, Integer type);

    /**
     * 根据业务ID和类型删除附件数据
     * @param businessId
     * @param type
     * @Author: jian.ye
     * @Date: 2019/10/23 20:11
     */
    void deleteAnnex(Integer businessId, Integer type);

}
