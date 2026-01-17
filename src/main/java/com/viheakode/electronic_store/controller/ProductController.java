package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.ProductRequest;
import com.viheakode.electronic_store.dto.response.ProductDto;
import com.viheakode.electronic_store.service.serviceImp.ProductServiceImp;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@ModelAttribute ProductRequest productRequest, HttpServletRequest request){
        ProductDto productDto = productServiceImp.createProduct(productRequest);
        return ApiResponseStructure.response(true, 201, "Created", productDto, request.getRequestURI(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(HttpServletRequest request){
        List<ProductDto> productDtoList = productServiceImp.getAllProducts();
        return ApiResponseStructure.response(true, 200, "Ok", productDtoList, request.getRequestURI(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id, HttpServletRequest request){
        ProductDto productDto = productServiceImp.getProduct(id);
        return ApiResponseStructure.response(true, 200, "Ok", productDto, request.getRequestURI(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(@PathVariable Long id, @ModelAttribute ProductRequest productRequest, HttpServletRequest request){
        ProductDto productDto = productServiceImp.update(id, productRequest);
        return ApiResponseStructure.response(true, 201, "Updated", productDto, request.getRequestURI(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, HttpServletRequest request){
        ProductDto productDto = productServiceImp.delete(id);
        return ApiResponseStructure.response(true, 200, "Deleted", productDto, request.getRequestURI(), HttpStatus.OK);
    }
}
