package com.project.silbaram.dao;

import com.project.silbaram.dto.CategoryDTO;
import com.project.silbaram.dto.LanguageDTO;
import com.project.silbaram.vo.LanguageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LanguageDAO {
    LanguageVO getLanguageById(Long lgid);
    List<LanguageDTO> selectAll();
}