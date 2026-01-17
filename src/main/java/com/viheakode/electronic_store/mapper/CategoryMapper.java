package com.viheakode.electronic_store.mapper;

import com.viheakode.electronic_store.dto.response.CategoryDto;
import com.viheakode.electronic_store.model.Category;
import org.springframework.beans.BeanUtils;

public class CategoryMapper {
    public static CategoryDto toDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }
}
