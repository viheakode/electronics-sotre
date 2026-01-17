package com.viheakode.electronic_store.mapper;

import com.viheakode.electronic_store.dto.response.BrandDto;
import com.viheakode.electronic_store.dto.response.CategoryDto;
import com.viheakode.electronic_store.dto.response.ProductDto;
import com.viheakode.electronic_store.model.Product;
import org.springframework.beans.BeanUtils;

public class ProductMapper {
    public static ProductDto toDto(Product product){

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);

        if (product.getBrand() != null){
            BrandDto brandDto =new BrandDto();
            BeanUtils.copyProperties(product.getBrand(), brandDto);
            productDto.setBrandDto(brandDto);
        }

        if (product.getCategory() != null){
            CategoryDto categoryDto =new CategoryDto();
            BeanUtils.copyProperties(product.getCategory(), categoryDto);
            productDto.setCategoryDto(categoryDto);
        }

        return productDto;
    }
}
