package com.viheakode.electronic_store.service;

import com.viheakode.electronic_store.dto.request.BrandRequest;
import com.viheakode.electronic_store.dto.response.BrandDto;

import java.util.List;

public interface BrandService {
    BrandDto createBrand(BrandRequest brandRequest);
    List<BrandDto> getAllBrands();
    BrandDto getBrand(Long brandId);
    BrandDto update(Long brandId, BrandRequest brandRequest);
    BrandDto delete(Long brandId);
}
