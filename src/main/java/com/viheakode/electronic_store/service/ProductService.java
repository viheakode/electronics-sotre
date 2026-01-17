package com.viheakode.electronic_store.service;

import com.viheakode.electronic_store.dto.request.ProductRequest;
import com.viheakode.electronic_store.dto.response.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductRequest productRequest);
    List<ProductDto> getAllProducts();
    ProductDto getProduct(Long productId);
    ProductDto update(Long productId, ProductRequest productRequest);
    ProductDto delete(Long productId);
}
