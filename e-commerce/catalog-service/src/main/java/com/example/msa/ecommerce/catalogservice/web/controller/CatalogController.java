package com.example.msa.ecommerce.catalogservice.web.controller;

import com.example.msa.ecommerce.catalogservice.domain.service.CatalogFindResponseDto;
import com.example.msa.ecommerce.catalogservice.domain.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/health-check")
    public String status(HttpServletRequest request) {
        return String.format("It's working in catalog service on port %s", request.getServerPort());
    }

    @GetMapping("/catalogs")
    public List<CatalogFindResponseDto> findAllCatalogs() {
        return catalogService.findAllCatalogs();
    }
}
