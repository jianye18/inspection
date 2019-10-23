package com.zhuhong.inspection.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.zhuhong.inspection.mapper.AnnexMapper;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.service.AnnexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnexServiceImpl implements AnnexService {

    @Autowired
    private AnnexMapper annexMapper;
    @Value("${upload_path}")
    private String FILE_DIR;

    @Override
    public void handleAnnex(boolean isUpdate, String annexs, Integer businessId, Integer type) {
        if (isUpdate) {
            Annex annex = new Annex();
            annex.setBusinessId(businessId);
            annex.setType(type);
            annexMapper.delete(annex);
        }
        List<Annex> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(annexs)) {
            JSONArray jsonArray = JSONArray.parseArray(annexs);
            for (Object o : jsonArray) {
                Annex annex = new Annex();
                String name = (String) o;
                annex.setName(name);
                annex.setPath(FILE_DIR + name);
                annex.setType(type);
                annex.setBusinessId(businessId);
                list.add(annex);
            }
            annexMapper.insertList(list);
        }
    }

    @Override
    public void deleteAnnex(Integer businessId, Integer type) {
        Annex annex = new Annex();
        annex.setBusinessId(businessId);
        annex.setType(type);
        annexMapper.delete(annex);
    }

}
