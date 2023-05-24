package com.project.silbaram.service;

import com.project.silbaram.dao.CategoryDAO;
import com.project.silbaram.dto.CategoryDTO;
import com.project.silbaram.vo.CategoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService{
    private final CategoryDAO categoryDAO;

    public List<CategoryDTO> selectAll() {
        return categoryDAO.selectAll();
    }

}
