package com.example.msa.ecommerce.catalogservice.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
