package com.project.silbaram.service;

import com.project.silbaram.dto.CategoryDTO;
import com.project.silbaram.vo.CategoryVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDTO> selectAll();
}
