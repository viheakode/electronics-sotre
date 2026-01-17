package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.CategoryRequest;
import com.viheakode.electronic_store.dto.response.CategoryDto;
import com.viheakode.electronic_store.service.serviceImp.CategoryServiceImp;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CategoryRequest categoryRequest, HttpServletRequest request){
        CategoryDto categoryDto = categoryServiceImp.createCategory(categoryRequest);
        return ApiResponseStructure.response(true, 201, "Created", categoryDto, request.getRequestURI(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(HttpServletRequest request){
        List<CategoryDto> categoryDtoList = categoryServiceImp.getAllCategories();
        return ApiResponseStructure.response(true, 200, "Ok", categoryDtoList, request.getRequestURI(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id, HttpServletRequest request){
        CategoryDto categoryDto = categoryServiceImp.getCategory(id);
        return ApiResponseStructure.response(true, 200, "Ok", categoryDto, request.getRequestURI(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest, HttpServletRequest request){
        CategoryDto categoryDto = categoryServiceImp.update(id, categoryRequest);
        return ApiResponseStructure.response(true, 201, "Updated", categoryDto, request.getRequestURI(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, HttpServletRequest request){
        CategoryDto categoryDto = categoryServiceImp.delete(id);
        return ApiResponseStructure.response(true, 200, "Deleted", categoryDto, request.getRequestURI(), HttpStatus.OK);
    }

}
