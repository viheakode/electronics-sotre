package com.viheakode.electronic_store.controller;

import com.viheakode.electronic_store.dto.request.BrandRequest;
import com.viheakode.electronic_store.dto.response.BrandDto;
import com.viheakode.electronic_store.service.serviceImp.BrandServiceImp;
import com.viheakode.electronic_store.util.ApiResponseStructure;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {

    @Autowired
    private BrandServiceImp brandServiceImp;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody BrandRequest brandRequest, HttpServletRequest request){
        BrandDto brandDto = brandServiceImp.createBrand(brandRequest);
        return ApiResponseStructure.response(true, 201, "Created", brandDto, request.getRequestURI(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(HttpServletRequest request){
        List<BrandDto> brandDtoList = brandServiceImp.getAllBrands();
        return ApiResponseStructure.response(true, 200, "Ok", brandDtoList, request.getRequestURI(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id, HttpServletRequest request){
        BrandDto brandDto = brandServiceImp.getBrand(id);
        return ApiResponseStructure.response(true, 200, "Ok", brandDto, request.getRequestURI(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody BrandRequest brandRequest, HttpServletRequest request){
        BrandDto brandDto = brandServiceImp.update(id, brandRequest);
        return ApiResponseStructure.response(true, 200, "Updated", brandDto, request.getRequestURI(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, HttpServletRequest request){
        BrandDto brandDto = brandServiceImp.delete(id);
        return ApiResponseStructure.response(true, 200, "Deleted", brandDto, request.getRequestURI(), HttpStatus.OK);
    }
}
