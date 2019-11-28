package com.zhuhong.inspection.service.impl;

import com.zhuhong.inspection.mapper.LawTypeMapper;
import com.zhuhong.inspection.service.LawTypeService;
import com.zhuhong.inspection.vo.SelectionLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 叶剑
 */
@Service
public class LawTypeServiceImpl implements LawTypeService {

    @Autowired
    private LawTypeMapper lawTypeMapper;

    @Override
    public List<SelectionLabel> getLawTypeListByCode(String code) {
        return lawTypeMapper.getLawTypeListByCode(code);
    }

    @Override
    public List<SelectionLabel> getAllLawType() {
        return lawTypeMapper.getAllLawType();
    }
}
