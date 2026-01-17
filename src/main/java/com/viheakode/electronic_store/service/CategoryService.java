package com.viheakode.electronic_store.service;

import com.viheakode.electronic_store.dto.request.CategoryRequest;
import com.viheakode.electronic_store.dto.response.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryRequest categoryRequest);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategory(Long categoryId);
    CategoryDto update(Long categoryId, CategoryRequest categoryRequest);
    CategoryDto delete(Long categoryId);
}
