package com.viheakode.electronic_store.dto.response;

import com.viheakode.electronic_store.model.Brand;
import com.viheakode.electronic_store.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {

    private Long productId;
    private String uuid;
    private String productName;
    private String description;
    private String image;
    private Double price;
    private Double discount;

    private BrandDto brandDto;

    private CategoryDto categoryDto;

    private String status;
    private String publisher;
    private Date publishedDate;
    private Date modifiedDate;
}
