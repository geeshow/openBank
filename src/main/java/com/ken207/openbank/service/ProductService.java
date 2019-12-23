package com.ken207.openbank.service;

import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.domain.RateEntity;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long createProduct(ProductDto.Create createProductDto) {

        ProductEntity product = ProductEntity.createProduct(createProductDto);
        ProductEntity newProduct = productRepository.save(product);

        return newProduct.getId();
    }
}
