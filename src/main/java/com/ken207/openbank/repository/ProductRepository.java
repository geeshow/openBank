package com.ken207.openbank.repository;

import com.ken207.openbank.domain.products.ProductRgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductRgEntity, Long> {
}
