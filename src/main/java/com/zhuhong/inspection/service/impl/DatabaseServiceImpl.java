package com.zhuhong.inspection.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseCondition;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.mapper.DatabaseBakMapper;
import com.zhuhong.inspection.model.DatabaseBak;
import com.zhuhong.inspection.service.DatabaseService;
import com.zhuhong.inspection.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author yejian
 * @Date 2020/3/18 0018 15:14
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    DatabaseBakMapper mapper;

    @Value("${spring.datasource.username}")
    String data_user;
    @Value("${spring.datasource.password}")
    String data_password;

    @Override
    public File backUp(String rootPath, Integer userId) throws Exception {
        String name = DateUtil.toDateString(LocalDate.now(), DateUtil.DATE_FORMATER) + "-bak.sql";
        String pathSql = rootPath + "database-bak/" + name;
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            fileSql.createNewFile();
        }
        createDatabaseBak(name, "备份数据库", Constants.DATABASE_BAK_TYPE_1, userId);
        handleDatabase(pathSql, Constants.DATABASE_BAK_TYPE_1);
        return fileSql;
    }

    @Override
    public boolean recover(MultipartFile file, String rootPath, Integer userId) {
        rootPath = rootPath + "database-bak/";
        File f = new File(rootPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String name = DateUtil.toDateString(LocalDate.now(), DateUtil.DATE_FORMATER) +
                "-" +
                System.currentTimeMillis() +
                "-bak.sql";
        String pathSql = rootPath +
                name;
        File sqlBak = new File(pathSql);
        try {
            file.transferTo(sqlBak);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createDatabaseBak(name, "恢复数据库", Constants.DATABASE_BAK_TYPE_2, userId);
        handleDatabase(pathSql, Constants.DATABASE_BAK_TYPE_2);
        return true;
    }

    private void handleDatabase(String pathSql, Integer type) {
        /**
         * 备份命令：mysqldump -hlocalhost -uroot -p123456 db > /home/back.sql
         * 恢复命令：mysql -hlocalhost -uroot -p123456 db < /home/back.sql
         */
        try {
            StringBuffer sb = new StringBuffer();
            if (type == 1) {
                sb.append("mysqldump");
            } else {
                sb.append("mysql");
            }
            sb.append(" -h127.0.0.1");
            sb.append(" -u" + data_user);
            sb.append(" -p" + data_password);
            if (type == 1) {
                sb.append(" " + "inspection" + " >");
            } else {
                sb.append(" " + "inspection" + " <");
            }
            sb.append(pathSql);
            System.out.println("cmd命令为："+sb.toString());
            Runtime runtime = Runtime.getRuntime();
            if (type == 1) {
                System.out.println("开始备份：inspection");
            } else {
                System.out.println("开始恢复：inspection");
            }
            String str = "";
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                str = "cmd /c";
            } else {
                str = "/bin/sh -c";
            }
            Process process = runtime.exec(str + sb.toString());
            if (type == 1) {
                System.out.println("备份成功!" + JSON.toJSONString(process));
            } else {
                System.out.println("恢复成功!" + JSON.toJSONString(process));
            }
            InputStream is = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is,"utf8"));
            String line = null;
            while ((line=bf.readLine())!=null){
                System.out.println(line);
            }
            is.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageInfo<DatabaseBak> getDatabaseBakPageList(BaseCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize(), "create_time desc");
        List<DatabaseBak> list = mapper.selectAll();
        return new PageInfo<>(list);
    }

    private void createDatabaseBak(String name, String remark, Integer type, Integer userId){
        DatabaseBak bak = new DatabaseBak();
        bak.setName(name);
        bak.setRemark(remark);
        bak.setType(type);
        bak.setCreateId(userId);
        bak.setCreateTime(new Date());
        mapper.insertSelective(bak);
    }

}
