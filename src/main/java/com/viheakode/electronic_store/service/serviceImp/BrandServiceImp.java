package com.viheakode.electronic_store.service.serviceImp;

import com.viheakode.electronic_store.dto.request.BrandRequest;
import com.viheakode.electronic_store.dto.response.BrandDto;
import com.viheakode.electronic_store.exception.NotFoundException;
import com.viheakode.electronic_store.mapper.BrandMapper;
import com.viheakode.electronic_store.model.Brand;
import com.viheakode.electronic_store.repository.BrandRepository;
import com.viheakode.electronic_store.service.BrandService;
import com.viheakode.electronic_store.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandDto createBrand(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setUuid(UUID.randomUUID().toString());
        brand.setBrandName(brandRequest.getBrandName());
        brand.setDescription(brandRequest.getDescription());
        brand.setPublisher(SecurityUtil.getPublisher());
        brandRepository.save(brand);
        return BrandMapper.toDto(brand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        List<Brand> brandDtoList = brandRepository.findAll();
        return brandDtoList.stream().map(BrandMapper::toDto).toList();
    }

    @Override
    public BrandDto getBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand Not Found"));
        return BrandMapper.toDto(brand);
    }

    @Override
    public BrandDto update(Long brandId, BrandRequest brandRequest) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand Not Found"));
        brand.setBrandName(brandRequest.getBrandName());
        brand.setDescription(brandRequest.getDescription());
        brand.setModifiedDate(new Date());
        brandRepository.save(brand);
        return BrandMapper.toDto(brand);
    }

    @Override
    public BrandDto delete(Long brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new NotFoundException("Brand Not Found"));
        brandRepository.deleteById(brandId);
        return BrandMapper.toDto(brand);
    }
}
