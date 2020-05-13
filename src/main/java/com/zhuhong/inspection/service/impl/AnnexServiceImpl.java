package com.zhuhong.inspection.service.impl;

import com.zhuhong.inspection.mapper.AnnexMapper;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.service.AnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 叶剑
 */
@Service
public class AnnexServiceImpl implements AnnexService {

    @Autowired
    private AnnexMapper annexMapper;
    @Value("${upload_path}")
    private String FILE_DIR;

    @Override
    public void saveAnnex(boolean isUpdate, List<Annex> annexList, Integer businessId, Integer type) {
        if (isUpdate) {
            Annex annex = new Annex();
            annex.setBusinessId(businessId);
            annex.setType(type);
            annexMapper.delete(annex);
        }
        if (annexList != null && annexList.size() > 0) {
            for (Annex annex : annexList) {
                annex.setPath(FILE_DIR + "docs/" + annex.getName());
                annex.setBusinessId(businessId);
                annex.setType(type);
            }
            annexMapper.insertList(annexList);
        }
    }

    @Override
    public void deleteAnnex(Integer businessId, Integer type) {
        Annex annex = new Annex();
        annex.setBusinessId(businessId);
        annex.setType(type);
        annexMapper.delete(annex);
    }

    @Override
    public List<Annex> getAnnexList(Integer businessId, Integer type) {
        Annex annex = new Annex();
        annex.setBusinessId(businessId);
        annex.setType(type);
        return annexMapper.select(annex);
    }

}
