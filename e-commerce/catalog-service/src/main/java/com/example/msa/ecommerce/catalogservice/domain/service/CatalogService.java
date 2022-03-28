package com.example.msa.ecommerce.catalogservice.domain.service;

import com.example.msa.ecommerce.catalogservice.domain.persistence.Catalog;
import com.example.msa.ecommerce.catalogservice.domain.persistence.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public List<CatalogFindResponseDto> findAllCatalogs() {
        return catalogRepository.findAll().stream()
                .map(CatalogFindResponseDto::from).collect(Collectors.toList());
    }
}
