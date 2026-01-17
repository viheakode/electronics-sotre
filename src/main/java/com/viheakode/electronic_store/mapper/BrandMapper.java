package com.viheakode.electronic_store.mapper;

import com.viheakode.electronic_store.dto.response.BrandDto;
import com.viheakode.electronic_store.model.Brand;
import org.springframework.beans.BeanUtils;

public class BrandMapper {
    public static BrandDto toDto(Brand brand){
        BrandDto brandDto = new BrandDto();
        BeanUtils.copyProperties(brand, brandDto);
        return brandDto;
    }
}
