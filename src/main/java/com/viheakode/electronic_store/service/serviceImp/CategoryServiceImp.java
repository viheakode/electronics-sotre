package com.viheakode.electronic_store.service.serviceImp;

import com.viheakode.electronic_store.dto.request.CategoryRequest;
import com.viheakode.electronic_store.dto.response.CategoryDto;
import com.viheakode.electronic_store.exception.NotFoundException;
import com.viheakode.electronic_store.mapper.CategoryMapper;
import com.viheakode.electronic_store.model.Category;
import com.viheakode.electronic_store.repository.CategoryRepository;
import com.viheakode.electronic_store.service.CategoryService;
import com.viheakode.electronic_store.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setUuid(UUID.randomUUID().toString());
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        category.setPublisher(SecurityUtil.getPublisher());
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category Not Found"));
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category Not Found"));
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        category.setModifiedDate(new Date());
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Override
    public CategoryDto delete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category Not Found"));
        categoryRepository.deleteById(categoryId);
        return CategoryMapper.toDto(category);
    }
}
