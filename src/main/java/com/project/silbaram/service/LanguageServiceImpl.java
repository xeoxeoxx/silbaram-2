package com.project.silbaram.service;

import com.project.silbaram.dao.LanguageDAO;
import com.project.silbaram.dto.CategoryDTO;
import com.project.silbaram.dto.LanguageDTO;
import com.project.silbaram.vo.LanguageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageDAO languageDAO;
    private final ModelMapper modelMapper;

    @Override
    public Long getLgidByName(Long lgid) {
        LanguageVO languageVO = languageDAO.getLanguageById(lgid);
        if (languageVO == null) {
            throw new IllegalArgumentException("Invalid language: " + lgid);
        }
        return languageVO.getLgid();
    }

    @Override
    public List<LanguageDTO> selectAll() {
        return languageDAO.selectAll();
    }
}
