package com.viheakode.electronic_store.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRequest {
    private String productName;
    private String description;
    private MultipartFile image;
    private Double price;
    private Double discount;
    private Long brandId;
    private Long categoryId;
}
