package com.zhuhong.inspection.service;


import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseCondition;
import com.zhuhong.inspection.model.DatabaseBak;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author yejian
 */
public interface DatabaseService {

    /**
     * 备份数据库
     * @param rootPath
     * @param userId
     * @return File
     * @Author: jian.ye
     * @Date: 2020/3/18 0018 15:15
     */
    File backUp(String rootPath, Integer userId) throws Exception;

    /**
     * 恢复数据库
     * @param file
     * @param userId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2020/3/20 0020 15:46
     */
    boolean recover(MultipartFile file, String rootPath, Integer userId);

    /**
     * 分页获取备份及恢复的记录数据
     * @param condition
     * @return PageInfo<DatabaseBak>
     * @Author: jian.ye
     * @Date: 2020/3/20 0020 14:50
     */
    PageInfo<DatabaseBak> getDatabaseBakPageList(BaseCondition condition);

}
