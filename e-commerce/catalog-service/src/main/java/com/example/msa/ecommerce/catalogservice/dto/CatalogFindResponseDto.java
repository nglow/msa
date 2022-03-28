package com.example.msa.ecommerce.catalogservice.dto;

import com.example.msa.ecommerce.catalogservice.domain.persistence.Catalog;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CatalogFindResponseDto {

    private Long id;

    private String productId;

    private String productName;

    private Integer stock;

    private Integer unitPrice;

    private LocalDate createdAt;

    public static CatalogFindResponseDto from(Catalog catalog) {
        var instance = new CatalogFindResponseDto();
        instance.id = catalog.getId();
        instance.productId = catalog.getProductId();
        instance.productName = catalog.getProductName();
        instance.stock = catalog.getStock();
        instance.unitPrice = catalog.getUnitPrice();
        instance.createdAt = catalog.getCreatedAt();

        return instance;
    }
}
