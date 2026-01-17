package com.viheakode.electronic_store.service.serviceImp;

import com.viheakode.electronic_store.dto.request.ProductRequest;
import com.viheakode.electronic_store.dto.response.ProductDto;
import com.viheakode.electronic_store.exception.BadRequestException;
import com.viheakode.electronic_store.exception.NotFoundException;
import com.viheakode.electronic_store.model.Brand;
import com.viheakode.electronic_store.model.Category;
import com.viheakode.electronic_store.model.Product;
import com.viheakode.electronic_store.mapper.ProductMapper;
import com.viheakode.electronic_store.repository.BrandRepository;
import com.viheakode.electronic_store.repository.CategoryRepository;
import com.viheakode.electronic_store.repository.ProductRepository;
import com.viheakode.electronic_store.service.ProductService;
import com.viheakode.electronic_store.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductRequest productRequest) {

        try {
            Product product = new Product();
            product.setUuid(UUID.randomUUID().toString());
            product.setProductName(productRequest.getProductName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setDiscount(productRequest.getDiscount());

            Map<String, String> image = cloudinaryService.upload(productRequest.getImage());
            product.setImage(image.get("url"));

            Brand brand = brandRepository.findById(productRequest.getBrandId())
                    .orElseThrow(() -> new NotFoundException("Brand Not Found"));
            product.setBrand(brand);

            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category Not Found"));
            product.setCategory(category);

            product.setPublisher(SecurityUtil.getPublisher());
            productRepository.save(product);

            return ProductMapper.toDto(product);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductMapper::toDto).toList();
    }

    @Override
    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));
        return ProductMapper.toDto(product);
    }

    @Override
    public ProductDto update(Long productId, ProductRequest productRequest) {

        try {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product Not Found"));
            product.setUuid(UUID.randomUUID().toString());
            product.setProductName(productRequest.getProductName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setDiscount(productRequest.getDiscount());

            Map<String, String> image = cloudinaryService.upload(productRequest.getImage());
            product.setImage(image.get("url"));

            Brand brand = brandRepository.findById(productRequest.getBrandId())
                    .orElseThrow(() -> new NotFoundException("Brand Not Found"));
            product.setBrand(brand);

            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category Not Found"));
            product.setCategory(category);

            product.setPublisher(SecurityUtil.getPublisher());
            product.setModifiedDate(new Date());
            productRepository.save(product);

            return ProductMapper.toDto(product);

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public ProductDto delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));
        productRepository.deleteById(productId);
        return ProductMapper.toDto(product);
    }
}
