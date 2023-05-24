package com.project.silbaram.dao;

import com.project.silbaram.dto.CategoryDTO;

import java.util.List;

public interface CategoryDAO {
    List<CategoryDTO> selectAll();
}
